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
	
		// 전체 체크박스 이벤트
		$( "#checkAll" ).click( function()	{
			$( "input:checkbox[name=checkDtlCd]" ).each( function(i)	{
				if ( $( "#checkAll" ).attr( "checked" ) )	{
					$(this).attr( "checked", true );
				}	else	{
					$(this).attr( "checked", false );
				}
			} );
		} );

		$("#srchGrpType").change( function() {
		    $("#srchGrpValue").val("");
		});

	} );

	function goPage(no) {
		$("form[name=searchCommCdForm] input[name=commCd\\.page\\.no]").val(no);
		$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeList.omp"/>");
		$( "#searchCommCdForm" ).submit();
	}

	var searchCommCdList = function() {
		if(showValidate('commCdForm', 'dialog', 'Check Input Value.', funcCheckForm)) {
			$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeList.omp"/>");
			$( "#searchCommCdForm" ).submit();
		}
	};

	var commCdList = function() {
		$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeList.omp"/>");
		$( "#searchCommCdForm" ).submit();
	};

	var viewCommCd = function( grpCode, dtlCode ) {
		$( "#selectedGrpCd" ).val( grpCode );
		$( "#selectedDtlCd" ).val( dtlCode );
		$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeView.omp"/>");
		$( "#searchCommCdForm" ).submit();
	};

	var viewCommCdDetailList = function( grpCode ) {
		$( "#gubun" ).val( "DETAIL" );
		$( "#srchGrpType" ).val( "" );
		$( "#srchGrpValue" ).val( "" );
		$( "#selectedGrpCd" ).val( grpCode );
		$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeDetailList.omp"/>");
		$( "#searchCommCdForm" ).submit();
	};

	var commCdDetailList = function( grpCode ) {
		$( "#gubun" ).val( "DETAIL" );
		$( "#srchGrpType" ).val( "" );
		$( "#srchGrpValue" ).val( "" );
		$( "#srchDtlType" ).val( "CD" );
		$( "#srchDtlValue" ).val( " " );
		$( "#selectedGrpCd" ).val( "" );
		$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/commonCodeDetailList.omp"/>");
		$( "#searchCommCdForm" ).submit();
	};

	var deleteCommCd = function() {

		if(!showValidate('searchCommCdForm', 'dialog', 'Check Input Value.')) {
			return;
		}
		
		var cntDtlCd = cntCheckDtlCd();

		if(cntDtlCd != "0") {
			alert("<gm:string value='jsp.adminmgr.code.commonCodeList.msg.alert' />");
			return;
		}
		
		var selectedGrpCd = makeGrpCdStr();
		var selectedDtlCd = makeDtlCdStr();

		$( "#selectedGrpCd" ).val( selectedGrpCd );
		$( "#selectedDtlCd" ).val( selectedDtlCd );

		if(confirm("<gm:string value='jsp.adminmgr.code.commonCodeList.msg.confirm_del'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$( "#searchCommCdForm" ).attr("action", "<c:url value="/adminMgr/updateCommCdUseYn.omp"/>");
			$( "#searchCommCdForm" ).submit();
		}
		
	};

	var clearSearchCommCd = function() {

		$( "#srchGrpType" ).val("CD");
		$( "#srchGrpValue" ).val("");

	};

	var makeGrpCdStr = function() {
		var ids = "";
		$("input:checkbox[name=checkDtlCd]:checked").each(function () {
			ids += "," + $(this).attr("grpValue");
		});
		return ids.substring(1);
	};

	var cntCheckDtlCd = function() {
		var ids = 0;
		$("input:checkbox[name=checkDtlCd]:checked").each(function () {
			ids += eval($(this).attr("cntValue"));
		});
		return ids;
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
			if( $("form[name=searchCommCdForm] input[name=commCd\\.srchGrpValue]").val() == "" ) {
				return false;
			}
			return true;
		}
	};

	var funcCodeCache = function() {

		if(!confirm("<gm:string value='jsp.adminmgr.code.commonCodeList.msg.confirm_alert'/>")) {
			return;
		}

		var form = checkCommCdForm;
		var width = 460;
		var height = 200;
	    var x = (screen.width) ? (screen.width-width)/2 : 0;
	    var y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		var scrollOption = "No";
		
		window.open("","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");		

		form.action="<c:url value="/adminMgr/popConfirmCacheCode.omp"/>";
		form.target="popup";
		form.submit();

	};
	
