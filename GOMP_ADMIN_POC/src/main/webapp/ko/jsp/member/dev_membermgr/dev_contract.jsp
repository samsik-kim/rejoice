<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<script type="text/javascript">

	// State & Grade Change
	function ajaxUpdate(type, msgcd, code){
		var message;
		
		if(type == "grade"){
			$("#type").val(type);
		}else if(type == "state"){
			$("#type").val(type);
			$("#uptdevmbrstatcd").val(code);
		}else if(type == "withdraw"){
			$("#type").val(type);
			$("#uptdevmbrstatcd").val(code);
		}else if(type == "reject"){
			var width = 440;
			var height = 250;
			
			openCenteredWindow("", width, height, "no", "reject");
			
			$("#uptdevmbrstatcd").val(code);
			
			$("#devMember").attr("target", "reject");
			$("#devMember").attr("action", "popTransRejectRegister.omp?" + $("#sc").val());
			$("#devMember").submit();
			
			return;
		}
		
		switch(msgcd){
			case 1 : message = "<gm:string value='jsp.member.dev_membermgr.dev_contract.msg.grade_change'/>"; break;
			case 2 : message = "<gm:string value='jsp.member.dev_membermgr.dev_contract.msg.trans_appr'/>"; break;
			case 3 : message = "<gm:string value='jsp.member.dev_membermgr.dev_contract.msg.calcuinfo_appr'/>"; break;
			case 4 : message = "<gm:string value='jsp.member.dev_membermgr.dev_contract.msg.withdraw_appr'/>"; break;
		}
		
		if(confirm(message)){
			$.ajax({
				type			: 	"POST",
				url			: 	env.contextPath + "/devMemMgr/ajaxStateUpdateExcute.omp",
				data			: 	$("#devMember").serialize(),
				dataType	: 	"json",
				cache		: 	false,
				async 		: 	false,
				success		: 	function(json) {
										if(json.result == "SUCCESS"){
											alert("<gm:string value='jsp.member.dev_membermgr.common.msg.process'/>");
											
											if(type != "grade"){
												$("#devMember").attr("target", "_self");
												$("#devMember").attr("action", "findDevMemberDetail.omp?" + $("#sc").val());
												$("#devMember").submit();
											}
										}
									},
				error			: 	function(){
										alert("<gm:string value='jsp.member.common.msg.error'/>");
										return false;
									}
			});	//end ajax
		}
	}
	
	// Pop-Up Open
	function popup(type){
		var width;
		var height;
		var action;
		var popname;
		
		if(type == "view"){
			popname = type;
			width = 810;
			height = 660;
			action = "popTransApplcationFormView.omp?"  + $("#sc").val();
		}else if(type == "history"){
			popname = type;
			width = 810;
			height = 600;
			action = "popTransHistoryView.omp";
		}else if(type == "reject"){
			popname = type;
			width = 440;
			height = 250;
			action = "popRejectView.omp";
		}else if(type == "withdraw"){
			popname = type;
			width = 440;
			height = 310;
			action = "popWithdrawView.omp?"+ $("#sc").val();
		}
		
		openCenteredWindow("", width, height, "no", popname);
		
		$("#devMember").attr("target", popname);
		$("#devMember").attr("action", action);
		$("#devMember").submit();
	}
	
</script>

<gc:codeList var="codeList" group="US0058"/>

