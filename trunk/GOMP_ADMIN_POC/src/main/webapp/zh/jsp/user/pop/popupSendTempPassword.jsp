<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
function closePop(){
	closePopupLayer('popupSendTempPassword');
	location.href = $('#returnAction').val();
}
</script>
<div class="layerwrap">
	
	<div id="pop_area01">
		<input type="hidden" value="${RETURN_ACTION}" name="returnAction" id="returnAction">
		<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/ut_title_03.gif'/>" alt="密碼錯誤15次" /></h2>
		<p class="pop_txt2">密碼錯誤達15次,系統, 系統已將 <span class="txtcolor06">臨時密碼發至會員信箱.</span> <br />請確認信箱後重新登入.</p>
		<div class="pop_btn">
			<a href="javascript:closePop();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_confirm.gif'/>" alt="確認" /></a>
		</div>
		<p class="pop_close"><a href="javascript:closePop();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif'/>" alt="close" /></a></p>

	</div>

</div>