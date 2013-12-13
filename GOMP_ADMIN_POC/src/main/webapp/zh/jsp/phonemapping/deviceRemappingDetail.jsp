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
				首頁  &gt; 手機管理 &gt; 支援手機管理 &gt; <strong>手機與產品介接處理結果</strong>  
			</div><!-- //location -->

			<h1 class="fl pr10">手機與產品介接處理結果</h1>
			<p>可查看手機與產品介接處理結果。</p>
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
						<th colspan="2">處理狀態 : <gc:html code="${phoneRemMgr.mappingType}"/>, 手機型號 : ${phoneRemMgr.targetPhoneModelCd}, 進行狀態 : <gc:html code="${phoneRemMgr.mappingStat}"/></th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>
						<select id="subSearchType" name="searchParam.subSearchType" style="width:85px;">
                        	<option value="prodNm" <c:if test='${searchParam.subSearchType eq "prodNm" }'>selected</c:if>>產品名稱</option>
							<option value="mbrId" <c:if test='${searchParam.subSearchType eq "mbrId" }'>selected</c:if>>開發商帳號</option>
							<option value="aid" <c:if test='${searchParam.subSearchType eq "aid" }'>selected</c:if>>AID</option>
                        </select>
					</th>
					<td class="align_td">
						<input id="subSearchText" name="searchParam.subSearchText" value="${searchParam.subSearchText }" type="text" class="txt" style="width:70%;"/>
						<a class="btn" href="#" id="searchBtn"><strong>搜尋</strong></a>
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
					<th>序號</th>
					<th>產品名稱</th>
					<th>AID</th>
					<th>類別</th>
					<th>開發商</th>
					<th>販售狀態</th>
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
				<a class="btn" href="#" id="excelBtn" ><span>下載Excel檔</span></a>
				<a class="btn" href="#" id="deviceList"><span>目錄</span></a>
			</p>
			<!-- paging -->
			<g:pageIndex item="${phoneRemScidList}"/>
			<!-- //paging -->
</body>
</html>