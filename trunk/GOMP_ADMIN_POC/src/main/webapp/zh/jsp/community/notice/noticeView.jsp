<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<%@ page import="com.omp.commons.util.StringUtil" %>

<%	String url = request.getRequestURL() + "";	%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<link rel="stylesheet" href="<c:url value="/cleditor/jquery.cleditor.css"/>" type="text/css" >
<script type="text/javascript" src="<c:url value="/cleditor/jquery.cleditor.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/cleditor/jquery.cleditor.js"/>"></script>

<script type="text/javascript">

	$(document).ready(function()	{
		
		if(	$("#ctgrCd").val() != "NTC5000" ) {
			$("#dscr").cleditor({width:650, height:250, useCSS:true})[0].focus();
		}

	});

	var funcNoticeList	= function()	{
		$("#noticeList").submit();
	};

	var processNotice = function() {
		
		if(showValidate('noticeRegist', 'dialog', 'Check Input Value.')) {
			if("<g:string value='${notice.title}'/>" == "") {
				if(confirm("<gm:string value='jsp.community.notice.noticeView.msg.confirm_ins'/>")) {
					$.blockUI({ message: 'Please Wait.' });
					$("#noticeRegist").submit();
				}
			} else {
				if(confirm("<gm:string value='jsp.community.notice.noticeView.msg.confirm_upd'/>")) {
					$.blockUI({ message: 'Please Wait.' });
					$("#noticeRegist").submit();
				}
			}
		}
		
	};

	var updateNotice = function() {
		if(confirm("<gm:string value='jsp.community.notice.noticeView.msg.confirm_del'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$("#updateNotice").submit();
		}
	};

	var fileImgExtCheck = function ( obj ) {
		var reg = /.(jpg|jpeg|gif|png)(s{0,})$/i;
		var result = obj.value.search(reg);
		if(result == "-1"){
			alert("<gm:string value="jsp.community.notice.noticeView.msg.check_file_ext"/>");
			if($.browser.msie) {
			       $("#attFileImg").replaceWith( $("#attFileImg").clone(true) );
			} else {
			      $("#attFileImg").val("");
			}
			return false;
		}
	};

	var fileExtCheck = function ( obj ) {
		var reg = /.(htm|html|zip|rar|alz|doc|ppt|pdf|txt|docx|xls|xlsx|pptx|hwp)(s{0,})$/i;
		var result = obj.value.search(reg);
		if(result == "-1"){
			alert("<gm:string value="jsp.community.notice.noticeView.msg.check_file_ext"/>");
			if($.browser.msie) {
			       $("#attFileEtc").replaceWith( $("#attFileEtc").clone(true) );
			} else {
			      $("#attFileEtc").val("");
			}
			return false;
		}
	};

	var fncDelAttFileImg = function() {
		$("#tobeImgFid").val( "" );
		$("#divAttFileImg").css("display", "");
		$("#divDelAttFileImg").css("display", "none");
	};

	var fncDelAttFileEtc = function() {
		$("#tobeEtcFid").val( "" );
		$("#divAttFileEtc").css("display", "");
		$("#divDelAttFileEtc").css("display", "none");
	};
	
</script>

	<div id="location">
		<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_WEB">
		首頁  &gt; 客戶服務中心  &gt; 公告事項管理  &gt; <strong>WEB公告事項</strong> 
		</s:if>
		<s:elseif test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_SC">
		首頁  &gt; 客戶服務中心  &gt; 公告事項管理  &gt; <strong>SC公告事項</strong> 
		</s:elseif>
		<s:elseif test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_DEV">
		首頁  &gt; 客戶服務中心  &gt; 公告事項管理  &gt; <strong>開發商公告事項</strong> 
		</s:elseif>
	</div><!-- //location -->

	<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_WEB">
	<h1 class="fl pr10">WEB公告事項</h1>
	</s:if>
	<s:elseif test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_SC">
	<h1 class="fl pr10">SC公告事項</h1>
	</s:elseif>
	<s:elseif test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_DEV">
	<h1 class="fl pr10">開發商公告事項</h1>
	</s:elseif>
	<p>可登載／管理公告事項。</p>

	<s:form action="noticeList" id="noticeList" theme="simple">
		<s:hidden id="srchFlag" name="srchFlag" value="TRUE"/>
		<s:hidden id="ctgrCd" name="ctgrCd" value="%{ctgrCd}" />
		<s:hidden id="notice.ctgrCd" name="notice.ctgrCd" value="%{ctgrCd}" />
		<s:hidden id="searchOpenYn" name="notice.searchOpenYn" value="%{notice.searchOpenYn}"/>
		<s:hidden id="startDate" name="notice.startDate" value="%{notice.startDate}"/>
		<s:hidden id="endDate" name="notice.endDate" value="%{notice.endDate}"/>
		<s:hidden id="searchType" name="notice.searchType" value="%{notice.searchType}"/>
		<s:hidden id="searchValue" name="notice.searchValue" value="%{notice.searchValue}"/>
		<s:hidden id="pageNo" name="notice.page.no" value="%{notice.page.no}"/>
	</s:form>
	<s:form action="updateNotice" id="updateNotice" theme="simple">
		<s:hidden id="mode" name="mode" value="D" />
		<s:hidden id="noticeId" name="notice.noticeId" value="%{notice.noticeId}" />
		<s:hidden id="ctgrCd" name="ctgrCd" value="%{ctgrCd}" />
		<s:hidden id="notice.ctgrCd" name="notice.ctgrCd" value="%{ctgrCd}" />
		<s:hidden id="selectedNoticeId" name="notice.selectedNoticeId" value="%{notice.noticeId}"/>
		
		<s:hidden id="searchOpenYn" name="notice.searchOpenYn" value="%{notice.searchOpenYn}"/>
		<s:hidden id="startDate" name="notice.startDate" value="%{notice.startDate}"/>
		<s:hidden id="endDate" name="notice.endDate" value="%{notice.endDate}"/>
		<s:hidden id="searchType" name="notice.searchType" value="%{notice.searchType}"/>
		<s:hidden id="searchValue" name="notice.searchValue" value="%{notice.searchValue}"/>
		<s:hidden id="pageNo" name="notice.page.no" value="%{notice.page.no}"/>
	</s:form>

	<s:form action="processNotice" id="noticeRegist" theme="simple" enctype="multipart/form-data">
		<s:hidden id="ctgrCd" name="ctgrCd" value="%{ctgrCd}" />
		<s:hidden id="notice.ctgrCd" name="notice.ctgrCd" value="%{ctgrCd}" />
		<s:hidden id="noticeId" name="notice.noticeId" value="%{notice.noticeId}" />
		<s:hidden id="gid" name="notice.gid" value="%{notice.gid}" />
		<s:hidden id="selectedNoticeId" name="notice.selectedNoticeId" value="%{notice.noticeId}"/>
		<s:hidden id="cntFileSupport" name="cntFileSupport" value="%{cntFileSupport}" />
		
		<s:hidden id="searchOpenYn" name="notice.searchOpenYn" value="%{notice.searchOpenYn}"/>
		<s:hidden id="startDate" name="notice.startDate" value="%{notice.startDate}"/>
		<s:hidden id="endDate" name="notice.endDate" value="%{notice.endDate}"/>
		<s:hidden id="searchType" name="notice.searchType" value="%{notice.searchType}"/>
		<s:hidden id="searchValue" name="notice.searchValue" value="%{notice.searchValue}"/>
		<s:hidden id="pageNo" name="notice.page.no" value="%{notice.page.no}"/>
	<table class="tabletype02">
		<colgroup>
			<col style="width:20%;">
			<col >
			<col style="width:20%;">
			<col >
		</colgroup>
		<tbody>
		<tr>
			<th>標題</th>
			<td colspan="3" class="text_l">
				<input id="title" type="text" name="notice.title" class="txt" style="width:80%;" value="<g:tagAttb value="${notice.title}"/>" 
					v:required m:required="<gm:string value="jsp.community.notice.noticeView.msg.check_title"/>"  />
			</td>
		</tr>
		<tr>
			<th>公開與否</th>
			<td colspan="3" class="text_l align_td">
				<input type="radio" name="notice.openYn" class="ml05" value="Y" <c:if test="${notice.openYn eq 'Y' }"> checked </c:if> />公開  
				<input type="radio" name="notice.openYn" class="ml05" value="N" <c:if test="${notice.openYn != 'Y' }"> checked </c:if> />隱藏 
				<span class="ml20">*未設定公開與否時，預設為［隱藏］。</span>
			</td>
		</tr>
		<s:if test='notice.noticeId != 0'>
		<tr>
			<th>設定日期</th>
			<td class="text_l"><g:text value="${notice.insDttm}" format="L####-##-## ##:##:##"/></td>
			<th>設定者</th>
			<td class="text_l">${notice.insNm}</td>
		</tr>
		</s:if>
		<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_NTC_SC">
		<tr>
			<th>內容</th>
			<td colspan="3" class="text_l">
				<textarea id="dscr" name="notice.dscr" class="text_area" 
					v:required m:required="<gm:string value="jsp.community.notice.noticeView.msg.check_dscr"/>" 
					v:text8_limit="4000" m:text8_limit="<gm:string value="jsp.community.notice.noticeView.msg.check_len_dscr"/>"
					><g:tagBody value="${notice.dscr}" /></textarea>
			</td>
		</tr>
		</s:if>
		<s:else>
		<tr>
			<th>內容</th>
			<td colspan="3" class="text_l">
				<textarea id="dscr" name="notice.dscr" class="text_area"
					v:required m:required="<gm:string value="jsp.community.notice.noticeView.msg.check_dscr"/>" 
					v:text8_limit="4000" m:text8_limit="<gm:string value="jsp.community.notice.noticeView.msg.check_len_dscr"/>"
					><g:tagBody value="${notice.dscr}" /></textarea>
			</td>
		</tr>
		</s:else>
		
		<tr>
			<th>附加圖片</th>
			<td colspan="3" class="text_l align_td">
				<div id="divAttFileImg">
				<input onchange="fileImgExtCheck(this);" id="attFileImg" type="file" name="notice.attFileImg" class="btn_s" style="width:40%;" />
				&nbsp;&nbsp;<span class="font_11">請上傳Width 665, 且小於1Mb之JPG,PNG,GIF檔。</span>
				</div>
		<c:set var="skipFid" value=""/>
		<s:if test="fileSupportList.size != 0">
			<s:iterator value="fileSupportList" status="status">
				<c:set var="resultType" value="${ftype}"/>
				<c:if test="${resultType=='JPG' or resultType=='JPEG' or resultType=='PNG' or resultType=='GIF'}">
					<script language="javascript">$("#divAttFileImg").css("display", "none");</script>
					<c:set var="skipFid" value="${fid}"/>
					<s:hidden id="asisImgFid" name="notice.asisImgFid" value="%{fid}" />
					<s:hidden id="tobeImgFid" name="notice.tobeImgFid" value="%{fid}" />
			<div id="divDelAttFileImg">
				<img src="${CONF['omp.common.url.http-share.common.notice']}${fnm}" width="37" height="38" 
					onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');" />
				&nbsp;<a class="btn_s" href="javascript:fncDelAttFileImg();"><span>- 刪除</span></a>
				<br/>&nbsp;VIEW FULL URL : ${CONF['omp.common.url.http-share.common.notice']}${fnm}
				<br/>&nbsp;DOWN FULL URL : /devpoc/fileSupport/bbsFileDown.omp?bnsType=common.path.http-share.common.notice&amp;filePath=${fnm}&amp;fileName=${ofnm}
			</div>
				</c:if>
			</s:iterator>
		</s:if>
			</td>
		</tr>

		<s:if test="ctgrCd != @com.omp.admin.common.Constants@CTGR_NTC_SC">
			<tr>
				<th>附加檔案</th>
				<td colspan="3" class="text_l align_td">
				<div id="divAttFileEtc">
					<input onchange="fileExtCheck(this);" id="attFileEtc" type="file" name="notice.attFileEtc" class="btn_s" style="width:40%;" />
					&nbsp;&nbsp;<span class="font_11">請上傳小於10MB檔。</span>
				</div>
			<s:if test="fileSupportList.size != 0">
				<s:iterator value="fileSupportList" status="status">
					<c:set var="resultFid" value="${fid}"/>
					<c:if test="${resultFid != skipFid}">
						<script language="javascript">$("#divAttFileEtc").css("display", "none");</script>
						<s:hidden id="asisEtcFid" name="notice.asisEtcFid" value="%{fid}" />
						<s:hidden id="tobeEtcFid" name="notice.tobeEtcFid" value="%{fid}" />
				<div id="divDelAttFileEtc">
					${ofnm}
					&nbsp;<a class="btn_s" href="javascript:fncDelAttFileEtc();"><span>- 刪除</span></a>
					<br/>
					&nbsp;DOWN FULL URL : /devpoc/fileSupport/bbsFileDown.omp?bnsType=common.path.http-share.common.notice&amp;filePath=${fnm}&amp;fileName=${ofnm}
				</div>
					</c:if>
				</s:iterator>
			</s:if>
				</td>
			</tr>
		</s:if>
		</tbody>
	</table>
	<s:if test='notice.noticeId != 0'>
	<p class="fl mt25"><a class="btn" href="javascript:updateNotice();"><span>刪除</span></a></p>
	<p class="btn_wrap text_r mt25"><a class="btn" id="btnSubmit" href="javascript:processNotice();"><span>儲存</span></a>
		<a class="btn" href="javascript:funcNoticeList();"><span>目錄</span></a></p>
	</s:if>
	<s:else>
	<p class="btn_wrap text_r mt25"><a class="btn" id="btnSubmit" href="javascript:processNotice();"><span>儲存</span></a>
		<a class="btn" href="javascript:funcNoticeList();"><span>目錄</span></a></p>
	</s:else>
	</s:form>

</body>
</html>
