<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<style>
	html,body{width:100%; height:100%; background:url(/resource/images/login_bg.gif) no-repeat top center #676767;}
</style>

<script type="text/javascript">

	$(function(){
		
		//Login 처리
		$("#goLogin").click(function(){
			if(showValidate('frm', 'alert', '입력 오류가 있습니다.')==false){
				return;
			}
	
			$("#frm").attr("action","/login/login.do").submit();
		});
	});

</script>

<!--Login S-->
<div id="login_container">
    <form name="frm" id="frm" method="post">
        <div id="login_box">
            <ul>
              <li>
                <input name="userId" id="userId" type="text" maxlength="25" class="input_text" title="아이디" style="width:190px;padding:4px 0 3px 0" value="admin" v:required m:required="ID를 입력해 주세요.">
              </li>
              <li>
                <input name="userPwd" pw="userPwd" type="password" maxlength="16" class="input_text" title="비밀번호" style="width:190px;padding:4px 0 3px 0" value="mrts123" v:required m:required="비밀번호를 입력해 주세요.">
              </li>
            </ul>
            <p class="logbtn"><a href="#" id="goLogin"><img src="/resource/images/mrts_login04_05.gif" width="88" height="57" /></a></p>
        </div>
        <div id="login_box_text">	
            <!-- Btn -->
			<p class="btnC">
	        	<a href="#" class="btn_red" onclick="javascript:Common.centerPopupWindow('/login/popup/registerPop.do','window',{width:420,height:560,scrollBars:'no'});"><strong>사용자 신청</strong></a> 
	        	<a href="#" class="btn_red" onclick="javascript:Common.centerPopupWindow('/login/popup/guidePop.do','window',{width:808,height:800,scrollBars:'YES'});"><strong>이용안내</strong></a>
       		</p>
            <p>* 본 서비스는 반드시 로그인이 필요합니다. *</p>
        </div>
    </form>
</div>
<!--Login E-->
