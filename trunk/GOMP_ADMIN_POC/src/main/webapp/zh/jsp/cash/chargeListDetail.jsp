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
			document.searchForm.action="chargeListDetail.omp";
			document.searchForm.submit();
		}
	}
	
	function goDetail(prchsDt){
		$("#prchsDt").val(prchsDt);
		document.searchForm.action="<c:url value="chargeListDetail.omp"/>";
		document.searchForm.submit();
	}
	
	function excelDown() {
		if(showValidate('searchForm', 'dialog', '<gm:string value="jsp.cash.cash_list.msg.validate"/>')){
			document.searchForm.action="chargeListDetailExcel.omp";
			document.searchForm.submit();
		}
	}
	
	function onCancle(mbrId, prchsDt, num)
	{
		url = '/purchase/purchasecancel/purchasecancel.omp';

		if(confirm("<gm:string value='jsp.cash.cash_list.msg.tcancel'/>")){
			
			/* button disabled */
			if (! document.getElementById('cancelBtn'+num).disabled){
				document.getElementById('cancelBtn'+num).disabled = true;
			}
			
			document.myForm.currentPage1.value = document.pageNaviForm.currentPage.value;

			window.open('','pop','width=420,height=600,top=150,left=150, scrollbars=no');
			var form = document.cnclForm;
			form.userId.value   = mbrId;
			form.prchsDtm.value = prchsDt;
			form.action         = url;
			form.target         = "pop";
			form.method         = "post";
			form.submit();
			form.target         = "_self";
			form.action         = setTimeout("onSearch()",2500);//onSearch();
			//form.action         = onSearch();
		}
	}
	
//]]>
</script>
	</head>
<body>
	<div id="location">
		首頁 &gt; 產品管理中心 &gt; Whoopy幣管理 &gt <strong>加值紀錄</strong> 
	</div><!-- //location -->

	<h1 class="fl pr10">加值紀錄</h1>
	<p>可查看Whoopy幣加值紀錄。</p>	
	
	<s:form name="cnclForm" theme="simple" method="post">
		<s:hidden name="userId" value="" />
		<s:hidden name="prchsDtm" value="" />
	</s:form>
	
	<s:form id="searchForm" name="searchForm" action="chargeListDetail" theme="simple" >
	<input type="hidden" id="prchsDt" name="sub.prchsDt" value="${sub.prchsDt }"/> 
	<input type="hidden" id="searchToday" name="sub.searchToday" value="${sub.searchToday }" class="searchDate" rel="searchTodayBtn" />
	<input type="hidden" id="searchWeek" name="sub.searchWeek" value="${sub.searchWeek }"  class="searchDate" rel="searchWeekBtn" />
	<input type="hidden" id="searchMonth" name="sub.searchMonth" value="${sub.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
	<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />
	
	<table class="tabletype01">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
		<tr>
			<th>會員搜尋</th>
				<td class="align_td">
					<label for="#" style="min-width:20px;">ID</label>
					<input id="searchText" name="sub.searchText" type="text" class="txt" style="width:120px;" value="${sub.searchText }"/>
				</td>
				<td class="text_c" >
					<a class="btn" href="#" id="searchBtn"><strong>搜尋</strong></a>
				</td>
			</tr>
	</table>
	<p class="fl mt35">${sub.prchsDt} &nbsp;加值詳細紀錄</p>			
	<p class="fr mt25 mb05"><a class="btn" href="#" id="excelDownBtn"><span>下載Excel檔 </span></a></p>
	</s:form>
	<!-- 리스트 시작 -->
	<table class="tabletype02">
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
		</colgroup>
		<thead>
		<tr>
			<th rowspan="2">序號</th>
			<th rowspan="2">帳號(姓名)</th>
			<th colspan="2">加值方式</th>
			<th rowspan="2">Whoopy幣交易額</th>
			<th rowspan="2">加值金額</th>
			<th rowspan="2">取消</th>
		</tr>
		<tr>
		  	<th>WEBATM</th>
		  	<th>管理者</th>
		</tr>                                
		</thead>	
		<tbody>
		<c:choose>
		<c:when test="${totalCount eq 0 }">
		<tr>
				<td colspan="7" class="text_c"><gm:html value='jsp.cash.cash_list.msg.noresult'/></td>
		</tr>
		</c:when>
		<c:otherwise>
		<c:forEach items="${list }" var="charge">
		<tr>
			<td>${charge.totalCount - charge.rnum + 1}</td>
			<td>${charge.mbrIdNm }</td>
			<td><c:choose><c:when test="${charge.atmAmt eq '0'}">-</c:when><c:otherwise> <g:html format="R###,###,###">${charge.atmAmt }</g:html> </c:otherwise></c:choose></td>
			<td><c:choose><c:when test="${charge.adminAmt eq '0'}">-</c:when><c:otherwise> <g:html format="R###,###,###">${charge.adminAmt } </g:html></c:otherwise></c:choose></td>
			<td><g:html format="R###,###,###">${charge.cashAmt }</g:html></td>
			<td><c:choose><c:when test="${charge.prchsAmt eq '0'}">-</c:when><c:otherwise> ${charge.prchsAmt } </c:otherwise></c:choose></td>
			<td><c:choose><c:when test="${charge.applyCancel eq 'Y'}">
					<a class="btn_s" id="cancelBtncharge.rnum" name="cancelBtn${charge.rnum }" href="#" onclick="onCancle('${charge.mbrId }','${charge.prchsDt }','${charge.rnum }');"/><span>取消</span></a></td>
				</c:when><c:otherwise>&nbsp;</c:otherwise></c:choose></td>
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