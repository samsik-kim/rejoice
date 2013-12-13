package com.omp.commons.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 문자열 처리 유틸리티
 * @author pat
 *
 */
public class StringUtil {
	
	private static final SimpleDateFormat	SDF	= new SimpleDateFormat("yyyyMMddHHmmss");
  
	/**
	 * 문자열(str)이 정해진 바이트(limit) 을 넘길때 잘라낸다.
	 * 정해진 바이트는 5 이상 입력해야 한다.
	 * 
	 * ex)    String tmp = StringUtil.cutString("ThisIsTestCase", 5);
	 *        System.out.println("TMP=" + tmp);
	 *        --------------------------------------------------------
	 *        ==> TMP=ThisI...      
	 * 
	 * 잘라내고 마지막에 ... 을 붙인다.
	 * @param str
	 * @param limit
	 * @return
	 * @deprecated 커스텀 태그의 format 이용
	 */
	public static String cutString(String str, int limit) {
		if (str == null || limit < 4)
			return str;

		int len = str.length();
		int cnt = 0, index = 0;

		while (index < len && cnt < limit) {
			if (str.charAt(index++) < 256)
				// 1바이트 문자라면...
				cnt++; // 길이 1 증가
			else {
				cnt += 2; // 길이 2 증가
			}
		}
		
		if (index < len && limit >= cnt)
			str = str.substring(0, index);
		else if (index < len && limit < cnt)
			str = str.substring(0, index - 1);
		
		return str + "...";
	}
	
	/**
	 * 문자열(str)이 정해진 바이트(limit) 을 넘길때 잘라낸다.
	 * 정해진 바이트는 5 이상 입력해야 한다.
	 * 
	 * ex)    String tmp = StringUtil.cutString("ThisIsTestCase", 5);
	 *        System.out.println("TMP=" + tmp);
	 *        --------------------------------------------------------
	 *        ==> TMP=ThisI...      
	 * 
	 * 잘라내기전 문자열(str)이 잘라낸 문자열(str)보다   
	 * 클경우엔 ...을 붙이고
	 * 작을경우엔  붙이지 않는다.
	 * @param str
	 * @param limit
	 * @return
	 * @deprecated 커스텀 태그의 format 이용
	 */
	public static String cutStringLimit(String str, int limit) {
		if (str == null || limit < 4)
			return str;

		int len = str.length();
		int cnt = 0, index = 0;
		
		while (index < len && cnt < limit) {
			if (str.charAt(index++) < 256)
				// 1바이트 문자라면...
				cnt++; // 길이 1 증가
			else {
				cnt += 2; // 길이 2 증가
			}
		}
		
		if (index < len && limit >= cnt)
			str = str.substring(0, index);
		else if (index < len && limit < cnt)
			str = str.substring(0, index - 1) ;
		
		
		if(len  > index){
			return str +"...";
		}else{
			return str;
		}
	}
	
	
	   /**
     * 문자열(str)이 정해진 바이트(limit) 을 넘길때 잘라낸다.
     * 정해진 바이트는 5 이상 입력해야 한다.
     * 
     * ex)    String tmp = StringUtil.cutString("ThisIsTestCase", 5);
     *        System.out.println("TMP=" + tmp);
     *        --------------------------------------------------------
     *        ==> TMP=ThisI...      
     * 
     * 잘라내기전 문자열(str)이 잘라낸 문자열(str)보다   
     * 클경우엔 ...을 붙이고
     * 작을경우엔  붙이지 않는다.
     * @param str
     * @param limit
     * @return
	 * @deprecated 커스텀 태그의 format 이용
     */
    public static String cutStringLimit(String str, int limit,String addStr) {
        if (str == null || limit < 4)
            return str;

        int len = str.length();
        int cnt = 0, index = 0;
        
        while (index < len && cnt < limit) {
            if (str.charAt(index++) < 256)
                // 1바이트 문자라면...
                cnt++; // 길이 1 증가
            else {
                cnt += 2; // 길이 2 증가
            }
        }
        
        if (index < len && limit >= cnt)
            str = str.substring(0, index);
        else if (index < len && limit < cnt)
            str = str.substring(0, index - 1) ;
        
        
        if(len  > index){
            return str + addStr;
        }else{
            return str;
        }
    }
    
    
    
