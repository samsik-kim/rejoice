<%@page import="com.nmimo.common.util.DateUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%
	//검색날짜 server시간 세팅
	String searchDayS = DateUtils.getToday("yyyy-MM-dd");
	String searchDayE = DateUtils.getToday("yyyy-MM-dd");
	String searchYear = DateUtils.getToday("yyyy");
%>

<script type="text/javascript">

	$(function() {
		
		//InnerList 호출
		pageLoadAjaxListInner("statsListForm", "innerList", "/stats/ajaxlistInner.do"); 
		
		//tablesorter 적용 
		$("#statsListTable").tablesorter({widgets: ['zebra']});
				
		//일별 검색 Default
		if($("#searchDayS").val() == "" ){
			$("#searchDayS").val('<%=searchDayS%>');	
		}
		if($("#searchDayE").val() == "" ){
			$("#searchDayE").val('<%=searchDayE%>');	
		}
		
		//날짜검색 SelectBox 년도
		var year="<%=searchYear%>";
		$("#searchYearS").append("<option value='"+eval(Number(year-2))+"'>"+eval(Number(year-2))+"</option>");
		$("#searchYearS").append("<option value='"+eval(Number(year-1))+"'>"+eval(Number(year-1))+"</option>");
		$("#searchYearS").append("<option value='"+year+"'>"+year+"</option>");
		$("#searchYearE").append("<option value='"+eval(Number(year-2))+"'>"+eval(Number(year-2))+"</option>");
		$("#searchYearE").append("<option value='"+eval(Number(year-1))+"'>"+eval(Number(year-1))+"</option>");
		$("#searchYearE").append("<option value='"+year+"'>"+year+"</option>");

		//날짜검색 SelectBox 월
		for(var i=1; i<13; i++){
			var month=i;
			var zero='0';
			if(i<10){
				month=zero+i;	
			}
			$("#searchMonthS").append("<option value='"+month+"'>"+month+"</option>");
			$("#searchMonthE").append("<option value='"+month+"'>"+month+"</option>");
		}
		
		//메세지특성 조건
		$("#jobKind1").append("<option value='0'>전체</option>");
		$("#jobKind1").append("<option value='1'>공지/홍보</option>");
		$("#jobKind1").append("<option value='2'>자동</option>");
		$("#jobKind1").append("<option value='3'>CREAM</option>");
		$("#jobKind2").hide();
		
		//서비스유형 조건
		$("#jobKind1").change(function(){
			var val = $("#jobKind1").val();
			
			$("#jobKind2").find("option").each(function(){
 				$(this).remove();
 			});

			if(val=='1'){
				$("#jobKind2").show();
				$("#jobKind2").append("<option value='0'>전체</option>");
				$("#jobKind2").append("<option value='1'>공지</option>");
				$("#jobKind2").append("<option value='2'>상품안내/홍보</option>");
			} else if(val=='2'){
				$("#jobKind2").show();
				$("#jobKind2").append("<option value='0'>전체</option>");
				$("#jobKind2").append("<option value='1'>사용량통보</option>");
				$("#jobKind2").append("<option value='2'>상배변경:요금</option>");
				$("#jobKind2").append("<option value='2'>상배변경:부가서비스</option>");
				$("#jobKind2").append("<option value='2'>상품안내</option>");
				$("#jobKind2").append("<option value='2'>상배변경:기타</option>");
			} else {
				$("#jobKind2").hide();
			}
		});

		//날짜검색 Calendar 이벤트
		var dates = $( "#searchDayS, #searchDayE" ).datepicker({
			
			buttonImage : "/resource/images/icon_calendar.gif",
 			maxDate: "+0D",
			onSelect: function( selectedDate ) {
				var option = this.id == "searchDayS" ? "minDate" : "maxDate",
				  instance = $( this ).data( "datepicker" ),
				  date = $.datepicker.parseDate(
				    instance.settings.dateFormat ||
				    $.datepicker._defaults.dateFormat,
				    selectedDate, instance.settings );
				dates.not( this ).datepicker( "option", option, date );
			}
		});
		
 		//날짜검색 조건 이벤트
		$("input:radio[name=searchDay]").change(function(){
			var val = $("input:radio[name=searchDay]:checked").val();
			
			if('D'==val){
				document.all.div_searchDay.style.display = "inline";
				document.all.div_searchMonth.style.display = "none";
			} else {
				document.all.div_searchDay.style.display = "none";
				document.all.div_searchMonth.style.display = "inline";
			}
		});
 		
		//ExcelDown 버튼 이벤트(팝업창)
 		$("#excelSavePop").click(function(){
 			Common.centerPopupWindow('/stats/popup/excelSavePop.do','window',{width:405,height:160,scrollbars:'no'});
		});
		
		//ExcelDown 버튼 이벤트(팝업창)
 		$("#excelSavePop2").click(function(){
 			Common.centerPopupWindow('/stats/popup/excelSavePop.do','window',{width:405,height:160,scrollbars:'no'});		});

		//전송정보 상세팝업
 		$("#listInfoDetailPop").click(function(){
 			Common.centerPopupWindow('/stats/popup/listInfoDetailPop.do','window',{width:720,height:350,scrollbars:'no'});			
		});
		
		//검색날짜 비교(월별 선택시 SelectBox )
		//hidden값 세팅
		$("#searchDateE").val($("#searchYearS").val()+$("#searchMonthS").val());
		$("#searchDateS").val($("#searchYearE").val()+$("#searchMonthE").val());

		//from 검색기간 변경시 hidden값 세팅
		$(" #searchYearS, #searchMonthS ").change(function(){
 			$("#searchDateS").val($("#searchYearS").val()+$("#searchMonthS").val());
		});
 		
		//to 검색기간 변경시 hidden값 세팅
		$(" #searchYearE, #searchMonthE ").change(function(){
 			$("#searchDateE").val($("#searchYearE").val()+$("#searchMonthE").val());
		});

	});	
	
	//검색조건 조회 aJax처리  
	function goSearch(){
		
		//검색키워드 validation체크
		if($("select[name=searchCode]").val() !="0"){	
			if(showValidate('statsListForm', 'dialog', '검색조건 확인.')){
				return;
			}	
			return;
		}
		pageLoadAjaxListInner("statsListForm", "innerList", "/stats/ajaxlistInner.do");
	}
	
	//Paging처리 aJax처리
	function goList(v){
		$("#pageIndex").val(v);
		pageLoadAjaxListInner("statsListForm", "innerList", "/stats/ajaxlistInner.do");
	}
