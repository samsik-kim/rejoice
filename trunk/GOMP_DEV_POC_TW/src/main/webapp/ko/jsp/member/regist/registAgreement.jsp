<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!-- Content Area S -->
<div id="contents_area" class="ut_area">

	<!-- Title Area S -->
	<div id="ctitle_area">
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_title01.gif'/>" alt="회원가입" /></h3>
	</div>
	<!-- //Title Area E -->

	<!-- CONTENT TABLE S-->
	<div id="utcontents">
	<form method="post" id="registAgree" name="registAgree" action="<c:url value='/member/registJoinForm.omp'/>">
		<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h4_03.gif'/>" alt="약관동의 - 회원가입 전에 반드시 읽어보시고, 동의를 하셔야 회원가입을 계속 진행하실 수 있습니다." /></h4>
		<!-- Tab_menu S -->
		<div class="tab01 mar_b22">
			<ul>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab01.gif'/>" alt="회원종류 선택" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab02_01.gif'/>" alt="가입 여부 확인" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab03_on.gif'/>" alt="약관동의" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab04.gif'/>" alt="회원정보 입력" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab05.gif'/>" alt="회원가입 완료" /></li>
			</ul>
		</div>
		<!-- //Tab_menu E -->
		<h5 class="h51"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h5_01.gif'/>" alt="App mart Developer Center 이용약관" /></h5>
		<div class="agreebox">
			<h6 class="h61">제 1 조 (목적)</h6>
			<p class="pad_b30">이 약관은 SK가 제공하는 AMART 제반 서비스의 이용과 관련하여 회사와 회원과의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.</p>
			<h6 class="h61">제 2 조 (정의)</h6>
			<p class="pad_b30">이 약관은 SK가 제공하는 AMART 제반 서비스의 이용과 관련하여 회사와 회원과의 권리, 의무 및 책임사항, 기타 필요한 사항을 규정함을 목적으로 합니다.</p>
		</div>
		<p class="pad_t7 txtcolor05"><input type="checkbox" name="useagree" id="useagree" v:mustcheck="1" m:mustcheck="이용약관에 동의해주세요."/> <label for="useagree">이용약관에 동의합니다.</label></p>
		<div class="btnarea mar_t30">
			<a shape="hover"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm1.gif'/>" id="agree" alt="확인" style="cursor: pointer;"/></a>
		</div>
		<input type="hidden" value="<g:tagAttb value='${member.psRegNo}'/>" id="psRegNo" name="member.psRegNo" />
		<input type="hidden" value="<g:tagAttb value='${member.mbrNm}'/>" id="mbrNm" name="member.mbrNm" />
		<input type="hidden" value="<g:tagAttb value='${member.mbrClsCd}'/>" name="member.mbrClsCd" id="mbrClsCd"/>
	</form>
	</div>
	<!-- //CONTENT TABLE E-->
</div>
<!-- //Content Area E -->


<script type="text/javascript">
/*
 * Init 
 */
$(document).ready( function() {
	$('#agree').click(function(){
		if(showValidate("registAgree", 'default', "<gm:string value='jsp.common.msg.title02'/>")){
			$('#registAgree').submit();
		}
	});
});
</script>