    /**
     * Return stack of exception message by character at exception appearance.<br> 
     * --> printStackTrace() Return message displayed at method use by string.
     * 
     * Exception 객체를 파라메타에 넣으면 Exception Stack Trace 를 String 으로 반환한다.
     * 
     * @param e
     * @return exception  trace string
     */
    public static String getStackMessage(Exception e) {
//        StringBuffer sb = new StringBuffer();
//        sb.append("Class==>" + e.getClass() + "\n");
//        sb.append("Message==>" + e.getMessage()+ "\n");
//        
// 
//        StackTraceElement[] ste = e.getStackTrace();
//        for(int i=0;i<ste.length;i++){
//            sb.append("Trace==>" + i + "||" + ste[i].toString()+ "\n");
//        }
//        
//        return sb.toString();
    	StringWriter	sw;
    	
    	sw	= new StringWriter();
    	try {
    		e.printStackTrace(new PrintWriter(sw));
    	} finally {
    		try {
    			sw.close();
    		} catch (IOException ignore) {}
    	}
    	return sw.toString();
    }
    /**
     * 파일의 Full path 를 입력하면
     * 파일명을 뺀 나머지 경로만을 반환한다.
     * 
     * ex)  String tmp = StringUtil.extractPathName("E:\\temp\\ibatis_config\\sql\\CommCode_SqlMap.xml");
     *      System.out.println("TMP=" + tmp);
     *      ----------------------------------------------------------------
     *      ==> TMP=E:\temp\ibatis_config\sql
     *      
     * @param fileFullPath
     * @return path name
     * @deprecated new File(fileFullPath).getPath() 로 표현가능
     */
    public static String extractPathName(String fileFullPath){
//        String returnStr = null;
//        if(fileFullPath!=null && fileFullPath.length()>0){
//            String fileSep = System.getProperty("file.separator");
//            int pp = fileFullPath.lastIndexOf(fileSep);
//            if(pp!=-1){
//                returnStr = fileFullPath.substring(0, pp);
//            }else{
//                int pps = fileFullPath.lastIndexOf("/");
//                if(pps!=-1){
//                    returnStr = fileFullPath.substring(0, pps);
//                }
//            }
//                
//        }
        return new File(fileFullPath).getPath();
    }
    /**
     * 파일의 Full path 를 입력하면
     * 경로를 뺀 나머지 파일명만을 반환한다.
     * 
     * ex)  String tmp = StringUtil.extractPathName("E:\\temp\\ibatis_config\\sql\\CommCode_SqlMap.xml");
     *      System.out.println("TMP=" + tmp);
     *      ----------------------------------------------------------------
     *      ==> TMP=CommCode_SqlMap.xml
     *      
     * @param fileFullPath
     * @return file name
     * @deprecated new File(fileFullPath).getName() 로 표현가능
     */
    public static String extractFileName(String fileFullPath){
//        String returnStr = null;
//        if(fileFullPath!=null && fileFullPath.length()>0){
//            String fileSep = System.getProperty("file.separator");
//            int pp = fileFullPath.lastIndexOf(fileSep);
//            if(pp!=-1){
//                returnStr = fileFullPath.substring(pp+1);                
//            }else{
//                int pps = fileFullPath.lastIndexOf("/");
//                if(pps!=-1){
//                    returnStr = fileFullPath.substring(pps+1);                    
//                }
//            }
//                
//        }
        return new File(fileFullPath).getName();
    }
     /**
     * 입력받은 XML 에서 특정 태그로 둘러쌓은 값을 반환한다.
     *
     * ex)   String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>" +
     *                    "<sqlmap><simplexml>TestValue</simplexml> </sqlmap>";
     *       String tmp = StringUtil.extractTagValue(xml, "simplexml");
     *       System.out.println("TMP=" + tmp);
     *       --------------------------------------------------------------------------------
     *       ===> TMP=TestValue
     * 
     * 
     * @param tagName tag name
     * @return tag value
     * @deprecated
     */
    public static String extractTagValue(String xmlStr,String tagName){
        String preTag = "<" + tagName + ">";
        String postTag = "</" + tagName + ">";
        int preStr = xmlStr.indexOf(preTag);
        int postStr = xmlStr.indexOf(postTag);
        
        
        if (xmlStr.indexOf(preTag,preStr+1) != -1){  
            StringBuffer tagValueBuf = new StringBuffer();
            preStr = 0;
            postStr = 0;
          
            while (true ){
                preStr = xmlStr.indexOf(preTag,preStr);
                postStr = xmlStr.indexOf(postTag,postStr);
                 
                if (preStr==-1 || postStr==-1){
                    break;
                }
        
                String tagValue = xmlStr.substring(preStr + preTag.length() ,postStr).trim();
                
                preStr = postStr + postTag.length();
                postStr = preStr; 
                
                tagValueBuf.append(tagValue);
                tagValueBuf.append(";");
            }           
            String resultStr = tagValueBuf.toString();
            if(resultStr.length() > 0){
                resultStr = resultStr.substring(0,(resultStr.length()-1));     
            }
            return resultStr.trim();
        }else{           
            if (preStr==-1 || postStr==-1){
                return "";
            }else{
                String tagValue = xmlStr.substring(preStr + preTag.length() ,postStr).trim();
                
                return tagValue.trim();
            }
        }
    }      
    /**
     * 한자리로 된 숫자 앞에 "0" 을 붙여 스트링으로 반환한다.
     * ex) 9 -> "09"
     * @param digit number
     * @return attach "0" number
     * @deprecated 용도불명
     */
    public static String makeTwoDigit(int digit){
     
        String digitStr = new Integer(digit).toString();
        if(digitStr.length()==1){
            digitStr = "0" + digitStr;
        }
        return digitStr;
    }
    /**
     * 입력받은 스트링이 null 이거나 길이가 "0" 일때 공백문자 "" 로 반환하고
     * 아닐 경우에는 trim 을 해서 반환한다.
     * 
     * @param tmp string that trim
     * @return string
     */
    public static String setTrim(String tmp){
        if(tmp!= null && tmp.length()>0){
            tmp = tmp.trim();
        }else{
            tmp = "";
        }
        return tmp;
    }
    /**
     * 스트링으로 "Y" 나 "N" 을 입력받아 java 기본형인 boolean 으로 반환한다.
     * @param tmp "Y" or "N"
     * @return boolean
     * @deprecated "Y".equalsIgnoreCase(tmp) 로 표현가능
     */
    public static boolean getString2Boolean(String tmp){
//        boolean returnStr = false;
//        if(tmp!= null && tmp.length()>0){
//            tmp = tmp.trim();
//            if(tmp.equals("Y")){
//                returnStr = true;
//            }
//        }
        return "Y".equalsIgnoreCase(tmp);
    }
    /**
     * 
     * 스트링으로 정수를 입력받아 java 기본형은 int 로 반환한다.
     * @param tmp string
     * @return number
	 * @deprecated 문자열에 숫자가 아닌 글자가 있어도 0으로 반환됨
     */
    public static int getString2Int(String tmp){
        int returnStr = 0;
        if(tmp!= null && tmp.length()>0){
            tmp = tmp.trim();
            try{
                returnStr = Integer.parseInt(tmp);
            }catch(Exception e){
                
            }
        }
        return returnStr;
    }   
     

     
    /**
     * 현재 연도를 반환한다.
     * @return year
     * @deprecated 용도불명
     */
    public static String getNowYear(){
        Calendar cal = Calendar.getInstance();
        return new Integer(cal.get(Calendar.YEAR)).toString();
    }
    
