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
		alert(stat);
	}
}
</script>

<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 會員中心 &gt; <span>會員轉換</span></p>
		<h3><img alt="會員轉換" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_title03.gif"/>"></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">

		<h4 class="h43"><img alt="會員轉換須知" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle01.gif"/>"></h4>
		<ul class="bult03 mar_b8">
			<li>個人戶即使轉換為公司戶(含一般營業和小規模營業),  也可使用相同帳號, 也可保持個人銷售業績.</li>
			<li>若您屬於免費會員,  只需申請即可轉換會員身分.</li>
			<li> 您在進行會員轉換流程時, 即使是已填寫 <strong>「銀行資料」</strong>之會員, 確認或變更「銀行資料」重新申請管理者核准後, 通過審核 才可完成會員轉換.</li>
			<li> 您在完成會員轉換後, 不准再轉換為起初所屬的會員類別.</li>
		</ul>
		<div class="mpbox bgimg11">
			<p class="txttype08">若您是公司戶(含小規模營業和一般營業) 請點選!<br />&nbsp;</p>
		</div>
		<div class="btnarea mar_t30">
			<a href="javascript:mypageCheckValidPrcStat();"><img alt="轉換" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_change.gif"/>"></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>