<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
$(document).ready(function(){

// 	Rejoice.alertDialog("임시","잠시용~");
	
	pageLoadAjaxListInner("frm", "innerDiv", "/sample/ajaxData.do");

});
</script>
<div>
<form name="frm" id="frm" method="post">

</form>
</div>
<div id="innerDiv"></div>