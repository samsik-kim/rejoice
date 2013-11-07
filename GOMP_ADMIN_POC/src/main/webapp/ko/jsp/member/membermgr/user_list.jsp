<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<script type="text/javascript">

	$(document).ready(function(){
		$("#startDate").datepicker();
		$("#endDate").datepicker();
		
		if('${sc.searchType}' != "hp"){
			$("#ex_hpno").hide();
		} else{
			$("#ex_hpno").show();
		}
		
		if('${sc.startDate}' == "") {
			var startDate = document.getElementById("startDate");
			var endDate = document.getElementById("endDate");
			
			setOrderSearchDateAdminPoC("7day", startDate, endDate);
		}
	});
	
	function selType_change(){
		$("#searchNm").val("");
		if($("#searchType").val() == "hp"){
			$("#ex_hpno").show();	
		} else {
			$("#ex_hpno").hide();
		}
	}
	
	// Search Date
	function search_date(term) {
		var startDate = document.getElementById("startDate");
		var endDate = document.getElementById("endDate");
		
		setOrderSearchDateAdminPoC(term, startDate, endDate);
	}
	
	// Search User Member
	function selectUserMember(){
		if(showValidate('userMember', 'default', '<gm:string value="jsp.common.dialog.title"/>')){
			$("#searchNm").val($.trim($("#searchNm").val()));
			$("form[name=userMember] input[name=sc\\.currentPageNo]").val(1);
			
			$("#userMember").attr("target", "_self");
			$("#userMember").attr("action", "findUserMemberMgrList.omp");
			$("#userMember").submit();
		}
	}
	
	// User Member Info Detail View	
	function member_detail(mbrno){
		$("form[name=userMember] input[name=userMember\\.mbrno]").val(mbrno);
		
		$("#userMember").attr("target", "_self");
		$("#userMember").attr("action", "findUserMemberDetail.omp");
		$("#userMember").submit();
	}
	
	// Value Initialization
	function init() {
		$("#mbrclscd option:first").attr("selected", "true");		// Member Class SelectBox Initialization 
		$("#mbrstatcd option:first").attr("selected", "true");		// Member State SelectBox Initialization
		
		var startDate = document.getElementById("startDate");
		var endDate = document.getElementById("endDate");

		setOrderSearchDateAdminPoC("7day", startDate, endDate);		// Search Date Default Initialization
		
		$("#searchType option:first").attr("selected", "true");		// Search Type Initialization
		
		selType_change();
		
		$("#searchNm").val("");		// Search Word Initialization
		
	}
	
	function goPage(no) {
		if(showValidate('userMember', 'default', '<gm:string value="jsp.common.dialog.title"/>')){
			$("#searchNm").val($.trim($("#searchNm").val()));
			$("form[name=userMember] input[name=sc\\.currentPageNo]").val(no);
	
			$("#userMember").attr("target", "_self");
			$("#userMember").attr("action", "findUserMemberMgrList.omp");
			$("#userMember").submit();
		}
	}
</script>	

<gc:codeList var="codeList" group="US0001" filter="admin.user"/>

