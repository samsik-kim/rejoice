<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<html>
<head>
<link href="<c:url value="/css/pcweb.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/css/base_pcweb.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/js/jquery/1.5.1/jquery.min.js"/>"></script>
<script type="text/javascript">
document.domain	= (location.host.indexOf("dtstore") != -1 ? "dtstore.tw" : "tstore.tw");
function goPage(no) {
	//$("form[name=listForm] input[name=faq\\.page\\.no]").val(no);
	document.listForm.no.value=no;
	searchFaqList();
}
var searchFaqList = function() {
	document.listForm.action="<c:url value="/community/iFrameListWebFaq.omp"/>";
	document.listForm.submit();
};
var searchFaqDetail = function(faqId) {
	document.listForm.faqId.value=faqId;
	document.listForm.action="<c:url value="/community/iFrameDetailWebFaq.omp"/>";
	document.listForm.submit();
};
</script>
</head>
<body onload="parent.frameLoaded($('#wrap').outerHeight())" bgcolor="White">
<div id="wrap" style="background:#fff;">
<form id="listForm" name="listForm" method="post">
<input type="hidden" id="no" name="faq.page.no" value="${faq.page.no}"/>
<input type="hidden" id="faqId" name="faq.faqId" value="${faq.faqId}"/>
<input type="hidden" id="nPageNo" name="nPageNo" value="${nPageNo}"/>
	<div class="search">
		<img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/i_search.gif" alt="" />
			<select style="width:80px;" id="searchType" name="faq.searchType">
				<option value="category" <c:if test="${faq.searchType eq 'category'}">selected</c:if>>카테고리</option>
				<option value="dscr" <c:if test="${faq.searchType eq 'dscr'}">selected</c:if>>내용</option>
			</select>
			<input type="text" id="searchValue" name="faq.searchValue" style="width:300px;" value="${faq.searchValue}"/>
			<a href="javascript:searchFaqList();"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/btn_search.gif" alt="검색" /></a>
	</div>
	<!-- 검색결과 -->
	<p class="pb05"><strong class="c_e7590e"><g:html value="${faq.searchValue}" /></strong><c:if test="${faq.searchValue ne ''}">에 대해</c:if> 총 <g:html value="${srchCnt}" />건이 검색 되었습니다.</p>
		<table class="list_table">
			<colgroup>
				<col width="55px" /><col width="115px" /><col width="434px" /><col width="90px" />
			</colgroup>
			<tr>
				<th class="bo_l"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/th_num.gif" alt="번호" /></th>
				<th><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/th_cl02.gif" alt="분류" /></th>
				<th><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/th_tit.gif" alt="제목" /></th>
				<th class="bo_r bg_f0"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/th_snum.gif" alt="조회수" /></th>
			</tr>
			<c:choose>
				<c:when test="${srchCnt > 0}">
					<c:forEach items="${faqList}" var="item" varStatus="cnt">
						<tr>
							<td><g:html value="${item.totalCount - item.rnum + 1}" /></td>
							<td class="cl"><g:html value="${item.ctgrNm}" /></td>
							<td class="list_tit"><a href="javascript:searchFaqDetail('${item.faqId}');"><g:html value="${item.title}" /></a></td>
							<td><g:html value="${item.hit}" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="4" class="text_c">검색 결과가 없습니다.</td></tr>
				</c:otherwise>
			</c:choose>
		</table>
		<!-- paging -->
		<div align="center">
			<g:pageIndex item="${faqList}"/>
		</div>
	<!-- //paging -->
</form>
</div>
</body>
</html>