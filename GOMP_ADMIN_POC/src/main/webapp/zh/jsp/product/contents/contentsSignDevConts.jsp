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
				首頁 &gt; 產品管理中心 &gt; 產品管理中心 &gt; <strong>產品資訊</strong>
			</div><!-- //location -->

			<h1 class="fl pr10">產品資訊</h1>
			<p>可查詢產品資訊及變更產品狀態。</p>
			<c:choose>
			<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_ING or contents.saleStat eq CONTENT_SALE_STAT_STOP}">
				<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="stopSaleBtn"><strong>禁售</strong></a></p>
			</c:when>
			<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_RESTRIC or contents.saleStat eq CONTENT_SALE_STAT_UNREGIST}">
				<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="startSaleBtn"><strong>解禁</strong></a></p>
			</c:when>
			</c:choose>
			<div class="tab">
				<ul>
					<li><a href="#" id="contentsBaseInfo">基本資訊</a></li>
					<li><a href="#" id="contentsProductInfo">產品資訊</a></li>
					<li><a href="#" id="devConts">開發資訊</a></li>
					<c:if test="${CONTENT_SALE_STAT_ING eq contents.saleStat}">
					<li><a href="#" id="saleDevConts">販售中產品開發資訊</a></li>
					</c:if>
					<c:if test="${CONTENT_SALE_STAT_NA ne contents.saleStat}">
					<li class="on"><a href="#" id="signDevConts">請審產品開發資訊</a></li>
					</c:if>
					<li><a href="#" id="saleStatHisList">狀態變更紀錄</a></li>
					<li><a href="#" id="productVerifyDetail">審核紀錄</a></li>
				</ul>
			</div>
			<table class="tabletype01">
				<colgroup><col style="width:150px;"><col ></colgroup>
				<tbody>
				<tr>
					<th>平台</th>
					<td><gc:html code="${contents.vmType }"/></td>
				</tr>
				<tr>
					<th>應用軟體DRM</th>
					<td>
					<c:choose>
					<c:when test="${contents.drmYn eq 'Y'}" ><gm:text value="jsp.product.drm.use"/></c:when>
					<c:otherwise><gm:text value="jsp.product.drm.notUse"/></c:otherwise>
					</c:choose>
					</td>
				</tr>
				<tr>
					<th>使用指南</th>
					<td>
						<c:if test="${ not empty contents.verifyScnrFile }">
							<a href="<c:url value="/fileSupport/fileDown.omp">
							<c:param name="bnsType" value="common.path.share.product"/>
							<c:param name="filePath" value="${contents.verifyScnrFile }"/>
							<c:param name="fileName" value="${contents.verifyScnrFileNm }"/>
							</c:url>">${contents.verifyScnrFileNm }</a>
						</c:if>
					</td>
				</tr>
				<tr>
					<th>最終變更日</th>
					<td><g:text format="L####-##-##" >${contents.updDttm }</g:text></td>
				</tr>
				</tbody>
			</table>
			<c:forEach items="${subContentsList }" var="subContents">
			<table class="tabletype02 mt25">
				<colgroup>
					<col style="width:150px;">
					<col style="width:300px;">
					<col style="width:100px;">
				</colgroup>
				<thead>
				<tr>
					<th colspan="4" class="text_l"><a href="<c:url value="/fileSupport/fileDown.omp">
							<c:param name="bnsType" value="common.path.share.product"/>
							<c:param name="filePath" value="${subContents.runFilePos }"/>
							<c:param name="fileName" value="${subContents.runFileNm }"/>
							</c:url>">#${subContents.runFileNm }</a></th>
				</tr>
				<tr>
					<th>手機名稱</th>
					<th>型號</th>
					<th>OS版本</th>
					<th>LCD 解析度</th>
				</tr>
				</thead>
				<tbody>
				<c:forEach items="${subContents.contentsSprtPhoneList }" var="phone">
				<tr>
					<td>${phone.modelNm }</td>
					<td>${phone.mgmtPhoneModelNm }</td>
					<td><gc:html code="${phone.vmVer }"/></td>
					<td><gc:html code="${phone.lcdSize }"/></td>
				</tr>
				</c:forEach>
				</tbody>
			</table>
			</c:forEach>
			<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="contentsList"><span>目錄</span></a></p>
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