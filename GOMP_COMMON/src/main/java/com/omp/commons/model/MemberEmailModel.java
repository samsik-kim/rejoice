package com.omp.commons.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.omp.commons.Constants;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.config.ConfigProperties;

/**
 * 회원 정보 관련 메일 처리용 공통 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class MemberEmailModel implements Serializable{
	
	private String mainUrl;			//메인 URL
	private String returnUrl;		//리턴(인증) URL
	private String reqDt;			//신청일
	private String endDt;			//완료일
	private String rejDt;			//거절일
	private String sendDt;			//발송일
	private String rejDesc; 		//거절내용
	private String tempPw;			//임시비밀번호
	private String email;			//이메일
	private String cardInfo;		//카드정보
	private String payDt;			//결제일
	private String payAmt;			//결제금액
	private String mbrId;			//회원 아이디
	
	//QnA
	private String writer;			// 작성자
	private String title;			// 제목
	private String dscr;			// 내용
	
	private static ConfigProperties conf = new ConfigProperties();
	
	/**
	 * @return the mainUrl
	 */
	public String getMainUrl() {
		return mainUrl = StringUtils.isNotEmpty(this.mainUrl) ? this.mainUrl : conf.getString("omp.common.url-prefix.http.dev") + conf.getString("omp.common.path.context.dev") + Constants.DEV_MAIN_URL;
	}
	
	/**
	 * @param mainUrl the mainUrl to set
	 */
	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}
	/**
	 * @return the returnUrl
	 */
	public String getReturnUrl() {
		return returnUrl;
	}
	/**
	 * @param returnUrl the returnUrl to set
	 */
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	/**
	 * @return the reqDt
	 */
	public String getReqDt() {
		return reqDt;
	}
	/**
	 * default : yyyy-mm-dd
	 * @param reqDt the reqDt to set
	 */
	public void setReqDt(String reqDt) {
		this.reqDt = DateUtil.toDisplayFormat(reqDt.substring(0, 8), "-");
	}
	/**
	 * @return the endDt
	 */
	public String getEndDt() {
		return endDt;
	}
	/**
	 * default : yyyy-mm-dd
	 * @param endDt the endDt to set
	 */
	public void setEndDt(String endDt) {
		this.endDt = DateUtil.toDisplayFormat(endDt.substring(0, 8), "-");
	}
	/**
	 * @return the rejDt
	 */
	public String getRejDt() {
		return rejDt;
	}
	/**
	 * default : yyyy-mm-dd
	 * @param rejDt the rejDt to set
	 */
	public void setRejDt(String rejDt) {
		this.rejDt = DateUtil.toDisplayFormat(rejDt.substring(0, 8), "-");
	}
	
	/**
	 * default : yyyy. mm. dd
	 * @return the sendDt
	 */
	public String getSendDt() {
		return sendDt = StringUtils.isNotEmpty(this.sendDt) ? this.sendDt : DateUtil.toDisplayFormat(DateUtil.getSysDate(), ". ");
	}
	/**
	 * @param dateType 1 : yyyy. mm. dd
	 * @param dateType 2 : yyyy-mm-dd
	 * @param dateType 3 : yyyy/mm/dd
	 * @param etc : yyyy. mm. dd
	 */
	public void setSendDt(int dateType) {
		switch(dateType) {
			case 1 : this.sendDt  = DateUtil.toDisplayFormat(DateUtil.getSysDate(), ". "); break;
		
			case 2 : this.sendDt  = DateUtil.toDisplayFormat(DateUtil.getSysDate(), "-"); break;
			
			case 3 : this.sendDt  = DateUtil.toDisplayFormat(DateUtil.getSysDate(), "/"); break;
			
			default : this.sendDt  = DateUtil.toDisplayFormat(DateUtil.getSysDate(), ". "); break;
		}
	}
	/**
	 * @return the rejDesc
	 */
	public String getRejDesc() {
		return rejDesc;
	}
	/**
	 * @param rejDesc the rejDesc to set
	 */
	public void setRejDesc(String rejDesc) {
		this.rejDesc = rejDesc;
	}
	/**
	 * @return the tempPw
	 */
	public String getTempPw() {
		return tempPw;
	}
	/**
	 * @param tempPw the tempPw to set
	 */
	public void setTempPw(String tempPw) {
		this.tempPw = tempPw;
	}
	/**
	 * @return the cardInfo
	 */
	public String getCardInfo() {
		return cardInfo;
	}
	/**
	 * @param cardInfo the cardInfo to set
	 */
	public void setCardInfo(String cardInfo) {
		this.cardInfo = cardInfo;
	}
	/**
	 * @return the payDt
	 */
	public String getPayDt() {
		return payDt;
	}
	/**
	 * default : yyyy-mm-dd
	 * @param payDt the payDt to set
	 */
	public void setPayDt(String payDt) {
		this.payDt = DateUtil.toDisplayFormat(payDt.substring(0, 8), "-");
	}
	/**
	 * @return the payAmt
	 */
	public String getPayAmt() {
		return payAmt;
	}
	/**
	 * @param payAmt the payAmt to set
	 */
	public void setPayAmt(String payAmt) {
		this.payAmt = payAmt;
	}
	/**
	 * @return the mbrId
	 */
	public String getMbrId() {
		return mbrId;
	}
	/**
	 * @param mbrId the mbrId to set
	 */
	public void setMbrId(String mbrId) {
		this.mbrId = mbrId;
	}
	
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the writer
	 */
	public String getWriter() {
		return writer;
	}

	/**
	 * @param writer the writer to set
	 */
	public void setWriter(String writer) {
		this.writer = writer;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the dscr
	 */
	public String getDscr() {
		return dscr;
	}

	/**
	 * @param dscr the dscr to set
	 */
	public void setDscr(String dscr) {
		this.dscr = dscr;
	}

	public static void main(String[] args) {
		MemberEmailModel me = new MemberEmailModel();
		//me.setSendDt("20110408142324", 2);
		//System.out.println(me.getSendDt());
		String str = "20101104174154";
		//me.setReqDt(str);
		//System.out.println(me.getSendDt());
		//me.setSendDt(2);
		//String str1 = DateUtil.toDisplayFormat(DateUtil.getSysDate());
		//str1 = str1.replaceAll("/", "");
		//me.setReqDt(str1);
		me.setReqDt(DateUtil.toDisplayFormat(DateUtil.getSysDate()));
		System.out.println(DateUtil.getSysDate());
		System.out.println(me.getReqDt());
		//System.out.println(DateUtil.getDateStr(DateUtil.getDateWithDelimiter("2011-04-08")));
	}
	
}
