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
		if(showValidate(document.companyForm.emailAddr, 'default', "<gm:string value='jsp.common.msg.title01'/>")){
			var data = $("#companyForm").serialize();
			$.ajax({
				url: env.contextPath + '/member/ajaxEmailCheck.omp',
				dataType: 'json',
				type	: "POST",
				data 	: data,
				async	: false,		
				cache	: false,	
				success: function(json){
					if(json.data == 'SUCCESS'){
						alert("<gm:string value='jsp.member.join.msg.com02'/>");
						$('#duplicateEmailCheck').val('Y');
					}else if(json.data == 'FAIL'){
						alert("<gm:string value='jsp.member.join.msg.com02'/>");
					}
				}
			});
		}
	});
	
	//[Check]  - ID
	$('#idCheck').click(function(){
		if(showValidate(document.companyForm.mbrId, 'default', "<gm:string value="jsp.common.msg.title01"/>")){
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
	$('#companyFormOk').click(function(){
		if(showValidate('companyForm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
			if(!isNull($('#phonenum2').val())){
				$('#opTelNo').val($('#phonenum').val() + '-' + $('#phonenum2').val());
			}
			//[check - websiteUrl]
			if(!$('#webSite').val().substr(7) == ""){
				$('#webSiteUrl').val($('#webSite').val());
			}
			var data = $("#companyForm").serialize();
			$.ajax({
				url: env.contextPath + '/member/ajaxJoinMember.omp',
				dataType: 'json',
				type	: "POST",
				data 	: data,
				async	: false,		
				cache	: false,	
				success: function(json){
					if(json.data.resultMsg == 'SUCCESS'){
						$('#companyForm').attr('action', env.contextPath + '/member/registFinish.omp');
				 		$('#companyForm').submit();
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
		<form id="companyForm" name="companyForm" method="post">			
			<div class="tstyleA mar_b22">
				<table summary="회원기본정보 입력 항목입니다">
					<caption><gm:html value='회원기본정보 입력 항목'/></caption>
					<colgroup>
						<col width="15%" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row"><gm:html value='회사명'/></th>
							<td><g:html  value='${member.mbrNm}' /></td>
							<input type="hidden" value='${member.mbrNm}' name="member.compNm" />
						</tr>   
						<tr>
							<th scope="row"><gm:html value='통일번호'/></th>
							<td><g:html  value='${member.psRegNo}' format="L#************"/></td>
							<input type="hidden" value='${member.psRegNo}' name="member.psRegNo"/>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="userid"><gm:html value='아이디'/></label></th>
							<td><input type="text" id="mbrId" name="member.mbrId" class="w180"
								v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.id01'/>"
								v:regexp="[a-z0-9]{8,13}" m:regexp="<gm:tagAttb value='jsp.member.join.msg.id02'/>"/>&nbsp;
								<input type="hidden" id="duplicateIdCheck" name="duplicateIdCheck" value="N"
								v:scompare="eq,Y" m:scompare="<gm:tagAttb value='jsp.member.join.msg.id03'/>">
							<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/btn_overlap.gif'/>" id="idCheck" alt="중복확인" /></a><span class="txtcolor01"> &nbsp;* 최대 8-13자의 영대소문자, 숫자 가능, 특수문자 사용 불가</span></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="email1"><gm:html value='이메일'/></label></th>
							<td><input type="text" id="emailAddr" name="member.emailAddr" class="w180" v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.email03'/>"
								v:email m:email="<gm:tagAttb value='jsp.member.join.msg.email04'/>" />&nbsp;
								<input type="hidden" id="duplicateEmailCheck" name="duplicateEmailCheck" value="N"
								v:scompare="eq,Y" m:scompare="<gm:tagAttb value='jsp.member.join.msg.email05'/>" />
							<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/btn_overlap.gif'/>" id="emailCheck" alt="중복확인" /></a></td>
						</tr>
						<tr> 
							<th scope="row"><span>*</span> <label for="pwnum"><gm:html value='비밀번호'/></label></th>
							<td><input type="password" id="mbrPw" name="member.mbrPw" class="w180"
								v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.pw01'/>"
								v:regexp="[a-zA-Z0-9\!\@\#\$\%\^\&\*\~]{6,16}" m:regexp="<gm:tagAttb value='jsp.member.join.msg.pw02'/>"/><span class="txtcolor01"> &nbsp;* 6-16자의 영대소문자, 숫자, 특수문자만 사용, 공백입력 불가</span></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="pwnuncon"><gm:html value='비밀번호 확인'/></label></th>
							<td><input type="password" id="pwnuncon" name="pwnuncon" class="w180" maxlength="16" v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.pw03'/>" 
						v:scompare="==,@{member.mbrPw}" m:scompare="<gm:tagAttb value='jsp.member.join.msg.pw04'/>"/></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="city"><gm:html value='도시'/></label></th>
							<td><input type="text" id="city" name="member.city" class="w180"
								v:text8_limit="75" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ct01'/>"
								v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.ct02'/>"
								/></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="address"><gm:html value='주소'/></label></th>
							<td><input type="text" id="homeAddrDtl" name="member.homeAddrDtl" class="w376" 
								v:text8_limit="150" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.addr01'/>" 
								v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.addr02'/>" /></td>
						</tr>
						<tr>
							<th scope="row"><label for="postnum"><gm:html value='우편번호'/></label></th>
							<td><input type="text" id="zipCd" name="member.zipCd" class="w180" maxlength="5"
								v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.zp01'/>" 
								v:regexp="[\d]*" m:regexp="<gm:tagAttb value='jsp.member.join.msg.zp02'/>"/>
								<span class="txtcolor01"> &nbsp;* "-" 을 생략하고 우편번호 5자리를 숫자로만 입력해 주세요.</span>	
							</td>
						</tr>
						<tr>
							<th scope="row" class="tit01"><label for="wsite">Website</label></th>
							<td class="bgnone">
								<input type="text" id="webSite" name="wsite" value="http://" class="w376" 
								v:text8_limit="150" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.wb01'/>" />
								<input type="hidden" name="member.webSiteUrl" id="webSiteUrl">
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h5_03.gif'/>" alt="담당자정보" /></h5>
			<div class="tstyleA">
				<table summary="회원담당자정보 입력 항목입니다">
					<caption><gm:html value='회원담당자정보 입력 항목'/></caption>
					<colgroup>
						<col width="15%" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row"><span>*</span> <label for="name"><gm:html value='이름'/></label></th>
							<td><input type="text" id="name" name="member.opNm" class="w180" 
								v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.nm01'/>"
								v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.nm02'/>"/></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="email2"><gm:html value='이메일'/></label></th>
							<td><input type="text" id="email2" name="member.opEmailAddr" class="w180" v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.mgEmail01'/>"
				               v:email m:email="<gm:tagAttb value='jsp.member.join.msg.email04'/>" /></td>
						</tr>
						<tr>
							<th scope="row"><label for="phonenum"><gm:html value='전화번호'/></label></th>
							<td>
								<div class="fltl pad_r4">
								<select id="phonenum" class=w67>
									<option value=""></option>
									<gc:options group="${CONST.PHONE_AREA_CODE}" codeType="full"/>
								</select>
								</div>
								<input type="text" class="w109" title="전화번호 뒷자리"
								v:regexp="[\d]*" m:regexp="<gm:tagAttb value='jsp.member.join.msg.mgph01'/>" id="phonenum2"/>
								<input type="hidden" name="member.opTelNo" id="opTelNo"/>
								<span class="txtcolor01"> &nbsp;* "-" 을 생략하고 숫자로만 입력해 주세요.</span>
							</td>
						</tr>
						<tr>
							<th scope="row" class="tit01"><span>*</span> <label for="moblenum"><gm:html value='휴대폰 번호'/></label></th>
							<td class="bgnone">
								<input type="text" id="moblenum" name="member.opHpNo" class="w180"
								v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.mghp01'/>"
								v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.mghp02'/>"
								v:regexp="[\d]*" maxlength="20" m:regexp="<gm:tagAttb value='jsp.member.join.msg.mghp03'/>"/>
								<input type="hidden" value="<g:tagAttb value='${member.mbrClsCd}'/>" name="member.mbrClsCd" id="mbrClsCd"/>
								<span class="txtcolor01"> &nbsp;* "-" 을 생략하고 숫자로만 입력해 주세요.</span>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</form>
			<div class="btnarea mar_t30">
				<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm1.gif'/>" id="companyFormOk" alt="확인" />
			</div>

		</div>
		<!-- //CONTENT TABLE E-->

	</div>
	<!-- //Content Area E -->