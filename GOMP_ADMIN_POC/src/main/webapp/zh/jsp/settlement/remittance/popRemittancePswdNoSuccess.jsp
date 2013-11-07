<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<
<script language="javascript">
function successUdate() {
	
	if("${chkResult}" == "error") {
		alert("<gm:string value="${receiptS.processMessage}"/>");
		opener.self.close();
		opener.opener.goPage("${RemittanceS.page.no}");
		self.close();
	}else{
		alert("<g:string value="${RemittanceSS.rmtReqYyyymm}" format="L####-##" /><gm:string value="之匯款作業已結束."/>");
		opener.self.close();
		opener.opener.goPage("${RemittanceS.page.no}");
		self.close();	
	}
}
</script>

<script language="javascript">
	successUdate();
</script>
