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
	<c:when test="${fn:contains(pageContext.request.requestURI, 'contentVerifyCancel')}"><c:set var="leftMenuId" value='2'/></c:when>
</c:choose>

<c:choose>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/contentsStatusList.omp')}"><c:set var="subMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/contentDetailInfoView.omp')}"><c:set var="subMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/contentDevInfoView.omp')}"><c:set var="subMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/registContentWrite.omp')}"><c:set var="subMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/testPhoneRegister.omp')}"><c:set var="subMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/getContentsVerifyList.omp')}"><c:set var="subMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/getContentVerifyDetail.omp')}"><c:set var="subMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/content/contentVerifyCancel.omp')}"><c:set var="subMenuId" value='2'/></c:when>
</c:choose>

<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/pm_left_title01.gif' />" alt="產品上架/管理" /></h3>
<ul class="fmenu">
	<li class="${leftMenuId eq 1 ? 'on':'' }"><a href="<c:url value='/content/registContentWrite.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/pm_fmenu01.gif'/>" alt="產品上架" /></a>
	<c:if test="${leftMenuId eq 1 }">
		<ul class="smenu">
			<li class="${subMenuId eq 1 ? 'on':'' }"><a href="<c:url value='/content/registContentWrite.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/pm_smenu01.gif' />" alt="產品上架" /></a></li>
			<li class="${subMenuId eq 2 ? 'on':'' }"><a href="<c:url value='/content/testPhoneRegister.omp' />" ><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/pm_smenu02.gif' />" alt="測試手機設定" /></a></li>
		</ul>
	</c:if>
	</li>
	
	<li class="${leftMenuId eq 2 ? 'on':'' }"><a href="<c:url value='/content/contentsStatusList.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/pm_fmenu02.gif' />" /></a>
	<c:if test="${leftMenuId eq 2 }">
		<ul class="smenu">
			<li class="${subMenuId eq 1 ? 'on':'' }"><a href="<c:url value='/content/contentsStatusList.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/pm_smenu03.gif' />" /></a></li>
			<li class="${subMenuId eq 2 ? 'on':'' }"><a href="<c:url value='/content/getContentsVerifyList.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/pm_smenu04.gif' />" /></a></li>
		</ul>
	</c:if>
	</li>
</ul>


