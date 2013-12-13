<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Content Area S -->
<div id="contents_area" class="ut_area">

<!-- Title Area S -->
	<div id="ctitle_area">
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_title01.gif'/>" alt="會員註冊" /></h3>
	</div>
<!-- //Title Area E -->
<c:url value=' '/>
<!-- CONTENT TABLE S-->
	<div id="utcontents">
		<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h4_01.gif'/>" alt="選擇會員類別 - 請選擇您所屬的會員類別!" /></h4>
		<!-- Tab_menu S -->
		<div class="tab01 mar_b22">
			<ul>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab01_on.gif'/>" alt="選擇會員類別" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab02.gif'/>" alt="確認註冊與否" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab03.gif'/>" alt="同意會員條款" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab04.gif'/>" alt="填寫會員資料" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab05.gif'/>" alt="會員註冊完成" /></li>
			</ul>
		</div>
		<!-- //Tab_menu E -->
		<div class="utbox bgimg04">
			<div class="fltl w260">
				<p class="pad_t102"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt02.gif'/>" alt="個人戶" /></p>
				<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt03.gif'/>" alt="台灣個人開發商請點選!" /></p>
				<p class="pad_tb12"><a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/btn_join.gif'/>" id="person" alt="註冊" /></a></p>
			</div>
			<div class="fltl w260">
				<p class="pad_t102"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt04.gif'/>" alt="公司戶" /></p>
				<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt05.gif'/>" alt="台灣公司開發商請點選! (一般營業和小規模營業)" /></p>
				<p class="pad_tb12"><a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/btn_join.gif'/>" id="company" alt="註冊" /></a></p>
			</div>
			<div class="fltl w260">
				<p class="pad_t102"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt06.gif'/>" alt="外國戶" /></p>
				<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt07.gif'/>" alt="外國戶開發商請點選!(個人和公司)" /></p>
				<p class="pad_tb12"><a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/btn_join.gif'/>" id="foreigner" alt="註冊" /></a></p>
			</div>
		</div>
	</div>
</div>
    
<form id="regist" name="regist" method="post">
	<input type="hidden" id="mbrClsCd" name="member.mbrClsCd"/>
</form>
<script type="text/javascript">
/*
 * Init
 */
$(document).ready( function() {
	$("#person").click(function() {
		$('#mbrClsCd').attr('value', '${CONST.MEM_CLS_PERSON}');
		$("#regist").attr('action', '${CONF['omp.common.url-prefix.https.dev']}' + env.contextPath + '/member/registPerson.omp');
		$("#regist").submit();
	});
	$("#company").click(function() {
		$('#mbrClsCd').attr('value', '${CONST.MEM_CLS_BUSINESS}');
		$("#regist").attr('action', '${CONF['omp.common.url-prefix.https.dev']}' + env.contextPath + '/member/registCompany.omp');
		$("#regist").submit();
	});
	$("#foreigner").click(function() {
		$('#mbrClsCd').attr('value', '${CONST.MEM_CLS_FOREIGNER}');
		$("#regist").attr('action', '${CONF['omp.common.url-prefix.https.dev']}' + env.contextPath + '/member/registForeigner.omp');
		$("#regist").submit();
	});
});
</script>