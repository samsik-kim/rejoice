<?
	include "../setup.php";

if (!$bbs_cd) $bbs_cd="1";

	include "sub_menu.php";

	$dbname="board";//�Խ��� ���̺��.

	if (!$page) $page=1; //������ �ʱⰪ

	$nperpage=10;//�������� �Խù���

if ($bbs_cd != 1){
	$addw[]="bbs_cd=$bbs_cd";//�⺻ ����
}
	$addw[]="del_yn='N'";

if ($keyg && $key){

	if ($keyg == "code_name"){
		$key2 = substr($key,0,4);

		$sql_imsi = "select * from $dbname where code_name='$key'";
		$sql_imsi_result = mysql_query($sql_imsi);

	}else{
		$key2 = $key;
	}
	$addw[] = "($keyg like '%$key2%')";
}

if ($y2 && $m2 && $d2){
	$addw[] = "(crt_date between ('$y1-$m1-$d1 00:00:00') and ('$y2-$m2-$d2 23:59:59'))";
}else{
	$y1 = date("Y");
	$m1 = date("m");
	$d1 = date("d");
}
if ($keyg == "code_name"){
	$addt[] = "code_name desc";
}else{
	$addt[] = "code_num asc";
}

	$addt[]="list_num desc";
//	$addt[]="ref asc";

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
$para="page=$page&bbs_cd=$bbs_cd&keyg=$keyg&key=$key&y1=$y1&m1=$m1&d1=$d1&y2=$y2&m2=$m2&d2=$d2";

//������� include
?>
<SCRIPT LANGUAGE="JavaScript">
<!--
<?
if ($keyg == "code_name" || $keyg == "code_num"){
	echo "top.amenu.location.href='/security/left.html?keyg=".$keyg."&key=".$key."';";
}else{
	echo "top.amenu.location.href='/security/left.html?keyg=&key=';";
}
?>
function pop(frm)
{
	window.open("","printboard","width=680, height=600, scrollbars=yes, top=10, left=10");//�켱 ��ũâ ���ð�..
	frm.target="printboard";	//��� â���� Ÿ�ټ���.
	frm.action="/security/board/print.php";
	frm.submit();
}
//-->
</SCRIPT>
<link rel="stylesheet" type="text/css" href="/utill/css/admin.css">
<br><br>
<table width="800" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr> 
     <td colspan="2" valign="top"><?=$title?></td>
  </tr>
  <tr> 
      <td colspan="2" align="center" valign="top">&nbsp;</td>
  </tr>
  <tr> 
      <td colspan="2" align="center" valign="top">
<table width="800" border="0" cellspacing="1" cellpadding="0" bgcolor="#00025B">
<form name="bsch" method="post" action="<?=$PHP_SELF?>">
<input type="hidden" name="bbs_cd" value="<?=$bbs_cd?>">
<input type="hidden" name="page" value="<?=$page?>">
          <tr height="30" align="center">  
            <td class="subject" colspan="5">�� ��</td>
         </tr>
          <tr height="30" align="center">  
            <td width="100" class="object">
<select name="keyg">
<option value="code_num" <?if ($keyg=="code_num") echo "selected";?>>�ڵ�˻�</option>
<option value="code_name" <?if ($keyg=="code_name") echo "selected";?>>�����˻�</option>
<option value="subject" <?if ($keyg=="subject") echo "selected";?>>����˻�</option>
<option value="content" <?if ($keyg=="content") echo "selected";?>>����˻�</option>
</select>
			</td>
            <td width="120" class="object">
<input type="text" name="key" style="width:90%;" value="<?=$key?>">
			</td>
            <td width="80" class="object">
���ڰ˻�
			</td>
            <td class="object">
<input type="text" name="y1" size="4" maxlength="4" value="<?=$y1?>">
 �� 
<input type="text" name="m1" size="2" maxlength="2" value="<?=$m1?>">
 �� 
<input type="text" name="d1" size="2" maxlength="2" value="<?=$d1?>">
 ��
  ~
<input type="text" name="y2" size="4" maxlength="4" value="<?=$y2?>">
 �� 
<input type="text" name="m2" size="2" maxlength="2" value="<?=$m2?>">
 �� 
<input type="text" name="d2" size="2" maxlength="2" value="<?=$d2?>">
 ��
			</td>
            <td width="100" class="object">