</script>

<!-- Title -->
<h3 class="tit">통 계</h3>

<!-- Table Area -->
<form id="statsListForm" name="statsListForm" method="post">
	<table summary="리스트" class="WriteTable">
		<caption>리스트</caption>
		<tbody>
			<tr>
				<th scope="row" class="l">기 간</th>
				<td class="l">
                      <form>
                          <fieldset>
                              <input name="" type="radio" value="" /><label for="">일별</label>
                              <input name="" type="radio" value="" style="margin-left:10px"/><label for="">월별</label>
                              <select name="select" class="sType" id="select" style="margin-left:15px">
                                <option value="">0000</option>
                                <option value=""></option>
                              </select> 년
                              <select name="select" class="sType" id="select">
                                <option value="">00</option>
                                <option value=""></option>
                              </select> 월
                              <select name="select" class="sType" id="select">
                                <option value="">00</option>
                                <option value=""></option>
                              </select> 일
                              ~
                              <select name="select" class="sType" id="select">
                                <option value="">0000</option>
                                <option value=""></option>
                              </select> 년
                              <select name="select" class="sType" id="select">
                                <option value="">00</option>
                                <option value=""></option>
                              </select> 월
                              <select name="select" class="sType" id="select">
                                <option value="">00</option>
                                <option value=""></option>
                              </select> 일
                          </fieldset>
                      </form>
                      </td>
			</tr>
			<tr>
				<th scope="row" class="l">메시지특성</th>
				<td class="l">
                      <form>
                          <fieldset>
                              <select name="select" class="sType" id="select" style="width:120px;">
                                <option value="">선택</option>
                                <option value=""></option>
                              </select>
                          </fieldset>
                      </form>
                      </td>
			</tr>
			<tr>
				<th scope="row" class="l">서비스유형</th>
				<td class="l">
                      <form>
                          <fieldset>
                              <input name="" type="radio" value="" /><label for="">전체</label>
                              <input name="" type="radio" value="" style="margin-left:10px"/><label for="">SMS</label>
                              <input name="" type="radio" value="" style="margin-left:10px"/><label for="">MMS</label>
                          </fieldset>
                      </form>
                     </td>
			</tr>
			<tr>
				<th scope="row" class="l">검색</th>
				<td class="l">
                      <form>
                          <fieldset>
                              <select name="select" class="sType" id="select" style="width:120px;">
                                <option value="">선택</option>
                                <option value=""></option>
                              </select>
                              <input name="" type="text" id="" style="width:400px" />
                              <a href="#" class="btn_sml"><span>결과보기</span></a>
                          </fieldset>
                      </form>
                      </td>
			</tr>
		</tbody>
	</table>
</form>

<!-- Btn -->
<p class="btnR" style="padding:10px 0">
	<a href="#" class="btn_red" onclick="javascript:Common.centerPopupWindow('/stats/popup/excelSave.do','window',{width:420,height:195,scrollBars:'no'});"><strong>엑셀저장</strong></a>
</p> 

<div id="innerList"></div>

<!-- Btn -->
<p class="btnR" style="padding:10px 0">
	<a href="#" class="btn_red" onclick="javascript:Common.centerPopupWindow('/stats/popup/excelSavePop.do','window',{width:420,height:195,scrollBars:'no'});" ><strong>엑셀저장(팝업)</strong></a>
</p> 