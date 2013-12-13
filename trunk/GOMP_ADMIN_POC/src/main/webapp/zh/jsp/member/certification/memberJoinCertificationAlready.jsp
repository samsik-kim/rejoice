<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="contents_area" class="ut_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_title01.gif'/>" alt="會員註冊" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="utcontents">
		
		<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h4_06.gif'/>" alt="信箱認證完成 - 您的信箱認證已完成!" /></h4>
		<div class="utbox bgimg03">
			<p class="txttype04">
				<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt01.gif'/>" alt="信箱認證成功!" /><br />
				<span class="txtcolor05">&nbsp;&nbsp;您已完成信箱認證, 請登入後使用服務!</span>
			</p>
		</div>
		<div class="btnarea mar_t30">
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/login/loginMain.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_btn.gif'/>" alt="登入" /></a>
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_main1.gif'/>" alt="首頁" /></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>