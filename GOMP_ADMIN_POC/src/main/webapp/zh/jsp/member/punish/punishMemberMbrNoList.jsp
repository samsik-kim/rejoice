<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<script type="text/javascript">

	function goPage(no) {
		$("form[name=searchPunishForm] input[name=usMemberPunish\\.page\\.no]").val(no);
		funcPunishList();
	}

	var searchPunishList = function() {
		$( "#searchPunishForm" ).submit();
	};

	var memberInfo = function() {
		$( "#memberForm" ).submit();
	};

	var popPunishMemberClose = function(mbrNo, mbrId) {

		var form = popPunishMemberForm;
		width = 480;
		height = 260;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		scrollOption = "Yes";
		
		window.open("","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");		
						
		form.action="${pageContext.request.contextPath }/member/popPunishMemberClose.omp?mbrNo=" + mbrNo + "&mbrId=" + mbrId;
		form.target="popup";
		form.submit();
	};

	var popBadnotiList = function(mbrNo, mbrId) {

		var form = popPunishMemberForm;
		width = 860;
		height = 780;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		scrollOption = "Yes";
		
		window.open("","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");		
						
		form.action="${pageContext.request.contextPath }/member/popBadnotiList.omp";
		form.target="popup";
		form.submit();
	};

</script>

		<div id="location">
			首頁  &gt; 會員管理中心  &gt; 使用者管理  &gt; <strong>懲戒會員管理</strong> 
		</div><!-- //location -->

		<h1>懲戒會員管理</h1>

		<s:form id="popPunishMemberForm" theme="simple">
			<input type="hidden" name="usMemberPunish.mbrNo" value="${usMemberPunish.mbrNo}" />
			<input type="hidden" name="usMemberPunish.mbrId" value="${usMemberPunish.mbrId}" />
		</s:form>

		<h2>基本資訊</h2>
		<table class="tabletype02">
			<colgroup>
				<col style="width:150px;">
				<col >
				<col style="width:150px;">
				<col >
			</colgroup>
			<tbody>
			<tr>
				<th>帳號</th>
				<td class="text_l"><a href="javascript:memberInfo();"><strong>${usMemberPunish.mbrId}</strong></a></td>
				<th>會員狀態</th>
				<td class="text_l">懲戒會員&nbsp;&nbsp;
					<a class="btn_s" href="javascript:popPunishMemberClose('${usMemberPunish.mbrNo}', '${usMemberPunish.mbrId}')"><span>解除懲戒</span></a>
				</td>
			</tr>
			<tr>
				<th>懲戒日期</th>
				<td class="text_l"><g:text value="${usMemberPunish.pnshStrtDttm}" format="L####-##-## ##:##"/></td>
				<th>處懲評論總數</th>
				<td class="text_l"><strong class="point2">${usMemberPunish.pnshNotiCnt}</strong> 筆</td>
			</tr>
			</tbody>
		</table>

		<p class="fl mt30">※ 處懲評論之記錄.</p>
		<p class="fr mt25 mb05">處懲評論總數 : 共 <strong class="point2">${usMemberPunish.pnshNotiCnt}</strong> 筆 
			<a class="btn_s" href="javascript:popBadnotiList();"><span>查看評論</span></a></p>
		<!-- 리스트 없음 -->
		<table class="tabletype02">
			<colgroup>
				<col style="width:60px;">
				<col >
				<col >
				<col >
				<col >
			</colgroup>
			<thead>
			<tr>
				<th>序號</th>
				<th>處懲評論數</th>
				<th>懲戒日期</th>
				<th>解戒日期</th>
				<th>管理者ID</th>
			</tr>
			</thead>
			<tbody>
			<s:if test="usMemberPunishList.size == 0">
			<tr><td colspan='5'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.member.punish.punishMemberMbrNoList.msg.none_result'/></span></td></tr>
			</s:if>
			<s:else>
			<c:set var="resultNum" value="${srchCnt - (usMemberPunish.page.no-1)*10 }"/>
			<s:iterator value="usMemberPunishList" status="status">
			<tr>
				<td>${resultNum}</td>
				<td>${pnshNotiCnt} 筆</td>
				<td><g:text value="${pnshStrtDttm}" format="L####-##-## ##:##"/></td>
				<td><g:text value="${pnshEndDttm}" format="L####-##-## ##:##"/></td>
				<td>${updId}</td>
			</tr>
			<c:set var="resultNum" value="${resultNum-1 }"/>
			</s:iterator>
			</s:else>
			</tbody>
		</table>
		<s:form action="punishMemberList" id="searchPunishForm" theme="simple">
		<input type="hidden" name="srchFlag" value="TRUE"/>
		<input type="hidden" name="usMemberPunish.page.no" value="1"/>
		<input type="hidden" name="usMemberPunish.searchValue" value="${usMemberPunish.searchValue}"/>
		<input type="hidden" name="usMemberPunish.startDate" value="${usMemberPunish.startDate}"/>
		<input type="hidden" name="usMemberPunish.endDate" value="${usMemberPunish.endDate}"/>
		</s:form>
		<p class="btn_wrap text_r mt25"><a class="btn" href="javascript:searchPunishList();"><span>目錄</span></a></p>
		<!-- paging -->
			<g:pageIndex item="${usMemberPunishList}"/>
		<!-- //paging -->

		<form id="memberForm" name="memberForm" action="<c:url value="/userMemMgr/findUserMemberDetail.omp?topCode=M001&leftCode=M001001001"/>" method="post">
			<input type="hidden" id="" name="userMember.mbrno" value="${usMemberPunish.mbrNo}" />
		</form>

</body>
</html>
