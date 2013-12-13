package com.omp.admin.common;

/**
 * Constants(DEV_PoC)
 * 
 * @author redaano
 * @version 0.1
 */
public final class Constants extends com.omp.commons.Constants {

	/**
	 * ------------------------------------------------------------------------ Member Area
	 * ------------------------------------------------------------------------
	 */
	// Main Url
	public static final String DEV_MAIN_URL = "/main/main.omp";

	// 회원 상태 코드
	public static String MEM_STATUS_NORMAL = "US000503"; // 정상회원
	public static String MEM_STATUS_STOP = "US000504"; // 징계회원
	public static String MEM_STATUS_LEAVE = "US000505"; // 탈퇴회원

	//** 회원구분코드 */
	public static String MEM_CLS_PRIVATE = "US000101"; // 개인
	public static String MEM_CLS_BUSINESS = "US000102"; // 사업자
	public static String MEM_CLS_FOREINGER = "US000103"; // 외국인

	//** 회원분류코드 */
	public static String MEM_TYPE_NORMAL = "US000202"; // 일반사용자 회원
	public static String MEM_TYPE_DEV_NOPAY = "US000205"; // 무료개발
	public static String MEM_TYPE_DEV_PAY = "US000206"; // 유료개발

	//** 개발자 회원상태코드 */
	public static String MEM_DEV_STATUS_REG_MOTION_REQ = "US000801"; // 가입신청
	public static String MEM_DEV_STATUS_REG_FINISH = "US000802"; // 가입완료
	public static String MEM_DEV_STATUS_TURN_MOTION_REQ = "US000804"; // 전환신청
	public static String MEM_DEV_STATUS_TURN_FINISH = "US000805"; // 전환완료
	public static String MEM_DEV_STATUS_TURN_REJECT = "US000806"; // 전환거절
	public static String MEM_DEV_STATUS_LEAVE_MOTION_REQ = "US000807"; // 탈퇴신청
	public static String MEM_DEV_STATUS_LEAVE_FINISH = "US000808"; // 탈퇴완료
	public static String MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT = "US000809"; // 정산정보승인대기
	public static String MEM_DEV_STATUS_ACCOUNT_DATA_APPR_FINISH = "US000810"; // 정산정보승인완료
	public static String MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT = "US000811"; // 정산정보승인거절
	public static String MEM_DEV_STATUS_ANNUAL_FEE_PAYMENT_FINISH = "US000812"; // 연회비결제완료
	public static String MEM_DEV_STATUS_ANNUAL_FEE_PAY_WAIT = "US000813"; // 연회비결제대기

	/**
	 * ------------------------------------------------------------------------ Product Area
	 * ------------------------------------------------------------------------
	 */
	/** Product Grade **/
	public static final String CODE_GAME_DELIB_GRD = "PD0044";

	/**
	 * ------------------------------------------------------------------------ Certify Area
	 * ------------------------------------------------------------------------
	 */
	// Test 진행상태
	public static final String TEST_PROGRESS_STATUS_REQUEST = "PD010901"; // Test 요청	
	public static final String TEST_PROGRESS_STATUS_ING = "PD010902"; // Test 중
	public static final String TEST_PROGRESS_STATUS_COMPLETE = "PD010903"; // Test 완료
	public static final String TEST_PROGRESS_STATUS_REJECT = "PD010904"; // Test 검증반려

	/**
	 * ------------------------------------------------------------------------ Display Area
	 * ------------------------------------------------------------------------
	 */

	/**
	 * ------------------------------------------------------------------------ Phurchase Area
	 * ------------------------------------------------------------------------
	 */
	// 구매상태
	public static final String PURCHASE_END = "OR000301"; // 구매완료
	public static final String PURCHASE_CANCEL = "OR000302"; // 구매취소
	public static final String PURCHASE_FAIL = "OR000303"; // 구매실패
	
	// 결제수단
	public static final String PURCHASE_CREDIT_CARD = "OR000601";			// 신용카드
	public static final String PURCHASE_CELLPHONE = "OR000602";				// 핸드폰 결제
	public static final String PURCHASE_TSTORE_CASH = "OR000607";			// Whoopy Cash
	public static final String PURCHASE_POINT = "OR000608";						// 포인트결제
	public static final String PURCHASE_WEB_ATM = "OR000609";					// WEB ATM
	public static final String PURCHASE_TEST_CELLPHONE = "OR000698";		// 테스트폰결제
	public static final String PURCHASE_FREE_PROD = "OR000699";				// 무료상품

	/**
	 * ------------------------------------------------------------------------ Accounts Area
	 * ------------------------------------------------------------------------
	 */

	/**
	 * ------------------------------------------------------------------------ Phone Info Area
	 * ------------------------------------------------------------------------
	 */
	//** 회원휴대폰 정보 이력 */
	public static String PHONE_HIS_CD_REGISTER = "US005101"; // 등록
	public static String PHONE_HIS_CD_USER_CHANGE = "US005102"; // 타 사용자로 이동
	public static String PHONE_HIS_CD_ADMIN_DIRECT_DELETE = "US005103"; // 관리자 직접 삭제

