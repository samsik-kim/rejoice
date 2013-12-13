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
	var imgNmLength = fileObj.value;
	var arrImg=("file:///"+imgNmLength.replace(/ /gi,"%20").replace(/\\/gi,"/")).split("/");
	var imgNm = arrImg[arrImg.length-1];
	if(fileObj.value != ""){
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
				if(responseText.result = "SUCCESS"){
					$("#inputForm").attr('action', env.contextPath + '/mypage/mypageCalculateInfoView.omp');
					$("#inputForm").submit();
				}
			}
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
		}
	}
}

//저장 취소 후 탭 이동
function cancelMoveTabPage() {
	if($("#forwardAction").val() == "PROFILE") {
		$('#introFrm').attr('action', env.contextPath + "/mypage/mypageProfileView.omp");
		$('#introFrm').submit();
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
				if(responseText.result = "SUCCESS"){
					$("#inputForm").attr('action', env.contextPath + '/mypage/mypageProfileView.omp');
					$("#inputForm").submit();
				}
			}
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
<c:if test="${calculateInfo.mbrClsCd eq 'US000101'}">
	if($("#bankinfo").val() != $("#tempBankinfo").val()) return true;
</c:if>
<c:if test="${calculateInfo.mbrClsCd eq 'US000102'}">
	if($("#bankinfo").val() != $("#tempBankinfo").val()) return true;
	if($("#bizCatCd").val() != $("#tmpbizCatCd").val()) return true;
</c:if>
<c:if test="${calculateInfo.mbrClsCd eq 'US000103'}">
	if($("#bankname").val() != $("#tempBankname").val()) return true;
	if($("#bankcode1").val() != $("#tempBankcode1").val()) return true;
</c:if>
}
</script>
<form name="introFrm" id="introFrm" method="post">
	<input type="hidden" name="isProfile" id="isProfile" value="<g:tagAttb value="${isProfile }"/>"/>
