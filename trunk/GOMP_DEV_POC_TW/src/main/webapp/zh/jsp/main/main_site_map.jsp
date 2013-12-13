<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- Site map S -->
<div id="mcbtm">
	<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/sitemap.gif' />" alt="SiteMap" /></h3>

	<div class="sitebox mar_l108">
		<h4><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/site_ti01.gif' />" alt="產品上架/管理" /></h4>
		<ul class="sitemap">
			<li><a href="<c:url value='/content/contentsSubMain.omp' />">產品上架管理</a></li>
			<li><a href="<c:url value='/content/registContentWrite.omp' />">產品上架</a></li>
			<li><a href="<c:url value='/content/contentsStatusList.omp' />">產品管理</a></li>
		</ul>

	</div>
	<div class="sitebox">
		<h4><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/site_ti02.gif' />" alt="產品販售/結算" /></h4>
		<ul class="sitemap">
			<li><a href="<c:url value='/settlement/dailysale/dailySaleList.omp' />">每日販售現狀</a></li>
			<li><a href="<c:url value='/settlement/productsale/productSaleList.omp' />">產品類別販售現狀</a></li>
			<li><a href="<c:url value='/settlement/report/settlementReport.omp' />">結算報告 </a></li>

		</ul>
	</div>
	<div class="sitebox">
		<h4><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/site_ti03.gif' />" alt="開發支援中心" /></h4>
		<ul class="sitemap">
			<li><a href="<c:url value='/community/memberGuide.omp'/>">開發支援指南</a></li>
			<li><a href="<c:url value='/community/listNotice.omp'/>">開發支援諮詢</a></li>
			<li><a href="<c:url value='/community/phoneInfoList.omp'/>">掌上設備訊息</a></li>

		</ul>
	</div>
	<div class="sitebox01">
		<h4><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/site_ti04.gif' />" alt="會員中心" /></h4>
		<ul class="sitemap01">
			<li><a href="<c:url value='/mypage/mypageIntro.omp' />">變更會員資料</a></li>
			<li><a href="<c:url value='/mypage/mypageEmail.omp' />">變更電子郵件址</a></li>
			<c:if test="${member.mbrClsCd eq 'US000101'}">
				<li><a href="<c:url value='/mypage/mypageMemberTransGuideView.omp' />">會員轉換</a></li>
			</c:if>
			<li><a href="<c:url value='/mypage/mypageMemberWithdraw.omp' />">會員註銷</a></li>
		</ul>
	</div>
</div>
<!-- //Site map E -->