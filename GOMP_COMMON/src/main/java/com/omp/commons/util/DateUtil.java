package com.omp.commons.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.StringTokenizer;

import org.apache.commons.lang.StringUtils;

/**
 * 날짜 유틸리티
 * @author Administrator
 */
@SuppressWarnings("unchecked")
public class DateUtil {

	public static SimpleDateFormat dateSqlFormat = new SimpleDateFormat("yyyy/MM/dd");
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	/**
	 * 현재 시간을 hhmmss 형식으로 반환한다.(ex: 01, 02, 03,.....10, 11, 12)
	 * 
	 * @return
	 */
	public static String getCurrentTime() {
		DateFormat df = new SimpleDateFormat("hhmmss");
		return df.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 현재 시간을 HHmmss 형식으로 반환한다.(ex: 01, 02, 03,.....22, 23, 24)
	 * 
	 * @return
	 */
	public static String getCurrentTime24() {
		DateFormat df = new SimpleDateFormat("HHmmss");
		return df.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * yyyymmdd형식의 날짜 문자열을 받아 Date형으로 반환한다.
	 * 
	 * @param yyyymmdd 년월일(예:20030329 -> 2003년 3월 29일)
	 * @return 날짜 표기에 적절치 않은 문자열 이라면 null을 반환한다.
	 */
	public static Date getDate(String yyyymmdd) {
		if (yyyymmdd != null && yyyymmdd.length() == 8 && isDigit(yyyymmdd)) {
			Calendar cal = Calendar.getInstance();
			cal.set(Integer.parseInt(yyyymmdd.substring(0, 4)), Integer.parseInt(yyyymmdd.substring(4, 6)) - 1,
					Integer.parseInt(yyyymmdd.substring(6, 8)));

			return cal.getTime();
		}

		return null;
	}

	/**
	 * yyyymm 형식의 날짜 문자열을 받아 Date형으로 반환한다.
	 * 
	 * @param yyyymm 년월일(예:200303 -> 2003년 3월)
	 * @return 날짜 표기에 적절치 않은 문자열 이라면 null을 반환한다.
	 */
	public static Date getDateYYYYMM(String yyyymm) {
		if (yyyymm != null && yyyymm.length() == 6 && isDigit(yyyymm)) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, Integer.parseInt(yyyymm.substring(0, 4)));
			cal.set(Calendar.MONTH, Integer.parseInt(yyyymm.substring(4, 6)) - 1);

			return cal.getTime();
		}

		return null;
	}

	/**
	 * yyyy-mm-dd, yyyy/mm/dd, yyyy.mm.dd 형식의 날짜 문자열을 받아 Date형으로 반환한다.
	 * 
	 * @param yyyymmdd 구분자를 포함하는 날짜 문자열
	 * @return 날짜 표기에 적절치 않은 문자열 이라면 null을 반환한다.
	 */
	public static Date getDateWithDelimiter(String yyyymmdd) {
		return getDateWithDelimiter(yyyymmdd, '\0');
	}

	/**
	 * yyyy-mm, yyyy/mm, yyyy.mm 형식의 날짜 문자열을 받아 Date형으로 반환한다.
	 * 
	 * @param yyyymm 구분자를 포함하는 날짜 문자열
	 * @return 날짜 표기에 적절치 않은 문자열 이라면 null을 반환한다.
	 */
	public static Date getDateWithDelimiterYYYYMM(String yyyymm) {
		return getDateWithDelimiterYYYYMM(yyyymm, '\0');
	}

	/**
	 * yyyy#mm#dd 형식의 날짜 문자열을 받아 Date형으로 반환한다.
	 * 
	 * @param yyyymmdd 구분자를 포함하는 날짜 문자열
	 * @param delimiter #에 해당하는 구분자
	 * @return 날짜 표기에 적절치 않은 문자열 이라면 null을 반환한다.
	 */
	public static Date getDateWithDelimiter(String yyyymmdd, char delimiter) {
		if (yyyymmdd != null && !yyyymmdd.equals("")) {
			StringTokenizer st = new StringTokenizer(yyyymmdd, String.valueOf(delimiter) + "/.-");
			String yyyy = st.nextToken();
			String mm = st.nextToken();
			String dd = st.nextToken();

			if (mm.length() < 2)
				mm = "0" + mm;
			if (dd.length() < 2)
				dd = "0" + dd;

			String temp = yyyy + mm + dd;
			if (isDigit(temp))
				return getDate(temp);
		}

		return null;
	}

