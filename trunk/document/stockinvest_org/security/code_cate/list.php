<?
	include "../setup.php";

	$dbname="code_cate";//게시판 테이블명.

	if (!$page) $page=1; //페이지 초기값

	$nperpage=10;//페이지당 게시물수

//	$addw[]="";//기본 조건

	$addt[]="seq_no desc";

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
$para="page=$page&bbs_cd=$bbs_cd";

//여기까지 include
?>
<link rel="stylesheet" type="text/css" href="/utill/css/admin.css">
<br><br>
<table width="600" border="0" cellspacing="0" cellpadding="0" align="center">
  <tr height="30"> 
     <td colspan="2"><font color="#AE0000"><B>[ 코드관리 ]</B></font></td>
  </tr>
  <tr> 
     <td colspan=2><table width="680" border="0" cellspacing="1" cellpadding="0" bgcolor="#00025B">
          <tr height="30" class="subject" align="center">  
            <td width="100">종목명</td>
            <td width="100">종목코드</td>
            <td width="100">지분보유</td>
            <td width="100">전화번호</td>
            <td width="100">정보연락처</td>
            <td width="100">등록일</td>
         </tr>
<?
	while ($data=mysql_fetch_array($result)){
		$tel = $data[tel1]." - ".$data[tel2]." - ".$data[tel3];
		$infotel = $data[info_tel1]." - ".$data[info_tel2]." - ".$data[info_tel3];
?>
			<tr bgcolor="white" height="25" style="cursor:hand;" onMouseOver="this.style.backgroundColor='#E6E6E6'; return true;" onMouseOut="this.style.backgroundColor='#ffffff'; return true;" onclick="location.href='/security/code_cate/view.php?cno=<?=$data[seq_no]?>';">  
                <td align="center"><?=$data[code_name]?></td>
                <td align="center"><?=$data[code_num]?></td>
                <td align="center"><?=$data[boyujibun]?></td>
                <td align="center"><?=$data[tel]?></td>
                <td align="center"><?=$data[info_tel]?></td>
                <td align="center"><?=date("Y-m-d", $data[crt_date])?></td>
           </tr>
<?
	$achno--;
}
?>
          <tr bgcolor="#C0C0C0" height="3"> 
              <td colspan="6"></td>
          </tr>
		</table>
	</td>
</tr>
<tr bgcolor="white"> 
    <td align="center" height="30">
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
	<td width="10%" align="right"><a href="write.php?<?=$para?>">[코드등록]</a></td>
</tr>
</table>