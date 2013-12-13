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
			
	});
</script>
</head>
<body>
			<div id="location">
				首頁 &gt; 結算管理中心 &gt; 結算現狀 &gt; <strong>發票處理作業</strong> 
			</div><!-- //location -->
			<div class="overflow_h">
				<h1 class="fl pr10">發票處理作業</h1>
				<p>可查看發票處理現狀.</p>
			</div>
			<p class="mt20">▶ 發票詳細記錄</p>
			
			<!-- 정산 정류가 조정일 경우 화면 -->
			<s:form id="processForm" name="processForm"  theme="simple" >
			<input type="hidden" id="accountsS.searchTimeBlock" name="accountsS.searchTimeBlock" value="${accountsS.searchTimeBlock}"/>
			<input type="hidden" id="accountsS.saleYm" name="accountsS.saleYm" value="${accountsS.saleYm}"/>
			<input type="hidden" id="accountsS.searchType" name="accountsS.searchType" value="${accountsS.searchType}"/>
			<input type="hidden" id="accountsS.searchCont" name="accountsS.searchCont" value="${accountsS.searchCont}"/>
			<input type="hidden" id="accountsS.mbrId" name="accountsS.mbrId" value="${accountsS.mbrId}" > 
			<input type="hidden" id="accountsS.mbrNm" name="accountsS.mbrNm" value="${accountsS.mbrNm}" > 
			<input type="hidden" id="accountsSS.adjYn" name="accountsSS.adjYn" value="${accountsSS.adjYn}"/>
			<input type="hidden" id="accountsS.page.no" name="accountsS.page.no" value="${accountsS.page.no}" />
			<input type="hidden" id="accountsS.firstAccessYN" name="accountsS.firstAccessYN" value="${accountsS.firstAccessYN}">
			<input type="hidden" id="accountsSS.saleYm" name="accountsSS.saleYm" value="${accountsSS.saleYm}"/>
			<input type="hidden" id="accountsSS.mbrNo" name="accountsSS.mbrNo" value="${accountsSS.mbrNo}"/>
			<input type="hidden" id="accountsSS.currencyUnitName" name="accountsSS.currencyUnitName" value="${accounts.currencyUnitName}"/><!-- 통화단위코드명 -->
			
			
			<table class="tabletype01">
				<colgroup><col style="width:26%;" ><col style="width:74%;" ></colgroup>
				<tr>
					<th>開發商</th>
					<td>
						${accounts.mbrNm}(${accounts.mbrId})
					</td>
				</tr>
				<tr>
					<th>會員類別 / 會員等級</th>
					<td>
						<gc:html code="${accounts.mbrClsCd}"/> / <gc:html code="${accounts.mbrGrCd}"/>
					</td>
				</tr>
				<tr>
					<th>結算類別</th>
					<td>
						調帳
					</td>
				</tr>
				<tr>
					<th>交易月份</th>
					<td>
						<g:text value="${accounts.saleYm}" format="L####-##" />
					</td>
				</tr>
				<tr>
					<th>調帳金額</th>
					<td>
						<g:text value="${accounts.adjAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th>緣由</th>
					<td>
						<g:html value="${accounts.adjReason}"/>
					</td>
				</tr>
				<tr>
					<th>稅額</th>
					<td>
						<g:text value="${accounts.txtAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th>付款幣別</th>
					<td>
						<gc:html code="${accounts.currencyUnit}"/>
					</td>
				</tr>
				<tr>
					<th>匯款幣別</th>
					<td>
						<g:text value="${accounts.devBuDvAmtSum}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th>申請與否</th>
					<td>
						<input type="radio" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004102"  disabled /> 憑證未送達
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004103" disabled /> 等待憑證
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004107" disabled /> 次月處理
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004104" disabled /> 資料無誤
					</td>
				</tr>
				<tr>
					<th>次月處理緣由</th>
					<td>
						<textarea cols="67" rows="8" disabled ><g:tagAttb value="${accounts.rmtReqReason}"/></textarea>
					</td>
				</tr>
			</table>
			</s:form>
			<!-- //정산 정류가 조정일 경우 화면 -->

			<p class="btn_wrap text_c mt20"><a class="btn" href="#" ><span id="listBtn">目錄</span></a></p>
</body>
</html>