</form>
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">  
		<p class="history">Home &gt; 會員中心 &gt; <span>變更會員資料</span></p>
		<h3><img alt="變更會員資料" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_title01.gif"/>"></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
	<form id="inputForm" name="inputForm"  method="post" enctype="multipart/form-data" action="<c:url value="/mypage/ajaxMypageCalculateInfoInsert.omp"/>">
		<input type="hidden" id="forwardAction" name="forwardAction" value=""/>
		<input type="hidden" name="isProfile" id="isProfile" value="<g:tagAttb value="${isProfile }"/>"/>
		<!-- Tab_menu S -->
		<div class="tab mar_b22">
			<ul>
				<li><a href="javascript:moveTabPage('PROFILE');"><img alt="基本資料" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_tab01.gif"/>"></a></li>
				<li><a href="javascript:moveTabPage('CALCULATE');"><img alt="銀行資料" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_tab02_on.gif"/>"></a></li>
			</ul>
			<p class="info"><span>*</span> 為必填欄位</p>
		</div>
		<!-- //Tab_menu E -->
		<h4 class="h41"><img alt="銀行資料" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_h4_02.gif"/>"></h4>
		<div class="tstyleA">
		
		<!-- 개인 -->
		<c:if test="${calculateInfo.mbrClsCd eq 'US000101'}">
			<table summary="銀行資料">
				<caption><gm:html value='銀行資料'/></caption>
				<colgroup>
					<col width="15%">
					<col>
				</colgroup>
				<tbody>
				<!-- New -->
					<tr>
						<th scope="row"><span>*</span> <label for="personal">身分證影本</label></th>
						<td>
						<div class="fileinputs" id="divIdentityDoc">
							<span><input type="file" class="inputFile" id="identityDoc" name="calculateInfo.identityDoc"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempIdentityDocFile','pict');"/></span>
							<div class="fakefile">
								<input type="hidden" id="identityDocFile" value="<g:tagAttb value="${calculateInfo.identityDocNm}"/>" />
								<input type="text" class="w180" name="calculateInfo.identityDocNm" id="tempIdentityDocFile" disabled="disabled" readonly value="<g:tagAttb value="${calculateInfo.identityDocNm}"/>" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.iddoc01'/>"/> &nbsp;
								<a href="#"><img alt="瀏覽" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
							</div>
						</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="savename">帳戶名稱</th>
						<td><input type="hidden" id="tempAccount" value="<g:tagAttb value="${calculateInfo.acctNm}"/>"/>
							<input type="text" class="w180" name="calculateInfo.acctNm" id="account" value="${calculateInfo.acctNm}"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acnm01'/>"
							v:checkspecial  m:checkspecial="<gm:tagAttb value='jsp.member.mypage.msg.com01'/>"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acnm02'/>" />
						</td> 
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="bankinfo">銀行代號</label></th>
						<td>
							<input type="hidden" id="tempBankinfo" value="<g:tagAttb value="${calculateInfo.bankCd}"/>"/>
							<input type="text" id="bankinfo" name="calculateInfo.bankCd" class="w180" value="${calculateInfo.bankCd}" readonly="readonly" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bk01'/>"/>&nbsp;
							<a href="#" id="searchBank"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_bankcode.gif"/>" alt="查詢銀行代號" /></a><br />
							<input type="text" id="bankinfo1" name="calculateInfo.bankNm" class="w400" value="<g:tagAttb value="${calculateInfo.bankNm}"/>" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="accountnum">銀行帳戶</label></th>
						<td>
							<input type="hidden" id="tempAccountnum" value="<g:tagAttb value="${calculateInfo.acctNo}"/>"/>
							<input type="text" class="w180" name="calculateInfo.acctNo" id="accountnum" value="${calculateInfo.acctNo}"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acno01'/>"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acno02'/>" 
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.acno03'/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> <label for="bankbook">存摺影本</label></th>
						<td class="bgnone">
							<div class="fileinputs" id="divAccountDoc">
								<span><input type="file" class="inputFile" id="accountDoc" name="calculateInfo.acctDoc" maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempAccountDocFile','pict');" /></span>
								<div class="fakefile">
									<input type="hidden" id="accountDocFile" value="<g:tagAttb value="${calculateInfo.acctDocNm}"/>" />
									<input type="text" class="w180" name="calculateInfo.acctDocNm" id="tempAccountDocFile" disabled="disabled" readonly value="<g:tagAttb value="${calculateInfo.acctDocNm}"/>" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acdoc01'/>" /> &nbsp;
									<a href="#"><img alt="瀏覽" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</c:if>
		
		<!-- 회사  -->
		<c:if test="${calculateInfo.mbrClsCd eq 'US000102'}">
			<table summary="銀行資料">
				<caption>銀行資料</caption>
				<colgroup>
					<col width="15%" />
					<col />
				</colgroup>
				<tbody>
					<!-- New -->
					<tr>
						<th scope="row"><span>*</span> <label for="company">公司營業證影本</label></th>
						<td>
							<div class="fileinputs" id="divIdentityDoc">
								<span><input type="file" class="inputFile" id="identityDoc" name="calculateInfo.identityDoc" maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempIdentityDocFile','pict');" /></span>
								<div class="fakefile">
									<input type="hidden" id="identityDocFile" value="<g:tagAttb value="${calculateInfo.identityDocNm}"/>" />
									<input type="text" class="w180" name="calculateInfo.identityDocNm" id="tempIdentityDocFile" disabled="disabled" readonly value="<g:tagAttb value="${calculateInfo.identityDocNm}"/>" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.iddoc02'/>"/> &nbsp;
									<a href="#"><img alt="瀏覽" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 營業分類</th>
						<input type="hidden" id="tmpbizCatCd" value="<g:tagAttb value="${calculateInfo.bizCatCd}"/>"/>
						<input type="hidden" id="bizCatCd" name="calculateInfo.bizCatCd" value="<g:tagAttb value="${calculateInfo.bizCatCd}"/>"/>
					<c:if test="${calculateInfo.bizCatCd eq 'US000901'}">
						<td id="bizCatCode"><gc:html code="US000901"/><a href="javascript:changeBizCatCd();">&nbsp;&nbsp;&nbsp;<img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_general.gif"/>" alt="欲轉為一般營業" /></a></td>
					</c:if>
					<c:if test="${calculateInfo.bizCatCd eq 'US000902'}">
						<td><gc:html code="US000902"/></td>
					</c:if>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 帳戶名稱</th>
						<td>
							<input type="hidden" id="tempAccount" value="<g:tagAttb value="${calculateInfo.acctNm}"/>"/>
							<input type="text" class="w180" name="calculateInfo.acctNm" id="account" value="${calculateInfo.acctNm}" 
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acnm01.'/>"
							v:checkspecial  m:checkspecial="<gm:tagAttb value='jsp.member.mypage.msg.com01'/>"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acnm02'/>" />
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="bankinfo">銀行代號</label></th>
						<td>
							<input type="hidden" id="tempBankinfo" value="<g:tagAttb value="${calculateInfo.bankCd}"/>"/>
							<input type="text" id="bankinfo" name="calculateInfo.bankCd" class="w180" value="${calculateInfo.bankCd}" readonly="readonly" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bk01'/>" />&nbsp;
							<a href="#" id="searchBank"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_bankcode.gif"/>" alt="查詢銀行代號" /></a><br />
							<input type="text" id="bankinfo1" name="calculateInfo.bankNm" class="w400" value="<g:tagAttb value="${calculateInfo.bankNm}"/>" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="accountnum">銀行帳戶</label></th>
						<td>
							<input type="hidden" id="tempAccountnum" value="<g:tagAttb value="${calculateInfo.acctNo}"/>"/>
							<input type="text" class="w227" name="calculateInfo.acctNo" id="accountnum" value="${calculateInfo.acctNo}"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acno02'/>"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acno01'/>" 
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.acno03'/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> <label for="bankbook">存摺影本</label></th>
						<td class="bgnone">
							<div class="fileinputs" id="divAccountDoc">
								<span><input type="file" class="inputFile" id="accountDoc" name="calculateInfo.acctDoc"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempAccountDocFile','pict');" /></span>
								<div class="fakefile">
									<input type="hidden" id="accountDocFile" value="<g:tagAttb value="${calculateInfo.acctDocNm}"/>" />
									<input type="text" class="w180" name="calculateInfo.acctDocNm" id="tempAccountDocFile" disabled="disabled" readonly value="<g:tagAttb value="${calculateInfo.acctDocNm}"/>"  v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acdoc01'/>" /> &nbsp;
									<a href="#"><img alt="瀏覽" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</c:if>
		
		<!-- 외국인 Old -->
		<c:if test="${calculateInfo.mbrClsCd eq 'US000103'}">
			<table summary="銀行資料">
				<caption>銀行資料</caption>
				<colgroup>
					<col width="15%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> 護照證明影本</th>
						<td class="bgnone"> 
						<div class="fileinputs" id="divIdentityDoc">
							<span><input type="file" class="inputFile" id="identityDoc" name="calculateInfo.identityDoc"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempIdentityDocFile','pict');" /></span>
							<div class="fakefile">
								<input type="hidden" id="identityDocFile" value="<g:tagAttb value="${calculateInfo.identityDocNm}"/>" />
								<input type="text" class="w180" name="calculateInfo.identityDocNm" id="tempIdentityDocFile" disabled="disabled" readonly value="<g:tagAttb value="${calculateInfo.identityDocNm}"/>" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.iddoc03'/>" /> &nbsp;
								<a href="#"><img alt="瀏覽" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
							</div>
						</div>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="account">帳戶名稱</label></th>
						<td>
							<input type="hidden" id="tempAccount" value="<g:tagAttb value="${calculateInfo.acctNm}"/>"/>
							<input type="text" class="w180" name="calculateInfo.acctNm" id="account" value="${calculateInfo.acctNm}"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acnm01'/>"
							v:checkspecial  m:checkspecial="<gm:tagAttb value='jsp.member.mypage.msg.com01'/>"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acnm02'/>" />
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="bankname">銀行名稱</label></th>
						<td>
							<input type="hidden" id="tempBankname" value="<g:tagAttb value="${calculateInfo.bankNm}"/>"/>
							<input type="text" class="w180" name="calculateInfo.bankNm" id="bankname" value="<g:tagAttb value="${calculateInfo.bankNm}"/>"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.bk02'/>" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bk01'/>" />
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="accountnum">銀行帳戶</label></th>
						<td>
							<input type="hidden" id="tempAccountnum" value="<g:tagAttb value="${calculateInfo.acctNo}"/>"/>
							<input type="text" class="w227" name="calculateInfo.acctNo" id="accountnum" value="${calculateInfo.acctNo}" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acno02'/>"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acno01'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.acno03'/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="bankcode">銀行代號</label></th>
						<td>
							<div class="fltl pad_r4">
							<select id="bankcode" name="calculateInfo.bankGlType" class="w188">
								<gc:options group="US0057" codeType="full" value="${calculateInfo.bankGlType}"/>
							</select>
							</div>
							<input type="hidden" id="tempBankcode1" value="<g:tagAttb value="${calculateInfo.bankGlCd}"/>" />
							<input type="text" id="bankcode1" name="calculateInfo.bankGlCd" class="w180" 
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.bkcd01'/>"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bkcd02'/>" value="${calculateInfo.bankGlCd}"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="selmoney">匯款幣別</label></th>
						<td>
							<select id="selmoney" name="calculateInfo.currentcyUnit" class="w188">
								<gc:options group="US0053" codeType="full" value="${calculateInfo.currentcyUnit}" filter="dev"/>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> <label for="bankbook">存摺影本</label></th>
						<td class="bgnone">
							<div class="fileinputs" id="divAccountDoc">
								<span><input type="file" class="inputFile" id="accountDoc" name="calculateInfo.acctDoc"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempAccountDocFile','pict');" /></span>
								<div class="fakefile">
									<input type="hidden" id="accountDocFile" value="<g:tagAttb value="${calculateInfo.acctDocNm}"/>" />
									<input type="text" class="w180" name="calculateInfo.acctDocNm" id="tempAccountDocFile" disabled="disabled" readonly value="<g:tagAttb value="${calculateInfo.acctDocNm}"/>"  v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acdoc01'/>" /> &nbsp;
									<a href="#"><img alt="瀏覽" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</c:if>
		
		</div>
		<div class="guideinfo">
			<p class="pbult04"> &middot; 您變更的銀行資料經管理者核准後, 才可使用變更之銀行資料進行結算處理</p>
            <p class="pad_l15"> &middot; 銀行帳戶變更是在每月1日至20日以前要申請完成, 之後會向新的帳戶匯錢</p>
		</div>
		<div class="btnarea mar_t30">
			<a shape="hover"><img id="btnSave" type="image" alt="確定" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif"/>" style="cursor: pointer;"/></a></li>
			<a href="${CONF['omp.common.url-prefix.http.dev']}${pageContext.request.contextPath }/main/main.omp"><img alt="取消" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif"/>"></a>
		</div>
	</form>
	</div>
	<!-- //CONTENT TABLE E-->
	<div id="pop_area01" style="display:none;"></div>
	
	<div id="pop_ok01" style="display:none;">
		<h2><img alt="確認變更之資料儲存與否" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/mp_title_01.gif"/>"></h2>
		<p class="pop_txt2">您所變更的個人資料尚未儲存! <br />若未經儲存離開本面,  您的變更資料將被刪除!</p>
		<div class="pop_btn">
			<a href="javascript:saveMoveTabPage();"><img alt="儲存" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/btn_savemove.gif"/>"></a>
			<a href="javascript:cancelMoveTabPage();"><img alt="取消" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/btn_cancle_02.gif"/>"></a>
		</div>
		<p class="pop_close"><a href="javascript:closePopLayer('pop_area');"><img alt="close" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif"/>"></a></p>
	</div>
	
	<div id="pop_ok02" style="display:none;">
			<h2><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/mp_title_02.gif"/>" alt="申請銀行資料審核" /></h2>
			<p class="pop_txt2">您是否要申請銀行資料審核?</p>
			<div class="pop_btn">
				<a href="javascript:submitSave();"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/btn_yes.gif"/>" alt="是" /></a>
				<a href="javascript:closePopLayer('pop_area');"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/btn_no.gif"/>" alt="否" /></a>
			</div>
			<p class="pop_close"><a href="javascript:closePopLayer('pop_area');"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif"/>" alt="close" /></a></p>
	</div>
</div>