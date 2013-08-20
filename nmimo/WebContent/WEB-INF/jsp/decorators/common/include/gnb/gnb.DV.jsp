<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mimo" uri="/WEB-INF/tld/mimo.tld"%>
<mimo:userinfo var="user_session" />

<!-- Top Logo & UserMenu -->
<div class="top">
	<!-- Logo -->
	<h1 class="topLogo">
		<a href="#" onclick="javascript:doPost('/nmimo/main.do', '9', '99');"><img src="<c:url value="/resource/images/top_logo.gif"/>" alt="Olleh KT MIMO 메시지 통합관리 시스템" /></a>
	</h1>

	<!-- UserMenu -->
	<div class="userMenu">
		<p>DV${user_session.userId}님 로그인 중</p>
		<ul>
			<li><a href="/notice/list.do"><img src="<c:url value="/resource/images/top_btn_noti.png"/>" alt="공지사항" /></a></li>
			<li><a href="/config/preferences.do"><img src="<c:url value="/resource/images/top_btn_admin.png"/>" alt="환경설정" /></a></li>
			<li><a href="/user/logout.do"><img src="<c:url value="/resource/images/top_btn_logout.png"/>" alt="로그아웃" /></a></li>
		</ul>
	</div>
</div>


<h2 class="hidden">주메뉴</h2>

<div class="gnb">
	<!--메인메뉴-->
	<div>
		<ul id="nav">
			<li>
				<c:if test="${menuId eq 'mynav0'}">
