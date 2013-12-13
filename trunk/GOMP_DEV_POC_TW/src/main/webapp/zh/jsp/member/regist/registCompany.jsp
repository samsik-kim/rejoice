<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
/*
 * Init
 */
$(document).ready( function() {
	$('#mbrNm').focus();
	$("#regist").click(function() {
		if(showValidate('registCompany', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
			var data = $("#registCompany").serialize();
			$.ajax({
				url: env.contextPath + '/member/ajaxRegistCheck.omp',
				dataType: 'json',
				type	: "POST",
				data 	: data,
				async	: true,		
				cache	: false,	
				success: function(json){
					if(json.data == 0){
						alert("<gm:string value='jsp.member.regist.registCompany.msg.cmno01'/>");
				 		$('#registCompany').attr('action', env.contextPath + '/member/registAgreement.omp');
				 		$('#registCompany').submit();
					}else if(json.data == 1) {
						alert("<gm:string value='jsp.member.regist.regist.msg.com01'/>");
						return false;
					}
				}
			});
		}
	});
});
</script>
<!-- Content Area S -->
<div id="contents_area" class="ut_area">
	
	<!-- Title Area S -->
	<div id="ctitle_area">
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_title01.gif'/>" alt="會員註冊" /></h3>
	</div>
	<!-- //Title Area E -->
	<!-- CONTENT TABLE S-->
	<div id="utcontents">
		<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h4_02.gif'/>" alt="確認註冊與否 - 請確認您是否已加入開發商專區會員!" /></h4>
		<!-- Tab_menu S -->
		<div class="tab01 mar_b22">
			<ul>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab01_01.gif'/>" alt="選擇會員類別" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab02_on.gif'/>" alt="確認註冊與否" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab03.gif'/>" alt="同意會員條款" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab04.gif'/>" alt="填寫會員資料" /></li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab05.gif'/>" alt="會員註冊完成" /></li>
			</ul>
		</div>
		<!-- //Tab_menu E -->
	<form name="registCompany" id="registCompany" method="post">		
	  <div class="utbox">
		<p class="pbult02 w233">
			<label for="name">公司名</label>
			&nbsp;
			<input type="text" id="mbrNm" name="member.mbrNm" class="w128" v:required='trim' m:required="<gm:tagAttb value='jsp.member.regist.registCompany.msg.cmnm01'/>"
			v:checkspecial  m:checkspecial="<gm:tagAttb value='jsp.member.regist.regist.msg.com02'/>"
			v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.regist.registCompany.msg.cmnm02'/>" />
		</p>
		<p class="pbult02">
			<label for="comnum">統一編號</label> &nbsp;
			<input type="text" id="psRegNo" name="member.psRegNo" class="w168" v:required='trim' m:required="<gm:tagAttb value='jsp.member.regist.registCompany.msg.cmno02'/>"
			v:regexp="[\d]{8}" m:regexp="<gm:tagAttb value='jsp.member.regist.registCompany.msg.cmno03'/>" />
		</p>
		</div>
		<input type="hidden" value="<g:tagAttb value='${member.mbrClsCd}'/>" name="member.mbrClsCd"/>
	</form>
		<div class="btnarea mar_t30">
			<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm1.gif'/>" id="regist" alt="確認" />
		</div>
	</div>
	<!-- //CONTENT TABLE E-->
</div>   
<!-- Content Area E -->