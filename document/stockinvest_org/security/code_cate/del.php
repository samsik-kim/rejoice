<?
	include "../setup.php";

	$dbname = "code_cate";

	$where = "where seq_no='$cno'";

	deletesql($dbname, $where);

	$para="page=$page";

	alertNgo("�����Ǿ����ϴ�.", "location", "/security/code_cate/list.php?".$para);

	foot();
?>