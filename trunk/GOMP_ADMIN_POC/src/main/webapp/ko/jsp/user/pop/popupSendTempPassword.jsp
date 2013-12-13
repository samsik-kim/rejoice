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
		<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/ut_title_03.gif'/>" alt="비밀번호 15회 오류" /></h2>
		<p class="pop_txt2">비밀번호 15회 입력오류로 인해 <span class="txtcolor06">임시비밀번호가 이메일로 발송</span> 되었습니다. 이메일을 확인하신 후 재로그인 해주십시오</p>
		<div class="pop_btn">
			<a href="javascript:closePop();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_confirm.gif'/>" alt="확인" /></a>
		</div>
		<p class="pop_close"><a href="javascript:closePop();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif'/>" alt="닫기" /></a></p>

	</div>

</div>