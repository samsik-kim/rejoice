<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!-- <link rel="stylesheet" type="text/css" href=""/> --> 
<style type="text/css">
	body.popup table {
		margin-bottom:15px
	}
</style>
<script type="text/javascript">
	
	$(function() {
		var form = document.frmAuthDwldConfirm;
		
		if($("#resAuthConfirm").val() == "success") {
			opener.excelExport();
			self.close();
		}else if( $("#resAuthConfirm").val() == "present"){
			opener.excute();
			self.close();
		}else if ($("#resAuthConfirm").val() == "error") {
			alert("<gm:string value='jsp.gift.popupAuthDwIdConfirm.msg.noresult'/>");
		}
		
		// cofirmBtn btn
		$("#cofirmBtn").click(function(event){
			event.preventDefault();
			goConfirm();
		});
		
		// exit Btn
		$("#exitBtn").click(function(event){
			event.preventDefault();
			self.close();
		});
		
	});

	function goConfirm() {
		document.frmAuthDwldConfirm.action="popAuthDwldConfirm.omp";
		document.frmAuthDwldConfirm.submit();
	}

	</script>
</head>
<body class="popup">
<s:form name="frmAuthDwldConfirm" action="popAuthDwldConfirm" theme="simple">
	<input type="hidden" id="resAuthConfirm" name="sub.resAuthConfirm" value="${sub.resAuthConfirm}"/>
	<input type="hidden" id="issueType" name="sub.issueType" value="${sub.issueType}" />
	
	<div id="popup_area_440">
		<p class="mb10"><strong>請輸入登入管理者之密碼。</strong></p>
	
		<table class="pop_tabletype01">
			<colgroup><col width="30%;"><col width="70%;"></colgroup>
			<tr>
				<th>密碼</th>
				<td><input type="password" id="password" name="sub.password"/></td>
			</tr>
		</table>
		<div class="pop_btn" >
			<a class="btn" href="#" id="cofirmBtn"><strong>確定</strong></a>
			<a class="btn" href="#" id="exitBtn"><strong>關閉</strong></a>
		</div>
		</s:form>
	</div>
</body>
</html>	