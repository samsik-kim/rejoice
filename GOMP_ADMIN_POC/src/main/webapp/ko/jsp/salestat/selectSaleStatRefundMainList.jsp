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
    		var shistSearchYm; //
    		
    		<c:choose>
    			<c:when test="${empty sub.fromYm }">
    				//alert("first");
    				toyy = eval(getYear());
    				tomm = getMonth();
    				shistSearchYm = shiftTime(toyy+""+tomm+"01000",0,-1,0,0); //1개월 이전
    				fromyy = eval(shistSearchYm.substring(0,4));
    				frommm = shistSearchYm.substring(4,6);
    			</c:when>
    			<c:otherwise>
    				<c:set var="fromYm" value="${sub.fromYm}" />
    				<c:set var="toYm" value="${sub.toYm}" />
    				fromyy =  eval('<c:out value="${fn:substring(fromYm,0,4)}"/>');
    				frommm = '<c:out value="${fn:substring(fromYm,4,6)}"/>';
    				toyy =  eval('<c:out value="${fn:substring(toYm,0,4)}"/>');
    				tomm = '<c:out value="${fn:substring(toYm,4,6)}"/>';
    			</c:otherwise>
    		</c:choose>	
    		
    		for ( var i= fromyy-10; i <= fromyy+10 ; i++){
    			if(i==fromyy){
    				$("#fromyy").append("<option value='"+i+"' selected>"+i+"년</option>");	
    			}else{
    				$("#fromyy").append("<option value='"+i+"'>"+i+"년</option>");
    			}
    			
    		}
    		
    		for ( var i= toyy-10; i <= toyy+10 ; i++){
    			if(i==toyy){
    				$("#toyy").append("<option value='"+i+"' selected>"+i+"년</option>");	
    			}else{
    				$("#toyy").append("<option value='"+i+"'>"+i+"년</option>");
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
     	
	     function funcSearch(){
	    	$("#fromYm").val($("#fromyy").val() + $("#frommm").val());
	 		$("#toYm").val($("#toyy").val() + $("#tomm").val());
	 		$("#no").val("1");
	 		if(showValidate('searchForm', 'default', '')){
				document.searchForm.action="<c:url value="/salestat/selectSaleStatRefundMainList.omp" />";
 				document.searchForm.submit();
			}
	    	 
	 	}
     
     
     	function goPage(no) {
     		$("#no").val(no);
     		$("#fromYm").val($("#fromyy").val() + $("#frommm").val());
 			$("#toYm").val($("#toyy").val() + $("#tomm").val());	
			if(showValidate('searchForm', 'default', '')){
				document.searchForm.action="<c:url value="/salestat/selectSaleStatRefundMainList.omp" />";
 				document.searchForm.submit();
			}
     	}
     	
     	function excelDown() {
     		$("#fromYm").val($("#fromyy").val() + $("#frommm").val());
 			$("#toYm").val($("#toyy").val() + $("#tomm").val());	
    		if(showValidate('searchForm', 'default', '')){
    			document.searchForm.action="exportSaleStatRefundMainList.omp";
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
		Home &gt; 정산관리 &gt; 판매현황 &gt; <strong>구매취소내역</strong> 
	</div><!-- //location -->

	<h1 class="fl pr10">구매취소내역</h1>
	<p>구매취소내역을 조회 합니다.</p>
	<form name="forexcel"><input type="hidden" name="excelCnt" value="${totalCount}" v:excelcnt m:excelcnt="<gm:string value='jsp.settlement.salestat.selectSaleStatDailyMainList.nodata'/>"/><!-- 엑셀 다운로드 메세지 사용 --></form>
	<s:form id="searchForm" name="searchForm" theme="simple" >
	<input type="hidden" id="saleYm" name="sub.saleYm">
	<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />
	<input type="hidden" id="fromYm" name="sub.fromYm">
	<input type="hidden" id="toYm" name="sub.toYm" v:ncompare=">=,@{sub.fromYm}" m:ncompare="<gm:string value='jsp.settlement.salestat.selectSaleStatRefundMainList.checkdate'/>"/>
	
	<table class="tabletype01">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
			<tr>
				<th>검색기간</th>
					<td>					
						<select id="fromyy" style="width:65px;">
                        	
                        </select>
						<select id="frommm" style="width:55px;">
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
						<select id="toyy" style="width:65px;">
                        	
                        </select>
						<select id="tomm" style="width:55px;">
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
					<td rowspan="2" class="text_c">
						<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
					</td>
				</tr>
				<tr>
					<th>검색어</th>
					<td class="align_td">
					<select id="searchType" name="sub.searchType" onchange="javascript:selType_change();">
						<option value="userId" <c:if test='${sub.searchType eq "userId" }'>selected</c:if>>구매자ID</option>
				   		<option value="devId" <c:if test='${sub.searchType eq "devId" }'>selected</c:if>>개발자ID</option>
				   		<option value="prd" <c:if test='${sub.searchType eq "prd" }'>selected</c:if>>상품명</option>
                 	</select>
					<input type="text" class="txt" id="searchText" name="sub.searchText" style="width:250px;" value="${sub.searchText }"/>
					</td>
				</tr>
			</table>
			</s:form>
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
					<th>번호</th>
					<th>취소일</th>
					<th>판매일</th>
					<th>개발자ID<br />(이름)</th>
					<th>구매자ID<br />(이름)</th>
					<th>상품명</th>
					<th>상품금액</th>
					<th>결제금액</th>
					<th>결제수단</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${totalCount eq 0 }">
				<tr>
					<td colspan="9" class="text_c"><gm:html value="jsp.product.noSearchMsg"/></td>
				</tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${list }" var="salestat">
				<tr>
					<td>${salestat.totalCount - salestat.rnum + 1}</td>
					<td>${salestat.dttm}</td>
					<td>${salestat.saleDttm}</td>
					<td>${salestat.memNameId}</td>
					<td>${salestat.userMemNameId}</td>
					<td>${salestat.prdName}</td>
					<td><g:text value="${salestat.prodprc}" format="#,###,###,###,###,###,###,###"/></td>
					<td>${salestat.paymethod}</td>
					<td><g:text value="${salestat.payAmt}" format="#,###,###,###,###,###,###,###"/></td>
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