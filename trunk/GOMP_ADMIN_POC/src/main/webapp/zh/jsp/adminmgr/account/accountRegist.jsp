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

	$(document).ready(function(){

	});

	function funcProcessAdMgr() {
		
		if(showValidate('adMgrForm', 'dialog', 'Check Input Value.', funcCheckForm)) {
			if(confirm("<gm:string value='jsp.adminmgr.account.accountRegist.msg.confirm_ins'/>")) {
				$( "#adMgrForm" ).attr("action", "${CONF['omp.common.url-prefix.https.admin']}${pageContext.request.contextPath}/adminMgr/insertAdMgr.omp");				
				$.blockUI({ message: 'Please Wait.' });
				$("#adMgrForm").submit();
			}
		}
		
	};

	var funcCheckForm = {
		checkmgrid : function() {
			var vMgrId = $("form[name=adMgrForm] input[name=adMgr\\.mgrId]").val();
			var regExp=/^[a-z][a-z0-9_-]{5,12}$/;
			if(!regExp.test( vMgrId )) {
				return false;
			}
			$("form[name=popAdMgrForm] input[name=chkMgrId]").val( vMgrId );
			//alert($("form[name=popAdMgrForm] input[name=chkMgrId]").val());
			return true;
		},
		checkemail : function() {
			var vEmailAddr = $('#emailAddrId').val() + "@" + $("#emailAddr").val();
			//alert(vEmailAddr);
			if (!(vEmailAddr.search(/^\s*[\w\~\-\.]+\@[\w\~\-]+(\.[\w\~\-]+)+\s*$/) >= 0)) {
				return false;
			}
			return true;
		}
	};

	var funcAccountList	= function()	{
		var url	= "${pageContext.request.contextPath}/adminMgr/accountList.omp";
		location.href = url;
	};

	function funcPopCheckAccount() {
		
		if(!showValidate('popAdMgrForm', 'dialog', 'Check Input Value.', funcCheckForm)) {
			return;
		}

		var form = popAdMgrForm;
		width = 480;
		height = 200;
	    x = (screen.width) ? (screen.width-width)/2 : 0;
	    y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		scrollOption = "No";
		
		window.open("","popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");		
						
		form.action="popCheckAccount.omp?mgrId=" + $("#chkMgrId").val();
		form.target="popup";
		form.submit();
	};

