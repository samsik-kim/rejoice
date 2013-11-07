<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.omp.dev.common.util.config.DevPropTest" %>

<html>
<head>
<title>DEV_CONF</title>
<script type="text/javascript">


</script>
</head>

<body>
<h1>DEV_CONF</h1>
<BR/>
<% 
			DevPropTest d = new DevPropTest();
			out.println(d.getString("omp.dev.url"));
%>			
</body>
</html>
