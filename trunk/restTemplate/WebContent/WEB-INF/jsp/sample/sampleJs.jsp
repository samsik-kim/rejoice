<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link type="text/css" href="/resource/jquery-ui-1.10.3.custom/css/ui-lightness/jquery-ui-1.10.3.custom.css" rel="stylesheet" />
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.10.3.custom/js/jquery-ui-1.10.3.custom.min.js"></script>
<script type="text/javascript" src="/resource/validate.js"></script>

<!-- 	<script type="text/javascript" src="../jquery-latest.js"></script> -->
	<script type="text/javascript">
	$(function() {
// 		$("#tablesorter-demo").tablesorter({sortList:[[0,0],[2,1]], widgets: ['zebra']});
		
//         $('#btnStart').click(function() {
//             $.blockUI({ message: '<h4>Wait Please.</h4>' }); 
//             setTimeout($.unblockUI, 3000);
//         });
	});	
	</script>
</head>
<body>
<hr/>

<p align="center" style="font-size:12pt;font-weight:bold">벨리데이터</p>

<br><br>
<span style="font-size:11pt;font-weight:bold">개요</span><br/>
&lt;script type="text/javascript" src="&lt;c:url value="/js/validate.js"/&gt;"&gt;&lt;/script&gt; 를통해 제공되는 스크립트 벨리데이터 펑션의 형식은 아래와 같습니다.
<div style="border:1px solid black">
/**
 * 입력값 유효검증을 하고 그결과를 표시합니다.
 * @param form 검사 대상 폼의 이름
 * @param showtype 표시 타입, alert, dialog, 그외 등이 가능하며, 그외일 때에는 해당 class 원소들이 메세지 표시에 사용됩니다.
 * @param extraComment 유효검증 실패시 나타낼 추가 코멘트
 * @param exfns 추가 룰 펑션
 * @returns {Boolean} 입력값이 유효하면 true, 유효하지 않으면 false를 반환합니다.
 */
function showValidate(form, showtype, extraComment, exfns);
</div>
<br/><span style="font-size:11pt;font-weight:bold">형식</span><br/>
showValidate 펑션은  form 태그 내에 존재하는 각종 input 태그들 중에 v: 로 시작하는 속성값에 따라 검사하고,
검사 결과가 이상 없을때는 true를 반환하고, 실패한 항목이 있을때는  m: 로 시작하는 검증 오류 메세지를 출력하고 false를 반환합니다.
아래는 아이디와 패스워드 두개의 입력을 받는 로그인 폼의 벨리데이션 설정을 샘플로 보여 줍니다.
<table cellspacing="1" cellpadding="4" style="background:black">
<tr style="background:white">
	<td>예제</td>
	<td>
<form name="loginForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('loginForm', 'alert', '입력 오류가 있습니다.')">
        아이디 : <input type="text" name="id" v:required m:required="아이디를 입력하십시오."/>
        비밀번호 : <input type="password" name="passwd" v:required m:required="비밀번호를 입력하`십시오."/>
	     <input type="submit" value="login"/>
</form>
	</td>
</tr>
</table>
<br/>
v: 와 m:의 형식은 다음과 같습니다.<br>x
&lt;input ... v:{검증방법}[검증index][="검증인수,,,"] m:{검증방법}[검증index]="검증실패시 나타낼 메세지"/&gt; (select, textarea 도 같은 형식)
<ul> 
<li>검증방법은 해당 폼 엘리먼트가 유효하려면 가져야할 값의 검증을 의미하며, validate.js에 제공되는 기본검증방법과 특정 페이지에서만 사용될 추가 검증방법을 지정 할 수 있습니다.</li>
<li>검증인수는 검증방법에 따라 필요한 추가 인수로 비교 대상 값이나, 필드들을 지정하는데 이용되며, 각 검증방법에서 지정하는 형식에 따릅니다.</li>
<li>검증인수가 여러개일때는 구분자 콤마를 사용하며, 검증인수의 값을 상수가 아닌 사용자가 입력한 다른 필드를 지정 하고자 할때는 @{대상필드명}의 형식으로 지정 할 수 있습니다.</li>
<li>검증index는 하나의 폼 요소내에 같은 방법의 검증이 여러개이나 검증 메세지는 각기 다를 경우 검증 메세지와의 짝을 짓기위한 식별자로 사용 되며, 같은 방법의 검증이 2개 이상 나타나지 않을때는 사용할 필요 없습니다.</li>
</ul>