    /**
     * 현재 월을 반환한다. 0 부터 시작이 아니고 1 부터 시작이라 +1 을 해서 리턴한다.
     * @return month
     */
    public static String getNowMonth(){
        Calendar cal = Calendar.getInstance();
        return StringUtil.makeTwoDigit(cal.get(Calendar.MONTH)+1);
    }
    /**
     * 현재 일자를 반환한다.
     * @return day
     */
    public static String getNowDay(){
        Calendar cal = Calendar.getInstance();
        return StringUtil.makeTwoDigit(cal.get(Calendar.DAY_OF_MONTH));
    }

    /**
     * 입력받은 스트링 문자열에 찾고자하는 문자열이 있는지 검색한다. 있는경우 true 를 반환한다.
     * @param fullText full text string
     * @param searchText search string
     * @return return exist search string
     * @deprecated fullText.indexof(searchText) != -1 로 대체
     */
    public static boolean isStringExist(String fullText,String searchText){
        boolean isExist = false;
        if(fullText!=null && fullText.length()>0){
            if(searchText!=null && searchText.length()>0){
                int indx = fullText.indexOf(searchText);
                if(indx!=-1){
                    isExist = true;
                }
            }
        }
        return isExist;
    }
 
    /**
     * 입력한 값이 "" 공백문자이거나 null 인 경우
     * 두번째 파라메타 값으로 초기화한다.
     *  
     * @param src original object
     * @param initStr initial string
     * @return init string
     */
    public static String nvlStr(Object src, String initStr)
    {
        if(src == null)
            return initStr ;
        else 
            return src.toString();
    }
    
