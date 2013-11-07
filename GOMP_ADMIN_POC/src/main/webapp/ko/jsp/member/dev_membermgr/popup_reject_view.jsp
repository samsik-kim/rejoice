<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 

<title>전환거절 사유 보기</title>

	<div id="popup_area_440">
		<h1>전환거절 사유 보기</h1>
		<p class="mb05"><strong class="c_06f"><g:html value="${devMember.mbrid}"/></strong> 님의 전환신청에 따른 거절 사유입니다.</p>
		<p class="box01"><g:html value="${devMember.rejreason}"/></p>
		<div class="pop_btn" >
			<a class="btn_s" href="javascript:self.close();"><strong>확인</strong></a>
		</div>
	</div>