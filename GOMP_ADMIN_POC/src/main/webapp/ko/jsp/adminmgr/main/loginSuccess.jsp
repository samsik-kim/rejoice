<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.omp.admin.common.Constants" %>
<%@ taglib prefix="s" uri="/struts-tags" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>T store management System</title>
<script language="javascript">
function successLogin() {
	// alert("로그인 되었습니다. ${loginSuccessMoveUrl}");
	// local 용
	self.location.replace("${loginSuccessMoveUrl}");
	// STG, REAL 용
	//self.location.href="http://<%=request.getServerName()%>${loginSuccessMoveUrl}"; 
}
</script>
</head>
<body>

<script language="javascript">
	successLogin();
</script>

</body>
</html>