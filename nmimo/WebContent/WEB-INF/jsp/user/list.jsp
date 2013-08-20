<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<script type="text/javascript">

	$(function() {
		
		//InnerList 호출
		pageLoadAjaxListInner("userListForm", "innerList", "/user/ajaxlistInner.do"); 
		
		//tablesorter 적용 
// 		$("#userListTable").tablesorter({widgets: ['zebra']});
		
	});	
	
	function goDetailPop(key){
		Common.centerPopupWindow('/user/popup/listInfoDetailPop.do?userId='+key,'window',{width:422,height:530,scrollBars:'no'});
	}
	
	//검색 조회 aJax처리  
	function goSearch(){
		pageLoadAjaxListInner("userListForm", "innerList", "/user/ajaxlistInner.do");
	}
	
	//Paging처리 aJax처리
	function goList(v){
		$("#pageIndex").val(v);
		pageLoadAjaxListInner("userListForm", "innerList", "/user/ajaxlistInner.do");
	}
</script>

<!-- Title -->
<h3 class="tit">사용자 관리</h3>

<div class="search">
	<form name="userListForm" id="userListForm" method="post" >
		<input type="hidden" id="beforeCurrentPage" value="${pageInfo.currentPage}" />
		<input type="hidden" name="currentPage" id="currentPage" />
		<fieldset style="padding:10px; text-align:center">
			<legend class="hidden">검색</legend>
			<select id="searchType" name="searchType" class="sType" style="width:100px;">
				<option value="1">사용자ID</option>
				<option value="2">사용자명</option>
			</select>
			<input type="text" id="searchStr" name="searchStr" class="iType" style="width:300px;" />
			<a href="#" onclick="javascript:goSearch();" class="btn_sml"><span>검 색</span></a>
		</fieldset>
	</form>
</div>      

<div id="innerList"></div>
