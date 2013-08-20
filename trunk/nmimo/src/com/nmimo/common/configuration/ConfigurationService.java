package com.nmimo.common.configuration;

import java.util.List;
import java.util.Properties;

/**
 * <pre>
 * .properties, .xml 파일에서 key, value 매핑된 정보를 조회 할 수 있는 기능을 제공한다.
 * 
 * <li>사용 예 1) .properties 파일에서 key, value 매핑 정보 조회
 *    ---------------------------------------
 *    가. [userdefined].properties 파일 설정
 *    ---------------------------------------
 *    mobile.path-prefix=mobile
 *    
 *    ---------------------------------------
 *    나. Java 에서 사용하기
 *    ---------------------------------------
 *    ConfigurationService config;
 *    config.getString("mobile.path-prefix");
 *    config.getList("mobile.exclude-url-pattern.value");
 * </li>
 *    
 * <li>사용 예 2) .xml 파일에서 key, value 매핑 정보 조회 
 *    ---------------------------------------
 *    가. [userdefined].xml 파일 설정
 *    ---------------------------------------
 *    &lt;?xml version="1.0" encoding="UTF-8"?>
 *    &lt;config>
 *     &lt;mobile>
 *       &lt;path-prefix>mobile&lt;/path-prefix>
 *    		
 *       &lt;exclude-character>
 *         &lt;value>forward</value>
 *         &lt;value>redirect</value>
 *       &lt;/exclude-character>
 *		
 *     &lt;/mobile>
 *    &lt;/config></li>
 * 
 *    ---------------------------------------
 *    나. Java 에서 사용하기
 *    ---------------------------------------
 *    ConfigurationService config;
 *    config.getString("mobile.path-prefix");
 *    config.getList("mobile.exclude-url-pattern.value");
 * </pre>
 * @author 
 */
public interface ConfigurationService {
	
	/**
	 * 
	 * <pre>
	 * property 파일에 저장된 key 에 매핑되는 value 값을 문자열 타입으로 반환한다.
	 * </pre>
	 * @param key property 파일의 key
	 * @return String key 에 매핑되어 있는 value 값  
	 */
	String getString(String key) ;
	
	/**
	 * 
	 * <pre>
	 * property 파일에 저장된 key 에 매핑되는 value 값을 정수 타입으로 반환한다.
	 * </pre>
	 * @param key key property 파일의 key
	 * @return int key 에 매핑되어 있는 value 값
	 */
	int getInt(String key);
	
	/**
	 * 
	 * <pre>
	 * property 파일에 저장된 key 에 매핑되는 value 값을 문자배열 타입으로 반환한다.
	 * </pre>
	 * @param key property 파일의 key
	 * @return String[] key 에 매핑되어 있는 value 값 
	 */
	String[] getStringArray(String key);
	
	/**
	 * 
	 * <pre>
	 * property 파일에 저장된 key 에 매핑되는 value 값을 Properties 타입으로 반환한다.
	 * </pre>
	 * @param key property 파일의 key
	 * @return Properties key 에 매핑되어 있는 value 값
	 */
	Properties getProperties(String key);
	
	/**
	 * 
	 * <pre>
	 * property 파일에 저장된 key 에 매핑되는 value 값을 List<String> 타입으로 반환한다.
	 * </pre>
	 * @param key property 파일의 key
	 * @return List<String> key 에 매핑되어 있는 value 값
	 */
	List<String> getList(String key);
}