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
	
	if ("<g:string value="${twIdNm}"/>" != "") str = "twId";
	if ("<g:string value="${compNm}"/>" != "") str = "comp";
	if ("<g:string value="${passportNm}"/>" != "") str = "passport";
				
	changeLabel(str);
});

function keypressEvent(frmNm, e){
	try {
		if (e.keyCode == 13) {
			e.preventDefault();
			findIdSubmit(frmNm); 
		}
	} catch(event) {
		if (e.keyCode == 13) {
			e.returnValue = false;
			findIdSubmit(frmNm); 
		}	
	}
}

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
		<p class="history">Home &gt; 查詢帳號/密碼 <strong>&gt;</strong> <span>查詢帳號</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_title01.gif' />" alt="查詢帳號" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		
		<h4 class="h43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_h4_01.gif' />" alt="以信箱查詢" /></h4>
		<p class="pbult06">可使用會員註冊時填寫之電子郵件查詢帳號</p>
		<form id="emailFrm" name="emailFrm" action="" method="post" onsubmit="return showValidate('emailFrm', 'default', '<gm:string value="jsp.user.common.msg.errTitle"/>');">
			<div class="utbox mar_b30">
				<span class="pbult07"><label for="email">電子郵件址</label></span> &nbsp;
				<input type="text" id="email" name="email" class="w308" value="<g:tagAttb value="${email}"/>"
					v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.email01' />" v:email m:email="<gm:tagAttb value='jsp.user.findId.msg.email02' />"/>
				<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ipbtn_confirm.gif' />" onclick="findIdSubmit('emailFrm')" alt="查詢" />
			</div>
			<h4 class="h43"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_h4_02.gif' />" alt="以身分證字號/ 統一編號 /護照號碼查" /></h4>
			<p class="pbult06">可使用會員註冊時填寫的身分證字號,統一編碼或護照號碼查詢帳號</p>
		</form>
		<!-- Taiwan ID S -->
		<div class="tab02 mar_b8">
			<ul>
				<li><a href="javascript:changeLabel('twId', true)"><img id="twIdTabImg" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_tab01.gif' />" alt="身分證字號" /></a></li>
				<li><a href="javascript:changeLabel('comp', true)"><img id="compTabImg" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_tab02.gif' />" alt="統一編號" /></a></li>
				<li><a href="javascript:changeLabel('passport', true)"><img id="passportTabImg" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ip_tab03.gif' />" alt="護照號碼" /></a></li>
			</ul>
		</div>

		<form id="twIdFrm" name="twIdFrm" action="" method="post" onsubmit="return showValidate('twIdFrm', 'default', '<gm:string value="jsp.user.common.msg.errTitle"/>');">
			<div id="twId">
				<div class="utbox">
					<p class="fltl mar_l145">
						<span class="pbult07"><label for="name">姓名</label></span> &nbsp;
						<input type="text" id="twIdNm" name="twIdNm" class="w128" value="<g:tagAttb value="${twIdNm }"/>" 
						v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.nm' />" onkeypress="javascript:keypressEvent('twIdFrm', event);"/>
					</p>
					<p class="fltl mar_l30">
						<span class="pbult07"><label for="taiwanid">身分證字號</label></span> &nbsp;
						<input type="text" id="psRegNo1" name="twIdPsRegNo" class="w128" value="${twIdPsRegNo }" v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.twId01' />" 
						v:regexp="[a-zA-Z0-9]{0,10}" m:regexp="<gm:tagAttb value='jsp.user.findId.msg.twId02'/>"
						v:twid m:twid="<gm:tagAttb value='jsp.user.findId.msg.twId03'/>" onkeypress="javascript:keypressEvent('twIdFrm', event);"/>
						<input type="image" id="submitBtn2" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ipbtn_confirm.gif' />" onclick="findIdSubmit('twIdFrm')" alt="查詢" />
					</p>
				</div>
			</div>				
		</form>
		<form id="compFrm" name="compFrm" action="" method="post" onsubmit="return showValidate('compFrm', 'default', '<gm:string value="jsp.user.common.msg.errTitle"/>');">
			<div id="comp" style="display:none;">		
				<div class="utbox">
					<p class="fltl mar_l145">
						<span class="pbult07"><label for="stname">公司名稱</label></span> &nbsp;
						<input type="text" id="compNm" name="compNm" class="w128" value="<g:tagAttb value="${compNm }"/>" 
						v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.comp01' />" onkeypress="javascript:keypressEvent('compFrm', event);"/>
					</p>
					<p class="fltl mar_l30">
						<span class="pbult07"><label for="uniformno">統一編號</label></span> &nbsp;
						<input type="text" id="psRegNo2" name="compPsRegNo" class="w128" value="${compPsRegNo }" 
						v:regexp="[\d]{8}" m:regexp="<gm:tagAttb value='jsp.user.findId.msg.compPsRegNo01'/>"
						v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.compPsRegNo02' />" onkeypress="javascript:keypressEvent('compFrm', event);"/>
						<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ipbtn_confirm.gif' />" onclick="findIdSubmit('compFrm')" alt="查詢" />
					</p>
				</div>
			</div>
		</form>
		
		<form id="passportFrm" name="passportFrm" action="" method="post" onsubmit="return showValidate('passportFrm', 'default', '<gm:string value="jsp.user.common.msg.errTitle"/>');">
			<div id="passport" style="display:none;">	
				<div class="utbox">
					<p class="fltl mar_l145">
						<span class="pbult07"><label for="pname">姓名</label></span> &nbsp;
						<input type="text" id="passportNm" name="passportNm" class="w128" value="<g:tagAttb value="${passportNm }"/>" 
						v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.nm' />" onkeypress="javascript:keypressEvent('passportFrm', event);"/>
					</p>
					<p class="fltl mar_l30">
						<span class="pbult07"><label for="passportno">護照號碼</label></span> &nbsp;
						<input type="text" id="psRegNo3" name="passportPsRegNo" class="w128" value="${passportPsRegNo }"
						v:text8_limit="18" m:text8_limit="<gm:tagAttb value='jsp.user.findId.msg.passPort01'/>"
						v:required m:required="<gm:tagAttb value='jsp.user.findId.msg.passPort02' />" onkeypress="javascript:keypressEvent('passportFrm', event);"/>
						<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ip/ipbtn_confirm.gif' />" onclick="findIdSubmit('passportFrm')" alt="查詢" />
					</p>
				</div>
			</div>
		</form>
		<!-- //Taiwan ID E -->
	
	</div>
	<!-- //CONTENT TABLE E-->

</div>