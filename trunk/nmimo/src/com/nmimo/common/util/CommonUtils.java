package com.nmimo.common.util;

import javax.servlet.http.HttpServletRequest;

public class CommonUtils {

	/**
	 * 주어진 문자열에서 a 태그를 제외한 태그를 모두 제거한다.
	 * 
	 * @param str 원본 문자열
	 * @param replacement 대체할 문자열
	 * @return
	 */
	public static String replaceAllTagsExceptA(String str, String replacement) {
		str = org.apache.commons.lang.StringUtils.trimToEmpty(str);
		replacement = org.apache.commons.lang.StringUtils.trimToEmpty(replacement);
		// /aA 또는 aA가 아닌 것으로 시작하는 모든 태그
		return str.replaceAll("<(?!(/[aA])|([aA])).([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", replacement);
	}

	/**
	 * 주어진 문자열에서 태그를 모두 제거한다.
	 * 
	 * @param str 원본 문자열
	 * @param replacement 대체할 문자열
	 * @return
	 */
	public static String replaceAllTagsExcept(String str, String replacement) {
		str = org.apache.commons.lang.StringUtils.trimToEmpty(str);
		replacement = org.apache.commons.lang.StringUtils.trimToEmpty(replacement);
		// 모든 태그
		return str.replaceAll("<?([a-zA-Z]*).([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", replacement);

	}
	
	/**
	 * <pre>
	 * 파일 확장자 추출
	 * </pre>
	 * @param fileFullPath
	 * @return
	 */
	public final static String getExt(String fileFullPath){
		
		String tempExt = "";
		if (fileFullPath.indexOf(".") >= 0) {
			tempExt = fileFullPath.substring(fileFullPath.lastIndexOf(".") + 1);
			tempExt = tempExt.toLowerCase(); 
		}else{
			return "확장자오류";
		}
		
		return tempExt;
	}
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param request
	 * @return
	 */
	public static String returnUri(HttpServletRequest request){
		
		String contextPath = request.getContextPath();
		
		String uriInfo = request.getRequestURI();
		if(contextPath!=null && uriInfo!=null && uriInfo.length()>contextPath.length()){
			String contextStr = uriInfo.substring(0,contextPath.length());
			if(contextPath.equals(contextStr)){
				uriInfo = uriInfo.substring(contextPath.length(), uriInfo.length());
			}
		}

		int k = 0;
		String andstring = "";
		String paramdata = "";
		
		for (java.util.Enumeration hparam = request.getParameterNames(); hparam
			.hasMoreElements(); k++) {
			String paramname = (String) hparam.nextElement();
			String[] paramvalue = request.getParameterValues(paramname);
			if (k > 0) {
				andstring = "&";
			} else {
				andstring = "?";
			}
			
			for (int i = 0; i < paramvalue.length; i++) {
					if (i > 0) {
							andstring = "&";
					}
					paramdata += andstring + paramname + "=" + paramvalue[i];
			}
		}
		
		String returi = uriInfo + paramdata;
		
		return returi;
	}
	
	public static void main(String[] args) {
		String a = "<a href='mailto:asdasdasd@gmail.com'>asdasdasd@gmail.com</a><html><head><script>aaaa</script></head><body><div>aaa</div> <div> <script></script></div><img src=\"http://tong.nate.com\" values=\">\"> 이건 어떻게 될까요 <!-- zmzm --></body></html>";
		System.out.println(CommonUtils.replaceAllTagsExcept(a, null));
	}
}