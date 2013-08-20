<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<style>
	html,body{background:#fff;}
</style>

<script>

	$(function() {
		
			//Form Submit 이벤트
			$("#formSubmit").click(function(){
 				$("form[name=excelSavePopForm]").attr('action', '/stats/popup/excelSave.do').submit();
				self.close();
			});
	
			//Popup Close 이벤트
			$("#formClose").click(function(){
				self.close();
			});
	});	
	
</script>

<div id="popup" style="width:400px;">

<!-- pTop -->
<div class="pTop">
	<h2>엑셀로 저장하기</h2>
</div>
<hr />

<!-- pContents -->
<div class="pContents">

    <!-- 엑셀로 저장하기 -->
    <div class="pUpload">
        <ul>
        <li>
            <form name="excelSavePopForm" id="excelSavePopForm" method="post">
                <fieldset>
                    <strong>
                    <input name="" type="radio" value="" /> <label for="">전송정보(일반)</label> <input name="" type="radio" value="" style="margin-left:15px" /> <label for="">월별 발송건수</label>
                    </strong>
                </fieldset>
            </form>
            <p style="text-indent:5px; padding-top:5px">를 엑셀로 저장하시겠습니까?</p>
        </li>
        </ul>
    </div>
</div>
<hr />

<!-- pBottom -->
<div class="pBottom">
	<a href="#" class="btn_red" id="formSubmit"><strong>확 인</strong></a> 
	<a href="#" class="btn_red"><strong>취 소</strong></a>
</div>

</div>
