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

		//처리결과 메세지가 존재하면 화면에 출력을 해준다.
		<c:if test="${not empty receiptS.processMessage   }">
			alert("<gm:string value='${receiptS.processMessage}'/>");	
		</c:if>
	});
	
	$(function() {
		var frm = document.searchForm; 
		
		
		//검색년도
		var curYear = eval(getYear());
		var curMonth = getMonth();
		
		//초기페이지에서는 현재년월로 셋팅
		<c:choose>
			<c:when test="${empty receiptS.firstAccessYN }">
				curYear = eval(getYear());
				curMonth = getMonth();
			</c:when>
			<c:otherwise>
				<c:set var="string" value="${receiptS.rtYearterm}" />;
				curYear =  eval('<c:out value="${fn:substring(string,0,4)}"/>');
				curMonth = '<c:out value="${fn:substring(string,4,6)}"/>';
				//alert(curMonth);
			</c:otherwise>
		</c:choose>
		
		var	selectYear;
		
		selectYear	= $("form[name=searchForm] select[name=receiptS\\.receiptFromYear]");
		//selectMonth = $("form[name=searchForm] select[name=receiptS\\.receiptFromMonth]");
		for ( var i=curYear-10; i <=curYear+10 ; i++){
			if(i==curYear){
				selectYear.append("<option value='"+i+"' selected>"+i+"年</option>");	
			}else{
				selectYear.append("<option value='"+i+"'>"+i+"年</option>");
			}
			
		}
		
		$("form[name=searchForm] select[name=receiptS\\.receiptFromMonth]").val(curMonth);
		
		
		
		$("#searchReceiptBtn").click(function(event){
			event.preventDefault();
			$("#receiptS\\.rtYearterm").val($("#receiptS\\.receiptFromYear").val() + $("#receiptS\\.receiptFromMonth").val());
			//alert($("#receiptS\\.rtYearterm").val());
			frm.action="<c:url value="/settlement/receiptList.omp" />";
			frm.submit();
		});
		
		
		$("#insertBtn").click(function(event){
			 	 document.insertForm.action = "<c:url value="/settlement/insertStartReceipt.omp" />";
			 	 document.insertForm.submit();
   	    });
		
		$("#receiptS\\.receiptFromYear").change(function() {
			var selectedYear = eval($(this).val());
			var curCnt = $("#receiptS\\.receiptFromYear option").size();
			
			for ( var i=1; i <=curCnt ; i++){
				$("#receiptS\\.receiptFromYear option:eq(0)").remove();
			}
			
			for ( var i=selectedYear-10; i <=selectedYear+10 ; i++){
				$("#receiptS\\.receiptFromYear").append("<option value='"+i+"'>"+i+"年</option>");
			}
			$("#receiptS\\.receiptFromYear > option[value="+selectedYear+"]").attr("selected", true);
			//alert($(this).val());
			//alert($(this).children("option:selected").text());
		});
	});
	

	function goPage(no) {
	    var sfrm = document.searchForm
		$("#no").val(no);
	    sfrm.action="<c:url value="/settlement/receiptList.omp" />";
		sfrm.submit();
	}
	
	function editPage(rtYearterm,occrNo) {
	    var ifrm = document.insertForm
		$("#receiptSS\\.rtYearterm").val(rtYearterm);
		$("#receiptSS\\.occrNo").val(occrNo);
		ifrm.action="<c:url value="/settlement/editStartReceipt.omp" />";
		ifrm.submit();
	}
