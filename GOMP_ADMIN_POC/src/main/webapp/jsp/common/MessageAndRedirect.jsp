<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
%><%@ taglib prefix="s" uri="/struts-tags"
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core"
%><%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"
%><html>
<head>
<script>
$(document).ready(function() {
	alert("<gm:string value="${message}"/>");
	location.replace("<g:string value="${to}"/>");
});
</script>
</head>
<body>
</body>
</html>
