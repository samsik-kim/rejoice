/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author       Date            Description
 * --------     ----------      ------------------
 * redaano      2009. 4. 15.    Description
 * nefer        2009.12.08      move to omp_common
 */
package com.omp.commons.util;

import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;

import com.omp.commons.action.BaseAction;

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
	 * 결제 페이지 한글 깨짐현상 방지를 위한 Decoding 함수
	 * 
	 * @param param
	 * @return
	 */
	public static String fncDecode(String param) {
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
					} catch (Exception e) {
						nLen = 0;
					}

					String code = "";
					if ((pos + nLen) > param.length())
						code = param.substring(pos);
					else
						code = param.substring(pos, (pos + nLen));

					pos += nLen;

					sb.append(((char) Integer.parseInt(code)));

					if (pos >= param.length())
						flg = false;
				}
			}
		} else {
			param = "";
		}

		return sb.toString();
	}

	/**
	 * 보안 점검을 위해 <,>,",',&,%,!,-- 값 막기"
	 * 
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
			param = param.replaceAll("[/*]", "");
			param = param.replaceAll("[*/]", "");
			param = param.replace(Character.toString(Character.toChars(49824)[0]), " ");
		}

		return param;
	}

	/**
	 * 대한민국 주민번호 체계를 이용해 현재 연도에 해당하는 만 나이를 추출해 냅니다.
	 * 
	 * @author 김영환
	 * @param socialNum1 주민번호 앞자리
	 * @param socialNum2 주민번호 뒷자리
	 * @return 만 나이
	 */
	public static int getAgeBySocalNumber(String socialNum1, String socialNum2) {
		java.util.Calendar cal = java.util.Calendar.getInstance();
		int targetYear = cal.get(cal.YEAR);
		cal = null;
		return getAgeBySocalNumber(socialNum1, socialNum2, targetYear);
	}

	/**
	 * 대한민국 주민번호 체계를 이용해 지정한 연도에 해당하는 만 나이를 추출해 냅니다.
	 * 
	 * @author 김영환
	 * @param socialNum1 주민번호 앞자리
	 * @param socialNum2 주민번호 뒷자리
	 * @param targetYear 지정 연도
	 * @return 만 나이
	 */
	public static int getAgeBySocalNumber(String socialNum1, String socialNum2, int targetYear) {
		if (StringUtil.isEmpty(socialNum1))
			return 0;
		if (StringUtil.isEmpty(socialNum2))
			return 0;

		int age = 0;
		int birth = Integer.parseInt(socialNum1.substring(0, 2));
		int sexPerfix = Integer.parseInt(socialNum2.substring(0, 1));
		int yearMultiplication = 0;

		/**
		 * 주민번호 뒷자리 첫번째 숫자 부여 체계 짝수 여성, 홀수 남성 구분 1800년도생 1900년도생 2000년도생 내국인 9, 0 1, 2 3, 4 외국인 5, 6 7, 8
		 */
		switch (sexPerfix) {
			case 1:
			case 2:
			case 5:
			case 6:
				yearMultiplication = 0;
				break;
			case 3:
			case 4:
			case 7:
			case 8:
				yearMultiplication = 1;
				break;
			case 9:
			case 0:
				yearMultiplication = -1;
				break;
		}
		System.out.println("yearMultiplication : " + yearMultiplication);
		age = targetYear - (1900 + birth + (yearMultiplication * 100));

		// 2011.01.17 jipark add - 월/일까지 계산하여 나이 계산하도록 수정함.
		String curMMDD = DateUtil.getCurrentDate().substring(4);
		String userMMDD = socialNum1.substring(2);

		// 만나이로 할려면 생일이 지나지 않은 경우는  한살 빼줘야 하지만,  
		// 현재  18세 체크하는 로직에   if( age > 18) : 이런식으로 되어 있어 19로 리턴해줘야 성인인증됨!
		// 즉, 만나이 아니고 우리나라 나이임.
		if (Integer.parseInt(userMMDD) > Integer.parseInt(curMMDD)) {
			//age--;
		} else {
			age++;
		}

		return age;
	}

	/**
	 * HashMap 객체에 전달된 클래스 객체의 속성을(get 프로퍼티 함수타입만 적용) 가져와 추가한다.<br />
	 * 성능이 좋지 않은 reflection 사용합니다. 막 쓰지 마세요.
	 * 
	 * @author nefer 20090107
	 * @param <T> 제네릭 (형변환 없이 추출하기 사용)
	 * @param map 원본 HashMap 객체
	 * @param clzz 전달할 클래스
	 * @exception NullArgumentException 인자값이 NULL인 경우 발생
	 * @exception Exception invoke 실행 오류 (전달한 클래스 객체에 get으로 시작하는 함수에 인자가 있는지 확인할것)
	 */
	public static <T> void appendMapForClassProperty(HashMap<String, Object> map, T clzz) {
		if (map == null || clzz == null)
			throw new NullArgumentException("argument is null.");

		Method[] methods = clzz.getClass().getMethods();
		for (int i = 0; i < methods.length; i++) {
			Method method = methods[i];
			if (!method.getReturnType().getName().equals("void") && method.getParameterTypes().length == 0
					&& !method.getName().equals("getClass") && method.getName().substring(0, 3).equals("get")) {
				String methodNm = method.getName().substring(3);
				methodNm = methodNm.substring(0, 1).toLowerCase() + methodNm.substring(1);

				try {
					Object obj = method.invoke(clzz, null);
					if (obj != null && !StringUtil.isEmpty(obj.toString())) {
						map.put(methodNm, obj);
						System.out.println("=== methodNm : " + methodNm + ", value : " + obj.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 이통사 코드로 이통사반환
	 * 
	 * @param mnoCd
	 * @return
	 */
	public static String convertTelecomAsCode(String mnoCd) {
		String telecom = "SKT";
		if ("US001202".equals(mnoCd)) {
			telecom = "KT";
		} else if ("US001203".equals(mnoCd)) {
			telecom = "LGT";
		}
		return telecom;
	}

	/**
	 * 이통사 이름으로 이통사코드 반환
	 * 
	 * @param telecom
	 * @return
	 */
	public static String convertMnoCdAsTelecom(String telecom) {
		String mnoCd = "";
		if ("SKT".equals(telecom)) {
			mnoCd = "US001201";
		} else if ("LGT".equals(telecom)) {
			mnoCd = "US001203";
		} else {
			mnoCd = "US001202"; // KTF->KT로 전체소스 수정했으나 찜찜하여 else 문에 KT 위치.
		}
		return mnoCd;
	}

	/**
	 * 주어진 문자열에서 a 태그를 제외한 태그를 모두 제거한다.
	 * 
	 * @param str 원본 문자열
	 * @param replacement 대체할 문자열
	 * @return
	 */
	public static String replaceAllTagsExceptA(String str, String replacement) {
		str = StringUtils.trimToEmpty(str);
		replacement = StringUtils.trimToEmpty(replacement);
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
		str = StringUtils.trimToEmpty(str);
		replacement = StringUtils.trimToEmpty(replacement);
		// 모든 태그
		return str.replaceAll("<?([a-zA-Z]*).([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", replacement);

	}
	
	/**
	 * 근본적인 원인 예외를 얻습니다.
	 * @param t
	 * @return
	 */
	public static Throwable getRootCause(Throwable t) {
		Throwable	cause;
		
		cause	= t.getCause();
		if (cause != null) {
			return getRootCause(cause);
		} else {
			return t;
		}
	}
	
	/**
	 * 원격 예외의 원인 예외를 얻습니다.
	 * @param e
	 * @return
	 */
	public static Throwable getRemoteCause(RemoteException e) {
		Throwable	cause;
		
		cause	= e.getCause();
		if (cause == null) {
			return e;
		} else if (!(cause instanceof RemoteException)) {
			return cause;
		} else {
			return getRemoteCause((RemoteException)cause); 
		}
	}
}