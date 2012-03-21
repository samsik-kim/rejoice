<?
// ���ڿ� ���� (�̻��� �����϶��� ... �� ǥ��)
// ��� �� : $memo=cut_str($memo,30); 30���ڱ����� ���
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

// HTML Tag�� �����ϴ� �Լ�
//��� �� : $memo=del_html($memo);
function del_html( $str ) {
	$str = str_replace( ">", "&gt;",$str );
	$str = str_replace( "<", "&lt;",$str );
	return $str;
}

// ������ ������ ������ �о��
//��� �� : $memo=rfile( ���� ); 
function rfile($filename) {
	if(!file_exists($filename)) return '';
	$f = fopen($filename,"r");
	$str = fread($f, filesize($filename));
	fclose($f);
	return $str;
}

// ������ �����ϴ� �Լ�
// ��� �� : $imsi_file=delfile("/[__�����ּ�__]/[__���ϸ�__]");
function delfile($filename) {
	@chmod($filename,0777);
	$handle = @unlink($filename);
	if(@file_exists($filename)) {
		@chmod($filename,0775);
		$handle=@unlink($filename);
	}
	return $handle;
}

//������� ����
//�� �Ʒ��ʿ� foot(); �̰Ÿ� ���ָ� ���.
function foot(){
	global $connect;
	if ($connect){
		mysql_close();
		unset($connect);
	}
}

//�������� ���̱�.
//��� �� : $memo=slash($memo);
function slash($memo){
	$memo=addslashes($memo);

	return $memo;
}

//�̹��� ����ũ�⺸�� ũ�� ���� ����.
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
//�̹��� ������ ���
function radioimg($filename,$x_size,$y_size){
$size=@getimagesize($filename);
$x1=$size[0];
$y1=$size[1];
if ($x1>$y1) { // x����
	if ($x1<=$x_size){
		$x=$x1;
		$y=$y1;
	} else {
		$radio=$y1/$x1;
		$y=$radio*$y_size;
		$x=$x_size;
	}
} else { // y ����
	if ($y1<=$y_size){ //����ũ�⺸�� ������
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

//�ڵ���ũ �Լ�
//��� �� : $memo = autolink($memo);
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

//�μ�Ʈ�� ������Ʈ�� ���Ǵ� set �������� �����..
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

//insert into ����
function insertsql($dbname, $set){
	$query = "insert into $dbname $set";
//echo $query;
	mysql_query($query);
}

//update ����
function updatesql($dbname, $set, $where){
	$query = "update $dbname $set $where";
	mysql_query($query);
}

//delete ����
function deletesql($dbname, $where){
	$query = "delete from $dbname $where";
	mysql_query($query);
}

//mysql_num_rows ����
function num_rows($dbname, $where){
	$query = "select * from $dbname $where";
	$run = mysql_query($query);
	$result = mysql_num_rows($run);

	return $result;
}

//mysql_fetch_array ���� (1���� �������ö���.)
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

//���� ���ε�
function File_Upload($tmp, $name, $size, $type, $devider, $folder="updata/"){
	
	$imsi_file=explode(".",$name);
	$file_name="f".time().$devider.".".$imsi_file[1];

	// ���� ������ �ִ°��
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

//���� ����
function File_Delete($file, $folder){

	if (file_exists($folder.$file)){
		delfile($folder.$file);
	}

}

//����¡�� �ʿ��� ���������� ����Ʈ �Ѹ���.
function Paging_Page($dbname, $addw, $addt, $page, $nperpage){

	//������ ����
	for ($i=0;$i<sizeof($addw);$i++){
		if ($i==0) $addo="where ".$addw[$i];
		else $addo.=" and ".$addw[$i];
	}

	//������ ����
	for ($i=0;$i<sizeof($addt);$i++){
		if ($i==0) $addq="order by ".$addt[$i];
		else $addq.=", ".$addt[$i];
	}

//��ü ���ڵ���� ���Ѵ�.
	$achnum = num_rows($dbname, $addo);

	if ($achnum==0){ //limit�� ���۰� ���� ���Ѵ�.
		$start=1;
		$end=0;
	}else{
		$start=$nperpage*($page-1);
		$end=$nperpage*($page);
	}

	$achno=$achnum - $nperpage*($page-1);  //�Խù��� �Ϸù�ȣ

	$totalpage=ceil($achnum/$nperpage); //��ü������;
	$totalblock=ceil($totalpage/10); //��ü��
	$nowb = ceil($page/10); //�����

	$nows=1+(($nowb-1)*10); //������� ����������
	$nowe=($nowb*10); //������� ��������

	if ($nowe>$totalpage) $nowe=$totalpage; //������ ������ �ʿ���� ������ ����

//����Ÿ�� �����´�.
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

///////////////////////////////////////��Ʒ��� ��������ȣ ����Ʈ////////////////////////////

//ó�� ��������..
	if ($nowb>1 && $use_first=="on"){
		$paging.= "<a href='".$PHP_SELF."?".$l_parameter."&page=1'>";
		$paging.= $pageimg1;
	}
//ó�� ��������..

//���� ������..
	if ($nowb>1){
		$nows2 = $nows - 10;
		$paging.= "<a href='".$PHP_SELF."?".$l_parameter."&page=".$nows2."'>";
		$paging.= $pageimg2."</a> &lt;";
	}
//���� ������..

//����������Ʈ..
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
//����������Ʈ..

//����������...
	if ($nowb<$totalblock){
		$nows3 = $nows + 10;
		$paging.="&gt; <a href='".$PHP_SELF."?".$l_parameter."&page=".$nows3."'>";
		$paging.=$pageimg3."</a>";
	}
//����������...

//������ ��������..
	if ($nowb<$totalblock && $use_last=="on"){
		$paging.="&gt; <a href='".$PHP_SELF."?".$l_parameter."&page=".$totalpage."'>";
		$paging.=$pageimg4."</a>";
	}
//������ ��������..

	$result = $paging;
///////////////////////////////////////����� ��������ȣ ����Ʈ////////////////////////////
	
	return $result;

}

//ȸ�����
function ulevelfun($no){
	switch ($no) {
		case 1 : 
			$levelpr="�Ϲ�ȸ��";
		break;
/*
		case 3 :
			$levelpr="����ȸ��";
		break;
		case 5 :
			$levelpr="������";
		break;
		case 7 : 
			$levelpr="�п���";
		break;
*/
		case 9 : 
			$levelpr="������";
		break;
		default :
			$levelpr="�Ϲ�ȸ��";
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
// style="word-break:break-all"  ������ td ���̸� �Ѿ�� td�� �� �� �־��ָ� �ȴ�.
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


//�迭����-------------------------------------------------------------!!
$order_step = array('','�������','�����Ϸ�','�����','��ۿϷ�','������');

$email_array = array('','hanmail.net','empal.com','dreamwiz.com','freechal.com','hanmir.com','hotmail.com','korea.com','lycos.co.kr','naver.com','netian.com','yahoo.co.kr','shinbiro.com','paran.com');

$tell_array = array('','02','031','032','033','041','042','043','051','052','053','054','055','061','062','063','064','080');

$hp_array = array('','010','011','016','017','018','019');

?>