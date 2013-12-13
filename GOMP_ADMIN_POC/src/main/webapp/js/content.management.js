// ByteCheck
function checkMsgLength(msgBoxId, maxSize) {

	var msglen = 0;	//message length(byte)
	var msg="";		
	
	var tempMsg = $("#"+msgBoxId).val();
	for(var i=0; i<tempMsg.length; i++) {
		var temp = tempMsg.charAt(i);
		if(escape(temp) == '%0D') continue;
		if(escape(temp).indexOf("%u") != -1) {
			msglen += 3;
		}else {
			msglen++;
		}
		if(msglen > maxSize) break;
		msg += temp;
	}
	
	if(msglen > maxSize) {
		
		$("#"+msgBoxId).val(msg);
		return false;
	}
	return true;
}

// content check price in content detail info page
function checkPrice(obj){
	checkNumber(obj);
//	var priceValue = parseInt(obj.value);
//	if(priceValue > price){
//		obj.value = '100000';
//		alert('최대 100,000원까지 등록 가능합니다.');
//	}
	comma(obj);
}

//
function comma(comma_rstr) {
    var nocomma = comma_rstr.value.replace(/,/gi,''); 		// comma remove
    var b = ''; 											
    var i = 0; 												
    for (var k=(nocomma.length-1); k>=0; k--) {	 			
        var a = nocomma.charAt(k); 
        if (k == 0 && a == 0 && nocomma.length > 1) {  		
            comma_rstr.value = '0'; 
            return; 
        } 
        else { 
            if (i != 0 && i % 3 == 0) { 					
                b = a + "," + b ; 
            } 
            else { 											
                b = a + b; 
            } 
            i++; 
        } 
    } 
    comma_rstr.value = b; 									
    return; 
}

// remove Upload File
function removeFile(frm, id1, id2, id3, flagId){
	setVisibleObject(false, id1);
	setVisibleObject(true, id2);
	setVisibleObject(false, id3);
	frm[flagId].value = true;
}   

// check extension
function isExt( fileName, extCode )	{
	
	if (fileName != null && fileName != "")
 	{
		var idx	= fileName.lastIndexOf( "." );
		var ext	= fileName.substring( idx ).toLowerCase();
	
		if ( extCode == "verifyDocu")	{
			if (ext != ".xls" && ext != ".xlsx"
				&& ext != ".ppt" && ext != ".pptx"
				&& ext != ".doc" && ext != ".docx"
				&& ext != ".pdf")	{
				return	false;
			}
		}	
		else if( extCode == "docu") {
			if ( ext != ".doc" && ext != ".pdf" && ext != ".ppt"  && ext != ".docx" && ext != ".pptx" && ext != ".xls" && ext != ".xlsx" )	{
				return	false;
			}
		}
		else if( extCode == "pict") {
			if ( ext != ".gif" && ext != ".jpg" && ext != ".png" && ext != ".bmp")	{
				return	false;
			}
		}
		else if( extCode == "descImg") {
			if (ext != ".jpg" && ext != ".bmp" && ext != ".png")	{
				return	false;
			}
		} else if ( extCode == "apk") {
			if (ext != ".apk" && ext != ".APK")	{
				return	false;
			}
		}
	
		return	true;	
 	} else {
 		return true;
 	}
}

//Content Detail Page
function gotoContentDetailInfoView(objFrmId, objCid, no) {

	//$("input[name=content\\.cid]").val(objCid);
	$("input[name=content\\.cid]").remove();  
	$("input[name=content\\.searchType]").val($('#searchType').val());
	$("input[name=content\\.saleSearchType]").val($('#saleStat').val());
	$("input[name=content\\.searchValue]").val($('#searchValue').val());
	
	$("#" + objFrmId).attr("action", "contentDetailInfoView.omp?content.cid=" + objCid);
	$("#" + objFrmId).submit();
	//location.href = env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + objCid;
}

//Content Detail Page
function gotoContentView(objCid) {

	location.href = env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + objCid;
}

// Content Category Popup
function popSelectCtgrList(cid){
	var props = {
		type : "GET",
		url: env.contextPath + "/content/ajaxSelectCtgrList.omp?content.cid=" + cid,
		layerId : "popSelectCtgrList",
		parentLayerId : "wrap"
	};

	popLayerAjaxCall(props, 620);
}

