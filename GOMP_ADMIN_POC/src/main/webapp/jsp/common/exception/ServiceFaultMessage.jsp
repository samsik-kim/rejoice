<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="s" uri="/struts-tags" 
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core"
%><c:choose>
	<c:when test="${ajaxCall}">
<script>
	alert("<g:string value="${localizedMessage}"/>");
</script>
	</c:when>
	<c:otherwise>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>Whoopy Management System</title>
	<link rel="stylesheet" href="/css/er_style_admin.css" type="text/css">
</head>
<body>
	<div class="error_area">
		<div class="fieldset fs_se">
			<div class="con">
				<div class="b_txt">
					系統有誤。
				</div>
				<div class="s_txt">
					因系統一時故障，無法完成作業。<br />
					請稍候重新進行。
				</div>

				<textarea class="error_textarea" cols="0" rows="0"><g:tagBody value="${localizedMessage}"/>
若故障持續無好轉，
請與Whoopy 管理團隊（００－００００－００００）聯繫。
謝謝。</textarea>
			</div>
		</div>
	</div>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</body>
</html>
	</c:otherwise>
</c:choose>