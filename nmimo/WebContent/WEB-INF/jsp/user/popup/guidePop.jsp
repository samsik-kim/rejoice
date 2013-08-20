<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<style>
	html,body{background:#fff;}
</style>

<div id="popup" style="width:770px;">

<!-- pTop -->
<div class="pTop">
	<h3>화면 도움말</h3>
	<h4>사용자 신청</h4>
    <h5>MIMO 사이트 이용을 위해서는 반드시 사용자 신청(회원가입 및 승인)후, 로그인을 해야 합니다.</h5>
</div>
<hr />

<!-- pContents -->
<div class="pContents">

	<!-- Text Area -->
    <ul>
        <li>
        	사용자신청을 위해서 MIMO 사이트 접속후, 좌측 하단의 사용자 신청 버튼을 클릭합니다.
        </li>
		<li>
            버튼 클릭후, 사용자 신청 팝업에 나타는 모든 항목을 정확하게 입력합니다.<br />
            입력 내용이 부정확한 경우, 사용자신청 승인이 반려될 수 있습니다.
        </li> 
		<li class="img">
            <img src="../../resource/images/help_000_1.gif" width="300" height="200" /> <img src="../../resource/images/help_000_2.gif" width="300" height="200" /> </li> 

        <li>
        	부서명 : 해당부서명의 풀네임을 띄어쓰기 없이 입력합니다. 
        			<p>부서명이 상이 할 경우, 통계 및 발송결과 검색이 불가능 할 수 있습니다.</p>
        </li>
 		<li>
        사용자 ID : <span style="color:red">사용자 ID는 사번을 입력합니다. </span>
        </li>
        <li>
        사용자명 : 실명을 입력합니다.
                <p><span style="color:blue">사번과 사용자명, 부서명이 명확하지 않은 경우, 사용자승인 반려될 수 있습니다.  </span></p>
        </li>
        <li>
        비밀번호 : 영문 + 숫자 조합으로 6자리 이상 입력합니다.
                <p>비밀번호는 보안을 위하여 의무적으로 2주 간격으로 변경해야 합니다. </p>
        </li>
        <li>
        권한선택 : 권한은 <span style="color:red">일반사용자(메시지 발송)과 통계조회자, 발송승인자(담당상무님)</span>만 선택 가능합니다. 
                <p>개발자 및 승인자 권한은 회원관리승인자가 "사용자관리' 메뉴에서 수정 관리 합니다.</p>
                <p>일반사용자 선택시 발송승인자(해당부서의 담당상무님)을 반드시 입력합니다.</p>
                <p><span style="color:blue">발송승인자 정보가 없는 경우, 메시지 발송을 위한 승인요청이 불가능 합니다.</span> </p>
        </li>
        <li>
        전화번호 : 가급적 사무실 전화번호를 입력합니다.  
        </li>
        <li>
        휴대폰번호 : MIMO사이트의 모든 업무처리시 알림 SMS가 발송되므로, 정확한 본인이 사용하는 번호가 필요합니다.  
        </li>
        <li>
        이메일 : 사내 이메일 주소를 입력합니다.  
        </li>
        <li>
        팝업내용을 정확하게 입력후, 등록버튼을 클릭합니다.<br />
        회원관리 승인자의 승인이 완료 되면 신청하신 ID와 비밀번호로 로그인후, 모든 기능을 이용할 수 있습니다. 
        </li>
        <li>
        회원관리 승인이 완료되면, 신청시 입력한 휴대폰번호로 안내 SMS가 발송됩니다. 
        </li>
        <li>
        로그인 이후, 사용안내는 각 화면의 화면 도움말을 참조하시기 바랍니다.
        </li>
	</ul>
</div>
<hr />

<!-- pBottom -->
<div class="pBottom">
	<a href="javascript:window.open('','_self').close();" class="btn_red"><span>닫 기</span></a>
</div>
</div>