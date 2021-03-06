<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#vstDt").datepicker();
	
	//취소 -> 목록
	$("#cancelBtn").click(function(){
		$("#regFrm").attr('action','/member/list.do') ;
		$("#regFrm").submit();
	});
	
		
	// 등록 -> 목록
	$('#regBtn').click(function(){
		if(showValidate('regFrm', 'default', "입력오류를 확인하십시오.")){
			if($("#mdnCk").val() == "N"){
				alert("휴대폰 중복확인을 해주세요.");
				return;
			}
			$("#regFrm").attr('action','/member/insert.do') ;
			$("#regFrm").submit();
		}
	});
	
	$("#mdnCheck").click(function(){
		if(showValidate(document.regFrm.mdn, 'default', "입력오류를 확인하십시오.")){
			var data = $("#regFrm").serialize();
			$.ajax({
				url: '/member/mdnCheck.do',
				dataType: 'json',
				type: "POST",
				data: data,
				async : false,		
				cache : false,	
				success: function(json){
					if(json.result == "SUCCESS"){
						alert("등록 가능한 번호입니다.");
						$("#mdnCk").val("Y");
					}else{
						if(confirm("이미 등록된 번호입니다. \n수정화면으로 이동하시겠습니까?")){
							$("#regFrm").attr("action", "/member/updateForm.do");
							$("#regFrm").submit();
						}
					}
				},
				error: function(xhr, textStatus, errorThrown){
					alert("ERROR!!!");	
				}
			});
		}
	});
	
});
</script>
<div class="tstyleA">
<form id="regFrm" name="regFrm" method="post" >
	<table summary="회원기본정보 입력 항목입니다">
		<caption>회원기본정보 입력 항목</caption>
		<colgroup>
			<col width="15%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span>*</span> 이름</th>
				<td>
					<input type="text" id="memberNm" name="memberNm" class="w180"
					v:required='trim' m:required="이름을 입력하십시오."/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> <label for="moblenum">휴대폰 번호</label></th>
				<td>
					<input type="hidden" name="mdnCk" id="mdnCk" value="N">
					<input type="text" id="mdn" name="mdn" class="w180"
					v:required='trim' m:required="휴대폰번호를 입력하십시오." 
					v:mustnum m:mustnum="휴대폰번호는 숫자로 입력하세요."/>&nbsp;
					<a href="#"><img src="/resource/images/mb/btn_overlap.gif" id="mdnCheck" alt="중복확인" /></a>
					<span class="txtcolor01"> &nbsp;* “ㅡ” 을 생략하고 숫자로만 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> 방문일</th>
				<td>
					<input type="text" id="vstDt" name="vstDt" class="w180" readonly="readonly"
					v:required='trim' m:required="방문일을 입력하십시오."/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> 당첨횟수</th>
				<td>
					<input type="text" id="winCnt" name="winCnt" class="w180" value="0"
					v:mustnum m:mustnum="당첨횟수를 숫자로만 입력하세요."/>&nbsp;
					<span class="txtcolor01"> &nbsp;* 숫자로만 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> 방문횟수</th>
				<td>
					<input type="text" id="vstCnt" name="vstCnt" class="w180" value="1"
					v:required='trim' m:required="방문횟수를 입력하십시오."
					v:mustnum m:mustnum="방문횟수를 숫자로만 입력하세요."/>&nbsp;
					<span class="txtcolor01"> &nbsp;* 숫자로만 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th scope="row" class="tit03">이메일</th>
				<td>
					<input type="text" id="email" name="email" class="w180"
					v:email m:email="이메일 주소가 올바르지 않습니다."/>&nbsp;
				</td>
			</tr>
			<tr>
				<th scope="row" class="tit03">주소</th>
				<td><input type="text" id="ADDR" name="ADDR" class="w376"/></td>
			</tr>
		</tbody>
	</table>
	<br/>
	<div class="btnarea">
		<a href="#"><img id="regBtn" src="/resource/images/common/btn_inner_ok3.gif" alt="등록" /></a>
		<a href="#"><img id="cancelBtn" src="/resource/images/common/btn_cancel2.gif" alt="취소" /></a>
	</div>
</form>			
</div>