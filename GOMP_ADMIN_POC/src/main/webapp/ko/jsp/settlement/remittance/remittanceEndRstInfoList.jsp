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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/common.js"></script>
<script type="text/javascript">

//<![CDATA[
	
	$(document).ready(function(){

			
		//처리상태 작업
		<c:choose>
			<c:when test="${empty remittanceSS.rmtEndCd }">
			$("input[id='rmtEndCd'][value='']").attr("checked", "checked");
			</c:when>
			<c:otherwise>
				<c:set var="rval" value="${remittanceSS.rmtEndCd}" />;
				$("input[id='rmtEndCd'][value=<c:out value='${rval}'/>]").attr("checked", "checked");
			</c:otherwise>
		</c:choose>
		//처리상태 작업
		
		
		
		//검색어 작업
		<c:set var="searchType" value="${remittanceSS.searchType}" />;
		<c:set var="searchCont" value="${remittanceSS.searchCont}" />;
		var searchType = $.trim("<c:out value='${searchType}'/>");
		var searchCont = $.trim("<c:out value='${searchCont}'/>");
		$("#searchType > option[value=<c:out value='${searchType}'/>]").attr("selected", true);
		$("#searchCont").val(searchCont);
		//검색어 작업
		
		//송금결과 라벨작업
		<c:choose>
			<c:when test="${empty remittanceSS.firstAccessYN }">
				$("#excel").hide(); //초기페이지 엑셀 안보이게 하기
			</c:when>
			<c:otherwise>
				<c:set var="string" value="${remittanceSS.rmtReqYyyymm}" />;
				$("#labelRmtReqYyyymm").text("▶ ${fn:substring(string,0,4)}-${fn:substring(string,4,6)}월 송금 결과 현황");
				$("#excel").show(); //초기페이지 엑셀 안보이게 하기
			</c:otherwise>
		</c:choose>
		
		//엑셀버튼작업
		<c:if test="${totalCount > 0}">
			$("#excel").show(); 
		</c:if>	
		<c:if test="${empty remittanceSS.firstAccessYN || totalCount eq 0 }">
			$("#excel").hide(); //초기페이지와 데이타가 존재하지 않을시  엑셀 안보이게 하기
		</c:if>
		
		
		
	});
	
	
	$(function() {
		var frm = document.searchForm; 
		
		$("#searchBtn").click(function(event){
			event.preventDefault();
			
			$("#searchCont").val($.trim($("#searchCont").val()));
			if ($("#searchType").val() == "B"){ //개발자번호 값 셋팅
				$("#mbrId").val($("#searchCont").val());
			}else if($("#searchType").val() == "C"){ //개발자명 값 셋팅
				$("#mbrNm").val($("#searchCont").val());
			}
			frm.action="<c:url value="/settlement/remittance/remittanceEndRstInfoList.omp" />";
			frm.target="_self";
			frm.submit();
		});
		
		

		$("#listBtn").click(function(event){
			event.preventDefault();
			var sfrm = document.listForm;
			sfrm.action="<c:url value="/settlement/remittance/remittanceEndRstList.omp" />";
			sfrm.target="_self";
			sfrm.submit();
			
		});
		
		
		$("#excel").click(function(event){
			event.preventDefault();
			var sfrm = document.searchForm;
			sfrm.action="<c:url value="/settlement/remittance/remittanceEndRstInfoExcelList.omp" />";
			sfrm.target="_self";
			sfrm.submit();
		});
		
		$("#searchType").change(function() {
			$("#searchCont").val("");
		});
		
		
	});
	

	function goPage(no) {
	    var sfrm = document.searchForm
		$("#no").val(no);
	    sfrm.action="<c:url value="/settlement/remittance/remittanceEndRstInfoList.omp" />";
	    sfrm.target="_self";
	    sfrm.submit();
	}
	
	//송금결과사유  상세
	function popUpPage(saleYm,rmtReqYyyymm,mbrNo,adjYn){
		
		var frm = document.popUpForm;
		
		$("#popUpForm\\.remittanceSS\\.saleYm").val(saleYm);
		$("#popUpForm\\.remittanceSS\\.rmtReqYyyymm").val(rmtReqYyyymm);
		$("#popUpForm\\.remittanceSS\\.mbrNo").val(mbrNo);
		$("#popUpForm\\.remittanceSS\\.adjYn").val(adjYn);
		
		var targetUrl = "<c:url value="/settlement/remittance/popUpRmtReasonInfo.omp"/>";
		popup_center('', 430, 290, 0, 0);
		frm.target = 'popup';
		frm.action = targetUrl;
		frm.submit();
		
	}
	
//]]>
</script>