<form name="devMember" id="devMember" method="post">
<input type="hidden" id="id" name="devMember.mbrid" value="${devMember.mbrid}" />
<input type="hidden" id="mbrno" name="devMember.mbrno" value="${devMember.mbrno}" />
<input type="hidden" id="devmbrstatcd" name="devMember.devmbrstatcd" value="${devMember.devmbrstatcd}" />
<input type="hidden" id="mbrcatcd" name="devMember.mbrcatcd" value="${devMember.mbrcatcd}" />
<input type="hidden" id="mbrclscd" name="devMember.mbrclscd" value="${devMember.mbrclscd}" />
<input type="hidden" id="uptdevmbrstatcd" name="devMember.uptdevmbrstatcd" value="" />
<input type="hidden" id="type" name="devMember.type" value="" />
<input type="hidden" id="dochmcd" name="devMember.dochmcd" value="" />
<input type="hidden" id="sc" name="" value="<g:printBean prefix='sc.' value='${sc}' outType='qs'/>" />

	<!-- location -->
	<div id="location">Home &gt; 회원관리 &gt; 개발관리 &gt; <strong>개발자정보관리</strong></div>
	<!-- //location -->
	
	<h1>개발자정보관리</h1>
	<h2>가입정보</h2>
	<table class="tabletype02">
		<colgroup>
			<col style="width:20%;"/>
			<col style="width:30%;"/>
			<col style="width:20%;"/>
			<col style="width:30%;"/>
		</colgroup>
		<tbody>
			<tr>
				<th>회원등급</th>
				<td class="text_l align_td" colspan="3">
					
					<c:choose>
						<c:when test="${(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_MOTION_REQ) 
													or (devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_FINISH)}">
							<select id="mbrgrcd" name="devMember.mbrgrcd" style="width:104px;" disabled>
								<gc:options group="US0050" codeType="full" value="${devMember.mbrgrcd}"/>
							</select>
						</c:when>
						<c:otherwise>
							<select id="mbrgrcd" name="devMember.mbrgrcd" style="width:104px;">
								<gc:options group="US0050" codeType="full" value="${devMember.mbrgrcd}"/>
							</select>
							<a class="btn_s" href="javascript:ajaxUpdate('grade', 1, '');"><span>변경</span></a>
						</c:otherwise>
					</c:choose>
					<span class="text_r">* 회원등급에 따라 권한이 변경되므로 주의해주시기 바랍니다.</span>
				</td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${(devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS) and (devMember.mbrcatcd eq CONST.MEM_TYPE_DEV_PAY)}">
						<th rowspan="2">회원분류</th>
						<td rowspan="2" class="text_l"><gc:text code="${devMember.mbrclscd}"/></td>
						<th>유/무료회원</th>
						<td class="text_l"><gc:text code="${devMember.mbrcatcd}"/></td>
					<tr>
						<th>회사분류</th>
						<td class="text_l"><gc:text code="${devMember.bizcatcd}"/></td>
					</tr>
					</c:when>
					<c:otherwise>
						<th>회원분류</th>
						<td class="text_l"><gc:text code="${devMember.mbrclscd}"/></td>
						<th>유/무료회원</th>
						<td class="text_l"><gc:text code="${devMember.mbrcatcd}"/></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<th>회원가입일</th>
				<td class="text_l"><g:html value="${devMember.mbrstartdt}" format="L####-##-##"/></td>
				<th rowspan="2">회원상태</th>
					<c:choose>
						<c:when test="${(devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS) and (devMember.mbrcatcd eq CONST.MEM_TYPE_DEV_PAY)}">
							<td rowspan="2" colspan="3" class="text_l">
						</c:when>
						<c:otherwise>
							<td rowspan="2" class="text_l">
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_REG_FINISH}">
							<gc:text code="${devMember.devmbrstatcd}"/>
						</c:when>
						<c:otherwise>
							<gc:text code="${devMember.devmbrstatcd}"/>
							<c:if test="${!empty devMember.mbrstatregdt}">(<g:html value="${devMember.mbrstatregdt}" format="L####-##-##"/>)</c:if>
							<c:choose>
								<c:when test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_TURN_MOTION_REQ}">
									<a class="btn_s" href="javascript:popup('view')"><span>전환신청서보기</span></a>
								</c:when>
								<c:when test="${(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_TURN_FINISH) or
														(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_FINISH)}">
									<a class="btn_s" href="javascript:popup('history')"><span>전환이력보기</span></a>
								</c:when>
								<c:when test="${(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_TURN_REJECT) or
														(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT)}">
									<a class="btn_s" href="javascript:popup('reject')"><span>거절사유보기</span></a>
								</c:when>
								<c:when test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_MOTION_REQ}">
									<a class="btn_s" href="javascript:popup('withdraw')"><span>탈퇴사유보기</span></a>
								</c:when>
								<c:when test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT}">
									<a class="btn_s" href="javascript:popup('view')"><span>정산정보신청서보기</span></a>
								</c:when>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
	</table>
	
	<h2>회원정보</h2>
	<table class="tabletype02">
		<colgroup>
			<col style="width:20%;"/>
			<col style="width:30%;"/>
			<col style="width:20%;"/>
			<col style="width:30%;"/>
		</colgroup>
		<tbody>
			<tr>
				<th>ID</th>
				<td class="text_l"><g:html value="${devMember.mbrid}"/></td>
				<c:choose>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_PRIVATE}"><th>타이완ID</th></c:when>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS}"><th>통일번호</th></c:when>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_FOREINGER}"><th>여권번호</th></c:when>
				</c:choose>
				<td class="text_l"><g:html value="${devMember.psregno}"/></td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS}">
						<th>회사명</th>
						<td class="text_l"><g:html value="${devMember.compnm}" /></td>
					</c:when>
					<c:otherwise>
						<th>이름</th>
						<td class="text_l"><g:html value="${devMember.mbrnm}" /></td>
						<th>전화번호</th>
						<td class="text_l"><g:html value="${devMember.telno}"/></td>
					</c:otherwise>
				</c:choose>
				
			</tr>
			<c:if test="${devMember.mbrclscd != CONST.MEM_CLS_BUSINESS }">
				<tr>
					<th>웹사이트</th>
					<td class="text_l"><g:html value="${devMember.webSiteUrl}"/></td>
					<th>휴대폰번호</th>
					<td class="text_l"><g:html value="${devMember.hpno}"/></td>
				</tr>
			</c:if>
			<tr>
				<c:choose>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_FOREINGER}">
						<th>국가</th>
						<td class="text_l">
							<c:forEach items="${codeList}" var="item">
								<c:if test="${item.addField1 eq devMember.nanm}"><g:html value="${item.cdNm}"/></c:if>
							</c:forEach>
						</td>
						<th>도시</th>
						<td class="text_l"><g:html value="${devMember.city}"/></td>
					</c:when>
					<c:otherwise>
						<th>이메일주소</th>
						<td class="text_l" >
							<g:html value="${devMember.emailaddr}"/>&nbsp;(<g:html value="${devMember.emailyn eq 'Y' ? '認證完筆' : '未認證' }" />)
						</td>
						<th>도시</th>
						<td class="text_l"><g:html value="${devMember.city}"/></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<c:if test="${devMember.mbrclscd eq CONST.MEM_CLS_FOREINGER}">
				<tr>
					<th>이메일주소</th>
					<td class="text_l" colspan="3">
						<g:html value="${devMember.emailaddr}"/> (<g:html value="${devMember.emailyn eq 'Y' ? '認證完筆' : '未認證' }" />)
					</td>
				</tr>
			</c:if>
			<tr>
				<th>주소</th>
				<td class="text_l" colspan="3">
					<c:choose>
						<c:when test="${!empty devMember.zipcd}">
							[<g:html value="${devMember.zipcd}"/>] <g:html value="${devMember.homeaddrdtl}"/>
						</c:when>
						<c:otherwise>
							<g:html value="${devMember.homeaddrdtl}"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
	</table>
	
	<h2>단말정보</h2>
	<table class="tabletype02">
		<colgroup>
			<col style="width:10%;" />
			<col style="width:45%;" />
			<col style="width:45%;" />
		</colgroup>
		<thead>
			<tr>
				<th>NO</th>
				<th>Wi-Fi MAC 주소</th>
				<th>등록일시</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${phoneList}" var="item" varStatus="status">
				<tr>
					<td><g:html value="${status.count}"/></td>
					<td><g:html value="${item.MACADDR}"/></td>
					<td><g:html value="${item.REGDT}" format="L####-##-## ##:##"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<c:if test="${devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS}">
		<h2>담당자정보</h2>
		<table class="tabletype02">
			<colgroup>
				<col style="width:20%;"/>
				<col style="width:80%;"/>
			</colgroup>
			<tbody>
				<tr>
					<th>이름</th>
					<td class="text_l"><g:html value="${devMember.opnm}"/></td>
				</tr>
				<tr>
					<th>이메일</th>
					<td class="text_l"><g:html value="${devMember.opemailaddr}"/></td>
				</tr>
				<tr>
					<th>휴대폰번호</th>
					<td class="text_l"><g:html value="${devMember.ophpno}"/></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td class="text_l"><g:html value="${devMember.optelno}"/></td>
				</tr>
			</tbody>
		</table>
	</c:if>
	
	<h2>정산정보</h2>
	<table class="tabletype02">
		<colgroup>
			<col style="width:15%;">
			<col style="width:15%;">
			<col style="width:70%;">
		</colgroup>
		<tbody>
			<tr>
				<th colspan="2">계좌정보</th>
				<td class="text_l">
					<c:if test="${!empty devMember.acctno}">
						<c:choose>
							<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_FOREINGER}">
								<g:html value="${devMember.backnm}"/> | <g:html value="${devMember.acctno}"/> | <g:html value="${devMember.acctnm}"/>
							</c:when>
							<c:otherwise>
								<g:html value="${devMember.backnm}"/>(<g:html value="${devMember.backcd}"/>) | <g:html value="${devMember.acctno}"/> | <g:html value="${devMember.acctnm}"/>
							</c:otherwise>
						</c:choose>
					</c:if>
				</td>
			</tr>
			<!-- Foreigner Member -->
			<c:if test="${devMember.mbrclscd eq CONST.MEM_CLS_FOREINGER}">
				<tr>
					<th colspan="2">화폐선택(TWD,USD)</th>
					<td class="text_l"><gc:text code="${devMember.currencyunit}"/></td>
				</tr>
				<tr>
					<c:choose>
						<c:when test="${not empty devMember.backgltype}">
							<th colspan="2"><gc:text group="US0057" codeType="full" code="${devMember.backgltype}"/></th>
							<td class="text_l"><g:html value="${devMember.backglcd}"/></td>
						</c:when>
						<c:otherwise>
							<th colspan="2">Swift Code / ABA No</th>
							<td class="text_l"></td>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:if>
			<!-- End Foreigner Member -->
			<tr>
				<th rowspan="2">등록서류</th>
				<c:choose>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_PRIVATE}"><th>신분증사분</td></c:when>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS}"><th>회사증명사본</td></c:when>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_FOREINGER}"><th>외국인증명사본</td></c:when>
				</c:choose>
				<td class="text_l">
					<a target="file_frame" href="<c:url value="/fileSupport/fileDown.omp">
									<c:param name="bnsType" value="common.path.share.member"/>
									<c:param name="filePath" ><g:html value="${devMember.idfilepath}" /></c:param>
									<c:param name="fileName" ><g:html value="${devMember.idfilenm}"/></c:param>
									</c:url>"><g:html value="${devMember.idfilenm}"/></a>
				</td>
			</tr>
			<tr>
				<th>통장사본</td>
				<td class="text_l">
					<a target="file_frame" href="<c:url value="/fileSupport/fileDown.omp">
									<c:param name="bnsType" value="common.path.share.member"/>
									<c:param name="filePath" ><g:html value="${devMember.acctfilepath}" /></c:param>
									<c:param name="fileName" ><g:html value="${devMember.acctfilenm}"/></c:param>
									</c:url>"><g:html value="${devMember.acctfilenm}"/></a>
				</td>
			</tr>
		</tbody>
	</table>
	
	<c:if test="${(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_MOTION_REQ) or (devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_FINISH)}">
		<h2>탈퇴정보</h2>
		<table class="tabletype02">
			<colgroup>
				<col style="width:20%;"/>
				<col style="width:30%;"/>
				<col style="width:20%;"/>
				<col style="width:30%;"/>
			</colgroup>
			<tbody>
				<c:choose>
					<c:when test="${devMember.mbrcatcd eq CONST.MEM_TYPE_DEV_NOPAY}">
						<tr>
							<th rowspan="2">탈퇴신청일</th>
							<td rowspan="2" class="text_l"><g:html value="${devMember.freedevreqdt}" format="L####-##-##"/></td>
							<th>탈퇴처리일</th>
							<td class="text_l">
								<c:if test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_FINISH}"><g:html value="${devMember.freedevenddt}" format="L####-##-##"/></c:if>
							</td>
						</tr>
						<tr>
							<th>탈퇴처리관리자ID</th>
							<td class="text_l">
								<c:if test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_FINISH}"><g:html value="${devMember.regid }" /></c:if>
							</td>
						</tr>
						<tr>
							<th>탈퇴사유</th>
							<td class="text_l" colspan="3"><g:html value="${devMember.freedevwithdrawtxt}" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th rowspan="2">탈퇴신청일</th>
							<td rowspan="2" class="text_l"><g:html value="${devMember.paydevreqdt}" format="L####-##-##"/></td>
							<th>탈퇴처리일</th>
							<td class="text_l">
								<c:if test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_FINISH}"><g:html value="${devMember.paydevenddt}" format="L####-##-##"/></c:if>
							</td>
						</tr>
						<tr>
							<th>탈퇴처리관리자ID</th>
							<td class="text_l">
								<c:if test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_FINISH}"><g:html value="${devMember.regid }" /></c:if>
							</td>
						</tr>
						<tr>
							<th>탈퇴사유</th>
							<td class="text_l" colspan="3"><g:html value="${devMember.paydevwithdrawtxt}" /></td>
						</tr>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</c:if>
	
	<c:choose>
		<c:when test="${(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_TURN_MOTION_REQ) and (devMember.mbrcatcd eq CONST.MEM_TYPE_DEV_PAY)}">
			<div class="fl mt10">
				<a class="btn_s" href="javascript:ajaxUpdate('state', 2, '${CONST.MEM_DEV_STATUS_TURN_FINISH}');"><strong>전환승인</strong></a>
				<a class="btn_s" href="javascript:ajaxUpdate('reject', '', '${CONST.MEM_DEV_STATUS_TURN_REJECT}');"><strong>전환거절</strong></a>
			</div>
		</c:when>
		<c:when test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT}">
			<div class="fl mt10">
				<a class="btn_s" href="javascript:ajaxUpdate('state', 3, '${CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_FINISH}');"><strong>정산정보승인</strong></a>
				<a class="btn_s" href="javascript:ajaxUpdate('reject', '', '${CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT}');"><strong>정산정보거절</strong></a>
			</div>
		</c:when>
		<c:when test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_MOTION_REQ}">
			<div class="fl mt10">
				<a class="btn_s" href="javascript:ajaxUpdate('withdraw', 4, '${CONST.MEM_DEV_STATUS_LEAVE_FINISH}');"><strong>탈퇴승인</strong></a>
			</div>
		</c:when>
	</c:choose>
	
	<p class="fr mt10">
		<c:choose>
			<c:when test="${not empty sc }">
				<a class="btn" href="<c:url value="/devMemMgr/findDevMemberMgrList.omp"/>?<g:printBean prefix="sc." value="${sc}" outType="qs"/>"><span>목록</span></a>
			</c:when>
			<c:otherwise>
				<a class="btn" href="<c:url value="/devMemMgr/findDevMemberMgrList.omp"/>"><span>목록</span></a>
			</c:otherwise>
		</c:choose>
	</p>

</form>

<iframe src="about;blank" style="display:none" id="file_frame" name="file_frame"></iframe>