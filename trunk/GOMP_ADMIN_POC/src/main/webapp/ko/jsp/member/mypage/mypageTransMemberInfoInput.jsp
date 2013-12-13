<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<style>							
	.fileinputs {position: relative; overflow: hidden; height: 24px; width: 600px;}
	.fileinputs * {vertical-align: middle;}
	.fakefile {position: absolute; top:0px; left:0px; height: 30px;  z-index: 1; cursor: pointer;}
	.inputFile {position: relative; text-align: right; top: -12px; width: 250px; height:35px; filter: alpha(opacity=0); opacity: 0; z-index: 2; direction: ltl; selector-dummy: expression(this.hideFocus=true); cursor: pointer;}
</style>

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
		}
	});
	
	$("#searchBank").click(function(){
		var targetUrl = env.contextPath + "/mypage/popupSearchBankList.omp";
		popup_center(targetUrl, 456, 367, 0, 0);
	});
});

function setUploadFileNameCheck( fileObj, id, extCode )	{
	var frm = fileObj.form;

	//var fname=document.all.myfile.value;
	var imgNmLength = fileObj.value;
	var arrImg=("file:///"+imgNmLength.replace(/ /gi,"%20").replace(/\\/gi,"/")).split("/");
	var imgNm = arrImg[arrImg.length-1];
	if(fileObj.value != ""){
		//alert(imgNmLength.getByteLength() + " :::: " + imgNmLength);
		if(imgNm.getByteLength() > 100){
			alert("<gm:string value='jsp.member.mypage.msg.file01'/>");
			fileObj = "";
			return;
		}
	
		if ( !isExt( fileObj.value, extCode ) )	{
			alert( "<gm:string value='jsp.member.mypage.msg.file02'/>" );
			return	false;
		}
	
		var index = fileObj.value.lastIndexOf("\\");
	
		if ( index > -1 )	{
			$("#"+id).val(fileObj.value.substring(index+1));
		}	else	{
			$("#"+id).val(fileObj.value);
		}
	}
}

/**
 * 확장자 확인
 * @param {String} fileName	선택된 파일경로
 * @param {String} extCode	확인할 확장자코드
 */
function isExt( fileName, extCode )	{
	var idx	= fileName.lastIndexOf( "." );
	var ext	= fileName.substring( idx ).toLowerCase();

	if ( extCode != "pict" && extCode != "docu")	{
		if ( ext != "."+extCode && ext != "."+extCode+"x" )	{
			return	false;
		}
	}	
	else if( extCode != "pict") {
		if ( ext != ".doc" && ext != ".pdf" && ext != ".ppt" && ext != ".jpg" && ext != ".docx" && ext != ".pptx" && ext != ".xls" && ext != ".xlsx" && ext != ".hwp")	{
			return	false;
		}
	}
	else if( extCode != "docu") {
		if ( ext != ".gif" && ext != ".jpg" && ext != ".png" && ext != ".bmp")	{
			return	false;
		}
	}

	return	true;
}
 