<br/><span style="font-size:11pt;font-weight:bold">내장 검증방법</span><br/>
validate.js 에 포함되어 있는 기본 내장 검증 방법은 아래와 같습니다.
<table cellspacing="1" cellpadding="4" style="background:black">
<tr style="background:white">
	<td width="200" align="center">검증방법</td>
	<td width="*" align="center">설명 및 예제</td>
</tr>
<tr style="background:white">
	<td>
		v:required
	</td>
	<td>
		필수값을 검증합니다.<br/>
		해당 필드의 값이 비어 있다면, 오류 처리하며, 화이트 스페이스는 값이 지정된것으로 간주합니다. (트림하지 않음)<br/>
		이 검증방법은 checkbox나 radio 버튼에서는 사용할 수 없으며, 아래에서 설명하는 mustcheck를 사용하십시오. 
		<table cellspacing="1" cellpadding="4" style="background:black">
		<tr style="background:white">
			<td>예제</td>
			<td>
				<form name="requiredSampleForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('requiredSampleForm', 'dialog', '입력 오류가 있습니다.')">
				        아이디 : <input type="text" name="id"
				                               v:required m:required="아이디를 입력하십시오."/>
				        비밀번호 : <input type="password" name="passwd"
				                               v:required m:required="비밀번호를 입력하십시오."/>
						 <select name="select" id="select" v:required m:required="select box를 선택하세요.">
					     	<option value="">전체</option>
					     	<option value="1">A</option>
					     	<option value="2">B</option>
					     </select>
					     <input type="submit" value="login"/>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr style="background:white">
	<td>
		v:mustcheck="체크갯수"<br/>
		or
		v:mustcheck="최소체크갯수,최대체크갯수"
	</td>
	<td>
		checkbox나 radio버튼의 체크를 검증합니다.<br/>
		체크갯수에 만족하지 않거나, 최소 체크갯수 미달 혹은 최대 체크갯수 초과시 오류 처리 합니다.<br/>
		체크갯수 혹은 최소체크갯수를 1로 지정하면 required와 같은 검증용으로 사용될 수 있습니다.<br/>
		이 검증방법은 한 그룹당 하나의 폼엘리먼트에만 기술해도 됩니다.
		<table cellspacing="1" cellpadding="4" style="background:black">
		<tr style="background:white">
			<td>예제</td>
			<td>
				<form name="mustcheckSampleForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('mustcheckSampleForm', 'dialog', '입력오류를 확인하십시오.')">
				        성별
				          <input type="radio" name="sex" value="m"
				                              v:mustcheck="1" m:mustcheck="성별을 선택하세요"/>남자
				          <input type="radio" name="sex" value="f"/>여자<br/>
				        취미
				          <input type="checkbox" name="hobi" value="1"
				                              v:mustcheck="1,3" m:mustcheck="취미는 하나 이상 3개까지 선택 할 수 있습니다."/>영화
				          <input type="checkbox" name="hobi" value="2"/>운동
				          <input type="checkbox" name="hobi" value="3"/>게임
				          <input type="checkbox" name="hobi" value="4"/>독서
				          <input type="checkbox" name="hobi" value="5"/>음악
				          <input type="checkbox" name="hobi" value="6"/>여행
				          <input type="checkbox" name="hobi" value="7"/>수집
					      <input type="submit" value="전송"/>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr style="background:white">
	<td>
		v:mustnum
	</td>
	<td>
		입력값이 숫자로 유효한지 검사합니다.<br/>
		숫자가 아닌 내용이 입력되었을 경우 오류처리 합니다.<br/>
		입력된 값이 없을 경우 검사하지 않습니다.
		<table cellspacing="1" cellpadding="4" style="background:black">
		<tr style="background:white">
			<td>예제</td>
			<td>
				<form name="mustnumSampleForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('mustnumSampleForm', 'dialog', '다시 입력하세요.')">
				        나이
				          <input type="text" name="age"
				                             v:mustnum m:mustnum="나이는 올바른 숫자로 입력하세요."/>세
					      <input type="submit" value="전송"/>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr style="background:white">
	<td>
		v:scompare="등호,비교값"
		v:ncompare="등호,비교값"
		v:fcompare="등호,비교값"
	</td>
	<td>
		입력값의 대소등 비교를 통해 검증을 수행합니다.<br/>
		scompare는 문자열간의 대소등 비교를, ncompare는 정수의 대소등 비교를, fcompare는 실수의 대소등 비교를 수행합니다.
		주어진 등호식이 거짓일때 오류 처리합니다.<br/>
		입력된 값이 없을 경우 검사하지 않습니다.
		<table cellspacing="1" cellpadding="4" style="background:black">
		<tr style="background:white">
			<td>예제</td>
			<td>
				<form name="ncompareSampleForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('ncompareSampleForm', 'dialog', '다시 입력하세요.')">
				        18금 통과 나이는?
				          <input type="text" name="age"
				                             v:mustnum m:mustnum="18금 통과 나이는 숫자로 입력 해야 합니다."
				                             v:ncompare=">=,18" m:ncompare="18세 이상만 가능합니다."/>세<br/>
				    고등학생 나이는?
				          <input type="text" name="hsage"
				                             v:mustnum m:mustnum="고등학생 나이는 숫자로 입력 해야 합니다."
				                             v:ncompare1=">=,17" m:ncompare1="17세 미만은 너무 어립니다."
				                             v:ncompare2="<=,19" m:ncompare2="20세 이상은 너무 늙었습니다."/>세
				          <input type="button" value="Test" onclick="showValidate(this.form.hsage, 'default', '다시 입력하세요.')"/><br/>
				    선호하는 연예인 나이는?
				          <input type="text" name="fromAge"
				                             v:mustnum m:mustnum="선호하는 연예인 나이(부터)는 숫자로 입력 해야 합니다."
				                             v:ncompare="<=,@{toAge}" m:ncompare="연예인 나이 (부터)가 나이(까지) 보다 클 수 없습니다."/>부터
				          <input type="text" name="toAge"
				                             v:mustnum m:mustnum="선호하는 연예인 나이(까지)는 숫자로 입력 해야 합니다."/>까지
					      <input type="submit" value="전송"/>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr style="background:white">
	<td>
		v:email
	</td>
	<td>
		입력값이 이메일 주소 형식으로 유효한지 검사합니다.<br/>
		입력된 값이 없을 경우 검사하지 않습니다.
		<table cellspacing="1" cellpadding="4" style="background:black">
		<tr style="background:white">
			<td>예제</td>
			<td>
				<form name="emailSampleForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('emailSampleForm', 'dialog', '다시 입력하세요.')">
				        이메일
				          <input type="text" name="email" v:email m:email="이메일 주소가 올바르지 않습니다.."/>
					      <input type="submit" value="전송"/>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr style="background:white">
	<td>
		v:date
	</td>
	<td>
		입력값이 날짜(yyyymmdd)형식으로 유효한지 검사합니다.<br/>
		입력된 값이 없을 경우 검사하지 않습니다.
		<table cellspacing="1" cellpadding="4" style="background:black">
		<tr style="background:white">
			<td>예제</td>
			<td>
				<form name="dateSampleForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('dateSampleForm', 'dialog', '다시 입력하세요.')">
				        시작일
				          <input type="text" name="startDate"
				                             v:required m:required="시작일을 입력하십시오."
				                             v:date m:date="시작일이 올바른 날짜 형식이 아닙니다."
				                             v:scompare="<=,@{endDate}" m:scompare="시작일이 종료일보다 나중 일 수 없습니다."/>
				        종료일
				          <input type="text" name="endDate"
				                             v:required m:required="종료일을 입력하십시오."
				                             v:date m:date="종료일이 올바른 날짜 형식이 아닙니다."/>
					      <input type="submit" value="전송"/>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr style="background:white">
	<td>
		v:regexp
	</td>
	<td>
		입력값이 주어진 정규식에 맞는지 검사합니다.<br/>
		입력된 값이 없을 경우 검사하지 않습니다.
		<table cellspacing="1" cellpadding="4" style="background:black">
		<tr style="background:white">
			<td>예제</td>
			<td>
				<form name="regexpSampleForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('regexpSampleForm', 'dialog', '다시 입력하세요.')">
				        ID
				          <input type="text" name="id"
				                             v:required m:required="ID를 입력하세요."
				                             v:regexp="[a-zA-Z0-9]+" m:regexp="ID는 숫자및 영문만 지정 가능합니다."/>
					      <input type="submit" value="전송"/>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr style="background:white">
	<td>
		v:day_term="{to필드},{허용일수}"
	</td>
	<td>
		두 날짜 사이의 간격을 검사 합니다..<br/>
		두 날짜중 하나라도 입력된 값이 없거나 올바른 날짜 형식이 아니라면, 검사하지 않습니다.<br/>
		이 룰을 거는 쪽는 from 비교 대상 쪽은 to 이어야 하며, to가 from 보다 작다면 오류 처리 됩니다.
		<table cellspacing="1" cellpadding="4" style="background:black">
		<tr style="background:white">
			<td>예제</td>
			<td>
				<script>
					$(document).ready(function() {
						$("form[name=dayTermLimitForm] input[name=startDate]").datepicker();
						$("form[name=dayTermLimitForm] input[name=endDate]").datepicker();
					})
				</script>
				<form name="dayTermLimitForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('dayTermLimitForm', 'dialog', '조회기간 오류')">
				        조회 기간 : 
				          <input type="text" name="startDate"
				                             v:required m:required="조회기간 from을 입력하세요."
				                             v:scompare="lt,@{endDate}" m:scompare="조회기간 from은 to보다 클 수 없습니다."
				                             v:date m:date="조회기간 from은 올바른 날짜 형식이 아닙니다."
				                             v:day_term="7,@{endDate}" m:day_term="조회기간은 7일을 넘을 수 없습니다."/>
				          ~
				          <input type="text" name="endDate"
				                             v:required m:required="조회기간 to를 입력하세요."
				                             v:date m:date="조회기간 to는 올바른 날짜 형식이 아닙니다."/>
					      <input type="submit" value="전송"/>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr style="background:white">
	<td>
		v:text8_limit="{허용byte수}"
	</td>
	<td>
		입력된 글자의 byte 수를 검사하여 주어진 바이트 수보다 클때 오류 처리 합니다.
		<table cellspacing="1" cellpadding="4" style="background:black">
		<tr style="background:white">
			<td>예제</td>
			<td>
				<form name="textLimitForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('textLimitForm', 'dialog', '입력제한 오류')">
					<textarea name="note"
						v:text8_limit="10" m:text8_limit="10바이트 까지만 입력 할 수 있습니다."
							onkeyup="$('#textBytes').text($.getByteLength(this));"></textarea>
					<span id="textBytes">0</span>Bytes<br/>
					<input type="submit" value="전송"/>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br/><span style="font-size:11pt;font-weight:bold">검증결과 오류 메세지 표시 방법</span><br/>
