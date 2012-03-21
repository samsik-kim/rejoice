<?
	include "../setup.php";

	$para="page=$page&bbs_cd=$bbs_cd&keyg=code_num&key=$key";

	$sql="delete from board where seq_no='$bno'";
	mysql_query($sql);

?>
<SCRIPT LANGUAGE="JavaScript">
<!--
alert("삭제 되었습니다.");
location.href="/security/board/list.php?<?=$para?>";
//-->
</SCRIPT>