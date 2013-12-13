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
		<h2 class="hide">login area</h2>
		<div id="login_area">
			<h3 class="h31 fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/amart_logo1.gif'/>" alt="Amart" /></h3>
			<div class="loginbox bgimg05">
				<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_txt01.gif'/>" alt="歡迎進入開發商專區!" /></p>
				<p class="pad_t7 pad_b23"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_txt02.gif'/>" alt="若尚未申請帳號, 請註冊後使用!" /></p>
				<p class="pad_b2">
					<label for="loginid"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_id.gif'/>" alt="帳號" /></label>
					<input type="text" id="user_id" name="user_id" class="w164"
					v:required m:required="<gm:tagAttb value='jsp.main.main.msg.id'/>" tabindex="1"/>
					<input type="checkbox" name="idsave" id="idsave" onclick="javascript:checkboxConfirm();" tabindex="3"/>
					<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_idsave.gif'/>" alt="記住帳號" />
				</p>
				<p class="pad_b2">
					<label for="loginpw"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_pw.gif'/>" alt="密碼" /></label>
					<input type="password" id="user_passwd" name="user_passwd" class="w164" 
					v:required m:required="<gm:tagAttb value='jsp.main.main.msg.pw'/>" tabindex="2"/>
					<a shape="hover" tabindex="4"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/login_btn.gif'/>" alt="登入" id="loginBtn" style="cursor: pointer;" /></a>
				</p>
				<p class="pad_tl54">
					<a href="javascript:gotoPage('CHECKBEFOREREGIST');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_btn1.gif'/>" alt="註冊會員" /></a>
                    <a href="<c:url value='/login/findId.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_btn2.gif'/>" alt="查詢帳號及密碼" /></a>
				</p>
			</div>
		</div>
		<!-- //Login Area E -->
	</div>
</form>