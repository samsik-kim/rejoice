<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/calendar.js"></script>
<script type="text/javascript">

//<![CDATA[
	
	$(document).ready(function(){

		
		
	});
	
	
	
	$(function() {
		var frm = document.insertForm; 
		
		//검색년도
		var curYear ;
		var curMonth ;
		//초기페이지에서는 현재년월로 셋팅
		<c:choose>
			<c:when test="${empty exRateS.saleYm }">
				curYear = eval(getYear());
				curMonth = getMonth();
			</c:when>
			<c:otherwise>
				<c:set var="string" value="${exRateS.saleYm}" />;
				curYear =  eval('<c:out value="${fn:substring(string,0,4)}"/>');
				curMonth = '<c:out value="${fn:substring(string,4,6)}"/>';
				//alert(curMonth);
			</c:otherwise>
		</c:choose>
		
		for ( var i=curYear-10; i <=curYear+10 ; i++){
			if (i==curYear){
				$("#exRateS\\.exchangeYear").append("<option value='"+i+"' selected>"+i+"年</option>");	
			}else{
				$("#exRateS\\.exchangeYear").append("<option value='"+i+"'>"+i+"年</option>");
			}
		}
		$("#exRateS\\.exchangeMonth").val(curMonth);
		
		
		$("#insertBtn").click(function(event){
			event.preventDefault();
			/*
			$("#exRateS\\.saleYm").val($("#exRateS\\.exchangeYear").val() + $("#exRateS\\.exchangeMonth").val());
			//alert($("#exchangeDate").val())	;
			if($.trim($("#exRateS\\.currency").val()) == ""){
				alert("<gm:string value='jsp.settlement.basis.exchangeRateList.exchange'/>");	
			}
			frm.action="<c:url value="/settlement/insertExchangeRate.omp" />";
			frm.submit();
			
			*/
			
			if(showValidate('insertForm', 'default', "<gm:string value=""/>")){
				$("#exRateS\\.saleYm").val($("#exRateS\\.exchangeYear").val() + $("#exRateS\\.exchangeMonth").val());
				frm.action="<c:url value="/settlement/insertExchangeRate.omp" />";
				frm.submit();
			}
		});
		
		
		$("#receiptS\\.exchangeYear").change(function() {
			var selectedYear = eval($(this).val());
			var curCnt = $("#exRateS\\.exchangeYear option").size();
			
			for ( var i=1; i <=curCnt ; i++){
				$("#exRateS\\.exchangeYear option:eq(0)").remove();
			}
			
			for ( var i=selectedYear-10; i <=selectedYear+10 ; i++){
				$("#exRateS\\.exchangeYear").append("<option value='"+i+"'>"+i+"年</option>");
			}
			$("#exRateS\\.exchangeYear > option[value="+selectedYear+"]").attr("selected", true);
			//alert($(this).val());
			//alert($(this).children("option:selected").text());
		});
		
		
		
		
		/*
		$("#salYmLabel").click(function(event){
			event.preventDefault();
			var saleYm = $("#saleYmLabel").text();
			alert($(this).text());
		});
		*/
		
		
	});
	

	function goPage(no) {
	    var sfrm = document.insertForm
		$("#exRateS\\.page\\.no").val(no);
	    sfrm.action="<c:url value="/settlement/exchangeRateList.omp" />";
		sfrm.submit();
	}
	
	
	function setSaleYm(obj) {
		
		$("#exRateS\\.exchangeYear > option[value="+(obj.saleYm).substring(0,4)+"]").attr("selected", true);
		$("#exRateS\\.exchangeMonth > option[value="+(obj.saleYm).substring(4,6)+"]").attr("selected", true);
		$("#exRateS\\.currency").val(obj.currency);
	}
	
	function js_OnlyNumberR(obj)	{
		if (event.keyCode == 13){event.returnValue =false;}
		//alert(obj.value);
	    //alert(event.keyCode);
		val=obj.value;
		re=/[^0-9][\.?]/gi;
		obj.value=val.replace(re,"");
	}
	
	
//]]>
</script>
</head>
<body>
	

	
			<div id="location">
				首頁 &gt; 結算管理中心 &gt; 結算匯率管理 &gt; <strong>結算匯率管理</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">結算匯率管理</h1>
			<p>可查看每月匯率情況。</p>
			<s:form id="insertForm" name="insertForm"  theme="simple" >
			<input type="hidden" id=exRateS.page.no name="exRateS.page.no" value="${exRateS.page.no}" />
			<input type="hidden" id="exRateS.saleYm" name="exRateS.saleYm" value="${exRateS.saleYm}"/>
			<input type="hidden" id="exRateS.cnt" name="exRateS.cnt"	value="${totalCount}">		
			<table class="tabletype01">
				<colgroup><col style="width:120px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>交易月份</th>
					<td class="align_td">					
						<select id="exRateS.exchangeYear" style="width:65px;"></select>
						<select id="exRateS.exchangeMonth" style="width:55px;">
                        	<option value="01">01月</option>
							<option value="02">02月</option>
							<option value="03">03月</option>
							<option value="04">04月</option>
							<option value="05">05月</option>
							<option value="06">06月</option>
							<option value="07">07月</option>
							<option value="08">08月</option>
							<option value="09">09月</option>
							<option value="10">10月</option>
							<option value="11">11月</option>
							<option value="12">12月</option>
                        </select>
					</td>
					<td rowspan="2" class="text_c" >
						<a class="btn" href="#" id="insertBtn"><strong>確定</strong></a>
					</td>
				</tr>
				<tr>
					<th>美元USD</th>
					<td class="align_td">					
						<input type="text" id="exRateS.currency" name="exRateS.currency" class="txt" style="width:450px" onKeyUp = "js_OnlyNumberR(this);"
							v:required m:required="<gm:string value='jsp.settlement.basis.exchangeRateList.exchange'/>"
							v:regexp="[0-9.]+" m:regexp="<gm:string value='jsp.settlement.basis.exchangeRateList.exchange'/>"
						/>
					</td>
				</tr>
			</table>
			</s:form>
			<table class="tabletype02 mt20">
				<colgroup>
					<col >
					<col >
					<col >
				</colgroup>
				<thead>
				<tr>
					<th rowspan="2">交易月份</th>
					<th>平均匯率</th>
					<th rowspan="2">新增日期</th>
				</tr>
				<tr>
					<th>美元(USD)</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${totalCount eq 0 }">
				<tr>
					<td colspan="3" class="text_c"><gm:html value="jsp.product.noSearchMsg"/></td>
				</tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${exchangeRateList}" var="exRate">
				<tr>
					<td id="salYmLabel" style="cursor:hand" onClick="JavaScript:setSaleYm(<g:tajson value="${exRate}"/>)"><g:text value="${exRate.saleYm}" format="L####-##" /></td>
					<td><g:text value="${exRate.currency}" format="###,###,###,###.##" /></td>
					<td><g:text value="${exRate.insDttm}" format="L####-##-##" /></td>
				</tr>
				</c:forEach>
				</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<!-- paging -->
			<div align="center">
		        <g:pageIndex item="${exchangeRateList}"/>
		    </div>
	
	
	<hr>
	
</body>
</html>
