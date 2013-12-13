<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<style>							
	.fileinputs {position: relative; overflow: hidden; height: 24px; width: 600px;}
	.fileinputs * {vertical-align: middle;}
	.fakefile {position: absolute; top:0px; left:0px; height: 30px;  z-index: 1;}
	.inputFile {position: relative; text-align: right; top: -12px; width: 250px; height:35px; filter: alpha(opacity=0); opacity: 0; z-index: 2; direction: ltl; selector-dummy: expression(this.hideFocus=true);}
</style>

<script type="text/javascript">
$(document).ready(function(){
	createPopupLayer('pop_area');
<c:if test="${calculateInfo.mbrClsCd eq 'US000103'}">
	$('#selmoney_container').css("position","absolute");
	$('#selmoney_container').css("z-index","3");
</c:if>
});

$(function() {
	$("#btnSave").click(function() {
		if(showValidate('inputForm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
			$('#pop_area01').show();
			$("#pop_area01").html($('#pop_ok02').html());
			$("#pop_area_body").html($('#pop_area01'));
			showPopupLayer('pop_area', 'wrap');
			return false;
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
			$(fileObj).parent().html($(fileObj).parent().html());
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
 
function TotalFileSizeLimit()
{   
	var form = document.inputForm;
	var len = 0;  
	var maxSize = 1; //1MB
	var img = new Image();
	
	var inputs = form.elements.tags("INPUT"); 
	var rtnValue = false;
	var count = 1;
	for(var i=0;i<inputs.length;i++)
	{
		if(inputs(i).type=="file" && inputs(i).value != "")
		{
			img.dynsrc = inputs(i).value;
			len = img.fileSize;
			alert(maxSize);alert((len / 1024 / 1024));
			if(maxSize < (len / 1024 / 1024))
			{
				alert("File(" + (count) + ") size limit : " + maxSize + "MB");     
				rtnValue = true;
				return rtnValue;        
			}
			count++;
		}
	}
}

function submitSave() {
	if(showValidate('inputForm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
		$("#forwardAction").val("CALCULATE");
		var options = {
				type: "POST",
		        dataType:  "json",
		        async	: true,		
				cache	: false,
		        success: function(responseText, status){
					//alert("responseText1 : " + responseText + "\nstatus : " + status);
					if(responseText.result = "SUCCESS"){
						$("#inputForm").attr('action', env.contextPath + '/mypage/mypageCalculateInfoView.omp');
						$("#inputForm").submit();
					}
				},
				error: function(xhr, textStatus, errorThrown){}
		};
		
		var frm = $('#inputForm');
		frm.attr("action","./ajaxMypageCalculateInfoInsert.omp");
		frm.ajaxSubmit(options);
		
	}
}

function changeBizCatCd(obj) {
	$("#bizCatCode").text("<gc:html code="US000902"/>");
	$("#bizCatCd").val("US000902");
}

//탭이동
function moveTabPage(target) {
	if(isExistChangedInfo()) {
		$("#forwardAction").val(target);
		$('#pop_area01').show();
		$("#pop_area01").html($('#pop_ok01').html());
		$("#pop_area_body").html($('#pop_area01'));
		showPopupLayer('pop_area', 'wrap');
	}
	else {
		if(target == "PROFILE") {
			$('#introFrm').attr('action', env.contextPath + "/mypage/mypageProfileView.omp");
			$('#introFrm').submit();
			//location.href = env.contextPath + "/mypage/mypageProfileView.omp";
		}
	}
}

//저장 후 탭 이동
function saveMoveTabPage() {
	closePopLayer('pop_area');
	if(showValidate('inputForm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
		$("#forwardAction").val("PROFILE");
		var options = {
				type: "POST",
		        dataType:  "json",
		        async	: true,		
				cache	: false,
		        success: function(responseText, status){
					//alert("responseText1 : " + responseText.result + "\nstatus : " + status);
					if(responseText.result = "SUCCESS"){
						$("#inputForm").attr('action', env.contextPath + '/mypage/mypageProfileView.omp');
						$("#inputForm").submit();
					}
				},
				error: function(xhr, textStatus, errorThrown){}
		};
		
		var frm = $('#inputForm');
		frm.attr("action","./ajaxMypageCalculateInfoInsert.omp");
		frm.ajaxSubmit(options);
	}
}

//edit
function isExistChangedInfo() {
	if($("#tempIdentityDocFile").val() != $("#identityDocFile").val()) return true;
	if($("#account").val() != $("#tempAccount").val()) return true;
	if($("#accountnum").val() != $("#tempAccountnum").val()) return true;
	if($("#accountDocFile").val() != $("#tempAccountDocFile").val()) return true;
<c:if test="${profileInfo.mbrClsCd eq 'US000101'}">
	if($("#bankinfo").val() != $("#tempBankinfo").val()) return true;
</c:if>
<c:if test="${profileInfo.mbrClsCd eq 'US000102'}">
	if($("#bankinfo").val() != $("#tempBankinfo").val()) return true;
</c:if>
<c:if test="${profileInfo.mbrClsCd eq 'US000103'}">
	if($("#bankname").val() != $("#tempBankname").val()) return true;
	if($("#bankcode1").val() != $("#tempBankcode1").val()) return true;
</c:if>
}
</script>
<form name="introFrm" id="introFrm" method="post">
	<input type="hidden" name="isProfile" id="isProfile" value="${isProfile }"/>
</form>
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; <gm:html value='마이페이지'/> &gt; <span><gm:html value='회원정보변경'/></span></p>
		<h3><img alt="회원정보변경" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_title01.gif"/>"></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
	<form id="inputForm" name="inputForm"  method="post" enctype="multipart/form-data" action="<c:url value="/mypage/ajaxMypageCalculateInfoInsert.omp"/>">
		<input type="hidden" id="forwardAction" name="forwardAction" value=""/>
		<input type="hidden" name="isProfile" id="isProfile" value="${isProfile }"/>
		<!-- Tab_menu S -->
		<div class="tab mar_b22">
			<ul>
				<li><a href="javascript:moveTabPage('PROFILE');"><img alt="기본정보" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_tab01.gif"/>"></a></li>
				<li><a href="javascript:moveTabPage('CALCULATE');"><img alt="정산정보" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_tab02_on.gif"/>"></a></li>
			</ul>
			<p class="info"><span>*</span> 가 표시된 부분은 필수입력 항목입니다.</p>
		</div>
		<!-- //Tab_menu E -->
		<h4 class="h41"><img alt="정산정보" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_h4_02.gif"/>"></h4>
		<div class="tstyleA">
		
		<!-- 개인 -->
		<c:if test="${calculateInfo.mbrClsCd eq 'US000101'}">
			<table summary="정산정보 입력">
				<caption><gm:html value='정산정보 입력'/></caption>
				<colgroup>
					<col width="15%">
					<col>
				</colgroup>
				<tbody>
				<!-- New -->
					<tr>
						<th scope="row"><span>*</span> <label for="personal"><gm:html value='개인신분 사본'/></label></th>
						<td>
						<div class="fileinputs" id="divIdentityDoc">
							<span><input type="file" class="inputFile" id="identityDoc" name="calculateInfo.identityDoc"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempIdentityDocFile','pict');" style="cursor: pointer;" onclick="this.blur();" /></span>
							<div class="fakefile">
								<input type="hidden" id="identityDocFile" value="${calculateInfo.identityDocNm }" />
								<input type="text" class="w180" name="calculateInfo.identityDocNm" id="tempIdentityDocFile" disabled="disabled" readonly value="${calculateInfo.identityDocNm}" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.iddoc01'/>"/> &nbsp;
								<a href="#"><img alt="찾기" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
								<!-- 
								 <a href="<c:url value="/fileSupport/fileDown.omp">
							         <c:param name="bnsType" value="common.path.share.member"/>
							         <c:param name="filePath" ><g:html value="${calculateInfo.identityDocPath }" /></c:param>
							         <c:param name="fileName" ><g:html value="${calculateInfo.identityDocNm }"/></c:param>
							         </c:url>">다운로드
								</a>
								 -->
							</div>
						</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="savename"><gm:html value='예금주'/></th>
						<td><input type="hidden" id="tempAccount" value="${calculateInfo.acctNm}"/>
							<input type="text" class="w180" name="calculateInfo.acctNm" id="account" value="${calculateInfo.acctNm}"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acnm01'/>"
							v:checkspecial  m:checkspecial="<gm:tagAttb value='jsp.member.mypage.msg.com01'/>"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acnm02'/>" />
						</td> 
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="bankinfo"><gm:html value='은행정보'/></label></th>
						<td>
							<input type="hidden" id="tempBankinfo" value="${calculateInfo.bankCd}"/>
							<input type="text" id="bankinfo" name="calculateInfo.bankCd" class="w180" value="${calculateInfo.bankCd}" readonly="readonly" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bk01'/>"/>&nbsp;
							<a href="#" id="searchBank"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_bankcode.gif"/>" alt="은행코드찾기" /></a><br />
							<input type="text" id="bankinfo1" name="calculateInfo.bankNm" class="w400" value="<gc:html code="${calculateInfo.bankCd}" codeType="full"/>" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="accountnum"><gm:html value='계좌정보'/></label></th>
						<td>
							<input type="hidden" id="tempAccountnum" value="${calculateInfo.acctNo}"/>
							<input type="text" class="w180" name="calculateInfo.acctNo" id="accountnum" value="${calculateInfo.acctNo}"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acno01'/>"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acno02'/>" 
							v:regexp="[\d]*" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.acno03'/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> <label for="bankbook"><gm:html value='통장사본'/></label></th>
						<td class="bgnone">
							<div class="fileinputs" id="divAccountDoc">
								<span><input type="file" class="inputFile" id="accountDoc" name="calculateInfo.acctDoc" maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempAccountDocFile','pict');" style="cursor: pointer;" onclick="this.blur();"/></span>
								<div class="fakefile">
									<input type="hidden" id="accountDocFile" value="${calculateInfo.acctDocNm }" />
									<input type="text" class="w180" name="calculateInfo.acctDocNm" id="tempAccountDocFile" disabled="disabled" readonly value="${calculateInfo.acctDocNm}" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acdoc01'/>" /> &nbsp;
									<a href="#"><img alt="찾기" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
									<!-- 
									<a href="<c:url value="/fileSupport/fileDown.omp">
								         <c:param name="bnsType" value="common.path.share.member"/>
								         <c:param name="filePath" ><g:html value="${calculateInfo.acctDocPath }" /></c:param>
								         <c:param name="fileName" ><g:html value="${calculateInfo.acctDocNm }"/></c:param>
								         </c:url>">다운로드
									</a>
									 -->
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</c:if>
		
		<!-- 회사  -->
		<c:if test="${calculateInfo.mbrClsCd eq 'US000102'}">
			<table summary="정산정보 입력">
				<caption><gm:html value='정산정보 입력'/></caption>
				<colgroup>
					<col width="15%" />
					<col />
				</colgroup>
				<tbody>
					<!-- New -->
					<tr>
						<th scope="row"><span>*</span> <label for="company"><gm:html value='회사증명사본'/></label></th>
						<td>
							<div class="fileinputs" id="divIdentityDoc">
								<span><input type="file" class="inputFile" id="identityDoc" name="calculateInfo.identityDoc" maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempIdentityDocFile','pict');" style="cursor: pointer;" onclick="this.blur();" /></span>
								<div class="fakefile">
									<input type="hidden" id="identityDocFile" value="${calculateInfo.identityDocNm }" />
									<input type="text" class="w180" name="calculateInfo.identityDocNm" id="tempIdentityDocFile" disabled="disabled" readonly value="${calculateInfo.identityDocNm}" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.iddoc02'/>"/> &nbsp;
									<a href="#"><img alt="찾기" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
									<!-- 
									<a href="<c:url value="/fileSupport/fileDown.omp">
								         <c:param name="bnsType" value="common.path.share.member"/>
								         <c:param name="filePath" ><g:html value="${calculateInfo.identityDocPath }" /></c:param>
								         <c:param name="fileName" ><g:html value="${calculateInfo.identityDocNm }"/></c:param>
								         </c:url>">다운로드
									</a>
									 -->
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='회사구분'/> </th>
						<input type="hidden" id="tmpbizCatCd" value="${calculateInfo.bizCatCd}"/>
						<input type="hidden" id="bizCatCd" name="calculateInfo.bizCatCd" value="${calculateInfo.bizCatCd}"/>
					<c:if test="${calculateInfo.bizCatCd eq 'US000901'}">
						<td id="bizCatCode"><gc:html code="US000901"/><a href="javascript:changeBizCatCd();"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_general.gif"/>" alt="일반영업으로 전환" /></a></td>
					</c:if>
					<c:if test="${calculateInfo.bizCatCd eq 'US000902'}">
						<td><gc:html code="US000902"/></td>
					</c:if>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='예금주'/> </th>
						<td>
							<input type="hidden" id="tempAccount" value="${calculateInfo.acctNm}"/>
							<input type="text" class="w180" name="calculateInfo.acctNm" id="account" value="${calculateInfo.acctNm}" 
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acnm01.'/>"
							v:checkspecial  m:checkspecial="<gm:tagAttb value='jsp.member.mypage.msg.com01'/>"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acnm02'/>" />
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="bankinfo"><gm:html value='은행정보'/></label></th>
						<td>
							<input type="hidden" id="tempBankinfo" value="${calculateInfo.bankCd}"/>
							<input type="text" id="bankinfo" name="calculateInfo.bankCd" class="w180" value="${calculateInfo.bankCd}" readonly="readonly" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bk01'/>" />&nbsp;
							<a href="#" id="searchBank"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_bankcode.gif"/>" alt="은행코드찾기" /></a><br />
							<input type="text" id="bankinfo1" name="calculateInfo.bankNm" class="w400" value="<gc:html code="${calculateInfo.bankCd}" codeType="full"/>" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="accountnum"><gm:html value='계좌정보'/></label></th>
						<td>
							<input type="hidden" id="tempAccountnum" value="${calculateInfo.acctNo}"/>
							<input type="text" class="w227" name="calculateInfo.acctNo" id="accountnum" value="${calculateInfo.acctNo}"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acno02'/>"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acno01'/>" 
							v:regexp="*" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.acno03'/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> <label for="bankbook"><gm:html value='통장사본'/></label></th>
						<td class="bgnone">
							<div class="fileinputs" id="divAccountDoc">
								<span><input type="file" class="inputFile" id="accountDoc" name="calculateInfo.acctDoc"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempAccountDocFile','pict');" style="cursor: pointer;" onclick="this.blur();"/></span>
								<div class="fakefile">
									<input type="hidden" id="accountDocFile" value="${calculateInfo.acctDocNm }" />
									<input type="text" class="w180" name="calculateInfo.acctDocNm" id="tempAccountDocFile" disabled="disabled" readonly value="${calculateInfo.acctDocNm}"  v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acdoc01'/>" /> &nbsp;
									<a href="#"><img alt="찾기" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
									<!-- 
									<a href="<c:url value="/fileSupport/fileDown.omp">
								         <c:param name="bnsType" value="common.path.share.member"/>
								         <c:param name="filePath" ><g:html value="${calculateInfo.acctDocPath }" /></c:param>
								         <c:param name="fileName" ><g:html value="${calculateInfo.acctDocNm }"/></c:param>
								         </c:url>">다운로드
									</a>
									 -->
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</c:if>
		
		<!-- 외국인 Old -->
		<c:if test="${calculateInfo.mbrClsCd eq 'US000103'}">
			<table summary="정산정보 입력">
				<caption><gm:html value='정산정보 입력'/></caption>
				<colgroup>
					<col width="15%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='외국인증명 사본'/></th>
						<td class="bgnone"> 
						<div class="fileinputs" id="divIdentityDoc">
							<span><input type="file" class="inputFile" id="identityDoc" name="calculateInfo.identityDoc"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempIdentityDocFile','pict');" style="cursor: pointer;" onclick="this.blur();"/></span>
							<div class="fakefile">
								<input type="hidden" id="identityDocFile" value="${calculateInfo.identityDocNm }" />
								<input type="text" class="w180" name="calculateInfo.identityDocNm" id="tempIdentityDocFile" disabled="disabled" readonly value="${calculateInfo.identityDocNm}" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.iddoc03'/>" /> &nbsp;
								<a href="#"><img alt="찾기" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
								<!-- 
								<a href="<c:url value="/fileSupport/fileDown.omp">
							         <c:param name="bnsType" value="common.path.share.member"/>
							         <c:param name="filePath" ><g:html value="${calculateInfo.identityDocPath }" /></c:param>
							         <c:param name="fileName" ><g:html value="${calculateInfo.identityDocNm }"/></c:param>
							         </c:url>">다운로드
								</a>
								-->
							</div>
						</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="account"><gm:html value='예금주'/></label></th>
						<td>
							<input type="hidden" id="tempAccount" value="${calculateInfo.acctNm}"/>
							<input type="text" class="w180" name="calculateInfo.acctNm" id="account" value="${calculateInfo.acctNm}"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acnm01'/>"
							v:checkspecial  m:checkspecial="<gm:tagAttb value='jsp.member.mypage.msg.com01'/>"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acnm02'/>" />
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="bankname"><gm:html value='은행명'/></label></th>
						<td>
							<input type="hidden" id="tempBankname" value="${calculateInfo.bankNm}"/>
							<input type="text" class="w180" name="calculateInfo.bankNm" id="bankname" value="${calculateInfo.bankNm}"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.bk02'/>" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bk01'/>" />
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="accountnum"><gm:html value='계좌정보'/></label></th>
						<td>
							<input type="hidden" id="tmpAccountnum" value="${calculateInfo.acctNo}"/>
							<input type="text" class="w227" name="calculateInfo.acctNo" id="accountnum" value="${calculateInfo.acctNo}" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acno02'/>"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acno01'/>"
							v:regexp="[\d]*" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.acno03'/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="bankcode"><gm:html value='은행코드'/></label></th>
						<td>
							<div class="fltl pad_r4">
							<select id="bankcode" name="calculateInfo.bankGlType" class="w188">
								<gc:options group="US0057" codeType="full" value="${calculateInfo.bankGlType}"/>
							</select>
							</div>
							<input type="hidden" id="tempBankcode1" value="${calculateInfo.bankGlCd}" />
							<input type="text" id="bankcode1" name="calculateInfo.bankGlCd" class="w180" 
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.bkcd01'/>"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bkcd02'/>" value="${calculateInfo.bankGlCd}"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="selmoney"><gm:html value='화폐선택'/></label></th>
						<td>
							<select id="selmoney" name="calculateInfo.currentcyUnit" class="w188">
								<gc:options group="US0053" codeType="full" value="${calculateInfo.currentcyUnit}"/>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> <label for="bankbook"><gm:html value='통장사본'/></label></th>
						<td class="bgnone">
							<div class="fileinputs" id="divAccountDoc">
								<span><input type="file" class="inputFile" id="accountDoc" name="calculateInfo.acctDoc"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempAccountDocFile','pict');" style="cursor: pointer;" onclick="this.blur();" /></span>
								<div class="fakefile">
									<input type="hidden" id="accountDocFile" value="${calculateInfo.acctDocNm }" />
									<input type="text" class="w180" name="calculateInfo.acctDocNm" id="tempAccountDocFile" disabled="disabled" readonly value="${calculateInfo.acctDocNm}"  v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acdoc01'/>" /> &nbsp;
									<a href="#"><img alt="찾기" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
									<!-- 
									<a href="<c:url value="/fileSupport/fileDown.omp">
								         <c:param name="bnsType" value="common.path.share.member"/>
								         <c:param name="filePath" ><g:html value="${calculateInfo.acctDocPath }" /></c:param>
								         <c:param name="fileName" ><g:html value="${calculateInfo.acctDocNm }"/></c:param>
								         </c:url>"><g:html value="${calculateInfo.acctDocNm }"/>
									</a>
									 -->
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</c:if>
		
		</div>
		<div class="guideinfo">
			<p class="pbult04">정산정보 수정 시 운영자의 검토 후 승인 완료된 후에 수정된 정보로 판매정산 처리가 되어집니다.</p>
		</div>
		<div class="btnarea mar_t30">
			<a shape="hover"><img id="btnSave" type="image" alt="확인" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif"/>" style="cursor: pointer;"/></a></li>
			<a href="javascript:gotoPage('MAIN');"><img alt="취소" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif"/>"></a>
		</div>
	</form>
	</div>
	<!-- //CONTENT TABLE E-->
	<div id="pop_area01" style="display:none;"></div>
	
	<div id="pop_ok01" style="display:none;">
		<h2><img alt="변경 내용 저장 확인" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/mp_title_01.gif"/>"></h2>
		<p class="pop_txt2">변경된 내용이 저장되지 않았습니다. <br>저장을 하지 않고 이동하실 경우 변경된 내용이 삭제됩니다.</p>
		<div class="pop_btn">
			<a href="javascript:saveMoveTabPage();"><img alt="저장 후 이동" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/btn_savemove.gif"/>"></a>
			<a href="javascript:cancelMoveTabPage();"><img alt="변경내용 취소" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/btn_cancle_02.gif"/>"></a>
		</div>
		<p class="pop_close"><a href="javascript:closePopLayer('pop_area');"><img alt="닫기" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif"/>"></a></p>
	</div>
	
	<div id="pop_ok02" style="display:none;">
			<h2><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/mp_title_02.gif"/>" alt="정산정보 승인요청" /></h2>
			<p class="pop_txt2">등록하신 정산정보를 승인요청하시겠습니까?</p>
			<div class="pop_btn">
				<a href="javascript:submitSave();"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/btn_yes.gif"/>" alt="예" /></a>
				<a href="javascript:closePopLayer('pop_area');"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/btn_no.gif"/>" alt="아니오" /></a>
			</div>
			<p class="pop_close"><a href="javascript:closePopLayer('pop_area');"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif"/>" alt="닫기" /></a></p>
	</div>
</div>