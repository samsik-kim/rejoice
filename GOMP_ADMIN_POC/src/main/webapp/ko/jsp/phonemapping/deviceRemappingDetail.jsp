<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!-- <link rel="stylesheet" type="text/css" href=""/> --> 
<script type="text/javascript" language="javascript">
//<![CDATA[
$(document).ready(function(){
	//goList
	$("#deviceList").click(function(event){
		event.preventDefault();
		document.searchForm.action = "<c:url value="/phonemapping/deviceRemappingList.omp"/>";
		document.searchForm.submit();
	});
	
	// search btn
	$("#searchBtn").click(function(event){
		event.preventDefault();
		funcSearch();
	});
	
	$("#excelBtn").click(function(event){
		if('${totalCount}' == '0'){
			alert('<gm:string value="jsp.product.noSearchMsg"/>');
			return;
		}
		document.searchForm.action="<c:url value="/phonemapping/excelDetailList.omp"/>";
		document.searchForm.submit();
	});
	
	// searchType change Action
	$("#subSearchType").change(function (e){
		$("#subSearchText").val('');
	});
	
	// searchText Enter
	$("#subSearchText").keypress(function (e){
		if(e.which == 13){ 
			funcSearch();
		}
	});
});


function goList(){
	document.searchForm.action="deviceRemappingList.omp";
	document.searchForm.submit();
}

function funcSearch(){
	document.searchForm.action="deviceRemappingDetail.omp";
	document.searchForm.submit();
}

function goPage(no) {
    $("#no").val(no);
    funcSearch();
}

//]]>
</script>
</head>
<body>
			<div id="location">
				Home &gt; 단말관리 &gt; 서비스단말관리 &gt; <strong>단말 Mapping 처리결과</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">단말 Mapping 처리결과</h1>
			<p>대상단말 관리 처리결과 입니다.</p>
			<s:form id="searchForm" name="searchForm" action="deviceRemappingDetail" theme="simple" method="get">
			<input type="hidden" id="txId" name="searchParam.txId" value="${searchParam.txId }"/>
			<input type="hidden" id="searchToday" name="searchParam.searchToday" value="${searchParam.searchToday }" class="searchDate" rel="searchTodayBtn" />
			<input type="hidden" id="searchWeek" name="searchParam.searchWeek" value="${searchParam.searchWeek }"  class="searchDate" rel="searchWeekBtn" />
			<input type="hidden" id="searchMonth" name="searchParam.searchMonth" value="${searchParam.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
			<input type="hidden" id="no" name="searchParam.page.no" value="${searchParam.page.no }" />
			<input type="hidden" id="masterNo" name="searchParam.masterNo" value="${searchParam.masterNo }" />
			<input type="hidden" id="targetPhoneModelCd" name="searchParam.targetPhoneModelCd" value="${ searchParam.targetPhoneModelCd}" />
			<input type="hidden" id="standardPhoneModelCd" name="searchParam.standardPhoneModelCd" value="${searchParam.standardPhoneModelCd }" />
			<input type="hidden" id="mappingType" name="searchParam.mappingType" value="${searchParam.mappingType }" />
			<input type="hidden" id="searchType" name="searchParam.searchType" value="${searchParam.searchType }" />
			<input type="hidden" id="searchText" name="searchParam.searchText" value="${searchParam.searchText }" />
			<input type="hidden" id="mappingStat" name="searchParam.mappingStat" value="${searchParam.mappingStat }" />
			<input type="hidden" id="startDate" name="searchParam.startDate" value="${searchParam.startDate }" /> 
			<input type="hidden" id="endDate" name="searchParam.endDate" value="${searchParam.endDate }" />
			
			<table class="tabletype01">
				<colgroup>
					<col style="width:20%;">
					<col style="width:80%;">
				</colgroup>
				<thead>
					<tr>
						<th colspan="2">처리기능 : <gc:html code="${phoneRemMgr.mappingType}"/>, 대상단말 : ${phoneRemMgr.targetPhoneModelCd}, 진행상태 : <gc:html code="${phoneRemMgr.mappingStat}"/></th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>
						<select id="subSearchType" name="searchParam.subSearchType" style="width:85px;">
                        	<option value="prodNm" <c:if test='${searchParam.subSearchType eq "prodNm" }'>selected</c:if>>상품명</option>
							<option value="mbrId" <c:if test='${searchParam.subSearchType eq "mbrId" }'>selected</c:if>>개발자ID</option>
							<option value="aid" <c:if test='${searchParam.subSearchType eq "aid" }'>selected</c:if>>AID</option>
                        </select>
					</th>
					<td class="align_td">
						<input id="subSearchText" name="searchParam.subSearchText" value="${searchParam.subSearchText }" type="text" class="txt" style="width:70%;"/>
						<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
					</td>
				</tr>
				</tbody>
			</table>
			</s:form>
			<table class="tabletype02 mt25">
				<colgroup>
					<col >
					<col >
					<col >
					<col >
					<col >
					<col >
				</colgroup>
				<thead>
				<tr>
					<th>번호</th>
					<th>상품명</th>
					<th>AID</th>
					<th>분류체계</th>
					<th>개발자</th>
					<th>판매상태</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${totalCount eq 0 }">
				<tr>
					<td colspan="6" class="text_c"><gm:html value="jsp.product.noSearchMsg"/></td>
				</tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${phoneRemScidList }" var="phoneRemScid">
				<tr>
					<td>${phoneRemScid.totalCount - phoneRemScid.rnum + 1}</td>
					<td>${phoneRemScid.prodNm }</td>
					<td>${phoneRemScid.aid }</td>
					<td>${phoneRemScid.ctgrNm2 }</td>
					<td>${phoneRemScid.mbrId }</td>
					<td><gc:html code="${phoneRemScid.saleStat}"/></td>
				</tr>
				</c:forEach>
				</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<p class="btn_wrap text_r mt25">
				<a class="btn" href="#" id="excelBtn" ><span>Excel 다운로드</span></a>
				<a class="btn" href="#" id="deviceList"><span>목록</span></a>
			</p>
			<!-- paging -->
			<g:pageIndex item="${phoneRemScidList}"/>
			<!-- //paging -->
</body>
</html>