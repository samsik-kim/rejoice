<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ taglib prefix="mimo" uri="/WEB-INF/tld/mimo.tld"%>
<mimo:userinfo var="user_session" />

<!-- Left Area -->
<div class="contents_left" style="width:190px;">
	<div id="main_left">
	<!-- Table Area -->
		<div class="main_left_user" style="width:190px; height:115px; background:url(/resource/images/main_left_top_bg.gif) no-repeat">
			<h1 style=" position:relative; top:8px;  color:#fff; font-weight:bold; text-align:center; ">${user_session.authority}</h1>
			<p style=" position:relative; top:30px;  height:30px; margin:0 20px; line-height:18px">안녕하세요! <strong>${user_session.userId}님</strong><br />${user_session.authority}등급 입니다.<br />행복한 하루 되세요!</p>
		</div>
		<!-- List Area -->
		<h2>검토현황</h2>
			<ul>
				<li>대 기<span>00 건</span></li>
				<li>반 려<span>00 건</span></li>
				<li>완 료<span>00 건</span></li>
			</ul>
		<h2>작업현황</h2>
			<ul>
				<li>대 기<span>000000 건</span></li>
				<li>반 려<span>00 건</span></li>
				<li>완 료<span>00 건</span></li>
			</ul>
		<h2>공지사항</h2>
			<ul>
				<li><a href="#">대기</a></li>
				<li><a href="#">반려</a></li>
				<li><a href="#">완료</a></li>
			</ul>
	</div>
</div>

<!-- ContentsRight Area -->
<div class="contents_right" style="width:720px; vertical-align:top">
	<!-- Title -->
	<h3 class="tit">검토 처리 내역</h3>
	<!-- Tab -->
	<h3 class="tabR"><a href="#"><img src="/resource/images/mrts_main_tab01.gif" alt="대기내역" /></a><a href="#"><img src="/resource/images/mrts_main_14_ov.gif" alt="반려처리한 내역" /></a></h3>
	<!-- Table Area -->
	<table summary="리스트" class="ListTable">
		<caption>리스트</caption>
		<colgroup>
			<col width="*" />
			<col width="15%" />
			<col width="15%" />
			<col width="15%" />
		</colgroup>
		<thead>
			<tr>
				<th style="width:415px">제목</th>
				<th style="width:100px">부서</th>
				<th style="width:100px">사용자</th>
				<th style="width:100px">요청일시</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="l"><a href="#">aaa</a></td>
				<td>고객만족기획팀</td>
				<td>고민영</td>
				<td>2013-00-00</td>
			</tr>
			<tr>
				<td class="l"><a href="#">aaa</a></td>
				<td>고객만족기획팀</td>
				<td>고민영</td>
				<td>2013-00-00</td>
			</tr>
		</tbody>
		<tfoot>
		<tr>
			<td colspan="4">
				<a href="#"><img src="/resource/images/mrts_main_27.gif" alt="더보기" /></a>
			</td>
		</tr>
		</tfoot>
	</table>
	
	<!-- Title -->
	<h3 class="tit"  style="margin-top:50px">나의 작업 내역</h3>
	<!-- Tab -->
	<h3 class="tabR"><a href="#"><img src="/resource/images/mrts_main_tab01.gif" alt="대기내역" /></a><a href="#"><img src="/resource/images/mrts_main_35_ov.gif" alt="반려된 내역" /></a></h3>
	<!-- Table Area -->
	<table summary="리스트" class="ListTable">
		<caption>리스트</caption>
		<colgroup>
			<col width="*" />
			<col width="15%" />
		 </colgroup>
		 <thead>
			<tr>
				<th style="width:617px">제목</th>
				<th style="width:100px">요청일시</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td class="l"><a href="#">aaa</a></td>
				<td>2013-00-00</td>
			</tr>
			<tr>
				<td class="l"><a href="#">aaa</a></td>
				<td>2013-00-00</td>
			</tr>
		</tbody>
		<tfoot>
			<tr>
				<td colspan="2">
					<a href="#"><img src="/resource/images/mrts_main_27.gif" alt="더보기" /></a>
				</td>
			</tr>
		</tfoot>
	</table>
</div>