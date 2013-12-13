<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
	function submitEdit(frm){	
		$.blockUI({ message: '<h4>잠시만 기다려 주세요.</h4>' });
		frm.submit();
	}

	$(function() {
		$("#datepicker").datepicker();
	});
	
</script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.ui.all.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery.blockUI.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Insert title here</title>
</head>

<body>
	<s:form name="editForm" method="post" enctype="multipart/form-data" action="fileUpload.omp">	
	<style>							
		.fileinputs{position: relative;}
		.fileinputs *{vertical-align: middle;}
		.fakefile {	position: absolute;	top:0px;left:0px;z-index: 1;}
		.inputFile {position: relative;text-align:right ;filter:alpha(opacity:0);opacity:0;z-index:2;}
	</style>

	<input type="hidden" name="content.subContents[0].runFile.scid" value="010123123"/>
	<input type="hidden" name="content.subContents[0].testFile.scid" value="abcdefge"/>
	
	<input type="hidden" name="content.subContents[1].runFile.scid" value="010101010"/>
	<input type="hidden" name="content.subContents[1].testFile.scid" value="fefsfsdfs"/>
	
	<input type="hidden" name="content.subContents[2].runFile.scid" value="023413141"/>
	<input type="hidden" name="content.subContents[2].testFile.scid" value="fsdfsgafd"/>
	
	<input type="hidden" name="content.subContents[3].runFile.scid" value="023413141"/>
	<input type="hidden" name="content.subContents[3].testFile.scid" value="fsdgsfwge"/>
	
	<s:file name="content.subContents[0].runFile.run" />
	<s:file name="content.subContents[0].testFile.apiTest" />
	
	

		<input type="button" name="bt" title="등록" onclick="javascrpt:submitEdit(document.editForm); return false;"/>
				
	</s:form>
</body>
</html>