<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="com.stockinvest.common.interceptor.info.SessionInfo" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div id="main_header">
	<div id="main_header_area">
		<h1 align="center"><a href="/stockinvest/main.do" title=""><img src="/resource/images/mb/admin_logo.jpg"></a></h1>
		<h2 class="hide">사용자메뉴영역</h2>
		<ul id="utility">
			<li>
				<h3>
				<c:if test="${LOGIN_SESSION.memId != ''}">
					<span style="color:#ff6600;line-height:110%;">${LOGIN_SESSION.memId}</span> 님 환영합니다.
				&nbsp;<a href="/logOut.do"><img src="/resource/images/common/FBtn_logout.gif" alt="logout"  style="vertical-align:middle;"/></a>
				</c:if>
				</h3>
			</li>
		</ul>
		<!-- 상단주메뉴(GNB) S -->
		<h2 class="hide">주메뉴영역</h2>
		<div id="gnb">
			<ul>
				<li class="frt"><a href="#"></a></li>
				<li><a href="#"></a></li>
				<li><a href="#"></a></li>
			</ul>
		</div>  
		<!-- //상단주메뉴(GNB) E -->
	</div>
</div>