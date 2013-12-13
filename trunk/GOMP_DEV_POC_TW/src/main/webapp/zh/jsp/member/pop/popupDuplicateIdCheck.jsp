<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<title>아이디 중복체크</title>
<script type="text/javascript">
/*
 * Init
 */
 $(document).ready(function(){
	 <c:if test="${map.result eq 'FAIL'}">
		$('#avsd').hide();
	 </c:if>
	 
	// ID Input Enter event
	$("#id").keypress(function(event) {
		if(event.keyCode == 13) {
			$("#mbrIdCheck").click();
			return false;
		}
	});
	
	//[CHECK] - ID
	$('#mbrIdCheck').click(function(){
		if(showValidate(document.duIdForm.id, 'default', "<gm:string value='jsp.common.msg.title01'/>")){
			$.ajax({
				url: env.contextPath + '/member/ajaxIdCheck.omp',
				dataType: 'json',
				type	: "POST",
				data 	: {mbrId : $('#id').val()},
				async	: true,		
				cache	: false,	
				success: function(json){
					$('#pop_txt_msg').empty();
					if(json.data.result == "SUCCESS"){
						$('#pop_txt_msg').append("您所輸入的<span class='txtcolor06'>"+json.data.mbrId+"</span>可以使用! <br /> 若欲使用其他帳號, 請輸入並檢查! ");
						$('#avsd').show();
						$('#mbrId_1').val(json.data.mbrId);
					}else{
						$('#avsd').hide();
						$('#pop_txt_msg').append("您所輸入的<span class='txtcolor06'>"+json.data.mbrId+"</span>已註冊, 無法使用。 <br /> 請輸入其他帳號, 並再次檢查!");
					}
				}
			});
		}
	});
	
	//[SET] - mbrID 
	$('#useId').click(function(){
		$('#mbrId', parent.document).val($('#mbrId_1').val());
		$("#duplicateIdCheck", parent.document).val("Y");
		closePopupLayer('popupDuplicateIdCheck');
	});
});

</script>
<div class="layerwrap">
	<div id="pop_area01">
	<form name="duIdForm" method="post">
		<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/ut_title_02.gif'/>" alt="아이디 중복체크" /></h2>
		<input type="hidden" id="mbrId_1" value="${map.mbrId }" />
	<c:if test="${map.result eq 'SUCCESS'}">
			<p class="pop_txt2 mar_b8" id="pop_txt_msg">您所輸入的 <span class="txtcolor06"><g:html value="${map.mbrId }"/></span>可以使用! <br /> 若欲使用其他帳號, 請輸入並檢查! </p>
	</c:if>
	<c:if test="${map.result eq 'FAIL'}">
			<p class="pop_txt2 mar_b8" id="pop_txt_msg">您所輸入的 <span class="txtcolor06"><g:html value="${map.mbrId }"/></span>已註冊, 無法使用。 <br /> 請輸入其他帳號, 並再次檢查!</p>
	</c:if>     
			<input type="text" class="w280" name="id" id="id" 
			v:regexp="[a-z0-9]{8,13}" m:regexp="<gm:tagAttb value='jsp.member.pop.popupDuplicateIdCheck.msg.id01'/>" 
			v:required='trim' m:required="<gm:tagAttb value='jsp.member.pop.popupDuplicateIdCheck.msg.id02'/>"/>
			<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_check.gif'/>" alt="檢查重複" id="mbrIdCheck"/></a>
			<div class="pop_btn" id="avsd">
				<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_use.gif'/>" alt="使用" id="useId"/></a>
				<a href="javascript:closePopupLayer('popupDuplicateIdCheck');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_cancle.gif'/>" alt="取消"/></a>
			</div>
			<p class="pop_close"><a href="javascript:closePopupLayer('popupDuplicateIdCheck');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif'/>" alt="close" /></a></p>
	</form>
	</div>
</div>
