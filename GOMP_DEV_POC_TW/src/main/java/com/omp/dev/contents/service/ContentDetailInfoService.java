package com.omp.dev.contents.service;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.omp.commons.exception.ServiceException;
import com.omp.dev.contents.model.Category;
import com.omp.dev.contents.model.Contents;
import com.omp.dev.contents.model.SaleStatHist;

public interface ContentDetailInfoService {

	/**
	 * Content Detail Info
	 * : 상품 상세 정보 조회
	 * 
	 * @param cid
	 * @return
	 * @throws ServiceException
	 * @throws InfraException
	 */
	public Contents getContentDetailInfo(String cid);
	
	/**
	 * Content Base Info
	 * : 상품 기본 정보 조회
	 * 
	 * @param cid
	 * @return
	 * @throws ServiceException
	 * @throws InfraException
	 */
	public Contents getContentBaseInfo(String cid);
	
	/**
	 * Category List
	 * : 카테고리 조회
	 * 
	 * @return
	 * @throws ServiceException
	 * @throws InfraException
	 */
	public List<Category> getCategoryList();
	
	/**
	 * Content Detail Info Modify
	 * : 상품 상세 정보 수정
	 * 
	 * @param content
	 * @return
	 * @throws ServiceException
	 */
	public String  modifyContentDetail(Contents content, Contents oldContent);
	
	/**
	 * Content Cid mapping Category
	 * : 상품 카테고리 조회
	 * 
	 * @param cid
	 * @return
	 * @throws ServiceException
	 * @throws InfraException
	 */
	public String getContentCategory(String cid);
	
	
	/**
	 * Content Sale Status List
	 * : 상품 판매상태 내역 조회
	 * 
	 * @param cid
	 * @return
	 * @throws ServiceException
	 */
	public List<SaleStatHist> getContentSaleStatList(String cid);
	
	/**
	 * Content Images List
	 * : 상품 이미지 조회
	 * 
	 * @param paramContentImage
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, Object> getListContentImage(String cid, Properties prop);
	
	/**
	 * Content Tag Info List
	 * : 상품 카테고리 조회
	 * 
	 * @param cid
	 * @return
	 */
	public Map<String, Object> getContentTagNameList(String cid);
}

