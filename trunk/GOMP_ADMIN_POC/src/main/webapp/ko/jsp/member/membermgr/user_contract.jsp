<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<script type="text/javascript">
	
	// PHONE DELETE
	function phone_delete(gdid){
		
		$("#gdid").val(gdid);
		
		if(confirm("<gm:string value='jsp.member.membermgr.user_contract.msg.del_confirm'/>")){
			$.ajax({
				type			: 	"POST",
				url			: 	env.contextPath + "/userMemMgr/ajaxPhoneDeleteExcute.omp",
				data			: 	$("#userMember").serialize(),
				dataType	: 	"json",
				cache		: 	false,
				async 		: 	false,
				success		: 	function(json) {
										if(json.result == "SUCCESS"){
											alert("<gm:string value='jsp.member.membermgr.user_contract.msg.del_success'/>");
											
											$("#userMember").attr("target", "_self");
											$("#userMember").attr("action", "findUserMemberDetail.omp");
											$("#userMember").submit();
					   					}
									},
				error			: 	function(){
										alert("<gm:string value='jsp.member.common.msg.error'/>");
										return false;
									}
			});
		}
	}
	
	// TEMP PASSWORD EMAIL SEND
	function pop_email(){
		var width = 440;
		var height = 200;
		
		openCenteredWindow("", width, height, "no", "pwpop");
		
		$("#userMember").attr("target", "pwpop");
		$("#userMember").attr("action", "popTempEmailSend.omp?" + $("#sc").val());
		$("#userMember").submit();
	}
	
	// LOGIN INFO HISTORY
	function pop_login(){
		var width = 810;
		var height = 660;
		var url = env.contextPath +"/userMemMgr/popLoginInfoHistory.omp?userMember.mbrno=" + $("#mbrno").val();
		
		openCenteredWindow(url, width, height, "no", "login");
	}
	
	// PURCHASE HISTORY
	function pop_purchase(mbrNo){
		var width = 810;
		var height = 660;
		var url = env.contextPath +"/membermgr/popPurchaseList.omp?purchase.mbrNo=" + mbrNo;
		
		openCenteredWindow(url, width, height, "no", "pop_purchase");
	}
	
	//MEMBER CANCEL
	function punish_cancel(mbrNo, mbrId){
		var width = 440;
		var height = 250;
		var url = env.contextPath + "/member/popPunishMemberClose.omp?mbrNo=" + mbrNo + "&mbrId=" + mbrId + "&userMemYn=Y";

		openCenteredWindow(url, width, height, "no", "punish_cancel");
	}
	
</script>