function submitSave() {
	if(showValidate('inputForm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
		if($('#phonenum').val() != '' && $('#afterPhoneNum').val() != ''){
			$('#opTelNo').val($('#phonenum').val() + '-' +  $('#afterPhoneNum').val());
		}
		$("#inputForm").attr('action', env.contextPath + '/mypage/mypageMemberTransInfoInput.omp');
		$("#inputForm").submit();
	}
}
</script>

<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; <gm:html value='마이페이지'/> &gt; <span><gm:html value='회원전환'/></span></p>
		<h3><img alt="회원전환" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_title03.gif"/>"></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
    <form id="inputForm" name="inputForm"  method="post" enctype="multipart/form-data" action="<c:url value="/mypage/mypageMemberTransInfoInsert.omp"/>">
		<h4 class="h43"><img alt="기본정보 등록" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_subtitle03.gif"/>"></h4>
		<p class="pbult05">회사 (일반영업인, 소규모영업인) 회원으로 전환하기 위해 아래 정보를 입력해주십시오.</p>
              <h5 class="h51 fltl"><img alt="기본정보" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_h4_01.gif"/>"></h5>
		<p class="txtr txt_90 pad_t2"><span class="txtcolor03">*</span> 가 표시된 부분은 필수입력 항목입니다.</p>
		<div class="tstyleA mar_b22">
			<table summary="기본정보 수정">
				<caption>기본정보 수정</caption>
				<colgroup>
					<col width="15%">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row" class="tit03">아이디</th>
						<td>
							<g:html value="${MEMBER_SESSION.mbrId}"/>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit03">회사명</th>
						<td>
							<input name="transferInfo.compNm" type="hidden" value="<g:tagAttb value="${transferInfo.compNm}"/>"/>
							<g:html value="${transferInfo.compNm}"/>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit03">통일번호</th>
						<td>
							<input name="transferInfo.psRegNo" type="hidden" value="<g:tagAttb value="${transferInfo.psRegNo}"/>"/>
							<g:html  value='${transferInfo.psRegNo}' format="L#********"/>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit03">이메일</th>
						<td>
							<g:html value="${profileInfo.emailAddr}"/> &nbsp; 
							<a href="<c:url value='/mypage/mypageEmail.omp'/>">
								<img alt="이메일 변경" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_email.gif"/>">
							</a>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="city">도시</label></th>
						<td>
							<input type="text" class="w180" value="<g:tagAttb value="${transferInfo.city}"/>" name="transferInfo.city" id="city" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.ct02'/>"
							v:text8_limit="75" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ct01'/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="address">회사 주소</label></th>
						<td>
							<input type="text" class="w376" value="<g:tagAttb value="${transferInfo.homeAddrDtl}"/>" name="transferInfo.homeAddrDtl" id="address" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.addr02'/>"
							v:text8_limit="150" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.addr01'/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit03"><label for="postnum">우편번호</label></th>
						<td>
							<input type="text" class="w180" value="<g:tagAttb value="${transferInfo.zipCd}"/>" name="transferInfo.zipCd" id="postnum" maxlength="3"
							v:text8_limit="3" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.zp01'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.zp02'/>"/>&nbsp;<span class="txtcolor01"> * “-”을 생략하고 우편번호 5자리를 숫자로만 입력해 주세요</span>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01 tit03"><label for="wsite">Website</label></th>
						<td class="bgnone">
							<input type="text" class="w376" value="<g:tagAttb value="${transferInfo.webSiteUrl}"/>" name="transferInfo.webSiteUrl" id="wsite" 
							v:text8_limit="150" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.wb01'/>" />
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<h5 class="h52"><img alt="담당자정보" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_h4_03.gif"/>"></h5>
		<div class="tstyleA mar_b22">
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
							<input type="text" class="w180" value="<g:tagAttb value="${transferInfo.opNm}"/>" name="transferInfo.opNm" id="name" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.nm02'/>"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.nm01'/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="email2">이메일</label></th>
						<td>
							<input type="text" class="w180" value="<g:tagAttb value="${transferInfo.opEmail}"/>" name="transferInfo.opEmail" id="email2" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.mgEmail01'/>" 
							v:email m:email="<gm:tagAttb value='jsp.member.join.msg.email04'/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit03"><label for="phonenum">전화번호</label></th>
						<td>
							<div class="fltl pad_r4">
								<select id="phonenum" class="w67" name="phone" v:phone m:phone="<gm:tagAttb value='jsp.member.join.msg.ph05'/>">
									<option value=""></option>
									<gc:codeList group="${CONST.PHONE_AREA_CODE}" var="USCODE"/>
							    	<c:forEach items="${USCODE}" var="us">
										<option value="${us.cdNm}">${us.cdNm}</option>
							    	</c:forEach>
								</select>
							</div>
							<input id="afterPhoneNum" type="text" class="w109"
							v:afterphone m:afterphone="<gm:tagAttb value='jsp.member.join.msg.ph02'/>" 
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.mgph01'/>"
							v:text8_limit="16" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.ph04'/>"
							/>&nbsp;<span class="txtcolor01"> * “-”을 생략하고 숫자로만 입력해 주세요. </span>
							<input type="hidden" name="transferInfo.opTelNo" id="opTelNo"/>
						</td>
					</tr>
					<tr>
						<th class="tit01" scope="row"><span>*</span> <label for="moblenum">휴대폰 번호</label></th>
						<td class="bgnone">
							<input type="text" class="w180" value="<g:tagAttb value="${transferInfo.opHpNo}"/>" name="transferInfo.opHpNo" id="moblenum" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.join.msg.mghp01'/>"
							v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.member.join.msg.mghp02'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.join.msg.mghp03'/>"/>&nbsp;<span class="txtcolor01"> * “-”을 생략하고 숫자로만 입력해 주세요. </span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<c:if test="${profileInfo.mbrCatCd eq 'US000206'}">
		<h5 class="h52"><img alt="정산정보" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_h4_02.gif"/>"></h5>
		<div class="tstyleA">
			<table summary="정산정보 수정">
				<caption>정산정보 수정</caption>
				<colgroup>
					<col width="15%">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <label for="company">회사증명사본 </label></th>
						<td>
							<div class="fileinputs" id="divIdentityDoc">
								<input type="file" class="inputFile" id="identityDoc" name="transferInfo.identityDoc"  onchange="javascript:setUploadFileNameCheck(this,'tempIdentityDocFile','pict');" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.iddoc02'/>" />
								<div class="fakefile">
									<input type="text" class="w180" name="transferInfo.identityDocNm" id="tempIdentityDocFile" disabled="disabled" readonly value="<g:tagAttb value="${transferInfo.identityDocNm}"/>"/> &nbsp;
									<a href="#"><img alt="찾기" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span>  회사구분</th>
						<td>
							<gc:radioButtons name="transferInfo.bizCatCd" group="US0009" codeType="full" value="${transferInfo.bizCatCd}" divide="&nbsp; &nbsp; &nbsp;">
								<g:param name="extra">
									v:mustcheck="1" m:mustcheck="<gm:tagAttb value='jsp.member.mypage.msg.comp01'/>"
								</g:param>			
							</gc:radioButtons>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="account">예금주</label></th>
						<td>
							<input type="text" class="w180" name="transferInfo.acctNm" id="acctNm" value="<g:tagAttb value="${transferInfo.acctNm}"/>" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acnm02'/>" 
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acnm01'/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="bankinfo">은행정보</label></th>
						<td><input type="text" id="bankinfo" name="transferInfo.bankCd" class="w180" readonly="readonly" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bk01'/>" />&nbsp;
						<a href="#" id="searchBank"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_bankcode.gif"/>" alt="은행코드찾기" /></a><br />
						<input type="text" id="bankinfo1" name="transferInfo.bankNm" class="w400" readonly="readonly"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="accountnum">계좌정보</label></th>
						<td>
							<input type="text" class="w227" name="transferInfo.acctNo" id="accountnum" value="<g:tagAttb value="${transferInfo.acctNo}"/>" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acno02'/>"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acno01'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.acno03'/>"/>
						</td>
					</tr>
					<tr>
						<th class="tit01" scope="row"><span>*</span> <label for="bankbook">통장사본</label></th>
						<td class="bgnone">
							<div class="fileinputs" id="divAccountDoc">
								<input type="file" class="inputFile" id="accountDoc" name="transferInfo.acctDoc"  onchange="javascript:setUploadFileNameCheck(this,'tempAccountDocFile','pict');" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acdoc01'/>" />
								<div class="fakefile">
									<input type="text" class="w180" name="transferInfo.acctDocNm" id="tempAccountDocFile" disabled="disabled" readonly value="<g:tagAttb value="${transferInfo.acctDocNm}"/>"/> &nbsp;
									<a href="#"><img alt="찾기" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
								</div>
							</div>
						</td>
					</tr>
				</tbody>  
			</table>
		</div>
		</c:if>
	</form>
		
		<div class="btnarea mar_t39">
			<a href="javascript:submitSave();"><img alt="확인" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif"/>"></a>
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img alt="취소" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif"/>"></a>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>