</script>

		<div id="location">
			Home &gt; 운영자관리 &gt; 기준정보관리 &gt; <strong>공통코드관리</strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">공통코드 관리</h1>
		<p>공통코드를 조회하고 수정 및 삭제할 수 있습니다.</p>
		<s:form action="popConfirmCacheCode" id="checkCommCdForm" name="checkCommCdForm" theme="simple">
		</s:form>
		<s:form action="commonCodeList" id="commCdForm" name="commCdForm" theme="simple">
			<input type="hidden" id="checkSearchGrpValue" name="checkSearchGrpValue" 
				v:checkgrpvalue m:checkgrpvalue="<gm:string value="jsp.adminmgr.code.commonCodeList.msg.check_search"/>" />
		</s:form>
		<s:form action="commonCodeList" id="searchCommCdForm" name="searchCommCdForm" theme="simple">
			<s:hidden id="gubun" name="commCd.gubun" value="GROUP" />
			<s:hidden id="selectedGrpCd" name="commCd.selectedGrpCd" />
			<s:hidden id="selectedDtlCd" name="commCd.selectedDtlCd" />
			<s:hidden id="srchDtlType" name="commCd.srchDtlType" />
			<s:hidden id="srchDtlValue" name="commCd.srchDtlValue" />
			<s:hidden id="pageNo" name="commCd.page.no" value="%{commCd.page.no}"/>
		<table class="tabletype01">
			<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
			<tr>
				<th>검색어</th>
				<td class="align_td">
					<s:select list="result.selectMap" id="srchGrpType" name="commCd.srchGrpType" style="width:90px;"  theme="simple" />
					<input id="srchGrpValue" type="text" name="commCd.srchGrpValue" class="txt" style="width:250px;" value="${commCd.srchGrpValue}" />
				</td>
				<td class="text_c" >
					<a class="btn" href="javascript:searchCommCdList();"><span>검색</span></a>
					<a class="btn" href="javascript:clearSearchCommCd();"><span>검색초기화</span></a>
				</td>
			</tr>
			<tr>
				<th>코드초기화</th>
				<td colspan="3" class="align_td">
					<a class="btn_s" href="javascript:funcCodeCache();"><span>코드캐쉬초기화</span></a>
					&nbsp;&nbsp;<span><strong><font color="#DF2020">* 코드캐쉬초기화 전에는 등록, 수정, 삭제된 코드가 서버에 반영되지 않습니다.</font></strong></span>
				</td>
			</tr>
		</table>
		<div class="tab">
			<ul>
				<li class="on"><a href="javascript:commCdList();">그룹코드</a></li>
				<li><a href="javascript:commCdDetailList();">상세코드</a></li>
			</ul>
		</div>

		<table class="tabletype02 mt20">
			<colgroup>
				<col style="width:3%;" >
				<col style="width:50px;" />
				<col style="width:100px;" />
				<col />
				<col style="width:100px;" />
			</colgroup>
			<tbody>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>번호</th>
				<th>그룹코드</th>
				<th>코드명</th>
				<th>상세코드 개수</th>
			</tr>
			<s:if test="commCdList.size == 0">
			<tr><td colspan='5'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.adminmgr.code.commonCodeList.msg.none_result'/></span></td></tr>
			</s:if>
			<s:else>
			<c:set var="resultNum" value="${srchCnt - (commCd.page.no-1)*10 }"/>
			<s:iterator value="commCdList" status="status">
			<tr>
				<td><input type="checkbox" name="checkDtlCd" cntValue="${cntDtlCd}" grpValue="${grpCd}" value="${dtlCd}" 
					v:mustcheck="1,20" m:mustcheck="<gm:string value="jsp.adminmgr.code.commonCodeList.msg.none_select_cd"/>" /></td>
				<td>${resultNum}</td>
				<td><a href="javascript:viewCommCd('${grpCd}','${dtlCd}');"><strong>${dtlCd}</strong></a></td>
				<td class="text_l">${cdNm}</td>
				<td><a href="javascript:viewCommCdDetailList('${dtlCd}');">${cntDtlCd}</a></td>
			</tr>
			<c:set var="resultNum" value="${resultNum-1 }"/>
			</s:iterator>
			</s:else>
		</table>
		<div class="overflow_h">
			<div class="fl mt10">
				<a class="btn_s" href="javascript:deleteCommCd();"><strong>선택삭제</strong></a>
			</div>
			<p class="fr mt10"><a class="btn" href="javascript:viewCommCd('999999','');"><span>등록</span></a></p>
		</div>
		</s:form>

		<!-- paging -->
			<g:pageIndex item="${commCdList}"/>
		<!-- //paging -->

</body>
</html>
