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
var searchFaqList = function() {
	document.detailForm.action="<c:url value="/community/iFrameListWebFaq.omp"/>";
	document.detailForm.submit();
};
</script>
</head>
<body onload="parent.frameLoaded($('#wrap').outerHeight())" bgcolor="White">
<div id="wrap" style="background:#fff;">
<form id="detailForm" name="detailForm" method="post">
<input type="hidden" name="faq.searchType" value="${faq.searchType}"/>
<input type="hidden" name="faq.searchValue" value="${faq.searchValue}"/>
<input type="hidden" id="no" name="faq.page.no" value="${faq.page.no}"/>
	<table class="list_table list_table_v">
		<colgroup>
			<col width="55px" /><col width="135px" /><col width="58px" /><col width="140px" /><col width="65px" /><col width="220px" />
		</colgroup>
		<tr>
			<th colspan="6" class="faq_q"><g:html value="${faq.title}" /></th>
		</tr>
		<tr>
			<th class="t_th"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/th_cl02.gif" alt="분류" /></th>
			<td class="num cl"><g:html value="${faq.ctgrNm}" /></td>
			<th class="t_th"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/th_snum02.gif" alt="조회수" /></th>
			<td class="num"><g:html value="${faq.hit}" /></td>
			<th class="t_th"><img src="../${ThreadSession.serviceLocale.language}/images/pw/th_file.gif" alt="첨부파일" /></th>
			<c:forEach items="${faqSub}" var="item" varStatus="cnt">
				<c:if test="${item.ftype ne 'JPG' && item.ftype ne 'JPEG' && item.ftype ne 'GIF' && item.ftype ne 'PNG'}">
				<td class="num"><img src="../${ThreadSession.serviceLocale.language}/images/pw/icon_file.gif" alt="" />
					<a href="<c:url value="/phmodel/fileWebDown.omp"><c:param name="bnsType" value="common.path.http-share.common.faq"/><c:param name="filePath" value="${item.fnm}"/><c:param name="fileName" value="${item.ofnm}"/></c:url>" class="cl"><g:html value="${item.ofnm}" /></a> (${item.fsz}KB)
				</td>
				</c:if>
			</c:forEach>
		</tr>
		<tr>
			<td colspan="6" class="cont">
				<div class="faq_a">
					${faq.dscr}<br>
				<c:forEach items="${faqSub}" var="item" varStatus="cnt">
					<c:if test="${item.ftype eq 'JPG' ||item.ftype eq 'JPEG'||item.ftype eq 'GIF'||item.ftype eq 'PNG'}">
					<img src="<c:url value="/phmodel/imgWebView.omp"><c:param name="bnsType" value="common.path.http-share.common.faq"/><c:param name="filePath" value="${item.fnm}"/><c:param name="fileName" value="${item.ofnm}"/></c:url>" />
					</c:if>
				</c:forEach>
				</div>
			</td>
		</tr>
	</table>
	<div class="btn text_r">
		<a href="javascript:searchFaqList();" class="arr"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/btn_list.gif" alt="목록" /></a>
	</div>
</form>
</div>
</body>
</html>