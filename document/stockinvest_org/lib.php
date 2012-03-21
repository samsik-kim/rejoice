<?
// 문자열 끊기 (이상의 길이일때는 ... 로 표시)
// 상용 예 : $memo=cut_str($memo,30); 30글자까지만 출력
function cut_str($msg,$cut_size) {
	if($cut_size<=0) return $msg;
	if(ereg("\[RE\]",$msg)) $cut_size=$cut_size+4;
	for($i=0;$i<$cut_size;$i++) if(ord($msg[$i])>127) $han++; else $eng++;
	$cut_size=$cut_size+(int)$han*0.6;
	$point=1;
	for ($i=0;$i<strlen($msg);$i++) {
		if ($point>$cut_size) return $pointtmp."...";
		if (ord($msg[$i])<=127) {
			$pointtmp.= $msg[$i];
			if ($point%$cut_size==0) return $pointtmp."..."; 
		} else {
			if ($point%$cut_size==0) return $pointtmp."...";
			$pointtmp.=$msg[$i].$msg[++$i];
			$point++;
		}
		$point++;
	}
	return $pointtmp;
}

// HTML Tag를 제거하는 함수
//사용 예 : $memo=del_html($memo);
function del_html( $str ) {
	$str = str_replace( ">", "&gt;",$str );
	$str = str_replace( "<", "&lt;",$str );
	return $str;
}

// 지정된 파일의 내용을 읽어옴
//사용 예 : $memo=rfile( 파일 ); 
function rfile($filename) {
	if(!file_exists($filename)) return '';
	$f = fopen($filename,"r");
	$str = fread($f, filesize($filename));
	fclose($f);
	return $str;
}

// 파일을 삭제하는 함수
// 사용 예 : $imsi_file=delfile("/[__절대주소__]/[__파일명__]");
function delfile($filename) {
	@chmod($filename,0777);
	$handle = @unlink($filename);
	if(@file_exists($filename)) {
		@chmod($filename,0775);
		$handle=@unlink($filename);
	}
	return $handle;
}

//디비접속 해제
//맨 아래쪽에 foot(); 이거만 써주면 댄다.
function foot(){
	global $connect;
	if ($connect){
		mysql_close();
		unset($connect);
	}
}

//역슬레쉬 붙이기.
//사용 예 : $memo=slash($memo);
function slash($memo){
	$memo=addslashes($memo);

	return $memo;
}

//이미지 지정크기보다 크면 강제 줄임.
function show_pic($filename,$widthx=80,$widthy=80,$directory="data/",$domain=""){
	global $dir;
	$files=$directory.$filename;
	$filename2=$dir.$files;
	if (file_exists($filename2)&&$filename) {

	$sizes=radioimg($filename2,$widthx,$widthy);
	$ret="<img src='$domain/$files' width='$sizes[0]' height='$sizes[1]' galleryimg=no align=absmiddle>";
	} else {
	$files=$directory."noimg.gif";
	$filename2=$dir.$directory."noimg.gif";
	$sizes=radioimg($filename2,$widthx,$widthy);
	$ret="<img src='$domain/$files' width='$sizes[0]' height='$sizes[1]' galleryimg=no align=absmiddle>";
	}
	return $ret;	
}
//이미지 비율로 출력
function radioimg($filename,$x_size,$y_size){
$size=@getimagesize($filename);
$x1=$size[0];
$y1=$size[1];
if ($x1>$y1) { // x기준
	if ($x1<=$x_size){
		$x=$x1;
		$y=$y1;
	} else {
		$radio=$y1/$x1;
		$y=$radio*$y_size;
		$x=$x_size;
	}
} else { // y 기준
	if ($y1<=$y_size){ //월래크기보다 작으면
		$x=$x1;
		$y=$y1;	
	} else {
		$radio=$x1/$y1;
		$x=$radio*$x_size;
		$y=$y_size;
	}
}

$size[0]=$x;
$size[1]=$y;
return $size;
}

//자동링크 함수
//사용 예 : $memo = autolink($memo);
function autolink($contents) {
       $pattern = "/(http|https|ftp|mms):\/\/[0-9a-z-]+(\.[_0-9a-z-]+)+(:[0-9]{2,4})?\/?";
	   // domain+port
       $pattern .= "([\.~_0-9a-z-]+\/?)*";
		// sub roots
       $pattern .= "(\S+\.[_0-9a-z]+)?";
	   // file & extension string
       $pattern .= "(\?[_0-9a-z#%&=\-\+]+)*/i"; 
	   // parameters
       $replacement = "<a href=\"\\0\" target=\"_blank\">\\0</a>";
       return preg_replace($pattern, $replacement, $contents, -1);
}