//Content Sale Stat Change History
function popContentSaleStatList(winName, url, width, heigth, cid) {	
	openPop('about:blank', width, heigth, winName);
	
	document.editForm.target = winName;
	document.editForm.action = url;
	document.editForm.submit();
}

// Browser Center Open
function openPop(url, width, height, winName) {
	var left = (screen.availWidth-width)/2;
	var top = (screen.availHeight-height)/2;
	var features = "left=" + left + ",top=" + top + ",width=" + width + ",height=" + height + ",status=no,toolbar=no,menubar=no,location=no,resizable=no";
	window.open(url, winName, features);
}

// page move
function moveTabCheck(pageGbn, objCid) {
	
	var moveFlag = preDoSubmit();

	if (moveFlag) {
		moveTab(pageGbn, objCid);
	} else {
		//showValidationForm(pageGbn, objCid);
		showChangeFormOkLayer(pageGbn, objCid);
	}
}

// content Detail Info Page Tab
function moveTab(pageGbn, objCid) {
	
	if (pageGbn == 'DETAIL') {
		$("#editForm").attr("action", "contentDetailInfoView.omp?content.cid=" + objCid);
	} else if (pageGbn == 'DEVELOP') {
		$("#editForm").attr("action", "contentDevInfoView.omp?content.cid=" + objCid);
	}
	
	$("input[name=content\\.cid]").remove(); 
	$("input[name=subContent\\.cid]").remove(); 

	$("#editForm").attr("target", "_self");	
	$("#editForm").submit();
}

// open popup content preview
function popPreview(cid) {
	 var winName = "winContPreviewInfo";
	 var url = "popItemPreview.omp";
	 try{
	 	$( "#prodPrc" ).val(parseInt($( "#prodPrc" ).val().replace(/,/gi,''))); // comma remove
	 }catch(e) {}
	 popItemPreviewOpen(winName, url, '556', '500', cid);
}

// open popup image preview
function popImagePreview(cid) {
	 var winName = "winImagePreviewInfo";
	 var url = "popImagePreview.omp";
	 try{
	 	$( "#prodPrc" ).val(parseInt($( "#prodPrc" ).val().replace(/,/gi,''))); // comma remove
	 }catch(e) {}
	 popItemPreviewOpen(winName, url, '556', '500', cid);
}


//Item Preview Windows Open Script
function popItemPreviewOpen(winName, url, width, heigth, cid)	{
	
	openPop('about:blank', width, heigth, winName);
	document.editForm.target = winName;
	document.editForm.action = url;
	document.editForm.submit();
}


//// Develop Infomation Script ////

// remove verifyScnrfile
function removeVerifyScnrFile(hiddenObj, showObj, delYn) {
	setVisibleObject(false, hiddenObj);
	setVisibleObject(true, showObj);
	$("#" + delYn).val("Y");
}

// search sprt phone in content devinfo page
function searchSprtPhone(objFrm, objIndex) {

	$('#' + objFrm).attr("target", "_self");	
	$('#' + objFrm).attr("action", "ajaxSprtPhoneList.omp");
	$('#' + objFrm).ajaxSubmit(function (data, statusText) {

		$('#sprtPhoneListTr' + objIndex).html(data);
		
		$('#allSelPhoneChkBox').click(function() {allSelectCheckBox(this, 'editForm' + objIndex, objIndex);});
		//$('#allSelPhoneChkBox').attr("onclick",  "javascript:allSelectCheckBox(this, 'editForm'" + objIndex +"," + objIndex + ");");
		$('#allSelPhoneChkBox').attr("id", "allSelPhoneChkBox" + objIndex);
		
		$('input:checkbox[name=sprtPhoneModel]').each(function() {
			$(this).click(function() {alramSprtPhoneCnt(this, 'editForm' + objIndex);});
			$(this).attr("name", "sprtPhoneModel" + objIndex);
		});
		
		$('#searchALink').attr("href", "javascript:searchSprtPhone('editForm" + objIndex +  "', '" + objIndex  + "');");
		//$('img[name=appendSubContentBtn]').remove();
		$('.txtr').hide();
	});
}

