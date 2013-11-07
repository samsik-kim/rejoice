<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 
<script language="javascript">
function successUdate() {
	alert("<g:string value="${accountsS.settYyyymm}" format="L####-##" /><gm:string value="之結算已結束"/>");
	opener.goPage("1");
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