<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<script type="text/javascript">

	$(function() {
		
		//InnerList 호출
		pageLoadAjaxListInner("myWorkCompleteListForm", "innerList", "/myWork/ajaxCompleteListInner.do"); 
		
		//tablesorter 적용 
// 		$("#myWorkCompleteListTable").tablesorter({widgets: ['zebra']});

	});	
	
	//검색조건 조회 aJax처리  
	function goSearch(){
		pageLoadAjaxListInner("myWorkCompleteListForm", "innerList", "/myPage/ajaxCompleteListInner.do");
	}
	
	//Paging처리 aJax처리
	function goList(v){
		$("#pageIndex").val(v);
		pageLoadAjaxListInner("myWorkCompleteListForm", "innerList", "/myPage/ajaxCompleteListInner.do");
	}

</script>

		
<!-- Title -->
<h3 class="tit">완 료</h3>

<!-- Help -->
<div class="help">
	검토 및 발송승인 완료되어 발송대기 상태의 내역입니다.<br />
	발송일시 및 내용이 변경 될 수 있으니 최종 내역을 확인하시기 바랍니다.
	<span><a href="#" class="btn_help"><strong>화면도움말</strong></a></span>
</div>

<!-- Search Area -->
<div class="search">
	<form id="myWorkCompleteListForm" name="myWorkCompleteListForm" method="post">
		<input type="hidden" id="beforeCurrentPage" value="${pageInfo.currentPage}" />
		<input type="hidden" name="currentPage" id="currentPage" />	
		<fieldset style="padding:10px; text-align:center">
			<legend class="hidden">검색</legend>
			<select id="searchType" name="searchType" class="sType" style="width:100px;">
				<option value="">전체</option>
				<option value=""></option>
			</select>
			<input type="text" id="searchStr" name="searchStr" class="iType" style="width:300px;" />
			<a href="#" class="btn_sml"><span>검 색</span></a>
		</fieldset>
	</form>
</div>

<div id="innerList"></div>
