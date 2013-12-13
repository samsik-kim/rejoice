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
<title>아이디 중복확인</title>
</head>
<body class="popup">

<script language="javascript">

	function funcClose( resultId ){

		if("N" == resultId) {
			opener.document.getElementById('chkMgrIdYn').value = "";
			opener.document.getElementById('mgrId').value = "";
			self.close();
		} else {
			opener.document.getElementById('chkMgrIdYn').value = "Y";
			opener.document.getElementById('mgrId').value = document.getElementById('checkMgrId').value;
			opener.document.getElementById('mgrId').readOnly = true;
			self.close();
		}

	}

	var funcCheckForm = {
		checkmgrid : function() {
			var vMgrId = $("form[name=adMgrForm] input[name=checkMgrId]").val();
			var regExp=/^[a-z][a-z0-9_-]{5,12}$/;
			if(!regExp.test( vMgrId )) {
				return false;
			}
			return true;
		}
	};


	function funcPopCheckAccount() {

		if(showValidate('adMgrForm', 'dialog', 'Check Input Value.', funcCheckForm)) {

			document.adMgrForm.action = "popCheckAccount.omp?mgrId=" + document.getElementById('checkMgrId').value;
			document.adMgrForm.submit();

		}

	}

</script>

	<!-- 사용가능한 경우 -->
	<s:form action="popCheckAccount" id="adMgrForm" name="adMgrForm"  theme="simple">
	<div id="popup_area_440">
		<p class="mb10"> 사용하고자 하시는 아이디를 입력 후 버튼을 클릭하세요.</p>
        <div class="box03">
        	<p class="mt20"><label for="#"><strong>아이디</strong></label>
            <input type="text" id="checkMgrId" class="txt" name="checkMgrId" style="width:50%;" value="${chkMgrId}" 
            	onkeydown="javascript: if(event.keyCode == 13) {funcPopCheckAccount();}"
				v:required m:required="<gm:string value="jsp.adminmgr.account.popCheckAccount.msg.insert_id"/>"
				v:checkmgrid m:checkmgrid="<gm:string value="jsp.adminmgr.account.popCheckAccount.msg.check_id"/>" /> 
            <a class="btn_s" href="javascript:funcPopCheckAccount();"><strong>아이디 중복확인</strong></a></p>
	<s:if test="adMgr == null">
            <p class="mt10">"${chkMgrId}" <gm:string value="jsp.adminmgr.account.popCheckAccount.msg.possible"/></p>
	</s:if>
	<s:else>
			<c:set var="resultId" value="N"/>
            <p class="mt10">"${chkMgrId}" <gm:string value="jsp.adminmgr.account.popCheckAccount.msg.impossible"/></p>
	</s:else>
        </div>

		<div class="pop_btn" >
			<a class="btn_s" href="javascript:funcClose('${resultId}');"><strong>확인</strong></a>
		</div>
	</div>
	</s:form>

</body>
</html>
