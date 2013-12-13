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

		// search start ~ end date datepicker
		$( "#startDate" ).datepicker();
		$( "#endDate" ).datepicker();
		
		// today, weekly, monthly search btn action
		$(".searchDateBtn").click(function(event){
			event.preventDefault();
			var clickedId = this.id;
			
			// strong / span switch
			$(".searchDateBtn").each(function(){
				if(clickedId == this.id){
					$(this).html("<strong>"+$(this).text()+"</stong>");
					
				}else{
					$(this).html("<span>"+$(this).text()+"</span>");
				}
			});
			// searchDate Condition setting
			$(".searchDate").each(function(){
				if(clickedId == $(this).attr('rel')){
					$(this).val("Y");
				}else{
					$(this).val("");
				}
			});
			
			// startDate, endDate calculate (7day, .. , ..);
			setOrderSearchDateAdminPoC($(this).attr("rel"),form.startDate, form.endDate);
			// search
			funcSearch();
		});
		
		// startDate, endDate onChange Action
		$("#startDate, #endDate ").change(function(event){
			// strong / span switch
			$(".searchDateBtn").each(function(){
				$(this).html("<span>"+$(this).text()+"</span>");
			});
			// searchDate Condition setting
			$(".searchDate").each(function(){
				$(this).val("");
			});
		});
		
		// searchDateBtn init condition (onLoad apply)
		$(".searchDate").each(function(){
			var btnId = $(this).attr("rel");
			if($(this).val() == 'Y'){
				$("#"+btnId).html("<strong>"+$("#"+btnId).text()+"</stong>");
			}
		});
		
		// search btn
		$("#searchBtn").click(function(event){
			event.preventDefault();
			funcSearch();
		});
		
		// search init btn	
		$("#searchInitBtn").click(function(event){
			event.preventDefault();
			$.clearForm("searchForm", true, false);
			$("#searchWeekBtn").click();
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
	});
	
	function goPage(no) {
	    $("#no").val(no);
	    $("#searchBtn").click();
	}
	
	function funcSearch(){
		if(showValidate('searchForm', 'default', '')){
			document.searchForm.action="selectSaleStatDailyMainList.omp";
			document.searchForm.submit();
		}
	}
	
	function goDetail(saleDt){
		$("#saleDt").val(saleDt);
		document.searchForm.action="<c:url value="selectSaleStatDailyDetailList.omp"/>";
		document.searchForm.submit();
	}
	
	function excelDown() {
		if(showValidate('searchForm', 'default', '',excelCount)){
			document.searchForm.action="exportSaleStatDailyMainList.omp";
			document.searchForm.submit();
		}
	}
	var excelCount = {
   		"excelcnt" : function() {
  		if ("${totalCount}"  == 0) {
  			return false;
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
		首頁 &gt; 結算管理中心 &gt; 販售現狀 &gt; <strong>每日販售現狀</strong> 
	</div><!-- //location -->

	<h1 class="fl pr10">每日販售現狀</h1>
	<p>可查看每日販售現狀.</p>
	<form name="forexcel"><input type="hidden" name="excelCnt" value="${totalCount}" v:excelcnt m:excelcnt="<gm:string value='jsp.settlement.salestat.selectSaleStatDailyMainList.nodata'/>"/><!-- 엑셀 다운로드 메세지 사용 --></form>
	<s:form id="searchForm" name="searchForm" action="selectSaleStatDailyMainList" theme="simple" >
	<input type=hidden id=saleDt name=sub.saleDt>
	<input type="hidden" id="searchToday" name="sub.searchToday" value="${sub.searchToday }" class="searchDate" rel="searchTodayBtn" />
	<input type="hidden" id="searchWeek" name="sub.searchWeek" value="${sub.searchWeek }"  class="searchDate" rel="searchWeekBtn" />
	<input type="hidden" id="searchMonth" name="sub.searchMonth" value="${sub.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
	<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />			
	
	<table class="tabletype01">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
		<tr>
			<th>搜尋期間</th>
			<td class="align_td">
				<!-- <label for="startDate">搜尋期間</label>  -->
				<input id="startDate" name="sub.startDate" type="text" class="txt" style="width:80px;" value="${sub.startDate }" readonly v:required m:required="<gm:string value='jsp.settlement.salestat.selectSaleStatDailyMainList.inputstartdate'/>" /> ~ 
				<input id="endDate" name="sub.endDate" type="text" class="txt" style="width:80px;" value="${sub.endDate }" readonly v:required m:required="<gm:string value='jsp.settlement.salestat.selectSaleStatDailyMainList.inputenddate'/>" v:scompare="ge,@{sub.startDate}" m:scompare="<gm:string value='jsp.settlement.salestat.selectSaleStatDailyMainList.checkdate'/>"/> 
				<a class="btn_s searchDateBtn" href="#" id="searchTodayBtn" rel="today"><span>今日</span></a>
				<a class="btn_s searchDateBtn" href="#" id="searchWeekBtn" rel="7day"><span>最近一周</span></a>
				<a class="btn_s searchDateBtn" href="#" id="searchMonthBtn" rel="1month"><span>最近一個月</span></a>
			</td>
			<td rowspan="2" class="text_c" >
				<a class="btn" href="#" id="searchBtn"><strong>搜尋</strong></a>
			</td>
		</tr>
		</table>
		</s:form>
		<p class="fl mt25"> ▶ 每日販售現狀(${sub.startDate } ~ ${sub.endDate })</p>  	
		<p class="fr mt20 mb05"><a class="btn" href="#" id="excelDownBtn"><span>下載Excel 檔</span></a></p>		
		<!-- 리스트 시작 -->
		<table class="tabletype02" id="contsTable">
			<colgroup>
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
				<th rowspan="2">年 / 月</th>
				<th colspan="3">銷售記錄</th>
				<th colspan="2">取消筆數</th>
				<th colspan="3">付款方式</th>
				<th rowspan="2">總計</th>
				<th rowspan="2">詳細內容</th>
			</tr>
			<tr>
				<th>銷售金額</th>
				<th>付費</th>
				<th>免費</th>
				<th>筆數</th>
				<th>金額</th>
				<th>信用卡</th>
				<th>手機</th>
				<th>Whoopy幣</th>
		    </tr>
		    </thead>
			<tbody>
			<c:choose>
			<c:when test="${totalCount eq 0 }">
			<tr>
				<td colspan="11" class="text_c"><gm:html value="jsp.product.noSearchMsg"/></td>
			</tr>
			</c:when>
			<c:otherwise>
			<c:forEach items="${list }" var="salestat">
			<tr>
				<td>${salestat.saleDt}</td>
				<td><g:text value="${salestat.saleAmt}" format="#,###,###,###,###,###,###,###"/></td>
				<td><g:text value="${salestat.chargedDwnlCnt}" format="#,###"/></td>
				<td><g:text value="${salestat.freeDwnlCnt}" format="#,###"/></td>
				<td><g:text value="${salestat.cancelDwnlCnt}" format="#,###"/></td>
				<td><g:text value="${salestat.cancelAmt}" format="#,###,###,###,###,###,###,###"/></td>
				<td><g:text value="${salestat.cardDwnlCnt}" format="#,###"/></td>
				<td><g:text value="${salestat.phoneDwnlCnt}" format="#,###"/></td>
				<td><g:text value="${salestat.cashDwnlCnt}" format="#,###"/></td>
				<td><g:text value="${salestat.subAmt}" format="#,###,###,###,###,###,###,###"/></td>
				<td><a class="btn_s" href="javascript:goDetail('${salestat.saleDt}');"><span>詳細內容</span></a></td>
			</tr>
			</c:forEach>
			</c:otherwise>
			</c:choose>
			</tbody>
		</table>	
		<!-- paging -->
		<div align="center">
		       <g:pageIndex item="${list}"/>
		</div>
		<!-- //paging -->
</body>
</html>