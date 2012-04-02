<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<link rel="stylesheet" type="text/css" href="/resource/ets/sildmenu/sildmenu.css" />

<c:set var="uri" value="${pageContext.request.requestURI}"/>
<c:choose>
	<c:when test="${fn:contains(uri, '/admin/')}">
		<c:set var="leftMenuId" value="1"/>
	</c:when>   
	<c:when test="${fn:contains(uri, '/code/')}">		
		<c:set var="leftMenuId" value="2"/>
	</c:when>
	<c:when test="${fn:contains(uri, '/stockinvest/')}">
		<c:set var="leftMenuId" value="3"/>
	</c:when>
</c:choose>

<script type="text/javascript">
/*
 * Slide Menu
 */
function initMenu() {
	$('#leftmenu ul').hide();
	$('#leftmenu ul:eq(${leftMenuId})').show();
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
}
$(document).ready(function() {
	initMenu();
});
</script>

<fieldset style="width: 230px; height: 680px;">
	<legend>목록</legend>
	<ul id="leftmenu">
		<c:choose>
			<c:when test="${leftMenuId eq 0}">
			</c:when>
			<c:when test="${leftMenuId eq 1}">
			</c:when>
			<c:when test="${leftMenuId eq 2}">
			</c:when>
			<c:when test="${leftMenuId eq 3}">
				<li><a href="#">계정관리</a></li>
				<li><a href="#">코드관리</a></li>
				<li>
					<a href="#">게시판관리</a>
					<ul>
						<li><a href="#">종합정보</a></li>
						<li><a href="#">문재인</a></li>
						<li><a href="#">턴어라운드</a></li>
					</ul>
				</li>
			</c:when>
		</c:choose>
	</ul>
</fieldset>