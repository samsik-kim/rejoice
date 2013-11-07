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
		Home &gt; 상품관리 &gt; 캐쉬관리 &gt <strong>기프트카드 내역</strong> 
	</div><!-- //location -->
	<h1 class="fl pr10">기프트카드 내역</h1>
	<p>기프트카드 내역 관리 및 신규 생성 합니다.</p>
		
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
					<th>기프트 카드명</th>
					<td class="text_l">${giftCard.cardName }</td>
				</tr>
				<tr>
					<th>발급형태</th>
					<td class="text_l"><gc:text code="${giftCard.cardType }"/></td>
				</tr>
				<tr>
					<th>Cash 선택</th>
					<td class="text_l"><g:html format="R###,###,###"><gc:text code="${giftCard.cardAmt }"/></g:html> P</td>
				</tr>
				<tr>
					<th>발급일자</th>
					<td class="text_l">${giftCard.issueReqDt }</td>
				</tr>
				<tr>
					<th>발급개수</th>
					<td class="text_l">${giftCard.cardCount } 장</td>
				</tr>
				<tr>
					<th>등록현황</th>
					<td class="text_l">
					<g:text value="${giftCard.regCnt }" format="R#,###"/> / <g:text value="${giftCard.cardCount }" format="R#,###"/>(등록된 기프트카드 수 / 발급된 기프트카드 수) <a class="btn" href="#" id="registedViewBtn"><span>기프트카드 등록현황</span></a></td>
				</tr>
				<tr>
					<th>등록일</th>
					<td class="text_l">${giftCard.regDt }</td>
				</tr>
			</table>
			
			<p class="btn_wrap text_r mt25">
			<a class="btn" href="#" id="authDwldBtn"><span>난수 Excel 다운로드</span></a> 
			<a class="btn" href="#" id="listBtn"><span>목록</span></a></p>
		</div>
	</s:form>
</body>
</html>	