<gc:codeList var="codeList" group="US0001" excludeFilter="dev" />
<form name="userMember" id="userMember" method="post">
<input type="hidden" id="mbrno" name="userMember.mbrno" value="<g:tagAttb value='${userMember.mbrno}'/>" />
<input type="hidden" id="gdid" name="userMember.gdid" value="" />
<input type="hidden" id="sc" name="" value="<g:printBean prefix='sc.' value='${sc}' outType='qs'/>" />

	<!-- location -->
	<div id="location">Home &gt; 회원관리 &gt; 사용자관리 &gt; <strong>회원정보관리</strong></div>
	<!-- //location -->
	
	<h1>회원정보관리</h1>
	<h2 class="fl pr10">일반정보</h2>
	<div class="fl mt15 mb10">
		<a class="btn_s" href="javascript:pop_login();"><strong>로그인정보</strong></a>
		<a class="btn_s" href="javascript:pop_purchase('${userMember.mbrno}');"><strong>구매내역</strong></a>
	</div>
	
	<table class="tabletype02">
		<colgroup>
			<col style="width:20%;">
			<col style="width:30%;">
			<col style="width:20%;">
			<col style="width:30%;">
		</colgroup>
		<tbody>
			<tr>
				<th>회원분류</th>
				<td class="text_l">
					<c:forEach items="${codeList}" var="code">
						<c:if test="${code.dtlFullCd eq userMember.mbrclscd}"><g:html value="${code.addField1}"/></c:if>
					</c:forEach>
				</td>
				<th>회원상태</th>
				<td class="text_l"><gc:text code="${userMember.mbrstatcd}"/>
					<c:if test="${userMember.mbrstatcd eq CONST.MEM_STATUS_STOP}">
						<a class="btn_s" href="javascript:punish_cancel('${userMember.mbrno}', '${userMember.mbrid}');"><span>해지하기</span></a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>아이디</th>
				<td class="text_l"><g:html value="${userMember.mbrid}"/></td>
				<th>비밀번호</th>
				<td class="text_l">
					<c:if test="${(userMember.mbrstatcd eq CONST.MEM_STATUS_NORMAL) or (userMember.mbrstatcd eq CONST.MEM_STATUS_STOP)}">
						<a class="btn_s" href="javascript:pop_email();"><span>임시비밀번호발송</span></a>
					</c:if>
				</td>
			</tr>
			<tr>
				<th>타이완아이디(여권번호)</th>
				<td class="text_l"><g:html value="${userMember.psregno}"/></td>
				<th>이름</th>
				<td class="text_l"><g:html value="${userMember.mbrnm}"/></td>
			</tr>
			<tr>
				<th>성별</th>
				<td class="text_l">
						<g:html value="${userMember.sex eq '1' ? '남' : '여'}"/>
				</td>
				<th>생년월일</th>
				<td class="text_l"><g:html value="${userMember.birthdt}" format="L####년 ##월 ##일"/></td>
			</tr>
			<tr>
				<th>이메일주소</th>
				<td class="text_l">
					<c:if test="${not empty userMember.emailaddr}">
						<g:html value="${userMember.emailaddr}"/> (<g:html value="${userMember.newsyn eq 'Y' ? '수신' : '미수신'}"/>)
					</c:if>
				</td>
				<th>휴대폰번호</th>
				<td class="text_l"><g:html value="${userMember.hpno}"/></td>
			</tr>
		</tbody>
	</table>
	
	<h2 class="fl">단말정보</h2>
	<p class="fr mt20">등록단말수 : <g:html value="${searchCnt}"/> 대</p>
	<table class="tabletype02">
		<colgroup>
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
		</colgroup>
		<thead>
			<tr>
				<th>모델명</th>
				<th>등록일시</th>
				<th>MDN</th>
				<th>Wi-Fi MAC 주소</th>
				<th>GDID</th>
				<th>유효성</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!empty phoneList}">
				<c:forEach items="${phoneList}" var="item">
					<tr>
						<td><g:html value="${item.HPMODELNM}"/></td>
						<td><g:html value="${item.REGDTS}" format="L####-##-## ##:##"/></td>
						<td><g:html value="${item.HPNO}"/></td>
						<td><g:html value="${item.MACADDR}" format="L##:##:##:##:##:##"/></td>
						<td><g:html value="${item.GDID}"/></td>
						<td><g:html value="${item.GDIDYN eq 'Y' ? 'Valid' : 'Invalid'}"/></td>
						<td>
							<c:if test="${(userMember.mbrstatcd eq CONST.MEM_STATUS_NORMAL) or (userMember.mbrstatcd eq CONST.MEM_STATUS_STOP)}">
								<a class="btn_s" href="javascript:phone_delete('<g:html value="${item.GDID}"/>')"><span>삭제</span></a></td>
							</c:if>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
	
	<h2>가입탈퇴정보</h2>
	<table class="tabletype02">
		<colgroup>
			<col style="width:150px;" />
			<col style="width:200px;" />
			<col style="width:150px;" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th>가입일</th>
				<td><g:html value="${userMember.mbrstartdt}" format="L####-##-##"/></td>
				<th>탈퇴일</th>
				<td>
					<c:if test="${userMember.mbrstatcd eq CONST.MEM_STATUS_LEAVE}">
						<g:html value="${userMember.mbrenddt}" format="L####-##-##"/>
					</c:if>
				</td>
			</tr>
		</tbody>
	</table>
	
	<p class="btn_wrap text_r mt25">
		<c:choose>
			<c:when test="${not empty sc}">
				<a class="btn" href="<c:url value="/userMemMgr/findUserMemberMgrList.omp"/>?<g:printBean prefix="sc." value="${sc}" outType="qs"/>"><span>목록</span></a>
			</c:when>
			<c:otherwise>
				<a class="btn" href="<c:url value="/userMemMgr/findUserMemberMgrList.omp"/>"><span>목록</span></a>
			</c:otherwise>
		</c:choose>
	</p>

</form>