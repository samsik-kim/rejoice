<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
$(document).ready(function(){ 
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

// 레이어 취소 버튼 클릭
function onclickCancel() {
	showConfirmLayer('pop_area01','wrap',350);
}

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
			url: env.contextPath + '/mypage/ajaxMypageProfileModify.omp',
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
			showConfirmLayer('pop_area01','wrap',350);
		}
		else {
			if(target == "CALCULATE") {
				$("#frmProfile").attr('action', env.contextPath + '/mypage/mypageCalculateInfoView.omp');
				$("#frmProfile").submit();
				//location.href = env.contextPath + "/mypage/mypageCalculateInfoView.omp";
			}
		}
	}
}

// 저장 취소 후 탭 이동
function cancelMoveTabPage() {
	if($("#forwardAction").val() == "CALCULATE") {
		$("#frmProfile").attr('action', env.contextPath + '/mypage/mypageCalculateInfoView.omp');
		$("#frmProfile").submit();
		//location.href = env.contextPath + "/mypage/mypageCalculateInfoView.omp";
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
			url: env.contextPath + '/mypage/ajaxMypageProfileModify.omp',
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
		$("#afterPhoneNum").replace("-","");
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
		<input type="hidden" name="isProfile" value="${isProfile }"/>
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
						<th scope="row">회원타입</th>
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
						<th scope="row">멤버십 구분</th>
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
							<th scope="row">이름</th>
							<td><g:html value="${profileInfo.mbrNm}"/></td>
						</c:if>
						<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
							<th scope="row">회사명</th>
							<td><g:html value="${profileInfo.compNm}"/></td>
						</c:if>
						<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
							<th scope="row">이름</th>
							<td><g:html value="${profileInfo.mbrNm}"/></td>
						</c:if>
					</tr>
					  
					<tr>
						<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
							<th scope="row">Taiwan ID</th>
							<td><g:html  value='${profileInfo.psRegNo}' format="L##***********"/></td>
						</c:if>
						<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
							<th scope="row">통일번호</th>
							<td><g:html  value='${profileInfo.psRegNo}' format="L##***********"/></td>
						</c:if>
						<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
							<th scope="row">여권번호</th>
							<td><g:html  value='${profileInfo.psRegNo}' format="L##***********"/></td>
						</c:if>
					</tr>
					
					<tr>
						<th scope="row">아이디</th>
						<td>
							<g:html value="${profileInfo.mbrId}"/>
							<input type="hidden" name="profileInfo.mbrId" value="${profileInfo.mbrId}"/>
						</td>
					</tr>
					
					<tr>
						<th scope="row"><span>*</span> <label for="email1">이메일</label></th>
						<td><g:html value="${profileInfo.emailAddr}"/>&nbsp;
						<a href="<c:url value='/mypage/mypageEmail.omp'/>"><img alt="이메일 변경" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/btn_email.gif'/>"></a></td>
					</tr>
					
					<tr> 
						<th scope="row"><span>*</span> <label for="pwnum">비밀번호</label></th>
						<td>
							<input type="password" class="w180" value="${profileInfo.mbrPw}" name="profileInfo.mbrPw" id="pwnum" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.pw02'/>" 
							v:regexp="[a-zA-Z0-9\!\@\#\$\%\^\&\*\~]{6,16}" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.pw03'/>"/>
				            <span class="txtcolor01"> * 6-16자의 영대소문자, 숫자, 특수문자만 사용, 공백입력 불가</span>
							<input type="hidden" id="prevMbrPw" value="${profileInfo.mbrPw}"/>
						</td>
					</tr>
					
					<tr>
						<th scope="row"><span>*</span> <label for="pwnuncon">비밀번호 확인</label></th>
						<td><input type="password" class="w180" value="${profileInfo.mbrPw}" id="pwnuncon" 
							v:required='trim' m:required="jsp.member.mypage.msg.pw05" 
							v:scompare="==,@{profileInfo.mbrPw}" m:scompare="jsp.member.mypage.msg.pw06"/></td>
					</tr>
					
					<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
					<tr>
						<input id="naCd" type="hidden" value="${profileInfo.naCd}"/>
						<th scope="row"><span>*</span> <label for="pwnuncon">국가</label></th>
						<td>
							<select id="country" name="profileInfo.naCd" class="w232">  
								<option value="Afghanistan">Afghanistan</option>
								<option value="Albania">Albania</option>
								<option value="Algeria">Algeria</option>
								<option value="American Samoa">American Samoa</option>
								<option value="Andorra">Andorra</option>
								<option value="Angola">Angola</option>
								<option value="Anguilla">Anguilla</option>
								<option value="Antarctica">Antarctica</option>
								<option value="Antigua and Barbuda">Antigua and Barbuda</option>
								<option value="Argentina">Argentina</option>
								<option value="Armenia">Armenia</option>
								<option value="Aruba">Aruba</option>
								<option value="Ascension Island">Ascension Island</option>
								<option value="Australia">Australia</option>
								<option value="Austria">Austria</option>
								<option value="Azerbaijan">Azerbaijan</option>
								<option value="Bahamas">Bahamas</option>
								<option value="Bahrain">Bahrain</option>
								<option value="Bangladesh">Bangladesh</option>
								<option value="Barbados">Barbados</option>
								<option value="Belarus">Belarus</option>
								<option value="Belgium">Belgium</option>
								<option value="Belize">Belize</option>
								<option value="Benin">Benin</option>
								<option value="Bermuda">Bermuda</option>
								<option value="Bhutan">Bhutan</option>
								<option value="Bolivia">Bolivia</option>
								<option value="Bosnia and Herzegovina">Bosnia and Herzegovina</option>
								<option value="Botswana">Botswana</option>
								<option value="Brazil">Brazil</option>
								<option value="British Virgin Islands">British Virgin Islands</option>
								<option value="Brunei">Brunei</option>
								<option value="Bulgaria">Bulgaria</option>
								<option value="Burkina Faso">Burkina Faso</option>
								<option value="Burundi">Burundi</option>
								<option value="Cambodia">Cambodia</option>
								<option value="Cameroon">Cameroon</option>
								<option value="Canada">Canada</option>
								<option value="Cayman Islands">Cayman Islands</option>
								<option value="Central African Republic">Central African Republic</option>
								<option value="Chad">Chad</option>
								<option value="Chile">Chile</option>
								<option value="China">China</option>
								<option value="Colombia">Colombia</option>
								<option value="Comoros">Comoros</option>
								<option value="Congo">Congo</option>
								<option value="Cook Islands">Cook Islands</option>
								<option value="Costa Rica">Costa Rica</option>
								<option value="Croatia">Croatia</option>
								<option value="Cuba">Cuba</option>
								<option value="Cyprus">Cyprus</option>
								<option value="Czech Republic">Czech Republic</option>
								<option value="Democratic Republic of Congo">Democratic Republic of Congo</option>
								<option value="Denmark">Denmark</option>
								<option value="Diego Garcia">Diego Garcia</option>
								<option value="Djibouti">Djibouti</option>
								<option value="Dominican Republic">Dominican Republic</option>
								<option value="East Timor">East Timor</option>
								<option value="Ecuador">Ecuador</option>
								<option value="Egypt">Egypt</option>
								<option value="El Salvador">El Salvador</option>
								<option value="Eritrea">Eritrea</option>
								<option value="Estonia">Estonia</option>
								<option value="Ethiopia">Ethiopia</option>
								<option value="Falkland Islands">Falkland Islands</option>
								<option value="Faroe Islands">Faroe Islands</option>
								<option value="Fiji">Fiji</option>
								<option value="Finland">Finland</option>
								<option value="France">France</option>
								<option value="French Guiana">French Guiana</option>
								<option value="French Polynesia">French Polynesia</option>
								<option value="Gabon">Gabon</option>
								<option value="Gambia">Gambia</option>
								<option value="Georgia">Georgia</option>
								<option value="Germany">Germany</option>
								<option value="Ghana">Ghana</option>
								<option value="Gibraltar">Gibraltar</option>
								<option value="Greece">Greece</option>
								<option value="Greenland">Greenland</option>
								<option value="Grenada">Grenada</option>
								<option value="Guadeloupe">Guadeloupe</option>
								<option value="Guam">Guam</option>
								<option value="Guatemala">Guatemala</option>
								<option value="Guinea">Guinea</option>
								<option value="Guinea-Bissau">Guinea-Bissau</option>
								<option value="Guyana">Guyana</option>
								<option value="Haiti">Haiti</option>
								<option value="Honduras">Honduras</option>
								<option value="Hong Kong">Hong Kong</option>
								<option value="Hungary">Hungary</option>
								<option value="Iceland">Iceland</option>
								<option value="India">India</option>
								<option value="Indonesia">Indonesia</option>
								<option value="Iraq">Iraq</option>
								<option value="Ireland">Ireland</option>
								<option value="Islamic Republic of Iran">Islamic Republic of Iran</option>
								<option value="Israel">Israel</option>
								<option value="Italy">Italy</option>
								<option value="Ivory Coast">Ivory Coast</option>
								<option value="Jamaica">Jamaica</option>
								<option value="Japan">Japan</option>
								<option value="Jordan">Jordan</option>
								<option value="Kazakhstan">Kazakhstan</option>
								<option value="Kenya">Kenya</option>
								<option value="Kiribati">Kiribati</option>
								<option value="Kuwait">Kuwait</option>
								<option value="Kyrgyzstan">Kyrgyzstan</option>
								<option value="Laos">Laos</option>
								<option value="Latvia">Latvia</option>
								<option value="Lebanon">Lebanon</option>
								<option value="Lesotho">Lesotho</option>
								<option value="Liberia">Liberia</option>
								<option value="Libya">Libya</option>
								<option value="Liechtenstein">Liechtenstein</option>
								<option value="Lithuania">Lithuania</option>
								<option value="Luxembourg">Luxembourg</option>
								<option value="Macau">Macau</option>
								<option value="Macedonia">Macedonia</option>
								<option value="Madagascar">Madagascar</option>
								<option value="Malawi">Malawi</option>
								<option value="Malaysia">Malaysia</option>
								<option value="Maldives">Maldives</option>
								<option value="Mali">Mali</option>
								<option value="Malta">Malta</option>
								<option value="Marshall Islands">Marshall Islands</option>
								<option value="Martinique">Martinique</option>
								<option value="Mauritania">Mauritania</option>
								<option value="Mauritius">Mauritius</option>
								<option value="Mayotte">Mayotte</option>
								<option value="Mexico">Mexico</option>
								<option value="Micronesia">Micronesia</option>
								<option value="Moldova">Moldova</option>
								<option value="Monaco">Monaco</option>
								<option value="Mongolia">Mongolia</option>
								<option value="Montenegro">Montenegro</option>
								<option value="Montserrat">Montserrat</option>
								<option value="Morocco">Morocco</option>
								<option value="Mozambique">Mozambique</option>
								<option value="Myanmar">Myanmar</option>
								<option value="Namibia">Namibia</option>
								<option value="Nepal">Nepal</option>
								<option value="Netherlands">Netherlands</option>
								<option value="Netherlands Antilles">Netherlands Antilles</option>
								<option value="New Caledonia">New Caledonia</option>
								<option value="New Zealand">New Zealand</option>
								<option value="Nicaragua">Nicaragua</option>
								<option value="Niger">Niger</option>
								<option value="Nigeria">Nigeria</option>
								<option value="Niue">Niue</option>
								<option value="North Korea">North Korea</option>
								<option value="Northern Cyprus">Northern Cyprus</option>
								<option value="Northern Mariana Islands">Northern Mariana Islands</option>
								<option value="Norway">Norway</option>
								<option value="Oman">Oman</option>
								<option value="Pakistan">Pakistan</option>
								<option value="Palau">Palau</option>
								<option value="Palestinian Occupied Territories">Palestinian Occupied Territories</option>
								<option value="Panama">Panama</option>
								<option value="Papua New Guinea">Papua New Guinea</option>
								<option value="Paraguay">Paraguay</option>
								<option value="Peru">Peru</option>
								<option value="Philippines">Philippines</option>
								<option value="Poland">Poland</option>
								<option value="Portugal">Portugal</option>
								<option value="Puerto Rico">Puerto Rico</option>
								<option value="Qatar">Qatar</option>
								<option value="Republic of Nauru">Republic of Nauru</option>
								<option value="Reunion">Reunion</option>
								<option value="Romania">Romania</option>
								<option value="Russia">Russia</option>
								<option value="Rwanda">Rwanda</option>
								<option value="Saint Helena">Saint Helena</option>
								<option value="Saint Kitts and Nevis">Saint Kitts and Nevis</option>
								<option value="Saint Lucia">Saint Lucia</option>
								<option value="Saint Vincent and the Grenadines">Saint Vincent and the Grenadines</option>
								<option value="Samoa">Samoa</option>
								<option value="San Marino">San Marino</option>
								<option value="Sao Tome and Principe">Sao Tome and Principe</option>
								<option value="Saudi Arabia">Saudi Arabia</option>
								<option value="Senegal">Senegal</option>
								<option value="Serbia">Serbia</option>
								<option value="Seychelles">Seychelles</option>
								<option value="Sierra Leone">Sierra Leone</option>
								<option value="Singapore">Singapore</option>
								<option value="Slovakia">Slovakia</option>
								<option value="Slovenia">Slovenia</option>
								<option value="Solomon Islands">Solomon Islands</option>
								<option value="Somalia">Somalia</option>
								<option value="South Africa">South Africa</option>
								<option value="South Korea">South Korea</option>
								<option value="Spain">Spain</option>
								<option value="Sri Lanka">Sri Lanka</option>
								<option value="Sultan">Sultan</option>
								<option value="Suriname">Suriname</option>
								<option value="Swaziland">Swaziland</option>
								<option value="Sweden">Sweden</option>
								<option value="Switzerland">Switzerland</option>
								<option value="Syria">Syria</option>
								<option value="Taiwan">Taiwan</option>
								<option value="Tajikistan">Tajikistan</option>
								<option value="Tanzania">Tanzania</option>
								<option value="Thailand">Thailand</option>
								<option value="The Commonwealth of Dominica">The Commonwealth of Dominica</option>
								<option value="The Islands of StPierre and Miquelon">The Islands of StPierre and Miquelon</option>
								<option value="The Republic of Cape Verde">The Republic of Cape Verde</option>
								<option value="The Republic of Equatorial Guinea">The Republic of Equatorial Guinea</option>
								<option value="Togo">Togo</option>
								<option value="Tokelau">Tokelau</option>
								<option value="Tonga">Tonga</option>
								<option value="Trinidad and Tobago">Trinidad and Tobago</option>
								<option value="Tunisia">Tunisia</option>
								<option value="Turkey">Turkey</option>
								<option value="Turkmenistan">Turkmenistan</option>
								<option value="Turks And Caicos Islands">Turks And Caicos Islands</option>
								<option value="Tuvalu">Tuvalu</option>
								<option value="US Virgin Islands">US Virgin Islands</option>
								<option value="Uganda">Uganda</option>
								<option value="Ukraine">Ukraine</option>
								<option value="United Arab Emirates">United Arab Emirates</option>
								<option value="United Kingdom">United Kingdom</option>
								<option value="United States">United States</option>
								<option value="Uruguay">Uruguay</option>
								<option value="Uzbekistan">Uzbekistan</option>
								<option value="Vanuatu">Vanuatu</option>
								<option value="Vatican">Vatican</option>
								<option value="Venezuela">Venezuela</option>
								<option value="Vietnam">Vietnam</option>
								<option value="Wallis and Futuna">Wallis and Futuna</option>
								<option value="Yemen">Yemen</option>
								<option value="Zambia">Zambia</option>
								<option value="Zanzibar">Zanzibar</option>
								<option value="Zimbabwe">Zimbabwe</option>
							</select>
						</td>
					</tr>
					</c:if>
					
					<tr>
						<th scope="row"><span>*</span> <label for="city">도시</label></th>
						<td>
							<input type="text" class="w180" value="${profileInfo.city}" name="profileInfo.city" id="city"
							v:text8_limit="75" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ct01'/>"  
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.ct02'/>"/>
							<input type="hidden" id="prevCity" value="${profileInfo.city}"/>
						</td>
					</tr>
					
					<tr>
						<th scope="row"><span>*</span> <label for="address">주소</label></th>
						<td>
							<input type="text" class="w376" value="${profileInfo.homeAddrDtl}" name="profileInfo.homeAddrDtl" id="address"
							v:text8_limit="150" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.addr01'/>" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.addr02'/>" />
							<input type="hidden" id="prevAddress" value="${profileInfo.homeAddrDtl}"/>
						</td>
					</tr>
					
					<tr>
						<th scope="row"><label for="postnum">우편번호</label></th>
						<td>
							<input type="text" class="w180" value="${profileInfo.zipCd}" name="profileInfo.zipCd" id="postnum" 
							v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.zp01'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.zp02'/>"/>
							<input type="hidden" id="prevPostNum" value="${profileInfo.zipCd}"/>
						</td>
					</tr>
					
					<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
					<tr>
						<th scope="row"><label for="phonenum">전화번호</label></th>
						<td>
							<input id="telNo" type="hidden" name="profileInfo.telNo" value="${profileInfo.telNo}"/>
							<div class="fltl pad_r4">
								<select id="phonenum" class="w67">
									<option value=""></option>
									<gc:options group="US0054" codeType="full"/>
								</select>
							</div>
							<input id="afterPhoneNum" type="text" title="전화번호 뒷자리" class="w109"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.ph01'/>" id="phonenum2"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="moblenum">휴대폰 번호</label></th>
						<td>
							<input type="text" class="w180" value="${profileInfo.hpNo}" name="profileInfo.hpNo" id="moblenum"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.hp01'/>"
							v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.hp02'/>"
							v:regexp="[\d]+" maxlength="20" m:regexp="<gm:tagAttb value='jsp.member.join.msg.hp03'/>"/>
							<input type="hidden" id="prevMoblenum" value="${profileInfo.hpNo}"/>
						</td>
					</tr>
					</c:if>
				<!-- Foregner -->
					<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
					<tr>
						<th scope="row"><label for="phonenum">전화번호</label></th>
						<td>
							<input type="text" class="w180" value="${profileInfo.telNo}" name="profileInfo.telNo" id="phonenum"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.ph02'/>" 
							v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ph03'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.ph01'/>" />&nbsp;
                                  <span class="txt_90"> ex. 국가번호 + 지역번호 + 전화번호 </span>
							<input type="hidden" id="prevPhonenum" value="${profileInfo.telNo}"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="moblenum">휴대폰 번호</label></th>
						<td><input type="text" class="w180" value="${profileInfo.hpNo}" name="profileInfo.hpNo" id="moblenum"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.hp03'/>"
							v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.hp02'/>"
						 	v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.hp01'/>" />&nbsp;
                              <span class="txt_90"> ex. 국가번호 + 휴대폰 번호</span>
                            <input type="hidden" id="prevMoblenum" value="${profileInfo.hpNo}"/>
                        </td>
					</tr>
					</c:if>
                      
					<tr>
						<th class="tit01" scope="row"><label for="wsite">Website</label></th>
						<td class="bgnone">
							<input type="text" class="w376" value="${profileInfo.webSiteUrl}" name="profileInfo.webSiteUrl" id="wsite" 
							v:text8_limit="150" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.wb01'/>"/>
							<input type="hidden" id="prevWsite" value="${profileInfo.webSiteUrl}"/>
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
							<input type="text" class="w180" value="${profileInfo.opNm}" name="profileInfo.opNm" id="name"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.nm01'/>"
							v:checkspecial  m:checkspecial="<gm:tagAttb value='jsp.member.mypage.msg.com01'/>"
							v:required='trim' m:required="jsp.member.join.msg.nm02"/>
							<input type="hidden" id="prevName" value="${profileInfo.opNm}"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="email2">이메일</label></th>
						<td>
							<input type="text" class="w180" value="${profileInfo.opEmailAddr}" name="profileInfo.opEmailAddr" id="email2" 
							v:required='trim' m:required="jsp.member.join.msg.mgEmail01"
							v:email m:email="jsp.member.join.msg.email04"/>
							<input type="hidden" id="prevEmail" value="${profileInfo.opEmailAddr}"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="phonenum">전화번호</label></th>
						<td>
							<input id="opTelNo" type="hidden" name="profileInfo.opTelNo" value="${profileInfo.opTelNo}"/>
							<div class="fltl pad_r4">
								<select id="phonenum" class="w67">
									<option value="">선택</option>
									<gc:options group="US0054" codeType="full"/>
								</select>
							</div>
							<input id="afterPhoneNum" type="text" title="전화번호 뒷자리" class="w109"
							v:regexp="[\d]+" m:regexp="담당자 전화번호는  숫자,- 이어야 합니다."/>
						</td>
					</tr>
					<tr>
						<th class="tit01" scope="row"><span>*</span> <label for="moblenum">휴대폰 번호</label></th>
						<td class="bgnone">
							<input type="text" class="w180" value="${profileInfo.opHpNo}" name="profileInfo.opHpNo" id="moblenum"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.mghp01'/>"
							v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.mghp02'/>"
							v:regexp="[\d]+" maxlength="20" m:regexp="<gm:tagAttb value='jsp.member.join.msg.mghp03'/>"/>
							<input type="hidden" id="prevMoblenum" value="${profileInfo.opHpNo}"/>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</c:if>
	</form>			
		<div class="btnarea mar_t39">
			<input id="btnModify" type="image" alt="수정" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_modify.gif'/>">
			<a href="javascript:gotoPage('MAIN');"><img alt="취소" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>"></a>
		</div>
	
	</div>
	<!-- //CONTENT TABLE E-->

<div id="pop_area01" style="display:none; position: absolute;">
	<h2><img alt="변경 내용 저장 확인" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/mp_title_01.gif'/>"></h2>
	<p class="pop_txt2">변경된 내용이 저장되지 않았습니다. <br>저장을 하지 않고 이동하실 경우 변경된 내용이 삭제됩니다.</p>
	<div class="pop_btn">
		<a href="javascript:saveMoveTabPage();"><img alt="저장 후 이동" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_savemove.gif'/>"></a>
		<a href="javascript:cancelMoveTabPage();"><img alt="변경내용 취소" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_cancle_02.gif'/>"></a>
	</div>
	<p class="pop_close"><a href="javascript:closePopLayer('pop_area01');"><img alt="닫기" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif'/>"></a></p>
</div>
</div>