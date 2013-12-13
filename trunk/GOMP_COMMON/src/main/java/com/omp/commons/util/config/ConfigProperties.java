package com.omp.commons.util.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.InvalidPropertiesFormatException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 컨피그 용도의 프로퍼티 로더
 * 로딩된 컨피그는 업데이트 설정하지 않는한 로드된 내용을 반환합니다.
 * 프로퍼티간의 참조 표현 및 로컬 프로퍼티 덮어쓰기 기능 및 시스템 프로퍼티 통합을 추가 했습니다. 
 * @author kbeui
 *
 */
public class ConfigProperties
{
	/**
	 * readonly properties
	 * @author kbeui
	 */
	@SuppressWarnings("serial")
	private static class ReadOnlyProperties
		extends Properties {

		private boolean needUpdate	= true;

		@Override
		public synchronized void load(InputStream inStream) throws IOException {
			throw new UnsupportedOperationException("this properties cat not update.");
		}

		@Override
		public synchronized void loadFromXML(InputStream in) throws IOException,
				InvalidPropertiesFormatException {
			throw new UnsupportedOperationException("this properties cat not update.");
		}

		@Override
		public synchronized Object setProperty(String key, String value) {
			throw new UnsupportedOperationException("this properties cat not update.");
		}

		@Override
		public synchronized void clear() {
			throw new UnsupportedOperationException("this properties cat not update.");
		}

		@Override
		public Set<java.util.Map.Entry<Object, Object>> entrySet() {
			throw new UnsupportedOperationException("this properties cat not update.");
		}

		@Override
		public synchronized Object put(Object key, Object value) {
			throw new UnsupportedOperationException("this properties cat not update.");
		}

		@Override
		public synchronized void putAll(Map<? extends Object, ? extends Object> t) {
			throw new UnsupportedOperationException("this properties cat not update.");
		}

		@Override
		public synchronized Object remove(Object key) {
			throw new UnsupportedOperationException("this properties cat not update.");
		}
		
		private synchronized void replace(Properties props) {
			super.clear();
			if (props != null) {
				for (Object key: props.keySet()) {
					super.put(key, props.get(key));
				}
			}
		}
	}
	
    private static ReadOnlyProperties				loadedProps			= new ReadOnlyProperties();
    private static String							resourcePath;
    private static Pattern							PTN_REF_EXPR	= Pattern.compile("\\$\\{([a-zA-Z0-9\\.\\-\\_]+?)\\}");
    private static Set<ConfigUpdateEventListener>	listeners		= new HashSet<ConfigUpdateEventListener>();
    
    
    private Properties	props; 
    
    /**
     * 생성자
     * 이미 생성 되어 있다면 다시 로드 하지 않습니다.
     */
    public ConfigProperties() {
        synchronized (ConfigProperties.loadedProps) {
	    	if (ConfigProperties.loadedProps.needUpdate) {
	    		doInitialize(ConfigProperties.resourcePath);
	    	}
        }
        this.props	= ConfigProperties.loadedProps;
    }
    
    /**
     * 이벤트 통지용 객체 내부 생성자
     * @param props
     */
    private ConfigProperties(ReadOnlyProperties props) {
    	this.props	= props;
    }
    
    /**
     * 컨피그 업데이트 이벤트 리스너를 추가 합니다.
     * @param listener
     */
    public static void addListener(ConfigUpdateEventListener listener) {
    	ConfigProperties.listeners.add(listener);
    }
    
    /**
     * 컨피그 업데이트 이벤트 리스너를 제거 합니다.
     * @param listener
     */
    public static void removeListener(ConfigUpdateEventListener listener) {
    	ConfigProperties.listeners.remove(listener);
    }

