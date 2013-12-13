package com.omp.dev.common;

/**
 * Constants(DEV_PoC)
 * 
 * @author redaano
 * @version 0.1
 */
public final class Constants
	extends com.omp.commons.Constants {
	

    /**
	 * -------------------------------------------------------------------------------------------
	 * Common Area
	 * -------------------------------------------------------------------------------------------
	 */
	// Y or N
	public static final String YES = "Y";
	public static final String NO = "N";

	// HTTP 연동 방식
	public static final String HTTP_METHOD_GET	= "GET";
    public static final String HTTP_METHOD_POST = "POST";
    
    public static final String DEV_MAIN_URL		= "/main/main.omp";
	
    /**
	 * -------------------------------------------------------------------------------------------
	 * Product Area START
	 * -------------------------------------------------------------------------------------------
	 */
    // 상품바이너리 첨부가능 파일 확장자
    public static final String FILEEXT_VERIFYSCNR 	= "doc|docx|xls|xlsx|ppt|pptx|pdf";		//검증시나리오
    public static final String FILEEXT_ANDROID_BIN 	= "apk";								//안드로이드 바이너리
  
    /**
	 * -------------------------------------------------------------------------------------------
	 * Product Area END
	 * -------------------------------------------------------------------------------------------
	 */
	
    /**
	 * -------------------------------------------------------------------------------------------
	 * Member Area START
	 * -------------------------------------------------------------------------------------------
	 */
    
    public static final int NUMBER_EIGHTH_CIPHERS					= 8;		  // RandomString 자릿수
    
	/** 회원구분코드 */
	public static final String MEM_CLS_PERSON						= "US000101"; // 개인
	public static final String MEM_CLS_BUSINESS						= "US000102"; // 사업자
	public static final String MEM_CLS_FOREIGNER					= "US000103"; // 외국인
	
	/** 회원분류코드 */
	public static final String MEM_TYPE_NORMAL 						= "US000202"; // 사용자 회원
	public static final String MEM_TYPE_DEV_NOPAY 					= "US000205"; // 무료개발
	public static final String MEM_TYPE_DEV_PAY 					= "US000206"; // 유료개발
	
	/** 개발자 회원상태코드 */
	public static final String MEM_DEV_STATUS_REG_MOTION 			= "US000801"; // 가입신청
	public static final String MEM_DEV_STATUS_REG_FINISH 			= "US000802"; // 가입완료
	public static final String MEM_DEV_STATUS_TURN_MOTION 			= "US000804"; // 전환신청
	public static final String MEM_DEV_STATUS_TURN_FINISH 			= "US000805"; // 전환완료
	public static final String MEM_DEV_STATUS_TURN_REJECT 			= "US000806"; // 전환거절
	public static final String MEM_DEV_STATUS_LEAVE_MOTION 			= "US000807"; // 탈퇴신청
	public static final String MEM_DEV_STATUS_ACCOUNTS_WAIT  		= "US000809"; // 정산정보승인대기
	public static final String MEM_DEV_STATUS_ACCOUNTS_FINSH 		= "US000810"; // 정산정보승인완료
	public static final String MEM_DEV_STATUS_ACCOUNTS_REJECT		= "US000811"; // 정산정보승인거절
	public static final String MEM_DEV_STATUS_MEMBERSHIP_FINISH 	= "US000812"; // 연회비결제완료
	public static final String MEM_DEV_STATUS_MEMBERSHIP_READY 		= "US000813"; // 연회비결제완료
	
	/** 회원상태코드 */
	public static final String MEM_STATUS_NORMAL 					= "US000503"; // 정상회원
	public static final String MEM_STATUS_STOP 						= "US000504"; // 징계회원
	public static final String MEM_STATUS_LEAVE 					= "US000505"; // 탈퇴회원
	
	/** 개발자 회원 등급코드 */
	public static final String MEM_DEV_LEAVE_NORMAL 				= "US000505"; // Normal
	public static final String MEM_DEV_LEAVE_TRUST 					= "US000505"; // Trust
	public static final String MEM_DEV_LEAVE_SPECIAL 				= "US000505"; // Specital
	
	/** 사업자 종류 코드 */
	public static final String BIZ_CAT_PRIVATE 						= "US000901"; // 회사(소규모)
	public static final String BIZ_CAT_LEGAL 						= "US000902"; // 회사(일반영업)
	
	/** 처리상태코드 */
	public static final String PROCCESS_STATUS_MOTION				= "US001601"; // 승인요청
	public static final String PROCCESS_STATUS_FINISH				= "US001602"; // 승인완료
	public static final String PROCCESS_STATUS_REJECT				= "US001603"; // 승인거절
	
	/** 등록서류항목코드 */
	public static final String DOC_HM_01 							= "US001901"; // 회사증명사본
	public static final String DOC_HM_02 							= "US001902"; // 개인신분증사본
	public static final String DOC_HM_03 							= "US001903"; // 외국인증명사본
	public static final String DOC_HM_04 							= "US001904"; // 통장사본
	
	public static final String PHONE_AREA_CODE						= "US0054";	  //지역번호
	
	/** 탈퇴사유 */
	public static final String MEM_LEAVE_REASON_NOPAY				= "US0017"; // 무료사용자 탈퇴사유 
	public static final String MEM_LEAVE_REASON_PAY 				= "US0018"; // 유료사용자 탈퇴사유
	
	/** 로그인 후 실행될 URL */
	public static final String RETURN_URL_KEY						= "RETURN_URL";
	public static final String RETURN_ACTION						= "RETURN_ACTION";
	public static final String RETURN_AUTH_MEMBER					= "/cert/q.omp?j=";
	public static final String RETURN_AUTH_MYPAGE					= "/cert/cf.omp?j=";
	
	/** 가입 Email  인증 */
	public static final String AUTH_EMAIL_JOIN						= "authEmailJoin";
	/** Email 변경 인증 */
	public static final String AUTH_EMAIL_CHANGE					= "authEmailChange";
	/** 회원전환 Email 인증 */
	public static final String AUTH_MEM_CHANGE						= "authMemChange";
	// FAQ 개발자 Poc
	public static final String CTGR_FAQ_DEV							= "SFAQ1000";
	// TBL_CTGR(공지사항_개발자)
	public static final String CTGR_NTC_DEV 						= "NTC1000"; 
	// TBL_CTGR(공지사항_WEB)
	public static final String CTGR_NTC_WEB 						= "NTC3000";
	// TBL_CTGR(FAQ_WEB)
	public static final String CTGR_FAQ_WEB 						= "SFAQ3000";
	
    /**
	 * -------------------------------------------------------------------------------------------
	 * Member Area END
	 * -------------------------------------------------------------------------------------------
	 */
	
	/*
	 * 삭제 예정 
	 */
    //이메일목록
    public static final String CODE_EMAIL = "PD0072";
    //Q&A유형코드
    public static final String CODE_QNA_TP_CD_DEV = "CM000602";		//개발자(사) 직접문의
    //Q&A진행상태코드
    public static final String CODE_QNA_PRC_STAT_CD_WAIT = "CM000701";	//대기
	
}