<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<title>전환거절 사유등록</title>

<script type="text/javascript">

	function register(code){
		if(showValidate('devMember', 'default','<gm:string value="jsp.common.dialog.title"/>')){
			$("#uptdevmbrstatcd").val(code);
			
			$.ajax({
				type			: 	"POST",
				url			: 	env.contextPath + "/devMemMgr/ajaxStateUpdateExcute.omp",
				data			: 	$("#devMember").serialize(),
				dataType	: 	"json",
				cache		: 	false,
				async 		: 	false,
				success		: 	function(json) {
										if(json.result == "SUCCESS"){
											alert("<gm:string value='jsp.member.dev_membermgr.popup_reject_register.msg.reason_regiger_success'/>");
											
											self.close();
											
											if(opener.window.name == "view"){
												opener.close();
												opener.opener.location.href = env.contextPath + "/devMemMgr/findDevMemberDetail.omp?devMember.mbrno=" + $("#mbrno").val() + "&" + $("#sc").val();
											}else{
												opener.location.href = env.contextPath + "/devMemMgr/findDevMemberDetail.omp?devMember.mbrno=" + $("#mbrno").val() + "&" + $("#sc").val();
											}
										}
									},
				error			: 	function(){
										alert("<gm:string value='jsp.member.common.msg.error'/>");
										return false;
									}
			});	//end ajax
		}
	}

</script>

<form name="devMember" id="devMember">
<input type="hidden" id="id" name="devMember.mbrid" value="<g:tagAttb value='${devMember.mbrid}'/>" />
<input type="hidden" id="mbrno" name="devMember.mbrno" value="<g:tagAttb value='${devMember.mbrno}'/>" />
<input type="hidden" id="devmbrstatcd" name="devMember.devmbrstatcd" value="<g:tagAttb value='${devMember.devmbrstatcd}'/>" />
<input type="hidden" id="mbrcatcd" name="devMember.mbrcatcd" value="<g:tagAttb value='${devMember.mbrcatcd}'/>" />
<input type="hidden" id="mbrclscd" name="devMember.mbrclscd" value="<g:tagAttb value='${devMember.mbrclscd}'/>" />
<input type="hidden" id="uptdevmbrstatcd" name="devMember.uptdevmbrstatcd" value="" />
<input type="hidden" id="type" name="devMember.type" value="state" />
<input type="hidden" id="sc" name="" value="<g:printBean prefix='sc.' value='${sc}' outType='qs'/>" />
	<div id="popup_area_440">
		<h1>전환거절 사유등록</h1>
		<p><strong class="c_06f"><g:html value="${devMember.mbrid}" /></strong> 님의 전환거절 사유를 입력해 주세요.</p>
		<textarea id="rejreason" name="devMember.rejreason" rows="5" cols="47"  v:required="trim" m:required="<gm:string value='jsp.member.dev_membermgr.popup_reject_register.msg.rejreason_req'/>"
			v:text8_limit="1000" m:text8_limit="<gm:string value='jsp.member.dev_membermgr.popup_reject_register.msg.rejreason_limit'/>" ></textarea>
		<div class="pop_btn" >
			<a class="btn_s" href="javascript:register('${devMember.uptdevmbrstatcd}');"><strong>등록</strong></a>
			<a class="btn_s" href="javascript:self.close();"><strong>취소</strong></a>
		</div>
	</div>
</form>