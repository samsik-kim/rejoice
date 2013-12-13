package com.omp.admin.community.qna.model;

import com.omp.commons.model.CommonModel;
import com.omp.commons.model.PagenateInfoModel;
import com.omp.commons.model.Pagenateable;
import com.omp.commons.model.TotalCountCarriable;


@SuppressWarnings("serial")
public class QnA extends CommonModel implements Pagenateable, TotalCountCarriable
{
	private PagenateInfoModel	page		= new PagenateInfoModel();
	private Long    totalCount;
	private String rnum;
	private int	   queNo = 0;     	
	private int    rowCnt = 0;		
	private String qnaTpCd = "";   	
	private String qnaClsCd = "";  	
	private String qnaClsNm = "";  	
	private String queId = "";     	
	private String queNm = "";     	
	private String emailAddr = ""; 	
	private String queTitle = "";  	
	private String queDscr = "";   	
	private String prcStatCd = ""; 	
	private String prcStatNm = ""; 	
	private String delYn = "";     	
	private String regDt = "";     	
	private String delDt = "";     	
	private String prcOpId = "";   	
	private String prcDscr = "";   	
	private String prcCompDt = ""; 	
	private String hitCnt = "";    	
	private String prodId = "";    	
	private String devMbrNo = "";  	
	private String ansNo = "";     	
	private String ansTitle = "";  	
	private String ansDscr = "";   	
	private String ansId = "";     	
	private String ansRegDt = "";	
	private int	   delayCnt = 0;	
	private String ctgrCd = "";		
	private String displayOd = "";		
	private String ctgrLevel = "";		
	
	private String ctgrNm = "";		
	private String keyword = "";	
	private String highCtgr = "";	
	private String highCtgrNm = "";	
	
	private String keytype = "title";	
	private String dateType = "";	
	private String rcptDt = "";		
	
	private String filePath = "";		
	private String fileName = "";	
	private String fileReal = "";		
	
	private String qnaClsCd2 = "";		
	private String qnaClsNm2 = "";			
	private String qnaCd = "";		

	private String hpNo= "";
	private String hpModel = "";
	private String attFile = "";
	private String attFileNm = "";
	private String contentNm = "";
	private String contentErrDttm = "";
	private String contentErrChannel = "";
	
	@Override
	public Long getTotalCount() {
		return this.totalCount;
	}

	@Override
	public void setTotalCount(Long totalCount) {
		this.totalCount	= totalCount;
	}
	
	@Override
    public PagenateInfoModel getPage() {
        return this.page;
    }
	public String getRnum() {
		return rnum;
	}

	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getDisplayOd() {
		return displayOd;
	}

	public void setDisplayOd(String displayOd) {
		this.displayOd = displayOd;
	}

	public String getCtgrLevel() {
		return ctgrLevel;
	}

	public void setCtgrLevel(String ctgrLevel) {
		this.ctgrLevel = ctgrLevel;
	}
	
	public int getQueNo() {
		return queNo;
	}
	public void setQueNo(int queNo) {
		this.queNo = queNo;
	}

	public String getQnaTpCd() {
		return qnaTpCd;
	}
	public void setQnaTpCd(String qnaTpCd) {
		this.qnaTpCd = qnaTpCd;
	}

	public String getQnaClsCd() {
		return qnaClsCd;
	}
	public void setQnaClsCd(String qnaClsCd) {
		this.qnaClsCd = qnaClsCd;
	}

	public String getQnaClsNm() {
		return qnaClsNm;
	}
	public void setQnaClsNm(String qnaClsNm) {
		this.qnaClsNm = qnaClsNm;
	}

	public String getQueId() {
		return queId;
	}
	public void setQueId(String queId) {
		this.queId = queId;
	}

	public String getQueNm() {
		return queNm;
	}
	public void setQueNm(String queNm) {
		this.queNm = queNm;
	}

	public String getEmailAddr() {
		return emailAddr;
	}
	public void setEmailAddr(String emailAddr) {
		this.emailAddr = emailAddr;
	}

	public String getQueTitle() {
		return queTitle;
	}
	public void setQueTitle(String queTitle) {
		this.queTitle = queTitle;
	}

	public String getQueDscr() {
		return queDscr;
	}
	public void setQueDscr(String queDscr) {
		this.queDscr = queDscr;
	}

	public String getPrcStatCd() {
		return prcStatCd;
	}
	public void setPrcStatCd(String prcStatCd) {
		this.prcStatCd = prcStatCd;
	}

	public String getPrcStatNm() {
		return prcStatNm;
	}
	public void setPrcStatNm(String prcStatNm) {
		this.prcStatNm = prcStatNm;
	}

	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getRegDt() {
		return regDt;
	}
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getDelDt() {
		return delDt;
	}
	public void setDelDt(String delDt) {
		this.delDt = delDt;
	}

