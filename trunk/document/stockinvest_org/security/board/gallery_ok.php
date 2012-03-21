<?
	include "../setup.php";

$para = "page=$page";
$dbname = "gallery";

$data = fetch_array($dbname, "where seq_no='$gno'");

// 파일 등록 1	
	$file1 = $HTTP_POST_FILES[img1][tmp_name];
	$file1_name = $HTTP_POST_FILES[img1][name];
	$file1_size = $HTTP_POST_FILES[img1][size];
	$file1_type = $HTTP_POST_FILES[img1][type];

	if($file1_size>0) {

		if ($bno){
			File_Delete($data[file1], $dir."updata/");
		}

		$s_file_name1 = File_Upload($file1, $file1_name, $file1_size, $file1_type, 1, $dir."updata/");
	}else{
		$s_file_name1 = $data[file1];
	}

if ($gno){
	$msg="파일이 수정되었습니다.";
	
	$fields = array('', 'gcate', 'subject', 'name', 'file1');
	$keys = array('', $gcate, $subject, $name, $s_file_name1);
	$set = autoset($fields, $keys);
	$where = "where seq_no='$gno'";
	
	updatesql($dbname, $set, $where);

}else{
	$msg="파일이 등록되었습니다.";

	$fields = array('', 'gcate', 'subject', 'name', 'file1', 'crt_date');
	$keys = array('', $gcate, $subject, $name, $s_file_name1, 'now()');
	$set = autoset($fields, $keys);
	
	insertsql($dbname, $set);
}

	alertNgo($msg, "location", "/security/board/glist.php?".$para);

foot();
?>