	/**
	 * ------------------------------------------------------------------------ Etc Area
	 * ------------------------------------------------------------------------
	 */
	// lineSeparator
	public static final String LS = System.getProperty("line.separator");
	// lineSeparator
	public static final String FS = System.getProperty("file.separator");

	public static String CTGR_NTC_WEB = "NTC3000"; // TBL_CTGR(공지사항_WEB)
	public static String CTGR_NTC_DEV = "NTC1000"; // TBL_CTGR(공지사항_개발자)
	public static String CTGR_NTC_SC = "NTC5000"; // TBL_CTGR(공지사항_ShopClient)

	public static String CTGR_FAQ_DEV = "SFAQ1000"; // FAQ (Developer)
	public static String CTGR_FAQ_SC = "SFAQ3000"; //  FAQ (ShopClient)

	/** Announce Code **/
	public static final String CODE_ANOC_INSPECT = "DP005302"; // 점검
	public static final String CODE_ANOC_NOTICE = "DP005301"; // 알림

	public static final String CODE_BADNOTI_RPT = "PD0043";

	public static final String CONTENT_PLATFORM_ANDROID = "PD005606";

	public static String CTGR_SC_UPDPCATNO = "DP01";

	/**
	 * ------------------------------------------------------------------------ Administrator Area
	 * ------------------------------------------------------------------------
	 */
	public static String YES = "Y";
	public static String NO = "N";

	//ADMIN_POC 인증관련
	public static final String ADMIN_AUTH_SESSION_KEY = "ADMIN_SESSION"; //어드민로그인 정보 세션키

	/** Auth Code Of Administrator **/
	public static final String AUTH_GBN_ADMIN = "AD000101";// 운영자
	public static final String AUTH_GBN_APPROVER = "AD000102";// 검증/할당 승인자
	public static final String AUTH_GBN_TESTER = "AD000103";// Tester

	/**
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ------------------------------------------------------------------------ ------------------------------------------------------------------------ 바로 아래에
	 * 작성된 코드들은 추후에 삭제될 예정이 오니 위쪽의 각 영역에 맞게 상수들을 이동 부탁 드립니다. ------------------------------------------------------------------------
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 * ######################################################################### ########################################
	 */

	public static final int NUMBER_EIGHTH_CIPHERS = 8; // RandomString 자릿수

	public static final String CTGR_TECH = "TECH"; // TBL_CTGR(이슈,지식,다운로드)
	public static final String CTGR_TECH_MAIN = "TECHMAIN"; // 다운로드관리 메인화면 노출용
	public static String CTGR_KNW_WIPI = "PKNW0001"; // KnowledgeBase 코드 (WIPI)
	public static String CTGR_KNW_WIDGET = "PKNW0002"; // KnowledgeBase 코드 (WIDGET)
	public static String CTGR_KNW_GPOS = "PKNW0003"; // KnowledgeBase 코드 (GPOS)

	public static final String CTGR_FORUM = "FRM"; // 포럼

	public static final int MEMBERSHIP_FEE = 100000; // 연회비
	public static final int CBT_MEMBERSHIP_FEE = 0; // CBT연회비

	//public static String INS_TEMP = "TEMP_ADMIN"; // 등록자, 수정자ID(나중에 삭제)

	/** 회원탈퇴신청유형코드 */
	public static String END_REQ_TYPE_USER_POC = "US002001"; // 사용자POC 탈퇴
	public static String END_REQ_TYPE_DEV_POC = "US002002"; // 개발자POC 탈퇴
	public static String END_REQ_TYPE_BOTH_POC = "US002003"; // 사용자/개발자POC 동시탈퇴

	/** 채널코드 */
	public static String CHANEL_CD_PCSUITE = "US001001";
	public static String CHANEL_CD_WEB = "US001002";
	public static String CHANEL_CD_WAP = "US001003";
	public static String CHANEL_CD_MOBILE = "US001004";

	/** 유료개발자 유형코드 */
	public static String MEM_DEV_TYPE_PRIVATE_PERSON = "US000401"; // 개인셀러
	public static String MEM_DEV_TYPE_PRIVATE_BUSINESS = "US000402"; // 개인사업자셀러
	public static String MEM_DEV_TYPE_LEGAL_BUSINESS = "US000403"; // 법인사업자셀러

	/** 회원상태코드 */
	public static String MEM_STATUS_MOBILE = "US000501"; // 정상(모바일)
	public static String MEM_STATUS_EMAIL_WAITING = "US000502"; // 가가입(이메일승인대기)
	//public static String MEM_STATUS_NORMAL = "US000503"; // 정상회원
	//public static String MEM_STATUS_STOP = "US000504"; // 징계회원

	/** 회원분류코드 */
	public static String MEM_TYPE_MOBILE = "US000201"; // 모바일 전용 회원
	public static String MEM_TYPE_NORMAL_DEV_NOPAY = "US000203"; // 일반무료개발
	public static String MEM_TYPE_NORMAL_DEV_PAY = "US000204"; // 일반유료개발

	// 이슈진행상태(PD0038)
	public static final String ISS_PRGR_STAT_01 = "PD003801"; // 검토대기
	public static final String ISS_PRGR_STAT_02 = "PD003802"; // 검토중
	public static final String ISS_PRGR_STAT_03 = "PD003803"; // 해결방안제시
	public static final String ISS_PRGR_STAT_04 = "PD003804"; // 추가이슈제기
	public static final String ISS_PRGR_STAT_05 = "PD003805"; // 지원종료

