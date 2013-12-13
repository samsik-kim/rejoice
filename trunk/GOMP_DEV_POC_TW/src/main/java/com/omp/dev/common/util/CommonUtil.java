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
package com.omp.dev.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.omp.commons.action.BaseAction;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.dev.common.model.Paging;

/**
 * CommonUtil
 * 
 * @author redaano
 * @version 0.1
 */
@SuppressWarnings("serial")
public class CommonUtil extends BaseAction {
    @SuppressWarnings("unused")
    private static GLogger logger = new GLogger(CommonUtil.class);


    /**
     * 페이징 조회된 결과에서 totalCount 를 리턴
     * 
     * @param list
     * @return
     */
    @SuppressWarnings("unchecked")
    public static long getPagingTotalCount(List result) {
        if(result != null && result.size() > 0) {
            return ((Paging)result.get(0)).getTotalCount();
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

        for(int i = flagYear; i < toYear; i++) {
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
        for(int i = 1; i <= 12; i++) {
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
        if(mm.length() < 2) {
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
     * 결제 페이지 한글 깨짐현상 방지를 위한 Decoding 함수
     * @param param
     * @return
     */
    public static String  fncDecode( String param ) {
        StringBuffer sb = new StringBuffer();
        int pos = 0;
        boolean flg = true;

        if (param != null) {
            if (param.length() > 1) {
                while (flg) {
                    String sLen = param.substring(pos, ++pos);
                    int nLen = 0;

                    try {
                        nLen = Integer.parseInt(sLen);
                    }
                    catch (Exception e) {
                        nLen = 0;
                    }

                    String code = "";
                    if ((pos + nLen) > param.length())
                        code = param.substring(pos);
                    else
                        code = param.substring(pos, (pos + nLen));

                    pos += nLen;

                    sb.append(((char)Integer.parseInt(code)));

                    if (pos >= param.length())
                        flg = false;
                }
            }
        }
        else {
            param = "";
        }

        return sb.toString();
    }
    
    
    /**
     * 보안 점검을 위해 <,>,",',&,%,!,-- 값 막기"
     * @param param
     * @return
     */
    public static String  removeSpecial(String param) {
        if(param==null) param="";
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
            param = param.replaceAll("[/*]", "");
            param = param.replaceAll("[*/]", "");
            param = param.replace(Character.toString(Character.toChars(46824)[0]), "");
        }
        
        return param;
    }
    /**
     * 보안 점검을 위해 <,>,",',&,%,!,-- 값 막기"
     * @param param
     * @return
     */
    public static String  removeSpecialTwo(String param) {
        if(param==null) param="";
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
			param = param.replace(Character.toString(Character.toChars(46824)[0]), "");
        }
        
        return param;
    }
    
    
    
}