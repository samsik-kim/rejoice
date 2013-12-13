package com.omp.dev.contents.service;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.communicate.CommunicatorFactory;
import com.omp.commons.communicate.mail.MailSendAgent;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.InvalidInputException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.ContentMailModel;
import com.omp.commons.product.service.ARMManagerService;
import com.omp.commons.product.service.ARMManagerServiceImpl;
import com.omp.commons.product.service.ContentsHistoryService;
import com.omp.commons.product.service.ContentsHistoryServiceImpl;
import com.omp.commons.product.service.DisplayDistributeService;
import com.omp.commons.product.service.DisplayDistributeServiceImpl;
import com.omp.commons.product.service.DownloadDistributeService;
import com.omp.commons.product.service.DownloadDistributeServiceImpl;
import com.omp.commons.service.AbstractService;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.CommonUtil;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.config.ConfigProperties;
import com.omp.dev.common.Constants;
import com.omp.dev.contents.model.ContentSprtPhone;
import com.omp.dev.contents.model.Contents;
import com.omp.dev.contents.model.Provision;

/**
 * @author snoopy
 *
 */
public class ContentManagementServiceImpl 
	extends AbstractService		implements ContentManagementService {

	public ContentManagementServiceImpl() {}
	
	/* Sales Status Contents List (Recent 7Days, Count : 3)
	 * @see com.omp.dev.contents.service.ContentsManagementService#getContentStatusList7Days(com.omp.dev.contents.model.Contents)
	 */
	@SuppressWarnings("unchecked")
	public List<Contents> getContentStatusList7Days (Contents contents) {
		
		List<Contents> resultList = new ArrayList<Contents>();
		
		try {
			resultList = this.commonDAO.queryForList("ContentsStatus.getContentStatusList7Days", contents);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}
		
		return resultList;
	}
	
	/* Verify Status Contents List (Recent 7Days, Count : 3)
	 * @see com.omp.dev.contents.service.ContentManagementService#getContentVerifyStatusList7Days(com.omp.dev.contents.model.Contents)
	 */
	@SuppressWarnings("unchecked")
	public List<Contents> getContentVerifyStatusList7Days (Contents contents)  {
		
		List<Contents> resultList = new ArrayList<Contents>();
		
		try {
			resultList = this.commonDAO.queryForList("ContentsStatus.getContentVerifyStatusList7Days", contents);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}
		
		return resultList;
	}

	/* Contents Status List
	 * @see com.omp.dev.contents.service.ContentsManagementService#getContentStatusList(com.omp.dev.contents.model.Contents)
	 */
	@SuppressWarnings("unchecked")
	public List<Contents> getContentStatusList(Contents contents) {
		
		List<Contents> resultList = new ArrayList<Contents>();
		
		try {
			resultList = this.commonDAO.queryForPageList("ContentsStatus.getContentStatusList", contents);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}
		
		return resultList;
	}

	/* Contents Status List Count
	 * @see com.omp.dev.contents.service.ContentsManagementService#getContentStatusCount(com.omp.dev.contents.model.Contents)
	 */
	public String getContentStatusCount(Contents contents) {
		
		String listCount;
		
		try {
			listCount = (String) this.commonDAO.queryForObject("ContentsStatus.getContentStatusCount", contents);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}
		
		return listCount;
	}
	
	/* Contents Status List Count 7Days
	 * @see com.omp.dev.contents.service.ContentsManagementService#getContentStatusCount(com.omp.dev.contents.model.Contents)
	 */
	public String getContentStatusCount7Days(Contents contents) {
		
		String listCount;
		
		try {
			this.setStep("GetContentStatusCount7Days");
			listCount = (String) this.commonDAO.queryForObject("ContentsStatus.getContentStatusCount7Days", contents);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}
		
		return listCount;
	}
	
	/* Contents Verify Status List Count 7Days
	 * @see com.omp.dev.contents.service.ContentsManagementService#getContentStatusCount(com.omp.dev.contents.model.Contents)
	 */
	public String getContentVerifyStatusCount7Days(Contents contents) {
		
		String listCount;
		
		try {
			this.setStep("GetContentVerifyStatusCount7Days");
			listCount = (String) this.commonDAO.queryForObject("ContentsStatus.getContentVerifyStatusCount7Days", contents);
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}
		
		return listCount;
	}

	/* Create Content
	 * @see com.omp.dev.contents.service.ContentManagementService#setContent(com.omp.dev.contents.model.Contents, java.util.Properties)
	 */
	public Contents setContent(Contents content, Properties prop){
		
		this.daoManager.startTransaction();
		
		try{
			
			int uuid = (Integer)this.commonDAO.queryForObject("ContentsStatus.getContentSeqVal");	// Create new Content Identity Sequence

			// Create Content Cid (0000000000 + Sequence) 
			Integer cid 	= Integer.valueOf(uuid);
			String strCid 	= "0000000000";
			int cidLen 		= cid.toString().length();
			strCid 			= strCid.substring(0, strCid.length() - cidLen) + cid.toString();

			String mbrGrdCd = (String) this.commonDAO.queryForObject("ContentDetailInfo.getSignOption", content.getDevUserId());
			
			String adjRate = prop.getProperty("omp.common.product.default.settlement.rate", "65");
			String adjRateSkt = String.valueOf(100 - Integer.parseInt(prop.getProperty("omp.common.product.default.settlement.rate", "65")));
			
			content.setCid(strCid);																		// Content Identity Setting
			content.setAid(getAppId(uuid, content.getVmType()));										// Content Application Identity Setting
			content.setPid(content.getCid());															// Product Identity Setting
			//content.setProdGbn(Constants.PRODUCT);														// Content Divide Setting
			content.setPaidYn(Constants.NO);															// Content Paid - N  Setting
			content.setContsType(Constants.CONTS_TYPE_NATIVE);											// Contents Type
			content.setVerifyPrgrYn(Constants.CODE_VERIFY_INIT);										// Verify Status   Setting
			content.setSaleStat(Constants.CONTENT_SALE_STAT_NA);										// Sale Status Setting
			content.setDelYn(Constants.NO);																// Content Delete Flag - N Setting 
			//content.setVerifyReqVer(0);																// Verify Req Sequence  - 0 Setting
			content.setAgrmntStat(Constants.AGREEMENT_STATUS_INIT);										// Agrement Status Setting								
			content.setAdjRate(adjRate);																// Adj Rate Developer - 65  Setting 
			content.setAdjRateSkt(adjRateSkt);															// Adj Rate Whoopy - 35 Setting 
			content.setProdPrc(prop.getProperty("omp.product.prodPrc", "0"));							// Content Price - 0 Setting 
			content.setSignOption(mbrGrdCd);
			content.setDrmYn("N");																		// Drm YN
			content.setUpdApplyDivisionCd(Constants.CONTENT_UPGRADE_STAT_NA);							// Contents UPGRADE Stat
			
			setContentBaseInfoValue(content);															// Content Base Info Setting

			this.commonDAO.insert("ContentsStatus.insertContent", content);

			daoManager.commitTransaction();
			
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.");
		} finally {
			daoManager.endTransaction();
		}
		
		return content;
	}

	/* Delete Content
	 * @see com.omp.dev.contents.service.ContentManagementService#ajaxContentDel(com.omp.dev.contents.model.Contents)
	 */
	public void updateContentDelete (Contents oldContent) {
		
		Contents content = new Contents();
		
		try {
			
			content.setCid(oldContent.getCid());							// Set Content Identity 
			content.setDelYn(Constants.YES);								// Set Contents Delete Flag - Y(삭여 여부를 Y로 Modify 한다.)
			content.setUpdBy(oldContent.getUpdBy());						// Set Update User
			
			this.commonDAO.update("ContentDetailInfo.updateContent", content);
	
		} catch(Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}		
	}
	
	/* Check Required Value
	 * @see com.omp.dev.contents.service.ContentManagementService#getRequiredValueCheck(com.omp.dev.contents.model.Contents)
	 */
	public String getRequiredValueCheck(Contents content) {
		
		String resultMessage = "SUCCESS";
		
		this.setStep("GetContentTagInfo");
		ContentDetailInfoService detailService 	= new ContentDetailInfoServiceImpl();
		Map<String, Object>	contentTagMap 		=  detailService.getContentTagNameList(content.getCid());
		
		// 1. 상세정보 체크
		// 상품명, 판매자 노출명, 상품가격, 분류, 이용등급, 태그, 요약설명, 상세설명, 대표아이콘, 미리보기이미지
		this.setStep("CheckContentProdNm");
		if(content.getProdNm() == null || "".equals(content.getProdNm())) {
			return "ErrorProdNm";
		}
		
		this.setStep("CheckExposureDevNm");
		if (content.getExposureDevNm()  == null || "".equals(content.getExposureDevNm())) {
			return "ErrorExposureDevNm";
		}
		
		this.setStep("CheckProdPrc");
		if (content.getProdPrc()  == null || "".equals(content.getProdPrc())) {
			return "ErrorProdPrc";
		}
		
		this.setStep("CheckCtgrCd");
		if (content.getCtgrCd() == null || "".equals(content.getCtgrCd())) {
			return "ErrorProdCtgr";
		}
		
		this.setStep("CheckGameDelibGrd");
		if (content.getGameDelibGrd()  == null || "".equals(content.getGameDelibGrd())) {
			return "ErrorGameDelibGrd";
		}

		this.setStep("CheckTagNm");
		if (contentTagMap  == null || contentTagMap.size() <= 0 ) {
			return "ErrorTagNm";
		}
		
		this.setStep("CheckProdDescSmmr");
		if (content.getProdDescSmmr()  == null || "".equals(content.getProdDescSmmr())) {
			return "ErrorProdDescSmmr";
		}
		
		this.setStep("CheckProdDescDtl");
		if (content.getProdDescDtl()  == null || "".equals(content.getProdDescDtl())) {
			return "ErrorProdDescDtl";
		}
		
		this.setStep("CheckIconImg1");
		if (content.getIconImg1()  == null || "".equals(content.getIconImg1().getFileNm())) {
			return "ErrorIconImg1";
		}
		
		this.setStep("CheckPreviewImg1");
		if (content.getPreviewImg1()  == null || "".equals(content.getPreviewImg1().getFileNm())) {
			return "ErrorPreviewImg1";
		}
		
		this.setStep("CheckPreviewImg2");
		if (content.getPreviewImg2()  == null || "".equals(content.getPreviewImg2().getFileNm())) {
			return "ErrorPreviewImg2";
		}
		
		this.setStep("CheckPreviewImg3");
		if (content.getPreviewImg3()  == null || "".equals(content.getPreviewImg3().getFileNm())) {
			return "ErrorPreviewImg3";
		}
		
		this.setStep("CheckPreviewImg4");
		if (content.getPreviewImg4()  == null || "".equals(content.getPreviewImg4().getFileNm())) {
			return "ErrorPreviewImg4";
		}
		
		// 2. 개발정보 체크
		// 바이너리 유무
		this.setStep("CheckSubContents");
		if(!Constants.YES.equals(content.getVerifyAvailable())) {
			return "NoneSubContents";
		}

		return resultMessage;
	}
	
	/* Verify Request
	 * @see com.omp.dev.contents.service.ContentManagementService#updateContentVerifyRequest(com.omp.dev.contents.model.Contents)
	 */
	public String updateContentVerifyRequest(Contents contents) {
		
		int result = 0;
		
		this.setStep("StartTransaction");
		this.daoManager.startTransaction();
		
		try {
			
			// 검증 요청 Comment , Comment CD
			if (contents.getVerifyCommentCd() == null || "".equals(contents.getVerifyCommentCd())) {
				contents.setVerifyCommentCd(Constants.VERIFY_COMMENT_CD_INIT);
			} else {
				StringBuffer verifyCommentCd = new StringBuffer();
				String arrVerifyCommentCd[] = contents.getVerifyCommentCd().split(",");
				
				if(arrVerifyCommentCd.length > 0) {
					
					for (int i = 0; i < arrVerifyCommentCd.length; i++) {
						
						verifyCommentCd.append(arrVerifyCommentCd[i].trim());
					
						if(i != arrVerifyCommentCd.length - 1)  {
							verifyCommentCd.append(";");
						}
					}
				}
				contents.setVerifyCommentCd(verifyCommentCd.toString());
			}

			// 검증대기 상태
			contents.setVerifyPrgrYn(Constants.CODE_VERIFY_REQ);
			
			// Comment TBL_PD_CONTS Update
			this.setStep("UpdateContentVerifyRequest");
			result = this.commonDAO.update("ContentsStatus.updateContentVerifyRequest", contents);
			
			this.setStep("GetContentBaseInfo");
			Contents newContents = (Contents) this.commonDAO.queryForObject("ContentDetailInfo.getContentBaseInfo", contents.getCid());
			
			// 검증요청 Insert (TBL_CT_CONTS/TBL_CT_SUB_CONTS/TBL_CT_PROVISION_ITEM/TBL_CT_CTG/TBL_CT_SPRT_PHONE)
			processVerifyRequest(newContents);
			
			this.setStep("CommitTransaction");
			this.daoManager.commitTransaction();
			
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} finally {
            this.setStep("EndTransaction");
			this.daoManager.endTransaction();
		}
	
		return String.valueOf(result);
	}
	
	/**
	 * 검증 요청
	 * 
	 * @param contents
	 */
	@SuppressWarnings("unchecked")
	private void processVerifyRequest (Contents contents)  {
	
		List<String> 			scidList 		= new ArrayList<String>();
		List<ContentSprtPhone> 	sprtPhoneList 	= new ArrayList<ContentSprtPhone>();
		List<Provision> 		provisionItem 	= new ArrayList<Provision>();
		Map<String, Object> 	paramMap 		= new HashMap<String, Object>();
	
		try {
		
			//서브 컨텐츠 조회
			this.setStep("GetSubContentInfoByCid");
			scidList = this.commonDAO.queryForList("ContentsStatus.getContentScidListByCid", contents.getCid());

			//TBL_CT_CONTS
			this.setStep("InsertTBL_CT_CONTS");
			this.commonDAO.insert("ContentsStatus.insertCtContents" , contents.getCid());
			
			for (int i = 0; i < scidList.size(); i++) {
				
				paramMap.put("verifyReqVer", contents.getVerifyReqVer());
				paramMap.put("agrmntStat", contents.getAgrmntStat());
				paramMap.put("cid", contents.getCid());
				paramMap.put("scid", (String)(scidList.get(i)));
				
				//TBL_CT_SUB_CONTS
				this.setStep("InsertTBL_CT_SUB_CONTS");
				this.commonDAO.insert("ContentsStatus.insertCtSubContents" , paramMap);
				
				// 프로비전 Item 리스트 조회
				this.setStep("GetProvisionItemList");
				provisionItem = this.commonDAO.queryForList("ContentsStatus.getContentProvisionItemListByScid", paramMap.get("scid"));
				
				for (int j = 0; j < provisionItem.size(); j++) {

					paramMap.put("itemSeq", provisionItem.get(j).getItemSeq());
					
					//TBL_CT_PROVISION_ITEM
					this.setStep("InsertTBL_CT_PROVISION_ITEM");
					this.commonDAO.insert("ContentsStatus.insertCtProvisionItem" , paramMap);
				}
				
				// 대상 단말 리스트 조회
				this.setStep("GetSprtPhoneList");
				sprtPhoneList = this.commonDAO.queryForList("ContentsStatus.getContentSprtPhoneListByScid", paramMap.get("scid"));
				
				for (int j = 0; j < sprtPhoneList.size(); j++) {
				
					paramMap.put("sprtPhoneSeq", sprtPhoneList.get(j).getSprtPhoneSeq());
					
					//TBL_CT_SPRT_PHONE
					this.setStep("InsertTBL_CT_SPRT_PHONE");
					this.commonDAO.insert("ContentsStatus.insertCtSprtPhone" , paramMap);
				}
			}
			
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다." , e);	
		} 
		
	}
	
	/* 판매 상태 변경
	 * @see com.omp.dev.contents.service.ContentManagementService#updateChangeSaleStatusContent(com.omp.dev.contents.model.Contents, com.omp.dev.contents.model.Contents)
	 */
	public boolean updateChangeSaleStatusContent(Contents oldContent, Contents content) {
		
		// 최초검증인지 재검증인지 값 필요...
		//boolean isNotFirstVerificationFlag = false;		// false : 최초검증 , true :  재검증

		boolean result = false;
		int updateResult = 0;
		ARMManagerService service = new ARMManagerServiceImpl();
		
		this.daoManager.startTransaction(); 
		
		try {

			//isNotFirstVerificationFlag = getVerificationFlag(oldContent);
			
			if (Constants.CONTENT_SALE_STAT_WAIT.equals(oldContent.getSaleStat())) { 
				
				log.debug(" =========== First Content Sale Start  ===========");
				// * 판매대기 -> 판매중 (최초검증)
				
				// 1. PD 판매상태 변경
				content.setSaleStat(Constants.CONTENT_SALE_STAT_ING);
				content.setSaleStartDt(DateUtil.getGeneralTimeStampString());
				updateResult = updateChangeSaleStatus(content);
				
				// 2. 업데이트 이력 날짜 업데이트
				modifyUpdateHistoryDate(content.getCid());
				
				if (updateResult > 0) {
					
					// ARM 여부 : Y 일 때
					if(Constants.YES.equals(oldContent.getDrmYn())) {
						// 3. ARM Application 등록
						result = service.connectARMReqRegisterApplication(content.getCid());
					}else {
						result = true;
					}
					
					if (result) {
						// 4. DP Full 배포
						DisplayDistributeService displayDistributeservice = new DisplayDistributeServiceImpl();
						result = displayDistributeservice.dpDeployContents(content.getCid(), false);
					} else {
						return false;
					}
					
					log.debug("* First Content Sale Start RESULT : {0}", new Object[] {result}); 
				} else {
					return false;
				}
				
			} else if (Constants.CONTENT_SALE_STAT_ING.equals(oldContent.getSaleStat())) {	
				
				log.debug(" =========== Content Sale Staop  ===========");
				// * 판매중 -> 판매중지
				
				// 1. PD 판매상태 변경
				content.setSaleStat(Constants.CONTENT_SALE_STAT_STOP);
				updateResult = updateChangeSaleStatus(content);
					
				if (updateResult > 0) {
					
					// 3. ARM Application 상태 변경
					result = service.connectARMReqModifyApplicationStatus(content.getCid());
					
					if (result) {
						// 4. DP 판매상태, 전시여부 변경
						DisplayDistributeService displayDistributeservice = new DisplayDistributeServiceImpl();
						result = displayDistributeservice.stopSaleContents(content.getCid());
					}
				}
				
			} else if (Constants.CONTENT_SALE_STAT_RESTRIC.equals(oldContent.getSaleStat())) {
				
				log.debug(" ===========Content Sale UNREGIST Request  ===========");
				
				// * 판매금지 -> 해지요청
				
				// 1. PD 판매상태 변경
				content.setSaleStat(Constants.CONTENT_SALE_STAT_UNREGIST);
				content.setUpdDttm(oldContent.getDevUserId());
				updateChangeSaleStatus(content);
				
				result = true;
				
			} else if (Constants.CONTENT_SALE_STAT_STOP.equals(oldContent.getSaleStat())) {
				
				log.debug(" ===========Content Sale Start  ===========");
				// * 판매중지 -> 판매 중
				
				// 1. PD 판매상태 변경
				content.setSaleStat(Constants.CONTENT_SALE_STAT_ING);
				updateResult =  updateChangeSaleStatus(content);
				
				if (updateResult > 0) {
					
					// 4. ARM( DRM여부가 Y)
					result = service.connectARMReqModifyApplicationStatus(content.getCid());
					
					if (result) {
						
						//5. DD Main 배포
						DownloadDistributeService downloadDistributeservice = new DownloadDistributeServiceImpl();
						result = downloadDistributeservice.ddDeployContents(content.getCid(), true);
						
						if(result) { 
							//6. DP Full 배포
							DisplayDistributeService displayDistributeservice = new DisplayDistributeServiceImpl();
							result = displayDistributeservice.dpDeployContents(content.getCid(), false);
						}
						
					} else {
						return false;
					}
				}
			}
			
			if(result) {
				this.daoManager.commitTransaction();
			}
		
			return result;
			
		} catch (Exception e) {
			result = false;
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} finally {
			this.daoManager.endTransaction();
		}
	
	}
	

	/* 특정회원의 판매중인 상품 갯수 조회
	 * @see com.omp.dev.contents.service.ContentManagementService#getCountSaleContentByMbrNo(java.lang.String)
	 */
	public int getCountSaleContentByMbrNo(String mbrNo) {
		
		int saleContentCount = 0;
		
		if(log.isDebugEnabled()) log.debug(" =========== Sale Content Counting START =========== ");
		
		try {
			
			if(log.isDebugEnabled()) log.debug("* Secession member 		: {0}", new Object[] {mbrNo});
						
			if (mbrNo != null && !"".equals(mbrNo)) {
			
				// 특정회원 mbr_no에 등록된 판매중 상품 갯수 조회
				Contents paramContent = new Contents();
				paramContent.setDevUserId(mbrNo);
				paramContent.setSaleStat(Constants.CONTENT_SALE_STAT_ING);
				
				saleContentCount = (Integer) this.commonDAO.queryForObject("ContentStatus.getSaleContentCountByMbrNo", paramContent);

			} else {
				throw new ServiceException("No Member Info[mbr no] Data!");
			}
			
		} catch (Exception e) {
			throw new ServiceException("Sale product counting is Fail!", e);
		}
		
		if(log.isDebugEnabled()) log.debug("* Content Count 		: {0}", new Object[] {saleContentCount});
		if(log.isDebugEnabled()) log.debug(" =========== Sale Content Counting END  =========== ");
		
		return saleContentCount;
	}
	

	/* 특정회원에 대해 그 회원의 모든 상품을 판매 중지. - 회원 탈퇴시 호출됨 -> getCountSaleContentByMbrNo(String mbrNo)로 대체
	 * @see com.omp.dev.contents.service.ContentManagementService#updateChangesaleStatusByMbrNo(java.lang.String)
	 */
	@Deprecated
	@SuppressWarnings("unchecked")
	public boolean updateChangesaleStatusByMbrNo(String mbrNo) {
		
		boolean result = false;
		
		int listSize = 0,  updateResult = 0;
		ARMManagerService 			armService 					= new ARMManagerServiceImpl();
		List<Contents> 				oldContentList 				= new ArrayList<Contents>();
		ContentsHistoryService		contentsHistoryService		= new ContentsHistoryServiceImpl();
		DisplayDistributeService 	displayDistributeservice 	= new DisplayDistributeServiceImpl();
		
		if(log.isDebugEnabled()) log.debug(" =========== Member Secession - [Content Sale Staop] START =========== ");
		
		try {
			
			if (mbrNo != null && "".equals(mbrNo)) {
				
				// 특정회원 mbr_no에 등록된 판매중 상품 조회
				Contents paramContent = new Contents();
				paramContent.setDevUserId(mbrNo);
				paramContent.setSaleStat(Constants.CONTENT_SALE_STAT_ING);
				
				oldContentList = this.commonDAO.queryForList("ContentStatus.getSaleContentInfoByMbrNo", paramContent);
				listSize = oldContentList == null ? 0: oldContentList.size();
	
				if(log.isDebugEnabled()) {
					log.debug("* Secession member 		: {0}", new Object[] {mbrNo});
					log.debug("* Salling Content Count  : {0}", new Object[] {listSize});
				}
				
				// 해당 회원의 판매중인 상품이 존재할 때
				if(listSize > 0) {
					
					Contents content = new Contents();
					
					for(int i = 0 ; i < listSize; i++) {
							
						// 1. PD 판매상태 변경 : 판매중인 상품 -> 판매중지로 변경
						content.setCid(oldContentList.get(i).getCid());
						content.setSaleStat(Constants.CONTENT_SALE_STAT_STOP);
						updateResult = updateChangeSaleStatus(content);
	
						if(updateResult > 0) {
							
							// 2. DP 판매상태, 전시여부 변경
							result = displayDistributeservice.stopSaleContents(content.getCid());
							if (!result) return false;
							
							if (Constants.YES.equals(oldContentList.get(i).getDrmYn())) {
								
								// 3. ARM Application 상태 변경
								result = armService.connectARMReqRegisterApplication(content.getCid());
								if (!result) return false;
							}
							
						} else {
							log.error("Change Contents Sale Stat Fail!");
							return false;
						}
			
					}
	
				} // 해당 회원의 판매중인 상품이 존재하지 않을 때 return true
				else { 
					result = true;
				}
				
			} else {
				result = false;
				log.error("Member No is null! Check Member Info.");
			}
			
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}

		if(log.isDebugEnabled()) if(result) log.debug("* Change Contents Sale Stat Success.");
	
		// 4. PD 판매상태 변경이 성공하였을 때, history에 insert
		if(updateResult > 0 && result) {
			
			if(log.isDebugEnabled()) log.debug(" * Insert Contents History Table START ");
			
			for(int i = 0 ; i < listSize; i++) {
				// History Table Insert
				boolean isContsHis = contentsHistoryService.insertContsHis(oldContentList.get(i).getCid());
				if(log.isDebugEnabled()) if(!isContsHis)log.debug("Contents History Table Insert Fail!");
	
				// PD Sale Stat history
				boolean iscontSaleHis = contentsHistoryService.insertSaleStatHis(oldContentList.get(i).getCid(), Constants.CONTENT_SALE_STAT_STOP , mbrNo, false);	
				if(log.isDebugEnabled()) if(!iscontSaleHis)log.debug("Contents Sale Stat History Table Insert Fail");
			}
			
			if(log.isDebugEnabled()) log.debug(" * Insert Contents History Table END ");
			
		}
	
		if(log.isDebugEnabled()) log.debug(" =========== Member Secession - [Content Sale Staop] END  =========== ");
		
		return result;
	}
	
	/* 상품 상태 변경
	 * @see com.omp.dev.contents.service.ContentManagementService#updateChangeSaleStatus(com.omp.dev.contents.model.Contents)
	 */
	public int updateChangeSaleStatus(Contents contents) {
		
		int result = 0;
		
		try{
			result = this.commonDAO.update("ContentsStatus.updateChangeSaleStatus", contents);
		} catch(Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} 
		
		return result;
	}
	
	/* 최초검증, 재검증 여부(true : 최초검증, false : 재검증)
	 * @see com.omp.dev.contents.service.ContentManagementService#getVerificationFlag(com.omp.dev.contents.model.Contents)
	 */
	public boolean getVerificationFlag(Contents oldContents) {
		
		boolean resultFlag = false;
		
		try {
			
			String result = (String) this.commonDAO.queryForObject("ContentStatus.getVerificationFlag", oldContents);
			
			if (result == null) resultFlag = false;
			else resultFlag = true;
			
			return resultFlag;
			
		} catch(Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}
	
	}
	
	/* 검증요청한 상품 정보 조회
	 * @see com.omp.dev.contents.service.ContentManagementService#getVerifyReqContentInfo(java.lang.String)
	 */
	public Contents getVerifyReqContentInfo(String cid) {

		try{
			
			this.setStep("GetVerifyReqContentInfo");
			Contents content = (Contents) this.commonDAO.queryForObject("ContentStatus.getVerifyReqContentInfo", cid);
			
			return content;
			
		} catch(Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}
	}
	
	/**
	 * 업데이트 이력 날짜 업데이트
	 * 
	 * @param cid
	 * @return
	 */
	private int modifyUpdateHistoryDate(String cid) {
		
		int result = 0;
		
		try {
			result = this.commonDAO.update("ContentStatus.modifyUpdateHistoryDate", cid);
		} catch (SQLException e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}
		
		return result;
	}

	/**
	 * Make Content Application ID
	 * : OA00000001
	 * 
	 * @param uuid
	 * @param vmType
	 * @return
	 */
	private String getAppId(int uuid, String vmType) {
		
		StringBuffer strBuff = new StringBuffer();
		ConfigProperties conf = new ConfigProperties();

		// 플랫폼 추가시 추가되어야할 부분. (AID 정책)
		if(vmType!= null) {
			if(vmType.equals(Constants.CONTENT_PLATFORM_ANDROID)) {						// Taiwan AID Create
				strBuff.append(conf.getString("omp.dev.product.taiwan.aid.android", "TA"));			
			}
		}
		
		Integer cid 	= new Integer(uuid);
		String strCid 	= strBuff.toString()  + "00000000";
		
		int cidLen = cid.toString().length();
		strCid = strCid.substring(0, strCid.length() - cidLen) + cid.toString(); // AID 10Length :  vm Type + 00000000 + new Sequence
		
		return strCid;
	}
	
	/**
	 * Content Base Info Setting
	 * 
	 * @param content
	 * @throws ServiceException
	 */	
	private void setContentBaseInfoValue(Contents content) {
		
		try {
			
			// for Security handling ( <,>,",',&,%,!,-- remove )
			content.setProdNm(CommonUtil.removeSpecial(content.getProdNm()));
			
			// Content Name remove Space character 
			int prodNmLastIdx = 0;
			
			for (int i = 0; i < content.getProdNm().length(); i++) {
				if (content.getProdNm().charAt(i) != ' ') {
					prodNmLastIdx = i;
					break;
				}
			}
			
			content.setProdNm(content.getProdNm().substring(prodNmLastIdx,content.getProdNm().length()));
			
			// Content Name Byte Check and Setting
			//content.setProdNm(cutByteStringValue(content.getProdNm(), 30));

			// Prod Price
			if (Integer.parseInt(content.getProdPrc()) > 0) content.setPaidYn(Constants.YES);
			else content.setPaidYn(Constants.NO);
			
		} catch(Exception e) {
			throw new InvalidInputException("Does not Content Security Info Setting", e);
		}
	}
	
	/* 판매상태 변경에 따른 메일 발송
	 * @see com.omp.dev.contents.service.ContentManagementService#sendContentSaleStatMail(com.omp.dev.contents.model.Contents, com.omp.dev.contents.model.Contents, java.lang.String, java.lang.String)
	 */
	public long sendContentSaleStatMail(Contents oldContent, Contents newContent, String returnUrl, String mbrId, String toAddr) {

		//Mail Model 메일 모델
		ContentMailModel 	contentMailModel = new ContentMailModel();;
		SendMailModel 		mail;
		MailSendAgent 		msa;
		long				result = 0;
	
		daoManager.startTransaction();
		
		try {
			ConfigProperties	conf	= new ConfigProperties();
			
			//1. Content Mail Model Setting
			contentMailModel.setProdNm(newContent.getProdNm());
			contentMailModel.setDevUserId(mbrId);
			contentMailModel.setMainUrl(returnUrl);
	
			//판매대기 -> 판매중(최초판매개시)
			if (Constants.CONTENT_SALE_STAT_WAIT.equals(oldContent.getSaleStat())) { 	
				
				String saleStartDt = newContent.getSaleStartDt().substring(0, 8);
				contentMailModel.setSaleStartDt(DateUtil.fmtDate(saleStartDt, "yyyyMMdd", "yyyy-MM-dd"));
				String mailSubject = LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님의 신규등록 상품이 최초 판매되었습니다.");
				
				//2. SendMailModel Setting
				mail = new SendMailModel();
				mail.setTemplateId("/DEV/PD_003.html");
				mail.setRefAppId("ContentAction.ajaxChangeSaleStatus.FirstSaleStart");
				mail.setSubject(mailSubject);
				mail.setRefDataId(newContent.getCid());
				mail.setToAddr(toAddr);
				//mail.setToName(mbrId);
				mail.setContentData(contentMailModel);
				mail.setFromAddr(conf.getString("omp.dev.email.default.from"));
				mail.setFromName(conf.getString("omp.dev.email.default.fromName"));
				mail.setReturnAddr(conf.getString("omp.dev.email.default.return"));
				msa = CommunicatorFactory.getMailSendAgent();
	
				//3. Send Mail
				result = msa.requestMailSend(mail);
				
				if(log.isDebugEnabled()) {
					log.debug("*********** Send Email ***********");
					log.debug("* 메일 제목 : {0}", new Object[] {mail.getSubject()});
					log.debug("* 발송일 : {0}", new Object[] {contentMailModel.getSendDt()});
					log.debug("* 상품명 : {0}", new Object[] {contentMailModel.getProdNm()});
					log.debug("* 회원ID : {0}", new Object[] {contentMailModel.getDevUserId()});
					log.debug("* 최초판매개시일 : {0}", new Object[] {contentMailModel.getSaleStartDt()});
					log.debug("* 업무명 : {0}", new Object[] {mail.getRefAppId()});
					log.debug("* 메일 발송 결과 : {0}", new Object[] {result});
					log.debug("*********** Send Email ***********");
				}
				
			}
			//판매중 -> 판매중지 || 판매중지 -> 판매중
			else if (Constants.CONTENT_SALE_STAT_ING.equals(oldContent.getSaleStat()) 
					|| Constants.CONTENT_SALE_STAT_STOP.equals(oldContent.getSaleStat())) {	
				
				String updDttm = newContent.getUpdDttm().substring(0, 8);
				contentMailModel.setUpdDttm(DateUtil.fmtDate(updDttm, "yyyyMMdd", "yyyy-MM-dd"));
				contentMailModel.setOldSaleStat(CacheCommCode.getCommCodeByFullCode(oldContent.getSaleStat()).getCdNm());
				contentMailModel.setNewSaleStat(CacheCommCode.getCommCodeByFullCode(newContent.getSaleStat()).getCdNm());
				String mailSubject = LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님의 상품상태 변경 안내입니다.");
				
				//2. SendMailModel Setting
				mail = new SendMailModel();
				mail.setTemplateId("/DEV/PD_004.html");
				mail.setRefAppId("ContentAction.ajaxChangeSaleStatus.ChnageSaleStat");
				mail.setSubject(mailSubject);
				mail.setRefDataId(newContent.getCid());
				mail.setToAddr(toAddr);
				//mail.setToName(mbrId);
				mail.setContentData(contentMailModel);
				mail.setFromAddr(conf.getString("omp.dev.email.default.from"));
				mail.setFromName(conf.getString("omp.dev.email.default.fromName"));
				mail.setReturnAddr(conf.getString("omp.dev.email.default.return"));
				msa = CommunicatorFactory.getMailSendAgent();
	
				//3. Send Mail
				result = msa.requestMailSend(mail);
				
				if(log.isDebugEnabled()) {
					log.debug("*********** Send Email ***********");
					log.debug("* 메일 제목 : {0}", new Object[] {mail.getSubject()});
					log.debug("* 발송일 : {0}", new Object[] {contentMailModel.getSendDt()});
					log.debug("* 상품명 : {0}", new Object[] {contentMailModel.getProdNm()});
					log.debug("* 회원ID : {0}", new Object[] {contentMailModel.getDevUserId()});
					log.debug("* 변경일 : {0}", new Object[] {contentMailModel.getUpdDttm()});
					log.debug("* 변경전상태 : {0}", new Object[] {contentMailModel.getOldSaleStat()});
					log.debug("* 변경후상태 : {0}", new Object[] {contentMailModel.getNewSaleStat()});
					log.debug("* 업무명 : {0}", new Object[] {mail.getRefAppId()});
					log.debug("* 메일 발송 결과 : {0}", new Object[] {result});
					log.debug("*********** Send Email ***********");
				}
				
			} 
			
			daoManager.commitTransaction();
			
		} catch(Exception e) {
			log.error("Verify Request Send Mail Fail ", e);
		} finally {
			daoManager.endTransaction();
		}
		
		return result;
	}
	
	/* 검증요청 시 메일 발송
	 * @see com.omp.dev.contents.service.ContentManagementService#sendContentSaleStatMail(com.omp.dev.contents.model.Contents, java.lang.String, java.lang.String)
	 */
	public long sendContentSaleStatMail(Contents content, String returnUrl, String mbrId, String toAddr) {

		//Mail Model 메일 모델
		ContentMailModel 	contentMailModel = new ContentMailModel();;
		SendMailModel 		mail;
		MailSendAgent 		msa;
		long				result = 0;

		this.setStep("StartTransaction");
		daoManager.startTransaction();
		
		try {
			ConfigProperties	conf	= new ConfigProperties();
			
			//1. Content Mail Model Setting
			this.setStep("SettingMailValue");
			contentMailModel.setProdNm(content.getProdNm());
			contentMailModel.setDevUserId(mbrId);
			contentMailModel.setMainUrl(returnUrl);
			
			String ctReqDt = content.getInsDttm().substring(0, 8);
			contentMailModel.setCtReqDt(ctReqDt);
			String mailSubject = LocalizeMessage.getLocalizedMessage("[Whoopy 개발자센터] 회원님의 상품 검증 요청이 접수되었습니다.");
			
			//2. SendMailModel Setting
			this.setStep("SettingMailTemplate");
			mail = new SendMailModel();
			mail.setTemplateId("/DEV/PD_002.html");
			mail.setRefAppId("ContentAction.ajaxVerifyReq");
			mail.setSubject(mailSubject);
			mail.setRefDataId(content.getCid());
			mail.setToAddr(toAddr);
			//mail.setToName(mbrId);
			mail.setContentData(contentMailModel);
			mail.setFromAddr(conf.getString("omp.dev.email.default.from"));
			mail.setFromName(conf.getString("omp.dev.email.default.fromName"));
			mail.setReturnAddr(conf.getString("omp.dev.email.default.return"));
			msa = CommunicatorFactory.getMailSendAgent();
	
			//3. Send Mail
			this.setStep("SendMailContentVerifyReqResult");
			result = msa.requestMailSend(mail);
			
			if(log.isDebugEnabled()) {
				log.debug("*********** Send Email ***********");
				log.debug("* 메일 제목 : {0}", new Object[] {mail.getSubject()});
				log.debug("* 발송일 : {0}", new Object[] { contentMailModel.getSendDt()});
				log.debug("* 상품명 : {0}", new Object[] {contentMailModel.getProdNm()});
				log.debug("* 회원ID : {0}", new Object[] {contentMailModel.getDevUserId()});
				log.debug("* 검증요청일 : {0}", new Object[] {contentMailModel.getCtReqDt()});
				log.debug("* 업무명 : {0}", new Object[] {mail.getRefAppId()});
				log.debug("* 메일 발송 결과 : {0}", new Object[] {result});
				log.debug("*********** Send Email ***********");
			}
			
			this.setStep("CommitTransaction");
			daoManager.commitTransaction();
			
		} catch(Exception e) {
			log.error("Verify Request Send Mail Fail ", e);
		} finally {
			this.setStep("EndTransaction");
			daoManager.endTransaction();
		}
		
		return result;
	}
	
	@SuppressWarnings("unused")
	@Deprecated
	private String cutByteStringValue(String value, int limitByte) {
		try {
			byte[] tempProdNm = value.getBytes("UTF-8");
			
			//  UTF-8 String Value Byte check
			if (tempProdNm.length > limitByte) {
				String cutProdNm = "";
				cutProdNm = cutStr(value, limitByte, '-');
				return cutProdNm;	
			}else { 
				return value;	
			}
		} catch (Exception e) {
			throw new InvalidInputException("Does not Content Security Info Setting ", e);
		}
	}
	
	/** 
	* Cutting String
	* 
	* @param str Original String 
	* @param int Cut Byte length 
	* @param char +1 or -1 
	* @return Cutted String 
	* @throws UnsupportedEncodingException 
	*/ 
	@Deprecated
	private String cutStr(String str, int length ,char type) throws UnsupportedEncodingException { 
		byte[] bytes = str.getBytes("UTF-8"); 
		int len = bytes.length; 
		int counter = 0; 
		
		if (length >= len) { 
			StringBuffer sb = new StringBuffer(); 
			sb.append(str); 
			
			for(int i=0;i<length-len;i++){ 
				sb.append(' '); 
			} 
			return sb.toString(); 
		} 
		for (int i = length - 1; i >= 0; i--) { 
			if (((int)bytes[i] & 0x80) != 0) counter++; 
		} 
		
		String f_str = null; 
		
		if(type == '+') { 
			f_str = new String(bytes, 0, length + (counter % 3), "UTF-8"); 
		} else if(type == '-') { 
			f_str = new String(bytes, 0, length - (counter % 3), "UTF-8"); 
		} else { 
			f_str = new String(bytes, 0, length - (counter % 3), "UTF-8"); 
		} 
		return f_str; 
	}
	
}