<input type="submit" value="�� ��">
<input type="button" value="�ʱ�ȭ" onclick="location.href='/security/board/list.php?bbs_cd=<?=$bbs_cd?>';">
			</td>
         </tr>
</form>
</table>
	  </td>
  </tr>
<?
	if ($keyg=="code_num"){
		$code_data = fetch_array("code_cate", "where code_num='$key'");
?>
  <tr> 
      <td colspan="2" align="center" valign="top">&nbsp;</td>
  </tr>
  <tr> 
      <td colspan="2" align="center" valign="top">
<table width="800" border="0" cellpadding="0" cellspacing="1" bgcolor="#330066">
<tr height="30" bgcolor="white">
	<td class="subject" width="100">�� �� ��</td>
	<td class="object" style="padding-left:5px;"><?=$code_data[code_name]?></td>
	<td class="subject" width="100">�����ڵ�</td>
	<td class="object" style="padding-left:5px;"><?=$code_data[code_num]?></td>
</tr>
 <tr height="30" bgcolor="white">
	<td class="subject">��ȭ��ȣ</td>
	<td class="object" style="padding-left:5px;">
 	<?=$code_data[tel]?>
<!-- 	<?=$code_data[tel1]?>-<?=$code_data[tel2]?>-<?=$code_data[tel3]?> -->
	</td>
	<td class="subject">��������ó</td>
	<td class="object" style="padding-left:5px;">
	<?=$code_data[info_tel]?>
<!-- 	<?=$code_data[info_tel1]?>-<?=$code_data[info_tel2]?>-<?=$code_data[info_tel3]?> -->
	</td>
</tr>
<tr height="30" bgcolor="white">
	<td class="subject">���к���</td>
	<td class="object" style="padding-left:5px;"><?=$code_data[boyujibun]?></td>
	<td class="subject">����</td>
	<td class="object" style="padding-left:5px;"><?=$code_data[juju]?></td>
</tr>
</table>
	  </td>
  </tr>
  <tr> 
      <td colspan="2" align="center" valign="top">&nbsp;</td>
  </tr>
  <tr> 
      <td colspan="2" align="right"><a href="/security/code_cate/write.php?<?=$para?>&ref=board&cno=<?=$code_data[seq_no]?>">[�ڵ����]</a></td>
  </tr>
<?}?>
  <tr> 
      <td colspan="2" align="center" valign="top">&nbsp;</td>
  </tr>
  <tr> 
      <td colspan="2" align="right"><a href="write.php?<?=$para?>">[�۾���]</a></td>
  </tr>
  <tr> 
      <td colspan="2" align="center" valign="top">&nbsp;</td>
  </tr>
  <tr> 
     <td colspan=2><table width="800" border="0" cellspacing="1" cellpadding="0" bgcolor="#00025B">
<form name="print_bno"method="post" action="/security/board/print.php" target="printboard">
<input type="hidden" name="bno[]" value="">
		  <tr height="30" class="subject" align="center">  
            <td width="150">�ۼ���</td>
            <td>�� ��</td>
            <td width="150">
�� ��
 | 
<a href="javascript:pop(this.print_bno);">[�μ�]</a>
			</td>
         </tr>
<?
if ($keyg == "code_name"){
while ($data_imsi=mysql_fetch_array($sql_imsi_result)){
	$rimsi=explode(" ",$data_imsi[crt_date]);
	$date1=$rimsi[0];
	$date2=str_replace("-","",$rimsi[0]);
	$date3=str_replace("-",",",$rimsi[0]);
	$ref=$data[re_level];
?>
			<tr bgcolor="white" height="25">  
<input type="hidden" name="bno[]" value="<?=$data_imsi[seq_no]?>">
                <td align="center"><?=$data_imsi[crt_date]?></td>
                <td style="padding-left:5px;">
	<?if ($ref>0){?>
		<?for ($i=1;$i<=$ref;$i++){?>
			&nbsp;
		<?}?>
	����[�亯]
	<?}?>									  
<a href="list.php?<?=$para?>&bno=<?=$data_imsi[seq_no]?>"><?=$font?><?=cut_str($data_imsi[subject],60)?></font></a>
				  </td>
                   <td align="center"><a href="<?=$PHP_SELF?>?<?=$para?>&keyg=code_num&key=<?=$data_imsi[code_num]?>"><?=$data_imsi[code_name]?> 
				   <font color="red">( <?=$data_imsi[code_num]?> )</font></td>
                </tr>
<?
$achno--;
	}
}
?>

