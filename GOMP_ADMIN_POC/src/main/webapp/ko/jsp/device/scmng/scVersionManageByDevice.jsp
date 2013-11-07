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
});

function doClear() {
	$.clearForm("sform");
	$("form[name=sform] input[name=sc\\.svcCd][value=US005202]").attr("checked", true);
	$("form[name=sform] input[name=sc\\.svcCd][value=US005203]").attr("checked", true);
}

function doSearch() {
	if (showValidate("sform", "default", "검색 조건 오류")) {
		document.sform.submit();
	}
}

function selectDevice(tobj) {
	$(".selectDeviceTr").css("background", "");
	$(tobj).parents(".selectDeviceTr").css("background", "#FFFFB0");
	doSearchCoreAppList();
}

function selectAppType(tobj) {
	doSearchCoreAppList();
}


function doSearchCoreAppList() {
	var	device;
	var	appType;
	
	device	= $("input[name=selectedDevice]:checked");
	appType	= $("input[name=selectedAppType]:checked");
	if (device.length == 1 && appType.length == 1) {
		$("#workArea").block({ message: '<gm:string value="조회 중입니다."/>' });
		$("input[name=selectedDevice]").attr("disabled", "disabled");
		$("input[name=selectedAppType]").attr("disabled", "disabled");
		$.ajax("<c:url value="/device/ajaxScVersionListByDevice.omp"/>",
			{
				  async    : true
				, cache    : false
				, data     : {"csc.platform" : "DP002906", "csc.device" : device.val(), "csc.appType" : appType.val()}
				, dataType : "html"
				, success  : function(data) {$("#divCoreApp").html(data);}
				, complete : function() {
								$("input[name=selectedDevice]").removeAttr("disabled");
								$("input[name=selectedAppType]").removeAttr("disabled");
								$("#workArea").unblock();
							}
			}
		);
	}
}

function setTestVersion(coreappId) {
	setSupportDeviceStatus(coreappId, "DP005402");
}

function setProdVersion(coreappId, isConfirm) {
	setSupportDeviceStatus(coreappId, "DP005403", isConfirm);
}

function setSupportDeviceStatus(coreappId, verStatus, isConfirm) {
	var	device;
	var appType;
	
	if (isConfirm && !confirm("<gm:string value="테스트 중인 버전이 아닙니다. 테스트 없이 바로 상용 버전으로 설정 하시겠습니까?"/>")) {
		return;
		
	}
	
	device	= $("input[name=selectedDevice]:checked");
	appType	= $("input[name=selectedAppType]:checked");
	if (device.length == 1 && appType.length == 1) {
		$.blockUI({ message: "<gm:string value="처리 중 입니다."/>"});
		$.ajax("<c:url value="/device/ajaxScVersionSetStatus.omp"/>",
				{
					  async    : true
					, cache    : false
					, data     : {"support.coreappId" : coreappId, "support.phoneModelCd" : device.val(), "support.verStatus" : verStatus}
					, dataType : "html"
					, success  : function(data) {$("#divDummy").html(data);}
					, complete : function() {
									$("input[name=selectedDevice]").removeAttr("disabled");
									$("input[name=selectedAppType]").removeAttr("disabled");
									$.unblockUI();
								}
				}
		);
	}
}

