<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" 
%><%@ taglib prefix="s" uri="/struts-tags" 
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core" 
%><%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<script type="text/javascript">

	$(document).ready( function()	{

		$( "#checkAll" ).click( function()	{
			$( "input:checkbox[name=checkMgrId]" ).each( function(i)	{
				if ( $( "#checkAll" ).attr( "checked" ) )	{
					$(this).attr( "checked", true );
				}	else	{
					$(this).attr( "checked", false );
				}
			} );
		} );

		$("#searchType").change( function() {
		    $("#searchValue").val("");
		});

	} );

	function goPage(no) {
		$("form[name=searchAccountForm] input[name=adMgr\\.page\\.no]").val(no);
		$( "#searchAccountForm" ).submit();
	}

	var funcAccountList = function() {
		//if(showValidate('ckeckForm', 'dialog', 'Check Input Value.', funcCheckForm)) {
			$("form[name=searchAccountForm] input[name=adMgr\\.page\\.no]").val("1");
			$( "#searchAccountForm" ).submit();
		//}
	};

	var makeAccountStr = function() {
		var ids = "";
		$("input:checkbox[name=checkMgrId]:checked").each(function () {
			ids += "," + $(this).attr("value");
		});
		return ids.substring(1);
	};

	var popAdMgrAuth = function(mgrId) {
		
		var form = popAdMgrAuthForm;
		width = 480;
		height = 600;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		scrollOption = "Yes";
		
		window.open("","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");		
						
		form.action="${pageContext.request.contextPath }/adminMgr/popAccountAuth.omp?selectedAccount=" + mgrId;
		form.target="popup";
		form.submit();
	};

	var popAdMgrAuthList = function() {

		if(!showValidate('searchAccountForm', 'dialog', 'Check Input Value.')) {
			return;
		}
		
		var selectedAccount =  makeAccountStr();
		//alert("SELECTED ID : " +selectedAccount);
		$("#selectedAccount").val(selectedAccount);

		var form = popAdMgrAuthForm;
		width = 480;
		height = 600;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		scrollOption = "Yes";
		
		window.open("","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");		
						
		form.action="${pageContext.request.contextPath }/adminMgr/popAccountAuth.omp?selectedAccount=" + selectedAccount;
		form.target="popup";
		form.submit();
	};

	var accountView = function( mgrId ) {
		$( "#mgrId" ).val( mgrId );
		$( "#searchAccountForm" ).attr("action", "<c:url value="/adminMgr/accountView.omp"/>");
		$( "#searchAccountForm" ).submit();
	};

	var deleteAccount = function(mgrId) {
		$( "#selectedAccount" ).val( mgrId );
		if(confirm("<gm:string value='jsp.adminmgr.account.accountList.msg.confirm_del'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$( "#searchAccountForm" ).attr("action", "<c:url value="/adminMgr/deleteAdMgr.omp"/>");
			$( "#searchAccountForm" ).submit();
		}
	};

	var deleteAccountList = function() {
		
		if(showValidate('searchAccountForm', 'dialog', 'Check Input Value.')) {
			
			var selectedAccount =  makeAccountStr();
			// alert("SELECTED ID : " +selectedAccount);
			$("#selectedAccount").val(selectedAccount);
			
			if(confirm("<gm:string value='jsp.adminmgr.account.accountList.msg.confirm_del'/>")) {
				$.blockUI({ message: 'Please Wait.' });
				$( "#searchAccountForm" ).attr("action", "<c:url value="/adminMgr/deleteAdMgr.omp"/>");
				$( "#searchAccountForm" ).submit();
			}

		}
		
	};

	var funcCheckForm = {
		checkvalue : function() {
			if( $("form[name=searchAccountForm] input[name=adMgr\\.searchValue]").val() == "" ) {
				return false;
			}
			return true;
		}
	};

</script>

		<div id="location">
			首頁  &gt; 管理者中心  &gt; 帳號權限管理  &gt; <strong>搜尋帳號 </strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">搜尋帳號</h1>
		<p>可搜尋管理者帳號, 並進行變更或刪除。</p>

		<s:form action="popAccountAuth" id="popAdMgrAuthForm" name="popAdMgrAuthForm"  theme="simple">
			<input type="hidden" id="authMgrId" name="authMgrId" />
		</s:form>

		<s:form id="ckeckForm" name="ckeckForm" theme="simple">
			<input type="hidden" id="checkSearchValue" name="checkSearchValue" 
				v:checkvalue m:checkvalue="<gm:string value="jsp.adminmgr.account.accountList.msg.check_search"/>" />
		</s:form>

		<s:form action="accountList" id="searchAccountForm" theme="simple">
			<s:hidden id="selectedAccount" name="selectedAccount" />
			<s:hidden id="pageNo" name="adMgr.page.no" value="%{adMgr.page.no}"/>
			<s:hidden id="mgrId" name="adMgr.mgrId" />
		<p class="text_r mt10 mb10" style="background:#ededed; padding:10px; border:1px solid #cdcdcd;">
			<s:select list="result.selectMap" id="searchType" name="adMgr.searchType" style="width:85px;  vertical-align:middle;" />
			<input id="searchValue" type="text" name="adMgr.searchValue" class="txt" style="width:150px;  vertical-align:middle;" value="${adMgr.searchValue}" />
			<a class="btn_s" href="javascript:funcAccountList();"><span>搜尋</span></a>
		</p>

		<p class="fr mt10">您共新增  <strong class="point2">${srchCnt}</strong> 個帳號。</p>
		<table class="tabletype02 ">
			<colgroup>
				<col style="width:3%;" >
				<col >
				<col >
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
				<th>姓名</th>
				<th>公司名稱</th>
				<th>行動電話</th>
				<th>新增日期</th>
				<th>管理</th>
			</tr>
			</thead>
			<tbody>
			<s:if test="adMgrList.size == 0">
			<tr><td colspan='8'><span style="padding-top:3px;*padding-top:2px;"><gm:string value="jsp.adminmgr.account.accountList.msg.none_result" /></span></td></tr>
			</s:if>
			<s:else>
			<c:set var="resultNum" value="${srchCnt - (adMgr.page.no-1)*10 }"/>
			<s:iterator value="adMgrList" status="status">
			<tr>
				<td><input type="checkbox" name="checkMgrId" value="${mgrId}"
					v:mustcheck="1,20" m:mustcheck="<gm:string value="jsp.adminmgr.account.accountList.msg.none_select_id"/>" /></td>
				<td>${resultNum}</td>
				<td class="text_l"><a href="javascript:accountView('${mgrId}');"><strong>${mgrId}</strong></a></td>
				<td>${mgrNm}</td>
				<td>${compNm}</td>
				<td>${hp1No}-${hp2No}-${hp3No}</td>
				<td><g:text value="${regDt}" format="L####-##-## ##:##:##"/></td>
				<td><a class="btn_s" href="javascript:popAdMgrAuth('${mgrId}')"><span>權限設定</span></a> 
				<a class="btn_s" href="javascript:deleteAccount('${mgrId}')"><span>刪除</span></a></td>
			</tr>
			<c:set var="resultNum" value="${resultNum-1 }"/>
			</s:iterator>
			</s:else>
			</tbody>
		</table>
		</s:form>
		<p class="text_l mt05"><a class="btn_s" href="javascript:popAdMgrAuthList();"><span>權限設定</span></a>
			<a class="btn_s" href="javascript:deleteAccountList()"><span>刪除</span></a></p>
		<!-- paging -->
			<g:pageIndex item="${adMgrList}"/>
		<!-- //paging -->

</body>
</html>
