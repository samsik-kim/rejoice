<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 
<script language="javascript">
function successUdate(){
	alert("<gm:string value="jsp.member.punish.popPunishMemberSuccess.msg.success_ins"/>");
	self.opener.location.href = "<c:url value="/member/punishMemberList.omp"/>"; 
	//self.opener.location.href = "<c:url value="/member/punishMemberList.omp?srchFlag=TRUE"/>"; 
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