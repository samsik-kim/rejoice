<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<script type="text/javascript">

	$(function() {
		
		//InnerList 호출
		pageLoadAjaxListInner("etcListForm", "innerList", "/user/ajaxEtcListInner.do"); 
		
		//tablesorter 적용 
// 		$("#etcListTable").tablesorter({widgets: ['zebra']});
		
	
		//날짜검색 Calendar 이벤트
// 		var dates = $( "#searchDayS, #searchDayE" ).datepicker({
			
// 			buttonImage : "/resource/images/icon_calendar.gif",
//  			maxDate: "+0D",
// 			onSelect: function( selectedDate ) {
// 				var option = this.id == "searchDayS" ? "minDate" : "maxDate",
// 				  instance = $( this ).data( "datepicker" ),
// 				  date = $.datepicker.parseDate(
// 				    instance.settings.dateFormat ||
// 				    $.datepicker._defaults.dateFormat,
// 				    selectedDate, instance.settings );
// 				dates.not( this ).datepicker( "option", option, date );
// 			}
// 		});
		
//  		//전송정보 상세팝업
//  		$("#listInfoDetailPop").click(function(){
// 			window.open('/user/popup/listInfoDetailPop.do','','width=720,height=350,scrollbars=no,toolbar=no');		
// 		});
		
// 		//검색날짜 비교(월별 선택시 SelectBox )
// 		//hidden값 세팅
// 		$("#searchDateE").val($("#searchYearS").val()+$("#searchMonthS").val());
// 		$("#searchDateS").val($("#searchYearE").val()+$("#searchMonthE").val());


	});	
	
	//검색조건 조회 aJax처리  
	function goSearch(){
		
		//검색기간 validation체크
// 		if($("input:radio[name=searchDay]:checked").val()=="M"){
// 			if(showValidate('etcListForm', 'alert', '검색조건 확인.')){
// 				return;
// 			}	
// 			return;
// 		}
		
// 		//검색키워드 validation체크
// 		if($("select[name=searchCode]").val() !="0"){	
// 			if(showValidate('etcListForm', 'alert', '검색조건 확인.')){
// 				return;
// 			}	
// 			return;
// 		}

		pageLoadAjaxListInner("etcListForm", "innerList", "/user/ajaxEtcListInner.do");
	}
	function goDetailPop(key){
		Common.centerPopupWindow('/user/popup/listInfoDetailPop.do?userId='+key,'window',{width:422,height:530,scrollBars:'no'});
	}	
	//Paging처리 aJax처리
	function goList(v){
		$("#pageIndex").val(v);
		pageLoadAjaxListInner("etcListForm", "innerList", "/user/ajaxEtcListInner.do");
	}
</script>

<!-- Title -->
<h3 class="tit">기타사용자 관리</h3>

<div class="search">
	<form name="etcListForm" id="etcListForm" method="post" >
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
