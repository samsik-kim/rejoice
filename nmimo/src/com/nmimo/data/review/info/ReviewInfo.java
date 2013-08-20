package com.nmimo.data.review.info;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReviewInfo implements Serializable{
	
	/** 페이징 시작 번호 */
	private int startNum;
	/** 페이징 끝 번호 */
	private int endNum;
	/** */
	private int pageGroupSize = 10;
	/** 총글수 */
	private int totalCount;
	/** 검색 값 */
	private String searchStr;
	/** 검색 구분 */
	private String searchType;
	
	/**  작업아이디  */
	private String wrkId;
	/**  작업명  */
	private String wrkNm;
	/**  사용자명  */
	private String userNm;
	/**  사용자부서명  */
	private String userDeptNm;
	/**  메세지구분값  */
	private String msgDivVal;
	/**  메세지특성값  */
	private String msgCharVal;
	/**  메시지제목명  */
	private String msgTitleNm;
	/**  발송대상내용  */
	private String dspTgtSbst;
	/**  발송목적내용  */
	private String dspPrpsSbst;
	/**  기타정보내용  */
	private String etcInfoSbst;
	/**  bpm결재번호  */
	private String bpmSnctNo;
	/**  검토사항값  */
	private String reviewMtrVal;
	/**  발신번호  */
	private String otgoNo;
	/**  메세지내용  */
	private String msgSbst;
	/**  생성일시  */
	private String cretDt;
	/**  예약일시  */
	private String rsrvDt;
	/**  수정일시  */
	private String amdDt;
	/**  수정자아이디  */
	private String amdrId;
	/**  수정자부서명  */
	private String amdrDeptNm;
	/**  대상자파일명  */
	private String tgtrFileNm;
	/**  재전송여부  */
	private String rsndYn;
	/**  재전송횟수  */
	private String rsndTmscnt;
	/**  전송요청건수  */
	private String trmRqtCascnt;
	/**  실제전송건수  */
	private String realTrmCascnt;
	/**  파일중복건수  */
	private String fileDuplCascnt;
	/**  거부건수  */
	private String rfslCascnt;
	/**  SQL오류건수  */
	private String sqlErrCascnt;
	/**  일일중복건수  */
	private String dlyDuplCascnt;
	/**  전송상태값  */
	private String trmSttusVal;			
	/**  작업경로값  */
	private String wrkPathVal;			
	/**  승인상태값  */
	private String apvSttusVal;			
	/**  검토자아이디  */
	private String reviwrId;			
	/**  검토자명  */
	private String reviwrNm;			
	/**  검토시간명  */
	private String reviewTimeNm;			
	/**  콜백URL유형값  */
	private String calbkUrlTypeVal;			
	/**  피처폰콜백URL값  */
	private String feaponCalbkUrlVal;			
	/**  스마트폰콜백URL값  */
	private String smphCalbkUrlVal;			
	/**  콜백URL메모내용  */
	private String calbkNoTypeVal;			
	/**  콜백번호유형값  */
	private String feaponCalbkNo;			
	/**  피처폰콜백번호  */
	private String smphCalbkNo;			
	/**  스마트폰콜백번호  */
	private String calbkUrlMemoSbst;
	/**  생성자아이디  */
	private String cretrId;
	/**  예약시간  */
	private String rsrvHH;
	/**  배너사용 유무  */
	private String bannerUseVal;
	
	/**
	 * @return the startNum
	 */
	public int getStartNum() {
		return startNum;
	}
	/**
	 * @param startNum the startNum to set
	 */
	public void setStartNum(int startNum) {
		this.startNum = startNum;
	}
	/**
	 * @return the endNum
	 */
	public int getEndNum() {
		return endNum;
	}
	/**
	 * @param endNum the endNum to set
	 */
	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}
	/**
	 * @return the totalCount
	 */
	public int getTotalCount() {
		return totalCount;
	}
	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
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
		this.wrkId = wrkId;
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
		this.wrkNm = wrkNm;
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
		this.userNm = userNm;
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
		this.userDeptNm = userDeptNm;
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
		this.msgDivVal = msgDivVal;
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
		this.msgCharVal = msgCharVal;
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
		this.msgTitleNm = msgTitleNm;
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
		this.dspTgtSbst = dspTgtSbst;
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
		this.dspPrpsSbst = dspPrpsSbst;
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
		this.etcInfoSbst = etcInfoSbst;
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
		this.bpmSnctNo = bpmSnctNo;
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
		this.reviewMtrVal = reviewMtrVal;
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
		this.otgoNo = otgoNo;
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
		this.msgSbst = msgSbst;
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
		this.cretDt = cretDt;
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
		this.rsrvDt = rsrvDt;
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
		this.amdDt = amdDt;
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
		this.amdrId = amdrId;
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
		this.amdrDeptNm = amdrDeptNm;
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
		this.tgtrFileNm = tgtrFileNm;
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
		this.rsndYn = rsndYn;
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
	 * @return the trmRqtCascnt
	 */
	public String getTrmRqtCascnt() {
		return trmRqtCascnt;
	}
	/**
	 * @param trmRqtCascnt the trmRqtCascnt to set
	 */
	public void setTrmRqtCascnt(String trmRqtCascnt) {
		this.trmRqtCascnt = trmRqtCascnt;
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
		this.trmSttusVal = trmSttusVal;
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
		this.wrkPathVal = wrkPathVal;
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
		this.apvSttusVal = apvSttusVal;
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
		this.reviwrId = reviwrId;
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
		this.reviwrNm = reviwrNm;
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
		this.reviewTimeNm = reviewTimeNm;
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
		this.calbkUrlTypeVal = calbkUrlTypeVal;
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
		this.feaponCalbkUrlVal = feaponCalbkUrlVal;
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
		this.smphCalbkUrlVal = smphCalbkUrlVal;
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
		this.calbkNoTypeVal = calbkNoTypeVal;
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
		this.feaponCalbkNo = feaponCalbkNo;
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
		this.smphCalbkNo = smphCalbkNo;
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
		this.calbkUrlMemoSbst = calbkUrlMemoSbst;
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
		this.cretrId = cretrId;
	}
	/**
	 * @return the searchStr
	 */
	public String getSearchStr() {
		return searchStr;
	}
	/**
	 * @param searchStr the searchStr to set
	 */
	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}
	/**
	 * @return the pageGroupSize
	 */
	public int getPageGroupSize() {
		return pageGroupSize;
	}
	/**
	 * @param pageGroupSize the pageGroupSize to set
	 */
	public void setPageGroupSize(int pageGroupSize) {
		this.pageGroupSize = pageGroupSize;
	}
	/**
	 * @return the searchType
	 */
	public String getSearchType() {
		return searchType;
	}
	/**
	 * @param searchType the searchType to set
	 */
	public void setSearchType(String searchType) {
		this.searchType = searchType;
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
		this.rsrvHH = rsrvHH;
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
		this.bannerUseVal = bannerUseVal;
	}
	
	
}