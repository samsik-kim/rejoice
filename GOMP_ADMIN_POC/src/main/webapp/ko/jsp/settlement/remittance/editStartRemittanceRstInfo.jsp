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
<script type="text/javascript">
	
	
	$(document).ready(function(){
	
		//현재 송금결과 상태 처리
		$("input[id='remittanceSS\\.rmtEndCd'][value='${remittance.rmtEndCd}']").attr("checked", "checked");
			
	});
	
	
	
	$(function() {
		var listForm = document.listForm;
		var updateForm = document.updateForm;
		
		$("#listBtn").click(function(event){
			
			listForm.action="<c:url value="/settlement/remittance/remittanceRstList.omp" />";
			listForm.submit();
		});
		
		$("#updateBtn").click(function(event){
			/*
			var updateForm = document.updateForm;
			event.preventDefault();
			if ($("input[id='remittanceSS\\.rmtEndCd']:checked").length==0){
				alert("<gm:string value='jsp.settlement.remittance.editStartRemittanceRstInfo.process'/>");
				return;
			}else if (($("input[id='remittanceSS\\.rmtEndCd']").filter("input[value='PD004123']").attr("checked")==true) && ($.trim($("#remittanceSS\\.rmtReason").val())=="")){
				alert("<gm:string value='jsp.settlement.remittance.editStartRemittanceRstInfo.giveup'/>");
				return;
			}else{
				if (($("input[id='remittanceSS\\.rmtEndCd']").filter("input[value='PD004123']").attr("checked")==true)){ //송금포기상태로 저장시
					if(showValidate('updateForm', 'default', "<gm:string value=""/>")){
						if(confirm("<gm:string value='jsp.settlement.remittance.editStartRemittanceRstInfo.giveupchange'/>")){
							updateForm.action="<c:url value="/settlement/remittance/editEndRemittanceRstInfo.omp" />";
							updateForm.submit();
						}
					}
				}else if (($("input[id='remittanceSS\\.rmtEndCd']").filter("input[value='PD004122']").attr("checked")==true)){ //재송금 상태로 저장시
					if (confirm("<gm:string value='jsp.settlement.remittance.editStartRemittanceRstInfo.resend'/>")){
						updateForm.action="<c:url value="/settlement/remittance/editEndRemittanceRstInfo.omp" />";
						updateForm.submit();
					}
				}
			}
			*/
			var updateForm = document.updateForm;
			event.preventDefault();
			if(showValidate('updateForm', 'default', "<gm:string value=""/>")){
				if (($("input[id='remittanceSS\\.rmtEndCd']").filter("input[value='PD004123']").attr("checked")==true)){ //송금포기상태로 저장시
					if(confirm("<gm:string value='jsp.settlement.remittance.editStartRemittanceRstInfo.giveupchange'/>")){
						updateForm.action="<c:url value="/settlement/remittance/editEndRemittanceRstInfo.omp" />";
						updateForm.submit();
					}
				}else if (($("input[id='remittanceSS\\.rmtEndCd']").filter("input[value='PD004122']").attr("checked")==true)){ //재송금 상태로 저장시
					if (confirm("<gm:string value='jsp.settlement.remittance.editStartRemittanceRstInfo.resend'/>")){
						updateForm.action="<c:url value="/settlement/remittance/editEndRemittanceRstInfo.omp" />";
						updateForm.submit();
					}
				}
			}			
		});
		
	});
</script>	

