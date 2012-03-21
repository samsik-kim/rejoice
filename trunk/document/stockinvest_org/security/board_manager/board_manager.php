<?
	include "../setup.php";

	$dbname = "board_manager";

if ($mode=="add"){
	$max_sql = mysql_query("select max(bbs_cd) from $dbname");
	$max = @mysql_result($max_sql, 0);

	if ($max >= 1){
		$max = $max + 1;
	}else{
		$max = $max;
	}

	insertsql($dbname, "set board_name='$board_name', bbs_cd='$max'");

	$bmno = "";
	$mode = "";

?>
<SCRIPT LANGUAGE="JavaScript">
<!--
	top.amenu.location.href="/security/left.html";	
//-->
</SCRIPT>
<?

}elseif ($mode=="mody" && $bmno){

	updatesql($dbname, "set board_name='$board_name'","where seq_no='$bmno'");

	$bmno = "";
	$mode = "";

?>
<SCRIPT LANGUAGE="JavaScript">
<!--
	top.amenu.location.href="/security/left.html";	
//-->
</SCRIPT>
<?

}elseif ($mode=="del"){

	deletesql($dbname, "where seq_no='$bmno'");

	$bmno = "";
	$mode = "";

?>
<SCRIPT LANGUAGE="JavaScript">
<!--
	top.amenu.location.href="/security/left.html";	
//-->
</SCRIPT>
<?

}

	$sql = "select * from $dbname order by seq_no asc";
	$result = mysql_query($sql);

?>
<SCRIPT LANGUAGE="JavaScript">
<!--
function inquery(){
	board_manager.submit();
}
//-->
</SCRIPT>
<link rel="stylesheet" href="/utill/css/admin.css" type="text/css">
<br><br>
<table width="600" cellpadding="0" cellspacing="0" border="0" align="center">
  <tr height="30">
    <td><font color="#990000">[게시판 추가/삭제]</font></td>
  </tr>
  <tr>
    <td>
	  <table width="600" cellpadding="0" cellspacing="1" border="0" bgcolor="#330066">
	    <tr bgcolor="white">
		  <td class="subject">게시판 명</td>
		  <td class="subject" width="150">선 택</td>
		</tr>
<?while ($data=mysql_fetch_array($result)){?>
		<tr bgcolor="white" height="30">
			<td align="center"><?=$data[board_name]?></td>
			<td align="center">
<a href="<?=$PHP_SELF?>?bmno=<?=$data[seq_no]?>">[ 수 정 ]</a>
 / 
<a href="<?=$PHP_SELF?>?mode=del&bmno=<?=$data[seq_no]?>">[ 삭 제 ]</a>
			</td>
		</tr>
<?}?>
<?
if ($bmno){
	$data = fetch_array($dbname, "where seq_no='$bmno'");

	$btn_name = "수 정";
	$mode = "mody";

}else{

	$btn_name = "추 가";
	$mode = "add";

}
?>
  <form name="board_manager" method="post" action="<?=$PHP_SELF?>">
  <input type="hidden" name="mode" value="<?=$mode?>">
  <input type="hidden" name="bmno" value="<?=$bmno?>">
		<tr bgcolor="white">
			<td align="center"> 
			  <input type="text" name="board_name" value="<?=$data[board_name]?>"></td>
			<td align="center"> 
			  <input type="button" value=" <?=$btn_name?> " onclick="javascript:inquery();"></td>
		</tr>
</form>
	  </table>
	  </td></tr>
	  </form>
  </td></tr>
</table>