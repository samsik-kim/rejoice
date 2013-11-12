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
	
	addValidateFunction({
		reqnotuse	:	function(c){
			
			if($(c).val() == $("#psregno").val()){
				return false;
			}else if($("#telNo").val() != ''){
				if($(c).val() == $("#telNo").val()) return false;
				else return true;
			}else if($(c).val() == $("#mbrNm").val()){
				return false;
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
			<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_title01.gif'/>" alt="會員註冊" /></h3>
		</div>
		<!-- //Title Area E -->
		
		<!-- CONTENT TABLE S-->
		<div id="utcontents">
			
			<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h4_04.gif'/>" alt="填寫會員資料 - 請填寫基本資料以加入開發商專區會員!" /></h4>
			<!-- Tab_menu S -->
			<div class="tab01 mar_b22">
				<ul>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab01.gif'/>" alt="選擇會員類別" /></li>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab02.gif'/>" alt="確認註冊與否" /></li>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab03_01.gif'/>" alt="同意會員條款" /></li>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab04_on.gif'/>" alt="填寫會員資料" /></li>
					<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_tab05.gif'/>" alt="會員註冊完成" /></li>
				</ul>
			</div>
			<!-- //Tab_menu E -->
			<h5 class="h52 fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/ut_h5_02.gif'/>" alt="基本資料" /></h5>
			<p class="txtr txt_90"><span class="txtcolor03">*</span> 為必填欄位</p>
			<div class="tstyleA">
			<form id="foreignerForm" name="foreignerForm" method="post">
				<table summary="基本資料">
					<caption>基本資料</caption>
					<colgroup>
						<col width="15%" />
						<col />
					</colgroup>
					<tbody>   
						<tr>   
							<th scope="row" class="tit03">姓名</th>
							<td><g:html  value='${member.mbrNm}' /></td>
							<input type="hidden" value="<g:tagAttb value="${member.mbrNm}"/>" name="member.mbrNm" id="mbrNm"/>
						</tr>
						<tr>
							<th scope="row" class="tit03">護照號碼</th>
							<td><g:html  value='${member.psRegNo}' format="L#*******"/></td>
							<input type="hidden" value="<g:tagAttb value="${member.psRegNo}"/>" name="member.psRegNo" id="psregno"/>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="userid">自訂帳號</label></th>
							<td><input type="text" id="mbrId" name="member.mbrId" class="w180" maxlength="13"
							     v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.id01'/>" 
							     v:regexp="[a-z0-9]{8,13}" m:regexp="<gm:tagAttb value='jsp.member.join.msg.id02'/>"/>&nbsp;
								<input type="hidden" id="duplicateIdCheck" name="duplicateIdCheck" value="N"
				                 v:scompare="eq,Y" m:scompare="<gm:tagAttb value='jsp.member.join.msg.id03'/>">
							<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/btn_overlap.gif'/>" id="idCheck" alt="檢查重複" /></a><span class="txtcolor01"> &nbsp;* 小寫英文及數字共8~13碼(不可使用特殊符號)</span></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="email1">電子郵件</label></th>
							<td><input type="text" id="emailAddr" name="member.emailAddr" class="w180" v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.email03'/>"
				               v:email m:email="<gm:tagAttb value='jsp.member.join.msg.email04'/>" />&nbsp;
				               <input type="hidden" id="duplicateEmailCheck" name="duplicateEmailCheck" value="N"
								v:scompare="eq,Y" m:scompare="<gm:tagAttb value='jsp.member.join.msg.email05'/>">
							<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/ut/btn_overlap.gif'/>" id="emailCheck" alt="檢查重複" /></a></td>
						</tr>
						<tr> 
							<th scope="row"><span>*</span> <label for="pwnum">自訂密碼</label></th>
							<td><input type="password" id="mbrPw" name="member.mbrPw" class="w180" maxlength="16"
								v:reqnotuse m:reqnotuse="<gm:tagAttb value='jsp.member.join.msg.pw05'/>"
								v:reqexpw m:reqexpw="<gm:tagAttb value='jsp.member.join.msg.pw06'/>"
								v:reqexpwwith m:reqexpwwith="<gm:tagAttb value='jsp.member.join.msg.pw02'/>"
								v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.pw01'/>"/><span class="txtcolor01"> &nbsp;* 需包含大小寫英文、數字、特殊符號共6~16碼(不可有空白)</span></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="pwnuncon">確認密碼</label></th>
							<td><input type="password" id="pwnuncon" name="pwnuncon" class="w180" maxlength="16" v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.pw03'/>" 
						         maxlength="16" v:scompare="==,@{member.mbrPw}" m:scompare="<gm:tagAttb value='jsp.member.join.msg.pw04'/>" /></td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="nation">所在國家</label></th>
							<td>
							<select id="naCd" name="member.naCd" class="w188">
								<gc:codeList group="US0058" var="USCODE"/>
						    	<c:forEach items="${USCODE}" var="us">
									<option value="${us.addField1}">${us.cdNm}</option>
						    	</c:forEach>
							</select>
							</td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="city">所在縣市</label></th>
							<td><input type="text" id="city" name="member.city" class="w180"
								v:text8_limit="75" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ct01'/>" 
							    v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.ct02'/>"/>
							</td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="address">通訊地址</label></th>
							<td><input type="text" id="homeAddrDtl" name="member.homeAddrDtl" class="w376"
								v:text8_limit="150" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.addr01'/>" 
								v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.addr02'/>" /></td>
						</tr>
						<tr>
							<th scope="row" class="tit03"><label for="postnum">郵遞區號</label></th>
							<td><input type="text" id="zipCd" name="member.zipCd" class="w180" onkeyup="checkNumber(this)" maxlength="5"
								v:text8_limit="5" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.zp03'/>"
								v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.zp02'/>"/> <span class="txtcolor01">&nbsp;* 請省略"-"並輸入郵遞區號</span>
							</td>
						</tr>
						<tr>
							<th scope="row" class="tit03"><label for="phonenum">聯絡電話</label></th>
							<td><input type="text" id="telNo" name="member.telNo" class="w180" onkeyup="checkNumber(this)"
								v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ph03'/>"
								v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.ph01'/>" /> <span class="txtcolor01">&nbsp;* 請省略"-"並輸入含國碼和區碼之電話號碼</span>
							</td>
						</tr>
						<tr>
							<th scope="row"><span>*</span> <label for="moblenum">行動電話</label></th>
							<td>
								<input type="text" id="devHpNo" name="member.hpNo" class="w180" onkeyup="checkNumber(this)"
								v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.hp03'/>"
								v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.hp02'/>"
							 	v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.hp01'/>" /> <span class="txtcolor01">&nbsp;* 請省略"-"並輸入含國碼之行動電話號碼</span>
							 </td>
						</tr>
						<tr>
							<th scope="row" class="tit01 tit03"><label for="wsite">官方網站</label></th>
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
				<input type="image" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm1.gif'/>" id="foreignerFormOk" alt="確認" />
			</div>

		</div>
		<!-- //CONTENT TABLE E-->

	</div>
	<!-- //Content Area E -->