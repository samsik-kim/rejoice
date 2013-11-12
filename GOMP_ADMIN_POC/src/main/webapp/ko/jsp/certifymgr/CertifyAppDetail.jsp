<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Application 조회</title>

<script type="text/javascript">
	//<![CDATA[
           
	$(function() {
		
		jQuery.fn.selfPage = {
				
			    /**
			     * Save Test Ing
			     *
			     * @param cid
			     * @param verifyReqVer
			     * @param status
			     */  
			    searchTestEndCheck : function (cid, verifyReqVer, status){
			        
			        var param = "saveTestStatus.cid=" + cid + "&saveTestStatus.verifyReqVer=" + verifyReqVer + "&saveTestStatus.status=" + status;
			        
			        $.ajax({
			            type: "POST",
			            async : false,
			            url: env.contextPath + "/certifymgr/testEndCheckByAjax.omp",
			            data : param,
			            dataType:  "json",
			            success: function(transport){
						    try{
								if(transport.responseCode == 'SUCC'){
									if(transport.responseMessage == 'Y'){
										$.fn.selfPage.saveTestStatus('${appInfo.cid }', '${appInfo.verifyReqVer }', '${CONST.TEST_PROGRESS_STATUS_COMPLETE}');		
									}else{
										alert("<gm:string value='jsp.certifymgr.certifyAppDetail.msg.fail_result'/>");	
									}
								}
							}catch(e){
							}
			            },
			            error: function(xhr, textStatus, errorThrown){}
			        });                     
			    },				
				
			    /**
			     * Save Test Ing
			     *
			     * @param cid
			     * @param verifyReqVer
			     * @param status
			     */  
			    saveTestStatus : function (cid, verifyReqVer, status){
			        
			        var param = "saveTestStatus.cid=" + cid + "&saveTestStatus.verifyReqVer=" + verifyReqVer + "&saveTestStatus.status=" + status;
			        
			        $.ajax({
			            type: "POST",
			            async : false,
			            url: env.contextPath + "/certifymgr/saveTestStatusByAjax.omp",
			            data : param,
			            dataType:  "json",
			            success: function(transport){
						    try{
						    	if(transport.responseFlag != "TEST_ING") {
						    		alert(transport.responseMessage);	
						    	}
								
								if(transport.responseCode == 'SUCC'){
									location.replace(env.contextPath + "/certifymgr/testAppList.omp?<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>");
								}								
							}catch(e){
							}
			            },
			            error: function(xhr, textStatus, errorThrown){}
			        });                     
			    },
			    /**
			     * Save Agree status
			     *
			     * @param cid
			     * @param verifyReqVer
			     * @param status
			     */  
			    saveAgreeStatus : function (cid, verifyReqVer, status){
			        
			        var param = "saveTestStatus.cid=" + cid + "&saveTestStatus.verifyReqVer=" + verifyReqVer + "&saveTestStatus.status=" + status;
			        
			        $.ajax({
			            type: "POST",
			            async : false,
			            url: env.contextPath + "/certifymgr/saveAgreeStatusByAjax.omp",
			            data : param,
			            dataType:  "json",
			            success: function(transport){
						    try{
								alert(transport.responseMessage);
								
								if(transport.responseCode == 'SUCC'){
									location.replace(env.contextPath + "/certifymgr/appList.omp?<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>");
								}								
							}catch(e){
							}
			            },
			            error: function(xhr, textStatus, errorThrown){}
			        });                     
			    }	    			    
		};
		
		$("#testEndBtn").click(function(event){
			event.preventDefault();
			
			$.fn.selfPage.searchTestEndCheck('${appInfo.cid }', '${appInfo.verifyReqVer }', '${CONST.TEST_PROGRESS_STATUS_COMPLETE}');
			
		});
		
		$("#testIngBtn").click(function(event){
			event.preventDefault();
			
			if(confirm("<gm:string value='jsp.certifymgr.certifyAppDetail.msg.confirm_test_start'/>")){
				$.fn.selfPage.saveTestStatus('${appInfo.cid }', '${appInfo.verifyReqVer }', '${CONST.TEST_PROGRESS_STATUS_ING}');	
			}
		});
		
		$("#certifyAgreeBtn").click(function(event){
			event.preventDefault();
			
			if(confirm("<gm:string value='jsp.certifymgr.certifyAppDetail.msg.confirm_certify_result'/>")){
				$.fn.selfPage.saveAgreeStatus('${appInfo.cid }', '${appInfo.verifyReqVer }', '${CONST.CODE_VERIFY_END}');	
			}
		});
		
		$("#testRejectBtn").click(function(event){
			event.preventDefault();
			openCenteredWindow(env.contextPath + "/certifymgr/popTestRejectReason.omp?saveTestStatus.cid=${appInfo.cid }&saveTestStatus.verifyReqVer=${appInfo.verifyReqVer }", 440, 250, "no", "");
		});		
		
		$("#appListBtn").click(function(event){
			event.preventDefault();
			location.replace(env.contextPath + "/certifymgr/appList.omp?<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>");
		});		
		
		
		$("#testerListBtn").click(function(event){
			event.preventDefault();
			location.replace(env.contextPath + "/certifymgr/testAppList.omp?<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>");
		});				
		
		
		
		
		$("#idCertifyHistory").click(function(){
			
			var cid = $(this).attr("cid");
			
			openCenteredWindow(env.contextPath + "/certifymgr/popHistoryList.omp?searchCon.cid="+cid, 810, 550, "yes", "");
		});
		
	});
	
	
	// Ajax file upload
	// pre-submit callback
	function eventBeforeSubmit(formData, jqForm, options) { 
	    // formData is an array; here we use $.param to convert it to a string to display it
	    // but the form plugin does this for you automatically when it submits the data
	    
	    //var queryString = $.param(formData);
	    //var form = jqForm[0];
	    
		//if((form.procType.value == 'create' || form.procType.value == 'update') && form.file.value == '') {
		//	$.fn.daily.popup.DailyAlert('업로드할 파일을 선택해주세요');
		//	return false;
		//}
	 
	    return true; 
	} 
	
	// Ajax file upload 
	// post-submit callback 
	function eventResponse(responseText, statusText)  {
		alert(responseText);
	}	
	
	function popTestCtg(cid, verifyReqVer, ctCtgSeq){
		openCenteredWindow(env.contextPath + "/certifymgr/popTestCaseList.omp?searchTestCaseCon.cid="+cid + "&searchTestCaseCon.verifyReqVer="+verifyReqVer + "&searchTestCaseCon.ctCtgSeq="+ctCtgSeq, 810, 580, "yes", "");
	}
	
	//]]>