    /**
     * 초기화 (로드)
     * @param resourcePath 대상 properties 파일의 resource path
     */
    public static void doInitialize(String resourcePath) {
        synchronized (ConfigProperties.loadedProps) {
            try {
            	Properties							workProps;
            	Set<String>							refHistory;
            	int									sp;
                String 								localResourcePath;
                Iterator<ConfigUpdateEventListener> itrListeners;
                ConfigProperties					conf;
                Log									logger;
            	
            	workProps	= new Properties();
            	
            	// system Properties 복사
            	for (Object key : System.getProperties().keySet()) {
            		workProps.setProperty("sysprop." + key, System.getProperty((String)key));
            	}
            	
            	// 공통 properties 로드
            	loadProperty("/conf/omp.common.properties", workProps);
            	
            	// 대상 properties 로드
            	if (!loadProperty(resourcePath, workProps)) {
            		throw new RuntimeException("properties resource '" + resourcePath + "' is not found."); 
            	}
            	
            	// 추가 properties 로드
                loadExtendedProperty(workProps);
                
            	// 로컬 properties 로드
                sp	= resourcePath.lastIndexOf("."); 
                if (sp > 0) {
                	localResourcePath	= resourcePath.substring(0, sp) + ".local" + resourcePath.substring(sp);
                } else {
                	localResourcePath	= resourcePath + ".local";
                }
                loadProperty(localResourcePath, workProps);
                
                // property 간의 참조 처리
                refHistory	= new HashSet<String>();
            	for (Object key : workProps.keySet()) {
            		String	propName;
            		
            		propName	= (String)key;
            		if (propName.indexOf("sysprop.") == 0) {
            			continue;
            		}
            		refHistory.clear();
            		resolvePropertyValue(propName, workProps, refHistory);
            	}
            	
            	ConfigProperties.loadedProps.replace(workProps);
            	ConfigProperties.loadedProps.needUpdate	= false;
            	ConfigProperties.resourcePath		= resourcePath;
            	
            	// 이벤트 통지
            	conf			= new ConfigProperties(ConfigProperties.loadedProps);
            	logger			= LogFactory.getLog(ConfigProperties.class);
            	itrListeners	= ConfigProperties.listeners.iterator();
            	while (itrListeners.hasNext()) {
            		ConfigUpdateEventListener listener;
            		
            		listener	= itrListeners.next();
            		try {
            			listener.configUpdated(conf);
            		} catch (Exception e) {
            			logger.warn("Config update event delivery faile.", e);
            		}
            	}
            } catch (Exception e) {
                throw new RuntimeException("cannot load properties : " + e.getMessage(), e);
            }
        }    	
    }

    /**
     * 속성을 로드
     * @param resourcePath 대상 속성파일 resource path
     * @param workProps
     * @return 로드 했으면 true
     * @throws Exception
     */
    private static boolean loadProperty(String resourcePath, Properties workProps) throws Exception
    {
        InputStream	is;

    	System.out.println("load properties from " + resourcePath);
    	is	= getResourceAsStream(ConfigProperties.class.getClassLoader(), resourcePath);
        if (is != null) {
            try {
            	workProps.load(is);
            } finally {
            	is.close();
            }
            return true;
        } else {
        	System.out.println("there is no properties resource " + resourcePath);
        	return false;
        }
    }
    
    private static InputStream getResourceAsStream(ClassLoader cl, String resourcePath) {
    	InputStream	rv;
    	
    	if (resourcePath.charAt(0) == '/') {
    		resourcePath	= resourcePath.substring(1);
    	}
    	rv	= cl.getResourceAsStream(resourcePath);
    	if (rv == null) {
    		ClassLoader	pcl;
    		
    		pcl	= cl.getParent(); 
    		if (pcl != null) {
    			rv = getResourceAsStream(pcl, resourcePath); 
    		} else {
    			rv = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
    		}
    	}
    	return rv;
    }

    /**
     * 추가속성을 로드
     * @param workProps
     * @throws Exception
     */
    private static void loadExtendedProperty(Properties workProps) throws Exception
    {
        String 			extFilePath = null;
        String 			filePath = null;
        StringTokenizer stk = null;

        extFilePath 	= workProps.getProperty("properties.ext.files");
        
        if (extFilePath == null)
            return;
        
        if (StringUtils.isEmpty(extFilePath)) {
            return;
        }

	    stk = new StringTokenizer(extFilePath, ",");
	    
	    while (stk.hasMoreTokens())
	    {
	        filePath = stk.nextToken();
	        filePath = filePath.trim();

	        loadProperty(filePath, workProps);
	    }
    }
    
