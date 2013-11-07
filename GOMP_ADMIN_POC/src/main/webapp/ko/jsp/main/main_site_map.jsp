<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Site map S -->
<div id="mcbtm">
	<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/sitemap.gif' />" alt="SiteMap" /></h3>

	<div class="sitebox mar_l108">
		<h4><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/site_ti01.gif' />" alt="상품등록/관리" /></h4>
		<ul class="sitemap">
			<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/content/contentsSubMain.omp">상품등록관리</a></li>
			<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/content/registContentWrite.omp">상품등록</a></li>
			<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/content/contentsStatusList.omp'">상품관리</a></li>
		</ul>

	</div>
	<div class="sitebox">
		<h4><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/site_ti02.gif' />" alt="판매/정산관리" /></h4>
		<ul class="sitemap">
			<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/settlement/dailysale/dailySaleList.omp>">일별판매현황</a></li>
			<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/settlement/productsale/productSaleList.omp'">상품별매출현황</a></li>
			<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/settlement/report/settlementReport.omp">정산리포트 </a></li>

		</ul>
	</div>
	<div class="sitebox">
		<h4><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/site_ti03.gif' />" alt="개발지원센터" /></h4>
		<ul class="sitemap">
			<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/community/memberGuide.omp">개발지원가이드</a></li>
			<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/community/listNotice.omp">개발지원문의</a></li>
			<li><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/community/phoneInfoList.omp">단말정보</a></li>

		</ul>
	</div>
	<div class="sitebox01">
		<h4><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/site_ti04.gif' />" alt="마이페이지" /></h4>
		<ul class="sitemap01">
			<li><a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/mypage/mypageIntro.omp">회원정보변경</a></li>
			<li><a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/mypage/mypageEmail.omp">이메일변경</a></li>
			<c:if test="${member.mbrClsCd eq 'US000101'}">
				<li><a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/mypage/mypageMemberTransGuideView.omp">회원전환</a></li>
			</c:if>
			<li><a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/mypage/mypageMemberWithdraw.omp">회원탈퇴</a></li>
		</ul>
	</div>
</div>
<!-- //Site map E -->