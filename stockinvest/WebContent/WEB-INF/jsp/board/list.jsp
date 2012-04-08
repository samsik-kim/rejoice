<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
<!--
$(document).ready(function(){
	$("#regBtn").click(function(){
		$("#searchFrm").attr('action','/board/insertBoardForm.do') ;
		$("#searchFrm").submit();
	});

	$("#excelBtn").click(function(){
//		$("#stDt").val($("#stDt").val().replace(/-/g, ''));
//		$("#enDt").val($("#enDt").val().replace(/-/g, ''));
		$("#searchFrm").attr('action','/board/boardListExcel.do') ;
		$("#searchFrm").submit();		
//		location.href = "/board/boardListExcel.do?stDt="+$("#stDt").val()+"&enDt="+$("#enDt").val();
	});	
	
	$("#stDt").datepicker();
	$("#enDt").datepicker();
	
	$("#delBtn").click(function(){
		var delValues = "";
		
		$("input:[name=delch]:checked").each(function (index) {   
			delValues += $(this).val() + ",";   
		});   
		
		if(confirm("삭제 하시겠습니까?")){
			$("#delVal").val(delValues);
			$("#searchFrm").attr('action','/board/boardDelete.do') ;
			$("#searchFrm").submit();
		}
	});
	
	pageLoadAjaxListInner("searchFrm", "innerList", "/board/ajaxBoardListinner.do"); // 리스트 호출	

});

//검색
function searchList(){
//		if($("#stDt").val() == "시작일") $("#stDt").val('');
//		if($("#enDt").val() == "종료일") $("#enDt").val('');
//		$("#stDt").val($("#stDt").val().replace(/-/g, ''));
//		$("#enDt").val($("#enDt").val().replace(/-/g, ''));
		$("#searchFrm").attr('action','/board/boardList.do') ;
		$("#searchFrm").submit();
}

//목록
function goList(currentPage){
	$("#searchFrm").attr('action','/board/boardList.do') ;
	$("#currentPage").val(currentPage);
	$("#searchFrm").submit();
//	location.href="/board/boardList.do?currentPage="+currentPage+"&bbsCd=";
}

//상세
function fn_detail(seqNo){
	$("#seqNo").val(seqNo);
	$("#searchFrm").attr('action','/board/boardDetail.do') ;
	$("#searchFrm").submit();
}

//-->
</script>
<div class="pmbox mar_b22">
<form name="searchFrm" id="searchFrm" method="post">
<input type="hidden" name="bbsCd" id="bbsCd" value="${info.bbsCd}">
<input type="hidden" name="seqNo" id="seqNo">
<input type="hidden" name="delVal" id="delVal"/>
<input type="hidden" name="currentPage" id="currentPage" value="${info.currentPage}">
	<div class="fltl mar_trl20">
		시작일:<input type="text" name="stDt" id="stDt" class="w70" value="${info.stDt }"/>&nbsp;~
		종료일:<input type="text" name="enDt" id="enDt" class="w70" value="${info.enDt }"/>&nbsp;&nbsp;
		<select id="searchKey" name="searchKey" class="w128" title="검색조건선택">
			<option value="codeNum" <c:if test="${info.searchKey eq 'codeNum' }">selected</c:if>>종목코드</option>
			<option value="codeName" <c:if test="${info.searchKey eq 'codeName' }">selected</c:if>>종목명</option>
			<option value="subject" <c:if test="${info.searchKey eq 'subject' }">selected</c:if>>제목</option>
			<option value="content" <c:if test="${info.searchKey eq 'content' }">selected</c:if>>내용</option>
		</select>&nbsp;
	</div>
	<input type="text" id="searchValue" name="searchValue" class="w150" value="${info.searchValue }" v:required='trim' m:required="검색어를 입력하십시오." />
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
			<a href="#"><img id="regBtn" src="/resource/images/board/btn_regist.jpg" alt="등록" /></a>
			<a href="#"><img id="delBtn" src="/resource/images/board/btn_delete.jpg" alt="삭제하기" /></a>	
		</td>
	</tr>
</table>