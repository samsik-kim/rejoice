<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<script type="text/javascript">
	$(document).ready(function(){
		$("#usrPw").focus();
		
		$("#usrPw").keypress(function(event) {
			 if(event.keyCode == 13) {
				 $("#ok_btn").click();
			  	
				return false;
			 }
		});	
	});
	
	$(function(){
		$("#ok_btn").click(function(){
			if(showValidate("frm", "default", "<gm:string value='jsp.common.msg.title01'/>")){
				$.ajax({
					async: false,
					cache: false,
					type: "POST",
					dataType: 'json',
					url: env.contextPath + "/mypage/ajaxWithdrawExcute.omp",
					data: {usrPw : $("#usrPw").val()},
					success: function(json) {
						if(json.result == "SUCCESS"){
							//mypageCheckValidPrcStat();	// Member State Check
							location.href=env.contextPath + "/mypage/mypageWithdrawReason.omp";
						}else if(json.result == "FAIL"){
							alert("<gm:string value='jsp.member.mypage.msg.pw01'/>");
							
							return false;
						}
					}
				});
			}
		});
		
	});
	
//	function mypageCheckValidPrcStat() {
//		$.post(env.contextPath + "/mypage/mypageCheckValidPrcStatAjax.omp", {"param":"TRANSFER"}, function(data) {
//			resultCheckValidPrcStat(data);
//		}, "json");
//	}

//	function resultCheckValidPrcStat(data) {
//		var result = data.result;
//		var stat = data.stat;
		
//		if(result == "SESS_OUT") {
//			location.href=env.contextPath + "/login/loginMain.omp";
			
//			return false;
//		}
//		else if(result == "FALSE") {
//			alert("<gm:string value='jsp.member.mypage.msg.com04'/>" + stat + "<gm:string value='jsp.member.mypage.msg.com05'/>");
			
//			return false;
//		}else{
//			location.href=env.contextPath + "/mypage/mypageWithdrawReason.omp";
//		}
//	}

</script>

<div id="contents_area">
<form id="frm" name="frm" action="">
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; <gm:html value='마이페이지'/> &gt; <span><gm:html value='회원탈퇴'/></span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title04.gif'/>" alt="회원탈퇴" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		<h4 class="h43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle06.gif'/>" alt="회원탈퇴 안내" /></h4>
			<ul class="bult03 mar_b8">
				<li>개발자 센터 회원탈퇴를 원하시나요? 그동안 저희 사이트를 이용해주신 회원님께 진심으로 감사 드립니다.</li>
				<li>회원탈퇴 신청에 앞서  아래의 사항을 반드시 확인하시기 바랍니다.</li>
			</ul>
		<div class="mpbox mar_b30">
			<ol class="bult04">
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_01.gif'/>" alt="1" class="pad_b5" /><span class="txtcolor14">6개월간 동일 아이디로 회원가입 불가</span>
					<ul class="bult05">
						<li>회원탈퇴를 신청하시면 해당 아이디는 즉시 탈퇴 처리되며, 이후 6개월간 해당 아이디를 사용하여 재가입 하실 수 없습니다.<br />
							6개월 후 동일한 아이디로 재가입 하더라도 신규가입과 동일하게 가입됩니다.<br />
							(다른 아이디로는 바로 신규 회원가입을 하실 수 있습니다.)</li>
					</ul>
				</li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_02.gif'/>" alt="2" class="pad_b5" /><span class="txtcolor14"><gm:html value='회원정보 삭제'/></span>
					<ul class="bult05">
						<li>회원탈퇴 즉시 회원정보는 삭제됩니다.</li>
					</ul>
				</li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_03.gif'/>" alt="3" class="pad_b5" /><span class="txtcolor14"><gm:html value='서비스의 정보 삭제'/></span>
					<ul class="bult05">
						<li>회원님께서 구매하신 구매이력 등은 고객지원을 위해 6개월 동안만 보관하며, 금융정보는 1년 동안 보관합니다.</li>
					</ul>
				</li>
				<li class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_04.gif'/>" alt="4" class="pad_b5" /><span class="txtcolor14">불량이용 및 이용제한에 관한 기록 1년 동안 보관</span>
					<ul class="bult05">
						<li>불량이용 및 이용제한에 관한 기록은 1년 동안 삭제되지 않고 보관됩니다.</li>
					</ul>
				</li>
			</ol>
		</div>

		<h4 class="h43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle07.gif'/>" alt="회원탈퇴" /></h4>
		<ul class="bult03 mar_b8">
			<li>비밀번호 입력 후 확인 버튼을 클릭하시면, 회원 탈퇴가 진행됩니다.</li>
		</ul>
		<div class="mpbox">
			<div class="txttype10">
				<dl class="dlist05 mar_b5">
					<dt><gm:html value='아이디'/></dt>
					<dd><strong><g:html value="${profileInfo.mbrId}"/></strong></dd>
				</dl>
				<dl class="dlist05">
					<dt><label for="userpw"><gm:html value='비밀번호'/></label></dt>
					<dd><input type="password" id="usrPw" name="usrPw" class="w172" value="${usrPw}"
						v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.pw02'/>" 
		 				v:regexp="[a-zA-Z0-9\!\@\#\$\%\^\&\*\~]{6,16}" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.pw03'/>"/></dd>
				</dl>
			</div>
		</div>
		<div class="btnarea mar_t30">
<!--			<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif'/>" id="ok_btn" alt="확인" />-->
			<a shape="hover" ><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif'/>" id="ok_btn" alt="확인" style="cursor: pointer;" /></a>
			<a href="javascript:gotoPage('MAIN');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="취소" /></a>
		</div>
	</div>
	<!-- //CONTENT TABLE E-->
</div>
</form>