</script>
</head>
<body>
	<div id="location">
		Home &gt; 단말관리 &gt; <strong>단말기별 버전관리</strong> 
	</div><!-- //location -->

	<h1 class="fl pr10 mb0">단말기별 버전관리</h1>
	<p>단말기 별 Shop Client의 버전을 관리 할 수 있습니다.</p>

	<form name="sform" action="scVersionManageByDevice.omp">
	<input type="hidden" name="sc.vmType" value="PD005606"/>
	<table class="tabletype01 mt20">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
		<tr>
			<th>단말 명칭</th>

			<td class="align_td">
				<input name="sc.modelNm" type="text" class="txt" style="width:80px;" value="<g:tagAttb value="${sc.modelNm}"/>"/>
			</td>
			<th>단말 생산자</th>

			<td class="align_td">
				<select name="sc.makeComp">
					<option value="">전체</option>
					<gc:options group="PD0040" value="${sc.makeComp}"/>
				</select>
			</td>

			<td rowspan="3" class="text_c" >
				<a class="btn" href="javascript:doSearch()"><strong>검색</strong></a>
				<a class="btn" href="javascript:doClear()"><span>검색초기화</span></a>
			</td>
		</tr>
		<tr>
			<th>지원 OS</th>
			<td class="align_td">
				<select name="sc.vmVer">
					<option value="">전체</option>
					<gc:options group="PD0091" value="${sc.vmVer}"/>
				</select>
			</td>
			<th>서비스구분</th>

			<td class="align_td">
				<gc:checkBoxs name="sc.svcCd" group="US0052" value="${sc.svcCd}"/>
			</td>
		</tr>
		<tr>
			<th>삭제단말</th>

			<td class="align_td">
				<input name="sc.includeDeletedDevice" type="checkbox" value="true" ${sc.includeDeletedDevice ? "checked" : "" }>삭제단말 포함
			</td>
			<th>버전 상태</th>

			<td class="align_td">
				<gc:checkBoxs name="sc.verStatus" group="DP0054" value="${sc.verStatus}"/>
			</td>
		</tr>
	</table>
	</form>
	<div id="workArea">
	<table width="100%" cellspacing="0" cellpadding="0">
	<tr>
		<td>
			<g:size var="deviceListSize" item="${deviceList}"/>
			<p class="fl mt20">총 <g:html value="${deviceListSize}"/>개 단말기가 발견 되었습니다.</p>
		</td>
	</tr>
	<tr>
		<td>
			<div class="scroll">
			<table class="tabletype02" style="width:100%; border:1px solid #ddd;">
		
				<colgroup>
					<col width="50">
					<col >
					<col >
					<col >
					<col >
					<col >
					<col >
				</colgroup>
		
				<thead>
				<tr>
					<th>-</th>
					<th>단말 명칭</th>
					<th>모델명</th>
					<th>지원 OS</th>
					<th>LCD Size</th>
					<th>서비스</th>
					<th>삭제</th>
				</tr>
				</thead>
				<tbody>
<c:choose>
	<c:when test="${empty deviceList}">
				<tr>
					<td colspan="7">검색 결과가 없습니다.</td>
				</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${deviceList}" var="device">
				<tr class="selectDeviceTr">
					<td><input name="selectedDevice" type="radio" value="<g:tagAttb value="${device.phoneModelCd}"/>" onclick="selectDevice(this)"/></td>
					<td class="text_l"><g:html value="${device.modelNm}"/></td>
					<td class="text_l"><g:html value="${device.mgmtPhoneModelNm}"/></td>
					<td><gc:html code="${device.vmVer}"/></td>
					<td><gc:html code="${device.lcdSize}"/></td>
					<td><gc:html code="${device.svcCd}"/></td>
					<td><g:html value="${device.delYn eq 'Y' ? 'Yes' : 'No'}"/></td>
				</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>
				</tbody>
			</table>
			</div>
		</td>
	</tr>
	<tr>
		<td>
			<p class="fl mt20">
				설정할 SC의 유형을 선택 하십시오. :
				<gc:radioButtons name="selectedAppType" group="DP0028" divide="&nbsp;&nbsp;&nbsp;&nbsp;">
					<g:param name="extra">
						onclick="selectAppType(this)"
					</g:param>
				</gc:radioButtons>
			</p>
		</td>
	</tr>
	<tr>
		<td>
			<div id="divCoreApp" class="scroll">
			<table class="tabletype02" style="width:100%; border:1px solid #ddd;">
				<colgroup>
					<col width="40">
					<col width="40">
					<col >
					<col >
					<col >
					<col >
				</colgroup>
		
				<thead>
				<tr>
					<th>-</th>
					<th>-</th>
					<th>버전</th>
					<th>파일명</th>
					<th>등록일</th>
					<th>업데이트내용</th>
				</tr>
				</thead>
				<tbody>
				<tr>
					<td colspan="6">단말기와 SC의 유형을 선택하십시오.</td>
				</tr>
				</tbody>
			</table>
			</div>
		</td>
	</tr>
	</table>
	</div>
	<div id="divDummy" style="dispay:none"></div>
</body>
</html>