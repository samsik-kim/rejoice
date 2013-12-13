<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
/*
 * init
 */
$(document).ready( function() {
	$('#userpw').focus();
	$( "#userpw" ).keydown( function( event )	{
		if ( event.keyCode == 13 )	{
			mypageCertification();
		}
	} );
});

function mypageCertification() {
	if(showValidate('frm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
		var usrPw = $("#userpw").val();
		$.post("${CONF['omp.common.url-prefix.https.dev']}" + env.contextPath + "/mypage/mypageCertificationAjax.omp", {"usrPw":usrPw, "param":"PROFILE"}, function(data) {
			resultCertification(data);
		}, "json");
	}
}

function resultCertification(data) {
	var result = data.result;
	var stat = data.stat;
	if(result == "SESS_OUT") {
		location.href=env.contextPath + "/login/loginMain.omp";
	}
	else if(result == "SUCCESS") {
		$('#isProfile').val(data.isProfile);
		if("<g:html value="${forwardAction}"/>" == "PROFILE") {
			$('#introFrm').attr('action', env.contextPath + "/mypage/mypageProfileView.omp");
		}
		else if("<g:html value="${forwardAction}"/>" == "CALCULATE") {
			$('#introFrm').attr('action', env.contextPath + "/mypage/mypageCalculateInfoView.omp");
		}
		$('#introFrm').submit();
	}
	else if(result == "FAIL") {
		alert("<gm:string value='jsp.member.mypage.msg.pw04'/>");
	$('#userpw').val('');
	$('#userpw').focus();
	}
	else if(result == "BLOCK") {
		alert(stat);
	}
}
</script>
<form name="introFrm" id="introFrm" method="post">
	<input type="hidden" name="isProfile" id="isProfile"/>
</form>
<div id="contents_area">
	<div id="ctitle_area">
		<p class="history">Home &gt; <gm:html value='마이페이지'/> &gt; <span><gm:html value='회원정보변경'/></span></p>
		<h3><img alt="회원정보변경" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title01.gif'/>"></h3>
	</div>
	
	<div id="contents">
		<div class="mpbox bgimg10">
		<form name="frm" method="post">
			<div class="txttype06">
				<p><img alt="회원님의 정보를 안전하게 보호하기 위해 비밀번호를 다시 한번 입력해주세요." src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_txt03.gif'/>"></p>	
				<dl class="dlist03">
					<dt><label for="userid">아이디</label></dt>
					<dd><input type="text" class="w128" value="<g:tagAttb value="${MEMBER_SESSION.mbrId}"/>" name="userid" id="userid" readonly></dd>
				</dl>
				<dl class="dlist03 pad_b15">
					<dt><label for="userpw">비밀번호</label></dt>
					<dd><input type="password" class="w128" value="" name="userpw" id="userpw" 
						v:regexp="[a-zA-Z0-9\!\@\#\$\%\^\&\*\~]{6,16}" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.pw03'/>"
						v:required m:required="<gm:tagAttb value='jsp.member.mypage.msg.pw02'/>" /></dd>
				</dl>
			</div>
		</form>
		</div>
		<div class="btnarea mar_t30">
			<a href="javascript:mypageCertification();"><img alt="확인" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif'/>"></a>
		</div>

	</div>
</div>