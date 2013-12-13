<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core"
%><%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"
%><%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"
%><%@ taglib prefix="s" uri="/struts-tags"
%><<g:size item="${supportDeviceList}" var="supportDeviceListSize" 
/><!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script>
$(document).ready(function() {
	showAppFileInput(${not empty coreApp.appPath ? "false" : "true"});
	appTypeChanged("<g:string value="${empty coreApp.coreappId ? 'DP002821'  : coreApp.appType}"/>");
});

function showAppFileInput(showFlag) {
	if (showFlag) {
		$("#spanAppFileInput").css("display", "");
		$("#spanAppFileInfo").css("display", "none");
		$("form[name=eform] input[name=coreApp\\.appFile]").removeAttr("disabled");
	} else {
		$("#spanAppFileInput").css("display", "none");
		$("#spanAppFileInfo").css("display", "");
		$("form[name=eform] input[name=coreApp\\.appFile]").attr("disabled", "disabled");
	}
}


function appTypeChanged(appType) {
	$("#divGroupList").block({ message: '<gm:string value="jsp.device.scmng.scVersionEdit.msg.inquireNotice"/>', overlayCSS: {backgroundColor: 'white', opacity: 0.6} });
	$("input[name=coreApp\\.appType]").attr("disabled", "disabled");
	$("#spanLastVersion").html("");
	$.ajax("<c:url value="/device/ajaxScVersionGroupList4Edit.omp"/>",
		{
			  async    : true
			, cache    : false
			, data     : {"sc.appTypes" : appType}
			, dataType : "html"
			, success  : function(data) {
							$("#divGroupList").html(data);
							$("input[name=coreApp\\.coreappGroupId]").each(function (idx, tobj){
<c:if test="${not empty coreApp.coreappId}">
								tobj.checked 	= (tobj.value == "<g:string value="${coreApp.coreappGroupId}"/>");
</c:if>
								tobj.disabled	= <g:text value="${not empty coreApp.status and coreApp.status ne 'DP005401'}"/> 
							});
						}
			, complete : function() {
							$("input[name=coreApp\\.appType]").removeAttr("disabled");
							$("#divGroupList").unblock();
						}
		}
	);
}

function saveCoreApp() {
	if (showValidate("eform", "default", "<gm:string value="jsp.device.scmng.scVersionEdit.msg.invalidInput"/>")) {
		$.blockUI({ message: '<gm:string value="jsp.device.scmng.scVersionEdit.msg.saving"/>', overlayCSS: {backgroundColor: 'white', opacity: 0.6} });		
		document.eform.submit();
	}
}


var	popupGroup;
function popupGroupDeviceList(group) {
	window.open("<c:url value="/device/popupScGroupDeviceList.omp"/>?" + $.param({"groupId" : group.coreappGroupId}), "groupDeviceList", "width=830,height=400,scrollbars=yes").focus();
	popupGroup	= group;
}

function groupSelected(groupId) {
	$("#spanLastVersion").text("now checking...");
	$("form[name=eform] input[name=coreApp\\.ver]").attr("disabled", true);
	$.ajax("<c:url value="/device/ajaxScLastVersionInGroup.omp"/>",
			{
				  async    : true
				, cache    : false
				, data     : {"sc.groupId" : groupId}
				, dataType : "html"
				, success  : function(data) {
								$("#spanLastVersion").html(data);
							}
				, complete : function() {
								$("form[name=eform] input[name=coreApp\\.ver]").removeAttr("disabled", true);								
							}
			}
		);
}

function setLastVersion(groupId, lastVer) {
<c:if test="${not empty coreApp.coreappGroupId}">	
	if (groupId == "<g:string value="${coreApp.coreappGroupId}"/>") {
		$("input[name=lastVer]").val("");
		//$("input[name=coreApp\\.ver]").val("<g:string value="${coreApp.ver}"/>");
		return;
	}
</c:if>	
	
	$("input[name=lastVer]").val(lastVer);
	//$("input[name=coreApp\\.ver]").val(lastVer);
}

</script>
</head>
<body>
	<div id="location">
		首頁 &gt; 手機管理中心 &gt; SC 版本管理 &gt;<strong>Binary 檔管理</strong> 
	</div><!-- //location -->

	<h1 class="fl pr10 mb0">Binary 檔管理</h1>
	<p>可管理Shop Client之 Client, ARM Binary 檔.</p>
	<form name="eform" method="post" action="scVersionSave.omp" enctype="multipart/form-data">
	<g:printBean value="${sc}" outType="hidden" prefix="sc."/>
<c:if test="${empty coreApp.coreappId }">
	<input type="hidden" name="coreApp.status" value="DP005401"/>
