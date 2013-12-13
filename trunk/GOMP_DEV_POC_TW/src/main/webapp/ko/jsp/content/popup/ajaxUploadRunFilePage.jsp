<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 

<script type="text/javascript">
<!--

	//File Upload, remove Upload File
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
//-->
</script>

<c:if test="${resultMap.subContentsCnt == 0}" >
<p class="txtr" ></p>
<div class="tstyleA mar_b22" id="divSubContent${resultMap.subContentsCnt}" name="divSubContent">
</c:if>
 
<c:if test="${resultMap.subContentsCnt > 0}" >
<div class="txtr mar_b5" name="divBtnArea" id="divBtnArea${resultMap.subContentsCnt}">
	<a href="javascript:removeForm('divSubContent', 'divBtnArea' , ${resultMap.subContentsCnt});"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del03.gif' />" alt="삭제" /></a>
</div>
<div class="tstyleA mar_b22" id="divSubContent${resultMap.subContentsCnt}" name="divSubContent">
</c:if>

<form id="editForm${resultMap.subContentsCnt}" name="editForm${resultMap.subContentsCnt}"  method="post" enctype="multipart/form-data">
<input type="hidden" name="subContent.cid"	value="${subContent.cid }" />
<input type="hidden" name="subContent.scid"	value="${subContent.scid }" id = "scid${resultMap.subContentsCnt}"/>

<table summary="바이너리 파일정보 입력">
	<caption>바이너리 파일정보 입력</caption>
	<colgroup>
		<col width="23%" />
		<col />
	</colgroup>
	<tbody>
		<tr>
			<th scope="row"><span>*</span> LCD Size(복수선택가능) </th>
			<td>
				<label for="lcd_1" id="label${resultMap.subContentsCnt}"><gc:checkBoxs name="subContent.provisionItem" group="${CONST.PHONE_LCD_SIZE}" codeType="full" divide=" &nbsp;&nbsp; " split="&nbsp;"/></label>
			</td>		
		</tr>
		<tr>
			<th scope="row" class="tit01"><span>*</span> <label for="fileupload">바이너리 파일등록</label></th>
			<td class="bgnone">
				<div class="fileinputs" id="uploadDiv1bin0"  >
					<span><input type="file" class="inputFile" id="1bin0" name="subContent.runFile.runUpload"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempRunUpload${resultMap.subContentsCnt}','${CONST.FILEEXT_ANDROID_BIN}');" accept="${CONST.FILEEXT_ANDROID_BIN}" style="cursor: pointer;" onkeydown="this.blur();" /></span>
					<div class="fakefile">
						<input type="text" id="tempRunUpload${resultMap.subContentsCnt}" name="tempRunUpload"  value="" class="w410" />
						<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif' />" alt="첨부파일" /></a>
						
						<a href="javascript:uploadRunFile('editForm${resultMap.subContentsCnt}', 'tempRunUpload${resultMap.subContentsCnt}', 'label${resultMap.subContentsCnt}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_upload.gif' />" alt="업로드" id="addBinaryBtn" /></a>
	
					</div>
				</div>
			</td>
		</tr>
	</tbody>
</table>
</form>

