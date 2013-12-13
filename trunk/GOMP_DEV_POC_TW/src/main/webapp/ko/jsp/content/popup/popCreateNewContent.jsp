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

<!-- 레이어팝업 -->
<div class="layerwrap">

	<div id="pop_area01">
		<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_05.gif'/>" alt="상품 등록 완료" /></h2>
		<p class="pop_txt2">등록하신 상품에 대한 <br />Application ID : <span class="txtcolor06">${content.aid}</span>가 발급이 완료되었습니다.</p>
		<p class="pop_txt2">상품등록을 계속 하시겠습니까?</p>
		<div class="pop_btn">
			<a href="#" id="regBtn"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_yes.gif'/>" alt="예" /></a>
			<a href="#" id="cnlBtn"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_no.gif'/>" alt="아니오" /></a>
		</div>
		<p class="pop_close">
			<a href="#" id="clsBtn"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif'/>" alt="닫기" /></a>
		</p>
	</div>
</div>
<!-- //레이어팝업 -->	