<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires", 0);
	if(request.getProtocol().equals("HTTP/1.1")){
		response.addHeader("Cache-Control","no-cache");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Expires" content="-1"/> 
<meta http-equiv="Pragma" content="no-cache"/> 
<meta http-equiv="Cache-Control" content="no-cache"/>

<link href="<c:url value='/${ThreadSession.serviceLocale.language}/css/content.css'/>" rel="stylesheet" type="text/css" />
<link type="text/css" href="<c:url value="/js/jquery/ui/css/smoothness/jquery-ui-1.8.10.custom.css"/>" rel="stylesheet" />
<!-- script src="http://jquery-ui.googlecode.com/svn/tags/latest/external/jquery.bgiframe-2.1.2.js" type="text/javascript"></script -->

<script type="text/javascript" src="<c:url value="/js/jquery/1.5.1/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery-ui-1.8.10.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/jsTree/jquery.jstree.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.cookie.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.metadata.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.MultiFile.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.selectbox-1.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/validate.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/content.js"/>"></script>

<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/dev.common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/xtractor_cookie.js'/>"></script>

<script type="text/javascript">
//<![CDATA[
	$(function() {
		// datepicker localizations
		$.datepicker.setDefaults($.datepicker.regional['ko']);
		// $.datepicker.setDefaults($.datepicker.regional['zh-TW']);
		$.datepicker.setDefaults({dateFormat: 'yy-mm-dd'});
		// datepicker input right img create
		$.datepicker.setDefaults({showOn:'both', buttonImage:'<c:url value="/images/pm/btn_cal.gif"/>', buttonImageOnly:true});
	});
//]]>
</script>

<c:set var="uri" value="${pageContext.request.requestURI}"/>
<c:set var="mainMenuId" value="${param.mainMenuId}"/>
<c:choose>
	<c:when test="${fn:contains(uri, '/content/')}">	<!-- Content Register/Management -->
		<c:set var="mainMenuId" value="1"/>
		<script type="text/javascript" src="<c:url value='/js/content.management.js'/>"></script>
	</c:when>   
	<c:when test="${fn:contains(uri, '/uri 기술 필요/')}">		<!-- 판매정산/관리 -->
		<c:set var="mainMenuId" value="2"/>
	</c:when>
	<c:when test="${fn:contains(uri, '/uri 기술 필요/')}">		<!-- 개발지원센터 -->
		<c:set var="mainMenuId" value="3"/>
	</c:when>
	<c:when test="${fn:contains(uri, '/uri 기술 필요/')}">		<!-- 고객지원 -->
		<c:set var="mainMenuId" value="4"/>
	</c:when>
	<c:when test="${fn:contains(uri, '/mypage/')}">				<!-- 마이페이지 -->
		<c:set var="mainMenuId" value="5"/>
	</c:when>
	<c:when test="${fn:contains(uri, '/login/find')}">			<!-- 아이디/패스워드 찾기 -->
		<c:set var="mainMenuId" value="6"/>
	</c:when>
</c:choose>

<script>
$(document).ready(function(){
	setContextPath("${pageContext.request.contextPath }");
});
</script>
<style>
.dimmed {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	background: #d7d7d7;
	filter:alpha(opacity=40); 
	-moz-opacity: 0.4; 
	opacity: 0.4; 
	-khtml-opacity: 0.4;
}
.ui-datepicker-trigger{ margin-left:3px;cursor:pointer; }
.ui-datepicker { width:200px; }
</style>
<title><decorator:title default="T store 개발자센터" /></title>
<link rel="shortcut icon" href="<c:url value='/images/ico/win_icon_developers.ico'/>">
<decorator:head />
</head>

<body> 
<div id="wrap"> 
	<!-- Logo & Usermenu -->
	<div id="header">			
		<!-- top gnb -->
		<page:applyDecorator page="/${ThreadSession.serviceLocale.language}/jsp/common/topGnb.jsp?menuId=${mainMenuId}" name="empty" />  
	</div>
	<hr />

	<c:choose>
		<c:when test="${empty mainMenuId }">
			<decorator:body />
		</c:when>
		<c:otherwise>
			<!-- Container S-->
			<div id="container" class="none_bg">
			
				<!-- Content Area S -->
				<h2 class="hide">본문영역</h2>
				<decorator:body />
				<!-- Content Area E -->
				
			</div>
			<hr />
			<!-- //Container E-->
		</c:otherwise>
	</c:choose>

	<h2 class="hide">카피라이터영역</h2>
	<div id="footer_wrap">
		<page:applyDecorator page="/${ThreadSession.serviceLocale.language}/jsp/common/footer.jsp" name="empty" />
	</div>
	<hr />
</div>

<div id="bodyFrame"></div>
<div id="layerArea">
</div>
</body>
</html>