function showValidate(form, showtype, extraComment, exfns)에 전달하는 인수중 검증결과 오류메세지에 관계하는 인수는 extraComment와 showtype 이 있습니다.<br/>
인수 extraComment는 검출된 오류가 1건이상 있을때 함께 나타날 기본 메세지를 지정합니다.<br/>
인수 showtype은 검증 결과에서 검출된 오류 메세지를 표시 하는 방법을 의미하며, 지정하는 값에 따라 다음의 3가지로 구분됩니다.<br/>
<ul type="1">
	<li>"alert", 자바스크립트 alert을 이용하여 오류 메세지를 표시합니다.</li>
	<li>"dialog", jQuery의 dialog를 이용하여 오류 메세지를 표시합니다. 이때 표시되는 메세지에는 오류가 검출된 폼 엘리먼트로 포커스를 이동 시켜주는 링크가 포함됩니다.</li>
	<li>그외, 그외 지정되는 것은 메세지가 표시될 영역의 class 식별자로 이용됩니다. 아래에 추가 설명이 있습니다.</li>
</ul>
<table cellspacing="1" cellpadding="4" style="background:black">
<tr style="background:white">
	<td width="100" align="center">표시방법</td>
	<td width="*" align="center">설명 및 예제</td> 
</tr>
<tr style="background:white">
	<td>
		"alert"
	</td>
	<td>
		자바스크립트 alert을 이용하여 오류 메세지를 표시합니다.<br/>
		메세지 구성 방법은 extraComment + "\n" + 오류메세지1 + "\n" + ... 오류메세지n 입니다.
		<table cellspacing="1" cellpadding="4" style="background:black">
		<tr style="background:white">
			<td>예제</td>
			<td>
				<form name="shotypeAlertSampleForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('shotypeAlertSampleForm', 'alert', '잘못했을껄?')">
				<table>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>ID
				    </td>
				    <td>
				        <input type="text" name="id" maxlength="20"
				               v:required m:required="ID는 필수값입니다."
				               v:regexp="[a-zA-Z][a-zA-Z0-9]{3,}" m:regexp="ID첫자는 영문, 그외는 숫자및 영문이어야하며, 반드시 4자 이상 이어야 합니다."/>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>이름
				    </td>
				    <td>
				        <input type="text" name="name" v:required m:required="이름은 필수값입니다."/>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>이메일
				    </td>
				    <td>
				        <input type="text" name="email"
				               v:required m:required="이메일은 필수값입니다."
				               v:email m:email="올바른 이메일 형식이 아닙니다."/>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>비밀번호
				    </td>
				    <td>
				        <input type="password" name="passwd" maxlength="12"
				               v:required m:required="비밀번호는 필수입니다."
     						   v:regexp="[a-zA-Z0-9]{4,16}" m:regexp="비밀번호는 숫자및 영문이며, 반드시 4자 이상 16자 이하 이어야 합니다."/>
