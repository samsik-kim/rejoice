<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<td>* 고객명 :</td>
		<td><input type="text" name="MEMBER_NM"></td>
	</tr>
	<tr>
		<td>* 휴대폰 :</td>
		<td>
			<select name="tel1">
				<option value="010">010</option>
				<option value="011">011</option>
				<option value="016">016</option>
				<option value="017">017</option>
				<option value="018">018</option>
				<option value="019">019</option>
			</select>
			 - <input type="text" name="tel2">
			 - <input type="text" name="tel3">
		</td>
	</tr>
	<tr>
		<td>* 전화번호 :</td>
		<td><input type="text" name="CONTANCT2"></td>
	</tr>
	<tr>
		<td>* 방문횟수 :</td>
		<td><input type="text" name="VST_CNT"></td>
	</tr>
	<tr>
		<td>* 주소 :</td>
		<td><input type="text" name="ADDR"></td>
	</tr>
	<tr>
		<td>* 당첨횟수 :</td>
		<td><input type="text" name="WIN_CNT"></td>
	</tr>
</table>
</body>
</html>