<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
<!--
var CURRENT_PAGE = "";
$(document).ready(function(){
	$("#stDt").datepicker();
	$("#enDt").datepicker();
	
	pageLoadAjaxListInner("searchFrm", "innerList", "/code/ajaxCodeListinner.do"); // 리스트 호출
	
	$("#excelBtn").click(function(){
		$("#stDt").val($("#stDt").val().replace(/-/g, ''));
		$("#enDt").val($("#enDt").val().replace(/-/g, ''));
		location.href = "/code/codeListExcel.do?stDt="+$("#stDt").val()+"&enDt="+$("#enDt").val();
	});	
});

//검색
function searchList(){
	$("#currentPage").val(CURRENT_PAGE);
	pageLoadAjaxListInner("searchFrm", "innerList", "/code/ajaxCodeListinner.do"); // 리스트 호출
}

//목록
function goList(currentPage){
	CURRENT_PAGE = currentPage;
	searchList();
}

//상세
function fn_detail(seqNo){
	$("#seqNo").val(seqNo);
	$("#searchFrm").attr('action','/code/codeDetail.do') ;
	$("#searchFrm").submit();
}
//-->
</script>
<div class="pmbox mar_b22">
<form name="searchFrm" id="searchFrm" method="post" enctype="application/x-www-form-urlencoded">
	<input type="hidden" name="currentPage" id="currentPage" value="${pageInfo.currentPage}" />
	<input type="hidden" name="seqNo" id="seqNo"/>
	<div class="fltl mar_trl20">
		시작일:<input type="text" name="stDt" id="stDt" class="w70" value="${info.stDt }"/>&nbsp;~
		종료일:<input type="text" name="enDt" id="enDt" class="w70" value="${info.enDt }"/>&nbsp;&nbsp;
		<select id="searchKey" name="searchKey" class="w128" title="검색조건선택">
			<option value="codeName" <c:if test="${info.searchKey eq 'codeName' }">selected</c:if>>종목명</option>
			<option value="codeNum" <c:if test="${info.searchKey eq 'codeNum' }">selected</c:if>>종목코드</option>
		</select>
	</div>
	<input type="text" id="searchValue" name="searchValue" class="w150" value="${info.searchValue }"  />
	<a href="#"><img id="searchBtn" src="/resource/images/common/btn_board_search.gif" alt="검색" onclick="javascript:searchList();"/></a>
</form>
</div>
<!-- Excel Download -->
<div class="fltr mar_trl20">
	<a href="#"><img id="excelBtn" src="/resource/images/common/btn_excel.gif" alt="excel" /></a>
</div>
<div id="innerList"></div>
<table align="right">
	<tr>
		<td align="right">
			<a href="#"><img id="regBtn" src="/resource/images/board/btn_regist.gif" alt="등록" /></a>
		</td>
	</tr>
</table>