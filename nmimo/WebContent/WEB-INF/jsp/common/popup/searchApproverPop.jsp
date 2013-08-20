<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<style>
	html,body{background:#fff;}
	html {overflow:hidden;}
</style>
<div id="popup" style="width:400px;">

<script type="text/javascript">

$(function(){
	
	//비밀번호 초기화
	$("#goSearch").click(function(){
 		submitSave("frm","/user/ajaxSearchApprover.do", goSearchResult);
	});
	
	//승인자ID opener 팝업창에 세팅
	$("#goSubmit").click(function(){
		$("#basApvrId",opener.document).val($("#setUserId").val());
		self.close();
	});


});

//비밀번호 초기화 Result
function goSearchResult(text){
	if("F"==text.resultCode){
		alert(text.resultMsg);
		$("#resultS").hide();
		$("#resultF").show();
	}else{
		$("#sUserId").text(text.userId);
		$("#sUserNm").text(text.userNm);
		$("#sUserRlvnDeptNm").text(text.userRlvnDeptNm);
		$("#setUserId").val(text.userId);
		$("#resultS").show();
		$("#resultF").hide();
	}
}

</script>

<!-- pTop -->
<div class="pTop">
	<h2>발송 승인자 검색</h2>
</div>
<hr />

<!-- pContents -->
<div class="pContents">

	<!-- Table Area -->
	<form name="frm" id="frm" method="post">
	<input type="hidden" name="setUserId" id="setUserId">
	<table summary="쓰기" class="WriteTable">
		<caption>리스트</caption>
		<colgroup>
			<col width="25%" />
			<col width="*" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" class="l" style="width:70px">발송승인자</th>
				<td class="l"><input name="userId" id="userId" type="text" style="width:150px"> <a href="#" id="goSearch" class="btn_sml"><span>검 색</span></a>
                <span class="fontSS">부서장의 사번으로 검색합니다.</span>
                </td>
			</tr>
		</tbody>
	</table>

	<!-- Table Area -->
	<table summary="쓰기" class="ListTable" style="margin-top:10px">
		<caption>리스트</caption>
			<thead>
				<tr>
					<th style="width:100px">사번</th>
					<th style="width:100px">이름</th>
					<th style="width:120px">부서명</th>
				</tr>
			</thead>
		<tbody>
			<tr id="resultS" style="display:none">
				<td><span id="sUserId"></span></td>
				<td><span id="sUserNm"></span></td>
				<td><span id="sUserRlvnDeptNm"></span></td>
			</tr>
            <!--검색결과가 없을 경우-->
			<tr id="resultF">
				<td colspan="4" class="nosearch">검색결과가 없습니다.</td>
			</tr>

		</tbody>
	</table>
	<table>
		<tr height="15"><td>&nbsp;</td></tr>
		<tr><td ><font color="red">※ 발송 승인자 정보가 없이 회원가입은 가능하나 메시지 발송 업무를<br /><br />&nbsp;&nbsp;&nbsp;&nbsp;진행 할 수 없으니 이점 유의 하시기 바랍니다.</font></td></tr>
	</table>
    </form>
</div>
<hr />

				

<!-- pBottom -->
<div class="pBottom">
	<a href="#" id="goSubmit" class="btn_red"><strong>확 인</strong></a>
	<a href="javascript:window.open('','_self').close();" class="btn_red"><strong>취 소</strong></a>
</div>

</div>`