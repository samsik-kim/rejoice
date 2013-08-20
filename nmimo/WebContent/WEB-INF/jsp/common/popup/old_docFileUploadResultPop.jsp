<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

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
	        <li><strong>${fileInfo.orgFileName}파일에서 ${fileInfo.fCnt} 건의 부적합폰 모델이 검색되었습니다.</strong></li>
        </ul>
    </div>
    <div class="pUpload">
        <ul>
	        <li>
	        	<strong>전송 가능 대상자 파일</strong><br>
	        	${fileInfo.successFileName} 가 생성되었습니다.<br>
	        	파일을 다운로드 받아 재등록 해주시기 바랍니다.<br>
	        	<div align="right"><a href="#" onclick="goFileDown('${fileInfo.successFileName}');"><img src="/resource/images/btn_create_file_down.gif"></a></div>
        		</a>
	        </li>
        </ul>
    </div>
    <div class="pUpload">
        <ul>
	        <li>
	        	<strong>전송 불가능 대상자 파일</strong><br>
	        	${fileInfo.failFileName}가 생성되었습니다.<br>
				파일을 다운로드 받아 해당 단말기에 서비스가 될수 있도록<br> 
				단말기 등록을 해주시기 바랍니다.<br>
	        	<div align="right"><a href="#" onclick="goFileDown('${fileInfo.failFileName}');"><img src="/resource/images/btn_create_file_down.gif"></a></div>
        	</li>
        </ul>
    </div>
    <div class="pUpload">
        <ul>
	        <li>단말기 등록 문의 : 고객만족팀 고민영 대리</li>
        </ul>
    </div>
</form>
</div>
<hr />

<!-- pBottom -->
<div class="pBottom">
	<a href="#" class="btn_red"><strong>적용</strong></a> 
	<a href="#" class="btn_red"><strong>재등록</strong></a> 
	<a href="#" onclick="javascript:window.open('','_self').close();" class="btn_red"><strong>닫 기</strong></a>
</div>

</div>