	/**
	 * yyyy#mm 형식의 날짜 문자열을 받아 Date형으로 반환한다.
	 * 
	 * @param yyyymm 구분자를 포함하는 날짜 문자열
	 * @param delimiter #에 해당하는 구분자
	 * @return 날짜 표기에 적절치 않은 문자열 이라면 null을 반환한다.
	 */
	public static Date getDateWithDelimiterYYYYMM(String yyyymm, char delimiter) {
		if (yyyymm != null && !yyyymm.equals("")) {
			StringTokenizer st = new StringTokenizer(yyyymm, String.valueOf(delimiter) + "/.-");
			String yyyy = st.nextToken();
			String mm = st.nextToken();

			if (mm.length() < 2)
				mm = "0" + mm;

			String temp = yyyy + mm;
			if (isDigit(temp))
				return getDateYYYYMM(temp);
		}

		return null;
	}

	/**
	 * 날짜 객체를 입력으로 받아 문자열로 반환한다.
	 * 
	 * @param date 날짜 객체
	 * @param delimiter 구분자
	 * @return 문자열의 날짜가 반환된다.
	 */
	public static String getDateStr(Date date, char delimiter) {
		return getDateStr(date, delimiter + "");
	}

	public static String getDateStr(Date date, String delimiter) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat("yyyy" + delimiter + "MM" + delimiter + "dd");
			return df.format(date);
		}

		return null;
	}

	/**
	 * 날짜 객체를 입력으로 받아 문자열로 반환한다.
	 */
	public static String getDateStr(Date date) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat("yyyyMMdd");
			return df.format(date);
		}

		return null;
	}

	/**
	 * YY/MM/DD 형식을 YYMMDD 형식으로 바꾼다.
	 * 
	 * @param String 객체
	 * @return 문자열의 날짜가 반환된다.
	 */
	public static String toDBFormat(String _displayDate) {
		if (_displayDate == null)
			return null;
		if (_displayDate.length() == 10) {
			return _displayDate.substring(0, 4) + _displayDate.substring(5, 7) + _displayDate.substring(8);
		} else if (_displayDate.length() == 7) {
			return _displayDate.substring(0, 4) + _displayDate.substring(5, 7);
		} else {

			return _displayDate;
		}
	}

	public static int getMonth(String date) {
		date = deleteSlash(date);
		date = deleteDash(date);
		return Integer.parseInt(date.substring(4, 6));
	}

	public static String getMonthString(String date) {
		date = deleteSlash(date);
		date = deleteDash(date);
		return date.substring(4, 6);
	}

	public static int getYear(String date) {
		date = deleteSlash(date);
		date = deleteDash(date);
		return Integer.parseInt(date.substring(0, 4));
	}

	public static String getDateStrWeekly(Date date, char delimiter) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat("(E)MM" + delimiter + "dd");
			return df.format(date);
		}

		return null;
	}

	public static String getDateStrWithMMDD(Date date) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat("MMdd");
			return df.format(date);
		}

		return null;
	}

	/**
	 * 날짜 객체를 입력으로 받아 문자열로 반환한다.(YYYYMM형식)
	 * 
	 * @param date 날짜 객체
	 * @param delimiter 구분자
	 * @return 문자열의 날짜가 반환된다.
	 */
	public static String getDateStrYYYYMM(Date date, char delimiter) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat("yyyy" + delimiter + "MM");
			return df.format(date);
		}

		return null;
	}

	public static String getDateStrYYYY(Date date) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat("yyyy");
			return df.format(date);
		}

		return null;
	}

	/**
	 * HHMM 형식의 스트링 문자열을 Date객체로 리턴한다.(24시간제)
	 * 
	 * @param hhmm 시간 문자열
	 * @return
	 */
	public static Date getTime(String hhmm) {
		if (hhmm != null && hhmm.length() == 4 && isDigit(hhmm)) {
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hhmm.substring(0, 2)));
			cal.set(Calendar.MINUTE, Integer.parseInt(hhmm.substring(2, 4)));

			return cal.getTime();
		}

		return null;
	}

	/**
	 * HHMM 형식의 시간:분 문자열을 받아 Date형으로 반환한다.
	 * 
	 * @param hhmm 시분 문자열
	 * @return 시간 표기에 적절치 않은 문자열 이라면 null을 반환한다.
	 */
	public static Date getTimeWithDelimiter(String hhmm) {
		return getTimeWithDelimiter(hhmm, '\0');
	}

	/**
	 * HH#MM 형식의 시간:분 문자열을 받아 Date형으로 반환한다.
	 * 
	 * @param hhmm 시분 문자열
	 * @param delimiter #에 해당하는 구분자
	 * @return 시간 표기에 적절치 않은 문자열 이라면 null을 반환한다.
	 */
	public static Date getTimeWithDelimiter(String hhmm, char delimiter) {
		if (hhmm != null) {
			StringTokenizer st = new StringTokenizer(hhmm, String.valueOf(delimiter) + ":-");
			String hh = st.nextToken();
			String mm = st.nextToken();

			if (hh.length() < 2)
				hh = "0" + hh;
			if (mm.length() < 2)
				mm = "0" + mm;

			String temp = hh + mm;
			if (isDigit(temp))
				return getTime(temp);
		}

		return null;
	}

	/**
	 * 날짜(시간) 객체를 입력으로 받아 문자열로 반환한다.
	 * 
	 * @param date 날짜(시간) 객체
	 * @param delimiter 구분자
	 * @return 문자열의 시분이 반환된다.
	 */
	public static String getTimeStr(Date date, char delimiter) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat("HH" + delimiter + "mm");
			return df.format(date);
		}

		return null;
	}

	/**
	 * 문자열이 숫자인지 체크
	 * 
	 * @param digitStr 숫자로 구성된 문자열
	 * @return 숫자로 구성되어 있으며 true, 아니면 false
	 */
	private static boolean isDigit(String digitStr) {
		if (digitStr != null) {
			for (int i = 0; i < digitStr.length(); i++)
				if (!Character.isDigit(digitStr.charAt(i)))
					return false;
		}
		return true;
	}

	/**
	 * 현재 날짜를 YYYYMM형식으로 반환한다.
	 * 
	 * @return
	 */
	public static String getCurrentYear() {
		DateFormat df = new SimpleDateFormat("yyyy");
		return df.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 현재 날짜를 MM형식으로 반환한다.
	 * 
	 * @return
	 */
	public static String getCurrentMonth() {
		DateFormat df = new SimpleDateFormat("MM");
		return df.format(new Date(System.currentTimeMillis()));
	}

	/**
	 * 기준월의 달력을 구성하기 위해 날짜의 배열을 반환한다(2차원)
	 * 
	 * @param dateStr
	 * @return
	 */
	public static ArrayList listMonthlyDate(String dateStr) {
		ArrayList row = new ArrayList();
		ArrayList col = null;
		Date searchDate = DateUtil.getDateWithDelimiter(dateStr);
		Calendar cal = Calendar.getInstance();
		cal.setTime(searchDate);

		cal.set(Calendar.DAY_OF_MONTH, 1);
		int firstWeek = cal.get(Calendar.DAY_OF_WEEK);
		int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		col = new ArrayList();
		int i = 0;
		for (i = 1; i < firstWeek; i++)
			col.add("");
		for (i = 1; i <= 7 - (firstWeek - 1); i++)
			col.add("" + i);
		row.add(col);
		col = new ArrayList();
		for (int j = i, k = 1; j <= endDay; j++, k++) {
			if (k > 7) {
				row.add(col);
				k = 1;
				col = new ArrayList();
			}
			col.add("" + j);
		}
		row.add(col);

		return row;
	}

	public static int getDayStr(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		return cal.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * @param dateStr
	 * @return
	 */
	private static String deleteDash(String dateStr) {
		return dateStr.replaceAll("-", "");
	}

	/**
	 * @param dateStr
	 * @return
	 */
	private static String deleteSlash(String dateStr) {
		return dateStr.replaceAll("/", "");
	}

	/**
	 * yyyymm 형식의 날짜 문자열을 받아 몇달전의 yyyymm형식으로 반환한다.
	 * 
	 * @param yyyymm 년월, beforeMonth(예:200303, -2 -> 200301)
	 * @return 날짜 표기에 적절치 않은 문자열 이라면 null을 반환한다.
	 */
	public static String getYYYYMMBeforeMonth(String yyyymm, int beforeMonth) {
		if (yyyymm != null && yyyymm.length() == 6 && isDigit(yyyymm)) {
			Calendar result = Calendar.getInstance();
			result.set(Calendar.YEAR, Integer.parseInt(yyyymm.substring(0, 4)));
			result.set(Calendar.MONTH, Integer.parseInt(yyyymm.substring(4, 6)) - 1);
			// 몇달전으로 바꾸기
			result.add(Calendar.MONTH, beforeMonth);
			DateFormat df = new SimpleDateFormat("yyyyMM");
			return df.format(result.getTime());
		}
		return null;
	}

	/**
	 * Date 형식 날짜 받아 yyyy형식으로 반환한다.
	 * 
	 * @param Date
	 * @return String
	 */
	public static String getYYYY(Date date) {
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		DateFormat df = new SimpleDateFormat("yyyy");
		return df.format(result.getTime());
	}

	/**
	 * Date 형식 날짜 받아 yyyyMM형식으로 반환한다.
	 * 
	 * @param Date
	 * @return String
	 */
	public static String getYYYYMM(Date date) {
		return getYYYYMM(date, "");
	}

	/**
	 * Date 형식 날짜 받아 yyyyMM형식으로 반환한다.
	 * 
	 * @param Date
	 * @return String
	 */
	public static String getYYYYMM(Date date, String delimiter) {
		Calendar result = Calendar.getInstance();
		result.setTime(date);
		DateFormat df = new SimpleDateFormat("yyyy" + delimiter + "MM");
		return df.format(result.getTime());
	}

	/**
	 * yyyymmdd를 yyyy/mm/dd 포맷으로 바꾼다.
	 */
	public static String toDisplayFormat(String _srcDate) {
		return toDisplayFormat(_srcDate, "/");
	}

	/**
	 * yyyymmdd를 yyyy/mm/dd 포맷으로 바꾼다.
	 */
	public static String toDisplayFormat(String _srcDate, String delimiter) {

		if (_srcDate == null || !(_srcDate.length() == 8 || _srcDate.length() == 14))
			return _srcDate;

		String time = "";
		if (_srcDate.length() == 14) {
			try {
				time = " " + _srcDate.substring(8, 10) + ":" + _srcDate.substring(10, 12) + ":" + _srcDate.substring(12, 14);
			} catch (Exception e) {}
		}

		return _srcDate.substring(0, 4) + delimiter + _srcDate.substring(4, 6) + delimiter + _srcDate.substring(6, 8) + time;
	}

	/**
	 * 특정 날짜에 요청된 날짜 수 만큼을 더하여 기본 포맷(yyyyMMdd)으로 리턴한다.
	 * 
	 * @param _oneDay String (특정일)
	 * @param _aFewDays int (현재 날짜에서 더하기 할 날짜 수)
	 * @return 현재 날짜에 요청된 날 만큼 더해진 날에 대한 String값
	 */
	public static String getOneDayPlusSomeDate(String _oneDay, int _aFewDays) {

		if (_oneDay == null || "".equals(_oneDay))
			return "";
		//		_oneDay = FormatUtil.deleteChar(_oneDay, "/");
		_oneDay = deleteSlash(_oneDay);

		// 특정일와 현재일의 차이를 구한다
		Date now = new Date(System.currentTimeMillis());
		String currentDate = dateFormat.format(now);

		long interval = Long.parseLong(_oneDay) - Long.parseLong(currentDate);

		// 현재일 기준으로 증가시킬 날 수를 구한다
		interval += _aFewDays;

		// 더하기 할 날짜 수에 해당하는 Millisecond를 계산한다
		// 요청날수 * 시 * 분 * 초 * 1000(millisecond 단위)
		long intervalMillis = interval * (long) 24 * (long) 60 * (long) 60 * (long) 1000;

		Date current = new Date(System.currentTimeMillis() + intervalMillis);
		return dateSqlFormat.format(current);
	}

	/**
	 * 날짜 객체를 입력으로 받아 YYYYMMDD 형태로 반환한다.
	 */
	public static String getYYYYMMDD(Date date) {
		return getYYYYMMDD(date, "");
	}

	/**
	 * 현재일과 지정된 날과의 일수를 가져온다.(start : YYYYMMDD)
	 */
	public static int getDiffDays(String start) {
		Calendar cal = Calendar.getInstance();
		int daydiff = 0;
		//일자가 있는지 체크
		if (start != null && !start.trim().equals("") && start.length() >= 8) {
			Date startDate;
			if(start.length()>8){
				start = start.substring(0,8);
			}
			try {
				startDate = dateFormat.parse(start);
			} catch (ParseException e) {
				throw new RuntimeException("invalid start date " + start + " (yyyyMMdd)", e);
			}
			daydiff = getDiffDays(startDate, cal.getTime());
		}
		return daydiff;
	}

	/**
	 * 지정된 날들 사이의 일수를 가져온다.
	 */
	public static int getDiffDays(String start, String end) {
		Date startDate;
		try {
			startDate = dateFormat.parse(start);
		} catch (ParseException e) {
			throw new RuntimeException("invalid start date " + start + " (yyyyMMdd)", e);
		}

		Date endDate;
		try {
			endDate = dateFormat.parse(end);
		} catch (ParseException e) {
			throw new RuntimeException("invalid end date " + end + " (yyyyMMdd)", e);
		}

		return getDiffDays(startDate, endDate);
	}
	
	
	/**
	 * 지정된 날들 사이의 일수를 가져온다.
	 */
	public static int getDiffDays(Date startDate, Date endDate) {
		return (int)((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000));
	}

	/**
	 * 날짜 객체를 입력으로 받아 YYYYMMDD 형태로 반환한다.
	 */
	public static String getYYYYMMDD(Date date, String delimiter) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat("yyyy" + delimiter + "MM" + delimiter + "dd");
			return df.format(date);
		}

		return null;
	}

	public static Date getDateFirstDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);

		return calendar.getTime();
	}

	public static Date getDateLastDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar = new GregorianCalendar(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		return calendar.getTime();
	}

	public static String toDisplayMonth(String _dbDate, String del) {
		if (_dbDate.length() < 6)
			return _dbDate;
		return _dbDate.substring(0, 4) + del + _dbDate.substring(4);
	}

	public static String getYYMM() {
		Date current = new Date(System.currentTimeMillis());
		return new SimpleDateFormat("yyMM").format(current);
	}

	public static String getYYYYMM() {
		Date current = new Date(System.currentTimeMillis());
		return new SimpleDateFormat("yyyyMM").format(current);
	}

	public static Date getDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 특정날짜(yyyymmdd)에서 날짜수를 더한만큼 리턴
	 */
	public static String getDayAfterWithOutSlash(String startDate, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(startDate.substring(0, 4)), Integer.parseInt(startDate.substring(4, 6)) - 1,
				Integer.parseInt(startDate.substring(6, 8)));
		cal.add(Calendar.DAY_OF_YEAR, day);

		return dateFormat.format(cal.getTime());

	}

	/**
	 * 현재날짜에서 해당 날짜를 받아 더해서 YYYYMMDD 형태로 반환한다.
	 * 
	 * @param addDay 현재날짜에서 더 할 날짜
	 * @return
	 */
	public static String getAddDay(String addDay) {

		Calendar c = Calendar.getInstance();
		c.setTime(new Date(System.currentTimeMillis()));
		c.add(Calendar.DAY_OF_MONTH, Integer.parseInt(addDay));
		String date = DateUtil.getYYYYMMDD(c.getTime(), "");
		return date;
	}

	public static String getAddDay(int days, String pattern) {
		String now = getSysDate();
		Calendar cal = Calendar.getInstance();
		int yyyy = Integer.parseInt(now.substring(0, 4));
		int mm = Integer.parseInt(now.substring(4, 6)) - 1;
		int dd = Integer.parseInt(now.substring(6, 8));
		cal.set(yyyy, mm, dd);
		cal.add(5, days);
		now = getFormatString(pattern, cal);
		return now;
	}

	/**
	 * 시스템 시간을 리턴한다.
	 * 
	 * @return
	 */
	public static String getShortTimeStampString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmssSSS");
		return formatter.format(new java.util.Date());
	}

	public static String getGeneralTimeStampString() {
		java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(new java.util.Date());
	}

	public static String getFmtGeneralTimeStampString(String date) {
		return fmtDate(date, "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * srcFmt형식의 date를 입력하면 destFmt형식의 date를 리턴함 입력된 date가 null일 경우 ""를 리턴, Comn.fmtDate("20001008", "yyyyMMdd", "yyyy-MM-dd");
	 * Comn.fmtDate("2000.10.08.","yyyy.MM.dd." "yyyy/MM/dd");
	 * 
	 * @param date "20001010"
	 * @param srcFmt "yyyyMMdd"
	 * @param destFmt "yyyy-MM-dd"
	 * @return String 2000-10-10
	 */
	public static String fmtDate(String date, String srcFmt, String destFmt) {
		if (StringUtils.isBlank(date) || StringUtils.isBlank(srcFmt) || StringUtils.isBlank(destFmt)) {
			return "";
		}
		java.text.SimpleDateFormat srcFormat;
		java.text.SimpleDateFormat destFormat;
		java.util.Date targetDate;
		String result = "";

		if (date.trim().length() > 0) {

			srcFormat = new java.text.SimpleDateFormat(srcFmt);
			destFormat = new java.text.SimpleDateFormat(destFmt);

			try {
				targetDate = srcFormat.parse(date);
			} catch (final Exception e) {
				return date;
			}
			result = destFormat.format(targetDate);

		}
		return result;
	}

	/**
	 * 외부와의 통신을 위해 YYYY-MM-DD hh24:mi:ss 을 YYYYMMDDhhmiss 로 변환한다.
	 * 
	 * @return
	 */
	public static String changeFormat(String timeFormat) {
		return timeFormat.trim().replaceAll("/", "").replaceAll(":", "").replaceAll(" ", "");
	}

	/**
	 * N 개월 후/전을 구한다. N > 0이면 N개월 후, N < 0이면 N개월 전의 Date를 구한다.
	 * 
	 * @param date 기준 Date
	 * @param month 더할 개월 수
	 * @return N개월 후/전의 Date
	 */
	public static Date addMonth(Date date, int month) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}

	/**
	 * 현재 날짜를 MM형식으로 반환한다.
	 * 
	 * @return
	 */
	public static String getCurrentDate() {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(new Date(System.currentTimeMillis()));
	}

	public static String getFormatString(String pattern, Calendar cal) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		String dateString = formatter.format(cal.getTime());
		return dateString;
	}

	/**
	 * 현재 날짜를 yyyyMMdd형식으로 반환한다.
	 * 
	 * @return
	 */
	public static String getSysDate() {
		String date = "";
		Calendar cal = Calendar.getInstance();
		int mm = cal.get(2) + 1;
		int dd = cal.get(5);
		date = (new StringBuilder(String.valueOf(date))).append(cal.get(1)).toString();
		if (mm < 10)
			date = (new StringBuilder(String.valueOf(date))).append("0").append(mm).toString();
		else
			date = (new StringBuilder(String.valueOf(date))).append(mm).toString();
		if (dd < 10)
			date = (new StringBuilder(String.valueOf(date))).append("0").append(dd).toString();
		else
			date = (new StringBuilder(String.valueOf(date))).append(dd).toString();
		return date;
	}

	/**
	 * 현재 날짜를 yyyyMMdd형식으로 반환한다.
	 * 
	 * @return
	 */
	public static String getSysDate(String pattern) {
		Calendar cal = Calendar.getInstance();
		return getFormatString(pattern, cal);
	}

	/**
	 * Get first day of week.
	 * 
	 * @param weekOfYear weekOfYear.
	 * @return First day of week.
	 */
	public static String getFirstDayOfWeek(int weekOfYear) {
		String date = getDayOfWeek(getSysDate("yyyyMMdd"), "yyyyMMdd", Calendar.SUNDAY, weekOfYear);
		return DateUtil.fmtDate(date, "yyyyMMdd", "yyyy-MM-dd");
	}

	/**
	 * Get last day of week.
	 * 
	 * @param weekOfYear weekOfYear.
	 * @return Last day of week.
	 */
	public static String getLastDayOfWeek(int weekOfYear) {
		String date = getDayOfWeek(getSysDate("yyyyMMdd"), "yyyyMMdd", Calendar.SATURDAY, weekOfYear);
		return DateUtil.fmtDate(date, "yyyyMMdd", "yyyy-MM-dd");

	}

	/**
	 * Get day of week.
	 * 
	 * @param str Specified datetime string.
	 * @param pattern Given datetime pattern.
	 * @param day Specified day.
	 * @param weekOfYear weekOfYear.
	 * @return day of week.
	 */
	public static String getDayOfWeek(String str, String pattern, int day, int weekOfYear) {
		Calendar cal = Calendar.getInstance();
		Date org_date = getDateByPattern(str, pattern);

		cal.setTime(org_date);
		cal.set(Calendar.DAY_OF_WEEK, day);

		cal.add(Calendar.WEEK_OF_YEAR, weekOfYear);

		Date date = cal.getTime();

		return getFormattedString(new Date(date.getTime()), pattern);

	}

	/**
	 * Get formatted datetime with given date and pattern.
	 * 
	 * @param date Given date.
	 * @param pattern Given pattern such as 'yyyyMMdd'.
	 * @return Formatted datetime string.
	 */
	public static String getFormattedString(Date date, String pattern) {
		Locale locale = Locale.getDefault();
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
		String dateString = formatter.format(date);
		return dateString;
	}

	/**
	 * Get date by given pattern.
	 * 
	 * @param originDate Given date string.
	 * @param pattern Given pattern.
	 * @return Date object.
	 */
	public static Date getDateByPattern(String originDate, String pattern) {
		Date dt = null;
		try {
			Locale locale = Locale.getDefault();
			SimpleDateFormat formatter = new SimpleDateFormat(pattern, locale);
			dt = formatter.parse(originDate);

		} catch (Exception exx) {
			exx.toString();
		}
		return dt;
	}

	/**
	 * 입력받은 날을 기준으로 입력값 만큼 지난날/이후날 반환
	 * 
	 * @param curDay String
	 * @param fur
	 * @param format
	 * @return
	 */
	public static String getDayInterval(String curDay, int fur, String format) {
		Calendar cal = getCalendar(curDay);
		cal.add(Calendar.DATE, fur);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}

	/**
	 * 입력받은 날을 기준으로 입력값 만큼 지난날/이후날 반환
	 * 
	 * @param curDay Date
	 * @param fur
	 * @param format
	 * @return
	 */
	public static String getDayInterval(Date curDay, int fur, String format) {
		Calendar cal = getCalendar(getDateToStr(curDay, format));
		cal.add(Calendar.DATE, fur);
		Date d = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(d);
	}

	public static Calendar getCalendar(String day) {
		String format = "yyyyMMddHHmm";
		if (day.length() == 8)
			format = "yyyyMMdd";
		else if (day.length() == 10)
			format = "yyyyMMddHH";
		return getCalendar(day, format);
	}

	public static Calendar getCalendar(String day, String format) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getStrToDate(day, format));
		return cal;
	}

	/**
	 * Date형의 날짜를 지정한 포맷으로 반환.
	 * 
	 * @param str
	 * @param dateFormat
	 * @return
	 */
	public static String getDateToStr(Date str, String dateFormat) {
		String retVal = "";
		try {
			if (str == null) {
				return retVal;
			}
			//Date validation
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setLenient(false);

			try {
				retVal = sdf.format(str);
			} catch (Exception ex) {

			}
		} catch (Exception e) {

		}
		return retVal;
	}

	/**
	 * String형식의 날짜를 지정된 포맷으로 반환.
	 * 
	 * @param str
	 * @param dateFormat
	 * @return
	 */
	public static Date getStrToDate(String str, String dateFormat) {
		Date date = null;

		try {
			if (str == null || str.length() != dateFormat.length()) {
				return date;
			}
			//Date validation
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setLenient(false);

			try {
				date = sdf.parse(str);
			} catch (ParseException pe) {
				date = new Date();
			}
		} catch (Exception e) {
			date = new Date();
		}

		return date;
	}

	/**
	 * 개월 차이 <br>
	 * 입력한 날짜 사이의 개월수를 가져온다.(YYYYMMDD)
	 * 
	 * @param s from s "yyyyMMdd".
	 * @return date java.util.Date
	 */
	public static int getDiffMonths(String from, String to) {

		Date fromDate = null;
		try {
			fromDate = check(from);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date toDate = null;
		try {
			toDate = check(to);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// if two date are same, return 0.
		if (fromDate.compareTo(toDate) == 0)
			return 0;

		SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
		SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
		SimpleDateFormat dayFormat = new SimpleDateFormat("dd");

		int fromYear = Integer.parseInt(yearFormat.format(fromDate));
		int toYear = Integer.parseInt(yearFormat.format(toDate));
		int fromMonth = Integer.parseInt(monthFormat.format(fromDate));
		int toMonth = Integer.parseInt(monthFormat.format(toDate));
		int fromDay = Integer.parseInt(dayFormat.format(fromDate));
		int toDay = Integer.parseInt(dayFormat.format(toDate));

		int result = 0;
		result += ((toYear - fromYear) * 12);
		result += (toMonth - fromMonth);

		if (((toDay - fromDay) > 0))
			result += toDate.compareTo(fromDate);

		return result;
	}

	/**
	 * 년월을 입력하면 그 달의 마지막 일을 리턴한다. "201102" -> "28"
	 * 
	 * @param str 년월(yyyymm)
	 * @return java.lang.String
	 */
	public static String getlastDayOfMonth(String yyyymm) {
		String yyyymmdd = yyyymm + "01";
		Calendar cal = getCalendar(yyyymmdd);
		int lastDate = cal.getActualMaximum(cal.DATE);
		return String.valueOf(lastDate);
	}

	/**
	 * 년월일 사이에 '-'를 첨가한다. "yyyymmdd" -> "yyyy-mm-dd"
	 * 
	 * @param str 날짜(yyyymmdd)
	 * @return java.lang.String
	 */
	public static final String dashDate(String str) throws Exception {
		return getDisDate(str, "yyyy-MM-dd");
	}

	/**
	 * 지정일을 지정된 형식으로 변환
	 * 
	 * @param s
	 * @param format 날짜 포맷 (예: "yyyy-MM-dd")
	 * @return String
	 */
	public static final String getDisDate(String s, String format) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date targetDate = null;
		try {
			targetDate = check(s);
		} catch (ParseException pe) {
			return s;
		}
		// return targetDate.toString();
		return formatter.format(targetDate);
	}

	/**
	 * 일자 변환 <br>
	 * 주어진 포맷 형식의 문자열을 Date형으로 변환하여 리턴한다.
	 * 
	 * @param s
	 * @param format 날짜 포맷 (예: "yyyy-MM-dd")
	 * @return date java.util.Date
	 */
	public static final Date check(String s, String format) throws ParseException {
		if (s == null)
			throw new ParseException("date string to check is null", 0);
		if (format == null)
			throw new ParseException("format string to check date is null", 0);
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = formatter.parse(s);
		} catch (ParseException e) {
			throw new ParseException(" wrong date:\"" + s + "\" with format \"" + format + "\"", 0);
		}
		if (!formatter.format(date).equals(s))
			throw new ParseException("Out of bound date:\"" + s + "\" with format \"" + format + "\"", 0);
		return date;
	}

	/**
	 * 일자 변환 <br>
	 * "yyyyMMdd"형식의 문자열을 Date형으로 변환하여 리턴한다.
	 * 
	 * @param s "yyyyMMdd".
	 * @return date java.util.Date
	 */
	public static final Date check(String s) throws ParseException {
		return check(s, "yyyyMMdd");
	}
	
	public static void main(String[] args) {
		System.out.println(DateUtil.getDateToStr(new Date(), "yyyy年 MM月 dd日"));
	}
}