    /**
     * 참조 식이 존재하는 속성 값을 치환합니다.
     * @param propName
     * @param props
     * @param refHistory
     * @return
     */
    private static String resolvePropertyValue(String propName, Properties props, Set<String> refHistory) {
    	String			propValue;
    	Matcher			mch;
    	String			rv;

    	
    	propValue	= System.getProperty(propName);
    	if (propValue != null) {
    		props.setProperty(propName, propValue);
    	}
    	propValue	= props.getProperty(propName);
    	if (propValue == null) {
			System.out.println("# there is no property " + propName);
    		return null;
    	}
    	mch			= PTN_REF_EXPR.matcher(propValue);
    	if (mch.find()) {
        	StringBuffer	resolveValue;
    		
        	refHistory.add(propName);
        	resolveValue	= new StringBuffer();
        	do {
        		String	refPropName;
        		String	refPropValue;
        		
        		refPropName	= mch.group(1);
        		if (refPropName.equals("$")) {
        			mch.appendReplacement(resolveValue, "\\$");
        		} else {
            		if (refHistory.contains(refPropName)) {
            			throw new RuntimeException("property " + propName + " has circular reference.");
            		}
            		refPropValue	= resolvePropertyValue(refPropName, props, refHistory);
            		if (refPropValue == null) {
            			mch.appendReplacement(resolveValue, Matcher.quoteReplacement("${" + refPropName + "}"));
            		} else {
            			mch.appendReplacement(resolveValue, Matcher.quoteReplacement(refPropValue));
            		}
        		}
        	} while (mch.find());
        	mch.appendTail(resolveValue);
        	rv	= resolveValue.toString();
        	props.setProperty(propName, rv);
    	} else {
    		rv	= propValue;
    	}
    	refHistory.remove(propName);
    	return rv;
    }
    
    
    /**
     * 속성들을 다시 로드하도록 설정
     */
    public void setNeedUpdate() {
        synchronized (ConfigProperties.loadedProps) {
        	ConfigProperties.loadedProps.needUpdate	= true;
        }
    }


    /**
     * Ű�� ���� ���� �����Ѵ�.
     * Ű�� �ش��ϴ� ���� ���� ��쿡�� null�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @return the String value to which the key is mapped in this properties.
     *          null if the property is not found.
     */
    public String getString(String key)
    {
        return this.props.getProperty(key);
    }

    /**
     * Ű�� ���� ���� �����Ѵ�.
     * Ű�� �ش��ϴ� ���� ��� ��쿡�� defaultValue�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @param defaultValue a value used if the key is not mapped to any value in this properties.
     * @return the String value to which the key is mapped in this properties.
     */
    public String getString(String key, String defaultValue)
    {
        String value = getString(key);
        if (value == null)
            return defaultValue;
        else
            return value;
    }

    /**
     * Ű�� ���� boolean ���� �Ѱ��ش�.
     * Ű�� �ش��ϴ� ���� ��ҹ��ڸ� ������ �ʰ� "true" �� ��쿡�� true�� �����ϰ� �� �ܿ��� false�� �����Ѵ�.
     * Ű�� �ش��ϴ� ���� ��� ��쿡�� defaultValue�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @param defaultValue a value used if the key is not mapped to any value in this properties.
     * @return the boolean value to which the key is mapped in this properties.
     */
    public boolean getBoolean(String key, boolean defaultValue)
    {
        String value = getString(key, null);
        if (value == null)
            return defaultValue;
        else
            return (new Boolean(value)).booleanValue();
    }

