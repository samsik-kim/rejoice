/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 4. 15.    Description
 *
 */
package com.omp.admin.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdSession;
import com.omp.admin.common.Constants;
import com.omp.admin.common.model.Paging;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;

/**
 * CommonUtil
 * 
 * @author redaano
 * @version 0.1
 */
@SuppressWarnings("serial")
public class CommonUtil extends BaseAction {

	@SuppressWarnings("unused")
	private static final GLogger logger = new GLogger(CommonUtil.class);

	/**
	 * 페이징 조회된 결과에서 totalCount 를 리턴
	 * 
	 * @param list
	 * @return
	 */
	@SuppressWarnings({ "rawtypes" })
	public static long getPagingTotalCount(List result) {
		if (result != null && result.size() > 0) {
			return ((Paging) result.get(0)).getTotalCount();
		} else {
			return 0;
		}
	}

	/**
	 * 기준년(flagYear) 부터 현재년+plusYear 를 담은 Map 을 리턴
	 * 
	 * @param flagYear
	 * @param plusYear
	 * @return
	 */
	public static Map<String, String> getYearMap(int flagYear, int plusYear) {
		Map<String, String> map = new LinkedHashMap<String, String>();
		int toYear = Integer.parseInt(DateUtil.getYYYY(new Date())) + plusYear;

		for (int i = flagYear; i < toYear; i++) {
			map.put("" + i, "" + i);
		}
		return map;
	}

	/**
	 * 01월 ~ 12월을 담은 Map 을 리턴
	 * 
	 * @return
	 */
	public static Map<String, String> getMonthMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (int i = 1; i <= 12; i++) {
			map.put(getMM("" + i), getMM("" + i));
		}
		return map;
	}

	/**
	 * 1-12월 -> 01.02,03....12월로 변환
	 * 
	 * @param mm
	 * @return
	 */
	public static String getMM(String mm) {
		if (mm.length() < 2) {
			mm = "0" + mm;
		}
		return mm;
	}

	/**
	 * Date 형식 날짜 받아 월(MM) 형식으로 반환한다.
	 * 
	 * @param Date
	 * @return String
	 */
	public static String getMonth(Date date) {
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		DateFormat df = new SimpleDateFormat("MM");
		return df.format(result.getTime());
	}

	/**
	 * Date 형식 날짜 받아 일(dd) 형식으로 반환한다.
	 * 
	 * @param Date
	 * @return String
	 */
	public static String getDay(Date date) {
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		DateFormat df = new SimpleDateFormat("dd");
		return df.format(result.getTime());
	}

	/**
	 * 보안 점검을 위해 <,>,",',&,%,!,-- 값 막기"
	 * @param param
	 * @return
	 */
	public static String removeSpecial(String param) {
		if (param == null)
			param = "";
		else {
			param = param.replaceAll("<", "");
			param = param.replaceAll(">", "");
			param = param.replaceAll("\"", "");
			param = param.replaceAll("'", "");
			param = param.replaceAll("&", "");
			param = param.replaceAll("%", "");
			param = param.replaceAll("!", "");
			param = param.replaceAll("--", "");
			param = param.replaceAll("#", "");
			param = param.replaceAll("\\/\\*", "");
			param = param.replaceAll("\\*\\/", "");
		}

		return param;
	}

	/**
	 * 보안 점검을 위해 <,>,",',%,!,-- 값 막기 (url용)
	 * @param param
	 * @return
	 */
	public static String removeSpecial4Url(String param) {
		if (param == null)
			param = "";
		else {
			param = param.replaceAll("<", "");
			param = param.replaceAll(">", "");
			param = param.replaceAll("\"", "");
			param = param.replaceAll("'", "");
			param = param.replaceAll("%", "");
			param = param.replaceAll("!", "");
			param = param.replaceAll("--", "");
			param = param.replaceAll("#", "");
			param = param.replaceAll("\\/\\*", "");
			param = param.replaceAll("\\*\\/", "");
		}

		return param;
	}

	/**
	 * null 처리
	 * 
	 * @param str
	 * @return
	 */
	public static String chkNull(String str) {
		return StringUtils.defaultString(str);
	}

	/**
	 * null 처리 and Trim
	 * 
	 * @param str
	 * @return
	 */
	public static String chkNullTrim(String str) {
		return StringUtils.defaultString(str).trim();
	}

	public static String getLoginId(HttpSession session) {
		String errMsg = "어드민 로그인 정보를 가져올 수 없습니다.";
		AdSession adSession = (AdSession) session.getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		if (adSession == null)
			throw new NoticeException(errMsg);

		AdMgr adMgr = adSession.getAdMgr();
		if (adMgr == null || StringUtils.isEmpty(adMgr.getMgrId()))
			throw new NoticeException(errMsg);
		return adMgr.getMgrId();
	}
	
	/**
	 * Login Info Get
	 * @param session
	 * @return
	 * @throws ServiceException
	 */
	public static AdMgr getAdMgr(HttpSession session) throws ServiceException {
		
		AdSession adSession = (AdSession) session.getAttribute(Constants.ADMIN_AUTH_SESSION_KEY);
		if (adSession == null)
			throw new NoticeException("어드민 로그인 정보를 가져올 수 없습니다.");

		AdMgr adMgr = adSession.getAdMgr();
		if (adMgr == null || StringUtils.isEmpty(adMgr.getMgrId()))
			throw new NoticeException("어드민 로그인 정보를 가져올 수 없습니다.");
		
		return adMgr;
	}	
}