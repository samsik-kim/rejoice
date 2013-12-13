<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
</script>

<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; <gm:html value='마이페이지'/> &gt; <span><gm:html value='회원전환'/></span></p>
		<h3><img alt="회원전환" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_title03.gif"/>"></h3>
	</div>
	<!-- //Title Area E -->
	
	<c:if test="${profileInfo.mbrCatCd eq 'US000205'}">
	<!-- CONTENT TABLE S-->
	<div id="contents">
              
		<h4 class="h43"><img alt="회원전환 신청 완료" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle09.gif"/>"></h4>
		<ul class="bult03 mar_b8">
			<li>회원전환 신청 후 이메일 인증을 하셔야 회원 전환이 완료됩니다.</li>
			<li>회원전환이 완료되기 전까지는 전환 전 개인 회원으로 유지됩니다.</li>
		</ul>
		<div class="mpbox bgimg14">
			<div class="txttype12">
				<p class="txttype13"><span class="txtcolor03"><g:html value="${MEMBER_SESSION.mbrId}"/>/<g:html value="${transferInfo.compNm}"/></span>님의 이메일 주소 <span class="txtcolor07"><g:html value="${profileInfo.emailAddr}"/></span>로<br>인증 확인 이메일이 발송 되었습니다.</p>
				<p class="pad_b30">이메일 인증을 하셔야 회원 가입이 완료 됩니다.</p>
				<dl class="dlist06">
					<dt><gm:html value='변경 전'/></dt>
					<dd><gm:html value='개인회원'/></dd>
				</dl>
				<dl class="dlist06 pad_b15">
					<dt><gm:html value='변경 후'/></dt>
					<dd><gm:html value='회사회원'/></dd>
				</dl>
			</div>
		</div>
		<div class="btnarea mar_t30">
			<a href="<c:url value='/main/main.omp'/>"><img alt="확인" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif"/>"></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->
	</c:if>
	
	<c:if test="${profileInfo.mbrCatCd eq 'US000206'}">
	<!-- CONTENT TABLE S-->
	<div id="contents">
                
		<h4 class="h43"><img alt="회원전환 신청 완료" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle09.gif"/>"></h4>
		<ul class="bult03 mar_b8">
			<li>회원전환 신청 후 운영자의 승인 단계를 거쳐 회원전환이 완료됩니다.</li>
			<li>입력하신 정보에 문제가 있는 경우 운영자의 승인거절(거절사유 포함) 이루어집니다.</li>
			<li>회원전환이 완료 되기 전까지는 변경 전의 회원으로 유지됩니다.</li>
		</ul>
		<div class="mpbox bgimg16">
			<div class="txttype11">
				<p class="txttype13"><span class="txtcolor03"><g:html value="${MEMBER_SESSION.mbrId}"/>/<g:html value="${transferInfo.compNm}"/></span>님의 회원전환 신청이 완료되었습니다.</p>						
				<dl class="dlist06">
					<dt><gm:html value='변경 전'/></dt>
					<dd><gm:html value='유료개인회원'/></dd>
				</dl>
				<dl class="dlist06 pad_b10">
					<dt><gm:html value='변경 후'/></dt>
					<dd><gm:html value='유료회사회원'/> (<gc:html code="${transferInfo.bizCatCd}"/>)</dd>
				</dl>
			</div>
		</div>
		<div class="btnarea mar_t30">
			<a href="<c:url value='/main/main.omp'/>"><img alt="확인" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif"/>"></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->
	</c:if>

</div>