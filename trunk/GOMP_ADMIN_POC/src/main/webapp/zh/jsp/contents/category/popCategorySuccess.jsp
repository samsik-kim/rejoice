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
<title>類別</title>
 
<script language="javascript">
function successUdate(mode){
	if(mode == "I") {
		alert( "<gm:string value='jsp.contents.category.popCategorySuccess.msg.success_ins'/>" );
	} else {
		alert( "<gm:string value='jsp.contents.category.popCategorySuccess.msg.success_upd'/>" );
	}
	// self.opener.location.reload();
	// alert(env.contextPath);
	self.opener.location.href = "${pageContext.request.contextPath}/contents/selectCategoryList.omp";
	self.close();
}
</script>
</head>
<body>

<script language="javascript">
	successUdate('${mode}');
</script>

</body>
</html>