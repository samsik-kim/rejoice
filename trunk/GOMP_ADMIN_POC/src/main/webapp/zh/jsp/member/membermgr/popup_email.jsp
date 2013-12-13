<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<title>發送臨時密碼</title>

<script type="text/javascript">
	// Send E-Mail
	function email_send(){
		$("#email").val($.trim($("#email").val()));
		
		if(showValidate('userMember', 'default', '<gm:string value="jsp.common.dialog.title"/>')){
			$.ajax({
				type			: 	"POST",
				url			: 	"${pageContext.request.contextPath}/userMemMgr/ajaxEmailSendExcute.omp",
				data			: 	$("#userMember").serialize(),
				dataType	: 	"json",
				cache		: 	false,
				async 		: 	false,
				success		: 	function(json) {
										if(json.result == "SUCCESS"){
											alert("<gm:string value='jsp.member.membermgr.user_contract.msg.email_success'/>");
											
											self.close();
											
											opener.location.href = env.contextPath + "/userMemMgr/findUserMemberDetail.omp?userMember.mbrno=" + $("#mbrno").val() + "&" + $("#sc").val();
					   					}
									},
				error			: 	function(){
										alert("<gm:string value='jsp.member.common.msg.error'/>");
										return false;
									}
			});	//end ajax
		}else{
			return;
		}
	}
	
</script>

<form name="userMember" id="userMember" method="post">
<input type="hidden" id="mbrno" name="userMember.mbrno" value="<g:tagAttb value='${userMember.mbrno}'/>" />
<input type="hidden" id="mbrid" name="userMember.mbrid" value="<g:tagAttb value='${userMember.mbrid}'/>" />
<input type="hidden" id="email" name="userMember.emailaddr" value="<g:tagAttb value='${userMember.emailaddr}'/>" />
<input type="hidden" id="sc" name="" value="<g:printBean prefix='sc.' value='${sc}' outType='qs'/>" />
	<div id="popup_area_440">
		<h1>發送臨時密碼</h1>
		<p>您要將臨時密碼發送至以下信箱.</p>
		<p class="mt20">
			<label><strong>E-mail</strong></label>
			<input type="text" name="userMember.tempEmailAddr" id="email" class="txt" value="<g:tagAttb value='${userMember.emailaddr}'/>" style="width:70%;" 
				v:required m:required="<gm:string value='jsp.member.membermgr.user_contract.msg.email_req'/>" v:email m:email="<gm:string value='jsp.member.membermgr.user_contract.msg.email_chk'/>" onkeydown="javascript:if (event.keyCode == 13) {return false;}"/>
		</p>
		<div class="pop_btn" >
			<a class="btn_s" href="javascript:email_send();"><strong style="cursor:pointer;">送出</strong></a>
			<a class="btn_s" href="javascript:self.close();"><strong style="cursor:pointer;">取消</strong></a>
		</div>
	</div>
</form>