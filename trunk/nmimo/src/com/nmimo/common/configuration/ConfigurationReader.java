package com.nmimo.common.configuration;

import java.net.URL;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.CompositeConfiguration;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.configuration.FileConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

import com.nmimo.common.configuration.exception.ConfigFileNotFoundException;

public class ConfigurationReader implements ConfigurationService {
	
	/** configuration 객체 */
	private Configuration configuration;
	
	/** configuration 파일의 위치정보 */
	private String configLocation;
	
	/** configuration 재 기동 여부 */
	private String reloadable;
	
	/**
	 * 
	 * <pre>
	 * configuration 파일이 설정되어 있는 위치정보를 읽어온다.
	 * </pre>
	 * @return {@link String} configuration 파일이 설정되어 있는 문자열
	 */
	public String getConfigLocation() {
		return configLocation;
	}

	/**
	 * 
	 * <pre>
	 * configuration 파일의 위치 정보를 세팅한다.
	 * </pre>
	 * @param configLocation
	 */
	public void setConfigLocation(String configLocation) {
		this.configLocation = configLocation;
	}

	/**
	 * 
	 * <pre>
	 * configuration 파일을 was 재기동 없이 읽어 올 수 있는지 여부를 판단하여 값을 반환한다. 
	 * </pre>
	 * @return
	 */
	public String getReloadable() {
		return reloadable;
	}

	/**
	 * 
	 * <pre>
	 * configuration 파일을 was 재기동 없이 읽어 올 것이지 여부를 세팅한다.
	 * </pre>
	 * @param reloadable
	 */
	public void setReloadable(String reloadable) {
		this.reloadable = reloadable;
	}

	/**
	 * 
	 * <pre>
	 * configuration 위치정보와 재 기동 여부를 파라미터로 받아 configuration 설정을 load 한다.
	 * </pre>
	 * @param configPath configuration 파일의 위치정보
	 * @param reloadable 재 기동 여부
	 */
	public void loadConfiguration(String configPath , String reloadable) {
		
    	ConfigurationFactory factory = new ConfigurationFactory();	

    	URL url = ConfigurationReader.class.getResource(configPath);

    	if(url != null)
    		factory.setConfigurationURL(url);        		
    	else
    		factory.setConfigurationFileName(configPath);
    	try {
			configuration = factory.getConfiguration();
		} catch (ConfigurationException e) {
			throw new ConfigFileNotFoundException(configPath);
		}
    	
    	if("true".equalsIgnoreCase(reloadable))
    		setReload(configuration);        
	}
	
	/**
	 * 
	 * <pre>
	 * configuration 의 리로드 여부를 세팅한다.
	 * </pre>
	 * @param config configuration 객체
	 */
	private static void setReload(Configuration config) {
		
		if (config instanceof  CompositeConfiguration) {
			CompositeConfiguration cc = (CompositeConfiguration)config;
			for (int i=0; i < cc.getNumberOfConfigurations(); i++) {
				if (cc.getConfiguration(i) instanceof FileConfiguration) {
					((FileConfiguration)cc.getConfiguration(i)).setReloadingStrategy(new FileChangedReloadingStrategy());
				}
			}
		}

	}
	
	/**
	 * 
	 * <pre>
	 * configuration 설정정보를 초기화 한다.
	 * </pre>
	 */
	private void initialize() {
		if(configuration == null)
			loadConfiguration(getConfigLocation() , getReloadable());	
	}
	
	/***
	 * 
	 * <pre>
	 * key 에 매핑되는 정보를 String 타입으로 반환한다.
	 * </pre>
	 * @param param property 파일에 저장되어 있는 key 값
	 * @return String key 에 매핑되어 있는 value 의 String 값
	 */
	public String getString(String param) {
		initialize();
		return configuration.getString(param);
	}
	
	/**
	 * 
	 * <pre>
	 * key 에 매핑되는 정보를 int 타입으로 반환한다.
	 * </pre>
	 * @param param property 파일에 저장되어 있는 key 값
	 * @return int key 에 매핑되어 있는 value 의 int 값
	 */
	public int getInt(String param) {	
		initialize();
		return configuration.getInt(param);
	}
	
	/**
	 * 
	 * <pre>
	 * key 에 매핑되는 정보를 String[] 타입으로 반환한다.
	 * </pre>
	 * @param param property 파일에 저장되어 있는 key 값
	 * @return String[] key 에 매핑되어 있는 value 의 String[] 값
	 */
	public String[] getStringArray(String param) {
		initialize();
		return configuration.getStringArray(param);
	}
	
	/**
	 * 
	 * <pre>
	 * key 에 매핑되는 정보를 Properties 타입으로 반환한다.
	 * </pre>
	 * @param param property 파일에 저장되어 있는 key 값
	 * @return Properties key 에 매핑되어 있는 value 의 Properties 값
	 */
	public Properties getProperties(String param) {
		initialize();
		return configuration.getProperties(param);
	}
	
	/**
	 * 
	 * <p>
	 * key 에 매핑되는 정보를 Properties 타입으로 반환한다.
	 * </p>
	 * @param param property 파일에 저장되어 있는 key 값
	 * @return String key 에 매핑되어 있는 value 의 List 값
	 */
	public List getList(String param) {
		initialize();
		return configuration.getList(param);
	}
}
