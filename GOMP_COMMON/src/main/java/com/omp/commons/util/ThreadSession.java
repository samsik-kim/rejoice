package com.omp.commons.util;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * 쓰레드 영역 안에서만 유효한 정보들을 유지하게 하는 객체
 * 이 객체의 사용은 소스상에서 데이터 전달의 흐름이 나타나지 않기 때문에
 * 시스템 차원의 데이터 처리가 아닌 일반 업무 개발에서의 사용은 지양해야 합니다.
 * @author pat
 */

public class ThreadSession {
	
	private static final Map<Thread, ThreadSession>	sessionMap	= new HashMap<Thread, ThreadSession>();
	
	
	private Thread			thread;
	private Locale			serviceLocale;
	private String			actionStep;
	private String			serviceStep;
	private String			pkey;
	
	private ThreadSession(Thread thread) {
		this.thread	= thread;
	}
	
	/**
	 * 쓰레드 세션을 만료 시킵니다.
	 */
	public static void doExpire() {
		sessionMap.remove(Thread.currentThread());
	}


	/**
	 * 쓰레드에 속성용 맵을 할당하고 반환합니다.
	 * @return
	 */
	public static ThreadSession getSession() {
		Thread			thread;
		ThreadSession	rv;
		
		thread	= Thread.currentThread();
		rv		= sessionMap.get(thread);
		if (rv == null) {
			rv	= new ThreadSession(thread);
			sessionMap.put(thread, rv);
		}
		return rv;
	}
	
	/**
	 * 모든 쓰레드에 설정된 속성들을 제거 합니다.
	 */
	public static void claearAll() {
		sessionMap.clear();
	}
	
	/**
	 * 쓰레드 세션의 대상 쓰레드 반환
	 * @return
	 */
	public Thread getThread() {
		return thread;
	}

	/**
	 * 서비스 로케일 반환
	 * @return
	 */
	public Locale getServiceLocale() {
		return serviceLocale;
	}

	/**
	 * 서비스 로케일 설정
	 * @param serviceLocale
	 */
	public void setServiceLocale(Locale serviceLocale) {
		this.serviceLocale = serviceLocale;
	}
	
	/**
	 * 액션의 스텝을 설정합니다.
	 * @param step
	 */
	public void setActionStep(String step) {
		this.actionStep	= step;
	}
	
	/**
	 * 액션의 스텝을 얻습니다.
	 * @return
	 */
	public String getActionStep() {
		return this.actionStep;
	}
	
	/**
	 * 서비스의 스텝을 설정합니다.
	 * @param step
	 */
	public void setServiceStep(String step) {
		this.serviceStep	= step;
	}
	
	/**
	 * 서비스의 스텝을 얻습니다.
	 * @return
	 */
	public String getServiceStep() {
		return this.serviceStep;
	}
	
	/**
	 * 요청 내용의 프라이머리 키 설정
	 * @param pkey
	 */
	public void setPrimaryKey(String pkey) {
		this.pkey	= pkey;
	}
	
	/**
	 * 요청 내영의 프라이머리 키 조회
	 * @return
	 */
	public String getPrimaryKey() {
		return this.pkey;
	}
}
