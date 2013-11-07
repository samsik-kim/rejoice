<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<c:set var="leftMenuId" value='11'/>
<c:set var="subMenuId" value='1'/>
<c:set var="uri" value="%{@org.apache.struts2.ServletActionContext@getRequest().getRequestURI()}"/>

<c:choose>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'memberGuide')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'basicInfoGuide')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'detailInfoGuide')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'devInfoGuide')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'prodVerificationGuide')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'saleCalculateGuide')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'appDrmGuide')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'listNotice')}"><c:set var="leftMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'viewNotice')}"><c:set var="leftMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'newQna')}"><c:set var="leftMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'listFaq')}"><c:set var="leftMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'phoneInfoList')}"><c:set var="leftMenuId" value='3'/></c:when>
</c:choose>

<c:choose>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'memberGuide')}"><c:set var="subMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'basicInfoGuide')}"><c:set var="subMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'detailInfoGuide')}"><c:set var="subMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'devInfoGuide')}"><c:set var="subMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'prodVerificationGuide')}"><c:set var="subMenuId" value='3'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'saleCalculateGuide')}"><c:set var="subMenuId" value='4'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'appDrmGuide')}"><c:set var="subMenuId" value='5'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'listNotice')}"><c:set var="subMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'viewNotice')}"><c:set var="subMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'listFaq')}"><c:set var="subMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'newQna')}"><c:set var="subMenuId" value='3'/></c:when>
</c:choose>   

<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_left_title01.gif'/>" alt="개발지원센터" /></h3>
<ul class="fmenu">
	<li class="${leftMenuId eq 1 ? 'on':'' }"><a href="<c:url value='/community/memberGuide.omp'/>">개발지원가이드</a>
	<c:if test="${leftMenuId eq 1 }">
		<ul class="smenu">
			<li class="${subMenuId eq 1 ? 'depon':'' }"><a href="<c:url value='/community/memberGuide.omp'/>">회원</a></li>
			<li class="${subMenuId eq 2 ? 'depon':'' }"><a href="<c:url value='/community/basicInfoGuide.omp'/>">상품등록/관리</a></li>
            <li class="${subMenuId eq 3 ? 'depon':'' }"><a href="<c:url value='/community/prodVerificationGuide.omp'/>">상품검증</a></li>
            <li class="${subMenuId eq 4 ? 'depon':'' }"><a href="<c:url value='/community/saleCalculateGuide.omp'/>">판매정산</a></li>
            <li class="${subMenuId eq 5 ? 'depon':'' }"><a href="<c:url value='/community/appDrmGuide.omp'/>">Application DRM</a></li>
		</ul>
	</c:if>
	</li>
    <li class="${leftMenuId eq 2 ? 'on':'' }"><a href="<c:url value='/community/listNotice.omp'/>">개발지원문의</a>
    <c:if test="${leftMenuId eq 2 }">
		<ul class="smenu">
			<li class="${subMenuId eq 1 ? 'depon':'' }"><a href="<c:url value='/community/listNotice.omp'/>">공지사항</a></li>
			<li class="${subMenuId eq 2 ? 'depon':'' }"><a href="<c:url value='/community/listFaq.omp'/>">FAQ</a></li>
            <li class="${subMenuId eq 3 ? 'depon':'' }"><a href="<c:url value='/community/newQna.omp'/>">Q&amp;A</a></li>
		</ul>
	</c:if>
	</li>
	
    <li class="${leftMenuId eq 3 ? 'on':'' }"><a href="<c:url value='/community/phoneInfoList.omp'/>">단말정보</a></li>
    
</ul>