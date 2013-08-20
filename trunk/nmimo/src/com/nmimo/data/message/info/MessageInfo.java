package com.nmimo.data.message.info;

import java.io.Serializable;

import com.nmimo.common.util.StringUtils;

@SuppressWarnings("serial")
public class MessageInfo extends MessageDefaultInfo implements Serializable{

	private String wrkId;
	private String wrkNm;
	private String userNm;
	private String userDeptNm;
	private String msgDivVal;
	private String msgCharVal;
	private String msgTitleNm;
	private String dspTgtSbst;
	private String dspPrpsSbst;
	private String etcInfoSbst;
	private String bpmSnctNo;
	private String reviewMtrVal;
	private String otgoNo;
	private String msgSbst;
	private String cretDt;
	private String cretrId;
	private String rsrvDt;
	private String amdDt;
	private String amdrId;
	private String amdrDeptNm;
	private String tgtrFileNm;
	private String rsndYn;
	private String rsndTmscnt;
	private String trmRqtCasCnt;
	private String realTrmCascnt;
	private String fileDuplCascnt;
	private String rfslCascnt;
	private String sqlErrCascnt;
	private String dlyDuplCascnt;
	private String trmSttusVal;
	private String wrkPathVal;		// A - 일반용, C - 상담사용
	private String apvSttusVal;
	private String reviwrId;
	private String reviwrNm;
	private String reviewTimeNm;
	private String calbkUrlTypeVal;
	private String feaponCalbkUrlVal;
	private String smphCalbkUrlVal;
	private String calbkUrlMemoSbst;
	private String calbkNoTypeVal;
	private String feaponCalbkNo;
	private String smphCalbkNo;
	private String msgSbstHTML;
	private String msgSbstTEXT;
	private String arrMultiFileName;
	private String bannerUseVal;
	private String rsrvHH;
	private String nstepPrmsVal;
	
