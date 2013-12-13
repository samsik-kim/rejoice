<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<form name="tempPw" method="post" id="tempPw">
	<input type="hidden" name="tempPw" value="<g:tagAttb value='${session.tempPw }'/>">
	<input type="hidden" name="emailAddr" value="<g:tagAttb value='${session.emailAddr }'/>">
	<input type="hidden" name="mbrNo" value="<g:tagAttb value='${session.mbrNo }'/>">
	<input type="hidden" name="mbrId" value="<g:tagAttb value='${session.mbrId }'/>">
</form>
<input type="hidden" value="${RETURN_ACTION}" name="returnAction" id="returnAction">
<script>
	var returnUrl = "";
	var mainUrl = "${pageContext.request.contextPath }/main/main.omp";
	var loginUrl = "${pageContext.request.contextPath }/login/loginMain.omp";
	<%
		String reqUrl = request.getRequestURL().toString();
	%>
		returnUrl = '${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }';
		mainUrl = '${CONF['omp.common.url-prefix.http.dev']}'+ mainUrl;
		loginUrl = '${CONF['omp.common.url-prefix.http.dev']}'+loginUrl;
	<c:if test="${session.resultMsg == 'fail'}">
		alert("<gm:string value='jsp.user.loginResult.msg01'/>");
		returnUrl = $('#returnAction').val();
		location.href = returnUrl;
	</c:if>
	<c:if test="${session.resultMsg == 'loginFailTempPw'}">
		var param = $('#tempPw').serialize();
		$.ajax({
			type: "POST",
			url: '${pageContext.request.contextPath }/login/ajaxSendTempPassword.omp',
			data : param,
			beforeSend: function(xhr) {},
			success: function(data) {
				createPopupLayer('popupSendTempPassword');
				$("#popupSendTempPassword_body").html(data);
				showPopupLayer('popupSendTempPassword', 'wrap');
			}
		});
	</c:if>
	<c:if test="${session.resultMsg == 'reSendEmail'}">
		$.ajax({
			type: "POST",
			url: '${pageContext.request.contextPath }/login/ajaxReEmailCertification.omp',
			data: {email : '${session.emailAddr}',
				   mbrNo : '${session.mbrNo}'},
			beforeSend: function(xhr) {},
			success: function(data) {
				createPopupLayer('popupReEmailCertification');
				$("#popupReEmailCertification_body").html(data);
				showPopupLayer('popupReEmailCertification', 'wrap');			
			}
		});
	</c:if>
	
	<c:if test="${session.resultMsg == 'loginResult'}">
		// 로그인 성공
		<c:if test="${empty RETURN_URL}">
			returnUrl = mainUrl;
		</c:if>
	
		<c:if test="${not empty RETURN_URL}">
			returnUrl = "${RETURN_URL}";
			<c:remove var="RETURN_URL" scope="session" />
		</c:if>
		location.href = returnUrl;
	</c:if>
</script>