<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>STOCKINVEST</title>
	<link rel="stylesheet" type="text/css" href="/resource/css/base.css" />
	<style type="text/css">
		*{margin:0; padding:0;}
		body{background:#f7f7f7; font-size:12px; font-family:dotum, Tahoma;}
		.login{width:530px; height:180px; margin:0 auto; margin-top:15%; padding:0;}
		.login_txt{text-align:right; font-size:11px; color:#989898; margin-bottom:5px;}
		.copy_txt{text-align:center; color:#8e8e8e; font-weight:bold;}
		
		.fieldset{height:100px; background:#fff url(/resource/images/mb/admin_logo.jpg) no-repeat 25px 40px; border:1px solid #ccc; padding:43px 0 0 210px; margin-bottom:13px; position:relative;}
		fieldset{border:0 none;}
		legend{display:none;}
		label{display:inline-block; width:65px; font-weight:bold;}
		.id{margin-bottom:6px;}
		.pw{}
		.loginBtn{position:absolute; top:41px; right:20px;}

		.textType{width:150px; height:16px; padding:2px; border:1px solid #cdcdcd;}
	</style>
	<link type="text/css" href="/resource/jquery-ui-1.8.17.custom/css/cupertino/jquery-ui-1.8.17.custom.css" rel="stylesheet" />	
	<script type="text/javascript" src="/resource/jquery-ui-1.8.17.custom/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="/resource/jquery-ui-1.8.17.custom/js/jquery-ui-1.8.17.custom.min.js"></script>
	<script type="text/javascript" src="/resource/ets/validate.js"></script>
</head>
<body>
<script type="text/javascript">
$(document).ready(function(){
	$("#loginCheck").click(function(){
		if(showValidate("login", 'default', "입력오류를 확인하십시오.")){
			var data = $("#login").serialize();
			$.ajax({
				url: '/loginCheck.do',
				dataType: 'json',
				type: "POST",
				data: data,
				async : false,		
				cache : false,	
				success: function(json){
					if(json.result == "SUCCESS"){
						if(json.returnUrl != ""){
							$("#login").attr("action", json.returnUrl);
						}else{
							$("#login").attr("action", "/stockinvest/main.do");
						}
							$("#login").submit();
					} else {
						alert("로그인 정보가 잘못되었습니다.\n다시입력해주세요.");
							$("#login").attr("action", "/stockinvest/loginForm.do");
							$("#login").submit();
					}
				},
				error: function(xhr, textStatus, errorThrown){
					alert("ERROR!!!");	
				}
			});
		}
	});
});
</script>
<form name="login" id="login" method="post">
	<div class="login">
		<p class="login_txt">
		</p>
		<div class="fieldset">
		<fieldset>
			<legend>로그인 영역</legend>
			<div class="id">
				<label for="identify">아이디</label>
				<input type="text" name="admin_id" id="admin_id" class="textType" value="samsung" 
				v:required='trim' m:required="아이디를 입력하십시오."/>
			</div>
			<div class="pw">
				<label for="pw">비밀번호</label>
				<input  type="password" name="password" id="password" class="textType" value="2542"
				v:required='trim' m:required="비밀번호를 입력하십시오."/>
			</div>
			<a href="#"><img id="loginCheck" src="/resource/images/common/login.gif" class="loginBtn" alt="login" /></a>
		</fieldset>
		</div>
		<p class="copy_txt">Copyright © 2011 stockinvest. All Right Reserved</p>
	</div>
</form>
</body>
</html>