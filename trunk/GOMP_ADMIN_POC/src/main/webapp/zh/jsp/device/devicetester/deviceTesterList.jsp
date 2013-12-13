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
			$( "input:checkbox[name='checkTesterId']" ).each( function(i)	{
				if ( $( "#checkAll" ).attr( "checked" ) )	{
					$(this).attr( "checked", true );
				}	else	{
					$(this).attr( "checked", false );
				}
			} );
		} );
	
	} );

	var notiDeviceTesterList = function() {
		//alert("<gm:string value='jsp.device.devicetester.deviceTesterList.msg.none_select' />");
	};
	
	function goPage(no) {
		$("form[name=searchTesterForm] input[name=phoneTester\\.page\\.no]").val(no);
		$( "#searchTesterForm" ).attr("action", "<c:url value="/device/deviceTesterList.omp"/>");
		$( "#searchTesterForm" ).submit();
	}

	var searchTesterList = function( mode ) {

		if( mode != "A") {
			if(showValidate('searchTesterForm', 'dialog', 'Check Input Value.')) {
				$( "#searchTesterForm" ).attr("action", "<c:url value="/device/deviceTesterList.omp"/>");
				$( "#searchTesterForm" ).submit();
			}
		} else {
			$("input[name='phoneTester.searchValue']").val( "" );
			$( "#searchTesterForm" ).attr("action", "<c:url value="/device/deviceTesterList.omp"/>");
			$( "#searchTesterForm" ).submit();
		}
	};

	var insertPhoneTester = function() {
		
		if(showValidate('testerForm', 'dialog', 'Check Input Value.')) {
			if(confirm("<gm:string value='jsp.device.devicetester.deviceTesterList.msg.confirm_ins'/>")) {
				$.blockUI({ message: 'Please Wait.' });
				$( "#testerForm" ).attr("action", "<c:url value="/device/insertPhoneTester.omp"/>");
				$( "#testerForm" ).submit();
			}
		}
	};

	var deleteTester = function( testerId ) {
		
		var selectedTesterId =  testerId;
		$("form[name=testerListForm] input[name=selectedTesterId]").val(selectedTesterId);

		if(confirm("<gm:string value='jsp.device.devicetester.deviceTesterList.msg.confirm_del'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$( "#testerListForm" ).attr("action", "<c:url value="/device/deletePhoneTester.omp"/>");
			$( "#testerListForm" ).submit();
		}
	};

	var deleteTesterList = function() {
		
		
		if(showValidate('testerListForm', 'dialog', 'Check Input Value.')) {
			
			var selectedTesterId =  makeTesterIdStr();
			$("form[name=testerListForm] input[name=selectedTesterId]").val(selectedTesterId);
			
			if(confirm("<gm:string value='jsp.device.devicetester.deviceTesterList.msg.confirm_del'/>")) {
				$.blockUI({ message: 'Please Wait.' });
				$( "#testerListForm" ).attr("action", "<c:url value="/device/deletePhoneTester.omp"/>");
				$( "#testerListForm" ).submit();
			}

		}

	};

	var makeTesterIdStr = function() {
		var ids = "";
		$("input:checkbox[name=checkTesterId]:checked").each(function () {
			ids += "," + $(this).attr("value");
		});
		return ids.substring(1);
	};

</script>

		<div id="location">
			首頁  &gt; 手機管理  &gt; 測試者帳號管理  &gt; <strong>測試者帳號管理 </strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">測試者帳號管理 </h1>
		<p>可搜尋／新增／刪除測試帳號。</p>

		<s:form id="testerForm" theme="simple">
			<s:hidden name="phoneTester.page.no" value="%{phoneTester.page.no}"/>
			<s:hidden id="selectedTesterId" name="selectedTesterId" />
		<table class="tabletype01">
			<colgroup><col style="width:120px;"><col ><col style="width:125px"></colgroup>
			<tr>
				<th>新增測試者帳號</th>
				<td class="line2_5">					
					<label for="#">帳號 </label>
					<input id="testerId" type="text" name="phoneTester.testerId" class="txt" style="width:70%;" 
						v:required m:required="<gm:string value="jsp.device.devicetester.deviceTesterList.msg.check_testerid"/>" /> <br />
					<label for="#">說明</label>
					<input id="dscr" type="text" name="phoneTester.dscr" class="txt" style="width:70%;" 
						v:required m:required="<gm:string value="jsp.device.devicetester.deviceTesterList.msg.check_dscr"/>" />
				</td>
				<td class="text_c" >
					<a class="btn" href="javascript:insertPhoneTester();"><strong>新增</strong></a>
				</td>
			</tr>
		</table>
		</s:form>

		<s:form action="deviceTesterList" id="searchTesterForm" theme="simple">
			<s:hidden name="phoneTester.page.no" value="%{phoneTester.page.no}"/>
			<s:hidden id="selectedTesterId" name="selectedTesterId" />
		<p class="text_r mt25 mb10" style="background:#ededed; padding:10px; border:1px solid #cdcdcd;">
			<label for="#">帳號</label>
			<input id="searchValue" type="text" name="phoneTester.searchValue" class="txt" style="width:150px; vertical-align:middle;" value="${phoneTester.searchValue}" 
				v:required m:required="<gm:string value="jsp.device.devicetester.deviceTesterList.msg.check_search_id"/>" />
			<a class="btn_s" href="javascript:searchTesterList('');"><span>搜尋</span></a>
		</p>
		</s:form>
		
		<s:form id="testerListForm" theme="simple">
			<s:hidden name="phoneTester.page.no" value="%{phoneTester.page.no}"/>
			<s:hidden id="selectedTesterId" name="selectedTesterId" />
			<s:hidden id="searchValue" name="phoneTester.searchValue" value="%{phoneTester.searchValue}" />
		<table class="tabletype02 ">
			<colgroup>
				<col style="width:3%;" >
				<col >
				<col >
				<col >
				<col >
				<col >
			</colgroup>
			<thead>
			<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>序號</th>
				<th>帳號</th>
				<th>說明</th>
				<th>新增時間</th>
				<th>刪除</th>
			</tr>
			</thead>
			<tbody>
			<s:if test="phoneTesterList.size == 0">
			<tr><td colspan='10'><span style="padding-top:3px;*padding-top:2px;"><gm:string value='jsp.device.devicetester.deviceTesterList.msg.none_result'/></span></td></tr>
			</s:if>
			<s:else>
			<c:set var="resultNum" value="${srchCnt - (phoneTester.page.no-1)*10 }"/>
			<s:iterator value="phoneTesterList" status="status">
			<tr>
				<td><input type="checkbox" name="checkTesterId" value="${testerId}" 
					v:mustcheck="1,20" m:mustcheck="<gm:string value="jsp.device.devicetester.deviceTesterList.msg.none_select"/>" /></td>
				<td>${resultNum}</td>
				<td>${testerId}</td>
				<td class="text_l">${dscr}</td>
				<td><g:text value="${regDttm}" format="L####-##-## ##:##:##"/></td>
				<td><a class="btn_s" href="javascript:deleteTester('${testerId}');"><span>刪除</span></a></td>
			</tr>
			<c:set var="resultNum" value="${resultNum-1 }"/>
			</s:iterator>
			</s:else>
			</tbody>
		</table>
		</s:form>
		<s:if test="phoneTesterList.size == 0">
		<p class="fl mt05"><a class="btn_s" href="javascript:notiDeviceTesterList();"><span>批量刪除</span></a></p>
		<!--
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:searchTesterList('A');"><span>目錄</span></a></p>
		-->
		</s:if>
		<s:else>
		<p class="fl mt05"><a class="btn_s" href="javascript:deleteTesterList();"><span>批量刪除</span></a></p>
		<!--
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:searchTesterList('A');"><span>目錄</span></a></p>
		-->
		</s:else>
		<!-- paging -->
			<g:pageIndex item="${phoneTesterList}"/>
		<!-- //paging -->

</body>
</html>
