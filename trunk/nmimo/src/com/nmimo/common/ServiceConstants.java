package com.nmimo.common;

public class ServiceConstants {
	
	
	/** Configuration xml 파일이 존재 하지 않음. */
	public static final String ConfigFileNotFound     = "NMIMO_1000001";
	
	/** 특정 Layer 에서 예외가 발생했는지를 판단 */
	public static final String LAYER_NAME             = "NMIMO_1000002";
	
	/** 특정 업무에서 예외가 발생했는지를 판단 */
	public static final String TASK_NAME              = "NMIMO_1000003";
	
	/** 데이타베이스 에러 명 */
	public static final String SQL_ERROR              = "NMIMO_3333333";
	
	/** 지원되지 않는 서비스 타입 정의 */
	public static final String NO_SUPPORTED_TYPE      = "NMIMO_9999998";
	
	/** 정의 되지 않은 에러코드 명 */
	public static final String NOT_DEFINED_ERROR_CODE = "NMIMO_9999999";

    /**
     * <pre>
     * 공통 관련 상수들
     * </pre>
     *
    */	
	public static class Common {
	
		public static String TEST_GUBUN = "TEST"; 
		/** Success */
		public static String SUCCESS_CODE = "SUCCESS";
		/** Fail */
		public static String FAIL_CODE = "FAIL";
		/** 에러 */
		public static String RST_CODE_ERROR = "ERROR";

		/** JKFT암호화 rawKey설정 */
		public static String  SYSTEM_JKTF_RAWKEY = "nMIMOSecretKey";
		
		/** resultCode 설정 */
		public static String  RESULT_CODE = "resultCode";

		/** resultMsg 설정 */
		public static String  RESULT_MSG = "resultMsg";
		
		/** returnUrl 설정 */
		public static String  RETURN_URL = "returnUrl";
		
		public static String CHARSET_EUC_KR = "EUC_KR";
		
		public static String CHARSET_UTF_8 = "UTF-8";
		
		public static String TRANS_MODE_REQ = "trans.mode.req";
		
		public static String TRANS_MODE_RES = "trans.mode.res";
		
	}
	
	/**
	 * <pre>
	 * Member 관련 상수들
	 * </pre>
	 * @file ServiceConstants.java
	 * @since 2013. 5. 23.
	 * @author Administrator
	 */
	public static class Member {
		public static String SESSION_LOGIN_INFO_KEY	="LOGIN_SESSION";
		public static String SESSION_LOGIN_Y		="Y";
		public static String SESSION_LOGIN_N		="N";
		public static String PSSO_LOGIN_Y			="Y";
		public static String PSSO_LOGIN_N			="N";
		public static String PSSO_LOGIN_FAIL		="f";
		
	}
	
	/**
	 * <pre>
	 * CRM 관련 상수들
	 * </pre>
	 * @file ServiceConstants.java
	 * @since 2013. 5. 23.
	 * @author Administrator
	 */
	public static class Crm{
		
		public static String RESULT_CODE_SUCCESS		="SUCCESS";
		public static String RESULT_CODE_FAIL			="FAIL";
		public static String RESULT_MSG_FAIL_400		="crm.result.msg.400";
		public static String RESULT_MSG_FAIL_401		="crm.result.msg.401";
		public static String RESULT_MSG_FAIL_402		="crm.result.msg.402";
		public static String RESULT_LIST_URL			="/crm/list.do";
		public static String RESULT_VIEW_URL			="/crm/requestJobViewPop";
		public static String RESULT_URL					="/crm/result.do";
		
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @file ServiceConstants.java
	 * @since 2013. 7. 31.
	 * @author Rejoice
	 */
	public static class File{
		/** File 확장자 */
		public static String FILE_EXT_JPG = "jpg";
		public static String FILE_EXT_BMP = "bmp";
		public static String FILE_EXT_GIF = "gif";
		public static String FILE_EXT_XLS = "xls";
		public static String FILE_EXT_TXT = "txt";
		
		/** File MIME Type */
		public static String FILE_MIME_TYPE_JPG = "image/jpeg";
		public static String FILE_MIME_TYPE_GIF = "image/gIf";
		public static String FILE_MIME_TYPE_MP4 = "video/x-ktf-mp4";
		public static String FILE_MIME_TYPE_K3G = "video/k3g";
		public static String FILE_MIME_TYPE_3GP = "video/3gp";
		
		/** IMG Size */
		public static String IMG_WIDTH = "width";
		public static String IMG_HEIGHT = "height";
	}
}
