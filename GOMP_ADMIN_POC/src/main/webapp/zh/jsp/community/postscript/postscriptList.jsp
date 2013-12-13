<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld" %>
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

		$( "#startDate" ).datepicker();
		$( "#endDate" ).datepicker();
		
		if( $("#startDate").val() == "" )	{
			setOrderSearchDateAdminPoC('today', postscriptForm.startDate, postscriptForm.endDate);
		}

		$( "#checkAll" ).click( function()	{
			$( "input:checkbox[name=dpProdNoti.notiNo]" ).each( function(i)	{
				if ( $( "#checkAll" ).attr( "checked" ) )	{
					if ( !$(this).attr( "disabled" ) )	{
						$(this).attr( "checked", true );
					}					
					//$(this).attr( "checked", true );
				}	else	{
					$(this).attr( "checked", false );
				}
			} );
		} );

		if ( '${badnotiYn}' == '' )	{
			$( "input[name=badnotiYn]" ).filter( 'input[value=]' ).attr( "checked", "checked" );
		}
		if ( '${delYn}' == '' )	{
			$( "input[name=delYn]" ).filter( 'input[value=]' ).attr( "checked", "checked" );
		}

		$("#searchType").change( function() {
		    $("#searchValue").val("");
		});

	} );

	var searchPostsctiptList = function() {
		
		if(showValidate('ckeckForm', 'dialog', 'Check Input Value.', funcCheckForm)) {
			$("form[name=postscriptForm] input[name=dpProdNoti\\.page\\.no]").val("1");
			$( "#postscriptForm" ).attr("action", "<c:url value="/community/postscriptList.omp"/>");
			document.postscriptForm.target="_self";
			$( "#postscriptForm" ).submit();
		}

	};

	var clearSrchCond = function( mode ) {
		$(":input:radio[name=dpProdNoti.badnotiYn]").filter('input:radio[value=""]').attr("checked", "checked");
		$(":input:radio[name=dpProdNoti.delYn]").filter('input:radio[value=""]').attr("checked", "checked");
		setOrderSearchDateAdminPoC('today', postscriptForm.startDate, postscriptForm.endDate);
		$("#searchType").val("byMbrId");
		$("#searchValue").val("");
	};

	var badNotiView = function(badnotiYn, notiNo) {
		
		var form = postscriptForm;
		width = 480;
		if(badnotiYn == "N") {
			height = 270;
		} else {
			height = 260;
		}
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		scrollOption = "No";
		
		window.open("","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");		

		if(badnotiYn == "N") {
			form.action="${pageContext.request.contextPath }/community/popBadnotiRegist.omp?notiNo=" + notiNo;
		} else {
			form.action="${pageContext.request.contextPath }/community/popBadnotiView.omp?notiNo=" + notiNo + "&badnotiYn=" + badnotiYn;
		}
		form.target="popup";
		form.submit();
	};

	var makeNotiNoStr = function() {
		var ids = "";
		$("input:checkbox[name=checkNotiNo]:checked").each(function () {
			ids += "," + $(this).attr("value");
		});
		return ids.substring(1);
	};

	function registBadNotiList() {

		if(!showValidate('postscriptForm', 'dialog', 'Check Input Value.')) {
			return;
		}

		var selectedNotiNo =  makeNotiNoStr();

		var form = postscriptForm;
		width = 480;
		height = 270;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		scrollOption = "No";
		
		window.open("","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");		

		form.action="${pageContext.request.contextPath }/community/popBadnotiRegist.omp?selectedNotiNo=" + selectedNotiNo;
		form.target="popup";
		form.submit();
	};

	function updateProdNotiDelYn( delYn ) {
		
		if(!showValidate('postscriptForm', 'dialog', 'Check Input Value.')) {
			return;
		}

		var selectedNotiNo =  makeNotiNoStr();

		var form = postscriptForm;
		form.action="${pageContext.request.contextPath }/community/updateProdNotiDelYn.omp";

		$( "#delYn" ).val( delYn );
		$( "#selectedNotiNo" ).val( selectedNotiNo );
		if(confirm("<gm:string value='jsp.community.postscript.postscriptList.msg.confirm_mod'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			document.postscriptForm.target="_self";
			$( "#postscriptForm" ).submit();
		}
	};

	function goPage(no) {
		$("form[name=postscriptForm] input[name=dpProdNoti\\.page\\.no]").val(no);
		$( "#postscriptForm" ).attr("action", "<c:url value="/community/postscriptList.omp"/>");
		document.postscriptForm.target="_self";
		$( "#postscriptForm" ).submit();
	};

	var funcCheckForm = {
		checkdatevalue : function() {
			if( $("form[name=postscriptForm] input[name=dpProdNoti\\.startDate]").val() > $("form[name=postscriptForm] input[name=dpProdNoti\\.endDate]").val() ) {
				return	false;
			}
			return true;
		}
	};

</script>

		<div id="location">
			首頁  &gt; 客戶服務中心  &gt; 評論管理  &gt; <strong>評論管理</strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">評論管理</h1>
		<p>可檢查產品評論與回貼。</p>
		<s:form id="ckeckForm" name="ckeckForm" theme="simple">
			<input type="hidden" id="checkDateValue" name="checkDateValue" 
				v:checkdatevalue m:checkdatevalue="<gm:string value="jsp.community.postscript.postscriptList.msg.check_date"/>" />
		</s:form>
		<s:form action="postscriptList" id="postscriptForm" theme="simple">
			<s:hidden id="srchFlag" name="srchFlag" value="TRUE"/>
			<s:hidden id="pageNo" name="dpProdNoti.page.no" value="%{dpProdNoti.page.no}" />
			<s:hidden id="selectedNotiNo" name="selectedNotiNo" />
			<s:hidden id="delYn" name="dpProdNoti.delYn" />
		<table class="tabletype01">
			<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
			<tr>
				<th>搜尋條件</th>
				<td class="align_td">
					<label for="#">狀態</label>
					<s:radio list="result.radioMap1" name="dpProdNoti.srchBadnotiYn" cssClass="ml05" />
					<br />
					<label for="#">公開與否</label>
					<s:radio list="result.radioMap2" name="dpProdNoti.srchDelYn" cssClass="ml05" />
					<br />

					<label for="#">搜尋期間</label>
					<input type="text" id="startDate" name="dpProdNoti.startDate" value="<g:text value='${dpProdNoti.startDate}' format='L####-##-##'/>" class="txt" style="width:80px;" maxlength="10" readonly/>
					&nbsp; ~ &nbsp;
					<input type='text' id="endDate" name="dpProdNoti.endDate" value="<g:text value='${dpProdNoti.endDate}' format='L####-##-##'/>" class="txt" style="width:80px;" maxlength="10" readonly/>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('today', postscriptForm.startDate, postscriptForm.endDate);"><strong>今日</strong></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('7day', postscriptForm.startDate, postscriptForm.endDate);"><span>最近一周</span></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('14day', postscriptForm.startDate, postscriptForm.endDate);"><span>最近二周</span></a>
					<a class="btn_s" href="javascript:setOrderSearchDateAdminPoC('1month', postscriptForm.startDate, postscriptForm.endDate);"><span>最近一個月</span></a>
				</td>
				<td rowspan="2" class="text_c" >
					<a class="btn" href="javascript:searchPostsctiptList();"><strong>搜尋</strong></a>
					<a class="btn" href="javascript:clearSrchCond();"><span>重新搜尋</span></a>
				</td>
			</tr>
			<tr>
				<th>搜索關鍵字</th>
				<td class="align_td">
					<s:select list="result.selectMap" id="searchType" name="dpProdNoti.searchType" style="width:104px;" />
					<input id="searchValue" type="text" name="dpProdNoti.searchValue" class="txt" value="${dpProdNoti.searchValue}" style="width:200px;" />
				</td>
			</tr>
		</table>

		<table class="tabletype02 mt25">
			<colgroup>
				<col style="width:3%;" >
				<col >
				<col >
				<col style="width:300px;">
				<col >
				<col >
				<col >
			</colgroup>
			<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>序號</th>
				<th>產品名稱</th>
				<th>產品評論</th>
				<th>狀態</th>
				<th>公開與否</th>
				<th>檢舉</th>
			</tr>
			</thead>
			<tbody>
			<s:if test="srchFlag != 'TRUE'">
			<tr><td colspan='7'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.admin.common.first.page'/></span></td></tr>
			</s:if>
			<s:else>
				<s:if test="dpProdNotiList.size == 0">
			<tr><td colspan='7'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.community.postscript.postscriptList.msg.none_result'/></span></td></tr>
				</s:if>
				<s:else>
				<s:iterator value="dpProdNotiList" status="status">
			<tr>
				<td>
					<c:if test="${badnotiYn eq 'Y' }">
					<input disabled type="checkbox" name="checkNotiNo" value="${notiNo}"
					v:mustcheck="1,20" m:mustcheck="<gm:string value="jsp.community.postscript.postscriptList.msg.none_select"/>" />
					</c:if>
					<c:if test="${badnotiYn eq 'N' }">
					<input type="checkbox" name="checkNotiNo" value="${notiNo}"
					v:mustcheck="1,20" m:mustcheck="<gm:string value="jsp.community.postscript.postscriptList.msg.none_select"/>" />
					</c:if>
				</td>
				<td><s:property value="#status.index+1 + ((dpProdNoti.page.no - 1) * 10)"/></td>
				<td>${prodNm}</td>
				<td class="text_l">
					<strong>${mbrId}</strong> [<g:text value="${regDts}" format="L####-##-## ##:##:##"/>]<br />
					<g:tagBody value="${notiDscr}" />
				</td>
				<td><c:if test="${badnotiYn eq 'Y' }">惡性</c:if><c:if test="${badnotiYn eq 'N' }">正常</c:if></td>
				<td><c:if test="${delYn eq 'Y' }">隱藏</c:if><c:if test="${delYn eq 'N' }">公開</c:if></td>
				<td><a class="btn_s" href="javascript:badNotiView('${badnotiYn}', '${notiNo}', '${mbrNo}');">
					<span><c:if test="${badnotiYn eq 'Y' }">檢舉緣由</c:if>
					<c:if test="${badnotiYn eq 'N' }">檢舉</c:if></span></a></td>
			</tr>
				</s:iterator>
				</s:else>
			</s:else>
			</tbody>
		</table>
		</s:form>

		<s:if test="dpProdNotiList.size != 0">
		<p class="text_l mt05">
			<a class="btn_s" href="javascript:registBadNotiList();"><span>檢舉</span></a>			
			<a class="btn_s" href="javascript:updateProdNotiDelYn('N');"><span>公開</span></a>			
			<a class="btn_s" href="javascript:updateProdNotiDelYn('Y');"><span>隱藏</span></a>			
		</p>
		</s:if>
		<!-- paging -->
			<g:pageIndex item="${dpProdNotiList}"/>
		<!-- //paging -->

</body>
</html>
