<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags" %>


<c:set var="leftMenuId" value='11'/>
<c:set var="subMenuId" value='1'/>
<c:set var="uri" value="%{@org.apache.struts2.ServletActionContext@getRequest().getRequestURI()}"/>


<c:choose>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'registContentWrite')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'testPhoneRegister')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'contentsStatusList')}"><c:set var="leftMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'contentDetailInfoView')}"><c:set var="leftMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'contentDevInfoView')}"><c:set var="leftMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'getContentsVerifyList')}"><c:set var="leftMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'getContentVerifyDetail')}"><c:set var="leftMenuId" value='2'/></c:when>
</c:choose>

<c:choose>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/contentsStatusList.omp')}"><c:set var="subMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/contentDetailInfoView.omp')}"><c:set var="subMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/contentDevInfoView.omp')}"><c:set var="subMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/registContentWrite.omp')}"><c:set var="subMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/testPhoneRegister.omp')}"><c:set var="subMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/getContentsVerifyList.omp')}"><c:set var="subMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/getContentVerifyDetail.omp')}"><c:set var="subMenuId" value='2'/></c:when>
</c:choose>

<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/pm_left_title01.gif' />" alt="상품등록/관리" /></h3>
<ul class="fmenu">
	<li class="${leftMenuId eq 1 ? 'on':'' }"><a href="<c:url value='/content/registContentWrite.omp' />">상품등록</a>
	<c:if test="${leftMenuId eq 1 }">
		<ul class="smenu">
			<li class="${subMenuId eq 1 ? 'depon':'' }"><a href="<c:url value='/content/registContentWrite.omp' />">상품등록</a></li>
			<li class="${subMenuId eq 2 ? 'depon':'' }"><a href="<c:url value='/content/testPhoneRegister.omp' />">테스트단말 설정</a></li>
		</ul>
	</c:if>
	</li>
	<li class="${leftMenuId eq 2 ? 'on':'' }"><a href="<c:url value='/content/contentsStatusList.omp' />">상품관리</a>
	<c:if test="${leftMenuId eq 2 }">
		<ul class="smenu">
			<li class="${subMenuId eq 1 ? 'depon':'' }"><a href="<c:url value='/content/contentsStatusList.omp' />">상품현황</a></li>
			<li class="${subMenuId eq 2 ? 'depon':'' }"><a href="<c:url value='/content/getContentsVerifyList.omp' />">검증현황</a></li>
		</ul>
	</c:if>
	</li>
</ul>