	/**
	 * <pre>
	 * 입력한 값이 &quot;&quot; 공백문자이거나 null 인 경우
	 * 두번째 파라메타 값으로 초기화한다.
	 * ex)nvrStr:&quot;+StringUtils.nvlStr(null,&quot;0&quot;)
	 * > nvrStr: 0
	 * 
	 * </pre>
	 * &#064;param orgStr 원본 문자열
	 * &#064;param initStr 빈값 치환 문자열
	 * @return init string 치환한 결과
	 */
	public static String nvlStr(Object src) {
		return nvlStr(src, "");
	} 
    
    /**
     * 현재 시간을 반환한다.
     * 반환되는 포멧은 20090330144349 이런 형태로 반환된다.
     * @return a String in Date format
     * @deprecated SimpleDateFormat 이용
     */
    public static String getCurrentDate(){
//        Calendar cal = Calendar.getInstance();
//        StringBuffer sb = new StringBuffer();
//        String nResult;
//        
//        sb.append(cal.get(Calendar.YEAR));     
//        
//        if(cal.get(Calendar.MONTH) < 10){
//            sb.append(0);
//            sb.append(cal.get(Calendar.MONTH)+1);          
//        }
//        else{
//            sb.append(cal.get(Calendar.MONTH)+1);
//        }
//        
//        if(cal.get(Calendar.DAY_OF_MONTH) < 10){
//            sb.append(0);
//            sb.append(cal.get(Calendar.DAY_OF_MONTH));
//        }
//        else{
//            sb.append(cal.get(Calendar.DAY_OF_MONTH));
//        }
//        
//        
//        if(cal.get(Calendar.HOUR_OF_DAY) < 10){
//            sb.append(0);
//            sb.append(cal.get(Calendar.HOUR_OF_DAY));
//        }
//        else{
//            sb.append(cal.get(Calendar.HOUR_OF_DAY));
//        }
//        
//        if(cal.get(Calendar.MINUTE) < 10){
//            sb.append(0);
//            sb.append(cal.get(Calendar.MINUTE));           
//        }
//        else{
//            sb.append(cal.get(Calendar.MINUTE));
//        }
//            
//        if(cal.get(Calendar.SECOND) < 10){
//            sb.append(0);
//            sb.append(cal.get(Calendar.SECOND));
//        }
//        else{
//            sb.append(cal.get(Calendar.SECOND));
//        }
//        nResult = sb.toString();
//         
//        return nResult;
    	return SDF.format(new Date());
    }
    /**
     * 날짜를 입력받아 날짜 포멧을 특정한 형태로 바꿔준다.
     * 
     * 
     * ex)  String tmp = StringUtil.dateMask(StringUtil.getCurrentDate(), "yyyy/MM/dd");
     *      System.out.println("TMP=" + tmp);
     *      -----------------------------------------------------------------------
     *      TMP=2009/03/30
     *
     * @param str
     * @param mask
     * @return
     * @throws Exception
     * @deprecated java.text.SimpleDateFormat 이용
     */
    public static String dateMask(String str, String mask) throws Exception {
        if (str == null || str.length() == 0)
            return "";
        String pattern = "";
        switch (str.length()) {
            case 8:
                pattern = "yyyyMMdd";
                break;
            case 10:
                pattern = "yyyyMMddHH";
                break;
            case 12:
                pattern = "yyyyMMddHHmm";
                break;
            case 14:
                pattern = "yyyyMMddHHmmss";
                break;
        }

        SimpleDateFormat fmt = new SimpleDateFormat(pattern);
        java.util.Date d = fmt.parse(str);
        fmt.applyPattern(mask);
        return fmt.format(d);
    }
    /**
     * 
     * 입력받은 스트링 오른쪽에 스트링개수를 재외한 길이만큼 char 로 채운다.
     * 
     *  ex)  String tmp = StringUtil.RPAD("TEST", 10, '#');
     *       System.out.println("TMP=" + tmp);
     *       ---------------------------------------------------
     *       TMP=TEST######
     * 
     * @param str String
     * @param iLen 
     * @param cPad 
     * @return String
     * @deprecated 바이트연산으로 오동작 가능성 있슴, 바이트 변환시 케릭터셋에 대한 고려도 없슴 
     */
    public static String RPAD(String str, int iLen, char cPad) {
        String result = str;
        int iTempLen = iLen - result.getBytes().length;
        for (int i = 0; i < iTempLen; i++) {
            result = result + cPad;
        }
        return result;
    }
    
