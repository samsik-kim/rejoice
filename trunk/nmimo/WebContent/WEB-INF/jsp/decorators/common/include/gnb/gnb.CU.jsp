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
		<p>CU${user_session.userId}님 로그인 중</p>
		<ul>
			<li><a href="/notice/list.do"><img src="<c:url value="/resource/images/top_btn_noti.png"/>" alt="공지사항" /></a></li>
			<li><a href="/user/logout.do"><img src="<c:url value="/resource/images/top_btn_logout.png"/>" alt="로그아웃" /></a></li>
		</ul>
	</div>
</div>


<h2 class="hidden">주메뉴</h2>

<!--메인메뉴-->
<div class="gnb" style="width: 560px">
	<div>
		<ul id="nav">
			<li>
				<c:if test="${menuId eq 'mynav0'}">
					<a href="#" onclick="javascript:doPost('/myWork/standbyList.do', '0', '00');" onmouseover="javascript:qiehuan(0);" id="mynav0" class="nav_on" style='cursor:hand;'><span>나의작업</span></a>
				</c:if>
				<c:if test="${menuId ne 'mynav0'}">
					<a href="#" onclick="javascript:doPost('/myWork/standbyList.do', '0', '00');" onmouseover="javascript:qiehuan(0);" onmouseout="javascript:qiehuanOut(0);"  id="mynav0" class="nav_off" style='cursor:hand;'><span>나의작업</span></a>
				</c:if>
			</li>
			<li>
				<c:if test="${menuId eq 'mynav1'}">
					<a href="#" onclick="javascript:doPost('/reservation/list.do', '1', '10');" onmouseover="javascript:qiehuan(1);" id="mynav1" class="nav_on" style='cursor:hand;'><span>사전예약</span></a>
				</c:if>
				<c:if test="${menuId ne 'mynav1'}">
					<a href="#" onclick="javascript:doPost('/reservation/list.do', '1', '10');" onmouseover="javascript:qiehuan(1);" onmouseout="javascript:qiehuanOut(1);" id="mynav1" class="nav_off" style='cursor:hand;'><span>사전예약</span></a>
				</c:if>
			</li>
			<li>
				<c:if test="${menuId eq 'mynav2'}">
					<a href="#" onclick="javascript:doPost('/message/msgRegForm.do', '2', '20');" onmouseover="javascript:qiehuan(2);" id="mynav2" class="nav_on" style='cursor:hand;'><span>발송등록</span></a>
				</c:if>
				<c:if test="${menuId ne 'mynav2'}">
					<a href="#" onclick="javascript:doPost('/message/msgRegForm.do', '2', '20');" onmouseover="javascript:qiehuan(2);" onmouseout="javascript:qiehuanOut(2);" id="mynav2" class="nav_off" style='cursor:hand;'><span>발송등록</span></a>
				</c:if>
			</li>
			<li>
				<c:if test="${menuId eq 'mynav3'}">				
					<a href="#" onclick="javascript:doPost('/stats/list.do', '3', '30');" onmouseover="javascript:qiehuan(3);" id="mynav3" class="nav_on" style='cursor:hand;'><span>통계</span></a>
				</c:if>
				<c:if test="${menuId ne 'mynav3'}">				
					<a href="#" onclick="javascript:doPost('/stats/list.do', '3', '30');" onmouseover="javascript:qiehuan(3);" onmouseout="javascript:qiehuanOut(3);" id="mynav3"  class="nav_off" style='cursor:hand;'><span>통계</span></a>
				</c:if>						
			</li>
		</ul>
	</div>
	<!--메인메뉴 끝-->
	<!--서브메뉴 시작-->
	<div id="menu_con">
		<div id="qh_con0" style="display: none">
			<ul>
				<li><a href="#" onclick="javascript:doPost('/myWork/standbyList.do', '0', '00');" id="mycon00"><span>대기</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/myWork/returnList.do', '0', '01');" id="mycon01"><span>반려</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/myWork/completeList.do', '0', '02');" id="mycon02"><span>완료</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/myPage/messageList.do', '0', '03');" id="mycon03"><span>나의 보관함</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/myPage/info.do', '0', '04');" id="mycon04"><span>나의 정보관리</span></a></li>
			</ul>
		</div>
		<div id="qh_con1">
			<ul style="display:none">
				<li><a href="#" onclick="javascript:doPost('/reservation/list.do', '1', '10');" id="mycon10"><span>사전예약</span></a></li>
			</ul>		
		</div>
		<div id="qh_con2" style="display: none; margin-left: 250px">
			<ul>
				<li><a href="#" onclick="javascript:doPost('/message/msgRegForm.do', '2', '20');" id="mycon20"><span>공지/홍보</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/message/autoMsgRegForm.do', '2', '21');" id="mycon21"><span>자동안내</span></a></li>
			</ul>
		</div>
		<div id="qh_con3">
			<ul style="display:none">
				<li><a href="#" onclick="javascript:doPost('/stats/list.do', '3', '30');" id="mycon10"><span>통계</span></a></li>
			</ul>		
		</div>
	</div>
	<!--서브메뉴 끝-->
</div>
