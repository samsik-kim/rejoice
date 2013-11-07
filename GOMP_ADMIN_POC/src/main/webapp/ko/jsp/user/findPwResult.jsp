<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
		
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 아이디 비밀번호 찾기 <strong>&gt;</strong> <span>비밀번호 찾기</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_title02.gif'/>" alt="비밀번호 찾기" /></h3>
	</div>

	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		
		<div class="ipbox bgimg07">
			<p class="txttype06"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_txt02.gif'/>" alt="이메일 발송이 완료되었습니다." /><br />
			임시비밀번호가 입력하신 <span class="txtcolor07"><g:html  value='${email }'/></span>의 주소로 발송되었습니다.<br />
			임시비밀번호로 로그인 하신 후 다시 설정하시기 바랍니다. 감사합니다</p>

		</div>
		<div class="btnarea mar_t30">
			<a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/login/loginMain.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_login1.gif'/>" alt="로그인" /></a>
			<a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/login/findPw.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_back.gif'/>" alt="뒤로" /></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>