//]]>
</script>
</head>
<body>
	
	
			<div id="location">
				首頁 &gt; 結算管理中心 &gt; 發票管理 &gt; <strong>開立發票管理</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">開立發票管理</h1>
			<p>可查看開立發票的情況。</p>
			<s:form id="searchForm" name="searchForm"  theme="simple" >
			<input type="hidden" id="receiptS.page.no" name="receiptS.page.no" value="${receiptS.page.no}" />			
			<input type="hidden" id="receiptS.rtYearterm" name="receiptS.rtYearterm" value="${receiptS.rtYearterm}"/>
			<input type="hidden" id="receiptS.firstAccessYN" name="receiptS.firstAccessYN" value="N">
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>搜尋</th>
					<td class="align_td line2_5">					
						<label for="#">使用月份</label>
                        <select id="receiptS.receiptFromYear" name="receiptS.receiptFromYear" style="width:65px;"></select>
						<select id="receiptS.receiptFromMonth" name="receiptS.receiptFromMonth" style="width:55px;">
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
						<a class="btn" href="#" id="searchReceiptBtn" ><strong>搜尋</strong></a>
					</td>
				</tr>
			</table>
			</s:form>
			<table class="tabletype02 mt20">
				<colgroup>
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
					<th colspan="3">發票編號</th>
					<th rowspan="2">使用期間</th>
					<th rowspan="2">「使用中」發票編號總數</th>
					<th rowspan="2">剩餘發票編號總數</th>
					<th rowspan="2">新增日期</th>
					<th rowspan="2">備註</th>
				</tr>
				<tr>
					<th>文字</th>
					<th>開頭</th>
					<th>末尾</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${empty receiptS.firstAccessYN }">
					<td colspan="8" ><gm:html value="jsp.product.initSearchMsg"/></td>
				</c:when>
				<c:otherwise>	
					<c:choose>
					<c:when test="${totalCount eq 0 }">
					<tr>
						<td colspan="8" class="text_c"><gm:html value="jsp.product.noSearchMsg"/></td>
					</tr>
					</c:when>
					<c:otherwise>
					<c:forEach items="${receiptList}" var="receipt">
					<tr>
						<c:set var="string" value="${receipt.rtYearterm}" />
						<td>${receipt.rtPrfix}</td>
						<td>${receipt.rtStartNo}</td>
						<td>${receipt.rtEndNo}</td>
						<td><g:text value="${receipt.rtYearterm}" format="L####-##" /></td>
						<td><g:text value="${receipt.rtUseCnt}" format="R###,###,###,###" /></td>
						<td><g:text value="${receipt.rtUnuseCnt}" format="R###,###,###,###" /></td>
						<td><g:text value="${receipt.regDt}" format="L####-##-##" /></td>
						<td>
							<c:choose>
							<c:when test="${receipt.rtPossbilYn eq 'Y' }">
							<a href="#" class="btn_s" onClick="JavaScript:editPage('${receipt.rtYearterm}','${receipt.occrNo}')"><span>變更</span></a>
							</c:when>
							<c:otherwise>
								-
							</c:otherwise>
							</c:choose>
						</td>
					</tr>
					</c:forEach>
					</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose>
				
				</tbody>
			</table>
			<p class="btn_wrap text_r mt05"><a class="btn" href="#" id="insertBtn"><span>新增</span></a></p>
			<s:form id="insertForm" name="insertForm"  theme="simple" >
			<input type="hidden" id="receiptS.rtYearterm" name="receiptS.rtYearterm" value="${receiptS.rtYearterm}">
			<input type="hidden" id="receiptS.receiptFromMonth" name="receiptS.receiptFromMonth"  value="${receiptS.receiptFromMonth }">
			<input type="hidden" id="receiptS.receiptFromYear" name="receiptS.receiptFromYear"  value="${receiptS.receiptFromYear }">
			<input type="hidden" id="receiptS.page.no" name="receiptS.page.no" value="${receiptS.page.no}" />
			<input type="hidden" id="receiptS.firstAccessYN" name="receiptS.firstAccessYN" value="${receiptS.firstAccessYN}">
			<input type="hidden" id="receiptSS.rtYearterm" name="receiptSS.rtYearterm" >
			<input type="hidden" id="receiptSS.occrNo" name="receiptSS.occrNo" >
			</s:form>
			<!-- paging -->
			<div align="center">
		        <g:pageIndex item="${receiptList}"/>
		    </div>
			<!-- //paging -->

	<hr>

</body>
</html>
