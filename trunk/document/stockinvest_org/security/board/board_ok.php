<?
	require("../setup.php");

$para="page=$page&bbs_cd=$bbs_cd&keyg=code_num&key=$code_num";

$dbname = "board";

$data = fetch_array("board", "where seq_no='$bno'");

for ($i=1;$i<=1;$i++){
	$files="file".$i;
// 파일 등록 1	

	$ifile1 = $HTTP_POST_FILES[$files][tmp_name];
	$ifile1_name = $HTTP_POST_FILES[$files][name];
	$ifile1_size = $HTTP_POST_FILES[$files][size];
	$ifile1_type = $HTTP_POST_FILES[$files][type];

	if($ifile1_size>0) {
		if ($gno){
			File_Delete($data[$files], $dir."updata/");
		}
		${"rfile".$i} = File_Upload($ifile1, $ifile1_name, $ifile1_size, $ifile_type, $i, $dir."updata/");
	}else{
		${"rfile".$i} = $data[$files];
	}
}


if (!$bno && !$rbno){
	$max_sql="select max(list_num) from board where bbs_cd='$bbs_cd'";
	$max_result=mysql_query($max_sql);
	$max=@mysql_result($max_result,0);

	if ($max<1) $max=1;
	else $max=$max+1;

	$fields = array('', 'list_num', 'code_num', 'code_name', 'name', 'email', 'subject', 'content', 'file1', 'passwd', 'crt_date', 'udt_date', 'bbs_cd');

	$keys = array('', $max, $code_num, $code_name, $name, $email, $subject, $content, $rfile1, $passwd, 'now()', 'now()',$bbs_cd);

	$set = autoset($fields, $keys);

	insertsql($dbname, $set);

	alertNgo("등록이 완료되었습니다.", "location", "list.php?".$para);

}elseif ($bno){

	if ($passwd!=$data[passwd] && !$u_admin){

		alertNgo("비밀번호가 일치하지 않습니다.", "back", "1");
		exit;

	}

	$set = "set code_num='$code_num', code_name='$code_name', passwd='$passwd', email='$email', subject='$subject', content='$content',file1='$rfile1', udt_date=now()";

	$where = "where seq_no='$bno'";

	updatesql($dbname, $set, $where);

	alertNgo("수정이 완료되었습니다.", "location", "list.php?".$para);

}elseif ($rbno && $reply=="Y"){

	$data = fetch_array($dbname, "where seq_no='$rbno'");

	$max = $data[list_num];
	$renum = $data[ref]+1;
	$relevel = $data[re_level]+1;

	if ($relevel == 0){
		$parno = $data[seq_no];
	}else{
		$parno = $data[par_no];
	}

	updatesql($dbname, "set ref=ref+1", "where list_num='$max' and ref>='$renum'");

	$fields = array('', 'list_num', 'ref', 're_level', 'par_no', 'code_num', 'name', 'email', 'subject', 'content', 'file1', 'passwd', 'crt_date', 'udt_date', 'bbs_cd');

	$keys = array('', $max, $renum, $relevel, $parno, $code_num, $name, $email, $subject, $content, $rfile1, $passwd, 'now()', 'now()', $bbs_cd);
	
	$set = autoset($fields, $keys);

	insertsql($dbname, $set);

	alertNgo("답글이 등록되었습니다.", "location", "list.php?".$para);

}	
foot();
?>