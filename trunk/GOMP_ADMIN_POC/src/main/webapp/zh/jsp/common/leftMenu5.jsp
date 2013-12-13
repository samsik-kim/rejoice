<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<c:set var="leftMenuId" value='11'/>
<c:set var="subMenuId" value='1'/>
<c:set var="uri" value="%{@org.apache.struts2.ServletActionContext@getRequest().getRequestURI()}"/>


<c:choose>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/mypage/mypageIntro.omp')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/mypage/mypageProfileView.omp')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/mypage/mypageCalculateInfoView.omp')}"><c:set var="leftMenuId" value='1'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/mypage/mypageEmail.omp')}"><c:set var="leftMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/mypage/mypageEmailAuthResult.omp')}"><c:set var="leftMenuId" value='2'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/mypage/mypageMemberTransGuideView.omp')}"><c:set var="leftMenuId" value='3'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/mypage/mypageMemberTransCompView.omp')}"><c:set var="leftMenuId" value='3'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/mypage/mypageMemberTransInfoView.omp')}"><c:set var="leftMenuId" value='3'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/mypage/mypageMemberTransInfoInput.omp')}"><c:set var="leftMenuId" value='3'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/mypage/mypageMemberWithdraw.omp')}"><c:set var="leftMenuId" value='4'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/mypage/mypageWithdrawReason.omp')}"><c:set var="leftMenuId" value='4'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, '/mypage/mypageWithdrawExcute.omp')}"><c:set var="leftMenuId" value='4'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'mypageMemberWithdraw')}"><c:set var="leftMenuId" value='4'/></c:when>
	<c:when test="${fn:contains(pageContext.request.requestURI, 'mypageWithdrawReason')}"><c:set var="leftMenuId" value='4'/></c:when>
</c:choose>


<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/mp_left_title.gif'/>" alt="會員中心" /></h3>
<ul class="fmenu">
	<li class="${leftMenuId eq 1 ? 'on':'' }"><a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/mypage/mypageIntro.omp"><img src='<c:url value="/${ThreadSession.serviceLocale.language}/images/common/mp_fmenu01.gif"/>' alt="變更會員資料" /></a></li>
	<li class="${leftMenuId eq 2 ? 'on':'' }"><a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/mypage/mypageEmail.omp"><img src='<c:url value="/${ThreadSession.serviceLocale.language}/images/common/mp_fmenu02.gif"/>'  alt="變更電子郵件" /></a></li>
	<c:if test="${isTransMenuValid eq 'TRUE'}">
	<li class="${leftMenuId eq 3 ? 'on':'' }"><a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/mypage/mypageMemberTransGuideView.omp"><img src='<c:url value="/${ThreadSession.serviceLocale.language}/images/common/mp_fmenu03.gif"/>'  alt="會員轉換" /></a></li>
	</c:if>
	<li class="${leftMenuId eq 4 ? 'on':'' }"><a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/mypage/mypageMemberWithdraw.omp"><img src='<c:url value="/${ThreadSession.serviceLocale.language}/images/common/mp_fmenu04.gif"/>' alt="會員註銷" /></a></li>
</ul>