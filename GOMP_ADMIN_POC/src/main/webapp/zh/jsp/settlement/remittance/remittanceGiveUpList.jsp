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
		var curYear = eval(getYear());
		var curMonth = getMonth();
		
		<c:choose>
			<c:when test="${empty remittanceS.saleYm }">
				curYear = eval(getYear());
				curMonth = getMonth();
			</c:when>
			<c:otherwise>
				<c:set var="string" value="${remittanceS.saleYm}" />;
				curYear =  eval('<c:out value="${fn:substring(string,0,4)}"/>');
				curMonth = '<c:out value="${fn:substring(string,4,6)}"/>';
			</c:otherwise>
		</c:choose>	
		
		for ( var i=curYear-10; i <=curYear+10 ; i++){
			if(i==curYear){
				$("#saleYear").append("<option value='"+i+"' selected>"+i+"年</option>");	
			}else{
				$("#saleYear").append("<option value='"+i+"'>"+i+"年</option>");
			}
			
		}
		
		$("#saleMonth").val(curMonth);
		//검색 전체 해당월 체크
		$("form[name=searchForm] input[name=remittanceS\\.searchTimeBlock]").each(function (idx, obj) {
			obj.checked = obj.value == '${remittanceS.searchTimeBlock}'; 
		});
		
		//검색년도
			
		
		
		//검색어 작업
		<c:set var="searchType" value="${remittanceS.searchType}" />;
		<c:set var="searchCont" value="${remittanceS.searchCont}" />;
		
		var searchType = $.trim("<c:out value='${searchType}'/>");
		var searchCont = $.trim("<c:out value='${searchCont}'/>");
		$("#searchType").val('${searchType}');
		$("#searchCont").val(searchCont);
		//검색어 작업
		
		///송금결과 라벨작업
		<c:if test="${empty remittanceS.firstAccessYN || totalCount eq 0 }">
				$("#excel").hide(); //초기페이지와 데이타가 존재하지 않을시  엑셀 안보이게 하기
		</c:if>
		<c:if test="${totalCount > 0}">
			$("#excel").show(); 
		</c:if>
		<c:if test="${not empty remittanceS.saleYm   }">
			<c:set var="string" value="${remittanceS.saleYm}" />;	
			$("#labelSaleYyymm").text("▶ ${fn:substring(string,0,4)}-${fn:substring(string,4,6)}月份匯款失敗現狀");
		</c:if>
		
		
		
		
		$("#saleYear").change(function() {
			var selectedYear = eval($(this).val());
			var curCnt = $("#saleYear option").size();
			
			for ( var i=1; i <=curCnt ; i++){
				$("#saleYear option:eq(0)").remove();
			}
			
			for ( var i=selectedYear-10; i <=selectedYear+10 ; i++){
				$("#saleYear").append("<option value='"+i+"'>"+i+"年</option>");
			}
			$("#saleYear > option[value="+selectedYear+"]").attr("selected", true);
			//alert($(this).val());
			//alert($(this).children("option:selected").text());
		});
		
		
		
		$("#searchBtn").click(function(event){
			event.preventDefault();
			if($("#searchTimeBlock:checked").val()==""){ //전체 기간 선택 검색시
				$("#saleYm").val("");
			}else{ //부분 기간 선택 검색시
				$("#saleYm").val($("#saleYear").val() + $("#saleMonth").val());	
			}
			
			$("#searchCont").val($.trim($("#searchCont").val()));
			var searchType = $("#searchType").val();
			if ($("#searchType").val() == "B"){ //개발자번호 값 셋팅
				$("#mbrId").val($("#searchCont").val());
			}else if($("#searchType").val() == "C"){ //개발자명 값 셋팅
				$("#mbrNm").val($("#searchCont").val());
			}
			frm.action="<c:url value="/settlement/remittance/remittanceGiveUpList.omp" />";
			frm.submit();
		});
		
		$("#excel").click(function(event){
			event.preventDefault();
			var sfrm = document.searchForm;
			sfrm.action="<c:url value="/settlement/remittance/remittanceGiveUpExcelList.omp" />";
			sfrm.submit();
		});
		
		$("#searchType").change(function() {
			$("#searchCont").val("");
		});
		
		
	});
	

	function goPage(no) {
	    var sfrm = document.searchForm
		$("#no").val(no);
	    sfrm.action="<c:url value="/settlement/remittance/remittanceRstList.omp" />";
		sfrm.submit();
	}
	
	function viewPage(saleYm,rmtReqYyyymm,mbrNo,adjYn){
		var frm = document.viewForm;
		
		$("#remittanceSS\\.saleYm").val(saleYm);
		$("#remittanceSS\\.rmtReqYyyymm").val(rmtReqYyyymm);
		$("#remittanceSS\\.mbrNo").val(mbrNo);
		$("#remittanceSS\\.adjYn").val(adjYn);
		
		frm.action="<c:url value="/settlement/remittance/remittanceGiveUpInfo.omp"/>";	
		frm.submit();
	}
	
	
	
//]]>
</script>

