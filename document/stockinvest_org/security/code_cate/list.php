<?
	include "../setup.php";

	$dbname="code_cate";//�Խ��� ���̺��.

	if (!$page) $page=1; //������ �ʱⰪ

	$nperpage=10;//�������� �Խù���

//	$addw[]="";//�⺻ ����

	$addt[]="seq_no desc";

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
$para="page=$page&bbs_cd=$bbs_cd";

//������� include
?>
<link rel="stylesheet" type="text/css" href="/utill/css/admin.css">
<br><br>
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr height="30"> 
     <td colspan="2"><font color="#AE0000"><B>[ �ڵ���� ]</B></font></td>
  </tr>
  <tr> 
     <td colspan=2><table width="680" border="0" cellspacing="1" cellpadding="0" bgcolor="#00025B">
          <tr height="30" class="subject" align="center">  
            <td width="100">�����</td>
            <td width="100">�����ڵ�</td>
            <td width="100">���к���</td>
            <td width="100">��ȭ��ȣ</td>
            <td width="100">��������ó</td>
            <td width="100">�����</td>
         </tr>
<?
	while ($data=mysql_fetch_array($result)){
		$tel = $data[tel1]." - ".$data[tel2]." - ".$data[tel3];
		$infotel = $data[info_tel1]." - ".$data[info_tel2]." - ".$data[info_tel3];
?>
			<tr bgcolor="white" height="25" style="cursor:hand;" onMouseOver="this.style.backgroundColor='#E6E6E6'; return true;" onMouseOut="this.style.backgroundColor='#ffffff'; return true;" onclick="location.href='/security/code_cate/view.php?cno=<?=$data[seq_no]?>';">  
                <td align="center"><?=$data[code_name]?></td>
                <td align="center"><?=$data[code_num]?></td>
                <td align="center"><?=$data[boyujibun]?></td>
                <td align="center"><?=$data[tel]?></td>
                <td align="center"><?=$data[info_tel]?></td>
                <td align="center"><?=date("Y-m-d", $data[crt_date])?></td>
           </tr>
<?
	$achno--;
}
?>
          <tr bgcolor="#C0C0C0" height="3"> 
              <td colspan="6"></td>
          </tr>
		</table>
	</td>
</tr>
<tr bgcolor="white"> 
    <td align="center" height="30">
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
	<td width="10%" align="right"><a href="write.php?<?=$para?>">[�ڵ���]</a></td>
</tr>
</table>