<form name="userMember" id="userMember" method="post">
<input type="hidden" id="pageno" name="sc.currentPageNo" value="<g:tagAttb value='${sc.currentPageNo}'/>"/>
<input type="hidden" name="userMember.mbrno" value="" />
	<!-- Contents -->
	<div id="location">Home &gt; 회원관리 &gt; 사용자관리 &gt; <strong>회원정보관리</strong> </div>
	<h1>회원정보관리</h1>
	<table class="tabletype01">
		<colgroup>
			<col style="width:100px;" />
			<col />
			<col style="width:125px"/>
		</colgroup>
		<tr>
			<th>검색조건</th>
			<td class="align_td line2_5">
				<label for="#">회원분류</label>
				<select id="mbrclscd" name="sc.mbrclscd" style="width:104px;">
					<option value="ALL">전체</option>
					<c:forEach items="${codeList}" var="item">
						<option value="${item.dtlFullCd}" <c:if test="${item.dtlFullCd eq sc.mbrclscd}">selected="selected"</c:if>><g:text value="${item.addField1}"/></option>
					</c:forEach>
				</select>
				<label for="#">회원상태</label>
				<select id="mbrstatcd" name="sc.mbrstatcd" style="width:104px;">
					<option value="ALL">전체</option>
					<gc:options group="US0005" codeType="full" filter="admin" value="${sc.mbrstatcd}"/>
				</select>
				<br/>
				<label for="#">가입일</label>
				<input id="startDate" name="sc.startDate" type="text" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.startDate}' format='L####-##-##'/>" readonly/> ~ 
				<input type="text" id="endDate" name="sc.endDate" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.endDate}' format='L####-##-##'/>" 
					v:scompare="ge,@{sc.startDate}" m:scompare="<gm:string value='jsp.member.common.msg.datechk'/>" readonly/> 
				<a class="btn_s" href="javascript:search_date('today');"><span>오늘</span></a>
				<a class="btn_s" href="javascript:search_date('7day');"><strong>최근1주</strong></a>
				<a class="btn_s" href="javascript:search_date('1month');"><span>최근1개월</span></a>
			</td>
			<td rowspan="2" class="text_c" >
				<a class="btn" href="javascript:selectUserMember();" ><strong>검색</strong></a>
				<a class="btn" href="javascript:init();"><span>검색초기화</span></a>
			</td>
		</tr>
		<tr>
			<th>검색어</th>
			<td class="align_td">
				<select id="searchType" name="sc.searchType" style="width:104px;" onchange="javascript:selType_change();">
                   	<option value="id" selected>아이디</option>
					<option value="email" <c:if test="${sc.searchType eq 'email' }">selected</c:if>>이메일</option>
                    <option value="hp" <c:if test="${sc.searchType eq 'hp' }">selected</c:if>>휴대폰 번호</option>
				</select>
				<input type="text" id="searchNm" name="sc.searchNm" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.searchNm}'/>" onkeydown="javascript:if (event.keyCode == 13) {selectUserMember();}" />&nbsp;&nbsp; 
				<span id="ex_hpno">입력 예 : 01012341234</span>
			</td>
		</tr>
	</table>
	<p class="fr mt25">검색회원수 : <g:html value="${userMember.page.totalCount}"/> 명</p>
	<table class="tabletype02 mt25">
		<colgroup>
			<c:choose>
				<c:when test="${!empty userMemberList}"><col style="width:60px;" /></c:when>
				<c:otherwise><col style="width:8%;" ></c:otherwise>
			</c:choose>
			<col />
			<col />
			<col />
			<col />
			<col />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th>
				<th>아이디</th>
				<th>회원분류</th>
				<th>단말등록수</th>
				<th>가입일</th>
				<th>회원상태</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${searchYn eq 'N'}">
					<tr><td colspan="6" class="text_c">검색조건을 선택하신 후 ‘검색’ 버튼을 클릭하세요.</td></tr>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${!empty userMemberList}">
							<c:forEach items="${userMemberList}" var="item">
								<tr>
									<td><g:html value="${item.rnum}" /></td>
									<td><a href="javascript:member_detail('${item.mbrno }');"><strong><g:html value="${item.mbrid }" /></strong></a></td>
									<td>
										<c:forEach items="${codeList}" var="code">
											<c:if test="${code.dtlFullCd eq item.mbrclscd}"><g:html value="${code.addField1}"/></c:if>
										</c:forEach>
									</td>
									<td><g:html value="${item.mobilecnt}" /></td>
									<td><g:html value="${item.mbrstartdt}" format="L####-##-##" /></td>
									<td><gc:text code="${item.mbrstatcd}" /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="6" class="text_c">검색 결과가 없습니다.</td></tr>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<!-- paging -->
	<div align="center">
		<g:pageIndex item="${userMemberList}"/>
	</div>
	<!-- //paging -->
	
</form>