</head>
<body>

			<div id="location">
				首頁 &gt; 結算管理中心 &gt; 結算現狀  &gt; <strong>匯款失敗現狀</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">匯款失敗現狀</h1>
			
			<p>可查看匯款失敗現狀內容.</p>
			<s:form id="searchForm" name="searchForm"  theme="simple" >
			<input type="hidden" id="saleYm" name="remittanceS.saleYm" value="${remittanceS.saleYm}"> <!-- 판매월 -->
			<input type="hidden" id="mbrId" name="remittanceS.mbrId" value="${remittanceS.mbrId}"> <!-- 개발자번호 검색값 -->
			<input type="hidden" id="mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}"> <!-- 개발자명 검색값 -->
			<input type="hidden" id="no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
			<input type="hidden" id="firstAccessYN" name="remittanceS.firstAccessYN" value="N">
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>搜索條件</th>
					<td class="line2_5">					
						<label for="#">搜索期間</label>
						<input type="radio" class="ml05" id="searchTimeBlock" name="remittanceS.searchTimeBlock" value="" />全部   
						<input type="radio" class="ml05" id="searchTimeBlock" name="remittanceS.searchTimeBlock" value="P"/>交易月份
						<select class="ml05" id="saleYear" style="width:65px;">
						</select>
						<select id="saleMonth" style="width:55px;">
                        	<option value="01">1月</option>
                        	<option value="02">2月</option>
                        	<option value="03">3月</option>
                        	<option value="04">4月</option>
                        	<option value="05">5月</option>
                        	<option value="06">6月</option>
                        	<option value="07">7月</option>
                        	<option value="08">8月</option>
                        	<option value="09">9月</option>
                        	<option value="10">10月</option>
                        	<option value="11">11月</option>
                        	<option value="12">12月</option>
                        </select>
						<br />
						<label for="#">搜索關鍵字</label>
						<select id="searchType" name="remittanceS.searchType">
                        	<option value="B">開發商帳號</option>
							<option value="C">開發商姓名</option>
                        </select>
                        <input type="text" class="txt" id="searchCont" name="remittanceS.searchCont" style="width:250px;" />
					</td>
					<td class="text_c" >
						<a class="btn" href="#" id="searchBtn"><strong>搜尋</strong></a>
					</td>
				</tr>
			</table>
			</s:form>
			<p class="fl mt25" id="labelSaleYyymm">
			<p class="fr mt20 mb05"><a class="btn_s" href="#"><span id="excel">下載Excel 檔</span></a></p>
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
					<th>開發商(帳號)</th>
					<th>結算類別</th>
					<th>交易月份</th>
					<th>付款幣別</th>
					<th>匯款金額</th>
					<th>詳細內容</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${empty remittanceS.firstAccessYN }">
					<td colspan="6" ><gm:html value="jsp.product.initSearchMsg"/></td>
				</c:when>
				<c:otherwise>	
					<c:choose>
					<c:when test="${totalCount eq 0 }">
						<td colspan="6" ><gm:html value="jsp.product.noSearchMsg"/></td>
					</c:when>
					<c:otherwise>
					<c:forEach items="${remittanceRstList}" var="remittance">
					<tr>
						<td>${remittance.mbrNm}(${remittance.mbrId})</td>
						<td>
							<c:choose>
							<c:when test="${remittance.adjYn eq 'Y'}">
							調帳
							</c:when>
							<c:when test="${remittance.adjYn eq 'N'}">
							結算
							</c:when>
							</c:choose>
						</td>
						<td>${fn:substring(remittance.saleYm,0,4)}-${fn:substring(remittance.saleYm,4,6)}</td>
						<td><gc:html code="${remittance.currencyUnit}"/></td>
						<td><g:text value="${remittance.rmtAmt}" format="###,###,###,###.##" /></td>
						<td><a href="#" onClick="JavaScript:viewPage('${remittance.saleYm}','${remittance.rmtReqYyyymm}','${remittance.mbrNo}','${remittance.adjYn}')" class="btn_s"><span>詳細內容</span></a></td>
					</tr> 
					</c:forEach>
					</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<s:form id="viewForm" name="viewForm"  theme="simple" >
				<input type="hidden" id="remittanceS.saleYm" name="remittanceS.saleYm" value="${remittanceS.saleYm}"/>
				<input type="hidden" id="remittanceS.searchTimeBlock" name="remittanceS.searchTimeBlock" value="${remittanceS.searchTimeBlock}"> <!-- 기간선택  검색값 -->
				<input type="hidden" id="remittanceS.searchType" name="remittanceS.searchType" value="${remittanceS.searchType}"/>
				<input type="hidden" id="remittanceS.searchCont" name="remittanceS.searchCont" value="${remittanceS.searchCont}"/>
				<input type="hidden" id="remittanceS.mbrId" name="remittanceS.mbrId" value="${remittanceS.mbrId}" > 
				<input type="hidden" id="remittanceS.mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}" >
				<input type="hidden" id="remittanceS.page.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
				<input type="hidden" id="firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
				<input type="hidden" id="remittanceSS.saleYm" name="remittanceSS.saleYm"}" />
				<input type="hidden" id="remittanceSS.rmtReqYyyymm" name="remittanceSS.rmtReqYyyymm"}" />
				<input type="hidden" id="remittanceSS.mbrNo" name="remittanceSS.mbrNo"  />
				<input type="hidden" id="remittanceSS.adjYn" name="remittanceSS.adjYn"  />
			</s:form>
			
			<!-- paging -->
			<div align="center">
		        <g:pageIndex item="${remittanceRstList}"/>
		    </div>
			<!-- //paging -->

	<hr>
	
</body>
</html>
