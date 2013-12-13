<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 
<div class="layerwrap">
 
	<div id="pop_area01">
 	<form id="verifyReqFrm" name="verifyReqFrm" method="post">
 	<input type="hidden" name="content.cid" value="${content.cid}" />
 	<input type="hidden" name="tabGbn" value="${tabGbn}" />
		<h2><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/pm_title_04.gif' />" alt="상품검증요청" /></h2>
		<p class="pop_txt4 txt_bold"><span class="txtcolor06"><g:html value="${content.prodNm}"/></span>을(를) 검증요청하시겠습니까?</p>
		<div class="appbox">
			<ul>
				<li>검증진행 중에는 상품 정보 수정이 불가능하며 상품정보에 대한 운영자 검증이 완료되기 전까지는 '검증요청취소'가 가능합니다.</li>
			</ul>
		</div>
		<div class="pop_btn">
			<a href="javascript:verifyReq('${content.cid}', '${tabGbn}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_confirm.gif' />" alt="확인" id="verifyReqBtn" /></a>
			<a href="javascript:closePopupLayer('popVerifyReq');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_cancle.gif' />" alt="취소" /></a>
		</div>
		<p class="pop_close"><a href="javascript:closePopupLayer('popVerifyReq');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif' />" alt="닫기" /></a></p>
 	</form>
	</div>
</div>
