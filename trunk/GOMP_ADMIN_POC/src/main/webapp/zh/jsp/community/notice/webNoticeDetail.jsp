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
var searchNoticeList = function() {
	document.detailForm.action="<c:url value="/community/iFrameListWebNotice.omp"/>";
	document.detailForm.submit();
};

</script>
</head>
<body onload="parent.frameLoaded($('#wrap').outerHeight())" bgcolor="White">
<div id="wrap" style="background:#fff;">
<form id="detailForm" name="detailForm">
<input type="hidden" name="notice.page.no" value="${notice.page.no}"/>
	<table class="list_table list_table_v">
		<colgroup>
			<col width="55px" /><col width="135px" /><col width="58px" /><col width="140px" /><col width="65px" /><col width="220px" />
		</colgroup>
		<tr>
			<th colspan="6"><g:html value="${notice.title}" /> </th>
		</tr>
		<tr>
			<th class="t_th"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/th_snum02.gif" alt="hits" /></th>
			<td class="num"><g:html value="${notice.hit}" /></td>
			<th class="t_th"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/th_wdate02.gif" alt="date" /></th>
			<td class="num"><g:html value="${notice.insDttm}" format="L####-##-##" /></td>
			<th class="t_th"><img src="../${ThreadSession.serviceLocale.language}/images/pw/th_file.gif" alt="attachment" /></th>
			<c:forEach items="${noticeSub}" var="item" varStatus="cnt">
				<c:if test="${item.ftype ne 'JPG' && item.ftype ne 'JPEG' && item.ftype ne 'GIF' && item.ftype ne 'PNG'}">
				<td class="num"><img src="../${ThreadSession.serviceLocale.language}/images/pw/icon_file.gif" alt="" />
					<a href="<c:url value="/phmodel/fileWebDown.omp"><c:param name="bnsType" value="common.path.http-share.common.notice"/><c:param name="filePath" value="${item.fnm}"/><c:param name="fileName" value="${item.ofnm}"/></c:url>" class="cl"><g:html value="${item.ofnm}" /></a> (${item.fsz}KB)
				</td>
				</c:if>
			</c:forEach>
		</tr>
		<tr>
			<td colspan="6" class="cont">
				${notice.dscr}<br>
				<c:forEach items="${noticeSub}" var="item" varStatus="cnt">
					<c:if test="${item.ftype eq 'JPG' ||item.ftype eq 'JPEG'||item.ftype eq 'GIF'||item.ftype eq 'PNG'}">
					<img src="<c:url value="/phmodel/imgWebView.omp"><c:param name="bnsType" value="common.path.http-share.common.notice"/><c:param name="filePath" value="${item.fnm}"/><c:param name="fileName" value="${item.ofnm}"/></c:url>" />
					</c:if>
				</c:forEach>
			</td>
		</tr>
	</table>
	<div class="btn text_r">
		<a href="javascript:searchNoticeList();" class="arr"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pw/btn_list.gif" alt="list" /></a>
	</div>
</form>
</div>
</body>
</html>