	// 이슈구분(PD0039)
	public static final String ISS_GBN_01 = "PD003901"; // 이슈등록
	public static final String ISS_GBN_02 = "PD003902"; // 해결방안
	public static final String ISS_GBN_03 = "PD003903"; // 추가이슈

	// 상품관리
	public static final String CODE_PLFM = "PD0007"; //플랫폼구분
	public static final String CODE_VM_TYPE = "PD0056"; //VM_타입
	public static final String CODE_PROD_GBN = "PD0006"; //상품구분
	public static final String CODE_SALESTAT = "PD0004"; //판매상태
	public static final String DP_CAT_GAME = "DP01";
	public static final String DP_CAT_PHONE = "DP02";
	public static final String DP_CAT_FUN = "DP03";
	public static final String DP_CAT_LIVE = "DP04";
	public static final String DP_CAT_MUSIC = "DP05";
	public static final String DP_CAT_CARTOON = "DP06";
	public static final String DP_CAT_MOVIE = "DP07";
	public static final String DP_CAT_EDUCATION = "DP08";
	public static final String DP_CAT_ANDROID = "DP12";

	// 컨텐츠 정산유형
	public static final String CODE_STTL_TYPE = "OR0012";

	// 이슈지원기간
	public static final String ISS_ADD_DAY_AFTER = "7"; // 이슈등록 후 기간내 해결방안제시
	public static final String ISS_SOLVE_DAY_AFTER = "5"; // 해결방안등록 후 기간내 추가이슈제기

	// 날짜, 시간포멧
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	public static final String FORMAT_DATETIME = "yyyy-MM-dd HH:mm";
	public static final String DP_BLANK = "-------";

	//유료개발자 Mail/SMS 발송 코드
	public static final String DEV_EXPIRE_MONTH = "30"; //만료 30일전
	public static final String DEV_EXPIRE_WEEK = "7"; //만료 7일전
	public static final String DEV_EXPIRE_OVER = "0"; //만료

	public static final String DEFAULT_SEARCH_PERIOD = "7"; // 디폴트 검색기간 : 7일 

	/** 전시 상품카테고리 구분코드 */
	public static final String DP_UP_CAT_NO_GAME = "DP01"; // 게임 상품카테고리 구분코드
	public static final String DP_UP_CAT_NO_APP = "DP02"; // 어플리케이션 상품카테고리 구분코드
	public static final String DP_UP_CAT_NO_MULTI = "DP03"; // 멀티미디어 상품카테고리 구분코드

	/** 전시상품이미지구분(DP0001) */
	public static final String DP_IMAGE_TYPE_01 = "DP000101"; // 대표아이콘 원본 212*212
	public static final String DP_IMAGE_TYPE_02 = "DP000102"; // 대표아이콘 원본 52*52
	public static final String DP_IMAGE_TYPE_03 = "DP000103"; // 212x212
	public static final String DP_IMAGE_TYPE_04 = "DP000104"; // 104x104
	public static final String DP_IMAGE_TYPE_05 = "DP000105"; // 97x97
	public static final String DP_IMAGE_TYPE_06 = "DP000106"; // 76x76
	public static final String DP_IMAGE_TYPE_07 = "DP000107"; // 68x68
	public static final String DP_IMAGE_TYPE_08 = "DP000108"; // 52x52
	public static final String DP_IMAGE_TYPE_09 = "DP000109"; // 46x46
	public static final String DP_IMAGE_TYPE_10 = "DP000110"; // 42x42
	public static final String DP_IMAGE_TYPE_11 = "DP000111"; // 40x40
	public static final String DP_IMAGE_TYPE_12 = "DP000112"; // 36x36
	public static final String DP_IMAGE_TYPE_13 = "DP000113"; // 24x24
	public static final String DP_IMAGE_TYPE_14 = "DP000114"; // 미리보기1
	public static final String DP_IMAGE_TYPE_15 = "DP000115"; // 미리보기2
	public static final String DP_IMAGE_TYPE_16 = "DP000116"; // 미리보기3
	public static final String DP_IMAGE_TYPE_17 = "DP000117"; // 미리보기4

	/** 전시상품 아이콘 타입(DP0006) */
	public static final String DP_ICON_TYPE_HOT = "DP000601"; // HOT
	public static final String DP_ICON_TYPE_NEW = "DP000602"; // NEW

	/** 검색기간 */
	public static final int WEEK = 0; //인기검색어 이번주
	public static final int THIS_WEEK = -1; //  금주
	public static final int LAST_WEEK = -2; // 1주전
	public static final int TWO_WEEKS_AGO = -3; // 2주전
	public static final int THREE_WEEKS_AGO = -4; // 3주전
	public static final int FOUR_WEEKS_AGO = -5; // 4주전

