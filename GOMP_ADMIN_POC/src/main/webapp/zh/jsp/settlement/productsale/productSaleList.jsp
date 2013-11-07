<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
	$(document).ready(function(){
		<c:if test="${productSaleS.searchDay ne '' }">
			payDateSet("${productSaleS.searchDay}");
		</c:if>
	});
	$(function() {
        
		// datepicker localizations
		$.datepicker.setDefaults($.datepicker.regional['${ThreadSession.serviceLocale.language}']);
		// $.datepicker.setDefaults($.datepicker.regional['zh-TW']);
		$.datepicker.setDefaults({dateFormat: 'yy-mm-dd'});
		// datepicker input right img create
		$.datepicker.setDefaults({showOn:'both', buttonImage:'<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/btn_cal.gif"/>', buttonImageOnly:true});
		
		$( "#startDate" ).datepicker();
		$( "#endDate" ).datepicker();
		
		var form = document.searchForm;
		
		
		// search btn
		$("#searchBtn").click(function(event){
			event.preventDefault();
			funcSearch();
		});
		
		// excel download btn 
		$("#excelDownBtn").click(function(event){
			event.preventDefault();
			if(showValidate('forexcel', 'default', "<gm:string value=''/>",excelCount)){
				excelDown();
			}
		});
				
		// listBtn btn
		$("#listBtn").click(function(event){
			event.preventDefault();
			goList();
		});
		
		
		//검색어 작업
		<c:set var="searchType" value="${productSaleS.searchType}" />
		var searchType = $.trim("<g:string value='${searchType}'/>");
		$("#searchType > option[value=<g:string value='${searchType}'/>]").attr("selected", true);
		//검색어 작업
		
		//상품구분 작업
		<c:set var="listSize" value="0"/>
		<c:forEach var="data" items="${productSaleS.prdType}" varStatus="status">
			$(".strPrdType").each(function(){
				if ($(this).val()== "${data}"){
					$(this).attr("checked","checked");
				}
			});
		</c:forEach>
		//상품구분 작업
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
			
			var searchText;
			$("#searchText").val($.trim($("#searchText").val()));
			searchText = $("#searchText").val();
			//alert($("#searchType").val());
			if ($("#searchType").val() == "prdName" && searchText !=""){ //상품명 값 셋팅
				//alert("prdName");
				$("#prdName").val(searchText);
			}else if($("#searchType").val() == "prodId" && searchText !=""){ //상품ID 값 셋팅
				//alert("prodId");
				$("#prodId").val(searchText);
			}
			document.searchForm.action="productSaleList.omp";
			document.searchForm.submit();
		}
	}
	
	//일별 판매 현황 
 	function goList(){
 		document.searchForm.action="selectSaleStatDailyMainList.omp";
 		document.searchForm.submit();
 	}
	
	function excelDown() {
		if(showValidate('searchForm', 'default', "")){
			document.searchForm.action="productSaleExcelList.omp";
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
	
	
</script>
		<div id="contents_area">	  
			<!-- Title Area S -->
			<div id="ctitle_area"> 
				<p class="history">Home &gt; 產品販售/結算 <strong>&gt;</strong> <span>產品類別販售現狀</span></p>
				<h3><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/sl_title02.gif" alt="產品類別販售現狀" /></h3>
			</div>
			<!-- //Title Area E -->
			
			<!-- CONTENT TABLE S-->
			<div id="contents">
            	<form name="forexcel"><input type="hidden" name="excelCnt" value="${totalCount}" v:excelcnt m:excelcnt="<gm:string value='jsp.settlement.dailysale.productSaleList.nodata'/>"/><!-- 엑셀 다운로드 메세지 사용 --></form>
				<div class="tstyleD mar_b10">
					<s:form id="searchForm" name="searchForm"  theme="simple" >
					<input type="hidden" id="prodId" name="productSaleS.prodId" value="${productSaleS.prodId}"> <!-- 상품id 검색값 -->
					<input type="hidden" id="prdName" name="productSaleS.prdName" value="<g:tagAttb value="${productSaleS.prdName}"/>"> <!-- 상품명 검색값 -->
					<input type="hidden" id="no" name="productSaleS.page.no" value="${productSaleS.page.no}" />
					<input type="hidden" id="searchDay" name="productSaleS.searchDay" value="${productSaleS.searchDay }" />
					<table summary="搜尋產品">
						<caption>搜尋產品</caption>
						<colgroup>
							<col width="18%" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/sl_txt02.gif" alt="搜尋產品" /></th>
								<td class="tit02">
									<select id="searchType" name="productSaleS.searchType" class="w90">
										<option value="prdName" >產品名稱</option>
										<option value="prodId" >產品AID</option>
									</select>
									<input type="text" id="searchText" name="productSaleS.searchText" value="<g:tagAttb value="${productSaleS.searchText }"/>" class="w255"/>
								</td>
							</tr>
							<tr>
								<th scope="row"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/sl_txt03.gif" alt="交易期間" /></th>
								<td class="tit02">
									<input type="text" id="startDate" name="productSaleS.startDate" class="w82" value="${productSaleS.startDate}" title="start" />
									&nbsp;~&nbsp;
									<input type="text" id="endDate" name="productSaleS.endDate"  class="w82" value="${productSaleS.endDate}" title="end" />
									&nbsp;&nbsp; 最近
									<a href="javascript:payDateSet('today')"><img id="today" src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/btn_1day.gif" alt="1日" /></a>
									<a href="javascript:payDateSet('7day')"><img id="7day" src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/btn_7day_on.gif" alt="7日" /></a>
									<a href="javascript:payDateSet('1month')"><img id="1month" src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/btn_1mon.gif" alt="1個月" /></a>
									<a href="javascript:payDateSet('3month')"><img id="3month" src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/btn_3mon.gif" alt="3個月" /></a>
								</td>
							</tr>
							<tr>
								<th scope="row"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/sl_txt04.gif" alt="產品區分" /></th>
								<td class="tit02">
									<input type="checkbox" id="strPrdType" class="strPrdType" name="productSaleS.strPrdType" value="1"/><label for="procate01">&nbsp;付費產品</label>&nbsp;&nbsp;
									<input type="checkbox" id="strPrdType" class="strPrdType" name="productSaleS.strPrdType" value="0"/><label for="procate02">&nbsp;免費產品</label>
								</td>
							</tr>
							<tr>
								<th colspan="2" class="tit03" scope="row"><a href="#" id="searchBtn"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/btn_search.gif" alt="搜尋" /></a></th>
							</tr>
						</tbody>
					</table>
					</s:form>
				</div>
				<p class="txtr mar_b5"><a href="#" id="excelDownBtn"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/btn_excel.gif" alt="下載Excel檔" /></a></p>
				<div class="tstyleE">
					<table summary="產品類別販售現狀">
						<caption>產品類別販售現狀</caption>
						<colgroup>
							<col width="5%" />
							<col />
							<col width="9%" />
							<col width="6%" />
							<col width="8%" />
							<col width="6%" />
							<col width="8%" />
							<col width="7%" />
							<col width="7%" />
							<col width="8%" />
							<col width="6%" />
							<col width="8%" />
						</colgroup>
						<thead>
							<tr>
								<th rowspan="2" scope="col" class="btnone"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit06.gif" alt="項次" /></th>
								<th rowspan="2" scope="col" class="btnone"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit07.gif" alt="產品名稱" /></th>
								<th rowspan="2" scope="col" class="btnone"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit08.gif" alt="產品AID" /></th>
								<th rowspan="2" scope="col" class="btnone"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit09.gif" alt="產品區分" /></th>
								<th rowspan="2" scope="col" class="btnone"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit10.gif" alt="產品價格" /></th>
								<th colspan="2" scope="col" class="btnone th01"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit02.gif" alt="銷售記錄" /></th>
								<th colspan="3" scope="col" class="btnone th01"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit03.gif" alt="付款方式" /></th>
								<th colspan="2" scope="col" class="btnone brnone th01"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit04.gif" alt="取消購買之記錄" /></th>
							</tr>
							<tr>
								<th class="th02"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit04_01.gif" alt="筆數" /></th>
								<th class="th02"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit04_02.gif" alt="金額" /></th>
								<th class="th02"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit03_01.gif" alt="信用卡" /></th>
								<th class="th02"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit03_02.gif" alt="手機" /></th>
								<th class="th02"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit22.gif" alt="whoopy cash" /></th>
								<th class="th02"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit04_01.gif" alt="筆數" /></th>
								<th class="th02 brnone"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit04_02.gif" alt="金額" /></th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
							<c:when test="${totalCount eq 0 }">
							<tr>
								<td colspan="12" class="text_c">無資料。</td>
							</tr>
							</c:when>
							<c:otherwise>
							<c:forEach items="${productSaleList }" var="salestat">
							
							<tr>
								<td>${salestat.totalCount - salestat.rnum + 1}</td>
								<td class="tit07">${salestat.prdName}</td>
								<td><span class="tcor">TA${salestat.prodId}</span></td>
								<td>
									<c:choose>
									<c:when test="${salestat.prdSaleType eq '0' }">
										<span class="txtcolor03">免費</span>
									</c:when>
									<c:otherwise>
										<span class="txtcolor03">付費</span>
									</c:otherwise>
									</c:choose>
								</td>
								<td><strong><g:text value="${salestat.prodprc}" format="###,###,###,###"/></strong></td>
								<td><g:text value="${salestat.chargedDwnlCnt}" format="###,###,###,###" />筆</td>
								<td><g:text value="${salestat.saleAmt}" format="###,###,###,###" /></td>
								<td><g:text value="${salestat.cardSaleAmt}" format="R###,###,###,###" /></td>
								<td><g:text value="${salestat.phoneSaleAmt}" format="R###,###,###,###" /></td>
								<td><g:text value="${salestat.cashSaleAmt}" format="R###,###,###,###" /></td>
								<td><g:text value="${salestat.cancelDwnlCnt}" format="###,###,###,###" />筆</td>
								<td class="brnone"><g:text value="${salestat.cancelAmt}" format="###,###,###,###" /></td>
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
				       <g:pageIndex item="${productSaleList}"/>
				</div>
				<!-- //paging -->

			</div>
			<!-- //CONTENT TABLE E-->

		</div>
		<!-- //Content Area E -->