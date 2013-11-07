<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/commonTag.tld" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<script type="text/javascript" >

function successUdate() {
	if("${chkResult}" == "error") {
		alert("<gm:string value="請查正密碼"/>");
		self.close();
	}
}
function funcClose() {
	self.close();
}

function funcPopCheckPswdNo() {
	if(showValidate('remittanceForm', 'defalut', 'Check Input Value.')) {
		document.remittanceForm.action = "<c:url value="/settlement/remittance/popConfirmRemittance.omp"/>";
		document.remittanceForm.submit();
	}
}
</script>
	<s:form action="popConfirmRemittance" id="remittanceForm" name="remittanceForm"  theme="simple">
		<input type="hidden" id="updateForm.remittanceS.searchTimeBlock" name="remittanceS.searchTimeBlock" value="${remittanceS.searchTimeBlock}"/>
		<input type="hidden" id="updateForm.remittanceS.rmtReqYyyymm" name="remittanceS.rmtReqYyyymm" value="${remittanceS.rmtReqYyyymm}"/>
		<input type="hidden" id="updateForm.remittanceS.rmtEndCd" name="remittanceS.rmtEndCd" value="${remittanceS.rmtEndCd}">
		<input type="hidden" id="updateForm.remittanceS.searchType" name="remittanceS.searchType" value="${remittanceS.searchType}"/>
		<input type="hidden" id="updateForm.remittanceS.searchCont" name="remittanceS.searchCont" value="${remittanceS.searchCont}"/>
		<input type="hidden" id="updateForm.remittanceS.mbrId" name="remittanceS.mbrId" value="${remittanceS.mbrId}" > 
		<input type="hidden" id="updateForm.remittanceS.mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}" > 
		<input type="hidden" id="updateForm.remittanceS.page.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
		<input type="hidden" id="updateForm.remittanceS.firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
		<input type="hidden" id="updateForm.remittanceSS.rmtReqYyyymm" name="remittanceSS.rmtReqYyyymm" value="${remittanceSS.rmtReqYyyymm}"/>
	<div id="popup_area_440">
		<p class="mb10"><strong>請輸入登入管理者之密碼。</strong></p>
		<table class="pop_tabletype01">
			<colgroup><col width="30%;"><col width="70%;"></colgroup>
			<tr>
				<th>密碼</th>
				<td><input type="password" id="adMgrPswdNo" name="remittanceS.adMgrPswdNo" value="${remittanceS.adMgrPswdNo}" 
	            	onkeydown="javascript: if(event.keyCode == 13) {funcPopCheckPswdNo();}"
					v:required m:required="<gm:string value="jsp.adminmgr.account.accountView.msg.check_password"/>" />
				</td>
			</tr>
		</table>
		<div class="pop_btn" >
			<a class="btn_s" href="javascript:funcPopCheckPswdNo();"><strong>確定</strong></a>
			<a class="btn_s" href="javascript:funcClose();"><strong>關閉</strong></a>
		</div>
	</div>
	</s:form>
	
<script language="javascript">
	successUdate();
</script>