	/** 태그 분류 코드 **/
	public static final String TAG_TYPE_CATEGORY = "DP004501"; // 카테고리 태그
	public static final String TAG_TYPE_KEYWORD = "DP004502"; // 키워드 태그
	public static final String TAG_TYPE_SELLER = "DP004503"; // 셀러 태그
	public static final String TAG_TYPE_TASTE_TAGET = "DP004504"; // 테이스트 대상 태그
	public static final String TAG_TYPE_TASTE_ENV = "DP004505"; // 테이스트 장소 태그
	public static final String TAG_TYPE_TASTE_ACTION = "DP004506"; // 테이스트 목적 태그

	//단말기 제조사
	public static final String CODE_PHONE_MAKE_COMP = "PD0040";

	/** 컨텐츠 타입(PD0025 */
	public static final String PD_CONTENT_TYPE_CHANNEL = "PD002501"; // 채널
	public static final String PD_CONTENT_TYPE_EPISODE = "PD002502"; // 에피소드

	// 메인 TOP 유형 코드(DP0005)
	public static final String ITOPPING_TOP = "DP000500"; // Whoopy TOP
	public static final String GAME_TOP = "DP000501"; // 게임 TOP
	public static final String FUN_TOP = "DP000503"; // FUN TOP
	public static final String LIFE_TOP = "DP000504"; // 생활/위치 TOP
	public static final String MUSIC_TOP = "DP000505"; // 뮤직 TOP
	public static final String COMIC_TOP = "DP000506"; // 만화 TOP
	public static final String MOVIE_TOP = "DP000507"; // 방송/영화 TOP
	public static final String EDU_TOP = "DP000508"; // 어학/교육 TOP
	public static final String APPL_TOP = "DP000503"; // TODO 애플리케이션 TOP 은 제거해야 함.

	// MALL 구분 코드(DP0002)
	public static final String APPL_MALL = "DP000201"; // 애플리케이션 MALL
	public static final String GAME_MALL = "DP000202"; // 게임 MALL
	public static final String MULT_MALL = "DP000203"; // 멀티미디어 MALL         

	/** 전시 상품카테고리 코드 */
	public static final String DP_CATEGORY_MUSIC = "DP03001"; // 뮤직
	public static final String DP_CATEGORY_VIDEO = "DP03004"; // 동영상
	public static final String DP_CATEGORY_EDU = "DP03007"; // 어학/교육
	public static final String DP_CATEGORY_CARTOON = "DP03008"; // 만화
	public static final String DP_CATEGORY_TCAST = "DP03010"; // T-Cast

	/** SMS 발송타입 */
	public static final String SMS_SEND_TP_NOW = "AD000501"; // 즉시전송
	public static final String SMS_SEND_TP_LATER = "AD000502"; // 예약전송

	/** SMS 전송상태 */
	public static final String SMS_SEND_STAT_FINISH = "AD000401"; // 전송완료
	public static final String SMS_SEND_STAT_WAIT = "AD000402"; // 전송대기
	public static final String SMS_SEND_STAT_FAIL = "AD000403"; // 전송실패

	/** 전환유형코드 */
	public static String TRANS_TYPE_FREE_TO_PRIVATE_PERSON = "US001501"; // 유료전환 (무료개발자 -> 유료개인개발자)
	public static String TRANS_TYPE_FREE_TO_PRIVATE_BUSINESS = "US001502"; // 개인사업자가입 (무료개발자 -> 개인사업자)
	public static String TRANS_TYPE_FREE_TO_LEGAL_BUSINESS = "US001503"; // 법인사업자가입 (무료개발자 -> 법인사업자)
	public static String TRANS_TYPE_CHARGE_TO_PRIVATE_BUSINESS = "US001504"; // 개인사업자전환 (유료개인개발자 -> 개인사업자)
	public static String TRANS_TYPE_CHARGE_TO_LEGAL_BUSINESS = "US001505"; // 법인사업자전환 (유료개인개발자 -> 법인사업자)
	public static String TRANS_TYPE_BUSINESS_TO_BUSINESS = "US001506"; // 사업자간 전환 (개인사업자 -> 법인사업자)

	/** 처리상태코드 */
	public static String PROCCESS_STATUS_MOTION = "US001601"; // 승인요청
	public static String PROCCESS_STATUS_FINISH = "US001602"; // 승인완료
	public static String PROCCESS_STATUS_REJECT = "US001603"; // 승인거절

