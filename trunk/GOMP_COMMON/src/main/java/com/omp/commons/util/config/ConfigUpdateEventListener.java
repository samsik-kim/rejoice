package com.omp.commons.util.config;

/**
 * 컨피그 변경 이벤트 리스너 인터페이스
 * @author pat
 *
 */
public interface ConfigUpdateEventListener {
	
	/**
	 * 변경된 컨피그시 전달 됩니다.
	 * @param conf
	 */
	public void configUpdated(ConfigProperties conf);

}
