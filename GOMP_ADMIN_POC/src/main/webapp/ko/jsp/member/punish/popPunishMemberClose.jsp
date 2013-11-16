<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>징계 해지</title>

<script type="text/javascript">

	function doSubmit() {
	
		if(showValidate('popPunishMemberForm', 'dialog', 'check input value.')) {
			if(confirm("<gm:string value='jsp.member.punish.popPunishMemberClose.msg.success_close'/>")) {
				popPunishMemberForm.submit();
			}
		}
		
	};

	function doCancle(){
		self.close();
	};

</script>

</head>
<body class="popup">
	<s:form action="popUpdateMemberPunish" name="popPunishMemberForm" theme="simple">
		<input type="hidden" name="usMemberPunish.mbrNo" value="${usMemberPunish.mbrNo}" />
		<input type="hidden" name="usMemberPunish.mbrId" value="${usMemberPunish.mbrId}" />
	<div id="popup_area_440">
		<h1>징계 해지</h1>
		<p><strong class="c_06f">${usMemberPunish.mbrId}</strong> 님의 징계 해지 처리 사유를 입력해 주세요.</p>
		<textarea id="pnshCloseDscr" name="usMemberPunish.pnshCloseDscr" rows="5" cols="47"
			v:required="trim" m:required="<gm:string value="jsp.member.punish.popPunishMemberClose.msg.check_dscr"/>" 
			v:text8_limit="2000" m:text8_limit="<gm:string value="jsp.member.punish.popPunishMemberClose.msg.check_len_dscr"/>" ></textarea>
		<div class="pop_btn" >
			<a class="btn_s" href="javascript:doSubmit();"><strong>등록</strong></a>
			<a class="btn_s" href="javascript:doCancle();"><strong>닫기</strong></a>
		</div>
	</div><!-- //contents -->
	</s:form>
</body>
</html>