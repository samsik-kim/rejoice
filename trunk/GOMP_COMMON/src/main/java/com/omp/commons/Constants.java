/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author       Date            Description
 * --------     ----------      ------------------
 * ?            ?               ?
 * nefer        2009.12.08      attach 
 *
 */
package com.omp.commons;

/**
 * 공통 상수 객체
 * 
 * @author pat
 */
public class Constants {

	/** 개발자 메인 */
	public static final String DEV_MAIN_URL = "/main/main.omp";

	/** 판매상태 */
	/** 미승인상품 */
	public static final String CONTENT_SALE_STAT_NA = "PD000402";
	/** 판매대기 */
	public static final String CONTENT_SALE_STAT_WAIT = "PD000403";
	/** 판매중 */
	public static final String CONTENT_SALE_STAT_ING = "PD000404";
	/** 판매중지 */
	public static final String CONTENT_SALE_STAT_STOP = "PD000405";
	/** 판매금지 */
	public static final String CONTENT_SALE_STAT_RESTRIC = "PD000406";
	/** 해제요청 */
	public static final String CONTENT_SALE_STAT_UNREGIST = "PD000407";

	/** 플랫폼Platform 구분 */
	public static final String CODE_PLFM = "PD0056";
	/** 상품 관리 - 판매상태 구분 */
	public static final String CODE_SALESTAT = "PD0004";
	/** 상품 관리 - 검증진행상태 구분 */
	public static final String CODE_VERIFYSTAT = "PD0053";

	/** 상품 카테고리 */
	public static final String DP_CAT_GAME = "DP01";

	/** 이용 등급 */
	/** 전체 이용 가능 */
	public static final String GRADE_ALL = "PD004401";
	/** 성인 이용 가능 */
	public static final String GRADE_ADULT = "PD004404";

	/** 상품 진행 코드 */
	/** 미검증 */
	public static final String CODE_VERIFY_INIT = "PD005399";
	/** 검증대기 */
	public static final String CODE_VERIFY_REQ = "PD005301";
	/** 검증중 */
	public static final String CODE_VERIFY_ING = "PD005302";
	/** 검증 완료 */
	public static final String CODE_VERIFY_END = "PD005303";
	/** Test 완료 */
	public static final String CODE_VERIFY_TEST_END = "PD005304";
	/** Test 검증 반려 */
	public static final String CODE_VERIFY_TEST_REJECT = "PD005305";
	/** 검증 취소 */
	public static final String CODE_VERIFY_CANCEL = "PD005306";

	/** 상품 구분 코드 */
	/** Application */
	public static final String PRODUCT = "PD000601";
	/** Library */
	public static final String LIBRARY = "PD000602";

	/** Contents Type */
	public static final String CONTS_TYPE_NATIVE = "PD009301";

	/** 상품 이미지 */
	/** 대표아이콘1 */
	public static final String CONTENT_IMAGE_ICON1 = "DP000101";
	/** 미리보기 이미지1 */
	public static final String CONTENT_IMAGE_PREV1 = "DP000103";
	/** 미리보기 이미지2 */
	public static final String CONTENT_IMAGE_PREV2 = "DP000104";
	/** 미리보기 이미지3 */
	public static final String CONTENT_IMAGE_PREV3 = "DP000105";
	/** 미리보기 이미지 4 */
	public static final String CONTENT_IMAGE_PREV4 = "DP000106";
	/** 설명 이미지 */
	public static final String CONTENT_IMAGE_DESC = "DP000107";
	/** 72 * 72 */
	public static final String IMG_72_72 = "DP000108";
	/** 130 * 130 */
	public static final String IMG_130_130 = "DP000109";

	/** LCD SIZE 구분 */
	public static final String PHONE_LCD_SIZE = "PD0021";
	/** Provission Item 구분 */
	public static final String PROVISION_TYPE_LCD_SIZE = "PD008303";

	/** 상품 승인 상태 */
	/** 등록 */
	public static final String AGREEMENT_STATUS_INIT = "PD005001";
	/** 승인 */
	public static final String AGREEMENT_STATUS_AGREE = "PD005002";
	/** 반려 */
	public static final String AGREEMENT_STATUS_REJECT = "PD005003";

