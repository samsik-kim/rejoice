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

<script type="text/javascript">

	$(document).ready( function()	{
	
		$( "#checkAll" ).click( function()	{
			$( "input:checkbox[name=checkDtlCd]" ).each( function(i)	{
				if ( $( "#checkAll" ).attr( "checked" ) )	{
					$(this).attr( "checked", true );
				}	else	{
					$(this).attr( "checked", false );
				}
			} );
		} );

		$("#srchDtlType").change( function() {
		    $("#srchDtlValue").val("");
		});

	} );

	function goPage(no) {
		$("form[name=searchCommCdForm] input[name=commCd\\.page\\.no]").val(no);
		$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeDetailList.omp"/>");
		$( "#searchCommCdForm" ).submit();
	}

	var searchCommCdDetailList = function() {

		//if(showValidate('commCdForm', 'dialog', 'Check Input Value.', funcCheckForm)) {
			$("form[name=searchCommCdForm] input[name=commCd\\.page\\.no]").val("1");
			$( "#selectedGrpCd" ).val( "" );
			$( "#srchDtlValue").val( jQuery.trim($("#srchDtlValue").val()) );
			$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeDetailList.omp"/>");
			$( "#searchCommCdForm" ).submit();
		//}

	};

	var viewCommCdDetail = function( grpCode, dtlCode ) {
		$( "#selectedGrpCd" ).val( grpCode );
		$( "#selectedDtlCd" ).val( dtlCode );
		$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeDetailView.omp"/>");
		$( "#searchCommCdForm" ).submit();
	};
	
	var viewCommCdModify = function( grpCode, dtlCode ) {
		$( "#selectedGrpCd" ).val( grpCode );
		$( "#selectedDtlCd" ).val( dtlCode );
		$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeDetailModify.omp"/>");
		$( "#searchCommCdForm" ).submit();
	};	

	var viewCommCdList = function( ) {
		$( "#gubun" ).val( "GROUP" );
		$( "#srchDtlType" ).val( "CD" );
		$( "#srchDtlValue" ).val( "" );
		$( "#selectedGrpCd" ).val( "" );
		$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeList.omp"/>");
		$( "#searchCommCdForm" ).submit();
	};

	var deleteCommCd = function() {

		if(!showValidate('searchCommCdForm', 'dialog', 'Check Input Value.')) {
			return;
		}
		
		var selectedGrpCd = makeGrpCdStr();
		var selectedDtlCd = makeDtlCdStr();

		$( "#selectedGrpCd" ).val( selectedGrpCd );
		$( "#selectedDtlCd" ).val( selectedDtlCd );

		if(confirm("<gm:string value='jsp.adminmgr.code.commonCodeDetailList.msg.confirm_del'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/updateCommCdDetailUseYn.omp"/>");
			$( "#searchCommCdForm" ).submit();
		}
		
	};

	var clearSearchCommCd = function() {

		$(":input:radio[name=commCd.srchUseYn]").filter('input:radio[value=""]').attr("checked", "checked");
		$( "#srchDtlType" ).val("CD");
		$( "#srchDtlValue" ).val("");

	};

	var makeGrpCdStr = function() {
		var ids = "";
		$("input:checkbox[name=checkDtlCd]:checked").each(function () {
			ids += "," + $(this).attr("grpValue");
		});
		return ids.substring(1);
	};

	var makeDtlCdStr = function() {
		var ids = "";
		$("input:checkbox[name=checkDtlCd]:checked").each(function () {
			ids += "," + $(this).attr("value");
		});
		return ids.substring(1);
	};

	var funcCheckForm = {
		checkgrpvalue : function() {
			if( $("form[name=searchCommCdForm] input[name=commCd\\.srchDtlValue]").val() == "" ) {
				return false;
			}
			return true;
		}
	};

</script>

		<div id="location">
			首頁  &gt; 管理者中心  &gt; 標準資訊管理  &gt; <strong>共同編碼管理</strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">共同編碼管理</h1>
		<p>可搜尋共同編碼,並進行變更或刪除。</p>
		<s:form action="commonCodeList" id="commCdForm" name="commCdForm" theme="simple">
			<input type="hidden" id="checkSearchGrpValue" name="checkSearchGrpValue" 
				v:checkgrpvalue m:checkgrpvalue="<gm:string value="jsp.adminmgr.code.commonCodeDetailList.msg.check_search"/>" />
		</s:form>
		<s:form action="commonCodeList" id="searchCommCdForm" name="searchCommCdForm" theme="simple">
			<s:hidden id="gubun" name="commCd.gubun" value="DETAIL" />
			<s:hidden id="selectedGrpCd" name="commCd.selectedGrpCd" />
			<s:hidden id="selectedDtlCd" name="commCd.selectedDtlCd" />
			<s:hidden id="pageNo" name="commCd.page.no" value="%{commCd.page.no}"/>
		<table class="tabletype01">
			<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
			<tr>
				<th>使用與否</th>
				<td class="align_td">
					<s:radio list="result.radioMap" id="srchUseYn" name="commCd.srchUseYn" cssClass="ml05" />
				</td>
				<td rowspan="3" class="text_c" >
					<a class="btn" href="javascript:searchCommCdDetailList();"><span>搜尋</span></a><br/>
					<a class="btn" href="javascript:clearSearchCommCd();"><span>重新搜尋</span></a>
				</td>
			</tr>
			<tr>
				<th>關鍵字</th>
				<td class="align_td">
					<s:select list="result.selectMap" id="srchDtlType" name="commCd.srchDtlType" style="width:90px;"  theme="simple" />
					<input id="srchDtlValue" type="text" name="commCd.srchDtlValue" class="txt" style="width:250px;" value="${commCd.srchDtlValue}" onkeydown="javascript: if(event.keyCode == 13) {searchCommCdDetailList();}" />
				</td>
			</tr>
		</table>
		<div class="tab">
			<ul>
				<li><a href="javascript:viewCommCdList();">群組碼</a></li>
				<li class="on"><a href="#">詳細碼</a></li>
			</ul>
		</div>

		<table class="tabletype02 mt20">
			<colgroup>
				<col style="width:3%;" >
				<col style="width:50px;" />
				<col style="width:100px;" />
				<col style="width:100px;" />
				<col />
				<col style="width:80px;" />
				<col style="width:80px;" />
			</colgroup>
			<tbody>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>序號</th>
				<th>群組碼</th>
				<th>詳細碼</th>
				<th>編碼名稱</th>
				<th>編碼順序</th>
				<th>使用與否</th>
			</tr>
			<s:if test="commCdList.size == 0">
			<tr><td colspan='5'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.adminmgr.code.commonCodeDetailList.msg.none_result'/></span></td></tr>
			</s:if>
			<s:else>
			<c:set var="resultNum" value="${srchCnt - (commCd.page.no-1)*10 }"/>
			<s:iterator value="commCdList" status="status">
			<tr>
				<td><input type="checkbox" name="checkDtlCd" grpValue="${grpCd}" value="${dtlCd}"
					v:mustcheck="1,20" m:mustcheck="<gm:string value="jsp.adminmgr.code.commonCodeDetailList.msg.none_select_code"/>" /></td>
				<td>${resultNum}</td>
				<td>${grpCd}</td>
				<td><a href="javascript:viewCommCdModify('${grpCd}','${dtlCd}');">${dtlCd}</a></td>
				<td class="text_l">${cdNm}</td>
				<td>${displayOrder}</td>
				<td><c:if test="${useYn eq 'Y' }">使用</c:if><c:if test="${useYn eq 'N' }">未使用</c:if></td>
			</tr>
			<c:set var="resultNum" value="${resultNum-1 }"/>
			</s:iterator>
			</s:else>
		</table>
		</s:form>
		<div class="overflow_h">
			<div class="fl mt10">
				<a class="btn_s" href="javascript:deleteCommCd();"><strong>選擇刪除</strong></a>
			</div>
			<!--
			<p class="fr mt10"><a class="btn" href="javascript:viewCommCdDetail('${commCd.selectedGrpCd}','');"><span>儲存</span></a></p>
			-->
			<p class="fr mt10"><a class="btn" href="javascript:viewCommCdDetail('${commCd.selectedGrpCd}','');"><span>新增</span></a></p>
		</div>

		<!-- paging -->
			<g:pageIndex item="${commCdList}"/>
		<!-- //paging -->

</body>
</html>
