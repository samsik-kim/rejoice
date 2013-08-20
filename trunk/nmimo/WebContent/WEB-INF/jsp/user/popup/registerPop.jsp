<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<style>
	html,body{background:#fff;}
</style>

<script type="text/javascript">
	
	$(function(){
		
		//사용자ID 검색
		$("#goSearch").click(function(){
			
			if(""==$("#userId").val()){
				alert("ID를 입력해 주세요.");
				return;
			}
			
			submitSave(	"frm", "/login/ajaxSearchUserId.do", goSearchResult);			
		});

		//등록 처리
		$("#goSubmit").click(function(){
			
			if(""==$("#userPwd").val()){
				alert("비밀번호를 입력해 주세요.");
				return;
			}

			if("0"==$("#userAutVal option:selected").val()){
				alert("권한을 선택해 주세요.");
				return;
			}

			$("#genlTelNo").val($("#genlTelNo1").val()+"-"+$("#genlTelNo2").val()+"-"+$("#genlTelNo3").val());
			$("#mphonNo").val($("#mphonNo1").val()+"-"+$("#mphonNo2").val()+"-"+$("#mphonNo3").val());
			$("#emailAdr").val($("#emailAdr1").val()+"@"+$("#emailAdr2").val());
			
			submitSave(	"frm", "/login/ajaxRegisterUserInfo.do", goSubmitResult);			
		});
		
	});
	
	//사용자ID 검색 Result
	function goSearchResult(text){
		
		if("F"== text.resultCode){
			alert(text.resultMsg);
			return;
		}

		var genlTelNo = text.genlTelNo;
		var mphonNo = text.mphonNo;
		var emailAdr = text.emailAdr;
		
		var genlTelNo1;
		var genlTelNo2;
		var genlTelNo3;
		var mphonNo1;
		var mphonNo2;
		var mphonNo3;
		var emailAdr1;
		var emailAdr2;
		var strArray;
		
		genlTelNo = genlTelNo.replace(/(^\s*)|(\s*$)/g, "");
		genlTelNo = genlTelNo.replace(" ", "");
		genlTelNo = genlTelNo.replace(" ", "");
		mphonNo = mphonNo.replace(/(^\s*)|(\s*$)/g, "");
		emailAdr = emailAdr.replace(/(^\s*)|(\s*$)/g, "");
		
		//전화번호 세팅
		if(genlTelNo.length > 0){
			if(genlTelNo.length == 9){
				genlTelNo1 = genlTelNo.substr(0,2);				
				genlTelNo2 = genlTelNo.substr(2,3);
				genlTelNo3 = genlTelNo.substr(5,4);
			}else if(genlTelNo.length == 10){
				genlTelNo1 = genlTelNo.substr(0,3);				
				genlTelNo2 = genlTelNo.substr(3,3);
				genlTelNo3 = genlTelNo.substr(6,4);
			}else if(genlTelNo.length == 11){
				genlTelNo1 = genlTelNo.substr(0,3);				
				genlTelNo2 = genlTelNo.substr(3,4);
				genlTelNo3 = genlTelNo.substr(7,4);
			}else{
				genlTelNo1 = genlTelNo;
			}
		}

		//휴대폰번호 세팅
		if(mphonNo.length > 0){
			strArray=mphonNo.split('-');
		
			if(strArray.length == 3){
				mphonNo1 = strArray[0];				
				mphonNo2 = strArray[1];
				mphonNo3 = strArray[2];
			}else{
				mphonNo1 = mphonNo;
			}
		}

		//이메일 세팅
		if(emailAdr.length > 0){
			strArray=emailAdr.split('@');
			
			if(strArray.length == 2){
				emailAdr1 =	strArray[0];
				emailAdr2 = strArray[1];
			}
		}
		
		$("#userRlvnDeptNm").val(text.userRlvnDeptNm);
		$("#userNm").val(text.userNm);
		$("#genlTelNo1").val(genlTelNo1);
		$("#genlTelNo2").val(genlTelNo2);
		$("#genlTelNo3").val(genlTelNo3);
		$("#mphonNo1").val(mphonNo1);
		$("#mphonNo2").val(mphonNo2);
		$("#mphonNo3").val(mphonNo3);
		$("#emailAdr1").val(emailAdr1);
		$("#emailAdr2").val(emailAdr2);
	}
	
	
	//등록 처리 Result
	function goSubmitResult(text){
		
		if("S"==text.resultCode){
			alert(text.resultMsg);
			self.close();
		}else{
			alert(text.resultMsg);
		}
	}
	
</script>

<div id="popup" style="width:400px;">

<!-- pTop -->
<div class="pTop">
	<h2>사용자 신청</h2>
</div>
<hr />

<!-- pContents -->
<div class="pContents">

	<!-- Table Area -->
	<form name="frm" id="frm" method="post">
	<input type="hidden" name="genlTelNo" id="genlTelNo">
	<input type="hidden" name="mphonNo" id="mphonNo">
	<input type="hidden" name="emailAdr" id="emailAdr">
	<table summary="쓰기" class="WriteTable">
		<caption>리스트</caption>
		<colgroup>
			<col width="25%" />
			<col width="*" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" class="l" style="width:300px">부서명</th>
				<td class="l"><input name="userRlvnDeptNm" id="userRlvnDeptNm" type="text"  style="width:150px" readonly></td>
			</tr>
			<tr>
				<th scope="row" class="l">사용자ID</th>
				<td class="l"><input name="userId" id="userId" type="text"  style="width:150px"> <a href="#" id="goSearch" class="btn_ssml"><span>검 색</span></a>
                <span class="fontSS">ID를 정확하게 입력해 주세요.<br />
                부정확한 ID를 입력시 가입이 불가능 합니다.<br />
                아이디에 포함된 알파벳은 대소문자를 정확히 입력해 주세요. </span>
                </td>
			</tr>
			<tr>
				<th scope="row" class="l">사용자명</th>
				<td class="l"><input name="userNm" id="userNm" type="text" style="width:150px" readonly></td>
			</tr>
			<tr>
				<th scope="row" class="l">비밀번호</th>
				<td class="l"><input name="userPwd" id="userPwd" type="password" style="width:100px" ></td>
			</tr>
			<tr>
				<th scope="row" class="l">권한선택</th>
				<td class="l">
                    <select name="userAutVal" id="userAutVal" class="sType" style="width:170px;">
                        <option value="0">선택</option>
                        <option value="CU">일반사용자</option>
                        <option value="CC1">발송승인자</option>
                        <option value="CC2">발송승인자(114)</option>
                        <option value="CC3">발송승인자(부서장)</option>
                        <option value="MA">운영자</option>
                        <option value="DV">개발자</option>
                        <option value="AD">통계조회자</option>
                        <option value="SC">회원관리승인자</option>
                        <option value="KT">KBN사용자</option>
                    </select>
                </td>
			</tr>
			<tr>
				<th scope="row" class="l">전화번호</th>
				<td class="l">
					<input name="genlTelNo1" id="genlTelNo1" type="text" style="width:40px" readonly> - 
					<input name="genlTelNo2" id="genlTelNo2" type="text" style="width:40px" readonly> - 
					<input name="genlTelNo3" id="genlTelNo3" type="text" style="width:40px" readonly>
				</td>
			</tr>
			<tr>
				<th scope="row" class="l">휴대폰번호</th>
				<td class="l">
                    <input name="mphonNo1" id="mphonNo1" type="text"  style="width:40px" readonly>
	                 - <input name="mphonNo2" id="mphonNo2" type="text"  style="width:40px" readonly> 
	                 - <input name="mphonNo3" id="mphonNo3" type="text" style="width:40px" readonly> 
	                 <a href="#" id="goSendAuthNo" class="btn_ssml"><span>인증번호전송</span></a>
				</td>
			</tr>
			<tr>
				<th scope="row" class="l">인증번호</th>
				<td class="l">
					<input name="inputSendAuthNo" id="inputSendAuthNo" type="text" style="width:100px" readonly><a href="#" id="goChkAuthNo" class="btn_ssml"><span>인증하기</span></a>
				</td>
			</tr>
			<tr>
				<th scope="row" class="l">이메일</th>
				<td class="l">
					<input name="emailAdr1" id="emailAdr1" type="text" style="width:80px" readonly> @ <input name="emailAdr2" id="emailAdr2" type="text" style="width:120px" readonly>
                	<span class="fontSS">이메일 계정은 사내 메일 계정을 입력하시기 바랍니다.</span>
                </td>
			</tr>
		</tbody>
	</table>
	</form>
</div>
<hr />

<!-- pBottom -->
<div class="pBottom">
	<a href="#" id="goSubmit" class="btn_red"><span>등 록</span></a> 
	<a href="#" onclick="javascript:window.open('','_self').close();" class="btn_red"><span>닫 기</span></a>
</div>
</div>