<?

$bd_data = fetch_array("board_manager", "where bbs_cd='$bbs_cd'");
	$title = $bd_data[board_name];

if ($keyg == "code_name"){
	$sch_key = substr($key,0,4);
	$sch_data = fetch_array("board", "where $keyg like '%$key%'");
}elseif ($keyg == "code_num"){
	$sch_data = fetch_array("board", "where $keyg='$key'");
}
	$title = $title."<font color='red'> < ".$sch_data[code_name]." > </font>";
?>