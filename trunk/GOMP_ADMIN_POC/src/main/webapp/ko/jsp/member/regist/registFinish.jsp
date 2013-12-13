<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<!-- Content Area S -->
		<div id="contents_area" class="ut_area">
			<!-- Title Area S -->
			<div id="ctitle_area">
				<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_title01.gif'/>" alt="회원가입" /></h3>
			</div>
			<!-- //Title Area E -->
			
			<!-- CONTENT TABLE S-->
			<div id="utcontents">
				
				<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h4_05.gif'/>" alt="회원정보 입력 - 회원 인증을 위한 이메일을 발송하였습니다." /></h4>
				<!-- Tab_menu S -->
				<div class="tab01 mar_b22">
					<ul>
						<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab01.gif'/>" alt="회원종류 선택" /></li>
						<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab02.gif'/>" alt="가입 여부 확인" /></li>
						<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab03.gif'/>" alt="약관동의" /></li>
						<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab04_01.gif'/>" alt="회원정보 입력" /></li>
						<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab05_on.gif'/>" alt="회원가입 완료" /></li>
					</ul>
				</div>
				<!-- //Tab_menu E -->
				<div class="utbox bgimg01">
					<p class="txttype02">
						<span class="txtcolor06"><g:html  value='${member.mbrId}'/></span>님의 이메일 주소 <span class="txtcolor07"><g:html  value='${member.emailAddr}'/></span>로 <strong>인증확인</strong> 메일이 발송되었습니다.<br />이메일 인증을 하셔야 회원 가입이 완료됩니다.
					</p>
				</div>
				<div class="btnarea mar_t30">
					<a href="<c:url value='/main/main.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_main.gif'/>" alt="메인" /></a>
				</div>
			
			</div>
			<!-- //CONTENT TABLE E-->

		</div>
		<!-- //Content Area E -->