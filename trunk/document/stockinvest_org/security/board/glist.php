<?
	include "../setup.php";

	$dbname="gallery";//게시판 테이블명.

	if (!$page) $page=1; //페이지 초기값

	$nperpage=18;//페이지당 게시물수

	$addn[]="seq_no desc";

$result = array(); //결과를 배열로 받기 위해 배열변수로 정의한다.

$result = Paging_Page($dbname, $addw, $addt, $page, $nperpage);

$totalblock = $result[1]; //전체블럭수
$nowb = $result[2];//현재블럭번호
$nows = $result[3];//현재블럭의 시작페이지번호
$nowe = $result[4];//현재블럭의 마지막페이지번호
$achno = $result[5];//일련번호
$allnum = $result[6];//일련번호
$result = $result[0];//mysql_query값

//URL과 함께 넘어갈 기본 파라메타
$para="page=$page";

//여기까지 include
?>
<link rel="stylesheet" type="text/css" href="/utill/css/admin.css">
<br><br>
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr height="30">
    <td><font color="#8B0901"><B>[갤러리관리]</font></td>
  </tr>
<tr>
<td height="20"></td>
</tr>
<tr>
<td><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
<tr>
<?
	$i=0;
while ($data=mysql_fetch_array($result)){
	$i++;
?>
	<td class="object" onclick="location.href='/security/board/gview.php?<?=$l_parameter?>&gno=<?=$data[seq_no]?>';" style="cursor:hand;" align="center"><img src="/updata/<?=$data[file1]?>" width="100" height="100"><br><br><?=$gallery_array[$data[gcate]]?></td>
<?
	if (($i%6) == 0){
?>
</tr>
<tr>
<?
	}
$achno--;
}
?>

</tr>
</table>
<tr>
<td height="30" align="center">
<?
//리스트 페이징~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

	//처음, 이전, 다음, 마지막, 이미지
	$pageimg1 = "◀ ";
	$pageimg2 = "[이전]";
	$pageimg3 = "[다음]";
	$pageimg4 = " ▶";
	//처음, 이전, 다음, 마지막, 이미지

echo Paging_num($l_parameter, $totalblock, $nowb, $nows, $nowe, $page, $pageimg1, $pageimg2, $pageimg3, $pageimg4);

//리스트 페이징~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
?>	
</td>
</tr>
<tr>
<td height="30" align="right">
<a href="/security/board/gwrite.php">[등 록]</a></td>
</tr>
<tr>
<td>&nbsp;</td>
</tr>
</table>