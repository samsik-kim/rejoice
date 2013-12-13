<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<c:url value="/inc/_pophead.js"/>"></script>
<script type="text/javascript">
function limtAttach(obj) {
	var isSubmit = false;
	var compStr = new Array("xls", "doc");
	var updVal = document.caseForm.bodyUpload.value;
	var str = obj.value;
	if( str == '') {
		return;
	}
	idx = str.lastIndexOf('.');
	if (idx != -1) {
		ext = str.substring(idx + 1, str.len);
	} else {
		ext = "";
	}		
	if (ext != "") {
		for (i = 0; i < compStr.length; i++) {
			if (ext.toLowerCase() == compStr[i]) {
				isSubmit = true;
				break;
			}
		}
	}
	if (!isSubmit) {
		alert("<gm:string value='jsp.certifymgr.popupCase.msg.doc_type'/>");
		$("#bodyUpload").parent().html($("#bodyUpload").parent().html());
		return;
	}
	return;
}
function saveCase(){
	var tempNm = document.getElementById('name').value;
	var tempExpl = document.getElementById('explanation').value;
	var tempFile = document.getElementById('bodyUpload').value;
	var tempPlat = document.getElementById('platCtgr').value;
	var tempCtgr = document.getElementById('category').value;
	if(showValidate('caseForm', 'default','')){
		if(document.getElementById('useY').checked==true){
		document.getElementById('usYN').value='Y';
	}else{
		document.getElementById('usYN').value='N';
	}
	var options={ 
			 success:       showResponse,
	         url:       env.contextPath+"/verify/saveCase.omp", 
	         type:      "post"  
		}; 
	$('#caseForm').ajaxSubmit(options);
	}
}

function showResponse(){
	alert("<gm:string value='jsp.certifymgr.popupCase.msg.save'/>");
	window.opener.location.reload();
	window.close();
}

function closeCase(){
	//window.opener.location.reload();
	window.opener.document.location.href = window.opener.document.URL;
	opener.location.replace(window.opener.document.location.href);  
	window.close();
}
</script>
<title>Case 등록</title>
</head>
<body class="popup">
<s:form id="caseForm" name="caseForm" theme="simple" method="post" enctype="multipart/form-data">
	<s:hidden id="fileName" name="fileName"/>
	<s:hidden id="filePathName" name="filePathName"/>
	<s:hidden id="usYN" name="usYN"/>
	<s:hidden id="ctCtgrSeq" name="ctCtgrSeq"/>
	<div id="popup_area_810">
		<h1>Case 내용</h1>
		<table class="pop_tabletype01">
			<colgroup>
				<col width="20%;">
				<col width="80%;">
			</colgroup>
			<tr>
				<th>Platform</th>
				<td>
					<select id="platCtgr" name="verify.platCtgr" style="width: 104px;" >
						<gc:options group="PD0056" codeType="full" excludeFilter="dev" value="${verify.platCtgr}" />
					</select>
				</td>
			</tr>
			<tr>
				<th>Category</th>
				<td>
					<s:if test="ctgrCnt == null">
						<select id="category" name="verify.ctCtgrSeq" style="width: 104px;">
							<option value="">전체 </option>
						</select>
					</s:if>
					<s:else>
						<s:select id="category" name="verify.ctCtgSeq" theme="simple" list="categoryNameList" listKey="ctCtgSeq" listValue="titleNm" value="%{verify.ctCtgSeq}" style="width:104px;"/>
					</s:else>
				</td>
			</tr>
			<tr>
				<th>항목명</th>
				<td><input type="text" id="name" name="verify.titleNm" style="width:300px;" maxlength="20" v:required m:required="<gm:tagAttb value='jsp.certifymgr.popupCase.msg.title01'/>" v:text8_limit="60" m:text8_limit="<gm:tagAttb value="jsp.certifymgr.popupCase.msg.title02"/>"/></td>
			</tr>			
			<tr>
				<th>설명</th>
				<td><input type="text" id="explanation" name="verify.explain" style="width:300px;" maxlength="50" v:required m:required="<gm:tagAttb value='jsp.certifymgr.popupCase.msg.explain01'/>" v:text8_limit="150" m:text8_limit="<gm:tagAttb value="jsp.certifymgr.popupCase.msg.explain02"/>"/></td>
			</tr>
			<tr>
				<th>절차서</th>
				<td>
					<input type="file" id="bodyUpload" name="bodyUpload" onchange="javascript:limtAttach(this);" style="width:400px;" v:required m:required="<gm:tagAttb value='jsp.certifymgr.popupCase.msg.file'/>" />
				</td>
			</tr>
			<tr>
				<th>사용유무</th>
				<td><input type="radio" id="useY" name="use" value="Y" checked/> 예 <input type="radio" id="useN" name="use" value="N" v:mustcheck="1" m:mustcheck="<gm:tagAttb value='jsp.certifymgr.popupCase.msg.use'/>" /> 아니오</td>
			</tr>
		</table>
		<div class="pop_btn" >
			<a class="btn" href="javascript:saveCase();"><strong>확인</strong></a>
			<a class="btn" href="javascript:closeCase();"><strong>닫기</strong></a>
		</div>
	</div><!-- //contents -->
	</s:form>
</body>