	private String[] arrMulti;

	
	/**
	 * @return the wrkId
	 */
	public String getWrkId() {
		return wrkId;
	}
	/**
	 * @param wrkId the wrkId to set
	 */
	public void setWrkId(String wrkId) {
		this.wrkId = StringUtils.nvlStr(wrkId);
	}
	/**
	 * @return the wrkNm
	 */
	public String getWrkNm() {
		return wrkNm;
	}
	/**
	 * @param wrkNm the wrkNm to set
	 */
	public void setWrkNm(String wrkNm) {
		this.wrkNm = StringUtils.nvlStr(wrkNm);
	}
	/**
	 * @return the userNm
	 */
	public String getUserNm() {
		return userNm;
	}
	/**
	 * @param userNm the userNm to set
	 */
	public void setUserNm(String userNm) {
		this.userNm = StringUtils.nvlStr(userNm);
	}
	/**
	 * @return the userDeptNm
	 */
	public String getUserDeptNm() {
		return userDeptNm;
	}
	/**
	 * @param userDeptNm the userDeptNm to set
	 */
	public void setUserDeptNm(String userDeptNm) {
		this.userDeptNm = StringUtils.nvlStr(userDeptNm);
	}
	/**
	 * @return the msgDivVal
	 */
	public String getMsgDivVal() {
		return msgDivVal;
	}
	/**
	 * @param msgDivVal the msgDivVal to set
	 */
	public void setMsgDivVal(String msgDivVal) {
		this.msgDivVal = StringUtils.nvlStr(msgDivVal);
	}
	/**
	 * @return the msgCharVal
	 */
	public String getMsgCharVal() {
		return msgCharVal;
	}
	/**
	 * @param msgCharVal the msgCharVal to set
	 */
	public void setMsgCharVal(String msgCharVal) {
		this.msgCharVal = StringUtils.nvlStr(msgCharVal);
	}
	/**
	 * @return the msgTitleNm
	 */
	public String getMsgTitleNm() {
		return msgTitleNm;
	}
	/**
	 * @param msgTitleNm the msgTitleNm to set
	 */
	public void setMsgTitleNm(String msgTitleNm) {
		this.msgTitleNm = StringUtils.nvlStr(msgTitleNm);
	}
	/**
	 * @return the dspTgtSbst
	 */
	public String getDspTgtSbst() {
		return dspTgtSbst;
	}
	/**
	 * @param dspTgtSbst the dspTgtSbst to set
	 */
	public void setDspTgtSbst(String dspTgtSbst) {
		this.dspTgtSbst = StringUtils.nvlStr(dspTgtSbst);
	}
	/**
	 * @return the dspPrpsSbst
	 */
	public String getDspPrpsSbst() {
		return dspPrpsSbst;
	}
	/**
	 * @param dspPrpsSbst the dspPrpsSbst to set
	 */
	public void setDspPrpsSbst(String dspPrpsSbst) {
		this.dspPrpsSbst = StringUtils.nvlStr(dspPrpsSbst);
	}
	/**
	 * @return the etcInfoSbst
	 */
	public String getEtcInfoSbst() {
		return etcInfoSbst;
	}
	/**
	 * @param etcInfoSbst the etcInfoSbst to set
	 */
	public void setEtcInfoSbst(String etcInfoSbst) {
		this.etcInfoSbst = StringUtils.nvlStr(etcInfoSbst);
	}
	/**
	 * @return the bpmSnctNo
	 */
	public String getBpmSnctNo() {
		return bpmSnctNo;
	}
	/**
	 * @param bpmSnctNo the bpmSnctNo to set
	 */
	public void setBpmSnctNo(String bpmSnctNo) {
		this.bpmSnctNo = StringUtils.nvlStr(bpmSnctNo);
	}
	/**
	 * @return the reviewMtrVal
	 */
	public String getReviewMtrVal() {
		return reviewMtrVal;
	}
	/**
	 * @param reviewMtrVal the reviewMtrVal to set
	 */
	public void setReviewMtrVal(String reviewMtrVal) {
		this.reviewMtrVal = StringUtils.nvlStr(reviewMtrVal);
	}
	/**
	 * @return the otgoNo
	 */
	public String getOtgoNo() {
		return otgoNo;
	}
	/**
	 * @param otgoNo the otgoNo to set
	 */
	public void setOtgoNo(String otgoNo) {
		this.otgoNo = StringUtils.nvlStr(otgoNo);
	}
	/**
	 * @return the msgSbst
	 */
	public String getMsgSbst() {
		return msgSbst;
	}
	/**
	 * @param msgSbst the msgSbst to set
	 */
	public void setMsgSbst(String msgSbst) {
		this.msgSbst = StringUtils.nvlStr(msgSbst);
	}
	/**
	 * @return the cretDt
	 */
	public String getCretDt() {
		return cretDt;
	}
	/**
	 * @param cretDt the cretDt to set
	 */
	public void setCretDt(String cretDt) {
		this.cretDt = StringUtils.nvlStr(cretDt);
	}
	/**
	 * @return the cretrId
	 */
	public String getCretrId() {
		return cretrId;
	}
	/**
	 * @param cretrId the cretrId to set
	 */
	public void setCretrId(String cretrId) {
		this.cretrId = StringUtils.nvlStr(cretrId);
	}
	/**
	 * @return the rsrvDt
	 */
	public String getRsrvDt() {
		return rsrvDt;
	}
	/**
	 * @param rsrvDt the rsrvDt to set
	 */
	public void setRsrvDt(String rsrvDt) {
		this.rsrvDt = StringUtils.nvlStr(rsrvDt);
	}
	/**
	 * @return the amdDt
	 */
	public String getAmdDt() {
		return amdDt;
	}
	/**
	 * @param amdDt the amdDt to set
	 */
	public void setAmdDt(String amdDt) {
		this.amdDt = StringUtils.nvlStr(amdDt);
	}
	/**
	 * @return the amdrId
	 */
	public String getAmdrId() {
		return amdrId;
	}
	/**
	 * @param amdrId the amdrId to set
	 */
	public void setAmdrId(String amdrId) {
		this.amdrId = StringUtils.nvlStr(amdrId);
	}
	/**
	 * @return the amdrDeptNm
	 */
	public String getAmdrDeptNm() {
		return amdrDeptNm;
	}
	/**
	 * @param amdrDeptNm the amdrDeptNm to set
	 */
	public void setAmdrDeptNm(String amdrDeptNm) {
		this.amdrDeptNm = StringUtils.nvlStr(amdrDeptNm);
	}
	/**
	 * @return the tgtrFileNm
	 */
	public String getTgtrFileNm() {
		return tgtrFileNm;
	}
	/**
	 * @param tgtrFileNm the tgtrFileNm to set
	 */
	public void setTgtrFileNm(String tgtrFileNm) {
		this.tgtrFileNm = StringUtils.nvlStr(tgtrFileNm);
	}
	/**
	 * @return the rsndYn
	 */
	public String getRsndYn() {
		return rsndYn;
	}
	/**
	 * @param rsndYn the rsndYn to set
	 */
	public void setRsndYn(String rsndYn) {
		this.rsndYn = StringUtils.nvlStr(rsndYn);
	}
	
