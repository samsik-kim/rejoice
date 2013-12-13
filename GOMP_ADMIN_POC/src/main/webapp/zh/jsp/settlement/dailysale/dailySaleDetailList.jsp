<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
//<![CDATA[
	$(function() {
		var form = document.searchForm;
				// excel download btn 
		$("#excelDownBtn").click(function(event){
			event.preventDefault();
			if(showValidate('forexcel', 'default', "<gm:string value=''/>",excelCount)){
				excelDown();
			}
		});
		
	});
	
	function goPage(no) {
	    $("#no").val(no);
	    document.searchForm.action="<c:url value="/settlement/dailysale/popUpDailySaleDetailList.omp" />";
		document.searchForm.submit();
	}
	
	function excelDown() {
		if(showValidate('searchForm', 'default', "")){
			document.searchForm.action="<c:url value="/settlement/dailysale/dailySaleDetailExcelList.omp" />";
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
<div id="pop_area06">

	<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pop_sl_tit01.gif' />" alt="每日販售現狀" /></h2>
	<p class="fltl pbult11">交易期間 : ${dailySaleS.startDate } ~ ${dailySaleS.endDate } <span class="txt01">(購買完畢為 <span class="txtcolor02">${dailySale.totalSaleCount}</span>筆, 取消購買為  <span class="txtcolor02">${dailySale.totalCancelCount}</span>筆)</span></p>
	<p class="txtr mar_b5"><a href="#" id="excelDownBtn"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/btn_excel.gif' />" alt="下載Excel檔" /></a></p>
	<div class="tstyleE">
		<form name="forexcel"><input type="hidden" name="excelCnt" value="${totalCount}" v:excelcnt m:excelcnt="<gm:string value='jsp.settlement.dailysale.dailySaleDetailList.nodata'/>"/><!-- 엑셀 다운로드 메세지 사용 --></form>
		<s:form id="searchForm" name="searchForm" action="selectSaleStatDailyMainList" theme="simple" >
		<input type="hidden" id="no" name="dailySaleS.page.no" value="${dailySaleS.page.no }" />
		<input type="hidden" id="startDate" name="dailySaleS.startDate" value="${dailySaleS.startDate }" />
		<input type="hidden" id="endDate" name="dailySaleS.endDate" value="${dailySaleS.endDate }" />
		<table summary="產品類別販售現狀">
			<caption>產品類別販售現狀</caption>
			<colgroup>
				<col width="4%" />
				<col width="12%" />
				<col width="19%" />
				<col width="10%" />
				<col width="8%" />
				<col width="11%" />
				<col width="9%" />
				<col width="9%" />
				<col width="10%" />
				<col />
			</colgroup>
			<thead>
				<tr>
					<th rowspan="2" scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit06.gif' />" alt="日期" /></th>
					<th rowspan="2" scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit25.gif' />" alt="交易日期" /></th>
					<th rowspan="2" scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit07.gif' />" alt="產品名稱" /></th>
					<th rowspan="2" scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit08.gif' />" alt="產品AID" /></th>
					<th rowspan="2" scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit09.gif' />" alt="產品類別" /></th>
					<th rowspan="2" scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit23.gif' />" alt="銷售金額" /></th>
					<th colspan="3" scope="col" class="btnone th01"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit03.gif' />" alt="付款方式" /></th>
					<th rowspan="2" scope="col" class="btnone brnone th01"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit24.gif' />" alt="狀態" /></th>
				</tr>
				<tr>
					<th class="th02"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit03_01.gif' />" alt="信用卡" /></th>
					<th class="th02"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit03_02.gif' />" alt="手機" /></th>
					<th class="th02"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/sl/th_tit22.gif' />" alt="whoopy cash" /></th>
				</tr>
			</thead>
			<tbody>
				<c:choose>
				<c:when test="${totalCount eq 0 }">
					<tr>
						<td colspan="9" class="text_c">無資料。</td>
					</tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${dailySaleList }" var="salestat">
				
				<tr class="txt_90">
					<td>${salestat.totalCount - salestat.rnum + 1}</td>
					<td><g:text value="${salestat.saleDt}" format="L####-##-##" /></td>
					<td class="tit07">${salestat.prdName}</td>
					<td><span class="tcor">${salestat.aid}</span></td>
					<td>
						<c:choose>
						<c:when test="${salestat.prdSaleType eq '0' }">
							<span class="tcor">免費</span>
						</c:when>
						<c:otherwise>
							<span class="txtcolor03">付費</span>
						</c:otherwise>
						</c:choose>
					</td>
					<td><strong><g:text value="${salestat.saleAmt}" format="###,###,###,###" /></strong></td>
					<td><g:text value="${salestat.cardSaleAmt}" format="R###,###,###,###" /></td>
					<td><g:text value="${salestat.phoneSaleAmt}" format="R###,###,###,###" /></td>
					<td><g:text value="${salestat.cashSaleAmt}" format="R###,###,###,###" /></td>
					<td class="brnone">
						<c:choose>
						<c:when test="${salestat.saleYN eq 'N' }">
							<span class="tcor">取消購買</span>
						</c:when>
						<c:otherwise>
							<span class="tcor">購買完畢</span>
						</c:otherwise>
						</c:choose>
					</td>	
				</tr>
				</c:forEach>
				</c:otherwise>
				</c:choose>
			</tbody>
		</table>
		</s:form>
	</div>
	<p class="txtr pad_t5 txt_90">幣別 : NT$</p>
	<!-- paging -->
	<div align="center">
	      <g:pageIndex item="${dailySaleList}"/>
	</div>
	<!-- //paging -->
</div>