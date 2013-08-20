<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<script type="text/javascript">

	$(function() {
		
		//InnerList 호출
		pageLoadAjaxListInner("ecammsListForm", "innerList", "/review/ajaxEcammsListInner.do"); 
		
		//tablesorter 적용 
		$("#reviewEcammsListTable").tablesorter({widgets: ['zebra']});

	});	
	
	//검색조건 조회 aJax처리  
	function goSearch(){
		
		//검색키워드 validation체크
		if($("select[name=searchCode]").val() !="0"){	
			if(showValidate('ecammsListForm', 'alert', '검색조건 확인.')){
				return;
			}	
			return;
		}
		pageLoadAjaxListInner("ecammsListForm", "innerList", "/review/ajaxEcammsListInner.do");
	}
	
	//Paging처리 aJax처리
	function goList(v){
		$("#pageIndex").val(v);
		pageLoadAjaxListInner("ecammsListForm", "innerList", "/review/ajaxEcammsListInner.do");
	}
</script>

		
<!-- Title -->
<h3 class="tit">캠페인 리스트</h3>

<!-- Help -->
<div class="help">
	ECAMMS에서 발송 요청된 메시지를 열람하는 화면입니다.<span><a href="#" class="btn_help"><strong>화면도움말</strong></a></span>
</div>

<!-- Search Area -->
<div class="search">
	<form id="ecammsListForm" name="ecammsListForm" method="post">
		<fieldset style="padding:10px; text-align:center">
			<legend class="hidden">검색</legend>
				<select id=yearSelect style='font-family:굴림;font-size:12px;color:black' >
				<script type=text/javascript>
					for (var i = 2012, selectStr = ""; i <= 2013; i++)
					selectStr += "<option value='" + i + "'>" + i + " </option>";
					selectStr += "</select>";
					document.write(selectStr);
				</script>
				년
				</select>
				
				<select id=monthSelect style='font-family:굴림;font-size:12px;color:black'>
				<script type=text/javascript>
					for (var i = 1, selectStr = ""; i <= 12; i++)
					selectStr += "<option value='" + i + "'>" + i + " </option>";
					selectStr += "</select>";
					document.write(selectStr);
				</script>
				월
				</select>	
				
				<select id=daySelect style='font-family:굴림;font-size:12px;color:black'>
				<script type=text/javascript>
					for (var i = 1, selectStr = ""; i <= 31; i++)
					selectStr += "<option value='" + i + "'>" + i + " </option>";
					selectStr += "</select>";
					document.write(selectStr);
				</script>
				일
				</select>
				
			<a href="#" class="btn_red"><span>검 색</span></a>
		</fieldset>
	</form>
</div>

<div id="innerList"></div>
