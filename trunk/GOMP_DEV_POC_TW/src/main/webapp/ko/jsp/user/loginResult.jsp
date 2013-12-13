<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form name="tempPw" method="post" id="tempPw">
	<input type="hidden" name="tempPw" value="${session.tempPw }">
	<input type="hidden" name="emailAddr" value="${session.emailAddr }">
	<input type="hidden" name="mbrNo" value="${session.mbrNo }">
	<input type="hidden" name="mbrId" value="${session.mbrId }">
</form>
<input type="hidden" value="${RETURN_ACTION}" name="returnAction" id="returnAction">
<script>
	var returnUrl = "";
	var mainUrl = "${pageContext.request.contextPath }/main/main.omp";
	var loginUrl = "${pageContext.request.contextPath }/login/loginMain.omp";
	<%
		
		String reqUrl = request.getRequestURL().toString();
		
	if(reqUrl.indexOf("https") > -1 ) {
		    reqUrl = reqUrl.substring(0, reqUrl.indexOf(request.getRequestURI())).replaceFirst("https", "http");
	%>
		returnUrl = "<%=reqUrl%>";
		mainUrl = "<%=reqUrl%>"+ mainUrl;
		loginUrl = "<%=reqUrl%>"+loginUrl; 
	<%	}  %>
		
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