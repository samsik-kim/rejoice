<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript" src="/resource/ets/validate.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#loginCheck").click(function(){
		if(showValidate("login", 'default', "입력오류를 확인하십시오.")){
			var data = $("#login").serialize();
			$.ajax({
				url: '/stockinvest/loginCheck.do',
				dataType: 'json',
				type: "POST",
				data: data,
				async : false,		
				cache : false,	
				success: function(json){
					if(json.result == "SUCCESS"){
						$("#login").attr("action", "/stockinvest/main.do");
						$("#login").submit();
					} else {
						alert("로그인 정보가 잘못되었습니다.\n다시입력해주세요.");
							$("#login").attr("action", "/stockinvest/logInForm.do");
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
<table width="100%" height="100%" cellpadding="0" cellspacing="0">
<tr height="40%">
	<td colspan=3>&nbsp;<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br></td>
</tr>
<tr height="20%">
	<td width="30%" bgcolor="#336600">&nbsp;<br><br><br><br><br><br><br><br><br><br><br></td>
	<td width="40%">
		<table width="400" cellpadding="0" cellspacing="0">
			<tr>
				<td rowspan="2"><img src="/resource/images/admin_logo.jpg"></td>
				<td align="center" width="100">아이디</td>
				<td>
					<input type="text" name="admin_id" id="admin_id" style="width:100px;height:20px;"
					v:required='trim' m:required="이름을 입력하십시오."/>
				</td>
				<td align="center" rowspan="2">&nbsp;&nbsp;	<img src="/resource/images/common/login.gif" id="loginCheck" alt="로그인" />&nbsp;&nbsp;</td>
			</tr>
			<tr>
				<td align="center">비밀번호</td>
				<td>
					<input type="password" name="password" id="password" style="width:100px;height:20px;"
					v:required='trim' m:required="암호를 입력하십시오."/>
				</td>
			</tr>	
		</table>
	</td>
	<td width="30%" bgcolor="#336600">&nbsp;</td>
</tr>
<tr height="40%">
	<td colspan=3><br><br><br><br><br><br><br>&nbsp;</td>
</tr>
</table>
</form>