</c:if>
<c:if test="${not empty coreApp.coreappId}">	
	<input type="hidden" name="coreApp.coreappId" value="${coreApp.coreappId}"/>
</c:if>
	<table class="tabletype01 mt20">
		<colgroup>
			<col style="width:150px;">
			<col >
		</colgroup>

		<tr>
			<th>類別</th>
			<td>
<c:choose>
	<c:when test="${empty coreappId}">
				<gc:radioButtons name="coreApp.appType" group="DP0028" filter="coreAppManageSc" value="DP002821">
					<g:param name="extra">
						onclick="appTypeChanged(this.value);"
						v:mustcheck="1,1" m:mustcheck="<gm:tagAttb value="jsp.device.scmng.scVersionEdit.msg.reqCheckAppType"/>"
					</g:param>
				</gc:radioButtons>
	</c:when>
	<c:otherwise>
				<input type="hidden" name="coreApp.appType" value="${coreApp.appType}"/>
				<gc:html code="${coreApp.appType}"/>
	</c:otherwise>
</c:choose>
			</td>
		</tr>
		<tr>
			<th>群組</th>
			<td>
				<div id="divGroupList" class="scroll">
					<table class="tabletype02" style="width:621px; border:1px solid #ddd;">
						<colgroup>
							<col width="40px" />
							<col width="60%" />
							<col width="12%" />
							<col >
						</colgroup>
						<tbody>
						<tr>
							<th>選擇</th>
							<th>群組名稱</th>
							<th>手機</th>
							<th>新增日期</th>
						</tr>
						<tr>
							<td colspan="4"><gm:html value="jsp.device.scmng.scVersionEdit.msg.plzCheckAppType"/></td>
						</tr>
						</tbody>
					</table>
				</div>
			</td>
		</tr>
		<tr>
			<th>版本</th>
			<td>
				<input type="hidden" name="lastVer" value="0.0">
				<input name="coreApp.ver" type="text" class="txt" style="width:200px;" value="${coreApp.ver}" maxlength="8"
					v:required m:required="<gm:tagAttb value="jsp.device.scmng.scVersionEdit.msg.reqVersion"/>"
					v:regexp="[0-9\\.]+[0-9]" m:regexp="<gm:tagAttb value="jsp.device.scmng.scVersionEdit.msg.invalidVersion"/>"
					v:scompare="gt,@{lastVer}" m:scompare="<gm:tagAttb value="jsp.device.scmng.scVersionEdit.msg.invalidVersion2"/>"
					onfocus="this.select();"/>
				&nbsp;<span id="spanLastVersion"></span>
			</td>
		</tr>
		<tr>
			<th>檔案</th>
			<td>
				<span id="spanAppFileInput" style="display:none">
					<input name="coreApp.appFile" type="file" style="width:500px;" disabled
						v:required m:required="<gm:tagAttb value="jsp.device.scmng.scVersionEdit.msg.reqFile"/>"
						v:regexp=".+\.apk" m:regexp="<gm:tagAttb value="jsp.device.scmng.scVersionEdit.msg.wrongFileType"/>"/>
<c:if test="${not empty coreApp.appPath}">
					<a href="javascript:showAppFileInput(false);" class="btn_s"><span><gm:html value="jsp.device.scmng.scVersionEdit.btn.cancelDelete"/></span></a>
</c:if>
				</span>
<c:if test="${not empty coreApp.appPath}">
				<span id="spanAppFileInfo">
					<a href="${CONF['omp.common.url.http-share.coreapp']}/${coreApp.appPath}">${coreApp.appPath}</a>
					<a href="javascript:showAppFileInput(true);" class="btn_s"><span><gm:html value="刪除"/></span></a>
				</span>
</c:if>
			</td>
		</tr>
		<tr>
			<th>更新內容</th>
			<td class="align_td">
				<textarea name="coreApp.prodDesc" rows="10" cols="77"
					v:required m:required="<gm:tagAttb value="jsp.device.scmng.scVersionEdit.msg.reqVersionDesc"/>"
					v:text8_limit="4000" m:text8_limit="<gm:tagAttb value="jsp.device.scmng.scVersionEdit.msg.limitVersionDesc"/>"><g:tagBody value="${coreApp.prodDesc}"/></textarea>
			</td>
		</tr>
	</table>
	</form>
	<div class="text_c mt20">
		<a href="javascript:saveCoreApp();" class="btn"><strong>確定</strong></a>
		<a href="<c:url value="/device/scVersionList.omp"/>?<g:printBean prefix="sc." value="${sc}" outType="qs"/>" class="btn"><strong class="">取消</strong></a>
	</div>
</body>
</html>