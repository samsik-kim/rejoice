<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	$("#okBtn").click(function(){
		$("#testFrm").attr("action", "/test/edit.do");
		$("#testFrm").submit();
	});
});

function fileDown(){
	location.href = "/test/fileDownload.do";
}
</script>
<form name="testFrm" id="testFrm" method="post" enctype="multipart/form-data">
<div>
<textarea  name="CONTENT" cols="150" rows="50"></textarea>

<script type="text/javascript">
//<![CDATA[
CKEDITOR.replace('CONTENT');
//]]
</script>
</div>
	<div class="fltr mar_t10">
		<a href="#"><img id="okBtn" src="/resource/images/common/btn_inner_ok3.gif" alt="OK" /></a>
	</div>
	<input type="hidden" name="atchFileNm" value=""/>
	<input type="file" id="atchFile" name="atchFile"/>
</form>
<div>
<a href="#" class="sbutton" onclick="javascript:fileDown()">다운받기</a>
</div>
