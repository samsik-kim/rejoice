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
		<h1>상품검색 <span class="h1_sub"> 상품명을 클릭하시면 해당 상품이 화면에 적용됩니다.</span></h1>
		<table class="pop_tabletype01">
			<colgroup><col style="width:20%;"><col style="width:70%"><col style="width:10%"></colgroup>
			<tbody>
			<tr>
				<th>상품검색</th>
				<td>
					
					<select name="searchParam.searchType">
						<option value="prodNm" selected="selected">상품명</option>
						<option value="aid">상품AID</option>
					</select>
					<input type="text" id="" class="txt" size="30" name="searchParam.searchText" value="${searchParam.searchText}"/>
				</td>
				<td><a href="#" class="btn" id="searchBtn"><span>검색</span></a></td>
			</tr>
			</tbody>
		</table>
		<table class="pop_tabletype02 mt25">
			<colgroup><col style="width:8%;"><col style="width:13%;"><col style="width:25%;"><col style="width:15%;"><col style="width:12%"><col style="width:12%"><col style="width:15%;"></colgroup>
			<tbody>
			<tr>
				<th>번호</th>
				<th>카테고리</th>
				<th>상품명</th>
				<th>개발자</th>
				<th>상품AID</th>
				<th>다운로드</th>
				<th>게시일</th>
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
		<a class="btn fr" href="#" id="closeBtn"><strong>닫기</strong></a>
	</div>	
</div><!-- //contents -->
</form>
</body>
</html>