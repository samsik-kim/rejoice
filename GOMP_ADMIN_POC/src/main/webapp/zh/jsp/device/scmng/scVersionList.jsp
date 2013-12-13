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
	$("form[name=sform] input[name=sc\\.fromDate]").datepicker();
	$("form[name=sform] input[name=sc\\.toDate]").datepicker();
	//setDefaultSearchCondition();
	appTypeChanged("<g:string value="${sc.groupId}"/>");
});

function setDefaultSearchCondition() {
	setSearchTerm("60day");
	$("form[name=sform] input[name=sc\\.appType]").each(function(idx, obj) {obj.checked=true;});
	$("form[name=sform] input[name=sc\\.status]").each(function(idx, obj) {obj.checked=true;});
	appTypeChanged();
}

function doSearch() {
	if (showValidate("sform", "default", "<gm:string value="jsp.device.scmng.scVersionList.msg.invalidInput"/>")) {
		document.sform.submit();
	}
}

function doClear() {
	$.clearForm("sform");
	setDefaultSearchCondition();
}

function goPage(pageNo) {
	$("form[name=sform] input[name=sc\\.page\\.no]").val(pageNo);
	doSearch();
}

function setSearchTerm(term) {
	var	tform;
	
	tform	= document.sform;
	setOrderSearchDateAdminPoC(term, tform.elements["sc.fromDate"], tform.elements["sc.toDate"]);
}

function createNew() {
	location.href	= "scVersionEdit.omp?<g:printBean value="${sc}" outType="qs" prefix="sc."/>";
}

function edit(coreappId) {
	location.href="<c:url value="/device/scVersionEdit.omp"/>?coreappId=" + coreappId + "&<g:printBean value="${sc}" prefix="sc." outType="qs"/>";
}

function appTypeChanged(selectedValue) {
	$("#divGroupCondition").block({ message: null, overlayCSS: {backgroundColor: 'white', opacity: 0.6} });
	$("#divAppTypesCondition").block({ message: null, overlayCSS: {backgroundColor: 'white', opacity: 0.6} });
	$.ajax("<c:url value="/device/ajaxScVersionGroupList4Search.omp"/>",
		{
			  async    : true
			, cache    : false
			, data     : $("form[name=sform]").serialize()
			, dataType : "html"
			, success  : function(data) {
							$("#divGroupCondition").html(data);
							if (selectedValue != null) {
								$("form[name=sform] select[name=sc\\.groupId]").val(selectedValue);								
							}
						}
			, complete : function() {
							$("#divGroupCondition").unblock();
							$("#divAppTypesCondition").unblock();
						}
		}
	);
}

function checkAll(checked) {
	$("input[name=selectedCoreapps]").each(function (idx, tobj){tobj.checked=checked;});
}

function doChangeStatus(status) {
	exfncs.groupCount	= new Array();
	$("form[name=eform] input[name=status]").val(status);
	if (showValidate("eform", "default", "<gm:string value="jsp.device.scmng.scVersionList.msg.invalidCheck"/>", exfncs)) {
		document.eform.submit();
	}
}

