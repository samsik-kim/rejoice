<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<script type="text/javascript">
$(document).ready( function() {
	
	// Email Check
	$('#emailCheck').click(function(){
		$("#radio2").attr("checked", "checked");
		if(showValidate(document.getElementById("newEmail"), "dialog", "<gm:string value='jsp.user.common.msg.errTitle' />")) {
			$("#email").val($("#newEmail").val());
			$("#emailAddr").val($("#newEmail").val());
			var data = $("#checkFrm").serialize();
			$.ajax({
				url: env.contextPath + '/member/ajaxEmailCheck.omp',
				dataType: 'json',
				type	: "POST",
				data 	: data,
				async	: false,		
				cache	: false,	
				success: function(json){
					if(json.data == 'SUCCESS'){
						if(confirm("<gm:string value='jsp.user.pop.popupReEmailCertification.msg.emailChk01' />")){
							$('#duplicateEmailCheck').val('Y');
						}								
					}else if(json.data == 'FAIL'){
						alert("<gm:string value='jsp.user.pop.popupReEmailCertification.msg.emailChk02' />");
					}
				},
				error: function(xhr, textStatus, errorThrown){
					alert('An error occurred! : ' + (errorThrown ? errorThrown : xhr.status) + "\ntextStatus : " + textStatus);
				}
			});
		}
	});
	
	// Join Email Certification Send
	$("#emailConfirm").click(function(){
		if($("input[@type=radio]:checked").val() == "new") {
			if(showValidate(document.getElementById("duplicateEmailCheck"), "dialog", "<gm:string value='jsp.user.common.msg.errTitle' />")) {
				frmSubmit();
			}
		} else {
			$("#emailAddr").val($("#oldEmail").val());
			frmSubmit();
		}
	});
	
	$("#newEmail").click(function(){
		$("#radio2").attr("checked", "checked");
	});
	
	$("#radio1").click( function() {
		$("#newEmail").val("");
	});
});

function frmSubmit() {
	
	var data = $("#frm").serialize();
	$.ajax({
		url: env.contextPath + '/login/reEmailCertification.omp',
		dataType: 'json',
		type	: "POST",
		data 	: data,
		async	: true,		
		cache	: false,	
		success: function(json){
			if(json.data == 'success'){
				alert("<gm:string value='jsp.user.pop.popupReEmailCertification.msg.emailChk03' />");							
			}
			closePopupLayer('popupReEmailCertification');
			gotoPage('MAIN');
		},
		error: function(xhr, textStatus, errorThrown){
			alert('An error occurred! : ' + (errorThrown ? errorThrown : xhr.status) + "\ntextStatus : " + textStatus);
		}
	});
}
function closePop(){
	closePopupLayer('popupReEmailCertification');
	location.href = $('#returnAction').val();
}
</script>
<form id="checkFrm" name="checkFrm" action="" method="post">
	<input type="hidden" id="email" name="member.emailAddr" value="" />
	<input type="hidden" id="mbrNo" name="mbrNo" value="${mbrNo }" />
</form>
<form id="frm" name="frm" method="post">
<input type="hidden" id="oldEmail" name="oldEmail" value="${email }"/>
<input type="hidden" id="mbrNoC" name="mbrNoC" value="${mbrNo }" />
<input type="hidden" id="emailAddr" name="emailAddr" value=""/>

<div class="layerwrap">

	<div id="pop_area01">

		<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/ut_title_01.gif' />" alt="重新進行信箱認證" /></h2>

		<div class="pop_con">

			<p class="pop_txt">您申請會員註冊後,  未曾回復認證信. 系統已發送認證信至您<br />所填寫的電子郵件地址, 請先完成信箱認證後使用服務!</p>
			<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/ut_sub_01.gif'/>" alt="重發認證信" /></h3>
			<div class="pop_box pbimg01">
				<input type="radio" class="radio" id="radio1" name="radio" value="old" checked="checked"/>&nbsp;<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/ut_txt_01.gif' />" alt="請發送至曾填寫的電子郵件" />
				<input type="hidden" value="${RETURN_ACTION}" name="returnAction" id="returnAction">
				<p class="popbult01 pop_b25">我的電子郵件地址: <span class="txt_bold">${email }</span></p>
				<input type="radio" class="radio" id="radio2" name="radio" value="new" />&nbsp;<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/ut_txt_02.gif'/>" alt="請發送至新電子郵件地址" />
				<p class="pad_t7"><span class="popbult01">新地址</span> &nbsp;
					<input type="text" id="newEmail" name="newEmail" class="w150" v:required='trim' m:required="<gm:tagAttb value='jsp.user.pop.popupReEmailCertification.msg.emailChk04' />"
				               v:email m:email="<gm:tagAttb value='jsp.user.pop.popupReEmailCertification.msg.emailChk05' />"/> &nbsp;
				<input type="hidden" id="duplicateEmailCheck" value="N" v:scompare="eq,Y" m:scompare="<gm:tagAttb value='jsp.user.pop.popupReEmailCertification.msg.emailChk06' />">
				<a href="#"><img id="emailCheck" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_check.gif'/>" alt="檢查重複" /></a></p>
				<p class="pop_txt1">若更改電子郵件址, 系統會自動變更您的電子郵件地址!</p>
			</div>
		</div>
		<div class="pop_btn">

			<a href="#"><img id="emailConfirm" name="emailConfirm" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_certify.gif' />" alt="確定"></a>
			<a href="javascript:closePop();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_cancle.gif' />" alt="取消"></a>
     
		</div>
		<p class="pop_close"><a href="javascript:closePop();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif'/>" alt="close"></a></p>
	</div>

</div>
</form>