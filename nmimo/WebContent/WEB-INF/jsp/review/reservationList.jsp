<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<script type="text/javascript">

	$(function() {
		
		//InnerList 호출
		pageLoadAjaxListInner("reviewReservationListForm", "innerList", "/review/ajaxReservationListInner.do"); 
		
		//tablesorter 적용 
// 		$("#reviewReservationListTable").tablesorter({widgets: ['zebra']});

	});	
	
	//검색조건 조회 aJax처리  
	function goSearch(){
		pageLoadAjaxListInner("reviewReservationListForm", "innerList", "/review/ajaxReservationListInner.do");
	}
	
	//Paging처리 aJax처리
	function goList(v){
		$("#pageIndex").val(v);
		pageLoadAjaxListInner("reviewReservationListForm", "innerList", "/review/ajaxReservationListInner.do");
	}
	
</script>
		
<!-- Title -->
<h3 class="tit">사전예약</h3>

<!-- Help -->
<div class="help">
	사전예약 화면으로 사전예약후 사전예약승인번호를 발급 받는 화면입니다.<br /> 
	사전예약승인번호가 있어야 발송등록 요청이 가능합니다.<br /> 
	승인된 예약번호를 클릭하면 예약번호가 복사 됩니다. 
	<span><a href="#" class="btn_help"><strong>화면도움말</strong></a></span>
</div>

<!-- Search Area -->
<div class="search">
	<form id="reviewReservationListForm" name="reviewReservationListForm" method="post">
		<input type="hidden" id="beforeCurrentPage" value="${pageInfo.currentPage}" />
		<input type="hidden" name="currentPage" id="currentPage" />	
		<fieldset style="padding:10px; text-align:center">
			<legend class="hidden">검색</legend>
			<select id="searchType" name="searchType" class="sType" style="width:100px;">
				<option value="1">작업명</option>
				<option value="2">신청자</option>
			</select>
			<input type="text" id="searchStr" name="searchStr" class="iType" style="width:300px;" />
			<a href="#" onclick="javascript:goSearch();" class="btn_red"><span>검 색</span></a>
			<a href="#" onclick="javascript:doPostUrl('/review/reservationRegisterForm.do');"  class="btn_red"><strong>사전예약 기간 설정</strong></a>
		</fieldset>
	</form>
</div>

<div id="innerList"></div>