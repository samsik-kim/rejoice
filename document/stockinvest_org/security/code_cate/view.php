<?
	include "../setup.php";

	$data = fetch_array("code_cate", "where seq_no='$cno'");

	$para="page=$page";

?>
<link rel="stylesheet" type="text/css" href="/utill/css/admin.css">
<br><br>
<table width="600" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr height="30"> 
     <td><font color="#AE0000"><B>[ �ڵ���� ]</B></font></td>
  </tr>
  <tr>
	<td>

<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#330066">
<tr height="30" bgcolor="white">
	<td class="subject" width="100">�� �� ��</td>
	<td class="object" style="padding-left:5px;"><?=$data[code_name]?></td>
	<td class="subject" width="100">�����ڵ�</td>
	<td class="object" style="padding-left:5px;"><?=$data[code_num]?></td>
</tr>
 <tr height="30" bgcolor="white">
	<td class="subject">��ȭ��ȣ</td>
	<td class="object" style="padding-left:5px;">
	<?=$data[tel]?>
<!-- 	<?=$data[tel1]?>-<?=$data[tel2]?>-<?=$data[tel3]?> -->
	</td>
	<td class="subject">��������ó</td>
	<td class="object" style="padding-left:5px;">
	<?=$data[info_tel]?>
<!-- 	<?=$data[info_tel1]?>-<?=$data[info_tel2]?>-<?=$data[info_tel3]?> -->
	</td>
</tr>
<tr height="30" bgcolor="white">
	<td class="subject">���к���</td>
	<td class="object" style="padding-left:5px;"><?=$data[boyujibun]?></td>
	<td class="subject">����</td>
	<td class="object" style="padding-left:5px;"><?=$data[juju]?></td>
</tr>
</table>

</td>
</tr>
<tr height="30">
	<td align="right">

<a href="/security/code_cate/write.php?<?=$para?>&cno=<?=$cno?>">[ �� �� ]</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:delchk();">[ �� �� ]</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/security/code_cate/list.php?<?=$para?>">[ �� �� ]</a>

	</td>
</tr>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
function delchk(){
	if (confirm("������ ����Ÿ�� ������ �Ұ����մϴ�. \n\n���� �����Ͻðڽ��ϱ�?")){
		location.href="/security/code_cate/del.php?<?=$para?>&cno=<?=$cno?>";
	}
}
//-->
</SCRIPT>