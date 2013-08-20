<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- Title -->
<h3 class="tit">나의 정보관리</h3>

<!-- Help -->
<div class="help">
	나의 개인정보를 관리하는 화면입니다.
	<span><a href="#" class="btn_help"><strong>화면도움말</strong></a></span>
</div>

<!-- Img Area -->
<div style="float:left; width:465px;"><img src="/resource/images/img_myinfo.jpg" alt="도움말이미지" /></div>

<!-- Table Area -->
<div style="float:right; width:500px;">
	<table summary="리스트" class="WriteTable">
		<caption>리스트</caption>
		<tbody>
			<tr>
				<th scope="row" class="l">부서명</th>
				<td class="l">${viewInfo.userRlvnDeptNm}</td>
			</tr>
			<tr>
				<th scope="row" class="l">권한</th>
				<td class="l">${viewInfo.userAutVal}</td>
			</tr>
			<tr>
				<th scope="row" class="l">사용자명</th>
				<td class="l">${viewInfo.userNm}</td>
			</tr>
			<tr>
				<th scope="row" class="l">사용자ID</th>
				<td class="l">${viewInfo.userId}</td>
			</tr>
			<tr>
				<th scope="row" class="l">비밀번호</th>
				<td class="l">${viewInfo.userPwd}</td>
			</tr>
			<tr>
				<th scope="row" class="l">전화번호</th>
				<td class="l">${viewInfo.genlTelNo}</td>
			</tr>
			<tr>
				<th scope="row" class="l">휴대폰번호</th>
				<td class="l">${viewInfo.mphonNo}</td>
			</tr>
			<tr>
				<th scope="row" class="l">이메일</th>
				<td class="l">${viewInfo.emailAdr}</td>
			</tr>
		</tbody>
	</table>

	<!-- Btn -->
	<p class="btnR" style=" padding-bottom:30px">
		<a href="#" onclick="javascript:doPostDetail('','/user/userInfoModify.do');" class="btn_red"><strong>수정</strong></a>
		<a href="#" class="btn_red"><strong>확인</strong></a>
	</p>
</div>


