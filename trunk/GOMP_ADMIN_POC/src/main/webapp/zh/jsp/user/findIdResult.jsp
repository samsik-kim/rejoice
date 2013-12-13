<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!-- //Content Area S -->
<div id="contents_area">	  
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 查詢帳號/密碼 <strong>&gt;</strong> <span>查詢帳號</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_title01.gif' />" alt="查詢帳號" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">

		<div class="utbox bgimg06">
			<p class="txttype05"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_txt01.gif' />" alt="帳號查詢結果" /><br />
			您的帳號為<br />
			<span class="pbult08">帳號 : </span> <span class="txtcolor12"><g:html  value='${resultId }'/></span></p>
		</div>
		<div class="btnarea mar_t30">
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/login/loginMain.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_login1.gif' />" alt="登入" /></a>
			<a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/login/findId.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_back.gif' />" alt="上頁" /></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->
