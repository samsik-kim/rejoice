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

		//alert("상태코드 : ${remittanceS.adjStatCd}");
		
		//처리결과 메세지가 존재하면 화면에 출력을 해준다.
		<c:if test="${not empty remittanceS.processMessage   }">
			alert("<gm:string value='${receiptS.processMessage}'/>");
		</c:if>
		
		
			
	});
	
	
	$(function() {
		var frm = document.searchForm; 
		
		
		//검색년도
		var curYear = eval(getYear());
		var curMonth = getMonth();
		
		<c:choose>
			<c:when test="${empty remittanceS.rmtReqYyyymm }">
				//alert("first");
				curYear = eval(getYear());
				curMonth = getMonth();
			</c:when>
			<c:otherwise>
				<c:set var="string" value="${remittanceS.rmtReqYyyymm}" />;
				curYear =  eval('<c:out value="${fn:substring(string,0,4)}"/>');
				curMonth = '<c:out value="${fn:substring(string,4,6)}"/>';
			</c:otherwise>
		</c:choose>	
		
		
		for ( var i=curYear-10; i <=curYear+10 ; i++){
			if(i==curYear){
				$("#rmtReqYyyy").append("<option value='"+i+"' selected>"+i+"</option>");	
			}else{
				$("#rmtReqYyyy").append("<option value='"+i+"'>"+i+"</option>");
			}
			
		}
		$("#rmtReqmm").val(curMonth);
		
		
		$("input[id='searchTimeBlock'][value='${remittanceS.searchTimeBlock}']").attr("checked", "checked"); //검색 전체 해당월 체크
		//검색년도
			
		//처리상태 작업
		<c:choose>
			<c:when test="${empty remittanceS.rmtEndCd }">
			$("input[id='rmtEndCd'][value='']").attr("checked", "checked");
			</c:when>
			<c:otherwise>
				<c:set var="rval" value="${remittanceS.rmtEndCd}" />;
				$("input[id='rmtEndCd'][value=<c:out value='${rval}'/>]").attr("checked", "checked");
			</c:otherwise>
		</c:choose>
		//처리상태 작업
		
		
		
		//검색어 작업
		<c:set var="searchType" value="${remittanceS.searchType}" />;
		<c:set var="searchCont" value="${remittanceS.searchCont}" />;
		var searchType = $.trim("<c:out value='${searchType}'/>");
		var searchCont = $.trim("<c:out value='${searchCont}'/>");
		$("#searchType > option[value=<c:out value='${searchType}'/>]").attr("selected", true);
		$("#searchCont").val(searchCont);
		//검색어 작업
		
		
		//송금결과 라벨, 엑셀버튼, 송금결과마감 버튼작업
		$("#excel").hide();
		<c:if test="${totalCount > 0}">
			$("#excel").show(); 
		</c:if>
		<c:if test="${not empty remittanceS.rmtReqYyyymm   }">
			<c:set var="string" value="${remittanceS.rmtReqYyyymm}" />;	
			$("#labelRmtReqYyyymm").text("▶ ${fn:substring(string,0,4)}-${fn:substring(string,4,6)}월 송금 결과 현황");
		</c:if>
		
		//송금신청대기시, 송금결과 완료시에만 버튼 출력
		//alert("월별 정산 상태코드 : "+"${remittanceS.adjStatCd}");
		$("#bundleProcess").hide();
		$("#bundleProcessEnd").hide();
		<c:if test="${remittanceS.adjStatCd=='PD004104'  }">
			$("#bundleProcess").show(); 
		</c:if>
		<c:if test="${remittanceS.adjStatCd=='PD004106'  }">
			$("#bundleProcessEnd").show(); 
		</c:if>
		
		
		
		$("#searchBtn").click(function(event){
			event.preventDefault();
			
			$("#rmtReqYyyymm").val($("#rmtReqYyyy").val() + $("#rmtReqmm").val());	
			
			if(($('input[id="searchTimeBlock"]:checked').val()=="P")){ //부분날자 검색일대 송금월 처리
				//alert("xxxx");
				$("#rmtReqYyyymm").val($("#rmtReqYyyy").val() + $("#rmtReqmm").val());
				//alert($("#rmtReqYyyymm").val());
			}else if (($('input[id="searchTimeBlock"]:checked').val()=="")){ //전체날자 선택시 
				$("#rmtReqYyyymm").val("");
			}
			
			
			$("#searchCont").val($.trim($("#searchCont").val()));
			if ($("#searchType").val() == "B"){ //개발자번호 값 셋팅
				$("#mbrId").val($("#searchCont").val());
			}else if($("#searchType").val() == "C"){ //개발자명 값 셋팅
				$("#mbrNm").val($("#searchCont").val());
			}
			frm.action="<c:url value="/settlement/remittance/remittanceRstList.omp" />";
			frm.target="_self";
			frm.submit();
		});
		
		
		
		$("#rmtReqYyyy").change(function() {
			var selectedYear = eval($(this).val());
			var curCnt = $("#rmtReqYyyy option").size();
			
			for ( var i=1; i <=curCnt ; i++){
				$("#rmtReqYyyy option:eq(0)").remove();
			}
			
			for ( var i=selectedYear-10; i <=selectedYear+10 ; i++){
				$("#rmtReqYyyy").append("<option value='"+i+"'>"+i+"</option>");
			}
			$("#rmtReqYyyy > option[value="+selectedYear+"]").attr("selected", true);
			//alert($(this).val());
			//alert($(this).children("option:selected").text());
		});
		
		
		$("#excel").click(function(event){
			event.preventDefault();
			var sfrm = document.searchForm;
			sfrm.action="<c:url value="/settlement/remittance/remittanceRstExcelList.omp" />";
			sfrm.target="_self";
			sfrm.submit();
		});
		
		//일괄 송금결과마감 처리
		$("#bundleProcess").click(function(event){
			
			var frm = document.updateForm;
			event.preventDefault();
			
			/*
			//$("#remittanceSS\\.rmtReqYyyymm").val($("#rmtReqYyyy").val() + $("#rmtReqmm").val());	
			
			if(($('input[id="searchTimeBlock"]:checked').val()=="P")){ //부분날자 검색일대 송금월 처리
				$("#updateForm.\\remittanceSS\\.rmtReqYyyymm").val($("#rmtReqYyyy").val() + $("#rmtReqmm").val());
			}else if (($('input[id="searchTimeBlock"]:checked').val()=="")){ //전체날자 선택시 
				alert("송금 결과 마감을 처리할 해당월을 선택해 주세요.");
				return;
			}
			
			$("#searchCont").val($.trim($("#searchCont").val()));
			if ($("#searchType").val() == "B"){ //개발자번호 값 셋팅
				$("#updateForm.\\remittanceS\\.mbrId").val($("#searchCont").val());
			}else if($("#searchType").val() == "C"){ //개발자명 값 셋팅
				$("#updateForm.\\remittanceS\\.mbrNm").val($("#searchCont").val());
			}
			*/
			
			popup_center('', 440, 300, 0, 0);
			frm.target = 'popup';
			frm.action="<c:url value="/settlement/remittance/popUpRemittanceEnd.omp"/>";
			frm.submit();
			
		});
		
		
		//일괄 송금결과마감 결과
		$("#bundleProcessEnd").click(function(event){
			
			var frm = document.updateForm;
			event.preventDefault();
			
			//$("#remittanceSS\\.rmtReqYyyymm").val($("#rmtReqYyyy").val() + $("#rmtReqmm").val());	
			/*
			
			if(($('input[id="searchTimeBlock"]:checked').val()=="P")){ //부분날자 검색일대 송금월 처리
				$("#updateForm.\\remittanceSS\\.rmtReqYyyymm").val($("#rmtReqYyyy").val() + $("#rmtReqmm").val());
			}else if (($('input[id="searchTimeBlock"]:checked').val()=="")){ //전체날자 선택시 
				alert("송금 결과 마감을 처리할 해당월을 선택해 주세요.");
				return;
			}
			
			$("#searchCont").val($.trim($("#searchCont").val()));
			if ($("#searchType").val() == "B"){ //개발자번호 값 셋팅
				$("#updateForm.\\remittanceS\\.mbrId").val($("#searchCont").val());
			}else if($("#searchType").val() == "C"){ //개발자명 값 셋팅
				$("#updateForm.\\remittanceS\\.mbrNm").val($("#searchCont").val());
			}
			*/
			popup_center('', 440, 300, 0, 0);
			frm.target = 'popup';
			frm.action="<c:url value="/settlement/remittance/popUpRemittanceEndRst.omp"/>";
			frm.submit();
			
		});
		
		
		$("#searchType").change(function() {
			$("#searchCont").val("");
		});
		
		
		
	});
	

	function goPage(no) {
	    var sfrm = document.searchForm
		$("#no").val(no);
	    sfrm.action="<c:url value="/settlement/remittance/remittanceRstList.omp" />";
	    sfrm.target="_self";
		sfrm.submit();
	}
	
	//송금처리 상세
	function viewPage(saleYm,rmtReqYyyymm,mbrNo,adjYn){
		var frm = document.viewForm;
		
		$("#remittanceSS\\.saleYm").val(saleYm);
		$("#remittanceSS\\.rmtReqYyyymm").val(rmtReqYyyymm);
		$("#remittanceSS\\.mbrNo").val(mbrNo);
		$("#remittanceSS\\.adjYn").val(adjYn);
		
		frm.action="<c:url value="/settlement/remittance/remittanceRstInfo.omp"/>";
		frm.target="_self";
		frm.submit();
	}
	
	//송금처리 수정
	function editPage(saleYm,rmtReqYyyymm,mbrNo,adjYn){
		var frm = document.viewForm;
		
		$("#remittanceSS\\.saleYm").val(saleYm);
		$("#remittanceSS\\.rmtReqYyyymm").val(rmtReqYyyymm);
		$("#remittanceSS\\.mbrNo").val(mbrNo);
		$("#remittanceSS\\.adjYn").val(adjYn);
		
		frm.action="<c:url value="/settlement/remittance/editStartRemittanceRstInfo.omp"/>";
		frm.target="_self";
		frm.submit();
	}
	
	//엑셀 다운로드
	function excelDown() {
		var sfrm = document.searchForm
		sfm.action="/settlement/remittance/remittanceRstExcelList.omp";
		srm.target="_self";
		sfm.submit();
	}
	
	
	
