<?
	require("../setup.php");

	$data = fetch_array("gallery", "where seq_no='$gno'");
	$rimsi=explode(" ",$data[crt_date]);
	$date=$rimsi[0];

	if ($data[file1]!=""){
		$size = ImgSize($dir."updata/".$data[file1], 400);
		$w = $size[0];
		$h = $size[1];
	}
//URL과 함께 넘어갈 기본 파라메타
$l_parameter="page=$page";
?>
<link rel="stylesheet" href="/utill/css/admin.css" type="text/css">
<br>
<table border="0" width="600" cellpadding="0" cellspacing="0" align="center">
  <tr height="30">
    <td><font color="#8B0901"><B>[갤러리관리]</font></td>
  </tr>
<tr>
  <td>
<table width="600" border="0" align="center" cellpadding="0" cellspacing="0">
<!-- 	<tr>
		<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="8%"><div align="center">제 목</div></td>
				<td width="92%"style="padding-top:2px;"><div align="center"></div>
					<div align="center"></div>
					<strong><?=$data[subject]?></strong></td>
			</tr>
				</table></td>
	</tr>
	<tr>
		<td height="5"></td>
	</tr>
	<tr>
		<td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="8%"><div align="center">작성자</div></td>
				<td width="20%" height="25" valign="middle"><?=$data[name]?>
				</td>
				<td width="9%"><div align="center">작성일</div></td>
				<td width="21%"><?=$date?>
				</td>
				<td width="9%"><div align="center">파일명</div></td>
				<td width="33%"><?=$data[file1]?>
				</td>
			</tr>
			</table></td>
	</tr> -->
	<tr>
		<td><table width="602" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td width="30" rowspan="6">&nbsp;</td>
				<td></td>
				<td width="30" rowspan="6">&nbsp;</td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
<?//		if ($imsifile[1]=="gif" || $imsifile[1]=="jpg"){ ?>
				<td height="150"><img src="/updata/<?=$data[file1]?>" width="<?=$w?>">
<!-- <?//}else{?>
				<td height="150" align="right">Download File : <a href="/upimg/<?=$data[file1]?>" target="_blank"><?=$data[file1]?></a></td>
<?//}?> -->
				</td>
			</tr>
			<tr>
				<td height="4">&nbsp;</td>
			</tr>
<!-- 			<tr>
				<td height="5"><?=nl2br($data[memo])?></td>
			</tr> -->
			<tr>
				<td height="9">&nbsp;</td>
			</tr>
				</table></td>
	</tr>
	<tr>
		<td height="1" bgcolor="#EAEAEA"></td>
	</tr>
	<tr>
		<td height="3" bgcolor="#F7F7F7"></td>
	</tr>
	<tr>
		<td height="9"></td>
	</tr>
	<tr>
		<td height="30" align="right">
		<a href="/security/board/gwrite.php?<?=$l_parameter?>&gno=<?=$gno?>">[수 정]</a>
		<a href="/security/board/g_del.php?<?=$l_parameter?>&gno=<?=$gno?>">[삭 제]</a>
		<a href="/security/board/glist.php?<?=$l_parameter?>">[목 록]</a>
		</td>
	</tr>
	<tr>
		<td height="9"></td>
	</tr>
</table>
</td></tr>
</table>