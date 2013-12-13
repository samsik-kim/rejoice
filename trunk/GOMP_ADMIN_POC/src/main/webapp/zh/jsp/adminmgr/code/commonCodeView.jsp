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
</head>
<body>

<script type="text/javascript">

	$(document).ready( function()	{
	
	} );

	var processCommCd = function() {
		
		if(!showValidate('commCdForm', 'dialog', 'Check Input Value.')) {
			return;
		}

		// ACTION
		//var vFullCd = $("#grpCd").val() + $("#dtlCd").val();
		//$("#dtlFullCd").val( vFullCd );

		if(confirm("<gm:string value='jsp.adminmgr.code.commonCodeView.msg.confirm_ins'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$( "#commCdForm" ).submit();
		}
	};

	var commCdList = function() {
		$( "#commCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeList.omp"/>");
		$( "#commCdForm" ).submit();
	};

</script>

		<div id="location">
			首頁  &gt; 管理者中心  &gt; 標準資訊管理  &gt; <strong>共同編碼管理 </strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">共同編碼管理 </h1>
		<p>可搜尋共同編碼,並進行變更或刪除。</p>
		<s:form action="processCommCd" id="commCdForm" name="commCdForm" theme="simple">
			<s:hidden id="gubun" name="commCd.gubun" value="GROUP" />
			<s:hidden id="srchGrpType" name="commCd.srchGrpType" value="%{commCd.srchGrpType}" />
			<s:hidden id="srchGrpValue" name="commCd.srchGrpValue" value="%{commCd.srchGrpValue}" />
			<s:hidden id="pageNo" name="commCd.page.no" value="%{commCd.page.no}"/>
			<s:hidden id="cntDtlCd" name="commCd.cntDtlCd" value="%{commCd.cntDtlCd}" />
			<s:hidden id="grpCd" name="commCd.grpCd" value="%{commCd.grpCd}" />
			<s:hidden id="dtlFullCd" name="commCd.dtlFullCd" value="%{commCd.dtlFullCd}" />
		<table class="tabletype01">
			<colgroup><col style="width:15%;"><col style="width:35%;"><col style="width:15%;"><col style="width:35%;"></colgroup>
			<tr>
				<th>群組碼</th>
				<td>
					<input id="dtlCd" type="text" name="commCd.dtlCd" class="txt" style="width:210px;" maxlength="6" value="${commCd.dtlCd}" 
						v:required m:required="<gm:string value="jsp.adminmgr.code.commonCodeView.msg.insert_grpcd"/>" /></td>
				<th>編碼名稱</th>
				<td>
					<input id="cdNm" type="text" name="commCd.cdNm" class="txt" style="width:210px;" value="${commCd.cdNm}" 
						v:required m:required="<gm:string value="jsp.adminmgr.code.commonCodeView.msg.insert_cdnm"/>" 
						v:text8_limit="200" m:text8_limit="<gm:string value="jsp.adminmgr.code.commonCodeView.msg.check_len_cdnm"/>" /></td>
			</tr>
			<tr>
				<th>編碼說明</th>
				<td colspan="3">
					<textarea id="description" name="commCd.description" rows="2" cols="75"
						v:text8_limit="200" m:text8_limit="<gm:string value="jsp.adminmgr.code.commonCodeView.msg.check_len_descript"/>" 
						><g:tagBody value="${commCd.description}" /></textarea>
				</td>
			</tr>
			<tr>
				<th>使用與否</th>
				<td colspan="3">
					<select id="delYn" name="commCd.useYn">
						<option value="Y" <c:if test="${commCd.useYn eq 'Y' }"> selected </c:if>>使用</option>
						<option value="N" <c:if test="${commCd.useYn eq 'N' }"> selected </c:if>>未使用</option>
					</select>
				</td>
			</tr>
		</table>
		</s:form>
		<p class="fr mt10">
			<a class="btn" href="javascript:processCommCd();"><span>儲存</span></a>
			<a class="btn" href="javascript:commCdList();"><span>目錄</span></a></p>

</body>
</html>