	/**
	 * @return the rsndTmscnt
	 */
	public String getRsndTmscnt() {
		return rsndTmscnt;
	}
	/**
	 * @param rsndTmscnt the rsndTmscnt to set
	 */
	public void setRsndTmscnt(String rsndTmscnt) {
		this.rsndTmscnt = rsndTmscnt;
	}
	/**
	 * @return the trmRqtCasCnt
	 */
	public String getTrmRqtCasCnt() {
		return trmRqtCasCnt;
	}
	/**
	 * @param trmRqtCasCnt the trmRqtCasCnt to set
	 */
	public void setTrmRqtCasCnt(String trmRqtCasCnt) {
		this.trmRqtCasCnt = trmRqtCasCnt;
	}
	/**
	 * @return the realTrmCascnt
	 */
	public String getRealTrmCascnt() {
		return realTrmCascnt;
	}
	/**
	 * @param realTrmCascnt the realTrmCascnt to set
	 */
	public void setRealTrmCascnt(String realTrmCascnt) {
		this.realTrmCascnt = realTrmCascnt;
	}
	/**
	 * @return the fileDuplCascnt
	 */
	public String getFileDuplCascnt() {
		return fileDuplCascnt;
	}
	/**
	 * @param fileDuplCascnt the fileDuplCascnt to set
	 */
	public void setFileDuplCascnt(String fileDuplCascnt) {
		this.fileDuplCascnt = fileDuplCascnt;
	}
	/**
	 * @return the rfslCascnt
	 */
	public String getRfslCascnt() {
		return rfslCascnt;
	}
	/**
	 * @param rfslCascnt the rfslCascnt to set
	 */
	public void setRfslCascnt(String rfslCascnt) {
		this.rfslCascnt = rfslCascnt;
	}
	/**
	 * @return the sqlErrCascnt
	 */
	public String getSqlErrCascnt() {
		return sqlErrCascnt;
	}
	/**
	 * @param sqlErrCascnt the sqlErrCascnt to set
	 */
	public void setSqlErrCascnt(String sqlErrCascnt) {
		this.sqlErrCascnt = sqlErrCascnt;
	}
	/**
	 * @return the dlyDuplCascnt
	 */
	public String getDlyDuplCascnt() {
		return dlyDuplCascnt;
	}
	/**
	 * @param dlyDuplCascnt the dlyDuplCascnt to set
	 */
	public void setDlyDuplCascnt(String dlyDuplCascnt) {
		this.dlyDuplCascnt = dlyDuplCascnt;
	}
	/**
	 * @return the trmSttusVal
	 */
	public String getTrmSttusVal() {
		return trmSttusVal;
	}
	/**
	 * @param trmSttusVal the trmSttusVal to set
	 */
	public void setTrmSttusVal(String trmSttusVal) {
		this.trmSttusVal = StringUtils.nvlStr(trmSttusVal);
	}
	/**
	 * @return the wrkPathVal
	 */
	public String getWrkPathVal() {
		return wrkPathVal;
	}
	/**
	 * @param wrkPathVal the wrkPathVal to set
	 */
	public void setWrkPathVal(String wrkPathVal) {
		this.wrkPathVal = StringUtils.nvlStr(wrkPathVal);
	}
	/**
	 * @return the apvSttusVal
	 */
	public String getApvSttusVal() {
		return apvSttusVal;
	}
	/**
	 * @param apvSttusVal the apvSttusVal to set
	 */
	public void setApvSttusVal(String apvSttusVal) {
		this.apvSttusVal = StringUtils.nvlStr(apvSttusVal);
	}
	/**
	 * @return the reviwrId
	 */
	public String getReviwrId() {
		return reviwrId;
	}
	/**
	 * @param reviwrId the reviwrId to set
	 */
	public void setReviwrId(String reviwrId) {
		this.reviwrId = StringUtils.nvlStr(reviwrId);
	}
	/**
	 * @return the reviwrNm
	 */
	public String getReviwrNm() {
		return reviwrNm;
	}
	/**
	 * @param reviwrNm the reviwrNm to set
	 */
	public void setReviwrNm(String reviwrNm) {
		this.reviwrNm = StringUtils.nvlStr(reviwrNm);
	}
	/**
	 * @return the reviewTimeNm
	 */
	public String getReviewTimeNm() {
		return reviewTimeNm;
	}
	/**
	 * @param reviewTimeNm the reviewTimeNm to set
	 */
	public void setReviewTimeNm(String reviewTimeNm) {
		this.reviewTimeNm = StringUtils.nvlStr(reviewTimeNm);
	}
	/**
	 * @return the calbkUrlTypeVal
	 */
	public String getCalbkUrlTypeVal() {
		return calbkUrlTypeVal;
	}
	/**
	 * @param calbkUrlTypeVal the calbkUrlTypeVal to set
	 */
	public void setCalbkUrlTypeVal(String calbkUrlTypeVal) {
		this.calbkUrlTypeVal = StringUtils.nvlStr(calbkUrlTypeVal);
	}
	/**
	 * @return the feaponCalbkUrlVal
	 */
	public String getFeaponCalbkUrlVal() {
		return feaponCalbkUrlVal;
	}
	/**
	 * @param feaponCalbkUrlVal the feaponCalbkUrlVal to set
	 */
	public void setFeaponCalbkUrlVal(String feaponCalbkUrlVal) {
		this.feaponCalbkUrlVal = StringUtils.nvlStr(feaponCalbkUrlVal);
	}
	/**
	 * @return the smphCalbkUrlVal
	 */
	public String getSmphCalbkUrlVal() {
		return smphCalbkUrlVal;
	}
	/**
	 * @param smphCalbkUrlVal the smphCalbkUrlVal to set
	 */
	public void setSmphCalbkUrlVal(String smphCalbkUrlVal) {
		this.smphCalbkUrlVal = StringUtils.nvlStr(smphCalbkUrlVal);
	}
	/**
	 * @return the calbkUrlMemoSbst
	 */
	public String getCalbkUrlMemoSbst() {
		return calbkUrlMemoSbst;
	}
	/**
	 * @param calbkUrlMemoSbst the calbkUrlMemoSbst to set
	 */
	public void setCalbkUrlMemoSbst(String calbkUrlMemoSbst) {
		this.calbkUrlMemoSbst = StringUtils.nvlStr(calbkUrlMemoSbst);
	}
	/**
	 * @return the calbkNoTypeVal
	 */
	public String getCalbkNoTypeVal() {
		return calbkNoTypeVal;
	}
	/**
	 * @param calbkNoTypeVal the calbkNoTypeVal to set
	 */
	public void setCalbkNoTypeVal(String calbkNoTypeVal) {
		this.calbkNoTypeVal = StringUtils.nvlStr(calbkNoTypeVal);
	}
	/**
	 * @return the feaponCalbkNo
	 */
	public String getFeaponCalbkNo() {
		return feaponCalbkNo;
	}
	/**
	 * @param feaponCalbkNo the feaponCalbkNo to set
	 */
	public void setFeaponCalbkNo(String feaponCalbkNo) {
		this.feaponCalbkNo = StringUtils.nvlStr(feaponCalbkNo);
	}
	/**
	 * @return the smphCalbkNo
	 */
	public String getSmphCalbkNo() {
		return smphCalbkNo;
	}
	/**
	 * @param smphCalbkNo the smphCalbkNo to set
	 */
	public void setSmphCalbkNo(String smphCalbkNo) {
		this.smphCalbkNo = StringUtils.nvlStr(smphCalbkNo);
	}
	