<!--      						   v:regexp=".{6,}" m:regexp="비밀번호는 6자이상 어야 합니다."/>				                -->
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>비밀번호확인
				    </td>
				    <td>
				        <input type="password" name="passwdConfirm" maxlength="12"
				               v:required m:required="비밀번호 확인은 필수입니다."
				               v:scompare="==,@{passwd}" m:scompare="비밀번호와 비밀번호 확인이 일치하지 않습니다."/>
				    </td>
				</tr>
				<tr>
				    <td colspan="2" align="right">
				        <input type="submit" value="가입하기"/>
				    </td>
				</tr>
				</table>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr style="background:white">
	<td>
		"dialog"
	</td>
	<td>
		jQuery의 dialog를 이용하여 오류 메세지를 표시합니다.<br/>
		메세지 구성 방법은 extraComment가 다이얼로그 타이틀로 사용되고, 오류메세지 들은 &lt;ul&gt;태그의 &lt;li&gt;요소로 렌더링 되어 나타납니다.<br/>
		"alert" 방법에 비해 각 오류 메세지 우측에 해당 폼 요소로 바로 가는 포커스 링크가 추가된 점이 다릅니다.  
		<table cellspacing="1" cellpadding="4" style="background:black">
		<tr style="background:white">
			<td>예제</td>
			<td>
				<form name="shotypeDialogSampleForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('shotypeDialogSampleForm', 'dialog', '오류다이얼로그제목')">
				<table>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>ID
				    </td>
				    <td>
				        <input type="text" name="id" maxlength="20"
				               v:required m:required="ID는 필수값입니다."
				               v:regexp="[a-zA-Z][a-zA-Z0-9]{3,}" m:regexp="ID첫자는 영문, 그외는 숫자및 영문이어야하며, 반드시 4자 이상 이어야 합니다."/>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>이름
				    </td>
				    <td>
				        <input type="text" name="name" v:required m:required="이름은 필수값입니다."/>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>이메일
				    </td>
				    <td>
				        <input type="text" name="email"
				               v:required m:required="이메일은 필수값입니다."
				               v:email m:email="올바른 이메일 형식이 아닙니다."/>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>비밀번호
				    </td>
				    <td>
				        <input type="password" name="passwd" maxlength="12"
				               v:required m:required="비밀번호는 필수입니다."
				               v:regexp=".{6,}" m:regexp="비밀번호는 6자이상 어야 합니다."/>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>비밀번호확인
				    </td>
				    <td>
				        <input type="password" name="passwdConfirm" maxlength="12"
				               v:required m:required="비밀번호 확인은 필수입니다."
				               v:scompare="==,@{passwd}" m:scompare="비밀번호와 비밀번호 확인이 일치하지 않습니다."/>
				    </td>
				</tr>
				<tr>
				    <td colspan="2" align="right">
				        <input type="submit" value="가입하기"/>
				    </td>
				</tr>
				</table>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
