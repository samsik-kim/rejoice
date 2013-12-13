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

		<c:if test="${productSaleS.searchDay ne '' }">
			payDateSet("${productSaleS.searchDay}");
		</c:if>
	});
	$(function() {
		// datepicker localizations
		$.datepicker.setDefaults($.datepicker.regional['ko']);
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
		<c:set var="searchType" value="${productSaleS.searchType}" />;
		<c:set var="searchText" value="${productSaleS.searchText}" />;
		var searchType = $.trim("<c:out value='${searchType}'/>");
		var searchText = $.trim("<c:out value='${searchText}'/>");
		$("#searchType > option[value=<c:out value='${searchType}'/>]").attr("selected", true);
		$("#searchText").val(searchText);
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
		if(showValidate('searchForm', 'default', "<gm:string value='검색조건을 올바르게 넣어주세요.'/>")){
			
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
		if(showValidate('searchForm', 'default', "<gm:string value='검색조건을 올바르게 넣어주세요.'/>")){
			document.searchForm.action="productSaleExcelList.omp";
			document.searchForm.submit();
		}
	}
	
	var excelCount = {
		"excelCnt" : function() {
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
		<div id="contents_area">	  
			<!-- Title Area S -->
			<div id="ctitle_area"> 
				<p class="history">Home &gt; 판매/정산 현황 <strong>&gt;</strong> <span>상품별 판매현황</span></p>
				<h3><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/sl_title02.gif" alt="상품별 판매현황" /></h3>
			</div>
			<!-- //Title Area E -->
			
			<!-- CONTENT TABLE S-->
			<div id="contents">
            	<form name="forexcel"><input type="hidden" name="excelCnt" value="${totalCount}" v:excelCnt m:excelCnt="<gm:string value='검색결과가 없습니다.'/>"/><!-- 엑셀 다운로드 메세지 사용 --></form>
				<div class="tstyleD mar_b10">
					<s:form id="searchForm" name="searchForm"  theme="simple" >
					<input type="hidden" id="prodId" name="productSaleS.prodId" value="${productSaleS.prodId}"> <!-- 상품id 검색값 -->
					<input type="hidden" id="prdName" name="productSaleS.prdName" value="${productSaleS.prdName}"> <!-- 상품명 검색값 -->
					<input type="hidden" id="no" name="productSaleS.page.no" value="${productSaleS.page.no}" />
					<input type="hidden" id="searchDay" name="productSaleS.searchDay" value="${productSaleS.searchDay }" />
					<table summary="기간검색">
						<caption>기간검색</caption>
						<colgroup>
							<col width="18%" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/sl_txt02.gif" alt="상품검색" /></th>
								<td class="tit02">
									<select id="searchType" name="productSaleS.searchType" class="w90">
										<option value="prdName" >상품명</option>
										<option value="prodId" >상품AID</option>
									</select>
									<input type="text" id="searchText" name="productSaleS.searchText" value="${productSaleS.searchText }" class="w255" />
								</td>
							</tr>
							<tr>
								<th scope="row"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/sl_txt03.gif" alt="판매일자" /></th>
								<td class="tit02">
									<input type="text" id="startDate" name="productSaleS.startDate" class="w82" value="${productSaleS.startDate}" title="시작일 입력" />
									&nbsp;~&nbsp;
									<input type="text" id="endDate" name="productSaleS.endDate"  class="w82" value="${productSaleS.endDate}" title="종료일 입력" />
									&nbsp;&nbsp; 최근
									<a href="javascript:payDateSet('today')"><img id="today" src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/btn_1day.gif" alt="1일" /></a>
									<a href="javascript:payDateSet('7day')"><img id="7day" src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/btn_7day_on.gif" alt="7일" /></a>
									<a href="javascript:payDateSet('1month')"><img id="1month" src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/btn_1mon.gif" alt="1개월" /></a>
									<a href="javascript:payDateSet('3month')"><img id="3month" src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/btn_3mon.gif" alt="3개월" /></a>
								</td>
							</tr>
							<tr>
								<th scope="row"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/sl_txt04.gif" alt="상품구분" /></th>
								<td class="tit02">
									<input type="checkbox" id="strPrdType" class="strPrdType" name="productSaleS.strPrdType" value="1"/><label for="procate01">&nbsp;유료상품</label>&nbsp;&nbsp;
									<input type="checkbox" id="strPrdType" class="strPrdType" name="productSaleS.strPrdType" value="0"/><label for="procate02">&nbsp;무료상품</label>
								</td>
							</tr>
							<tr>
								<th colspan="2" class="tit03" scope="row"><a href="#" id="searchBtn"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/btn_search.gif" alt="검색" /></a></th>
							</tr>
						</tbody>
					</table>
					</s:form>
				</div>
				<p class="txtr mar_b5"><a href="#" id="excelDownBtn"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/btn_excel.gif" alt="엑셀파일 다운로드" /></a></p>
				<div class="tstyleE">
					<table summary="상품별 판매현황">
						<caption>상품별 판매현황</caption>
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
								<th rowspan="2" scope="col" class="btnone"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit06.gif" alt="번호" /></th>
								<th rowspan="2" scope="col" class="btnone"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit07.gif" alt="상품명" /></th>
								<th rowspan="2" scope="col" class="btnone"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit08.gif" alt="상품AID" /></th>
								<th rowspan="2" scope="col" class="btnone"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit09.gif" alt="상품구분" /></th>
								<th rowspan="2" scope="col" class="btnone"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit10.gif" alt="상품가격" /></th>
								<th colspan="2" scope="col" class="btnone th01"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit02.gif" alt="판매내역" /></th>
								<th colspan="3" scope="col" class="btnone th01"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit03.gif" alt="결제수단" /></th>
								<th colspan="2" scope="col" class="btnone brnone th01"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit04.gif" alt="구매취소내역" /></th>
							</tr>
							<tr>
								<th class="th02"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit04_01.gif" alt="건수" /></th>
								<th class="th02"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit04_02.gif" alt="금액" /></th>
								<th class="th02"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit03_01.gif" alt="신용카드" /></th>
								<th class="th02"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit03_02.gif" alt="핸드폰" /></th>
								<th class="th02"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit22.gif" alt="T store Cash" /></th>
								<th class="th02"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit04_01.gif" alt="건수" /></th>
								<th class="th02 brnone"><img src="/devpoc/${ThreadSession.serviceLocale.language}/images/sl/th_tit04_02.gif" alt="금액" /></th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
							<c:when test="${totalCount eq 0 }">
							<tr>
								<td colspan="12" class="text_c">검색결과가 없습니다.</td>
							</tr>
							</c:when>
							<c:otherwise>
							<c:forEach items="${productSaleList }" var="salestat">
							
							<tr>
								<td>${salestat.totalCount - salestat.rnum + 1}</td>
								<td class="tit07">${salestat.prdName}</td>
								<td><span class="tcor">${salestat.prodId}</span></td>
								<td>
									<c:choose>
									<c:when test="${salestat.prdSaleType eq '0' }">
										<span class="txtcolor03">무료</span>
									</c:when>
									<c:otherwise>
										<span class="txtcolor03">유료</span>
									</c:otherwise>
									</c:choose>
								</td>
								<td><strong><g:text value="${salestat.prodprc}" format="###,###,###,###"/></strong>원</td>
								<td><g:text value="${salestat.chargedDwnlCnt}" format="###,###,###,###" />건</td>
								<td><g:text value="${salestat.saleAmt}" format="###,###,###,###" />원</td>
								<td><g:text value="${salestat.cardSaleAmt}" format="R###,###,###,###" />원</td>
								<td><g:text value="${salestat.phoneSaleAmt}" format="R###,###,###,###" />원</td>
								<td><g:text value="${salestat.cashSaleAmt}" format="R###,###,###,###" />원</td>
								<td><g:text value="${salestat.cancelDwnlCnt}" format="###,###,###,###" />건</td>
								<td class="brnone"><g:text value="${salestat.cancelAmt}" format="###,###,###,###" />원</td>
							</tr>
							</c:forEach>
							</c:otherwise>
							</c:choose>
						</tbody>
					</table>
				</div>
				<!-- paging -->
				<div align="center">
				       <g:pageIndex item="${list}"/>
				</div>
				<!-- //paging -->

			</div>
			<!-- //CONTENT TABLE E-->

		</div>
		<!-- //Content Area E -->