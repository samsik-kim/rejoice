<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<c:set var="uri" value="%{@org.apache.struts2.ServletActionContext@getRequest().getRequestURI()}"/>

<c:choose>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/findId')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/findPw')}"><c:set var="leftMenuId" value='2'/></c:when>
</c:choose>

<h3><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/common/ip_left_title.gif" alt="아이디 비밀번호 찾기" /></h3>
	<ul class="fmenu">
	<li class="${leftMenuId eq 1 ? 'on':'' }"><a href="<c:url value='/login/findId.omp' />">아이디 찾기</a></li>
	<li class="${leftMenuId eq 2 ? 'on':'' }"><a href="<c:url value='/login/findPw.omp' />">비밀번호 찾기</a></li>
</ul>