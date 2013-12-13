<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
//<![CDATA[
	
	$(document).ready(function(){
		<c:if test="${dailySaleS.searchDay ne '' }">
			payDateSet("${dailySaleS.searchDay}");
		</c:if>
	});
	
	$(function() {
		var form = document.searchForm;
		// datepicker localizations
		$.datepicker.setDefaults($.datepicker.regional['${ThreadSession.serviceLocale.language}']);
		// $.datepicker.setDefaults($.datepicker.regional['zh-TW']);
		$.datepicker.setDefaults({dateFormat: 'yy-mm-dd'});
		// datepicker input right img create
		$.datepicker.setDefaults({showOn:'both', buttonImage:'<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/btn_cal.gif"/>', buttonImageOnly:true});
		
		// search start ~ end date datepicker
		$( "#startDate" ).datepicker();
		$( "#endDate" ).datepicker();
		
		//setOrderSearchDateAdminPoC("7day", document.getElementById("startDate"), document.getElementById("endDate"));
		/*
		// today, weekly, monthly search btn action
		$(".searchDateBtn").click(function(event){
			event.preventDefault();
			var clickedId = this.id;

			// searchDate Condition setting
			$(".searchDate").each(function(){
				if(clickedId == $(this).attr('rel')){
					$(this).val("Y");
				}else{
					$(this).val("");
				}
			});
			
			// startDate, endDate calculate (7day, .. , ..);
			setOrderSearchDateAdminPoC($(this).attr("rel"),form.startDate, form.endDate);
			// search
			funcSearch();
		});
		*/
		
		$("#startDate, #endDate ").keydown(function(event){
			if(event.keyCode == '8') {
				event.preventDefault(); 
			} 
		});
		
		// searchDateBtn init condition (onLoad apply)
		$(".searchDate").each(function(){
			var btnId = $(this).attr("rel");
			if($(this).val() == 'Y'){
				$("#"+btnId).html("<strong>"+$("#"+btnId).text()+"</stong>");
			}
		});
		
		// search btn
		$("#searchBtn").click(function(event){
			event.preventDefault();
			funcSearch();
		});
		
		// search init btn	
		$("#searchInitBtn").click(function(event){
			event.preventDefault();
			$.clearForm("searchForm", true, false);
			$("#searchWeekBtn").click();
			funcSearch();
		});
		
		// excel download btn 
		$("#excelDownBtn").click(function(event){
			event.preventDefault();
			if(showValidate('forexcel', 'default', "<gm:string value=''/>",excelCount)){
				excelDown();
			}
		});
		
		
	});
	
	
	
	// 기간검색 1일, 7일, 1개월, 3개월 버튼 Click시 Setting
	function payDateSet(payDate) {
		setOrderSearchDateAdminPoC(payDate, document.getElementById("startDate"), document.getElementById("endDate")); 
		
		switch(payDate) {
			case "today" :
				$("#searchDay").val("today");
				
				$("#today").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1day_on.gif");
				$("#7day").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_7day.gif");
				$("#1month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1mon.gif");
				$("#3month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_3mon.gif");
				break;
			case "7day" :
				$("#searchDay").val("7day");
				
				$("#today").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1day.gif");
				$("#7day").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_7day_on.gif");
				$("#1month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1mon.gif");
				$("#3month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_3mon.gif");
				break;
			case "1month" :
				$("#searchDay").val("1month");
				
				$("#today").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1day.gif");
				$("#7day").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_7day.gif");
				$("#1month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1mon_on.gif");
				$("#3month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_3mon.gif");
				break;
			case "3month" :
				$("#searchDay").val("3month");
				
				$("#today").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1day.gif");
				$("#7day").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_7day.gif");
				$("#1month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1mon.gif");
				$("#3month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_3mon_on.gif");
				break;
			case "free" :
				$("#searchDay").val("free");
				
				$("#today").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1day.gif");
				$("#7day").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_7day.gif");
				$("#1month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_1mon.gif");
				$("#3month").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/pm/btn_3mon.gif");
				break;
		}
	}
	
	function goPage(no) {
	    $("#no").val(no);
	    $("#searchBtn").click();
	}
	
	function funcSearch(){
		if(showValidate('searchForm', 'default', "")){
			document.searchForm.action="<c:url value="/settlement/dailysale/dailySaleList.omp" />";
			document.searchForm.target="_self";
			document.searchForm.submit();
		}
	}
	
	function goDetail(startDate,endDate){
		$("#detailForm\\.startDate").val(startDate);
		$("#detailForm\\.endDate").val(endDate);
		//document.detailForm.action="<c:url value="/settlement/dailysale/popUpDailySaleDetailList.omp"/>";
		//document.detailForm.submit();
		
		var targetUrl = "<c:url value="/settlement/dailysale/popUpDailySaleDetailList.omp"/>";
		settlement_popup_center('', 660, 600, 0, 0);
		document.detailForm.target = 'popup';
		document.detailForm.action = targetUrl;
		document.detailForm.submit();
		
		
	}
	
	function excelDown() {
		if(showValidate('searchForm', 'default', "")){
			document.searchForm.action="dailySaleExcelList.omp";
			document.searchForm.target="_self";
			document.searchForm.submit();
		}
	}
	
	var excelCount = {
		"excelcnt" : function() {
			if ("${totalCount}"  == 0) {
				return false;
				//alert("<gm:string value='검색결과가 없습니다.'/>");
				//return;
			}else{
				return true;
			}       
		}
	};
	
