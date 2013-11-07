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
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/common.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
	
		//신청여부 체크
		$("input[id='accountsSS\\.aggtStatCd'][value='${accounts.aggtStatCd}']").attr("checked", "checked"); 
		
	});
	
	$(function() {
		var pForm = document.processForm;
		
		$("#listBtn").click(function(event){
			event.preventDefault();
			pForm.target = '_self';
			pForm.action="<c:url value="/settlement/accounts/receiptProcessList.omp" />";
			pForm.submit();
			
		});
		
		$("#updateBtn").click(function(event){
			event.preventDefault();
			/*
			if ($("input[id='accountsSS\\.aggtStatCd']:checked").length==0){
				alert("<gm:string value='jsp.settlement.accounts.editStartReceiptProcessInfo.application'/>");
				return;
			//}else if (($("input[id='accountsSS\\.aggtStatCd']").filter("input[value='PD004107']").attr("checked")==true) && ($.trim($("#accountsSS\\.rmtReqReason").val())=="")){
			}else if (($("input[id='accountsSS\\.aggtStatCd']").val() =='PD004107') && ($.trim($("#accountsSS\\.rmtReqReason").val())=="")){	
				alert("<gm:string value='jsp.settlement.accounts.editStartReceiptProcessInfo.trnsfer'/>");
				return;
			}else{
				if (confirm("<gm:string value='jsp.settlement.accounts.editStartReceiptProcessInfo.receiptrecord'/>")){
						pForm.action="<c:url value="/settlement/accounts/editEndReceiptProcessInfo.omp" />";
						pForm.target = '_self';
						pForm.submit();
				}
			}*/
			
			
			if(showValidate('insertForm', 'default', "<gm:string value=""/>")){
				if (confirm("<gm:string value='jsp.settlement.accounts.editStartReceiptProcessInfo.receiptrecord'/>")){
					pForm.action="<c:url value="/settlement/accounts/editEndReceiptProcessInfo.omp" />";
					pForm.target = '_self';
					pForm.submit();
				}
			}
			
		});
		
		$("#adjPopUp").click(function(){
			var targetUrl = "<c:url value="/settlement/accounts/popUpAdjAmt.omp"/>";
			popup_center('', 500, 300, 0, 0);
			pForm.target = 'popup';
			pForm.action = targetUrl;
			pForm.submit();
		});
			
	});
	
	
	function setAdjAmt(){
		var devBuDvAmtSum = eval($("#accounts\\.devBuDvAmtSum").val()); //개발자 초기 배분 금액 합계
		var devBuAdjAmtSum; //개발자 조정 배분 금액 합계
		var adjAmt = eval($("#accountsSS\\.adjAmt").val());
		//devBuAdjAmtSum = devBuDvAmtSum + adjAmt; 
		
		
		$("#adjAmtText").text(digitsComma(String(adjAmt))); //조정액 TEXT 수정
		//$("#devBuAdjAmtSumText").text(digitsComma(String(devBuAdjAmtSum)));  //실정산액 TEXT 수정
		//$("#sendAmtSumText").text(digitsComma(String(devBuAdjAmtSum)));  //송금금액 TEXT 수정
		 
	}
	
	
	function digitsComma(numStr)
	{
		var pmCheck="";
		var s=numStr;

		if(s.charAt(0)=="-"){ // +,- 기호처리
			pmCheck = s.charAt(0);
			s = s.substring(1);
		}
		
		var t="";
	    var i;
	    var j=0;
	    var tLen =s.length;

	    if (s.length <= 3 ) {
	        return pmCheck+s;
	    }else{
	    	for(i=0;i<tLen;i++){
		       if (i!=0 && ( i % 3 == tLen % 3) )     t += ",";
		       if(i < s.length ) t += s.charAt(i);
		    }
		    return pmCheck+t;	
	    }
	}
	
</script>	

