<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>測試類別程序</title>
<script type="text/javascript" src="<c:url value="/js/inc/_pophead.js"/>"></script>
<script type="text/javascript">
//<![CDATA[
           
	$(function() {
		
		$("#closeBtn").click(function(event){
			event.preventDefault();
			window.close();
		});						
		
	});
//]]>
</script>
</head>
<body class="popup">
	<div id="popup_area_810">
		<h1>測試類別程序</h1>
		<table class="pop_tabletype02">
			<colgroup><col style="width:10%;"><col style="width:20%"><col style="width:55%;"><col style="width:15%"></colgroup>
			<tbody>
			<tr>
				<th>序號</th>
				<th>審核項目</th>
				<th>說明</th>
				<th>附加文件</th>
			</tr>
			<c:forEach items="${testCaseList}" var="info">
			<tr>
				<td>${info.seqNo }</td>
				<td><g:html value="${info.titleNm}"/></td>
				<td><g:html value="${info.explain}"/></td>
				<td><a class="btn_s" href="<c:url value="/fileSupport/fileDown.omp">
												<c:param name="bnsType" value="admin.path.share.testcase"/>
												<c:param name="filePath" value="${info.stepFile }" />
												<c:param name="fileName" value="${info.stepFileNm}" />
									       </c:url>"><span>文件</span></a></td>
			</tr>
			</c:forEach>
			</tbody>
		</table>
		<div class="pop_btn" >
			<a class="btn" href="#" id="closeBtn"><strong>關閉</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>

