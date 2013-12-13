package com.omp.dev.contents.model;

import java.io.File;
import java.io.InputStream;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;

@SuppressWarnings("serial")
public class Contents extends CommonModel implements Pagenateable, TotalCountCarriable {
	
	private 	String 		cid;
	private 	String 		pid;
	private 	String 		aid;
	
	private 	String 		prodGbn;
	private 	long 		verifyReqVer;
	private 	String 		verifyPrgrYn;
	private 	String 		agrmntStat;
	private 	String 		saleStat;
	private 	String 		devUserId;
	private 	String 		prodNm;
	private 	String 		vmType;
	private 	String  	prodPrc;
	private 	String 		paidYn;
	private 	String 		adjRate;
	private 	String 		adjRateSkt;
	private 	String 		prodDescSmmr;
	private 	String 		prodDescDtl;
	private 	String 		gameDelibGrd;
	private 	String 		contsType;
	private 	String 		verifyScnrFile;
	private 	String 		verifyScnrFileNm;
	private 	String 		promotionUrl;
	private 	String 		ctgrDepth;
	private 	String 		ctgrCd;
	private 	String 		ctgrNm;
	private 	long 		orderSeq;
	private 	String 		tagNm;
	private 	String 		drmYn;
	private 	String 		drmSetOpt;
	private 	String 		drmSetVal;
	private 	String 		firstInsDt;
	private 	String 		firstAgrmntDt;
	private 	String 		saleStartDt;
	private 	String 		signOption;
	private 	String 		exposureDevNm;
	private 	String 		delYn;
	private 	String 		verifyCommentCd;
	private 	String 		verifyCommentNm;
	private 	String 		verifyEtcCmt;
	private 	String 		insBy;
	private 	String 		insDttm;
	private 	String 		updBy;
	private 	String 		updDttm;

	private		String 		verifyInsDttm;
	private 	String 		statusImgPos;
	private	 	String 		statusImgNm;
	private 	String 		imgGbn;
	private    String		verifyAvailable;
	private 	String 		newRegistContentFlag;
	private 	String 		newSaleWaitContentFlag;
	private 	String      newRequestStartContentflag;
	private 	String      newRequestEndContentflag;
	private 	String 		verifyCtReqInsDttm;
	private 	String 		verifyCtAgrmntStat;
	private 	String 		corpProdNo;
	
	// Image Files
	private 	ContentImage descImg;
	private 	ContentImage previewImg1;
	private 	ContentImage previewImg2;
	private 	ContentImage previewImg3;
	private 	ContentImage previewImg4;
	private 	ContentImage iconImg1;
	private 	ContentImage iconImg2;
	
	private 	boolean descImgDelFlag;
	private 	boolean previewImg1DelFlag;
	private 	boolean previewImg2DelFlag;
	private 	boolean previewImg3DelFlag;
	private 	boolean previewImg4DelFlag;
	private 	boolean iconImg1DelFlag;
	private 	boolean iconImg2DelFlag;

	// Manual Files
	private File 		verifyScnrUpload; 						// 검증시나리오 첨부파일
	private String 		verifyScnrUploadFileName; 				// 첨부파일명
	private String 		verifyScnrUploadContentType; 			// 첨부파일 컨텐츠타입
	private String 		verifyScnrUploadDelYn; 					// 삭제여부
	private String 		verifyScnrFileSize;    					// 실행_파일_크기
	private InputStream verifyScnrInputStream;
	private String 		verifyScnrContentDisposition;
	private long 		verifyScnrContentLength;

	private 	boolean verifyScnrDelFlag;
	
	private		String				payMemberInfo;			
	private 	String 				dispRowNum;
	private 	PagenateInfoModel	page = new PagenateInfoModel();
    private 	Long				totalCount;
    private    String				saleSearchType;

