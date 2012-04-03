<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	$("#okBtn").click(function(){
		$("#testFrm").attr("action", "/test/edit.do");
		$("#testFrm").submit();
	});
});
</script>
<form name="testFrm" id="testFrm" method="post">
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
</form>
