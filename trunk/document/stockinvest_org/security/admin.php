<?
	include "setup.php";

if ($mode=="mody"){
	mysql_query("update admin set admin_id='$admin_id', passwd='$admin_pw' where seq_no='1'");
}

	$data = fetch_array("admin", "where seq_no=1");
?>
<SCRIPT LANGUAGE="JavaScript">
<!--
function inquery(){
	bank.submit();
}
//-->
</SCRIPT>
<link rel="stylesheet" href="/utill/css/admin.css" type="text/css">
<br><br>
<table width="600" cellpadding="0" cellspacing="0" border="0" align="center">
  <tr height="30">
    <td><font color="#990000">[관리자계정설정]</font></td>
  </tr>
  <tr>
    <td>
	  <table width="600" cellpadding="0" cellspacing="1" border="0" bgcolor="#330066">
  <form name="bank" method="post" action="<?=$PHP_SELF?>">
  <input type="hidden" name="mode" value="mody">
		<tr bgcolor="white">
		  <td class="subject">관리자 아이디</td>
		  <td class="subject">관리자 비밀번호</td>
		  <td class="subject" width="100">버 튼</td>
		</tr>
		<tr bgcolor="white" height="30">
			<td align="center"><input type="text" name="admin_id" value="<?=$data[admin_id]?>"></td>
			<td align="center"><input type="text" name="admin_pw" value="<?=$data[passwd]?>"></td>
			<td align="center"><input type="submit" value="확 인" style="width:98%;height:98%;"></td>
		</tr>
</form>
	  </table>
	  </td></tr>
  </td></tr>
</table>