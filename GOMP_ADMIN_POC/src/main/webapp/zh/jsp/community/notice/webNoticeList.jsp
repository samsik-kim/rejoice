<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<html>
<head>
<link href="<c:url value="/${ThreadSession.serviceLocale.language}/css/pcweb.css"/>" rel="stylesheet" type="text/css" />
<link href="<c:url value="/${ThreadSession.serviceLocale.language}/css/base_pcweb.css"/>" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<c:url value="/js/jquery/1.5.1/jquery.min.js"/>"></script>
<script type="text/javascript">
document.domain	= (location.host.indexOf("dtstore") != -1 ? "dtstore.tw" : "whoopy.tw");
function goPage(no) {
	//$("form[name=listForm] input[name=notice\\.page\\.no]").val(no);
	document.listForm.no.value=no;
	searchNoticeList();
}
var searchNoticeList = function() {
	document.listForm.action="<c:url value="/community/iFrameListWebNotice.omp"/>";
	document.listForm.submit();
};
var searchNoticeDetail = function(noticeId) {
	document.listForm.noticeId.value=noticeId;
	document.listForm.action="<c:url value="/community/iFrameDetailWebNotice.omp"/>";
	document.listForm.submit();
};
</script>
</head>
<body onload="parent.frameLoaded($('#wrap').outerHeight())" bgcolor="White">
<div id="wrap" style="background:#fff;">
<form id="listForm" name="listForm">
<input type="hidden" id="no" name="notice.page.no" value="${notice.page.no}"/>
<input type="hidden" id="noticeId" name="notice.noticeId" value="${notice.noticeId}"/>
	<table class="list_table">
		<colgroup>
			<col width="55px" /><col width="461px" /><col width="90px" /><col width="90px" />
		</colgroup>
		<tr>
			<th class="bo_l"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/th_num.gif" alt="no" /></th>
			<th><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/th_tit.gif" alt="title" /></th>
			<th><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/th_snum.gif" alt="hits" /></th>
			<th class="bo_r bg_f0"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/th_wdate.gif" alt="date" /></th>
		</tr>
		<c:choose>
			<c:when test="${srchCnt > 0}">
				<c:forEach items="${noticeList}" var="item" varStatus="cnt">
					<tr>
						<td><g:html value="${item.totalCount - item.rnum + 1}" /></td>
						<td class="list_tit"><a href="javascript:searchNoticeDetail('${item.noticeId}');"><g:html value="${item.title}" /></a></td>
						<td><g:html value="${item.hit}" /></td>
						<td><g:html value="${item.insDttm}" format="L####-##-##" /></td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr><td colspan="4" class="text_c">無資料。</td></tr>
			</c:otherwise>
		</c:choose>
	</table>
	<!-- paging -->
		<div align="center">
			<g:pageIndex item="${noticeList}"/>
		</div>
	<!-- //paging -->
</form>
</div>
</body>
</html>