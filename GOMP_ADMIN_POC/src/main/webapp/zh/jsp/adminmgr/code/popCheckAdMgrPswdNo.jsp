<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/commonTag.tld" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>確認密碼</title>
</head>
<body class="popup">

<script language="javascript">

	function successUdate() {
		if("${chkResult}" == "error") {
			alert("<gm:string value="jsp.adminmgr.account.accountRegist.msg.not_same_password"/>");
			self.close();
		}
	}

	function funcClose() {
		self.close();
	}
	function funcPopCheckPswdNo() {
		if(showValidate('commCdForm', 'dialog', 'Check Input Value.')) {
			if(confirm("<gm:string value='jsp.adminmgr.code.commonCodeList.msg.confirm_realert'/>")) {
				document.commCdForm.action = "<c:url value="/adminMgr/popConfirmCacheCode.omp"/>";
				document.commCdForm.submit();

			}
		}
	}

</script>

	<s:form action="popConfirmCacheCode" id="commCdForm" name="commCdForm"  theme="simple">
	<div id="popup_area_440">
		<p class="mb10"> 請輸入管理者　ＩＤ之密碼。</p>
        <div class="box03">
        	<p class="mt20"><label for="#"><strong>密碼</strong></label>
            <input type="password" id="adMgrPswdNo" class="txt" name="commCd.adMgrPswdNo" style="width:50%;" value="${commCd.adMgrPswdNo}" 
            	onkeydown="javascript: if(event.keyCode == 13) {funcPopCheckPswdNo();}"
				v:required m:required="<gm:string value="jsp.adminmgr.account.accountView.msg.check_password"/>" /> 
        </div>

		<div class="pop_btn" >
			<a class="btn" href="javascript:funcPopCheckPswdNo();"><strong>確定</strong></a>
            <a class="btn" href="javascript:funcClose();"><strong>取消</strong></a>
		</div>
	</div>
	</s:form>

<script language="javascript">
	successUdate();
</script>

</body>
</html>
