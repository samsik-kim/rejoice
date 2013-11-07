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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
form{clear:both;}
</style>
<script type="text/javascript">
//<![CDATA[
           
         $(document).ready(function(){

		
    });	
    
     $(function() {
     		var frm = document.searchForm; 
     		
     		//검색년도
    		var fromyy ; 
    		var frommm; 
    		var toyy;
    		var tomm;
    		var shistSearchYm; 
    		
    		<c:choose>
    			<c:when test="${empty sub.fromYm }">
    				toyy = eval(getYear());
    				tomm = getMonth();
    				shistSearchYm = shiftTime(toyy+""+tomm+"01000",0,-12,0,0); //10개월 이전
    				fromyy = eval(shistSearchYm.substring(0,4));
    				frommm = shistSearchYm.substring(4,6);
    			</c:when>
    			<c:otherwise>
    				<c:set var="fromYm" value="${sub.fromYm}"/>
    				<c:set var="toYm" value="${sub.toYm}"/>
    				fromyy =  eval('<c:out value="${fn:substring(fromYm,0,4)}"/>');
    				frommm = '<c:out value="${fn:substring(fromYm,4,6)}"/>';
    				toyy =  eval('<c:out value="${fn:substring(toYm,0,4)}"/>');
    				tomm = '<c:out value="${fn:substring(toYm,4,6)}"/>';
    			</c:otherwise>
    		</c:choose>	
    		
    		for ( var i= fromyy-10; i <= fromyy+10 ; i++){
    			if(i==fromyy){
    				$("#fromyy").append("<option value='"+i+"' selected>"+i+"年</option>");	
    			}else{
    				$("#fromyy").append("<option value='"+i+"'>"+i+"年</option>");
    			}
    		}
    		
    		for ( var i= toyy-10; i <= toyy+10 ; i++){
    			if(i==toyy){
    				$("#toyy").append("<option value='"+i+"' selected>"+i+"年</option>");	
    			}else{
    				$("#toyy").append("<option value='"+i+"'>"+i+"年</option>");
    			}
    			
    		}
    		
    		$("#frommm > option[value='"+frommm+"']").attr("selected", true);
    		$("#tomm > option[value='"+tomm+"']").attr("selected", true);
     		
     		
     		
     		
     		$("#searchBtn").click(function(event){
     			event.preventDefault();
     			funcSearch();
     		});
     		
     		$("#fromyy").change(function() {
     			var selectedYear = eval($(this).val());
     			var curCnt = $("#fromyy option").size();
     			
     			for ( var i=1; i <=curCnt ; i++){
     				$("#fromyy option:eq(0)").remove();
     			}
     			
     			for ( var i=selectedYear-10; i <=selectedYear+10 ; i++){
     				$("#fromyy").append("<option value='"+i+"'>"+i+"年</option>");
     			}
     			$("#fromyy > option[value="+selectedYear+"]").attr("selected", true);
     			//funcSearch();
     		});
     		
     		$("#frommm").change(function() {
     			//funcSearch();
     		});
     		
     		$("#toyy").change(function() {
     			var selectedYear = eval($(this).val());
     			var curCnt = $("#toyy option").size();
     			
     			for ( var i=1; i <=curCnt ; i++){
     				$("#toyy option:eq(0)").remove();
     			}
     			
     			for ( var i=selectedYear-10; i <=selectedYear+10 ; i++){
     				$("#toyy").append("<option value='"+i+"'>"+i+"年</option>");
     			}
     			$("#toyy > option[value="+selectedYear+"]").attr("selected", true);
     			//funcSearch();
     		});
     		
     		$("#tomm").change(function() {
     			//funcSearch();
     		});
     		
     		// excel download btn 
    		$("#excelDownBtn").click(function(event){
    			event.preventDefault();
    			if(showValidate('forexcel', 'default', '',excelCount)){
    				excelDown();
    			}
    			
    		});
     	
     	});
     	
	     function funcSearch(){
	    	$("#fromYm").val($("#fromyy").val() + $("#frommm").val());
	 		$("#toYm").val($("#toyy").val() + $("#tomm").val());
	 		$("#no").val("1");
	 		
	 		if(showValidate('searchForm', 'default', '')){
	 			
	 	 		document.searchForm.action="selectSaleStatMonthlyMainList.omp";
	 	 		document.searchForm.submit();
	 	 	}
	 		 
	 	}
     
     
     	function goPage(no) {
     		$("#no").val(no);
     		$("#fromYm").val($("#fromyy").val() + $("#frommm").val());
 			$("#toYm").val($("#toyy").val() + $("#tomm").val());	
 			/*
 			if (eval($("#fromYm").val()) > eval($("#toYm").val())){
 				alert("검색기간을 정확히 입력하세요");
 				return;
 			}else {
 				document.searchForm.action="<c:url value="/salestat/selectSaleStatMonthlyMainList.omp" />";
 				document.searchForm.submit();
 			}*/
			if(showValidate('searchForm', 'default', '')){
				document.searchForm.action="<c:url value="/salestat/selectSaleStatMonthlyMainList.omp" />";
 				document.searchForm.submit();
	 	 	}
     	}
     	
     	function excelDown() {
     		$("#fromYm").val($("#fromyy").val() + $("#frommm").val());
 			$("#toYm").val($("#toyy").val() + $("#tomm").val());	
    		if(showValidate('forexcel', 'default', '',excelCount)){
    			document.searchForm.action="exportSaleStatMonthlyMainList.omp";
    			document.searchForm.submit();
    		}
    	}
     	
     	//일별 판매 현황 
     	function goDetail(saleYm){
     		$("#saleYm").val(saleYm);
     		document.searchForm.action="selectSaleStatDailyMainList.omp";
     		document.searchForm.submit();
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
		Home &gt; 정산관리 &gt; 판매현황 &gt; <strong>월별판매현황</strong> 
	</div><!-- //location -->

	<h1 class="fl pr10">월별판매현황 </h1>
	<p>월별판매현황을 조회 합니다.</p>
	<form name="forexcel"><input type="hidden" name="excelCnt" value="${totalCount}" v:excelcnt m:excelcnt="<gm:string value='jsp.settlement.salestat.selectSaleStatMonthlyMainList.nodata'/>"/><!-- 엑셀 다운로드 메세지 사용 --></form>
	<s:form id="searchForm" name="searchForm" theme="simple" >
	<input type="hidden" id="saleYm" name="sub.saleYm">
	<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />
	<input type="hidden" id="fromYm" name="sub.fromYm"/>
	<input type="hidden" id="toYm" name="sub.toYm" v:ncompare=">=,@{sub.fromYm}" m:ncompare="<gm:string value='jsp.settlement.salestat.selectSaleStatMonthlyMainList.checkdate'/>"/>
	
	<table class="tabletype01">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
			<tr>
				<th>검색기간</th>
					<td>					
						<select id="fromyy" style="width:60px;">
                        	
                        </select>
						<select id="frommm" style="width:50px;">
                        	<option value="01">01월</option>
                        	<option value="02">02월</option>
                        	<option value="03">03월</option>
                        	<option value="04">04월</option>
                        	<option value="05">05월</option>
                        	<option value="06">06월</option>
                        	<option value="07">07월</option>
                        	<option value="08">08월</option>
                        	<option value="09">09월</option>
                        	<option value="10">10월</option>
                        	<option value="11">11월</option>
                        	<option value="12">12월</option>
                        </select> ~
						<select id="toyy" class="txt" style="width:60px;"> 
                        	
                        </select>
						<select id="tomm" style="width:50px;"> 
                        	<option value="01">01월</option>
                        	<option value="02">02월</option>
                        	<option value="03">03월</option>
                        	<option value="04">04월</option>
                        	<option value="05">05월</option>
                        	<option value="06">06월</option>
                        	<option value="07">07월</option>
                        	<option value="08">08월</option>
                        	<option value="09">09월</option>
                        	<option value="10">10월</option>
                        	<option value="11">11월</option>
                        	<option value="12">12월</option>
                        </select>
					</td>
					<td class="text_c">
						<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
					</td>
				</tr>
			</table>
			</s:form>
			
			<p class="fl mt25">▶ 월별판매현황(최근  ${diffMonth } 개월)</p>
			<p class="fr mt20 mb05"><a class="btn" href="#" id="excelDownBtn"><span>Excel 다운로드</span></a></p>
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
					<col >
					<col >
				</colgroup>
				<thead>
				<tr>
					<th rowspan="2">년/월</th>
					<th colspan="3">판매내역</th>
					<th colspan="2">취소건수</th>
					<th colspan="3">결제수단</th>
					<th rowspan="2">소계</th>
					<th rowspan="2">상세보기</th>
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
					<td colspan="11" class="text_c"><gm:html value="jsp.product.noSearchMsg"/></td>
				</tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${list }" var="salestat">
				<tr>
					<td>${salestat.saleYm}</td>
					<td><g:text value="${salestat.saleAmt}" format="#,###,###,###,###,###,###,###"/></td>
					<td><g:text value="${salestat.payCnt}" format="#,###,###"/></td>
					<td><g:text value="${salestat.freeCnt}" format="#,###,###"/></td>
					<td><g:text value="${salestat.cancelCnt}" format="#,###,###"/></td>
					<td><g:text value="${salestat.cancelAmt}" format="#,###,###"/></td>
					<td><g:text value="${salestat.cardAmt}" format="#,###,###"/></td>
					<td><g:text value="${salestat.phoneAmt}" format="#,###,###"/></td>
					<td><g:text value="${salestat.cashAmt}" format="#,###,###"/></td>
					<td><g:text value="${salestat.subAmt}" format="#,###,###"/></td>
					<td><a class="btn" href="javascript:goDetail('${salestat.saleYm}');"><span>상세보기</span></a></td>
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
				
				
				
				
			
	
