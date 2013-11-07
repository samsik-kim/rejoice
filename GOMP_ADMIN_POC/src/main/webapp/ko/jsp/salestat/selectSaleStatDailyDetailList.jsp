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
<style type="text/css">
form{clear:both;}
</style>
<script type="text/javascript">
//<![CDATA[
	$(function() {
		var form = document.searchForm;
		
		
		// search btn
		$("#searchBtn").click(function(event){
			event.preventDefault();
			funcSearch();
		});
		
		// excel download btn 
		$("#excelDownBtn").click(function(event){
			event.preventDefault();
			/*
			if (${totalCount}  == 0) {
				alert('검색결과가 없습니다.');
				return;
			}
			excelDown();
			*/
			if(showValidate('forexcel', 'default', '',excelCount)){
				excelDown();
			}
		});
				
		// listBtn btn
		$("#listBtn").click(function(event){
			event.preventDefault();
			goList();
		});
		
	});
	
	function goPage(no) {
	    $("#no").val(no);
	    $("#searchBtn").click();
	}
	
	function funcSearch(){
		if(showValidate('searchForm', 'default', '')){
			document.searchForm.action="selectSaleStatDailyDetailList.omp";
			document.searchForm.submit();
		}
	}
	
	//일별 판매 현황 
 	function goList(){
 		document.searchForm.action="selectSaleStatDailyMainList.omp";
 		document.searchForm.submit();
 	}
	
	function excelDown() {
		if(showValidate('searchForm', 'default', '')){
			document.searchForm.action="exportSaleStatDailyDetailList.omp";
			document.searchForm.submit();
		}
	}
	
	function selType_change(){
		$("#searchText").val("");
	}
	
	var excelCount = {
		"excelcnt" : function() {
			if ("${totalCount}"  == 0) {
				return false;
				//alert("<gm:string value='검색결과가 없습니다.'/>");
				//return;
			}else{
				return true;
			}       
		}
	};
	
//]]>
</script>
	</head>
<body>
	<div id="location">
		Home &gt; 정산관리 &gt; 판매현황 &gt; <strong>일별판매현황</strong> 
	</div><!-- //location -->
	<s:form id="searchForm" name="searchForm" theme="simple" >
		<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />		
		<input type="hidden" id="saleDt" name="sub.saleDt" value="${sub.saleDt }" />
		<input type="hidden" id="startDate" name="sub.startDate" value="${sub.startDate }"/> 
		<input type="hidden" id="endDate" name="sub.endDate" value="${sub.endDate }" />
		
	<h1 class="fl pr10">일별판매현황</h1>
	<p>일별판매현황을 조회 합니다.</p>
	<table class="tabletype01">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
		<tr>
			<th>검색어</th>
			<td class="align_td">
				<select id="searchType" name="sub.searchType"  onchange="javascript:selType_change();">
				   	<option value="devId" <c:if test='${sub.searchType eq "devId" }'>selected</c:if>>개발자ID</option>
				   	<option value="prd" <c:if test='${sub.searchType eq "prd" }'>selected</c:if>>상품명</option>
                 </select>
				<input type="text" class="txt" id="searchText" name="sub.searchText" style="width:250px;" value="${sub.searchText }"/>
			</td>
			<td rowspan="6" class="text_c" >
			<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
			</td>
		</tr>
	</table>
	<p class="fl mt25">▶${sub.saleDt } 판매 상세 현황</p>
	<p class="fr mt25 mb05"><a class="btn" href="#" id="excelDownBtn"><span>EXCEL다운로드</span></a></p>
	</s:form>
	<table class="tabletype02">
		<colgroup>
			<col style="width:30px;" >
			<col >
			<col >
			<col >
			<col >
			<col >
			<col >
			<col >
			<col >
			<col >
			<col >
			<col >
		</colgroup>
		<thead>
		<tr>
			<th rowspan="2">번호</th>
			<th rowspan="2">개발자ID(이름)</th>
			<th rowspan="2">상품명</th>
			<th colspan="3">판매건수</th>
			<th colspan="2">취소건수</th>
			<th colspan="3">결제수단</th>
			<th rowspan="2">소계</th>
		</tr>
		<tr>
			<th>판매금액</th>
			<th>유료</th>
			<th>무료</th>
			<th>건수</th>
			<th>금액</th>
			<th>신용카드</th>
			<th>휴대폰</th>
			<th>캐쉬</th>
		</tr>
		</thead>
		<tbody>
		<c:choose>
		<c:when test="${totalCount eq 0 }">
		<tr>
			<td colspan="12" class="text_c"><gm:html value="jsp.product.noSearchMsg"/></td>
		</tr>
		</c:when>
		<c:otherwise>
		<c:forEach items="${list }" var="salestat">
		<tr>
			<td>${salestat.totalCount - salestat.rnum + 1}</td>
			<td>${salestat.memNameId}</td>
			<td>${salestat.prdName}</td>
			<td><g:text value="${salestat.saleAmt}" format="#,###,###,###,###,###,###,###"/></td>
			<td><g:text value="${salestat.chargedDwnlCnt}" format="#,###"/></td>
			<td><g:text value="${salestat.freeDwnlCnt}" format="#,###"/></td>
			<td><g:text value="${salestat.cancelDwnlCnt}" format="#,###"/></td>
			<td><g:text value="${salestat.cancelAmt}" format="#,###,###,###,###,###,###,###"/></td>
			<td><g:text value="${salestat.cardDwnlCnt}" format="#,###"/></td>
			<td><g:text value="${salestat.phoneDwnlCnt}" format="#,###"/></td>
			<td><g:text value="${salestat.cashDwnlCnt}" format="#,###"/></td>
			<td><g:text value="${salestat.subAmt}" format="#,###,###,###,###,###,###,###"/></td>
		</tr>
		</c:forEach>
		</c:otherwise>
		</c:choose>
		</tbody>
		</table>	
		<p class="btn_wrap text_r mt05"><a class="btn" href="#" id="listBtn"><span>목록</span></a></p>
		<!-- paging -->
		<div align="center">
		       <g:pageIndex item="${list}"/>
		</div>
		<!-- //paging -->
</body>
</html>