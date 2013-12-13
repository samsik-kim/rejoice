<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
form{clear:both;}
</style>
<script type="text/javascript">
//<![CDATA[
	$(function() {
		var form = document.searchForm;

		// search start ~ end date datepicker
		$( "#startDate" ).datepicker();
		$( "#endDate" ).datepicker();
		
		// today, weekly, monthly search btn action
		$(".searchDateBtn").click(function(event){
			event.preventDefault();
			var clickedId = this.id;
			
		// strong / span switch
		$(".searchDateBtn").each(function(){
			if(clickedId == this.id){
				$(this).html("<strong>"+$(this).text()+"</stong>");
				
			}else{
				$(this).html("<span>"+$(this).text()+"</span>");
			}
		});
		
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
		
		// startDate, endDate onChange Action
		$("#startDate, #endDate ").change(function(event){
			// strong / span switch
			$(".searchDateBtn").each(function(){
				$(this).html("<span>"+$(this).text()+"</span>");
			});
			// searchDate Condition setting
			$(".searchDate").each(function(){
				$(this).val("");
			});
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
			$("input:radio[name=sub.issueType]").filter('input:radio[value=all]').attr("checked", "checked");
			$("#searchWeekBtn").click();
		});
		
		// registBtn btn 
		$("#registBtn").click(function(event){
			event.preventDefault();
			gotoRegistReqGiftCard();
		});
		
	});
	
	function goPage(no) {
	    $("#no").val(no);
	    $("#searchBtn").click();
	}
	
	function funcSearch(){
		if(showValidate('searchForm', 'dialog', '<gm:string value="jsp.gift.giftCardInfoList.msg.noresult"/>')){
			document.searchForm.action="giftCardinfoList.omp";
			document.searchForm.submit();
		}
	}
	
	function goGiftCardDetail(seq) {
		$("#groupSeq").val(seq);
		document.searchForm.action="giftCardDetailInfo.omp";
		document.searchForm.submit();
	}
	
	function gotoRegistReqGiftCard() {
		document.searchForm.action="viewRequestIssueGiftCard.omp";
		document.searchForm.submit();
	}
	
//]]>
</script>
	</head>
<body>
	<div id="location">
		Home &gt; 상품관리 &gt; 캐쉬관리 &gt <strong>기프트카드 내역</strong> 
	</div><!-- //location -->
	<h1 class="fl pr10">기프트카드 내역</h1>
	<p>기프트카드 내역 관리 및 신규 생성 합니다.</p>
	
	<s:form name="searchForm" id="searchForm" theme="simple" method="post">
	<input type="hidden" id="groupSeq" name="sub.groupSeq"/>
	<input type="hidden" id="searchToday" name="sub.searchToday" value="${sub.searchToday }" class="searchDate" rel="searchTodayBtn" />
	<input type="hidden" id="searchWeek" name="sub.searchWeek" value="${sub.searchWeek }"  class="searchDate" rel="searchWeekBtn" />
	<input type="hidden" id="searchMonth" name="sub.searchMonth" value="${sub.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
	<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />
	
	<table class="tabletype01">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
		<tr>
			<th>발급기준</th>
			<td class="align_td">
				<s:radio id="issueType" name="sub.issueType" list="#{'all':'전체','OR002901':'증정용','OR002902':'판매용'}" value="sub.issueType" required="true" tooltip="<gm:string value='jsp.gift.giftCardInfoList.msg.tooltip'/>"/>
			</td>
			<td rowspan="3" class="text_c" >
				<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
				<a class="btn" href="#" id="searchInitBtn"><span>검색초기화</span></a>
			</td>
		</tr>
		<tr>
			<th>검색기간</th>
			<td class="align_td">
				<input id="startDate" name="sub.startDate" type="text" class="txt" style="width:80px;" value="${sub.startDate }" readonly v:required m:required='<gm:tagAttb value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.list.search.startDate"/></gm:arg></gm:tagAttb>' /> ~ 
				<input id="endDate" name="sub.endDate" type="text" class="txt" style="width:80px;" value="${sub.endDate }" readonly v:required m:required='<gm:tagAttb value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.list.search.endDate"/></gm:arg></gm:tagAttb>' v:scompare="ge,@{sub.startDate}" m:scompare='<gm:tagAttb value="jsp.list.validate.wrongDate"/>'/> 
				<a class="btn_s searchDateBtn" href="#" id="searchTodayBtn" rel="today"><span>오늘</span></a>
				<a class="btn_s searchDateBtn" href="#" id="searchWeekBtn" rel="7day"><span>최근1주</span></a>
				<a class="btn_s searchDateBtn" href="#" id="searchMonthBtn" rel="1month"><span>최근1개월</span></a>
			</td>
		</tr>
		<tr>
			<th>기프트 카드명</th>
			<td class="align_td" ><input type="text" id="searchText" name="sub.searchText" value="${sub.searchText }" cssClass="txt" style="width:80%;" /></td>
		</tr>
	</table>
	
	</s:form>
	<!-- 리스트 시작 -->
	<table class="tabletype02 mt25">
		<colgroup>
				<col >
				<col >
				<col >
				<col >
				<col >
				<col >
				<col >
				<col >
		</colgroup>
		<thead>
		<tr>
			<th>번호</th>
			<th>기프트카드명</th>
			<th>발급형태</th>
			<th>발급 Cash</th>
			<th>발급</th>
			<th>발급수량</th>
			<th>발급일</th>
			<th>등록일</th>
		</tr>                                
		</thead>
		<tbody>
		<c:choose>
		<c:when test="${totalCount eq 0 }">
		<tr>
			<td colspan="8" class="text_c"><gm:html value='jsp.gift.giftCardInfoList.msg.noresult'/></td>
		</tr>
		</c:when>
		<c:otherwise>
		<c:forEach items="${giftCardlist }" var="giftCard">
		<tr>
			<td>${giftCard.totalCount - giftCard.rnum + 1}</td>
			<td><a href="javascript:goGiftCardDetail('${giftCard.gcgroupseq}');">${giftCard.cardName }</a></td>
			<td><gc:text code="${giftCard.cardType }" /></td>
			<td><g:html format="R###,###,###"><gc:text code="${giftCard.cardAmt }"/></g:html> P </td>
			<td><c:if test='${giftCard.issueYn == "Y"}'>발급</c:if><c:if test='${giftCard.issueYn != "Y"}'>미발급</c:if></td>
			<td><g:text value="${giftCard.cardCount }" format="R#,###"/> 장</td>
			<td>${giftCard.issueReqDt }</td>
			<td>${giftCard.regDt }</td>
		</tr>
		</c:forEach>
		</c:otherwise>
		</c:choose>
		</tbody>
	</table>
	<p class="btn_wrap text_r mt25">
		<a class="btn" href="#" id="registBtn"><span>기프트카드 생성</span></a>
	</p>
	
	<!-- paging -->
	<div align="center">
	       <g:pageIndex item="${list}"/>
	</div>
	<!-- //paging -->
</body>
</html>