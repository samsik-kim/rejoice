<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<style>
	html,body{background:#fff;}
</style>
</head>

<div id="popup" style="width:400px;">

<!-- pTop -->
<div class="pTop">
	<h2>1발송대상 파일 업로드</h2>
</div>
<hr />

<!-- pContents -->
<div class="pContents">

    <!-- Upload Area -->
    <input name="" type="text" id="" style="width:220px"> <a href="#" class="btn_sml"><span>찾아보기</span></a> <a href="#" class="btn_help"><span>도움말</span></a>
    
    <div class="pUpload">
        <ul>
        <li><strong>발송대상 파일은 형식에 맞는 TXT, XLS 파일로만 업로드가 가능합니다.(XLSX불가능) <a href="#">>>Excel 양식다운로드</a></strong></li>
        <li class="red">공지메시지는 100만건, 홍보 메시지는 50만건으로 제한됩니다. </li>
        </ul>
    </div>
</div>
<hr />

<!-- pBottom -->
<div class="pBottom">
	<a href="#" class="btn_red"><strong>확 인</strong></a> 
	<a href="#" class="btn_red"><strong>닫 기</strong></a>
</div>

</div>


