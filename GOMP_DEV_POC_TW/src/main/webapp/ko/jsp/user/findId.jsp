<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
/*
 * Init
 */
$(document).ready( function(){
	if ("${errMsg}" != "") alert("${errMsg}");
	
	var str = "";
	
	if ("${twIdNm}" != "") str = "twId";
	if ("${compNm}" != "") str = "comp";
	if ("${passportNm}" != "") str = "passport";
				
	changeLabel(str);
	
	$('#psRegNo1').keypress(function(event){
		if(event.keyCode == 13) {
			findIdSubmit('twIdFrm');
		}
	});
	$('#psRegNo2').keypress(function(event){
		if(event.keyCode == 13) {
			findIdSubmit('compFrm');
		}			
	});
	$('#psRegNo3').keypress(function(event){
		if(event.keyCode == 13) {
			findIdSubmit('passportFrm');
		}
	});
	
});

/*
 * Find ID Submit
 */
function findIdSubmit(frmId){
	$("#" + frmId).attr('action', env.contextPath + '/login/findIdResult.omp');
	$("#" + frmId).submit();
}

/*
 * Change Lable
 */
function changeLabel(labelType, changeYn) {
	if (labelType == "twId") {
		$("#twId").show();
		$("#comp").hide();
		$("#passport").hide();
		
		labelOn(labelType);
	} else if (labelType == "comp") {
		$("#twId").hide();
		$("#comp").show();
		$("#passport").hide();
		
		labelOn(labelType);
	} else if (labelType == "passport") {
		$("#twId").hide();
		$("#comp").hide();
		$("#passport").show();
		
		labelOn(labelType);
	} else {
		labelOn(labelType);
	}
	
	if(changeYn) defaultVal();
}

/* 
 * Label Show
 */
function labelOn(str){
	if ("twId" == str) {
		$("#twIdTabImg").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/ip/ip_tab01_on.gif");
		$("#compTabImg").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/ip/ip_tab02.gif");
		$("#passportTabImg").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/ip/ip_tab03.gif");
	}
	
	if ("comp" == str) {
		$("#twIdTabImg").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/ip/ip_tab01.gif");
		$("#compTabImg").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/ip/ip_tab02_on.gif");
		$("#passportTabImg").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/ip/ip_tab03.gif");
	}
	
	if ("passport" == str) {
		$("#twIdTabImg").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/ip/ip_tab01.gif");
		$("#compTabImg").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/ip/ip_tab02.gif");
		$("#passportTabImg").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/ip/ip_tab03_on.gif");
	}
	
	if("" == str) $("#twIdTabImg").attr("src", env.contextPath + "/${ThreadSession.serviceLocale.language}/images/ip/ip_tab01_on.gif");
}

/*
 * Default Value Setting
 */
function defaultVal(){
	$("#twIdNm").val("");
	$("#compNm").val("");
	$("#passportNm").val("");
	$("#psRegNo1").val("");
	$("#psRegNo2").val("");
	$("#psRegNo3").val("");
}

</script>

