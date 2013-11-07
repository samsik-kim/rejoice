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
<html>
<head>
<script>
	alert("<g:string value="${localizedMessage}"/>");
	history.back();
</script>
</head>
<body>
</body>
</html>
	</c:otherwise>
</c:choose>