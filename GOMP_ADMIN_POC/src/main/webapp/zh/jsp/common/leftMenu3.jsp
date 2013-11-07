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

<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_left_title01.gif'/>" alt="開發商支援中心" /></h3>
<ul class="fmenu">
	<li class="${leftMenuId eq 1 ? 'on':'' }"><a href="<c:url value='/community/memberGuide.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_fmenu01.gif' />" alt="開發支援指南" /></a>
	<c:if test="${leftMenuId eq 1 }">
		<ul class="smenu">
			<li class="${subMenuId eq 1 ? 'on':'' }"><a href="<c:url value='/community/memberGuide.omp'/>" ><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_smenu01.gif' />" alt="會員" /></a></li>
			<li class="${subMenuId eq 2 ? 'on':'' }"><a href="<c:url value='/community/basicInfoGuide.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_smenu02.gif' />" alt="產品上架管理" /></a></li>
            <li class="${subMenuId eq 3 ? 'on':'' }"><a href="<c:url value='/community/prodVerificationGuide.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_smenu03.gif' />" alt="產品審核" /></a></li>
            <li class="${subMenuId eq 4 ? 'on':'' }"><a href="<c:url value='/community/saleCalculateGuide.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_smenu04.gif' />" alt="產品販售結算" /></a></li>
            <li class="${subMenuId eq 5 ? 'on':'' }"><a href="<c:url value='/community/appDrmGuide.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_smenu05.gif' />" alt="Application DRM" /></a></li>
		</ul>
	</c:if>
	</li>
    <li class="${leftMenuId eq 2 ? 'on':'' }"><a href="<c:url value='/community/listNotice.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_fmenu02.gif' />" alt="開發支援詢問" /></a>
    <c:if test="${leftMenuId eq 2 }">
		<ul class="smenu">
			<li class="${subMenuId eq 1 ? 'on':'' }"><a href="<c:url value='/community/listNotice.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_smenu06.gif' />" alt="公告事項" /></a></li>
			<li class="${subMenuId eq 2 ? 'on':'' }"><a href="<c:url value='/community/listFaq.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_smenu07.gif' />" alt="常見問題" /></a></li>
            <li class="${subMenuId eq 3 ? 'on':'' }"><a href="<c:url value='/community/newQna.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_smenu08.gif' />" alt="客戶諮詢" /></a></li>
		</ul>
	</c:if>
	</li>
	
    <li class="${leftMenuId eq 3 ? 'on':'' }"><a href="<c:url value='/community/phoneInfoList.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/uc_fmenu03.gif' />" alt="掌上設備資訊" /></a></li>
    
</ul>