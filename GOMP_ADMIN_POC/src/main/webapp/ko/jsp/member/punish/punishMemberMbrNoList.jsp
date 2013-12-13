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
		// alert("회원정보관리상세페이지 이동 예정");
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
			Home &gt; 회원관리 &gt; 사용자관리 &gt; <strong>징계회원관리</strong> 
		</div><!-- //location -->

		<form id="memberForm" name="memberForm" action="<c:url value="/userMemMgr/findUserMemberDetail.omp?topCode=M001&leftCode=M001001001"/>" method="post">
			<input type="hidden" id="" name="userMember.mbrno" value="${usMemberPunish.mbrNo}" />
		</form>

		<s:form id="popPunishMemberForm" theme="simple">
			<input type="hidden" name="usMemberPunish.mbrNo" value="${usMemberPunish.mbrNo}" />
			<input type="hidden" name="usMemberPunish.mbrId" value="${usMemberPunish.mbrId}" />
		</s:form>
		<h1>징계회원관리</h1>

		<h2>일반정보</h2>
		<table class="tabletype02">
			<colgroup>
				<col style="width:150px;">
				<col >
				<col style="width:150px;">
				<col >
			</colgroup>
			<tbody>
			<tr>
				<th>아이디</th>
				<td class="text_l"><a href="javascript:memberInfo();"><strong>${usMemberPunish.mbrId}</strong></a></td>
				<th>회원상태</th>
				<td class="text_l">징계회원	<a class="btn_s" href="javascript:popPunishMemberClose('${usMemberPunish.mbrNo}', '${usMemberPunish.mbrId}')"><span>해지하기</span></a>
				</td>
			</tr>
			<tr>
				<th>징계처리일시</th>
				<td class="text_l"><g:text value="${usMemberPunish.pnshStrtDttm}" format="L####-##-## ##:##"/></td>
				<th>징계 게시물수</th>
				<td class="text_l"><strong class="point2">${usMemberPunish.pnshNotiCnt}</strong> 건</td>
			</tr>
			</tbody>
		</table>
		<p class="fl mt30">※ 회원님의 징계 히스토리 입니다.</p>
		<p class="fr mt25 mb05">징계 게시물 : <strong class="point2">${usMemberPunish.pnshNotiCnt}</strong> 건 
			<a class="btn_s" href="javascript:popBadnotiList();"><span>게시물보기</span></a></p>
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
				<th>번호</th>
				<th>징계게시물수</th>
				<th>징계처리일시</th>
				<th>징계해지일시</th>
				<th>운영자ID</th>
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
				<td>${pnshNotiCnt} 건</td>
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
		<p class="btn_wrap text_r mt25"><a class="btn" href="javascript:searchPunishList();"><span>목록</span></a></p>
		<!-- paging -->
			<g:pageIndex item="${usMemberPunishList}"/>
		<!-- //paging -->

</body>
</html>
