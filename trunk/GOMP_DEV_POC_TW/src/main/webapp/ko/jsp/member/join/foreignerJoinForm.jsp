<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
/*
 * Init 
 */
$(document).ready(function(){
	//[Check] - Email
	$('#emailCheck').click(function(){
		// 이메일형식 체크
		if(showValidate(document.foreignerForm.emailAddr, 'default', "<gm:string value='jsp.common.msg.title01'/>")){
			var data = $("#foreignerForm").serialize();
			$.ajax({
				url: env.contextPath + '/member/ajaxEmailCheck.omp',
				dataType: 'json',
				type	: "POST",
				data 	: data,
				async	: false,		
				cache	: false,	
				success: function(json){
					if(json.data == 'SUCCESS'){
						alert("<gm:string value='jsp.member.join.msg.email01'/>");
						$('#duplicateEmailCheck').val('Y');
					}else if(json.data == 'FAIL'){
						alert("<gm:string value='jsp.member.join.msg.email02'/>");
					}
				}
			});
		}
	});
	
	//[Check]  - ID
	$('#idCheck').click(function(){
		if(showValidate(document.foreignerForm.mbrId, 'default', "<gm:string value='jsp.common.msg.title01'/>")){
			$.ajax({
				type: "POST",
				url: env.contextPath + '/member/ajaxDuplicateIdCheck.omp',
				data: {mbrId : $('#mbrId').val()},
				beforeSend: function(xhr) {},
				success: function(data) {
					createPopupLayer('popupDuplicateIdCheck');
					$("#popupDuplicateIdCheck_body").html(data);
					showPopupLayer('popupDuplicateIdCheck', 'wrap');			
				}
			});
		}
	});
	
	//[JOIN] - OK
	$('#foreignerFormOk').click(function(){
		if(showValidate('foreignerForm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
			//[check - websiteUrl]
			if(!$('#webSite').val().substr(7) == ""){
				$('#webSiteUrl').val($('#webSite').val());
			}
			var data = $("#foreignerForm").serialize();
			$.ajax({
				url: env.contextPath + '/member/ajaxJoinMember.omp',
				dataType: 'json',
				type	: "POST",
				data 	: data,
				async	: false,		
				cache	: false,	
				success: function(json){
					if(json.data.resultMsg == 'SUCCESS'){
						$('#foreignerForm').attr('action', env.contextPath + '/member/registFinish.omp');
				 		$('#foreignerForm').submit();
					}else if(json.data.resultMsg == 'FAIL'){
						//alert('FAIL');
						return;
					}
				}
			});
		}
	});
	
});

</script>
<!-- Content Area S -->
	<h2 class="hide">본문영역</h2>
	<div id="contents_area" class="ut_area">
		<!-- Title Area S -->
		<div id="ctitle_area">
			<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_title01.gif'/>" alt="회원가입" /></h3>
		</div>
		<!-- //Title Area E -->
		
		<!-- CONTENT TABLE S-->
		<div id="utcontents">
			
			<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h4_04.gif'/>" alt="회원정보 입력 - 회원가입을 위해 아래  회원정보를 입력해주시기 바랍니다." /></h4>
			<!-- Tab_menu S -->
			<div class="tab01 mar_b22">
				<ul>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab01.gif'/>" alt="회원종류 선택" /></li>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab02.gif'/>" alt="가입 여부 확인" /></li>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab03_01.gif'/>" alt="약관동의" /></li>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab04_on.gif'/>" alt="회원정보 입력" /></li>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab05.gif'/>" alt="회원가입 완료" /></li>
				</ul>
			</div>
			<!-- //Tab_menu E -->
			<h5 class="h52 fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h5_02.gif'/>" alt="기본정보" /></h5>
			<p class="txtr txt_90"><span class="txtcolor03">*</span> 가 표시된 부분은 필수입력 항목입니다.</p>
			<div class="tstyleA">
			<form id="foreignerForm" name="foreignerForm" method="post">
				<table summary="회원기본정보 입력 항목입니다">
					<caption><gm:html value='회원기본정보 입력 항목'/></caption>
					<colgroup>
						<col width="15%" />
						<col />
					</colgroup>
					<tbody>   
						<tr>   
							<th scope="row"><gm:html value='이름'/></th>
							<td><g:html  value='${member.mbrNm}' /></td>
							<input type="hidden" value='${member.mbrNm}' name="member.mbrNm" id=""/>
						</tr>
						<tr>
							<th scope="row"><gm:html value='여권번호'/></th>
							<td><g:html  value='${member.psRegNo}' format="L#*******"/></td>
							<input type="hidden" value='${member.psRegNo}' name="member.psRegNo" />
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="userid"><gm:html value='아이디'/></label></th>
							<td><input type="text" id="mbrId" name="member.mbrId" class="w180" maxlength="13"
							     v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.id01'/>" 
							     v:regexp="[a-z0-9]{8,13}" m:regexp="<gm:tagAttb value='jsp.member.join.msg.id02'/>"/>&nbsp;
								<input type="hidden" id="duplicateIdCheck" name="duplicateIdCheck" value="N"
				                 v:scompare="eq,Y" m:scompare="<gm:tagAttb value='jsp.member.join.msg.id03'/>">
							<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/btn_overlap.gif'/>" id="idCheck" alt="중복확인" /></a><span class="txtcolor01"> &nbsp;* 최대 8-13자의 영소문자, 숫자 가능, 특수문자 사용 불가</span></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="email1"><gm:html value='이메일'/></label></th>
							<td><input type="text" id="emailAddr" name="member.emailAddr" class="w180" v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.email03'/>"
				               v:email m:email="<gm:tagAttb value='jsp.member.join.msg.email04'/>" />&nbsp;
				               <input type="hidden" id="duplicateEmailCheck" name="duplicateEmailCheck" value="N"
								v:scompare="eq,Y" m:scompare="<gm:tagAttb value='jsp.member.join.msg.email05'/>">
							<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/btn_overlap.gif'/>" id="emailCheck" alt="중복확인" /></a></td>
						</tr>
						<tr> 
							<th scope="row"><span>*</span> <label for="pwnum"><gm:html value='비밀번호'/></label></th>
							<td><input type="password" id="mbrPw" name="member.mbrPw" class="w180" maxlength="16" 
								v:regexp="[a-zA-Z0-9\!\@\#\$\%\^\&\*\~]{6,16}" m:regexp="<gm:tagAttb value='jsp.member.join.msg.pw02'/>"
								v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.pw01'/>"/><span class="txtcolor01"> &nbsp;* 6-16자의 영대소문자, 숫자, 특수문자만 사용, 공백입력 불가</span></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="pwnuncon"><gm:html value='비밀번호 확인'/></label></th>
							<td><input type="password" id="pwnuncon" name="pwnuncon" class="w180" maxlength="16" v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.pw03'/>" 
						         maxlength="16" v:scompare="==,@{member.mbrPw}" m:scompare="<gm:tagAttb value='jsp.member.join.msg.pw04'/>" /></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="nation"><gm:html value='국가'/></label></th>
							<td>
							<select id="naCd" name="member.naCd" class="w188">
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
						<tr>
							<th scope="row"><span>*</span> <label for="city"><gm:html value='도시'/></label></th>
							<td><input type="text" id="city" name="member.city" class="w180"
								v:text8_limit="75" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ct01'/>" 
							    v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.ct02'/>"/></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="address"><gm:html value='주소'/></label></th>
							<td><input type="text" id="homeAddrDtl" name="member.homeAddrDtl" class="w376"
								v:text8_limit="150" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.addr01'/>" 
								v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.addr02'/>" /></td>
						</tr>
						<tr>
							<th scope="row"><label for="postnum"><gm:html value='우편번호'/></label></th>
							<td><input type="text" id="zipCd" name="member.zipCd" class="w180"
								v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.zp01'/>"
								v:regexp="[\d]*" m:regexp="<gm:tagAttb value='jsp.member.join.msg.zp02'/>"/>
							</td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="phonenum"><gm:html value='전화번호'/></label></th>
							<td><input type="text" id="telNo" name="member.telNo" class="w180" 
								v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.ph02'/>"
								v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ph03'/>"
								v:regexp="[\d]*" m:regexp="<gm:tagAttb value='jsp.member.join.msg.ph01'/>" />
							<span class="txt_90">&nbsp;ex. 국가번호 + 지역번호 + 전화번호</span></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="moblenum"><gm:html value='휴대폰 번호'/></label></th>
							<td>
								<input type="text" id="devHpNo" name="member.hpNo" class="w180" 
								v:regexp="[\d]*" m:regexp="<gm:tagAttb value='jsp.member.join.msg.hp03'/>"
								v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.hp02'/>"
							 	v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.hp01'/>" /> <span class="txt_90">&nbsp;ex. 국가번호 + 휴대폰 번호</span>
							 </td>
						</tr>
						<tr>
							<th scope="row" class="tit01"><label for="wsite">Website</label></th>
							<td class="bgnone"><input type="text" id="webSite" name="webSite" value="http://" class="w376" 
								v:text8_limit="150" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.wb01'/>" />
								<input type="hidden" id="webSiteUrl" name="member.webSiteUrl"/>
							</td>
						</tr>
					</tbody>
				</table>
				<input type="hidden" value="<g:tagAttb value='${member.mbrClsCd}'/>" name="member.mbrClsCd" id="mbrClsCd"/>
			</form>
			</div>
			<div class="btnarea mar_t30">
				<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm1.gif'/>" id="foreignerFormOk" alt="확인" />
			</div>

		</div>
		<!-- //CONTENT TABLE E-->

	</div>
	<!-- //Content Area E -->