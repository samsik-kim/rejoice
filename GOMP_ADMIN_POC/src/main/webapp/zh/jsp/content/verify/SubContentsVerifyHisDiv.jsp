<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<head>
<script type="text/javascript">
function reasonView(cid, verifyReqVer){ 
	width = 540;
	height = 510;
	action = env.contextPath + "/content/popupSubContentsVerifyDetailView.omp?ctVerify.cid="+cid+"&ctVerify.verifyReqVer="+verifyReqVer;

    x = (screen.width) ? (screen.width-width)/2 : 0;
    y = (screen.height) ? (screen.height-height)/2 : 0;
	  
	window.open(action,"popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",resizable=no, ScrollBars=no, status=yes, menubar=no");
}
</script>
</head>

		<p class="pbult09">共有 <span class="txtcolor02">${ctVerify.page.totalCount }</span>&nbsp;項審核紀錄</p>
		<div class="btstyleA">
			<table summary="審核紀錄">
				<caption>審核紀錄</caption>

				<colgroup>
					<col width="7%" />
					<col width="12%" />
					<col width="12%" />
					<col />
					<col width="12%" />
					<col width="10%" />
					<col width="8%" />
					<col width="11%" />

				</colgroup>
				<thead>
					<tr>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit25.gif' />" alt="序號" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit35.gif' />" alt="版本號碼" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit36.gif' />" alt="版本名稱" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit37.gif' />" alt="Binary 檔" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit27.gif' />" alt="申請日期" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit28.gif' />" alt="審核狀態" /></th>

						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit29.gif' />" alt="審核結果" /></th>
						<th scope="col" class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit30.gif' />" alt="審核完畢(預期)日期" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${resultList }" var="subContents" varStatus="status">
						<tr>
							<td> ${ctVerify.page.totalCount - subContents.rnum + 1 } </td>
							<td>
								<c:forEach items="${subContents.list }" var="versionCodeList"> ${versionCodeList.versionCode } <br/> </c:forEach>
							</td>
							<td>
								<c:forEach items="${subContents.list }" var="versionNameList"> ${versionNameList.versionName } <br/> </c:forEach>
							</td>
							<td>
								<c:forEach items="${subContents.list }" var="runFileNmList"> ${runFileNmList.runFileNm } <br/> </c:forEach>
							</td>
							<td> <g:text value="${subContents.insDttm }" format="L####-##-##" /> </td>
							<td> 
								<c:choose>
									<c:when test="${CONST.CODE_VERIFY_TEST_REJECT eq  subContents.verifyPrgrYn || CONST.CODE_VERIFY_TEST_END eq subContents.verifyPrgrYn}">
										<gc:text code="${CONST.CODE_VERIFY_ING }" />
									</c:when>
									<c:otherwise>
										<gc:text code="${subContents.verifyPrgrYn }" />											
									</c:otherwise> 
								</c:choose>
							</td>
							<td>
								<c:choose>
								<c:when test="${subContents.agrmntStat eq CONST.AGREEMENT_STATUS_AGREE || subContents.agrmntStat eq CONST.AGREEMENT_STATUS_REJECT}">
									<a id="popOpenBtn" href="javascript:reasonView('${subContents.cid }', '${subContents.verifyReqVer }');">
										<c:if test="${CONST.AGREEMENT_STATUS_AGREE == subContents.agrmntStat}">
											<gc:text code="${subContents.agrmntStat }" />
										</c:if>
										<c:if test="${CONST.AGREEMENT_STATUS_REJECT == subContents.agrmntStat }">
											<span class="txtcolor03 link01"> <gc:text code="${subContents.agrmntStat }" /> </span>
										</c:if>
									</a>
								</c:when>
								<c:otherwise>-</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:if test="${subContents.verifyPrgrYn eq CONST.CODE_VERIFY_ING || subContents.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_END || subContents.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_REJECT}">
		                        	<c:if test="${0 > subContents.ctEndExDt}"><div align="center"> D${subContents.ctEndExDt } 日<div></c:if>
									<c:if test="${0 < subContents.ctEndExDt}"><div align="center"> D+${subContents.ctEndExDt } 日<div></c:if>
									<c:if test="${0 == subContents.ctEndExDt}"><div align="center"> D-${subContents.ctEndExDt } 日<div></c:if>
		                        	(<g:text value="${subContents.ctEndDt }" format="L####-##-##" />)
		                        </c:if>
		                        <c:if test="${subContents.verifyPrgrYn eq CONST.CODE_VERIFY_END}">
		                        	<g:text value="${subContents.ctEndDt }" format="L####-##-##" />
		                        </c:if>
		                        <c:if test="${!subContents.verifyPrgrYn eq CONST.CODE_VERIFY_END || !subContents.verifyPrgrYn eq CONST.CODE_VERIFY_ING || !subContents.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_END || !subContents.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_REJECT}"> - </c:if>
							</td>

						</tr>
					</c:forEach>							
				</tbody>
			</table>
			
			<!-- paging -->
			<form id="frm" name="frm" action="<c:url value='/content/getSubContentsVerifyHisList.omp' />" method="get">
				<input type="hidden" name="ctVerify.cid" value="${contentVerifyDetail.cid }"/>
				<input type="hidden" name="ctVerify.verifyReqVer" value="${contentVerifyDetail.verifyReqVer }"/>
				<input type="hidden" name="ctVerify.page.no" value="1"/>
				<div align="center">
					<g:pageIndex item="${resultList}" />
				</div>
				<script type="text/javascript">
					function goPage(no) {
					    $("form[name=frm] input[name=ctVerify\\.page\\.no]").val(no);
					    frmSubmit();
					}
					
					function frmSubmit() {
					    //if (showValidate("frm")) {
					        document.getElementsByName("frm").item(0).submit();
					    //}
					}	
				</script>
			</form>
			<!-- //paging -->
			
		</div>