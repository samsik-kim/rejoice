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
					url: "${CONF['omp.common.url-prefix.https.dev']}" + env.contextPath + "/mypage/ajaxWithdrawExcute.omp",
					data: {usrPw : $("#usrPw").val()},
					success: function(json) {
						if(json.result == "100"){
							$("#frm").attr("action", env.contextPath + "/mypage/mypageWithdrawReason.omp");
							$("#frm").submit();
						}else if(json.result == "200"){
							alert("<gm:string value='jsp.member.mypage.mypageWithdraw.msg.not01'/>");
							return false;
						}else if(json.result == "300"){
							alert("<gm:string value='jsp.member.mypage.msg.pw01'/>");
							return false;
						}
					}
				});
			}
		});
		
	});
	
</script>

<div id="contents_area">
<form id="frm" name="frm" action="" method="post">
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 會員中心 &gt; <span>會員註銷</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title04.gif'/>" alt="會員註銷" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		<h4 class="h43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle06.gif'/>" alt="會員註銷須知" /></h4>
			<ul class="bult03 mar_b8">
				<li>您確定要註銷開發商專區會員嗎? 非常感謝您對我們的網站支持和配合</li>
				<li>請詳細閱讀以下會員註銷須知</li>
			</ul>
		<div class="mpbox mar_b30">
			<ol class="bult04">
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_01.gif'/>" alt="1" class="pad_b5" /><span class="txtcolor14">不可以同一帳號重新註冊</span>
					<ul class="bult05">
						<li>若申請會員註銷, 該帳號將被註銷後, 不可以同一帳號註冊.</li>
					</ul>
				</li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_02.gif'/>" alt="2" class="pad_b5" /><span class="txtcolor14">刪除會員個人資料</span>
					<ul class="bult05">
						<li>申請會員註銷, 個人資料立即被刪除.</li>
					</ul>
				</li>
				<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_03.gif'/>" alt="3" class="pad_b5" /><span class="txtcolor14">服務記錄刪除</span>
					<ul class="bult05">
						<li>對於您的購買記錄等各種服務記錄與金融記錄, 我們將分別保留6個月與1年.</li>
					</ul>
				</li>
				<li class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_04.gif'/>" alt="4" class="pad_b5" /><span class="txtcolor14">違法行為及服務限制</span>
					<ul class="bult05">
						<li>對於使用者違法與限制使用之記錄, 我們將保留1年.</li>
					</ul>
				</li>
			</ol>
		</div>

		<h4 class="h43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle07.gif'/>" alt="會員註銷" /></h4>
		<ul class="bult03 mar_b8">
			<li>請輸入密碼且點選[確定], 以便進行會員註銷流程. </li>
		</ul>
		<div class="mpbox">
			<div class="txttype10">
				<dl class="dlist05 mar_b5">
					<dt>帳號</dt>
					<dd><strong><g:html value="${profileInfo.mbrId}"/></strong></dd>
				</dl>
				<dl class="dlist05">
					<dt><label for="userpw">密碼</label></dt>
					<dd><input type="password" id="usrPw" name="usrPw" class="w172" value="<g:tagAttb value="${usrPw}"/>"
						v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.pw02'/>" /></dd>
				</dl>
			</div>
		</div>
		<div class="btnarea mar_t30">
			<a shape="hover" ><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif'/>" id="ok_btn" alt="確定" style="cursor: pointer;" /></a>
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="取消" /></a>
		</div>
	</div>
	<!-- //CONTENT TABLE E-->
</div>
</form>