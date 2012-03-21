<?
	require("../setup.php");

$data = fetch_array("code_cate", "where seq_no='$cno'");

$para="page=$page";

?>
<SCRIPT LANGUAGE="JavaScript">
<!--
function chkcode(now_code){

// 코드번호를 미리 뽑아낸다.. 
	var aleady_code = new Array();
<?
	$sql = "select * from code_cate";
	$result = mysql_query($sql);

$z = 0;
	while ($run = mysql_fetch_array($result)){
		$z++;
?>
	aleady_code[<?=$z?>] = "<?=$run[code_num]?>";
<?
	}
?>
	var allnum_code = <?=$z?> - 1 + 1;

// 여기까지 코드번호를 미리 뽑아낸다.. 어차피 관리자만 쓸꺼니깐 상관없음..

	for (j=1;j<=allnum_code;j++){

		if (now_code == aleady_code[j]){
			alert("이미 등록된 종목코드입니다.");
			document.code.code_num.value="";
			document.code.code_num.focus();
			return;
		}

	}

}
//-->
</SCRIPT>
<link rel="stylesheet" type="text/css" href="/utill/css/admin.css">
<br><br>
<table width="600" border="0" cellpadding="0" cellspacing="0" align="center">
<form name="code" method="post" action="/security/code_cate/code_ok.php">
<input type="hidden" name="cno" value="<?=$cno?>">
<input type="hidden" name="ref" value="<?=$ref?>">
<input type="hidden" name="keyg" value="<?=$keyg?>">
<input type="hidden" name="key" value="<?=$key?>">
<input type="hidden" name="key" value="<?=$key?>">
<input type="hidden" name="bbs_cd" value="<?=$bbs_cd?>">
<tr height="30">
	<td><font color="#AE0000"><B>[ 코드관리 ]</B></font></td>
</tr>
<tr>
   <td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#330066">
			<tr height="30" bgcolor="white">
				<td class="subject" width="100">종 목 명</td>
				<td class="object" style="padding-left:5px;">
<input name="code_name" type="text" size="20" maxlength="20" value="<?=$data[code_name]?>">
				</td>
				<td class="subject" width="100">종목코드</td>
				<td class="object" style="padding-left:5px;">
<input name="code_num" type="text" size="20" maxlength="6" value="<?=$data[code_num]?>" onblur="chkcode(this.value);">
				</td>
			</tr>
			<tr height="30" bgcolor="white">
				<td class="subject">전화번호</td>
				<td class="object" style="padding-left:5px;">
<input name="tel" type="text" size="20" value="<?=$data[tel]?>">
				</td>
				<td class="subject">정보연락처</td>
				<td class="object" style="padding-left:5px;">
<input name="info_tel" type="text" size="20" value="<?=$data[info_tel]?>">
				</td>
			</tr>
			<tr height="30" bgcolor="white">
				<td class="subject" width="100">지분보유</td>
				<td class="object" style="padding-left:5px;">
<input name="boyujibun" type="text" size="30" maxlength="30" value="<?=$data[boyujibun]?>">
				</td>
				<td class="subject" width="100">주주</td>
				<td class="object" style="padding-left:5px;">
<input name="juju" type="text" size="30" maxlength="30" value="<?=$data[juju]?>">
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr height="30">
	<td align="right">
<a href="javascript:this.code.submit();">[ 완 료 ]</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/security/code_cate/list.php?<?=$para?>">[ 목 록 ]</a>
	</td>
</tr>
</form>
</table>