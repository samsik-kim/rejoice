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
<script language="javascript">
//<![CDATA[
	
	$(document).ready(function(){

		//alert("상태코드 : ${accountsS.adjStatCd}");
		
		//처리결과 메세지가 존재하면 화면에 출력을 해준다.
		<c:if test="${not empty accountsS.processMessage   }">
			alert("<gm:string value='${accountsS.processMessage}'/>");
		</c:if>
		
	});
	
	
	$(function() {
		var frm = document.searchForm; 
		
		
		
		//검색년도
		var curYear = eval(getYear());
		var curMonth = getMonth();
		
		<c:choose>
			<c:when test="${empty accountsS.saleYm }">
				//alert("first");
				curYear = eval(getYear());
				curMonth = getMonth();
			</c:when>
			<c:otherwise>
				<c:set var="string" value="${accountsS.saleYm}" />;
				curYear =  eval('<c:out value="${fn:substring(string,0,4)}"/>');
				curMonth = '<c:out value="${fn:substring(string,4,6)}"/>';
			</c:otherwise>
		</c:choose>	
		
		
		for ( var i=curYear-10; i <=curYear+10 ; i++){
			if(i==curYear){
				$("#saleYyyy").append("<option value='"+i+"' selected>"+i+"</option>");	
			}else{
				$("#saleYyyy").append("<option value='"+i+"'>"+i+"</option>");
			}
			
		}
		$("#saleMm").val(curMonth);
		
		$("input[id='searchTimeBlock'][value='${accountsS.searchTimeBlock}']").attr("checked", "checked"); //검색 전체 해당월 체크
		//검색년도
			
		//처리상태 작업
		<c:choose>
			<c:when test="${empty accountsS.aggtStatCd }">
			$("input[id='aggtStatCd'][value='']").attr("checked", "checked");
			</c:when>
			<c:otherwise>
				<c:set var="rval" value="${accountsS.aggtStatCd}" />;
				$("input[id='aggtStatCd'][value=<c:out value='${rval}'/>]").attr("checked", "checked");
			</c:otherwise>
		</c:choose>
		//처리상태 작업
		
		
		//검색어 작업
		<c:set var="searchType" value="${accountsS.searchType}" />;
		<c:set var="searchCont" value="${accountsS.searchCont}" />;
		var searchType = $.trim("<c:out value='${searchType}'/>");
		var searchCont = $.trim("<c:out value='${searchCont}'/>");
		$("#searchType > option[value=<c:out value='${searchType}'/>]").attr("selected", true);
		//$("#searchType").val('${searchType}');
		$("#searchCont").val(searchCont);
		//검색어 작업
		
		
		
		//local, national 영수증 버튼처리
		$("#localReceipt").hide();
		$("#nationalReceipt").hide();
		
		<c:set var="localReceipt" value="${fn:trim(accounts.localSum)}"></c:set>
		<c:set var="nationalReceipt" value="${fn:trim(accounts.usSum)}"></c:set>
		
		<c:if test="${localReceipt ne '0'  && localReceipt ne '' }">
			$("#localReceipt").show(); 
		</c:if>
		<c:if test="${nationalReceipt ne '0' && nationalReceipt ne ''}">
			$("#nationalReceipt").show(); 
		</c:if>
		
		//(정산마감 _ settlement Report 제공 상태)에만 버튼 출력
		//alert("월별 정산 상태코드 : "+"${accountsS.adjStatCd}");
		$("#bundleProcess").hide();
		<c:if test="${accountsS.wholeAdjStatCd=='PD004102'  }">
			$("#bundleProcess").show(); 
		</c:if>
		
		
		
		
		
		
		
		
		
		
		$("#searchBtn").click(function(event){
			event.preventDefault();
			
			if(($('input[id="searchTimeBlock"]:checked').val()=="P")){ //부분날자 검색일대 송금월 처리
				//alert("xxxx");
				$("#saleYm").val($("#saleYyyy").val() + $("#saleMm").val());
				//alert($("#rmtReqYyyymm").val());
			}else if (($('input[id="searchTimeBlock"]:checked').val()=="")){ //전체날자 선택시 
				$("#saleYm").val("");
			}
			$("#searchCont").val($.trim($("#searchCont").val()));
			if ($("#searchType").val() == "B"){ //개발자ID 값 셋팅
				$("#mbrId").val($("#searchCont").val());
			}else if($("#searchType").val() == "C"){ //개발자명 값 셋팅
				$("#mbrNm").val($("#searchCont").val());
			}
			frm.action="<c:url value="/settlement/accounts/receiptProcessList.omp" />";
			frm.submit();
		});
		
		
		
		$("#saleYyyy").change(function() {
			var selectedYear = eval($(this).val());
			var curCnt = $("#saleYyyy option").size();
			
			for ( var i=1; i <=curCnt ; i++){
				$("#saleYyyy option:eq(0)").remove();
			}
			
			for ( var i=selectedYear-10; i <=selectedYear+10 ; i++){
				$("#saleYyyy").append("<option value='"+i+"'>"+i+"</option>");
			}
			$("#saleYyyy > option[value="+selectedYear+"]").attr("selected", true);
			//alert($(this).val());
			//alert($(this).children("option:selected").text());
		});
		
		
		$("#searchType").change(function() {
			$("#searchCont").val("");
		});
		
		
		//일괄 영수증 처리 마감 처리
		$("#bundleProcess").click(function(event){
			
			var frm = document.viewForm;
			event.preventDefault();
			
			//$("#accountsSS\\.saleYyyymm").val($("#saleYyyy").val() + $("#saleMm").val());	
			
			/*
			if(($('input[id="searchTimeBlock"]:checked').val()=="P")){ //부분날자 검색일대 판매월 처리
				$("#viewForm.\\accountsSS\\.saleYm").val($("#saleYyyy").val() + $("#saleMm").val());
			}else if (($('input[id="searchTimeBlock"]:checked').val()=="")){ //전체날자 선택시 
				alert("<gm:string value='jsp.settlement.accounts.receiptProcessList.inputmonth'/>");
				return;
			}
			*/
			
			if(showValidate('viewForm', 'default', "<gm:string value=""/>")){
				$("#searchCont").val($.trim($("#searchCont").val()));
				if ($("#searchType").val() == "B"){ //개발자번호 값 셋팅
					$("#viewForm.\\accountsS\\.mbrId").val($("#searchCont").val());
				}else if($("#searchType").val() == "C"){ //개발자명 값 셋팅
					$("#viewForm.\\accountsS\\.mbrNm").val($("#searchCont").val());
				}
				
				if (confirm($("#saleYyyy").val()+"-"+$("#saleMm").val()+"월 정산 마감하시겠습니까?")){
					frm.action="<c:url value="/settlement/accounts/updateReceiptProcessBundleEnd.omp"/>";
					frm.submit();	
				}
			}
			
		});
		
				
	});
	

	function goPage(no) {
	    var sfrm = document.searchForm
		$("#no").val(no);
	    sfrm.action="<c:url value="/settlement/accounts/receiptProcessList.omp" />";
		sfrm.submit();
	}
	
	//송금처리 상세
	function viewPage(saleYm,mbrNo,adjYn){
		var frm = document.viewForm;
		
		$("#accountsSS\\.saleYm").val(saleYm);
		$("#accountsSS\\.mbrNo").val(mbrNo);
		$("#accountsSS\\.adjYn").val(adjYn);
		
		frm.action="<c:url value="/settlement/accounts/receiptProcessInfo.omp"/>";
		frm.submit();
	}
	
	//송금처리 수정
	function editPage(saleYm,mbrNo,adjYn){
		var frm = document.viewForm;
		
		$("#accountsSS\\.saleYm").val(saleYm);
		$("#accountsSS\\.mbrNo").val(mbrNo);
		$("#accountsSS\\.adjYn").val(adjYn);
		
		frm.action="<c:url value="/settlement/accounts/editStartReceiptProcessInfo.omp"/>";
		frm.submit();
	}
	
	
	//local, national 영수증 다운로드
	function excelLoNaDown(currencyUnit) {
		
		var frm = document.searchForm;
		
		if(($('input[id="searchTimeBlock"]:checked').val()=="P")){ //부분날자 검색일대 송금월 처리
			$("#saleYm").val($("#saleYyyy").val() + $("#saleMm").val());
		}else if (($('input[id="searchTimeBlock"]:checked').val()=="")){ //전체날자 선택시 
			$("#saleYm").val("");
		}
		$("#searchCont").val($.trim($("#searchCont").val()));
		if ($("#searchType").val() == "B"){ //개발자ID 값 셋팅
			$("#mbrId").val($("#searchCont").val());
		}else if($("#searchType").val() == "C"){ //개발자명 값 셋팅
			$("#mbrNm").val($("#searchCont").val());
		}
		
		$("#currencyUnit").val(currencyUnit);
		
		frm.action="<c:url value="/settlement/accounts/receiptProcessLoNaExcelList.omp" />";
		frm.submit();	
	}
	
	
