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
		
		$("#adjPopUp").click(function(){
			var targetUrl = "<c:url value="/settlement/accounts/popUpAdjAmtView.omp"/>";
			popup_center('', 500, 300, 0, 0);
			pForm.target = 'popup';
			pForm.action = targetUrl;
			pForm.submit();
		});
		
			
	});
	
	
	function setAdjAmt(){
		$("#adjAmtText").text(digitsComma(String(adjAmt))); //조정액 TEXT 수정
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
<body>
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
			<input type="hidden" id="accountsSS.adjYn" name="accountsSS.adjYn" value="${accountsSS.adjYn}"/>
			<input type="hidden" id="accountsSS.adjAmt" name="accountsSS.adjAmt" value="${accounts.adjAmt}"/><!-- 조정액 -->
			<input type="hidden" id="accountsSS.adjReason" name="accountsSS.adjReason" value="<g:tagAttb value="${accounts.adjReason}"/>"/><!-- 개발자 조정사유 -->
			<input type="hidden" id="accounts.devBuDvAmtSum" name="accounts.devBuDvAmtSum" value="${accounts.devBuDvAmtSum}"/><!-- 개발자 초기 배분 금액 -->
			
			<table class="tabletype01">
				<colgroup><col style="width:88px;" ><col style="width:80px;" /><col ><col ></colgroup>
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
					<th colspan="2">정산종류</th>
					<td>
						정산
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
						<g:text value="${accounts.totlPayAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th>세전금액</th>
					<td>
						<g:text value="${accounts.beforeTaxAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th>Total</th>
					<td>
						체크할 사항: <g:text value="${accounts.devBuDvAmtSum}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th colspan="2">세금(B)</th>
					<td>
						<g:text value="${accounts.txtAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th colspan="2">정산액(C=A+B)</th>
					<td>
						<g:text value="${accounts.devBuDvAmtSum}" format="###,###,###,###.##" />
					</td>
				</tr>
				<!-- <tr>
					<th colspan="2">실정산액(E=C+(-D))</th>
					<td>
						00,000
					</td>
				</tr> -->
				<tr>
					<th colspan="2">지불화폐</th>
					<td>
						<gc:html code="${accounts.currencyUnit}"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">송금금액</th>
					<td>
						<font id="sendAmtSumText" ><g:text value="${accounts.devBuDvAmtSum}" format="###,###,###,###.##" /></font>
					</td>
				</tr>
				<tr>
					<th colspan="2">조정액</th>
					<td>
						<font id="adjAmtText" ><g:text value="${accounts.adjAmt}" format="###,###,###,###.##" /></font>
						
						<c:if test="${ accounts.adjAmt > 0}"> <!-- 조정액이 존재하면 팝업-->
						 <a href="#" class="btn_s" id="adjPopUp"><span>자세히보기</span></a><p class="fr pt05"><gc:html code="${accounts.adjReasonInsBy}"/></p>
						</c:if>
					</td>
				</tr>
				<tr>
					<th colspan="2">신청여부</th>
					<td>
						<input type="radio" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004102" disabled /> 송금미신청
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004103" disabled /> 수신대기
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004107" disabled /> 이월처리
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004104" disabled /> 수신완료
					</td>
				</tr>
				<tr>
					<th colspan="2">이월처리안내</th>
					<td>
						<textarea cols="67" rows="8" id="accountsSS.rmtReason" name="accountsSS.rmtReqReason" disable><g:tagAttb value="${accounts.rmtReqReason}"/></textarea>
					</td>
				</tr>
			</table>
			</s:form>
			<p class="btn_wrap text_c mt20"><a class="btn" href="#"><span id="listBtn">목록</span></a></p>
		
</body>
</html>
