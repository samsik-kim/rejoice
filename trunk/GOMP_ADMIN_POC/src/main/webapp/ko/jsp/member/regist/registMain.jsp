<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- Content Area S -->
<div id="contents_area" class="ut_area">

<!-- Title Area S -->
	<div id="ctitle_area">
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_title01.gif'/>" alt="회원가입" /></h3>
	</div>
<!-- //Title Area E -->
<c:url value=' '/>
<!-- CONTENT TABLE S-->
	<div id="utcontents">
		<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h4_01.gif'/>" alt="회원종류 선택 - 가입을 원하시는 회원종류를 선택해주세요." /></h4>
		<!-- Tab_menu S -->
		<div class="tab01 mar_b22">
			<ul>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab01_on.gif'/>" alt="회원종류 선택" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab02.gif'/>" alt="가입 여부 확인" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab03.gif'/>" alt="약관동의" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab04.gif'/>" alt="회원정보 입력" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab05.gif'/>" alt="회원가입 완료" /></li>
			</ul>
		</div>
		<!-- //Tab_menu E -->
		<div class="utbox bgimg04">
			<div class="fltl w260">
				<p class="pad_t102"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt02.gif'/>" alt="개인" /></p>
				<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt03.gif'/>" alt="대만 개인 개발자의 경우 선택해 주세요" /></p>
				<p class="pad_tb12"><a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/btn_join.gif'/>" id="person" alt="가입하기" /></a></p>
			</div>
			<div class="fltl w260">
				<p class="pad_t102"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt04.gif'/>" alt="회사" /></p>
				<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt05.gif'/>" alt="대만회사(일반영업, 소규모영업)의 경우 선택해 주세요" /></p>
				<p class="pad_tb12"><a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/btn_join.gif'/>" id="company" alt="가입하기" /></a></p>
			</div>
			<div class="fltl w260">
				<p class="pad_t102"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt06.gif'/>" alt="외국인" /></p>
				<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt07.gif'/>" alt="외국인 회원(개인, 회사)의 경우 선택해 주세요" /></p>
				<p class="pad_tb12"><a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/btn_join.gif'/>" id="foreigner" alt="가입하기" /></a></p>
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