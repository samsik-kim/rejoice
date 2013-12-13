<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<title>로그인정보</title>

<script type="text/javascript">
	
	$(document).ready(function(){
		$("#startDate").datepicker();
		$("#endDate").datepicker();
		
		if('${sc.startDate}' == "") {
			var startDate = document.getElementById("startDate");
			var endDate = document.getElementById("endDate");
			
			setOrderSearchDateAdminPoC("7day", startDate, endDate);
		}
	});

	//Search Date
	function search_date(term) {
		var startDate = document.getElementById("startDate");
		var endDate = document.getElementById("endDate");
		
		setOrderSearchDateAdminPoC(term, startDate, endDate);
	}

	// Value Initialization
	function init() {
		var startDate = document.getElementById("startDate");
		var endDate = document.getElementById("endDate");

		setOrderSearchDateAdminPoC("7day", startDate, endDate);		// Search Date Default Initialization
		
		$("#searchNm").val("");		// Search Word Initialization
		
	}
	
	// Search Login Info History
	function selectLoginInfoHistory(){
		if(showValidate('userMember', 'default', '<gm:string value="jsp.common.dialog.title"/>')){
			$("#searchNm").val($.trim($("#searchNm").val()));
			$("form[name=userMember] input[name=sc\\.currentPageNo]").val(1);
			
			$("#userMember").attr("target", "_self");
			$("#userMember").attr("action", "popLoginInfoHistory.omp");
			$("#userMember").submit();
		}
	}
	
	// Paging
	function goPage(no) {
		if(showValidate('userMember', 'default', '<gm:string value="jsp.common.dialog.title"/>')){
			$("#searchNm").val($.trim($("#searchNm").val()));
			$("form[name=userMember] input[name=sc\\.currentPageNo]").val(no);
			
			$("#userMember").attr("target", "_self");
			$("#userMember").attr("action", "popLoginInfoHistory.omp");
			$("#userMember").submit();
		}
	}
	
</script>

<form name="userMember" id="userMember" method="post">
<input type="hidden" id="pageno" name="sc.currentPageNo" value="<g:tagAttb value='${sc.currentPageNo}'/>"/>
<input type="hidden" id="mbrno" name="userMember.mbrno" value="<g:tagAttb value='${userMember.mbrno}'/>" />
	<div id="popup_area_810">
		<h1>로그인정보</h1>
		<table class="pop_tabletype01">
			<colgroup>
				<col style="width:100px;" />
				<col />
				<col style="width:125px" />
			</colgroup>
			<tbody>
			<tr>
				<th>접속일</th>
				<td class="align_td">
					<input id="startDate" name="sc.startDate" type="text" class="txt" value="<g:tagAttb value='${sc.startDate}' format='L####-##-##' />"  style="width:80px;" readonly/> ~ 
					<input type="text" id="endDate" name="sc.endDate" class="txt" value="<g:tagAttb value='${sc.endDate}' format='L####-##-##'/>"  style="width:80px;" 
							v:scompare="ge,@{sc.startDate}" m:scompare="<gm:string value='jsp.member.common.msg.datechk'/>" readonly/>
					<a class="btn_ss" href="javascript:search_date('today');"><strong>오늘</strong></a>
					<a class="btn_ss" href="javascript:search_date('7day');"><strong>최근 1주</strong></a>
					<a class="btn_ss" href="javascript:search_date('1month');"><strong>최근 1개월</strong></a>
				</td>
				<td rowspan="2" class="text_c" >
					<a class="btn" href="javascript:selectLoginInfoHistory();"><strong>검색</strong></a>
					<a class="btn" href="javascript:init();"><span>검색초기화</span></a>
				</td>
			</tr>
			<tr>
				<th>검색어</th>
				<td>
					<select id="searchType" name="sc.searchType" style="width:120px;">
	                    <option value="MDN" selected>MDN</option>
	                    <option value="MAC" <c:if test="${sc.searchType eq 'MAC' }">selected</c:if>>Wi-Fi MAC 주소</option>
					</select>
					<input type="text" id="searchNm" class="txt" name="sc.searchNm" style="width:70%;" value="<g:tagAttb value='${sc.searchNm}'/>" onkeydown="javascript:if (event.keyCode == 13) {selectLoginInfoHistory();}" />
				</td>
			</tr>
			</tbody>
		</table>
		<table class="pop_tabletype02 mt25">
			<colgroup>
				<col style="width:50px;" />
				<col />
				<col style="width:210px" />
				<col />
				<col />
			</colgroup>
			<thead>
			<tr>
				<th>NO</th>
				<th>일시</th>
				<th>Wi-Fi MAC 주소 / MDN</th>
				<th>모델명</th>
				<th>S.C버전</th>
			</tr>
			</thead>
			<tbody>
				<c:if test="${!empty userMemberList}">
					<c:forEach items="${userMemberList}" var="item">
						<tr>
							<td><g:html value="${userMember.page.totalCount - item.rnum + 1}" /></td>
							<td><span class="c_06f"><g:html value="${item.accdts}"/></span></td>
							<td>
								<g:html value="${item.accipaddr}"/>
								<c:if test="${!empty item.hpno}"> / <g:html value="${item.hpno}"/></c:if>
							</td>
							<td><g:html value="${item.modelnm}"/></td>
							<td><g:html value="${item.scver}"/></td>
						</tr>
					</c:forEach>
				</c:if>
			</tbody>
		</table>
		<!-- paging -->
		<div align="center">
			<g:pageIndex item="${userMemberList}"/>
		</div>
		<!-- //paging -->
		<div class="pop_btn" >
			<a class="btn" href="javascript:self.close();"><strong>닫기</strong></a>
		</div>
	</div><!-- //contents -->
</form>  