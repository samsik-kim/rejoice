<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.omp.commons.util.SessionUtil" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String sessionR = "";
if(null != SessionUtil.getAnySession(request, "sessionR")) sessionR = (String)SessionUtil.getAnySession(request, "sessionR");
%>
<c:set var="session" value="<%=sessionR %>"/>
<div id="main_header">
	<div id="main_header_area">
		<h1><a href="<c:url value='/main/main.omp'/>" title="Amart 메인으로 이동"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/amart_logo.gif'/>" alt="Amart" /></a></h1>
		<h2 class="hide">사용자메뉴영역</h2>
		<ul id="utility"> 
			<c:choose>
				<c:when test="${empty MEMBER_SESSION}">
					<li><a href="<c:url value='/login/loginMain.omp'/>" >로그인</a></li>
					<li><a href="<c:url value='/member/registMain.omp'/>" >회원가입</a></li>
				</c:when>
				<c:otherwise>
					<li>${MEMBER_SESSION.mbrId}&nbsp;님</li>
					<li><a href="<c:url value='/logout/logout.omp'/>" >로그아웃</a></li>
					<li><a href="<c:url value='/mypage/mypageIntro.omp'/>">마이페이지</a></li>
				</c:otherwise>
			</c:choose>
			<li class="lat"><a href="<c:url value='/community/newQna.omp'/>">Q&amp;A</a></li>
		</ul>
	
		<!-- 상단주메뉴(GNB) S -->
		<h2 class="hide">주메뉴영역</h2>
		<div id="gnb">
			<ul>
				<li class="frt"><a href="<c:url value='/content/contentsSubMain.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/gnb01.gif'/>" alt="상품등록" /></a></li>
				<li><a href="<c:url value='/settlement/dailysale/dailySaleList.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/gnb02.gif'/>" alt="판매/정산관리" /></a></li>
				<li><a href="<c:url value='/community/memberGuide.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/gnb03.gif'/>" alt="개발지원센터" /></a></li>
			</ul>
		</div>  
		<!-- //상단주메뉴(GNB) E -->
	</div>
	
	<c:choose>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/main/main.omp')}">
		<div id="visual">
			<div id="visual_area">
				<p class="pad_t64"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/main_visual_txt01.jpg'/>" alt="your easy-to-use app mart - Developer center Amart grand open!" /></p>
				<p class="fltl"><a href="<c:url value='/community/basicInfoGuide.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/main_btntxt01.gif'/>" alt="상품등록 가이드 보러가기" /></a></p>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div id="header_visual">
			<div id="header_visual_area">
				<p class="pad_t43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/visual_txt.gif'/>" alt="개발을 위한 모든것! Amart가 지원합니다" /></p>
			</div>
		</div>
	</c:otherwise>
	</c:choose>

</div>
