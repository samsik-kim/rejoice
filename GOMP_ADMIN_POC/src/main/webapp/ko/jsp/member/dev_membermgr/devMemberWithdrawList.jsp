<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<head>
<script type="text/javascript"> 
	$(document).ready( function(){
		$( "#withdrawReqStartDt" ).datepicker();
		$( "#withdrawReqEndDt" ).datepicker();
		$( "#withdrawApprStartDt" ).datepicker();
		$( "#withdrawApprEndDt" ).datepicker();
		
		if("${sc.withdrawReqStartDt }" == "") {
			setOrderSearchDateAdminPoC("7day", document.getElementById("withdrawReqStartDt"), document.getElementById("withdrawReqEndDt"));
		}
		
		if("${sc.withdrawApprStartDt }" == "") {
			setOrderSearchDateAdminPoC("7day", document.getElementById("withdrawApprStartDt"), document.getElementById("withdrawApprEndDt"));
		}
		
		// Search Condition Setting
		var option = '<option value="">전체</option>';
		var mbrcatcd, bizcatcd;
		
		if($("#mbrclscd").val() != ""){
			if($("#mbrcatcd").val() == ""){
				mbrcatcd = option + '<gc:options group="US0002" codeType="full" filter="admin.dev" value="${sc.mbrcatcd}"/>';
			}
			
			$("#mbrcatcd").html(mbrcatcd);
			
			if($("#mbrclscd").val() == '${CONST.MEM_CLS_BUSINESS}' && $("#mbrcatcd").val() == '${CONST.MEM_TYPE_DEV_PAY}'){
				$("#bizcatcd").empty();
				
				bizcatcd = option + '<gc:options group="US0009" codeType="full" filter="admin.dev" value="${sc.bizcatcd}"/>';
				
				$("#bizcatcd").html(bizcatcd);
			} else {
				$("#bizcatcd").empty();
				$("#bizcatcd").html(option);
			}
		}else{
			$("#mbrcatcd").empty();
			$("#mbrcatcd").html(option);
			
			$("#bizcatcd").empty();
			$("#bizcatcd").html(option);
		}
		
		// 검색 초기화
		$("#searchIntiBtn").click( function() {
			$("#mbrclscd option:first").attr("selected", "true");
			
			selType_change();
			
			$("#devmbrstatcd option:first").attr("selected", "true");
			
			setOrderSearchDateAdminPoC("7day", document.getElementById("withdrawReqStartDt"), document.getElementById("withdrawReqEndDt"));
			setOrderSearchDateAdminPoC("7day", document.getElementById("withdrawApprStartDt"), document.getElementById("withdrawApprEndDt"));
			
			$("#searchType option:first").attr("selected", "true");
			$("#searchValue").val("");
		});
		
		$("#mbrclscd").change( function() {
			selType_change();
		});
		
		$("#mbrcatcd").change( function() {
			selType_change();
		});
		
		$("#searchType").change( function() {
			$("#searchValue").val("");	
		});
	}); 
	
	
	// Seltype Change
	function selType_change(){
		var option = '<option value="">전체</option>';
		var mbrcatcd, bizcatcd;
		
		if($("#mbrclscd").val() != ""){
			if($("#mbrcatcd").val() == ""){
				mbrcatcd = option + '<gc:options group="US0002" codeType="full" filter="admin.dev" />';
			}
			
			$("#mbrcatcd").html(mbrcatcd);
			
			if($("#mbrclscd").val() == '${CONST.MEM_CLS_BUSINESS}' && $("#mbrcatcd").val() == '${CONST.MEM_TYPE_DEV_PAY}'){
				$("#bizcatcd").empty();
				
				bizcatcd = option + '<gc:options group="US0009" codeType="full" filter="admin.dev" />';
				
				$("#bizcatcd").html(bizcatcd);
			} else {
				$("#bizcatcd").empty();
				$("#bizcatcd").html(option);
			}
		}else{
			$("#mbrcatcd").empty();
			$("#mbrcatcd").html(option);
			
			$("#bizcatcd").empty();
			$("#bizcatcd").html(option);
		}
	}
	
	var payDateSet = function(payDate, flag) {
		if(flag == 'req') setOrderSearchDateAdminPoC(payDate, document.getElementById("withdrawReqStartDt"), document.getElementById("withdrawReqEndDt"));
		if(flag == 'end') setOrderSearchDateAdminPoC(payDate, document.getElementById("withdrawApprStartDt"), document.getElementById("withdrawApprEndDt"));
	};
	
	function reasonView(mbrNo){
		width = 440;
		height = 310;
		action = env.contextPath + "/devMemMgr/popWithdrawView.omp?devMember.mbrno="+mbrNo + "&" + $("#sc").val();
	
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		scrollOption = "no";
		  
		window.open(action,"withdrawList", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");
	}
	
	function searchList(){
		$("#searchValue").val($.trim($("#searchValue").val()));
		$("form[name=frm] input[name=sc\\.currentPageNo]").val(1);
		
		$("#frm").attr('action', env.contextPath + '/member/devMemberWithdrawList.omp');
		$("#frm").submit();
	}
	
	function goPage(no) {
		$("#searchValue").val($.trim($("#searchValue").val()));
	    $("form[name=frm] input[name=sc\\.currentPageNo]").val(no);
	    frmSubmit();
	}
	
	function frmSubmit() {
		document.getElementsByName("frm").item(0).submit();
	}
	
</script>
</head>

<div id="location">
	Home &gt; 회원관리 &gt; 개발자관리 &gt; <strong>탈퇴회원내역</strong>
</div>
<!-- //location -->

<h1>탈퇴회원내역</h1>

<form name="frm" action="" id="frm" method="post" onsubmit="return showValidate('frm', 'default', '<gm:string value="jsp.common.dialog.title"/>');">
<input type="hidden" name="sc.currentPageNo" value="<g:tagAttb value='${sc.currentPageNo}'/>'"/>
<input type="hidden" id="sc" name="" value="<g:printBean prefix='sc.' value='${sc}' outType='qs'/>" />
<table class="tabletype01">

	<colgroup>
		<col style="width: 100px;">
		<col>
		<col style="width: 125px">
	</colgroup>

	<tr>

		<th>검색조건</th>

		<td class="align_td line2_5">
			<label for="#">회원분류</label> 
			<select id="mbrclscd" name="sc.mbrclscd" style="width:85px;" onchange="javascript:selType_change();">
				<option value="">전체</option>
				<gc:options group="US0001" codeType="full" value="${sc.mbrclscd }"/>
			</select> 
			<select id="mbrcatcd" name="sc.mbrcatcd" style="width:85px;" onchange="javascript:selType_change();">
				<option value="">전체</option>
			</select> 
			
			<select id="bizcatcd" name="sc.bizcatcd" style="width: 85px;">
				<option value="">전체</option>
			</select> 
			
			<label for="#">회원상태</label> 
			<select id="devmbrstatcd" name="sc.devmbrstatcd" style="width: 85px;">
				<option value="">전체</option>
				<gc:options group="US0008" codeType="full" filter="admin.devMemberWithdraw" value="${sc.devmbrstatcd }"/>
			</select> <br />
	
			<label for="#" style="width:60px;">탈퇴신청일</label> 
				<input id="withdrawReqStartDt" name="sc.withdrawReqStartDt" type="text" class="txt" style="width: 80px;" 
					value="<g:tagAttb value='${sc.withdrawReqStartDt }' format='L####-##-##' />" readonly/> ~ 
				<input id="withdrawReqEndDt" name="sc.withdrawReqEndDt" type="text" class="txt" style="width: 80px;"
					v:scompare="ge,@{sc.withdrawReqStartDt}" m:scompare="<gm:string value='jsp.member.common.msg.datechk'/>"
					value="<g:tagAttb value='${sc.withdrawReqEndDt }' format='L####-##-##' />" readonly/> 
				<a class="btn_s" id="todayBtn" name="todayBtn" href="javascript:payDateSet('today', 'req');" ><span>오늘</span></a> 
				<a class="btn_s" id="weekBtn" name="weekBtn" href="javascript:payDateSet('7day', 'req');" ><strong>최근1주</strong></a>
				<a class="btn_s" id="monthBtn" name="monthBtn" href="javascript:payDateSet('1month', 'req');" ><span>최근1개월</span></a> <br />
	
			<label for="#" style="width:60px;">탈퇴처리일</label> 
				<input id="withdrawApprStartDt" name="sc.withdrawApprStartDt" type="text" class="txt" style="width: 80px;" 
					value="<g:tagAttb value='${sc.withdrawApprStartDt }' format='L####-##-##' />" readonly/> ~  
				<input id="withdrawApprEndDt" name="sc.withdrawApprEndDt" type="text" class="txt" style="width: 80px;"
					v:scompare="ge,@{sc.withdrawApprStartDt}" m:scompare="<gm:string value='jsp.member.common.msg.datechk'/>"
					value="<g:tagAttb value='${sc.withdrawApprEndDt }' format='L####-##-##' />"  readonly/> 
				<a class="btn_s" id="todayBtn1" name="todayBtn1" href="javascript:payDateSet('today', 'end');" ><span>오늘</span></a> 
				<a class="btn_s" id="weekBtn1" name="weekBtn1" href="javascript:payDateSet('7day', 'end');" ><strong>최근1주</strong></a>
				<a class="btn_s" id="monthBtn1" name="monthBtn1" href="javascript:payDateSet('1month', 'end');" ><span>최근1개월</span></a> <br />
		</td>

		<td rowspan="2" class="text_c">
			<a class="btn" id="searchBtn" name="searchBtn" href="#" onclick="searchList();"><strong>검색</strong></a>
			<a class="btn" href="#" id="searchIntiBtn" name="searchIntiBtn"><span>검색초기화</span></a>
		</td>
	</tr>

	<tr>
		<th>검색어</th>
		<td class="align_td">
			<select id="searchType" name="sc.searchType" style="width: 104px;">
				<option value="id" <c:if test="${sc.searchType eq 'id' || sc.searchType eq null }">selected="selected"</c:if>>아이디</option>
				<option value="name" <c:if test="${sc.searchType eq 'name' }">selected="selected"</c:if>>이름/상호</option>
			</select> 
			<input id="searchValue" name="sc.searchNm" type="text" class="txt" style="width: 80px;" value="<g:tagAttb value='${sc.searchNm}'/>" /></td>
	</tr>

</table>
</form>
<p class="fr mt25">검색 회원수 : <strong><g:html value="${memberContract.page.totalCount}"/></strong> 명</p>

<form name="frm1" id="frm1">
<table class="tabletype02">
	<colgroup>
		<col style="width: 7%;">
		<col>
		<col>
		<col>
		<col>
		<col>
		<col>
		<col>
		<col>
		<col>
	</colgroup>
	<thead>
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름/상호</th>
			<th>회원등급</th>
			<th>회원분류</th>
			<th>유료/무료</th>
			<th>탈퇴상태</th>
			<th>탈퇴신청일</th>
			<th>탈퇴처리일</th>
			<th>사유</th>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${searchYn eq 'N'}">
				<tr><td colspan="10">검색조건을 선택하신 후 ‘검색’ 버튼을 클릭하세요.</td></tr>
			</c:when>
			<c:otherwise>
				<c:choose>   
					<c:when test="${not empty resultList }">
						<c:forEach items="${resultList }" var="member" varStatus="status">	
							<tr>		
								<td><g:html value="${member.rnum}"/></td>
								<td><a href="<c:url value='/devMemMgr/findDevMemberDetail.omp?devMember.mbrno=${member.mbrNo }&&topCode=M001&leftCode=M001002001' />"><strong title="${member.devEndReasonDscr }"><g:html value="${member.mbrId}"/></strong></a></td>
								<td><g:html value="${member.mbrNm}"/></td>
								<td><gc:text code="${member.mbrGrCd }" /></td>
								<td><gc:text code="${member.mbrClsCd }" /></td>
								<td><gc:text code="${member.mbrCatCd }" /></td>
								<td><gc:text code="${member.devMbrStatCd }" /></td>
								<td><g:html value="${member.devEndReqDt }" format="L####-##-##"/></td>
								<td><g:html value="${member.mbrEndDt }" format="L####-##-##"/></td>
								<td><a id="popBtn" class="btn_s" href="#" onclick="javascript:reasonView('${member.mbrNo }');"><span>사유보기</span></a></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr><td colspan="10">검색 결과가 없습니다.</td></tr> 
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</tbody>
</table>
</form>

	<!-- paging -->
	<div align="center">
		<g:pageIndex item="${resultList}" />
	</div>
	<!-- //paging -->
