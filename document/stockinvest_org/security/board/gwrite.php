<?
	require("../setup.php");

	if ($gno){
		$data = fetch_array("gallery", "where seq_no='$gno'");
	}
?>
<link rel="stylesheet" href="/utill/css/admin.css" type="text/css">
<br>
<table border="0" width="600" cellpadding="0" cellspacing="0" align="center">
  <tr height="30">
    <td><font color="#8B0901"><B>[����������]</font></td>
  </tr>
<tr>
  <td>
<table width="100%" cellpadding="0" cellspacing="1" border="0" bgcolor="#01116B">
<form name="gallery" method="post" action="/security/board/gallery_ok.php" enctype="multipart/form-data">
<input type="hidden" name="page" value="<?=$page?>">
<input type="hidden" name="gno" value="<?=$gno?>">
<!-- <tr  bgcolor="white" height="30">
	<td class="subject" width="100">�� ��</td>
	<td style="padding-left:10px;"><input type="text" size="30" name="subject" value="<?=$data[subject]?>"></td>
</tr>
<tr  bgcolor="white" height="30">
	<td class="subject" width="100">�ۼ���</td>
	<td style="padding-left:10px;"><input type="text" size="30" name="name" value="<?=$adname?>"></td>
</tr> -->
<tr  bgcolor="white" height="30">
	<td class="subject">�з�����</td>
	<td style="padding-left:10px;">
<select name="gcate">
<option value="">�з�����</option>
<?
	echo MkSelect2($gallery_array, $data[gcate]);
?>
</select>
	</td>
</tr>
<tr  bgcolor="white" height="30">
	<td class="subject">������</td>
	<td style="padding-left:10px;"><input type="file" size="50" name="img1"></td>
</tr>
<tr  bgcolor="white" height="30">
	<td class="subject" colspan=2>ȸ������ (ȸ�������� ��츸 �Է��Ͻø� �˴ϴ�.)</td>
</tr>
<tr  bgcolor="white" height="30">
	<td class="subject">ȸ�����̵�</td>
	<td style="padding-left:10px;"><input type="text" size="50" name="name" value="<?=$data[name]?>"></td>
</tr>
<!-- <tr  bgcolor="white" height="100">
	<td class="subject">�� ��</td>
	<td align="center"><textarea style="FONT-SIZE: 9pt; BORDER-TOP-STYLE: groove; FONT-FAMILY: ����; BORDER-RIGHT-STYLE: groove; BORDER-LEFT-STYLE: groove; BORDER-BOTTOM-STYLE: groove;width:98%;height:98%;" name="memo"><?=$data[memo]?></textarea></td>
</tr> -->
</form>
</table></td>
</tr>
<tr height="30">
	<td align="right">
	<a href="javascript:gallery.submit();">[�� ��]</a>
	<a href="/security/board/glist.php">[�� ��]</a>
	</td>
</tr>
</table>
