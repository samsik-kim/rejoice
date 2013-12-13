<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/calendar.js"></script>
<script type="text/javascript">

//<![CDATA[
	
	$(document).ready(function(){

				
		
	});
	
	
	$(function() {
		var frm = document.searchForm; 
		
		//검색년도
		var searchStartYear ; 
		var searchStartMonth; 
		var searchEndYear;
		var searchEndMonth;
		var shistSearchYm; //
		
		<c:choose>
			<c:when test="${empty remittanceS.searchStartYm }">
				//alert("first");
				searchEndYear = eval(getYear());
				searchEndMonth = getMonth();
				shistSearchYm = shiftTime(searchEndYear+""+searchEndMonth+"01000",0,-10,0,0); //10개월 이전
				searchStartYear = eval(shistSearchYm.substring(0,4));
				searchStartMonth = shistSearchYm.substring(4,6);
				$("#excel").hide(); //초기페이지 엑셀 안보이게 하기
				
			</c:when>
			<c:otherwise>
				<c:set var="startYm" value="${remittanceS.searchStartYm}" />;
				<c:set var="endYm" value="${remittanceS.searchEndYm}" />;
				searchStartYear =  eval('<c:out value="${fn:substring(startYm,0,4)}"/>');
				searchStartMonth = '<c:out value="${fn:substring(startYm,4,6)}"/>';
				searchEndYear =  eval('<c:out value="${fn:substring(endYm,0,4)}"/>');
				searchEndMonth = '<c:out value="${fn:substring(endYm,4,6)}"/>';
			</c:otherwise>
		</c:choose>	
		
		
		for ( var i=searchStartYear-10; i <=searchStartYear+10 ; i++){
			if(i==searchStartYear){
				$("#searchStartYear").append("<option value='"+i+"' selected>"+i+"年</option>");	
			}else{
				$("#searchStartYear").append("<option value='"+i+"'>"+i+"年</option>");
			}
			
		}
		
		for ( var i=searchEndYear-10; i <=searchEndYear+10 ; i++){
			if(i==searchEndYear){
				$("#searchEndYear").append("<option value='"+i+"' selected>"+i+"年</option>");	
			}else{
				$("#searchEndYear").append("<option value='"+i+"'>"+i+"年</option>");
			}
		}
		
		$("#searchStartMonth").val(searchStartMonth);
		$("#searchEndMonth").val(searchEndMonth);
		
		///엑셀버튼작업
		<c:if test="${empty remittanceS.firstAccessYN || totalCount eq 0 }">
				$("#excel").hide(); //초기페이지와 데이타가 존재하지 않을시  엑셀 안보이게 하기
		</c:if>
		<c:if test="${totalCount > 0}">
			$("#excel").show(); 
		</c:if>
		
	
		
		
		
		$("#searchBtn").click(function(event){
			event.preventDefault();
			/*
			$("#remittanceS\\.searchStartYm").val($("#searchStartYear").val() + $("#searchStartMonth").val());
			$("#remittanceS\\.searchEndYm").val($("#searchEndYear").val() + $("#searchEndMonth").val());	
			
			if (eval($("#remittanceS\\.searchStartYm").val()) > eval($("#remittanceS\\.searchEndYm").val())){
				alert("<gm:string value='jsp.settlement.remittance.remittanceEndRstList.date'/>");
				return;
			}else {
				document.searchForm.action="<c:url value="/settlement/remittance/remittanceEndRstList.omp" />";
				document.searchForm.submit();
			}
			*/
			if(showValidate('searchForm', 'default', "<gm:string value=""/>",searchBtnFnc)){
				document.searchForm.action="<c:url value="/settlement/remittance/remittanceEndRstList.omp" />";
				document.searchForm.submit();
			}
		});
		
		
		
		$("#searchStartYear").change(function() {
			var selectedYear = eval($(this).val());
			var curCnt = $("#searchStartYear option").size();
			
			for ( var i=1; i <=curCnt ; i++){
				$("#searchStartYear option:eq(0)").remove();
			}
			
			for ( var i=selectedYear-10; i <=selectedYear+10 ; i++){
				$("#searchStartYear").append("<option value='"+i+"'>"+i+"</option>");
			}
			$("#searchStartYear").val(selectedYear);
			//alert($(this).val());
			//alert($(this).children("option:selected").text());
		});
		
		$("#searchEndYear").change(function() {
			var selectedYear = eval($(this).val());
			var curCnt = $("#searchEndYear option").size();
			
			for ( var i=1; i <=curCnt ; i++){
				$("#searchEndYear option:eq(0)").remove();
			}
			
			for ( var i=selectedYear-10; i <=selectedYear+10 ; i++){
				$("#searchEndYear").append("<option value='"+i+"'>"+i+"</option>");
			}
			$("#searchEndYear").val(selectedYear);
		});
		
		$("#excel").click(function(event){
			event.preventDefault();
			var sfrm = document.searchForm;
			sfrm.action="<c:url value="/settlement/remittance/remittanceEndRstExcelList.omp" />";
			sfrm.submit();
		});
		
		
	});
	

	function goPage(no) {
	    var sfrm = document.searchForm
		$("#no").val(no);
	    sfrm.action="<c:url value="/settlement/remittance/remittanceEndRstList.omp" />";
		sfrm.submit();
	}
	
	//송금결과 현황 상세 리스트
	function viewPage(rmtReqYyyymm){
		var frm = document.viewForm;
		
		$("#remittanceSS\\.rmtReqYyyymm").val(rmtReqYyyymm);
		
		frm.action="<c:url value="/settlement/remittance/remittanceEndRstInfoList.omp"/>";
		frm.submit();
	}
	
	var searchBtnFnc = {
	   "dateCheck" : function() {
			$("#remittanceS\\.searchStartYm").val($("#searchStartYear").val() + $("#searchStartMonth").val());
			$("#remittanceS\\.searchEndYm").val($("#searchEndYear").val() + $("#searchEndMonth").val());	
			
			if (eval($("#remittanceS\\.searchStartYm").val()) > eval($("#remittanceS\\.searchEndYm").val())){
				return false;
			}else {
				return true;
			}
	   }
	};
	
	
	
	
