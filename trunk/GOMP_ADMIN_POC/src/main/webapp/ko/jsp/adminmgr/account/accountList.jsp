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

		// 전체 체크박스 이벤트
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
		//alert("선택된 아이디:" +selectedAccount);
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
			// alert("선택된 아이디:" +selectedAccount);
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
			Home &gt; 운영자관리 &gt; 계정권한관리 &gt; <strong>계정조회</strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">계정조회</h1>
		<p>운영자를 조회하고 수정/삭제 합니다.</p>

		<s:form action="popAccountAuth" id="popAdMgrAuthForm" name="popAdMgrAuthForm"  theme="simple">
			<input type="hidden" id="authMgrId" name="authMgrId" />
		</s:form>

		<s:form id="ckeckForm" name="ckeckForm" theme="simple">
			<input type="hidden" id="checkSearchValue" name="checkSearchValue" v:checkvalue m:checkvalue="<gm:string value="jsp.adminmgr.account.accountList.msg.check_search"/>" />
		</s:form>

		<s:form action="accountList" id="searchAccountForm" theme="simple">
			<s:hidden id="selectedAccount" name="selectedAccount" />
			<s:hidden id="pageNo" name="adMgr.page.no" value="%{adMgr.page.no}"/>
			<s:hidden id="mgrId" name="adMgr.mgrId" />
		<p class="text_r mt10 mb10" style="background:#ededed; padding:10px; border:1px solid #cdcdcd;">
			<s:select list="result.selectMap" id="searchType" name="adMgr.searchType" style="width:85px;  vertical-align:middle;" />
			<input id="searchValue" type="text" name="adMgr.searchValue" class="txt" style="width:150px;  vertical-align:middle;" value="${adMgr.searchValue}" />
			<a class="btn_s" href="javascript:funcAccountList();"><span>검색</span></a>
		</p>

		<p class="fr mt10">총 <strong class="point2">${srchCnt}</strong>개의 아이디가 운영자 계정으로 등록되어 있습니다.</p>
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
				<th>번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th>회사명</th>
				<th>휴대폰번호</th>
				<th>생성일시</th>
				<th>관리</th>
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
				<td><a class="btn_s" href="javascript:popAdMgrAuth('${mgrId}')"><span>권한설정</span></a> 
				<a class="btn_s" href="javascript:deleteAccount('${mgrId}')"><span>삭제</span></a></td>
			</tr>
			<c:set var="resultNum" value="${resultNum-1 }"/>
			</s:iterator>
			</s:else>
			</tbody>
		</table>
		</s:form>
		<p class="text_l mt05"><a class="btn_s" href="javascript:popAdMgrAuthList();"><span>권한설정</span></a>
			<a class="btn_s" href="javascript:deleteAccountList()"><span>삭제</span></a></p>
		<!-- paging -->
			<g:pageIndex item="${adMgrList}"/>
		<!-- //paging -->

</body>
</html>
