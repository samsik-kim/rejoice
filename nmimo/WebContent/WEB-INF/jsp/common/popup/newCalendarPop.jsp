<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.nmimo.common.util.DateUtils"%>
<%@ page import="com.nmimo.common.util.StringUtils"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ taglib prefix="mimo" uri="/WEB-INF/tld/mimo.tld"%>
<mimo:userinfo var="user_session" />
<%
	//오늘날짜 가져오기
	String today = DateUtils.getToday("yyyy-MM-dd");
	String arr[] = today.split("-");
	String year = arr[0].toString();
	String month = arr[1].toString();
	String day = arr[2].toString();
	String type= StringUtils.nvlStr(request.getParameter("type"));
	
%>
<div id="popup" style="width:330px;">
<div class="pTop">
	<h6>예약시간 설정</h6>
</div>

<style>
	html,body{background:#fff;}
	html {overflow:hidden;}
	.title {color:#009900}
</style>

<script type="text/javascript">
 	
	var userAuth = 	"${user_session.authority}";
	var headerfooter_time_year = <%=year%>;
	var headerfooter_time_month = <%=month%>;
	var headerfooter_time_day = <%=day%>;
	var rege_0_type = "calendar";
	var yearSelect;
	var monthSelect;
	var oYearSelect;
	var todayDate;
	var calendarType = '<%=type%>';
	
	if (typeof(headerfooter_time_year) != "undefined"){
		/* 오늘의 날짜를 서버 날짜로 설정 */
		todayDate = new Date(
		headerfooter_time_year, headerfooter_time_month - 1,headerfooter_time_day);
	}else{
		todayDate = new Date();	
	}
	
	function getToDate(){
	
		var date = todayDate;
		var year = date.getFullYear();
		var month = (date.getMonth()+1);
		var day = date.getDate();
		var weekday= new Array('일','월','화','수','목','금','토');
		var wkd= date.getDay();
		
		toDay_yearmonth.innerText = year+"년 "+month+"월";
		toDay_day.innerText = day;
		toDay_weekday.innerText = weekday[wkd]+"요일";
		getLunarDate();
	}

	function getLunarDate(){
	
		var date = todayDate;
		var year = date.getFullYear();
		var month = (date.getMonth()+1);
		var day = date.getDate();
		var date = lunarCalc(year, month, day, 1);
		toDay_con.innerText = "음력 " + (date.leapMonth ? "윤" : "") + date.month + "월 " + date.day + "일";
	}

	//음력변환 함수
	function conversion(){
		var year = a.value;
		var monty = b.value;
		var day = c.value;
		lunarCalc(a, b, c, 1, 0);
	}

	function memorialDay(name, month, day, solarLunar, holiday, type){
		this.name = name;
		this.month = month;
		this.day = day;
		this.solarLunar = solarLunar;
		this.holiday = holiday; /* true : 빨간날 false : 안빨간날 */
		this.type = type; /* true : real time setting */
		this.techneer = true;
	}
	
	//매년 반복되는 기념일
	var memorialDays = Array (
	new memorialDay("", 12, 0, 2, true, true), /* 형식에 맞게 입력*/
			
	new memorialDay("신정", 1, 1, 1, true),
	new memorialDay("설날", 1, 1, 2, true),
	new memorialDay("", 1, 2, 2, true),
	new memorialDay("3·1절", 3, 1, 1, true),
	new memorialDay("식목일", 4, 5, 1, true),
	new memorialDay("석가탄신일", 4, 8, 2, true),
	new memorialDay("어린이날", 5, 5, 1, true),
	new memorialDay("현충일", 6, 6, 1, true),
	new memorialDay("제헌절", 7, 17, 1, false),
	new memorialDay("광복절", 8, 15, 1, true),
	new memorialDay("", 8, 14, 2, true),
	new memorialDay("추석", 8, 15, 2, true),
	new memorialDay("", 8, 16, 2, true),
	new memorialDay("개천절", 10, 3, 1, true),
	new memorialDay("성탄절", 12, 25, 1, true),
	new memorialDay("정월대보름", 1, 15, 2, false),
	new memorialDay("단오", 5, 5, 2, false),
	new memorialDay("국군의날", 10, 1, 1, false),
	new memorialDay("한글날", 10, 9, 1, false),
	new memorialDay("6·25전쟁일", 6, 25, 1, false),
	new memorialDay("발렌타인데이", 2, 14, 1, false),
	new memorialDay("물의날", 3, 22, 1, false),
	new memorialDay("만우절", 4, 1, 1, false),
	new memorialDay("임시정부수립일", 4, 13, 1 , false),
	new memorialDay("4·19혁명기념일", 4, 19, 1 , false),
	new memorialDay("장애인의날", 4, 20, 1 , false),
	new memorialDay("과학의날", 4, 21, 1 , false),
	new memorialDay("정보통신의날", 4, 22, 1 , false),
	new memorialDay("법의날", 4, 25, 1 , false),
	new memorialDay("충무공탄신일", 4, 28, 1, false),
	new memorialDay("근로자의날", 5, 1, 1, false),
	new memorialDay("어버이날", 5, 8, 1, false),
	new memorialDay("스승의날", 5, 15, 1, false),
	new memorialDay("5·18 민주화운동일", 5, 18, 1, false),
	new memorialDay("발명의날", 5, 19, 1, false),
	new memorialDay("바다의날", 5, 31, 1, false),
	new memorialDay("환경의날", 6, 5, 1, false),
	new memorialDay("칠월칠석", 7, 7, 2, false),
	new memorialDay("노인의날", 10, 2, 1, false),
	new memorialDay("체육의날", 10, 15, 1, false),
	new memorialDay("국제연합일", 10, 24, 1, false),
	new memorialDay("학생독립운동기념일", 11, 3, 1, false),
	new memorialDay("철도의날", 9, 18, 1, false),
	new memorialDay("소방의날", 11, 9, 1, false)
	);
	
	//특정 연도의 날짜로만 된 기념일 또는 매년 반복되더라도 날짜가 불규칙하게 바뀌는 기념일은 이곳에 입력한다.
	function yearmemorialDay(name, year, month, day, solarLunar, holiday, type)
	{
		this.name = name;
		this.year = year;
		this.month = month;
		this.day = day;
		this.solarLunar = solarLunar;
		this.holiday = holiday; /* true : 빨간날 false : 안빨간날 */
		this.type = type; /* true : real time setting */
		this.techneer = true;
	}
	
	var yearmemorialDays = Array (
		new yearmemorialDay("", 1989, 10, 2, 1, true),
		new yearmemorialDay("제16대 국회의원 선거일", 2000, 4, 13, 1, true),
		new yearmemorialDay("제3대 지방선거일", 2002, 6, 13, 1, true),
		new yearmemorialDay("월드컵기념 임시공휴일", 2002, 7, 1, 1, true),
		new yearmemorialDay("제16대 대통령 선거일", 2002, 12, 19, 1, true),
		new yearmemorialDay("제17대 국회의원 선거일", 2004, 4, 15, 1, true),
		new yearmemorialDay("제4대 지방선거일", 2006, 5, 31, 1, true),
		new yearmemorialDay("제17대 대통령 선거일", 2007, 12, 19, 1, true)
	);
	
	function myDate(year, month, day, leapMonth){
		this.year = year;
		this.month = month;
		this.day = day;
		this.leapMonth = leapMonth;
	}

	
	// 음력 데이터 (평달 - 작은달 :1,  큰달:2 )
	// (윤달이 있는 달 - 평달이 작고 윤달도 작으면 :3 , 평달이 작고 윤달이 크면 : 4)
	// (윤달이 있는 달 - 평달이 크고 윤달이 작으면 :5,  평달과 윤달이 모두 크면 : 6)
	var lunarMonthTable = [
	[1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2],
	[2, 1, 2, 5, 2, 1, 2, 1, 2, 1, 2, 1],
	[1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2], /* 1801 */ 
	[1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2, 1],
	[2, 3, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
	[2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2],
	[1, 2, 1, 2, 1, 3, 2, 1, 2, 2, 2, 1],
	[2, 2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 1],
	[2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],
	[1, 2, 2, 1, 5, 2, 1, 2, 1, 1, 2, 1],
	[2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2],
	[1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2],
	[1, 1, 5, 2, 1, 2, 2, 1, 2, 2, 1, 2], /* 1811 */
	[1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2, 1],
	[2, 1, 2, 1, 1, 1, 2, 1, 2, 2, 2, 1],
	[2, 5, 2, 1, 1, 1, 2, 1, 2, 2, 1, 2],
	[2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2],
	[2, 2, 1, 2, 1, 5, 1, 2, 1, 2, 1, 2],
	[2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1],
	[2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2],
	[1, 2, 1, 5, 2, 2, 1, 2, 2, 1, 2, 1],
	[1, 2, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
	[1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2], /* 1821 */
	[2, 1, 5, 1, 1, 2, 1, 2, 2, 1, 2, 2],
	[2, 1, 2, 1, 1, 1, 2, 1, 2, 1, 2, 2],
	[2, 1, 2, 1, 2, 1, 4, 1, 2, 1, 2, 2],
	[2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
	[2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1],
	[2, 1, 2, 2, 4, 1, 2, 1, 2, 1, 2, 1],
	[2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
	[1, 1, 2, 3, 2, 1, 2, 2, 1, 2, 2, 2],
	[1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2], /* 1831 */
	[1, 2, 1, 2, 1, 1, 2, 1, 5, 2, 2, 2],
	[1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
	[1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
	[1, 2, 2, 1, 2, 5, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1],
	[2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],
	[1, 2, 1, 5, 1, 2, 2, 1, 2, 2, 1, 2],
	[1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
	[2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
	[1, 2, 4, 1, 1, 2, 1, 2, 1, 2, 2, 1],   /* 1841 */
	[2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 1],
	[2, 2, 2, 1, 2, 1, 4, 1, 2, 1, 2, 1],
	[2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1],
	[2, 1, 2, 1, 5, 2, 1, 2, 2, 1, 2, 1],
	[2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
	[1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
	[2, 1, 2, 3, 2, 1, 2, 1, 2, 1, 2, 2],
	[2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2],
	[2, 2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2],   /* 1851 */
	[2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 1, 2],
	[2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 1, 2, 5, 2, 1, 2, 1, 2],
	[1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1],
	[2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
	[1, 2, 1, 1, 5, 2, 1, 2, 1, 2, 2, 2],
	[1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2],
	[2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],
	[2, 1, 6, 1, 1, 2, 1, 1, 2, 1, 2, 2],
	[1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2],   /* 1861 */
	[2, 1, 2, 1, 2, 2, 1, 5, 2, 1, 1, 2],
	[1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],
	[1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
	[2, 1, 1, 2, 4, 1, 2, 2, 1, 2, 2, 1],
	[2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2],
	[1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2],
	[1, 2, 2, 3, 2, 1, 1, 2, 1, 2, 2, 1],
	[2, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1],
	[2, 2, 2, 1, 2, 1, 2, 1, 1, 5, 2, 1],
	[2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1, 2],   /* 1871 */
	[1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2],
	[1, 1, 2, 1, 2, 4, 2, 1, 2, 2, 1, 2],
	[1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2, 1],
	[2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1],
	[2, 2, 1, 1, 5, 1, 2, 1, 2, 2, 1, 2],
	[2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2],
	[2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1],
	[2, 2, 4, 2, 1, 2, 1, 1, 2, 1, 2, 1],
	[2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2],
	[1, 2, 1, 2, 1, 2, 5, 2, 2, 1, 2, 1],   /* 1881 */
	[1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
	[1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2],
	[2, 1, 1, 2, 3, 2, 1, 2, 2, 1, 2, 2],
	[2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
	[2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
	[2, 2, 1, 5, 2, 1, 1, 2, 1, 2, 1, 2],
	[2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1],
	[2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
	[1, 5, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],   /* 1891 */
	[1, 1, 2, 1, 1, 5, 2, 2, 1, 2, 2, 2],
	[1, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
	[1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
	[2, 1, 2, 1, 5, 1, 2, 1, 2, 1, 2, 1],
	[2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
	[1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1],
	[2, 1, 5, 2, 2, 1, 2, 1, 2, 1, 2, 1],
	[2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],
	[1, 2, 1, 1, 2, 1, 2, 5, 2, 2, 1, 2],
	[1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],   /* 1901 */
	[2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
	[1, 2, 1, 2, 3, 2, 1, 1, 2, 2, 1, 2],
	[2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1],
	[2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2],
	[1, 2, 2, 4, 1, 2, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
	[2, 1, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2],
	[1, 5, 1, 2, 1, 2, 1, 2, 2, 2, 1, 2],
	[1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
	[2, 1, 2, 1, 1, 5, 1, 2, 2, 1, 2, 2],   /* 1911 */
	[2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2],
	[2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2],
	[2, 2, 1, 2, 5, 1, 2, 1, 2, 1, 1, 2],
	[2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
	[2, 3, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1],
	[2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
	[1, 2, 1, 1, 2, 1, 5, 2, 1, 2, 2, 2],
	[1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2],
	[2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],   /* 1921 */
	[2, 1, 2, 2, 3, 2, 1, 1, 2, 1, 2, 2],
	[1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2],
	[2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1],
	[2, 1, 2, 5, 2, 1, 2, 2, 1, 2, 1, 2],
	[1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
	[2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
	[1, 5, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2],
	[1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2],
	[1, 2, 2, 1, 1, 5, 1, 2, 1, 2, 2, 1],
	[2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1],   /* 1931 */
	[2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2],
	[1, 2, 2, 1, 6, 1, 2, 1, 2, 1, 1, 2],
	[1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2],
	[1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
	[2, 1, 4, 1, 1, 2, 2, 1, 2, 2, 2, 1],
	[2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1],
	[2, 2, 1, 1, 2, 1, 4, 1, 2, 2, 1, 2],
	[2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 1, 2],
	[2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1],
	[2, 2, 1, 2, 2, 4, 1, 1, 2, 1, 2, 1],   /* 1941 */
	[2, 1, 2, 2, 1, 2, 2, 1, 1, 2, 1, 2],
	[1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
	[2, 1, 2, 4, 1, 2, 1, 2, 2, 1, 2, 2],
	[1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2],
	[2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2],
	[2, 5, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
	[2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
	[2, 1, 2, 2, 1, 2, 3, 2, 1, 2, 1, 2],
	[1, 2, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1],
	[2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],   /* 1951 */
	[1, 2, 1, 2, 4, 1, 2, 2, 1, 2, 1, 2],
	[1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2, 2],
	[1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2],
	[2, 1, 4, 1, 1, 2, 1, 2, 1, 2, 2, 2],
	[1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
	[2, 1, 2, 1, 2, 1, 1, 5, 2, 1, 2, 2],
	[1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
	[1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1],
	[2, 1, 2, 1, 2, 5, 2, 1, 2, 1, 2, 1],
	[2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],   /* 1961 */
	[1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1],
	[2, 1, 2, 3, 2, 1, 2, 1, 2, 2, 2, 1],
	[2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
	[1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 2],
	[1, 2, 5, 2, 1, 1, 2, 1, 1, 2, 2, 1],
	[2, 2, 1, 2, 2, 1, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 2, 1, 5, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
	[2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2],
	[1, 2, 1, 1, 5, 2, 1, 2, 2, 2, 1, 2],   /* 1971 */
	[1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
	[2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2],
	[2, 2, 1, 5, 1, 2, 1, 1, 2, 2, 1, 2],
	[2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2],
	[2, 2, 1, 2, 1, 2, 1, 5, 1, 2, 1, 2],
	[2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 1],
	[2, 1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1],
	[2, 1, 1, 2, 1, 6, 1, 2, 2, 1, 2, 1],
	[2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
	[1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2],   /* 1981 */
	[2, 1, 2, 3, 2, 1, 1, 2, 1, 2, 2, 2],
	[2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],
	[2, 1, 2, 2, 1, 1, 2, 1, 1, 5, 2, 2],
	[1, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2],
	[1, 2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 1],
	[2, 1, 2, 1, 2, 5, 2, 2, 1, 2, 1, 2],
	[1, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
	[2, 1, 1, 2, 1, 2, 1, 2, 1, 2, 2, 2],
	[1, 2, 1, 1, 5, 1, 2, 2, 1, 2, 2, 2],
	[1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2],   /* 1991 */
	[1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],
	[1, 2, 5, 2, 1, 2, 1, 1, 2, 1, 2, 1],
	[2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2],
	[1, 2, 2, 1, 2, 1, 2, 5, 2, 1, 1, 2],
	[1, 2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 1],
	[2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
	[2, 1, 1, 2, 3, 2, 2, 1, 2, 2, 2, 1],
	[2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1],
	[2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1],
	[2, 2, 1, 5, 2, 1, 1, 2, 1, 2, 1, 2],   /* 2001 */
	[2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1],
	[2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2],
	[1, 5, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
	[2, 1, 2, 1, 2, 1, 5, 2, 2, 1, 2, 2],
	[1, 1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2],
	[2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2],
	[2, 2, 1, 1, 5, 1, 2, 1, 2, 1, 2, 2],
	[2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
	[2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1],   /* 2011 */
	[2, 1, 2, 5, 2, 2, 1, 1, 2, 1, 2, 1],
	[2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 1, 2, 1, 2, 5, 2, 1, 2],
	[1, 2, 1, 1, 2, 1, 2, 2, 2, 1, 2, 1],
	[2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2],
	[1, 2, 1, 2, 1, 4, 1, 2, 1, 2, 2, 2],
	[1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
	[2, 1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2],
	[2, 1, 2, 5, 2, 1, 1, 2, 1, 2, 1, 2],
	[1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1],   /* 2021 */
	[2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2],
	[1, 5, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2],
	[1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1],
	[2, 1, 2, 1, 1, 5, 2, 1, 2, 2, 2, 1],
	[2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
	[1, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1],
	[2, 2, 2, 1, 5, 1, 2, 1, 1, 2, 2, 1],
	[2, 2, 1, 2, 2, 1, 1, 2, 1, 1, 2, 2],
	[1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1],
	[2, 1, 5, 2, 1, 2, 2, 1, 2, 1, 2, 1],   /* 2031 */
	[2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2],
	[1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 5, 2],
	[1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 1, 2],
	[2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2],
	[2, 2, 1, 2, 1, 4, 1, 1, 2, 2, 1, 2],
	[2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2],
	[2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1],
	[2, 2, 1, 2, 5, 2, 1, 2, 1, 2, 1, 1],
	[2, 1, 2, 2, 1, 2, 1, 2, 2, 1, 2, 1],
	[2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2],   /* 2041 */
	[1, 5, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2],
	[1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2],
	[2, 1, 2, 1, 1, 2, 3, 2, 1, 2, 2, 2],
	[2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],
	[2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2],
	[2, 1, 2, 2, 4, 1, 2, 1, 1, 2, 1, 2],
	[1, 2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1],
	[2, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2, 1],
	[1, 2, 4, 1, 2, 1, 2, 2, 1, 2, 2, 1], 
	[2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 2],   /* 2051 */
	[1, 2, 1, 1, 2, 1, 1, 5, 2, 2, 2, 2],
	[1, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 2],
	[1, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2],
	[1, 2, 2, 1, 2, 4, 1, 1, 2, 1, 2, 1],
	[2, 2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2],
	[1, 2, 2, 1, 2, 1, 2, 2, 1, 1, 2, 1],
	[2, 1, 2, 4, 2, 1, 2, 1, 2, 2, 1, 1],
	[2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 2, 1],
	[2, 1, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1],
	[2, 2, 3, 2, 1, 1, 2, 1, 2, 2, 2, 1],   /* 2061 */
	[2, 2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1],
	[2, 2, 1, 2, 1, 2, 3, 2, 1, 2, 1, 2],
	[2, 2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1],
	[2, 2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2],
	[1, 2, 1, 2, 5, 2, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1],
	[2, 1, 2, 1, 1, 2, 2, 1, 2, 2, 1, 2],
	[1, 2, 1, 5, 1, 2, 1, 2, 2, 2, 1, 2],
	[2, 1, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2],
	[2, 1, 2, 1, 2, 1, 1, 5, 2, 1, 2, 2],   /* 2071 */
	[2, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2],
	[2, 1, 2, 2, 1, 2, 1, 1, 2, 1, 2, 1],
	[2, 1, 2, 2, 1, 5, 2, 1, 2, 1, 2, 1],
	[2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 2, 1, 2, 1, 2, 2, 1, 2, 1],
	[2, 1, 2, 3, 2, 1, 2, 2, 2, 1, 2, 1],
	[2, 1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2],
	[1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2],
	[2, 1, 5, 2, 1, 1, 2, 1, 2, 1, 2, 2],
	[1, 2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2],   /* 2081 */
	[1, 2, 2, 2, 1, 2, 3, 2, 1, 1, 2, 2],
	[1, 2, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1],
	[2, 1, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2],
	[1, 2, 1, 1, 6, 1, 2, 2, 1, 2, 1, 2],
	[1, 2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1],
	[2, 1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2],
	[1, 2, 1, 5, 1, 2, 1, 1, 2, 2, 2, 1],
	[2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1],
	[2, 2, 2, 1, 2, 1, 1, 5, 1, 2, 2, 1],
	[2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1],   /* 2091 */
	[2, 2, 1, 2, 2, 1, 2, 1, 2, 1, 2, 1],
	[1, 2, 2, 1, 2, 4, 2, 1, 2, 1, 2, 1],
	[2, 1, 1, 2, 1, 2, 2, 1, 2, 2, 1, 2],
	[1, 2, 1, 1, 2, 1, 2, 1, 2, 2, 2, 1],
	[2, 1, 2, 3, 2, 1, 1, 2, 2, 2, 1, 2],
	[2, 1, 2, 1, 1, 2, 1, 1, 2, 2, 1, 2],
	[2, 2, 1, 2, 1, 1, 2, 1, 1, 2, 1, 2],
	[2, 5, 2, 2, 1, 1, 2, 1, 1, 2, 1, 2],
	[2, 2, 1, 2, 1, 2, 1, 2, 1, 1, 2, 1],
	[2, 2, 1, 2, 2, 1, 5, 2, 1, 1, 2, 1]];
	
	
	/* 양력 <-> 음력 변환 함수
	* type : 1 - 양력 -> 음력
	*        2 - 음력 -> 양력
	* leapmonth : 0 - 평달
	*             1 - 윤달 (type = 2 일때만 유효)
	*/ 
	function lunarCalc(year, month, day, type, leapmonth){
		var solYear, solMonth, solDay;
		var lunYear, lunMonth, lunDay;
		var lunLeapMonth, lunMonthDay; 
		var i, lunIndex;
		var solMonthDay = [31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
		/* range check */
		if (year < 1800 || year > 2101)
		{
			alert('1800년부터 2101년까지만 지원합니다');
			return;
		}
		/* 속도 개선을 위해 기준 일자를 여러개로 한다 */
		if (year >= 2080)
		{
			/* 기준일자 양력 2080년 1월 1일 (음력 2079년 12월 10일) */
			solYear = 2080;
			solMonth = 1;
			solDay = 1;
			lunYear = 2079;
			lunMonth = 12;
			lunDay = 10;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 2080 년 2월 28일 */
			lunMonthDay = 30; /* 2079년 12월 */
		}
		else if (year >= 2060)
		{
			/* 기준일자 양력 2060년 1월 1일 (음력 2059년 11월 28일) */
			solYear = 2060;
			solMonth = 1;
			solDay = 1;
			lunYear = 2059;
			lunMonth = 11;
			lunDay = 28;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 2060 년 2월 28일 */
			lunMonthDay = 30; /* 2059년 11월 */
		}
		else if (year >= 2040)
		{
			/* 기준일자 양력 2040년 1월 1일 (음력 2039년 11월 17일) */
			solYear = 2040;
			solMonth = 1;
			solDay = 1;
			lunYear = 2039;
			lunMonth = 11;
			lunDay = 17;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 2040 년 2월 28일 */
			lunMonthDay = 29; /* 2039년 11월 */
		}
		else if (year >= 2020)
		{
			/* 기준일자 양력 2020년 1월 1일 (음력 2019년 12월 7일) */
			solYear = 2020;
			solMonth = 1;
			solDay = 1;
			lunYear = 2019;
			lunMonth = 12;
			lunDay = 7;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 2020 년 2월 28일 */
			lunMonthDay = 30; /* 2019년 12월 */
		}
		else if (year >= 2000)
		{
			/* 기준일자 양력 2000년 1월 1일 (음력 1999년 11월 25일) */
			solYear = 2000;
			solMonth = 1;
			solDay = 1;
			lunYear = 1999;
			lunMonth = 11;
			lunDay = 25;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 2000 년 2월 28일 */
			lunMonthDay = 30; /* 1999년 11월 */
		}
		else if (year >= 1980)
		{
			/* 기준일자 양력 1980년 1월 1일 (음력 1979년 11월 14일) */
			solYear = 1980;
			solMonth = 1;
			solDay = 1;
			lunYear = 1979;
			lunMonth = 11;
			lunDay = 14;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 1980 년 2월 28일 */
			lunMonthDay = 30; /* 1979년 11월 */
		}
		else if (year >= 1960)
		{
			/* 기준일자 양력 1960년 1월 1일 (음력 1959년 12월 3일) */
			solYear = 1960;
			solMonth = 1;
			solDay = 1;
			lunYear = 1959;
			lunMonth = 12;
			lunDay = 3;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 1960 년 2월 28일 */
			lunMonthDay = 29; /* 1959년 12월 */
		}
		else if (year >= 1940)
		{
			/* 기준일자 양력 1940년 1월 1일 (음력 1939년 11월 22일) */
			solYear = 1940;
			solMonth = 1;
			solDay = 1;
			lunYear = 1939;
			lunMonth = 11;
			lunDay = 22;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 1940 년 2월 28일 */
			lunMonthDay = 29; /* 1939년 11월 */
		}
		else if (year >= 1920)
		{
			/* 기준일자 양력 1920년 1월 1일 (음력 1919년 11월 11일) */
			solYear = 1920;
			solMonth = 1;
			solDay = 1;
			lunYear = 1919;
			lunMonth = 11;
			lunDay = 11;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 1920 년 2월 28일 */
			lunMonthDay = 30; /* 1919년 11월 */
		}
		else if (year >= 1900)
		{
			/* 기준일자 양력 1900년 1월 1일 (음력 1899년 12월 1일) */
			solYear = 1900;
			solMonth = 1;
			solDay = 1;
			lunYear = 1899;
			lunMonth = 12;
			lunDay = 1;
			lunLeapMonth = 0;
			solMonthDay[1] = 28; /* 1900 년 2월 28일 */
			lunMonthDay = 30; /* 1899년 12월 */
		}
		else if (year >= 1880)
		{
			/* 기준일자 양력 1880년 1월 1일 (음력 1879년 11월 20일) */
			solYear = 1880;
			solMonth = 1;
			solDay = 1;
			lunYear = 1879;
			lunMonth = 11;
			lunDay = 20;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 1880 년 2월 28일 */
			lunMonthDay = 30; /* 1879년 11월 */
		}
		else if (year >= 1860)
		{
			/* 기준일자 양력 1860년 1월 1일 (음력 1859년 12월 9일) */
			solYear = 1860;
			solMonth = 1;
			solDay = 1;
			lunYear = 1859;
			lunMonth = 12;
			lunDay = 9;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 1860 년 2월 28일 */
			lunMonthDay = 30; /* 1859년 12월 */
		}
		else if (year >= 1840)
		{
			/* 기준일자 양력 1840년 1월 1일 (음력 1839년 11월 27일) */
			solYear = 1840;
			solMonth = 1;
			solDay = 1;
			lunYear = 1839;
			lunMonth = 11;
			lunDay = 27;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 1840 년 2월 28일 */
			lunMonthDay = 30; /* 1839년 11월 */
		}
		else if (year >= 1820)
		{
			/* 기준일자 양력 1820년 1월 1일 (음력 1819년 11월 16일) */
			solYear = 1820;
			solMonth = 1;
			solDay = 1;
			lunYear = 1819;
			lunMonth = 11;
			lunDay = 16;
			lunLeapMonth = 0;
			solMonthDay[1] = 29; /* 1820 년 2월 28일 */
			lunMonthDay = 30; /* 1819년 11월 */
		}
		else if (year >= 1800)
		{
			/* 기준일자 양력 1800년 1월 1일 (음력 1799년 12월 7일) */
			solYear = 1800;
			solMonth = 1;
			solDay = 1;
			lunYear = 1799;
			lunMonth = 12;
			lunDay = 7;
			lunLeapMonth = 0;
			solMonthDay[1] = 28; /* 1800 년 2월 28일 */
			lunMonthDay = 30; /* 1799년 12월 */
		}
		lunIndex = lunYear - 1799;
		while (true)
		{
			if (type == 1 &&
			year == solYear &&
			month == solMonth &&
			day == solDay)
			{
			return new myDate(lunYear, lunMonth, lunDay, lunLeapMonth);
			} 
		else if (type == 2 &&
		year == lunYear &&
		month == lunMonth &&
		day == lunDay && 
		leapmonth == lunLeapMonth)
		{
			return new myDate(solYear, solMonth, solDay, 0);
		}
			
		/* add a day of solar calendar */
		if (solMonth == 12 && solDay == 31){
			solYear++;
			solMonth = 1;
			solDay = 1;
			/* set monthDay of Feb */
			if (solYear % 400 == 0)
				solMonthDay[1] = 29;
			else if (solYear % 100 == 0)
				solMonthDay[1] = 28;
			else if (solYear % 4 == 0)
				solMonthDay[1] = 29;
			else
				solMonthDay[1] = 28;
		}else if (solMonthDay[solMonth - 1] == solDay){
			solMonth++;
			solDay = 1; 
		}else
			solDay++;
		
		/* add a day of lunar calendar */
		if (lunMonth == 12 &&
		((lunarMonthTable[lunIndex][lunMonth - 1] == 1 && lunDay == 29) ||
		(lunarMonthTable[lunIndex][lunMonth - 1] == 2 && lunDay == 30)))
		{
			lunYear++;
			lunMonth = 1;
			lunDay = 1;
			if (lunYear > 2101) {
				alert("입력하신 날 또는 달은 없습니다. 다시 입력하시기 바랍니다.");
			break;
		}
		lunIndex = lunYear - 1799;
		if (lunarMonthTable[lunIndex][lunMonth - 1] == 1)
			lunMonthDay = 29;
		else if (lunarMonthTable[lunIndex][lunMonth - 1] == 2)
			lunMonthDay = 30;
		}else if (lunDay == lunMonthDay){
			
			if (lunarMonthTable[lunIndex][lunMonth - 1] >= 3&& lunLeapMonth == 0){
				lunDay = 1;
				lunLeapMonth = 1;
			}else{
				lunMonth++;
				lunDay = 1;
				lunLeapMonth = 0;
			}
			if (lunarMonthTable[lunIndex][lunMonth - 1] == 1)
				lunMonthDay = 29;
			else if (lunarMonthTable[lunIndex][lunMonth - 1] == 2)
				lunMonthDay = 30;
			else if (lunarMonthTable[lunIndex][lunMonth - 1] == 3)
				lunMonthDay = 29;
			else if (lunarMonthTable[lunIndex][lunMonth - 1] == 4 &&
				lunLeapMonth == 0)
				lunMonthDay = 29;
			else if (lunarMonthTable[lunIndex][lunMonth - 1] == 4 &&
				lunLeapMonth == 1)
				lunMonthDay = 30;
			else if (lunarMonthTable[lunIndex][lunMonth - 1] == 5 &&
				lunLeapMonth == 0)
				lunMonthDay = 30;
			else if (lunarMonthTable[lunIndex][lunMonth - 1] == 5 &&
				lunLeapMonth == 1)
				lunMonthDay = 29;
			else if (lunarMonthTable[lunIndex][lunMonth - 1] == 6)
				lunMonthDay = 30;
		}else
			lunDay++;
		}
	}
	
	function memorialDayCheck(solarDate, lunarDate){
		var i;
		var memorial;
		
		for (i = 0; i < memorialDays.length; i++){
			if (memorialDays[i].month == solarDate.month &&
			memorialDays[i].day == solarDate.day &&
			memorialDays[i].solarLunar == 1)
			return memorialDays[i];
			
			//윤달의 공휴일 처리에 대한 예외처리 
			if ( lunarDate.leapMonth && lunarDate.month == 4 && lunarDate.day == 8 ) {
				return null;
			}
			if ( lunarDate.leapMonth && lunarDate.month == 8 && lunarDate.day > 13 && lunarDate.day < 17) {
				return null;
			}
			if (memorialDays[i].month == lunarDate.month &&	memorialDays[i].day == lunarDate.day &&	memorialDays[i].solarLunar == 2 &&!memorialDays[i].leapMonth){
				return memorialDays[i];	
			}
		}
		return null;
	}

	
	function yearmemorialDayCheck(solarDate, lunarDate){
		var i;
		var yearmemorial;
		
		for (i = 0; i < yearmemorialDays.length; i++){
			
			if (yearmemorialDays[i].year == solarDate.year &&yearmemorialDays[i].month == solarDate.month &&yearmemorialDays[i].day == solarDate.day &&yearmemorialDays[i].solarLunar == 1){
				return yearmemorialDays[i];	
			}
			if (yearmemorialDays[i].year == lunarDate.year &&yearmemorialDays[i].month == lunarDate.month &&yearmemorialDays[i].day == lunarDate.day &&yearmemorialDays[i].solarLunar == 2 &&
			!yearmemorialDays[i].leapMonth){
				return yearmemorialDays[i];	
			}
		}
		return null;
	}
	
	/* 이곳에 이전달, 다음달로 넘어가는 함수를 입력한다 */
	function trim(str) {
		return str.replace(/(^\s+)|(\s+$)/g,"");
	}
	
	function prevMonth(){
		var yearTmp = parseInt(document.getElementById('yearSelect').value, 10);
		var monthTmp = parseInt(document.getElementById('monthSelect').value, 10);
		if (yearTmp >= 1800 && monthTmp >= 1) {
			var date = new Date(yearTmp, monthTmp - 2);
			document.getElementById('yearSelect').value = date.getFullYear();
			document.getElementById('monthSelect').value = date.getMonth() + 1;
			
			setCalendar(date.getFullYear(), date.getMonth() + 1);
		}
	}
	
	function nextMonth(){
	
		var yearTmp = parseInt(document.getElementById('yearSelect').value, 10);
		var monthTmp = parseInt(document.getElementById('monthSelect').value, 10);
		
		if (yearTmp <= 2101 && monthTmp <= 12) { 
			var date = new Date(yearTmp, monthTmp);
			
			document.getElementById('yearSelect').value = date.getFullYear();
			document.getElementById('monthSelect').value = date.getMonth() + 1;
			
			setCalendar(date.getFullYear(), date.getMonth() + 1);
		}
	}
	
	function currentMonth(){

		var yearTmp = parseInt(document.getElementById('yearSelect').value, 10);
		var monthTmp = parseInt(document.getElementById('monthSelect').value, 10);
		
		if (yearTmp >= 1800 && monthTmp >= 1 && yearTmp <= 2101 && monthTmp <= 12) { 
			var todayDate = new Date();
			
			document.getElementById('yearSelect').value = todayDate.getFullYear();
			document.getElementById('monthSelect').value = todayDate.getMonth() + 1;
			setCalendar(todayDate.getFullYear(), todayDate.getMonth() + 1);
		}
	}
	
	
	function setCalendar(year, month){
	
		var i;
		var oYearSelect = document.getElementById('yearSelect');
		var oMonthSelect = document.getElementById('monthSelect');
		if (!year){
			year = oYearSelect.value;
			month = oMonthSelect.value;
		}else{
			for (i = 0; i < oYearSelect.length; i++){
				if (oYearSelect[i].value == year){
					oYearSelect.selectedIndex = i;
					break;
				}	
			}
		
			for (i = 0; i < oMonthSelect.length; i++){
				if (oMonthSelect[i].value == month){
					oMonthSelect.selectedIndex = i;
					break;				
				}
			}
		}
		
		var monthDay = Array(31, 0, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
		/* set monthDay of Feb */
		if (year % 400 == 0)
			monthDay[1] = 29;
		else if (year % 100 == 0)
			monthDay[1] = 28;
		else if (year % 4 == 0)
			monthDay[1] = 29;
		else
			monthDay[1] = 28;
		/* set the day before 설날 */
		if (lunarMonthTable[year - 1 - 1799][11] == 1)
			memorialDays[1].day = 29;
		else if (lunarMonthTable[year - 1 - 1799][11] == 2)
			memorialDays[1].day = 30;
		var date = new Date(year, month - 1, 1);
		var startWeekday = date.getDay();
		/* clean all day cell */
		for (i = 0; i < 42; i++){
			document.getElementById('dayCell' + i).innerHTML = "";
			document.getElementById('dayCell' + i).bgColor = "FFFFF";
			document.getElementById('userCell' + i).value = "";
			document.getElementById('nowCell' + i).value = "";
		}
		/* fill day cell */  
		for (i = 0; i < monthDay[month - 1]; i ++){
			var index = startWeekday + i;
			var dayHTML;
			var userHTML;
			var nowHTML;
			var solarDate = new myDate(year, month, i + 1);
			var lunarDate = lunarCalc(year, month, i + 1, 1);
			var enMonthName = new Array('January','February','March','April','May','June',
			'July','August','September','October','November','December');
// 			curenMonth.innerHTML = enMonthName[solarDate.month - 1];
			/* memorial day */
			var memorial = memorialDayCheck(solarDate, lunarDate);
			/* 쉬지않는 기념일 */
			var memorialDay = false;
			if (memorial && memorial.holiday == false)
			memorialDay = true;
			
			var yearmemorial = yearmemorialDayCheck(solarDate, lunarDate);
			/* 쉬지않는 기념일 */
			var yearmemorialDay = false;
			if (yearmemorial && yearmemorial.holiday == false)
			yearmemorialDay = true;
			
 			dayHTML = "<p align=left><font id=ln2 color='COLOR'>HIGHLIGHT_START" + ( i + 1 ) + "</P>";
			userHTML = "TYPE";
			nowHTML =  i + 1 ;
			
			//현재날짜 세팅
			var cToday = new Date();
			var cYear = cToday.getFullYear();
			var cMonth = cToday.getMonth()+1;
			var cDate = cToday.getDate();
			var chkDate;

			//선택날짜 
			var	chkSolarDate = new Date(solarDate.year,solarDate.month-1,solarDate.day);
 			var	chkSolarDate = chkSolarDate.getTime();
			
 			//달력 Type에 따라 선택가능날짜 변경
 			//type == M , 메세지관련 달력
 			//type == R , 사전예약 달력 
 			if(calendarType=="M"){

				chkDate = new Date(cYear,cMonth-1,cDate+5);
				chkDate = chkDate.getTime();
				
				if( chkSolarDate < chkDate ){	//오늘기준 +5일 처리
					dayHTML = dayHTML.replace("COLOR", "#EEEEE");
					userHTML = userHTML.replace("TYPE","N");
				}else if ((memorial && memorial.holiday) || (yearmemorial && yearmemorial.holiday) || index % 7 == 0){	//일요일이나 공휴일 처리
					dayHTML = dayHTML.replace("COLOR", "red");
					userHTML = userHTML.replace("TYPE","N");
				}else if (index % 7 == 6){	//토요일 처리
					dayHTML = dayHTML.replace("COLOR", "#0099FF");
					userHTML = userHTML.replace("TYPE","N");
				}else{	//평일
					userHTML = userHTML.replace("TYPE","Y");
				}
 				
 			}else{
 				
 				//권한별 설정 ( MA,DV,CC1,CC2 계정은 현재-1달+휴일없이 선택가능 )	
 				if(userAuth=="MA" || userAuth=="DV" || userAuth=="CC1" || userAuth=="CC2" ){
 					
 					chkDate = new Date(cYear,cMonth-2,cDate);
 					chkDate = chkDate.getTime();
 					
 					if( chkSolarDate < chkDate ){	//오늘기준 한달이전 처리
 						dayHTML = dayHTML.replace("COLOR", "#EEEEE");
 						userHTML = userHTML.replace("TYPE","N");
 					}else if ((memorial && memorial.holiday) || (yearmemorial && yearmemorial.holiday) || index % 7 == 0){	//일요일이나 공휴일 처리
 						dayHTML = dayHTML.replace("COLOR", "red");
 						userHTML = userHTML.replace("TYPE","Y");
 					}else if (index % 7 == 6){	//토요일 처리
 						dayHTML = dayHTML.replace("COLOR", "#0099FF");
 						userHTML = userHTML.replace("TYPE","Y");
 					}else{	//평일
 						userHTML = userHTML.replace("TYPE","Y");
 					}
 				
 				}else{

 					var chkDate = new Date(cYear,cMonth-1,cDate);
 					var chkDate = chkDate.getTime();
 					
 					if( chkSolarDate < chkDate ){	//오늘기준 이전날짜 처리
 						dayHTML = dayHTML.replace("COLOR", "#EEEEE");
 						userHTML = userHTML.replace("TYPE","N");
 					}else if ((memorial && memorial.holiday) || (yearmemorial && yearmemorial.holiday) || index % 7 == 0){	//일요일이나 공휴일 처리
 						dayHTML = dayHTML.replace("COLOR", "red");
 						userHTML = userHTML.replace("TYPE","N");
 					}else if (index % 7 == 6){	//토요일 처리
 						dayHTML = dayHTML.replace("COLOR", "#0099FF");
 						userHTML = userHTML.replace("TYPE","N");
 					}else{
 						userHTML = userHTML.replace("TYPE","Y");
 					}
 				}
 				
 			}
 			
			

			
			
			//겹치지 않는 기념일
			if (memorial)
				dayHTML = dayHTML.replace("MEMO", memorial.name);
			if (yearmemorial)
				dayHTML = dayHTML.replace("MEMO", yearmemorial.name);
			
			if (todayDate.getFullYear() == year &&todayDate.getMonth() + 1 == month &&todayDate.getDate() == i + 1){
				dayHTML = dayHTML.replace("HIGHLIGHT_START", "<strong>");
				dayHTML = dayHTML.replace("HIGHLIGHT_END", "</strong>");
				document.getElementById('dayCell' + index).bgColor = "#DDFFFF";
			}
			
			dayHTML = dayHTML.replace("COLOR", "");
			dayHTML = dayHTML.replace("HIGHLIGHT_START", "");
			dayHTML = dayHTML.replace("HIGHLIGHT_END", "");
			document.getElementById('dayCell' + index).innerHTML = dayHTML;
			document.getElementById('userCell' + index).value = userHTML;
			document.getElementById('nowCell' + index).value = nowHTML;
		}
	}
	
	
	function goApply(){
		var choiceDate = document.getElementById("choiceDate").value;
		opener.document.frm.rsrv_dt.value=choiceDate;

		window.close();
	}	
	

</script>

<form name="frm" id="frm" method="POST">
<input type="hidden" name="choiceDate" id="choiceDate" >
	<table width=330 border=0 cellpadding=0 cellspacing=0>
		<tr>
			<td nowrap valign=top>
			<!--------달력 들어갈곳----->
			<table border=0 cellpadding=0 cellspacing=0 width=100%>
				<tr>
					<td>
						<table border=0 cellpadding=0 cellspacing=0 width=100%>
							<tr>
								<td rowspan=2 width=1 nowrap bgcolor=ffffff></td>
								<td width="1%" bgcolor=#FFFFFF></td>
							</tr>
							<tr>
								<td height=1 bgcolor=#FFFFFF></td>
							</tr>
							<tr>
								<td width="97%" bgcolor=#C0C0C0 height=32 align=center bordercolorlight="#000000" bordercolordark="#000000">
								<!-----날짜 넣는 곳--->
								<table border=0 cellpadding=0 cellspacing=0>
									<tr>
										<td nowrap>
											<input TYPE="button" VALUE="◀"	onClick="javascript:prevMonth();" class="10">
											<!--------년도---------->
											<select id=yearSelect style='font-family:굴림;font-size:12px;color:black' onChange='setCalendar()'>
											<script type=text/javascript>
												for (var i = 2013, selectStr = ""; i <= 2020; i++)
												selectStr += "<option value='" + i + "'>" + i + " 년</option>";
												selectStr += "</select>";
												document.write(selectStr);
											</script>
											<!--------년도---------->   
											<!--------월---------->
											</select><select id=monthSelect style='font-family:굴림;font-size:12px;color:black' id=monthSelect onChange='setCalendar()'>
											<script type=text/javascript>
												for (var i = 1, selectStr = ""; i <= 12; i++){
													if(i < 10){
														selectStr += "<option value='0" + i + "'>" + "0"+i + " 월</option>";
													}else{
														selectStr += "<option value='" + i + "'>" + i + " 월</option>";	
													}
												}
													
												selectStr += "</select>";
												document.write(selectStr);	
												
											</script>
											<!--------월---------->  
											<input TYPE="button" VALUE="▶"	onClick="javascript:nextMonth();" class="10">
											</select>
										</td>
									<td ailgn=right>
									<p align="center">&nbsp;<b><font color=#009900></font></b></td>
									</tr>
								</table>
								<!-----날짜 넣는 곳--->
								</td>
							</tr>
							<tr>
								<td width="97%" height=1 border=0 bgcolor="#000000" bordercolorlight="#000000" bordercolordark="#000000"></td>
							</tr>
							<tr>
								<td width="97%" bgcolor=ffffff align=center bordercolorlight="#000000" bordercolordark="#000000">
									<!----달력 넣는곳------>
									<table border=1 cellpadding=0 cellspacing=0 width=100% bordercolor="#E9E9E9">
										<tr><td colspan=7 height=7 nowrap></td></tr>
										<tr>
											<td width=15% align=center><font id=ln6 color=red>일</font></td>
											<td width=14% align=center><font id=ln6>월</font></td>
											<td width=14% align=center><font id=ln6>화</font></td>
											<td width=14% align=center><font id=ln6>수</font></td>
											<td width=14% align=center><font id=ln6>목</font></td>
											<td width=14% align=center><font id=ln6>금</font></td>
											<td width=15% align=center><font id=ln6 color=#0099FF>토</font></td>
										</tr>
										<tr><td colspan=7 height=7 nowrap></td></tr>
										
										<script type="text/javascript">
										
											function chkVal(v){
												
												var chkYN = document.getElementById("userCell"+v).value;
												var nowYear = document.getElementById("yearSelect").value;
												var nowMonth = document.getElementById("monthSelect").value;
												var nowDay = document.getElementById("nowCell"+v).value;
												
												for(k = 0 ; k < 42 ; k++){
													document.getElementById("dayCell"+k).bgColor="#FFFFF";
												}
												
												if(chkYN == 'Y'){
													document.getElementById("dayCell"+v).bgColor="#DDFFFF";
												}
												
												if(nowDay<10){
													nowDay = "0"+nowDay;
												}
												
												var choiceDate = nowYear+"-"+nowMonth+"-"+nowDay;
 												document.getElementById("choiceDate").value = choiceDate;
											}
												
											for (i = 0; i < 6; i++){
												document.write("<tr height='45'>");
												for (j = 0; j < 7; j++){
													document.write("<td vAlign='top' cellSpacing='1' id='dayCell" + ( i * 7 + j )+ "' style='cursor:hand' onClick='chkVal("+( i * 7 + j )+");'></td>");
													document.write("<input type='hidden' id='userCell" + ( i * 7 + j )+ "'>");
													document.write("<input type='hidden' id='nowCell" + ( i * 7 + j )+ "'>");
												}
												document.write("</tr>");
											}
										</script>
										<tr><td colspan=7 height=7 nowrap></td></tr>
									</table>  
									<!----달력 넣는곳------>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
			<table>
				<tr>
					<td width="5%">&nbsp;</td>
					<td width="90%" class="red" style="font-size:13px">
					<br>
					&nbsp;* 발송 예약일은 발송 신청일 5일 이후 <br>&nbsp;&nbsp;&nbsp;평일(공휴일 주말 제외)만 선택이 가능합니다.<br><br>
					&nbsp;* 발송 일시는 승인 단계에서 조정 될 수 있으니 <br>&nbsp;&nbsp;&nbsp;승인완료시에 메시지 발송 일시를 확인하시기 <br>&nbsp;&nbsp;&nbsp;바랍니다.<br>&nbsp;
					</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
</form>

</div>

<div class="pBottom" style="width:350px;">
	<a href="#" onclick="goApply();" class="btn_red"><strong>선 택</strong></a> <a href="#" class="btn_red" onClick="self.close();" ><strong>닫 기</strong></a>
</div>    

<script type="text/javascript">
	//HTML생성 후 현재날짜 기준으로 달력 출력
	currentMonth();
</script>