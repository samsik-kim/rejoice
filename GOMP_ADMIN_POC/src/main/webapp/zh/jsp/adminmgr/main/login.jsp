<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.omp.commons.text.LocalizeMessage" %>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Whoopy Management System</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/css/base.css" />
	<link rel="shortcut icon" href="${pageContext.request.contextPath}/${ThreadSession.serviceLocale.language}/images/whoopy.ico" type="image/x-icon" />
	<style type="text/css">
		*{margin:0; padding:0;}
		body{background:#f7f7f7; font-size: 13px; font-family:arial, helvetica, clean, sans-serif; line-height:1.3em;}
		.login{width:530px; height:180px; margin:0 auto; margin-top:15%; padding:0;}
		.login_txt{text-align:right; font-size:12px; color:#989898; margin-bottom:5px;}
		.copy_txt{text-align:center; color:#8e8e8e; font-weight:bold;}
		
		.fieldset{height:100px; background:#fff url(${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/bg_fieldset.gif) no-repeat 30px 13px; border:1px solid #ccc; padding:43px 0 0 210px; margin-bottom:13px; position:relative;}
		fieldset{border:0 none;}
		legend{display:none;}
		label{display:inline-block; width:65px; font-weight:bold;}
		.id{margin-bottom:6px;}
		.pw{}
		.loginBtn{position:absolute; top:45px; right:20px;}
 
		.textType{width:150px; height:16px; padding:2px; border:1px solid #cdcdcd;}
	</style>
<%
	String resultCd = request.getParameter("resultCd");
	//String msg = LocalizeMessage.getLocalizedMessage("아이디와 비밀번호를 입력해주세요");
	String msg = "請輸入帳號和密碼。";
	if(resultCd != null && resultCd.length() > 0) {
    	if("ERROR".equals(resultCd)) {
        	//msg = LocalizeMessage.getLocalizedMessage("존재하지 않는 아이디 이거나 패스워드가 맞지 않습니다.<br> 확인 하시고 다시 로그인 해주세요");
        	msg = "帳號和密碼不一致, 請查正!";
    	} else if("ERROR_AUTH".equals(resultCd)) {
        	//msg = LocalizeMessage.getLocalizedMessage("아무런 페이지 권한이 없는 아이디 입니다.<br>Admin 권한 관리자에게 권한 설정을 문의하세요");
        	msg = "無權限。";
    	} else {
    		msg = "";
    	}
	}
%>

<script language="javascript">

	function execLogin() {
		document.myForm.action = "${CONF['omp.common.url-prefix.https.admin']}${pageContext.request.contextPath}/adminMgr/adminLoginExcute.omp";
		document.myForm.submit();
	}

</script>
</head>
<body>
	<form name="myForm" method="post">
	<div class="login">
		<p class="login_txt">
			<%=msg%>
		</p>
		<div class="fieldset">
		<fieldset>
			<legend>LogIn Area</legend>
			<div class="id">
				<label for="identify">帳號</label>
				<input NAME="adMgr.mgrId" type="text" id="adMgr.mgrId" class="textType" value="" />
			</div>
			<div class="pw">
				<label for="pw">密碼</label>
				<input NAME="adMgr.pswdNo" type="password" id="adMgr.pswdNo" class="textType" value="" onkeydown="javascript: if(event.keyCode == 13) {execLogin();}" />
			</div>
			<input type="image" src="${pageContext.request.contextPath}/${ThreadSession.serviceLocale.language}/images/login_taiwan.gif" class="loginBtn" alt="登入" onClick="javascript:execLogin();" />
		</fieldset>
		</div>
		<p class="copy_txt">Copyright @ 2011 East Power Mobilephone Co., Ltd. All Rights Reserved.</p>
	</div>
	</form>
</body>
</html>
