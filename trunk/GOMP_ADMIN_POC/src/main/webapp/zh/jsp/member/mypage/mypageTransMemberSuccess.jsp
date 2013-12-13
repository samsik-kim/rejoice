<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 會員中心 &gt; <span>會員轉換</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title03.gif'/>" alt="會員轉換" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
              
		<h4 class="h43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle10.gif'/>" alt="會員轉換信箱認證完畢" /></h4>
		<ul class="bult03 mar_b8">
			<li>您的信箱認證順利完成! </li>
		</ul>
		<div class="mpbox bgimg15">
			<div class="txttype14">
				<p class="pad_t7"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_txt05.gif'/>" alt="會員轉換信箱認證順利成功!" /></p>
				<dl class="dlist06">
					<dt>轉換前</dt>
					<dd>個人戶</dd>
				</dl>
				<dl class="dlist06 pad_b30">
					<dt>轉換後</dt>
					<dd>公司戶</dd>
				</dl>
				<p class="txtcolor13 cb">您屬於個人戶, 現在只能販售免費產品. 若要上傳且販售付費產品, 需填寫銀行資料.<br />為了販售付費產品, 您確定要填寫銀行資料嗎?</p>
			</div>
		</div>
		<div class="btnarea mar_t30">
			<a href="<c:url value='/mypage/mypageIntro.omp?forwardAction=CALCULATE'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif'/>" alt="確定" /></a>
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="取消" /></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>