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
<style type="text/css">
form{clear:both;}
</style>
<script type="text/javascript">
//<![CDATA[
$(function() {
	
	// registBtn btn 
	$("#registedViewBtn").click(function(event){
		event.preventDefault();
		gotoRegistedGiftCardList();
	});
	
	//authDwldBtn
	$("#authDwldBtn").click(function(event){
		event.preventDefault();
		authDwldConfirmPopup();
	});
	
	//listBtn
	$("#listBtn").click(function(event){
		event.preventDefault();
		gotoList();
	});
	
});
	
	function gotoRegistedGiftCardList() {
		width = 810;
		height = 670;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		scrollOption = "No";
		window.open("","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");
		document.myForm.target="popup";
		document.myForm.action="popRegistedGiftCardList.omp";
		document.myForm.submit();
	}
	
	function authDwldConfirmPopup() {
		width = 440;
		height = 160;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		scrollOption = "No";
		window.open("","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");
		document.myForm.action="popAuthDwldConfirm.omp";
		document.myForm.target="popup";
		document.myForm.submit();
	}
	
	function gotoList() {
		document.myForm.action="giftCardinfoList.omp";
		document.myForm.target="";
		document.myForm.submit();
	}

	function excelExport() {
		var frm = document.myForm;
		var issueYn = document.getElementById('issueYn').value;
		if(issueYn == "N") {
			alert("<gm:string value='jsp.gift.giftCardInfoDetail.msg.nocard'/>");
			return;
		}
		frm.target="_parent";
		frm.action="randomNumberExcelExport.omp";
		frm.submit();
	}

	
	//]]>
</script>
</head>
<body>
	<div id="location">
		首頁  &gt; 產品管理中心  &gt; Whoopy幣管理 &gt <strong>Whoopy禮券紀錄 </strong> 
	</div><!-- //location -->
	<h1 class="fl pr10">Whoopy禮券紀錄</h1>
	<p>可管理及新增禮券。</p>
		
	<s:form name="myForm" id="myForm" theme="simple" method="post">
		<input type="hidden" id="issueYn" name="sub.issueYn"  value="${giftCard.issueYn}"/>
		<input type="hidden" id="groupSeq" name="sub.groupSeq"  value="${sub.groupSeq}"/>
		<input type="hidden" id="issuedGiftCardCnt" name="sub.issuedGiftCardCnt"  value="${giftCard.cardCount }"/>
		<input type="hidden" id="issueType" name="sub.issueType" value="${sub.issueType}"/>
		<input type="hidden" id="startDate" name="sub.startDate" value="${sub.startDate}"/>
		<input type="hidden" id="toSearchDt" name="sub.endDate" value="${sub.endDate}"/>
		<input type="hidden" id="searchText" name="sub.searchText" value="${sub.searchText}"/>
			
			<table class="tabletype02">
				<colgroup>
					<col style="width:20%;">
					<col style="width:80%;">
				</colgroup>
				<tbody>
				<tr>
					<th>禮券名稱</th>
					<td class="text_l">${giftCard.cardName }</td>
				</tr>
				<tr>
					<th>發行形式</th>
					<td class="text_l"><gc:text code="${giftCard.cardType }"/></td>
				</tr>
				<tr>
					<th>選擇Whoopy幣</th>
					<td class="text_l"><g:html format="R###,###,###"><gc:text code="${giftCard.cardAmt }"/></g:html> P</td>
				</tr>
				<tr>
					<th>發行日期</th>
					<td class="text_l">${giftCard.issueReqDt }</td>
				</tr>
				<tr>
					<th>發行數量</th>
					<td class="text_l">${giftCard.cardCount } 張</td>
				</tr>
				<tr>
					<th>註冊現狀</th>
					<td class="text_l">
					<g:text value="${giftCard.regCnt }" format="R#,###"/> / <g:text value="${giftCard.cardCount }" format="R#,###"/>(登錄之禮券數  / 發行之禮券數) <a class="btn" href="#" id="registedViewBtn"><span>禮券登錄現狀</span></a></td>
				</tr>
				<tr>
					<th>註冊日期</th>
					<td class="text_l">${giftCard.regDt }</td>
				</tr>
			</table>
			
			<p class="btn_wrap text_r mt25">
			<a class="btn" href="#" id="authDwldBtn"><span>下載隨機Excel檔</span></a> 
			<a class="btn" href="#" id="listBtn"><span>目錄</span></a></p>
		</div>
	</s:form>
</body>
</html>	