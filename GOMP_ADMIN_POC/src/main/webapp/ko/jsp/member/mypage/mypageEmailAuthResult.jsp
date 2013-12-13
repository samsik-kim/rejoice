<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!-- Content Area S -->
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; <gm:html value='마이페이지'/> &gt; <span><gm:html value='이메일변경'/></span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title02.gif'/>" alt="이메일변경" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
	
		<div class="mpbox bgimg08">
			<p class="txttype07"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_txt01.gif'/>" alt="이메일 변경 완료" /><br />
			요청하신 이메일 주소 변경이 완료되었습니다.<br />
			<span class="pbult08">변경된 이메일 주소 :</span> <span class="txtcolor12"><g:html  value='${profileInfo.emailAddr}' /></span></p>
		</div>
		<div class="btnarea mar_t30">
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif'/>" alt="확인" /></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->