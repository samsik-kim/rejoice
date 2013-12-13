<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<title>탈퇴사유 보기</title>

<script type="text/javascript">

	function ajaxUpdate(code){
		$("#uptdevmbrstatcd").val(code);
		
		if(confirm("<gm:string value='jsp.member.dev_membermgr.dev_contract.msg.withdraw_appr'/>")){
			$.ajax({
				type			: 	"POST",
				url			: 	env.contextPath +"/devMemMgr/ajaxStateUpdateExcute.omp",
				data			: 	$("#devMember").serialize(),
				dataType	: 	"json",
				cache		: 	false,
				async 		: 	false,
				success		: 	function(json) {
										if(json.result == "SUCCESS"){
											alert("<gm:string value='jsp.member.dev_membermgr.common.msg.process'/>");
											
											if(window.name == "withdrawList"){
												opener.location.href = env.contextPath + "/member/devMemberWithdrawList.omp?" + $("#sc").val();
											}else if(window.name == "withdraw"){
												opener.location.href = env.contextPath + "/devMemMgr/findDevMemberDetail.omp?devMember.mbrno=" + $("#mbrno").val() + "&" + $("#sc").val();
											}
											
											self.close();
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

<form name="devMember" id="devMember"  method="post">
<input type="hidden" id="mbrno" name="devMember.mbrno" value="<g:tagAttb value='${devMember.mbrno}'/>" />
<input type="hidden" id="devmbrstatcd" name="devMember.devmbrstatcd" value="<g:tagAttb value='${devMember.devmbrstatcd}'/>" />
<input type="hidden" id="mbrcatcd" name="devMember.mbrcatcd" value="<g:tagAttb value='${devMember.mbrcatcd}'/>" />
<input type="hidden" id="mbrclscd" name="devMember.mbrclscd" value="<g:tagAttb value='${devMember.mbrclscd}'/>" />
<input type="hidden" id="uptdevmbrstatcd" name="devMember.uptdevmbrstatcd" value="" />
<input type="hidden" id="type" name="devMember.type" value="withdraw" />
<input type="hidden" id="sc" name="" value="<g:printBean prefix='sc.' value='${sc}' outType='qs'/>" />
	<div id="popup_area_440">
		<h1>탈퇴사유 보기</h1>
		<div class="scroll" style="width:400px;height:160px; border:1px solid #ddd;clear:both;">
			<table class="pop_tabletype01">
				<colgroup><col style="width:30%;"><col style="width:70%"></colgroup>
				<tbody>
					<tr>
						<th>탈퇴신청자ID</th>
						<td><g:html value="${devMember.mbrid}" /></td>
					</tr>
					<tr>
						<th>탈퇴신청일</th>
						<c:choose>
							<c:when test="${devMember.mbrcatcd eq CONST.MEM_TYPE_DEV_NOPAY}">
								<td><g:html value="${devMember.freedevreqdt}" format="L####-##-##" /></td>
							</c:when>
							<c:otherwise>
								<td><g:html value="${devMember.paydevreqdt}" format="L####-##-##" /></td>
							</c:otherwise>
						</c:choose>
					</tr>
					<tr>
						<th>탈퇴사유</th>
						<c:choose>
							<c:when test="${devMember.mbrcatcd eq CONST.MEM_TYPE_DEV_NOPAY}">
								<td><g:html value="${devMember.freedevwithdrawtxt}" /></td>
							</c:when>
							<c:otherwise>
								<td><g:html value="${devMember.paydevwithdrawtxt}" /></td>
							</c:otherwise>
						</c:choose>
					</tr>
				</tbody>
			</table>
		</div><!-- //scroll -->
		
		<div class="pop_btn" >
			<c:if test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_MOTION_REQ}">
				<a class="btn_s" href="javascript:ajaxUpdate('${CONST.MEM_DEV_STATUS_LEAVE_FINISH}');"><strong>탈퇴승인</strong></a>
			</c:if>
			<a class="btn_s" href="javascript:self.close();"><strong>확인</strong></a>
		</div>
	</div><!-- //contents -->
</form>