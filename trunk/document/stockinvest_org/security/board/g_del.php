<?
	include "../setup.php";

$para = "page=$page";

$data = fetch_array("gallery", "where seq_no='$gno'");

		if ($gno){
			File_Delete($data[file1], $dir."updata/");
		}

	deletesql("gallery", "where seq_no='$gno'");

	alertNgo("�����Ǿ����ϴ�.", "location", "/security/board/glist.php?".$para);

foot();
?>