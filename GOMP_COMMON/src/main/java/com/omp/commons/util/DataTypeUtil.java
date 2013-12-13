package com.omp.commons.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Map;

/**
 * 테이터 타입 처리 유틸리티
 * @author 양영열
 *
 */
public class DataTypeUtil {

    /**
     * Map에 들어 있는 값을 찾아 해당 값을 반환한다. 
     * 만약 해당 값이 NULL인 경우 “” 으로 변환하여 반환한다.
     * @param map
     * @param stringValue
     * @return
     * @throws Exception
     */
    public static String setObjToString( Map map, String stringValue ) throws Exception {
        String returnToString = new String();

        if (map.get(stringValue) == null) {
            returnToString = "";
        }
        else {
            returnToString = (String)map.get(stringValue);
        }

        return returnToString;
    }

   /**
    * 주어진 수만큼 자리수를 '0'으로 채워서 준다.
    * @param num 원래의 값 (int)
    * @param cnt '0'을 붙일 수
    * @return
    * @throws Exception
    */
    public static String getSeqValRule( int num, int cnt ) throws Exception {
        String strSeqVal = new Integer(num).toString();

        int iLength = strSeqVal.length();

        for (; iLength < cnt; iLength++) {
            strSeqVal = "0" + strSeqVal;
        }

        return strSeqVal;
    }

    /**
     * 주어진 수만큼 자리수를 '0'으로 채워서 준다.
     * @param val 원래의 값 (String)
     * @param cnt '0'을 붙일 수
     * @return
     * @throws Exception
     */
    public static String getSeqValRule2( String val, int cnt ) throws Exception {
        String strSeqVal = val;

        int iLength = strSeqVal.length();

        for (; iLength < cnt; iLength++) {
            strSeqVal = "0" + strSeqVal;
        }

        return strSeqVal;
    }

    /**
     * 금액문자열을 금액표시타입으로 변환한다. (예) 12345678 --> 12,345,678
     * 
     * @param moneyString
     * @return
     */
    public static String makeMoneyType( String moneyString ) {
        if (moneyString == null || moneyString.length() <= 0)
            return "0";

        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();

        dfs.setGroupingSeparator(',');
        df.setGroupingSize(3);
        df.setDecimalFormatSymbols(dfs);

        return (df.format(Double.parseDouble(moneyString))).toString();
    }

    /**
     * 금액문자열을 금액표시타입으로 변환한다. (예) 12345678 --> 12,345,678
     * 
     * @param money
     * @return
     */
    public static String makeMoneyType( int money ) {
        String moneyString = new Integer(money).toString();
        if (moneyString == null || moneyString.length() <= 0)
            return "0";

        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();

        dfs.setGroupingSeparator(',');
        df.setGroupingSize(3);
        df.setDecimalFormatSymbols(dfs);

        return (df.format(Double.parseDouble(moneyString))).toString();
    }

