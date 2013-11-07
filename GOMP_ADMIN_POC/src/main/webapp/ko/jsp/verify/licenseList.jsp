<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
$(document).ready(function(){
	$("#startDate").datepicker();
	$("#endDate").datepicker();	
	
	if('${verifyLicense.startDate}' == "") {
		
		var startDate = document.getElementById("startDate");
		var endDate = document.getElementById("endDate");
		
		setOrderSearchDateAdminPoC("7day", startDate, endDate);
	}
});

function init(){
	var startDate = document.getElementById("startDate");
	var endDate = document.getElementById("endDate");
	setOrderSearchDateAdminPoC("7day", startDate, endDate);
	$("#keyType option:first").attr("selected", "true");
	$("#keyWord").attr("value","");
}

//Search Date
function search_date(term) {
	var startDate = document.getElementById("startDate");
	var endDate = document.getElementById("endDate");
	
	setOrderSearchDateAdminPoC(term, startDate, endDate);
}

function goPage(no) {
	$("form[name=licenseForm] input[name=verifyLicense\\.page\\.no]").val(no);
	selectLicenseList();
}

function selectLicenseList(){
	if(showValidate('licenseForm', 'default', '')){
		document.licenseForm.action="licenseList.omp";
		document.licenseForm.submit();
	}
}

function registerLicense(){
	document.licenseForm.action="registerLicense.omp";
	document.licenseForm.submit();
}

function detailLicense(seq){
	document.licenseForm.seqOta.value=seq;
	document.licenseForm.action="detailLicense.omp";
	document.licenseForm.submit();
}

$(function() {
	$("#keyType").change(function() {
		$("#keyWord").attr("value", "");
	});
});
</script>
</head>
<body>
<s:form id="licenseForm" name="licenseForm" theme="simple" method="post">
<input type="hidden" name="verifyLicense.page.no" value="${verifyLicense.page.no}"/>
<input type="hidden" id="seqOta" name="seqOta"/>
<div id="location">
				Home &gt; 단말관리 &gt; 검증툴 License 관리 &gt; <strong>검증툴 License 관리</strong> 
			</div><!-- //location -->
 
			<h1 class="fl pr10">검증툴 License 관리</h1>
			<p>검증툴 License를 발급하거나 발급 내역을 확인할 수 있습니다.</p>
			<table class="tabletype01">

				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>발급일</th>
					<td class="align_td">					
						<!-- <label for="#" style="width:60px;">가입일</label> -->
						<input id="startDate" name="verifyLicense.startDate" type="text" class="txt" style="width:80px;" value="<g:text value='${verifyLicense.startDate }' format='L####-##-##'/>" readonly/> ~ 
						<input id="endDate" name="verifyLicense.endDate" type="text" class="txt" style="width:80px;" value="<g:text value='${verifyLicense.endDate }' format='L####-##-##'/>" readonly v:scompare="ge,@{verifyLicense.startDate}" m:scompare="<gm:tagAttb value='jsp.community.qna.qna_list.msg.datechk'/>"/>
						<a class="btn_s" href="javascript:search_date('today');"><span>오늘</span></a> 
						<a class="btn_s" href="javascript:search_date('7day');"><strong>최근1주</strong></a> 
						<a class="btn_s" href="javascript:search_date('1month');"><span>최근1개월</span></a>
					</td>
					<td rowspan="2" class="text_c" >
						<a class="btn" href="javascript:selectLicenseList();"><strong>검색</strong></a>
						<a class="btn" href="javascript:init();"><span>검색초기화</span></a>
					</td>
				</tr>
				<tr>
					<th>검색어</th>
					<td class="align_td">
                        <select id="keyType" name="verifyLicense.keyType" style="width:104px;">
                        	<option value="id">발급자ID</option>
							<option value="mac">Wifi MAC</option><!-- 2011-03-24 -->
                        </select>
						<input id="keyWord" name="verifyLicense.keyWord" value="${verifyLicense.keyWord }" type="text" class="txt" style="width:200px;" />
					</td>
				</tr>
			</table>
			<p class="mt20">총  <strong class="point2"><g:html value="${licenseCnt }" /></strong>개의 검증툴 License 발급내역이 존재합니다.</p>
			<table class="tabletype02">
				<colgroup>
				<c:choose>
				<c:when test="${licenseCnt > 0}">
					<col >
					<col >
					<col >
					<col >
				</c:when>
				<c:otherwise><col style="width:8%;" ></c:otherwise>
			</c:choose>
				</colgroup>
				<thead>
				<tr>
					<th>번호</th>
					<th>Wifi MAC</th>
					<th>유효기간</th>
					<th>발급자 ID</th>
					<th>발급일</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${licenseCnt > 0}">
					<c:forEach items="${verifyLicenseList}" var="item" varStatus="cnt">
						<tr>
							<td><g:html value="${item.totalCount - item.rnum + 1}" /></td>
							<td><a href="javascript:detailLicense('${item.seqOta }');"><g:html value="${item.macAddr }" format="L####################..."/></a></td>
							<td><g:text value='${item.validFrdt }' format="L####\-##\-##"/> ~ 
								<g:text value='${item.validTodt }' format="L####\-##\-##"/>
							</td>
							<td><g:html value="${item.insId}" /></td>
							<td><g:html value="${item.issueDttm}" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="5" class="text_c">${searcheck}</td>
				</c:otherwise>
			</c:choose>
				</tbody>
			</table>
			<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:registerLicense();"><span>신규발급</span></a></p>
			<!-- paging -->
			<div align="center">
				<g:pageIndex item="${verifyLicenseList}"/>
			</div>
			<!-- //paging -->
		</s:form>
</body>
</html>