	/** 상품 관리 */
	public static final String CONTENT_PLATFORM_WM = "PD005604";
	/** 상품 관리 */
	public static final String CONTENT_PLATFORM_ANDROID = "PD005606";

	/** DRM OPT SET */
	/** ARM COSTRAINT 기간 N일 */
	public static final String CONTENT_DRM_INTERVAL = "PD001301";
	/** ARM COSTRAINT 기한 Due Date */
	public static final String CONTENT_DRM_DUEDATE = "PD001302";
	/** ARM COSTRAINT 횟수 Execution Count */
	public static final String CONTENT_DRM_COUNT = "PD001303";
	/** ARM COSTRAINT 영구소장 Infinite */
	public static final String CONTENT_DRM_INFINITE = "PD001304";

	/** Android Version Code */
	/** Android OS */
	public static final String ANDROID_OS = "PD0091";
	/** Android OS 7 Version 2.1 */
	public static final String ANDROID_OS_VERSION_7 = "PD009102";
	/** Android OS 8 Version 2.2 */
	public static final String ANDROID_OS_VERSION_8 = "PD009104";
	/** Android OS 9 Version 2.3 */
	public static final String ANDROID_OS_VERSION_9 = "PD009106";

	/** Certification Request Comment Code **/
	/** 검증 사유 그룹코드 */
	public static final String VERIFY_COMMENT_CD_GRP = "PD0111";
	/** 최초 검증 */
	public static final String VERIFY_COMMENT_CD_INIT = "PD011101";
	/** 단말 추가 */
	public static final String VERIFY_COMMENT_CD_DEVICE_ADD = "PD011102";
	/** 단말 삭제 */
	public static final String VERIFY_COMMENT_CD_DEVICE_DEL = "PD011103";
	/** 기능 변경 */
	public static final String VERIFY_COMMENT_CD_FUNC_CHANGE = "PD011104";
	/** 이미지 변경 */
	public static final String VERIFY_COMMENT_CD_IMG_CHANGE = "PD011105";
	/** manifes 변경 */
	public static final String VERIFY_COMMENT_CD_MANIFEST_CHANGE = "PD011106";
	/** 기타 */
	public static final String VERIFY_COMMENT_CD_MANIFEST_ETC = "PD011107";

	// 단말매핑 등록/삭제
	/** 단말등록 */
	public static final String PHONE_MAPPING_REG = "PD006101";
	/** 단말삭제 */
	public static final String PHONE_MAPPING_DEL = "PD006102";

	// 단말매핑요청 상태
	/** 등록요청 */
	public static final String MAPPING_REG_FIRST_ACC_SUC = "PD006201";
	/** 등록완료 */
	public static final String MAPPING_REG_SECOND_ACC_SUC = "PD006202";
	/** 등록요청실패 */
	public static final String MAPPING_REG_FIRST_ACC_FAIL = "PD006203";
	/** DL등록실패 */
	public static final String MAPPING_REG_SECOND_ACC_FAIL = "PD006204";
	/** 삭제요청 */
	public static final String MAPPING_DEL_FIRST_ACC_SUC = "PD006205";
	/** 삭제완료 */
	public static final String MAPPING_DEL_SECOND_ACC_SUC = "PD006206";
	/** 삭제요청실패 */
	public static final String MAPPING_DEL_FIRST_ACC_FAIL = "PD006207";
	/** DL삭제실패 */
	public static final String MAPPING_DEL_SECOND_ACC_FAIL = "PD006208";

	// 상품 UPGRADE 상태
	/** 최초등록상품 */
	public static final String CONTENT_UPGRADE_STAT_NA = "PD011201";
	/** 상품 UPGRADE 준비 */
	public static final String CONTENT_UPGRADE_STAT_READY = "PD011202";
	/** 상품 UPGRADE를 위한 UPDATE 작업 시작 */
	public static final String CONTENT_UPGRADE_STAT_STANDBY = "PD011203";
	/** UPDATE 변경 완료 */
	public static final String CONTENT_UPGRADE_STAT_COMPLETE = "PD011299";

	// 단말 서비스 구분 코드
	/** 미등록 */
	public static final String PHONE_INFO_NOT_REGIST = "US005201";
	/** 테스트 */
	public static final String PHONE_INFO_TEST_REGIST = "US005202";
	/** 상용 */
	public static final String PHONE_INFO_REAL_REGIST = "US005203";

}