var exfncs = {
	"check_status" : function (c, arg) {
						if (!c.checked || arg[0] != $("form[name=eform] input[name=status]").val()) {
							return true;
						}
						return arg[0] != $(c).attr("p_status"); 
					 },
	"check_group"  : function (c, arg) {
						var tgroupId;		
		
						if (!c.checked) {
							return true;
						}
						tgroupId	= $(c).attr("p_group");
						if (exfncs.groupCount[tgroupId] != null) {
							return false;
						} else {
							exfncs.groupCount[tgroupId] = "checked";
							return true;
						}
	 			     }
}
</script>
</head>
<body>
	<div id="location">
		首頁 &gt; 手機管理中心 &gt; SC 版本管理 &gt;<strong>Binary 檔管理</strong>
	</div><!-- //location -->

	<h1 class="fl pr10 mb0">Binary 檔管理</h1>
	<p>可管理Shop Client之 Client, ARM Binary 檔.</p>

	<form name="sform" action="scVersionList.omp">
	<input type="hidden" name="sc.page.no" value="1"/> 
	<table class="tabletype01 mt20">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
		<tr>
			<th>新增日期</th>

			<td class="align_td">
				<input name="sc.fromDate" type="text" class="txt" style="width:80px;"
					value="<g:tagAttb value="${sc.fromDate}" format="L####-##-##"/>"
					v:required m:required="<gm:tagAttb value="jsp.device.scmng.scVersionList.msg.requiredSearchFrom"/>"
					v:day_term="60,@{sc.toDate}" m:day_term="<gm:tagAttb value="jsp.device.scmng.scVersionList.msg.cantOverSearcheTerm60Days"/>"
					v:scompare="le,@{sc.toDate}" m:scompare="<gm:tagAttb value="jsp.device.scmng.scVersionList.msg.wrongSearchFromTo"/>"
					/>
				~
				<input name="sc.toDate" type="text" class="txt" style="width:80px;"
					value="<g:tagAttb value="${sc.toDate}" format="L####-##-##"/>"
					v:required m:required="<gm:tagAttb value="jsp.device.scmng.scVersionList.msg.requiredSearchTo"/>"
					/>
				<a class="btn_s" href="javascript:setSearchTerm('7day')"><span>最近一周</span></a>
				<a class="btn_s" href="javascript:setSearchTerm('30day')"><span>最近三十日</span></a>
				<a class="btn_s" href="javascript:setSearchTerm('60day')"><strong>最近六十日</strong></a>
			</td>

			<td rowspan="4" class="text_c" >
				<a class="btn" href="javascript:doSearch()"><strong>搜尋</strong></a>
				<a class="btn" href="javascript:doClear()"><span>重新搜尋</span></a>
			</td>
		</tr>
		<tr>
			<th>群組名稱</th>

			<td>
				<div id="divGroupCondition">
				<select name="sc.groupId">
					<option value="">全部</option>
				</select>
				</div>
			</td>
		</tr>
		<tr>
			<th>類別</th>

			<td>
				<div id="divAppTypesCondition">
				<gc:checkBoxs name="sc.appTypes" group="DP0028" value="${sc.appTypes}" filter="coreAppManageSc" divide="&nbsp;&nbsp;&nbsp;">
					<g:param name="extra">v:mustcheck="1,2" m:mustcheck="<gm:tagAttb value="jsp.device.scmng.scVersionList.msg.plzCheckAppType"/>" onclick="appTypeChanged()"</g:param>
				</gc:checkBoxs>
				</div>
			</td>
		</tr>
		<tr>
			<th>狀態</th>

			<td>
				<gc:checkBoxs name="sc.status" group="DP0054" value="${sc.status}" divide="&nbsp;&nbsp;&nbsp;">
					<g:param name="extra">v:mustcheck="1,3" m:mustcheck="<gm:tagAttb value="jsp.device.scmng.scVersionList.msg.plzCheckStatus"/>"</g:param>
				</gc:checkBoxs>
			</td>
		</tr>
	</table>
	</form>
	<p class="fl mt20">共附加<g:html value="${sc.page.totalCount}"/>個群組.</p>
	<form name="eform" method="post" action="<c:url value="/device/scVersionChangeStatus.omp"/>">
	<input type="hidden" name="status"/>
	<g:printBean value="${sc}" outType="hidden" prefix="sc."/>
	<table class="tabletype02">

		<colgroup>
			<col style="width:30px;">
			<col >
			<col >
			<col >
			<col >
			<col >
			<col >
		</colgroup>

		<thead>
		<tr>
			<th><input type="checkbox" onclick="checkAll(this.checked)"/></th>
			<th>類別</th>
			<th>版本</th>
			<th>狀態</th>
			<th>群組名稱</th>
			<th>檔案名稱</th>
			<th>新增日期</th>
		</tr>
		</thead>
		<tbody>
<c:choose>
	<c:when test="${coreAppList == null}">
		<tr>
			<td colspan="7" style="width:100%"><gm:html value="jsp.device.scmng.scVersionList.msg.plzSearch"/></td>
		</tr>
	</c:when>
	<c:when test="${empty coreAppList}">
		<tr>
			<td colspan="7" style="width:100%"><gm:html value="jsp.device.scmng.scVersionList.msg.emptyResult"/></td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${coreAppList}" var="coreApp">
		<tr>
			<td>
				<input name="selectedCoreapps" type="checkbox" value="${coreApp.coreappId}"
					v:mustcheck="1,9999" m:mustcheck="<gm:tagAttb value="jsp.device.scmng.scVersionList.msg.plzCheckBinary"/>"
					v:check_status1="DP005403" m:check_status1="<gm:tagAttb value="jsp.device.scmng.scVersionList.msg.statusProductAlready"/>"
					v:check_status2="DP005402" m:check_status2="<gm:tagAttb value="jsp.device.scmng.scVersionList.msg.statusTestAlready"/>"
					v:check_group m:check_group="<gm:tagAttb value="jsp.device.scmng.scVersionList.msg.duplicateCheckInGroup"/>"
					p_group="<g:tagAttb value="${coreApp.coreappGroupId}"/>"
					p_status="<g:tagAttb value="${coreApp.status}"/>"
					/> 
			</td>
			<td class="text_l"><gc:html code="${coreApp.appType}"/></td>
			<td style="cursor:pointer;text-decoration:underline;color:blue;text-align:right" onclick="edit('${coreApp.coreappId}')"><g:html value="${coreApp.ver}"/></td>
			<td><gc:html code="${coreApp.status}"/></td>
			<td class="text_l"><g:html value="${coreApp.groupName}"/></td>
			<td class="text_l"><a href="${CONF['omp.common.url.http-share.coreapp']}/${coreApp.appPath}"><g:html value="${coreApp.appPath}"/></a></td>
			<td><g:html value="${coreApp.regDttm}" format="L####-##-## ##:##"/></td>
		</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
		</tbody>
	</table>
	</form>
	<p class="fl mt05"><a class="btn_s" href="javascript:doChangeStatus('DP005403')"><span>使用</span></a> <a class="btn_s" href="javascript:doChangeStatus('DP005402')"><span>測試</span></a></p>
	<p class="btn_wrap text_r mt05">
		<a class="btn" href="javascript:createNew()"><span>新增</span></a>
	</p>
	<!-- paging -->
	<g:pageIndex item="${coreAppList}"/>
	<!-- //paging -->
</body>
</html>