<tr style="background:white">
	<td>
		그외
	</td>
	<td>
		그외의 문자열을 showtype로 지정하면 해당 문자열은 오류 메세지가 나타나야할 폼 엘리먼트의 class로 간주하고,<br/>
		해당 문자열이 class로 지정된 엘리먼트중 오류가 검출된 폼 요소와 연관된 엘리먼트의 내부(이하 오류표시엘리먼트)에 오류 메세지를 설정하고 display 시킵니다.<br/>
		만약 오류표시엘리먼트 내부에 &lt;span&gt; 태그가 있을때에는 &lt;span&gt; 내부에 메세지를 설정하고 오류표시엘리먼트를 display 시킵니다.<br/>
		오류표시엘리먼트는 미리 html상에 자리를 만들어 두어야 하며, 다음의 두가지 방법을 사용하여 찾게 됩니다.
		<ul type="1">
			<li>명시적인 지정 - class가 showtype과 일치하고 name속성의 값이 "오류검출된 폼요소의 이름"/"폼의 이름" 인 엘리먼트</li>
			<li>묵시적인 지정 - 오류검출된 폼요소 다음에 나타나는 class가 showtype과 일치하는 엘리먼트</li>
		</ul>
		위에 기술된 방법중 순서에 의해 처음 나타나는 엘리먼트를 사용합니다.<br/>
		주의 해야 할 것은 오류표시엘리먼트는 오류발생시에만 나타나야 하기 때문에 css "display"를 "none" 으로 설정해 두어야 합니다. 
		<table cellspacing="1" cellpadding="4" style="background:black">
		<tr style="background:white">
			<td>묵시적인 지정 예제</td>
			<td>
				<form name="shotypeEtc1SampleForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('shotypeEtc1SampleForm', 'verror', '입력된 내용에 오류가 존재합니다.')">
				<table>
				<tr class="verror" name="/shotypeEtc1SampleForm" style="display:none">
					<td colspan="2" align="center">
						<span name="message" style="color:red;font-weight:bold;font-size:10pt"></span>
					</td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>ID
				    </td>
				    <td>
				        <input type="text" name="id" maxlength="20"
				               v:required m:required="ID는 필수값입니다."
				               v:regexp="[a-zA-Z][a-zA-Z0-9]{3,}" m:regexp="ID첫자는 영문, 그외는 숫자및 영문이어야하며, 반드시 4자 이상 이어야 합니다."/>
				        <br/><span class="verror" style="color:red;display:none"></span>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>이름
				    </td>
				    <td>
				        <input type="text" name="name" v:required m:required="이름은 필수값입니다."/>
				        <br/><span class="verror" style="color:red;display:none"></span>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>이메일
				    </td>
				    <td>
				        <input type="text" name="email"
				               v:required m:required="이메일은 필수값입니다."
				               v:email m:email="올바른 이메일 형식이 아닙니다."/>
				        <br/><span class="verror" style="color:red;display:none"></span>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>비밀번호
				    </td>
				    <td>
				        <input type="password" name="passwd" maxlength="12"
				               v:required m:required="비밀번호는 필수입니다."
				               v:regexp=".{6,}" m:regexp="비밀번호는 6자이상 어야 합니다."/>
				        <br/><span class="verror" style="color:red;display:none"></span>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>비밀번호확인
				    </td>
				    <td>
				        <input type="password" name="passwdConfirm" maxlength="12"
				               v:required m:required="비밀번호 확인은 필수입니다."
				               v:scompare="==,@{passwd}" m:scompare="비밀번호와 비밀번호 확인이 일치하지 않습니다."/>
				        <br/><span class="verror" style="color:red;display:none"></span>
				    </td>
				</tr>
				<tr>
				    <td colspan="2" align="right">
				        <input type="submit" value="가입하기"/>
				    </td>
				</tr>
				</table>
				</form>
			</td>
		</tr>
		<tr style="background:white">
			<td>명시적인 지정 예제</td>
			<td>
				<form name="shotypeEtc2SampleForm" method="post" action="/sample/sampleJs.do" onsubmit="return showValidate('shotypeEtc2SampleForm', 'verror', '입력된 내용에 다음과 같은 오류가 존재합니다.')">
				<table>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>ID
				    </td>
				    <td>
				        <input type="text" name="id" maxlength="20"
				               v:required m:required="ID는 필수값입니다."
				               v:regexp="[a-zA-Z][a-zA-Z0-9]{3,}" m:regexp="ID첫자는 영문, 그외는 숫자및 영문이어야하며, 반드시 4자 이상 이어야 합니다."/>
				    </td>
				    <td rowspan="5">
				    	<div class="verror" style="display:none" name="/shotypeEtc2SampleForm">
				    		<span name="message" style="color:red;font-weight:bold"></span>
				    		<ul type="1">
				    			<li class="verror" style="display:none" name="id/shotypeEtc2SampleForm">[ID] <span name="message"></span></li>
				    			<li class="verror" style="display:none" name="name/shotypeEtc2SampleForm">[이름] <span name="message"></span></li>
				    			<li class="verror" style="display:none" name="email/shotypeEtc2SampleForm">[이메일] <span name="message"></span></li>
				    			<li class="verror" style="display:none" name="passwd/shotypeEtc2SampleForm">[비번] <span name="message"></span></li>
				    			<li class="verror" style="display:none" name="passwdConfirm/shotypeEtc2SampleForm">[비번확인] <span name="message"></span></li>
				    		</ul>
				    	</div>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>이름
				    </td>
				    <td>
				        <input type="text" name="name" v:required m:required="이름은 필수값입니다."/>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>이메일
				    </td>
				    <td>
				        <input type="text" name="email"
				               v:required m:required="이메일은 필수값입니다."
				               v:email m:email="올바른 이메일 형식이 아닙니다."/>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>비밀번호
				    </td>
				    <td>
				        <input type="password" name="passwd" maxlength="12"
				               v:required m:required="비밀번호는 필수입니다."
				               v:regexp=".{6,}" m:regexp="비밀번호는 6자이상 어야 합니다."/>
				    </td>
				</tr>
				<tr>
				    <td align="right">
				        <span style="color:red">*</span>비밀번호확인
				    </td>
				    <td>
				        <input type="password" name="passwdConfirm" maxlength="12"
				               v:required m:required="비밀번호 확인은 필수입니다."
				               v:scompare="==,@{passwd}" m:scompare="비밀번호와 비밀번호 확인이 일치하지 않습니다."/>
				    </td>
				</tr>
				<tr>
				    <td colspan="3" align="right">
				        <input type="submit" value="가입하기"/>
				    </td>
				</tr>
				</table>
				</form>
			</td>
		</tr>
		</table>
	</td>
