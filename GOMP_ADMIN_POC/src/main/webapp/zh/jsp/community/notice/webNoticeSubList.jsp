<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Whoopy Management System</title>
<link rel="stylesheet" href="<c:url value="/${ThreadSession.serviceLocale.language}/css/pcweb.css"/>" type="text/css">
<link rel="stylesheet" href="<c:url value="/${ThreadSession.serviceLocale.language}/css/base_pcweb.css"/>" type="text/css">
<style type="text/css"> 
.con_btm_01_if{float:left;width:358px;position:relative;}
.con_btm_01_if h3{height:15px;margin-bottom:10px;}
.notice{width:310px;}
.notice li{background:url('../images/pw/bul_gs.gif') no-repeat left 8px;height:18px; padding-left:8px;}
.notice li a.tit{color:#666;float:left;}
.notice li img{vertical-align:middle;}
.notice li span.date{color:#848484;float:right; font-size:11px;}
.con_btm_02{float:left;background:url('../images/pw/bg_con_btm_02.gif') no-repeat left top;width:270px;height:200px;padding-left:255px;font-size:0;line-height:0;}
a.m_btn02{position:absolute;top:0px;right:45px;}
</style>
<script type="text/javascript" src="<c:url value="/js/jquery/1.5.1/jquery.min.js"/>"></script>
<script type="text/javascript">
var	domain;
var	urlprefix;
domain	= (location.host.indexOf("dtstore") != -1 ? "dtstore.tw" : "whoopy.tw");
urlprefix		= location.protocol + "//www." + domain;
document.domain	= domain;
function goList(id){
	if(id=='list'){
		parent.location.href=urlprefix+"/www/webNoticeList.html";
	}else{
		parent.location.href=urlprefix+"/www/webNoticeList.html?id="+id;
	}
}

</script>
</head>
<body onload="parent.frameLoaded($('#wrap').outerHeight())">
<div id="wrap" class="con_btm_01_if">
	<h3><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/txt_notice.gif" alt="Whoopy Notice"></h3>
	<ul class="notice">
	<c:choose>
		<c:when test="${not empty noticeList}">
			<c:forEach items="${noticeList}" var="item" varStatus="status">
				<li><a href="javascript:goList('${item.noticeId}');" class="tit"><g:html value="${item.title}" /><c:if test="${item.newYn eq 'Y' }"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/icon_new.gif" alt="" /></c:if></a><span class="date"><g:html value="${item.insDttm}" format="L####-##-##" /></span></li>
			</c:forEach>
		</c:when>
	</c:choose>
	</ul>
	<a href="javascript:goList('list');" class="m_btn02"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/btn_more.gif" alt=" more" /></a>
</div>
</body>
</html>