</head>
<body>
	
			<div id="location">
				Home &gt; 정산관리 &gt; 정산현황  &gt; <strong>송금결과현황</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">송금결과현황</h1>
			<p>송금 결과 현황 상세를 조회 합니다</p>
			<s:form id="popUpForm" name="popUpForm"  theme="simple" ><!-- 송금결과 사유에 보낼 파라미터 -->
				<input type="hidden" id="popUpForm.remittanceSS.saleYm" name="remittanceSS.saleYm" > 
				<input type="hidden" id="popUpForm.remittanceSS.mbrNo" name="remittanceSS.mbrNo" > 
				<input type="hidden" id="popUpForm.remittanceSS.rmtReqYyyymm" name="remittanceSS.rmtReqYyyymm" >
				<input type="hidden" id="popUpForm.remittanceSS.adjYn" name="remittanceSS.adjYn" >
			</s:form>
			<s:form id="searchForm" name="searchForm"  theme="simple" >
			<input type="hidden" id="rmtReqYyyymm" name="remittanceSS.rmtReqYyyymm" value="${remittanceSS.rmtReqYyyymm}"> <!-- 송금년월 검색값 -->
			<input type="hidden" id="mbrNo" name="remittanceSS.mbrId" value="${remittanceSS.mbrNo}"> <!-- 개발자ID 검색값 -->
			<input type="hidden" id="mbrNm" name="remittanceSS.mbrNm" value="${remittanceSS.mbrNm}"> <!-- 개발자명 검색값 -->
			<input type="hidden" id="no" name="remittanceSS.page.no" value="${remittanceSS.page.no}" />
			<input type="hidden" id="firstAccessYN" name="remittanceSS.firstAccessYN" value="N">
			<!-- 전페이지 리스트로 이동하기 위한 hidden 변수 -->
			<input type="hidden" id="remittanceS.searchStartYm" name="remittanceS.searchStartYm" value="${remittanceS.searchStartYm}"/>
			<input type="hidden" id="remittanceS.searchEndYm" name="remittanceS.searchEndYm" value="${remittanceS.searchEndYm}">
			<input type="hidden" id="remittanceS.page.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
			<input type="hidden" id="remittanceS.firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>검색조건</th>
					<td class="align_td line2_5">					
						<label for="#">송금상태</label>
						<input type="radio" class="ml10" id="rmtEndCd" name="remittanceSS.rmtEndCd" value="" /> 전체
						<input type="radio" class="ml10" id="rmtEndCd" name="remittanceSS.rmtEndCd" value="PD004124"/> 송금완료
						<input type="radio" class="ml10" id="rmtEndCd" name="remittanceSS.rmtEndCd" value="PD004122"/> 재송금
						<input type="radio" class="ml10" id="rmtEndCd" name="remittanceSS.rmtEndCd" value="PD004123"/> 송금포기
						<input type="radio" class="ml10" id="rmtEndCd" name="remittanceSS.rmtEndCd" value="PD004121"/> 이월처리
						<br />
						<label for="#">검색어</label>						
						<select id="searchType" name="remittanceSS.searchType" class="ml10">
                        	<option value="B">개발자 ID</option>
							<option value="C">개발자 이름</option>
                        </select>
						<input type="text" class="txt" id="searchCont" name="remittanceSS.searchCont" value="${remittanceSS.searchCont}"/>
					</td>
					<td class="text_c" >
						<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
					</td>
				</tr>
			</table>
			<p class="fl mt25 mb05" id="labelRmtReqYyyymm"></p>
			<p class="fr mt20 mb05"><a class="btn_s" href="#"><span id="excel">Excel 다운로드</span></a></p>
			<table class="tabletype02">
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
					<th>개발자(ID)</th>
					<th>판매월</th>
					<th>정산종류</th>
					<th>지불화폐</th>
					<th>송금금액</th>					
					<th>송금상태</th>				
					<th>내역보기</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${empty remittanceSS.firstAccessYN }">
					<td colspan="7" >검색 결과가 없습니다.</td>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${totalCount eq 0 }">
						<td colspan="7" >검색조건을 선택하신 후 ‘검색’ 버튼을 클릭하세요.</td>
					</c:when>
					<c:otherwise>
					<c:forEach items="${remittanceRstList}" var="remittance">
					<tr>
						<td>${remittance.mbrNm}/(${remittance.mbrNo})</td>
						<td><g:text value="${remittance.saleYm}" format="R####-##" /></td>
						<td><c:choose>
							<c:when test="${remittance.adjYn} eq 'Y'">
							정산
							</c:when>
							<c:when test="${remittance.adjYn} eq 'N'">
							조정
							</c:when>
							</c:choose>
						</td>
						<td><gc:html code="${remittance.currencyUnit}"/></td>
						<td><g:text value="${remittance.rmtAmt}" format="###,###,###,###.##" /></td>
						<td><gc:html code="${remittance.rmtEndCd}"/></td>
						<td><!-- 송금완료이외의 상태만 링크 -->
						<c:choose>
						<c:when test="${(remittance.rmtEndCd eq 'PD004122' ||remittance.rmtEndCd eq 'PD004123') && remittance.rmtReason ne '' }"><!-- 재송금, 송금포기 상태이면서 송금결과사유 존재시   -->
							<a class="btn_s" id="viewPage" href="#" onClick="JavaScript:popUpPage('${remittance.saleYm}','${remittance.rmtReqYyyymm}','${remittance.mbrNo,,'${remittance.adjYn}')"><span>자세히보기</span></a>
						<!--  <a class="btn_s" id="viewPage" href="#" onClick="JavaScript:alert('작업중')"><span>자세히보기</span></a>  -->
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
			</s:form>
			<p class="btn_wrap text_r mt05"><a class="btn" href="#" id="listBtn"><span>목록</span></a></p>
			<s:form id="listForm" name="listForm"  theme="simple" >
				<input type="hidden" id="remittanceS.searchStartYm" name="remittanceS.searchStartYm" value="${remittanceS.searchStartYm}"/>
				<input type="hidden" id="remittanceS.searchEndYm" name="remittanceS.searchEndYm" value="${remittanceS.searchEndYm}">
				<input type="hidden" id="remittanceS.page.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
				<input type="hidden" id="remittanceS.firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
			</s:form>
			<!-- paging -->
			<div align="center">
		        <g:pageIndex item="${remittanceRstList}"/>
		    </div>
			<!-- //paging -->
	
</body>
</html>
