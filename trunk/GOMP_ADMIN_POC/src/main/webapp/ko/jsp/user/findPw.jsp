<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
/*
 * Init
 */
$(document).ready(function() {
	if("${errMsg}" != "") alert("${errMsg}");
});

/*
 * Find PW Submit 
 */
function findPwSubmit() {
	$("#frm").attr('action', env.contextPath + '/login/findPwResult.omp');
	$("#frm").submit();
}

function keypressEvent(e){
	try {
		if (e.keyCode == 13) {
			e.preventDefault();
			findPwSubmit(); 
		}
	} catch(event) {
		if (e.keyCode == 13) {
			e.returnValue = false;
			findPwSubmit(); 
		}	
	}
}
</script>
		
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 아이디 비밀번호 찾기 <strong>&gt;</strong> <span>비밀번호 찾기</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_title02.gif' />" alt="비밀번호 찾기" /></h3>
	</div>

	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		
		<h4 class="h43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_h4_03.gif' />" alt="아이디로 찾기" /></h4>
		<p class="pbult06">사용자 아이디를 입력하시면 이메일로 비밀번호 재설정 방법을 알려드립니다.</p>
		<form id="frm" name="frm" action="" method="post"" onsubmit="return showValidate('frm', 'default', '<gm:string value="jsp.user.common.msg.errTitle"/>');">
			<div class="utbox mar_b30">	
				<span class="pbult07"><label for="userid">아이디</label></span> &nbsp;
				<input type="text" id="userId" name="userId" class="w205" v:required m:required="<gm:tagAttb value='jsp.user.findPw.msg.id01'/>" 
				v:regexp="[a-z0-9]{8,13}" m:regexp="<gm:tagAttb value='jsp.user.findPw.msg.id02'/>" value="${userId }" onkeypress="javascript:keypressEvent(event);"/>
	
			</div>
		</form>  
			<div class="btnarea mar_t30">
				<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif' />" onclick="javascript:findPwSubmit();" alt="확인" />
			</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>