	/**
	 * @return the cid
	 */
	public String getCid() {
		return cid;
	}
	/**
	 * @param cid the cid to set
	 */
	public void setCid(String cid) {
		this.cid = cid;
	}
	/**
	 * @return the pid
	 */
	public String getPid() {
		return pid;
	}
	/**
	 * @param pid the pid to set
	 */
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**
	 * @return the aid
	 */
	public String getAid() {
		return aid;
	}
	/**
	 * @param aid the aid to set
	 */
	public void setAid(String aid) {
		this.aid = aid;
	}
	/**
	 * @return the prodGbn
	 */
	public String getProdGbn() {
		return prodGbn;
	}
	/**
	 * @param prodGbn the prodGbn to set
	 */
	public void setProdGbn(String prodGbn) {
		this.prodGbn = prodGbn;
	}
	/**
	 * @return the verifyReqVer
	 */
	public long getVerifyReqVer() {
		return verifyReqVer;
	}
	/**
	 * @param verifyReqVer the verifyReqVer to set
	 */
	public void setVerifyReqVer(long verifyReqVer) {
		this.verifyReqVer = verifyReqVer;
	}
	/**
	 * @return the verifyPrgrYn
	 */
	public String getVerifyPrgrYn() {
		return verifyPrgrYn;
	}
	/**
	 * @param verifyPrgrYn the verifyPrgrYn to set
	 */
	public void setVerifyPrgrYn(String verifyPrgrYn) {
		this.verifyPrgrYn = verifyPrgrYn;
	}
	/**
	 * @return the agrmntStat
	 */
	public String getAgrmntStat() {
		return agrmntStat;
	}
	/**
	 * @param agrmntStat the agrmntStat to set
	 */
	public void setAgrmntStat(String agrmntStat) {
		this.agrmntStat = agrmntStat;
	}
	/**
	 * @return the saleStat
	 */
	public String getSaleStat() {
		return saleStat;
	}
	/**
	 * @param saleStat the saleStat to set
	 */
	public void setSaleStat(String saleStat) {
		this.saleStat = saleStat;
	}
	/**
	 * @return the devUserId
	 */
	public String getDevUserId() {
		return devUserId;
	}
	/**
	 * @param devUserId the devUserId to set
	 */
	public void setDevUserId(String devUserId) {
		this.devUserId = devUserId;
	}
	/**
	 * @return the prodNm
	 */
	public String getProdNm() {
		return prodNm;
	}
	/**
	 * @param prodNm the prodNm to set
	 */
	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}
	/**
	 * @return the vmType
	 */
	public String getVmType() {
		return vmType;
	}
	/**
	 * @param vmType the vmType to set
	 */
	public void setVmType(String vmType) {
		this.vmType = vmType;
	}
	/**
	 * @return the prodPrc
	 */
	public String getProdPrc() {
		return prodPrc;
	}
	/**
	 * @param prodPrc the prodPrc to set
	 */
	public void setProdPrc(String prodPrc) {
		this.prodPrc = prodPrc;
	}
	/**
	 * @return the paidYn
	 */
	public String getPaidYn() {
		return paidYn;
	}
	/**
	 * @param paidYn the paidYn to set
	 */
	public void setPaidYn(String paidYn) {
		this.paidYn = paidYn;
	}
	/**
	 * @return the adjRate
	 */
	public String getAdjRate() {
		return adjRate;
	}
	/**
	 * @param adjRate the adjRate to set
	 */
	public void setAdjRate(String adjRate) {
		this.adjRate = adjRate;
	}
	/**
	 * @return the adjRateSkt
	 */
	public String getAdjRateSkt() {
		return adjRateSkt;
	}
	/**
	 * @param adjRateSkt the adjRateSkt to set
	 */
	public void setAdjRateSkt(String adjRateSkt) {
		this.adjRateSkt = adjRateSkt;
	}
	/**
	 * @return the prodDescSmmr
	 */
	public String getProdDescSmmr() {
		return prodDescSmmr;
	}
	/**
	 * @param prodDescSmmr the prodDescSmmr to set
	 */
	public void setProdDescSmmr(String prodDescSmmr) {
		this.prodDescSmmr = prodDescSmmr;
	}
	/**
	 * @return the prodDescDtl
	 */
	public String getProdDescDtl() {
		return prodDescDtl;
	}
	/**
	 * @param prodDescDtl the prodDescDtl to set
	 */
	public void setProdDescDtl(String prodDescDtl) {
		this.prodDescDtl = prodDescDtl;
	}
	/**
	 * @return the gameDelibGrd
	 */
	public String getGameDelibGrd() {
		return gameDelibGrd;
	}
	/**
	 * @param gameDelibGrd the gameDelibGrd to set
	 */
	public void setGameDelibGrd(String gameDelibGrd) {
		this.gameDelibGrd = gameDelibGrd;
	}
	/**
	 * @return the contsType
	 */
	public String getContsType() {
		return contsType;
	}
	/**
	 * @param contsType the contsType to set
	 */
	public void setContsType(String contsType) {
		this.contsType = contsType;
	}
	/**
	 * @return the verifyScnrFile
	 */
	public String getVerifyScnrFile() {
		return verifyScnrFile;
	}
	/**
	 * @param verifyScnrFile the verifyScnrFile to set
	 */
	public void setVerifyScnrFile(String verifyScnrFile) {
		this.verifyScnrFile = verifyScnrFile;
	}
	/**
	 * @return the verifyScnrFileNm
	 */
	public String getVerifyScnrFileNm() {
		return verifyScnrFileNm;
	}
	/**
	 * @param verifyScnrFileNm the verifyScnrFileNm to set
	 */
	public void setVerifyScnrFileNm(String verifyScnrFileNm) {
		this.verifyScnrFileNm = verifyScnrFileNm;
	}
	
	/**
	 * @return the ctgrDepth
	 */
	public String getCtgrDepth() {
		return ctgrDepth;
	}
	/**
	 * @param ctgrDepth the ctgrDepth to set
	 */
	public void setCtgrDepth(String ctgrDepth) {
		this.ctgrDepth = ctgrDepth;
	}
	/**
	 * @return the ctgrCd
	 */
	public String getCtgrCd() {
		return ctgrCd;
	}
	/**
	 * @param ctgrCd the ctgrCd to set
	 */
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	/**
	 * @return the ctgrNm
	 */
	public String getCtgrNm() {
		return ctgrNm;
	}
	/**
	 * @param ctgrNm the ctgrNm to set
	 */
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}
	/**
	 * @return the orderSeq
	 */
	public long getOrderSeq() {
		return orderSeq;
	}
	/**
	 * @param orderSeq the orderSeq to set
	 */
	public void setOrderSeq(long orderSeq) {
		this.orderSeq = orderSeq;
	}
	/**
	 * @return the tagNm
	 */
	public String getTagNm() {
		return tagNm;
	}
	/**
	 * @param tagNm the tagNm to set
	 */
	public void setTagNm(String tagNm) {
		this.tagNm = tagNm;
	}
	/**
	 * @return the drmYn
	 */
	public String getDrmYn() {
		return drmYn;
	}
	/**
	 * @param drmYn the drmYn to set
	 */
	public void setDrmYn(String drmYn) {
		this.drmYn = drmYn;
	}
	/**
	 * @return the drmSetOpt
	 */
	public String getDrmSetOpt() {
		return drmSetOpt;
	}
	/**
	 * @param drmSetOpt the drmSetOpt to set
	 */
	public void setDrmSetOpt(String drmSetOpt) {
		this.drmSetOpt = drmSetOpt;
	}
	/**
	 * @return the drmSetVal
	 */
	public String getDrmSetVal() {
		return drmSetVal;
	}
	/**
	 * @param drmSetVal the drmSetVal to set
	 */
	public void setDrmSetVal(String drmSetVal) {
		this.drmSetVal = drmSetVal;
	}
	/**
	 * @return the firstInsDt
	 */
	public String getFirstInsDt() {
		return firstInsDt;
	}
	/**
	 * @param firstInsDt the firstInsDt to set
	 */
	public void setFirstInsDt(String firstInsDt) {
		this.firstInsDt = firstInsDt;
	}
	/**
	 * @return the firstAgrmntDt
	 */
	public String getFirstAgrmntDt() {
		return firstAgrmntDt;
	}
	/**
	 * @param firstAgrmntDt the firstAgrmntDt to set
	 */
	public void setFirstAgrmntDt(String firstAgrmntDt) {
		this.firstAgrmntDt = firstAgrmntDt;
	}
	/**
	 * @return the saleStartDt
	 */
	public String getSaleStartDt() {
		return saleStartDt;
	}
	/**
	 * @param saleStartDt the saleStartDt to set
	 */
	public void setSaleStartDt(String saleStartDt) {
		this.saleStartDt = saleStartDt;
	}
	/**
	 * @return the signOption
	 */
	public String getSignOption() {
		return signOption;
	}
	/**
	 * @param signOption the signOption to set
	 */
	public void setSignOption(String signOption) {
		this.signOption = signOption;
	}
	/**
	 * @return the exposureDevNm
	 */
	public String getExposureDevNm() {
		return exposureDevNm;
	}
	/**
	 * @param exposureDevNm the exposureDevNm to set
	 */
	public void setExposureDevNm(String exposureDevNm) {
		this.exposureDevNm = exposureDevNm;
	}
	/**
	 * @return the delYn
	 */
	public String getDelYn() {
		return delYn;
	}
	/**
	 * @param delYn the delYn to set
	 */
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	/**
	 * @return the verifyCommentCd
	 */
	public String getVerifyCommentCd() {
		return verifyCommentCd;
	}
	/**
	 * @param verifyCommentCd the verifyCommentCd to set
	 */
	public void setVerifyCommentCd(String verifyCommentCd) {
		this.verifyCommentCd = verifyCommentCd;
	}
	/**
	 * @return the verifyCommentNm
	 */
	public String getVerifyCommentNm() {
		return verifyCommentNm;
	}
	/**
	 * @param verifyCommentNm the verifyCommentNm to set
	 */
	public void setVerifyCommentNm(String verifyCommentNm) {
		this.verifyCommentNm = verifyCommentNm;
	}
	/**
	 * @return the verifyEtcCmt
	 */
	public String getVerifyEtcCmt() {
		return verifyEtcCmt;
	}
	/**
	 * @param verifyEtcCmt the verifyEtcCmt to set
	 */
	public void setVerifyEtcCmt(String verifyEtcCmt) {
		this.verifyEtcCmt = verifyEtcCmt;
	}
	/**
	 * @return the promotionUrl
	 */
	public String getPromotionUrl() {
		return promotionUrl;
	}
	/**
	 * @param promotionUrl the promotionUrl to set
	 */
	public void setPromotionUrl(String promotionUrl) {
		this.promotionUrl = promotionUrl;
	}
	
	/**
	 * @return the corpProdNo
	 */
	public String getCorpProdNo() {
		return corpProdNo;
	}
	/**
	 * @param corpProdNo the corpProdNo to set
	 */
	public void setCorpProdNo(String corpProdNo) {
		this.corpProdNo = corpProdNo;
	}
	/**
	 * @return the insBy
	 */
	public String getInsBy() {
		return insBy;
	}
	/**
	 * @param insBy the insBy to set
	 */
	public void setInsBy(String insBy) {
		this.insBy = insBy;
	}
	/**
	 * @return the insDttm
	 */
	public String getInsDttm() {
		return insDttm;
	}
	/**
	 * @param insDttm the insDttm to set
	 */
	public void setInsDttm(String insDttm) {
		this.insDttm = insDttm;
	}
	/**
	 * @return the updBy
	 */
	public String getUpdBy() {
		return updBy;
	}
	/**
	 * @param updBy the updBy to set
	 */
	public void setUpdBy(String updBy) {
		this.updBy = updBy;
	}
	/**
	 * @return the updDttm
	 */
	public String getUpdDttm() {
		return updDttm;
	}
	/**
	 * @param updDttm the updDttm to set
	 */
	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}
	/**
	 * @return the statusImgPos
	 */
	public String getStatusImgPos() {
		return statusImgPos;
	}
	/**
	 * @param statusImgPos the statusImgPos to set
	 */
	public void setStatusImgPos(String statusImgPos) {
		this.statusImgPos = statusImgPos;
	}
	/**
	 * @return the statusImgNm
	 */
	public String getStatusImgNm() {
		return statusImgNm;
	}
	/**
	 * @param statusImgNm the statusImgNm to set
	 */
	public void setStatusImgNm(String statusImgNm) {
		this.statusImgNm = statusImgNm;
	}
	/**
	 * @return the imgGbn
	 */
	public String getImgGbn() {
		return imgGbn;
	}
	/**
	 * @param imgGbn the imgGbn to set
	 */
	public void setImgGbn(String imgGbn) {
		this.imgGbn = imgGbn;
	}
	/**
	 * @return the verifyAvailable
	 */
	public String getVerifyAvailable() {
		return verifyAvailable;
	}
	/**
	 * @param verifyAvailable the verifyAvailable to set
	 */
	public void setVerifyAvailable(String verifyAvailable) {
		this.verifyAvailable = verifyAvailable;
	}
	/**
	 * @return the newRegistContentFlag
	 */
	public String getNewRegistContentFlag() {
		return newRegistContentFlag;
	}
	/**
	 * @param newRegistContentFlag the newRegistContentFlag to set
	 */
	public void setNewRegistContentFlag(String newRegistContentFlag) {
		this.newRegistContentFlag = newRegistContentFlag;
	}
	/**
	 * @return the newSaleWaitContentFlag
	 */
	public String getNewSaleWaitContentFlag() {
		return newSaleWaitContentFlag;
	}
	/**
	 * @param newSaleWaitContentFlag the newSaleWaitContentFlag to set
	 */
	public void setNewSaleWaitContentFlag(String newSaleWaitContentFlag) {
		this.newSaleWaitContentFlag = newSaleWaitContentFlag;
	}

	/**
	 * @return the verifyInsDttm
	 */
	public String getVerifyInsDttm() {
		return verifyInsDttm;
	}
	/**
	 * @param verifyInsDttm the verifyInsDttm to set
	 */
	public void setVerifyInsDttm(String verifyInsDttm) {
		this.verifyInsDttm = verifyInsDttm;
	}
	/**
	 * @return the descImg
	 */
	public ContentImage getDescImg() {
		return descImg;
	}
	/**
	 * @param descImg the descImg to set
	 */
	public void setDescImg(ContentImage descImg) {
		this.descImg = descImg;
	}
	/**
	 * @return the previewImg1
	 */
	public ContentImage getPreviewImg1() {
		return previewImg1;
	}
	/**
	 * @param previewImg1 the previewImg1 to set
	 */
	public void setPreviewImg1(ContentImage previewImg1) {
		this.previewImg1 = previewImg1;
	}
	/**
	 * @return the previewImg2
	 */
	public ContentImage getPreviewImg2() {
		return previewImg2;
	}
	/**
	 * @param previewImg2 the previewImg2 to set
	 */
	public void setPreviewImg2(ContentImage previewImg2) {
		this.previewImg2 = previewImg2;
	}
	/**
	 * @return the previewImg3
	 */
	public ContentImage getPreviewImg3() {
		return previewImg3;
	}
	/**
	 * @param previewImg3 the previewImg3 to set
	 */
	public void setPreviewImg3(ContentImage previewImg3) {
		this.previewImg3 = previewImg3;
	}
	/**
	 * @return the previewImg4
	 */
	public ContentImage getPreviewImg4() {
		return previewImg4;
	}
	/**
	 * @param previewImg4 the previewImg4 to set
	 */
	public void setPreviewImg4(ContentImage previewImg4) {
		this.previewImg4 = previewImg4;
	}
	/**
	 * @return the iconImg1
	 */
	public ContentImage getIconImg1() {
		return iconImg1;
	}
	/**
	 * @param iconImg1 the iconImg1 to set
	 */
	public void setIconImg1(ContentImage iconImg1) {
		this.iconImg1 = iconImg1;
	}
	/**
	 * @return the iconImg2
	 */
	public ContentImage getIconImg2() {
		return iconImg2;
	}
	/**
	 * @param iconImg2 the iconImg2 to set
	 */
	public void setIconImg2(ContentImage iconImg2) {
		this.iconImg2 = iconImg2;
	}

	/**
	 * @return the descImgDelFlag
	 */
	public boolean isDescImgDelFlag() {
		return descImgDelFlag;
	}
	/**
	 * @param descImgDelFlag the descImgDelFlag to set
	 */
	public void setDescImgDelFlag(boolean descImgDelFlag) {
		this.descImgDelFlag = descImgDelFlag;
	}
	/**
	 * @return the previewImg1DelFlag
	 */
	public boolean isPreviewImg1DelFlag() {
		return previewImg1DelFlag;
	}
	/**
	 * @param previewImg1DelFlag the previewImg1DelFlag to set
	 */
	public void setPreviewImg1DelFlag(boolean previewImg1DelFlag) {
		this.previewImg1DelFlag = previewImg1DelFlag;
	}
	/**
	 * @return the previewImg2DelFlag
	 */
	public boolean isPreviewImg2DelFlag() {
		return previewImg2DelFlag;
	}
	/**
	 * @param previewImg2DelFlag the previewImg2DelFlag to set
	 */
	public void setPreviewImg2DelFlag(boolean previewImg2DelFlag) {
		this.previewImg2DelFlag = previewImg2DelFlag;
	}
	/**
	 * @return the previewImg3DelFlag
	 */
	public boolean isPreviewImg3DelFlag() {
		return previewImg3DelFlag;
	}
	/**
	 * @param previewImg3DelFlag the previewImg3DelFlag to set
	 */
	public void setPreviewImg3DelFlag(boolean previewImg3DelFlag) {
		this.previewImg3DelFlag = previewImg3DelFlag;
	}
	/**
	 * @return the previewImg4DelFlag
	 */
	public boolean isPreviewImg4DelFlag() {
		return previewImg4DelFlag;
	}
	/**
	 * @param previewImg4DelFlag the previewImg4DelFlag to set
	 */
	public void setPreviewImg4DelFlag(boolean previewImg4DelFlag) {
		this.previewImg4DelFlag = previewImg4DelFlag;
	}
	/**
	 * @return the iconImg1DelFlag
	 */
	public boolean isIconImg1DelFlag() {
		return iconImg1DelFlag;
	}
	/**
	 * @param iconImg1DelFlag the iconImg1DelFlag to set
	 */
	public void setIconImg1DelFlag(boolean iconImg1DelFlag) {
		this.iconImg1DelFlag = iconImg1DelFlag;
	}
	/**
	 * @return the iconImg2DelFlag
	 */
	public boolean isIconImg2DelFlag() {
		return iconImg2DelFlag;
	}
	/**
	 * @param iconImg2DelFlag the iconImg2DelFlag to set
	 */
	public void setIconImg2DelFlag(boolean iconImg2DelFlag) {
		this.iconImg2DelFlag = iconImg2DelFlag;
	}
	/**
	 * @return the verifyScnrUpload
	 */
	public File getVerifyScnrUpload() {
		return verifyScnrUpload;
	}
	/**
	 * @param verifyScnrUpload the verifyScnrUpload to set
	 */
	public void setVerifyScnrUpload(File verifyScnrUpload) {
		this.verifyScnrUpload = verifyScnrUpload;
	}
	/**
	 * @return the verifyScnrUploadFileName
	 */
	public String getVerifyScnrUploadFileName() {
		return verifyScnrUploadFileName;
	}
	/**
	 * @param verifyScnrUploadFileName the verifyScnrUploadFileName to set
	 */
	public void setVerifyScnrUploadFileName(String verifyScnrUploadFileName) {
		this.verifyScnrUploadFileName = verifyScnrUploadFileName;
	}
	/**
	 * @return the verifyScnrUploadContentType
	 */
	public String getVerifyScnrUploadContentType() {
		return verifyScnrUploadContentType;
	}
	/**
	 * @param verifyScnrUploadContentType the verifyScnrUploadContentType to set
	 */
	public void setVerifyScnrUploadContentType(String verifyScnrUploadContentType) {
		this.verifyScnrUploadContentType = verifyScnrUploadContentType;
	}
	/**
	 * @return the verifyScnrUploadDelYn
	 */
	public String getVerifyScnrUploadDelYn() {
		return verifyScnrUploadDelYn;
	}
	/**
	 * @param verifyScnrUploadDelYn the verifyScnrUploadDelYn to set
	 */
	public void setVerifyScnrUploadDelYn(String verifyScnrUploadDelYn) {
		this.verifyScnrUploadDelYn = verifyScnrUploadDelYn;
	}
	/**
	 * @return the verifyScnrFileSize
	 */
	public String getVerifyScnrFileSize() {
		return verifyScnrFileSize;
	}
	/**
	 * @param verifyScnrFileSize the verifyScnrFileSize to set
	 */
	public void setVerifyScnrFileSize(String verifyScnrFileSize) {
		this.verifyScnrFileSize = verifyScnrFileSize;
	}
	/**
	 * @return the verifyScnrInputStream
	 */
	public InputStream getVerifyScnrInputStream() {
		return verifyScnrInputStream;
	}
	/**
	 * @param verifyScnrInputStream the verifyScnrInputStream to set
	 */
	public void setVerifyScnrInputStream(InputStream verifyScnrInputStream) {
		this.verifyScnrInputStream = verifyScnrInputStream;
	}
	/**
	 * @return the verifyScnrContentDisposition
	 */
	public String getVerifyScnrContentDisposition() {
		return verifyScnrContentDisposition;
	}
	/**
	 * @param verifyScnrContentDisposition the verifyScnrContentDisposition to set
	 */
	public void setVerifyScnrContentDisposition(String verifyScnrContentDisposition) {
		this.verifyScnrContentDisposition = verifyScnrContentDisposition;
	}
	/**
	 * @return the verifyScnrContentLength
	 */
	public long getVerifyScnrContentLength() {
		return verifyScnrContentLength;
	}
	/**
	 * @param verifyScnrContentLength the verifyScnrContentLength to set
	 */
	public void setVerifyScnrContentLength(long verifyScnrContentLength) {
		this.verifyScnrContentLength = verifyScnrContentLength;
	}
	/**
	 * @return the verifyScnrDelFlag
	 */
	public boolean isVerifyScnrDelFlag() {
		return verifyScnrDelFlag;
	}
	/**
	 * @param verifyScnrDelFlag the verifyScnrDelFlag to set
	 */
	public void setVerifyScnrDelFlag(boolean verifyScnrDelFlag) {
		this.verifyScnrDelFlag = verifyScnrDelFlag;
	}
	
	/**
	 * @return the newRequestStartContentflag
	 */
	public String getNewRequestStartContentflag() {
		return newRequestStartContentflag;
	}
	/**
	 * @param newRequestStartContentflag the newRequestStartContentflag to set
	 */
	public void setNewRequestStartContentflag(String newRequestStartContentflag) {
		this.newRequestStartContentflag = newRequestStartContentflag;
	}
	/**
	 * @return the newRequestEndContentflag
	 */
	public String getNewRequestEndContentflag() {
		return newRequestEndContentflag;
	}
	/**
	 * @param newRequestEndContentflag the newRequestEndContentflag to set
	 */
	public void setNewRequestEndContentflag(String newRequestEndContentflag) {
		this.newRequestEndContentflag = newRequestEndContentflag;
	}
	/**
	 * @return the verifyCtReqInsDttm
	 */
	public String getVerifyCtReqInsDttm() {
		return verifyCtReqInsDttm;
	}
	/**
	 * @param verifyCtReqInsDttm the verifyCtReqInsDttm to set
	 */
	public void setVerifyCtReqInsDttm(String verifyCtReqInsDttm) {
		this.verifyCtReqInsDttm = verifyCtReqInsDttm;
	}
	/**
	 * @return the verifyCtAgrmntStat
	 */
	public String getVerifyCtAgrmntStat() {
		return verifyCtAgrmntStat;
	}
	/**
	 * @param verifyCtAgrmntStat the verifyCtAgrmntStat to set
	 */
	public void setVerifyCtAgrmntStat(String verifyCtAgrmntStat) {
		this.verifyCtAgrmntStat = verifyCtAgrmntStat;
	}
	public String getDispRowNum() {
		return dispRowNum;
	}
	
	public void setDispRowNum(String dispRowNum) {
		this.dispRowNum = dispRowNum;
	}

	/**
	 * @return the payMemberInfo
	 */
	public String getPayMemberInfo() {
		return payMemberInfo;
	}
	/**
	 * @param payMemberInfo the payMemberInfo to set
	 */
	public void setPayMemberInfo(String payMemberInfo) {
		this.payMemberInfo = payMemberInfo;
	}
	@Override
	public PagenateInfoModel getPage() {
		return this.page;
	}

	@Override
	public Long getTotalCount() {
		return this.totalCount;
	}

	@Override
	public Long setTotalCount(Long totalCount) {
		return this.totalCount	= totalCount;
	}

	/**
	 * @return the saleSearchType
	 */
	public String getSaleSearchType() {
		return saleSearchType;
	}
	/**
	 * @param saleSearchType the saleSearchType to set
	 */
	public void setSaleSearchType(String saleSearchType) {
		this.saleSearchType = saleSearchType;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("\n==========[ Contents Status Info ]========================\n");
		sb.append("* CID 					: " + this.getCid()				+ "\n");
		sb.append("* AID 					: " + this.getAid()				+ "\n");
		sb.append("* PROD name 				: " + this.getProdNm()			+ "\n");
		sb.append("* PROD PRICE 			: " + this.getProdPrc()			+ "\n");
		sb.append("* Verify Status 			: " + this.getVerifyPrgrYn()	+ "\n");
		sb.append("* Verify Req Version 	: " + this.getVerifyReqVer()	+ "\n");
		sb.append("* ARM YN		 			: " + this.getDrmYn()			+ "\n");
		sb.append("* Sale Status 			: " + this.getSaleStat()		+ "\n");
		sb.append("\n==========================================================\n");
		
		return sb.toString();
	}
	
}
