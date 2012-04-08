<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
<!--
$(document).ready(function(){
	
	$("#stDt").datepicker();
	$("#enDt").datepicker();
	
	$("#options").tablesorter({
		sortList: [[1,0]], 
		headers: { 0:{sorter: false}, 3:{sorter: false}, 7:{sorter: false}}
	});
	
	pageLoadAjaxListInner("searchFrm", "innerList", "/member/ajaxlistInner.do"); // 리스트 호출
	
	//삭제
	$("#delBtn").click(function(){
		var delArr = document.getElementsByName("delch");
		var delValues = "";
		for(var i=0; i<delArr.length; i++){
			delValues += delArr[i].value + ",";
		}
		if(confirm("삭제 하시겠습니까?")){
			$("#delVal").val(delValues);
			$("#searchFrm").attr('action','/member/delete.do') ;
			$("#searchFrm").submit();
		}
	});
	
	//엑셀
	$("#excelBtn").click(function(){
		$("#stDt").val($("#stDt").val().replace(/-/g, ''));
		$("#enDt").val($("#enDt").val().replace(/-/g, ''));
		$("#searchFrm").attr('action','/member/excel.do');
		$("#searchFrm").submit();
	});
});

//검색
function searchList(){
		$("#stDt").val($("#stDt").val().replace(/-/g, ''));
		$("#enDt").val($("#enDt").val().replace(/-/g, ''));
		$("#currentPage").val($("#beforeCurrentPage").val());
		pageLoadAjaxListInner("searchFrm", "innerList", "/member/ajaxlistInner.do"); // 리스트 호출
}

//목록
function goList(currentPage){
	$("#currentPage").val(currentPage);
	pageLoadAjaxListInner("searchFrm", "innerList", "/member/ajaxlistInner.do"); // 리스트 호출
}

//상세
function fn_detail(seq){
	$("#seq").val(seq);
	$("#searchFrm").attr('action','/member/updateForm.do') ;
	$("#searchFrm").submit();
}

//-->
</script>
<div class="pmbox mar_b22">
<form name="searchFrm" id="searchFrm" method="post">
	
	<input type="hidden" name="seq" id="seq" />
	<input type="hidden" id="beforeCurrentPage" value="${pageInfo.currentPage}" />
	<input type="hidden" name="currentPage" id="currentPage" />
	<input type="hidden" name="delVal" id="delVal"/>
	
	<div class="fltl mar_trl20">
		시작일:<input type="text" name="stDt" id="stDt" class="w70" value="${info.stDt }"/>&nbsp;~
		종료일:<input type="text" name="enDt" id="enDt" class="w70" value="${info.enDt }"/>&nbsp;&nbsp;
		<select id="searchKey" name="searchKey" class="w128" title="검색조건선택">
			<option value="nm" <c:if test="${info.searchKey eq 'nm' }">selected</c:if>>고객명</option>
			<option value="mdn" <c:if test="${info.searchKey eq 'mdn' }">selected</c:if>>휴대폰</option>
		</select>&nbsp;
	</div>
	<input type="text" id="searchValue" name="searchValue" class="w150" value="${info.searchValue }" v:required='trim' m:required="검색어를 입력하십시오." />
	<a href="#"><img id="searchBtn" src="/resource/images/common/btn_board_search.gif" alt="검색" onclick="javascript:searchList();"/></a>
</form>
</div>
<div class="fltr mar_trl20">
	<a href="#"><img id="excelBtn" src="/resource/images/common/btn_excel.gif" alt="excel" /></a>
</div>
<div id="innerList"></div>