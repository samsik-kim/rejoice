<?
	include "../setup.php";

	$para = "page=$page&bbs_cd=$bbs_cd&keyg=$keyg&key=$key";

	if ($ref == "board"){
		$loc = "/security/board/list.php";
	}else{
		$loc = "list.php";
	}
	
	$dbname = "code_cate";

	$times = time();

	$fields = array('', 'code_name', 'code_num', 'juju', 'tel', 'tel1', 'tel2', 'tel3', 'info_tel', 'info_tel1', 'info_tel2', 'info_tel3', 'boyujibun', 'crt_date');

	$keys = array('', $code_name, $code_num, $juju, $tel, $tel1, $tel2, $tel3, $info_tel, $info_tel1, $info_tel2, $info_tel3, $boyujibun, $times);

	$set = autoset($fields, $keys);

	$where = "where seq_no='$cno'";

	if ($cno){

		updatesql($dbname, $set, $where);

		alertNgo("수정이 완료되었습니다.", "location", $loc."?".$para);

	}else{
	
		insertsql($dbname, $set);

		alertNgo("등록이 완료되었습니다.", "location", $loc."?".$para);

	}

foot();
?>