//인서트나 업데이트에 사용되는 set 형식으로 만들기..
function autoset($fields, $keys){
	$set = "set ";
	for ($i = 1; $i < sizeof($fields); $i++){
		if ($i == 1){
			if ($keys[$i]=="now()"){
				$set.= $fields[$i]."='".$keys[$i];
			}else{
				$set.= $fields[$i]."='".$keys[$i]."'";
			}
		}else{
			if ($keys[$i]=="now()"){
				$set.= ", ".$fields[$i]."=".$keys[$i];
			}else{
				$set.= ", ".$fields[$i]."='".$keys[$i]."'";
			}
		}
	}

	return $set;
}

//insert into 실행
function insertsql($dbname, $set){
	$query = "insert into $dbname $set";
//echo $query;
	mysql_query($query);
}

//update 실행
function updatesql($dbname, $set, $where){
	$query = "update $dbname $set $where";
	mysql_query($query);
}

//delete 실행
function deletesql($dbname, $where){
	$query = "delete from $dbname $where";
	mysql_query($query);
}

//mysql_num_rows 실행
function num_rows($dbname, $where){
	$query = "select * from $dbname $where";
	$run = mysql_query($query);
	$result = mysql_num_rows($run);

	return $result;
}

//mysql_fetch_array 실행 (1개의 정보빼올때만.)
function fetch_array($dbname, $where){
	$query = "select * from $dbname $where";
	$run = mysql_query($query);
	$result = mysql_fetch_array($run);

	return $result;
}

function alertNgo($msg,$gotype,$go,$target=""){
		echo "<script>";
		echo "alert('$msg');";

	if ($gotype == "location"){
		echo $target."location.href='$go';";
	}elseif ($gotype == "back"){
		echo "history.go(-$go);";
	}

		echo "</script>";
}

//파일 업로드
function File_Upload($tmp, $name, $size, $type, $devider, $folder="updata/"){
	
	$imsi_file=explode(".",$name);
	$file_name="f".time().$devider.".".$imsi_file[1];

	// 같은 파일이 있는경우
		$z=0;
	while (file_exists($folder.$file_name)){
		$z++;

		$tmp_file=explode(".",$file_name);
		$file_name=$tmp_file[0]."_".$z.".".$tmp_file[1];
	}


	if(!move_uploaded_file($tmp, $folder.$file_name)) {
		$result = "failed";
	}else{
		$result = $file_name;
	}

	return $result;
}

//파일 삭제
function File_Delete($file, $folder){

	if (file_exists($folder.$file)){
		delfile($folder.$file);
	}

}

//페이징이 필요한 페이지에서 리스트 뿌리기.
function Paging_Page($dbname, $addw, $addt, $page, $nperpage){

	//쿼리로 생성
	for ($i=0;$i<sizeof($addw);$i++){
		if ($i==0) $addo="where ".$addw[$i];
		else $addo.=" and ".$addw[$i];
	}

	//쿼리로 생성
	for ($i=0;$i<sizeof($addt);$i++){
		if ($i==0) $addq="order by ".$addt[$i];
		else $addq.=", ".$addt[$i];
	}

//전체 레코드수를 구한다.
	$achnum = num_rows($dbname, $addo);

	if ($achnum==0){ //limit의 시작과 끝을 구한다.
		$start=1;
		$end=0;
	}else{
		$start=$nperpage*($page-1);
		$end=$nperpage*($page);
	}

	$achno=$achnum - $nperpage*($page-1);  //게시물의 일련번호

	$totalpage=ceil($achnum/$nperpage); //전체페이지;
	$totalblock=ceil($totalpage/10); //전체블럭
	$nowb = ceil($page/10); //현재블럭

	$nows=1+(($nowb-1)*10); //현재블럭의 시작페이지
	$nowe=($nowb*10); //현재블럭의 끝페이지

	if ($nowe>$totalpage) $nowe=$totalpage; //마지막 블럭에서 필요없는 페이지 제거

//데이타를 가져온다.
	$query="select * from $dbname $addo $addq LIMIT $start, $nperpage";
	$run=mysql_query($query);
	
	$result[0] = $run;
	$result[1] = $totalblock;
	$result[2] = $nowb;
	$result[3] = $nows;
	$result[4] = $nowe;
	$result[5] = $achno;

	return $result;
}