</tr>
</table>

<br/><span style="font-size:11pt;font-weight:bold">커스텀 검증</span><br/>
일반적인 검증방법의 조합으로 처리하기 힘든 검증일 경우 커스텀 검증을 지정하여 체크할 수 있으며, 이 방법은 전통적인 스크립트 펑션 기반의 벨이데이션 처리 방법과 유사합니다.<br/>
function showValidate(form, showtype, extraComment, exfns); 의 인수 exfns에 아래와 같은 형식으로 검증 펑션을 작성하여 처리할 수 있습니다.<br/>

<script type="text/javascript">
document.write("var fooFns = {");
document.write("<br/>");
document.write("펑션이름1 : function() {");
document.write("<br/>");
document.write("//검증 스크립트를 검증 통과시 true, 오류 처리해야 할때는  false를 리턴하도록 작성");
document.write("<br/>");
document.write("},");
document.write("<br/>");
document.write("펑션이름2 : function() {");
document.write("<br/>");
document.write("//검증 스크립트를 검증 통과시 true, 오류 처리해야 할때는  false를 리턴하도록 작성");
document.write("<br/>");
document.write("},");
document.write("<br/>");
document.write(".");
document.write("<br/>");
document.write(".");
document.write("<br/>");
document.write(".");
document.write("<br/>");
document.write("};");
</script>
<!-- <form name="someForm" onsubmit="return showValidate('someForm', 'dialog', '입력오류', fooFns)"> -->
<!-- . -->
<!-- <input type="text" v:펑션이름1 m:펑션이름1="어쩌고는 저쩌고이어야 합니다."/> -->
<!-- <input type="text" v:펑션이름2 m:펑션이름2="요거는 조렇다고?"/> -->
<!-- . -->
<!-- . -->
<!-- </form> -->
 


