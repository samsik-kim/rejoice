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
		var form = document.myForm;
		
		// search start ~ end date datepicker
		$( "#reqIssueDate" ).datepicker();
		
		// listBtn
		$("#listBtn").click(function(event){
			event.preventDefault();
			gotoList();
		});
		
		// registBtn
		$("#registBtn").click(function(event){
			event.preventDefault();
			authDwldConfirmPopup();
		});
		
	});
	
	function authDwldConfirmPopup(){
		var frm = document.getElementById('myForm');

		var reqCardNm = frm.reqCardNm.value;
		var issueNum = frm.reqCardCnt.value;
		
		if(reqCardNm == "" || reqCardNm == null) {
			alert("<gm:string value='jsp.gift.registReqIssueGiftCard.msg.chkCardName'/>");
			return;
		}

		if(issueNum == "" || issueNum == null) {
			alert("<gm:string value='jsp.gift.registReqIssueGiftCard.msg.chkCardCount'/>");
			return;
		}
		
		if(isNaN(parseInt(issueNum))){
			alert("<gm:string value='jsp.gift.registReqIssueGiftCard.msg.chkCardNumber'/>");
			return;
		}
		
		if(confirm("<gm:string value='jsp.gift.registReqIssueGiftCard.msg.confirmRegister'/>")){
			width = 440;
			height = 160;
			x = (screen.width) ? (screen.width-width)/2 : 0;
			y = (screen.height) ? (screen.height-height)/2 : 0;
			scrollOption = "No";
			var url = "popAuthDwldConfirm.omp?sub.issueType=specific";
			window.open(url,"popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");
		}
	}
	
	function excute() {
		var frm = document.myForm;
		frm.action="requestIssueGiftCard.omp";
		frm.submit();
	}

	function gotoList() {
		if(confirm("<gm:string value='jsp.gift.registReqIssueGiftCard.msg.confirmCancel'/>")){
			var frm = document.myForm;
			frm.action="giftCardinfoList.omp";
			frm.submit();
		}
	}

	
         
           
//]]>
</script>
	</head>
<body>
	<div id="location">
		首頁 &gt; 產品管理中心 &gt; Whoopy幣管理 &gt <strong>電子禮券紀錄</strong> 
	</div><!-- //location -->
	<h1 class="fl pr10">電子禮券紀錄</h1>
	<p>可管理及新增禮券。</p>
	<s:form id="myForm" name="myForm" theme="simple" method="post" >
		<input type="hidden" id="issueType" name="sub.issueType" value="${sub.issueType}"/>
		<input type="hidden" id="startDate" name="sub.startDate"  value="${sub.startDate}"/>
		<input type="hidden" id="endDate" name="sub.endDate"  value="${sub.endDate}"/>
		<input type="hidden" id="searchText" name="sub.searchText"  value="${sub.searchText}"/>
	<table class="tabletype02">
		<colgroup>
			<col style="width:20%;">
			<col style="width:80%;">
		</colgroup>
		<tbody>
		<tr>
			<th>禮券名稱</th>
			<td class="text_l"><input type="text" id="reqCardNm" name="reqIssueGiftCard.cardNm" class="txt" style="width:80%;"/></td>
		</tr>
		<tr>
			<th>發行形式</th>
			<td class="text_l">
				<gc:radioButtons name="reqIssueGiftCard.cardType" group="OR0029" divide="&nbsp;" extra="id='reqIssueType'" filter="GIFTCARD" value="${reqIssueGiftCard.cardType }"/>
			</td>
		</tr>
		<tr>
			<th>選擇Whoopy幣(P)</th>
			<td class="text_l">
				<gc:radioButtons name="reqIssueGiftCard.cardAmt" group="OR0028" divide="&nbsp;" extra="id='reqCardAmt'" filter="GIFTCARD" value="${reqIssueGiftCard.cardAmt }" />					
			</td>
		</tr>
		<tr>
			<th>發行日期</th>
			<td class="text_l align_td">
				<input type='text' id='reqIssueDate' name='reqIssueGiftCard.issueReqDt'  value='${reqIssueGiftCard.issueReqDt}' class="txt" style="width:80px;" readonly><br>
				※ 注意：贈送券與販售券之有效期分別為６個月與一年。
			</td>
		</tr>
		<tr>
			<th>發行數量</th>
				<td class="text_l"><input type='text' id="reqCardCnt" name="reqIssueGiftCard.cardCnt" class="txt" style="width:80px;"/> 張</td>
		</tr>
		</tbody>
	</table>
	</s:form>
	<p class="btn_wrap text_r mt25">
		<a class="btn" href="#" id="registBtn"><span>儲存</span></a> 
		<a class="btn" href="#" id="listBtn"><span>目錄</span></a></p>
	</body>
</html>	
