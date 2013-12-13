package com.omp.dev.contents.service;

import java.util.List;
import java.util.Properties;

import com.omp.commons.exception.ServiceException;
import com.omp.dev.contents.model.Contents;

/**
 * @author snoopy
 *
 */
public interface ContentManagementService {

	/**
	 * Sales Status Contents List (Recent 7Days, Count : 3)
	 * : 최근 7일 판매상태에 따른 상품 3개 조회
	 * 
	 * @param contents
	 * @return
	 * @throws ServiceException
	 * @throws InfraException
	 */
	public List<Contents> getContentStatusList7Days(Contents contents);

	/**
	 * Verify Status Contents List (Recent 7Days, Count : 3)
	 * : 최근 7일 판매상태에 따른 상품 3개 조회
	 * 
	 * @param contents
	 * @return
	 */
	public List<Contents> getContentVerifyStatusList7Days (Contents contents);
	
	/**
	 * Contents Status List Count 7Days
	 * : 최근 7일 판매상태에 따른 상품 갯수
	 * 
	 * @param contents
	 * @return
	 */
	public String getContentStatusCount7Days(Contents contents);
	
	/**
	 * Contents Verify Status List Count 7Days
	 * : 최근 7일 검증상태에 따른 상품 갯수
	 * 
	 * @param contents
	 * @return
	 */
	public String getContentVerifyStatusCount7Days(Contents contents);
	
	/**
	 * Contents Status List
	 * : 상품 현황 리스트
	 * 
	 * @param contents
	 * @return
	 * @throws ServiceException
	 * @throws InfraException
	 */
	public List<Contents> getContentStatusList(Contents contents);
	
	/**
	 * Contents Status List Count
	 * : 상품 조회갯수
	 * 
	 * @param contents
	 * @return
	 * @throws ServiceException
	 * @throws InfraException
	 */
	public String getContentStatusCount(Contents contents);
	
	/**
	 * Create Content
	 * : 상품 생성
	 * 
	 * @param content
	 * @param prop
	 * @return
	 */
	public Contents setContent(Contents content, Properties prop);
	
	/**
	 * Delete Content
	 * : 상품 삭제
	 * 
	 * @param oldContent
	 */
	public void updateContentDelete (Contents oldContent);
	
	/**
	 * Check Required Value
	 * : 검증 요청 전 필수값 체크
	 * 
	 * @param content
	 * @return
	 */
	public String getRequiredValueCheck(Contents content);
	
	/**
	 * Verify Request
	 * : 검증 요청
	 * 
	 * @param contents
	 * @return
	 */
	public String updateContentVerifyRequest(Contents contents);
	
	/**
	 * Update Content Sale Status
	 * : 판매 상태 변경
	 * 
	 * @param oldContent
	 * @param content
	 * @return
	 */
	public boolean updateChangeSaleStatusContent(Contents oldContent, Contents content);
	
	/**
	 * Update Content Sale Status
	 * : 판매 상태 변경
	 * 
	 * @param contents
	 * @return
	 */
	public int updateChangeSaleStatus(Contents contents);
	
	/**
	 * Get Verification Flag
	 * : 최초검증, 재검증 여부 조회
	 * 
	 * @param oldContents
	 * @return
	 */
	public boolean getVerificationFlag(Contents oldContents);

	/**
	 * Get Verify Request Content Info
	 * : 검증요청한 상품 정보 조회
	 * 
	 * @param cid
	 * @return
	 */
	public Contents getVerifyReqContentInfo(String cid);
	
	/**
	 * Sending Mail When Content Sale Stat Change
	 * : 판매상태 변경에 따른 메일 발송
	 * 
	 * @param oldContent
	 * @param newContent
	 * @param returnUrl
	 * @param toAddr
	 * @return
	 */
	public long sendContentSaleStatMail(Contents oldContent, Contents newContent, String returnUrl, String mbrId, String toAddr);
	
	/**
	 *  Sending Mail When Verify Request
	 *  : 검증요청 시 메일 발송
	 *  
	 * @param content
	 * @param returnUrl
	 * @param toAddr
	 * @return
	 */
	public long sendContentSaleStatMail(Contents content, String returnUrl, String mbrId, String toAddr);
}
