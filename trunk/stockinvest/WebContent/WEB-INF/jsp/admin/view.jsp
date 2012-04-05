<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	//수정-> 관리자수정페이지
	$('#modifyBtn').click(function(){
		if(showValidate('modifyFrm', 'default', "입력오류를 확인하십시오.")){
			if($("passWd").val() == ""){
				alert("암호를 확인 해주세요.");
				return;
			}

			if(!confirm('정말 수정하시겠습니까?')) return;
			
			$("#modifyFrm").attr('action','/admin/adminUpdate.do') ;
			$("#modifyFrm").submit();
		}
	});
	
});
</script>
<div class="tstyleA">
<form id="modifyFrm" name="modifyFrm" method="post" >
	<table summary="종목코드 기본정보 입력 항목입니다">
		<caption>종목코드 입력 항목</caption>
		<colgroup>
			<col width="15%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span>*</span>사용자</th>
				<td>${info.adminId}</td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> <label for="moblenum">종목코드</label></th>
				<td>
					<input type="password" id="passWd" name="passWd" class="w180"
					v:required='trim' m:required="암호를 입력하십시오." 
					value="${info.passWd}"/>&nbsp;
				</td>
			</tr>
			<input type="hidden" id="seqNo" name="seqNo" value="${info.seqNo}"/>
		</tbody>
	</table>
	<br/>
	<div class="btnarea">
		<a href="#"><img id="modifyBtn" src="/resource/images/common/btn_inner_ok3.gif" alt="수정" /></a>
	</div>
</form>				
</div>