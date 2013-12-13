<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	<div class="utbox bgimg02">
		<p class="txttype03">
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt08.gif'/>" alt="이메일 인증이 정상적으로 완료되었습니다." class="pad_b15" /><br />
			<span class="txtcolor06">${member.mbrId }</span>恭喜您成為開發商專區會員!
		</p>
		<p class="pad_b7">
			現在您可以上傳或販售免費產品. 若您要上傳或販售付費產品,<br />
			需填寫「 銀行資料」. 若需要請點選「<span class="txtcolor08">銀行資料</span>」!
		</p>
	</div>
	<div class="btnarea mar_t30">   
		<a href="<c:url value='/mypage/mypageIntro.omp?forwardAction=CALCULATE'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_register1.gif'/>" alt="銀行資料" /></a>
		<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_login.gif'/>" alt="登入" onclick="javascript:gotoPage('LOGIN');" /></a>
	</div>

</div>
<!-- //CONTENT TABLE E-->
</div>