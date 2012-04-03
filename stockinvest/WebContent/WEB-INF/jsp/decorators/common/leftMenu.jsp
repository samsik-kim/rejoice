<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="uri" value="${pageContext.request.requestURI}"/>
<c:choose>
	<c:when test="${fn:contains(uri, '/admin/')}">
		<c:set var="leftMenuId" value="1"/>
	</c:when>   
	<c:when test="${fn:contains(uri, '/code/')}">		
		<c:set var="leftMenuId" value="2"/>
	</c:when>
	<c:when test="${fn:contains(uri, '/board/')}">
		<c:set var="leftMenuId" value="3"/>
	</c:when>
	<c:otherwise>
		<c:set var="leftMenuId" value="4"/>
	</c:otherwise>
</c:choose>
<script type="text/javascript">
var getLeftMenuId = '${leftMenuId}';
/*
 * Slide Menu
 */
$(document).ready(function() {
	pageLoadAjaxListInner("menuFrm", "innerBoardList", "/board/ajaxBoardListinner.do"); // 리스트 호출
	$('#leftmenu ul').hide();
 
	if (getLeftMenuId == '2') {
		$('#depth2').show();
	} else if (getLeftMenuId == '3') {
		$('#depth3').show();
	}
	
	$('#leftmenu li a').click(function() {
		var checkElement = $(this).next();
		if ((checkElement.is('ul')) && (checkElement.is(':visible'))) {
			$('#leftmenu ul:visible').slideUp('slow');
			return false;
		}
		if ((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
			checkElement.slideDown('slow');
			return false;
		}
	});
});
</script>
<form id="menuFrm" method="post">
</form>
<div id="innerBoardList"></div>