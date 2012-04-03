<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<br><br>
<form name="board" method="post" enctype="multipart/form-data">
<input name="currentPage" id="currentPage" value="${info.currentPage}"/>
<input type="hidden" name="bbs_cd" value="${boardManagerInfo.bbsCd}>">
${boardManagerInfo.boardName}>
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

<a href="">[ 완 료 ]</a>
&nbsp;&nbsp;&nbsp;&nbsp;
<a href="">[ 목 록 ]</a>

	</td>
</tr>
</form>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
	focusauto();
//-->
</SCRIPT>