<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
		
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 查詢帳號/密碼 <strong>&gt;</strong> <span>查詢帳號</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_title02.gif'/>" alt="查詢密碼" /></h3>
	</div>

	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		
		<div class="ipbox bgimg07">
			<p class="txttype06"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_txt02.gif'/>" alt="電子郵件發送成功" /><br/>
			臨時密碼已發送至 <span class="txtcolor07">${email }</span>中.<br />
			使用臨時密碼登入後, 請更改密碼，謝謝！</p>
		</div>
		<div class="btnarea mar_t30">
			<a href="<c:url value='/login/loginMain.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_login1.gif'/>" alt="上頁" /></a>
			<a href="<c:url value='/login/findPw.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_back.gif'/>" alt="登入" /></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>