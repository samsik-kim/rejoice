<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	
	//취소 -> 목록
	$("#cancelBtn").click(function(){
		$("#frm").submit();
	});
	
	
		
	// 등록
	$('#regBtn').click(function(){
		if(showValidate('frm', 'default', "고객등록")){
			
		}
	});
	
});
</script>
<div class="tstyleA">
<form id="frm" name="frm" method="post" action="/member/list.do">
	<table summary="회원기본정보 입력 항목입니다">
		<caption>회원기본정보 입력 항목</caption>
		<colgroup>
			<col width="15%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" class="tit03">이름</th>
				<td><input type="text" id="MEMBER_NM" name="MEMBER_NM" class="w180" v:required m:required="아이디를 입력하십시오."/></td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> <label for="email1">이메일</label></th>
				<td><input type="text" id="EMAIL" name="EMAIL" class="w180"/>&nbsp;
				<a href="#">중복확인</a></td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> <label for="moblenum">휴대폰 번호</label></th>
				<td><input type="text" id="devHpNo" name="member.hpNo" class="w180"/>
				<span class="txtcolor01">  &nbsp;* “ㅡ” 을 생략하고 숫자로만 입력해 주세요.</span></td>
			</tr> 
			<tr>
				<th scope="row" class="tit03"><label for="phonenum">전화번호</label></th>
				<td>
					<input type="text" id="devHpNo" name="member.hpNo" class="w180"/>
					<span class="txtcolor01"> &nbsp;* “ㅡ” 을 생략하고 숫자로만 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> <label for="address">주소</label></th>
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