	//상품 이미지 코드
	public static String CONTENT_IMAGE_ICON1 = "DP000101"; //(원본)대표아이콘 212*212
	public static String CONTENT_IMAGE_ICON2 = "DP000102"; //(원본)대표아이콘 76*76
	public static String CONTENT_IMAGE_PREV1 = "DP000103"; //(원본)미리보기1
	public static String CONTENT_IMAGE_PREV2 = "DP000104"; //(원본)미리보기2
	public static String CONTENT_IMAGE_PREV3 = "DP000105"; //(원본)미리보기3
	public static String CONTENT_IMAGE_PREV4 = "DP000106"; //(원본)미리보기4
	public static String CONTENT_IMAGE_DESC = "DP000107"; //(원본)상품설명이미지
	public static String IMG_214_214 = "DP000108"; //(생성)대표아이콘 212*212
	public static String IMG_104_104 = "DP000109"; //(생성)대표아이콘 212*212
	public static String IMG_80_81 = "DP000110"; //(생성)대표아이콘 76*76
	public static String IMG_76_76 = "DP000111"; //(생성)대표아이콘 76*76
	public static String IMG_72_73 = "DP000112"; //(생성)대표아이콘 76*76
	public static String IMG_52_52 = "DP000113"; //(생성)대표아이콘 76*76
	public static String IMG_44_45 = "DP000114"; //(생성)대표아이콘 76*76
	public static String IMG_42_42 = "DP000115"; //(생성)대표아이콘 76*76
	public static String IMG_PREV1 = "DP000116"; //(생성)미리보기1
	public static String IMG_PREV2 = "DP000117"; //(생성)미리보기2
	public static String IMG_PREV3 = "DP000118"; //(생성)미리보기3
	public static String IMG_PREV4 = "DP000119"; //(생성)미리보기4
	public static String IMG_24_24 = "DP000120"; //(생성)대표아이콘 76*76
	public static String IMG_64_64 = "DP000121"; //(생성)대표아이콘 64*64
	public static String IMG_32_32 = "DP000122"; //(생성)대표아이콘 32*32
	public static String IMG_120_111 = "DP000124"; //(원본)대표아이콘 120*111 써클
	public static String IMG_72_72 = "DP000125"; //(원본)대표아이콘 72*72

	//검증 Step CD
	public static final String CODE_VERIFY_STEP_REQ = "PD000301"; // 검증요청

	/** 통신사 */
	public static final String TELECOM_SKT = "US001201"; // SKT
	public static final String TELECOM_KFT = "US001202"; // KTF
	public static final String TELECOM_LGT = "US001203"; // LGT

	/** GNEX 플레이어 Product ID */
	public static final String GNEX_PLAYER_PID = "0000001263";

	/** 위젯 플레이어 Product ID */
	public static final String WIDGET_PLAYER_PID = "0000001255";

	//  public static String TCAST_IMG_EFFECT[] = {"/data/img/img_filter/thumbnail/40/round_mask.png","/data/img/img_filter/thumbnail/40/filter_merge.png","/data/img/img_filter/thumbnail/40/drop_shadow.png"};
	//  public static int    TCAST_IMG_EXPAND[] = {1,1,3,4};//"1,1,3,4";
	public static String TCAST_IMG_EFFECT[] = { "/data/img/img_filter/icon/104/round_mask.png",
			"/data/img/img_filter/icon/104/filter_merge.png", "/data/img/img_filter/icon/104/drop_shadow.png" };
	public static int TCAST_IMG_EXPAND[] = { 4, 4, 18, 18 };
	public static String TCAST_IMG_TYPE = "jpge";
	public static int TCAST_IMG_WIDTH = 80;
	public static int TCAST_IMG_HEIGHT = 80;
	public static String TCAST_DIR_PATH = "thumbnail";

	public static String TCAST_FILE_PATH = "/data/img/web/tcast/";
	public static String TCAST_WEB_PATH = "/images/web/tcast/";

	public static String MULTITOP_FILE_PATH = "/data/common/flash/web/multi/img/";
	public static String MULTITOP_WEB_PATH = "/flash/web/multi/img/";

	//지적재산권 접수구분
	public static final String IPR_TYPE_REPORT = "PD008101"; //신고서
	public static final String IPR_TYPE_VINDICATION = "PD008102"; //소명서
	public static final String IPR_TYPE_RIGHTS_PRT_REQ = "PD008103"; //권리보호요청

	//지적재산권 진행상태구분
	public static final String IPR_STAT_NEW = "PD008201"; //신  규
	public static final String IPR_STAT_ING = "PD008202"; //진행중
	public static final String IPR_STAT_RET = "PD008203"; //반려
	public static final String IPR_STAT_END = "PD008204"; //완료 

	/** 결제종류코드 */
	public static final String PAY_TP_MEMBERSHIP_FEE = "US002201"; // 연회비
	public static final String PAY_TP_PROD_REG_FEE = "US002202"; // 등록비 수수료

	/** 결제수단코드 */
	public static final String PAY_METHOD_CREDIT_CARD = "US002301"; // 신용카드
	public static final String PAY_METHOD_PAYBACK = "US002302"; // 정산액

	/** 변경주체코드 */
	public static final String CHANGER_IS_USER = "US002401"; // 회원
	public static final String CHANGER_IS_OPERATOR = "US002402"; // 운영자

	/** 변경내역코드 */
	public static final String CHNG_HIST_TSTORE10 = "US002501";
	public static final String CHNG_HIST_TSTORE20 = "US002502";
	public static final String CHNG_HIST_TSTORE30 = "US002503";
	public static final String CHNG_HIST_PAYBACK = "US002504"; // 정산액
	public static final String CHNG_HIST_BETA = "US002598"; // 베타서비스 기간동안 결제없이 유료회원 된 경우 (~09.12.31)
	public static final String CHNG_HIST_RENEW_EVENT = "US002599"; // 유료회원 연장 이벤트

	/** VM TYPE */
	public static final String CONTENT_PLATFORM_BADA = "PD005610";
	public static final String CONTENT_PLATFORM_FLASH = "PD005609";
	//public static final String CONTENT_PLATFORM_ANDROID = "PD005606";
	public static final String CONTENT_PLATFORM_LINUX = "PD005605";
	public static final String CONTENT_PLATFORM_WM = "PD005604";
	public static final String CONTENT_PLATFORM_WIDGET = "PD005602";
	public static final String CONTENT_PLATFORM_WIPI = "PD005601";