<div id="contents_area">	  
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 아이디 비밀번호 찾기 <strong>&gt;</strong> <span>아이디 찾기</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_title01.gif' />" alt="아이디 찾기" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		
		<h4 class="h43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_h4_01.gif' />" alt="이메일로 찾기" /></h4>
		<p class="pbult06">회원 가입 시 등록했던 이메일 입력을 통해 아이디 찾기가 가능합니다.</p>
		<form id="emailFrm" name="emailFrm" action="" method="post" onsubmit="return showValidate('emailFrm', 'default', '<gm:string value="jsp.user.common.msg.errTitle"/>');">
			<div class="utbox mar_b30">
				<span class="pbult07"><label for="email">이메일 주소</label></span> &nbsp;
				<input type="text" id="email" name="email" class="w308" value="${email }"
					v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.email01' />" v:email m:email="<gm:tagAttb value='jsp.user.findId.msg.email02' />"/>
				<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ipbtn_confirm.gif' />" onclick="findIdSubmit('emailFrm')" alt="확인" />
			</div>
			<h4 class="h43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_h4_02.gif' />" alt="타이완 ID / 통일번호 / 여권번호 로 찾기" /></h4>
			<p class="pbult06">회원 가입 시 등록했던 타이완 ID ,통일번호, 여권번호 입력을 통해 ID 찾기가 가능합니다.</p>
		</form>
		<!-- Taiwan ID S -->
		<div class="tab02 mar_b8">
			<ul>
				<li><a href="javascript:changeLabel('twId', true)"><img id="twIdTabImg" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_tab01.gif' />" alt="타이완 ID" /></a></li>
				<li><a href="javascript:changeLabel('comp', true)"><img id="compTabImg" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_tab02.gif' />" alt="통일번호" /></a></li>
				<li><a href="javascript:changeLabel('passport', true)"><img id="passportTabImg" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_tab03.gif' />" alt="여권번호" /></a></li>
			</ul>
		</div>

		<form id="twIdFrm" name="twIdFrm" action="" method="post" onsubmit="return showValidate('twIdFrm', 'default', '<gm:string value="jsp.user.common.msg.errTitle"/>');">
			<div id="twId">
				<div class="utbox">
					<p class="fltl mar_l145">
						<span class="pbult07"><label for="name">이름</label></span> &nbsp;
						<input type="text" id="twIdNm" name="twIdNm" class="w128" value="${twIdNm }" 
						v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.nm' />"/>
					</p>
					<p class="fltl mar_l30">
						<span class="pbult07"><label for="taiwanid">Taiwan ID</label></span> &nbsp;
						<input type="text" id="psRegNo1" name="twIdPsRegNo" class="w128" value="${twIdPsRegNo }" v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.twId01' />" 
						v:regexp="[a-zA-Z0-9]{0,10}" m:regexp="<gm:tagAttb value='jsp.user.findId.msg.twId02'/>"
						v:twid m:twid="<gm:tagAttb value='jsp.user.findId.msg.twId03'/>" />
						<input type="image" id="submitBtn2" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ipbtn_confirm.gif' />" onclick="findIdSubmit('twIdFrm')" alt="확인" />
					</p>
				</div>
			</div>				
		</form>
		<form id="compFrm" name="compFrm" action="" method="post" onsubmit="return showValidate('compFrm', 'default', '<gm:string value="jsp.user.common.msg.errTitle"/>');">
			<div id="comp" style="display:none;">		
				<div class="utbox">
					<p class="fltl mar_l145">
						<span class="pbult07"><label for="stname">상호명</label></span> &nbsp;
						<input type="text" id="compNm" name="compNm" class="w128" value="${compNm }" 
						v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.comp01' />" />
					</p>
					<p class="fltl mar_l30">
						<span class="pbult07"><label for="uniformno">통일번호</label></span> &nbsp;
						<input type="text" id="psRegNo2" name="compPsRegNo" class="w128" value="${compPsRegNo }" 
						v:regexp="[\d]{8}" m:regexp="<gm:tagAttb value='jsp.user.findId.msg.compPsRegNo01'/>"
						v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.compPsRegNo02' />" />
						<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ipbtn_confirm.gif' />" onclick="findIdSubmit('compFrm')" alt="확인" />
					</p>
				</div>
			</div>
		</form>
		
		<form id="passportFrm" name="passportFrm" action="" method="post" onsubmit="return showValidate('passportFrm', 'default', '<gm:string value="jsp.user.common.msg.errTitle"/>');">
			<div id="passport" style="display:none;">	
				<div class="utbox">
					<p class="fltl mar_l145">
						<span class="pbult07"><label for="pname">이름</label></span> &nbsp;
						<input type="text" id="passportNm" name="passportNm" class="w128" value="${passportNm }" 
						v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.nm' />" />
					</p>
					<p class="fltl mar_l30">
						<span class="pbult07"><label for="passportno">여권번호</label></span> &nbsp;
						<input type="text" id="psRegNo3" name="passportPsRegNo" class="w128" value="${passportPsRegNo }" 
						v:regexp="[a-zA-Z0-9]{0,18}" m:regexp="<gm:tagAttb value='jsp.user.findId.msg.passPort01'/>"
						v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.passPort02' />" />
						<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ipbtn_confirm.gif' />" onclick="findIdSubmit('passportFrm')" alt="확인" />
					</p>
				</div>
			</div>
		</form>
		<!-- //Taiwan ID E -->
	
	</div>
	<!-- //CONTENT TABLE E-->

</div>