    /**
     * 
     * 입력받은 스트링 왼쪽에 스트링개수를 재외한 길이만큼 char 로 채운다.
     * 
     *  ex)  String tmp = StringUtil.LPAD("TEST", 10, '#');
     *       System.out.println("TMP=" + tmp);
     *       ---------------------------------------------------
     *       TMP=######TEST
     * 
     * @param str String
     * @param iLen 
     * @param cPad 
     * @return String 
     * @deprecated 바이트연산으로 오동작 가능성 있슴, 바이트 변환시 케릭터셋에 대한 고려도 없슴 
     */
    public static String LPAD(String str, int iLen, char cPad) {
        String result = str;
        int iTempLen = iLen - result.getBytes().length;
        for (int i = 0; i < iTempLen; i++)
            result = cPad + result;
        return result;
    }
    
    /**
     * 
     * <,>,\n,\t," " 를 HTML 로 반환한다.
     * 
     * 
     * @param str 
     * @return String 
     * @deprecated 커스텀 태그 이용 
     */
    public static String getHTML(String str) {
        String res = str;
        res = replace(res, "<", "&lt;");
        res = replace(res, ">", "&gt;");
        res = replace(res, "\n", "<br>");
        res = replace(res, "\t", "<tab>");
        res = replace(res, " ", "&nbsp;");
        return res;
    }
    
    /**
     * 입력받은 스트링 중에서 해당 문자열을 해당 단어로 치환한다.
     * 
     * ex)    String tmp = StringUtil.replace("<xml>", "<", "&lt;");
     *        System.out.println("TMP=" + tmp);
     *        ---------------------------------------------------------
     *        TMP=&lt;xml>
     *  
     * @param str
     * @param src
     * @param tgt
     * @return String
     * @deprecated 정규식이나 String.replaceAll() 이용
     */
    public static String replace(String str, String src, String tgt) {
        StringBuffer buf = new StringBuffer();
        String ch = null;
        if (str == null || str.length() == 0)
            return "";
        int i = 0;
        int len = src.length();
        while (i < str.length() - len + 1) {
            ch = str.substring(i, i + len);
            if (ch.equals(src)) {
                buf.append(tgt);
                i = i + len;
            } else {
                buf.append(str.substring(i, i + 1));
                i++;
            }
        }
        if (i < str.length())
            buf.append(str.substring(i, str.length()));
        return buf.toString();
    }
    
    /**
     * 
     * 입력받은 숫자 스트링을 원단위 "," 를 넣어서 반환한다.
     * 
     *      String tmp = StringUtil.makeMoneyType("123456789", ",");
     *      System.out.println("TMP=" + tmp);
     *      --------------------------------------------------------
     *      TMP=123,456,789
     * 
     * (예) 12345678 --> 12,345,678
     * 
     * 
     * @param str
     * @param del
     * @return String
     * @deprecated 숫자 타입의 DecimalFormat 를 이용하거나 커스텀 태그 이용 
     */
    public static String makeMoneyType(String str, String del) {
        DecimalFormat df = new DecimalFormat();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setGroupingSeparator(del.charAt(0));
        df.setGroupingSize(3);
        df.setDecimalFormatSymbols(dfs);
        return (df.format(Double.parseDouble(str))).toString();
    }


