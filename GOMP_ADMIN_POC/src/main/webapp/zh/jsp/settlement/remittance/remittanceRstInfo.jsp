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
	
$(function() {
	var listForm = document.listForm;
	
	$("#listBtn").click(function(event){
		event.preventDefault();
		
		document.listForm.action="<c:url value="/settlement/remittance/remittanceRstList.omp" />";
		document.listForm.submit();
		
	});
	
});


</script>	

</head>
<body>

			<div id="location">
				首頁 &gt; 結算管理中心 &gt; 結算現狀 &gt; <strong>匯款結果操作</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">匯款結果操作</h1>
			<p>可查看匯款結果操作內容.</p>
			<table class="tabletype01">
				<colgroup><col style="width:150px;" ><col ></colgroup>
				<tr>
					<th>開發商(帳號) / 銀行帳戶</th>
					<td>
						${remittance.mbrNm}(${remittance.mbrId}) / ${remittance.acctNo}
					</td>
				</tr>
				<tr>
					<th>會員類別 / 會員等級</th>
					<td>
						<gc:html code="${remittance.mbrClsCd}"/> / <gc:html code="${remittance.mbrGrCd}"/>
					</td>
				</tr>
				<tr>
					<th>結算類別</th>
					<td>
						<c:choose>
						<c:when test="${remittance.adjYn eq 'Y'}">
							調帳
						</c:when>
						<c:when test="${remittance.adjYn eq 'N'}">
							結算
						</c:when>
						</c:choose>
					</td>
				</tr>
				<tr>
					<th>匯款狀態</th>
					<td>
						<gc:html code="${remittance.rmtEndCd}"/>
					</td>
				</tr>
				<tr>
					<th>付款幣別</th>
					<td>
						<gc:html code="${remittance.currencyUnit}"/>
					</td>
				</tr>
				<tr>
					<th>匯款金額</th>
					<td>
						<g:text value="${remittance.rmtAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th>處理狀態</th>
					<td>
						<c:choose>
						<c:when test="${remittance.rmtEndCd eq 'PD004123' }"> <!-- 송금포기시에만 포기담당자,포기일자 표기 -->
							<p class="fr"><g:html value="${remittance.rmtGiveupReason}"/></p>
							<gc:html code="${remittance.rmtEndCd}"/>
						</c:when>
						<c:otherwise>
							<gc:html code="${remittance.rmtEndCd}"/>
						</c:otherwise>
						</c:choose>
						
					</td>
				</tr>
				<tr>
					<th>變更緣由</th>
					<td>
						<g:html value="${remittance.rmtReason}"/>
						
					</td>
				</tr>
			</table>
			<p class="btn_wrap text_c mt05"><a class="btn" href="#" id="listBtn"><span>確定</span></a></p>

	<hr>
	
	<s:form id="listForm" name="listForm"  theme="simple" >
		<input type="hidden" id="listForm.searchTimeBlock" name="remittanceS.searchTimeBlock" value="${remittanceS.searchTimeBlock}"/>
		<input type="hidden" id="listForm.rmtReqYyyymm" name="remittanceS.rmtReqYyyymm" value="${remittanceS.rmtReqYyyymm}"/>
		<input type="hidden" id="listForm.rmtEndCd" name="remittanceS.rmtEndCd" value='<c:out value="${remittanceS.rmtEndCd}"/>'/>
		<input type="hidden" id="listForm.searchType" name="remittanceS.searchType" value="${remittanceS.searchType}"/>
		<input type="hidden" id="listForm.searchCont" name="remittanceS.searchCont" value="${remittanceS.searchCont}"/>
		<input type="hidden" id="listForm.mbrNo" name="remittanceS.mbrNo" value="${remittanceS.mbrNo}"/>
		<input type="hidden" id="listForm.mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}"/>
		<input type="hidden" id="listForm.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
		<input type="hidden" id="firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
	</s:form>
	
</body>
</html>