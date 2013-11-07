<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 

<script language="javascript">
	$(document).ready( function() {
		$('#regBtn').attr('href', "<c:url value='/content/contentDetailInfoView.omp'><c:param name='content.cid' value='${content.cid}'/></c:url>");
		$('#cnlBtn').attr('href', "<c:url value='/content/contentsStatusList.omp'/>");
		$('#clsBtn').attr('href', "<c:url value='/content/contentDetailInfoView.omp'><c:param name='content.cid' value='${content.cid}'/></c:url>");
	});
</script>

<div class="layerwrap">

	<div id="pop_area01">
		<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_05.gif'/>" alt="產品上架完畢" /></h2>
		<p class="pop_txt2">上架之產品AID已產生. <br />Application ID(應用程序帳號) : <span class="txtcolor06">${content.aid}</span></p>
		<p class="pop_txt2">是否繼續上傳?</p>
		<div class="pop_btn">
			<a href="#" id="regBtn"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_yes.gif'/>" alt="是" /></a>
			<a href="#" id="cnlBtn"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_no.gif'/>" alt="否" /></a>
		</div>
		<p class="pop_close">
			<a href="#" id="clsBtn"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif'/>" alt="close" /></a>
		</p>
	</div>
</div>