    /**
     * 일반유선전화번호를 넣어주면 해당 번호를 지역,국번,번호식으로 나누어서 배열로 만들어 return 
     * 예) 입력: 02-222-222 return [0]: 02, [1]:2222, [2]: 2222
     * @param phone
     * @return
     */
    public static String[] getPhoneArray( String phone ) {
        if (phone == null)
            return null;

        if (phone.indexOf("-") <= 0)
            return null;

        try {
            return phone.split("-");
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * 휴대폰번호를 넣어주면 해당 번호를 지역,국번,번호식으로 나누어서 배열로 만들어 return 예) 입력: 011-222-2222
     * return [0]: 011, [1]:2222, [2]: 2222
     * @param mdn
     * @return
     */
    public static String[] getMdnArray( String mdn ) {
        String[] strVal = new String[3];

        if (mdn == null || mdn.length() < 10)
            return null;

        try {
            strVal[0] = mdn.substring(0, 3);
            strVal[1] = mdn.substring(3, mdn.length() - 4);
            strVal[2] = mdn.substring(mdn.length() - 4, mdn.length());
        }
        catch (Exception e) {
            return null;
        }

        return strVal;
    }

    /**
     * 게시물의 내용을 필터링 한다.
     * 
     * @param content
     * @return
     */
    public static String filterHtml( String content ) {
        String result = null;
        if (content != null)
            result = content.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br>\r");

        return result;
    }

    /**
     * 게시물의 내용을 필터링 하고 원하는 줄만큼 자른다.
     * 
     * @param content
     * @return
     */
    public static String filterHtml( String content, int lineCnt ) {
        StringBuffer sb = new StringBuffer(content);
        String contents = sb.toString();
        int startIdx = 0;
        int cnt = 0;
        int endIdx = 0;

        String oldStr = "\r\n";
        String newStr = "<br>\n";

        try {

            while (cnt < lineCnt && (startIdx = contents.indexOf(oldStr, startIdx)) != -1) {
                cnt++;
                endIdx = startIdx + oldStr.length();
                sb.replace(startIdx, endIdx, newStr);
                startIdx += newStr.length();
                contents = sb.toString();
            }

        }
        catch (Exception e) {
            return null;
        }

        if (cnt == lineCnt)
            contents = contents.substring(0, endIdx - 2) + "...";

        return contents;
    }

    /**
     * 게시물의 내용을 필터링 하고 원하는 줄만큼 자른다. - WAP 용 
     * @param content
     * @return
     */
    public static String filterHtmlForWAP( String content, int lineCnt ) {
        if (lineCnt == 0) {
            String result = null;
            if (content != null)
                result = content.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\r\n", "<br/>\r");

            return result;

        }
        else {
            StringBuffer sb = new StringBuffer(content);
            String contents = sb.toString();
            int startIdx = 0;
            int cnt = 0;
            int endIdx = 0;

            String oldStr = "\r\n";
            String newStr = "<br/>\n";

            try {

                while (cnt < lineCnt && (startIdx = contents.indexOf(oldStr, startIdx)) != -1) {
                    cnt++;
                    endIdx = startIdx + oldStr.length();
                    sb.replace(startIdx, endIdx, newStr);
                    startIdx += newStr.length();
                    contents = sb.toString();
                }

            }
            catch (Exception e) {
                return null;
            }

            if (cnt == lineCnt)
                contents = contents.substring(0, endIdx - 2) + "...";

            return contents;
        }
    }

 
    /**
     * java의 replaceAll과 동일한 역할 문자열을 취환한다.
     * 
     * @param src 문자열
     * @param token 취환 소스 문자열
     * @param repl 취환 타겟 문자열
     * @return 'String'
     */
    public static String replaceAll( String src, String token, String repl ) {
        if (src == null)
            return null;
        String ret = "";
        int len = token.length();

        for (int i = 0; i < src.length(); i++) {
            if (len != 0 && ((i + len) <= src.length()) && (src.substring(i, i + len).equals(token))) {
                ret = ret + repl;
                i = i + len - 1;
            }
            else {
                ret = ret + src.substring(i, i + 1);
            }
        }

        return ret;
    }

    /**
     * 파일 확장자 부문만 가져온다.
     * 
     * @param src
     * @return
     */
    public static String getFileExtName( String src ) {
        if (src.indexOf(".") <= 0)
            return null;

        return src.substring(src.indexOf("."), src.length());
    }

    /**
     * 입력된 문자열이 UNICODE라 가정하고 해당 문자에 한글이 아닌 값이 입력되면 false를 return한다.
     * 
     * @param str
     * @return
     */
    public static boolean isKorean( String str ) {
        str = DataTypeUtil.replaceAll(str, " ", "");
        for (int i = 0; i < str.length(); i++) {
            System.out.println("str.charAt(i):" + (int)str.charAt(i));
            if ((str.charAt(i) > 55203 || str.charAt(i) < 44032)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Calendar 의 year, month, day 값으로 'YYYYMMDD' 의 String format 을 return 한다.
     * 
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static String getFormatDate( int year, int month, int day ) {
        String result = "";

        DecimalFormat f1 = new DecimalFormat("0000"); // year
        DecimalFormat f2 = new DecimalFormat("00"); // month, day

        result = f1.format(year) + f2.format(month) + f2.format(day);

        return result;
    }
    
    /**
     * Calendar 의 year, month, day, hour, minute, second 값으로 'YYYYMMDD' 의 String format 을 return 한다.
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static String getFormatDate( int year, int month, int day, int hour, int minute, int second ) {
        String result = "";

        DecimalFormat f1 = new DecimalFormat("0000"); // year
        DecimalFormat f2 = new DecimalFormat("00"); // month, day

        result = f1.format(year) + f2.format(month) + f2.format(day) + f2.format(hour) + f2.format(minute)
                + f2.format(second);

        return result;
    }
    
    
   

}
