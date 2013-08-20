<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<script type="text/javascript">

	$(function() {
		
		//InnerList 호출
		pageLoadAjaxListInner("myWorkStandbyListForm", "innerList", "/myWork/ajaxStandbyListInner.do");
		
		//tablesorter 적용 
		//$("#myWorkStandbyListTable").tablesorter({widgets: ['zebra']});

	});	
	
	//검색조건 조회 aJax처리  
	function goSearch(){
		pageLoadAjaxListInner("myWorkStandbyListForm", "innerList", "/myWork/ajaxStandbyListInner.do");
	}
	
	//Paging처리 aJax처리
	function goList(v){
		$("#pageIndex").val(v);
		pageLoadAjaxListInner("myWorkStandbyListForm", "innerList", "/myPage/ajaxMyWorkListInner.do");
	}
	
</script>


<!-- Title -->
<h3 class="tit">대 기</h3>
<!-- Tab -->
<!-- <h3 class="tab"><img src="/resource/images/ask_tab2_01focus_01.png" alt="대기" /><a href="#"><img src="/resource/images/ask_tab2_01b_focus_03.png" alt="캠페인 요청실패" /></a></h3> -->

<!-- Help -->
<div class="help">
	승인요청후, 검토 및 발송승인 완료전 내역 입니다.<br />
	요청대기 상태의 메시지는 수정 및 삭제가 가능합니다.
	<span><a href="#" class="btn_help"><strong>화면도움말</strong></a></span>
</div>

<!-- Search Area -->
<div class="search">
	<form name="myWorkStandbyListForm" id="myWorkStandbyListForm" method="post">
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
