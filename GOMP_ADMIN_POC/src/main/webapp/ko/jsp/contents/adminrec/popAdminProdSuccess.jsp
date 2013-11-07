<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 
<script language="javascript">
function successUdate(){
	alert("상품이 등록 되었습니다.");
	self.opener.location.reload();
	self.close();
}
</script>
</head>
<body onLoad="successUdate()">

</body>
</html>