<br/><span style="font-size:11pt;font-weight:bold">코드 관련 커스텀 태그와의 결합</span><br/>
코드관련 커스텀 태그중 checkbox 혹은 radio버튼 출력 커스텀 태그에서 검증룰을 지정 하고자 할때는 다음과 같은 형식으로 지정 할 수 있습니다.<br/>
&lt;gc:radioButtons group="코드그룹" name="이름" [codeType="코드형식"] [value="코드값"] [direction="나열방향"]&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&lt;g:param value="extra"&gt;v:mustcheck="1" m:mustcheck="반드시 하나는 선택되어야 합니다."&lt;/g:param&gt;<br/>
&lt;/gc:radioButtons&gt;<br/>
&lt;gc:checkBoxs group="코드그룹" name="이름" [codeType="코드형식"] [value="코드값"] [direction="나열방향"]&gt;<br/>
&nbsp;&nbsp;&nbsp;&nbsp;&lt;g:param value="extra"&gt;v:mustcheck="1,5" m:mustcheck="하나이상 다섯개까지 선택 가능합니다."&lt;/g:param&gt;<br/>
&lt;/gc:checkBoxs&gt;


<form name="fileForm" method="post" enctype="multipart/form-data" action="/sample/fileUploadAction.do">
	<br>
	업로드 형식 TXT , XLS , XLSX
	<br><br>
	업로드 파일1	<input type="file" name="file1"><br><br>
	업로드 파일2	<input type="file" name="file2"><br><br>
	업로드 파일3	<input type="file" name="file3"><br><br>
	<input type="submit" value="파일올리기">