	/** CONTENTS TYPE */
	public static final String CONTENTS_TYPE_GVM = "PD005701";
	public static final String CONTENTS_TYPE_SKVM = "PD005702";
	public static final String CONTENTS_TYPE_GENERIC = "PD005704";
	public static final String CONTENTS_TYPE_WIPIC = "PD005711";
	public static final String CONTENTS_TYPE_WIPIJAVA = "PD005712";

	/** 상품구분 */
	public static final String PROD_GBN_EXECUTABLE = "PD000601"; // 실행파일
	public static final String PROD_GBN_LIBRARY = "PD000602"; // 라이브러리
	public static final String PROD_GBN_VM = "PD000603"; // VM
	public static final String PROD_GBN_BLOGWIDGET_PAY = "PD000605"; // 블로그 위젯 과금
	public static final String PROD_GBN_BLOGWIDGET_NOPAY = "PD000606"; // 블로그 위젯 비과금

	/** Provision 아이템 구분 */
	public static final String PROVISION_ITEM_TYPE_SOUND = "PD008301";

	/** Core Application 모듈 구분 */
	public static final String CORE_APPL_MODULE = "DP0030";

	/** Core Application 어플리케이션 유형 구분 */
	public static final String CORE_APPL_TYPE = "DP0028";
	public static final String CORE_APPL_TYPE_ALL = "DP002801"; //ALL

	/** Core Application LCD SIZE 구분 */
	public static final String CORE_APPL_LCDSIZE = "PD0021";

	/** Core Application 버전 유형 구분 */
	public static final String CORE_VER_TYPE = "DP0029";
	public static final String CORE_VER_TYPE_NA = "DP002901"; //ALL

	/** Core Application 터치 지원 구분 */
	public static final String CORE_APPL_TOUCH_Y = "PD005201";
	public static final String CORE_APPL_TOUCH_N = "PD005200";

	/** Signed System return Message */
	public static final String SIGNED_AND_DEPLOY_ERROR_IN_SYSTEM = "99";
	public static final String SIGNED_AND_DEPLOY_ERROR_FILE_SIZE_DEPLOY = "62";
	public static final String SIGNED_AND_DEPLOY_ERROR_OF_DOWNLOAD_DEPLOY = "61";
	public static final String SIGNED_AND_DEPLOY_LIBRARY_ERROR = "54";
	public static final String SIGNED_AND_DEPLOY_FILE_WRITE_FAIL = "53";
	public static final String SIGNED_AND_DEPLOY_BLANK_FILE_PART = "52";
	public static final String SIGNED_AND_DEPLOY_NO_XML_FILE_NODE_NAME = "51";
	public static final String SIGNED_AND_DEPLOY_NO_SELFTEST_FILE = "49";
	public static final String SIGNED_AND_DEPLOY_NO_MANUAL_FILE = "48";
	public static final String SIGNED_AND_DEPLOY_NO_SCENARIO = "47";
	public static final String SIGNED_AND_DEPLOY_NO_TEST_COVERAGE_FILE = "46";
	public static final String SIGNED_AND_DEPLOY_NO_SOURCE_TEST_RESULT = "45";
	public static final String SIGNED_AND_DEPLOY_NO_API_TEST_FILE = "44";
	public static final String SIGNED_AND_DEPLOY_NO_APP_META_FILE = "43";
	public static final String SIGNED_AND_DEPLOY_NO_DESC_FILE = "42";
	public static final String SIGNED_AND_DEPLOY_NO_BINARY = "41";
	public static final String SIGNED_AND_DEPLOY_SPRT_PHONE_ERROR = "35";
	public static final String SIGNED_AND_DEPLOY_PRE_INFO_ERROR = "34";
	public static final String SIGNED_AND_DEPLOY_PACKAGE_FORMAT_ERROR = "33";
	public static final String SIGNED_AND_DEPLOY_BASIC_INFO_ERROR = "32";
	public static final String SIGNED_AND_DEPLOY_PROVISION_ERROR = "31";
	public static final String SIGNED_AND_DEPLOY_VERIFY_PRGR_YN = "19";
	public static final String SIGNED_AND_DEPLOY_PROCESS_DUP = "21";
	public static final String SIGNED_AND_DEPLOY_VIRUSCHK_FAIL = "17";
	public static final String SIGNED_AND_DEPLOY_ID_ERROR = "11";
	public static final String SIGNED_AND_DEPLOY_VERIFY_OBJ_NULL = "04";
	public static final String SIGNED_AND_DEPLOY_VERIFY_STATUS_NULL = "03";
	public static final String SIGNED_AND_DEPLOY_NO_SUBCONTENTS_FOR_VERIFY = "02";
	public static final String SIGNED_AND_DEPLOY_ALREADY_PROCESSING = "01";
	public static final String SIGNED_AND_DEPLOY_SUCCESS = "00";

	/** Download Server Send Xml Length */
	public static final int XML_LENGTH_THIRD = 10500;
	public static final int XML_LENGTH_SECOND = 7000;
	public static final int XML_LENGTH_FIRST = 3500;

