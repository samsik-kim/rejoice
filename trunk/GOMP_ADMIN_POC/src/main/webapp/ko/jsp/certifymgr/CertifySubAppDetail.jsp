<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Application 조회</title>

<script type="text/javascript">
	//<![CDATA[
           
	$(function() {
		
	    var options = {
		        beforeSubmit: eventBeforeSubmit,  // pre-submit callback
		        success     : eventResponse,      // post-submit callback
		        url         : env.contextPath + '/certifymgr/saveSubTestCompleteByAjax.omp',
		        type        : 'post',
		        async : false,
		        dataType:  "html"
		    };
		 
		$('#saveForm').ajaxForm(options);
		

		$("#subTestCompleteBtn").click(function(event){
			event.preventDefault();
			
			if(confirm("<gm:string value='jsp.certifymgr.certifySubAppDetail.msg.test_end'/>")){
				
				if(showValidate("saveForm", 'default')){
					$('#saveForm').submit();
				}
			}
		});
		
		$("#testModifyBtn").click(function(event){
			event.preventDefault();  
			location.replace(env.contextPath + "/certifymgr/subDetail.omp?searchConAppDetail.cid=${subInfo.cid}&searchConAppDetail.scid=${subInfo.scid}&searchConAppDetail.verifyReqVer=${subInfo.verifyReqVer}&isMofifyYn=Y&<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>");
		});
		
		$("#testListBtn").click(function(event){
			event.preventDefault(); 
			location.replace(env.contextPath + "/certifymgr/testAppList.omp?<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>");
		});
		
		
		$("#appListBtn").click(function(event){
			event.preventDefault();
			location.replace(env.contextPath + "/certifymgr/appList.omp?<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>");
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
	    try{
	    	responseText = responseText.replace("<PRE>","");
	    	responseText = responseText.replace("</PRE>","");
	    	
	    	var result = eval("(" + responseText + ")");
	    	
	    	alert(result.responseMessage);
	    	
			if(result.responseCode == 'SUCC'){
				
				location.replace(env.contextPath + "/certifymgr/subDetail.omp?searchConAppDetail.cid=${subInfo.cid}&searchConAppDetail.scid=${subInfo.scid}&searchConAppDetail.verifyReqVer=${subInfo.verifyReqVer}&<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>");
			}								
		}catch(e){
		}
	}	
	//]]>
</script>

</head>
<body>
			<div id="location">
				Home &gt; 상품관리 &gt; 검증관리 &gt <strong>Application 조회</strong> 
			</div><!-- //location -->
 
			
			<c:if test="${adminAuthGubun == CONST.AUTH_GBN_APPROVER && subInfo.verifyPrgrYn != CONST.CODE_VERIFY_TEST_END}">
				<h1 class="fl pr10">Application 조회</h1>
				<p>전체 Application List를 확인하실 수 있습니다.</p>
			</c:if>
			
			<c:if test="${adminAuthGubun == CONST.AUTH_GBN_TESTER }">
				<h1 class="fl pr10">Test List</h1>
				<p>전체 Application Test List를 확인하실 수 있습니다.</p>
			</c:if>
			
			<c:if test="${adminAuthGubun == CONST.AUTH_GBN_APPROVER && subInfo.verifyPrgrYn == CONST.CODE_VERIFY_TEST_END }">
				<h1 class="fl pr10">Test 승인</h1>
				<p>Test한 상품에 대해 최종 확인을 합니다.</p>
			</c:if>
			
			
			<div class="tab">
				<ul>
					<li><a href="<c:url value="/certifymgr/appDetail.omp?searchConAppDetail.cid=${subInfo.cid}&searchConAppDetail.verifyReqVer=${subInfo.verifyReqVer}"/>&<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>">App Information</a></li>
					<c:forEach items="${subKeyList}" var="info" varStatus="status">
						<c:choose>
							<c:when test="${info.scid == subInfo.scid}">
								<li class="on"><a href="<c:url value="/certifymgr/subDetail.omp?searchConAppDetail.cid=${info.cid}&searchConAppDetail.scid=${info.scid}&searchConAppDetail.verifyReqVer=${info.verifyReqVer}"/>&<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>">App file ${status.count }</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="<c:url value="/certifymgr/subDetail.omp?searchConAppDetail.cid=${info.cid}&searchConAppDetail.scid=${info.scid}&searchConAppDetail.verifyReqVer=${info.verifyReqVer}"/>&<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>">App file ${status.count }</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</ul>
			</div>
 <form id="saveForm" name="saveForm" method="post" enctype="multipart/form-data"> 
	<input type="hidden" name="subAppTestResultSave.cid" value="${subInfo.cid }">
	<input type="hidden" name="subAppTestResultSave.scid" value="${subInfo.scid }">
	<input type="hidden" name="subAppTestResultSave.verifyReqVer" value="${subInfo.verifyReqVer }">
	<input type="hidden" name="subAppTestResultSave.vmType" value="${subInfo.vmType }">
			<table class="tabletype02">
				<colgroup>
					<col style="width:10%;">
					<col style="width:10%;">
					<col style="width:80%;">
				</colgroup>
				<tbody>
				<tr>
					<th colspan="2">등록 파일</th>
					<td class="text_l align_td"><a href="<c:url value="/fileSupport/fileDown.omp">
															<c:param name="bnsType" value="common.path.share.product"/>
															<c:param name="filePath" value="${subInfo.runFilePos }" />
															<c:param name="fileName" value="${subInfo.runFileNm}" />
												         </c:url>">${subInfo.runFileNm}</a> (${subInfo.runFileSizeByKilobyte}Kbyte)</td><!-- 2011-03-25 -->
				</tr>
				<tr>
					<th rowspan="3">OS Version</th>
					<th>Min</th>
					<td class="text_l">${subInfo.vmVerMin}</td>
				</tr>
				<tr>
					<th>Target</th>
					<td class="text_l">${subInfo.vmVerTarget}</td>
				</tr>
				<tr>
					<th>Max</th>
					<td class="text_l">${subInfo.vmVerMax}</td>
				</tr>
				<tr>
					<th colspan="2">대상단말</th>
					<td class="text_l scroll_wrap">
						<div class="scroll">
							<table class="tabletype02" style="width:630px;">
								<colgroup>
									<col >
									<col >
									<col >
									<col >
								</colgroup>
								<thead>
								<tr>
									<th>단말 명칭</th>
									<th>모델명</th>
									<th>지원 OS</th>
									<th>LCD Size</th>
								</tr>
								<c:forEach items="${subInfo.supportPhoneList}" var="info">
									<tr>
										<td>${info.modelNm }</td>
										<td>${info.mgmtPhoneModelNm }</td>
										<td><gc:html code="${info.vmVer }"/></td>
										<td><gc:html code="${info.lcdSize }"/></td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</td>
				</tr>
				
			<c:choose>
				<c:when test="${adminAuthGubun == CONST.AUTH_GBN_TESTER && subInfo.testerCtStat != CONST.TEST_PROGRESS_STATUS_REQUEST && (subInfo.agrmntStat == CONST.AGREEMENT_STATUS_INIT || (subInfo.agrmntStat != CONST.AGREEMENT_STATUS_INIT && isMofifyYn == 'Y'))}">
				<tr>
					<th colspan="2">App. 검증결과서</th>
					<td class="text_l align_td">
						<input id="appCtResult" name="subAppTestResultSave.appCtResult" type="file" style="width:500px;" 
						<c:if test="${subInfo.verifyPrgrYn == CONST.CODE_VERIFY_TEST_REJECT }">
							v:required m:required="<gm:tagAttb value="jsp.certifymgr.certifySubAppDetail.msg.app_certify_result_regist"/>"
						</c:if>
						/>
						<!-- <a class="btn_s" href="#" id=""><span>찾아보기</span></a> --> <a class="btn_s" href="<c:url value="/fileSupport/fileDown.omp">
																													<c:param name="bnsType" value="common.path.share.misc"/>
																													<c:param name="filePath" value="/AppResultSample_An_110322.xls" />
																													<c:param name="fileName" value="App검증결과서.xls" />
																												 </c:url>"><span>App.검증결과서Down</span></a>
					</td>
				</tr>
				<tr>
					<th colspan="2">Test 결과</th>
					<td class="text_l align_td">
						<select id="agrmntStat" name="subAppTestResultSave.agrmntStat" style="width:104px;" v:required m:required="(Test 결과)필수 입력해 주세요.">
                        	<option value=""></option>
                        	<gc:options group="PD0050" value="" codeType="full" filter="ct"/>
                        </select>
					</td>
				</tr>
				<tr>
					<th colspan="2">기타첨부파일</th>
					<td class="text_l align_td">
						<input id="addFile1" name="subAppTestResultSave.addFile1" type="file" style="width:500px;" /> 
						<!-- <a class="btn_s" href="#"><span>찾아보기</span></a> --><br />
						<input id="addFile2" name="subAppTestResultSave.addFile2" type="file" style="width:500px;" /> 
						<!-- <a class="btn_s" href="#"><span>찾아보기</span></a> --><br />
						<input id="addFile3" name="subAppTestResultSave.addFile3" type="file" style="width:500px;" /> 
						<!-- <a class="btn_s" href="#"><span>찾아보기</span></a> --><br />
						<input id="addFile4" name="subAppTestResultSave.addFile4" type="file" style="width:500px;" /> 
						<!-- <a class="btn_s" href="#"><span>찾아보기</span></a> --><br />
					</td>
				</tr>
				<tr>
					<th colspan="2">Comment</th>
					<td class="text_l align_td">
						<textarea class="text_area" id="appCtCmt" name="subAppTestResultSave.appCtCmt" v:text8_limit="3000" m:text8_limit="<gm:tagAttb value="jsp.certifymgr.certifySubAppDetail.msg.comment"/>"> </textarea>
					</td>
				</tr>				
				</c:when>
				<c:otherwise>
				<tr>
					<th colspan="2">App. 검증결과서</th>
					<td class="text_l align_td"><a href="<c:url value="/fileSupport/fileDown.omp">
															<c:param name="bnsType" value="common.path.share.product"/>
															<c:param name="filePath" value="${subInfo.appCtResultFile }" />
															<c:param name="fileName" value="${subInfo.appCtResultFileNm}" />
												         </c:url>">${subInfo.appCtResultFileNm }</a></td><!-- 2011-03-25 -->
				</tr>
				<tr>
					<th colspan="2">Test 결과</th>
					<td class="text_l"><gc:html code="${subInfo.agrmntStat }"/></td>
				</tr>
				<tr>
					<th colspan="2">기타첨부파일</th>
					<td class="text_l align_td">
					<c:forEach items="${reportAddFileList}" var="info">
						<a href="<c:url value="/fileSupport/fileDown.omp">
									<c:param name="bnsType" value="common.path.share.product"/>
									<c:param name="filePath" value="${info.addFile }" />
									<c:param name="fileName" value="${info.addFileNm}" />
						         </c:url>">${info.addFileNm }</a><br />
					</c:forEach>
					</td>
				</tr>
				<tr>
					<th colspan="2">Comment</th>
					<td class="text_l">${subInfo.appCtCmtByBr}</td>
				</tr>				
				</c:otherwise>	
			</c:choose>							
				</tbody>
			</table>
 </form>
 			<c:choose>
				<c:when test="${adminAuthGubun == CONST.AUTH_GBN_TESTER && subInfo.testerCtStat != CONST.TEST_PROGRESS_STATUS_REQUEST && subInfo.testerCtStat != CONST.TEST_PROGRESS_STATUS_COMPLETE && (subInfo.agrmntStat == CONST.AGREEMENT_STATUS_INIT || isMofifyYn == 'Y')}">
					<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="subTestCompleteBtn"><span>Test완료</span></a> <a class="btn" href="#" id="testListBtn"><span>목록</span></a></p>			
				</c:when>
				<c:when test="${adminAuthGubun == CONST.AUTH_GBN_TESTER && subInfo.testerCtStat != CONST.TEST_PROGRESS_STATUS_REQUEST && subInfo.testerCtStat == CONST.TEST_PROGRESS_STATUS_ING && subInfo.agrmntStat != CONST.AGREEMENT_STATUS_INIT}">
					<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="testModifyBtn"><span>수정</span></a> <a class="btn" href="#" id="testListBtn"><span>목록</span></a></p>				
				</c:when>
				<c:when test="${adminAuthGubun == CONST.AUTH_GBN_TESTER && subInfo.testerCtStat == CONST.TEST_PROGRESS_STATUS_REQUEST && subInfo.testerCtStat != CONST.TEST_PROGRESS_STATUS_ING && subInfo.agrmntStat == CONST.AGREEMENT_STATUS_INIT}">
					<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="testListBtn"><span>목록</span></a></p>
				</c:when>
				<c:otherwise>
					<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="appListBtn"><span>목록</span></a></p>				
				</c:otherwise>
			</c:choose>
</body>
</html>

