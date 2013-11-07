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
	$("form[name=sform] select[name=sc\\.searchType]").val("<g:string value="${sc.searchType}"/>");
});

function setDefaultSearchCondition() {
	setSearchTerm("60day");
	$("form[name=sform] input[name=sc\\.appTypes]").each(function(idx, obj) {obj.checked=true;});
	$("form[name=sform] input[name=sc\\.searchType]").val("groupName");
}

function doSearch() {
	if (showValidate("sform", "default", "<gm:string value="jsp.device.scmng.scGroupList.msg.invalidInput"/>")) {
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
	location.href	= "scGroupEdit.omp?<g:printBean value="${sc}" outType="qs" prefix="sc."/>";
}

function edit(coreappGroupId) {
	location.href="<c:url value="/device/scGroupEdit.omp"/>?groupId=" + coreappGroupId + "&<g:printBean value="${sc}" prefix="sc." outType="qs"/>";
}

function deleteGroup() {
	if (showValidate("dform", "default", "<gm:string value="jsp.device.scmng.scGroupList.msg.invalidChoice"/>")
		&& confirm("<gm:string value="jsp.device.scmng.scGroupList.msg.confirmDeleteGroup"/>")) {
		document.dform.submit();
	}
}

function checkAll(checked) {
	$("input[name=selectedGroups]").each(function (idx, tobj){tobj.checked=checked;});
}
</script>
</head>
<body>
	<div id="location">
		首頁 &gt; 手機管理中心 &gt; SC 版本管理 &gt;<strong>群組管理</strong> 
	</div><!-- //location -->

	<h1 class="fl pr10 mb0">群組管理</h1>
	<p>可管理Shop Client之群組.</p>

	<form name="sform" action="scGroupList.omp">
	<input type="hidden" name="sc.page.no" value="1"/> 
	<table class="tabletype01 mt20">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
		<tr>
			<th>新增日期</th>

			<td class="align_td">					
				<input name="sc.fromDate" type="text" class="txt" style="width:80px;"
					value="<g:tagAttb value="${sc.fromDate}" format="L####-##-##"/>"
					v:required m:required="<gm:tagAttb value="jsp.device.scmng.scGroupList.msg.requiredSearchFrom"/>"
					v:day_term="60,@{sc.toDate}" m:day_term="<gm:tagAttb value="jsp.device.scmng.scGroupList.msg.cantOverSearcheTerm60Days"/>"
					v:scompare="le,@{sc.toDate}" m:scompare="<gm:tagAttb value="jsp.device.scmng.scGroupList.msg.wrongSearchFromTo"/>"
					/>
				~
				<input name="sc.toDate" type="text" class="txt" style="width:80px;"
					value="<g:tagAttb value="${sc.toDate}" format="L####-##-##"/>"
					v:required m:required="<gm:tagAttb value="jsp.device.scmng.scGroupList.msg.requiredSearchTo"/>"
					/>
				<a class="btn_s" href="javascript:setSearchTerm('7day');"><span>最近一周</span></a>
				<a class="btn_s" href="javascript:setSearchTerm('30day')"><span>最近三十日</span></a>
				<a class="btn_s" href="javascript:setSearchTerm('60day')"><strong>最近六十日</strong></a>
			</td>

			<td rowspan="3" class="text_c" >
				<a class="btn" href="javascript:doSearch()"><strong>搜尋</strong></a>
				<a class="btn" href="javascript:doClear()"><span>重新搜尋</span></a>
			</td>
		</tr>
		<tr>
			<th>類別</th>

			<td>
				<gc:checkBoxs name="sc.appTypes" group="DP0028" value="${sc.appTypes}" filter="coreAppManageSc" divide="&nbsp;&nbsp;&nbsp;">
					<g:param name="extra">v:mustcheck="1,2" m:mustcheck="<gm:tagAttb value="jsp.device.scmng.scGroupList.msg.requiredAppType"/>"</g:param>
				</gc:checkBoxs>
			</td>
		</tr>
		<tr>
			<th>搜尋關鍵字</th>
			<td>
				<select name="sc.searchType">
					<option value="groupName">群組名稱</option>
					<option value="modelName">型號</option>
					<option value="deviceName">手機名稱</option>
				</select>
				<input name="sc.keyword" type="text" class="txt" style="width:330px;" value="<g:tagAttb value="${sc.keyword}"/>" />
			</td>
		</tr>
	</table>
	</form>
	<p class="fl mt20">共有<g:html value="${sc.page.totalCount}"/>個群組.</p>
	<form name="dform" action="scGroupDelete.omp">
	<g:printBean value="${sc}" outType="hidden" prefix="sc."/>
	<table class="tabletype02">
		<colgroup>
			<col style="width:40px;" />
			<col />
			<col width="50%" />
			<col />
			<col />
			<col />
		</colgroup>
		<thead>
		<tr>
			<th><input type="checkbox" onclick="checkAll(this.checked)"/></th>
			<th nowrap>類別</th>
			<th nowrap>群組名稱</th>
			<th nowrap>手機</th>
			<th nowrap>Binary</th>
			<th nowrap>新增日期</th>
		</tr>
		</thead>
		<tbody>
<c:choose>
	<c:when test="${groupList == null}">
		<tr>
			<td colspan="6" style="width:100%"><gm:html value="jsp.device.scmng.scGroupList.msg.plzSearch"/></td>
		</tr>
	</c:when>
	<c:when test="${empty groupList}">
		<tr>
			<td colspan="6" style="width:100%"><gm:html value="jsp.device.scmng.scGroupList.msg.emptyResult"/></td>
		</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${groupList}" var="coreAppGroup">
		<tr>
			<td>
				<input name="selectedGroups" type="checkbox" value="<g:tagAttb value="${coreAppGroup.coreappGroupId}"/>"
					v:mustcheck="1,9999" m:mustcheck="<gm:tagAttb value="jsp.device.scmng.scGroupList.msg.plzCheckCheckbox"/>"/>
			</td>
			<td class="text_l" nowrap><gc:html code="${coreAppGroup.appType}"/></td>
			<td class="text_l"><a href="javascript:edit('${coreAppGroup.coreappGroupId}')"><g:html value="${coreAppGroup.groupName}"/></a></td>
			<td nowrap style="text-align:right"><g:html value="${coreAppGroup.deviceCount}"/></td>
			<td nowrap style="text-align:right"><g:html value="${coreAppGroup.coreappCount}"/></td>
			<td nowrap><g:html value="${coreAppGroup.regDttm}" format="L####-##-## ##:##"/></td>
		</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
		</tbody>
	</table>
	</form>
	<p class="fl mt05"><a class="btn_s" href="javascript:deleteGroup()"><span>批量刪除</span></a></p>

	<p class="btn_wrap text_r mt05">
		<a class="btn" href="javascript:createNew()"><span>新增</span></a>
	</p>
	<!-- paging -->
	<g:pageIndex item="${groupList}"/>
	<!-- //paging -->
</div>
</body>
</html>
