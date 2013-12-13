<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
/*
 * Init
 */
$(document).ready(function(){
	//Init Focus
	$('#businame').focus();
	
	//
	$("#comnum").keypress(function(event) {
		if(event.keyCode == 13) {
			compInfoOnClick();
			return false;
		}
	});
});

/*
 * 
 */
function compInfoOnClick() {
	if(showValidate('frmCompInput', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
		$('#psRegNo').val($('#comnum').val());
		$('#mbrNm').val($('#businame').val());
		var data = $("#registFrm").serialize();
		$.ajax({
			url: env.contextPath + '/member/ajaxRegistCheck.omp',
			dataType: 'json',
			type	: "POST",
			data 	: data,
			async	: false,		
			cache	: false,	
			success: function(json){
				if(json.data == 0){
					alert("<gm:string value='jsp.member.regist.registCompany.msg.cmno01'/>");
			 		$('#frmCompInput').attr('action', env.contextPath + '/mypage/mypageMemberTransInfoView.omp');
			 		$('#frmCompInput').submit();
				}else if(json.data == 1) {
					alert("<gm:string value='jsp.member.regist.regist.msg.com01'/>");
					return false;
				}
			}
		});
	}
}

</script>

<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; <gm:html value='마이페이지'/> &gt; <span><gm:html value='회원전환'/></span></p>
		<h3><img alt="회원전환" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_title03.gif"/>"></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
	<form id="frmCompInput" name="frmCompInput" method="post">
		<h4 class="h43"><img alt="회사 확인" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle02.gif"/>"></h4>
		<ul class="bult03 mar_b8">
			<li>회사명과 통일번호를 입력해 주십시오</li>
		</ul>
		<div class="mpbox bgimg12">
			<div class="txttype09">
				<dl class="dlist04">
					<dt><label for="businame">회사명</label></dt>
					<dd>
						<input type="text" class="w227" name="transferInfo.compNm" id="businame" v:required='trim' m:required="<gm:tagAttb value='jsp.member.regist.registCompany.msg.cmnm01'/>"
						v:checkspecial  m:checkspecial="<gm:tagAttb value='jsp.member.mypage.msg.com01'/>"
						v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.regist.registCompany.msg.cmnm02'/>" />
					</dd>
				</dl>
				<dl class="dlist04 mar_b5">
					<dt><label for="comnum">통일번호</label></dt>
					<dd>
						<input type="text" class="w227" name="transferInfo.psRegNo" id="comnum" v:required='trim' m:required="<gm:tagAttb value='jsp.member.regist.registCompany.msg.cmno02'/>" 
						v:regexp="[\d]{8}" m:regexp="<gm:tagAttb value='jsp.member.regist.registCompany.msg.cmno03'/>" />
					</dd>
				</dl>
			</div>
		</div>
		<div class="btnarea mar_t30">
			<a href="javascript:compInfoOnClick();"><img alt="확인" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif"/>"></a>
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img alt="취소" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif"/>"></a>
		</div>
	</form>
	<form id="registFrm" name="registFrm" method="post">
		<input type="hidden" name="member.psRegNo" id="psRegNo">
		<input type="hidden" name="member.mbrNm" id="mbrNm">
		<input type="hidden" name="member.mbrClsCd" value="US000102">
	</form>
	</div>
	<!-- //CONTENT TABLE E-->

</div>