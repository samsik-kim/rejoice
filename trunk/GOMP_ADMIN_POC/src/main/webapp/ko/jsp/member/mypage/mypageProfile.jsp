<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
$(document).ready(function(){
	
	addValidateFunction({
		phone	: 	function(c) {
			if($(c).val() == '' && $('#afterPhoneNum').val() != '') return false;
			else return true;
		},
		afterphone : function(c) {
			if($(c).val() == '' && $('#phonenum').val() != '') return false;
			else return true;
		},
		reqnotuse	:	function(c){
			var telno, psregno;
			<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
			telno = $("#phonenum").val() + $("#afterPhoneNum").val();
			psregno = $(c).val().toUpperCase();
			</c:if>
			<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
			telno = $("#phonenum").val() + $("#afterPhoneNum").val();
			psregno = $(c).val();
			</c:if>
			<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
			telno = $("#phonenum").val();
			psregno = $(c).val();
			</c:if>
			if(psregno == $("#psregno").val()){
				return false;
			}else if($(c).val() == $("#mbrNm").val()){
				return false;
			}else  if(telno != ''){
				if($(c).val() == telno) return false;
				else return true;
			}else if($("#emailAddr").val() != ''){
				var num = $(c).val().indexOf("@");
				var email = $(c).val().substring(0, num);
				if($(c).val() == email) return false;
				else return true;
			}else{
				return true;
			}
		}
	});
	
	$("#pop_area01").hide();
<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
	var telNo = $("#telNo").val();
	var splitStr = telNo.split("-");
	var regionNum = splitStr[0];
	var afterPhoneNum = telNo.substring(regionNum.length + 1, telNo.length);
	$("#afterPhoneNum").val(afterPhoneNum);
	$("#phonenum option").each(function(i) {
		if($(this).val() == regionNum)
			$(this).attr("selected", "selected");
	});
</c:if>
<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
	var opTelNo = $("#opTelNo").val();
	var splitStr = opTelNo.split("-");
	var regionNum = splitStr[0];
	var afterPhoneNum = opTelNo.substring(regionNum.length + 1, opTelNo.length);
	$("#afterPhoneNum").val(afterPhoneNum);
	$("#phonenum option").each(function(i) {
		if($(this).val() == regionNum)
			$(this).attr("selected", "selected");
	});
</c:if>
<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
	$("#country option").each(function(i) {
		if($(this).val() == $("#naCd").val())
			$(this).attr("selected", "selected");
	});
</c:if>
 });

$(function() {
	// 수정 버튼 클릭 이벤트 바인드
	$("#btnModify").click(function() {
		submitModify();
		return false;
	});
});

