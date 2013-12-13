<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

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
		
		if(	$("#ctgrCd").val() == "SFAQ1000" ) {
			$("#dscr").cleditor({width:500, height:250, useCSS:true})[0].focus();
		}
	
	});

	function funSaveValid(){
		if ( $("#selectCtgrCd").val() == "" )	{
			alert( "<gm:string value="jsp.community.faq.faqView.msg.select_ctgr"/>" );
			return false;
		}
		return true;
	};

	var processFaq = function() {
		
		if(!funSaveValid()) {
			return;
		}
		
		if(showValidate('faqForm', 'dialog', 'Check Input Value.')) {
			if(confirm("<gm:string value='jsp.community.faq.faqView.msg.confirm_ins'/>")) {
				$.blockUI({ message: 'Please Wait.' });
				$("#faqForm").submit();
			}
		}
		
	};

	var fileImgExtCheck = function ( obj ) {
		var reg = /.(jpg|jpeg|gif|png)(s{0,})$/i;
		var result = obj.value.search(reg);
		if(result == "-1"){
			alert("<gm:string value="jsp.community.faq.faqView.msg.check_file_ext"/>");
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
			alert("<gm:string value="jsp.community.faq.faqView.msg.check_file_ext"/>");
			if($.browser.msie) {
			       $("#attFileEtc").replaceWith( $("#attFileEtc").clone(true) );
			} else {
			      $("#attFileEtc").val("");
			}
			return false;
		}
	};

	var updateFaqList = function( mode )	{
		
		$( "#mode" ).val( mode );
		$( "#selectedFaqId" ).val( $("#faqId").val() );

		if(confirm("<gm:string value='jsp.community.faq.faqView.msg.confirm_proc'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$( "#faqForm" ).attr("action", "<c:url value="/community/updateFaqList.omp"/>");
			$( "#faqForm" ).submit();
		}
	};

	var funcFaqList	= function() {
		$("#faqList").submit();
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
			Home &gt; 고객지원 &gt; FAQ 관리 &gt; <strong>
			<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_SC">
			SC FAQ
			</s:if>
			<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_DEV">
			개발자 FAQ
			</s:if>
			</strong> 
		</div><!-- //location -->

		<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_SC">
		<h1 class="fl pr10">SC FAQ</h1>
		<p>SC 관련 FAQ 관리를 할 수 있습니다.</p>
		</s:if>
		<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_DEV">
		<h1 class="fl pr10">개발자 FAQ</h1>
		<p>개발자 관련 FAQ 관리를 할 수 있습니다.</p>
		</s:if>

		<s:form action="faqList" id="faqList" theme="simple">
			<s:hidden id="srchFlag" name="srchFlag" value="TRUE"/>
			<s:hidden id="faqId" name="faq.faqId" value="%{faq.faqId}" />
			<s:hidden id="ctgrCd" name="ctgrCd" />
			<s:hidden id="highCtgr" name="faq.highCtgr" value="%{ctgrCd}" />
			<s:hidden id="searchOpenYn" name="faq.searchOpenYn" value="%{faq.searchOpenYn}"/>
			<s:hidden id="startDate" name="faq.startDate" value="%{faq.startDate}"/>
			<s:hidden id="endDate" name="faq.endDate" value="%{faq.endDate}"/>
			<s:hidden id="searchType" name="faq.searchType" value="%{faq.searchType}"/>
			<s:hidden id="searchValue" name="faq.searchValue" value="%{faq.searchValue}"/>
			<s:hidden id="pageNo" name="faq.page.no" value="%{faq.page.no}"/>
		</s:form>

		<s:form action="processFaq" id="faqForm" theme="simple" enctype="multipart/form-data">
			<s:hidden id="faqId" name="faq.faqId" value="%{faq.faqId}" />
			<s:hidden id="gid" name="faq.gid" value="%{faq.gid}" />
			<s:hidden id="ctgrCd" name="ctgrCd" />
			<s:hidden id="highCtgr" name="faq.highCtgr" value="%{ctgrCd}" />
			<s:hidden id="selectedFaqId" name="selectedFaqId" />
			<s:hidden id="mode" name="mode" />
			<s:hidden id="searchOpenYn" name="faq.searchOpenYn" value="%{faq.searchOpenYn}"/>
			<s:hidden id="startDate" name="faq.startDate" value="%{faq.startDate}"/>
			<s:hidden id="endDate" name="faq.endDate" value="%{faq.endDate}"/>
			<s:hidden id="searchType" name="faq.searchType" value="%{faq.searchType}"/>
			<s:hidden id="searchValue" name="faq.searchValue" value="%{faq.searchValue}"/>
			<s:hidden id="pageNo" name="faq.page.no" value="%{faq.page.no}"/>
		<table class="tabletype02" id="faqTable">
			<colgroup>
				<col style="width:20%;">
				<col style="width:30%;">
				<col style="width:20%;">
				<col style="width:30%;">
			</colgroup>
			<tbody>
			<tr>
				<th>카테고리</th>
				<td colspan="3" class="text_l">
					<s:select list="result.ctgrMap" id="selectCtgrCd" name="faq.ctgrCd" style="width:104px;" theme="simple" >
					</s:select>
				</td>
			</tr>
			<tr>
				<th>질문</th>
				<td colspan="3" class="text_l">
					<input id="title" type="text" name="faq.title" class="txt" style="width:80%;" value="${faq.title}" 
						v:required m:required="<gm:string value="jsp.community.faq.faqView.msg.check_title"/>" />				
				</td>
			</tr>
			<tr>
				<th>노출설정</th>
				<td colspan="3" class="text_l align_td">
					<input type="radio" name="faq.openYn" class="ml05" value="Y" <c:if test="${faq.openYn eq 'Y' }"> checked </c:if> 
						v:mustcheck="1" m:mustcheck="<gm:string value="jsp.community.faq.faqView.msg.check_openYn"/>" />노출 
					<input type="radio" name="faq.openYn" class="ml05" value="N" <c:if test="${faq.openYn eq 'N' }"> checked </c:if> />숨김
				</td>
			</tr>
			<tr>
				<th>노출순서</th>
				<td colspan="3" class="text_l">
					<input id="sort" type="text" name="faq.sort" class="txt" style="width:40px;" value="${faq.sort}" />	
					<span>* 노출순서를 입력하지 않은 경우 최근에 등록한 FAQ가 가장 상단에 등록 됩니다.</span>
				</td>
			</tr>
		<s:if test='faq.faqId != 0'>
			<tr>
				<th>작성일</th>
				<td class="text_l"><g:text value="${faq.insDttm}" format="L####-##-## ##:##:##"/></td>
				<th>조회수</th>
				<td class="text_l">${faq.hit}</td>
			</tr>
		</s:if>
			<tr>
				<th>내용</th>
				<td colspan="3" class="text_l">
					<textarea id="dscr" class="text_area" name="faq.dscr" 
						v:required m:required="<gm:string value="jsp.community.faq.faqView.msg.check_dscr"/>" 
						v:text8_limit="4000" m:text8_limit="<gm:string value="jsp.community.faq.faqView.msg.check_len_dscr"/>"
					><g:tagBody value="${faq.dscr}" /></textarea>
			<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_SC">
					<p class="pt05">
						※ Html은 A 태그만 입력이 가능합니다. (예: &lt;a href="http://www.tstore.co.kr"&gt;Tstore&lt;/a&gt;)
					</p>
			</s:if>
				</td>
			</tr>
			<tr>
				<th>이미지</th>
				<td colspan="3" class="text_l align_td">
				<div id="divAttFileImg">
					<input onchange="fileImgExtCheck(this);" id="attFileImg" type="file" name="faq.attFileImg" class="btn_s" style="width:40%;" />
					&nbsp;&nbsp;<span class="font_11">1MB이하의 JPG,PNG,GIF 파일 (권장 사이즈: Width 665px 이하)</span>
				</div>
			<c:set var="skipFid" value=""/>
			<s:if test="fileSupportList.size != 0">
				<s:iterator value="fileSupportList" status="status">
					<c:set var="resultType" value="${ftype}"/>
					<c:if test="${resultType=='JPG' or resultType=='JPEG' or resultType=='PNG' or resultType=='GIF'}">
						<script language="javascript">$("#divAttFileImg").css("display", "none");</script>
						<c:set var="skipFid" value="${fid}"/>
						<s:hidden id="asisImgFid" name="faq.asisImgFid" value="%{fid}" />
						<s:hidden id="tobeImgFid" name="faq.tobeImgFid" value="%{fid}" />
				<div id="divDelAttFileImg">
					<img src="${CONF['omp.common.url.http-share.common.faq']}${fnm}" width="37" height="38" 
						onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');" />
					&nbsp;<a class="btn_s" href="javascript:fncDelAttFileImg();"><span>- 삭제</span></a>
					<br/>&nbsp;VIEW FULL URL : ${CONF['omp.common.url.http-share.common.faq']}${fnm}
					<br/>&nbsp;DOWN FULL URL : /devpoc/fileSupport/bbsFileDown.omp?bnsType=common.path.http-share.common.faq&amp;filePath=${fnm}&amp;fileName=${ofnm}
				</div>
					</c:if>
				</s:iterator>
			</s:if>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3" class="text_l align_td">
				<div id="divAttFileEtc">
					<input onchange="fileExtCheck(this);" id="attFileEtc" type="file" name="faq.attFileEtc" class="btn_s" style="width:40%;" />
					&nbsp;&nbsp;<span class="font_11">10MB이하 첨부파일</span>
				</div>
			<s:if test="fileSupportList.size != 0">
				<s:iterator value="fileSupportList" status="status">
					<c:set var="resultFid" value="${fid}"/>
					<c:if test="${resultFid != skipFid}">
						<script language="javascript">$("#divAttFileEtc").css("display", "none");</script>
						<s:hidden id="asisEtcFid" name="faq.asisEtcFid" value="%{fid}" />
						<s:hidden id="tobeEtcFid" name="faq.tobeEtcFid" value="%{fid}" />
				<div id="divDelAttFileEtc">
					${ofnm}
					&nbsp;<a class="btn_s" href="javascript:fncDelAttFileEtc();"><span>- 삭제</span></a>
					<br/>
					&nbsp;DOWN FULL URL : /devpoc/fileSupport/bbsFileDown.omp?bnsType=common.path.http-share.common.faq&amp;filePath=${fnm}&amp;fileName=${ofnm}
				</div>
					</c:if>
				</s:iterator>
			</s:if>
				</td>
			</tr>
			</tbody>
		</table>
		</s:form>
		<s:if test='faq.faqId != 0'>
		<p class="fl mt25"><a class="btn" href="javascript:updateFaqList('D');"><span>삭제</span></a></p>
		<p class="btn_wrap text_r mt25"><a class="btn" id="btnSubmit" href="javascript:processFaq();"><span>저장</span></a>
			<a class="btn" href="javascript:funcFaqList();"><span>목록</span></a></p>
		</s:if>
		<s:if test='faq.faqId == 0'>
		<p class="btn_wrap text_r mt25"><a class="btn" id="btnSubmit" href="javascript:processFaq();"><span>저장</span></a>
			<a class="btn" href="javascript:funcFaqList();"><span>목록</span></a></p>
		</s:if>

</body>
</html>
