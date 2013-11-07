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
	body.popup table {
		margin-bottom:15px
	}
</style>
<script type="text/javascript" src="<c:url value="/js/common/common.js"/>"></script>
<script type="text/javascript" language="javascript">
//<![CDATA[
$(document).ready(function(){
	$("#closeBtn").click(function(event){
		event.preventDefault();
		self.close();
	});
	
	// search btn
	$("#searchBtn").click(function(event){
		event.preventDefault();
		funcSearch();
	});

	//winResize();
});


function goPage(no) {
    $("#no").val(no);
    $("#searchBtn").click();
}

function funcSearch(){
	$('#searchForm').attr("action","./popProductInfo.omp");
	$('#searchForm').submit();	
}

function funcSelectContent(aid, index) {
	var prodNm = $('#prodNmIndex' + index).text();
	opener.selectedContent(aid, encodeURI(prodNm), this);
	//$("#closeBtn").click();
}


function winResize() {
 var body = $("body");
 body.css({margin:0, padding:0, overflow:"hidden"});
 var Dwidth = body.outerWidth();
 var Dheight = body.outerHeight();
 body.append("<div id= 'lastDiv'></div>");
 var divEl = $("#lastDiv");
 divEl.attr("id", "lastSapn");
 divEl.css({position:"absolute", left:0, top:0, width:"100%", height:"100%"});
    //alert("Dwidth : " + Dwidth + ", divEl.offsetWidth : " + divEl.outerWidth() + ", Dheight : " + Dheight + ", divEl.offsetHeight : " + divEl.innerHeight());
 if($.browser.msie) {
  window.resizeBy(Dwidth-divEl.outerWidth(), Dheight-divEl.outerHeight() + 10);
 } else {
  window.resizeBy(Dwidth-divEl.outerWidth(), Dheight-divEl.outerHeight());
 }
 divEl.remove();
}

//]]>
</script>
</head>
<body class="popup"  id="popBody" style="width:;heigth:;position:;">  
<div id="popup_area_810">
<form name="searchForm" id="searchForm">
	<input type="hidden" id="no" name="searchParam.page.no" value="${searchParam.page.no }" />
		<h1>搜尋產品 <span class="h1_sub"> 點選產品名稱，該產品之資訊將顯示於畫面。</span></h1>
		<table class="pop_tabletype01">
			<colgroup><col style="width:20%;"><col style="width:70%"><col style="width:10%"></colgroup>
			<tbody>
			<tr>
				<th>搜尋產品</th>
				<td>
					
					<select name="searchParam.searchType">
						<option value="prodNm" <c:if test='${searchParam.searchType ne "aid" }'>selected="selected"</c:if>  >產品名稱</option>
						<option value="aid" <c:if test='${searchParam.searchType eq "aid" }'>selected="selected"</c:if>>產品 AID</option>
					</select>
					<input type="text" id="" class="txt" size="30" name="searchParam.searchText" value="${searchParam.searchText}"/>
				</td>
				<td><a href="#" class="btn" id="searchBtn"><span>搜尋</span></a></td>
			</tr>
			</tbody>
		</table>
		<table class="pop_tabletype02 mt25">
			<colgroup><col style="width:8%;"><col style="width:13%;"><col style="width:25%;"><col style="width:15%;"><col style="width:12%"><col style="width:12%"><col style="width:15%;"></colgroup>
			<tbody>
			<tr>
				<th>序號</th>
				<th>類別</th>
				<th>產品名稱</th>
				<th>開發商</th>
				<th>AID</th>
				<th>下載</th>
				<th>上傳日期</th>
			</tr>
			<c:choose>
				<c:when test="${firstSearch}">
					<tr>
						<td colspan="7"><gm:html value="jsp.product.initSearchMsg"/></td>
					</tr>
				</c:when>
				<c:when test="${totalCount eq 0 }">
					<tr>
						<td colspan="7"><gm:html value="jsp.product.noSearchMsg"/></td>
					</tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${contentsList}" var="content" varStatus="listIndex">
						<tr>
							<td>${content.totalCount - content.rnum + 1}</td>
							<td>${content.ctgrNm2}</td>
							<td><a href="javascript:funcSelectContent('${content.aid}', '${listIndex.index}');" ><strong class="c_06f" id="prodNmIndex${listIndex.index}" >${content.prodNm}</strong></td>
							<td>${content.devUserId}</td>
							<td>${content.aid}</td>
							<td>${content.dwldCount}</td>
							<td>${content.saleStartDt}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>	
		</tbody>
	</table>
	<!-- paging -->
	<div align="center">
        <g:pageIndex item="${contentsList}"/>
    </div>
	<!-- //paging -->
	<div class="pop_btn" >
		<a class="btn fr" href="#" id="closeBtn"><strong>關閉</strong></a>
	</div>	
</div><!-- //contents -->
</form>
</body>
</html>