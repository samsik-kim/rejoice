<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<%@ page import="com.nmimo.common.util.StringUtils"%>

<style>
	html,body{background:#fff;}
	html {overflow:hidden;}
</style>
</head>

<script type="text/javascript">

function goFileDown(v){
	
	var frm = document.frm;

	frm.fileName.value=v;
	frm.action = "/common/popup/fileDownload.do";
	frm.submit();
}

function goApply(){
	
	opener.document.frm.tgtrFileNm.value = '${fileInfo.fileRealName}';
	opener.document.getElementById('cntInfo1').innerText = '총 : '+'${fileInfo.tCnt}'+' 건   ,   '+
														   '적용가능 : '+'${fileInfo.sCnt}'+' 건   ,   '+
														   '중복 : '+'${fileInfo.fCnt}'+' 건';
	window.close();
}

</script>

<div id="popup" style="width:400px;">

<!-- pTop -->
<div class="pTop">
	<h2>발송대상 파일 업로드</h2>
</div>
<hr />

<!-- pContents -->
<div class="pContents">
	<form name="frm" id="frm" method="POST">
		<input type="hidden" name="fileName" id="fileName"> 
		
	    <div class="pUpload">
	        <ul>
   		        <li>
   		        	<strong>${fileInfo.fileOrgName} 파일에서 아래와 같이 중복처리 되었습니다.<br>
					총 : ${fileInfo.tCnt} 건  |  <font color="blue">적용가능 건수 : ${fileInfo.sCnt} 건</font> | <font color="red">중복 : ${fileInfo.fCnt} 건</font>     		        
   		        	</strong>
   		        </li>
	        </ul>
	    </div>
	    <div class="pUpload">
	        <ul>
		        <li>
		        	<strong>전송 가능 대상자 파일</strong><br>
		        	${fileInfo.fileRealName} 가 생성되었습니다.<br>
		        	<div align="right"><a href="#" onclick="goFileDown('${fileInfo.fileRealName}');"><img src="/resource/images/btn_create_file_down.gif"></a></div>
	        		</a>
		        </li>
	        </ul>
	    </div>
	    <div class="pUpload">
	        <ul>
		        <li>중복처리는 등록한 파일의 번호정보를 이용하여 처리 됩니다.</li>
	        </ul>
	    </div>
	</form>
</div>
<hr />

<!-- pBottom -->
<div class="pBottom">
	<a href="#" onclick="goApply();" class="btn_red"><strong>적용</strong></a> 
	<a href="#" onclick="javascript:window.open('','_self').close();" class="btn_red"><strong>닫 기</strong></a>
</div>

</div>