<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<head>
<script type="text/javascript"> 
	$(document).ready( function(){
		$("#transReqStartDt").datepicker();
		$("#transReqEndDt").datepicker();
		
		
		if("${sc.transReqStartDt}" == "") {
			setOrderSearchDateAdminPoC("7day", document.getElementById("transReqStartDt"), document.getElementById("transReqEndDt"));
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
		
		// search initialization
		$("#searchInitBtn").click( function() {
			$("#mbrclscd option:first").attr("selected", "true");

			selType_change();
			
			$("#devmbrstatcd option:first").attr("selected", "true");
			
			setOrderSearchDateAdminPoC("7day", document.getElementById("transReqStartDt"), document.getElementById("transReqEndDt"));
			
			$("#searchType option:first").attr("selected", "true");
			$("#searchValue").val("");
		});
		
	});
	
	var payDateSet = function(term) {
		setOrderSearchDateAdminPoC(term, document.getElementById("transReqStartDt"), document.getElementById("transReqEndDt"));
	};
	
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
	
	function searchList(){
		$("#searchValue").val($.trim($("#searchValue").val()));
		$("form[name=frm] input[name=sc\\.currentPageNo]").val(1);
		
		$("#frm").attr('action', env.contextPath + '/member/devChangeMemberList.omp');
		$("#frm").submit();
	}
	
	function goPage(no) {
		$("#searchValue").val($.trim($("#searchValue").val()));
	    $("form[name=frm] input[name=sc\\.currentPageNo]").val(no);
	    frmSubmit();
	}
	
	function frmSubmit() {
	    //if (showValidate("frm")) {
	        document.getElementsByName("frm").item(0).submit();
	    //}
	}

</script>
</head>

<div id="location">
	Home &gt; 회원관리 &gt; 개발자관리 &gt; <strong>전환회원내역</strong>
</div><!-- //location -->

<h1>전환회원내역</h1>
<form name="frm" action="" id="frm" method="post" onsubmit="return showValidate('frm', 'default', '<gm:string value="jsp.common.dialog.title"/>');">
<input type="hidden" id="pageno" name="sc.currentPageNo" value="<g:tagAttb value='${sc.currentPageNo}'/>"/>
<table class="tabletype01">
	<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>

<tr>
	<th>검색조건</th>
	<td class="align_td line2_5">
		<label for="#">회원분류</label>
			<select id="mbrclscd" name="sc.mbrclscd" style="width:85px;" onchange="javascript:selType_change();">
	        	<option value="">전체</option>
	        	<gc:options group="US0001" codeType="full" value="${sc.mbrclscd}"/>
	        </select>
	
	        <select id="mbrcatcd" name="sc.mbrcatcd" style="width:85px;" onchange="javascript:selType_change();">
	        	<option value="">전체</option>
	        </select>
	
	        <select id="bizcatcd" name="sc.bizcatcd" style="width:85px;">
	        	<option value="">전체</option>
	        </select>

		<label for="#">회원상태</label>
	        <select id="devmbrstatcd" name="sc.devmbrstatcd" style="width:85px;">
	        	<option value="">전체</option>
	        	<gc:options group="US0008" codeType="full" filter="admin.devMemberChange" value="${sc.devmbrstatcd}"/>
	        </select>
		<br />

		<label for="#" style="width:60px;">전환신청일</label>

		<input id="transReqStartDt" name="sc.transReqStartDt" type="text" class="txt" style="width: 80px;"
							value="<g:tagAttb value='${sc.transReqStartDt}' format='L####-##-##' />" readonly="readonly" /> ~ 
		<input id="transReqEndDt" name="sc.transReqEndDt" type="text" class="txt" style="width: 80px;" 
							v:scompare="ge,@{sc.transReqStartDt}" m:scompare="<gm:string value='jsp.member.common.msg.datechk'/>"
							value="<g:tagAttb value='${sc.transReqEndDt }' format='L####-##-##' />" readonly="readonly" /> 
		<a class="btn_s" id="todayBtn" name="todayBtn" href="javascript:payDateSet('today', 'req');" ><span>오늘</span></a> 
		<a class="btn_s" id="weekBtn" name="weekBtn" href="javascript:payDateSet('7day', 'req');" ><strong>최근1주</strong></a>
		<a class="btn_s" id="monthBtn" name="monthBtn" href="javascript:payDateSet('1month', 'req')" ><span>최근1개월</span></a> <br />
	</td>

	<td rowspan="2" class="text_c">
		<a class="btn" id="searchBtn" name="searchBtn" href="#" onclick="searchList();"><strong>검색</strong></a>
		<a class="btn" href="#" id="searchInitBtn" name="searchInitBtn"><span>검색초기화</span></a>
	</td>

</tr>

<tr>
	<th>검색어</th>
	<td class="align_td">
	    <select id="searchType" name="sc.searchType" style="width:104px;">
	    	<option value="id" <c:if test="${sc.searchType eq 'id' || sc.searchType eq null }">selected</c:if>>아이디</option>
			<option value="name" <c:if test="${sc.searchType eq 'name' }">selected</c:if>>이름/상호</option>
	    </select>

		<input id="searchValue" name="sc.searchNm" type="text" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.searchNm}'/>" />
	</td>
</tr>

</table>

<p class="fr mt25">검색 회원수 : <strong><g:html value="${memberContract.page.totalCount}"/></strong> 명</p>

<!-- 리스트 없음 -->

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
	</colgroup>

	<thead>
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>이름/상호</th>
			<th>회원등급</th>
			<th>회원분류</th>
			<th>유료/무료</th>
			<th>회원상태</th>
			<th>전환신청일</th>
			<th>전환처리일</th>
		</tr>
	</thead>

	<tbody>
		<c:choose>
			<c:when test="${searchYn eq 'N'}">
				<tr><td colspan="9">검색조건을 선택하신 후 ‘검색’ 버튼을 클릭하세요.</td></tr>
			</c:when>
			<c:otherwise>
				<c:choose>	
					<c:when test="${not empty resultList }">
						<c:forEach items="${resultList }" var="member" varStatus="status">
							<tr>
								<td>${member.rnum}</td>
								<td><a href="<c:url value='/devMemMgr/findDevMemberDetail.omp?devMember.mbrno=${member.mbrNo }&topCode=M001&leftCode=M001002001' />"><strong>${member.mbrId }</strong></a></td>
								<td>${member.mbrNm }</td>
								<td><gc:text code="${member.mbrGrCd }" /></td>
								<td><gc:text code="${member.mbrClsCd }" /></td>
								<td><gc:text code="${member.mbrCatCd }" /></td>
								<td><gc:text code="${member.devMbrStatCd }" /></td>
								<td><g:html value="${member.regDttm }" format="L####-##-##" /> </td>
								<td><g:html value="${member.regDisposalDttm }" format="L####-##-##" /></td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr><td colspan="9">검색 결과가 없습니다.</td></tr>
					</c:otherwise>
				</c:choose>
			</c:otherwise>
		</c:choose>
	</tbody>

</table>
<!-- paging -->
	<div align="center">
		<g:pageIndex item="${resultList}" />
	</div>
<!-- //paging -->
</form>			
