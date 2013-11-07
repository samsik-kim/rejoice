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
	if(showValidate('accountsForm', 'defalut', 'Check Input Value.')) {
		//if(confirm("<gm:string value='jsp.adminmgr.code.commonCodeList.msg.confirm_realert'/>")) {
			document.accountsForm.action = "<c:url value="/settlement/accounts/popConfirmAccounts.omp"/>";
			document.accountsForm.submit();

		//}
	}
}
</script>
	<s:form action="popConfirmAccounts" id="accountsForm" name="accountsForm"  theme="simple">
	<input type="hidden" name="accountsS.settYyyymm" value="${accountsS.settYyyymm}">
	<div id="popup_area_440">
		<p class="mb10"><strong>請輸入登入管理者之密碼。</strong></p>
		<table class="pop_tabletype01">
			<colgroup><col width="30%;"><col width="70%;"></colgroup>
			<tr>
				<th>密碼</th>
				<td><input type="password" id="adMgrPswdNo" name="accountsS.adMgrPswdNo" value="${accountsS.adMgrPswdNo}" 
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