</head>
<body >

			<div id="location">
				Home &gt; 정산관리 &gt; 정산현황 &gt; <strong>영수증처리</strong> 
			</div><!-- //location -->
			<div class="overflow_h">
				<h1 class="fl pr10">영수증처리</h1>
				<p>영수증 처리 현황을 확인하실 수 있습니다.</p>
			</div>
			<p class="mt20">▶ 영수증 상세 내역</p>
			<s:form id="processForm" name="processForm"  theme="simple" >
			<input type="hidden" id="accountsS.searchTimeBlock" name="accountsS.searchTimeBlock" value="${accountsS.searchTimeBlock}"/>
			<input type="hidden" id="accountsS.saleYm" name="accountsS.saleYm" value="${accountsS.saleYm}"/>
			<input type="hidden" id="accountsS.searchType" name="accountsS.searchType" value="${accountsS.searchType}"/>
			<input type="hidden" id="accountsS.searchCont" name="accountsS.searchCont" value="${accountsS.searchCont}"/>
			<input type="hidden" id="accountsS.mbrId" name="accountsS.mbrId" value="${accountsS.mbrId}" > 
			<input type="hidden" id="accountsS.mbrNm" name="accountsS.mbrNm" value="${accountsS.mbrNm}" > 
			<input type="hidden" id="accountsS.page.no" name="accountsS.page.no" value="${accountsS.page.no}" />
			<input type="hidden" id="accountsS.firstAccessYN" name="accountsS.firstAccessYN" value="${accountsS.firstAccessYN}">
			<input type="hidden" id="accountsSS.saleYm" name="accountsSS.saleYm" value="${accountsSS.saleYm}"/>
			<input type="hidden" id="accountsSS.mbrNo" name="accountsSS.mbrNo" value="${accountsSS.mbrNo}"/>
			<input type="hidden" id="accountsSS.adjAmt" name="accountsSS.adjAmt" value="${accounts.adjAmt}"/><!-- 조정액 -->
			<input type="hidden" id="accountsSS.adjAmtEditYN" name="accountsSS.adjAmtEditYN" value="N"/><!-- 조정액 수정여부-->
			<input type="hidden" id="accountsSS.adjReason" name="accountsSS.adjReason" value="<g:tagAttb value="${accounts.adjReason}"/>"/><!-- 개발자 조정사유 -->
			<input type="hidden" id="accounts.devBuDvAmtSum" name="accounts.devBuDvAmtSum" value="${accounts.devBuDvAmtSum}"/><!-- 개발자 초기 배분 금액 -->
			
			<table class="tabletype01">
				<colgroup><col style="width:90px;" ><col style="width:80px;" ><col ></colgroup>
				<tr>
					<th colspan="2">개발자</th>
					<td>
						${accounts.mbrNm}(${accounts.mbrNo})
					</td>
				</tr>
				<tr>
					<th colspan="2">회원 구분/등급</th>
					<td>
						<gc:html code="${accounts.mbrClsCd}"/> / <gc:html code="${accounts.mbrGrCd}"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">판매월</th>
					<td>
						<g:text value="${accounts.saleYm}" format="L####-##" />
					</td>
				</tr>
				<tr>
					<th rowspan="3">배분총액(A)</th>
					<th>판매총액</th>
					<td>
						<g:text value="${accounts.totlPayAmt}" format="R###,###,###,###" />
					</td>
				</tr>
				<tr>
					<th>세전금액</th>
					<td>
						<g:text value="${accounts.beforeTaxAmt}" format="R###,###,###,###" />
					</td>
				</tr>
				<tr>
					<th>Total</th>
					<td>
						<g:text value="${accounts.devBuDvAmtSum}" format="R###,###,###,###" />
					</td>
				</tr>
				<tr>
					<th colspan="2">세금(B)</th>
					<td>
						<!-- 세금금액의  +, - 금액 처리구문 -->
						<c:set var="txtAmt" value="${fn:substring(accounts.txtAmt,0,1)}" />
						<c:set var="pmCheckTax" value="" />
						<c:choose>
						<c:when test="${accounts.txtAmt eq '0'}">
							<c:set var="txtAmt" value="${accounts.txtAmt}" />
						</c:when>
						<c:when test="${txtAmt eq '-'}">
							<c:set var="txtAmt" value="${fn:substring(accounts.txtAmt,1,100)}" />
							<c:set var="pmCheckTax" value="-" />
						</c:when>
						<c:otherwise>
							<c:set var="txtAmt" value="${accounts.txtAmt}" />
						</c:otherwise>
						</c:choose>
						<!-- 세금금액의  +, - 금액 처리구문 -->
						<c:out value="${pmCheckTax}"/><g:text value="${accounts.txtAmt}" format="R###,###,###,###" />
					</td>
				</tr>
				<tr>
					<th colspan="2">정산액(C=A+B)</th>
					<td>
						<g:text value="${accounts.devBuAdjAmtSum}" format="R###,###,###,###" /> 
					</td>
				</tr>
				<tr>
					<th colspan="2">조정액(D)</th>
					<td>
						<!-- 정산금액의  +, - 금액 처리구문 -->
						<c:set var="adjAmt" value="${fn:substring(accounts.adjAmt,0,1)}" />
						<c:set var="pmCheck" value="" />
						<c:choose>
						<c:when test="${accounts.adjAmt eq '0'}">
							<c:set var="adjAmt" value="${accounts.adjAmt}" />
						</c:when>
						<c:when test="${adjAmt eq '-'}">
							<c:set var="adjAmt" value="${fn:substring(accounts.adjAmt,1,100)}" />
							<c:set var="pmCheck" value="-" />
						</c:when>
						<c:otherwise>
							<c:set var="adjAmt" value="${accounts.adjAmt}" />
						</c:otherwise>
						</c:choose>
						<!-- 정산금액의  +, - 금액 처리구문 -->
						
						<font id="adjAmtText" ><c:out value="${pmCheck}"/><g:text value="${adjAmt}" format="R###,###,###,###" /></font>
						<c:choose>
							<c:when test="${ ( accounts.aggtStatCd eq 'PD004102' 
											|| accounts.aggtStatCd eq 'PD004103' 
											|| accounts.aggtStatCd eq 'PD004107' 
											|| accounts.aggtStatCd eq 'PD004104')}"><!-- 송금미신청, 수신대기(영수증),이월처리,수신완료 상태의 데이타만 수정가능  -->
						 			<a href="#" class="btn_s" id="adjPopUp"><span>자세히보기</span></a> <g:html value="${accounts.adjReasonInsBy}" />
							</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th colspan="2">실정산액(E=C+(-D))</th>
					<td>
						<font id="devBuAdjAmtSumText" ><g:text value="${accounts.devBuAdjAmtSum}" format="R###,###,###,###" /></font>
					</td>
				</tr>
				<tr>
					<th colspan="2">지불화폐</th>
					<td>
						<gc:html code="${accounts.currencyUnit}"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">송금금액</th>
					<td>
						<font id="sendAmtSumText" ><g:text value="${accounts.devBuAdjAmtSum}" format="R###,###,###,###" /></font>
					</td>
				</tr>
				<tr>
					<th colspan="2">신청여부</th>
					<td>
						<input type="radio" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004102" v:mustcheck="1" m:mustcheck="<gm:string value='jsp.settlement.accounts.editStartReceiptProcessInfo.application'/>"/> 송금미신청
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004103"/> 수신대기
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004107"/> 이월처리
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004104"/> 수신완료
					</td>
				</tr>
				<tr>
					<th colspan="2">이월처리안내</th>
					<td>
						<textarea cols="67" rows="8" id="accountsSS.rmtReqReason" name="accountsSS.rmtReqReason"><g:tagAttb value="${accounts.rmtReqReason}"/></textarea>
					</td>
				</tr>
			</table>
			</s:form>
			<p class="btn_wrap text_c mt20"><a class="btn" href="#" ><span id="updateBtn">저장</span></a> <a class="btn" href="#" ><span id="listBtn">목록</span></a></p>
		
	<hr>
</body>
</html>