    /**
     * 
     * @param str
     * @return
     * @deprecated 숫자 타입의 DecimalFormat 를 이용하거나 커스텀 태그 이용 
     */
    public static String makeMoneyType(String str) {
    	return makeMoneyType(str, ",");
    }


    /**
     * 입력받은 숫자 정수형태를 원단위 "," 를 넣어서 반환한다.
     * 
     *      String tmp = StringUtil.makeMoneyType("123456789", ",");
     *      System.out.println("TMP=" + tmp);
     *      --------------------------------------------------------
     *      TMP=123,456,789
     * 
     * (예) 12345678 --> 12,345,678
     * 
     * @param iAmt
     * @param del
     * @return String
     * @deprecated 숫자 타입의 DecimalFormat 를 이용하거나 커스텀 태그 이용 
     */
    public static String makeMoneyType(int iAmt, String del) {
        return (makeMoneyType(Integer.toString(iAmt), del));
    }
    
   
    /**
     * 
     * 입력받은 원단위 스트링에서 특정 문자 "," 를 뺀다.
     * (예) 12,345,678 --> 12345678
     * 
     * 
     * @param str
     * @param del
     * @return String
     * @deprecated str.replaceAll("[^0-9\\.]", ""); 이용 
     */
    public static String makeNoMoneyType(String str, String del) {
//        StringTokenizer st = new StringTokenizer(str, del);
//        StringBuffer convert = new StringBuffer();
//        while (st.hasMoreTokens()) {
//            convert.append(st.nextToken());
//        }
//        return convert.toString();
    	return str.replaceAll("[^0-9\\.]", "");
    }    
    
    /**
     * 빈문자열 검사.
     *
     * @param input
     *            the input
     *
     * @return true, if checks if is empty
     */
    public static boolean isEmpty(String input) {
        return (input == null || input.trim().equals(""));
    }
    
    
    /**
     * KSC5601 로 변환
     * @param str
     * @return
     * @deprecated 용도 불명, IO용이라면 OutputStreamWriter 혹은 InputStreamWriter 이용
     */
    public static String asc2ksc(String str)
    {
        String result = null;
        if (str == null)
            return null;
            
        try
        {
            result = new String(str.getBytes("8859_1"), "KSC5601");
        }
        catch (UnsupportedEncodingException e)
        {
            result = new String(str);
        }
        return result;
    }
    
    
    /**
     * URF-8 로 변환
     * @param str
     * @return
     * @deprecated 용도 불명, IO용이라면 OutputStreamWriter 혹은 InputStreamWriter 이용
     */
    public static String asc2utf8(String str)
    {
        String result = null;
        if (str == null)
            return null;
            
        try
        {
            result = new String(str.getBytes("8859_1"), "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            result = new String(str);
        }
        return result;
    }
    
    /**
     * 
     * TODO String 반환
     * <P/>
     * Null 이면 "" 반환 하고 Null이 아니면 String으로 변환하여 Return
     *
     * @param obj
     * @return
     * @deprecated 네이밍 오류 nvlStr 로 변경
     * @see #nvlStr
     */
    public static String toString(Object obj){
        if( obj == null ) return "";
        else return obj.toString(); 
    }
    
	/**
	 * 보안 점검을 위해 <,>,",',&,%,!,-- 값 막기
	 * @param param
	 * @return
	 * @deprecated 커스텀 태그 이용.
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
		}
		
		return param;
	}
	
	/**
	 * 보안 점검을 위해 <,>,",',%,!,-- 값 막기 (url용)
	 * @param param
	 * @return
	 * @deprecated 커스텀 태그 이용.
	 */
	public static String  removeSpecial4Url(String param) {
		if(param==null) param="";
		else {			
			param = param.replaceAll("<", "");
			param = param.replaceAll(">", "");
			param = param.replaceAll("'", "");
			param = param.replaceAll("%", "");
			param = param.replaceAll("!", "");
			param = param.replaceAll("--", "");
			param = param.replaceAll("#", "");
			param = param.replaceAll("[/*]", "");
			param = param.replaceAll("[*/]", "");
		}
		
		return param;
	}
}
