<?
	$dir="/home/stock/public_html/";  //������丮

	include $dir."dbconnect.php";
	include $dir."lib.php";

	if (!$u_admin){
		alertNgo("������ �α����� �� �ֽʽÿ�.", "location", "/","top.");
		exit;
	}
?>