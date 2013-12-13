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
	<div id="location">首頁 &gt; 會員管理中心 &gt; 開發商管理 &gt; <strong>開發商資訊管理</strong></div>
	<!-- //location -->
	
	<h1>開發商資訊管理</h1>
	<h2>註冊資訊</h2>
	<table class="tabletype02">
		<colgroup>
			<col style="width:20%;"/>
			<col style="width:30%;"/>
			<col style="width:20%;"/>
			<col style="width:30%;"/>
		</colgroup>
		<tbody>
			<tr>
				<th>會員等級</th>
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
							<a class="btn_s" href="javascript:ajaxUpdate('grade', 1, '');"><span>變更</span></a>
						</c:otherwise>
					</c:choose>
					<span class="text_r">* 請注意! 依不同會員等級, 會員權限不同!</span>
				</td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${(devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS) and (devMember.mbrcatcd eq CONST.MEM_TYPE_DEV_PAY)}">
						<th rowspan="2">會員類別</th>
						<td rowspan="2" class="text_l"><gc:text code="${devMember.mbrclscd}"/></td>
						<th>付費/免費</th>
						<td class="text_l"><gc:text code="${devMember.mbrcatcd}"/></td>
					<tr>
						<th>公司類別</th>
						<td class="text_l"><gc:text code="${devMember.bizcatcd}"/></td>
					</tr>
					</c:when>
					<c:otherwise>
						<th>會員類別</th>
						<td class="text_l"><gc:text code="${devMember.mbrclscd}"/></td>
						<th>付費/免費</th>
						<td class="text_l"><gc:text code="${devMember.mbrcatcd}"/></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>
				<th>會員註冊日期</th>
				<td class="text_l"><g:html value="${devMember.mbrstartdt}" format="L####-##-##"/></td>
				<th rowspan="2">會員狀態</th>
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
									<a class="btn_s" href="javascript:popup('view')"><span>查看轉換申請書</span></a>
								</c:when>
								<c:when test="${(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_TURN_FINISH) or
														(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_FINISH)}">
									<a class="btn_s" href="javascript:popup('history')"><span>查看轉換記錄</span></a>
								</c:when>
								<c:when test="${(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_TURN_REJECT) or
														(devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT)}">
									<a class="btn_s" href="javascript:popup('reject')"><span>查看拒絕緣由</span></a>
								</c:when>
								<c:when test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_MOTION_REQ}">
									<a class="btn_s" href="javascript:popup('withdraw')"><span>查看註銷緣由</span></a>
								</c:when>
								<c:when test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT}">
									<a class="btn_s" href="javascript:popup('view')"><span>查看銀行資料審核申請書</span></a>
								</c:when>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</tbody>
	</table>
	
	<h2>會員資訊</h2>
	<table class="tabletype02">
		<colgroup>
			<col style="width:20%;"/>
			<col style="width:30%;"/>
			<col style="width:20%;"/>
			<col style="width:30%;"/>
		</colgroup>
		<tbody>
			<tr>
				<th>帳號</th>
				<td class="text_l"><g:html value="${devMember.mbrid}"/></td>
				<c:choose>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_PRIVATE}"><th>身分證字號</th></c:when>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS}"><th>统一編號</th></c:when>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_FOREINGER}"><th>護照號碼</th></c:when>
				</c:choose>
				<td class="text_l"><g:html value="${devMember.psregno}"/></td>
			</tr>
			<tr>
				<c:choose>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS}">
						<th>公司名稱</th>
						<td class="text_l"><g:html value="${devMember.compnm}" /></td>
						<th>管方網站</th>
						<td class="text_l"><g:html value="${devMember.webSiteUrl}"/></td>
					</c:when>
					<c:otherwise>
						<th>姓名</th>
						<td class="text_l"><g:html value="${devMember.mbrnm}" /></td>
						<th>聯絡電話</th>
						<td class="text_l"><g:html value="${devMember.telno}"/></td>
					</c:otherwise>
				</c:choose>
				
			</tr>
			<c:if test="${devMember.mbrclscd != CONST.MEM_CLS_BUSINESS }">
				<tr>
					<th>管方網站</th>
					<td class="text_l"><g:html value="${devMember.webSiteUrl}"/></td>
					<th>行動電話</th>
					<td class="text_l"><g:html value="${devMember.hpno}"/></td>
				</tr>
			</c:if>
			<tr>
				<c:choose>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_FOREINGER}">
						<th>所在國家</th>
						<td class="text_l">
							<c:forEach items="${codeList}" var="item">
								<c:if test="${item.addField1 eq devMember.nanm}"><g:html value="${item.cdNm}"/></c:if>
							</c:forEach>
						</td>
						<th>所在縣市</th>
						<td class="text_l"><g:html value="${devMember.city}"/></td>
					</c:when>
					<c:otherwise>
						<th>電子郵件</th>
						<td class="text_l" >
							<g:html value="${devMember.emailaddr}"/>&nbsp;(<g:html value="${devMember.emailyn eq 'Y' ? '認證完筆' : '未認證' }" />)
						</td>
						<th>所在縣市</th>
						<td class="text_l"><g:html value="${devMember.city}"/></td>
					</c:otherwise>
				</c:choose>
			</tr>
			<c:if test="${devMember.mbrclscd eq CONST.MEM_CLS_FOREINGER}">
				<tr>
					<th>電子郵件</th>
					<td class="text_l" colspan="3">
						<g:html value="${devMember.emailaddr}"/> (<g:html value="${devMember.emailyn eq 'Y' ? '認證完筆' : '未認證' }" />)
					</td>
				</tr>
			</c:if>
			<tr>
				<th>通訊地址</th>
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
	
	<h2>手機資訊</h2>
	<table class="tabletype02">
		<colgroup>
			<col style="width:10%;" />
			<col style="width:45%;" />
			<col style="width:45%;" />
		</colgroup>
		<thead>
			<tr>
				<th>NO</th>
				<th>Wi-Fi MAC 地址</th>
				<th>註冊日期</th>
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
		<h2>連絡人資訊</h2>
		<table class="tabletype02">
			<colgroup>
				<col style="width:20%;"/>
				<col style="width:80%;"/>
			</colgroup>
			<tbody>
				<tr>
					<th>姓名</th>
					<td class="text_l"><g:html value="${devMember.opnm}"/></td>
				</tr>
				<tr>
					<th>電子郵件</th>
					<td class="text_l"><g:html value="${devMember.opemailaddr}"/></td>
				</tr>
				<tr>
					<th>行動電話</th>
					<td class="text_l"><g:html value="${devMember.ophpno}"/></td>
				</tr>
				<tr>
					<th>聯絡電話</th>
					<td class="text_l"><g:html value="${devMember.optelno}"/></td>
				</tr>
			</tbody>
		</table>
	</c:if>
	
	<h2>銀行資訊</h2>
	<table class="tabletype02">
		<colgroup>
			<col style="width:15%;">
			<col style="width:15%;">
			<col style="width:70%;">
		</colgroup>
		<tbody>
			<tr>
				<th colspan="2">帳戶資訊</th>
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
					<th colspan="2">匯款幣別(TWD,USD)</th>
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
				<th rowspan="2">憑證</th>
				<c:choose>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_PRIVATE}"><th>身分證影本 </td></c:when>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_BUSINESS}"><th>公司證明影本 </td></c:when>
					<c:when test="${devMember.mbrclscd eq CONST.MEM_CLS_FOREINGER}"><th>護照證明影本</td></c:when>
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
				<th>存摺影本</td>
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
		<h2>註銷資訊</h2>
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
							<th rowspan="2">註銷申請日期</th>
							<td rowspan="2" class="text_l"><g:html value="${devMember.freedevreqdt}" format="L####-##-##"/></td>
							<th>註銷日期</th>
							<td class="text_l">
								<c:if test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_FINISH}"><g:html value="${devMember.freedevenddt}" format="L####-##-##"/></c:if>
							</td>
						</tr>
						<tr>
							<th>註銷管理者ID</th>
							<td class="text_l">
								<c:if test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_FINISH}"><g:html value="${devMember.regid }" /></c:if>
							</td>
						</tr>
						<tr>
							<th>註銷緣由</th>
							<td class="text_l" colspan="3"><g:html value="${devMember.freedevwithdrawtxt}" /></td>
						</tr>
					</c:when>
					<c:otherwise>
						<tr>
							<th rowspan="2">註銷申請日期</th>
							<td rowspan="2" class="text_l"><g:html value="${devMember.paydevreqdt}" format="L####-##-##"/></td>
							<th>註銷日期</th>
							<td class="text_l">
								<c:if test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_FINISH}"><g:html value="${devMember.paydevenddt}" format="L####-##-##"/></c:if>
							</td>
						</tr>
						<tr>
							<th>註銷管理者ID</th>
							<td class="text_l">
								<c:if test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_FINISH}"><g:html value="${devMember.regid }" /></c:if>
							</td>
						</tr>
						<tr>
							<th>註銷緣由</th>
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
				<a class="btn_s" href="javascript:ajaxUpdate('state', 2, '${CONST.MEM_DEV_STATUS_TURN_FINISH}');"><strong>核准轉換</strong></a>
				<a class="btn_s" href="javascript:ajaxUpdate('reject', '', '${CONST.MEM_DEV_STATUS_TURN_REJECT}');"><strong>拒絕轉換</strong></a>
			</div>
		</c:when>
		<c:when test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_WAIT}">
			<div class="fl mt10">
				<a class="btn_s" href="javascript:ajaxUpdate('state', 3, '${CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_FINISH}');"><strong>核准銀行資料</strong></a>
				<a class="btn_s" href="javascript:ajaxUpdate('reject', '', '${CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT}');"><strong>拒絕銀行資料</strong></a>
			</div>
		</c:when>
		<c:when test="${devMember.devmbrstatcd eq CONST.MEM_DEV_STATUS_LEAVE_MOTION_REQ}">
			<div class="fl mt10">
				<a class="btn_s" href="javascript:ajaxUpdate('withdraw', 4, '${CONST.MEM_DEV_STATUS_LEAVE_FINISH}');"><strong>核准註銷</strong></a>
			</div>
		</c:when>
	</c:choose>
	
	<p class="fr mt10">
		<c:choose>
			<c:when test="${not empty sc }">
				<a class="btn" href="<c:url value="/devMemMgr/findDevMemberMgrList.omp"/>?<g:printBean prefix="sc." value="${sc}" outType="qs"/>"><span>目錄</span></a>
			</c:when>
			<c:otherwise>
				<a class="btn" href="<c:url value="/devMemMgr/findDevMemberMgrList.omp"/>"><span>目錄</span></a>
			</c:otherwise>
		</c:choose>
	</p>

</form>

<iframe src="about;blank" style="display:none" id="file_frame" name="file_frame"></iframe>