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
			checkverifydoc : function() {
				
				try{
					if ('${content.verifyScnrFileNm}' != '') {	
						
						if ($('#divScnrVerifyFile').attr('style').replace(' ' , '').replace(';' , '').toLowerCase() == 'display:none;'.replace(';' , '')) return true;
						else {
							if($('#tempScnrUploadFile').val() != '') return true;  
							else return false;
						}
					} else {	
						
						if ($('#verifyScnrUpload').val() != '') return true;
						else return false;
					}
				}catch(e){
					if ($('#tempScnrUploadFile').val() != verifyScnText) return true;
					else return false;
				}
			}
		};

		$("#listBtn").click(function() {
			var frm = $('#editForm');
			frm.attr("target", "_self");	
			frm.attr("action","./contentsStatusList.omp");
			frm.submit();
		}).css("cursor", "pointer");
	
		$("#btnCancel").click(function() {
			location.href = env.contextPath + "/content/contentDevInfoView.omp?content.cid=" + '${resultMap.cid}';
		}).css("cursor", "pointer");
	
		$("#btnMod").click(function() {
			if(confirm("<gm:string value='jsp.content.contentAndroidDevInfo.btn.save01'/>")) {
				$("#editEtcForm").attr("action", "modifyContentDevInfo.omp");
				$("#editEtcForm").attr("target", "_self");
				$("#editEtcForm").submit();	
			}
		}).css("cursor", "pointer");	
		
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
	
	function ajaxSubContsCnt(cid) {
		
		var resultValue = false;
		
		$.ajax({
	        type: "GET",
	        async : false,
	        url: env.contextPath + "/content/ajaxGetSubContentsCount.omp?content.cid=" + cid,
	        dataType:  "json",
	        success: function(responseText, statusText) {
	       
	        	if(parseInt(responseText.subContentsCnt) > 0) {
	        		resultValue = true;
	        	} else {
	        		resultValue = false;
	        	}
	        	
	        },
	        error: function(xhr, textStatus, errorThrown){}
	    });
		
		return resultValue;
	}
	
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
	
	function uploadRunFile(objFrm, objFile, objChkBox, objIndex) {
		
		if(submitConfirm(objFrm, objFile, objChkBox)) {
			options = {
					//beforeSubmit:  submitConfirm,
					async : false,
					success : 	function (data, statusText) {
						
							if (statusText == "success") {
								
								if (data.length > 100) {
									$('.txtr').hide();
									//$('div[name=divSubContent]:last').html(data);
									$('div#divSubContent' + objIndex).html(data);
									$("#delYn" + objIndex).val("N");
								} else if (data.length < 100 && data.length > 0){
									alert(data);
									$('div[name=divSubContent]:last input[name=tempRunUpload]').val('');
									$('div[name=divSubContent]:last input[name=tempRunUpload]').empty();
									$('div[name=divSubContent]:last').html($('div[name=divSubContent]:last').html());
								}
							}
						},
					error	:	function (data, statusText) {
							if (statusText == "success") {
								if (data.length > 100) {
									//$('div[name=divBtnArea]').remove();
									$('.txtr').hide();
									$('div#divSubContent' + objIndex).html(data);
								} else if (data.length < 100 && data.length > 0){
									alert(data);
									$('div[name=divSubContent]:last input[name=tempRunUpload]').val('');
									$('div[name=divSubContent]:last input[name=tempRunUpload]').empty();
									$('div[name=divSubContent]:last').html($('div[name=divSubContent]:last').html());
								}
							}
						},
					dataType:   "html",
					 cache	: false,	
					url		: "<%= request.getContextPath() %>/content/ajaxReadManifestXML.omp",
					type	: "post"
			};
		
			$('#modifySubContentIndex' + objIndex).val(objIndex);	
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
	function subContentSave(objFrm, objIndex) {
		
		if($("#delYn" + objIndex).val() != "Y")  {
			
			if (confirm("<gm:string value='jsp.content.contentAndroidDevInfo.btn.save02'/>")) {

				var options = {
						async : false, 
						beforeSubmit: function() {
							
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
		
		} else {
			
			if (confirm("<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent04'/>")) {
				
				var param = $('#' + objFrm).serialize();
				
				$.ajax({
			        type: "POST",
			        async : false,
			        url: env.contextPath + "/content/ajaxRemoveRunFile.omp",
			        data : param,
			        dataType:  "html",
			        success: removeRunFile,
			        error: function(xhr, textStatus, errorThrown){}
			    }); 

				
			} else {
				return;
			}
		}
		
		

	} 

	function createSubContentsResult(data, statusText) {
	
		if (statusText != "error") {
			
			alert("<gm:string value='jsp.content.common.msg.result.success'/>");
			
			$('#binaryList').html(data);
			
			$('#verifyReqALink').html('<img src="'+ env.contextPath + '/${ThreadSession.serviceLocale.language}/images/pm/btn_verify.gif" alt="請審" id="verifyReqBtn"/>');
			
		} else {
			
			alert("<gm:string value='jsp.content.common.msg.result.fail'/>");
		}
	}
	
	function removeRunFile(data, statusText) {

		if (statusText != "error") {	
			alert("<gm:string value='jsp.content.common.msg.result.success'/>");
			$('#binaryList').html(data);	
		} else {						
			alert("<gm:string value='jsp.content.common.msg.result.fail'/>");
		}
					
	}
	
	function removeTempRunFile(objFrm, formIndex) {
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
	
	function removeFormRunFile(objFrm, objIndex) {

		var fileHtml = "";
		//var index = parseInt(objIndex) - 1;
		var index = 	objIndex;
		
		fileHtml += "<th scope='row' class='tit01'><span>*</span> <label for='fileupload'>上傳Binary 檔</label></th>";
		fileHtml += "<td class='bgnone'>";
		fileHtml += "	<div class='fileinputs' id='uploadDiv1bin0'>";
		fileHtml += "		<span><input type='file' class='inputFile' id='1bin0' name='subContent.runFile.runUpload'  maxlength='1' onchange='javascript:setUploadFileNameCheck(this,\"tempRunUpload";
		fileHtml += 			index;
		fileHtml += "\",\"${CONST.FILEEXT_ANDROID_BIN}\");' accept='${CONST.FILEEXT_ANDROID_BIN}' style='cursor: pointer;' onkeydown='this.blur();' /></span>";
		fileHtml += "		<div class='fakefile'>";
		fileHtml += "			<input type='text' id=\"tempRunUpload" 
		fileHtml +=  			index;
		fileHtml += "\" name='tempRunUpload'  value='' class='w410' />";
		fileHtml += "			<a href='#'><img src=\"<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif' />\" alt='attachement file' /></a>";		
		fileHtml += "			<a href='javascript:uploadRunFile(\"editForm";
		fileHtml += 			index;
		fileHtml += "\", \"tempRunUpload";
		fileHtml += 			index;
		fileHtml += "\", \"label";
		fileHtml += 			index;
		fileHtml += "\", ";
		fileHtml += 			index;
		fileHtml += ");'><img src=\"<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_upload.gif' />\" alt='upload' id='addBinaryBtn' /></a>";
		fileHtml += "		</div>";
		fileHtml += "	</div>";
		fileHtml += "</td>";

		$("#binaryFileTr" + objIndex).html(fileHtml);
		$("#binaryInfoTr" + objIndex).remove();
		$("#sprtPhoneListTr" + objIndex).remove();
		//$('.txtr').each(function () {
    	//	$(this).hide(); 
    	//});
		$('.txtr').hide();
		$("#delYn" + objIndex).val("Y");

	}
	
	function devContsPageReset(objFrm, objIndex) {
		//location.reload();
		if($("#scid" + objIndex).val() != "" && $("#scid" + objIndex).val() != null) {
			
			$('#modifySubContentIndex' + objIndex).val(objIndex);	
			
			var param = $('#' + objFrm).serialize();
			
			$.ajax({
				
		        type: "POST",
		        async : false,
		        url: env.contextPath + "/content/ajaxModifySubContentCancel.omp",
		        data : param,
		        dataType:  "html",
		        success: function (data, statusText) {
		        	$('#divSubContent' + objIndex).html(data);
		        	$('.txtr').show();
		        },
		        error: function(xhr, textStatus, errorThrown){}
		    });
			
		} else {
	//		//$('#' + objFrm).reset();
			return;
		}
	}
	
	// remove form in content devinfo page
	function removeForm(objDiv1, objDiv2, index) {

		var formIndex = parseInt(index)-1;
		$('#' + objDiv1 + index).remove();
		$('#' + objDiv2 + index).remove();
		$('.txtr').empty();
		$('.txtr').append('<a href="javascript:appendFormDiv(\'editForm\');"><img src="<c:url value="/${ThreadSession.serviceLocale.language}/images/pm/btn_add.gif" />" alt="" name="appendSubContentBtn" /></a>');

	}

	function alramSprtPhoneCnt(obj, objFrm) {
		
		options = {
				dataType: "json",
				type	: "post",
				async 	: 	false,
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

	function ajaxCheckSprtPhone(obj, objFrm, objIndex) {

		options = {
				dataType: "json",
				type	: "post",
				async 	: 	false,
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

	function selectLcdSize(obj, objIndex) {
		
		if ($("#delYn" + objIndex).val() != "Y") {
			var chekedCnt = $(":checkbox[name=" + obj.name + "]:checked").length;
			alert("<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent06'/>");
			searchSprtPhone("editForm" + objIndex, objIndex);
		} else {
			return;
		}
	}

/////////////////////////////////////////////////////////////////////////////////////////////////////////////
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

	function selectUpdateText(seq, value) {
		
		$('#updateText').val(value);
		$('#updateSeq').val(seq);
		$('#updateText').focus();
		
		$("#updateInputLink").attr("href", "javascript:checkUpdateText('U');");
	}

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
<form id="editForm" name="editForm" method="post">
	<input type="hidden" id="cid" name="content.cid"		value="${resultMap.cid}" />
	<input type="hidden" name="subContent.cid"				value="${resultMap.cid}" />
	<input type="hidden" name="content.searchValue"			value="${content.searchValue}" />
	<input type="hidden" name="content.searchType"			value="${content.searchType}" />
	<input type="hidden" name="content.saleSearchType" 		value="${content.saleSearchType}" />
	<input type="hidden" name="content.page.no"				value="${content.page.no}" />
	<input type="hidden" id="redirectUrl" 	name="redirectUrl" 	value=""/>
	<input type="hidden" id="tabGbn" 		name="tabGbn" 		value="${tabGbn}"/>
	
</form>
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 產品上架/管理 &gt; 產品管理 <strong>&gt;</strong> <span>產品現狀</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title03.gif'/>" alt="產品現狀" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		
		<!-- include contentBaseInfo -->
		<jsp:include page="contentBaseInfo.jsp"/>
		<!-- //Tab_menu E -->
		
		<h4 class="h41 fltl">
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_10.gif'/>" alt="Binary 檔資訊" />
			<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
				<div class="helpbox">
				<div class="helpboxin">
					<p>可同時上傳多個LCD類別最佳狀態之Apk檔.</p>
				</div>
				</div>
			</a>
		</h4>
		<span id="binaryList">
		<c:choose>
			<c:when test="${resultMap.subContentsCnt > 0}">
				<c:forEach items="${resultMap.subContsList}" var="subContent" varStatus="listIndex">	

					<div class="tstyleA mar_b22" id="divSubContent${listIndex.index}" name="divSubContent">
					<form id="editForm${listIndex.index}" name="editForm${listIndex.index}"  method="post">
						<input type="hidden" name="subContent.cid"	value="${resultMap.cid}" />
						<input type="hidden" name="subContent.scid"	value="${subContent.scid}"  id = "scid${listIndex.index}"/>
						<input type="hidden" name="subContent.runFile.runFilePos"	value="${subContent.runFilePos}" />
						<input type="hidden" name="subContent.runFile.runUploadFileName"	value="${subContent.runFileNm}" />
						<input type="hidden" name="subContent.sprtPhoneModel" value="" />
						<input type="hidden" name="subContent.delYn"value="${subContent.delYn}"  id = "delYn${listIndex.index}"/>
						<input type="hidden" id = "modifySubContentIndex${listIndex.index}" name="modifySubContentIndex"	value="${modifySubContentIndex}" />
						
					<table summary="Binary 檔資訊" id="binaryInfoTable${listIndex.index}" >
						<caption>Binary 檔資訊</caption>
						<colgroup>
							<col width="21%" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><span>*</span> LCD Size(可重複選擇) </th>
								<td> <label for="lcd_1" id="label${listIndex.index}">
								<c:forEach items="${resultMap.lcdSizeChkBox}" var="lcdSize" varStatus="lcdListIndex">
									<input type="checkbox"" name="subContent.provisionItem" value="${lcdSize.dtlFullCd}" 
										<c:forEach items="${resultMap.provisionItemsList}" var="lcdSizeInfo" varStatus="privisionListIndex">		
											<c:if test="${lcdSizeInfo.scid == subContent.scid && lcdSizeInfo.itemCd == lcdSize.dtlFullCd}">checked="checked"</c:if>
										</c:forEach>
										onclick="javascript:selectLcdSize(this, '${listIndex.index}');"/>
										<label for="lcd_1">&nbsp;<gc:text code="${lcdSize.dtlFullCd}"/></label>&nbsp;&nbsp;
								</c:forEach>	
									</label>	
								</td>
							</tr>
							<tr id="binaryFileTr${listIndex.index}">
								<th scope="row" rowspan="2"><span>*</span> 上傳Binary 檔 </th>
								<td>
									<span class="txtcolor04">${subContent.runFileNm}</span> &nbsp;
									<a href="javascript:removeFormRunFile('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="刪除" /></a>
								</td>
							</tr>
							<tr id="binaryInfoTr${listIndex.index}">
								<td class="lh160">
									minSdkVersion : ${subContent.minSDKVersion} (${subContent.vmVerMin})<br />
									targetSdkVersion : ${subContent.targetSDKVersion}
									<c:if test="${not empty subContent.vmVerTarget && subContent.vmVerTarget ne 'N/A'}">(${subContent.vmVerTarget})</c:if><br />
									maxSdkVersion : ${subContent.maxSDKVersion}
									<c:if test="${not empty subContent.vmVerMax}">(${subContent.vmVerMax})</c:if><br />
									versionCode : ${subContent.versionCode}<br />
									versionName : ${subContent.versionName}<br />
									package : ${subContent.pkgNm}<br />
								</td>
							</tr>
							<tr  id="sprtPhoneListTr${listIndex.index}">
								<th class="tit01 tit03">選擇適用手機</th>
								<td class="bgnone">
									可以已上傳之Binary 檔為標準選擇手機.&nbsp;
									<a href="javascript:searchSprtPhone('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_dmsearch.gif'/>" alt="搜尋手機" /></a>
									<div class="tstyleC mar_t10">
										<table summary="手機名稱,型號,OS 版本,LCD Size" class="w577 bbnone">
											<caption>手機名稱,型號,支持OS,LCD Size</caption>
											<colgroup>
												<col width="7%" />
												<col width="18%" />
												<col width="25%" />
												<col width="18%" />
												<col />
											</colgroup>
											<thead>
											<tr>
												<th scope="col" class="tit06 btnone"><input type="checkbox" id="allSelPhoneChkBox${listIndex.index}"  disabled="disabled" checked="checked" onclick="allSelectCheckBox(this, 'editForm${listIndex.index}');"/></th>
												<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit21.gif'/>" alt="手機名稱" /></th>
												<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit22.gif'/>" alt="型號" /></th>
												<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit23.gif'/>" alt="OS 版本" /></th>
												<th scope="col" class="tit06 btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit24.gif'/>" alt="LCD Size" /></th>
											</tr>
											</thead>
										</table>
									</div>
									<div class="tstyleC hl185" style="overflow-x:hidden;">
										<table summary="手機名稱" class="w577">
											<caption>手機名稱</caption>
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
									<a href="javascript:subContentSave('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_ok.gif'/>" alt="確定" /></a>
									<a href="javascript:devContsPageReset('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_cancle.gif'/>" alt="取消" /></a>
								</td>
							</tr>
						</tbody>
					</table>
					</form>
					</div>
					<div class="txtr mar_b5" name="divBtnArea" id="divBtnArea${listIndex.index}">
						<a href="javascript:appendFormDiv('editForm${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_add.gif'/>" name="appendSubContentBtn" alt="附加" /></a>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise> 
				<div class="tstyleA mar_b22" id="divSubContent${resultMap.subContentsCnt}" name="divSubContent">
				<form id="editForm${resultMap.subContentsCnt}" name="editForm${resultMap.subContentsCnt}"  method="post" enctype="multipart/form-data">
				<input type="hidden" name="subContent.cid"	value="${content.cid }" />
				<input type="hidden" name="subContent.scid"	value="${subContent.scid }" id = "scid${resultMap.subContentsCnt}" />
				<table summary="Binary 檔資訊">
					<caption>Binary 檔資訊</caption>
					<colgroup>
						<col width="21%" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row"><span>*</span> LCD Size(可重複選擇) </th>
							<td>
								<label for="lcd_1" id="label${resultMap.subContentsCnt}"><gc:checkBoxs name="subContent.provisionItem" group="${CONST.PHONE_LCD_SIZE}" codeType="full" divide=" &nbsp;&nbsp; " split="&nbsp;"/></label>
							</td>		
						</tr>
						<tr>
							<th scope="row" class="tit01"><span>*</span> <label for="fileupload">上傳Binary 檔</label></th>
							<td class="bgnone">
								<div class="fileinputs" id="uploadDiv1bin0"  style="display:${empty subContent.runFile.runFilePos ? 'box':'none' };" >
									<span><input type="file" class="inputFile" id="1bin0" name="subContent.runFile.runUpload"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempRunUpload${resultMap.subContentsCnt}','${CONST.FILEEXT_ANDROID_BIN}');" accept="${CONST.FILEEXT_ANDROID_BIN}" style="cursor: pointer;" onkeydown="this.blur();" /></span>
									<div class="fakefile">
										<input type="text" id="tempRunUpload${resultMap.subContentsCnt}" name="tempRunUpload"  value="" class="w410" disabled="disabled" readonly/>
										<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="瀏覽檔案" /></a>
										
										<a href="javascript:uploadRunFile('editForm${resultMap.subContentsCnt}', 'tempRunUpload${resultMap.subContentsCnt}', 'label${resultMap.subContentsCnt}', ${resultMap.subContentsCnt});"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_upload.gif'/>" alt="上傳" id="addBinaryBtn" /></a>
					
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
	
		

		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_11.gif'/>" alt="審核附加資訊" /></h4>
		<div class="tstyleA mar_b22">
		<form id="editEtcForm" name="editEtcForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="content.cid"	value="${resultMap.cid}" />
		<input type="hidden" id="drmSetOpt" name="content.drmSetOpt" value="${content.drmSetOpt}" />
			<table summary="審核附加資訊">
				<caption>審核附加資訊</caption>
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
									<p>請選擇是否適用由Whoopy提供的ARM Library. 有關’ARMLibrary’之詳情請見[開發支援指南].</p>
								</div>
								</div>
							</a>
						</th>
						<td>
							<input type="radio" id="drmYnY" name="content.drmYn" value="Y" <c:if test='${content.drmYn == "Y"}'>checked</c:if> <c:if test="${content.saleStat != CONST.CONTENT_SALE_STAT_NA}">disabled='true'</c:if> onclick="javascript:selectDrmYn('Y');"/><label for="radio1" > <gm:string value='jsp.content.contentAndroidDevInfo.lbl.license01'/></label>&nbsp;&nbsp;
							<input type="radio" id="drmYnN" name="content.drmYn" value="N" <c:if test='${content.drmYn != "Y"}'>checked</c:if> <c:if test="${content.saleStat != CONST.CONTENT_SALE_STAT_NA}">disabled='true'</c:if> onclick="javascript:selectDrmYn('N');" /><label for="radio2"> <gm:string value='jsp.content.contentAndroidDevInfo.lbl.license02'/></label>
							<br />
							<span class="txt_90" id="licenseBtn">為測試ARM啟動與否, 請先下載.&nbsp;&nbsp;<a href="javascript:licenseForDeveloper('${content.cid}')"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_lidown.gif'/>" alt="下載許可" /></a></span>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01 tit03"><label for="manual">使用指南</label> 
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
								<div class="helpboxin">
									<p>該資訊為有效進行產品審核所用, 請易懂, 客觀地記述.</p>
								</div>
								</div>
							</a>
						</th>
						<td class="bgnone">
							<div class="fileinputs"  id="divScnrVerifyFile"   style="display:${empty content.verifyScnrFile?'box;':'none;' }" >
								<span><input type="file" class="inputFile"  id="verifyScnrUpload" name="content.verifyScnrUpload"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempScnrUploadFile','verifyDocu');" style="cursor: pointer;"/></span>
								<div class="fakefile">
									<input type="text" id="tempScnrUploadFile" name="tempScnrUploadFile" class="w410" disabled="disabled" readonly  value="檔案格式 : doc, docx, xls, xlsx, ppt, pptx, pdf"/>
									<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_filesearch.gif'/>" alt="檔案格式" /></a>
									
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
										<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del.gif'/>" alt="刪除" />
									</a>	
									
								</div>
							</c:if>
						</td>
					</tr>
				</tbody>
			</table>
		</form>	
		</div>
		
		<IFRAME src="<c:url value='/content/iFrameVerifyCommentList.omp?content.cid=${content.cid }'/>" id="Verify" frameBorder=0 width=100% scrolling=no marginwidth=0 marginheight=10 height="175px"></iframe>
		
		<form id="editUpdateForm"  name="editUpdateForm" method="post">
		<input type="hidden" name="contentUpdate.cid"	value="${content.cid}" />
		<input type="hidden" name="contentUpdate.updateSeq"	value="" id="updateSeq" />
		<div id="idUpdateListDiv">
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_12.gif'/>" alt="更新紀錄管理" />
			<a href="#" class="help zindex2"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
				<div class="helpbox">
				<div class="helpboxin">
					<p>可對販售產品之更新紀錄進行管理.</p>
				</div>
				</div>
			</a>
		</h4>
		<div class="tstyleC">
			<table summary="更新紀錄管理" class="w792 bbnone">
				<caption>更新紀錄管理</caption>
				<colgroup>
					<col width="18%" />
					<col width="18%" />
					<col />
					<col width="18%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit15.gif'/>" alt="適用日期" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit16.gif'/>" alt="上傳日期" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit17.gif'/>" alt="內容" /></th>
						<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit18.gif'/>" alt="修改/刪除" /></th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tstyleC h191" style="overflow-x:hidden;">
			<table summary="更新紀錄管理" class="w792 wbreak">
				<caption>更新紀錄管理</caption>
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
										<a href="javascript:selectUpdateText('${contentUpdate.updateSeq}', '${contentUpdate.updateText}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif'/>" alt="修改"/></a> 
										<a href="javascript:deleteUpdateText('${contentUpdate.updateSeq}');""><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="刪除"/></a>
									</c:if>
									<c:if test="${fn:trim(content.verifyPrgrYn) ne 'PD005399' and fn:trim(content.verifyPrgrYn) ne 'PD005303'}">
										<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update04"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif'/>" alt="修改"/></a> 
										<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update05"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="刪除"/></a>
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
										<a href="javascript:selectUpdateText('${contentUpdate.updateSeq}', '${contentUpdate.updateText}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif'/>" alt="修改"/></a> 
										<a href="javascript:deleteUpdateText('${contentUpdate.updateSeq}');""><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="刪除"/></a>
									</c:if>
									<c:if test="${fn:trim(content.verifyPrgrYn) ne 'PD005399' and fn:trim(content.verifyPrgrYn) ne 'PD005303'}">
										<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update04"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif'/>" alt="修改"/></a> 
										<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update05"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="刪除"/></a>
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
			<a href="javascript:checkUpdateText('I');" id="updateInputLink"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_submitl.gif'/>" alt="上傳" /></a>
		</div>
		</form>
	
		<div class="btnarea mar_t30">
			<img id="btnMod" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_save.gif'/>" alt="儲存" />
			<img id="btnCancel" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="取消" />
			<p class="btn"><img id="listBtn" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_list.gif'/>" alt="目錄" /></p>
		</div>
	</div>
	<!-- //CONTENT TABLE E-->
	
</div>
	

