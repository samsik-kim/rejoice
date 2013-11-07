<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>查看懲戒記錄</title>

<script type="text/javascript">

function goPage(no) {
	$("form[name=searchPunishForm] input[name=usMemberPunish\\.page\\.no]").val(no);
	funcPunishList();
}

var funcPunishList = function() {
	$( "#searchPunishForm" ).submit();
};

</script>

</head>
<body class="popup">
	<div id="popup_area_810">
		<h1>查看懲戒記錄</h1>
		<p class="mb05">
			※ 懲戒狀態  <br />  
			&nbsp; &nbsp; ${usMemberPunish.mbrId}會員被處懲之評論如下:
		</p>
		<p class="text_r mb05">
			評論總數  : ${srchCnt} 筆
		</p>
		<s:form action="punishMemberList" id="searchPunishForm" theme="simple">
		<input type="hidden" name="usMemberPunish.page.no" value="1"/>
		<table class="pop_tabletype02">
			<colgroup>
				<col style="width:40px;">
				<col >
				<col style="width:90px">
				<col style="width:90px">
				<col style="width:80px">
				<col style="width:80px">
			</colgroup>
			<thead>
			<tr>
				<th>序號</th>
				<th>評論標題</th>
				<th>發表日期</th>
				<th>檢舉日期</th>
				<th>管理者帳號</th>
				<th>檢舉緣由</th>
			</tr>
			</thead>
			<tbody>
			<s:if test="dpProdNotiList.size == 0">
			<tr><td colspan='5'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.member.punish.popBadnotiList.msg.none_result'/></span></td></tr>
			</s:if>
			<s:else>
			<c:set var="resultNum" value="${srchCnt - (usMemberPunish.page.no-1)*10 }"/>
			<s:iterator value="dpProdNotiList" status="status">
			<tr>
				<td>${resultNum}</td>
				<td class="text_l"><g:tagBody value="${notiDscr}" /></td>
				<td><g:text value="${regDttm}" format="L####-##-##"/></td>
				<td><g:text value="${notiRegDttm}" format="L####-##-##"/></td>
				<td>${regId}</td>
				<td>${badnotiRptNm}</td>
			</tr>
			<c:set var="resultNum" value="${resultNum-1 }"/>
			</s:iterator>
			</s:else>
			</tbody>
		</table>
		</s:form>
		<!-- paging -->
		<div class="paging_area">
			<div class="paging"> 
			<g:pageIndex item="${dpProdNotiList}"/>
			</div>
		</div>
		<!-- //paging -->
		<div class="pop_btn" >
			<a class="btn" href="javascript:self.close();"><strong>關閉</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>