</script>

</head>
<body>			
			<c:if test="${adminAuthGubun == CONST.AUTH_GBN_APPROVER && appInfo.verifyPrgrYn != CONST.CODE_VERIFY_TEST_END}">
				<div id="location">
					Home &gt; 상품관리 &gt; 검증관리 &gt <strong>Application 조회</strong> 
				</div><!-- //location -->
			
				<h1 class="fl pr10">Application 조회</h1>
				<p>전체 Application List를 확인하실 수 있습니다.</p>
			</c:if>
			
			<c:if test="${adminAuthGubun == CONST.AUTH_GBN_TESTER }">
				<div id="location">
					Home &gt; 상품관리 &gt; 검증관리 &gt <strong>Test List</strong> 
				</div><!-- //location -->
			
				<h1 class="fl pr10">Test List</h1>
				<p>전체 Application Test List를 확인하실 수 있습니다.</p>
			</c:if>
			
			<c:if test="${adminAuthGubun == CONST.AUTH_GBN_APPROVER && appInfo.verifyPrgrYn == CONST.CODE_VERIFY_TEST_END }">
				<div id="location">
					Home &gt; 상품관리 &gt; 검증관리 &gt <strong>Test 승인</strong> 
				</div><!-- //location -->
			
				<h1 class="fl pr10">Test 승인</h1>
				<p>Test한 상품에 대해 최종 확인을 합니다.</p>
			</c:if>
			
			
			<div class="tab">
				<ul>
					<li class="on"><a href="#">App Information</a></li>
					<c:forEach items="${subKeyList}" var="info" varStatus="status">
						<li><a href="<c:url value="/certifymgr/subDetail.omp?searchConAppDetail.cid=${info.cid}&searchConAppDetail.scid=${info.scid}&searchConAppDetail.verifyReqVer=${info.verifyReqVer}"/>&<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>">App file ${status.count }</a></li>
					</c:forEach>
				</ul>
			</div>
 <form id="saveForm" name="saveForm" method="post"> 
	<input type="hidden" name="saveAssign.cid" value="${appInfo.cid }">
	<input type="hidden" name="saveAssign.verifyReqVer" value="${appInfo.verifyReqVer }">
			<h2>기본정보</h2>		
			<table class="tabletype02">
				<colgroup>
					<col style="width:20%;">
					<col style="width:80%;">
				</colgroup>
				<tbody>
				<tr>
					<th>Name</th>
					<td class="text_l">${appInfo.prodNm }</td>
				</tr>
				<c:if test="${appInfo.verifyPrgrYn != CONST.CODE_VERIFY_TEST_END }">
					<tr>
						<th>상품 AID</th>
						<td class="text_l">${appInfo.aid }</td>
					</tr>
				</c:if>
				<tr>
					<th>Platform</th>
					<td class="text_l"><gc:html code="${appInfo.vmType}"/></td>
				</tr>
				
				<c:choose>
					<c:when test="${adminAuthGubun == CONST.AUTH_GBN_TESTER || appInfo.verifyPrgrYn == CONST.CODE_VERIFY_TEST_END}">
						<tr>
							<th>검증 요청일 / 검증 차수</th>
							<td class="text_l"><g:html value="${appInfo.insDttm}" format="L####-##-##" /> / ${appInfo.verifyReqVer }차 검증</td>
						</tr>
						<c:if test="${adminAuthGubun == CONST.AUTH_GBN_APPROVER }">
							<tr>
								<th>Tester</th>
								<td class="text_l">${appInfo.testerNm }</td>
							</tr>
						</c:if>
						<tr>
							<th>검증할당일 / 완료예정일</th>
							<td class="text_l"><g:html value="${appInfo.ctAssignDt}" format="L####-##-##" /> / <g:html value="${appInfo.ctEndExDt}" format="L####-##-##" /></td>
						</tr>
						<tr>
							<th>이용매뉴얼</th>
							<td class="text_l align_td"><a href="<c:url value="/fileSupport/fileDown.omp">
															<c:param name="bnsType" value="common.path.share.product"/>
															<c:param name="filePath" value="${appInfo.verifyScnrFile }" />
															<c:param name="fileName" value="${appInfo.verifyScnrFileNm}" />
												         </c:url>">${appInfo.verifyScnrFileNm }</a></td><!-- 2011-03-25 -->
						</tr>
						<tr>
							<th>Test 메모</th>
							<td class="text_l">${appInfo.testMemoByBr}</td>
						</tr>
						<tr>
							<th>Test Category</th>
							<td class="text_l">
								<c:forEach items="${appInfo.testCaseList}" var="info" varStatus="status">
									<a href="#" onClick="javascript:popTestCtg('${appInfo.cid}', '${appInfo.verifyReqVer}', '${info.ctCtgSeq}')">${info.testCaseTitleNm}</a>&nbsp;<c:if test="${!status.last }">/</c:if>&nbsp;
								</c:forEach>
							</td>
						</tr>
						<c:if test="${adminAuthGubun == CONST.AUTH_GBN_TESTER }">
							<tr>
								<th>Admin 반려 사유</th>
								<td class="text_l">${appInfo.testRejectCmtByBr}</td>
							</tr>
						</c:if>					
					</c:when>
					<c:otherwise>
						<tr>
							<th>검증차수</th>
							<td class="text_l">${appInfo.verifyReqVer }차 검증</td>
						</tr>
						<tr>
							<th>검증요청일</th>
							<td class="text_l"><g:html value="${appInfo.insDttm}" format="L####-##-##" /></td>
						</tr>
						<tr>
							<th>검증완료일</th>
							<td class="text_l"><g:html value="${appInfo.ctEndDt}" format="L####-##-##" /></td>
						</tr>
						<tr>
							<th>이용매뉴얼</th>
							<td class="text_l align_td"><a href="<c:url value="/fileSupport/fileDown.omp">
															<c:param name="bnsType" value="common.path.share.product"/>
															<c:param name="filePath" value="${appInfo.verifyScnrFile }" />
															<c:param name="fileName" value="${appInfo.verifyScnrFileNm}" />
												         </c:url>">${appInfo.verifyScnrFileNm }</a></td><!-- 2011-03-25 -->
						</tr>
						<tr>
							<th>검증 상태</th>
							<td class="text_l"><gc:html code="${appInfo.verifyPrgrYn}"/></td>
						</tr>					
					</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			
			<h2>검증사유</h2>		
			<c:if test="${appInfo.reCertifyReqYn == 'Y'}">
			<div class="fl mt15 mb05">
				<a class="btn_s" href="#" id="idCertifyHistory" cid="${appInfo.cid}"><strong>검증이력보기</strong></a>
			</div>
			</c:if>			
			
			<table class="tabletype02">
				<colgroup>
					<col style="width:20%;">
					<col style="width:80%;">
				</colgroup>
				<tbody>
				<tr>
					<th>검증요청사유</th>
					<td class="text_l">
						<c:set var="multiCd" value="${fn:split(appInfo.verifyCommentCd, ';')}" />
						<c:forEach items="${multiCd }" var="verifyCommentCd" varStatus="status">
							<gc:html code="${verifyCommentCd }" /> <c:if test="${fn:length(multiCd) != status.count }"> / </c:if>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td colspan="2" class="text_l">${appInfo.verifyEtcCmtByBr }</td>
				</tr>
				</tbody>
			</table>
 			
 			<c:if test="${adminAuthGubun != CONST.AUTH_GBN_TESTER && appInfo.verifyPrgrYn != CONST.CODE_VERIFY_TEST_END}">
			<h2>Test 정보</h2>		
			<table class="tabletype02">
				<colgroup>
					<col style="width:20%;">
					<col style="width:80%;">
				</colgroup>
				<tbody>
				<tr>
					<th>Test 할당일</th>
					<td class="text_l"><g:html value="${appInfo.ctAssignDt}" format="L####-##-##" /></td>
				</tr>
				<tr>
					<th>Test 완료 예정일</th>
					<td class="text_l"><g:html value="${appInfo.ctEndExDt}" format="L####-##-##" /></td>
				</tr>
				<tr>
					<th>Tester</th>
					<td class="text_l">${appInfo.testerNm }</td>
				</tr>
				<tr>
					<th>Test Category</th>
					<td class="text_l">
						<c:forEach items="${appInfo.testCaseList}" var="info">
							<a href="#" onClick="javascript:popTestCtg('${appInfo.cid}', '${appInfo.verifyReqVer}', '${info.ctCtgSeq}')">${info.testCaseTitleNm}</a>&nbsp;<c:if test="${!status.last }">/</c:if>&nbsp;
						</c:forEach>
					</td>
				</tr>
				<tr>
					<th>Test 메모</th>
					<td class="text_l">${appInfo.testMemoByBr}</td>
				</tr>
				<tr>
					<th>Test 완료일</th>
					<td class="text_l"><g:html value="${appInfo.testEndDt}" format="L####-##-##" /></td>
				</tr>
				</tbody>
			</table>
			</c:if>
 </form>
			<c:choose>
				<c:when test="${adminAuthGubun == CONST.AUTH_GBN_TESTER }">
					<c:choose>
						<c:when test="${appInfo.testerCtStat == CONST.TEST_PROGRESS_STATUS_ING }">
							<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="testEndBtn"><span>Test 종료</span></a> <a class="btn" href="#" id="testerListBtn"><span>목록</span></a></p>
						</c:when>
						<c:when test="${appInfo.testerCtStat == CONST.TEST_PROGRESS_STATUS_REQUEST ||  appInfo.testerCtStat == CONST.TEST_PROGRESS_STATUS_REJECT}">
							<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="testIngBtn"><span>Test 진행</span></a> <a class="btn" href="#" id="testerListBtn"><span>목록</span></a></p>
						</c:when>
						<c:otherwise>
							<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="testerListBtn"><span>목록</span></a></p>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:when test="${adminAuthGubun == CONST.AUTH_GBN_APPROVER }">
					<c:choose>
						<c:when test="${appInfo.testerCtStat == CONST.TEST_PROGRESS_STATUS_COMPLETE && appInfo.verifyPrgrYn != CONST.CODE_VERIFY_END }">					
							<p class="btn_wrap text_r mt25">
								<a class="btn" href="#" id="testRejectBtn"><span>반려</span></a> 
								<a class="btn" href="#" id="certifyAgreeBtn"><span>승인</span></a> 
								<a class="btn" href="#" id="appListBtn"><span>목록</span></a>
							</p>						
						</c:when>
						<c:otherwise>
							<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="appListBtn"><span>목록</span></a></p>
						</c:otherwise>	
					</c:choose>			
				</c:when>
				<c:otherwise>
					<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="appListBtn"><span>목록</span></a></p>
				</c:otherwise>
			</c:choose>
</body>
</html>
