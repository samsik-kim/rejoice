<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<!-- <link rel="stylesheet" type="text/css" href=""/> --> 
<style type="text/css">
	.mDisabled{background:#ededed;}
</style>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/ajaxfileupload.js"/>"></script>
<script type="text/javascript" language="javascript">
//<![CDATA[
var devicePopupCmd = "";           
           
$(document).ready(function(){
	$(document).ajaxStart(function(){
		$.blockUI({message:$("#blockUI")});
	}).ajaxComplete(function(){
		$.unblockUI();
	});
	
	$("#prodNm").val("<gm:string value='jsp.product.phonemapping.msg.aid.inputTitle'/>");
	
	// 페이지 로딩시 선택된 Action에 따라 버튼을 보여줌
	changeAction($('input:radio[id=cmd]:checked').val());
	changeType($('input:radio[id=type]:checked').val());
	
	$("#addBtn").click(function(event){	
		if($('input:radio[id=type]:checked').val() == 'aid'){
			if($.trim($("#aid").val()) == ''){
				//alert('<gm:string value="jsp.product.validate.needCheck"><gm:arg>AID</gm:arg></gm:string>');
				alert('<gm:string value="jsp.product.phonemapping.msg.aid.inputTitle" />');
				return;
			}
		}else{
			if($.trim($('#tmpExcel').val())==''){
				alert('<gm:string value="jsp.phonemapping.device.needExcel"/>');
				return;
			}
		}
		
		if($.trim($("#baseDevice").val()) == ''){
			alert('<gm:string value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.phonemapping.device.baseDevice"/></gm:arg></gm:string>');
			return;
		}
		
		if($.trim($("#addDevice").val()) == ''){
			alert('<gm:string value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.phonemapping.device.addDevice"/></gm:arg></gm:string>');
			return;
		}
		
		if(confirm('<gm:string value="jsp.phonemapping.device.addConfirm"/>')){
			$.post('<c:url value="/phonemapping/ajaxDeviceAddMapping.omp"/>' ,{
					"param.type":$('input:radio[id=type]:checked').val(), 
					"param.baseDevice":$("#baseDevice").val(), 
					"param.addDevice":$("#addDevice").val(), 
					"param.aid":$("#aid").val(), 
					"param.tmpExcel":$("#tmpExcel").val()}, 
				function(data){
					alert(data.msg);
					// if success
					if(data.resultCode == 1){
						window.location.reload(true);				
					}
			},"json");
		}
	});
	
	$("#excelBtn").click(function(event){
		if($.trim($("#searchDevice").val()) == ''){
			alert('<gm:string value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.phonemapping.device.searchDevice"/></gm:arg></gm:string>');
			return;
		}
		document.mappingForm.target="sub";
		document.mappingForm.action="<c:url value="/phonemapping/excelContentList.omp"/>";
		document.mappingForm.submit();
	});
	
	$("#delBtn").click(function(event){
		if($('input:radio[id=type]:checked').val() == 'aid'){
			if($.trim($("#aid").val()) == ''){
				alert('<gm:string value="jsp.product.phonemapping.msg.aid.inputTitle" />');
				return;
			}
		}else{
			if($.trim($('#tmpExcel').val())==''){
				alert('<gm:string value="jsp.phonemapping.device.needExcel"/>');
				return;
			}
		}
		
		if($.trim($("#delDevice").val()) == ''){
			alert('<gm:string value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.phonemapping.device.delDevice"/></gm:arg></gm:string>');
			return;
		}
		
		if(confirm('<gm:string value="jsp.phonemapping.device.delConfirm"/>')){
			$.post('<c:url value="/phonemapping/ajaxDeviceDelMapping.omp"/>' ,{
					"param.type":$('input:radio[id=type]:checked').val(), 
					"param.delDevice":$("#delDevice").val(), 
					"param.aid":$("#aid").val(), 
					"param.tmpExcel":$("#tmpExcel").val()}, 
				function(data){
					alert(data.msg);
					// if success
					if(data.resultCode == 1){
						window.location.reload(true);				
					}
			},"json");
		}
	});
});

function changeAction(cmd){
	// Action Btn
	$(".mBtn").each(function(){
		if(this.id == cmd+'Action'){
			$(this).css("display", "block");
		}else{
			$(this).css("display", "none");
		}
	});
	
	// Device Input
	$(".deviceInput").each(function(){
		$(this).val('');
		$(this).attr('disabled', 'disabled');
		$(this).addClass('mDisabled');
	});
	
	if(cmd == 'add'){
		$("#mappingTypeTr").css("display", "");
		$("#baseDevice").removeAttr('disabled');
		$("#baseDevice").removeClass('mDisabled');
		$("#addDevice").removeAttr('disabled');
		$("#addDevice").removeClass('mDisabled');
		$("#baseDeviceTr").css("display", "");
		$("#addDeviceTr").css("display", "");
		$("#delDeviceTr").css("display", "none");
		$("#targetDeviceTr").css("display", "none");
		$('#aid').val("");
		$("#prodNm").val("<gm:string value='jsp.product.phonemapping.msg.aid.inputTitle'/>");
		$('#baseDevice').val("");
		$('#addDevice').val("");
		$('#baseModelNm').text("");
		$('#addModelNm').text("");
		$('#textPTitle').text("* 신규 또는 기존 상품에 추가 단말을 등록하실 수 있습니다.");
		//$("input:radio[value=aidFile]").removeAttr("disabled");
	}else if(cmd=='del'){
		$("#mappingTypeTr").css("display", "");
		$("#delDevice").removeAttr('disabled');
		$("#delDevice").removeClass('mDisabled');
		//$("input:radio[value=aidFile]").removeAttr("disabled");
		$("#baseDeviceTr").css("display", "none");
		$("#addDeviceTr").css("display", "none");
		$("#delDeviceTr").css("display", "");
		$("#targetDeviceTr").css("display", "none");
		$('#aid').val("");
		$("#prodNm").val("<gm:string value='jsp.product.phonemapping.msg.aid.inputTitle'/>");
		$('#delDevice').val("");
		$('#delModelNm').text("");
		$('#textPTitle').text("* 기존 상품에 등록된 단말을 삭제하실 수 있습니다.");
	}else if(cmd == 'excel'){
		$("#mappingTypeTr").css("display", "none");
		$("#searchDevice").removeAttr('disabled');
		$("#searchDevice").removeClass('mDisabled');
		$("#baseDeviceTr").css("display", "none");
		$("#addDeviceTr").css("display", "none");
		$("#delDeviceTr").css("display", "none");
		$("#targetDeviceTr").css("display", "");
		$('#aid').val("");
		$('#prodNm').val("");
		$('#searchDevice').val("");
		$('#searchModelNm').text("");
		$('#textPTitle').text("* 대상 단말이 등록된 상품 List를 다운로드 받으실 수 있습니다.");
	}
}


function changeType(type){
	// alert("type:"+type);
	if(type == 'aid'){
		$("#aid").val('');
		$("#aidFile").val('');
		$("#tmpExcel").val('');
		$("#prodNm").val("<gm:string value='jsp.product.phonemapping.msg.aid.inputTitle'/>");
		$("#aidArea").css("display", "block");
		
		$("#aidFileArea").css("display", "none");
	}else{
		$("#aid").val('');
		$("#aidFile").val('');
		$("#tmpExcel").val('');
		$("#aidArea").css("display", "none");
		$("#aidFileArea").css("display", "block");
	}
}

function ajaxFileUpload(obj)
{
	if(!/(\.xls)$/i.test(obj.value)) {
        alert("<gm:string value='jsp.phonemapping.device.needExcelType'/>");
        return;
    }
	
	$.ajaxFileUpload
	(
		{
			url:"<c:url value="/phonemapping/ajaxExcelUpload.omp" />", 
			secureuri:false,
			fileElementId:'aidFile',
			dataType: 'JSON',
			success: function (data, status)
			{
				if(data.resultCode == 1){
					if(data.size > 10485760){
						alert("<gm:string value='jsp.product.validate.fileSizeLimit'><gm:arg>10MByte</gm:arg></gm:string>");
						return;
					}
					$("#tmpExcel").val(data.tempPath);
					alert("<gm:string value='jsp.phonemapping.device.excelUploadMsg' />");
				}
			},
			error: function (data, status, e)
			{
				alert(e);
			}
		}
	);
	return false;

}  

function selectContentAid() {
	var m_target = 'productInfoPop';
	openCenteredWindow('about:blank','835', '650', 'yes', m_target);
	$("#param.aid").val("");
	$('#mappingForm').attr("action","./popProductInfo.omp");
	$('#mappingForm').attr("target",m_target);
	$('#mappingForm').submit();	
}

function funcPopSearchPhone(cmd) {
	
	devicePopupCmd = cmd; 
		
    width = 835;
    height = 780;
    x = (screen.width) ? (screen.width-width)/2 : 0;
    y = (screen.height) ? (screen.height-height)/2 : 0;
    scrollOption = "Yes";
    window.open("/adminpoc/device/popDeviceSearching.omp","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");            
};


function selectedContent(aid, prodNm, popObj) {
	$('#aid').val(aid);
	$('#prodNm').val(decodeURI(prodNm));
	popObj.close();
}

function selectedTargetPhone(mgmtPhoneModelNm, modelNm, gdcd, phoneModelCd, lcdSizeNm, vmVerNm, svcCdNm, popObj) {
	
	var phoneInfoText = mgmtPhoneModelNm + "/" + modelNm + "/" + phoneModelCd + "/"  + lcdSizeNm + "/" + vmVerNm + "/" + svcCdNm + "   ";
		
	if (devicePopupCmd == "baseDevice") {
		$('#baseDevice').val(phoneModelCd);
		$('#baseModelNm').text(phoneInfoText);	
	} else if (devicePopupCmd == "addDevice") {
		$('#addDevice').val(phoneModelCd);
		$('#addModelNm').text(phoneInfoText);	
	} else if (devicePopupCmd == "delDevice") {
		$('#delDevice').val(phoneModelCd);
		$('#delModelNm').text(phoneInfoText);	
	} else if (devicePopupCmd == "searchDevice") {
		$('#searchDevice').val(phoneModelCd);
		$('#searchModelNm').text(phoneInfoText);	
	}

	$('#phoneInfoText1').empty();
	$('#phoneInfoText2').empty();
	popObj.close();
}
//]]>
</script>
</head>
<body>
			<div id="location">
				Home &gt; 단말관리 &gt; 서비스단말관리 &gt; <strong>단말 Mapping 관리</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">단말 Mapping 관리</h1>
			<p>대상단말 등록/삭제/조회가 가능합니다.</p>
			<form id="mappingForm" name="mappingForm" method="post""> 
			<table class="tabletype01">
				<colgroup>
					<col style="width:20%;"><col  style="width:80%;">
				</colgroup>
				<tr>
					<th>매핑선택</th>
					<td class="align_td">
						<input type="radio" class="ml05" id="cmd" name="param.cmd" onClick="changeAction('add')" value="add" checked />단말등록 
						<input type="radio" class="ml05" id="cmd" name="param.cmd" onClick="changeAction('del')"  value="del" />단말삭제
						<input type="radio" class="ml05" id="cmd" name="param.cmd" onClick="changeAction('excel')"  value="excel" />단말조회					
						<p class="ml05" id="textPTitle"></p>
					</td>
				</tr>
				<tr id="mappingTypeTr">
					<th>매핑구분</th>
					<td>	
						<input type="radio" class="ml05" id="type" name="param.type" value="aid" onClick="changeType('aid')" checked="checked" />상품매핑 
						<input type="radio" class="ml05" id="type" name="param.type" value="aidFile" onClick="changeType('aidFile')"/>파일매핑 <br />
						<p style="display:block" id="aidArea" class="ml05">
						<input type="hidden" id="aid" name="param.aid" value=""  />
						<input type="text" value="" style="width:300px" id="prodNm"  readonly="readonly"/> <a href="javascript:selectContentAid();" class="btn_s"><span>상품검색</span></a>
						</p>
						<p style="display:none"  id="aidFileArea" class="ml05">
						<input id="aidFile" name="param.aidFile" type="file" style="width:300px;" maxlength="500" onchange="ajaxFileUpload(this)" />
						컨텐츠 ${CONF['omp.admin.phonemapping.excel.row.count'] }개 이하만 매핑가능
						<input type="hidden" id="tmpExcel" name="param.tmpExcel"/>
						</p>
					</td>
				</tr>
				<tr id="baseDeviceTr">
					<th>기준단말</th>
					<td>				
						<input id="baseDevice" name="param.baseDevice" type="hidden" value=""/>
						<span id="baseModelNm" width="300px"></span>
						<a href="javascript:funcPopSearchPhone('baseDevice');" class="btn_s"><span>단말검색</span></a>
						<span id="phoneInfoText1">* 단말 정보를 검색해주세요.</span>
					</td>
				</tr>
				<tr id="addDeviceTr">	
					<th>추가단말</th>
					<td>					
						<input id="addDevice" name="param.addDevice" type="hidden"/>
						<span id="addModelNm" width="300px"></span>
						<a href="javascript:funcPopSearchPhone('addDevice');" class="btn_s"><span>단말검색</span></a>
						<span id="phoneInfoText2">* 단말 정보 : 모델명 / 단말명칭 / 단말코드 / LCD Size / Version / 서비스구분</span>
					</td>
				</tr>
				<tr id="delDeviceTr">
					<th>삭제단말</th>
					<td class="align_td">
						<input id="delDevice" name="param.addDevice" type="hidden" />
						<span id="delModelNm" width="300px"></span>
						<a href="javascript:funcPopSearchPhone('delDevice');" class="btn_s"><span>단말검색</span></a>	
						<span id="phoneInfoText1">* 단말 정보를 검색해주세요.</span>
					</td>
				</tr>
				<tr id="targetDeviceTr">	
					<th>대상단말</th>
					<td class="align_td">					
						<input id="searchDevice" name="param.searchDevice" type="hidden"  value="${param.searchDevice}"/>
						<span id="searchModelNm" width="300px"></span>
						<a href="javascript:funcPopSearchPhone('searchDevice');" class="btn_s"><span>단말검색</span></a>	
						<span id="phoneInfoText1">* 단말 정보를 검색해주세요.</span>
					</td>
				</tr>
			</table>
			</form>
			<p class="btn_wrap text_r mt05">
				<label id="addAction" class="mBtn" style="display:block"><a class="btn" href="#" id="addBtn" ><span>대상단말추가</span></a></label>
				<label id="excelAction"  class="mBtn" style="display:none"><a class="btn" href="#" id="excelBtn" ><span>상품목록 Excel 다운로드</span></a></label>				
				<label id="delAction" class="mBtn" style="display:none"><a class="btn" href="#" id="delBtn"><span>대상단말삭제</span></a></label>
			</p>
			<iframe id="sub" name="sub" style="display:none"></iframe>
</body>
</html>