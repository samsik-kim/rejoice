<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.omp.dev.common.util.config.OmpPropTest" %>

<html>
<head>
<title>OMP_CONF</title>
<script type="text/javascript">


</script>
</head>

<body>
<h1>OMP_CONF</h1>
<BR/>
<% 
			OmpPropTest d = new OmpPropTest();
			out.println(d.getString("omp.dev.url"));
%>			
</body>
</html>
