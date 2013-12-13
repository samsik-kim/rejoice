package com.omp.commons.text;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.omp.commons.util.ThreadSession;
import com.omp.commons.util.config.ConfigProperties;

/**
 * 리소스 번들을 사용하여 로컬라이징 할 수 있는 메세지 객체입니다.
 * @author pat
 */
@SuppressWarnings("serial")
public class LocalizeMessage
	implements Serializable {
	private static final Map<Locale, Properties>	undefinedMessagesMap	= new HashMap<Locale, Properties>();
	private static boolean							isCollectUndefined;
	private static Map<Locale, ResourceBundle> 		rsMap					= new HashMap<Locale, ResourceBundle>();
	private static final Log						logger					= LogFactory.getLog(LocalizeMessage.class);
	private static Locale							defaultLocale			= Locale.getDefault();
	
	private String		msgSrc;
	private Object[]	msgArgs;
	private String		lastMsgText;
	
	/**
	 * 정의되지 않은 메세지들 수집 할 것인지 여부
	 * @param flag true 이면 수집
	 */
	public static void setCollectUndefinedMessages(boolean flag) {
		isCollectUndefined	= flag;
	}
	
	/**
	 * 설정된 정의되지 않은 메세지들 수집 여부 조회
	 * @return
	 */
	public static boolean isCollectUndefinedMessages() {
		return isCollectUndefined;
	}
	
	/**
	 * 수집된 미정의 메세지 맵
	 * @return
	 */
	public static Map<Locale, Properties> getUndefinedMessagesMap() {
		return undefinedMessagesMap;
	}
	
	/**
	 * 생성자.
	 * @param message 메세지 소스
	 */
	public LocalizeMessage(String message) {
		this(message, null);
	}
	
	/**
	 * 생성자
	 * @param msgSrc 메세지 소스
	 * @param msgArgs 메세지 인수
	 */
	public LocalizeMessage(String msgSrc, Object[] msgArgs) {
		this.msgSrc			= msgSrc;
		this.msgArgs		= msgArgs;
	}
	
	/**
	 * 메세지 소스를 반환합니다.
	 * @return
	 */
	public String getMessageSource() {
		return this.msgSrc;
	}
	
	/**
	 * 메세지 인수를 반환합니다.
	 * @return
	 */
	public Object[] getMessageArguments() {
		return this.msgArgs;
	}


	public String getLocalizedMessage() {
		return this.getLocalizedMessage((ResourceBundle)null);
	}
	
	
	
	/**
	 * 로컬라이징 된 메세지를 반환합니다.
	 * bundle이 지정 되지 않았다면 setThreadResourceBundle에서 지정된 쓰레드 리소스 번들을 사용합니다.
	 * 쓰레드 리소스 번들이 존재하지 않거나 설정된 메세지 소스에 해당하는 로컬라이징 메세지를 발견하지 못한다면, 로컬라이징 하지 않은 메세지를 반환합니다.
	 * @param bundle 로컬라이징시 사용할 번들
	 * @return
	 * @see #setThreadResourceBundle
	 */
	public String getLocalizedMessage(ResourceBundle bundle) {
		String			rv;
		
		rv	= getLocalizedMessage(bundle, this.msgSrc, this.msgArgs);
		this.lastMsgText	= rv;
		return rv;
	}
	
	/**
	 * 객체가 담은 메세지를 같이 표시하도록 오버라이딩 되었습니다.
	 */
	public String toString() {
		return LocalizeMessage.class.getName() + "@" + this.hashCode() + ":"
			+ (this.lastMsgText == null ?
				getLocalizedMessage(null, this.msgSrc, this.msgArgs)
				: this.lastMsgText);
	}


	/**
	 * 쓰레드리소스 번들을 사용하여  지역화 메세지를 반환합니다. 
	 * @param msgSrc
	 * @return
	 * @see #setThreadResourceBundle
	 */
	public static String getLocalizedMessage(String msgSrc) {
		return getLocalizedMessage(null, msgSrc);
	}
	
	
	/**
	 * 로컬라이즈된 메세지를 얻습니다.
	 * @param bundle 로컬라이징 대상 리소스 번들
	 * @param msgSrc 메세지 소스
	 * @return
	 */
	public static String getLocalizedMessage(ResourceBundle bundle, String msgSrc) {
		return getLocalizedMessage(bundle, msgSrc, null);
	}
	

	/**
	 * 쓰레드리소스 번들을 사용하여  지역화 메세지를 반환합니다. 
	 * @param msgSrc
	 * @param msgArgs 메세지 인수
	 * @return
	 * @see #setThreadResourceBundle
	 */
	public static String getLocalizedMessage(String msgSrc, Object[] msgArgs) {
		return getLocalizedMessage(null, msgSrc, msgArgs);
	}
	
	
	/**
	 * 로컬라이즈된 메세지를 얻습니다.
	 * @param bundle 로컬라이징 대상 리소스 번들
	 * @param msgSrc 메세지 소스
	 * @param msgArgs 메세지 인수
	 * @return
	 */
	public static String getLocalizedMessage(ResourceBundle bundle, String msgSrc
			, Object[] msgArgs) {
		String			rv;
		MessageFormat	mf;
		Locale			locale;
	
		// 번들 지정되지 않았을때 처리할 로케일
		if (bundle == null) {
			ThreadSession	tses;
			
			tses	= ThreadSession.getSession();
			locale	= tses.getServiceLocale();
			if (locale == null) {
				locale	= LocalizeMessage.defaultLocale;
			}
			
			bundle	= LocalizeMessage.getResourceBundle(locale);
		} else {
			locale	= bundle.getLocale();
		}
		if (bundle == null) {
			rv = msgSrc;
			if (isCollectUndefined) {
				collectUndefinedMessage(LocalizeMessage.defaultLocale, msgSrc);
			}
		} else {
			try {
				rv	= bundle.getString(msgSrc);
				if (rv == null && isCollectUndefined) {
					collectUndefinedMessage(locale, msgSrc);
				}
			} catch (MissingResourceException e) {
				rv = msgSrc;
				if (isCollectUndefined) {
					collectUndefinedMessage(locale, msgSrc);
				}
			}
		}
		if (msgArgs != null) {
			for (int i=0; i<msgArgs.length; i++) {
				if (msgArgs[i] instanceof LocalizeMessage) {
					msgArgs[i]	= ((LocalizeMessage)msgArgs[i]).getLocalizedMessage(bundle);
				}
			}
		}
		mf	= new MessageFormat(rv);
		rv	= mf.format(msgArgs);
		return rv;
	}
	
	/**
	 * 정의되지 않은 메세지 들을 수집합니다.
	 * @param locale
	 * @param msgSrc
	 */
	private static void collectUndefinedMessage(Locale locale, String msgSrc) {
		Properties	undefinedMessages;
		
		if (locale == null) {
			locale	= defaultLocale;
		}
		synchronized (msgSrc) {
			undefinedMessages	= undefinedMessagesMap.get(locale);
			if (undefinedMessages == null) {
				undefinedMessages	= new Properties();
				undefinedMessagesMap.put(locale, undefinedMessages);
			}
		}
		undefinedMessages.put(msgSrc, msgSrc);
	}

	/**
	 * 주어진 지역의 리소스 번들을 얻습니다.
	 * 해당 베이스명과 지역에 대한 리소스번들이 존재하지 않으면 null 반환.
	 * @param loc
	 * @return
	 */
	public static ResourceBundle getResourceBundle(Locale	loc) {
		ConfigProperties	conf;
		ResourceBundle		rv;
		
		conf	= new ConfigProperties();
		// 공용 지역화 메세지 리소스 번들 베이스명
		String commonBaseName	= conf.getString("omp.common.locale.resource.common");
		// 지역화 메세지용 리소스 번들 베이스명
		String baseName			= conf.getString("omp.common.locale.resource.base");
		if (loc == null) {
			loc	= defaultLocale;
		}
		synchronized (LocalizeMessage.rsMap) {
			rv	= LocalizeMessage.rsMap.get(loc);
			if (rv == null && !LocalizeMessage.rsMap.containsKey(loc)) {
				try {
					Properties				props;
					ByteArrayOutputStream	bos;
					ByteArrayInputStream	bis;
					
					props	= new Properties();
					if (commonBaseName != null) {
						loadResourceBundleToProperties(commonBaseName, loc, props);
					}
					if (baseName != null) {
						loadResourceBundleToProperties(baseName, loc, props);
					}
					bos	= new ByteArrayOutputStream();
					try {
						props.store(bos, "");
					} finally {
						bos.close();
					}
					bis	= new ByteArrayInputStream(bos.toByteArray());
					try {
						rv	= new PropertyResourceBundle(bis);
					} finally {
						bis.close();
					}
				} catch (IOException e) {
					logger.warn("ResourceBundle load fail.", e);
				}
				LocalizeMessage.rsMap.put(loc, rv);
			}
		}
		return rv;
	}
	
	/**
	 * 내부 메소드
	 * 지역화 메세지 로드
	 * @param name
	 * @param loc
	 * @param props
	 * @throws IOException
	 */
	private static void loadResourceBundleToProperties(String name, Locale loc, Properties props)
		throws IOException {
		ResourceBundle.Control	rbctl;
		String					bundleName;
		String					resourceName;
		InputStream				is;
		
		rbctl			= ResourceBundle.Control.getControl(ResourceBundle.Control.FORMAT_DEFAULT);
		bundleName		= rbctl.toBundleName(name, loc);
		resourceName	= rbctl.toResourceName(bundleName, "properties");
		is				= LocalizeMessage.class.getResourceAsStream(resourceName);
		if (is != null) {
			try {
				props.load(is);
			} catch (IOException e) {
				logger.warn("ResourceBundle " + resourceName + " load fail.");
				throw e;
			} finally {
				is.close();
			}
		} else {
			logger.warn("ResourceBundle " + resourceName + " is not available.");
		}
	}
	
	public static void setDefaultLocale(Locale loc) {
		
	}

}
