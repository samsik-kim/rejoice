<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!-- Content Area S -->
<div id="contents_area" class="ut_area">

	<!-- Title Area S -->
	<div id="ctitle_area">
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_title01.gif'/>" alt="會員註冊" /></h3>
	</div>
	<!-- //Title Area E -->

	<!-- CONTENT TABLE S-->
	<div id="utcontents">
	<form method="post" id="registAgree" name="registAgree" action="<c:url value='/member/registJoinForm.omp'/>">
		<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h4_03.gif'/>" alt="同意會員條款 - 請仔細閱讀後勾選同意, 否則無法進行會員註冊." /></h4>
		<!-- Tab_menu S -->
		<div class="tab01 mar_b22">
			<ul>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab01.gif'/>" alt="選擇會員類別" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab02_01.gif'/>" alt="確認註冊與否" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab03_on.gif'/>" alt="同意會員條款" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab04.gif'/>" alt="填寫會員資料" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab05.gif'/>" alt="會員註冊完成" /></li>
			</ul>
		</div>
		<!-- //Tab_menu E -->
		<h5 class="h51"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h5_01.gif'/>" alt="App mart 開發商專區會員條款" /></h5>
		<div class="agreebox">
			<p class="pad_b30">XXXX股份有限公司(以下稱本公司)將依照以下條款之約定(以下稱本條款)，提供您各項資訊服務。為保障您的權益，請於註冊前
詳細閱讀之。您可以透過網際網路取得用戶密碼，您與本公司均同意此等方式是在考量您的便利性下，基於雙方的誠信與善意而
完成。因此，當您從網際網路的視窗點選「同意」，或實際開始使用本公司之服務時，均視為您已詳細閱讀並同意遵守本條款。
且本公司得於任何時候修改本條款之內容，並透過網際網路在本公司網站上公告，由於使用人數眾多，本公司並不會再另行通知，
故您在此認知並表示同意將會隨時注意並自行上網查詢條款內容是否有所更動及其更動之內容…….</p>
		</div>
		<p class="pad_t7 txtcolor05"><input type="checkbox" name="useagree" id="useagree" v:mustcheck="1" m:mustcheck="<gm:string value='이용약관에 동의해주세요.'/>"/> <label for="useagree">我同意條款內容</label></p>
		<div class="btnarea mar_t30">
			<a shape="hover"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm1.gif'/>" id="agree" alt="確認" style="cursor: pointer;"/></a>
		</div>
		<input type="hidden" value="<g:tagAttb value='${member.psRegNo}'/>" id="psRegNo" name="member.psRegNo" />
		<input type="hidden" value="<g:tagAttb value='${member.mbrNm}'/>" id="mbrNm" name="member.mbrNm" />
		<input type="hidden" value="<g:tagAttb value='${member.mbrClsCd}'/>" name="member.mbrClsCd" id="mbrClsCd"/>
	</form>
	</div>
	<!-- //CONTENT TABLE E-->
</div>
<!-- //Content Area E -->


<script type="text/javascript">
/*
 * Init 
 */
$(document).ready( function() {
	$('#agree').click(function(){
		if(showValidate("registAgree", 'default', "<gm:string value='jsp.common.msg.title02'/>")){
			$('#registAgree').submit();
		}
	});
});
</script>