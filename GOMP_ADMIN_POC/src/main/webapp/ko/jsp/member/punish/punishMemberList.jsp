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

	$(document).ready( function()	{
	
		$( "#startDate" ).datepicker();
		$( "#endDate" ).datepicker();
		
		if( $("#startDate").val() == "" )	{
			setOrderSearchDateAdminPoC('7day', searchPunishForm.startDate, searchPunishForm.endDate);
		}
	
	} );

	function goPage(no) {
		$("form[name=searchPunishForm] input[name=usMemberPunish\\.page\\.no]").val(no);
		funcPunishList();
	}

	var funcPunishList = function() {
		$( "#searchPunishForm" ).submit();
	};

	var popPunishMember = function(mgrId) {
		
		var form = popPunishMemberForm;
		width = 480;
		height = 600;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		scrollOption = "Yes";
		
		window.open("","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");		
						
		form.action="${pageContext.request.contextPath }/member/popPunishMember.omp?mgrId=" + mgrId;
		form.target="popup";
		form.submit();
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

	var searchPunishList = function() {
		
		if(showValidate('searchPunishForm', 'dialog', 'check input value.')) {
			$( "#searchPunishForm" ).submit();
		}
	
	};

	var clearSrchCond = function( mode ) {
		$("#searchValue").val("");
		setOrderSearchDateAdminPoC('7day', searchPunishForm.startDate, searchPunishForm.endDate);
	};

	var punishMemberMbrNoList = function(mbrNo) {
		$( "#mbrNo" ).val( mbrNo );
		$( "#searchPunishForm" ).attr("action", "<c:url value="/member/punishMemberMbrNoList.omp"/>");
		$( "#searchPunishForm" ).submit();
	};

</script>

		<div id="location">
			Home &gt; 회원관리 &gt; 사용자관리 &gt; <strong>징계회원관리</strong> 
		</div><!-- //location -->

		<h1>징계회원관리</h1>

		<s:form id="popPunishMemberForm" theme="simple">
			<input type="hidden" name="usMemberPunish.mbrNo" />
			<input type="hidden" name="usMemberPunish.mbrId" />
		</s:form>

		<s:form action="punishMemberList" id="searchPunishForm" theme="simple">
			<input type="hidden" name="srchFlag" value="TRUE"/>
			<input type="hidden" name="usMemberPunish.page.no" value="1"/>
			<input type="hidden" id="mbrNo" name="usMemberPunish.mbrNo" />
		<table class="tabletype01">
			<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
			<tr>
				<th>검색조건</th>
				<td class="align_td line2_5">					
					<label for="#" style="width:60px">아이디</label>
					<input id="searchValue" type="text" name="usMemberPunish.searchValue" class="txt" value="<g:tastring>${usMemberPunish.searchValue}</g:tastring>" style="width:150px;" />
					<br />
					<label for="#">징계처리일</label>
					<input type="text" id="startDate" name="usMemberPunish.startDate" value="<g:text value='${usMemberPunish.startDate}' format='L####-##-##'/>" class="txt" style="width:80px;" maxlength="10" readonly
						v:scompare="le,@{usMemberPunish.endDate}" m:scompare="<gm:string value="jsp.member.punish.punishMemberList.msg.check_date"/>" />
					&nbsp; ~ &nbsp;
					<input type='text' id="endDate" name="usMemberPunish.endDate" value="<g:text value='${usMemberPunish.endDate}' format='L####-##-##'/>" class="txt" style="width:80px;" maxlength="10" readonly/>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('today', searchPunishForm.startDate, searchPunishForm.endDate);"><span>오늘</span></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('7day', searchPunishForm.startDate, searchPunishForm.endDate);"><strong>최근1주</strong></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('1month', searchPunishForm.startDate, searchPunishForm.endDate);"><span>최근1개월</span></a>
				</td>
				<td rowspan="2" class="text_c" >
					<a class="btn" href="javascript:searchPunishList();"><strong>검색</strong></a>
					<a class="btn" href="javascript:clearSrchCond();"><span>검색초기화</span></a>
				</td>
			</tr>
		</table>

		<p class="fr mt25">검색 회원수 : ${srchCnt} 명</p>
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
				<th>아이디</th>
				<th>징계게시물수</th>
				<th>징계처리일시</th>
				<th>관리</th>
			</tr>
			</thead>
			<tbody>
			<s:if test="srchFlag != 'TRUE'">
			<tr><td colspan='5'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.admin.common.first.page'/></span></td></tr>
			</s:if>
			<s:else>
				<s:if test="usMemberPunishList.size == 0">
			<tr><td colspan='5'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.member.punish.punishMemberList.msg.none_result'/></span></td></tr>
				</s:if>
				<s:else>
				<c:set var="resultNum" value="${srchCnt - (usMemberPunish.page.no-1)*10 }"/>
				<s:iterator value="usMemberPunishList" status="status">
			<tr>
				<td>${resultNum}</td>
				<td><a href="javascript:punishMemberMbrNoList('${mbrNo}');"><strong>${mbrId}</strong></a></td>
				<td>${pnshNotiCnt} 개</td>
				<td><g:text value="${pnshStrtDttm}" format="L####-##-## ##:##"/></td>
				<td><a class="btn_s" href="javascript:popPunishMemberClose('${mbrNo}', '${mbrId}');"><span>징계해지</span></a></td>
			</tr>
				<c:set var="resultNum" value="${resultNum-1 }"/>
				</s:iterator>
				</s:else>
			</s:else>
			</tbody>
		</table>
		</s:form>
		<!-- paging -->
			<g:pageIndex item="${usMemberPunishList}"/>
		<!-- //paging -->

</body>
</html>
