<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 
<script language="javascript">
function successUdate(){
	alert("<gm:string value="jsp.adminmgr.account.popAccountAuthSuccess.msg.success_mod"/>");
	// self.opener.location.reload();
	self.opener.location.href = "${pageContext.request.contextPath}/adminMgr/accountList.omp";
	self.close();
}
</script>
</head>
<body>

<script language="javascript">
	successUdate();
</script>

</body>
</html>