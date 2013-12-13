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
			
			if(showValidate('insertForm', 'default', "<gm:string value=""/>")){
				if (confirm("<gm:string value='jsp.settlement.accounts.editStartReceiptProcessInfo.receiptrecord'/>")){
					pForm.action="<c:url value="/settlement/accounts/editEndAdjustmentMoneyInfo.omp" />";
					pForm.target = '_self';
					pForm.submit();
				}
			}
			
		});
			
	});
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
			
			<!-- 정산 정류가 조정일 경우 화면 -->
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
			
			<table class="tabletype01">
				<colgroup><col style="width:26%;" ><col style="width:74%;" ></colgroup>
				<tr>
					<th>개발자</th>
					<td>
						${accounts.mbrNm}(${accounts.mbrNo})
					</td>
				</tr>
				<tr>
					<th>회원 구분/등급</th>
					<td>
						<gc:html code="${accounts.mbrClsCd}"/> / <gc:html code="${accounts.mbrGrCd}"/>
					</td>
				</tr>
				<tr>
					<th>정산종류</th>
					<td>
						조정
					</td>
				</tr>
				<tr>
					<th>판매월</th>
					<td>
						<g:text value="${accounts.saleYm}" format="L####-##" />
					</td>
				</tr>
				<tr>
					<th>조정액수</th>
					<td>
						<g:text value="${accounts.adjAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th>사유</th>
					<td>
						<g:html value="${accounts.adjReason}"/>
					</td>
				</tr>
				<tr>
					<th>세금</th>
					<td>
						<g:text value="${accounts.txtAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th>지불화폐</th>
					<td>
						<gc:html code="${accounts.currencyUnit}"/>
					</td>
				</tr>
				<tr>
					<th>송금금액</th>
					<td>
						<g:text value="${accounts.devBuDvAmtSum}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th>신청여부</th>
					<td>
						<input type="radio" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004102"  /> 송금미신청
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004103"  /> 수신대기
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004107"  /> 이월처리
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004104"  /> 수신완료
					</td>
				</tr>
				<tr>
					<th>이월처리안내</th>
					<td>
						<textarea cols="67" rows="8" id="accountsSS.rmtReason" name="accountsSS.rmtReqReason" ><g:tagAttb value="${accounts.rmtReqReason}"/></textarea>
					</td>
				</tr>
			</table>
			</s:form>
			<!-- //정산 정류가 조정일 경우 화면 -->

			<p class="btn_wrap text_c mt20"><a class="btn" href="#" ><span id="updateBtn">저장</span></a> <a class="btn" href="#" ><span id="listBtn">목록</span></a></p>
</body>
</html>
