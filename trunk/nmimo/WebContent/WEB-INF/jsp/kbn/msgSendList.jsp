<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<script type="text/javascript">

	$(function() {
		
		//InnerList 호출
		pageLoadAjaxListInner("msgSendListForm", "innerList", "/kbn/ajaxMsgSendListInner.do"); 
		
		//tablesorter 적용 
// 		$("#msgSendListTable").tablesorter({widgets: ['zebra']});

	});	
	
	//검색조건 조회 aJax처리  
	function goSearch(){
		pageLoadAjaxListInner("msgSendListForm", "innerList", "/kbn/ajaxMsgSendListInner.do");
	}
	
	//Paging처리 aJax처리
	function goList(v){
		$("#pageIndex").val(v);
		pageLoadAjaxListInner("msgSendListForm", "innerList", "/kbn/ajaxMsgSendListInner.do");
	}
</script>

		
<!-- Title -->
<h3 class="tit">전송정보</h3>

<!-- Help -->
<div class="help">
	검토 및 발송승인시 반려된 내역입니다.<br /> 
	반려단계 및 사유 확인하여 수정후 재승인 요청 및 삭제가 가능합니다.
	<span><a href="#" class="btn_help"><strong>화면도움말</strong></a></span>
</div>

<!-- Search Area -->
<div class="search">
	<form id="msgSendListForm" name="msgSendListForm" method="post">
		<input type="hidden" id="beforeCurrentPage" value="${pageInfo.currentPage}" />
		<input type="hidden" name="currentPage" id="currentPage" />
		<fieldset style="padding:10px; text-align:center">
			<legend class="hidden">검색</legend>
			<select id="searchType" name="searchType" class="sType" style="width:100px;">
				<option value="1">제목</option>
				<option value="2">서비스구분</option>
			</select>
			<input type="text" id="searchStr" name="searchStr" class="iType" style="width:300px;" />
			<a href="#" onclick="javascript:goSearch();" class="btn_sml"><span>검 색</span></a>
		</fieldset>
	</form>
</div>

<div id="innerList"></div>
