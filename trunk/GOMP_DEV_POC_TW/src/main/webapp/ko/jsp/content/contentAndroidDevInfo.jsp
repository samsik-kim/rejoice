<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
<!--
	var options;
	var updateFns;
    var verifyScnText = "<gm:string value='jsp.content.contentAndroidDevInfo.text.verifyScnText' />";
    var updateText = "<gm:string value='jsp.content.contentAndroidDevInfo.text.update07' />";
    var updateInitText = "<gm:string value='jsp.content.contentAndroidDevInfo.msg.update03' />";
    
	var divBinaryCnt = ${resultMap.subContentsCnt};

	$(document).ready(function() {
		options = {
				//beforeSubmit:	submitConfirm,
				success : 	FileUploadCallBack,
				error	:	FileUploadCallBack,
				dataType:   "html",
				url		: env.contextPath + "/content/ajaxReadManifestXML.omp",
				type	: "post"
		};

		var fooFns  = {  
			checkverifydoc : function() {// 이용매뉴얼(검증시나리오)
				
				try{
					if ('${content.verifyScnrFileNm}' != '') {	//한번 저장 후
						
						if ($('#divScnrVerifyFile').attr('style').replace(' ' , '').replace(';' , '').toLowerCase() == 'display:none;'.replace(';' , '')) return true;
						else {
							if($('#tempScnrUploadFile').val() != '') return true;  
							else return false;
						}
					} else {	// 최초
						
						if ($('#verifyScnrUpload').val() != '') return true;
						else return false;
					}
				}catch(e){
					if ($('#tempScnrUploadFile').val() != verifyScnText) return true;
					else return false;
				}
			}
		};

		<%-- List Page --%>
		$("#btnList").click(function() {
			location.href = env.contextPath + "/content/contentsStatusList.omp";
		}).css("cursor", "pointer");
	
		<%-- 취소 --%>
		$("#btnCancel").click(function() {
			location.href = env.contextPath + "/content/contentDevInfoView.omp?content.cid=" + '${resultMap.cid}';
		}).css("cursor", "pointer");
	
		<%-- 수정요청 --%>
		$("#btnMod").click(function() {
			//var result = showValidate('editEtcForm', 'default', '개발 정보 입력 오류', fooFns);
			//if(result) {
			if(confirm("<gm:string value='jsp.content.contentAndroidDevInfo.btn.save01'/>")) {

				$("#editEtcForm").attr("action", "modifyContentDevInfo.omp");
				$("#editEtcForm").attr("target", "_self");
				$("#editEtcForm").submit();
			}
			//}
		}).css("cursor", "pointer");	
		
		// 검증라이센스 발급 노출 Flag
		if('${content.drmYn}' != 'Y') {
			$('#licenseBtn').hide();
		}
		
		updateFns = {
				checkupdate : function() {
	                  if (isNull($("#updateText").val()) || $("#updateText").val().indexOf(updateText) > -1) return false;
	                  else return true;
	            }
		};
	});
	
	function FileUploadCallBack(data, statusText) {

		if (statusText == "success") {
			if (data.length > 100) {
				$('div[name=divBtnArea]').remove();
				$('div[name=divSubContent]:last').html(data);
			} else if (data.length < 100 && data.length > 0){
				alert(data);
				$('div[name=divSubContent]:last input[name=tempRunUpload]').val('');
				$('div[name=divSubContent]:last input[name=tempRunUpload]').empty();
				$('div[name=divSubContent]:last').html($('div[name=divSubContent]:last').html());
			}
		}
	}
	  
	function submitConfirm(objForm, objFile, objChkBox)  {
	
		var selectedLcdCnt = $("#" + objChkBox + " input:checkbox[name=subContent\\.provisionItem]:checked").length;
	
		if (selectedLcdCnt > 0) 
			
			if ($("#"+objFile).val() == null || $("#"+objFile).val() == '') {
				alert("<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent01' />");  
				return false;
			} else {
				return true;
			}
		else {
			alert("<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent02'/>");
			return false;
		}
	}
	
	function uploadRunFile(objFrm, objFile, objChkBox) {
		
		if(submitConfirm(objFrm, objFile, objChkBox)) {
			options = {
					//beforeSubmit:  submitConfirm,
					success : 	FileUploadCallBack,
					error	:	FileUploadCallBack,
					dataType:   "html",
					 cache	: false,	
					url		: "<%= request.getContextPath() %>/content/ajaxReadManifestXML.omp",
					type	: "post"
			};
			
			$('#' + objFrm).attr("target", "_self");	
			$('#' + objFrm).ajaxSubmit(options);
		} else return;
	}
	
	
	// File Upload, remove Upload File
	function setUploadFileNameCheck( fileObj, id, extCode )	{
		var frm = fileObj.form;

		//var fname=document.all.myfile.value;
		var imgNmLength = fileObj.value;
		var arrImg=("file:///"+imgNmLength.replace(/ /gi,"%20").replace(/\\/gi,"/")).split("/");
		var imgNm = arrImg[arrImg.length-1];

		//alert(imgNmLength.getByteLength() + " :::: " + imgNmLength);
		if(imgNm.getByteLength() > 100){
			alert('<gm:string value="jsp.content.contentDetailInfo.msg.file01"/>');
			$(fileObj).parent().html($(fileObj).parent().html());
			return;
		}

		if ( !isExt( fileObj.value, extCode ) )	{
			alert('<gm:string value="jsp.content.contentDetailInfo.msg.file02"/>');
			$(fileObj).parent().html($(fileObj).parent().html());
			return;
		}

		var index = fileObj.value.lastIndexOf("\\");

		if ( index > -1 )	{
			$("#"+id).val(fileObj.value.substring(index+1));
		}	else	{
			$("#"+id).val(fileObj.value);
		}
		
	}
	
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 바이너리 등록 확인(등록/수정)
	 */		
	function subContentSave(objFrm, objIndex) {
		
		// lcd, sprtPhone 조회 Validation check 할 것
		
		if (confirm("<gm:string value='jsp.content.contentAndroidDevInfo.btn.save02'/>")) {

			var options = {
					beforeSubmit: function() {
						// lcd, sprtPhone 조회 Validation check
						var selectedLcdCnt = $("input:checkbox[name=subContent\\.provisionItem]:checked").length;
						var selectedPhoneCnt = $("input[name=sprtPhoneModel" + objIndex + "]:checked").length;

						if (selectedLcdCnt <= 0 ) {
							alert("<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent02'/>");
							return false;
						}
						
						if (selectedPhoneCnt <=0 ) {
							alert("<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent03'/>");
							return false;
						}
					},
					dataType:   "html",
					success : 	createSubContentsResult,
					error	:	function(xhr, textStatus, errorThrown){},
					type	: "post"
			};   

			var sprtPhone = "";
			$("input[name=sprtPhoneModel" + objIndex + ']:checked').each(function() {
				sprtPhone += $(this).val() + ",";
			});

			$("input[name=subContent\\.sprtPhoneModel]").val(sprtPhone);

			$('#' + objFrm).attr("target", "_self");
			$('#' + objFrm).attr("action", "ajaxModifySubContent.omp");
			$('#' + objFrm).ajaxSubmit(options);
		} else { 
			return; 
		}

	} 

	/**
	 * 바이너리 등록 확인(등록/수정) callback Function
	 */	
	function createSubContentsResult(data, statusText) {

		if (statusText != "error") {
			
			alert("<gm:string value='jsp.content.common.msg.result.success'/>");
			
			$('#binaryList').html(data);
			
			$('#verifyReqALink').html('<img src="'+ env.contextPath + '/${ThreadSession.serviceLocale.language}/images/pm/btn_verify.gif" alt="검증요청" id="verifyReqBtn"/>');
			
		} else {
			
			alert("<gm:string value='jsp.content.common.msg.result.fail'/>");
		}
	}
	
	/**
	 * 바이너리 삭제
	 */
	function removeRunFile(objFrm, formIndex) {
		//$('#' + objFrm).remove();
		if (confirm("<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent04'/>")) {
			$('#' + objFrm).attr("target", "_self");	
			$('#' + objFrm).attr("action", "ajaxRemoveRunFile.omp");
			$('#' + objFrm).ajaxSubmit(
					function(data, statusText) {
						if (statusText != "error") {	
							alert("<gm:string value='jsp.content.common.msg.result.success'/>");
							$('#binaryList').html(data);	
						} else {						
							alert("<gm:string value='jsp.content.common.msg.result.fail'/>");
						}
					}			
			);
		} else {
			return;
		}
	}
	
	/**
	 * 중복 선택 대상단말 체크
	 */
	function alramSprtPhoneCnt(obj, objFrm) {
		
		options = {
				dataType: "json",
				type	: "post",
				error   : function(xhr, textStatus, errorThrown){},
				success : function(responseText, statusText) {
					if (parseInt(responseText.sprtPhoneModelCnt) > 0) {
						alert("<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent05'/>");
						$(obj).attr("checked", "");
					}
				}
		};  

		$("input[name=subContent\\.sprtPhoneModel]").val($(obj).val());
		$('#' + objFrm).attr("target", "_self");	
		$('#' + objFrm).attr("action", "ajaxCheckSprtPhone.omp");
		$('#' + objFrm).ajaxSubmit(options);
	}
	
	/**
	 * 대상단말 전체 선택
	 */
	function allSelectCheckBox(obj, objFrm, objIndex) {

		var chkedFlag = $(obj).is(":checked");

		if (chkedFlag) {

			$('input:checkbox[name=sprtPhoneModel' + objIndex + ']').each(function() {
				this.checked = true;
				//$(obj).checked = true;
			});
		} else {
		
			$('input:checkbox[name=sprtPhoneModel' + objIndex + ']').each(function() {
				this.checked = false;
				//$(obj).checked = true;
			});
		}

		ajaxCheckSprtPhone(obj, objFrm, objIndex);
	}  

	/**
	 * 대상단말 전체 선택 시 중복 선택 대상단말 체크
	 */
	function ajaxCheckSprtPhone(obj, objFrm, objIndex) {

		options = {
				dataType: "json",
				type	: "post",
				error   : function(xhr, textStatus, errorThrown){},
				success : function(responseText, statusText) {
					if (parseInt(responseText.sprtPhoneModelCnt) > 0) {
						
						alert("<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent05'/>");
						
						$("#" + obj.id).attr("checked", "");
						$("input:checkbox[name=sprtPhoneModel" + objIndex + "]").each(function() {
							this.checked = false;
						});
					}
				}
		};  
		
		
		var sprtPhone = "";
		$("input[name=sprtPhoneModel" + objIndex + ']:checked').each(function() {
			sprtPhone += $(this).val() + ",";
		});
		
		$("input[name=subContent\\.sprtPhoneModel]").val(sprtPhone);
		
		$('#' + objFrm).attr("target", "_self");	
		$('#' + objFrm).attr("action", "ajaxCheckSprtPhone.omp");
		$('#' + objFrm).ajaxSubmit(options);

	}

	// 바이너리 LCD Size click시 search Phone ajaxList Refresh
	function selectLcdSize(obj, objFrm, objIndex) {
		var chekedCnt = $(":checkbox[name=" + obj.name + "]:checked").length;
		alert("<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent06'/>");
		searchSprtPhone(objFrm, objIndex);
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 개발자 ARM 라이센스 다운로드
	 */	
	function licenseForDeveloper(cid){

		if(confirm("<gm:string value='jsp.content.contentAndroidDevInfo.btn.license'/>"))
		{
			var url = env.contextPath + "/content/licenseForDeveloper.omp";
			var param = {
				"content.cid" : cid
			};		

			$.postJSON(url, param, function(data){
				
				if(data.resultCode == 'error')
				{  
					alert("<gm:string value='jsp.content.contentAndroidDevInfo.msg.license'/>");
				} else if(data.resultCode == 'success') {
					location.href = env.contextPath + "/content/licenseForDeveloperDownload.omp?content.cid="+cid;
				}
			});		
		}
	}
/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * 업데이트 내용 value 체크
	 */
	function checkUpdateText(gbn) {

		var result = showValidate('editUpdateForm', 'default', '<gm:string value="jsp.content.contentAndroidDevInfo.msg.title03"/>', updateFns);
		
		if(result) {
			if ("I" == gbn) {					// 등록
				ajaxUpdateManagementWrite();	
			} else if ("U" == gbn) {			// 수정
				$("#updateInputLink").attr("href", "javascript:checkUpdateText('I');");
				ajaxUpdateManagementUpdate();		
			}
		}else {
			return;
		}
	}

	/**
	 * 업데이트 수정 버튼 클릭
	 */
	function selectUpdateText(seq, value) {
		
		$('#updateText').val(value);
		$('#updateSeq').val(seq);
		$('#updateText').focus();
		
		$("#updateInputLink").attr("href", "javascript:checkUpdateText('U');");
	}

	/**
	 * 업데이트 삭제 버튼
	 */
	function deleteUpdateText(seq) {

		if(confirm("<gm:string value='jsp.content.contentAndroidDevInfo.btn.update01'/>")) {
			if (isNull(seq)) {
				alert("<gm:string value='jsp.content.contentAndroidDevInfo.msg.update02'/>");
				return false;
			}
			
			$('#updateSeq').val(seq);
			ajaxUpdateManagementDelete();		// 삭제
		}
	}

	/**
	 * 업데이트 input Text setting
	 */
	function inputCheck(type) {
		
		if(type=='1' && $( "#updateText" ).val() == updateInitText) {
			$("#updateText").val('');
			$("#updateText").empty();
		}
		else if(type=='2') {
			$("#updateText").empty();
			$("#updateText").val(updateInitText);
		}
	}

	/** 
	 * 업데이트 이력 등록
	 */
	function ajaxUpdateManagementWrite(){

		 options = { 
	         success:       showUpdateListResponse,  // post-submit callback 
	         error:			showUpdateListResponse,
	         // other available options: 
	         dataType : "html",
	        // url:       env.contextPath + "/content/ajaxUpdateManagementWrite.omp",         // override for form's 'action' attribute 
	         type:      "post"        // 'get' or 'post', override for form's 'method' attribute 
	        	
		 }; 	

		 $('#editUpdateForm').attr("target", "_self");	
		 $('#editUpdateForm').attr("action", "ajaxUpdateManagementWrite.omp");
			
		 $('#editUpdateForm').ajaxSubmit(options);
	}
	/**
	 * 업데이트 이력 수정
	 */
	function ajaxUpdateManagementUpdate(){
		
		
		 var options = { 
		         success:       showUpdateListResponse,  // post-submit callback 
		         error:			showUpdateListResponse,
		         // other available options: 
		         type:      "post"        // 'get' or 'post', override for form's 'method' attribute 
		     }; 	
	 
		 $('#editUpdateForm').attr("target", "_self");	
		 $('#editUpdateForm').attr("action", "ajaxUpdateManagementUpdate.omp");
			
		 $('#editUpdateForm').ajaxSubmit(options);
	}

	/** 
	 * 업데이트 이력 삭제
	 */
	function ajaxUpdateManagementDelete(){


		 var options = { 
		         success:       showUpdateListResponse,  // post-submit callback 
		         error:			showUpdateListResponse,
		         // other available options: 
		         type:      "post"        // 'get' or 'post', override for form's 'method' attribute 
		    
		 	}; 	

		 $('#editUpdateForm').attr("target", "_self");	
		 $('#editUpdateForm').attr("action", "ajaxUpdateManagementDelete.omp");
			
		 $('#editUpdateForm').ajaxSubmit(options);

	}

	/** 
	 * 업데이트 이력 등록/수정/삭제 post-submit callback 
	 */
	function showUpdateListResponse(data, statusText)  { 
		$('#idUpdateListDiv' ).html(data);
		inputCheck('2');
	} 

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
//-->
</script>
<style>							
	.fileinputs {position: relative; overflow: hidden; height: 24px; width: 600px;}
	.fileinputs * {vertical-align: middle;}
	.fakefile {position: absolute; top:0px; left:0px; height: 30px; z-index: 1; }
	.inputFile {position: relative; text-align: right; top: -12px; width: 488px; height:40px; filter: alpha(opacity=0); opacity:0; z-index: 2; direction: ltl; selector-dummy: expression(this.hideFocus=true);}
</style>   

<div id="contents_area">
<form id="editForm" name="editForm">
<input type="hidden" id="cid" name="content.cid"	value="${resultMap.cid}" />
<input type="hidden" name="subContent.cid"	value="${resultMap.cid}" />
<input type="hidden" id="redirectUrl" 	name="redirectUrl" 	value=""/>
<input type="hidden" id="tabGbn" 		name="tabGbn" 		value="${tabGbn}"/>
</form>
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 상품등록/관리 &gt; 상품관리 <strong>&gt;</strong> <span>상품현황</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title03.gif'/>" alt="상품현황" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		
		<!-- include contentBaseInfo -->
		<jsp:include page="contentBaseInfo.jsp"/>
		<!-- //Tab_menu E -->
		
		<h4 class="h41 fltl">
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_10.gif'/>" alt="바이너리 파일 정보" />
			<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
				<div class="helpbox">
				<div class="helpboxin">
					<p>LCD Type별로 최적화된 Apk 를 복수개 등록할 수 있습니다.</p>
				</div>
				</div>
			</a>
		</h4>

		<span id="binaryList">
		<c:choose>
			<c:when test="${resultMap.subContentsCnt > 0}">
				<c:forEach items="${resultMap.subContsList}" var="subContent" varStatus="listIndex">	

					<div class="txtr mar_b5" name="divBtnArea" id="divBtnArea${listIndex.index}">
						<a href="javascript:appendFormDiv('editForm${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_add.gif'/>" name="appendSubContentBtn" alt="추가" /></a>
					</div>
					
					<div class="tstyleA mar_b22" id="divSubContent${listIndex.index}" name="divSubContent">
					<form id="editForm${listIndex.index}" name="editForm${listIndex.index}"  method="post">
						<input type="hidden" name="subContent.cid"	value="${resultMap.cid}" />
						<input type="hidden" name="subContent.scid"	value="${subContent.scid}"  id = "scid${listIndex.index}"/>
						<input type="hidden" name="subContent.runFile.runFilePos"	value="${subContent.runFilePos}" />
						<input type="hidden" name="subContent.runFile.runUploadFileName"	value="${subContent.runFileNm}" />
						<input type="hidden" name="subContent.sprtPhoneModel" value="" />
						
					<table summary="바이너리 파일정보 입력">
						<caption>바이너리 파일정보 입력</caption>
						<colgroup>
							<col width="21%" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><span>*</span> LCD Size(복수선택가능)</th>
								<td> 
								<c:forEach items="${resultMap.lcdSizeChkBox}" var="lcdSize" varStatus="lcdListIndex">
									<input type="checkbox"" name="subContent.provisionItem" value="${lcdSize.dtlFullCd}" 
										<c:forEach items="${resultMap.provisionItemsList}" var="lcdSizeInfo" varStatus="privisionListIndex">		
											<c:if test="${lcdSizeInfo.scid == subContent.scid && lcdSizeInfo.itemCd == lcdSize.dtlFullCd}">checked="checked"</c:if>
										</c:forEach>
										onclick="javascript:selectLcdSize(this, 'editForm${listIndex.index}', '${listIndex.index}');"/>
										<label for="lcd_1">&nbsp;<gc:text code="${lcdSize.dtlFullCd}"/></label>&nbsp;&nbsp;
								</c:forEach>		
								</td>
							</tr>
							<tr>
								<th scope="row" rowspan="2"><span>*</span> 바이너리 파일등록</th>
								<td>
									<span class="txtcolor04">${subContent.runFileNm}</span> &nbsp;
									<a href="javascript:removeRunFile('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="삭제" /></a>
								</td>
							</tr>
							<tr>
								<td class="lh160">
									minSdkVersion : ${subContent.vmVerMin}<br />
									targetSdKVersion : ${subContent.vmVerTarget}<br />
									maxSdkVersion : ${subContent.vmVerMax}<br />
									versionCode : ${subContent.versionCode}<br />
									versionName : ${subContent.versionName}<br />
									package : ${subContent.pkgNm}<br />
								</td>
							</tr>
							<tr  id="sprtPhoneListTr${listIndex.index}">
								<th class="tit01 tit03">적용단말 선택</th>
								<td class="bgnone">
									등록된 바이너리 파일 정보를 기준으로 서비스대상 단말을 선택할 수 있습니다.&nbsp;
									<a href="javascript:searchSprtPhone('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_dmsearch.gif'/>" alt="단말검색" /></a>
									<div class="tstyleC mar_t10">
										<table summary="단말명칭,모델명,지원OS,LCD Size" class="w577 bbnone">
											<caption>단말명칭,모델명,지원OS,LCD Size</caption>
											<colgroup>
												<col width="7%" />
												<col width="18%" />
												<col width="25%" />
												<col width="18%" />
												<col />
											</colgroup>
											<thead>
											<tr>
												<th scope="col" class="tit06 btnone"><input type="checkbox" id="allSelPhoneChkBox${listIndex.index}"  disabled="disabled" checked="checked" onclick="javascript:allSelectCheckBox(this, 'editForm${listIndex.index}');"/></th>
												<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit21.gif'/>" alt="단말명칭" /></th>
												<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit22.gif'/>" alt="모델명" /></th>
												<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit23.gif'/>" alt="지원OS" /></th>
												<th scope="col" class="tit06 btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit24.gif'/>" alt="LCD Size" /></th>
											</tr>
											</thead>
										</table>
									</div>
									<div class="tstyleC hl185" style="overflow-x:hidden;">
										<table summary="적용단말 선택" class="w577">
											<caption>적용단말 선택</caption>
											<colgroup>
												<col width="7%" />
												<col width="18%" />
												<col width="25%" />
												<col width="18%" />
												<col />
											</colgroup>
											<tbody>
											<c:choose>
												<c:when test="${not empty resultMap.targetPhoneList}">
													<c:forEach items="${resultMap.targetPhoneList}" var="phone" varStatus="phonelistIndex">	
														<c:choose>
															<c:when  test="${subContent.scid == phone.scid}">
																<tr>
																	<td><input type="checkbox" checked="checked" disabled="disabled" /></td>
																	<td>${phone.modelNm}</td>
																	<td>
																		<a href="javascript:gotoPhoneModelInfoList('${phone.mgmtPhoneModelNm}');">
																		${phone.mgmtPhoneModelNm}
																		</a>
																	</td>
																	<td><gc:text code="${phone.osVersion}"/></td>
																	<td class="brnone"><gc:text code="${phone.lcdSize}"/></td>
																</tr>
															</c:when>
														</c:choose>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<tr>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td class="brnone">&nbsp;</td>
													</tr>
												</c:otherwise>
											</c:choose>
											</tbody>
										 </table>
									 </div>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="tit02">
									<a href="javascript:subContentSave('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_ok.gif'/>" alt="확인" /></a>
									<a href="javascript:subContentReset('editForm${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_cancle.gif'/>" alt="취소" /></a>
								</td>
							</tr>
						</tbody>
					</table>
					</form>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise> 
				
				<div class="tstyleA mar_b22" id="divSubContent${resultMap.subContentsCnt}" name="divSubContent">
				<form id="editForm${resultMap.subContentsCnt}" name="editForm${resultMap.subContentsCnt}"  method="post" enctype="multipart/form-data">
				<input type="hidden" name="subContent.cid"	value="${content.cid }" />
				<input type="hidden" name="subContent.scid"	value="${subContent.scid }" id = "scid${resultMap.subContentsCnt}" />
				
				<table summary="바이너리 파일정보 입력">
					<caption>바이너리 파일정보 입력</caption>
					<colgroup>
						<col width="21%" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row"><span>*</span> LCD Size(복수선택가능)</th>
							<td>
								<label for="lcd_1" id="label${resultMap.subContentsCnt}"><gc:checkBoxs name="subContent.provisionItem" group="${CONST.PHONE_LCD_SIZE}" codeType="full" divide=" &nbsp;&nbsp; " split="&nbsp;"/></label>
							</td>		
						</tr>
						<tr>
							<th scope="row" class="tit01"><span>*</span> <label for="fileupload">바이너리 파일등록</label></th>
							<td class="bgnone">
								<div class="fileinputs" id="uploadDiv1bin0"  style="display:${empty subContent.runFile.runFilePos ? 'box':'none' };" >
									<span><input type="file" class="inputFile" id="1bin0" name="subContent.runFile.runUpload"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempRunUpload${resultMap.subContentsCnt}','${CONST.FILEEXT_ANDROID_BIN}');" accept="${CONST.FILEEXT_ANDROID_BIN}" style="cursor: pointer;" onkeydown="this.blur();" /></span>
									<div class="fakefile">
										<input type="text" id="tempRunUpload${resultMap.subContentsCnt}" name="tempRunUpload"  value="" class="w410" disabled="disabled" readonly/>
										<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="첨부파일" /></a>
										
										<a href="javascript:uploadRunFile('editForm${resultMap.subContentsCnt}', 'tempRunUpload${resultMap.subContentsCnt}', 'label${resultMap.subContentsCnt}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_upload.gif'/>" alt="업로드" id="addBinaryBtn" /></a>
					
									</div>
								</div>
								
							</td>
						</tr>
					</tbody>
				</table>
				</form>
				</div>
			</c:otherwise>
		</c:choose>
		</span>
	
		

		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_11.gif'/>" alt="검증 추가 정보" /></h4>
		<div class="tstyleA mar_b22">
		<form id="editEtcForm" name="editEtcForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="content.cid"	value="${resultMap.cid}" />
		<input type="hidden" id="drmSetOpt" name="content.drmSetOpt" value="${content.drmSetOpt}" />
			<table summary="검증추가정보 입력">
				<caption>검증추가정보 입력</caption>
				<colgroup>
					<col width="21%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> Application DRM
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
								<div class="helpboxin">
									<p>T store에서 제공하는 ARM Library의 적용 유무를 선택하여 주세요. ARM Library에 대한 자세한 내용은 ‘개발지원가이드’를 통해 확인할 수 있습니다.</p>
								</div>
								</div>
							</a>
						</th>
						<td>
							<input type="radio" id="drmYnY" name="content.drmYn" value="Y" <c:if test='${content.drmYn == "Y"}'>checked</c:if> <c:if test="${content.saleStat != CONST.CONTENT_SALE_STAT_NA}">disabled='true'</c:if> onclick="javascript:selectDrmYn('Y');"/><label for="radio1" > <gm:string value='jsp.content.contentAndroidDevInfo.lbl.license01'/></label>&nbsp;&nbsp;
							<input type="radio" id="drmYnN" name="content.drmYn" value="N" <c:if test='${content.drmYn != "Y"}'>checked</c:if> <c:if test="${content.saleStat != CONST.CONTENT_SALE_STAT_NA}">disabled='true'</c:if> onclick="javascript:selectDrmYn('N');" /><label for="radio2"> <gm:string value='jsp.content.contentAndroidDevInfo.lbl.license02'/></label>
							<br />
							<span class="txt_90" id="licenseBtn">ARM 동작 유무를 자체적으로 테스트하기 위해 라이선스를 발급 받으세요.&nbsp;<a href="javascript:licenseForDeveloper('${content.cid}')"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_lidown.gif'/>" alt="라이센스 다운로드" /></a></span>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="manual">이용매뉴얼</label> 
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
								<div class="helpboxin">
									<p>효율적인 상품 검증을 위해 사용되는 정보이며, 상품 내용을 쉽고, 직관적으로 기술해 주세요.</p>
								</div>
								</div>
							</a>
						</th>
						<td class="bgnone">
							<div class="fileinputs"  id="divScnrVerifyFile"   style="display:${empty content.verifyScnrFile?'box;':'none;' }" >
								<span><input type="file" class="inputFile"  id="verifyScnrUpload" name="content.verifyScnrUpload"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempScnrUploadFile','verifyDocu');" style="cursor: pointer;"/></span>
								<div class="fakefile">
									<input type="text" id="tempScnrUploadFile" name="tempScnrUploadFile" class="w410" disabled="disabled" readonly  value="파일형식 : doc, docx, xls, xlsx, ppt, pptx, pdf"/>
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_filesearch.gif'/>" alt="파일찾기" /></a>
									
								</div>
								
								<c:if test='${empty content.verifyScnrFile}'>
									<input type="hidden" id="verifyScnrUploadDelYn" name="content.verifyScnrUploadDelYn" value="Y"/> 
								</c:if>
							</div>
							<c:if test='${not empty content.verifyScnrFile}'>
								<div id="divScnrVerifyInfo">
									<span class="txtcolor04">${content.verifyScnrFileNm}</span> &nbsp;
									<input type="hidden" id="verifyScnrUploadDelYn" name="content.verifyScnrUploadDelYn" value=""/>
									<a href="javascript:removeVerifyScnrFile('divScnrVerifyInfo', 'divScnrVerifyFile', 'verifyScnrUploadDelYn');">
										<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" alt="삭제하기" />
									</a>	
									
								</div>
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
		</form>	
		</div>
		
		<!-- 검증 이력 관리 -->
		<IFRAME src="<c:url value='/content/iFrameVerifyCommentList.omp?content.cid=${content.cid }'/>" id="Verify" frameBorder=0 width=100% scrolling=no marginwidth=0 marginheight=10 height="175px"></iframe>
		
		<!-- 업데이트 이력 관리 -->
		<form id="editUpdateForm"  name="editUpdateForm" method="post">
		<input type="hidden" name="contentUpdate.cid"	value="${content.cid}" />
		<input type="hidden" name="contentUpdate.updateSeq"	value="" id="updateSeq" />
		<div id="idUpdateListDiv">
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_12.gif'/>" alt="업데이트 이력 관리" />
			<a href="#" class="help zindex2"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
				<div class="helpbox">
				<div class="helpboxin">
					<p>판매하는 상품에 대한 업데이트 이력을 관리할 수있습니다.</p>
				</div>
				</div>
			</a>
		</h4>
		<div class="tstyleC">
			<table summary="적용일자,등록일자,내용,수정/삭제" class="w792 bbnone">
				<caption>적용일자,등록일자,내용,수정/삭제</caption>
				<colgroup>
					<col width="18%" />
					<col width="18%" />
					<col />
					<col width="18%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit15.gif'/>" alt="적용일자" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit16.gif'/>" alt="등록일자" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit17.gif'/>" alt="내용" /></th>
						<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit18.gif'/>" alt="수정/삭제" /></th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tstyleC h191" style="overflow-x:hidden;">
			<table summary="업데이트 이력관리" class="w792 wbreak">
				<caption>업데이트 이력관리</caption>
				<colgroup>
					<col width="18%" />
					<col width="18%" />
                    <col />
                    <col width="18%" />
				</colgroup>
				<tbody>
				<c:choose>
					<c:when test="${not empty resultContentUpdate }">
						<c:forEach items="${resultContentUpdate}" var="contentUpdate">
							<c:if test="${contentUpdate.contsUpdDtNum == 1}">
							<tr>
								<td rowspan="${contentUpdate.contsUpdDtRow}">${contentUpdate.contsUpdDt}</td>
								<td>${contentUpdate.insDttm}</td>
								<td class="tit01 wbtd">${contentUpdate.updateText}</td>
								<td class="brnone">
									<c:if test="${fn:trim(content.verifyPrgrYn) eq 'PD005399'  or fn:trim(content.verifyPrgrYn) eq 'PD005303'}">
										<a href="javascript:selectUpdateText('${contentUpdate.updateSeq}', '${contentUpdate.updateText}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif'/>" alt="수정"/></a> 
										<a href="javascript:deleteUpdateText('${contentUpdate.updateSeq}');""><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="삭제"/></a>
									</c:if>
									<c:if test="${fn:trim(content.verifyPrgrYn) ne 'PD005399' and fn:trim(content.verifyPrgrYn) ne 'PD005303'}">
										<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update04"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif'/>" alt="수정"/></a> 
										<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update05"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="삭제"/></a>
									</c:if>
								</td>
							</tr>
							</c:if>
							<c:if test="${contentUpdate.contsUpdDtNum != 1}">
							<tr>
								<td>${contentUpdate.insDttm}</td>
								<td class="tit01 wbtd">${contentUpdate.updateText}</td>
								<td class="brnone">
									<c:if test="${fn:trim(content.verifyPrgrYn) eq 'PD005399'  or fn:trim(content.verifyPrgrYn) eq 'PD005303'}">
										<a href="javascript:selectUpdateText('${contentUpdate.updateSeq}', '${contentUpdate.updateText}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif'/>" alt="수정"/></a> 
										<a href="javascript:deleteUpdateText('${contentUpdate.updateSeq}');""><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="삭제"/></a>
									</c:if>
									<c:if test="${fn:trim(content.verifyPrgrYn) ne 'PD005399' and fn:trim(content.verifyPrgrYn) ne 'PD005303'}">
										<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update04"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif'/>" alt="수정"/></a> 
										<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update05"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="삭제"/></a>
									</c:if>
								</td>
							</tr>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr><td colspan="4"><gm:string value='jsp.content.contentAndroidDevInfo.text.update.list02' /></td></tr>
						<tr><td colspan="4">&nbsp;</td></tr>
						<tr><td colspan="4">&nbsp;</td></tr>
						<tr><td colspan="4">&nbsp;</td></tr>
						<tr><td colspan="4">&nbsp;</td></tr>
					</c:otherwise>
				</c:choose>	
				</tbody>
			</table>
		</div>
		</div>
		<div class="update mar_b22">
			<input type="text" class="w692" value="<gm:string value='jsp.content.contentAndroidDevInfo.msg.update03' />" id="updateText" name="contentUpdate.updateText" onfocus="javascript:inputCheck('1');"  v:checkupdate m:checkupdate="<gm:tagAttb value='jsp.content.contentAndroidDevInfo.msg.update03'/>"  v:text8_limit="300" m:text8_limit="<gm:tagAttb value='jsp.content.contentAndroidDevInfo.msg.update06'/>" />
			<a href="javascript:checkUpdateText('I');" id="updateInputLink"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_submitl.gif'/>" alt="등록" /></a>
		</div>
		</form>
	
		<div class="btnarea mar_t30">
			<img id="btnMod" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_save.gif'/>" alt="저장" />
			<img id="btnCancel" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="취소" />
			<p class="btn"><img id="btnList" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_list.gif'/>" alt="목록" /></p>
		</div>
	</div>
	<!-- //CONTENT TABLE E-->
	
</div>
	

