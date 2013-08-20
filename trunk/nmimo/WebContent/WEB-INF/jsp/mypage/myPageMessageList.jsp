<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<script type="text/javascript">

	$(function() {
		
		//InnerList 호출
		pageLoadAjaxListInner("myPageMessageListForm", "innerList", "/myPage/ajaxMessageListInner.do"); 
		
		//tablesorter 적용 
		$("#myPageMessageListTable").tablesorter({widgets: ['zebra']});

	});	
	
	//검색조건 조회 aJax처리  
	function goSearch(){
		
		//검색키워드 validation체크
		if($("select[name=searchCode]").val() !="0"){	
			if(showValidate('myPageMessageListForm', 'alert', '검색조건 확인.')){
				return;
			}	
			return;
		}
		pageLoadAjaxListInner("myPageMessageListForm", "innerList", "/myPage/ajaxMessageListInner.do");
	}
	
	//Paging처리 aJax처리
	function goList(v){
		$("#pageIndex").val(v);
		pageLoadAjaxListInner("myPageMessageListForm", "innerList", "/myPage/ajaxMessageListInner.do");
	}
</script>

		
<!-- Title -->
<h3 class="tit">나의 보관함</h3>

<!-- Help -->
<div class="help">
	임시저장한 메시지 확인이 가능하며, 해당 내용 선택시 발송등록 화면으로 이동합니다.	<span><a href="#" class="btn_help"><strong>화면도움말</strong></a></span>
</div>

<!-- Search Area -->
<div class="search">
	<form id="myPageMessageListForm" name="myPageMessageListForm" method="post">
		<fieldset style="padding:10px; text-align:center">
			<legend class="hidden">검색</legend>
			<select id="" class="sType" style="width:100px;">
				<option value="">전체</option>
				<option value=""></option>
			</select>
			<input type="text" id="" class="iType" style="width:300px;" />
			<a href="#" class="btn_sml"><span>검 색</span></a>
		</fieldset>
	</form>
</div>

<div id="innerList"></div>
