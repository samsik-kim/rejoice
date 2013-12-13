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
function go_List(){
	document.detailForm.chk.value = "Y";
	$("#detailForm").attr("action", "licenseList.omp");
	$("#detailForm").submit();
}
</script>
</head>
<body>
<s:form id="detailForm" name="detailForm" theme="simple" method="post">
<s:hidden id="chk" name="chk" />
<input type="hidden" id="pageNo" name="verifyLicenseSub.page.no" value="${verifyLicenseSub.page.no}"/>
<s:hidden id="verifyLicenseSub" name="verifyLicenseSub.startDate" />
<s:hidden id="verifyLicenseSub" name="verifyLicenseSub.endDate" />
<s:hidden id="verifyLicenseSub" name="verifyLicenseSub.keyType" />
<s:hidden id="verifyLicenseSub" name="verifyLicenseSub.keyWord" />
<div id="location">
				Home &gt; 단말관리 &gt; <strong>검증툴 License 관리</strong> 
			</div><!-- //location -->
 
			<h1 class="fl pr10">검증툴 License 관리</h1>
			<p>검증툴 License를 발급하거나 발급 내역을 확인할 수 있습니다.</p>
			<table class="tabletype01">
				<colgroup>
					<col style="width:20%;"><col  style="width:30%;">
					<col style="width:20%;"><col  style="width:30%;">
				</colgroup>
				<tr>
					<th>발급자 ID</th>
					<td><g:text value='${verifyLicense.insId }' /></td>
					<th>발급일시</th>
					<td><g:text value='${verifyLicense.issueDttm }' /></td>
				</tr>
				<tr>
					<th>Wifi MAC</th>
					<td colspan="3" class="align_td">					
						<g:html value='${verifyLicense.macAddr }' />
					</td>
				</tr>
				<tr>
					<th>유효기간</th>
					<td colspan="3" class="align_td">					
						<g:text value='${verifyLicense.validFrdt }' format="L####\-##\-##"/> ~ 
						<g:text value='${verifyLicense.validTodt }' format="L####\-##\-##"/>				
					</td>
				</tr>
			</table>
			<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:go_List();"><span>목록</span></a></p>
			</s:form>
</body>
</html>