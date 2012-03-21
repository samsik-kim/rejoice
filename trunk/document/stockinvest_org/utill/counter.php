<?
// 카운터 //
$sql="select * from counter where seq_no=1";
$data=mysql_fetch_array(mysql_query($sql));

$times=time();
$today=date("Ymd",$times);
$dbtoday=date("Ymd",$data[today]);
$alltcounter=$data[aclick]+1;

if ($today==$dbtoday){

	$tcounter=$data[click]+1;
	mysql_query("update counter set aclick='$alltcounter', click='$tcounter' where seq_no=1");

}else{

	mysql_query("update counter set aclick='$alltcounter', click='1', today='$times'  where seq_no=1");
}
?>

<?
/*
출력부분

	if (!$table_handle){
		include "../dbconn/dbconn.php";
	}
		$sql="select * from counter where seq_no=1";
		$data=mysql_fetch_array(mysql_query($sql));

number_format($data[click])		//오늘카운터
number_format($data[aclick])	//전체카운터
*/
?>