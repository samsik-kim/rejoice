<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; <gm:html value='마이페이지'/> &gt; <span><gm:html value='회원전환'/></span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title03.gif'/>" alt="회원전환" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
              
		<h4 class="h43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle10.gif'/>" alt="회원전환 이메일 인증 완료" /></h4>
		<ul class="bult03 mar_b8">
			<li>이메일 인증이 정상적으로 완료되었습니다.</li>
		</ul>
		<div class="mpbox bgimg15">
			<div class="txttype14">
				<p class="pad_t7"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_txt05.gif'/>" alt="회원전환 이메일 인증이 정상적으로 완료되었습니다." /></p>
				<dl class="dlist06">
					<dt>변경 전</dt>
					<dd>개인회원</dd>
				</dl>
				<dl class="dlist06 pad_b30">
					<dt>변경 후</dt>
					<dd>회사회원</dd>
				</dl>
				<p class="txtcolor13 cb">회원님은 현재 무료상품만 판매가 가능합니다. 유료상품의 판매를 위해서는 정산정보가 필요합니다.<br />유료상품 판매를 위한 정산정보를 지금 등록 하시겠습니까?</p>
			</div>
		</div>
		<div class="btnarea mar_t30">
			<a href="<c:url value='/mypage/mypageIntro.omp?forwardAction=CALCULATE'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif'/>" alt="확인" /></a>
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="취소" /></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>