	/**
	 * JOBGBN : A [ALL ] => 메인 컨텐츠 정보 및 서브컨텐츠 정보 등록 C [CREATE ] => 서브컨텐츠 정보 등록 U [UPDATE ] => 서브컨텐츠 정보 수정 D [DELETE ] => 서브컨텐츠 정보 삭제 M [MAIN CONTENTS UPDATE] =>
	 * 메인컨텐츠 상품정보 수정시 수정 정보 전송
	 */
	public static String INFO_MAIN_AND_SUBCONTENTS_ALL_REGIST = "A";
	public static String INFO_SUBCONTENTS_REGIST = "C";
	public static String INFO_SUBCONTENTS_MODIFY = "U";
	public static String INFO_SUBCONTENTS_DELETE = "D";
	public static String INFO_MAINCONTENTS_MODIFY = "M";

	public static String DOWNLOAD_SEND_TO_OMP_MSG_SUCCESS = "0";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_TX_ID = "OME-00001";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_SVR_ID = "OME-00002";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_REQ_TIME = "OME-00003";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_SUB_CONTS_VER = "OME-00010";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_MOD_PROD = "OME-00021";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_DEL_PROD = "OME-00022";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_MOD_MAIN_CONTS = "OME-00023";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_DEL_MAIN_CONTS = "OME-00024";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_MOD_SUB_CONTS = "OME-00025";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_DEL_SUB_CONTS = "OME-00026";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NOT_CONECT_REMOTE_DB = "OME-00047";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_END_DEPLOY_PROCESS = "OME-00048";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_XML_FILE_SAVE_ERROR = "OME-00050";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_DEPLOY_INFO = "OME-00100";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NOT_VALID_REQ_XML_SCHEMA = "OME-00150";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NOT_VALID_XML_FORMAT = "OME-00200";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_MAIN_CONTS_ICON = "OME-00300";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_SUB_CONTS_NORMAL_FILE = "OME-00401";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_SUB_CONTS_ACHIVE_FILE = "OME-00402";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NOT_MATCH_NORMAL_FILE_SIZE = "OME-00411";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NOT_MATCH_ACHIVE_FILE_SIZE = "OME-00412";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_SUB_CONTS_NORMAL_FILE_SIZE = "OME-00421";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NO_SUB_CONTS_ACHIVE_FILE_SIZE = "OME-00422";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NOT_FIND_REMOTE_OBJECT = "OME-01000";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_NOT_CREATE_REMOTE_OBJECT = "OME-01100";
	public static String DOWNLOAD_SEND_TO_OMP_MSG_UNKOWN_ERROR = "OME-99999";

	public static final String SIGNED_MSG_TYPE_JOIN_REQ = "join_request";
	public static final String SIGNED_MSG_TYPE_JOIN_RES = "join_response";
	public static final String SIGNED_MSG_TYPE_MODIFY_REQ = "modify_request";
	public static final String SIGNED_MSG_TYPE_MODIFY_RES = "modify_response";
	public static final String SIGNED_MSG_TYPE_DROP_REQ = "drop_request";
	public static final String SIGNED_MSG_TYPE_DROP_RES = "drop_response";

	public static final String SIGNED_MEMINFO_RESULT_SUCCESS = "00";
	public static final String SIGNED_MEMINFO_RESULT_IDDUP = "01";
	public static final String SIGNED_MEMINFO_RESULT_DATA_LACK = "02";
	public static final String SIGNED_MEMINFO_RESULT_SYS_ERR = "99";

	/** 외부 Mall **/
	public static final String SAMSUNG_APPS_CD = "MM000101"; //삼성 Apps
	public static final String LG_APPS_CD = "MM000102"; //엘지 Apps

	/** 등록서류항목코드 */
	public static final String DOC_HM_01 = "US001901"; // 사업자 등록증 사본
	public static final String DOC_HM_02 = "US001902"; // 법인명의 통장사본
	public static final String DOC_HM_03 = "US001903"; // 법인(개인)인감증명서원본
	public static final String DOC_HM_04 = "US001904"; // 통신판매업 신고증 사본

	/** WAP 프리존관리 타입 */
	public static final String HST_TYPE_01 = "DP003801"; // 추천상품
	public static final String HST_TYPE_02 = "DP003802"; // 최신Top5
	public static final String HST_TYPE_03 = "DP003803"; // 인기Top5

