<?
	require("../dbconnect.php");
	require("../lib.php");
	
	$dbname = "admin";
	$where = "where admin_id='$log_id'";
	$idchk_yn = num_rows($dbname, $where);

	if ($idchk_yn < 1) {
		alertNgo("���� ������ �����ϴ�.", "back", "1");
		exit;
	}else{
		$admin = fetch_array($dbname, $where);

			if ($admin[passwd] != $log_pw) {
				alertNgo("���� ������ �����ϴ�.", "back", "1");
				exit;
			}else{

				//ini_set("session.cache_expire", 720);    // ���� ��ȿ�Ⱓ : �� (1��) 
				//ini_set("session.gc_maxlifetime", 86400);    // ���� ������ �÷��� : ��(1��) 
				
				session_start();
				
				$u_admin="true";
			    session_register("u_admin");
			}
	}
foot();

	if (!$ref) $ref="/security/";

?>
<SCRIPT LANGUAGE="JavaScript">
<!--
	location.href = "<?=$ref?>";
//-->
</SCRIPT>