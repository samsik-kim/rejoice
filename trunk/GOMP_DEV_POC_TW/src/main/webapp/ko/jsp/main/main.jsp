<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<script type="text/javascript">
/*
 * Init
 */
$(document).ready(function(){
	var frm	= document.frm;
	
	//Session Check
	getid(frm);
	
	<c:if test="${empty MEMBER_SESSION}">
		if(frm.idsave.checked){
			$('#loginid').addClass('idhide');
			$("#loginpw").focus();
		}else{
			$("#loginid").focus();
		}
	</c:if>
	
	// ID Input Enter event
	$("#loginid").keydown(function(event){
		if (event.keyCode == 13) $("#loginBtn").click();
	});
	
	// PW Input Enter event
	$("#loginpw").keydown(function(event){
		if(event.keyCode == 13) $( "#loginBtn" ).click();
	});
	
	// Do Login 
	$("#loginBtn").click(function(){
		if(showValidate('frm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
			$('#loginFrm').attr('action', env.contextPath + '/login/login.omp');
			$('#loginFrm').submit();
			saveid();
			return false;
		}
	} );
	
});

/*
 * Session Save
 */
function saveid()	{
	var expdate	= new Date();

	if ($("#idsave").attr("checked"))
		expdate.setTime( expdate.getTime() + 1000 * 3600 * 24 * 30 );	// 30일
	else
		expdate.setTime( expdate.getTime() - 1 );						// 쿠키 삭제조건
	setCookie("saveid", $("#loginid").val(), expdate );
}

/*
 * Get Session Check
 */
function getid(frm) {
	<c:if test="${empty MEMBER_SESSION}">
		frm.idsave.checked	= ((frm.loginid.value = getCookie("saveid")) != "");
	</c:if>
}

/*
 * Session Check Confirm
 */
function checkboxConfirm(){
	var frm	= document.frm;
	if(frm.idsave.checked){ 
			if(!confirm("<gm:string value='jsp.main.main.btn.chBox'/>")){
			frm.idsave.checked	= false;
		}
	}else{
		deleteCookie("saveid");
	}
}

/*
 * Download Best 5
 */
function downloadBest(type){
	var payNm = $("#pay").attr("src");
	var freeNm = $("#free").attr("src");

	var result = false;
	
	if(type == "pay"){
		if(payNm.indexOf("_on") == -1){
			$("#pay").attr("src", payNm.replace(".gif", "_on.gif"));
			$("#free").attr("src", freeNm.replace("_on.gif", ".gif"));
			
			result = true;
		}
	}else if(type == "free"){
		if(freeNm.indexOf("_on") == -1){
			$("#pay").attr("src", payNm.replace("_on.gif", ".gif"));
			$("#free").attr("src", freeNm.replace(".gif", "_on.gif"));

			result = true;
		}
	}
	
	if(result){
		$.post(
			env.contextPath + "/main/ajaxDownloadBest.omp",
			{type : type},
			function(data) {
				var str = "";
				for(var i = 0; i < data.downBestList.length; i++){
					str += "<li>";
					str += "<img src=\"<c:url value='/${ThreadSession.serviceLocale.language}/images/main/down_" + (i + 1) + ".gif' />\" alt=\"" + (i + 1) + "\" />";
					str += data.downBestList[i].prodNm;
					str += "</li>";
				}
				$("#downBest").empty();
				$("#downBest").html(str);
			},
			"json");
	}
}

/*
 * Main Notice View
 */
function noticeView(noticeId) {
	$("#noticeId").val(noticeId);
	$("#noticeForm").attr("action", env.contextPath + "/community/viewNotice.omp");
	$("#noticeForm").submit();
}
	
/**
 * goTo Content Status List
 * goTo Verify Status List
 */
function gotoContentsList(objName) {
	
	var saleStat = "";
	if (objName == "CONTENT_SALE_STAT_NA") {			// 미승인
		saleStat = '${CONST.CONTENT_SALE_STAT_NA}';
		
		var frm = $('#editForm');
		$('#saleSearchType').val(saleStat);
		frm.attr("target", "_self");	
		frm.attr("action", env.contextPath + "/content/contentsStatusList.omp");
		frm.submit();

	} else if (objName == "VERIFY_COM") {				// 검증완료
		location.href =  env.contextPath + "/content/getContentsVerifyList.omp?ctVerify.searchVerifyPrgrYn=${CONST.CODE_VERIFY_END}";
	} else if (objName == "VERIFY_ING") 	{			// 검증진행중
		location.href =  env.contextPath + "/content/getContentsVerifyList.omp?ctVerify.searchVerifyPrgrYn=${CONST.CODE_VERIFY_ING}";
	}
	
}
	
</script>
	<!-- Main_Container S-->
	<div id="main_container">
		<h2 class="hide">메인 컨텐츠 영역</h2>
		<!-- Main Contents Area S-->
		<div id="maincontent">
		
			<div id="mcleft">   
				<!-- Login S-->    
				<c:if test="${empty MEMBER_SESSION}">
					<div class="mlogin">
						<form id="loginFrm" name="frm" method="post">
						<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/login_title.gif' />" alt="Login" /></h3>
						<ul>
							<li><label for="loginid" class="hide">아이디</label><input type="text" id="loginid" name="user_id" onfocus="javascript:idhideBG(this);" onblur="javascript:idshowBG(this);" class="idshow" maxlength="13" v:required m:required="<gm:tagAttb value='jsp.main.main.msg.id'/>" tabindex="1"/><input type="checkbox" id="idsave" name="idsave" onclick="javascript:checkboxConfirm();" tabindex="3" /><label for="idsave">&nbsp;ID저장</label></li>
							<li><label for="loginpw" class="hide">비밀번호</label><input type="password" id="loginpw" name="user_passwd" onfocus="javascript:pwhideBG(this);" onblur="javascript:pwshowBG(this);" class="pwshow" maxlength="16" v:required m:required="<gm:tagAttb value='jsp.main.main.msg.pw'/>" tabindex="2"/><a shape="hover" tabindex="4"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/btn_login.gif'/>" alt="로그인" id="loginBtn" style="cursor: pointer;" /></a></li>
							<li class="txt01"><a href="javascript:gotoPage('CHECKBEFOREREGIST');">회원가입</a></li>
							<li class="txt02"><a href="<c:url value='/login/findId.omp'/>">아이디/비밀번호 찾기</a></li>
						</ul>
						</form>
					</div>   
				</c:if>
				<c:if test="${not empty MEMBER_SESSION}">
					<!-- Login S-->
					<div class="mloginB">
					<form id="editForm" name="editForm" method="post">
					<input type="hidden" id="saleSearchType" name="content.saleSearchType" value="">
						<p class="member"><span class="tcolor01"><g:html  value='${MEMBER_SESSION.mbrId}' /></span><strong>님</strong> (<g:text format="L##"><gc:html code='${member.mbrCatCd}' /></g:text><gc:html code='${member.mbrClsCd}' />회원)</p>
						<ul class="state">
							<li class="sta01"><span>신규등록</span> <a href="javascript:gotoContentsList('CONTENT_SALE_STAT_NA');"><g:text value="${newProcuctCnt}" /></a>건</li>
	
							<li class="sta02"><span>검증완료</span> <a href="<c:url value='/content/getContentsVerifyList.omp?ctVerify.searchVerifyPrgrYn=${CONST.CODE_VERIFY_END}' />"><g:text value="${authDoneProductCnt}" /></a>건</li>
							<li class="sta03">
								<span>전일판매현황</span> <a href="<c:url value='/settlement/dailysale/dailySaleList.omp'/>"><g:text value="${beforeSaleCnt}" format="R#,###,###,###" /></a>건<br />
								<strong class="sta_won"><a href="<c:url value='/settlement/dailysale/dailySaleList.omp'/>"><g:text value="${beforeSaleSum}" format="R#,###,###,###"  /></a>원</strong>
							</li>
						</ul>
						<ul class="proli">
							<li><a href="<c:url value='/content/registContentWrite.omp' />">상품등록</a></li>
							<li><a href="<c:url value='/content/getContentsVerifyList.omp' />">검증현황</a></li>
							<li><a href="<c:url value='/settlement/dailysale/dailySaleList.omp'/>">정산현황</a></li>
						</ul>
					</form>	
					</div>
				</c:if>
				<!-- //Login E-->
				<c:if test="${empty MEMBER_SESSION}">
					<p class="cb"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/open_banner3.gif' />" alt="Amart Developer Center Grand Open" /></p>
				</c:if>
				<div class="mdown">     
					<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/down_title.gif' />" alt="Download Best" /></h3>
					<p class="pay">
						<a href="javascript:downloadBest('pay')"><img id="pay" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/pay_on.gif' />" alt="유료" /></a>
						<a href="javascript:downloadBest('free')"><img id="free" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/pay_free.gif' />" alt="무료" /></a>
					</p>
					<ul id="downBest">
						<c:forEach items="${downBestList}" var="item" varStatus="status">
							<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/down_${status.count}.gif' />" alt="${status.count}" /><g:text>${item.prodNm}</g:text></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			
			<!-- Guide S -->
			<div id="mcright">
				<div class="guide1">
					<h3 class="gh3"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/main_h4_01.gif'/>" alt="상품등록가이드" /></h3>
					<p>직접 개발한 App을 AMart에서 판매<br />해보세요. 쉽고 간편하게 등록하시고<br />관리하실 수 있습니다.</p>
					<p class="more"><a href="<c:url value='/community/basicInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/more.gif' />" alt="상품등록가이드 더보기" /></a></p>
				</div>
				<div class="guide2">
					<h3 class="gh3"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/main_h4_02.gif' />" alt="상품검증가이드" /></h3>
					<p>개발자가 등록한 상품 정보를 정확하고,<br />간편하게 검수할 수 있습니다. 다양한<br />상품을 편리하게 관리하세요.</p>
					<p class="more"><a href="<c:url value='/community/prodVerificationGuide.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/more.gif' />" alt="상품검증가이드 더보기" /></a></p>
				</div>
				<div class="guide3">
					<h3 class="gh3"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/main_h4_03.gif' />" alt="상품판매 및 정산가이드" /></h3>
					<p>직접 개발한 App 판매 및 판매 수익에 대한<br />정산통계지원 서비스를 지원해 드립니다.<br />지금 바로 확인해 보세요.</p>
					<p class="more"><a href="<c:url value='/community/saleCalculateGuide.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/more.gif' />" alt="상품판매 및 정산가이드 더보기" /></a></p>
				</div>
				<div class="devnews">
					<div class="tit">
						<h4 class="fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/news_title.gif' />" alt="Developer Center News" /></h4>
						<p class="txtr pad_t5"><a href="<c:url value='/community/listNotice.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/more.gif' />" alt="Developer Center News 더보기" /></a></p>
					</div>
					<form id="noticeForm" name="noticeForm" method="get" action="">
						<input type="hidden" id="noticeId" name="notice.noticeId"/>
						<c:forEach items="${noticeList}" var="notice">
						<dl>
							<dt>
								<a href="javascript:noticeView('${notice.noticeId}');"><g:text value="${notice.title}" /></a>
								<c:if test="${notice.downYn eq 'Y'}">
									&nbsp;<a href="<c:url value="/fileSupport/bbsFileDown.omp">
																<c:param name="bnsType" value="common.path.http-share.common.notice"/>
																<c:param name="filePath" value="${notice.down_path}"/>
																<c:param name="fileName" value="${notice.down_ofnm}"/>
																</c:url>" ><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/icon_addfile.gif' />" alt="addfile" class="vm" /></a>
								</c:if>
								<c:if test="${notice.newYn eq 'Y' }">
									&nbsp;<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/main/icon_new.gif' />" alt="new" class="vm" />
								</c:if>
								<span><g:text value="${notice.insDttm}" format="L####-##-##" /></span>
							</dt>
							<dd><g:text value="${notice.htmlEsc}" /></dd>
						</dl>
					</c:forEach>
					</form>
				</div>
			</div>
			<!-- //Guide E -->
			
			<!-- Site map S -->
            	<jsp:include page="/${ThreadSession.serviceLocale.language}/jsp/main/main_site_map.jsp"></jsp:include>
			<!-- //Site map E -->
		</div> 
		<!-- Main Contents Area E-->
	</div>
    <hr />
	<!-- //Main_Container E-->
