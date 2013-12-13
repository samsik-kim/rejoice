<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 

<script language="javascript">
	$(document).ready( function() {
		$('#clsBtn').attr('href', "<c:url value='/content/contentsStatusList.omp'/>");
	});
</script>

<!-- 레이어팝업 -->
<div class="layerwrap">
	<div id="pop_area01">
		<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/er_title_01.gif'/>" alt="상품 등록 실패" /></h2>
		<p class="pop_txt2"><gm:string value="jsp.content.common.msg.result.fail"/></p>
		<p class="pop_close">
			<a href="#" id="clsBtn"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif'/>" alt="닫기" /></a>
		</p>
	</div>
</div>
<!-- //레이어팝업 -->	