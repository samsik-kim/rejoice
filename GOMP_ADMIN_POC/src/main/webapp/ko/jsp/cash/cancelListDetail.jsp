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
			document.searchForm.action="cancelListDetail.omp";
			document.searchForm.submit();
		}
	}
	
	function excelDown() {
		if(showValidate('searchForm', 'dialog', '<gm:string value="jsp.cash.cash_list.msg.validate"/>')){
			document.searchForm.action="cancelListDetailExcel.omp";
			document.searchForm.submit();
		}
	}
	
//]]>
</script>
	</head>
<body>
	<div id="location">
		Home &gt; 상품관리 &gt; 캐쉬관리 &gt <strong>취소내역</strong> 
	</div><!-- //location -->

	<h1 class="fl pr10">취소내역</h1>
	<p>취소된 T store Cash 현황을 조회합니다.</p>	
	
	<s:form name="searchForm" theme="simple" method="post">
	<input type="hidden" id="prchsDt" name="sub.prchsDt" value="" /> 
	    	
	<table class="tabletype01">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
		<tr>
		<th>회원검색</th>
			<td class="align_td">
				<label for="#" style="min-width:20px;">ID</label>
				<input id="searchText" name="sub.searchText" type="text" class="txt" value="${sub.searchText }" style="width:80px;"/>
			</td>
			<td class="text_c" >
				<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
			</td>
		</tr>
	</table>
	</s:form>	
	<p class="fl mt35">${sub.prchsDt} &nbsp; 취소 상세 현황</p>			
	<p class="fr mt25 mb05"><a class="btn" href="#" id="excelDownBtn"><span>EXCEL다운로드</span></a></p>
	
	<!-- 리스트 시작 -->
	<table class="tabletype02">
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
			<th>ID(이름)</th>
			<th>취소된 Cash</th>
			<th>기존충전수단</th>
			<th>충전금액</th>
			<th>충전일</th>
		</tr>                                
		</thead>
		<tbody>
		<c:choose>
		<c:when test="${totalCount eq 0 }">
		<tr>
				<td colspan="6" class="text_c"><gm:html value='jsp.cash.cash_list.msg.noresult'/></td>
		</tr>
		</c:when>
		<c:otherwise>
		<c:forEach items="${list }" var="cancel">
		<tr>
			<td>${cancel.totalCount - cancel.rnum + 1}</td>
			<td>${cancel.mbrId}</td>
			<td><g:html format="R###,###,###">${cancel.cashAmt}</g:html></td>
			<td>${cancel.payCls}</td>
			<td><g:html format="R###,###,###">${cancel.prchsAmt}</g:html></td>
			<td>${cancel.prchsDt}</td>
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