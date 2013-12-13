<%@page language="java" contentType="text/html; charset=UTF-8"%>      
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<head>
<script type="text/javascript" src="<c:url value="/js/common/niceforms.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/common/stringUtil.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/ajaxfileupload.js"/>"></script>
<script type="text/javascript">
var len=0;
$(document).ready(function(){
	if('${qna.qnaTpCd}'!=''){
		document.detailQnaForm.subject.value = '${qna.qnaTpCd}';
		document.detailQnaForm.dir_comment.value = '${qna.prcDscr}';
	}
	var selValue = document.detailQnaForm.qnaClsCd.value;
	var temp = document.detailQnaForm.qnaClsCd2.value; 
	$.post(env.contextPath+"/qna/getAjaxSecondCategoryList.omp", {"selectFaqCategoryType":selValue}, function(data) {
		$("#qnaCtgr02").find('option').remove();
		makeOptionReady("qnaCtgr02", data,temp);
	}, "json");
});

function makeOptionReady(target, data,temp) {
	if(data.status=='true'){
		alert("2Depth Category Error.");
	}
	var result = data.result;
	var defText = "<gm:string value='jsp.community.qna.qna_details.text.whole'/>";
	var option = $("<option/>").attr("value", "");
	var option = $("<option>" + defText + "</option>").attr("value", "");
	option.attr(($.browser.msie ? 'label':'text'), defText);
	$("#" + target).append(option);

	for(var i in result) {
		if(result[i].ctgrCd == temp){
			$("#" + target).append("<option value='" + result[i].ctgrCd + "' selected >" + result[i].ctgrNm + "</option>");
		}else{
			$("#" + target).append("<option value='" + result[i].ctgrCd + "'>" + result[i].ctgrNm + "</option>");
		}
	}
}

	function func_registryQnA(){
		var url = document.detailQnaForm.url.value;
		var q = document.getElementById('qNo');
		var at = document.getElementById('subject');
		var ad = document.getElementById('dir_comment');
		var frm = document.detailQnaForm;
		var url = document.detailQnaForm.url.value;
		if(confirm("<gm:string value='jsp.community.qna.qna_details.msg.savechk'/>")){	
			if(showValidate('detailQnaForm', 'default', '')){
				if(url == "displayDQnA.omp"){
					if(frm.qnaCtgr01.value == ''){
		        		alert("<gm:string value='jsp.community.qna.qna_details.msg.ctgrchk'/>");
		            	return;
		        	}       
				}else{
					if(frm.qnaCtgr02.value == ''){
		        		alert("<gm:string value='jsp.community.qna.qna_details.msg.ctgrchk'/>");
		            	return;
		        	}   
				}
				if(url == "displayDQnA.omp"){
					frm.modCtgr.value=frm.qnaCtgr01.value;
				}else{
					frm.modCtgrSub.value=$("#qnaCtgr01 option:selected").text();
					frm.modCtgrSub2.value=$("#qnaCtgr02 option:selected").text();
					frm.modCtgr.value=frm.qnaCtgr02.value;
				}
				frm.queNo.value = q.value;
				frm.ansTitle.value = at.value;
				frm.ansDscr.value = ad.value;
				//document.detailQnaForm.chk.value = "List";
				if(url == "displayDQnA.omp"){
					//frm.action="sendDMail.omp";
					//if(len>2097152){
					//	alert("<gm:string value='檔案需小於2MByte'/>");
					//	document.detailQnaForm.bodyUpload.value = "";
					//	return;
					//}
					var options={ 
			 				success:       responseSave,
	         				url:       env.contextPath+"/qna/sendDMail.omp", 
	         				type:      "post"  
						}; 
					$('#detailQnaForm').ajaxSubmit(options);
				}else{
					//frm.action="sendMail.omp";
					var options={ 
			 				success:       responseSave,
	         				url:       env.contextPath+"/qna/sendMail.omp", 
	         				type:      "post"  
						}; 
					$('#detailQnaForm').ajaxSubmit(options);
				}
				//frm.submit();
			}
			return;
		}
	}
	
	function responseSave(){
		alert("<gm:string value='jsp.community.qna.qna_details.msg.savere'/>");
		//var url	= env.contextPath+"/mobileBanner/mobileBannerList.omp";
		//location.href = url;
		go_List();
	}
	
	function limtAttach(obj) {

		var isSubmit = false;
		var compStr = new Array("gif", "jpg", "jpeg", "png" , "htm", "html", "zip", "rar", "alz" );
		var str = obj.value;

		if( str == '') {
			return true;
		}
		
		
		idx = str.lastIndexOf('.');
		if (idx != -1) {
			ext = str.substring(idx + 1, str.len);
		} 
		else {
			ext = "";
		}		

		
		if (ext != "") {
			for (i = 0; i < compStr.length; i++) {
				if (ext.toLowerCase() == compStr[i]) {
					isSubmit = true;
					break;
				}
			}
		}
		
		if (!isSubmit) {
			alert("<gm:string value='jsp.community.qna.qna_details.msg.extn'/>");
			obj.value = "";			
			
			return false;
		}
			
			var temp = obj;
		//	$.ajaxFileUpload
		var options = 
			//(
				{
					//url:env.contextPath+"/qna/ajaxQnaFileUpload.omp", 
					secureuri:false,
					//fileElementId:'bodyUpload',
					dataType: 'JSON',
					success: function (data, status)
					{
						 var obj2 = eval("(" + data + ")");
						if(obj2.resultCode == 1){
							len = obj2.size;
							if(obj2.size>2097152){
								alert("<gm:string value='檔案需小於2MByte'/>");
								//document.detailQnaForm.bodyUpload.value = "";
								//$(fileObj).parent().html($(fileObj).parent().html());
								$(obj).parent().html($(obj).parent().html());
								return;
							}
						}else{
							alert("<gm:string value='jsp.member.common.msg.error'/>");
						}
					},
					error: function (data, status, e)
					{
						alert(e);
					}
				};
			//);
			
			var frm = $('#detailQnaForm');
			frm.attr("target", "_self");	
			frm.attr("action","./ajaxQnaFileUpload.omp");
			frm.ajaxSubmit(options);
		return true;
	}
	
	function fileCheck(){
		alert(document.detailQnaForm.fileSize.value);
	}
	
	//2depth AJAX
	$(function() {
		$("#qnaCtgr01").change(function() {
			var selValue = this.value;
			$.post(env.contextPath+"/qna/getAjaxSecondCategoryList.omp", {"selectFaqCategoryType":selValue}, function(data) {
				$("#qnaCtgr02").find('option').remove();
				makeOption("qnaCtgr02", data);
			}, "json");
		});
	});

	function makeOption(target, data) {
		if(data.status=='true'){
			alert("2Depth Category Error.");
		}
		var result = data.result;
		var defText = "<gm:string value='jsp.community.qna.qna_details.text.whole'/>";
		var option = $("<option/>").attr("value", "");
		var option = $("<option>" + defText + "</option>").attr("value", "");
		option.attr(($.browser.msie ? 'label':'text'), defText);
		$("#" + target).append(option);

		for(var i in result) {
			$("#" + target).append("<option value='" + result[i].ctgrCd + "'>" + result[i].ctgrNm + "</option>");
		}
	}
	
	function func_cancel(){
		var url = document.detailQnaForm.url.value;
		if(url == "displayDQnA.omp"){
			frm.action="searchDQnA.omp";
		}else{
			frm.action="searchSCQnA.omp";
		}
		frm.submit();
    }

	function go_List(){
		var url = document.detailQnaForm.url.value;
		document.detailQnaForm.chk.value = "List";
		$("#qnaListForm").attr("target", "_self");
		if(url=='displayDQnA.omp'){
			$("#detailQnaForm").attr("action", "searchDQnA.omp");
		}else{
			$("#detailQnaForm").attr("action", "searchSCQnA.omp");
		}
		$("#detailQnaForm").submit();
	}
	//-->	
