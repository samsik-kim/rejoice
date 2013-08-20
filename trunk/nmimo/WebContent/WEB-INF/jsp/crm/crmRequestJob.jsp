<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<title>땡큐 MMS 전송내역</title>
</head>

<script language="javascript">
	function send(val) {
		document.frm.url_type.value = val;
		document.frm.target = "hiddenframe";
		document.frm.submit();
	}
	function send1(val) {
		document.frm.url_type.value = val;
		document.frm.target = "hiddenframe";
		document.frm.submit();
	}
</script>
<body bgcolor="#E3E3E3" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
	<table width="700" height="120" border="0" cellpadding="0"
		cellspacing="0">
		<form method="post" name="frm" action="test_ok.asp">
			<input name="url_type" value="0" type="hidden">
			<tr>
				<td>작업ID : <input name="jobid" value="DATARATE2" type="text">
					<br> 전화번호 : <input name="hp" value="01028752266" type="text"><br>
					개인화필드 : <input name="field" value="20100411134431" type="text">여러개일
					경우 [|]로 구분 ex) param1|param2 ... n<br> 3G,2G여부 : <input
					name="3G_IND" value="3G" type="text"><br> CMD: <input
					name="CMD" value="" type="text"><br>
				</td>
			</tr>
			<tr>
				<td height=10></td>
			</tr>
			<tr>
				<td
					style="border-bottom: 1px solid; border-top: 1px solid; padding: 10px;">
					테스트 유형 1 : 구분이 3G이며 개인화 필드 하나일 경우 <br> 작업ID : ONE_FIELDG<br>
					전화번호 : 01066568017<br> 개인화필드 : 150<br> 3G,2G여부 : 3G
				</td>
			</tr>
			<tr>
				<td style="border-bottom: 1px solid; padding: 10px;">테스트 유형 1 :
					구분이 업고, 개인화 필드 없을 경우 <br> 작업ID : NO_FIELD<br> 전화번호 :
					01066568017<br> 개인화필드 :
				</td>
			</tr>
			<tr>
				<td style="border-bottom: 1px solid; padding: 10px;">테스트 유형 1 :
					구분이 업고, 개인화 필드 1개일 경우 <br> 작업ID : ONE_FIELD<br> 전화번호 :
					01066568017<br> 개인화필드 : 100
				</td>
			</tr>
			<tr>
				<td style="border-bottom: 1px solid; padding: 10px;">테스트 유형 1 :
					구분이 업고, 개인화 필드 2개일 경우 <br> 작업ID : TWO_FIELD<br> 전화번호 :
					01066568017<br> 개인화필드 : 180|250
				</td>
			</tr>
		</form>
		<tr>
			<td align="center"><input type="button" name="btn" value="전송"
				onclick="send('3');"></td>
		</tr>
		<tr>
			<td align="center"><input type="button" name="btn" value="전송뉴"
				onclick="send('2');"></td>
		</tr>
		<tr>
			<td align="center"><input type="button" name="btn"
				value="전송2(상용적용후확인용)" onclick="send1('1');"></td>
		</tr>
		<tr>
			<td align="center"><input type="button" name="btn"
				value="뉴전송2(상용적용후확인용)" onclick="send1('4');"></td>
		</tr>
	</table>
	<iframe name="hiddenframe" border="0" width="500" height="350"></iframe>
</body>
</html>

