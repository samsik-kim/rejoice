<?
	require("../dbconnect.php");
	require("../lib.php");

	$u_admin = "";

	session_register("u_admin");
	session_destroy();

foot();

?>
<SCRIPT LANGUAGE="JavaScript">
<!--
location.href="/";
//-->
</SCRIPT>