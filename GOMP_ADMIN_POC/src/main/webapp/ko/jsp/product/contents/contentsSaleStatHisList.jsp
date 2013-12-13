<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ page import="com.omp.admin.common.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
form{clear:both;}
</style>
<script type="text/javascript" src="<c:url value="/js/product.js"/>"></script>
<script type="text/javascript">
$(function(){
	<c:choose>
	<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_ING or contents.saleStat eq CONTENT_SALE_STAT_STOP}">
		$("#stopSaleBtn").click(function(event){
			event.preventDefault();
			<c:choose>
			<c:when test="${contents.verifyPrgrYn eq CODE_VERIFY_REQ or contents.verifyPrgrYn eq CODE_VERIFY_ING}">
			alert('<gm:string value="jsp.product.not.modify"><gm:arg><gc:html code="${contents.verifyPrgrYn }"/></gm:arg></gm:string>');
			</c:when>
			<c:otherwise>
			if(confirm('<gm:string value="jsp.product.execute.confirm"><gm:arg><gm:string value="jsp.product.saleStat.saleStop"/></gm:arg></gm:string>')){
				$.post('<c:url value="/product/ajaxUpdateStopSaleStat.omp"/>',{cid:"${contents.cid}"}, 
					function(data){
						alert(data.msg);
						// if success
						if(data.resultCode == 1){
							$("#contentsBaseInfo").click();		
						}
				},"json");		
			}
			</c:otherwise>
			</c:choose>
		});
	</c:when>
	<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_RESTRIC or contents.saleStat eq CONTENT_SALE_STAT_UNREGIST}">
		$("#startSaleBtn").click(function(event){
			event.preventDefault();
			<c:choose>
			<c:when test="${contents.verifyPrgrYn eq CODE_VERIFY_REQ or contents.verifyPrgrYn eq CODE_VERIFY_ING}">
			alert('<gm:string value="jsp.product.not.modify"><gm:arg><gc:html code="${contents.verifyPrgrYn }"/></gm:arg></gm:string>');
			</c:when>
			<c:otherwise>
			if(confirm('<gm:string value="jsp.product.execute.confirm"><gm:arg><gm:string value="jsp.product.saleStat.saleStart"/></gm:arg></gm:string>')){
				$.post('<c:url value="/product/ajaxUpdateStartSaleStat.omp"/>',{cid:"${contents.cid}"}, 
					function(data){
						alert(data.msg);
						// if success
						if(data.resultCode == 1){
							$("#contentsBaseInfo").click();							
						}
				},"json");
			}
			</c:otherwise>
			</c:choose>
		});
	</c:when>
	</c:choose>
});
</script>
</head>
<body>
			<div id="location">
				Home &gt; 상품관리 &gt; 상품관리 &gt; <strong>상품정보</strong>
			</div><!-- //location -->

			<h1 class="fl pr10">상품정보</h1>
			<p>상품정보 조회 및 상품상태를 변경 관리합니다.</p>			
			<c:choose>
			<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_ING or contents.saleStat eq CONTENT_SALE_STAT_STOP}">
				<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="stopSaleBtn"><strong>판매금지</strong></a></p>
			</c:when>
			<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_RESTRIC or contents.saleStat eq CONTENT_SALE_STAT_UNREGIST}">
				<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="startSaleBtn"><strong>판매금지 해지</strong></a></p>
			</c:when>
			</c:choose>
			<div class="tab">
				<ul>
					<li><a href="#" id="contentsBaseInfo">기본정보</a></li>
					<li><a href="#" id="contentsProductInfo">상품정보</a></li>
					<li><a href="#" id="devConts">개발정보</a></li>
					<c:if test="${CONTENT_SALE_STAT_ING eq contents.saleStat}">
					<li><a href="#" id="saleDevConts">판매중개발정보</a></li>
					</c:if>
					<c:if test="${CONTENT_SALE_STAT_NA ne contents.saleStat}">
					<li><a href="#" id="signDevConts">검증요청개발정보</a></li>
					</c:if>
					<li class="on"><a href="#" id="saleStatHisList">상태변경내역</a></li>
					<li><a href="#" id="productVerifyDetail">검증내역</a></li>
				</ul>
			</div>
			<table class="tabletype02">
				<colgroup>
					<col style="width:200px;">
					<col >
				</colgroup>
				<tbody>
				<g:size var="contentsSaleHistorySize" item="${contentsSaleHistoryList}" />
				<c:if test="${contentsSaleHistorySize eq 0 }">
				<tr>
					<td colspan="2"><gm:string value="jsp.product.noSearchMsg"/></td>
				</tr>
				</c:if>
				<c:forEach items="${contentsSaleHistoryList }" var="saleHistory">
				<tr>
					<th><g:text format="L####-##-## ##:##" >${saleHistory.chngDttm }</g:text></th>
					<td class="text_l">
						<c:choose>
						<c:when test="${saleHistory.adminChngYn eq 'Y' }">
						<strong class="c_c06">Admin</strong>
						</c:when>
						<c:otherwise>
						<strong class="c_06f">${saleHistory.changeId}</strong>
						</c:otherwise>
						</c:choose>
						 에 의해 <strong>[ <gc:html code="${saleHistory.saleStat }"/> ]</strong> 상태로 변경되었습니다</td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="contentsList"><span>목록</span></a></p>
			<s:form id="searchForm" name="searchForm" action="listContents" theme="simple"  method="get">
			<input type="hidden" id="cid" name="sub.cid" value="${sub.cid }"/>
			<input type="hidden" id="searchToday" name="sub.searchToday" value="${sub.searchToday }" class="searchDate" rel="searchTodayBtn" />
			<input type="hidden" id="searchWeek" name="sub.searchWeek" value="${sub.searchWeek }"  class="searchDate" rel="searchWeekBtn" />
			<input type="hidden" id="searchMonth" name="sub.searchMonth" value="${sub.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
			<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />
			<input type="hidden" id="masterNo" name="sub.masterNo" value="${sub.masterNo }" />
			<input type="hidden" id="vmType" name="sub.vmType" value="${sub.vmType }" />
			<input type="hidden" id="saleStat" name="sub.saleStat" value="${sub.saleStat }" />
			<input type="hidden" id="verifyPrgrYn" name="sub.verifyPrgrYn" value="${sub.verifyPrgrYn }" />
			<input type="hidden" id="startDate" name="sub.startDate" value="${sub.startDate }" />
			<input type="hidden" id="endDate" name="sub.endDate" value="${sub.endDate }" />
			<input type="hidden" id="dpCat1" name="sub.dpCat1" value="${sub.dpCat1 }" />
			<input type="hidden" id="dpCat2" name="sub.dpCat2" value="${sub.dpCat2 }" />
			<input type="hidden" id="searchType" name="sub.searchType" value="${sub.searchType }" />
			<input type="hidden" id="searchText" name="sub.searchText" value="${sub.searchText }" />
			</s:form>
</body>
</html>