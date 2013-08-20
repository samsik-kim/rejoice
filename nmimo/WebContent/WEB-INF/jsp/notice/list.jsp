<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<script type="text/javascript">

	$(function(){

		//InnerList 호출
		pageLoadAjaxListInner("noticeListForm", "innerList", "/notice/ajaxlistInner.do"); 
			
		//tablesorter 적용 
// 		$("#noticeListTable").tablesorter({widgets: ['zebra']});
		
	});	
	
	//검색조건 조회 aJax처리  
	function goSearch(){
		pageLoadAjaxListInner("noticeListForm", "innerList", "/notice/ajaxlistInner.do");
	}
	
	//Paging처리 aJax처리
	function goList(v){
		$("#pageIndex").val(v);
		pageLoadAjaxListInner("noticeListForm", "innerList", "/notice/ajaxListInner.do");
	}
	
	function goDetail(key){
		$("#ntfSeq").val(key);
		document.noticeListForm.action = "/notice/detail.do";
		document.noticeListForm.submit();
	}
</script>

<!-- Title -->
<h3 class="tit">공지사항</h3>

<div class="search">
	<form name="noticeListForm" id="noticeListForm" method="post" >
		<input type="hidden" name="ntfSeq" id="ntfSeq" />
		<input type="hidden" id="beforeCurrentPage" value="${pageInfo.currentPage}" />
		<input type="hidden" name="currentPage" id="currentPage" />
		<fieldset style="padding:10px; text-align:center">
			<legend class="hidden">검색</legend>
			<select id="searchType" name="searchType" class="sType" style="width:100px;">
				<option value="1">제목</option>
				<option value="2">내용</option>
			</select>
			<input type="text" id="searchStr" name="searchStr" class="iType" style="width:300px;" />
			<a href="#" onclick="javascript:goSearch();" class="btn_sml"><span>검 색</span></a>
		</fieldset>
	</form>
</div>      

<div id="innerList"></div>