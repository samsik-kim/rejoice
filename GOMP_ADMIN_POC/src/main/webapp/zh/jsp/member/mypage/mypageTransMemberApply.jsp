<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
</script>

<div id="contents_area">
	<c:if test="${profileInfo.mbrCatCd eq 'US000205'}">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 會員中心 &gt; <span>會員轉換</span></p>
		<h3><img alt="會員轉換" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_title03.gif"/>"></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
              
		<h4 class="h43"><img alt="會員轉換申請完畢" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle09.gif"/>"></h4>
		<ul class="bult03 mar_b8">
			<li>申請會員轉換後, 進行信箱認證才可完成會員轉換流程.</li>
			<li>在會員轉換完畢以前, 您仍屬個人戶.</li>
		</ul>
		<div class="mpbox bgimg14">
			<div class="txttype12">
				<p class="txttype13">親愛的 <span class="txtcolor03"><g:html value="${MEMBER_SESSION.mbrId}"/> / <g:html value="${transferInfo.compNm}"/><br />我們已將認證信傳送至&nbsp;<span class="txtcolor03 uline"><g:html value="${profileInfo.emailAddr}"/></span></p>
				<p class="pad_b30">信箱認證完畢, 會員轉換流程才可完成! </p>
				<dl class="dlist06">
					<dt>轉換前</dt>
					<dd>個人戶</dd>
				</dl>
				<dl class="dlist06 pad_b15">
					<dt>轉換後</dt>
					<dd>公司戶</dd>
				</dl>
			</div>
		</div>
		<div class="btnarea mar_t30">
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img alt="確定" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif"/>"></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->
	</c:if>
	
	<c:if test="${profileInfo.mbrCatCd eq 'US000206'}">
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 會員中心 &gt; <span>會員轉換</span></p>
		<h3><img alt="會員轉換" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_title03.gif"/>"></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
                
		<h4 class="h43"><img alt="會員轉換申請完畢" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle09.gif"/>"></h4>
		<ul class="bult03 mar_b8">
			<li>申請會員轉換後, 應獲得管理者審核.</li>
			<li>若您所填寫之銀行資料有誤, 管理者會拒絕(告知拒絕原因)會員轉換.</li>
			<li>在會員轉換完畢以前, 您仍屬個人戶</li>
		</ul>
		<div class="mpbox bgimg16">
			<div class="txttype11">
				<p class="txttype13"><span class="txtcolor03"><g:html value="${MEMBER_SESSION.mbrId}"/> / <g:html value="${transferInfo.compNm}"/></span>會員轉換申請完畢!</p>						
				<dl class="dlist06">
					<dt>轉換前</dt>
					<dd>付費個人戶</dd>
				</dl>
				<dl class="dlist06 pad_b10">
					<dt>轉換後</dt>
					<dd>付費公司戶(<gc:html code="${transferInfo.bizCatCd}"/>)</dd>
				</dl>
			</div>
		</div>
		<div class="btnarea mar_t30">
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img alt="確定" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif"/>"></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->
	</c:if>

</div>