    /**
     * Ű�� ���� double���� �Ѱ��ش�.
     * Ű�� �ش��ϴ� ���� ��ų� ��ȯ�� �� ��� ��쿡�� defaultValue�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @param defaultValue a value used if the key is not mapped to any value in this properties or
     *          the mapped value does not contains a parsable value.
     * @return the double value to which the key is mapped in this properties.
     */
    public double getDouble(String key, double defaultValue)
    {
        try
        {
            return Double.parseDouble(getString(key, null));
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Ű�� ���� float���� �Ѱ��ش�.
     * Ű�� �ش��ϴ� ���� ��ų� ��ȯ�� �� ��� ��쿡�� defaultValue�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @param defaultValue a value used if the key is not mapped to any value in this properties or
     *          the mapped value does not contains a parsable value.
     * @return the float value to which the key is mapped in this properties.
     */
    public float getFloat(String key, float defaultValue)
    {
        try
        {
            return Float.parseFloat(getString(key, null));
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Ű�� ���� int���� �Ѱ��ش�.
     * Ű�� �ش��ϴ� ���� ��ų� ��ȯ�� �� ��� ��쿡�� defaultValue�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @param defaultValue a value used if the key is not mapped to any value in this properties or
     *          the mapped value does not contains a parsable value.
     * @return the int value to which the key is mapped in this properties.
     */
    public int getInt(String key, int defaultValue)
    {
        try
        {
            return Integer.parseInt(getString(key, null));
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Ű�� ���� long���� �Ѱ��ش�.
     * Ű�� �ش��ϴ� ���� ��ų� ��ȯ�� �� ��� ��쿡�� defaultValue�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @param defaultValue a value used if the key is not mapped to any value in this properties or
     *          the mapped value does not contains a parsable value.
     * @return the long value to which the key is mapped in this properties.
     */
    public long getLong(String key, long defaultValue)
    {
        try
        {
            return Long.parseLong(getString(key, null));
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    public long getLong(String key)
    {
    	return getLong(key, 0);
    }

    /**
     * Ű�� ���� ���� ','�� ���еǾ� ���� ��� �� ��ū�� ���� String[]�� �����Ѵ�.
     * Ű�� �ش��ϴ� ���� ��� ��쿡�� null�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @return the String value to which the key is mapped in this properties.
     *          null if the property is not found.
     */
    public String[] getStrings(String key)
    {
        int i = 0;
        String value = null;
        
        StringTokenizer stk = null;
        String token = null;
        String tokenValues[] = null;

        value = getString(key);
        if (value == null)
            return null;

        // ","�� ���е� StringTokenizer ��.
        stk = new StringTokenizer(value, ",");
        tokenValues = new String[stk.countTokens()];
        while (stk.hasMoreTokens())
        {
            token = stk.nextToken();
            tokenValues[i++] = token.trim();
        }

        return tokenValues;
    }

    /**
     * Ű�� ���� ���� ','�� ���еǾ� ���� ��� �� ��ū�� ���� String[]�� �����Ѵ�.
     * Ű�� �ش��ϴ� ���� ��� ��쿡�� default value�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @param defaultValue a value used if the key is not mapped to any value in this properties
     * @return the String value to which the key is mapped in this properties.
     *          null if the property is not found.
     */
    public String[] getStrings(String key, String[] defaultValue)
    {
        int i = 0;
        String value = null;
        
        StringTokenizer stk = null;
        String token = null;
        String tokenValues[] = null;

        value = getString(key);
        if (value == null)
            return defaultValue;

        // ","�� ���е� StringTokenizer ��.
        stk = new StringTokenizer(value, ",");
        tokenValues = new String[stk.countTokens()];
        while (stk.hasMoreTokens())
        {
            token = stk.nextToken();
            tokenValues[i++] = token.trim();
        }

        return tokenValues;
    }

    /**
     * Ű�� ���� ���� ','�� ���еǾ� ���� ��� �� ��ū�� ���� double[]�� �����Ѵ�.
     * Ű�� �ش��ϴ� ���� ��ų� ��ȯ�� �� ��� ��쿡�� defaultValue�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @param defaultValue a value used if the key is not mapped to any value in this properties or
     *          the mapped value does not contains a parsable value.
     * @return the double[] to which the key is mapped in this properties.
     *          null if the key is not mapped to any value in this properties.
     */
    public double[] getDoubles(String key, double[] defaultValue)
    {
        String[] ls_value = getStrings(key, null);
        if (ls_value == null)
            return defaultValue;
            
        try
        {
            double[] ld_return = new double[ls_value.length];
            for (int i = 0; i < ls_value.length; i++)
                ld_return[i] = Double.parseDouble(ls_value[i]);
                
            return ld_return;
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Ű�� ���� ���� ','�� ���еǾ� ���� ��� �� ��ū�� ���� float[]�� �����Ѵ�.
     * Ű�� �ش��ϴ� ���� ��ų� ��ȯ�� �� ��� ��쿡�� defaultValue�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @param defaultValue a value used if the key is not mapped to any value in this properties or
     *          the mapped value does not contains a parsable value.
     * @return the float[] to which the key is mapped in this properties.
     *          null if the key is not mapped to any value in this properties.
     */
    public float[] getFloats(String key, float[] defaultValue)
    {
        String[] ls_value = getStrings(key, null);
        if (ls_value == null)
            return defaultValue;
            
        try
        {
            float[] lf_return = new float[ls_value.length];
            for (int i = 0; i < ls_value.length; i++)
                lf_return[i] = Float.parseFloat(ls_value[i]);
                
            return lf_return;
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }

    /**
     * Ű�� ���� ���� ','�� ���еǾ� ���� ��� �� ��ū�� ���� int[]�� �����Ѵ�.
     * Ű�� �ش��ϴ� ���� ��ų� ��ȯ�� �� ��� ��쿡�� defaultValue�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @param defaultValue a value used if the key is not mapped to any value in this properties or
     *          the mapped value does not contains a parsable value.
     * @return the int[] to which the key is mapped in this properties.
     *          null if the key is not mapped to any value in this properties.
     */
    public int[] getInts(String key, int[] defalutValue)
    {
        String[] ls_value = getStrings(key, null);
        if (ls_value == null)
            return defalutValue;
            
        try
        {
            int[] li_return = new int[ls_value.length];
            for (int i = 0; i < ls_value.length; i++)
                li_return[i] = Integer.parseInt(ls_value[i]);
                
            return li_return;
        }
        catch (Exception e)
        {
            return defalutValue;
        }
    }

    /**
     * Ű�� ���� ���� ','�� ���еǾ� ���� ��� �� ��ū�� ���� long[]�� �����Ѵ�.
     * Ű�� �ش��ϴ� ���� ��ų� ��ȯ�� �� ��� ��쿡�� defaultValue�� �����Ѵ�.
     * 
     * @param key a key in the properties
     * @param defaultValue a value used if the key is not mapped to any value in this properties or
     *          the mapped value does not contains a parsable value.
     * @return the long[] to which the key is mapped in this properties.
     *          null if the key is not mapped to any value in this properties.
     */
    public long[] getLongs(String key, long[] defaultValue)
    {
        String[] ls_value = getStrings(key, null);
        if (ls_value == null)
            return defaultValue;

        try
        {
            long[] ll_return = new long[ls_value.length];
            for (int i = 0; i < ls_value.length; i++)
                ll_return[i] = Long.parseLong(ls_value[i]);
                
            return ll_return;
        }
        catch (Exception e)
        {
            return defaultValue;
        }
    }
    
    public Properties getStaticProperties() {
    	return this.props;
    }
    
    public static void destory() {
    	synchronized (ConfigProperties.loadedProps) {
    		ConfigProperties.loadedProps.replace(null);
    		ConfigProperties.loadedProps	= null;
    		ConfigProperties.listeners.clear();
    		ConfigProperties.listeners		= null;
		}
    }
    
    /**
     * 컨피그중 특정 문자로 시작하는 속성들을 Properties로 추출 합니다.
     * @param prefix 
     * @return
     */
    public Properties export(String prefix) {
    	return this.export(prefix, null);
    }
    

    /**
     * 컨피그중 특정 문자로 시작하는 속성들을 Properties로 추출 합니다.
     * @param prefix
     * @param prefixOut 출력되는 properties의 속성명에 붙일 전치사, null이 지정되면 추출되는 프로퍼티명을 변경하지 않습니다.
     * @return
     */
    public Properties export(String prefix, String prefixOut) {
    	Properties	rv;
    	Enumeration<?>	propNames;
    	int				prefixSize;
    	
    	rv	= new Properties();
    	if (prefix == null) {
    		prefix = "";
    	}
    	if (prefixOut == null) {
    		prefixOut	= prefix;
    	}
    	prefixSize	= prefix.length();
    	propNames	= this.props.propertyNames();
    	while (propNames.hasMoreElements()) {
    		String	propName;
    		String	propOutName;
    		
    		propName	= (String)propNames.nextElement();
    		if (propName.indexOf(prefix) != 0) {
    			continue;
    		}
    		propOutName	= prefixOut + propName.substring(prefixSize);
    		rv.setProperty(propOutName, this.props.getProperty(propName));
    	}
    	
    	return rv;
    }
    
    
    /**
     * 디버깅용 덤프
     * @return
     */
    public String getDumpText() {
    	TreeSet<Object>	pnSet;
    	StringBuffer	sb;
    	
    	pnSet	= new TreeSet<Object>(loadedProps.keySet());
    	sb		= new StringBuffer();
    	sb.append("resource path : ").append(resourcePath).append('\n');
    	for (Object pn: pnSet) {
    		sb.append(pn).append('=').append(loadedProps.get(pn)).append('\n');
    	}
    	return sb.toString();
    }
}