</head>
<body>

			<div id="location">
				Home &gt; 정산관리 &gt; 정산현황 &gt; <strong>송금결과처리</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">송금결과처리</h1>
			<p>송금 결과 처리 내역을 확인하실 수 있습니다.</p>
			
			<s:form id="updateForm" name="updateForm"  theme="simple" >
			<!-- 리스트 검색 조건값 -->
			<input type="hidden" id="remittanceS.searchTimeBlock" name="remittanceS.searchTimeBlock" value="${remittanceS.searchTimeBlock}"/>
			<input type="hidden" id="remittanceS.rmtReqYyyymm" name="remittanceS.rmtReqYyyymm" value="${remittanceS.rmtReqYyyymm}"/>
			<input type="hidden" id="remittanceS.rmtEndCd" name="remittanceS.rmtEndCd" value='<c:out value="${remittanceS.rmtEndCd}"/>'/>
			<input type="hidden" id="remittanceS.searchType" name="remittanceS.searchType" value="${remittanceS.searchType}"/>
			<input type="hidden" id="remittanceS.searchCont" name="remittanceS.searchCont" value="${remittanceS.searchCont}"/>
			<input type="hidden" id="remittanceS.mbrNo" name="remittanceS.mbrNo" value="${remittanceS.mbrNo}"/>
			<input type="hidden" id="remittanceS.mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}"/>
			<input type="hidden" id="remittanceS.page.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
			<input type="hidden" id="firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
			<!-- 업데이트에 사용될 값 -->
			<input type="hidden" id="remittanceSS.saleYm" name="remittanceSS.saleYm"}" value="${remittanceSS.saleYm}"/>
			<input type="hidden" id="remittanceSS.rmtReqYyyymm" name="remittanceSS.rmtReqYyyymm" value="${remittanceSS.rmtReqYyyymm}"/>
			<input type="hidden" id="remittanceSS.mbrNo" name="remittanceSS.mbrNo"  value="${remittanceSS.mbrNo}"/>
			<input type="hidden" id="remittanceSS.adjYn" name="remittanceSS.adjYn"  value="${remittanceSS.adjYn}"/>
			<input type="hidden" id="remittanceSS.rmtStatCd" name="remittanceSS.rmtStatCd" value="PD004112"><!-- 송금상태코드 : 송금실패코드 -->
			
			<table class="tabletype01">
				<colgroup><col style="width:150px;" ><col ></colgroup>
				<tr>
					<th>개발자(ID)/계좌번호</th>
					<td>
						${remittance.mbrNm}(${remittance.mbrNo}) / ${remittance.acctNo} 
					</td>
				</tr>
				<tr>
					<th>회원 구분/등급</th>
					<td>
						<gc:html code="${remittance.mbrClsCd}"/> / <gc:html code="${remittance.mbrGrCd}"/>
					</td>
				</tr>
				<tr>
					<th>정산종류</th>
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
				</tr>
				<tr>
					<th>송금상태</th>
					<td>
						<gc:html code="${remittance.rmtEndCd}"/>
					</td>
				</tr>
				<tr>
					<th>지불화폐</th>
					<td>
						<gc:html code="${remittance.currencyUnit}"/>
					</td>
				</tr>
				<tr>
					<th>송금금액</th>
					<td>
						<g:text value="${remittance.rmtAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				
				<tr>
					<th>처리상태</th>
					<td>
						<input type="radio"  id="remittanceSS.rmtEndCd" name="remittanceSS.rmtEndCd" value="PD004122" v:mustcheck="1" m:mustcheck="<gm:string value='jsp.settlement.remittance.editStartRemittanceRstInfo.process'/>"/> 재송금
						<input type="radio"  class="ml10" id="remittanceSS.rmtEndCd" name="remittanceSS.rmtEndCd" value="PD004123" /> 송금포기
					</td>
				</tr>
				<tr>
					<th>변경 안내</th>
					<td>
						<textarea cols="67" rows="8" id="remittanceSS.rmtReason" name="remittanceSS.rmtReason" 
						v:text8_limit="1000" m:text8_limit="<gm:tagAttb value='jsp.settlement.remittance.editStartRemittanceRstInfo.info1000'/>"><g:html value="${remittance.rmtReason}"/> </textarea>
					</td>
				</tr>
			</table>
			</s:form>
			<p class="btn_wrap text_c mt20"><a class="btn" href="#" id="updateBtn"><span>저장</span></a> <a class="btn" href="#" id="listBtn"><span>목록</span></a></p>
			
	
	<hr>
	<s:form id="listForm" name="listForm"  theme="simple" >
		<input type="hidden" id="remittanceS.searchTimeBlock" name="remittanceS.searchTimeBlock" value="${remittanceS.searchTimeBlock}"/>
		<input type="hidden" id="remittanceS.rmtReqYyyymm" name="remittanceS.rmtReqYyyymm" value="${remittanceS.rmtReqYyyymm}"/>
		<input type="hidden" id="remittanceS.rmtEndCd" name="remittanceS.rmtEndCd" value='<c:out value="${remittanceS.rmtEndCd}"/>'/>
		<input type="hidden" id="remittanceS.searchType" name="remittanceS.searchType" value="${remittanceS.searchType}"/>
		<input type="hidden" id="remittanceS.searchCont" name="remittanceS.searchCont" value="${remittanceS.searchCont}"/>
		<input type="hidden" id="remittanceS.mbrNo" name="remittanceS.mbrNo" value="${remittanceS.mbrNo}" > 
		<input type="hidden" id="remittanceS.mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}" > 
		<input type="hidden" id="remittanceS.page.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
		<input type="hidden" id="firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
	</s:form>
</body>
</html>
