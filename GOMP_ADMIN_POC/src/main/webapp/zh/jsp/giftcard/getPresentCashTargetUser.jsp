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
<!-- <link rel="stylesheet" type="text/css" href=""/> --> 
<style type="text/css">
	body.popup table {
		margin-bottom:15px
	}
</style>
<script type="text/javascript">
		
	$(function() {
		var form = document.searchForm;
		
		// search btn
		$("#searchBtn").click(function(event){
			event.preventDefault();
			funcSearch();
		});
		
		// exit Btn
		$("#exitBtn").click(function(event){
			event.preventDefault();
			self.close();
		});
		
	});

	// 검색
	function funcSearch(){
		if(showValidate('searchForm', 'dialog', '<gm:string value="jsp.gift.getPresentCashTargetUser.msg.validate"/>')){
			document.searchForm.action="popSearchTarget.omp";
			document.searchForm.submit();
		}
	}

	function setValue(mbrId){
		var parentPage = opener.document;
		var txtMsg = mbrId;
		parentPage.getElementById("testLabel").replaceAdjacentText("afterEnd", " ");	
		parentPage.getElementById("testLabel").insertAdjacentText("afterEnd", txtMsg);
		parentPage.getElementById("mbrId").value = mbrId;
	}

	String.prototype.trim = function() {
	    return this.replace(/(^\s*)|(\s*$)/gi, "");
	}
	
	</script>
</head>
<body>
<s:form name="searchForm" id="searchForm" theme="simple" >
<input type="hidden" id="searchType" name="sub.searchType" value="ID"/>
<input type="hidden" id="firstCheck" name="sub.firstCheck" value="N" />
<div id="popup_area_440">
	<h1 class="mt25">搜尋發行對象</h1>
	<table class="pop_tabletype01">
		<colgroup><col width="30%;"><col width="70%;"></colgroup>
		<tr>
			<th>搜尋會員</th>
			<td>帳號 <input type="text" id="searchText" name="sub.searchText" value="${sub.searchText }"/><a class="btn" href="#" id="searchBtn" ><strong>搜尋</strong></a></td>
		</tr>
	</table>
	<c:if test='${sub.firstCheck == "Y"}'>
		<div class="pop_btn" >
			<a class="btn" href="#" id="exitBtn"><strong>關閉</strong></a>
		</div>
	</c:if>
	<c:if test='${sub.firstCheck == "N"}'>
		<c:if test="${giftCardlist == null}">
			<table class="pop_tabletype02 mt10">
				<colgroup><col width="30%;"><col width="30%;"><col width="40%;"></colgroup>
					<tr>
						<th>帳號</th>
					</tr>
					<tr>
						<td><gm:html value='jsp.gift.getPresentCashTargetUser.msg.noresult'/></td>
					</tr>
			</table>		
		</c:if>
		<c:if test="${giftCardlist != null}">
			<table class="pop_tabletype02 mt10">
				<colgroup><col width="30%;"><col width="30%;"><col width="40%;"></colgroup>
				<tr>
					<th>帳號</th>
				</tr>
				<c:forEach items="${giftCardlist }" var="giftcard">
					<tr>
						<td>${giftcard.mbrId } &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						 <a class="btn" href="#" onclick="javascript:setValue('${giftcard.mbrId }');" align="right"><strong>選擇</strong></a></td>
					</tr>
				</c:forEach>
			</table>
			<div class="pop_btn" >
					<a class="btn" href="#" id="exitBtn"><strong>關閉</strong></a>
			</div>
		</c:if>
	</c:if>	
</div>
	</s:form>
</body>
</html>