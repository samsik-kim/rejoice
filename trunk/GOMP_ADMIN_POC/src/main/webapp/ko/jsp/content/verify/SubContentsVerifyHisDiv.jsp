<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

		<p class="pbult09">총 <span class="txtcolor02">${ctVerify.page.totalCount }</span>개의 검증 히스토리가 존재합니다.</p>
		<div class="btstyleA">
			<table summary="검증히스토리 상품목록">
				<caption>검증히스토리 상품목록</caption>

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
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit25.gif" alt="번호' />" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit35.gif' />" alt="Version code" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit36.gif' />" alt="Version name" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit37.gif' />" alt="바이너리 파일" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit27.gif' />" alt="검증요청일" /></th>
						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit28.gif' />" alt="검증상태" /></th>

						<th scope="col"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit29.gif' />" alt="검증결과" /></th>
						<th scope="col" class="none_bg"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit30.gif' />" alt="검증완료(예정)일" /></th>
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
									<c:if test="${CONST.AGREEMENT_STATUS_AGREE == subContents.agrmntStat}">
										<span class="txtcolor03">
											<span class="txtcolor03 link01"> <gc:text code="${subContents.agrmntStat }" /> </span>
										</span>
									</c:if>
									<c:if test="${CONST.AGREEMENT_STATUS_REJECT == subContents.agrmntStat }">
										<gc:text code="${subContents.agrmntStat }" />
									</c:if>
								</c:when>
								<c:otherwise>-</c:otherwise>
								</c:choose>
							</td>
							<td>
								<c:if test="${subContents.verifyPrgrYn eq CONST.CODE_VERIFY_ING || subContents.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_END || subContents.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_REJECT}">
		                        	<c:if test="${0 > subContents.ctEndExDt}"><div align="center"> D${subContents.ctEndExDt } 일<div></c:if>
									<c:if test="${0 < subContents.ctEndExDt}"><div align="center"> D+${subContents.ctEndExDt } 일<div></c:if>
									<c:if test="${0 == subContents.ctEndExDt}"><div align="center"> D-${subContents.ctEndExDt } 일<div></c:if>
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