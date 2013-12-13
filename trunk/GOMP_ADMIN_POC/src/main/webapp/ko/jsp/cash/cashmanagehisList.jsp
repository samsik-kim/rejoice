<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld" %>
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
		
		
	});
	
	function goPage(no) {
	    $("#no").val(no);
	    $("#searchBtn").click();
	}
	
	function funcSearch(){
		if(showValidate('searchForm', 'dialog', '<gm:string value="jsp.cash.cashmanagehisList.msg.validate"/>')){
			document.searchForm.action="cashmanagehisList.omp";
			document.searchForm.submit();
		}
	}
	
//]]>
</script>
</head>
<body>
	<div id="location">
		Home &gt; 상품관리 &gt; 캐쉬관리 &gt <strong>캐쉬충전관리 내역</strong> 
	</div><!-- //location -->
	
	<h1 class="fl pr10">캐쉬충전관리 내역</h1>
	<p>캐쉬충전관리 내역 관리 및 신규 생성 합니다.</p>
	<div class="tab">
		<ul>
			<li><a href="cashmanage.omp">현재상태 및 변경</a></li>
			<li class="on"><a href="#">과거 내역</a></li>
		</ul>
	</div>
	<s:form name="searchForm" id="searchForm" theme="simple" method="post">
	<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />	
	<input type="hidden" id="searchMonth" name="sub.searchMonth" value="${sub.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
	<input type="hidden" id="search3Month" name="sub.search3Month" value="${sub.search3Month }"  class="searchDate" rel="search3MonthBtn" />
	<input type="hidden" id="search6Month" name="sub.search6Month" value="${sub.search6Month }"  class="searchDate" rel="search6MonthBtn" />
	<input type="hidden" id="search12Month" name="sub.search12Month" value="${sub.search12Month }"  class="searchDate" rel="search12MonthBtn" />
	<table class="tabletype01">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
		<tr>
			<th>처리일</th>
			<td class="align_td">
				<input id="startDate" name="sub.startDate" type="text" class="txt" style="width:80px;" value="${sub.startDate }" readonly v:required m:required="<gm:string value='jsp.cash.cashmanagehisList.msg.chkStartDate'/>" /> ~ 
				<input id="endDate" name="sub.endDate" type="text" class="txt" style="width:80px;" value="${sub.endDate }" readonly v:required m:required="<gm:string value='jsp.cash.cashmanagehisList.msg.chkEndDate'/>" v:scompare="ge,@{sub.startDate}" m:scompare="<gm:string value='jsp.cash.cashmanagehisList.msg.chkDate'/>"/>
				<a class="btn_s searchDateBtn" href="#" id="searchMonthBtn" rel="1month"><span>최근1개월</span></a>
				<a class="btn_s searchDateBtn" href="#" id="search3MonthBtn" rel="3month"><span>최근3개월</span></a>
				<a class="btn_s searchDateBtn" href="#" id="search6MonthBtn" rel="6month"><span>최근6개월</span></a>
				<a class="btn_s searchDateBtn" href="#" id="search12MonthBtn" rel="12month"><span>최근1년</span></a>
			</td>
			<td rowspan="3" class="text_c" >
				<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
			</td>
		</tr>
	</table>
	</s:form>
	<table class="tabletype02 mt20">
		<colgroup>
			<col style="width:40px;">
			<col>
		</colgroup>
		<tbody>
		<tr>
			<th>번호</th>
			<th>캐쉬 범위</th>
			<th>기존 비율(%)</th>
			<th>변경 비율(%)</th>
			<th>아이디</th>
			<th>처리일시</th>
		</tr>
		<tbody>
		<c:choose>
		<c:when test="${totalCount eq 0 }">
		<tr>
			<td colspan="6" class="text_c"><gm:html value='jsp.cash.cashmanagehisList.msg.noresult'/></td>
		</tr>
		</c:when>
		<c:otherwise>
		<c:forEach items="${list }" var="cash">
		<tr>
			<td>${cash.totalCount - cash.rnum + 1}</td>
			<td>${cash.chrgCashNm}</td>
			<td>${cash.prebonusRatio}</td>
			<td>${cash.bonusRatio}</td>
			<td>${cash.regId}</td>
			<td>${cash.regDt}</td>
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