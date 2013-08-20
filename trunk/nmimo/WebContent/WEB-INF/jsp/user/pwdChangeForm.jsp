<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<style>
	html,body{width:100%; height:100%; background:url(/resource/images/login_bg.gif) no-repeat top center #676767;}
</style>

<script type="text/javascript">

function btnPassWdChange(){
	var frm = document.getElementById("passWdForm");

	frm.action = "/user/loginForm.do";
	frm.submit();
}

</script>

<!--Login S-->
<div id="login_container">
    <form name="passWdForm" id="passWdForm" method="post">
        <div id="pw_box">
            <ul>
              <li>
                <input name="userId"  id="userId" type="text" maxlength="25" class="input_text" title="아이디" style="width:150px;padding:4px 0 3px 0">
              </li>
              <li>
                <input name="userPwd" id="userPwd" type="password" maxlength="16" class="input_text" title="현재 비밀번호" style="width:150px;padding:4px 0 3px 0">
              </li>
              <li>
                <input name="userPwdChk1" id="userPwdChk1" type="password" maxlength="16" class="input_text" title="변경 비밀번호" style="width:150px;padding:4px 0 3px 0">
              </li>
              <li>
                <input name="userPwdChk2" id="userPwdChk2" type="password" maxlength="16" class="input_text" title="변경 비밀번호 확인" style="width:150px;padding:4px 0 3px 0">
              </li>
            </ul>
        </div>
        <div id="login_box_text">	
            <!-- Btn -->
			<p class="btnC">
        	<a href="#" class="btn_red" onClick="btnPassWdChange();"><strong>비밀번호 변경</strong></a>
       		</p>
        </div>
    </form>
</div>
<!--Login E-->
