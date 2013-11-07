<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!-- Content Area S -->
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 會員中心 &gt; <span>變更電子郵件</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title02.gif'/>" alt="變更電子郵件" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
	
		<div class="mpbox bgimg08">
			<p class="txttype07"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_txt01.gif'/>" alt="電子郵件地址變更成功" /><br />
			您的新電子郵件地址變更作業成功!<br />
			<span class="pbult08">新電子郵件地址 :</span> <span class="txtcolor12"><g:html  value='${profileInfo.emailAddr}' /></span></p>
		</div>
		<div class="btnarea mar_t30">
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif'/>" alt="確定" /></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->