// remove form after event in content devinfo page
function removeFormDivAfrerEvent(data, statusText, formIndex) {
	var intFormIndex = parseInt(formIndex);
	var frmIndex = $('div[name=divSubContent]').length;

	$('.txtr').empty();

	$('div#divSubContent' + intFormIndex).remove();
	$('.txtr').append('<a href="javascript:appendFormDiv(\'editForm\');"><img src="../images/pm/btn_add.gif" alt="" name="appendSubContentBtn" /></a>');

}

// form reset
function subContentReset(objFrm) {
	$('#' + objFrm).reset();
}

// append form in content dev info page
function appendFormDiv(objFrm) {
	
	$('#' + objFrm).attr("target", "_self");	
	$('#' + objFrm).attr("action", "ajaxAppendRunFile.omp");
	$('#' + objFrm).ajaxSubmit(appendFormDivAfrerEvent);
	
}

// append Form afrer event in content dev info page
function appendFormDivAfrerEvent(data, statusText) {

	//$('img[name=appendSubContentBtn]').remove();
	$('.txtr a').remove();
	//$('.txtr').empty();
	//$('.txtr').hide();
	//$('div[name=divBtnArea]').remove();
	
	$('div[name=divBtnArea]:last').after(data);
	$(':hidden[name=subContent\\.scid]:last').val('');	
	
}



// phone info page move
function gotoPhoneModelInfoList(phoneModelCd) {
	location.href =  env.contextPath + "/community/phoneInfoList.omp?phoneInfo.searchType=modelNm&phoneInfo.searchText=" + phoneModelCd;
}

//Verify Content
function verifyReq(cid, tabGbn){

	//$('#verifyReqBtn').attr("style","visibility='hidden'");
	
	if(showStatusCheck()) {
		var options = {   
			 datatype : "json",
		     type:      "post",        // 'get' or 'post', override for form's 'method' attribute 
		     success:       function (responseText, status) {
				 
				 closePopupLayer('popVerifyReq');	
				 
				 try { 
		    		 var obj = eval("(" + responseText + ")");
		    		 
		    		 if(obj.resultMessage != "") {
			    		 alert(obj.resultMessage);
			    	  }
		    		 
				 }catch(e) {}
				 
				 if (tabGbn == "DETAIL") location.replace(env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + cid);
				 else if (tabGbn == "DEVELOP") location.replace(env.contextPath +"/content/contentDevInfoView.omp?content.cid=" + cid);
			 }, 
		     error:	function(xhr, textStatus, errorThrown){}
		     // other available options: 
		   
		}; 	
		
		$('#verifyReqFrm').attr("target", "_self");	
		$('#verifyReqFrm').attr("method", "post");	
		$('#verifyReqFrm').attr("action", "ajaxVerifyReq.omp");
		
		$('#verifyReqFrm').ajaxSubmit(options);
		
	} else {
		if (tabGbn == "DETAIL") location.replace(env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + cid);
		 else if (tabGbn == "DEVELOP") location.replace(env.contextPath +"/content/contentDevInfoView.omp?content.cid=" + cid);
	}
}

// popup verify Request Comment
function popVerifyReqComnt(layerId, cid, tabGbn){
	
	if(showStatusCheck()) {
		
		var props = {
			type : "get",
			url : env.contextPath + "/content/ajaxVerifyReqComnt.omp?content.cid=" + cid + "&tabGbn=" + tabGbn,
			layerId : "popVerifyReq",
			parentLayerId : "wrap"
		};
	
		popLayerAjaxCall(props);
		
	} else {
		if (tabGbn == "DETAIL") location.replace(env.contextPath + "/content/contentDetailInfoView.omp?content.cid=" + cid);
		 else if (tabGbn == "DEVELOP") location.replace(env.contextPath +"/content/contentDevInfoView.omp?content.cid=" + cid);
	}
}

// ARM YN Flag
function selectDrmYn(objYN) {

	if(objYN == 'Y') {
		$('#licenseBtn').show();
	} else {
		$('#licenseBtn').hide();
	}
}

