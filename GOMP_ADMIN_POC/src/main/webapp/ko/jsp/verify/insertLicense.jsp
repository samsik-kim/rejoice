<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

$(document).ready(function(){
	$("#startDate").datepicker();
	$("#endDate").datepicker();	
	
	if('${verifyLicense.startDate}' == "") {
		
		var startDate = document.getElementById("startDate");
		var endDate = document.getElementById("endDate");
		
		setOrderSearchDateAdminPoC("1month", startDate, endDate);
	}
});

//Search Date
function search_date(term) {
	var startDate = document.getElementById("startDate");
	var endDate = document.getElementById("endDate");
	
	setOrderSearchDateAdminPoC(term, startDate, endDate);
}

function insertLicense(){
	if(showValidate('licenseForm', 'default','')){
		//document.licenseForm.action="insertLicense.omp";
		//document.licenseForm.submit();
		//alert("<gm:string value='jsp.verify.insertLicense.msg.licere'/>");
		$.ajax({ 
 				url:       env.contextPath+"/verifyLicense/insertLicense.omp", 
 				type:      "post"  ,
 				data 	: $("#licenseForm").serialize(),
 				dataType: "JSON",
 				success: function(json){
					if(json.status == 'true'){
					document.licenseForm.verificationLicense.value=json.result;
					alert("<gm:string value='jsp.verify.insertLicense.msg.licere'/>");
					document.licenseForm.action="downLicense.omp";
					document.licenseForm.submit();
					}
				},
				error : function(){
					alert("<gm:string value='jsp.member.common.msg.error'/>");
					return false;
				}
		});
	}
}

function go_List(){
	document.licenseForm.chk.value = "Y";
	$("#licenseForm").attr("action", "licenseList.omp");
	$("#licenseForm").submit();
}

</script>
</head>
<body>
<s:form id="licenseForm" name="licenseForm" theme="simple" method="post" enctype="multipart/form-data">
<input type="hidden" id="validCd" name="validCd"/>
<s:hidden id="chk" name="chk" />
<s:hidden id="verifyLicenseSub" name="verifyLicenseSub.startDate" />
<s:hidden id="verifyLicenseSub" name="verifyLicenseSub.endDate" />
<s:hidden id="verifyLicenseSub" name="verifyLicenseSub.keyType" />
<s:hidden id="verifyLicenseSub" name="verifyLicenseSub.keyWord" />
<s:hidden id="verificationLicense" name="verificationLicense" />
			<div id="location">
				Home &gt; 단말관리 &gt; <strong>검증툴 License 관리</strong> 
			</div><!-- //location -->
 
			<h1 class="fl pr10">검증툴 License 관리</h1>
			<p>검증툴 License를 발급하거나 발급 내역을 확인할 수 있습니다.</p>
			<table class="tabletype01">
				<colgroup>
					<col style="width:20%;"><col style="width:30%;">
					<col style="width:20%;"><col style="width:30%;">
				</colgroup>
				<tr>
					<th>발급자 ID</th>
					<td><g:text value='${verifyLicense.insId }' /></td>
					<th>발급일시</th>
					<td><g:text value='${verifyLicense.regDttm }' /></td>
				</tr>
				<tr>
					<th>Wifi MAC</th>
					<td colspan="3" class="align_td">					
						<input id="macAddr" name="verifyLicense.macAddr" type="text" class="txt" style="width:200px;" onfocus="this.value=''" value="${verifyLicense.macAddr }" v:required="trim" m:required="<gm:tagAttb value='jsp.verify.insertLicense.msg.licechk'/>" v:regexp="([a-zA-Z0-9]{2}\:){5}([a-zA-Z0-9]{2})" m:regexp="<gm:tagAttb value='jsp.verify.insertLicense.msg.licechk'/>" v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.verify.insertLicense.msg.20'/>" />
						Mac Address 12자리 입력해주세요. (예. 40:D3:2D:AF:3E:09)
					</td>
				</tr>
				<tr>
					<th>유효기간</th>
					<td colspan="3" class="align_td">					
						<gc:radioButtons name="verifyLicense.validFrdt" group="AD0002" value="${verifyLicense.validFrdt }" divide="&nbsp; &nbsp; &nbsp; &nbsp;"  />
						<span class="ml30">*유효기간은 라이선스 발급일 기준으로 선택한 기간동안만 유효합니다.</span>
					</td>
				</tr>
			</table>
			<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:insertLicense();"><span>발급하기</span></a></p>
		</s:form>
</body>
</html>