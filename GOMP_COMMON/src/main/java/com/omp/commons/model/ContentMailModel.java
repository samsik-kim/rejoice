package com.omp.commons.model;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.omp.commons.util.DateUtil;
import com.omp.commons.util.config.ConfigProperties;

/**
 * 상품관련  메일 공통 정보 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class ContentMailModel implements Serializable {

	private String devEmailAddr; // 개발자 Email Address
	private String sendDt; // 발송일
	private String devUserId; // 개발자 ID
	private String prodNm; // 상품명
	private String ctReqDt; // 검증요청일
	private String ctEndDt; // 검증완료일
	private String agrmntStat; // 검증결과
	private String mainUrl; // 개발자 사이트 MainUrl
	private String oldSaleStat; // 이전 판매상태
	private String newSaleStat; // 변경될 판매상태
	private String saleStartDt; // 상품최초판매개시일
	private String updDttm; // 업데이트 일자

	/**
	 * default : yyyy. mm. dd
	 * 
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
		switch (dateType) {
			case 1:
				this.sendDt = DateUtil.toDisplayFormat(DateUtil.getSysDate(), ". ");
				break;

			case 2:
				this.sendDt = DateUtil.toDisplayFormat(DateUtil.getSysDate(), "-");
				break;

			case 3:
				this.sendDt = DateUtil.toDisplayFormat(DateUtil.getSysDate(), "/");
				break;

			default:
				this.sendDt = DateUtil.toDisplayFormat(DateUtil.getSysDate(), ". ");
				break;
		}
	}

	public String getDevUserId() {
		return devUserId;
	}

	public void setDevUserId(String devUserId) {
		this.devUserId = devUserId;
	}

	public String getProdNm() {
		return prodNm;
	}

	public void setProdNm(String prodNm) {
		this.prodNm = prodNm;
	}

	public String getCtReqDt() {
		return ctReqDt;
	}

	public void setCtReqDt(String ctReqDt) {
		this.ctReqDt = DateUtil.toDisplayFormat(ctReqDt.substring(0, 8), "-");
	}

	public String getCtEndDt() {
		return ctEndDt;
	}

	public void setCtEndDt(String ctEndDt) {
		this.ctEndDt = DateUtil.toDisplayFormat(ctEndDt.substring(0, 8), "-");
	}

	public String getAgrmntStat() {
		return agrmntStat;
	}

	public void setAgrmntStat(String agrmntStat) {
		this.agrmntStat = agrmntStat;
	}

	public String getMainUrl() {
		ConfigProperties conf = new ConfigProperties();
		return StringUtils.defaultIfEmpty(mainUrl, conf.getString("omp.common.url-prefix.http.dev") + "/devpoc/main/main.omp");
	}

	public void setMainUrl(String mainUrl) {
		this.mainUrl = mainUrl;
	}

	public String getOldSaleStat() {
		return oldSaleStat;
	}

	public void setOldSaleStat(String oldSaleStat) {
		this.oldSaleStat = oldSaleStat;
	}

	public String getNewSaleStat() {
		return newSaleStat;
	}

	public void setNewSaleStat(String newSaleStat) {
		this.newSaleStat = newSaleStat;
	}

	public String getSaleStartDt() {
		return saleStartDt;
	}

	public void setSaleStartDt(String saleStartDt) {
		this.saleStartDt = saleStartDt;
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

	public String getDevEmailAddr() {
		return devEmailAddr;
	}

	public void setDevEmailAddr(String devEmailAddr) {
		this.devEmailAddr = devEmailAddr;
	}

}
