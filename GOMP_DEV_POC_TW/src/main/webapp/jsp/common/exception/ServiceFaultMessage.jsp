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
	<title>T store monogement System</title>
	<link rel="stylesheet" href="/css/er_style.css" type="text/css">
</head>
<body>
	<div class="error_area">
		<div class="fieldset fs_se">
			<div class="con">
				<div class="b_txt">
					시스템 오류가 발생 하였습니다.
				</div>
				<div class="s_txt">
					일시적인 시스템 오류가 발생하여 요청하신 작업을 수행할 수 없습니다.<br />
					잠시 후 작업을 다시 수행해 주시기 바랍니다.
				</div>

				<textarea class="error_textarea" cols="0" rows="0"><g:tagBody value="${localizedMessage}"/></textarea>
			</div>
		</div>
	</div>
	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</body>
</html>
	</c:otherwise>
</c:choose>