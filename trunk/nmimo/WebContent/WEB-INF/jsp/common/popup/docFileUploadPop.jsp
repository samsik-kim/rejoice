<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ page import="com.nmimo.common.util.StringUtils"%>


<script>

	$(function(){
		$("#goSubmit").click(function(){
	
			if(showValidate('frm', 'alert', '필수항목을 입력해 주세요.') == false){
				return;
			}
			
			if(fileExtCheck($("#fileNm").val()) == false){
				return;
			}

 			submitSave(	"frm", "/common/popup/ajaxDocFileUploadAction.do", goSubmitResult);
		});
	});
	
	function goSubmitResult(text){
	
		if(text.resultCode=="F"){
			alert(text.resultMsg);
		}else{
			$("#fileOrgName").val(text.fileOrgName);
			$("#fileRealName").val(text.fileRealName);
			$("#tCnt").val(text.tCnt);
			$("#sCnt").val(text.sCnt);
			$("#fCnt").val(text.fCnt);
			$("#frm").attr("action","/common/popup/docFileUploadResultPop.do").submit();
		}
	}

	//업로드 파일 확장자 체크
	function fileExtCheck(v){     

		var filePath = v;      
		var extension = (filePath.substr(filePath.length-3)).toLowerCase();             
		if( extension == 'txt' || extension == 'xls' ){        
			return;			        
		}else{
			alert("txt / xls 파일로만 업로드 가능합니다.");          
			return false;
		}
	}

</script>

<style>
	html,body{background:#fff;}
	html {overflow:hidden;}
</style>
</head>

<div id="popup" style="width:400px;">

<!-- pTop -->
<div class="pTop">
	<h2>발송대상 파일 업로드</h2><br>
</div>
<hr />

<!-- pContents -->
<div class="pContents">
	<form name="frm" id="frm" method="post" enctype="multipart/form-data">
	<input type="hidden" name="fileOrgName" id="fileOrgName">
	<input type="hidden" name=fileRealName id="fileRealName">
	<input type="hidden" name=tCnt id="tCnt">
	<input type="hidden" name=sCnt id="sCnt">
	<input type="hidden" name=fCnt id="fCnt">
    <!-- Upload Area -->
    <input class="btn_sml" type="file" name="fileNm" id="fileNm" value="찾아보기" v:required m:required="발송대상 파일을 선택해 주세요.">
    <a href="#" onclick="javascript:Common.centerPopupWindow('/message/popup/helpFileuploadPop.do','window',{width:800,height:570,scrollBars:'YES'});" class="btn_help"><span>도움말</span></a>
    <div class="pUpload">
        <ul>
        <li><strong>발송대상 파일은 형식에 맞는 TXT, XLS 파일로만 업로드가 가능합니다.(XLSX불가능) <a href="#">>>Excel 양식다운로드</a></strong><br></li>
        <li class="red">공지메시지는 100만건, 홍보 메시지는 50만건으로 제한됩니다. </li>
        </ul>
    </div>
</form>
</div>
<hr />

<!-- pBottom -->
<div class="pBottom_filePop">
	<a href="#" id="goSubmit" class="btn_red"><strong>확 인</strong></a> 
	<a href="#" onclick="javascript:window.open('','_self').close();" class="btn_red"><strong>닫 기</strong></a>
</div>

</div>