//]]>
</script>
<!-- Content Area S -->
<h2 class="hide">본문영역</h2>
<div id="contents_area">	  
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 產品販售/結算 <strong>&gt;</strong> <span>每日販售現狀</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_title01.gif'/>"  alt="每日販售現狀" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	
	<div id="contents">
        <form name="forexcel"><input type="hidden" name="excelCnt" value="${totalCount}" v:excelcnt m:excelcnt="<gm:string value='jsp.settlement.dailysale.dailySaleList.nodata'/>"/><!-- 엑셀 다운로드 메세지 사용 --></form>  
		<div class="tstyleD mar_b10">
			<s:form id="searchForm" name="searchForm"  theme="simple" >
			<input type="hidden" id="no" name="dailySaleS.page.no" value="${dailySaleS.page.no }" />
			<input type="hidden" id="searchDay" name="dailySaleS.searchDay" value="${dailySaleS.searchDay }" />
			
			<table summary="搜尋期間">
				<caption>搜尋期間</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/sl_txt01.gif' />" alt="搜尋期間" /></th>
						<td class="tit02">
							<input type="text" id="startDate" name="dailySaleS.startDate" class="w82" value="${dailySaleS.startDate }" readonly 
							v:required='trim' m:required="<gm:tagAttb value='jsp.settlement.dailysale.dailySaleList.startday'/>" title="start" />
							<a href="#"></a>&nbsp;~&nbsp;
							<input type="text" id="endDate" name="dailySaleS.endDate" class="w82" title="end" value="${dailySaleS.endDate }" readonly 
							v:required='trim' m:required="<gm:tagAttb value='jsp.settlement.dailysale.dailySaleList.endday'/>" v:scompare="ge,@{dailySaleS.startDate}" m:scompare="<gm:tagAttb value='jsp.settlement.dailysale.dailySaleList.comparedday'/>" />
							<a href="#"></a>
							&nbsp;&nbsp; 最近
							<a href="javascript:payDateSet('today')" class="searchDateBtn" id="searchTodayBtn" rel="today" ><img id="today" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_1day.gif' />" alt="1日" /></a>
							<a href="javascript:payDateSet('7day')" class="searchDateBtn" id="searchWeekBtn" rel="7day"><img id="7day" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_7day_on.gif' />" alt="7日" /></a>
							<a href="javascript:payDateSet('1month')" class="searchDateBtn" id="searchMonthBtn" rel="1month"><img id="1month" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_1mon.gif' />" alt="1個月" /></a>
							<a href="javascript:payDateSet('3month')" class="searchDateBtn"><img id="3month" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_3mon.gif' />" alt="3個月" /></a>
						</td>
					</tr>
					<tr>
						<th colspan="2" class="tit03" scope="row"><a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_search.gif' />" id="searchBtn" alt="搜尋" /></a></th>
					</tr>
				</tbody>
			</table>
			</s:form>
		</div>
		<p class="txtr mar_b5"><a href="#" id="excelDownBtn"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_excel.gif' />" alt="下載Excel檔" /></a></p>
		<div class="tstyleE">
			<table summary="每日販售現狀">
				<caption>每日販售現狀</caption>
				<colgroup>
					<col width="21%" />
					<col width="8%" />
					<col width="8%" />
					<col width="10%" />
					<col width="9%" />
					<col width="9%" />
					<col width="10%" />
					<col width="6%" />
					<col width="9%" />
					<col />
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2" scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit01.gif' />" alt="日期" /></th>
						<th colspan="3" scope="col" class="btnone th01"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit02.gif' />" alt="銷售記錄" /></th>
						<th colspan="3" scope="col" class="btnone th01"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit03.gif' />" alt="付款方式" /></th>
						<th colspan="2" scope="col" class="btnone th01"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit04.gif' />" alt="取消購買之記錄" /></th>
						<th scope="col" class="btnone th01 brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit05.gif' />" alt="總計" /></th>
					</tr>
					<tr>
						<th class="th02"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit02_01.gif' />" alt="免費(筆數)" /></th>
						<th class="th02"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit02_02.gif' />" alt="付費(筆數)" /></th>
						<th class="th02"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit02_03.gif' />" alt="銷售金額" /></th>
						<th class="th02"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit03_01.gif' />" alt="信用卡" /></th>
						<th class="th02"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit03_02.gif' />" alt="手機" /></th>
						<th class="th02"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit03_03.gif' />" alt="whoopy cash" /></th>
						<th class="th02"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit04_01.gif' />" alt="筆數" /></th>
						<th class="th02"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit04_02.gif' />" alt="金額" /></th>
						<th class="th02 brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit05_01.gif' />" alt="金額" /></th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${totalCount eq 0 }">
							<tr>
								<td colspan="10" class="text_c">無資料。</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach items="${dailySaleList }" var="salestat" varStatus="status">
								<c:if test="${status.first && dailySaleS.page.no eq 1 }">
								<tr class="tr01">
									<td><span class="link01"><a href="JavaScript:goDetail('${dailySaleS.startDate}','${dailySaleS.endDate}')">${dailySaleS.startDate} ~ ${dailySaleS.endDate}</a></span></td>
									<td><g:text value="${salestat.tFreeDwnlCnt}" format="#,###"/>筆</td>
									<td><g:text value="${salestat.tChargedDwnlCnt}" format="#,###"/>筆</td>
									<td><g:text value="${salestat.tSaleAmt}" format="#,###,###,###,###,###,###,###"/></td>
									<td><g:text value="${salestat.tCardSaleAmt}" format="R###,###,###,###" /></td>
									<td><g:text value="${salestat.tPhoneSaleAmt}" format="R###,###,###,###" /></td>
									<td><g:text value="${salestat.tCashSaleAmt}" format="R###,###,###,###" /></td>
									<td><g:text value="${salestat.tCancelDwnlCnt}" format="#,###"/>筆</td>
									<td><g:text value="${salestat.tSaleCancelAmt}" format="#,###,###,###,###,###,###,###"/></td>
									<td class="brnone"><g:text value="${salestat.tSubtotalAmt}" format="#,###,###,###,###,###,###,###"/></td>
								</tr>
								</c:if>
								<tr>
									<td><span class="link01"><a href="JavaScript:goDetail('${salestat.saleDt}','${salestat.saleDt}')">${salestat.saleDt}</a></span></td>
									<td><g:text value="${salestat.freeDwnlCnt}" format="#,###"/>筆</td>
									<td><g:text value="${salestat.chargedDwnlCnt}" format="#,###"/>筆</td>
									<td><g:text value="${salestat.saleAmt}" format="#,###,###,###,###,###,###,###"/></td>
									<td><g:text value="${salestat.cardSaleAmt}" format="R###,###,###,###" /></td>
									<td><g:text value="${salestat.phoneSaleAmt}" format="R###,###,###,###" /></td>
									<td><g:text value="${salestat.cashSaleAmt}" format="R###,###,###,###" /></td>
									<td><g:text value="${salestat.cancelDwnlCnt}" format="#,###"/>筆</td>
									<td><g:text value="${salestat.saleCancelAmt}" format="#,###,###,###,###,###,###,###"/></td>
									<td class="brnone"><g:text value="${salestat.subtotalAmt}" format="#,###,###,###,###,###,###,###"/></td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
		<p class="txtr pad_t5 txt_90">幣別 : NT$</p>
		
		<!-- paging -->
		<div align="center">
		       <g:pageIndex item="${dailySaleList}"/>
		</div>
		<!-- //paging -->

	</div>
	<!-- //CONTENT TABLE E-->
</div>
<s:form id="detailForm" name="detailForm" theme="simple" >
<input type="hidden" id="detailForm.startDate" name="dailySaleS.startDate">
<input type="hidden" id="detailForm.endDate" name="dailySaleS.endDate">
</s:form>
<!-- //Content Area E -->