<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
//<![CDATA[
	$(function() {
		var form = document.searchForm;
		
		// search btn
		$("#searchBtn").click(function(event){
			event.preventDefault();
			funcSearch();
		});
		
		// refund btn
		$("#refundBtn").click(function(event){
			event.preventDefault();
			goRefund();
		});
		
		// exit Btn
		$("#exitBtn").click(function(event){
			event.preventDefault();
			self.close();
		});
		
	});
	
	function funcSearch(){
		if (funSaveValid()){
			document.searchForm.action="popRefund.omp";
			document.searchForm.submit();
		}
	}
	
	function funSaveValid(){
		var	fm = document.searchForm;
		var searchText = fm.searchText.value;
		//alert(searchText);
		if (searchText == "" )	{
			alert("<gm:string value='jsp.cash.cash_list.msg.chkUser'/>");
			return false;
		}
		return true;
	}
	
	//캐시 차감
	function goRefund() {
		var	fm	= document.searchForm;
		if(fm.mbrNo == null){
			alert("<gm:string value='jsp.cash.popuprefund.msg.chkSubtractUser'/>");
			return;
		}
		
		if(fm.refundAmt.value.trim() == ""){
			alert("<gm:string value='jsp.cash.popuprefund.msg.chkSubtractPoint'/>");
			return;
		}else if(isNaN(fm.refundAmt.value.trim()) == true){
			alert("<gm:string value='jsp.cash.popuprefund.msg.chkNumber'/>"); 
			return;
		}
		if(fm.refundAmt.value.indexOf(".") != -1){
			alert("<gm:string value='jsp.cash.popuprefund.msg.chkRadixPoint'/>");
			return;
		}
		if(parseInt(fm.refundAmt.value) > parseInt(fm.cashTotAmt.value)){
			alert("<gm:string value='jsp.cash.popuprefund.msg.chkCash'/>");
			return;
		}else if(parseInt(fm.refundAmt.value) <= 0){
			alert("<gm:string value='jsp.cash.cash_list.msg.chkZeroCash'/>");
			return;
		}
		if(confirm("<gm:string value='jsp.cash.popuprefund.msg.chkConfirmCash'/>")){
			url = "/adminpoc/purchasecancel/purchaseRefundCash.omp";
			window.open('','pop','width=0,height=0,top=0,left=0, scrollbars=no');
			var refundFm = document.refundForm;
			refundFm.mbrNo.value   = fm.mbrNo.value;
			refundFm.refundAmt.value = fm.refundAmt.value.trim();
			refundFm.action         = url;
			refundFm.target         = "pop";
			refundFm.method         = "post";
			refundFm.submit();
			refundFm.target         = "_self";
			refundFm.action         = setTimeout("funcSearch()",2500);
			
		}
	}
	
	String.prototype.trim = function() {
	    return this.replace(/(^\s*)|(\s*$)/gi, "");
	}

//]]>
</script>
</head>
<body>
	<div id="popup_area_440">
		<s:form name="searchForm" id="searchForm" theme="simple" >
		<input type="hidden" id="firstCheck" name="sub.firstCheck" value="N" />
		<h1 class="mt25">搜尋扣除Whoopy幣會員</h1>
		<table class="pop_tabletype01">
			<colgroup><col width="30%;"><col width="70%;"></colgroup>
			<tr>
				<th>搜尋會員</th>
				<td>帳號 <input id="searchText" name="sub.searchText" type="text" class="txt" value="${sub.searchText }"/><a class="btn" href="#" id="searchBtn"><strong>搜尋</strong></a>
			</tr>
		</table>
	<c:choose>
	<c:when test="${sub.firstCheck == 'Y' }">
		<div class="pop_btn" >
			<a class="btn" href="#" id="exitBtn"><strong>關閉</strong></a>
		</div>
	</c:when>
	<c:otherwise>
		<c:choose>
			<c:when test="${totalCount eq 0 }">
				<table class="pop_tabletype02">
					<colgroup><col></colgroup>
					<tr>
						<td>無資料。</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
				<c:forEach items="${list }" var="charge">
					<input type="hidden" id="mbrNo" name="sub.mbrNo" value="${charge.mbrNo}" />
					<input type="hidden" id="mbrId" name="sub.mbrId" value="${charge.mbrId}" />
					<input type="hidden" id="cashTotAmt" name="sub.cashTotAmt" value="${charge.cashTotAmt}" />
					
					<table class="pop_tabletype01 mt10">
							<colgroup><col width="30%;"><col width="70%;"></colgroup>
								<tr>
									<th>帳號(姓名)</th>
									<td>${charge.mbrId}(${charge.mbrNm})</td>
								</tr>
								<tr>
									<th>Whoopy幣餘額</th>
									<td><g:text value="${charge.cashTotAmt}" format="R#,###,###,###,###,###,###,###"/> P</td>
								</tr>
								<tr>
									<th>扣除之Whoopy幣</th>
									<td><input id="refundAmt" name="sub.refundAmt" type="text" class="txt"/> P</td>
								</tr>
					</table>
				</c:forEach>
			</c:otherwise>
			</c:choose>
		<div class="pop_btn" >
			<a class="btn" href="#" id="refundBtn"><strong>確定</strong></a>
			<a class="btn" href="#" id="exitBtn"><strong>關閉</strong></a>
		</div>
	</c:otherwise>
	</c:choose>
	</s:form>
	<s:form name="refundForm" theme="simple" method="post">
		<input type="hidden" id="mbrNo" name="mbrNo" value=""/>
		<input type="hidden" id="refundAmt" name="refundAmt" value=""/>
	</s:form>
</div>
</body>

</html>