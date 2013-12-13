<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
/*
 * Init
 */
$(document).ready(function(){
	var frm  = document.frm;
	
	// Get Session Check
	getid(frm);
	
	// ID Input Enter event
	$("#user_id").keypress(function(event){
		if(event.keyCode == 13) $("#loginBtn").click();
	});
	
	// PW Input Enter event 
	$("#user_passwd").keypress(function(event){
		if(event.keyCode == 13) $("#loginBtn").click();
	});
	
	// Do Login 
	$("#loginBtn").click(function() {
		if(showValidate('frm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
			$('#loginFrm').attr('action', env.contextPath + '/login/login.omp');
			$('#loginFrm').submit();
			saveid();
			return false;
		}
	});
	
	<c:if test="${empty MEMBER_SESSION}">
		if(frm.idsave.checked) $("#user_passwd").focus();
		else $("#user_id").focus();
	</c:if>
	
});

/*
 * Get Session Check  
 */
function getid(form) {
	<c:if test="${empty MEMBER_SESSION}">
		form.idsave.checked = ((form.user_id.value = getCookie("saveid")) != "");
	</c:if>
}

/*
 * ID SAVE
 */
function saveid() {

	var expdate	= new Date();
	if ($("#idsave").attr("checked"))
		expdate.setTime( expdate.getTime() + 1000 * 3600 * 24 * 30 );	// 30일
	else
		expdate.setTime( expdate.getTime() - 1 );						// 쿠키 삭제조건
	setCookie("saveid", $("#user_id").val(), expdate );
}

/*
 * Session Check Confirm
 */
function checkboxConfirm() {
	var frm	= document.frm;
	if(frm.idsave.checked){ 
			if(!confirm("<gm:string value='jsp.main.main.btn.chBox'/>")){
			frm.idsave.checked	= false;
		}
	}else{
		deleteCookie("saveid");
	}
}
</script>
<form id="loginFrm" name="frm" method="post">
	<!-- Container S-->
	<div id="container" class="none_bg">
		<!-- Login Area S -->
		<h2 class="hide">로그인영역</h2>
		<div id="login_area">
			<h3 class="h31 fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/amart_logo1.gif'/>" alt="Amart" /></h3>
			<p class="jointxt"><a href="javascript:gotoPage('CHECKBEFOREREGIST');">회원가입</a> <span class="txtcolor11">|</span> <a href="<c:url value='/login/findId.omp'/>">아이디/비밀번호 찾기</a></p>
			<div class="loginbox bgimg05">
				<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_txt01.gif'/>" alt="개발자 센터에 오신 것을 환영합니다!" /></p>
				<p class="pad_b23"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_txt02.gif'/>" alt="로그인 아이디가 없으신 분은 회원 가입을 해주시면 더욱 편리하게 서비스를 이용할 수 있습니다." /></p>
				<p class="pad_b2">
					<label for="loginid"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_id.gif'/>" alt="아이디" /></label>
					<input type="text" id="user_id" name="user_id" class="w164"
					v:required m:required="<gm:tagAttb value='jsp.main.main.msg.id'/>" tabindex="1"/>
					<input type="checkbox" name="idsave" id="idsave" onclick="javascript:checkboxConfirm();" tabindex="3"/>
					<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_idsave.gif'/>" alt="아이디 저장" />
				</p>
				<p>   
					<label for="loginpw"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_pw.gif'/>" alt="비밀번호" /></label>
					<input type="password" id="user_passwd" name="user_passwd" class="w164" 
					v:required m:required="<gm:tagAttb value='jsp.main.main.msg.pw'/>" tabindex="2"/>
					<a shape="hover" tabindex="4"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_btn.gif'/>" alt="로그인" id="loginBtn" style="cursor: pointer;" /></a>
				</p>
			</div>
		</div>
		<!-- //Login Area E -->
	</div>
</form>