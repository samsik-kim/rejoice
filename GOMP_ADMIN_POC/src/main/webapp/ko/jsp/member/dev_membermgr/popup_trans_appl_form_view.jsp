<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<title>전환신청서</title>
<script type="text/javascript">

	// Appr & Reject
	function ajaxUpdate(type, code){
		var msg = "";
		
		$("#dochmcd").val("");
		
		$("#uptdevmbrstatcd").val(code);

		if(type == "reject"){
			var width = 480;
			var height = 250;
			
			openCenteredWindow("", width, height, "no", "rejectreg");
			
			$("#devMember").attr("target", "rejectreg");
			$("#devMember").attr("action", "popTransRejectRegister.omp?" + $("#sc").val());
			$("#devMember").submit();
			
			return;
		}else if(type == "trans"){
			msg ="<gm:string value='jsp.member.dev_membermgr.dev_contract.msg.trans_appr'/>";
		}else if(type == "info"){
			msg = "<gm:string value='jsp.member.dev_membermgr.dev_contract.msg.calcuinfo_appr'/>";
		}
			
		if(confirm(msg)){
			$.ajax({
				type			: 	"POST",
				url			: 	"${pageContext.request.contextPath}/devMemMgr/ajaxStateUpdateExcute.omp",
				data			: 	$("#devMember").serialize(),
				dataType	: 	"json",
				cache		: 	false,
				async 		: 	false,
				success		: 	function(json) {
										if(json.result == "SUCCESS"){
											alert("<gm:string value='jsp.member.dev_membermgr.common.msg.process'/>");
											
											opener.location.href = env.contextPath + "/devMemMgr/findDevMemberDetail.omp?devMember.mbrno=" + $("#mbrno").val() + "&" + $("#sc").val();
											
											self.close();
										}
									},
				error			: 	function(){
										alert("<gm:string value='jsp.member.common.msg.error'/>");
										return false;
									}
			});	//end ajax
		}
	}
	
</script>
<gc:codeList var="codeList" group="US0058"/>

