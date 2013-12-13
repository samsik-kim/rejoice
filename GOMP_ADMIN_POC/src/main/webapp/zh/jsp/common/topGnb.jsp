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
		<h1><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp" title="Whoopy Main"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/amart_logo.gif'/>" alt="Whoopy" /></a></h1>
		<h2 class="hide">User Menu Area</h2>
		<ul id="utility"> 
			<c:choose>
				<c:when test="${empty MEMBER_SESSION}">
					<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/login/loginMain.omp" >登入</a></li>
					<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/member/registMain.omp" >註冊會員</a></li>
				</c:when>
				<c:otherwise>
					<li><span class="txtcolor03 font_en txt_bold">${MEMBER_SESSION.mbrId}</span><strong>用戶</strong></li>
					<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/logout/logout.omp" >登出</a></li>
					<li><a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/mypage/mypageIntro.omp">會員中心</a></li>
				</c:otherwise>
			</c:choose>
			<li class="lat"><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/community/newQna.omp">客戶諮詢</a></li>
		</ul>
	
		<!-- 상단주메뉴(GNB) S -->
		<h2 class="hide">주메뉴영역</h2>
		<div id="gnb">
			<ul>
				<li class="frt"><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/content/contentsSubMain.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/gnb01.gif'/>" alt="產品上架/管理" /></a></li>
				<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/settlement/dailysale/dailySaleList.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/gnb02.gif'/>" alt="產品販售/結算" /></a></li>
				<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/community/memberGuide.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/gnb03.gif'/>" alt="開發商支援中心" /></a></li>
			</ul>
		</div>  
		<!-- //상단주메뉴(GNB) E -->
	</div>
	
	<c:choose>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/main/main.omp')}">
		<div id="visual">
			<div id="visual_area">
				<p class="pad_t64"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/main_visual_txt01.jpg'/>" alt="your easy-to-use app mart - Developer center Whoopy grand open!" /></p>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<div id="header_visual">
			<c:if test="${fn:contains(pageContext.request.requestURI, '/content/')}"><div id="header_visual_area" class="visual_pm"></c:if>
			<c:if test="${fn:contains(pageContext.request.requestURI, '/community/')}"><div id="header_visual_area" class="visual_uc"></c:if>
			<c:if test="${fn:contains(pageContext.request.requestURI, '/settlement/')}"><div id="header_visual_area" class="visual_sl"></c:if>
			<c:if test="${fn:contains(pageContext.request.requestURI, '/member/')}"><div id="header_visual_area" class="visual_ut"></c:if>
			<c:if test="${fn:contains(pageContext.request.requestURI, '/login/')}"><div id="header_visual_area" class="visual_ip"></c:if>
			<c:if test="${fn:contains(pageContext.request.requestURI, '/mypage/')}"><div id="header_visual_area" class="visual_mp"></c:if>
			<c:if test="${fn:contains(pageContext.request.requestURI, '/main/')}"><div id="header_visual_area" class="visual_ip"></c:if>
			<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/visual_txt.gif'/>" alt="Whoopy, 讓您的夢想成真!" /></p>
			</div>
		</div>
	</c:otherwise>
	</c:choose>

</div>
