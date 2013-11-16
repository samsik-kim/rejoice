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

	var processCommCdDetail = function() {
		
		if(!showValidate('commCdForm', 'dialog', 'Check Input Value.')) {
			return;
		}

		// ACTION
		//var vFullCd = $("#grpCd").val() + $("#dtlCd").val();
		//$("#dtlFullCd").val( vFullCd );

		if(confirm("<gm:string value='jsp.adminmgr.code.commonCodeDetailView.msg.confirm_ins'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$( "#commCdForm" ).submit();
		}
	};

	var commCdDetailList = function() {
		$( "#commCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeDetailList.omp"/>");
		$( "#commCdForm" ).submit();
	};


</script>

		<div id="location">
			Home &gt; 운영자관리 &gt; 기준정보관리 &gt; <strong>공통코드관리</strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">공통코드 관리</h1>
		<p>공통코드를 조회하고 수정 및 삭제할 수 있습니다.</p>
		<s:form action="processCommCdDetail" id="commCdForm" name="commCdForm" theme="simple">
			<s:hidden id="gubun" name="commCd.gubun" value="DETAIL" />
			<s:hidden id="selectedGrpCd" name="commCd.selectedGrpCd" value="%{commCd.grpCd}" />
			<s:hidden id="srchDtlType" name="commCd.srchDtlType" value="%{commCd.srchDtlType}" />
			<s:hidden id="srchDtlValue" name="commCd.srchDtlValue" value="%{commCd.srchDtlValue}" />
			<s:hidden id="pageNo" name="commCd.page.no" value="%{commCd.page.no}"/>
			<s:hidden id="cntDtlCd" name="commCd.cntDtlCd" value="%{commCd.cntDtlCd}" />
			<s:hidden id="dtlFullCd" name="commCd.dtlFullCd" value="%{commCd.dtlFullCd}" />
		<table class="tabletype01">
			<colgroup><col style="width:15%;"><col style="width:35%;"><col style="width:15%;"><col style="width:35%;"></colgroup>
			<tr>
				<th>그룹코드</th>
				<td>
					<input type="text" id="grpCd" class="txt" name="commCd.grpCd" style="width:210px;" maxlength="6" readonly value="${commCd.grpCd}" 
						v:required m:required="<gm:string value="jsp.adminmgr.code.commonCodeDetailView.msg.insert_grpcd"/>" /></td>
				<th>코드명</th>
				<td>
					<input type="text" id="cdNm" class="txt" name="commCd.cdNm" style="width:210px;" value="${commCd.cdNm}" 
						v:required m:required="<gm:string value="jsp.adminmgr.code.commonCodeDetailView.msg.insert_cdnm"/>" 
						v:text8_limit="200" m:text8_limit="<gm:string value="jsp.adminmgr.code.commonCodeDetailView.msg.check_len_cdnm"/>" /></td>
			</tr>
			<tr>
				<th>상세코드</th>
				<td>
					<input type="text" id="dtlCd" class="txt" name="commCd.dtlCd" style="width:210px;" maxlength="6" value="${commCd.dtlCd}" 
						v:required m:required="<gm:string value="jsp.adminmgr.code.commonCodeDetailView.msg.insert_dtlcd"/>" />
				<th>코드순서</th>
				<td>
					<input type="text" id="displayOrder" class="txt" name="commCd.displayOrder" style="width:210px;" value="${commCd.displayOrder}" maxlength="4" 
						v:required m:required="<gm:string value="jsp.adminmgr.code.commonCodeDetailView.msg.insert_order"/>"
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.code.commonCodeDetailView.msg.check_num_order"/>" /> 
				</td>
			</tr>
			<tr>
				<th>추가필드1</th>
				<td>
					<input type="text" id="addField1" class="txt" name="commCd.addField1" style="width:210px;" value="${commCd.addField1}" 
						v:text8_limit="200" m:text8_limit="<gm:string value="jsp.adminmgr.code.commonCodeDetailView.msg.check_len_addfield1"/>" /></td>
				<th>추가필드2</th>
				<td>
					<input type="text" id="addField2" class="txt" name="commCd.addField2" style="width:210px;" value="${commCd.addField2}" 
						v:text8_limit="200" m:text8_limit="<gm:string value="jsp.adminmgr.code.commonCodeDetailView.msg.check_len_addfield2"/>" /></td>
				</td>
			</tr>
			<tr>
				<th>코드설명</th>
				<td colspan="3">
					<textarea id="description" name="commCd.description" rows="2" cols="75"
						v:text8_limit="200" m:text8_limit="<gm:string value="jsp.adminmgr.code.commonCodeDetailView.msg.check_len_descript"/>" 
						><g:tagBody value="${commCd.description}" /></textarea>
				</td>
			</tr>
			<tr>
				<th>TAG</th>
				<td colspan="3">
					<input type="text" id="tags" class="txt" name="commCd.tags" style="width:620px;" value="${commCd.tags}" /><br />
					* TAG 구분은 콤마( , )로 구분하여 입력하세요.
				</td>
			</tr>
			<tr>
				<th>사용여부</th>
				<td colspan="3">
					<select id="delYn" name="commCd.useYn">
						<option value="Y" <c:if test="${commCd.useYn eq 'Y' }"> selected </c:if>>사용</option>
						<option value="N" <c:if test="${commCd.useYn eq 'N' }"> selected </c:if>>미사용</option>
					</select>
				</td>
			</tr>
		</table>
		</s:form>
		<p class="fr mt10">
			<a class="btn" href="javascript:processCommCdDetail();"><span>저장</span></a>
			<a class="btn" href="javascript:commCdDetailList();"><span>목록</span></a></p>

</body>
</html>