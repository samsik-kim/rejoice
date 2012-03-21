<?
	$dir="/home/stock/public_html/";  //절대디렉토리

	include $dir."dbconnect.php";
	include $dir."lib.php";

	if (!$u_admin){
		alertNgo("관리자 로그인을 해 주십시요.", "location", "/","top.");
		exit;
	}
?>