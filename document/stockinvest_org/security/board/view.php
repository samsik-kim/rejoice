<?
	require("../setup.php");
	include "sub_menu.php";

	$data = fetch_array("board", "where seq_no='$bno'");

$para="page=$page&bbs_cd=$bbs_cd";

?>
<link rel="stylesheet" type="text/css" href="/utill/css/admin.css">
<br><br>
<table width="600" border="0" cellpadding="0" cellspacing="0" align="center">
  <tr>
		  <td><?=$title?></td>
		</tr>
	    <tr>
		   <td>

<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#330066">
<tr height="30" bgcolor="white">
	<td class="subject">�� ��</td>
	<td class="object" style="padding-left:5px;">
	<?=$data[code_name]?> ( <?=$data[code_num]?> )
	</td>
</tr>
<tr height="30" bgcolor="white">
	<td class="subject">�� ��</td>
	<td class="object" style="padding-left:5px;"><?=$data[subject]?></td>
</tr>
<tr height="30" bgcolor="white">
	<td class="subject">�� ��</td>
	<td class="object" style="padding:5 5 5 5;">
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
<tr height="30">
	<td align="right">

<a href="/security/board/write.php?<?=$para?>&bno=<?=$bno?>">[ �� �� ]</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:delchk();">[ �� �� ]</a>
<?if ($bbs_cd!=1 && $bbs_cd!=4){?>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/security/board/write.php?<?=$para?>&rbno=<?=$bno?>&reply=Y">[ �� �� ]</a>
<?}?>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/security/board/list.php?<?=$para?>">[ �� �� ]</a>

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