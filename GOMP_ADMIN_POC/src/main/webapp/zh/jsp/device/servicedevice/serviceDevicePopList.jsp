<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

   
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>搜尋手機</title>
<script type="text/javascript" src="../inc/_pophead.js"></script>
</head>
<body class="popup">
<script type="text/javascript">

	$(document).ready( function()	{
		$("#searchType").change( function() {
		    $("#searchValue").val("");
		});

	} );

	function goPage(no) {
		$("form[name=searchDeviceForm] input[name=phoneInfo\\.page\\.no]").val(no);
		searchDeviceList();
	}

	var searchDeviceList = function() {
		$( "#searchDeviceForm" ).attr("action", "<c:url value="/device/popDeviceSearching.omp"/>");
		$( "#searchDeviceForm" ).submit();
	};

	var clearSrchCond = function( mode ) {
		$("#searchLcdSize").val("");
		$("#searchVmVer").val("");
		$("#searchSvcCd").val("");
		$("#searchType").val("byMgmtPhoneModelNm");
		$("#searchValue").val("");
	};

	var setDeviceView = function( mgmtPhoneModelNm, modelNm, gdcd, phoneModelCd, lcdSizeNm, vmVerNm, svcCdNm  ) {
		opener.selectedTargetPhone(mgmtPhoneModelNm, modelNm, gdcd, phoneModelCd, lcdSizeNm, vmVerNm, svcCdNm, this);
	};	
</script>

	<div id="popup_area_810">
		<h1>搜尋手機 <span class="h1_sub">若點選型號，該手機之資訊將顯示於畫面。</span></h1>
		
		<s:form action="popDeviceSearching" id="searchDeviceForm" theme="simple">
			<s:hidden id="srchFlag" name="srchFlag" value="TRUE"/>
			<s:hidden id="page.no" name="phoneInfo.page.no" value="%{phoneInfo.page.no}"/>
			<s:hidden id="phoneModelCd" name="phoneInfo.phoneModelCd" />
					
		<table class="pop_tabletype01">
			<colgroup><col style="width:20%;"><col style="width:80%"></colgroup>
			<tbody>
			<tr>
				<th>搜尋條件</th>
				<td class="align_td">					
					<label for="#">LCD Size</label>
						<select id="searchLcdSize" name="phoneInfo.searchLcdSize" style="width:85px;" >
                        	<option value="">全部</option>
		    				<gc:options group="PD0021" codeType="full" value="${phoneInfo.searchLcdSize}" />
		    			</select>

					<label for="#">OS版本</label>
						<select id="searchVmVer" name="phoneInfo.searchVmVer" style="width:85px;">
                        	<option value="">全部</option>
		    				<gc:options group="PD0091" codeType="full" filter="support" value="${phoneInfo.searchVmVer}" />
						</select>

					<label for="#">服務類別</label>
						<select id="searchSvcCd" name="phoneInfo.searchSvcCd" style="width:85px;">
                        	<option value="">全部</option>
		    				<gc:options group="US0052" codeType="full" value="${phoneInfo.searchSvcCd}" />
						</select>
				</td>
				<td rowspan="2" class="text_c" >
					<a class="btn" href="javascript:searchDeviceList();"><strong>搜尋</strong></a>
					<a class="btn" href="javascript:clearSrchCond();"><span>重新搜尋</span></a>
				</td>
			</tr>
			<tr>
				<th>搜尋關鍵字</th>
				<td class="align_td">
					<s:select list="result.selectMap" id="searchType" name="phoneInfo.searchType" style="width:104px;  vertical-align:middle;" />
					<input id="searchValue" type="text" name="phoneInfo.searchValue" class="txt" style="width:200px;  vertical-align:middle;" value="${phoneInfo.searchValue}" />
				</td>
			</tr>
			</tbody>
		</table>
		<table class="pop_tabletype02 mt25">
			<tr>
				<th>序號</th>
				<th>型號</th>
				<th>手機名稱</th>
				<th>手機編碼</th>
				<th>LCD Size</th>
				<th>OS版本</th>
				<th>服務類別</th>
			</tr>		
			<colgroup><col style="width:8%;"><col style="width:15%;"><col style="width:15%;"><col style="width:25%;"><col style="width:12%"><col style="width:10%"><col style="width:15%;"></colgroup>
			<tbody>
			<s:if test="srchFlag != 'TRUE'">
			<tr><td colspan='11'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.admin.common.first.page'/></span></td></tr>
			</s:if>
			<s:else>
				<s:if test="phoneInfoList.size == 0">
			<tr><td colspan='11'><span style="padding-top:3px;*padding-top:2px;">
				請選擇搜尋條件後點選［搜尋］鍵。 無資料.
				</span></td></tr>
				</s:if>
				<s:else>
				<c:set var="resultNum" value="${srchCnt - (phoneInfo.page.no-1)*10 }"/>
				<s:iterator value="phoneInfoList" status="status">
			<tr>
				<td>${resultNum}</td>
				<td><a href="javascript:setDeviceView('${mgmtPhoneModelNm}','${modelNm}','${gdcd}','${phoneModelCd}','${lcdSizeNm}','${vmVerNm}','${svcCdNm}');">${mgmtPhoneModelNm}</a></td>				
				<td>${modelNm}</td>
				<td>${phoneModelCd}</td>		
				<td>${lcdSizeNm}</td>					
				<td>${vmVerNm}</td>					
				<td>${svcCdNm}</td>	
			</tr>
				<c:set var="resultNum" value="${resultNum-1 }"/>
				</s:iterator>
				</s:else>
			</s:else>
			</tbody>
		</table>

		</s:form>
		<!-- paging -->
			<g:pageIndex item="${phoneInfoList}"/>
		<!-- //paging -->		
		<!-- //결과없는경우 -->
		<div class="pop_btn">
			<a href="javascript:window.close()" class="btn fr"><strong>關閉</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>