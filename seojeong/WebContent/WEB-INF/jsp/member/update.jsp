<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	
	$("#vstDt").datepicker();
	
	//취소 -> 목록
	$("#cancelBtn").click(function(){
		$("#updateFrm").attr('action','/member/list.do') ;
		$("#updateFrm").submit();
	});
	
		
	// 수정 -> 목록
	$('#okBtn').click(function(){
		if(showValidate('updateFrm', 'default', "필수 입력오류를 확인하십시오.")){
			var data = $("#updateFrm").serialize();
			$.ajax({
				url: '/member/update.do',
				dataType: 'json',
				type: "POST",
				data: data,
				async : false,		
				cache : false,	
				success: function(json){
					if(json.result == "SUCCESS"){
						alert("저장 되었습니다.");
						$("#updateFrm").attr('action','/member/list.do') ;
						$("#updateFrm").submit();
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
<form id="updateFrm" name="updateFrm" method="post" >
<input type="hidden" name="currentPage" value="${info.currentPage}">
<input type="hidden" name="seq" value="${info.seq}">
	<table summary="고객 기본정보 상세 항목입니다">
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
					value="${info.memberNm}" v:required='trim' m:required="이름을 입력하세요."/></td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> <label for="moblenum">휴대폰 번호</label></th>
				<td>
					<input type="text" id="mdn" name="mdn" class="w180" value="${info.mdn}"
					v:required='trim' m:required="휴대폰번호를 입력하십시오." 
					v:mustnum m:mustnum="휴대폰번호는 숫자로만 입력하세요."/>&nbsp;
					<span class="txtcolor01"> &nbsp;* “ㅡ” 을 생략하고 숫자로만 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> 방문일</th>
				<td>
					<input type="text" id="vstDt" name="vstDt" class="w180" value="${info.vstDt}"
					readonly="readonly"	v:required='trim' m:required="방문일을 선택 하세요."/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> 당첨횟수</th>
				<td>
					<input type="text" id="winCnt" name="winCnt" class="w180" value="${info.winCnt}"
					v:required='trim' m:required="당첨횟수를 입력하십시오."
					v:mustnum m:mustnum="당첨횟수를 숫자로만 입력하세요."/>&nbsp;
					<span class="txtcolor01"> &nbsp;* 숫자로만 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> 방문횟수</th>
				<td>
					<input type="text" id="vstCnt" name="vstCnt" class="w180" value="${info.vstCnt}"
					v:required='trim' m:required="방문횟수를 입력하십시오."
					v:mustnum m:mustnum="방문횟수를 숫자로만 입력하세요."/>&nbsp;
					<span class="txtcolor01"> &nbsp;* 숫자로만 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th scope="row" class="tit03">이메일</th>
				<td>
					<input type="text" id="email" name="email" class="w180" value="${info.email}"
					v:email m:email="이메일 주소가 올바르지 않습니다."/>&nbsp;
				</td>
			</tr>
			<tr>
				<th scope="row" class="tit03">주소</th>
				<td><input type="text" id="ADDR" name="ADDR" class="w376" value="${info.addr}"/></td>
			</tr>
		</tbody>
	</table>
	<br/>
	<div class="btnarea">
		<a href="#"><img id="okBtn" src="/resource/images/common/btn_inner_ok3.gif" alt="수정" /></a>
		<a href="#"><img id="cancelBtn" src="/resource/images/common/btn_cancel2.gif" alt="취소" /></a>
	</div>
</form>			
</div>