<!-- 					<a href="/myWork/standbyList.do?menuId=0&subMenuId=00" onmouseover="javascript:qiehuan(0);" id="mynav0" class="nav_on" style='cursor:hand;'><span>나의작업</span></a> -->
					<a href="#" onclick="javascript:doPost('/myWork/standbyList.do', '0', '00');" onmouseover="javascript:qiehuan(0);" id="mynav0" class="nav_on" style='cursor:hand;'><span>나의작업</span></a>
				</c:if>
				<c:if test="${menuId ne 'mynav0'}">
					<a href="#" onclick="javascript:doPost('/myWork/standbyList.do', '0', '00');" onmouseover="javascript:qiehuan(0);" onmouseout="javascript:qiehuanOut(0);"  id="mynav0" class="nav_off" style='cursor:hand;'><span>나의작업</span></a>
				</c:if>
			</li>
			<li>
				<c:if test="${menuId eq 'mynav1'}">
					<a href="#" onclick="javascript:doPost('/user/list.do', '1', '10');" onmouseover="javascript:qiehuan(1);" id="mynav1" class="nav_on" style='cursor:hand;'><span>사용자관리</span></a>
				</c:if>
				<c:if test="${menuId ne 'mynav1'}">
					<a href="#" onclick="javascript:doPost('/user/list.do', '1', '10');" onmouseover="javascript:qiehuan(1);" onmouseout="javascript:qiehuanOut(1);" id="mynav1" class="nav_off" style='cursor:hand;'><span>사용자관리</span></a>
				</c:if>
			</li>
			<li>
				<c:if test="${menuId eq 'mynav2'}">
					<a href="#" onclick="javascript:doPost('/reservation/list.do', '2', '20');"  onmouseover="javascript:qiehuan(2);" id="mynav2" class="nav_on" style='cursor:hand;'><span>사전예약</span></a>
				</c:if>
				<c:if test="${menuId ne 'mynav2'}">
					<a href="#" onclick="javascript:doPost('/reservation/list.do', '2', '20');" onmouseover="javascript:qiehuan(2);" onmouseout="javascript:qiehuanOut(2);" id="mynav2" class="nav_off" style='cursor:hand;'><span>사전예약</span></a>
				</c:if>
			</li>
			<li>
				<c:if test="${menuId eq 'mynav3'}">
					<a href="#" onclick="javascript:doPost('/message/msgRegForm.do', '3', '30');" onmouseover="javascript:qiehuan(3);" id="mynav3" class="nav_on" style='cursor:hand;'><span>발송등록</span></a>
				</c:if>
				<c:if test="${menuId ne 'mynav3'}">
					<a href="#" onclick="javascript:doPost('/message/msgRegForm.do', '3', '30');" onmouseover="javascript:qiehuan(3);" onmouseout="javascript:qiehuanOut(3);" id="mynav3" class="nav_off" style='cursor:hand;'><span>발송등록</span></a>
				</c:if>
			</li>
			<li>
				<c:if test="${menuId eq 'mynav4'}">
					<a href="#" onclick="javascript:doPost('/review/standbyList.do', '4', '40');" onmouseover="javascript:qiehuan(4);" id="mynav4" class="nav_on" style='cursor:hand;'><span>검토</span></a>
				</c:if>						
				<c:if test="${menuId ne 'mynav4'}">
					<a href="#" onclick="javascript:doPost('/review/standbyList.do', '4', '40');" onmouseover="javascript:qiehuan(4);" onmouseout="javascript:qiehuanOut(4);" id="mynav4" class="nav_off" style='cursor:hand;'><span>검토</span></a>
				</c:if>						
			</li>
			<li>
				<c:if test="${menuId eq 'mynav5'}">				
					<a href="#" onclick="javascript:doPost('/stats/list.do', '5', '50');" onmouseover="javascript:qiehuan(5);" id="mynav5" class="nav_on" style='cursor:hand;'><span>통계</span></a>
				</c:if>
				<c:if test="${menuId ne 'mynav5'}">				
					<a href="#" onclick="javascript:doPost('/stats/list.do', '5', '50');" onmouseover="javascript:qiehuan(5);" onmouseout="javascript:qiehuanOut(5);" id="mynav5"  class="nav_off" style='cursor:hand;'><span>통계</span></a>
				</c:if>						
			</li>
			<li>
				<c:if test="${menuId eq 'mynav6'}">				
					<a href="#" onclick="javascript:doPost('/kbn/msgRequestList.do', '6', '60');" onmouseover="javascript:qiehuan(6);" id="mynav6" class="nav_on" style='cursor:hand;'><span>KBN관리</span></a>
				</c:if>
				<c:if test="${menuId ne 'mynav6'}">				
					<a href="#" onclick="javascript:doPost('/kbn/msgRequestList.do', '6', '60');" onmouseover="javascript:qiehuan(6);" onmouseout="javascript:qiehuanOut(6);" id="mynav6" class="nav_off" style='cursor:hand;'><span>KBN관리</span></a>
				</c:if>						
			</li>
		</ul>
	</div>
	<!--메인메뉴 끝-->
	<!--서브메뉴 시작-->
	<div id="menu_con">
		<div id="qh_con0" style="display: none">
			<ul>
				<li><a href="#" onclick="javascript:doPost('/myWork/standbyList.do', '0', '00');"  id="mycon00"><span>대기</span></a></li>
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
		<div id="qh_con1" style="display: none; margin-left: 140px">
			<ul>
				<li><a href="#" onclick="javascript:doPost('/user/list.do', '1', '10');" id="mycon10"><span>사용자 관리</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/user/confirmList.do', '1', '11');" id="mycon11"><span>승인자 관리</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/user/etcList.do', '1', '12');" id="mycon12"><span>기타 사용자 관리</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/user/userInfo.do', '1', '13');" id="mycon13"><span>나의 정보</span></a></li>
			</ul>
		</div>
		<div id="qh_con2">
			<ul style="display:none">
				<li><a href="#" onclick="javascript:doPost('/user/list.do', '2', '20');" id="mycon20"><span>사전예약</span></a></li>
			</ul>
		</div>
		<div id="qh_con3" style="display: none; margin-left: 420px">
			<ul>
				<li><a href="#" onclick="javascript:doPost('/message/msgRegForm.do', '3', '30');" id="mycon30"><span>공지/홍보</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/message/autoMsgRegForm.do', '3', '31');" id="mycon31"><span>자동안내</span></a></li>
			</ul>
		</div>
		<div id="qh_con4" style="display: none; margin-left: 430px">
			<ul>
				<li><a href="#" onclick="javascript:doPost('/review/standbyList.do', '4', '40');" id="mycon40"><span>대기</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/review/returnList.do', '4', '41');" id="mycon41"><span>반려</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/review/completeList.do', '4', '42');" id="mycon42"><span>완료</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/review/bannerList.do', '4', '43');" id="mycon43"><span>배너 관리</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/review/reservationList.do', '4', '44');" id="mycon44"><span>사전 예약</span></a></li>
			</ul>
		</div>
		<div id="qh_con5">
			<ul style="display:none">
				<li><a href="#" onclick="javascript:doPost('/stats/list.do', '5', '50');" id="mycon20"><span>통계</span></a></li>
			</ul>
		</div>
		<div id="qh_con6" style="display: none;margin-left: 730px">
			<ul>
				<li><a href="#" onclick="javascript:doPost('/kbn/msgRequestList.do', '6', '60');" id="mycon60"><span>SMS/MMS 작업요청</span></a></li>
				<li class=menu_dot>|</li>
				<li><a href="#" onclick="javascript:doPost('/kbn/msgSendList.do', '6', '61');" id="mycon61"><span>전송정보</span></a></li>
			</ul>
		</div>
	</div>
	<!--서브메뉴 끝-->
</div>