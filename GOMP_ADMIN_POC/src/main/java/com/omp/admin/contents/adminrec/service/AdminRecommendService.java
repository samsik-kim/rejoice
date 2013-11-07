package com.omp.admin.contents.adminrec.service;

import java.util.List;

import com.omp.admin.contents.adminrec.model.AdminRecommend;
import com.omp.admin.contents.adminrec.model.AdminRecommendParam;
import com.omp.admin.contents.adminrec.model.DpCategoryList;

public interface AdminRecommendService {
	
	List<DpCategoryList> getDpCategoryList(AdminRecommendParam param);
	
	List<AdminRecommend> selectAdminRecommendList(AdminRecommendParam param);
	
	List<AdminRecommend> popAdminProdList(AdminRecommendParam param);
	
	AdminRecommend popAdminProdDetail(String prodId);
	
	void insertAdminRecommendProd(String selectedProdId, AdminRecommendParam param);
	
	void updateAdminRecommendList(AdminRecommendParam param, String[] upProdId, String[] expoYn, String[] expoPrior);
	
	void deleteAdminRecommendList(String selectedProdId, AdminRecommendParam param);

//	/**
//	 * 초기 리스트
//	 */
//	public PageResultList findAdminRecommends(HashMap hm);
//	
//	/**
//	 * e-Book 운영자추천 리스트
//	 */
//	public PageResultList findEbookAdminRecommends(HashMap hm);
//	
//	/**
//	 * Comic 운영자추천 리스트
//	 */
//	public PageResultList findComicAdminRecommends(HashMap hm);
//	
//	/*
//	 * INSERT LIST
//	 */
//	public PageResultList findWriteAdminRecommend(HashMap hm);
//	
//	/**
//	 * e-Book 운영자추천 등록 리스트
//	 */
//	public PageResultList findWriteEbookAdminRecommend(HashMap hm);
//	
//	/**
//	 * Comic 운영자추천 등록 리스트
//	 */
//	public PageResultList findWriteComicAdminRecommend(HashMap hm);
//	
//	public void saveAdminRecommend(HashMap map);
//	
//	public void deleteAdminRecommend(HashMap map);
//	
//	public void updateAdminRecommend(HashMap map);
//	
//	public List epsdList(HashMap hm) ;
//	
//	public AdminRecommend getProdDetailView(HashMap hm);
//	
//	public List getCategoryList(String highCtgr);
//		
//	public List selectDispCtgrList(HashMap hm);
//	
//	public List<AdminRecommend> selectFirstCategoryList(HashMap hm);
//	  
//	/**
//	 * WAP프리존 추천,최신,인기 상품 목록조회
//	 * @since 2010.04.19
//	 * @author Sung-Min Choi
//	 * @param hm
//	 */
//	public PageResultList findFreeZoneList(HashMap hm);
//	
//	/**
//	 * WAP프리존 이력리스트 목록조회
//	 * @since 2010.04.22
//	 * @author Sung-Min Choi
//	 * @param hm
//	 */
//	public PageResultList findFreeZoneHstList(HashMap hm);
//	
//	/**
//	 * WAP프리존 이력리스트 목록조회
//	 * @since 2010.04.22
//	 * @author Sung-Min Choi
//	 * @param hm
//	 */
//	public PageResultList findFreeZoneHstDtlList(HashMap hm);
//	
//	/**
//	 * 프리존노출컨텐츠이력를 등록한다.
//	 * @since 2010.04.21
//	 * @author Sung-Min Choi
//	 * @param map
//	 */
//	public void saveFreeZoneHst(HashMap map);
//	
//	/**
//	 * 프리존노출컨텐츠이력를 등록한다.
//	 * @since 2010.04.21
//	 * @author Sung-Min Choi
//	 * @param map
//	 */
//	public void deleteFreezoneProd(HashMap map);
//	
//	
//	////////////////////////////////////////////////////////////////////////////////////////
//	// WAP 네이트 관리
//	////////////////////////////////////////////////////////////////////////////////////////
//	
//	/**
//	 * WAP네이트 배너이미지, 컨텐츠 상품 목록 조회
//	 * @since 2010.05.03
//	 * @author Sung-Min Choi
//	 * @param hm
//	 */
//	public List findFreezoneListDtl(HashMap hm);
//	
//	/**
//	 * 프리존노출컨텐츠이력를 등록한다.
//	 * @since 2010.04.21
//	 * @author Sung-Min Choi
//	 * @param map
//	 */
//	public void saveFreezoneServ(HashMap map);
//	
//	////////////////////////////////////////////////////////////////////////////////////////
//	// WAP 네이트 관리
//	////////////////////////////////////////////////////////////////////////////////////////
//	
//	/**
//     * 시퀀스 조회
//     * @since 2010.07.12
//     * @author Choi
//     * @param seqNm
//     * @return
//     */
//    public String selectSequence(String seqNm);
//    
//	/**
//	 * 네이트홈배너이미지를 등록한다.
//	 * @since 2010.07.12 
//	 * @author Choi
//	 * @param vo
//	 */
//	public void insertNateHbnImg(AdminRecommend vo);
//	
//	/**
//	 * 네이트홈배너추천상품을 등록한다.
//	 * @since 2010.07.12
//	 * @author Choi
//	 * @param vo
//	 */
//	public void insertNateHbnImgDtl(AdminRecommend vo);
//	
//	/**
//	 * 네이트홈배너 등록처리
//	 * @since 2010.08.12
//	 * @author Sung-Min Choi
//	 * @param hm
//	 */
//	public PageResultList selectNateHbnImgList(HashMap hm);
//	
//	/**
//	 * 네이트홈배너 상세목록
//	 * @since 2010.08.12
//	 * @author Sung-Min Choi
//	 * @param hm
//	 */
//	public List selectNateHbnImgDtlList(HashMap hm);
//	
//	/**
//	 * 네이트홈배너 전송내역
//	 * @since 2010.08.12
//	 * @author Sung-Min Choi
//	 * @param hm
//	 */
//	public AdminRecommend selectNateHbnImgView(HashMap hm);
}