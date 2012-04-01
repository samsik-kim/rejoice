<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(document).ready(function(){
	//취소 -> 목록
	$("#cancelBtn").click(function(){
		$("#regFrm").attr('action','/stockinvest/codeList.do') ;
		$("#regFrm").submit();
	});
	
		
	// 등록 -> 목록
	$('#regBtn').click(function(){
//		if(showValidate('regFrm', 'default', "입력오류를 확인하십시오.")){
			if($("#codeName").val() == ""){
				alert("종목명을 확인 해주세요.");
				return;
			}

			if($("#codeNum").val() == ""){
				alert("종목코드를 확인 해주세요.");
				return;
			}
			
			$("#regFrm").attr('action','/stockinvest/codeInsert.do') ;
			$("#regFrm").submit();
//		}
	});
	
});
</script>
<form id="regFrm" name="regFrm" method="post">
<br><br>
<table width="600" border="0" cellpadding="0" cellspacing="0" align="center">
<tr height="30">
	<td><font color="#AE0000"><B>[ 코드관리 ]</B></font></td>
</tr>
<tr>
   <td>
		<table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#330066">
			<tr height="30" bgcolor="white">
				<td class="subject" width="100">종 목 명</td>
				<td class="object" style="padding-left:5px;">
				<input name="codeName" type="text" size="20" maxlength="20" v:required='trim' m:required="종목명을 입력하십시오."/>
				</td>
				<td class="subject" width="100">종목코드</td>
				<td class="object" style="padding-left:5px;">
				<input name="codeNum" type="text" size="20" maxlength="6" v:required='trim' m:required="종목코드를 입력하십시오.">
				</td>
			</tr>
			<tr height="30" bgcolor="white">
				<td class="subject">전화번호</td>
				<td class="object" style="padding-left:5px;">
				<input name="tel" type="text" size="20">
				</td>
				<td class="subject">정보연락처</td>
				<td class="object" style="padding-left:5px;">
				<input name="infoTel" type="text" size="20">
				</td>
			</tr>
			<tr height="30" bgcolor="white">
				<td class="subject" width="100">지분보유</td>
				<td class="object" style="padding-left:5px;">
				<input name="holdShare" type="text" size="30" maxlength="30">
				</td>
				<td class="subject" width="100">주주</td>
				<td class="object" style="padding-left:5px;">
				<input name="juju" type="text" size="30" maxlength="30">
				</td>
			</tr>
		</table>
	</td>
</tr>
<tr height="30">
	<td align="right">
		<a href="#"><img id="regBtn" src="/resource/images/common/btn_inner_ok3.gif" alt="등록" /></a>
		<a href="#"><img id="cancelBtn" src="/resource/images/common/btn_cancel2.gif" alt="취소" /></a>
	</td>
</tr>
</table>
</form>