</script>
</head>
<body>		
<s:form id="detailQnaForm" name="detailQnaForm" theme="simple" method="post" enctype="multipart/form-data">
	<input type="hidden" id="pageNo" name="qnaSub.page.no" value="${qnaSub.page.no}"/>
	<s:hidden id="qnaSub" name="qnaSub.startDate" />
	<s:hidden id="qnaSub" name="qnaSub.endDate" />
	<s:hidden id="qnaSub" name="qnaSub.prcStatCd" />
	<s:hidden id="qnaSub" name="qnaSub.queId" />
	<s:hidden id="qnaSub" name="qnaSub.ctgrCd" />
	<s:hidden id="qnaSub" name="qnaSub.qnaClsCd2" />
	<s:hidden id="qnaSub" name="qnaSub.keyword" />
	<s:hidden id="qnaSub" name="qnaSub.keytype" />
	<s:hidden id="qnaSub" name="qnaSub.dateType" />
	<s:hidden id="prcDscr" name="prcDscr" />
	<s:hidden id="prcOpId" name="prcOpId"/>
	<s:hidden name="selectFaqCategoryType"/>
	<s:hidden id="mailFrom" name="mailFrom" value="days365@feelingk.com"/>
	<s:hidden id="mailSubject" name="mailSubject" />
	<s:hidden id="mailContent" name="mailContent" />
	<s:hidden id="queNo" name="qna.queNo"/>
	<s:hidden id="ansDscr" name="qna.ansDscr"/>
	<s:hidden id="ansTitle" name="qna.ansTitle"/>
	<s:hidden id="modCtgr" name="modCtgr"/>
	<s:hidden id="modCtgrSub" name="modCtgrSub"/>
	<s:hidden id="modCtgrSub2" name="modCtgrSub2"/>
	<s:hidden id="qnaClsCd" name="qnaClsCd"/>
	<s:hidden id="qnaClsCd2" name="qnaClsCd2"/>
	<s:hidden id="dev_id" name="dev_id"/>
	<s:hidden id="app_id" name="app_id"/>
	<s:hidden id="dev_no" name="dev_no"/>
	<s:hidden id="dev_email" name="dev_email"/>
	<s:hidden id="url" name="url"/>
	<s:hidden id="searchType"name="searchType"/>
	<s:hidden id="topCode"name="topCode"/>	
	<s:hidden id="leftCode"name="leftCode"/>
	<s:hidden name="status"/>
	<s:hidden name="prcStatCd"/>
	<s:hidden id="fileName" name="fileName"/>
	<s:hidden id="fileSize" name="fileSize" />
	<s:hidden id="chk" name="chk" />
	<s:hidden id="filePathName" name="filePathName"/>
	
	<div id="location">
	<c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">
		Home &gt; 고객지원 &gt; Q&A 관리 &gt; <strong>개발자 Q&A</strong>  
	</c:if>
	<c:if test="${url eq 'displayQnA.omp' || url eq 'sendMail.omp'}">
		Home &gt; 고객지원 &gt; Q&A 관리 &gt; <strong>SC Q&A</strong> 
	</c:if>
	</div>
	<!-- //location -->
	<h1 class="fl pr10"><c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">개발자 Q&A</c:if><c:if test="${url eq 'displayQnA.omp' || url eq 'sendMail.omp'}">SC Q&A</c:if></h1>
	<c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}"><p>접수된 Q&A를 조회 / 답변할 수 있습니다.</p></c:if><c:if test="${url eq 'displayQnA.omp' || url eq 'sendMail.omp'}"><p>접수된 Q&A를 조회 / 답변할 수 있습니다.</p></c:if>
	<table class="tabletype02">
		<colgroup>
		<c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">
			<col style="width:10%;">
			<col style="width:10%;">
			<col style="width:35%;">
			<col style="width:10%;">
			<col style="width:35%;">
		</c:if><c:if test="${url eq 'displayQnA.omp' || url eq 'sendMail.omp'}">
			<col style="width:20%;">
			<col style="width:30%;">
			<col style="width:20%;">
			<col style="width:30%;">
		</c:if>
		</colgroup>
		<tbody>
			<c:forEach items="${questionList}" var="item" varStatus="cnt">
				<tr>
				<th <c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">colspan="2"</c:if>>조치상태</th><!-- 조치상태  -->
					<td colspan="3" class="text_l">
						<g:html value="${item.prcStatNm}"/>
						<input type="hidden" id="qNo" value="${item.queNo }" />
					</td>
				</tr>
				<tr>
					<th <c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">colspan="2"</c:if>>작성자</th>
					<td class="text_l">
						<g:html value="${item.queId}"/>
						<input type="hidden" id="insNm" name="insNm" value="${item.queId}"/>
						<input type="hidden" id="insMbr" name="insMbr" value="${item.devMbrNo}"/>
					</td>
					<th>작성일시</th>
					<td class="text_l">
						<g:html value="${item.regDt}" />
						<input type="hidden" id="insDt" name="insDt" value="${item.regDt}"/>
					</td>
				</tr>
				<tr>
					<th <c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">colspan="2"</c:if>>이메일</th>
					<td colspan="3" class="text_l">
						<g:html value="${item.emailAddr}"/>
						<input type="hidden" id="insEmail" name="insEmail" value="${item.emailAddr}"/>
					</td>
				</tr>
				<c:if test="${url eq 'displayQnA.omp' || url eq 'sendMail.omp'}">
				<tr>
					<th>휴대폰모델명</th>
					<td class="text_l">
						<g:html value="${item.hpModel}"/>
						<input type="hidden" id="insHpM" name="insHpM" value="${item.hpModel}"/>
					</td>
					<th>휴대폰번호</th>
					<td class="text_l">
						<g:html value="${item.hpNo}"/>
						<input type="hidden" id="insHpN" name="insHpN" value="${item.hpNo}"/>
					</td>
				</tr>
				</c:if>
				<tr>
					<th <c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">colspan="2"</c:if>>제목</th>
					<td colspan="3" class="text_l align_td">
						<s:select id="qnaCtgr01" name="qna.ctgrCd" theme="simple" list="categoryNameList" listKey="ctgrCd" listValue="ctgrNm" value="%{qnaClsCd}" headerKey="" headerValue="전체" style="width:104px;"/>
						<!-- 2차카테고리 -->
						<c:if test="${url eq 'displayQnA.omp' || url eq 'sendMail.omp'}">
						<s:if test="qna.ctgrCd == null">
							<select id="qnaCtgr01" name="qna.ctgrCd" style="width: 104px;">
								<option value="">전체</option>
							</select>
						</s:if>
						<s:else>
							<s:select id="qnaCtgr02" name="qna.qnaClsCd2" theme="simple" list="secondDepthCategoryList" listKey="ctgrCd" listValue="ctgrNm" value="%{qnaClsCd2}" headerKey="" headerValue="전체" style="width:104px;"/>
						</s:else>
						</c:if>
						<c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">
						<g:html value="${item.queTitle}"/>
						</c:if>
						<c:if test="${url eq 'displayQnA.omp' || url eq 'sendMail.omp'}">
						<p><g:html value="${item.queTitle}"/></p>
						</c:if>
						<input type="hidden" id="insTitle" name="insTitle" value="<g:html value="${item.queTitle}"/>"/>
					</td>
				</tr>
				<tr>
					<th <c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">colspan="2"</c:if>>내용</th>
					<td colspan="3" class="text_l">
						<g:html value="${item.queDscr}"/>
						<input type="hidden" id="insDscr" name="insDscr" value="<g:html value="${item.queDscr}"/>"/>
					</td>
				</tr>
			</c:forEach>
						<c:choose>
							<c:when test="${ansCnt > 0}">
								<c:forEach items="${ansList}" var="item" varStatus="ansCnt">
								<c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">
								<tr>
				  					<th rowspan="2">답변</th>
				  					<th>첨부파일</th>
				  					<td colspan="3" class="text_l">
				  						<a href="<c:url value="/fileSupport/fileDown.omp">
											<c:param name="bnsType" value="dev.path.share.misc.qna-attachement"/>
											<c:param name="filePath" value="${item.filePath}${item.fileName}"/>
											<c:param name="fileName" value="${item.fileReal}"/>
										</c:url>">${item.fileReal}</a>
									</td>
								</tr>
								<tr>
				  					<th>내용</th>
				  					<td colspan="3" class="text_l">
				  						<p><g:html value="${item.ansDscr}"/></p>
				  					</td>
								</tr>
								</c:if>
								<!-- <tr>
									<th>답변 제목</th>
									<td colspan="3" class="text_l align_td">
										<g:html value="${item.ansTitle}"/>
									</td>
								</tr>
								<c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">
								  <tr>
									<th>첨부파일</th>
									<td colspan="3" class="text_l">
										<a href="<c:url value="/fileSupport/fileDown.omp">
											<c:param name="bnsType" value="dev.path.share.misc.qna-attachement"/>
											<c:param name="filePath" value="${item.filePath}${item.fileName}"/>
											<c:param name="fileName" value="${item.fileReal}"/>
										</c:url>">${item.fileReal}</a>
                					</td>
								</tr>
								</c:if> -->
								<c:if test="${url eq 'displayQnA.omp' || url eq 'sendMail.omp'}">
								<tr>
									<th>답변</th>
									<td colspan="3" class="text_l">
										<p><g:html value="${item.ansDscr}"/></p>
									</td>
								</tr>
								</c:if>
							</c:forEach>
							</c:when>
							<c:otherwise>
							<tr>
								<th <c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">colspan="2"</c:if>>답변</th>
								<td colspan="3" class="text_l">
									등록된 답변이 없습니다.
								</td>
							</tr>
							</c:otherwise>
						</c:choose>
		</tbody>
	</table>
	<c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'||((url eq 'displayQnA.omp' || url eq 'sendMail.omp')&&prcstcd eq 'CM000701')}">
		<table class="tabletype02 mt05">
			<colgroup>
				<col style="width:20%;">
				<col style="width:80%;">
			</colgroup>
			<thead>
				<tr>
					<th colspan="2">답변 작성 후 [등록]을 클릭하면 문의한 회원에게 이메일로 발송 됩니다.</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th>제목</th>
					<td class="text_l">
						<input id="subject" type="text" class="txt" style="width:80%;" value="<g:tagAttb value=""/>" v:required="trim" m:required="<gm:tagAttb value='jsp.community.qna.qna_details.msg.title'/>" v:text8_limit="100" m:text8_limit="<gm:tagAttb value='jsp.community.qna.qna_details.msg.100'/>" />						
					</td>
				</tr>
				<c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'}">
				<tr>
					<th>메일 첨부 파일</th>
					<td class="text_l align_td">
					<span><s:file id="bodyUpload" name="bodyUpload" onchange="limtAttach(this);"/></span>
					</td>
				</tr>
				</c:if>
				<tr>
					<th>답변입력</th>
					<td class="text_l"><textarea id="dir_comment" name="dir_comment" v:required="trim" m:required="<gm:tagAttb value='jsp.community.qna.qna_details.msg.dscr'/>" class="text_area" v:text8_limit="4000" m:text8_limit="<gm:tagAttb value='jsp.community.qna.qna_details.msg.4000'/>"><g:tagBody value=""/></textarea></td>
				</tr>
			</tbody>
		</table>
		</c:if>
		<p class="btn_wrap text_r mt25">
		<c:if test="${url eq 'displayDQnA.omp' || url eq 'sendDMail.omp'||((url eq 'displayQnA.omp' || url eq 'sendMail.omp')&&prcstcd eq 'CM000701')}">
		<a class="btn" href="javascript:func_registryQnA();"><span>등록</span></a> 
		</c:if>
		<a class="btn" href="javascript:go_List();"><span>목록</span></a></p>
</s:form>
</body>