<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<script type="text/javascript">
	
 	var resultCode = '${resultCode}';
 	var resultMsg = '${resultMsg}';

 	//파일업로드 처리
 	if(resultCode == "FAIL"){
 		alert(resultMsg);
 		window.open('','_self').close();
 	}else{
 		alert(resultMsg);
 		window.open('','_self').close();
 	}

</script>