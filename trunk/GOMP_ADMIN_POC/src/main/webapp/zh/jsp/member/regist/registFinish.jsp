<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<!-- Content Area S -->
		<div id="contents_area" class="ut_area">
			<!-- Title Area S -->
			<div id="ctitle_area">
				<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_title01.gif'/>" alt="會員註冊" /></h3>
			</div>
			<!-- //Title Area E -->
			
			<!-- CONTENT TABLE S-->
			<div id="utcontents">
				
				<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h4_05.gif'/>" alt="填寫會員資料 - 系統將會發送認證信至您的信箱!" /></h4>
				<!-- Tab_menu S -->
				<div class="tab01 mar_b22">
					<ul>
						<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab01.gif'/>" alt="選擇會員類別" /></li>
						<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab02.gif'/>" alt="確認註冊與否" /></li>
						<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab03.gif'/>" alt="同意會員條款" /></li>
						<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab04_01.gif'/>" alt="填寫會員資料" /></li>
						<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab05_on.gif'/>" alt="會員註冊完成" /></li>
					</ul>
				</div>
				<!-- //Tab_menu E -->
				<div class="utbox bgimg01">
					<p class="txttype02">
						親愛的<span class="txtcolor06"><g:html  value='${member.mbrId}'/></span>系統將會發送認證信至 <span class="txtcolor07"><g:html  value='${member.emailAddr}'/></span>
					</p>
					<p class="txtl pad_l185 mar_b15">經由您進行信箱認證後, 才會完成註冊! </p>
				</div>
				<div class="btnarea mar_t30">
					<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_main.gif'/>" alt="首頁" /></a>
				</div>
			
			</div>
			<!-- //CONTENT TABLE E-->

		</div>
		<!-- //Content Area E -->