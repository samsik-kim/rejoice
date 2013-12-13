<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core"
%><%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"
%><%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"
%><%@ taglib prefix="s" uri="/struts-tags"
%><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Binary 檔管理</title>
<script>
$(document).ready(function () {
	$("#hiGroupName").text(opener.popupGroup.groupName);
});
</script>
</head>
<body class="popup">
	<div id="popup_area_810">
		<h1 id="hiGroupName"></h1>
		<table class="pop_tabletype02">

			<colgroup><col width="20%;"><col width=""><col width=""><col width="20%"></colgroup>
			<tr>
				<th>手機名稱</th>
				<th>型號</th>
				<th>OS版本</th>
				<th>LCD Size</th>
			</tr>
<c:if test="${empty deviceList}">
			<tr>
				<td colspan="4"><gm:html value="jsp.device.scmng.popupScGroupDeviceList.msg.emptyResult"/></td>
			</tr>
</c:if>			
<c:forEach items="${deviceList}" var="device">
			<tr>
				<td class="text_l"><g:html value="${device.modelNm}"/></td>
				<td class="text_l"><g:html value="${device.mgmtPhoneModelNm}"/></td>
				<td><gc:html code="${device.vmVer}"/></td>
				<td><gc:html code="${device.lcdSize}"/></td>
			</tr>
</c:forEach>
		</table>
		<div class="pop_btn" >
			<a class="btn" href="javascript:self.close()"><strong>關閉</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>