<?
while ($data=mysql_fetch_array($result)){
	if ($data[code_name] != $key){
	$rimsi=explode(" ",$data[crt_date]);
	$date1=$rimsi[0];
	$date2=str_replace("-","",$rimsi[0]);
	$date3=str_replace("-",",",$rimsi[0]);
	$ref=$data[re_level];
?>
			<tr bgcolor="white" height="25">  
<input type="hidden" name="bno[]" value="<?=$data[seq_no]?>">
                <td align="center"><?=$date1?></td>
                <td style="padding-left:5px;">
	<?if ($ref>0){?>
		<?for ($i=1;$i<=$ref;$i++){?>
			&nbsp;
		<?}?>
	����[�亯]
	<?}?>									  
<a href="list.php?<?=$para?>&bno=<?=$data[seq_no]?>"><?=$font?><?=cut_str($data[subject],60)?></font></a>
				  </td>
                   <td align="center"><a href="<?=$PHP_SELF?>?<?=$para?>&keyg=code_num&key=<?=$data[code_num]?>"><?=$data[code_name]?> 
				   <font color="red">( <?=$data[code_num]?> )</font></td>
                </tr>
<?
$achno--;
	}
}
?>
                    <tr bgcolor="#C0C0C0" height="3"> 
                      <td colspan="4"></td>
                    </tr>
</form>
                  </table></td>
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

echo Paging_num($para, $totalblock, $nowb, $nows, $nowe, $page, $pageimg1, $pageimg2, $pageimg3, $pageimg4);

//����Ʈ ����¡~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
?>	
</td>
      </tr>
</table>






<SCRIPT LANGUAGE="JavaScript">
<!--
function delchk(){
	if (confirm("������ ����Ÿ�� ������ �Ұ����մϴ�. \n\n���� �����Ͻðڽ��ϱ�?")){
		location.href="/security/board/del.php?<?=$para?>&bno=<?=$bno?>";
	}
}
//-->
</SCRIPT>

<?
if ($bno){

	$data = fetch_array("board", "where seq_no='$bno'");

	$para="page=$page&bbs_cd=$bbs_cd";

?>
<br><br>
<table width="650" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
		  <td><?=$title?></td>
		</tr>
	    <tr>
		   <td>

<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#330066">
<tr height="25" bgcolor="white">
	<td style="padding-left:5px;" colspan=3>
	<font color="#9F0000"> �� <?=$data[code_name]?> ( <?=$data[code_num]?> )</font>
	</td>
</tr>
<tr height="30" bgcolor="white">
	<td class="subject">�� ��</td>
	<td class="object" style="padding-left:5px;"><?=$data[subject]?></td>
	<td align="center" width="150">
<a href="javascript:delchk();">[����]</a>	
&nbsp;&nbsp;
<a href="/security/board/write.php?<?=$para?>&bno=<?=$bno?>">[����]</a>
&nbsp;&nbsp;
<a href="#" onclick="window.open('/security/board/print.php?<?=$para?>&bno=<?=$bno?>&mode=one','printboard','width=680, height=600, scrollbars=yes, top=10, left=10');">[�μ�]</td>
</tr>
<tr height="30" bgcolor="white">
	<td class="object" style="padding:5 5 5 5;" colspan=3>
<?
	if ($data[file1] != ""){
		$refile = explode(".", $data[file1]);
		if ($refile[1] == "jpg" || $refile[1] == "JPG" || $refile[1] == "GIF" || $refile[1] == "gif"){
			$size = ImgSize($dir."updata/".$data[file1], 500);
			$w = $size[0];
			$h = $size[1];
?>		
<div align="center"><img src="/updata/<?=$data[file1]?>" width="<?=$w?>" height="<?=$h?>"></div><br><br>
<?
		}else{
?>
<div align="right">÷�ε� ���� : <a href="/updata/<?=$data[file1]?>" target="_blank"><?=$data[file1]?></a></div><br><br>
<?
		}	
	}
?>
		<?=$data[content]?>
	</td>
</tr>
</table>

</td>
</tr>
<!-- <tr height="30">
	<td align="right">




<?if ($bbs_cd!=1 && $bbs_cd!=4){?>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/security/board/write.php?<?=$para?>&rbno=<?=$bno?>&reply=Y">[ �� �� ]</a>
<?}?>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/security/board/list.php?<?=$para?>">[ �� �� ]</a>

	</td>
</tr> -->
</table>
<?}?>
<br>
<br>