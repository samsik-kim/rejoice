<?
	include "../setup.php";

	$dbname="gallery";//�Խ��� ���̺��.

	if (!$page) $page=1; //������ �ʱⰪ

	$nperpage=18;//�������� �Խù���

	$addn[]="seq_no desc";

$result = array(); //����� �迭�� �ޱ� ���� �迭������ �����Ѵ�.

$result = Paging_Page($dbname, $addw, $addt, $page, $nperpage);

$totalblock = $result[1]; //��ü����
$nowb = $result[2];//�������ȣ
$nows = $result[3];//������� ������������ȣ
$nowe = $result[4];//������� ��������������ȣ
$achno = $result[5];//�Ϸù�ȣ
$allnum = $result[6];//�Ϸù�ȣ
$result = $result[0];//mysql_query��

//URL�� �Բ� �Ѿ �⺻ �Ķ��Ÿ
$para="page=$page";

//������� include
?>
<link rel="stylesheet" type="text/css" href="/utill/css/admin.css">
<br><br>
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr height="30">
    <td><font color="#8B0901"><B>[����������]</font></td>
  </tr>
<tr>
<td height="20"></td>
</tr>
<tr>
<td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
<?
	$i=0;
while ($data=mysql_fetch_array($result)){
	$i++;
?>
	<td class="object" onclick="location.href='/security/board/gview.php?<?=$l_parameter?>&gno=<?=$data[seq_no]?>';" style="cursor:hand;" align="center"><img src="/updata/<?=$data[file1]?>" width="100" height="100"><br><br><?=$gallery_array[$data[gcate]]?></td>
<?
	if (($i%6) == 0){
?>
</tr>
<tr>
<?
	}
$achno--;
}
?>

</tr>
</table>
<tr>
<td height="30" align="center">
<?
//����Ʈ ����¡~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//ó��, ����, ����, ������, �̹���
	$pageimg1 = "�� ";
	$pageimg2 = "[����]";
	$pageimg3 = "[����]";
	$pageimg4 = " ��";
	//ó��, ����, ����, ������, �̹���

echo Paging_num($l_parameter, $totalblock, $nowb, $nows, $nowe, $page, $pageimg1, $pageimg2, $pageimg3, $pageimg4);

//����Ʈ ����¡~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
?>	
</td>
</tr>
<tr>
<td height="30" align="right">
<a href="/security/board/gwrite.php">[�� ��]</a></td>
</tr>
<tr>
<td>&nbsp;</td>
</tr>
</table>