/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 11. | Description
 *
 */
package com.omp.admin.product.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.omp.admin.product.model.Contents;
import com.omp.admin.product.model.ContentsImg;
import com.omp.admin.product.model.ContentsSaleHistory;
import com.omp.admin.product.model.ContentsSub;
import com.omp.admin.product.model.ContentsVerify;
import com.omp.admin.product.model.DpCat;
import com.omp.admin.product.model.SubContents;

/**
 * 상품관리 서비스
 * 
 * @author bcpark
 * @version 0.1
 */
public interface ContentsManagementService {

	/**
	 * 카테고리 리스트를 가져온다.
	 * 
	 * @param upDpCatNo
	 * @return @
	 */
	List<DpCat> getDpCatList(String upDpCatNo);

	/**
	 * 검색조건에 따른 상품 리스트 조회
	 * 
	 * @param sub
	 * @return
	 */
	List<Contents> getContentsList(ContentsSub sub);

	/**
	 * 상품 리스트 엑셀 조회
	 * 
	 * @param sub
	 * @return
	 */
	File getExcelContentsList(ContentsSub sub);

	/**
	 * 상품 기본정보 조회
	 * 
	 * @param sub
	 * @return
	 */
	Contents getContentsBaseInfo(String cid);

	/**
	 * @param sub
	 * @return
	 */
	Contents getContentsProductInfo(String cid);

	/**
	 * 개발자 등록 키워드 조회
	 * 
	 * @param cid
	 * @return
	 */
	List<String> getContentsTagList(String cid);

	/**
	 * 상품 이미지 리스트 조회
	 * 
	 * @param cid
	 * @return
	 */
	Map<String, ContentsImg> getContentsImgMap(String cid);

	/**
	 * 상품의 검증레벨 업데이트
	 * 
	 * @param paramMap
	 * @return
	 */
	int updateContentsSignOption(Map<String, String> paramMap);

	/**
	 * 상품 정보 update<br/>
	 * 상품이 판매중이라면 DD MAIN배포, DP MAIN 배포를 같이 수행한다.
	 * 
	 * @param paramMap
	 * @return
	 */
	int updateContentsProductInfo(Map paramMap);

	/**
	 * 상품 개발정보 조회
	 * 
	 * @param cid
	 * @return
	 */
	Contents getContentsDevConts(String cid);

	/**
	 * 상품 개발정보 SCID 정보 조회
	 * 
	 * @param cid
	 * @return
	 */
	List<SubContents> getSubContents(String cid);

	/**
	 * 상품 판매중 개발정보 조회
	 * 
	 * @param cid
	 * @return
	 */
	Contents getSaleDevConts(String cid);

	/**
	 * 상품 판매중 개발정보 SCID 정보 조회
	 * 
	 * @param cid
	 * @return
	 */
	List<SubContents> getSaleSubContents(String cid);

	/**
	 * 상품 검증요청개발정보 조회
	 * 
	 * @param cid
	 * @return
	 */
	Contents getVerifyDevConts(String cid);

	/**
	 * 상품 검증요청 개발정보 SCID 조회
	 * 
	 * @param cid
	 * @return
	 */
	List<SubContents> getVerifySubContents(String cid);

	/**
	 * 상품 판매상태 변경내역 조회
	 * 
	 * @param cid
	 * @return
	 */
	List<ContentsSaleHistory> getContentsSaleHistory(String cid);

	/**
	 * 상품 검증내역 리스트 조회
	 * 
	 * @param cid
	 * @return
	 */
	List<ContentsVerify> getContentsVerifyList(ContentsSub sub);

	/**
	 * 상품 검증내역 상세 팝업 조회
	 * 
	 * @param cid
	 * @return
	 */
	List<SubContents> getContentsVerifyDetailList(ContentsSub sub);

	/**
	 * 상품의 정산율 업데이트
	 * 
	 * @param paramMap
	 * @return
	 */
	int updateContentsRate(Map<String, String> paramMap);

	/**
	 * 상품 판매금지
	 * 
	 * @param paramMap
	 */
	int updateStopSaleStat(Map<String, String> paramMap);

	/**
	 * 상품 판매금지해제<br/>
	 * DD 메인 배포 및 DP FULL 배포 수행
	 * 
	 * @param paramMap
	 * @return
	 */
	int updateStartSaleStat(Map<String, String> paramMap);

	/**
	 * DRM 미적용 상품의 ARM 등록 및 DRM 적용상태를 "적용"으로 update
	 * 
	 * @param cid
	 * @return
	 */
	boolean registArm(String cid);

	/**
	 * 해당 검증 버전의 검증요청일 및 검증완료일을 가져온다.
	 * 
	 * @param sub
	 * @return
	 */
	ContentsVerify getContentsVerifyInfo(ContentsSub sub);

}