	public String getPrcOpId() {
		return prcOpId;
	}
	public void setPrcOpId(String prcOpId) {
		this.prcOpId = prcOpId;
	}

	public String getPrcDscr() {
		return prcDscr;
	}
	public void setPrcDscr(String prcDscr) {
		this.prcDscr = prcDscr;
	}

	public String getPrcCompDt() {
		return prcCompDt;
	}
	public void setPrcCompDt(String prcCompDt) {
		this.prcCompDt = prcCompDt;
	}

	public String getHitCnt() {
		return hitCnt;
	}
	public void setHitCnt(String hitCnt) {
		this.hitCnt = hitCnt;
	}

	public String getProdId() {
		return prodId;
	}
	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getDevMbrNo() {
		return devMbrNo;
	}
	public void setDevMbrNo(String devMbrNo) {
		this.devMbrNo = devMbrNo;
	}

	public String getAnsNo() {
		return ansNo;
	}
	public void setAnsNo(String ansNo) {
		this.ansNo = ansNo;
	}

	public String getAnsTitle() {
		return ansTitle;
	}
	public void setAnsTitle(String ansTitle) {
		this.ansTitle = ansTitle;
	}

	public String getAnsDscr() {
		return ansDscr;
	}
	public void setAnsDscr(String ansDscr) {
		this.ansDscr = ansDscr;
	}

	public String getAnsId() {
		return ansId;
	}
	public void setAnsId(String ansId) {
		this.ansId = ansId;
	}

	public String getAnsRegDt() {
		return ansRegDt;
	}
	public void setAnsRegDt(String ansRegDt) {
		this.ansRegDt = ansRegDt;
	}
	
	public int getDelayCnt() {
		return delayCnt;
	}
	public void setDelayCnt(int delayCnt) {
		this.delayCnt = delayCnt;
	}
	
	public int getRowCnt() {
		return rowCnt;
	}
	public void setRowCnt(int rowCnt) {
		this.rowCnt = rowCnt;
	}

	public String getCtgrCd() {
		return ctgrCd;
	}
	public void setCtgrCd(String ctgrCd) {
		this.ctgrCd = ctgrCd;
	}
	
	public String getCtgrNm() {
		return ctgrNm;
	}
	public void setCtgrNm(String ctgrNm) {
		this.ctgrNm = ctgrNm;
	}

	public String getHighCtgr() {
		return highCtgr;
	}
	public void setHighCtgr(String highCtgr) {
		this.highCtgr = highCtgr;
	}

	public String getHighCtgrNm() {
		return highCtgrNm;
	}
	public void setHighCtgrNm(String highCtgrNm) {
		this.highCtgrNm = highCtgrNm;
	}

	public String getKeytype() {
		return keytype;
	}
	public void setKeytype(String keytype) {
		this.keytype = keytype;
	}

	public String getRcptDt() {
		return rcptDt;
	}
	public void setRcptDt(String rcptDt) {
		this.rcptDt = rcptDt;
	}

	public String getDateType() {
		return dateType;
	}
	public void setDateType(String dateType) {
		this.dateType = dateType;
	}
	
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public String getFileReal() {
		return fileReal;
	}
	public void setFileReal(String fileReal) {
		this.fileReal = fileReal;
	}
	
	public String getQnaClsCd2() {
		return qnaClsCd2;
	}
	public void setQnaClsCd2(String qnaClsCd2) {
		this.qnaClsCd2 = qnaClsCd2;
	}

	public String getQnaClsNm2() {
		return qnaClsNm2;
	}
	public void setQnaClsNm2(String qnaClsNm2) {
		this.qnaClsNm2 = qnaClsNm2;
	}
	
	public String getQnaCd() {
		return qnaCd;
	}
	public void setQnaCd(String qnaCd) {
		this.qnaCd = qnaCd;
	}
	
	public String getHpNo() {
		return hpNo;
	}
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	public String getHpModel() {
		return hpModel;
	}
	public void setHpModel(String hpModel) {
		this.hpModel = hpModel;
	}
	public String getAttFile() {
		return attFile;
	}
	public void setAttFile(String attFile) {
		this.attFile = attFile;
	}
	public String getAttFileNm() {
		return attFileNm;
	}
	public void setAttFileNm(String attFileNm) {
		this.attFileNm = attFileNm;
	}
	public String getContentNm() {
		return contentNm;
	}
	public void setContentNm(String contentNm) {
		this.contentNm = contentNm;
	}
	public String getContentErrDttm() {
		return contentErrDttm;
	}
	public void setContentErrDttm(String contentErrDttm) {
		this.contentErrDttm = contentErrDttm;
	}
	public String getContentErrChannel() {
		return contentErrChannel;
	}
	public void setContentErrChannel(String contentErrChannel) {
		this.contentErrChannel = contentErrChannel;
	}
	
}
