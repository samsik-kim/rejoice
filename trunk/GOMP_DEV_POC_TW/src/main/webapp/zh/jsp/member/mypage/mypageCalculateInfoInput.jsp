<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<style>							
	.fileinputs {position: relative; overflow: hidden; height: 24px; width: 600px;}
	.fileinputs * {vertical-align: middle;}
	.fakefile {position: absolute; top:0px; left:0px; height: 30px;  z-index: 1;}
	.inputFile {position: relative; text-align: right; top: -12px; width: 250px; height:35px; filter: alpha(opacity=0); opacity: 0; z-index: 2; direction: ltl; selector-dummy: expression(this.hideFocus=true);}
</style>

<script type="text/javascript">
$(document).ready(function(){ 
	createPopupLayer('pop_area');
<c:if test="${calculateInfo.mbrClsCd ne 'US000103'}">
	
</c:if>
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

/*
 * Save
 */
function submitSave() {
	if(showValidate('inputForm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
		$("#forwardAction").val("CALCULATE");
		var options = {
				type	: "POST",
		        dataType: "json",
		        async	: true,		
				cache	: false,
		        success	: function(responseText, status){
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
//저장 확인 레이어 팝업
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

//탭이동
function moveTabPage(target) {
	if(isExistChangedInfo()) {
		$("#forwardAction").val(target);
		$('#pop_area01').show();
		$("#pop_area01").html($('#pop_ok01').html());
		$("#pop_area_body").html($('#pop_area01'));
		showPopupLayer('pop_area', 'wrap');
	}else {
		if(target == "PROFILE") {
			$('#introFrm').attr('action', env.contextPath + "/mypage/mypageProfileView.omp");
			$('#introFrm').submit();
		}
	}
}

// 저장 취소 후 탭 이동
function cancelMoveTabPage() {
	if($("#forwardAction").val() == "PROFILE") {
		$('#introFrm').attr('action', env.contextPath + "/mypage/mypageProfileView.omp");
		$('#introFrm').submit();
	}
}

// 저장 후 탭 이동
function saveMoveTabPage() {
	closePopLayer('pop_area');
	if(showValidate('inputForm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
		var options = {
				type	: "POST",
		        dataType: "json",
		        async	: true,		
				cache	: false,
		        success	: function(responseText, status){
					//alert("responseText1 : " + responseText + "\nstatus : " + status);
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

function isExistChangedInfo() {
	if($("#tempIdentityDocFile").val() != "") return true;
	if($("#acctNm").val() != "") return true;
	if($("#accountnum").val() != "") return true;
	if($("#tempAccountDocFile").val() != "") return true;
<c:if test="${calculateInfo.mbrClsCd eq 'US000101'}">
	if($("#bankinfo").val() != "") return true;
	if($("#bankinfo1").val() != "") return true;
</c:if>
<c:if test="${calculateInfo.mbrClsCd eq 'US000102'}">
	if($("#bizCatCd input:radio:checked").val() != undefined) return true;
	if($("#bankinfo").val() != "") return true;
	if($("#bankinfo1").val() != "") return true;
</c:if>
<c:if test="${calculateInfo.mbrClsCd eq 'US000103'}">
	if($("#bankname").val() != "") return true;
	if($("#bankcode").val() != "") return true;
	if($("#Swiftcode").val() != "") return true;
</c:if>
}
</script>
<form name="introFrm" id="introFrm" method="post">
	<input type="hidden" name="isProfile" id="isProfile" value="${isProfile }"/>
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
	<form id="inputForm" name="inputForm"  method="post" enctype="multipart/form-data" action="<c:url value="/mypage/mypageCalculateInfoInsert.omp"/>">
		<input type="hidden" id="forwardAction" name="forwardAction" value=""/>
		<input type="hidden" name="isProfile" id="isProfile" value="${isProfile }"/>
		<!-- Tab_menu S -->
		<div class="tab mar_b22">
			<ul>
				<li><a href="javascript:moveTabPage('PROFILE');"><img alt="基本資料" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_tab01.gif"/>"></a></li>
				<li><a href="#"><img alt="銀行資料" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_tab02_on.gif"/>"></a></li>
			</ul>
			<p class="info"><span>*</span> 為必填欄位</p>
		</div>
		<!-- //Tab_menu E -->    
		<h4 class="h41"><img alt="銀行資料" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/mp_h4_02.gif"/>"></h4>
		<div class="tstyleA">
			<table summary="銀行資料">
				<caption>銀行資料</caption>
				<colgroup>
					<col width="15%">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<c:if test="${calculateInfo.mbrClsCd eq 'US000101'}">
						<th scope="row"><span>*</span> 身分證影本</th>
						</c:if>
						<c:if test="${calculateInfo.mbrClsCd eq 'US000102'}">
						<th scope="row"><span>*</span> 公司營業證影本</th>
						</c:if>
						<c:if test="${calculateInfo.mbrClsCd eq 'US000103'}">
						<th scope="row"><span>*</span> 護照證明影本</th>
						</c:if>
						<td>
							<div class="fileinputs" id="divIdentityDoc">
								<span><input type="file" class="inputFile" id="identityDoc" name="calculateInfo.identityDoc"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempIdentityDocFile','pict');" style="cursor: pointer;" onclick="this.blur();" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.iddoc01'/>" /></span>
								<div class="fakefile">
									<input type="text" class="w180" name="calculateInfo.identityDocNm" id="tempIdentityDocFile" disabled="disabled" readonly value="${calculateInfo.identityDocNm}" /> &nbsp;
									<a href="#"><img alt="瀏覽" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
								</div>
							</div>
						</td>
					</tr>
					<c:if test="${calculateInfo.mbrClsCd eq 'US000102'}">
					<tr>
						<th scope="row"><span>*</span> 營業分類</th>
						<td id="bizCatCd">
							<gc:radioButtons name="calculateInfo.bizCatCd" group="US0009" codeType="full" value="${calculateInfo.bizCatCd}" divide="&nbsp; &nbsp; &nbsp;" extra=" aa  ">
								<g:param name="extra">
									v:mustcheck="1,1" m:mustcheck="<gm:tagAttb value="jsp.member.mypage.msg.comp01"/>"
								</g:param>
							</gc:radioButtons>
						</td>
					</tr>
					</c:if>
					<tr>
						<th scope="row"><span>*</span> <label for="account">帳戶名稱</label></th>
						<td><input type="text" class="w180" name="calculateInfo.acctNm" id="acctNm" value="${calculateInfo.acctNm}"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acnm01'/>"
							v:checkspecial  m:checkspecial="<gm:tagAttb value='jsp.member.mypage.msg.com01'/>"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acnm02'/>" /></td>
					</tr>
					
					<!-- 개인 -->
					<c:if test="${calculateInfo.mbrClsCd eq 'US000101'}">
					<tr>
						<th scope="row"><span>*</span> <label for="bankinfo">銀行代號</label></th>
						<td><input type="text" id="bankinfo" name="calculateInfo.bankCd" class="w180" readonly="readonly" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bk01'/>"/>&nbsp;
						<a href="#" id="searchBank"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/btn_bankcode.gif'/>" alt="查詢銀行代號" /></a>&nbsp;&nbsp;
						<input type="text" id="bankinfo1" name="calculateInfo.bankNm" class="w180" readonly="readonly"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="accountnum">銀行帳戶</label></th>
						<td><input type="text" class="w180" name="calculateInfo.acctNo" id="accountnum" value="${calculateInfo.acctNo}"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acno01'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.acno03'/>"
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acno02'/>" /></td>
					</tr>
					</c:if>
					
					<!-- 회사 -->
					<c:if test="${calculateInfo.mbrClsCd eq 'US000102'}">
					<tr>
						<th scope="row"><span>*</span> <label for="bankinfo">銀行代號</label></th>
						<td><input type="text" id="bankinfo" name="calculateInfo.bankCd" class="w180" readonly="readonly" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bk01'/>"/>&nbsp;
						<a href="#" id="searchBank"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/btn_bankcode.gif'/>" alt="瀏覽銀行代號" id="searchBank" /></a>&nbsp;&nbsp;
						<input type="text" id="bankinfo1" name="calculateInfo.bankNm" class="w180" readonly="readonly"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="accountnum">銀行帳戶</label></th>
						<td><input type="text" class="w180" name="calculateInfo.acctNo" id="accountnum" value="${calculateInfo.acctNo}" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acno02'/>"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acno01'/>" 
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.acno03'/>"/>
						</td>
					</tr>
					</c:if>
					
					<!-- 외국인 -->
					<c:if test="${calculateInfo.mbrClsCd eq 'US000103'}">
					<!-- New -->
					<tr>
						<th scope="row"><span>*</span> <label for="bankname">銀行名稱</label></th>
						<td><input type="text" id="bankname" name="calculateInfo.bankNm" class="w180" value="${calculateInfo.bankNm}"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.bk02'/>" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bk01'/>" /></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="accountnum">銀行帳戶</label></th>
						<td><input type="text" class="w180" name="calculateInfo.acctNo" id="accountnum" value="${calculateInfo.acctNo}" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acno02'/>"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.acno01'/>"
							v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.acno03'/>"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="bankcode">銀行代號</label></th>
						<td>
							<div class="fltl pad_r4">
							<select id="bankGlType" name="calculateInfo.bankGlType" class="w188">
								<gc:options group="US0057" codeType="full" value="${calculateInfo.bankGlType}"/>
							</select>
							</div>
							<input type="text" id="bankcode" name="calculateInfo.bankGlCd" class="w180" value="${calculateInfo.bankCd}"
							v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.bkcd01'/>" 
							v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.bkcd02'/>"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="selmoney">匯款幣別</label></th>
						<td>
							<select id="selmoney" name="calculateInfo.currentcyUnit" class="w188">
								<gc:options group="US0053" codeType="full" value="${calculateInfo.currentcyUnit}"/>
							</select>
						</td>
					</tr>
					</c:if>
					
					<tr>
						<th class="tit01" scope="row"><span>*</span> <label for="bankbook">存摺影本</label></th>
						<td class="bgnone">
							<div class="fileinputs" id="divAccountDoc">
								<span><input type="file" class="inputFile" id="accountDoc" name="calculateInfo.acctDoc"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempAccountDocFile','pict');" style="cursor: pointer;" onclick="this.blur();" v:required='trim' m:required="<gm:tagAttb value='jsp.member.mypage.msg.acdoc01'/>"/></span>
								<div class="fakefile">
									<input type="text" class="w180" name="calculateInfo.acctDocNm" id="tempAccountDocFile" disabled="disabled" readonly value="${calculateInfo.acctDocNm}"/> &nbsp;
									<a href="#"><img alt="瀏覽" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/mp/btn_search.gif"/>"></a>
								</div>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		</form>
		<div class="guideinfo">
			<p class="pbult01">注意事項</p>
			<ul class="bult01 txt_90">
				<li>請填寫正確之銀行資料以順利銷售付費產品.</li>
				<li>您所填寫之銀行資料需通過管理者審核, 審核完成且繳交會員費後, 才可販售付費產品.</li>
			</ul>
		</div>
		<div class="btnarea mar_t30">
			<input id="btnSave" type="image" alt="確定" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif"/>">
			<a href="javascript:TotalFileSizeLimit();"><img alt="取消" src="<c:url value="/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif"/>"></a>
		</div>

	
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