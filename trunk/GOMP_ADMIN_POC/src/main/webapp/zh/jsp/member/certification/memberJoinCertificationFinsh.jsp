<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<div id="contents_area" class="ut_area">
<!-- Title Area S -->
<div id="ctitle_area">
	<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_title01.gif'/>" alt="會員註冊" /></h3>
</div>
<!-- //Title Area E -->

<!-- CONTENT TABLE S-->
<div id="utcontents">
	
	<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h4_06.gif'/>" alt="信箱認證完成 - 您的信箱認證已完成!" /></h4>
	
	<div class="utbox bgimg02">
		<p class="txttype03">
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt08.gif'/>" alt="您的信箱認證已完成!" /><br />
			<span class="txtcolor06">&nbsp;&nbsp;<g:html value="${member.mbrId }"/></span>恭喜您成為開發商專區會員!
		</p>
		<p class="txt_90 txtl pad_l185 mar_b15">
			現在您可以上傳或販售免費產品. 若您要上傳或販售付費產品, 需填寫「 銀行資料」. 若需要請點選<span class="txtcolor03">「銀行資料」</span>!
		</p>
	</div>
	<div class="btnarea mar_t30">   
		<a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/mypage/mypageIntro.omp?forwardAction=CALCULATE"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_register1.gif'/>" alt="銀行資料" /></a>
		<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/login/loginMain.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_login.gif'/>" alt="登入" /></a>
	</div>

</div>
<!-- //CONTENT TABLE E-->
</div>