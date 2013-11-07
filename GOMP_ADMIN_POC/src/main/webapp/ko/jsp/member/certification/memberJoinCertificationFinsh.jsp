<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
	<div class="utbox bgimg02">
		<p class="txttype03">
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_txt08.gif'/>" alt="이메일 인증이 정상적으로 완료되었습니다." class="pad_b15" /><br />
			<span class="txtcolor06">&nbsp;&nbsp;<g:html value="${member.mbrId }"/></span>
		</p>
		<p class="txt_90 txtl pad_l185">
			회원님은 현재 무료상품만 판매가 가능하십니다. 유료상품의 판매를 위해서는 정산정보 등록이 필요합니다.<br />
			<span class="txtcolor03">정산정보</span>를 지금 등록하시겠습니까?
		</p>
	</div>
	<div class="btnarea mar_t30">   
		<a href="${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }/mypage/mypageIntro.omp?forwardAction=CALCULATE"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_register1.gif'/>" alt="정산정보등록" /></a>
		<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/login/loginMain.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_login.gif'/>" alt="로그인" /></a>
	</div>

</div>
<!-- //CONTENT TABLE E-->
</div>