<?
	require("../setup.php");
	include "sub_menu.php";

$data = fetch_array("board", "where seq_no='$bno'");

	if ($bno){
		$code_num = $data[code_num];
	}else{
		if ($keyg == "code_num"){
			$code_num = $key;
		}
	}
	
	if ($code_num){
		$coden = fetch_array("code_cate", "where code_num='$code_num'");
		$code_name = $coden[code_name];
	}

$para="page=$page&bbs_cd=$bbs_cd";

?>
<SCRIPT LANGUAGE="JavaScript">
<!--
function chkcode(){
	var cnum = document.all.board.code_num.value;
	var code_len = cnum.length;

if (code_len == 6){
	var aleady_code = new Array();
	var aleady_name = new Array();

<?
	$sql = "select * from code_cate";
	$result = mysql_query($sql);

$z = 0;
	while ($run = mysql_fetch_array($result)){
		$z++;
?>
	aleady_code[<?=$z?>] = "<?=$run[code_num]?>";
	aleady_name[<?=$z?>] = "<?=$run[code_name]?>";
<?
	}
?>

	result = "false";

	for (i=1;i<aleady_code.length;i++){

		if (aleady_code[i] == cnum){
			result = "true";
			cname = aleady_name[i];
		}
	}

	if (result == "false" && document.all.board.code_num.value != ""){
		alert("등록되지 않은 코드번호 입니다.");
		document.all.board.code_num.value="";
		document.all.cvname.innerHTML = "";		
		document.all.board.code_num.focus();
		return;
	}
	document.all.board.code_name.value = cname;
	document.all.cvname.innerHTML = cname;

}
}

function focusauto(){
<?
	if ($code_num){
?>
		top.amain.document.all.board.subject.focus();
<?
	}else{
?>
		top.amain.document.all.board.code_num.focus();
<?
	}
?>
}
//-->
</SCRIPT>
<link rel="stylesheet" type="text/css" href="/utill/css/admin.css">
<br><br>
<table width="800" border="0" cellpadding="0" cellspacing="0" align="center">
<form name="board" method="post" action="/security/board/board_ok.php" enctype="multipart/form-data">
<input type="hidden" name="mode" value="<?=$mode?>">
<input type="hidden" name="bno" value="<?=$bno?>">
<input type="hidden" name="rbno" value="<?=$rbno?>">
<input type="hidden" name="reply" value="<?=$reply?>">
<input type="hidden" name="page" value="<?=$page?>">
<input type="hidden" name="bbs_cd" value="<?=$bbs_cd?>">
  <tr>
		  <td><?=$title?></td>
		</tr>
<?
	if ($keyg=="code_num"){
		$code_data = fetch_array("code_cate", "where code_num='$key'");
?>
  <tr> 
      <td colspan="2" align="center" valign="top">&nbsp;</td>
  </tr>
  <tr> 
      <td colspan="2" align="center" valign="top">
<table width="800" border="0" cellpadding="0" cellspacing="1" bgcolor="#330066">
<tr height="30" bgcolor="white">
	<td class="subject" width="100">종 목 명</td>
	<td class="object" style="padding-left:5px;"><?=$code_data[code_name]?></td>
	<td class="subject" width="100">종목코드</td>
	<td class="object" style="padding-left:5px;"><?=$code_data[code_num]?></td>
</tr>
 <tr height="30" bgcolor="white">
	<td class="subject">전화번호</td>
	<td class="object" style="padding-left:5px;">
	<?=$code_data[tel1]?>-<?=$code_data[tel2]?>-<?=$code_data[tel3]?>
	</td>
	<td class="subject">정보연락처</td>
	<td class="object" style="padding-left:5px;">
	<?=$code_data[info_tel1]?>-<?=$code_data[info_tel2]?>-<?=$code_data[info_tel3]?>
	</td>
</tr>
<tr height="30" bgcolor="white">
	<td class="subject">지분보유</td>
	<td class="object" style="padding-left:5px;"><?=number_format($code_data[boyujibun])?></td>
	<td class="subject">주주</td>
	<td class="object" style="padding-left:5px;"><?=number_format($code_data[juju])?></td>
</tr>
</table>
	  </td>
  </tr>
  <tr> 
      <td colspan="2" align="center" valign="top">&nbsp;</td>
  </tr>
<?}?>
		<tr>
		   <td>

<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#330066">
<tr height="30" bgcolor="white">
	<td class="subject">코드번호</td>
	<td class="object" style="padding-left:5px;"><input name="code_num" type="text" size="30" maxlength="30" value="<?=$code_num?>" onkeyup="chkcode();">
<input type="hidden" name="code_name" value="<?=$code_name?>">
&nbsp;&nbsp;&nbsp;
<font color="#FF6600">
<span id="cvname"><?=$code_name?></span>
</font>
	</td>
</tr>
<tr height="30" bgcolor="white">
	<td class="subject" width="100">제 목</td>
	<td class="object" style="padding-left:5px;"><input name="subject" type="text" size="100" maxlength="100" value="<?=$data[subject]?>"></td>
</tr>
<tr height="30" bgcolor="white">
	<td class="subject">내 용</td>
	<td class="object" style="padding-left:5px;">
<!-----------  편집기...  ----------->
<script language="Javascript1.2">
<!-- // load htmlarea
_editor_url = "/security/board/";                     // URL to htmlarea files
var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
if (navigator.userAgent.indexOf('Mac')        >= 0) { win_ie_ver = 0; }
if (navigator.userAgent.indexOf('Windows CE') >= 0) { win_ie_ver = 0; }
if (navigator.userAgent.indexOf('Opera')      >= 0) { win_ie_ver = 0; }
if (win_ie_ver >= 5.5) {
  document.write('<scr' + 'ipt src="' +_editor_url+ 'editor.js"');
  document.write(' language="Javascript1.2"></scr' + 'ipt>');  
} else { document.write('<scr'+'ipt>function editor_generate() { return false; }</scr'+'ipt>'); }
// -->
</script>
<textarea name="content" style="width:100%; height:300px; border:0;"  id="contant"><?=$data[content]?></textarea>

<script language='javascript1.2'>
editor_generate('content');
</script>
<!-----------  편집기...  ----------->		
	</td>
</tr>
<tr height="30" bgcolor="white">
	<td class="subject">첨부파일</td>
	<td class="object" style="padding-left:5px;"><input type="file" name="file1" size="80"></td>
</tr>
</table>
</td>
</tr>
<tr height="30">
	<td align="right">

<a href="javascript:board.submit();">[ 완 료 ]</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="/security/board/list.php?<?=$para?>">[ 목 록 ]</a>

	</td>
</tr>
</form>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
	focusauto();
//-->
</SCRIPT>