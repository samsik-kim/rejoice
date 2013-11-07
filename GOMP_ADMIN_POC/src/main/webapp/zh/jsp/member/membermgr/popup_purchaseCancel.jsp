<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<title>取消購買</title>

<script type="text/javascript">
	//Purchase Cancel
	function purchaseCancel() {
		var update_Result = false;
		var userId = $("#mbrId").val();
		var prchsId = $("#prchsId").val();
		var prchsDt = $("#prchsDt").val();
		
		$("#addr").val($.trim($("#addr").val()));
		
		if(showValidate('cancelfrm', 'default', '<gm:string value="jsp.common.dialog.title"/>')){
			if(confirm("<gm:string value='jsp.member.membermgr.PurchaseList.msg.cancel_confirm'/>")) {
				$.ajax({
					url: env.contextPath + '/membermgr/ajaxUpdateUserAddr.omp',
					dataType: 'json',
					type	: "POST",
					data 	: $("#cancelfrm").serialize(),
					async	: false,
					cache	: false,
					success: function(json){
						if(json.result == 'SUCCESS'){
							update_Result = true;
						}
					},
					error : function(){
						alert("<gm:string value='jsp.member.common.msg.error'/>");
						return false;
					}
				});
				
				if(update_Result){
					$.ajax({
						url: env.contextPath + '/purchasecancel/purchasecancel.omp',
						dataType: 'json',
						type	: "POST",
						data 	: "userId=" + userId + "&prchsId="+prchsId+"&prchsDtm="+prchsDt,
						async	: false,
						cache	: false,
						success: function(json){
							if(json.data == 'SUCCESS'){
								alert("<gm:string value='jsp.member.membermgr.PurchaseList.msg.cancel_success'/>");
								
								if(window.name == "popOpen"){
									//$("form[name=frm] input[name=purchase\\.popupYn]").val("Y");
									opener.location.href = env.contextPath + "/membermgr/popPurchaseList.omp?purchase.mbrNo=" + $("#mbrNo").val() + "&" + $("#sc").val() + "&purchase.popupYn=" + $("#popupYn").val();
									self.close();
								}else if(window.name == "cancelpop"){
									//$("form[name=frm] input[name=purchase\\.popupYn]").val("N");
									opener.location.href = env.contextPath + "/membermgr/listPurchaseList.omp?" + $("#sc").val()+ "&purchase.popupYn=" + $("#popupYn").val();
									self.close();
								}
								
							}else{
								alert("<gm:string value='jsp.member.membermgr.PurchaseList.msg.cancel_fail'/>");
								return false;
							}
						},
						error : function(){
							alert("<gm:string value='jsp.member.common.msg.error'/>");
							return false;
						}
					});
				}
			}
		}
	}
	
</script>

<form name="cancelfrm" id="cancelfrm"  method="post">
<input type="hidden" id="mbrNo" name="purchase.mbrNo" value="<g:tagAttb value='${purchase.mbrNo}'/>"/>
<input type="hidden" id="popupYn" value="<g:tagAttb value='${purchase.popupYn}'/>"/>
<input type="hidden" id="mbrId" value="<g:tagAttb value='${purchase.mbrId}' />"/>
<input type="hidden" id="prchsId" value="<g:tagAttb value='${purchase.prchsId}' />"/>
<input type="hidden" id="prchsDt" value="<g:tagAttb value='${purchase.prchsDt}' format='L########' />"/>
<input type="hidden" id="sc" name="" value="<g:printBean prefix='sc.' value='${sc}' outType='qs'/>" />

	<div id="popup_area_440">
		<h1>取消購買</h1>
		<p class="pb10">會員資訊</p>
		<table class="pop_tabletype01">
			<colgroup>
				<col style="width:20%;">
				<col style="width:80%">
			</colgroup>
			<tbody>
				<tr>
					<th>帳號</th>
					<td><g:html value="${purchase.mbrId}" /></td>
				</tr>
				<tr>
					<th>聯絡電話</th>
					<td><g:html value="${purchase.hpno}" /></td>
				</tr>
				<tr>
					<th>地址</th>
					<td>
						<input type="text" class="txt" size="30" name="purchase.addr" id="addr" value="<g:tagAttb value='${purchase.addr}' />"
							v:text8_limit="400" m:text8_limit="<gm:string value='jsp.member.membermgr.popup_purchaseCancel.msg.addr_limit'/>" 
							v:required m:required="<gm:string value='jsp.member.membermgr.PurchaseList.msg.addr_req'/>"/>
					</td>
				</tr>
			</tbody>
		</table>
		<p class="pt05">請點選「確定」取消購買!</p>
		
		<div class="pop_btn" >
			<a class="btn_s" href="javascript:purchaseCancel();"><strong>確定</strong></a>
			<a class="btn_s" href="javascript:self.close();"><strong>取消</strong></a>
		</div>
	</div><!-- //contents -->
</form>