<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
/*
 * Init
 */
$(document).ready(function(){
	//Init Focus
	$('#emaib').focus();
	
	//
	$("#userpw").keypress(function(event) {
		if(event.keyCode == 13) {
			$('#sendEmail').click();
			return false;
		}
	});
	
	/*
	 *
	 */
	$('#sendEmail').click(function(){
		var data = $("#sendEmailFrm").serialize();
		if(showValidate('sendEmailFrm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
			$.ajax({
				url: "${CONF['omp.common.url-prefix.https.dev']}" + env.contextPath + '/mypage/ajaxSendEmail.omp',
				dataType: 'json',
				type	: "POST",
				data 	: {'usrPw' : $('#userpw').val(), 'emailAddr' : $('#emaib').val()},
				async	: false,		
				cache	: false,	
				success: function(json){
					if(json.result == 'SUCCESS'){
						alert(json.sendEmail + "<gm:string value='jsp.member.mypage.msg.email01'/>");
						location.href= "${CONF['omp.common.url-prefix.http.dev']}" + env.contextPath + "/logout/logout.omp";
					}else if(json.result == 'FAIL'){
						alert("<gm:string value='jsp.member.mypage.msg.pw01'/>");
					}else if(json.result == 'useEamil'){
						alert("<gm:string value='jsp.user.pop.popupReEmailCertification.msg.emailChk02'/>");
					}
				}
			});
		}
	});
});
</script>
<!-- Content Area S -->
<h2 class="hide">Contents Area</h2>
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 會員中心 &gt; <span>變更電子郵件</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title02.gif'/>" alt="變更電子郵件" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		<div class="mpbox bgimg09">
		<form action="" method="post" name="sendEmailFrm">
			<div class="txttype07">
				<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_txt02.gif'/>" alt="請輸入您的新電子郵件地址!" /></p>
				<p class="pad_b10">請輸入新電子郵件地址和已註冊帳號之密碼後點選[送出]! </p>
				<dl class="dlist02">
					<dt><label for="emaia">舊電子郵件地址</label></dt>
					<dd><input type="text" id="emaia" name="emaia" value="<g:tagAttb value="${profileInfo.emailAddr}"/>" class="w166" /></dd>
				</dl>
				<dl class="dlist02">
					<dt><label for="emaib">新電子郵件地址</label></dt>
					<dd><input type="text" id="emaib" name="profileInfo.emailAddr" class="w166"
						v:required='trim' m:required="<gm:string value='jsp.member.mypage.msg.email02'/>"
						v:email m:email="<gm:string value='jsp.member.mypage.msg.email03'/>"/></dd>
				</dl>
				<dl class="dlist02 mar_b25">
					<dt><label for="userpw">密碼</label></dt>
					<dd><input type="password" id="userpw" name="usrPw" maxlength="16" class="w166"
						v:required='trim' m:required="<gm:string value='jsp.member.mypage.msg.pw02'/>"/></dd>
				</dl>
				<p class="cb"><span class="pbult01">注意</span><br />
				<span class="pad_l15">&middot; 若您有變更電子郵件地址, 我們將把有關開發商專區資訊和結算資訊傳送至新電子郵件地址!</span></p>
			</div>
		</form>
		</div>
		<div class="btnarea mar_t30">
			<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_email.gif'/>" alt="送出" id="sendEmail" /></a><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="取消" /></a>
		</div>
	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->