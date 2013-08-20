<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<%
	String userId = request.getParameter("userId");
%>

<style>
	html,body{background:#fff;}
</style>
</head>

<script type="text/javascript">

$(function(){
	
	//비밀번호 초기화
	$("#goPwdReset").click(function(){
 		submitSave("frm","/user/ajaxListInfoPwdResetAction.do", goPwdResetResult);
	});
	
	//발송승인자검색 팝업호출
	$("#goSearchApprover").click(function(){
		Common.centerPopupWindow('/common/popup/searchApproverPop.do','searchApproverPop',{width:419,height:325,scrollBars:'no'});
	});
	
	//수정
	$("#goSubmit").click(function(){
 		submitSave("frm","/user/ajaxUpdateUserInfoAction.do", goSubmitResult);
	});

	//해지
	$("#goDelete").click(function(){
 		submitSave("frm","/user/ajaxDeleteUserInfoAction.do", goDeleteResult);
	});

	
});

//비밀번호 초기화 Result
function goPwdResetResult(text){
	alert(text.resultMsg);	
}

//수정Result
function goSubmitResult(text){
	alert(text.resultMsg);
	if("S"==text.resultCode){
		self.close();			
	}
}

//해지 Result
function goDeleteResult(text){
	alert(text.resultMsg);
	if("S"==text.resultCode){
		self.close();			
	}
}

</script>


<div id="popup" style="width:400px;">

<!-- pTop -->
<div class="pTop">
	<h2>사용자 정보 수정</h2>
</div>
<hr />

<!-- pContents -->
<div class="pContents">

	<!-- Table Area -->
	<form name="frm" id="frm" method="post">
	<input type="hidden" name="userId" id="userId" value="${viewInfo.userId}">
	<table summary="쓰기" class="WriteTable">
		<caption>리스트</caption>
		<colgroup>
			<col width="25%" />
			<col width="*" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" class="l" style="width:70px">부서명</th>
				<td class="l"><input type="text" name="userRlvnDeptNm" id="userRlvnDeptNm" style="width:150px" value="${viewInfo.userRlvnDeptNm}"></td>
			</tr>
			<tr>
			  <th scope="row" class="l" style="width:70px">사용자ID</th>
			  <td class="l">${viewInfo.userId}</td>
		 	</tr>
			<tr>
			  <th scope="row" class="l" style="width:70px">사용자명</th>
			  <td class="l">${viewInfo.userNm}</td>
		  	</tr>
			<tr>
			  <th scope="row" class="l" style="width:70px">비밀번호</th>
			  <td class="l"><a href="#" id="goPwdReset" class="btn_sml"><span>비밀번호 초기화</span></a></td>
			</tr>
			<tr>
				<th scope="row" class="l" style="width:70px">권한선택</th>
			  	<td class="l">
				<select name="userAutVal" id="userAutVal" class="sType" style="width:170px;">
					<option value="0"   <c:if test="${viewInfo.userAutVal == '0'}">selected</c:if>>선택</option>
					<option value="CU"  <c:if test="${viewInfo.userAutVal == 'CU'}">selected</c:if>>일반사용자</option>
					<option value="CC1" <c:if test="${viewInfo.userAutVal == 'CC1'}">selected</c:if>>발송승인자</option>
					<option value="CC2" <c:if test="${viewInfo.userAutVal == 'CC2'}">selected</c:if>>발송승인자(114)</option>
					<option value="CC3" <c:if test="${viewInfo.userAutVal == 'CC3'}">selected</c:if>>발송승인자(부서장)</option>
					<option value="MA"  <c:if test="${viewInfo.userAutVal == 'MA'}">selected</c:if>>운영자</option>
					<option value="DV"  <c:if test="${viewInfo.userAutVal == 'DV'}">selected</c:if>>개발자</option>
					<option value="AD"  <c:if test="${viewInfo.userAutVal == 'AD'}">selected</c:if>>통계조회자</option>
					<option value="SC"  <c:if test="${viewInfo.userAutVal == 'SC'}">selected</c:if>>회원관리승인자</option>
					<option value="KT"  <c:if test="${viewInfo.userAutVal == 'KT'}">selected</c:if>>KBN사용자</option>
				</select>
                <input type="text" name="basApvrId" id="basApvrId" style="width:150px" value="${viewInfo.basApvrId}" readonly>
                <a href="#" id="goSearchApprover" class="btn_sml"><span>검색</span></a>
                </br>
                  - 발송 승인자는 신청자의 부서장입니다.</br>
				- 사용자의 경우, 발송 승인자 정보가 없으면</br>
				   &nbsp;&nbsp;&nbsp;메시지 발송이 불가능 하오니 반드시 입력해야</br>
				   &nbsp;&nbsp;&nbsp;합니다.
          		</td>
		  </tr>
			<tr>
			  <th scope="row" class="l" style="width:70px">전화번호</th>
			  <td class="l">${viewInfo.genlTelNo}</td>
		  </tr>
			<tr>
			  <th scope="row" class="l" style="width:70px">휴대폰번호</th>
			  <td class="l">${viewInfo.mphonNo}</td>
		  </tr>
			<tr>
			  <th scope="row" class="l" style="width:70px">이메일</th>
			  <td class="l">${viewInfo.emailAdr}</td>
		  </tr>
		</tbody>
	</table>
	</form>

</div>
<hr />

<!-- pBottom -->
<div class="pBottom">
	<a href="#" id="goSubmit" class="btn_red"><strong>수 정</strong></a>
	<a href="#" id="goDelete" class="btn_red"><strong>해 지</strong></a>
	<a href="javascript:window.open('','_self').close();" class="btn_red"><strong>닫 기</strong></a>
</div>

</div>
