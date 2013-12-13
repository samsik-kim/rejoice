<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!-- //Content Area S -->
<div id="contents_area">	  
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 아이디 비밀번호 찾기 <strong>&gt;</strong> <span>아이디 찾기</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_title01.gif' />" alt="아이디 찾기" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">

		<div class="utbox bgimg06">
			<p class="txttype05"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_txt01.gif' />" alt="아이디 조회 결과 입니다." /><br />
			입력하신 정보와 일치하는 아이디는 아래와 같습니다.<br />
			<span class="pbult08">아이디 :</span> <span class="txtcolor12">${resultId }</span></p>
		</div>
		<div class="btnarea mar_t30">
			<a href="<c:url value='/login/loginMain.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_login1.gif' />" alt="로그인" /></a>
			<a href="<c:url value='/login/findId.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_back.gif' />" alt="뒤로" /></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->
