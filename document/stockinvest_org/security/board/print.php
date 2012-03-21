<?
	include "../setup.php";

	$dbname = "board";

?>
<html>
<title>씨투투자자문</title>
<link rel="stylesheet" type="text/css" href="/utill/css/admin.css">
<SCRIPT LANGUAGE="JavaScript">
<!--
function printall(){
	document.all.buttonall.style.display="none";
	window.print();
}
//-->
</SCRIPT>
</head>
<body marginwidth="0" marginheight="0" topmargin="0" leftmargin="0">
<br>
<?
if ($mode=="one"){
	$data = fetch_array($dbname, "where seq_no='$bno'");
	$title = $data[code_name];
?>

<Br>
<table width="650" border="1" cellpadding="0" cellspacing="0" align="center" style="border:1 solid gray;">
  <tr height="30" bgcolor="#DFDFDF">
	  <td style="padding-left:10px;border:1 solid gray;"><B><?=$title?></B></td>
  </tr>
  <tr height="30" bgcolor="#DFDFDF">
	  <td style="padding-left:10px;border:1 solid gray;">
제 목 : <B><?=$data[subject]?></B>
	</td>
  </tr>
  <tr height="100">
	<td style="padding:5 5 5 5;" style="border:1 solid gray;" valign="top">
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
		}
	}
?>
		<?=$data[content]?>
	</td>
</tr>
</table>

<?
}else{
	for ($z=1;$z<sizeof($bno);$z++){
		$data = fetch_array($dbname, "where seq_no='$bno[$z]'");
		$title = $data[code_name];
?>

<Br>
<table width="650" border="1" cellpadding="0" cellspacing="0" align="center" style="border:1 solid gray;">
  <tr height="30" bgcolor="#DFDFDF">
	  <td style="padding-left:10px;border:1 solid gray;"><B><?=$title?></B></td>
  </tr>
  <tr height="30" bgcolor="#DFDFDF">
	  <td style="padding-left:10px;border:1 solid gray;">
제 목 : <B><?=$data[subject]?></B>
	</td>
  </tr>
  <tr height="100">
	<td style="padding:5 5 5 5;" style="border:1 solid gray;" valign="top">
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
		}
	}
?>
		<?=$data[content]?>
	</td>
</tr>
</table>

<?
	}
}
?>
<br>
<table width="650" border="0" cellpadding="0" cellspacing="0" align="center" id="buttonall" style="display:block;">
<tr height="30">
	<td align="right">
<a href="javascript:printall();">[ 인 쇄 ]</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="javascript:self.close();">[ 창닫기 ]</a>
	</td>
</tr>
</table>