function Paging_num($l_parameter, $totalblock, $nowb, $nows, $nowe, $page, $pageimg1, $pageimg2, $pageimg3, $pageimg4, $use_first="off", $use_last="off"){

///////////////////////////////////////요아래는 페이지번호 리스트////////////////////////////

//처음 페이지로..
	if ($nowb>1 && $use_first=="on"){
		$paging.= "<a href='".$PHP_SELF."?".$l_parameter."&page=1'>";
		$paging.= $pageimg1;
	}
//처음 페이지로..

//이전 블럭으로..
	if ($nowb>1){
		$nows2 = $nows - 10;
		$paging.= "<a href='".$PHP_SELF."?".$l_parameter."&page=".$nows2."'>";
		$paging.= $pageimg2."</a> &lt;";
	}
//이전 블럭으로..

//페이지리스트..
	for ($p=$nows;$p<=$nowe;$p++){

		if ($p != $nows){
			$paging.= "&nbsp; | &nbsp;";
		}	

		$paging.="<a href='".$PHP_SELF."?".$l_parameter."&page=".$p."'>";

		if ($p==$page){
			$paging.="<font color='#0099CC'>";
		}
			$paging.= "[".$p."]</font></a>";
	}
//페이지리스트..

//다음블럭으로...
	if ($nowb<$totalblock){
		$nows3 = $nows + 10;
		$paging.="&gt; <a href='".$PHP_SELF."?".$l_parameter."&page=".$nows3."'>";
		$paging.=$pageimg3."</a>";
	}
//다음블럭으로...

//마지막 페이지로..
	if ($nowb<$totalblock && $use_last=="on"){
		$paging.="&gt; <a href='".$PHP_SELF."?".$l_parameter."&page=".$totalpage."'>";
		$paging.=$pageimg4."</a>";
	}
//마지막 페이지로..

	$result = $paging;
///////////////////////////////////////요까지 페이지번호 리스트////////////////////////////
	
	return $result;

}

//회원등급
function ulevelfun($no){
	switch ($no) {
		case 1 : 
			$levelpr="일반회원";
		break;
/*
		case 3 :
			$levelpr="유료회원";
		break;
		case 5 :
			$levelpr="지사장";
		break;
		case 7 : 
			$levelpr="학원장";
		break;
*/
		case 9 : 
			$levelpr="관리자";
		break;
		default :
			$levelpr="일반회원";
	}
		return $levelpr;
}

function MkSelect($array,$sel){
	$result="";
	for ($i=1;$i<sizeof($array);$i++){
		if ($array[$i] == $sel){
			$result.="<option value='".$array[$i]."' selected>".$array[$i]."</option>";
		}else{
			$result.="<option value='".$array[$i]."'>".$array[$i]."</option>";
		}
	}
	
	return $result;
}

function MkSelect2($array,$sel){
	$result="";
	for ($i=1;$i<sizeof($array);$i++){
		if ($i == $sel){
			$result.="<option value='".$i."' selected>".$array[$i]."</option>";
		}else{
			$result.="<option value='".$i."'>".$array[$i]."</option>";
		}
	}
	
	return $result;
}

function cut_br($memo, $cut){
// style="word-break:break-all"  한줄이 td 넓이를 넘어갈때 td에 저 걸 넣어주면 된다.
	$rmemo = explode("<BR>",$memo);
	$result = "";
	
	for ($i=0;$i<sizeof($rmemo);$i++){
		if ($i < $cut){
			$result.= $rmemo[$i]."<BR>";
		}
	}

	return $result;
}

function ImgSize($upfile, $width=0, $height=0){
	$size = getimagesize($upfile);
	$w = $size[0];
	$h = $size[1];

	if ($w > $width && $width != 0){
		$w = $width;
	}

	if ($h > $height && $height != 0){
		$h = $height;
	}

	$result = array($w,$h);

	return $result;
}


//배열정보-------------------------------------------------------------!!
$order_step = array('','결제대기','결제완료','배송중','배송완료','휴지통');

$email_array = array('','hanmail.net','empal.com','dreamwiz.com','freechal.com','hanmir.com','hotmail.com','korea.com','lycos.co.kr','naver.com','netian.com','yahoo.co.kr','shinbiro.com','paran.com');

$tell_array = array('','02','031','032','033','041','042','043','051','052','053','054','055','061','062','063','064','080');

$hp_array = array('','010','011','016','017','018','019');

?>