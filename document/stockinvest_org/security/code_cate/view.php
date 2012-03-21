<?
	include "../setup.php";

	$data = fetch_array("code_cate", "where seq_no='$cno'");

	$para="page=$page";

?>
<link rel="stylesheet" type="text/css" href="/utill/css/admin.css">
<br><br>
<table width="600" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr height="30"> 
     <td><font color="#AE0000"><B>[ 코드관리 ]</B></font></td>
  </tr>
  <tr>
	<td>

<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#330066">
<tr height="30" bgcolor="white">
	<td class="subject" width="100">종 목 명</td>
	<td class="object" style="padding-left:5px;"><?=$data[code_name]?></td>
	<td class="subject" width="100">종목코드</td>
	<td class="object" style="padding-left:5px;"><?=$data[code_num]?></td>
</tr>
 <tr height="30" bgcolor="white">
	<td class="subject">전화번호</td>
	<td class="object" style="padding-left:5px;">
	<?=$data[tel]?>
<!-- 	<?=$data[tel1]?>-<?=$data[tel2]?>-<?=$data[tel3]?> -->
	</td>
	<td class="subject">정보연락처</td>
	<td class="object" style="padding-left:5px;">
	<?=$data[info_tel]?>
<!-- 	<?=$data[info_tel1]?>-<?=$data[info_tel2]?>-<?=$data[info_tel3]?> -->
	</td>
</tr>
<tr height="30" bgcolor="white">
	<td class="subject">지분보유</td>
	<td class="object" style="padding-left:5px;"><?=$data[boyujibun]?></td>
	<td class="subject">주주</td>
	<td class="object" style="padding-left:5px;"><?=$data[juju]?></td>
</tr>
</table>

</td>
</tr>
<tr height="30">
	<td align="right">

<a href="/security/code_cate/write.php?<?=$para?>&cno=<?=$cno?>">[ 수 정 ]</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:delchk();">[ 삭 제 ]</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/security/code_cate/list.php?<?=$para?>">[ 목 록 ]</a>

	</td>
</tr>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
function delchk(){
	if (confirm("삭제된 데이타는 복구가 불가능합니다. \n\n정말 삭제하시겠습니까?")){
		location.href="/security/code_cate/del.php?<?=$para?>&cno=<?=$cno?>";
	}
}
//-->
</SCRIPT>