	/** 쿠폰 발행 주최 코드 **/
	public static final String CN_ISSUE_AD = "OR002301"; //운영자
	public static final String CN_ISSUE_DEV = "OR002302"; //개발자
	/** 쿠폰 발행 타입 코드 **/
	public static final String CN_TYPE_ALL = "OR002401"; //전체회원
	public static final String CN_TYPE_TARGET = "OR002402"; //특정회원
	/** 쿠폰 발행 종류 코드 **/
	public static final String CN_KIDE_VARIETY_ALL = "OR002501"; //전체대상
	public static final String CN_KIDE_VARIETY_LIMIT = "OR002502"; //한정판
	public static final String CN_KIDE_VARIETY_SALE_ALL = "OR002503"; //구매자전체
	public static final String CN_KIDE_VARIETY_ID = "OR002504"; //아이디
	public static final String CN_KIDE_VARIETY_HP = "OR002505"; //휴대폰번호
	/** 쿠폰 발행 상태 코드 **/
	public static final String CN_STAT_MAKE = "OR002601"; //생성완료
	public static final String CN_STAT_ISSUE = "OR002602"; //발급완료
	public static final String CN_STAT_USE = "OR002603"; //사용중
	public static final String CN_STAT_USE_STOP = "OR002604"; //사용중지
	public static final String CN_STAT_END = "OR002605"; //사용종료
	public static final String CN_STAT_CLOSE = "OR002606"; //쿠폰마감
	public static final String CN_STAT_EXPIRE = "OR002607"; //기간종료
	/** 쿠폰 발행 전송 상태 코드 **/
	public static final String CN_TRANS_STAND = "OR002701"; //전송대기
	public static final String CN_TRANS_REFUSAL = "OR002702"; //전송거부
	public static final String CN_TRANS_FINISH = "OR002703"; //전송성공
	public static final String CN_TRANS_FAILURE = "OR002704"; //전송실패
	/** 단말 Remapping 상태 코드 **/
	public static final String PRM_DL_GRP = "PD0098"; //단말진행상태GRP
	public static final String PRM_DL_INS_REQ = "PD009801"; //등록요청
	public static final String PRM_DL_INS_COM = "PD009802"; //등록완료
	public static final String PRM_DL_INS_FAIL = "PD009803"; //등록요청실패
	public static final String PRM_DL_INS_RE_FAIL = "PD009804"; //DL등록실패
	public static final String PRM_DL_DEL_REQ = "PD009805"; //삭제요청
	public static final String PRM_DL_DEL_COM = "PD009806"; //삭제완료
	public static final String PRM_DL_DEL_FAIL = "PD009807"; //삭제요청실패
	public static final String PRM_DL_DEL_RE_FAIL = "PD009808"; //DL삭제실패
	/** mISSD 검증 타입 **/
	public static final String MISSD_TYPE_SCAN = "PD009901"; //스캐닝
	public static final String MISSD_TYPE_INTEG = "PD009902"; // 무결성
	/** mISSD 조치 단계코드 **/
	public static final String MISSD_VERIFY_STAT_REQ = "PD010001"; //missd_request
	public static final String MISSD_VERIFY_STAT_REQ_ACK = "PD010002"; //missd_request_ack
	public static final String MISSD_VERIFY_STAT_MODIFY = "PD010003"; //상품 상태 변경
	public static final String MISSD_VERIFY_STAT_RES = "PD010004"; //missd_response
	public static final String MISSD_VERIFY_STAT_RES_ACK = "PD010005"; //missd_response_ack
	/** mISSD 조치 결과 **/
	public static final String MISSD_RESULT_USER_STOP = "PD010101"; // 판매금지
	public static final String MISSD_RESULT_HIS = "PD010102"; //히스토리 화일
	public static final String MISSD_RESULT_NON_FILE = "PD010103"; // 해당 파일 없음
	public static final String MISSD_RESULT_NON_PROD = "PD010104"; // 상품정보 없음
	/** mISSD 바이너리 구분 **/
	public static final String MISSD_BINARY_ORG = "PD010201"; // 원본 바이너리
	public static final String MISSD_BINARY_S = "PD010102"; // S 바이너리
	public static final String MISSD_BINARY_V = "PD010103"; // V 바이너리
	/** OS or VM Version **/
	public static final String WIPI_VM_108 = "PD006201"; // WIPI 1.08
	public static final String WIPI_VM_112 = "PD006202"; // WIPI 1.12
	public static final String WIPI_VM_113 = "PD006203"; // WIPI 1.13
	public static final String WIPI_VM_20 = "PD006204"; // WIPI 2.0
	public static final String WIPI_VM_109 = "PD006205"; // WIPI 1.09
	public static final String WIPI_VM_110 = "PD006206"; // WIPI 1.10
	public static final String WIPI_VM_111 = "PD006207"; // WIPI 1.11
	public static final String WIPI_VM_114 = "PD006208"; // WIPI 1.14
	public static final String WIPI_VM_214 = "PD006210"; // WIPI 2.14
	public static final String WM_OS_61 = "PD006302"; // WM 6.1
	public static final String WM_OS_65 = "PD006303"; // WM 6.5
	public static final String WM_OS_6165 = "PD006304"; // WM 6.1&6.5
	public static final String LINUX_OS_10 = "PD008901"; // LINUX 1.0
	public static final String ANDROID_OS_201 = "PD009102"; // ANDROID 2.01
	public static final String ANDROID_OS_202 = "PD009104"; // ANDROID 2.02
	public static final String ANDROID_OS_201202 = "PD009105"; // ANDROID 2.01&2.02
	public static final String BADA_OS_12 = "PD010501"; // BADA 1.2

	public static final String CONTENT_DRM_INTERVAL = "PD001301"; //N일
	public static final String CONTENT_DRM_DUEDATE = "PD001302"; //Due Date
	public static final String CONTENT_DRM_COUNT = "PD001303"; //Execution Count
	public static final String CONTENT_DRM_INFINITE = "PD001304"; //Infinite
	
	

}