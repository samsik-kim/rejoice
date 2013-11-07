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
<script>
$(document).ready(function() {
	appTypeChanged("<g:string value="${empty edit.coreappGroupId ? 'DP002821'  : edit.appType}"/>");
});

function appTypeChanged(appType) {
	$("#divDeviceList").block({ message: '<gm:string value="jsp.device.scmng.scGroupEdit.msg.inquireNotice"/>', overlayCSS: {backgroundColor: 'white', opacity: 0.6} });
	$("input[name=edit\\.appType]").attr("disabled", "disabled");
	$.ajax("<c:url value="/device/ajaxScGroupDeviceList.omp"/>",
		{
			  async    : true
			, cache    : false
			, data     : {"deviceSc.appType" : appType, "deviceSc.coreappGroupId" : "${groupId}"}
			, dataType : "html"
			, success  : function(data) {$("#divDeviceList").html(data);}
			, complete : function() {
							$("input[name=edit\\.appType]").removeAttr("disabled");
							$("#divDeviceList").unblock();
						}
		}
	);
}

function deviceListUpdated() {
	var	cnt;
	var	scnt;
	
	cnt		= 0;
	scnt	= 0;
	$("input[name=edit\\.phoneModelCd]").each(function (idx, obj) {cnt++; scnt+=obj.checked ? 1 : 0;});
	
	
	$("#spanDeviceCount").text(cnt);
	$("#spanDeviceCheckCount").text(scnt);
}

function checkAll(checked) {
	$("input[name=edit\\.phoneModelCd]").each(function (idx, obj) {obj.checked = !obj.disabled && checked;});
	deviceListUpdated();
}

function doSave() {
	if (showValidate("eform", "default", "<gm:string value="jsp.device.scmng.scGroupEdit.msg.invalidInput"/>")) {
		document.eform.submit();
	}
}

function doCancel() {
	location.href	= "<c:url value="/device/scGroupList.omp"/>?<g:printBean value="${sc}" outType="qs" prefix="sc."/>";
}

var	popupDevice	= null;

function popupPhoneHistory(device, index) {
	var	appType;
	
	if ("${edit.coreappGroupId}" == "") {
		appType	= $("input[name=edit\\.appType]:checked").val();
	} else {
		appType	= $("input[name=edit\\.appType]").val();
	}
	
	window.open("<c:url value="/device/popupScPhoneBinaryHistory.omp"/>?" + $.param({"historySc.phoneModelCd" : device.phoneModelCd, "historySc.appType" : appType}), "phoneBinaryHistory", "width=830,height=700,scrollbars=yes").focus();
	popupDevice			= device;
	popupDevice.verStr	= $("#tdDeviceVer" + index).text();
}

</script>
</head>
<body>
	<div id="location">
		首頁 &gt; 手機管理中心 &gt; SC 版本管理 &gt;<strong>群組管理</strong> 
	</div><!-- //location -->

	<h1 class="fl pr10 mb0">群組管理</h1>
	<p>可管理Shop Client之群組.</p>
	
	<form name="eform" action="scGroupSave.omp">
	<g:printBean value="${sc}" outType="hidden" prefix="sc."/>
	<input type="hidden" name="edit.coreappGroupId" value="${edit.coreappGroupId}"/>
	<table class="tabletype01 mt20">
		<colgroup>
			<col style="width:150px;">
			<col >
		</colgroup>

		<tr>
			<th>類別</th>
			<td>					
<c:if test="${empty edit.coreappGroupId}">			
				<gc:radioButtons name="edit.appType" group="DP0028" value="${empty edit.appType ? 'DP002821' : edit.appType}" filter="coreAppManageSc" divide="&nbsp;&nbsp;&nbsp;">
					<g:param name="extra">v:mustcheck="1" m:mustcheck="<gm:tagAttb value="jsp.device.scmng.scGroupEdit.msg.plzCheckAppType"/>" onclick="appTypeChanged(this.value)"</g:param>
				</gc:radioButtons>
</c:if>				
<c:if test="${not empty edit.coreappGroupId}">
				<gc:html code="${edit.appType}"/>
				<input type="hidden" name="edit.appType" value="<g:tagAttb value="${edit.appType}"/>"/>
</c:if>			
			</td>
		</tr>
		<tr>
			<th>群組名稱</th>

			<td>					
				<input name="edit.groupName" type="text" class="txt" style="width:400px;" value="<g:tagAttb value="${edit.groupName}"/>"
					v:required m:required="<gm:tagAttb value="jsp.device.scmng.scGroupEdit.msg.reqGroupName"/>"/>
			</td>
		</tr>
		<tr>
			<th>手機</th>
			<td>
				<p>共有<span id="spanDeviceCount">0</span>項手機資訊. (已選<span id="spanDeviceCheckCount">0</span>項)</p>

				<div id="divDeviceList" class="scroll">
					<table class="tabletype02" style="width:621px; border:1px solid #ddd;">
						<colgroup>
							<col width="30px" />
							<col width="15%" />
							<col width="18%" />
							<col width="15%" />
							<col width="15%" />
							<col />

						</colgroup>
						<tbody>
						<tr>
							<th><input type="checkbox" id="" /></th>
							<th>手機名稱</th>
							<th>型號</th>
							<th>OS版本</th>
							<th>LCD Size</th>
							<th>群組</th>
						</tr>
						<tr>
							<td colspan="6"><gm:html value="jsp.device.scmng.scGroupEdit.msg.plzCheckAppType"/></td>
						</tr>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<th>說明</th>
			<td class="align_td">
				<textarea name="edit.groupDesc" rows="10" cols="77"
					v:required m:required="<gm:tagAttb value="jsp.device.scmng.scGroupEdit.msg.reqGroupDesc"/>"
					v:text8_limit="4000" m:text8_limit="<gm:tagAttb value="jsp.device.scmng.scGroupEdit.msg.limitGroupDesc"/>"><g:tagBody value="${edit.groupDesc}"/></textarea>
			</td>
		</tr>
	</table>
	</form>

	<div class="text_c mt20">

		<a href="javaScript:doSave()" class="btn"><strong>確定</strong></a> <a href="javaScript:doCancel()" class="btn"><strong class="">取消</strong></a>
	</div>
</body>
</html>