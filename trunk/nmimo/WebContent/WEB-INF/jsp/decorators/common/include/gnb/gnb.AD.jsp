<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mimo" uri="/WEB-INF/tld/mimo.tld"%>
<mimo:userinfo var="user_session" />

<!-- Top Logo & UserMenu -->
<div class="top">
	<!-- Logo -->
	<h1 class="topLogo">
		<a href="#" onclick="javascript:doPost('/nmimo/main.do', '0', '00');"><img src="<c:url value="/resource/images/top_logo.gif"/>" alt="Olleh KT MIMO 메시지 통합관리 시스템" /></a>
	</h1>

	<!-- UserMenu -->
	<div class="userMenu">
		<p>AD${user_session.userId}님 로그인 중</p>
		<ul>
			<li><a href="/notice/list.do"><img src="<c:url value="/resource/images/top_btn_noti.png"/>" alt="공지사항" /></a></li>
			<li><a href="/config/preferences.do"><img src="<c:url value="/resource/images/top_btn_admin.png"/>" alt="환경설정" /></a></li>
			<li><a href="/user/logout.do"><img src="<c:url value="/resource/images/top_btn_logout.png"/>" alt="로그아웃" /></a></li>
		</ul>
	</div>
</div>


<h2 class="hidden">주메뉴</h2>

<!--메인메뉴-->
<div class="gnb" style="width: 330px">
	<div>
		<ul id="nav">
			<li>
				<c:if test="${menuId eq 'mynav0'}">				
					<a href="#" onclick="javascript:doPost('/stats/list.do', '0', '00');" onmouseover="javascript:qiehuan(0);" id="mynav0" class="nav_on" style='cursor:hand;'><span>통계</span></a>
				</c:if>
				<c:if test="${menuId ne 'mynav0'}">				
					<a href="#" onclick="javascript:doPost('/stats/list.do', '0', '00');" onmouseover="javascript:qiehuan(0);" onmouseout="javascript:qiehuanOut(0);" id="mynav0"  class="nav_off" style='cursor:hand;'><span>통계</span></a>
				</c:if>						
			</li>
		</ul>
	</div>
	<!--메인메뉴 끝-->
	<!--서브메뉴 시작-->
	<div id="menu_con">
		<div id="qh_con0">
			<ul style="display:none">
				<li><a href="#" onclick="javascript:doPost('/stats/list.do', '0', '00');" id="mycon00"><span>통계</span></a></li>
			</ul>
		</div>
	</div>
	<!--서브메뉴 끝-->
</div>