	/**
	 * @return the msgSbstHTML
	 */
	public String getMsgSbstHTML() {
		return msgSbstHTML;
	}
	/**
	 * @param msgSbstHTML the msgSbstHTML to set
	 */
	public void setMsgSbstHTML(String msgSbstHTML) {
		this.msgSbstHTML = StringUtils.nvlStr(msgSbstHTML);
	}
	/**
	 * @return the msgSbstTEXT
	 */
	public String getMsgSbstTEXT() {
		return msgSbstTEXT;
	}
	/**
	 * @param msgSbstTEXT the msgSbstTEXT to set
	 */
	public void setMsgSbstTEXT(String msgSbstTEXT) {
		this.msgSbstTEXT = StringUtils.nvlStr(msgSbstTEXT);
	}
	/**
	 * @return the arrMultiFileName
	 */
	public String getArrMultiFileName() {
		return arrMultiFileName;
	}
	/**
	 * @param arrMultiFileName the arrMultiFileName to set
	 */
	public void setArrMultiFileName(String arrMultiFileName) {
		this.arrMultiFileName = StringUtils.nvlStr(arrMultiFileName);
	}
	/**
	 * @return the arrMulti
	 */
	public String[] getArrMulti() {
		return arrMulti;
	}
	/**
	 * @param arrMulti the arrMulti to set
	 */
	public void setArrMulti(String[] arrMulti) {
		this.arrMulti = arrMulti;
	}
	/**
	 * @return the bannerUseVal
	 */
	public String getBannerUseVal() {
		return bannerUseVal;
	}
	/**
	 * @param bannerUseVal the bannerUseVal to set
	 */
	public void setBannerUseVal(String bannerUseVal) {
		this.bannerUseVal = StringUtils.nvlStr(bannerUseVal);
	}
	/**
	 * @return the rsrvHH
	 */
	public String getRsrvHH() {
		return rsrvHH;
	}
	/**
	 * @param rsrvHH the rsrvHH to set
	 */
	public void setRsrvHH(String rsrvHH) {
		this.rsrvHH = StringUtils.nvlStr(rsrvHH);
	}
	/**
	 * @return the nstepPrmsVal
	 */
	public String getNstepPrmsVal() {
		return nstepPrmsVal;
	}
	/**
	 * @param nstepPrmsVal the nstepPrmsVal to set
	 */
	public void setNstepPrmsVal(String nstepPrmsVal) {
		this.nstepPrmsVal = StringUtils.nvlStr(nstepPrmsVal);
	}

	
	
}