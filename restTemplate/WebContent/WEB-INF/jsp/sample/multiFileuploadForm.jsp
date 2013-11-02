<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#addItemBtn").click(function(){
		// item 의 최대번호 구하기
        var lastItemNo = $("#example tr:last").attr("class").replace("item", "");
        var newitem = $("#example tr:eq(0)").clone();
        newitem.removeClass();
        newitem.find("td:eq(0)").attr("rowspan", "1");
        newitem.addClass("item"+(parseInt(lastItemNo)+1));
        $("#example").append(newitem);
	});
});

</script>
</head>
<body>
<form action="/Mupload.do" name="frm" method="post" enctype="multipart/form-data">
	<a href="#" id="addItemBtn">옵션추가</a>
	<table>
		<tr>
			<td align="center" width="100">첨부1</td>
			<td colspan="3"><input type="file" name="mapFiles"></td>
		</tr>
		<tr>
			<td align="center" width="100">첨부2</td>
			<td colspan="3"><input type="file" name="mapFiles"></td>
		</tr>
		<tr>
			<td align="center" width="100">첨부3</td>
			<td colspan="3"><input type="file" name="mapFiles"></td>
		</tr>
	</table>
	<table id="example">
		<tr class="item1">
			<td class="rowtitle" align="center" width="100">첨부</td>
			<td colspan="3"><input type="file" name="files" id="file"></td>
		</tr>
	</table>
	<input type="submit" name="upload" value="upload"/>
</form>
</body>
</html>