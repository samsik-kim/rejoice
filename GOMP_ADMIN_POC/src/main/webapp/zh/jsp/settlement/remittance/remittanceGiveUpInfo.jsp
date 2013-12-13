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
	
		$("input[id='remittanceSS\\.rmtEndCd'][value='${remittance.rmtEndCd}']").attr("checked", "checked"); //송금상태 체크
		
	});
	
	
	
	
	$(function() {
		var updateForm = document.searchForm;
		var listForm = document.listForm;
		
		$("#updateBtn").click(function(event){
			event.preventDefault();
			var frm = document.updateForm;
			
			if(showValidate('updateForm', 'default', "<gm:string value=""/>",insertBtnFnc)){
			
				if(confirm("<gm:string value='jsp.settlement.remittance.remittanceGiveUpInfo.resend'/>")){
					frm.action="<c:url value="/settlement/remittance/editRemittanceGiveUpInfo.omp" />";
					frm.submit();
				}
			}
			
		});
		
		$("#listBtn").click(function(event){
			event.preventDefault();
			var frm = document.listForm;
			frm.action="<c:url value="/settlement/remittance/remittanceGiveUpList.omp" />";
			frm.submit();
			
			
		});
		
	});
	
	var insertBtnFnc = {
	   "sameState" : function() {
		   var checkedVal = $("input[name=remittanceSS\\.rmtEndCd][checked]").val();
		   if (checkedVal=="PD004123"){ //송금포기시
			   return false;
		   }else{
			   return true;
		   }
	   }
	};

</script>
</head>
<body>
	

			<div id="location">
				首頁 &gt; 結算管理中心 &gt; 結算現狀  &gt; <strong>匯款失敗現狀</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">匯款失敗現狀</h1>
			<p>可查看匯款失敗現狀內容.</p>
			<p class="mt20">▶ 匯款失敗內容</p>
			<s:form id="updateForm" name="updateForm"  theme="simple" >
				<input type="hidden" id="remittanceS.saleYm" name="remittanceS.saleYm" value="${remittanceS.saleYm}"/>
				<input type="hidden" id="remittanceS.searchTimeBlock" name="remittanceS.searchTimeBlock" value="${remittanceS.searchTimeBlock}"> <!-- 기간선택  검색값 -->
				<input type="hidden" id="remittanceS.searchType" name="remittanceS.searchType" value="${remittanceS.searchType}"/>
				<input type="hidden" id="remittanceS.searchCont" name="remittanceS.searchCont" value="${remittanceS.searchCont}"/>
				<input type="hidden" id="remittanceS.mbrNo" name="remittanceS.mbrNo" value="${remittanceS.mbrNo}" > 
				<input type="hidden" id="remittanceS.mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}" >
				<input type="hidden" id="remittanceS.page.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
				<input type="hidden" id="firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
				<input type="hidden" id="remittanceSS.saleYm" name="remittanceSS.saleYm" value="${remittanceSS.saleYm}" />
				<input type="hidden" id="remittanceSS.rmtReqYyyymm" name="remittanceSS.rmtReqYyyymm" value="${remittanceSS.rmtReqYyyymm}" />
				<input type="hidden" id="remittanceSS.mbrNo" name="remittanceSS.mbrNo"  value="${remittanceSS.mbrNo}" />
				<input type="hidden" id="remittanceSS.adjYn" name="remittanceSS.adjYn"  value="${remittanceSS.adjYn}" />
				
			<table class="tabletype01">
				<colgroup><col style="width:150px;" ><col ></colgroup>
				<tr>
					<th>開發商(帳號)</th>
					<td>
						${remittance.mbrNm}(${remittance.mbrId})
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
					<th>匯款失敗緣由</th>
					<td>
						<g:html value="${remittance.rmtReason}" />
					</td>
				</tr>
				<tr>
					<th>轉換為再次匯款</th>
					<td>
						<input type="radio" id="remittanceSS.rmtEndCd" name="remittanceSS.rmtEndCd" value="PD004122" /> 是
						<input type="radio" id="remittanceSS.rmtEndCd" name="remittanceSS.rmtEndCd" value="PD004123" v:sameState m:sameState="<gm:string value='jsp.settlement.remittance.remittanceGiveUpInfo.currentgiveup'/>"/> 否
					</td>
				</tr>
			</table>
			</s:form>
			
			<s:form id="listForm" name="listForm"  theme="simple" >
				<input type="hidden" id="remittanceS.saleYm" name="remittanceS.saleYm" value="${remittanceS.saleYm}"/>
				<input type="hidden" id="remittanceS.searchTimeBlock" name="remittanceS.searchTimeBlock" value="${remittanceS.searchTimeBlock}"> <!-- 기간선택  검색값 -->
				<input type="hidden" id="remittanceS.searchType" name="remittanceS.searchType" value="${remittanceS.searchType}"/>
				<input type="hidden" id="remittanceS.searchCont" name="remittanceS.searchCont" value="${remittanceS.searchCont}"/>
				<input type="hidden" id="remittanceS.mbrNo" name="remittanceS.mbrNo" value="${remittanceS.mbrNo}" > 
				<input type="hidden" id="remittanceS.mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}" >
				<input type="hidden" id="remittanceS.page.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
				<input type="hidden" id="firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
			</s:form>
				
			<p class="btn_wrap text_c mt20"><a class="btn" href="#" id="updateBtn"><span>儲存</span></a> <a class="btn" href="#" id="listBtn"><span>目錄</span></a></p>

</body>
</html>