//]]>
</script>

</head>
<body>

			<div id="location">
				Home &gt; 정산관리 &gt; 정산현황 &gt; <strong>송금결과처리</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">송금결과처리</h1>
			<p>송금 결과 처리 내역을 확인하실 수 있습니다.</p>
			<s:form id="searchForm" name="searchForm"  theme="simple" >
			<input type="hidden" id="rmtReqYyyymm" name="remittanceS.rmtReqYyyymm" value="${remittanceS.rmtReqYyyymm}"> <!-- 송금신청년월 -->
			<input type="hidden" id="mbrId" name="remittanceS.mbrId" value="${remittanceS.mbrId}"> <!-- 개발자아이디 검색값 -->
			<input type="hidden" id="mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}"> <!-- 개발자명 검색값 -->
			<input type="hidden" id="no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
			<input type="hidden" id="firstAccessYN" name="remittanceS.firstAccessYN" value="N">
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>검색조건</th>
					<td class="align_td line2_5">
						<label for="#">검색기간</label>
						<input type="radio" class="ml05"  id="searchTimeBlock" name="remittanceS.searchTimeBlock" value=""/>전체 
						<input type="radio" class="ml05"  id="searchTimeBlock" name="remittanceS.searchTimeBlock" value="P"/>송금월
						<select class="ml05" id="rmtReqYyyy" name="rmtReqYyyy" style="width:60px;">
						</select>
						<select class="ml05" id="rmtReqmm" name="rmtReqmm" style="width:50px;">
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
						<label for="#">처리상태</label>
						<input type="radio" class="ml05" id="rmtEndCd" name="remittanceS.rmtEndCd" value=""/>전체 
						<input type="radio" class="ml05" id="rmtEndCd" name="remittanceS.rmtEndCd" value="PD004124"/>송금완료 
						<input type="radio" class="ml05" id="rmtEndCd" name="remittanceS.rmtEndCd" value="PD004122"/>재송금 
						<input type="radio" class="ml05" id="rmtEndCd" name="remittanceS.rmtEndCd" value="PD004123"/>송금포기
						<br />
						<label for="#">검색어</label>
						<select id="searchType" name="remittanceS.searchType">
							<option value="B">개발자 ID</option>
							<option value="C">개발자 이름</option>
						</select>
						<input type="text" class="txt" id="searchCont" name="remittanceS.searchCont" style="width:250px;" />
					</td>
					<td rowspan="2" class="text_c">
						<a class="btn" id="searchBtn" href="#"><strong>검색</strong></a>
					</td>
				</tr>
			</table>
			</s:form>
			<p class="fl mt25" id="labelRmtReqYyyymm"></p>
			<p class="fr mt20 mb05"><a class="btn_s" href="#"><span id="bundleProcess">송금결과마감</span></a> <a class="btn_s" href="#"><span id="bundleProcessEnd">송금결과마감</span></a> <a class="btn_s" href="#"><span id="excel">Excel 다운로드</span></a></p>
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
					<th>판매월</th>
					<th>정산종류</th>
					<th>지불화폐</th>
					<th>송금금액</th>
					<th>처리상태</th>
					<th>송금월</th>
					<th>상세내역</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${empty remittanceS.firstAccessYN }">
					<td colspan="8" >검색 결과가 없습니다.</td>
				</c:when>
				<c:otherwise>
					<c:choose>
					<c:when test="${totalCount eq 0 }">
						<td colspan="7" >검색조건을 선택하신 후 ‘검색’ 버튼을 클릭하세요.</td>
					</c:when>
					<c:otherwise>
					<c:forEach items="${remittanceRstList}" var="remittance">
					<tr>
						<td>${remittance.mbrNm}</td>
						<td><g:text value="${remittance.saleYm}" format="L####-##" /></td>
						<td>
							<c:choose>
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
						<td><g:text value="${remittance.rmtReqYyyymm}" format="L####-##" /></td>
						<td><!-- 송금완료이외의 상태만 링크 -->
							<c:choose>
							<c:when test="${remittance.rmtEndCd eq 'PD004122' || remittance.rmtEndCd eq 'PD004123' }"><!-- 재송금,송금포기시에는 상세보기페이지로 이동 -->
							<a class="btn_s" id="viewPage" href="#" onClick="JavaScript:viewPage('${remittance.saleYm}','${remittance.rmtReqYyyymm}','${remittance.mbrNo}','${remittance.adjYn}')"><span>자세히보기</span></a>
							</c:when>
							<c:when test="${remittance.rmtEndCd eq 'PD004120'}"><!-- 송금대기  상태만 수정 -->
								<a class="btn_s" id="viewPage" href="#" onClick="JavaScript:editPage('${remittance.saleYm}','${remittance.rmtReqYyyymm}','${remittance.mbrNo}','${remittance.adjYn}')"><span>자세히보기</span></a>
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
			<s:form id="viewForm" name="viewForm"  theme="simple" >
				<input type="hidden" id="remittanceS.searchTimeBlock" name="remittanceS.searchTimeBlock" value="${remittanceS.searchTimeBlock}"/>
				<input type="hidden" id="remittanceS.rmtReqYyyymm" name="remittanceS.rmtReqYyyymm" value="${remittanceS.rmtReqYyyymm}"/>
				<input type="hidden" id="remittanceS.rmtEndCd" name="remittanceS.rmtEndCd" value="${remittanceS.rmtEndCd}">
				<input type="hidden" id="remittanceS.searchType" name="remittanceS.searchType" value="${remittanceS.searchType}"/>
				<input type="hidden" id="remittanceS.searchCont" name="remittanceS.searchCont" value="${remittanceS.searchCont}"/>
				<input type="hidden" id="remittanceS.mbrId" name="remittanceS.mbrId" value="${remittanceS.mbrId}" > 
				<input type="hidden" id="remittanceS.mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}" > 
				<input type="hidden" id="remittanceS.page.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
				<input type="hidden" id="remittanceS.firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
				<input type="hidden" id="remittanceSS.saleYm" name="remittanceSS.saleYm" />
				<input type="hidden" id="remittanceSS.rmtReqYyyymm" name="remittanceSS.rmtReqYyyymm" />
				<input type="hidden" id="remittanceSS.mbrNo" name="remittanceSS.mbrNo"  />
				<input type="hidden" id="remittanceSS.adjYn" name="remittanceSS.adjYn"  />
			</s:form>
			
			<s:form id="updateForm" name="updateForm"  theme="simple" >
				<input type="hidden" id="updateForm.remittanceS.searchTimeBlock" name="remittanceS.searchTimeBlock" value="${remittanceS.searchTimeBlock}"/>
				<input type="hidden" id="updateForm.remittanceS.rmtReqYyyymm" name="remittanceS.rmtReqYyyymm" value="${remittanceS.rmtReqYyyymm}"/>
				<input type="hidden" id="updateForm.remittanceS.rmtEndCd" name="remittanceS.rmtEndCd" value="${remittanceS.rmtEndCd}">
				<input type="hidden" id="updateForm.remittanceS.searchType" name="remittanceS.searchType" value="${remittanceS.searchType}"/>
				<input type="hidden" id="updateForm.remittanceS.searchCont" name="remittanceS.searchCont" value="${remittanceS.searchCont}"/>
				<input type="hidden" id="updateForm.remittanceS.mbrId" name="remittanceS.mbrId" value="${remittanceS.mbrId}" > 
				<input type="hidden" id="updateForm.remittanceS.mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}" > 
				<input type="hidden" id="updateForm.remittanceS.page.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
				<input type="hidden" id="updateForm.remittanceS.firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
				<input type="hidden" id="updateForm.remittanceSS.rmtReqYyyymm" name="remittanceSS.rmtReqYyyymm" value="${remittanceS.rmtReqYyyymm}"/><!-- 송금일괄처리를 위해 검색값을 기본적으로 셋팅 -->
			</s:form>
			
			<!-- paging -->
			<div align="center">
		        <g:pageIndex item="${remittanceRstList}"/>
		    </div>
			<!-- //paging -->

	<hr>
	
</body>
</html>
