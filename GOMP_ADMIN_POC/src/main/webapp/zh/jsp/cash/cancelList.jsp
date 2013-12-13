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
			if (${totalCount}  == 0) {
				alert("<gm:string value='jsp.cash.cash_list.msg.noresult'/>");
				return;
			}
			excelDown();
		});
	});
	
	function goPage(no) {
	    $("#no").val(no);
	    $("#searchBtn").click();
	}
	
	function funcSearch(){
		if(showValidate('searchForm', 'dialog', '<gm:string value="jsp.cash.cash_list.msg.validate"/>')){
			document.searchForm.action="cancelList.omp";
			document.searchForm.submit();
		}
	}
	
	function goDetail(prchsDt){
		$("#prchsDt").val(prchsDt);
		document.searchForm.action="<c:url value="cancelListDetail.omp"/>";
		document.searchForm.submit();
	}
	
	function excelDown() {
		if(showValidate('searchForm', 'dialog', '<gm:string value="jsp.cash.cash_list.msg.validate"/>')){
			document.searchForm.action="cancelListExcel.omp";
			document.searchForm.submit();
		}
	}
	
	function goRefund(){
		
		width = 440;
		height = 320;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		scrollOption = "Yes";
	
		var url = "popRefund.omp";
		
		window.open(url,"popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");		
						
	}
	
//]]>
</script>
	</head>
<body>
	<div id="location">
		首頁  &gt; 產品管理中心  &gt;  Whoopy幣管理 &gt <strong>取消紀錄</strong> 
	</div><!-- //location -->
	<h1 class="fl pr10">取消紀錄</h1>
	<p>可查看取消之Whoopy幣之現狀。</p>
	<s:form id="searchForm" name="searchForm" action="cancelList" theme="simple">
	<input type="hidden" id="prchsDt" name="sub.prchsDt" value="" /> 
	<input type="hidden" id="searchToday" name="sub.searchToday" value="${sub.searchToday }" class="searchDate" rel="searchTodayBtn" />
	<input type="hidden" id="searchWeek" name="sub.searchWeek" value="${sub.searchWeek }"  class="searchDate" rel="searchWeekBtn" />
	<input type="hidden" id="searchMonth" name="sub.searchMonth" value="${sub.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
	<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />
		
	<table class="tabletype01">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
		<tr>
			<th>搜尋期間</th>
			<td class="align_td">
				<input id="startDate" name="sub.startDate" type="text" class="txt" style="width:80px;" value="${sub.startDate }" readonly v:required m:required='<gm:tagAttb value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.list.search.startDate"/></gm:arg></gm:tagAttb>' /> ~ 
				<input id="endDate" name="sub.endDate" type="text" class="txt" style="width:80px;" value="${sub.endDate }" readonly v:required m:required='<gm:tagAttb value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.list.search.endDate"/></gm:arg></gm:tagAttb>' v:scompare="ge,@{sub.startDate}" m:scompare='<gm:tagAttb value="jsp.list.validate.wrongDate"/>'/> 
				<a class="btn_s searchDateBtn" href="#" id="searchTodayBtn" rel="today"><span>今日</span></a>
				<a class="btn_s searchDateBtn" href="#" id="searchWeekBtn" rel="7day"><span>最近一周</span></a>
				<a class="btn_s searchDateBtn" href="#" id="searchMonthBtn" rel="1month"><span>最近一個月</span></a>
			</td>
			<td class="text_c" >
				<a class="btn" href="#" id="searchBtn"><strong>搜尋</strong></a>
			</td>
		</tr>
	</table>
	</s:form>	
	<p class="fl mt35">每日取消現狀(${sub.startDate} ~ ${sub.endDate})</p>			
	<p class="fr mt25 mb05">
		<a class="btn" href="#" onclick="javascript:goRefund();"><span>扣除Whoopy幣</span></a> 
		<a class="btn" href="#" id="excelDownBtn"><span>下載Excel檔</span></a>
	</p>

	<!-- 취소내역 리스트 시작 -->
	<table class="tabletype02">
		<colgroup>
			<col >
			<col >
			<col >
			<col >
		</colgroup>
		<thead>
		<tr>
			<th>日期</th>
			<th>Whoopy幣取消額(P)</th>
			<th>取消筆數</th>
			<th>詳細查看</th>
		</tr>                                
		</thead>
		<tbody>
		<c:choose>
		<c:when test="${totalCount eq 0 }">
		<tr>
				<td colspan="4" class="text_c"><gm:html value='jsp.cash.cash_list.msg.noresult'/></td>
		</tr>
		</c:when>
		<c:otherwise>
		<c:forEach items="${list }" var="charge">
		<tr>
			<td>${charge.prchsDt}</td>
			<td><g:html format="R###,###,###">${charge.cashAmt}</g:html></td>
			<td>${charge.cancelCnt}</td>
			<td><a class="btn_s"  href="javascript:goDetail('${charge.prchsDt}');"><span>詳細查看</span></a></td>
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