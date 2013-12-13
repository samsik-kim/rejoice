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
				$("#saleYear").append("<option value='"+i+"' selected>"+i+"</option>");	
			}else{
				$("#saleYear").append("<option value='"+i+"'>"+i+"</option>");
			}
			
		}
		
		$("#saleMonth").val(curMonth);
		$("input[id='searchTimeBlock'][value='${remittanceS.searchTimeBlock}']").attr("checked", "checked"); //검색 전체 해당월 체크
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
			$("#labelSaleYyymm").text("▶ ${fn:substring(string,0,4)}-${fn:substring(string,4,6)}월 송금 포기  현황");
		</c:if>
		
		
		
		
		
		
		
		
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
				Home &gt; 정산관리 &gt; 정산현황  &gt; <strong>송금포기현황</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">송금포기현황</h1>
			
			<p>송금 포기 현황 내역을 확인하실 수 있습니다.</p>
			<s:form id="searchForm" name="searchForm"  theme="simple" >
			<input type="hidden" id="saleYm" name="remittanceS.saleYm" value="${remittanceS.saleYm}"> <!-- 판매월 -->
			<input type="hidden" id="mbrId" name="remittanceS.mbrId" value="${remittanceS.mbrId}"> <!-- 개발자번호 검색값 -->
			<input type="hidden" id="mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}"> <!-- 개발자명 검색값 -->
			<input type="hidden" id="no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
			<input type="hidden" id="firstAccessYN" name="remittanceS.firstAccessYN" value="N">
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>검색조건</th>
					<td class="line2_5">					
						<label for="#">검색기간</label>
						<input type="radio" class="ml05" id="searchTimeBlock" name="remittanceS.searchTimeBlock" value="" />전체   
						<input type="radio" class="ml05" id="searchTimeBlock" name="remittanceS.searchTimeBlock" value="P"/>판매월
						<select class="ml05" id="saleYear" style="width:60px;">
						</select>
						<select id="saleMonth" style="width:50px;">
                        	<option value="01">1월</option>
                        	<option value="02">2월</option>
                        	<option value="03">3월</option>
                        	<option value="04">4월</option>
                        	<option value="05">5월</option>
                        	<option value="06">6월</option>
                        	<option value="07">7월</option>
                        	<option value="08">8월</option>
                        	<option value="09">9월</option>
                        	<option value="10">10월</option>
                        	<option value="11">11월</option>
                        	<option value="12">12월</option>
                        </select>
						<br />
						<label for="#">검색어</label>
						<select id="searchType" name="remittanceS.searchType">
                        	<option value="B">개발자 ID</option>
							<option value="C">개발자 이름</option>
                        </select>
                        <input type="text" class="txt" id="searchCont" name="remittanceS.searchCont" style="width:250px;" />
					</td>
					<td class="text_c" >
						<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
					</td>
				</tr>
			</table>
			</s:form>
			<p class="fl mt25" id="labelSaleYyymm">
			<p class="fr mt20 mb05"><a class="btn_s" href="#"><span id="excel">Excel 다운로드</span></a></p>
			<table class="tabletype02">
				<colgroup>
					<col >
					<col >
					<col >
					<col >
					<col >
				</colgroup>
				<thead>
				<tr>
					<th>개발자(ID)</th>
					<th>판매월</th>
					<th>지불화폐</th>
					<th>송금금액</th>
					<th>상세내역</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${empty remittanceS.firstAccessYN }">
					<td colspan="5" >검색 결과가 없습니다.</td>
				</c:when>
				<c:otherwise>	
					<c:choose>
					<c:when test="${totalCount eq 0 }">
						<td colspan="5" >검색조건을 선택하신 후 ‘검색’ 버튼을 클릭하세요.</td>
					</c:when>
					<c:otherwise>
					<c:forEach items="${remittanceRstList}" var="remittance">
					<tr>
						<td>${remittance.mbrNm}(${remittance.mbrId})</td>
						<td>${fn:substring(remittance.saleYm,0,4)}-${fn:substring(remittance.saleYm,4,6)}</td>
						<td><gc:html code="${remittance.currencyUnit}"/></td>
						<td><g:text value="${remittance.rmtAmt}" format="R###,###,###,###" /></td>
						<td><a href="#" onClick="JavaScript:viewPage('${remittance.saleYm}','${remittance.rmtReqYyyymm}','${remittance.mbrNo}','${remittance.adjYn}')" class="btn_s"><span>자세히보기</span></a></td>
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
