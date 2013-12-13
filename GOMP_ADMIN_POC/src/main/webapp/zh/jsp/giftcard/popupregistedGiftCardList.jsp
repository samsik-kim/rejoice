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
<style type="text/css">
	body.popup table {
		margin-bottom:15px
	}
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
		});
		
		// excel download btn 
		$("#excelDownBtn").click(function(event){
			event.preventDefault();
			if (${totalCount}  == 0) {
				alert("<gm:string value='jsp.gift.popupregistedGiftCardList.msg.noresult'/>");
				return;
			}
			excelDown();
		});
		
		// exit Btn
		$("#exitBtn").click(function(event){
			event.preventDefault();
			self.close();
		});
		
		
	});           	
    
	function funcSearch(){
		if(showValidate('searchForm', 'dialog', '<gm:string value="jsp.gift.popupregistedGiftCardList.msg.validate"/>')){
			document.searchForm.action="popRegistedGiftCardList.omp";
			document.searchForm.submit();
		}
	}
	
	function excelDown() {
		if(showValidate('searchForm', 'dialog', '<gm:string value="jsp.gift.popupregistedGiftCardList.msg.validate"/>')){
			document.searchForm.action="registStatExcelExport.omp";
			document.searchForm.submit();
		}
	}
	
//]]>
</script>
</head>
<body class="popup">
<div id="popup_area_810">
	<h1></h1>
	<s:form id="searchForm" name="searchForm" theme="simple" >
	<input type="hidden" id="groupSeq" name="sub.groupSeq" value="${sub.groupSeq}"/>
	<input type="hidden" id="issuedGiftCardCnt" name="sub.issuedGiftCardCnt"  value="${sub.issuedGiftCardCnt}"/>
	<input type="hidden" id="searchToday" name="sub.searchToday" value="${sub.searchToday }" class="searchDate" rel="searchTodayBtn" />
	<input type="hidden" id="searchWeek" name="sub.searchWeek" value="${sub.searchWeek }"  class="searchDate" rel="searchWeekBtn" />
	<input type="hidden" id="searchMonth" name="sub.searchMonth" value="${sub.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
	<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />

	<table class="pop_tabletype01">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
		<tbody>
		<tr>
			<th>搜尋期間</th><!-- 2011-03-15 -->
			<td class="align_td">
				<label for="startDate">期間搜尋</label>
				<input id="startDate" name="sub.startDate" type="text" class="txt" style="width:80px;" value="${sub.startDate }" readonly v:required m:required="<gm:string value='jsp.gift.popupregistedGiftCardList.msg.chkStartDate'/>" /> ~ 
				<input id="endDate" name="sub.endDate" type="text" class="txt" style="width:80px;" value="${sub.endDate }" readonly v:required m:required="<gm:string value='jsp.gift.popupregistedGiftCardList.msg.chkEndDate'/>" v:scompare="ge,@{sub.startDate}" m:scompare="<gm:string value='jsp.gift.popupregistedGiftCardList.msg.chkDate'/>"/> 
				<a class="btn_s searchDateBtn" href="#" id="searchTodayBtn" rel="today"><span>今日</span></a>
				<a class="btn_s searchDateBtn" href="#" id="searchWeekBtn" rel="7day"><span>最近一周</span></a>
				<a class="btn_s searchDateBtn" href="#" id="searchMonthBtn" rel="1month"><span>最近一個月</span></a>
			</td>
			<td rowspan="2" class="text_c" >
				<a class="btn" href="#" id="searchBtn"><strong>搜尋</strong></a>
				<a class="btn" href="#" id="searchInitBtn"><span>重新搜尋</span></a>
			</td>
		</tr>
		<tr>
			<th>搜尋會員</th>
			<td>ID <input type="text" id="searchText" name="sub.searchText" class="txt" style="width:70%;" /></td>
		</tr>
		</tbody>
	</table>
	<div class="text_r mt05">
		<p class="fr mt25 mb05"><a class="btn" href="#" id="excelDownBtn"><span>下載Excel 檔</span></a></p>
	</div>
	<table class="pop_tabletype02 mt20">
		<colgroup><col style="width:10%;"><col><col style="width:25%;"></colgroup>
		<thead>
		<tr>
			<th>序號</th>
			<th>ID(姓名)</th>
			<th>註冊日期</th>
		</tr>
		</thead>
		<tbody>
		<c:choose>
		<c:when test="${totalCount eq 0 }">
		<tr>
			<td colspan="3" class="text_c"><gm:html value='jsp.gift.popupregistedGiftCardList.msg.noresult'/></td>
		</tr>
		</c:when>
		<c:otherwise>
		<c:forEach items="${giftCardlist }" var="giftCard">
		<tr>
			<td>${giftCard.totalCount - giftCard.rnum + 1}</td>
			<td>${giftCard.mbrId }(${giftCard.mbrNm})</td>
			<td>${giftCard.occrDt }</td>
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
	<div class="pop_btn" >
		<a class="btn" href="#" id="exitBtn"><strong>關閉</strong></a>
	</div>
	</s:form>
</body>
</html>
	
