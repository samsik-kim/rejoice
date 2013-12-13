<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<c:set var="leftMenuId" value='11'/>
<c:set var="subMenuId" value='1'/>
<c:set var="uri" value="%{@org.apache.struts2.ServletActionContext@getRequest().getRequestURI()}"/>

<c:choose>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'dailySaleList')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'productSaleList')}"><c:set var="leftMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'settlementReport')}"><c:set var="leftMenuId" value='3'/></c:when>
</c:choose>

<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/sl_left_title.gif' />" alt="판매/정산 현황"/></h3>
<ul class="fmenu">
	<li class="${leftMenuId eq 1 ? 'on':'' }"><a href="<c:url value='/settlement/dailysale/dailySaleList.omp'/>">일별판매현황</a></li>
    <li class="${leftMenuId eq 2 ? 'on':'' }"><a href="<c:url value='/settlement/productsale/productSaleList.omp'/>">상품별판매현황</a></li>
    <li class="${leftMenuId eq 3 ? 'on':'' }"><a href="<c:url value='/settlement/report/settlementReport.omp'/>">정산리포트</a></li>
</ul>