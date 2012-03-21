<?
	include "../setup.php";

	$dbname = "code_cate";

	$where = "where seq_no='$cno'";

	deletesql($dbname, $where);

	$para="page=$page";

	alertNgo("삭제되었습니다.", "location", "/security/code_cate/list.php?".$para);

	foot();
?>