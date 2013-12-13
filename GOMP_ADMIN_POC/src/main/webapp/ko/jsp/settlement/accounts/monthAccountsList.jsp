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
		<c:if test="${not empty accountsS.processMessage   }">
			alert("<gm:string value='${accountsS.processMessage}'/>");
		</c:if>
		
		//검색어 작업
		<c:set var="searchType" value="${accountsS.searchType}" />;
		<c:set var="searchCont" value="${accountsS.searchCont}" />;
		var searchType = $.trim("<c:out value='${searchType}'/>");
		var searchCont = $.trim("<c:out value='${searchCont}'/>");
		//$("#accountsS\\.searchType > option[value=<c:out value='${searchType}'/>]").attr("selected", true);
		$("#accountsS\\.searchType").val('${searchType}');
		$("#accountsS\\.searchCont").val(searchCont);
		//검색어 작업
		
		//엑셀, 정산마감 버튼 작업
		$("#excel").hide(); //기본적으로 엑셀다운로드 버튼 숨김
		<c:if test="${totalCount > 0}">
				$("#excel").show(); //데이타가 존재할시에만  엑셀 보이게 하기
		</c:if>
		$("#accountsEnd").hide(); //기본적으로 정산마감 버튼 숨김, 정산상태코드가 판매마감일시에 버튼 보임
		<c:if test="${(accountsS.adjStatCd == 'PD004101') && (totalCount > 0)}">
			$("#accountsEnd").show();
		</c:if>
		
		
	});
	
	
	$(function() {
		var frm = document.searchForm; 
		
		$("#searchBtn").click(function(event){
			event.preventDefault();
			
			$("#accountsS\\.searchCont").val($.trim($("#accountsS\\.searchCont").val()));
			if ($("#accountsS\\.searchType").val() == "B"){ //개발자번호 값 셋팅
				$("#accountsS\\.mbrId").val($("#accountsS\\.searchCont").val());
			}else if($("#accountsS\\.searchType").val() == "C"){ //개발자명 값 셋팅
				$("#accountsS\\.mbrNm").val($("#accountsS\\.searchCont").val());
			}else if($("#accountsS\\.searchType").val() == "D"){ //상품명 값 셋팅
				$("#accountsS\\.prodNm").val($("#accountsS\\.searchCont").val());
			}
			frm.action="<c:url value="/settlement/accounts/monthAccountsList.omp" />";
			frm.submit();
		});
		
		
		$("#excel").click(function(event){
			event.preventDefault();
			var sfrm = document.searchForm;
			sfrm.action="<c:url value="/settlement/accounts/monthAccountsExcelList.omp" />";
			sfrm.submit();
		});
		
		
		$("#accountsEnd").click(function(event){
			event.preventDefault();
			var sfrm = document.searchForm;
			sfrm.action="<c:url value="/settlement/accounts/monthAccountsBundleEnd.omp" />";
			sfrm.submit();
		});
		
		$("#accountsS\\.searchType").change(function() {
			$("#accountsS\\.searchCont").val("");
		});
		
		
	});
	

	function goPage(no) {
	    var sfrm = document.searchForm
		$("#accountsS\\.page\\.no").val(no);
	    sfrm.action="<c:url value="/settlement/accounts/monthAccountsList.omp" />";
		sfrm.submit();
	}
	
	
	
//]]>
</script>

</head>
<body>

			<div id="location">
				Home &gt; 정산관리 &gt; 정산현황 &gt; <strong>당월정산현황</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">당월정산현황</h1>
			<p>당월 정산 현황 내역을 확인하실 수 있습니다.</p>
			<s:form id="searchForm" name="searchForm"  theme="simple" >
			<input type="hidden" id="accountsS.mbrId" name="accountsS.mbrId" value="${accountsS.mbrId}"> <!-- 개발자번호 검색값 -->
			<input type="hidden" id="accountsS.mbrNm" name="accountsS.mbrNm" value="${accountsS.mbrNm}"> <!-- 개발자명 검색값 -->
			<input type="hidden" id="accountsS.prodNm" name="accountsS.prodNm" value="${accountsS.prodNm}"> <!-- 상품명 검색값 -->
			<input type="hidden" id="accountsS.page.no" name="accountsS.page.no" value="${accountsS.page.no}" />
			<input type="hidden" id="accountsS.firstAccessYN" name="accountsS.firstAccessYN" value="N">
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>검색조건</th>
					<td class="align_td">
						<select id="accountsS.searchType" name="accountsS.searchType">
							<option value="B">개발자 ID</option>
							<option value="C">개발자 이름</option>
							<option value="D">상품명</option>
						</select>
						<input type="text" class="txt" id="accountsS.searchCont" name="accountsS.searchCont" style="width:250px;" />
					</td>
					<td rowspan="6" class="text_c" >
						<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
					</td>
				</tr>
			</table>
			</s:form>
			<p class="fr mt20 mb05"><a class="btn_s" href="#" ><span id="excel">Excel 다운로드</span></a> <a class="btn_s" href="#" ><span id="accountsEnd">정산마감</span></a></p>
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
				</colgroup>
				<thead>
				<tr>
					<th>개발자(ID)</th>
					<th>AID</th>
					<th>상품명</th>
					<th>판매월</th>
					<th>상품가격</th>
					<th>정산율</th>
					<th>판매대금</th>
					<th>정산금액</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${empty accountsS.firstAccessYN }">
					<td colspan="8" >검색 결과가 없습니다.</td>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${totalCount eq 0 }">
						<td colspan="8" >검색조건을 선택하신 후 ‘검색’ 버튼을 클릭하세요.</td>
					</c:when>
					<c:otherwise>
					<c:forEach items="${accountsList}" var="accounts">
					<tr>
						<td>${accounts.mbrNmId}</td>
						<td>${accounts.prodId}</td>
						<td>${accounts.prodNm}</td>
						<td><g:text value="${accounts.settYyyymm}" format="L####-##" /></td>
						<td><g:text value="${accounts.prodPrc}" format="R###,###,###,###" /></td>
						<td>${100-accounts.settlRt}:${accounts.settlRt}</td>
						<td><g:text value="${accounts.totlAmt}" format="R###,###,###,###" /></td>
						<td><g:text value="${accounts.devAmtNum}" format="###,###,###.##" /></td>
					</tr>
					</c:forEach>
					</c:otherwise>
					</c:choose>
				</c:otherwise>
				</c:choose>
				
				
				</tbody>
			</table>
			<!-- paging -->
			<div align="center">
		        <g:pageIndex item="${accountsList}"/>
		    </div>
			<!-- //paging -->

</body>
</html>
