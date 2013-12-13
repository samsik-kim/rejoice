<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="contents_area" class="ut_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_title01.gif'/>" alt="회원가입" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="utcontents">
		
		<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h4_06.gif'/>" alt="이메일 인증완료 - 이메일 인증이 정상적으로 완료되었습니다." /></h4>
		<div class="utbox bgimg03">
			<p class="txttype04">
				<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt01.gif'/>" alt="이메일 인증이 정상적으로 완료되었습니다." />
				<span class="txtcolor05 txt_bold">&middot; 회원님께서는 이미 이메일 인증을 완료하셨습니다.</span><br />&nbsp;&nbsp;가입하신 아이디로 로그인 후 개발자센터를 이용해주시기 바랍니다.
			</p>
		</div>
		<div class="btnarea mar_t30">
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_main.gif'/>" alt="메인" /></a>
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/login/loginMain.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_login.gif'/>" alt="로그인" /></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>