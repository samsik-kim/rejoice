package com.omp.dev.contents.service;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.omp.commons.exception.InvalidInputException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.product.service.DisplayDistributeService;
import com.omp.commons.product.service.DisplayDistributeServiceImpl;
import com.omp.commons.product.service.DownloadDistributeService;
import com.omp.commons.product.service.DownloadDistributeServiceImpl;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.CommonUtil;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.FileUtil;
import com.omp.commons.util.StringUtil;
import com.omp.commons.util.config.ConfigProperties;
import com.omp.dev.common.Constants;
import com.omp.dev.common.util.ImageResizeUtil;
import com.omp.dev.contents.model.Category;
import com.omp.dev.contents.model.ContentImage;
import com.omp.dev.contents.model.ContentTagInfo;
import com.omp.dev.contents.model.Contents;
import com.omp.dev.contents.model.SaleStatHist;
import com.sun.jimi.core.JimiException;

public class ContentDetailInfoServiceImpl 
		extends AbstractService		implements ContentDetailInfoService {
	
	public ContentDetailInfoServiceImpl() {}

	
	
	/* Content Detail Info
	 * @see com.omp.dev.contents.service.ContentDetailInfoService#getContentBaseInfo(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Contents getContentDetailInfo(String cid) {
		
		Contents resultContentInfo 			= null;
		List<ContentImage> contentImages 	= null;
		
		try{
			
			resultContentInfo = getContentBaseInfo(cid);
			contentImages = this.commonDAO.queryForList("ContentDetailInfo.selectContentImage", cid);				// 상품의 등록된 Image를 가져 온다.
			
			if(contentImages != null && contentImages.size() > 0){									
				for(ContentImage contentImage : contentImages){
					
					if (log.isDebugEnabled()) log.debug(contentImage.getFilePos());
					
					// 물리 파일이 존재 하지 않을 경우 Image Setting을 하지 않는다.
					//contentImageFiles = new File(contentImage.getFilePos());
					//if (!contentImageFiles.isFile()) continue;
					if (contentImage.getFilePos() == null || "".equals(contentImage.getFilePos())) continue;
					
					if(Constants.CONTENT_IMAGE_DESC.equals(contentImage.getImgGbn())){
						resultContentInfo.setDescImg(contentImage);										// 상품 설명 Image를 Contents의 Setting 한다.			
					} else if(Constants.CONTENT_IMAGE_PREV1.equals(contentImage.getImgGbn())){
						resultContentInfo.setPreviewImg1(contentImage);									// 상품  대표 Image1를 Contents의 Setting 한다.					
					} else if(Constants.CONTENT_IMAGE_PREV2.equals(contentImage.getImgGbn())){
						resultContentInfo.setPreviewImg2(contentImage);									// 상품 대표 Image2를 Contents의 Setting 한다.					
					} else if(Constants.CONTENT_IMAGE_PREV3.equals(contentImage.getImgGbn())){
						resultContentInfo.setPreviewImg3(contentImage);									// 상품 대표 Image3를 Contents의 Setting 한다.					
					} else if(Constants.CONTENT_IMAGE_PREV4.equals(contentImage.getImgGbn())){
						resultContentInfo.setPreviewImg4(contentImage);									// 상품 대표 Image4를 Contents의 Setting 한다.					
					} else if(Constants.CONTENT_IMAGE_ICON1.equals(contentImage.getImgGbn())){
						resultContentInfo.setIconImg1(contentImage);									// 상품 Icon1를 Contents의 Setting 한다.				
					}
				}
			}
			
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} 
		return resultContentInfo;
	}
	
	/* Content Base Info
	 * @see com.omp.dev.contents.service.ContentDetailInfoService#getContentBaseInfo(java.lang.String)
	 */
	public Contents getContentBaseInfo(String cid) {
		
		Contents resultContentInfo = null;
		
		try{
			resultContentInfo = (Contents) this.commonDAO.queryForObject("ContentDetailInfo.getContentBaseInfo", cid);
			
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} 
		return resultContentInfo;
	}
	
	/* Category List
	 * @see com.omp.dev.contents.service.ContentDetailInfoService#getCategoryList()
	 */
	@SuppressWarnings("unchecked")
	public List<Category> getCategoryList() {
		
		 List<Category> categoryList = null;
		
		try{
			String upDpCatNo = "DP01";		// 임시
			categoryList = this.commonDAO.queryForList("ContentDetailInfo.getCategoryList", upDpCatNo);
			
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} 
		
		return categoryList;
	}
	
	/* modify Content Detail Info
	 * @see com.omp.dev.contents.service.ContentDetailInfoService#modifyContentDetail(com.omp.dev.contents.model.Contents)
	 */
	public String modifyContentDetail(Contents content, Contents oldContent) {
		
		String result = "";
		boolean deployResult = false;

		this.daoManager.startTransaction();
		
		// 확장자 체크
		result = getAttachFileChecked(content);

		if (!"SUCCESS".equals(result)) {
			return result;
		}

		content.setDevUserId(oldContent.getInsBy());
			
		try{

			// CONTENT INFO MODIFY
			setContentBaseInfoValue(content);

			// IMAGES MODIFY & SAVE
			modifyContentImages(content);
			
			this.commonDAO.update("ContentDetailInfo.updateContent", content);
					
			// TAG INFO DELETE AND INSERT
			modifyContentTagInfo(content);
			
			// CATEGORY INFO MODIFY
			updateContentCategoryInfo(content, oldContent);
		
			// 판매중일 때 DD, DP Main 배포
			if(Constants.CONTENT_SALE_STAT_ING.equals(oldContent.getSaleStat())) {
				
				//1. DD Main 배포
				DownloadDistributeService downloadDistributeservice = new DownloadDistributeServiceImpl();
				deployResult = downloadDistributeservice.ddDeployContents(content.getCid(), true);
				
				if(deployResult) { 
					//2. DP Main 배포
					DisplayDistributeService displayDistributeservice = new DisplayDistributeServiceImpl();
					deployResult = displayDistributeservice.dpDeployContents(content.getCid(), true);
				}

				if (deployResult) {
					daoManager.commitTransaction();
				}
				
			} else {
				daoManager.commitTransaction();
			}
		} catch (Exception e) {
			result = "UPDATE_FAIL";
			throw new ServiceException("컨탠츠 상세정보 수정 실패.", e);
		} finally {
			daoManager.endTransaction();
		}
		
		return result;
	}
	
	/* Content Cid mapping Category
	 * @see com.omp.dev.contents.service.ContentDetailInfoService#getContentCategory(java.lang.String)
	 */
	public String getContentCategory(String cid) {
		String contentCtgr = null;
		
		try{
			contentCtgr = (String) this.commonDAO.queryForObject("ContentDetailInfo.getContentCategory", cid);
			
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		} 
		return contentCtgr;
	}
	
	/* Content Category Modify
	 * @see com.omp.dev.contents.service.ContentDetailInfoService#updateContentCategoryInfo(java.lang.String)
	 */
	public void updateContentCategoryInfo (Contents content, Contents oldContent) {
		
		Contents categoryContent = new Contents();
		
		categoryContent.setCid(content.getCid());
		categoryContent.setCtgrCd(content.getCtgrCd());
		categoryContent.setCtgrDepth(content.getCtgrDepth());
		categoryContent.setUpdBy(content.getUpdBy());
		
		try{
			
			if (oldContent.getCtgrCd() == null) {				
				
				categoryContent.setInsBy(oldContent.getInsBy());
				categoryContent.setOrderSeq(1);
				
				this.commonDAO.insert("ContentDetailInfo.insertContentCategory", categoryContent);
			} else {
				this.commonDAO.update("ContentDetailInfo.updateContentCategory", categoryContent);
			}
			
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}
		
	}
	
	/* Content Sale Status List
	 * @see com.omp.dev.contents.service.ContentDetailInfoService#getContentSaleStatList(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<SaleStatHist> getContentSaleStatList(String cid) {
		
		List<SaleStatHist> resultList = null;
		
		try{
			resultList = this.commonDAO.queryForList("ContentDetailInfo.getContentSaleStatList", cid);
			
		} catch (Exception e) {
			throw new ServiceException("요청 처리가 실패했습니다. 재시도해 주시기 바랍니다.", e);
		}
	
		return resultList;
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
			content.setExposureDevNm(CommonUtil.removeSpecial(content.getExposureDevNm()));
			content.setProdDescSmmr(CommonUtil.removeSpecial(content.getProdDescSmmr()));			
			content.setProdDescDtl(CommonUtil.replaceAllTagsExceptA(content.getProdDescDtl(),""));
			content.setCorpProdNo(CommonUtil.replaceAllTagsExceptA(content.getCorpProdNo(),""));
			
			// Content Name remove Space character 
			int prodNmLastIdx,exposureDevNmIdx,prodDescSmmrIdx,prodDescDtlIdx, corpProdNoIdx;
			prodNmLastIdx = exposureDevNmIdx = prodDescSmmrIdx = prodDescDtlIdx = corpProdNoIdx = 0;
			
			
			for (int i = 0; i < content.getProdNm().length(); i++) {
				if (content.getProdNm().charAt(i) != ' ') {
					prodNmLastIdx = i;
					break;
				}
			}
			for (int i = 0; i < content.getExposureDevNm().length(); i++) {
				if (content.getExposureDevNm().charAt(i) != ' ') {
					exposureDevNmIdx = i;
					break;
				}
			}
			for (int i = 0; i < content.getCorpProdNo().length(); i++) {
				if (content.getCorpProdNo().charAt(i) != ' ') {
					corpProdNoIdx = i;
					break;
				}
			}
			for (int i = 0; i < content.getProdDescSmmr().length(); i++) {
				if (content.getProdDescSmmr().charAt(i) != ' ') {
					prodDescSmmrIdx = i;
					break;
				}
			}
			for (int i = 0; i < content.getProdDescDtl().length(); i++) {
				if (content.getProdDescDtl().charAt(i) != ' ') {
					prodDescDtlIdx = i;
					break;
				}
			}
			
			content.setProdNm(content.getProdNm().substring(prodNmLastIdx,content.getProdNm().length()));
			content.setExposureDevNm(content.getExposureDevNm().substring(exposureDevNmIdx,content.getExposureDevNm().length()));
			content.setCorpProdNo(content.getCorpProdNo().substring(corpProdNoIdx,content.getCorpProdNo().length()));
			content.setProdDescSmmr(content.getProdDescSmmr().substring(prodDescSmmrIdx,content.getProdDescSmmr().length()));
			content.setProdDescDtl(content.getProdDescDtl().substring(prodDescDtlIdx,content.getProdDescDtl().length()));
			
			// Prod Price
			if (Integer.parseInt(content.getProdPrc()) > 0) content.setPaidYn(Constants.YES);
			else content.setPaidYn(Constants.NO);
			
		} catch(Exception e) {
			throw new InvalidInputException("상품 상세 정보가 올바르지 않습니다.", e);
		}
		
		
		String promotionUrl = content.getPromotionUrl();
		
		if("http://".equals(promotionUrl)) {
			content.setPromotionUrl("");
		} else {
			// Promotion URL Check
			try {
				if(log.isDebugEnabled()) {
					log.debug("Input Value URL : {0}", new Object[] {promotionUrl});
					log.debug("Setting Value URL : {0}", new Object[] {new URL(promotionUrl).toString()});
				}
			} catch (MalformedURLException me) {
				throw new InvalidInputException("프로모션 URL이 올바르지 않습니다.", me);
			}
		}
			
	}
	
	/**
	 * 상세 정보 이미지 수정 (설명이미지, 대표아이콘, 미리보기1,2,3,4)
	 * @param content
	 * @param prop
	 * @throws JimiException 
	 */
	private String modifyContentImages(Contents content) {

		String resultMessage 	= "";
		
		ConfigProperties conf 	= new ConfigProperties();
		StringBuffer basePath 	= new StringBuffer();
		StringBuffer filePath 	= new StringBuffer();
		String vmType 			= content.getVmType();
		
		basePath.append(conf.getString("omp.common.path.http-share.product"));
		
		if (log.isDebugEnabled()) {
			log.debug("File BASE PATH ::::: {0}" + new Object[] {basePath.toString()});
		}

		if(Constants.CONTENT_PLATFORM_ANDROID.equals(vmType)) vmType = "android";
		
		filePath.append("/" + vmType);
		filePath.append("/" + content.getCid().substring(0, 2));
		filePath.append("/" + content.getCid().substring(2, 6));
		filePath.append("/" + content.getCid().substring(6, 10));
		
		resultMessage = processingContentImage(content,  basePath.toString(), filePath.toString(), conf.getStaticProperties());	// 설명 이미지

		return resultMessage;

	}
	
	/**
	 * Content Tag Info Setting (insert/delete)
	 */
	private void modifyContentTagInfo(Contents content) {
		
		ContentTagInfo[] contentTagInfo = new ContentTagInfo[5];
		String[] arrayTabName = content.getTagNm().split(",");
		
		// 기 태그 조회
		Map<String, Object> oldContentTagNm = getContentTagNameList(content.getCid());
		
		try{
			
			if (oldContentTagNm.size() > 0) { // old Content Tag Info Delete
				
				this.commonDAO.delete("ContentDetailInfo.deleteTagInfo", content.getCid());
			} 
			
			if (arrayTabName.length > 0 && arrayTabName.length <= 5) {
				for (int i = 0, j = 0; i < arrayTabName.length; i++) {
	
					if (!"".equals(StringUtil.nvlStr(StringUtil.setTrim(arrayTabName[i])))) {
						
						contentTagInfo[j] = new ContentTagInfo();
						
						contentTagInfo[j].setCid(content.getCid());
						contentTagInfo[j].setTagInfoSeq(j+1);
						contentTagInfo[j].setTagNm(StringUtil.setTrim(arrayTabName[i]));
						contentTagInfo[j].setInsBy(content.getDevUserId());
						contentTagInfo[j].setUpdBy(content.getDevUserId());
						
						if (log.isDebugEnabled()) {
							log.debug("Tab Name : [" + contentTagInfo[j].getTagInfoSeq() + "]" + contentTagInfo[j].getTagNm());
						}
					
						this.commonDAO.insert("ContentDetailInfo.insertContentTagInfo", contentTagInfo[j]);
						
						j++;
						
					}
				}
			}
			
		} catch (Exception e) {
			throw new ServiceException("태그 정보 저장 처리가 실패했습니다. 재시도해주시기 바랍니다.", e);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> getContentTagNameList(String cid) {
		
		List<ContentTagInfo>  	resultList 	= new ArrayList<ContentTagInfo>();
		Map<String, Object> 	resultMap 	= new HashMap<String, Object>();
		
		try {
			
			resultList = this.commonDAO.queryForList("ContentDetailInfo.getContentTagNameList", cid);
			
			for (int i = 0; i < resultList.size(); i++) {
				resultMap.put("tagInfoSeq"+resultList.get(i).getTagInfoSeq(), resultList.get(i).getTagInfoSeq());
				resultMap.put("tagNm"+resultList.get(i).getTagInfoSeq(), resultList.get(i).getTagNm());
				
				log.debug("Tag Info : " + resultMap.get("tagNm"+resultList.get(i).getTagInfoSeq()));
				
			}
			
		} catch (Exception e) {
			throw new ServiceException("getContentTagNameList : ", e);
		}
		
		return resultMap;

	}

	/**
	 * Content Images Insert 
	 * 
	 * @throws Exception 
	 */	
	private String processingContentImage(Contents content, String pathPreFix, String filePath, Properties prop) {
		
		String resultMessage = "IMAGE_UPDATE_FAIL";
		
		

			if (log.isDebugEnabled()) {
				log.debug("contentImage.pathPreFix :::: " + pathPreFix);
				log.debug("contentImage.filePath :::: " + filePath);
			}
			
			ContentImage contentDescImageFile = content.getDescImg();
			ContentImage contentPre1ImageFile = content.getPreviewImg1();
			ContentImage contentPre2ImageFile = content.getPreviewImg2();
			ContentImage contentPre3ImageFile = content.getPreviewImg3();
			ContentImage contentPre4ImageFile = content.getPreviewImg4();
			ContentImage contentIconImageFile = content.getIconImg1();
			
			contentDescImageFile.setCid(content.getCid());
			contentDescImageFile.setInsBy(content.getDevUserId());
			contentDescImageFile.setUpdBy(content.getDevUserId());

			contentPre1ImageFile.setCid(content.getCid());
			contentPre1ImageFile.setInsBy(content.getDevUserId());
			contentPre1ImageFile.setUpdBy(content.getDevUserId());
			
			contentPre2ImageFile.setCid(content.getCid());
			contentPre2ImageFile.setInsBy(content.getDevUserId());
			contentPre2ImageFile.setUpdBy(content.getDevUserId());
						
			contentPre3ImageFile.setCid(content.getCid());
			contentPre3ImageFile.setInsBy(content.getDevUserId());
			contentPre3ImageFile.setUpdBy(content.getDevUserId());
			
			contentPre4ImageFile.setCid(content.getCid());
			contentPre4ImageFile.setInsBy(content.getDevUserId());
			contentPre4ImageFile.setUpdBy(content.getDevUserId());
			
			contentIconImageFile.setCid(content.getCid());
			contentIconImageFile.setInsBy(content.getDevUserId());
			contentIconImageFile.setUpdBy(content.getDevUserId());
			
			
			// 이미지파일을 수정해서 올리면 기존에 있던 등록된 Image + Thumbnail의 Data를 삭제 한다.
			if(content.isDescImgDelFlag()) {
				deleteContentImage(Constants.CONTENT_IMAGE_DESC, contentDescImageFile);
			}
			if(content.isPreviewImg1DelFlag()) {
				deleteContentImage(Constants.CONTENT_IMAGE_PREV1, contentPre1ImageFile);
			}
			if(content.isPreviewImg2DelFlag()) {
				deleteContentImage(Constants.CONTENT_IMAGE_PREV2, contentPre2ImageFile);
			}
			if(content.isPreviewImg3DelFlag()) {
				deleteContentImage(Constants.CONTENT_IMAGE_PREV3, contentPre3ImageFile);
			}
			if(content.isPreviewImg4DelFlag()) {
				deleteContentImage(Constants.CONTENT_IMAGE_PREV4, contentPre4ImageFile);
			}
			if(content.isIconImg1DelFlag()) {
				deleteContentImage(Constants.CONTENT_IMAGE_ICON1, contentIconImageFile);
			}
			
		try {
			if(contentIconImageFile.getUpload() != null && !"".equals(contentIconImageFile.getUploadFileName()) 
					&& contentIconImageFile.getUpload().length() > 0){
	
				String imgWidth, imgHeight = null;

				// ICON이 등록된 원본 Image에 대해서 Data를 저장 한다.
				ContentImage realFile = insertContentImage(Constants.CONTENT_IMAGE_ICON1, contentIconImageFile, pathPreFix, filePath, content.getCid());
				
				// 대표 Icon1를 Thumbnail 처리 한다.
				imgWidth = prop.getProperty("com.omp.dev.product.img.with."+Constants.IMG_130_130, "212");
				imgHeight = prop.getProperty("com.omp.dev.product.img.height."+Constants.IMG_130_130, "212");
				insertIconTumbnail(Constants.IMG_130_130, realFile, pathPreFix, filePath, content.getCid(), imgWidth, imgHeight);	// 130 * 130
				
				imgWidth = prop.getProperty("com.omp.dev.product.img.with."+Constants.IMG_72_72, "212");
				imgHeight = prop.getProperty("com.omp.dev.product.img.height."+Constants.IMG_72_72, "212");
				insertIconTumbnail(Constants.IMG_72_72, realFile, pathPreFix, filePath, content.getCid(), imgWidth, imgHeight);		// 72 * 72
			}
			
			// 기타 등록된 원본 Image에 대해서 Data를 저장 한다.
			// Thumbnail 처리 하지 않음
			if(contentDescImageFile.getUpload() != null && !"".equals(contentDescImageFile.getUploadFileName()) && contentDescImageFile.getUpload().length() > 0){
				insertContentImage(Constants.CONTENT_IMAGE_DESC, contentDescImageFile, pathPreFix, filePath, content.getCid());
			}
			if(contentPre1ImageFile.getUpload() != null && !"".equals(contentPre1ImageFile.getUploadFileName()) && contentPre1ImageFile.getUpload().length() > 0){
				insertContentImage(Constants.CONTENT_IMAGE_PREV1, contentPre1ImageFile, pathPreFix, filePath, content.getCid());
			}
			if(contentPre2ImageFile.getUpload() != null && !"".equals(content.getPreviewImg2().getUploadFileName()) && contentPre2ImageFile.getUpload().length() > 0){
				insertContentImage(Constants.CONTENT_IMAGE_PREV2, contentPre2ImageFile, pathPreFix, filePath, content.getCid());
			}
			if(contentPre3ImageFile.getUpload() != null && !"".equals(content.getPreviewImg3().getUploadFileName()) && contentPre3ImageFile.getUpload().length() > 0){
				insertContentImage(Constants.CONTENT_IMAGE_PREV3, contentPre3ImageFile, pathPreFix, filePath, content.getCid());
			}
			if(contentPre4ImageFile.getUpload() != null && !"".equals(content.getPreviewImg4().getUploadFileName()) && contentPre4ImageFile.getUpload().length() > 0){
				insertContentImage(Constants.CONTENT_IMAGE_PREV4, contentPre4ImageFile, pathPreFix, filePath, content.getCid());
			}

			resultMessage = "SUCCESS";
		
			
		} catch (JimiException je) {
			resultMessage = "THUMBNAIL_UPDATE_FAIL";
			throw new ServiceException("대표 아이콘 Thumbnail 이미지 생성에 실패했습니다. 원본 이미지 파일 확인 후 재시도해주시기 바랍니다.", je);
		} catch (Exception e) {
			resultMessage = "IMAGE_UPDATE_FAIL";
			throw new ServiceException("이미지 생성에 실패했습니다. 원본 이미지 파일 확인 후 재시도해주시기 바랍니다.", e);
		}

		return resultMessage;
	}
	
	/**
	 * 원본 이미지를 DB에 등록 한다.
	 * 
	 * @param imgType
	 * @param contentImage
	 * @param pathPreFix
	 * @param contentImageFile
	 * @param imgPreFix
	 * @param imgNm
	 * @param cid
	 * @throws Exception 
	 */		
	private ContentImage insertContentImage(String imgType, ContentImage contentImage, String pathPreFix, String filePath, String cid) {
		
		try {
			
			StringBuffer imgFileFullPath = new StringBuffer();
			String fileDxtension = contentImage.getUploadFileName().substring(contentImage.getUploadFileName().lastIndexOf("."));
			String fileFix = null;
			
			if (imgType.equals(Constants.CONTENT_IMAGE_DESC)) 			fileFix = "desc_";
			else if (imgType.equals(Constants.CONTENT_IMAGE_ICON1)) 	fileFix = "req_";
			else if (imgType.equals(Constants.IMG_72_72)) 				fileFix = "72_72_";
			else if (imgType.equals(Constants.IMG_130_130)) 			fileFix = "130_130_";
			else if (imgType.equals(Constants.CONTENT_IMAGE_PREV1)) 	fileFix = "preview_01_";
			else if (imgType.equals(Constants.CONTENT_IMAGE_PREV2)) 	fileFix = "preview_02_";
			else if (imgType.equals(Constants.CONTENT_IMAGE_PREV3)) 	fileFix = "preview_03_";
			else if (imgType.equals(Constants.CONTENT_IMAGE_PREV4)) 	fileFix = "preview_04_";
		
			String imgFileName = fileFix + DateUtil.getGeneralTimeStampString() + fileDxtension;
			
			imgFileFullPath.append(pathPreFix);
			imgFileFullPath.append(filePath);
			imgFileFullPath.append("/" + imgFileName);
		
			log.info("ORIGINAL Info - Imb Gbn   :: {0}", new Object[] {imgType});
			log.info("ORIGINAL Info - File Src Pos  :: {0}", new Object[] {contentImage.getUpload().getPath()});
			log.info("ORIGINAL Info - File Original Name  :: {0}", new Object[] {contentImage.getUploadFileName()});
			log.info("ORIGINAL Info - File Pos  :: {0}", new Object[] {imgFileFullPath.toString()});
			log.info("ORIGINAL Info - File Nm   :: {0}", new Object[] {imgFileName});
			log.info("ORIGINAL Info - File Size :: {0}", new Object[] {contentImage.getUpload().length()});
			
			contentImage.setFilePos(filePath + "/" + imgFileName);
			contentImage.setFileNm(contentImage.getUploadFileName());
			contentImage.setFileSize(contentImage.getUpload().length());
			contentImage.setImgGbn(imgType);

			FileUtil.move(contentImage.getUpload().getPath(), imgFileFullPath.toString(), false);
		
			// 첨부된 이미지를 DB에 등록 한다.
			this.commonDAO.insert("ContentDetailInfo.insertContentImage", contentImage);
			
			return contentImage;
			
		} catch(Exception e) {
			throw new ServiceException("상품 Image 등록 실패하였습니다. 재시도해주시기 바랍니다.", e);
		}
	}
	
	private void insertIconTumbnail(String imgType, ContentImage contentImage, String pathPreFix, String filePath, String cid, String imgWidth, String imgHeight) throws Exception{

			
		String iconOriginalImgFullPath = pathPreFix + contentImage.getFilePos();
		File iconOriginalImg = new File(iconOriginalImgFullPath);
		
		StringBuffer imgFileFullPath = new StringBuffer();
		String fileDxtension = contentImage.getFileNm().substring(contentImage.getFileNm().lastIndexOf("."));
		
		String fileFix = null;
		
		if (imgType.equals(Constants.CONTENT_IMAGE_DESC)) 			fileFix = "desc_";
		else if (imgType.equals(Constants.CONTENT_IMAGE_ICON1)) 	fileFix = "req_";
		else if (imgType.equals(Constants.IMG_72_72)) 				fileFix = "72_72_";
		else if (imgType.equals(Constants.IMG_130_130)) 			fileFix = "130_130_";
		else if (imgType.equals(Constants.CONTENT_IMAGE_PREV1)) 	fileFix = "preview_01_";
		else if (imgType.equals(Constants.CONTENT_IMAGE_PREV2)) 	fileFix = "preview_02_";
		else if (imgType.equals(Constants.CONTENT_IMAGE_PREV3)) 	fileFix = "preview_03_";
		else if (imgType.equals(Constants.CONTENT_IMAGE_PREV4)) 	fileFix = "preview_04_";

		
		String imgFileName = fileFix + DateUtil.getGeneralTimeStampString() + fileDxtension;
		
		imgFileFullPath.append(pathPreFix);
		imgFileFullPath.append(filePath);
		imgFileFullPath.append("/" + imgFileName);
	
		log.info("ICON ThumbNail Info - Imb Gbn   :: {0}", new Object[] {imgType});
		log.info("ICON ThumbNail Info - File Src Pos  :: {0}", new Object[] {iconOriginalImg.getPath()});
		log.info("ICON ThumbNail Info - File Original Name  :: {0}", new Object[] {contentImage.getFileNm()});
		log.info("ICON ThumbNail Info - File Pos  :: {0}", new Object[] {imgFileFullPath.toString()});
		log.info("ICON ThumbNail Info - File Nm   :: {0}", new Object[] {imgFileName});
		log.info("ICON ThumbNail Info - File Size :: {0}", new Object[] {iconOriginalImg.length()});
		log.info("ICON ThumbNail Info - File Width :: {0}", new Object[] {imgWidth});
		log.info("ICON ThumbNail Info - File Height :: {0}", new Object[] {imgHeight});
	
		int imgWidthInt = imgWidth != null && !"".equals(imgWidth)? Integer.parseInt(imgWidth) : 212;
		int imgHeightInt = imgHeight != null && !"".equals(imgHeight)? Integer.parseInt(imgHeight) : 212;
		
		log.info("ICON ThumbNail Info - File Width :: {0}", new Object[] {imgWidthInt});
		log.info("ICON ThumbNail Info - File Height :: {0}", new Object[] {imgHeightInt});
		
		// 임시 이미지 처리
		ImageResizeUtil.resizing(iconOriginalImg.getPath(), imgFileFullPath.toString(), imgWidthInt, imgHeightInt);
		//FileUtil.copy(iconOriginalImg.getPath(), imgFileFullPath.toString(), false);
	
		contentImage.setFilePos(filePath + "/" + imgFileName);
		contentImage.setFileNm(contentImage.getFileNm());
		contentImage.setFileSize(iconOriginalImg.length());
		contentImage.setImgGbn(imgType);
	
		// 첨부된 이미지를 DB에 등록 한다.
		this.commonDAO.insert("ContentDetailInfo.insertContentImage", contentImage);

	}

	/**
	 * 기 등록된 Image를 삭제 한다.
	 * 
	 * @param imgType
	 * @param contentImage
	 * @throws Exception
	 */	
	private void deleteContentImage(String imgType, ContentImage contentImage) throws ServiceException {
		
		try {
			if(imgType.equals(Constants.CONTENT_IMAGE_ICON1)) {
				contentImage.setImgGbn("'" + Constants.CONTENT_IMAGE_ICON1 +"','"
						+ Constants.IMG_72_72 +"','" + Constants.IMG_130_130 + "'");
			} else if(imgType.equals(Constants.CONTENT_IMAGE_PREV1)) {
				contentImage.setImgGbn("'" + Constants.CONTENT_IMAGE_PREV1 + "'");
			} else if(imgType.equals(Constants.CONTENT_IMAGE_PREV2)) {
				contentImage.setImgGbn("'" + Constants.CONTENT_IMAGE_PREV2 + "'");
			} else if(imgType.equals(Constants.CONTENT_IMAGE_PREV3)) {
				contentImage.setImgGbn("'" + Constants.CONTENT_IMAGE_PREV3 + "'");
			} else if(imgType.equals(Constants.CONTENT_IMAGE_PREV4)) {
				contentImage.setImgGbn("'" + Constants.CONTENT_IMAGE_PREV4 + "'");
			} else if(imgType.equals(Constants.CONTENT_IMAGE_DESC)) {
				contentImage.setImgGbn("'" + Constants.CONTENT_IMAGE_DESC + "'");
			}
			
			this.commonDAO.delete("ContentDetailInfo.deleteContentImage", contentImage);
			
		} catch(Exception e) {
			throw new ServiceException("상품 Image 수정에 실패했습니다. 재시도해주시기 바랍니다.", e);
		}
	}
	
	/* Content Images List
	 * @see com.omp.dev.contents.service.ContentDetailInfoService#getContentImage(com.omp.dev.contents.model.ContentImage)
	 */
	@SuppressWarnings("unchecked")
	public Map<String, Object> getListContentImage(String cid, Properties prop) throws ServiceException {

		List<ContentImage> contentImage = null;
		Map<String, Object> resultMap = new HashMap<String, Object>();
				
		try {
			contentImage = this.commonDAO.queryForList("ContentDetailInfo.selectListContentImageById", cid);
			if (contentImage != null) {
				for(int i = 0 ;i < contentImage.size(); i++) {
					
					log.debug("=========== Images : " +  contentImage.get(i).getFilePos());
					log.debug("=========== Images : " +  contentImage.get(i).getFileNm());
					
					resultMap.put(contentImage.get(i).getImgGbn(), contentImage.get(i).getFilePos());
					resultMap.put(contentImage.get(i).getImgGbn()+"_NM", contentImage.get(i).getFileNm());
				}
				
			}
			
		} catch (Exception e) {
			throw new ServiceException("Can't Content getListContentImage CID - [{0}] : ", new Object[] {cid}, e);
		}
		return resultMap;
	}
	
	/**
	 * 상품 이미지 확장자 Check 한다.
	 * @param content
	 * @throws Exception
	 */
	public String getAttachFileChecked(Contents content) {
		
		// 첨부파일 확장자 체크
		boolean fileFlag[] = new boolean[7];
		
		fileFlag[0] = getFileFormatChecked(content.getDescImg().getUploadFileName());				// 상품 설명 Image
		fileFlag[1] = getFileFormatChecked(content.getPreviewImg1().getUploadFileName());			// 미리보기 1 Image
		fileFlag[2] = getFileFormatChecked(content.getPreviewImg2().getUploadFileName());			// 미리보기 2 Image
		fileFlag[3] = getFileFormatChecked(content.getPreviewImg3().getUploadFileName());			// 미리보기 3 Image
		fileFlag[4] = getFileFormatChecked(content.getPreviewImg4().getUploadFileName());			// 미리보기 4 Image
		fileFlag[5] = getFileFormatChecked(content.getIconImg1().getUploadFileName());				// 아이콘 1 Image

		for(int i = 0 ;i < 6; i++) {
		
			if(!fileFlag[i]) {
				log.info("잘못된 파일첨부  에러 attachFile : {0}", new Object[] {fileFlag[i]});
				return "FILE_EXTENTION_ERROR";
			}
		}

		// 첨부파일 용량 제한 체크
		ConfigProperties prop = new ConfigProperties();
		Long descImgLimitSize = prop.getLong("omp.dev.product.contents.descImg.uploadLimit", 1048576);		// 설명 이미지 사이즈 : 1 MB
		Long previewImgLimitSize = prop.getLong("omp.dev.product.contents.previewImg.uploadLimit", 102400);	// 미리보기 이미지 사이즈 : 200 KB
		Long iconImgLimitSize = prop.getLong("omp.dev.product.contents.iconImg.uploadLimit", 204800);	// 대표아이콘 이미지 사이즈 : 200 KB
		
		if(content.getDescImg().getUpload() != null && content.getDescImg().getUpload().length() > descImgLimitSize) {
			log.info("# 설명 이미지 업로드 용량 초과:{0}", new Object[] {content.getDescImg().getUpload()});
			return "FILE_SIZE_ERROR";
			//throw new InvalidInputException("업로드 용량 초과");
		}
		
		if(content.getPreviewImg1().getUpload() !=null && content.getPreviewImg1().getUpload().length() > previewImgLimitSize) {
			log.info("# 미리보기 이미지 업로드 용량 초과:{0}", new Object[] {content.getPreviewImg1().getUpload()});
			return "FILE_SIZE_ERROR";
			//throw new InvalidInputException("업로드 용량 초과");
		}
		
		if(content.getPreviewImg2().getUpload() != null && content.getPreviewImg2().getUpload().length() > previewImgLimitSize) {
			log.info("# 미리보기 이미지 업로드 용량 초과:{0}", new Object[] {content.getPreviewImg2().getUpload()});
			return "FILE_SIZE_ERROR";
			//throw new InvalidInputException("업로드 용량 초과");
		}
		
		if(content.getPreviewImg3().getUpload() != null && content.getPreviewImg3().getUpload().length() > previewImgLimitSize) {
			log.info("# 미리보기 이미지 업로드 용량 초과:{0}", new Object[] {content.getPreviewImg3().getUpload()});
			return "FILE_SIZE_ERROR";
			//throw new InvalidInputException("업로드 용량 초과");
		}
		
		if(content.getPreviewImg4().getUpload() != null && content.getPreviewImg4().getUpload().length() > previewImgLimitSize) {
			log.info("# 미리보기 이미지 업로드 용량 초과:{0}", new Object[] {content.getPreviewImg4().getUpload()});
			return "FILE_SIZE_ERROR";
			//throw new InvalidInputException("업로드 용량 초과");
		}

		if(content.getIconImg1().getUpload() != null && content.getIconImg1().getUpload().length() > iconImgLimitSize) {
			log.info("# 대표아이콘 이미지 업로드 용량 초과:{0}", new Object[] {content.getIconImg1().getUpload()});
			return "FILE_SIZE_ERROR";
			//throw new InvalidInputException("업로드 용량 초과");
		}

		return "SUCCESS";
	}
	
	/**
	 * 확장자가 jpg, bmp, png, gif, pjpg 인지 Check 한다.
	 * 
	 * @param attachFile
	 * @throws Exception
	 */
	private boolean getFileFormatChecked(String attachFile) {
		if(!StringUtil.nvlStr(attachFile, "").equals("")) {
			String extension = attachFile.substring(attachFile.lastIndexOf("."));
			
			if(!extension.equalsIgnoreCase(".jpg") && !extension.equalsIgnoreCase(".bmp") && !extension.equalsIgnoreCase(".png") && !extension.equalsIgnoreCase(".gif") && !extension.equalsIgnoreCase(".pjpg"))
			{
				log.info("잘못된 파일첨부  에러 attachFile :{0}", new Object[] {attachFile});
				return false;
				//throw new ServiceException("잘못된 파일첨부  에러");
			}else {
				return true;
			}
		}
		return true;
	}


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
