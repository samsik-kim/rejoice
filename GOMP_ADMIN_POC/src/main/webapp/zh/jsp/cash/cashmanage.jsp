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

		// registBtn 
		$("#registBtn").click(function(event){
			event.preventDefault();
			authDwldConfirmPopup();
		});
		
	});

	function authDwldConfirmPopup() {
		var frm = document.myForm;
		
		var chrgCashIds = eval('frm.chrgCashId');
		var bonusRatios = eval('frm.bonusRatio');
		var prebonusRatios = eval('frm.prebonusRatio');

		for(var i = 0; i< chrgCashIds.length ; i++ ) {
			if (bonusRatios[i].value)
			if(bonusRatios[i].value == "" || bonusRatios[i].value == null) {
				alert("<gm:string value='jsp.cash.cashmanage.msg.chkCashRate'/>");
				return;
			}
		}
		
		if(confirm("<gm:string value='jsp.cash.cashmanage.msg.chkCashBonus'/>")){
			width = 440;
			height = 160;
			x = (screen.width) ? (screen.width-width)/2 : 0;
			y = (screen.height) ? (screen.height-height)/2 : 0;
			scrollOption = "No";
			var url = "/adminpoc/giftcard/popAuthDwldConfirm.omp?sub.issueType=specific";
			window.open(url,"popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");
		}
	}	
	
	function excute() {
		var frm = document.myForm;
		frm.action="registCashBonus.omp";
		frm.submit();
	}
	
//]]>
</script>
	</head>
<body>
	<s:form name="myForm" theme="simple" method="post">
		
	<div id="location">
		首頁 &gt; 產品管理中心 &gt; Whoopy幣管理 &gt <strong>Whoopy幣加值管理 </strong> 
	</div><!-- //location -->

	<h1 class="fl pr10">Whoopy幣加值管理</h1>
	<p>可管理Whoopy幣加值金額類別之Whoopy幣贈送率。</p>
	<div class="tab">
		<ul>
			<li class="on"><a href="#">現狀及變更</a></li>
			<li><a href="cashmanagehisList.omp">歷史紀錄</a></li>
		</ul>
	</div>
			
	<p class="text_r mt20"><strong class="c_333">最後修該日期 : ${lastedDate} </strong></p>

	<table class="tabletype02 mt05">
		<colgroup>
			<col style="width:100;" />
			<col />
			<col />
		</colgroup>
		<tbody>
		<tr>
			<th>Whoopy幣加值(P)</th>
			<th>Whoopy筆贈送率(%)</th>
		</tr>
		<c:forEach items="${list }" var="charge">
		<tr>
			<td>
				<input type="hidden" id="chrgCashId" name="sub.chrgCashId" value="${charge.chrgCashId }"/>
				<input type="hidden" id="prebonusRatio" name="sub.prebonusRatio" value="${charge.bonusRatio}"/>
				<strong class="point2">${charge.chrgCashNm }</strong> </td>
			<td><input type="text" id="bonusRatio" name="sub.bonusRatio" type="text" style="width:280px;text-align:right;" value="${charge.bonusRatio}"/> %</td>	
		</tr>
		</c:forEach>
	</table>
	<!-- //2011-03-16 -->
	<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="registBtn"><span>儲存</span></a></p>
	</s:form>
</div>