</form>

<table id="tablesorter-demo" class="tb" border="0" cellpadding="0" cellspacing="1">
	<thead>
		<tr>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Age</th>
			<th>Total</th>
			<th>Discount</th>
			<th>Difference</th>
			<th>Date</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>Peter</td>
			<td>Parker</td>
			<td>28</td>
			<td>$9.99</td>
			<td>20.9%</td>
			<td>+12.1</td>
			<td>Jul 6, 2006 8:14 AM</td>
		</tr>
		<tr>
			<td>John</td>
			<td>Hood</td>
			<td>33</td>
			<td>$19.99</td>
			<td>25%</td>
			<td>+12</td>
			<td>Dec 10, 2002 5:14 AM</td>
		</tr>
		<tr>
			<td>Clark</td>
			<td>Kent</td>
			<td>18</td>
			<td>$15.89</td>
			<td>44%</td>
			<td>-26</td>
			<td>Jan 12, 2003 11:14 AM</td>
		</tr>
		<tr>
			<td>Bruce</td>
			<td>Almighty</td>
			<td>45</td>
			<td>$153.19</td>
			<td>44.7%</td>
			<td>+77</td>
			<td>Jan 18, 2001 9:12 AM</td>
		</tr>
		<tr>
			<td>Bruce</td>
			<td>Evans</td>
			<td>22</td>
			<td>$13.19</td>
			<td>11%</td>
			<td>-100.9</td>
			<td>Jan 18, 2007 9:12 AM</td>
		</tr>
		<tr>
			<td>Bruce</td>
			<td>Evans</td>
			<td>22</td>
			<td>$13.19</td>
			<td>11%</td>
			<td>0</td>
			<td>Jan 18, 2007 9:12 AM</td>
		</tr>
	</tbody>
</table>
</body>
</html>