// 수정
function submitModify() {
	if(showValidate('frmProfile', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
		if(confirm("<gm:string value='jsp.member.mypage.msg.com06'/>") == false) {
			return false;
		}
	<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
		var telNo = $('#phonenum').val() + "-" + $("#afterPhoneNum").val();
		$("#telNo").val(telNo);
	</c:if>
	<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
		var opTelNo = $('#phonenum').val() + "-" + $("#afterPhoneNum").val();
		$("#opTelNo").val(opTelNo);
	</c:if>
		$("#forwardAction").val("PROFILE");
		var data = $("#frmProfile").serialize();
		$.ajax({
			url: "${CONF['omp.common.url-prefix.https.dev']}" + env.contextPath + '/mypage/ajaxMypageProfileModify.omp',
			dataType: 'json',
			type	: "POST",
			data 	: data,
			async	: false,		
			cache	: false,	
			success: function(json){
				if(json.result == 'SUCCESS'){
					$("#frmProfile").attr('action', env.contextPath + '/mypage/mypageProfileView.omp');
					$("#frmProfile").submit();					
				}
			}
		});
	}
}

// 저장 확인 레이어 팝업
function showConfirmLayer(targetLayerId, parentLayerId, screenTop){
	var targetLayer = $("#"+targetLayerId);
	var layerTop = (screen.height - targetLayer.height()) / 2 - 150;
	var layerLeft = (screen.width - targetLayer.width()) / 2 - 300;
	
	if (navigator.userAgent.indexOf("MSIE 6")>-1 && screenTop != undefined && screenTop != "") {
		layerTop = screenTop;
	}

	var zindex = 10;
	if(parentLayerId){
		var parentLayer = $("#"+parentLayerId);
		if(parentLayer.css("z-index") != "auto"){
			zindex = parseInt(parentLayer.css("z-index")) + 10;
		}
	}

	targetLayer.css("top", layerTop);
	targetLayer.css("left", layerLeft);
	targetLayer.css("z-index", zindex);
	targetLayer.fadeIn("fast");	
	setDimm(targetLayerId, true);
}

// 탭이동
function moveTabPage(target) {
	if(showValidate('frmProfile', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
		if(isExistChangedInfo()) {
			$("#forwardAction").val(target);
			createPopupLayer('pop_area');
			$('#pop_area01').show();
			$("#pop_area_body").html($('#pop_area01'));
			showPopupLayer('pop_area', 'wrap');
		}
		else {
			if(target == "CALCULATE") {
				$("#frmProfile").attr('action', env.contextPath + '/mypage/mypageCalculateInfoView.omp');
				$("#frmProfile").submit();
			}
		}
	}
}

// 저장 취소 후 탭 이동
function cancelMoveTabPage() {
	if($("#forwardAction").val() == "CALCULATE") {
		$("#frmProfile").attr('action', env.contextPath + '/mypage/mypageCalculateInfoView.omp');
		$("#frmProfile").submit();
	}
}

// 저장 후 탭 이동
function saveMoveTabPage() {
	<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
		var telNo = $('#phonenum').val() + "-" + $("#afterPhoneNum").val();
		$("#telNo").val(telNo);
	</c:if>
	<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
		var opTelNo = $('#phonenum').val() + "-" + $("#afterPhoneNum").val();
		$("#opTelNo").val(opTelNo);
	</c:if>
		var data = $("#frmProfile").serialize();
		$.ajax({
			url: "${CONF['omp.common.url-prefix.https.dev']}" + env.contextPath + '/mypage/ajaxMypageProfileModify.omp',
			dataType: 'json',
			type	: "POST",
			data 	: data,
			async	: false,		
			cache	: false,	
			success: function(json){
				if(json.result == 'SUCCESS'){
					$("#frmProfile").attr('action', env.contextPath + '/mypage/mypageCalculateInfoView.omp');
					$("#frmProfile").submit();					
				}
			}
		});
}

// 변경 여부 확인
function isExistChangedInfo() {
	if($("#prevMbrPw").val() != $("#pwnum").val()) return true;
	if($("#prevMbrPw").val() != $("#pwnuncon").val()) return true;
	if($("#prevCity").val() != $("#city").val()) return true;
	if($("#prevAddress").val() != $("#address").val()) return true;
	if($("#prevPostNum").val() != $("#postnum").val()) return true;
	if($("#prevWsite").val() != $("#wsite").val()) return true;
	if($("#prevMoblenum").val() != $("#moblenum").val()) return true;
<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
	var telNo = $('#phonenum').val() + "-" + $("#afterPhoneNum").val();
	if($("#telNo").val().replace("-","") != telNo.replace("-","")) return true;
</c:if>
<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
	if($("#prevName").val() != $("#name").val()) return true;
	if($("#prevEmail").val() != $("#email2").val()) return true;
	var opTelNo = $('#phonenum').val() + "-" + $("#afterPhoneNum").val();
	if((opTelNo != $("#opTelNo").val()) && ($("#afterPhoneNum").val() != "")){
		$("#opTelNo").val().replace("-","");
		return true;
	}
</c:if>
<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
	if($("#naCd").val() != $("#country").val()) return true;
	if($("#prevPhonenum").val() != $("#phonenum").val()) return true;
</c:if>
}

function validationProfile() {
	if($("#pwnuncon").val() != $("#pwnum").val()) return true;
}
</script>

<div id="contents_area">

	
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 마이페이지 &gt; <span>회원정보변경</span></p>
		<h3><img alt="회원정보변경" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title01.gif'/>"></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->   
	<div id="contents">    
	<form id="frmProfile" name="frmProfile" method="post">
		<input type="hidden" id="forwardAction" name="forwardAction" value=""/>
		<input type="hidden" name="isProfile" value="<g:tagAttb value="${isProfile}"/>"/>
		<!-- Tab_menu S -->
		<div class="tab mar_b22">
			<ul>   
				<li><a href="javascript:moveTabPage('PROFILE');"><img alt="기본정보" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_tab01_on.gif'/>"></a></li>
				<li><a href="javascript:moveTabPage('CALCULATE');"><img alt="정산정보" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_tab02.gif'/>"></a></li>
			</ul>
			<p class="info"><span>*</span> 가 표시된 부분은 필수입력 항목입니다.</p>
		</div>
		<!-- //Tab_menu E -->
		<h4 class="h41"><img alt="기본정보" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_h4_01.gif'/>"></h4>
		<div class="tstyleA mar_b22">
			<table summary="기본정보 수정">
				<caption>기본정보 수정</caption>
				<colgroup>
					<col width="15%">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row" class="tit03">회원타입</th>
						<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
							<td>개인 개발자</td>
						</c:if>
						<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
							<td>회사회원</td>
						</c:if>
						<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
							<td>외국인</td>
						</c:if>
					</tr>
					
					<tr>
						<th scope="row" class="tit03">멤버십 구분</th>
						<c:if test="${profileInfo.mbrCatCd eq 'US000205'}">
							<td>무료회원 
								<c:if test="${profileInfo.devMbrStatCd ne 'US000804'}">
									<a href="javascript:moveTabPage('CALCULATE');"><img alt="유료회원 전환" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/btn_pay.gif'/>"></a>
								</c:if>
							</td>
						</c:if>
						<c:if test="${profileInfo.mbrCatCd eq 'US000206'}">
							<td>유료회원(<g:html value="${profileInfo.payTransDt}"/>)</td>
						</c:if>
					</tr>
					
					<tr>
						<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
							<th scope="row" class="tit03">이름</th>
							<td><g:html value="${profileInfo.mbrNm}"/>
							<input type="hidden" id="mbrNm" value="<g:tagAttb value="${profileInfo.mbrNm}"/>"/>
							</td>
						</c:if>
						<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
							<th scope="row" class="tit03">회사명</th>
							<td><g:html value="${profileInfo.compNm}"/>
							<input type="hidden" id="mbrNm" value="<g:tagAttb value="${profileInfo.compNm}"/>"/>
							</td>
						</c:if>
						<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
							<th scope="row" class="tit03">이름</th>
							<td><g:html value="${profileInfo.mbrNm}"/>
							<input type="hidden" id="mbrNm" value="<g:tagAttb value="${profileInfo.mbrNm}"/>"/>
							</td>
						</c:if>
					</tr>
					  
					<tr>
						<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
							<th scope="row" class="tit03">Taiwan ID</th>
							<td><g:html  value='${profileInfo.psRegNo}' format="L##***********"/></td>
						</c:if>
						<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
							<th scope="row" class="tit03">통일번호</th>
							<td><g:html  value='${profileInfo.psRegNo}' format="L##***********"/></td>
						</c:if>
						<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
							<th scope="row">여권번호</th>
							<td><g:html  value='${profileInfo.psRegNo}' format="L##***********"/></td>
						</c:if>
						<input type="hidden" id="psregno" value="<g:tagAttb value="${profileInfo.psRegNo}"/>" />
					</tr>
					
					<tr>
						<th scope="row" class="tit03">아이디</th>
						<td>
							<g:html value="${profileInfo.mbrId}"/>
							<input type="hidden" name="profileInfo.mbrId" value="<g:tagAttb value="${profileInfo.mbrId}"/>"/>
						</td>
					</tr>
					
					<tr>
						<th scope="row"><span>*</span> <label for="email1">이메일</label></th>
						<td><g:html value="${profileInfo.emailAddr}"/>&nbsp;
						<input type="hidden" id="emailAddr" value="<g:tagAttb value="${profileInfo.emailAddr}"/>" />
						<a href="<c:url value='/mypage/mypageEmail.omp'/>"><img alt="이메일 변경" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/btn_email.gif'/>"></a></td>
					</tr>
					
					<tr> 
						<th scope="row"><span>*</span> <label for="pwnum">비밀번호</label></th>
						<td>
							<input type="hidden" id="prevMbrPw" value="<g:tagAttb value="${profileInfo.mbrPw}"/>"/>
							<input type="password" class="w180" value="<g:tagAttb value="${profileInfo.mbrPw}"/>" name="profileInfo.mbrPw" id="pwnum" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.pw02'/>"
							v:reqnotuse m:reqnotuse="<gm:tagAttb value='jsp.member.join.msg.pw05'/>"
							v:reqexpw m:reqexpw="<gm:tagAttb value='jsp.member.join.msg.pw06'/>"
							v:reqexpwwith m:reqexpwwith="<gm:tagAttb value='jsp.member.mypage.msg.pw03'/>"/><span class="txtcolor01"> * 6~16자의영대소문자, 숫자, 특수문자만 사용, 공백입력 불가</span></td>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="pwnuncon">비밀번호 확인</label></th>
						<td><input type="password" class="w180" value="<g:tagAttb value="${profileInfo.mbrPw}"/>" id="pwnuncon" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.pw05'/>" 
							v:scompare="==,@{profileInfo.mbrPw}" m:scompare="<gm:tagAttb value='jsp.member.mypage.msg.pw06'/>"/></td>
					</tr>
					
					<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
					<tr>
						<input id="naCd" type="hidden" value="<g:tagAttb value="${profileInfo.naCd}"/>"/>
						<th scope="row"><span>*</span> <label for="pwnuncon">국가</label></th>
						<td>
							<select id="country" name="profileInfo.naCd" class="w232">  
								<gc:codeList group="US0058" var="USCODE"/>
						    	<c:forEach items="${USCODE}" var="us">
									<option value="${us.addField1}">${us.cdNm}</option>
						    	</c:forEach>
							</select>
						</td>
					</tr>
					</c:if>
					
					<tr>
						<th scope="row"><span>*</span> <label for="city">도시</label></th>
						<td>
							<input type="text" class="w180" value="<g:tagAttb value="${profileInfo.city}"/>" name="profileInfo.city" id="city"
							v:text8_limit="75" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ct01'/>"  
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.ct02'/>"/>
							<input type="hidden" id="prevCity" value="<g:tagAttb value="${profileInfo.city}"/>"/>
						</td>
					</tr>
					
					<tr>
						<th scope="row"><span>*</span> <label for="address">주소</label></th>
						<td>
							<input type="text" class="w376" value="<g:tagAttb value="${profileInfo.homeAddrDtl}"/>" name="profileInfo.homeAddrDtl" id="address"
							v:text8_limit="150" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.addr01'/>" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.addr02'/>" />
							<input type="hidden" id="prevAddress" value="<g:tagAttb value="${profileInfo.homeAddrDtl}"/>"/>
						</td>
					</tr>
					
					<tr>
						<th scope="row" class="tit03"><label for="postnum">우편번호</label></th>
					<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
						<td>
							<input type="hidden" id="prevPostNum" value="<g:tagAttb value="${profileInfo.zipCd}"/>"/>
							<input type="text" class="w180" value="<g:tagAttb value="${profileInfo.zipCd}"/>" name="profileInfo.zipCd" id="postnum" maxlength="3"
							v:text8_limit="3" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.zp01'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.zp02'/>"/>
							<span class="txtcolor01"> * “-”을 생략하고 우편번호 5자리를 숫자로만 입력해 주세요</span>
						</td>
					</c:if>
					<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
						<td>
							<input type="hidden" id="prevPostNum" value="<g:tagAttb value="${profileInfo.zipCd}"/>"/>
							<input type="text" class="w180" value="<g:tagAttb value="${profileInfo.zipCd}"/>" name="profileInfo.zipCd" id="postnum" maxlength="3"
							v:text8_limit="3" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.zp01'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.zp02'/>"/>
							<span class="txtcolor01"> * “-”을 생략하고 우편번호를 숫자로만 입력해 주세요. </span>
						</td>
					</c:if>
					<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
						<td>
							<input type="hidden" id="prevPostNum" value="<g:tagAttb value="${profileInfo.zipCd}"/>"/>
							<input type="text" class="w180" value="<g:tagAttb value="${profileInfo.zipCd}"/>" name="profileInfo.zipCd" id="postnum" maxlength="5"
							v:text8_limit="5" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.zp03'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.zp02'/>"/>
							<span class="txtcolor01"> * “-”을 생략하고 우편번호 5자리를 숫자로만 입력해 주세요</span>
						</td>
					</c:if>
					</tr>
					
					<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
					<tr>
						<th scope="row" class="tit03"><label for="phonenum">전화번호</label></th>
						<td>
							<input id="telNo" type="hidden" name="profileInfo.telNo" value="<g:tagAttb value="${profileInfo.telNo}"/>"/>
							<div class="fltl pad_r4">
								<select id="phonenum" class="w67" name="phone" v:phone m:phone="<gm:tagAttb value='jsp.member.join.msg.ph05'/>">
									<option value=""></option>
									<gc:codeList group="${CONST.PHONE_AREA_CODE}" var="USCODE"/>
							    	<c:forEach items="${USCODE}" var="us">
										<option value="${us.cdNm}">${us.cdNm}</option>
							    	</c:forEach>
								</select>
							</div>  
							<input id="afterPhoneNum" type="text" class="w109" name="afterPhoneNum2"
							v:afterphone m:afterphone="<gm:tagAttb value='jsp.member.join.msg.ph02'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.ph01'/>"
							v:text8_limit="16" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ph04'/>"
							/> <span class="txtcolor01"> * “-”을 생략하고 숫자로만 입력해 주세요. </span>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="moblenum">휴대폰 번호</label></th>
						<td>
							<input type="text" class="w180" value="<g:tagAttb value="${profileInfo.hpNo}"/>" name="profileInfo.hpNo" id="moblenum"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.hp01'/>"
							v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.hp02'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.hp03'/>"/> <span class="txtcolor01"> * “-”을 생략하고 숫자로만 입력해 주세요. </span>
							<input type="hidden" id="prevMoblenum" value="<g:tagAttb value="${profileInfo.hpNo}"/>"/>
						</td>
					</tr>
					</c:if>
				<!-- Foregner -->
					<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
					<tr>
						<th scope="row" class="tit03"><label for="phonenum">전화번호</label></th>
						<td>
							<input type="text" class="w180" value="<g:tagAttb value="${profileInfo.telNo}"/>" name="profileInfo.telNo" id="phonenum"
							v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ph03'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.ph01'/>" />&nbsp;
							<span class="txtcolor01"> * “-”을 생략하고 국가번호와 지역번호를 포함하여 입력해 주세요.</span>
							<input type="hidden" id="prevPhonenum" value="<g:tagAttb value="${profileInfo.telNo}"/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="moblenum">휴대폰 번호</label></th>
						<td>
                            <input type="hidden" id="prevMoblenum" value="<g:tagAttb value="${profileInfo.hpNo}"/>"/>
							<input type="text" class="w180" value="<g:tagAttb value="${profileInfo.hpNo}"/>" name="profileInfo.hpNo" id="moblenum"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.hp03'/>"
							v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.hp02'/>"
						 	v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.hp01'/>" />&nbsp;
							<span class="txtcolor01"> * “-”을 생략하고 국가번호를 포함하여 입력해 주세요.</span>
                        </td>
					</tr>
					</c:if>
                      
					<tr>
						<th class="tit01 tit03" scope="row"><label for="wsite">Website</label></th>
						<td class="bgnone">
							<input type="text" class="w376" value="<g:tagAttb value="${profileInfo.webSiteUrl}"/>" name="profileInfo.webSiteUrl" id="wsite" 
							v:text8_limit="150" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.wb01'/>"/>
							<input type="hidden" id="prevWsite" value="<g:tagAttb value="${profileInfo.webSiteUrl}"/>"/>
						</td>	
					</tr>
				</tbody>
			</table>
		</div>
		
		<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
		<h4 class="h41"><img alt="담당자정보" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_h4_03.gif"/>"></h4>
		
		<div class="tstyleA">
			<table summary="담당자정보 수정">
				<caption>담당자정보 수정</caption>
				<colgroup>
					<col width="15%">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <label for="name">이름</label></th>
						<td>
							<input type="text" class="w180" value="<g:tagAttb value="${profileInfo.opNm}"/>" name="profileInfo.opNm" id="name"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.nm01'/>"
							v:checkspecial  m:checkspecial="<gm:tagAttb value='jsp.member.mypage.msg.com01'/>"
							v:required='trim' m:required="jsp.member.join.msg.nm02"/>
							<input type="hidden" id="prevName" value="<g:tagAttb value="${profileInfo.opNm}"/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="email2">이메일</label></th>
						<td>
							<input type="text" class="w180" value="<g:tagAttb value="${profileInfo.opEmailAddr}"/>" name="profileInfo.opEmailAddr" id="email2" 
							v:required='trim' m:required="jsp.member.join.msg.mgEmail01"
							v:email m:email="jsp.member.join.msg.email04"/>
							<input type="hidden" id="prevEmail" value="<g:tagAttb value="${profileInfo.opEmailAddr}"/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit03"><label for="phonenum">전화번호</label></th>
						<td>
							<input id="opTelNo" type="hidden" name="profileInfo.opTelNo" value="<g:tagAttb value="${profileInfo.opTelNo}"/>"/>
							<div class="fltl pad_r4">
								<select id="phonenum" class="w67" name="phone" v:phone m:phone="<gm:tagAttb value='jsp.member.join.msg.ph05'/>">
									<option value=""></option>
									<gc:codeList group="${CONST.PHONE_AREA_CODE}" var="USCODE"/>
							    	<c:forEach items="${USCODE}" var="us">
										<option value="${us.cdNm}">${us.cdNm}</option>
							    	</c:forEach>
								</select>
							</div>
							<input id="afterPhoneNum" type="text" class="w109" name="afterPhoneNum2"
							v:afterphone m:afterphone="<gm:tagAttb value='jsp.member.join.msg.ph02'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.mgph01'/>"
							v:text8_limit="16" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ph04'/>"
							/><span class="txtcolor01"> * “-”을 생략하고 숫자로만 입력해 주세요.</span>
						</td>
					</tr>
					<tr>
						<th class="tit01" scope="row"><span>*</span> <label for="moblenum">휴대폰 번호</label></th>
						<td class="bgnone">
							<input type="hidden" id="prevMoblenum" value="<g:tagAttb value="${profileInfo.opHpNo}"/>"/>
							<input type="text" class="w180" value="<g:tagAttb value="${profileInfo.opHpNo}"/>" name="profileInfo.opHpNo" id="moblenum"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.mghp01'/>"
							v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.mghp02'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.mghp03'/>"/><span class="txtcolor01"> * “-”을 생략하고 숫자로만 입력해 주세요. </span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</c:if>
	</form>			
		<div class="btnarea mar_t39">
			<input id="btnModify" type="image" alt="수정" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_modify.gif'/>">
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img alt="취소" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>"></a>
		</div>
	
	</div>
	<!-- //CONTENT TABLE E-->

<div id="pop_area01" style="display:none;">
	<h2><img alt="변경 내용 저장 확인" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/mp_title_01.gif'/>"></h2>
	<p class="pop_txt2">변경된 내용이 저장되지 않았습니다. <br>저장을 하지 않고 이동하실 경우 변경된 내용이 삭제됩니다.</p>
	<div class="pop_btn">
		<a href="javascript:saveMoveTabPage();"><img alt="저장 후 이동" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_savemove.gif'/>"></a>
		<a href="javascript:cancelMoveTabPage();"><img alt="변경내용 취소" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_cancle_02.gif'/>"></a>
	</div>
	<p class="pop_close"><a href="javascript:closePopLayer('pop_area');"><img alt="닫기" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif'/>"></a></p>
</div>
</div>