//]]>
</script>
</head>
<body>

			<div id="location">
				首頁 &gt; 結算管理中心 &gt; 結算現狀  &gt; <strong>匯款結果現狀</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">匯款結果現狀</h1>
			<p>可查看匯款結果現狀內容.</p>
			<s:form id="searchForm" name="searchForm"  theme="simple" >
			<input type="hidden" id="remittanceS.searchStartYm" name="remittanceS.searchStartYm" value="${remittanceS.searchStartYm}"/>
			<input type="hidden" id="remittanceS.searchEndYm" name="remittanceS.searchEndYm" value="${remittanceS.searchEndYm}">
			<input type="hidden" id="no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
			<input type="hidden" id="firstAccessYN" name="remittanceS.firstAccessYN" value="N">
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>搜尋條件</th>
					<td class="align_td line2_5">					
						<label for="#">匯款月份</label>
                        <select id="searchStartYear" v:dateCheck m:dateCheck="<gm:string value='jsp.settlement.remittance.remittanceEndRstList.date'/>" style="width:65px;">
                        </select>
						<select id="searchStartMonth" style="width:55px;">
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
                        </select> ~
						<select id="searchEndYear" style="width:65px;"></select>
						<select id="searchEndMonth" style="width:55px;">
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
					<td class="text_c" >
						<a class="btn" href="#" id="searchBtn"><strong>搜尋</strong></a>
					</td>
				</tr>
			</table>
			</s:form>
			<p class="fr mt20 mb05"><a class="btn_s" href="#" ><span id="excel">下載Excel檔</span> </a></p>
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
					<th rowspan="2">匯款月份</th>
					<th colspan="2">結算金額(TWD/USD)</th>
					<th colspan="4">匯款筆數</th>			
					<th colspan="2">匯款金額</th>
				</tr>
				<tr>
					<th>TWD</th>				
					<th>USD</th>
					<th>匯款完畢</th>
					<th>次月處理</th>
					<th>再次匯款</th>
					<th>匯款失敗</th>					
					<th>TWD</th>				
					<th>USD</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${remittanceS.firstAccessYN ne 'N' }">
					<td colspan="9" ><gm:html value="jsp.product.initSearchMsg"/></td>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${totalCount eq 0 }">
						<td colspan="9" ><gm:html value="jsp.product.noSearchMsg"/></td>
					</c:when>
					<c:otherwise>
					<c:forEach items="${remittanceRstList}" var="remittance">
					<tr>
						<td><a href="#" onClick="JavaScript:viewPage('${remittance.rmtReqYyyymm}')"><g:text value="${remittance.rmtReqYyyymm}" format="L####-##" /></a></td>
						<td><g:text value="${remittance.totlRmtPayAmt}" format="###,###,###,###.##" /></td>
						<td><g:text value="${remittance.totlRmtPayUsAmt}" format="###,###,###,###.##" /></td>
						<td><g:text value="${remittance.totlRmtCnt}" format="###,###,###,###" /></td>
						<td><g:text value="${remittance.crovCnt}" format="###,###,###,###" /></td>
						<td><g:text value="${remittance.reRmtCnt}" format="###,###,###,###" /></td>
						<td><g:text value="${remittance.giveupCnt}" format="###,###,###,###" /></td>
						<td><g:text value="${remittance.twAmt}" format="###,###,###,###.##" /></td>
						<td><g:text value="${remittance.usAmt}" format="###,###,###,###.##" /></td>
					</tr>
					</c:forEach>
					</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<s:form id="viewForm" name="viewForm"  theme="simple" >
				<input type="hidden" id="remittanceS.searchStartYm" name="remittanceS.searchStartYm" value="${remittanceS.searchStartYm}"/>
				<input type="hidden" id="remittanceS.searchEndYm" name="remittanceS.searchEndYm" value="${remittanceS.searchEndYm}">
				<input type="hidden" id="remittanceS.page.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
				<input type="hidden" id="remittanceS.firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
				<input type="hidden" id="remittanceSS.rmtReqYyyymm" name="remittanceSS.rmtReqYyyymm" />
			</s:form>
			<!-- paging -->
			<div align="center">
		        <g:pageIndex item="${remittanceRstList}"/>
		    </div>
			<!-- //paging -->

</body>
</html>
