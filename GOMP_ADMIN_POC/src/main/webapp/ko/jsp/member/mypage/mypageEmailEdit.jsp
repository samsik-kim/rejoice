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
		<p class="history">Home &gt; <gm:html value='마이페이지'/> &gt; <span><gm:html value='이메일변경'/></span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title02.gif'/>" alt="이메일변경" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		<div class="mpbox bgimg09">
		<form action="" method="post" name="sendEmailFrm">
			<div class="txttype07">
				<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_txt02.gif'/>" alt="변경할 이메일 주소를 입력해 주세요" /></p>
				<p class="pad_b10">변경하실 새 이메일 주소와 비밀번호를 입력한 후 전송 버튼을 클릭하세요.<br />변경사항을 확인하는 링크를 포함한 확인 이메일을 받게 됩니다.</p>
				<dl class="dlist02">
					<dt><label for="emaia">현재 이메일 주소</label></dt>
					<dd><input type="text" id="emaia" name="emaia" value="<g:tagAttb value="${profileInfo.emailAddr}"/>" class="w166" /></dd>
				</dl>
				<dl class="dlist02">
					<dt><label for="emaib">새 이메일 주소</label></dt>
					<dd><input type="text" id="emaib" name="profileInfo.emailAddr" class="w166"
						v:required='trim' m:required="<gm:string value='jsp.member.mypage.msg.email02'/>"
						v:email m:email="<gm:string value='jsp.member.mypage.msg.email03'/>"/></dd>
				</dl>
				<dl class="dlist02 mar_b25">
					<dt><label for="userpw">비밀번호</label></dt>
					<dd><input type="password" id="userpw" name="usrPw" maxlength="16" class="w166"
						v:regexp="[a-zA-Z0-9\!\@\#\$\%\^\&\*\~]{6,16}" m:regexp="<gm:string value='jsp.member.mypage.msg.pw03'/>"
						v:required='trim' m:required="<gm:string value='jsp.member.mypage.msg.pw02'/>"/></dd>
				</dl>
				<p class="txtcolor13 cb">이메일 변경 시 개발자 센터 소식, 정산 정보 등의 소식도 변경된 이메일로<br />발송되므로 이 점 확인 하신 후 이메일 주소를 변경하시기 바랍니다.</p>
			</div>
		</form>
		</div>
		<div class="btnarea mar_t30">
			<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_email.gif'/>" alt="이메일 전송" id="sendEmail" /></a><a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="취소" /></a>
		</div>
	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->