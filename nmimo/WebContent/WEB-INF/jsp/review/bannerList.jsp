<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<script type="text/javascript">

	$(function() {
		
		//InnerList 호출
		pageLoadAjaxListInner("bannerListForm", "innerList", "/review/ajaxBannerListInner.do"); 
		
		//tablesorter 적용 
		//$("#reviewBannerListTable").tablesorter({widgets: ['zebra']});

	});	
	
	//Paging처리 aJax처리
	function goList(v){
		$("#pageIndex").val(v);
		pageLoadAjaxListInner("bannerListForm", "innerList", "/review/ajaxBannerListInner.do");
	}
	
</script>


<!-- Title -->
<h3 class="tit">배너 관리</h3>

<!-- Search Area -->
<div class="search">
	<form id="bannerListForm" name="bannerListForm"  method="post">
		<fieldset style="padding:10px; text-align:left">
			<a href="#" onclick="javascript:doPostUrl('/review/bannerRegForm.do');" class="btn_red"><strong>작 성</strong></a>
		</fieldset>
	</form>
</div>

<div id="innerList"></div>