<form name="devMember" id="devMember" method="post">
<input type="hidden" id="id" name="devMember.mbrid" value="<g:tagAttb value='${devMember.mbrid}'/>" />
<input type="hidden" id="mbrno" name="devMember.mbrno" value="<g:tagAttb value='${devMember.mbrno}'/>" />
<input type="hidden" id="devmbrstatcd" name="devMember.devmbrstatcd" value="<g:tagAttb value='${devMember.devmbrstatcd}'/>" />
<input type="hidden" id="mbrcatcd" name="devMember.mbrcatcd" value="<g:tagAttb value='${devMember.mbrcatcd}'/>" />
<input type="hidden" id="mbrclscd" name="devMember.mbrclscd" value="<g:tagAttb value='${devMember.mbrclscd}'/>" />
<input type="hidden" id="uptdevmbrstatcd" name="devMember.uptdevmbrstatcd" value="" />
<input type="hidden" id="dochmcd" name="devMember.dochmcd" value="" />
<input type="hidden" id="type" name="devMember.type" value="state" />
<input type="hidden" id="sc" value="<g:printBean prefix='sc.' value='${sc}' outType='qs'/>" />
	<div id="popup_area_810">
		<h1>전환신청서</h1>
		<div class="scroll" style="width:770px;height:500px;">
			<p class="mb05">
				<strong class="c_06f"><g:html value="${devMember.mbrid}"/></strong> 님의  사업자 전환 신청서입니다.
			</p>
			<table class="pop_tabletype01" style="border:1px solid #ddd;">
				<colgroup>
					<col style="width:15%;"/>
					<col style="width:45%"/>
					<col style="width:15%;"/>
					<col style="width:25%"/>
				</colgroup>
				<tbody>
				<tr>
					<th>전환신청내용</th>
					<td>
						<c:forTokens items="${devMember.transinfo}" delims=":" var="code" varStatus="status">
							<c:if test="${(status.count eq 1) or (status.count eq 3)}">
								<gc:html group="US0001" code="${code}"/>
							</c:if>
							<c:if test="${(status.count eq 2) or (status.count eq 4)}">
								(<g:html format="L##"><gc:html group="US0002"  code="${code}"/></g:html>)
							</c:if>
							<c:if test="${status.count eq 2}">
								&nbsp;<g:html value="→"/>&nbsp;
							</c:if>
						</c:forTokens>
					</td>
					<th>신청서작성일</th>
					<td><g:html value="${devMember.regdts}" format="L####-##-##"/></td>
				</tr>
				</tbody>
			</table>
			
			<p class="mb05 mt25"><strong>회원정보</strong></p>
			<table class="pop_tabletype01" style="border:1px solid #ddd;">
				<colgroup>
					<col style="width:20%;"/>
					<col style="width:30%;"/>
					<col style="width:20%;"/>
					<col style="width:30%;"/>
				</colgroup>
				<tbody>
					<tr>
						<th>ID</th>
						<td><g:html value="${devMember.mbrid}"/></td>
						<c:choose>
							<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_PRIVATE
													and devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT}"><th class="c_06f">타이완ID</th></c:when>
							<c:when test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_TURN_MOTION_REQ}"><th class="c_06f">통일번호</th></c:when>
							<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS
													and devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT}"><th class="c_06f">통일번호</th></c:when>
							<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_FOREINGER
													and devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT}"><th class="c_06f">여권번호</th></c:when>
						</c:choose>
						<td><g:html value="${devMember.psregno}"/></td>
					</tr>
					<tr>
						<c:choose>
							<c:when test="${(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_TURN_MOTION_REQ)
											or (devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS and devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT)}">
								<th>회사명</th>
								<td><g:html value="${devMember.compnm}"/></td>
								<th>웹사이트</th>
								<td><g:html value="${devMember.webSiteUrl}"/></td>
							</c:when>
							<c:when test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT}">
								<th>이름</th>
								<td><g:html value="${devMember.mbrnm}"/></td>
							</c:when>
						</c:choose>
					</tr>
					<c:if test="${devMember.mbrclscd != CONST.MEM_CLS_BUSINESS and devMember.devmbrstatcd != CONST.MEM_DEV_STATUS_TURN_MOTION_REQ}">
						<tr>
							<th>웹사이트</th>
							<td><g:html value="${devMember.webSiteUrl}"/></td>
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
									<g:html value="${devMember.emailaddr}"/>&nbsp;(<g:html value="${devMember.emailyn eq 'Y' ? '인증완료' : '가가입' }" />)
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
						<td colspan="3">
							<c:choose>
								<c:when test="${!empty devMember.zipcd}">
									[<g:html value="${devMember.zipcd}" format="L###-###"/>] <g:html value="${devMember.homeaddrdtl}"/>
								</c:when>
								<c:otherwise>
									<g:html value="${devMember.homeaddrdtl}"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
				</tbody>
			</table>
			
			<c:if test="${!empty devMember.opnm}">
				<p class="mb05 mt25">
					<strong>담당자정보</strong>
				</p>
				<table class="pop_tabletype01" style="border:1px solid #ddd;">
					<colgroup>
						<col style="width:20%;"/>
						<col style="width:80%;"/>
					</colgroup>
					<tbody>
						<tr>
							<th>이름</th>
							<td><g:html value="${devMember.opnm}"/></td>
						</tr>
						<tr>
							<th>이메일</th>
							<td><g:html value="${devMember.opemailaddr}"/></td>
						</tr>
						<tr>
							<th>휴대폰번호</th>
							<td><g:html value="${devMember.ophpno}"/></td>
						</tr>
						<tr>
							<th>전화번호</th>
							<td><g:html value="${devMember.optelno}"/></td>
						</tr>
					</tbody>
				</table>
			</c:if>
			
			<c:if test="${((devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT) and (devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS))
								or ((devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT) and (devMember.mbrcatcd eq CONST.MEM_TYPE_DEV_NOPAY))
								or ((devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT) and (devMember.mbrcatcd eq CONST.MEM_TYPE_DEV_PAY))
								or ((devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_TURN_MOTION_REQ) and (devMember.mbrcatcd eq CONST.MEM_TYPE_DEV_PAY))
								or ((devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT) and (devMember.mbrclscd eq CONST.MEM_CLS_FOREINGER))}">
				<p class="mb05 mt25"><strong>정산정보</strong></p>
				<table class="pop_tabletype01" style="border:1px solid #ddd;">
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
								<th colspan="2"><gc:text group="US0057" codeType="full" code="${devMember.backgltype}"/></th>
								<td class="text_l"><g:html value="${devMember.backglcd}"/></td>
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
			</c:if>
		</div><!-- scroll -->
			
		<div class="pop_btn" >
			<c:if test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT}">
				<a class="btn_s" href="javascript:ajaxUpdate('info', '${CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_FINISH}');"><span>정산정보승인</strong></a>
				<a class="btn_s" href="javascript:ajaxUpdate('reject', '${CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT}');"><span>정산정보거절</strong></a>
			</c:if>
			<c:if test="${(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_TURN_MOTION_REQ) and (devMember.mbrcatcd eq CONST.MEM_TYPE_DEV_PAY)}">
				<a class="btn_s" href="javascript:ajaxUpdate('trans', '${CONST.MEM_DEV_STATUS_TURN_FINISH}');"><strong>전환승인</strong></a>
				<a class="btn_s" href="javascript:ajaxUpdate('reject', '${CONST.MEM_DEV_STATUS_TURN_REJECT}');"><strong>전환거절</strong></a>
			</c:if>
		</div>
	</div><!-- //contents -->
</form>  

<iframe src="about;blank" style="display:none" id="file_frame" name="file_frame"></iframe>
