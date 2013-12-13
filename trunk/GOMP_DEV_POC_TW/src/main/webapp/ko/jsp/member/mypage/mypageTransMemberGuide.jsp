<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
function mypageCheckValidPrcStat() {
	var usrPw = $("#userpw").val();
	$.post(env.contextPath + "/mypage/mypageCheckValidPrcStatAjax.omp", {"param":"TRANSFER"}, function(data) {
		resultCheckValidPrcStat(data);
	}, "json");
}

function resultCheckValidPrcStat(data) {
	var result = data.result;
	var stat = data.stat;
	
	if(result == "TRUE") {
		location.href=env.contextPath + "/mypage/mypageMemberTransCompView.omp";
	}
	else if(result == "SESS_OUT") {
		location.href=env.contextPath + "/login/loginMain.omp";
	}
	else if(result == "FALSE") {
		alert("<gm:string value='jsp.member.mypage.msg.com04'/>" + stat + "<gm:string value='jsp.member.mypage.msg.com05'/>");
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

		<h4 class="h43"><img alt="회원정보 입력" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle01.gif"/>"></h4>
		<ul class="bult03 mar_b8">
			<li>동일한 아이디와 판매 실적을 유지하면서 회사(일반영업인, 소규모영업인)으로 회원 전환을 하실 수 있습니다.</li>
			<li>기존에 무료회원인 경우, 회원 전환 신청만으로 회원 전환이 가능합니다.</li>
			<li><strong>정산정보</strong>를  입력한 회원의 경우, 회원전환 시 변경되는   정산정보’를 다시 입력하신 후 운영자의 승인과정을 거쳐야<br>최종 회원 전환신청이 완료됩니다.</li>
			<li>회원 전환 후 다시 이전 회원의 상태로 재전환은 불가능합니다.</li>
		</ul>
		<div class="mpbox bgimg11">
			<p class="txttype08">회사(일반영업인, 소규모영업인) 회원인 경우<br>선택해 주세요.</p>
		</div>
		<div class="btnarea mar_t30">
			<a href="javascript:mypageCheckValidPrcStat();"><img alt="전환하기" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_change.gif"/>"></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>