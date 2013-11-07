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
<title>Binary 檔資訊</title>
<script>
$(document).ready(function () {
	$("#h1title").text(opener.popupDevice.modelNm + "   |   " + opener.popupDevice.mgmtPhoneModelNm + "   |   " +  opener.popupDevice.verStr);
});
</script>
</head>
<body class="popup">
	<div id="popup_area_810">
		<h1 id="h1title"></h1>
		<table class="pop_tabletype02">
			<colgroup><col width="20%;"><col width=""><col width=""><col width="60%"></colgroup>
			<tr>
				<th>類別</th>
				<th>版本</th>
				<th>狀態</th>
				<th>檔案名稱</th>
			</tr>
<c:if test="${empty coreAppHistory}">
			<tr>
				<td colspan="4"><gm:html value="jsp.device.scmng.popupScPhoneBinaryHistory.msg.emptyResult"/></td>
			</tr>
</c:if>			
<c:forEach items="${coreAppHistory}" var="coreApp">
			<tr>
				<td><gc:html code="${historySc.appType}"/></td>
				<td style="text-align:right"><g:html value="${coreApp.ver}"/></td>
				<td><gc:html code="${coreApp.status}"/></td>
				<td class="text_l"><a href="${CONF['omp.common.url.http-share.coreapp']}/${coreApp.appPath}"><g:html value="${coreApp.appPath}"/></a></td>
			</tr>
</c:forEach>
		</table>
		<div class="pop_btn" >

			<a class="btn" href="javascript:self.close();"><strong>關閉</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>