</script>

		<div id="location">
			首頁  &gt; 管理者中心  &gt; 帳號權限管理  &gt; <strong>新增帳號</strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">新增帳號</h1>
		<p>新增管理者帳號。</p>
		<s:form action="popCheckAccount" id="popAdMgrForm" name="popAdMgrForm"  theme="simple">
			<input type="hidden" id="chkMgrId" name="chkMgrId" v:checkmgrid m:checkmgrid="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_id"/>" /> 
		</s:form>
		<s:form action="insertAdMgr" id="adMgrForm" name="adMgrForm"  theme="simple">
		<table class="tabletype01">
			<colgroup>
				<col style="width:20%;"><col  style="width:30%;">
				<col style="width:20%;"><col  style="width:30%;">
			</colgroup>
			<tr>
				<th>帳號</th>
				<td colspan="3" class="align_td">
					<input id="mgrId" type="text" name="adMgr.mgrId" class="txt" style="width:200px;" maxlength="12" 
						v:required m:required="<gm:string value="jsp.adminmgr.account.accountRegist.msg.insert_id"/>" />
					<a class="btn_s" href="javascript:funcPopCheckAccount();"><span>確認重複與否</span></a><br />
					<p style="line-height:1.2; margin-top:5px">
						* ※ 需包含大小寫英文、數字、特殊符號共6~16碼(不可有空白)。<br />
						* 開頭文字必須為英文。
					</p>
					<input type="hidden" id="chkMgrIdYn" name="chkMgrIdYn" 
						v:required m:required="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_dup_id"/>" />
				</td>
			</tr>
			<tr>
				<th><span class="point2">*</span> 密碼</th>
				<td>
					<input id="pswdNo" name="adMgr.pswdNo" type="password" class="txt" style="width:70%;"
				 	v:reqexpwwith m:reqexpwwith="<gm:tagAttb value='jsp.adminmgr.myinfo.myInfoEdit.pw02'/>"
					v:reqexpw m:reqexpw="<gm:tagAttb value='jsp.adminmgr.myinfo.myInfoEdit.pw03'/>"		
					v:reqnotuse m:reqnotuse="<gm:tagAttb value='jsp.adminmgr.myinfo.myInfoEdit.pw04'/>"
					v:required m:required="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_password"/>" />
					<br>
					<span class="txtcolor01"> * 需包含大小寫英文、數字、特殊符號共6~16碼(不可有空白)</span>				</td>
				<th><span class="point2">*</span> 確認密碼</th>
				<td><input id="pswdNo2" type="password" class="txt" style="width:70%;" 
					v:required m:required="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_password"/>" 
					v:scompare="==,@{adMgr.pswdNo}" m:scompare="<gm:string value="jsp.adminmgr.account.accountRegist.msg.not_same_password"/>" /></td>
			</tr>
			<tr>
				<th><span class="point2">*</span> 姓名</th>
				<td><input id="mgrNm" type="text" name="adMgr.mgrNm" class="txt" style="width:70%;" 
					v:required m:required="<gm:string value="jsp.adminmgr.account.accountRegist.msg.insert_mgrnm"/>" 
					v:text8_limit="50" m:text8_limit="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_len_mgrnm"/>"
					/></td>
				<th><span class="point2">*</span> 公司名稱</th>
				<td><input id="compNm" name="adMgr.compNm" type="text" class="txt" style="width:70%;" 
					v:required m:required="<gm:string value="jsp.adminmgr.account.accountRegist.msg.insert_compnm"/>" 
					v:text8_limit="100" m:text8_limit="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_len_compnm"/>"
					/></td>
			</tr>
			<tr>
				<th>部門</th>
				<td><input id="deptNm" type="text" name="adMgr.deptNm" class="txt" style="width:70%;" value="" 
					v:text8_limit="50" m:text8_limit="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_len_deptnm"/>"
					/></td>
				<th>職位</th>
				<td><input id="jobGrdNm" type="text" name="adMgr.jobGrdNm" class="txt" style="width:70%;" value="" 
					v:text8_limit="50" m:text8_limit="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_len_jobgrdnm"/>"
					/></td>
			</tr>
			<tr>
				<th>行動電話</th>
				<td>
					<input id="hp1No" type="text" name="adMgr.hp1No" class="txt" style="width:30px;" maxlength="4" 
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_num_hpno"/>" /> - 
					<input id="hp2No" type="text" name="adMgr.hp2No" class="txt" style="width:30px;" maxlength="4" 
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_num_hpno"/>" /> - 
					<input id="hp3No" type="text" name="adMgr.hp3No" class="txt" style="width:40px;" maxlength="4" 
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_num_hpno"/>" /> 
				</td>
				<th>公司電話</th>
				<td>
					<input id="bizTel1No" type="text" name="adMgr.bizTel1No" class="txt" style="width:30px;" maxlength="4" 
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_num_biztelno"/>" /> - 
					<input id="bizTel2No" type="text" name="adMgr.bizTel2No" class="txt" style="width:30px;" maxlength="4" 
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_num_biztelno"/>" /> - 
					<input id="bizTel3No" type="text" name="adMgr.bizTel3No" class="txt" style="width:40px;" maxlength="4" 
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_num_biztelno"/>" /> 
				</td>
			</tr>
			<tr>
				<th><span class="point2">*</span> 電子郵件</th>
				<td colspan="3">
					<input id="emailAddrId" type="text" name="adMgr.emailAddrId" class="txt" style="width:150px;" 
						v:required m:required="<gm:string value="jsp.adminmgr.account.accountRegist.msg.insert_emailaddrid"/>" 
						v:checkemail m:checkemail="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_text_email"/>" />
					@ 
					<input id="emailAddr" type="text" name="adMgr.emailAddr" class="txt" style="width:150px;" 
						v:required m:required="<gm:string value="jsp.adminmgr.account.accountRegist.msg.insert_emailaddr"/>" 
						v:checkemail m:checkemail="<gm:string value="jsp.adminmgr.account.accountRegist.msg.check_text_email"/>" />
				</td>
			</tr>
			<tr>
				<th>其他事項</th>
				<td colspan="3"><input id="etcDscr" type="text" name="adMgr.etcDscr" class="txt" style="width:80%;" value="" /></td>
			</tr>
			<tr>
				<th><span class="point2">*</span> 權現類別</th>
				<td colspan="3">
					<gc:radioButtons name="adMgr.authGbn" group="AD0001" codeType="full">
					<g:param name="extra">
						v:mustcheck="1" m:mustcheck="<gm:string value="jsp.adminmgr.account.accountRegist.msg.insert_authgbn"/>" 
					</g:param>
					</gc:radioButtons></td>
			</tr>
		</table>
		</s:form>
		<p class="btn_wrap text_r mt25">
			<a class="btn" id="btnSubmit" href="javascript:funcProcessAdMgr();"><span>儲存</span></a>
			<a class="btn" href="javascript:funcAccountList();"><span>目錄</span></a>
		</p>

</body>
</html>