//]]>
</script>

</head>
<body>

			<div id="location">
				Home &gt; 정산관리 &gt; 정산현황 &gt; <strong>영수증처리</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">영수증처리</h1>
			<p>영수증 처리 현황을 확인하실 수 있습니다.</p>
			<s:form id="searchForm" name="searchForm"  theme="simple" >
			<input type="hidden" id="saleYm" name="accountsS.saleYm" value="${accountsS.saleYm}"> <!-- 판매월 -->
			<input type="hidden" id="mbrId" name="accountsS.mbrId" value="${accountsS.mbrId}"> <!-- 개발자ID 검색값 -->
			<input type="hidden" id="mbrNm" name="accountsS.mbrNm" value="${accountsS.mbrNm}"> <!-- 개발자명 검색값 -->
			<input type="hidden" id="no" name="accountsS.page.no" value="${accountsS.page.no}" />
			<input type="hidden" id="firstAccessYN" name="accountsS.firstAccessYN" value="N">
			<input type="hidden" id="currencyUnit" name="accountsS.currencyUnit" > <!-- 통화단위코드 -->
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th rowspan="3">검색조건</th>
					<td class="align_td line2_5">
						<label for="#">검색기간</label>				
						<input type="radio" class="ml05" id="searchTimeBlock" name="accountsS.searchTimeBlock" value="" />전체 
						<input type="radio" class="ml05" id="searchTimeBlock" name="accountsS.searchTimeBlock" value="P"/>판매월
						<select class="ml15" id="saleYyyy" name="saleYyyy" style="width:60px;"></select>
						<select id="saleMm" name="saleMm" style="width:50px;">
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
						<br />
						<label for="#">신청여부</label>					
						<input type="radio" class="ml05" id="aggtStatCd" name="accountsS.aggtStatCd" value="" />전체 
						<input type="radio" class="ml05" id="aggtStatCd" name="accountsS.aggtStatCd" value="PD004102"/>송금미신청  <!-- (정산마감 _ settlement Report 제공 상태) -->
						<input type="radio" class="ml05" id="aggtStatCd" name="accountsS.aggtStatCd" value="PD004103"/>수신대기  <!-- (송금신청_수신대기) -->
						<input type="radio" class="ml05" id="aggtStatCd" name="accountsS.aggtStatCd" value="PD004104"/>수신완료 <!-- (송금신청 완료_영수증 처리 마감) -->
						<br />
						<label for="#">검색어</label>	
						<select class="ml05" id="searchType" name="accountsS.searchType">
							<option value="B">개발자 ID</option>
							<option value="C">개발자 이름</option>
						</select>
						<input type="text" id="searchCont" name="accountsS.searchCont" class="txt" style="width:250px;" />
					</td>
					<td class="text_c" >
						<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
					</td>
				</tr>
			</table>
			<p class="fr mt20 mb05">
				<a class="btn_s" href="#" id="bundleProcess"><span >영수증처리마감</span></a>
				<a class="btn_s" href="JavaScript:excelLoNaDown('US005301')" ><span id="localReceipt">Local 파일</span></a>
				<a class="btn_s" href="JavaScript:excelLoNaDown('US005302')" ><span id="nationalReceipt">National 파일</span></a>
			</p>
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
					<th>지불화폐</th>
					<th>송금금액</th>
					<th>신청일</th>
					<th>신청여부</th>
					<th>상세내역</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${empty accountsS.firstAccessYN }">
					<td colspan="7" >검색 결과가 없습니다.</td>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${totalCount eq 0 }">
						<td colspan="7" >검색조건을 선택하신 후 ‘검색’ 버튼을 클릭하세요.</td>
					</c:when>
					<c:otherwise>
					<c:forEach items="${accountsList}" var="accountsq">
					<tr>
						<td>${accountsq.mbrNm}(${accountsq.mbrId})</td>
						<td><g:text value="${accountsq.saleYm}" format="L####-##" /></td>
						<td><gc:html code="${accountsq.currencyUnit}"/></td>
						<td><g:text value="${accountsq.totlPayAmt}" format="###,###,###,###.##" /></td>
						<td><g:text value="${accountsq.rmtReqYyyymm}" format="L####-##-##" /></td>
						<td><gc:html code="${accountsq.aggtStatCd}"/></td>
						<td>
							<c:choose>
							<c:when test="${ ( accountsq.aggtStatCd eq 'PD004102' 
											|| accountsq.aggtStatCd eq 'PD004103' 
											|| accountsq.aggtStatCd eq 'PD004107' 
											|| accountsq.aggtStatCd eq 'PD004104')}"><!-- 송금미신청, 수신대기(영수증),이월처리,수신완료 상태의 데이타만 수정가능  -->
								<a class="btn_s" id="viewPage" href="#" onClick="JavaScript:editPage('${accountsq.saleYm}','${accountsq.mbrNo}','${accountsq.adjYn}')"><span>자세히보기</span></a>
							</c:when>
							<c:otherwise>
								<a class="btn_s" id="viewPage" href="#" onClick="JavaScript:viewPage('${accountsq.saleYm}','${accountsq.mbrNo}','${accountsq.adjYn}')"><span>자세히보기</span></a>
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
			<s:form id="viewForm" name="viewForm"  theme="simple" >
				<input type="hidden" id="accountsS.searchTimeBlock" name="accountsS.searchTimeBlock" value="${accountsS.searchTimeBlock}"/>
				<input type="hidden" id="accountsS.saleYm" name="accountsS.saleYm" value="${accountsS.saleYm}"/>
				<input type="hidden" id="accountsS.aggtStatCd" name="accountsS.aggtStatCd" value="${accountsS.aggtStatCd}">
				<input type="hidden" id="accountsS.searchType" name="accountsS.searchType" value="${accountsS.searchType}"/>
				<input type="hidden" id="accountsS.searchCont" name="accountsS.searchCont" value="${accountsS.searchCont}"/>
				<input type="hidden" id="accountsS.mbrId" name="accountsS.mbrId" value="${accountsS.mbrId}" > 
				<input type="hidden" id="accountsS.mbrNm" name="accountsS.mbrNm" value="${accountsS.mbrNm}" > 
				<input type="hidden" id="accountsS.page.no" name="accountsS.page.no" value="${accountsS.page.no}" />
				<input type="hidden" id="accountsS.firstAccessYN" name="accountsS.firstAccessYN" value="${accountsS.firstAccessYN}">
				<input type="hidden" id="accountsSS.saleYm" name="accountsSS.saleYm" value="${accountsS.saleYm}"/>
				<input type="hidden" id="accountsSS.mbrNo" name="accountsSS.mbrNo"  />
				<input type="hidden" id="accountsSS.adjYn" name="accountsSS.adjYn"  />
			</s:form>
			
			
			<!-- paging -->
			<div align="center">
		        <g:pageIndex item="${remittanceRstList}"/>
		    </div>
			<!-- //paging -->

	<hr>
</body>
</html>
