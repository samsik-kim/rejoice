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
<title>단말검색</title>
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
		//alert(mgmtPhoneModelNm + ":" + modelNm + ":" + gdcd + ":" + phoneModelCd + ":" + lcdSizeNm + ":" + vmVerNm + ":" + svcCdNm);
	};	

</script>

	<div id="popup_area_810">
		<h1>단말검색 <span class="h1_sub"> 모델명을 클릭하시면 해당 단말이 화면에 적용됩니다.</span></h1>
		
		<s:form action="popDeviceSearching" id="searchDeviceForm" theme="simple">
			<s:hidden id="srchFlag" name="srchFlag" value="TRUE"/>
			<s:hidden id="page.no" name="phoneInfo.page.no" value="%{phoneInfo.page.no}"/>
			<s:hidden id="phoneModelCd" name="phoneInfo.phoneModelCd" />
					
		<table class="pop_tabletype01">
			<colgroup><col style="width:20%;"><col style="width:80%"></colgroup>
			<tbody>
			<tr>
				<th>검색조건</th>
				<td class="align_td">					
					<label for="#">LCD크기</label>
						<select id="searchLcdSize" name="phoneInfo.searchLcdSize" style="width:85px;" >
                        	<option value="">전체</option>
		    				<gc:options group="PD0021" codeType="full" value="${phoneInfo.searchLcdSize}" />
		    			</select>

					<label for="#">지원OS</label>
						<select id="searchVmVer" name="phoneInfo.searchVmVer" style="width:85px;">
                        	<option value="">전체</option>
		    				<gc:options group="PD0091" codeType="full" filter="support" value="${phoneInfo.searchVmVer}" />
						</select>

					<label for="#">서비스 구분</label>
						<select id="searchSvcCd" name="phoneInfo.searchSvcCd" style="width:85px;">
                        	<option value="">전체</option>
		    				<gc:options filter="US0052" group="US0052" codeType="full" value="${phoneInfo.searchSvcCd}" />
						</select>
				</td>
				<td rowspan="2" class="text_c" >
					<a class="btn" href="javascript:searchDeviceList();"><strong>검색</strong></a>
					<a class="btn" href="javascript:clearSrchCond();"><span>검색초기화</span></a>
				</td>
			</tr>
			<tr>
				<th>검색어</th>
				<td class="align_td">
					<s:select list="result.selectMap" id="searchType" name="phoneInfo.searchType" style="width:104px;  vertical-align:middle;" />
					<input id="searchValue" type="text" name="phoneInfo.searchValue" class="txt" style="width:200px;  vertical-align:middle;" value="${phoneInfo.searchValue}" />
				</td>
			</tr>
			</tbody>
		</table>
		<table class="pop_tabletype02 mt25">
			<tr>
				<th>번호</th>
				<th>모델명</th>
				<th>단말명칭</th>
				<th>단말코드</th>
				<th>LCD크기</th>
				<th>지원OS</th>
				<th>서비스 구분</th>
			</tr>		
			<colgroup><col style="width:8%;"><col style="width:15%;"><col style="width:15%;"><col style="width:25%;"><col style="width:12%"><col style="width:10%"><col style="width:15%;"></colgroup>
			<tbody>
			<s:if test="srchFlag != 'TRUE'">
			<tr><td colspan='11'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.admin.common.first.page'/></span></td></tr>
			</s:if>
			<s:else>
				<s:if test="phoneInfoList.size == 0">
			<tr><td colspan='11'><span style="padding-top:3px;*padding-top:2px;">
				검색조건을 선택하신 후 "검색" 버튼을 클릭하세요.
				<!-- <gm:string value='jsp.device.servicedevice.serviceDeviceList.msg.none_result'/> -->
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
			<a href="javascript:window.close()" class="btn fr"><strong>닫기</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>