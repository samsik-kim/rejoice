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

	function funcProcessAdMgr() {

		if(showValidate('adMgrForm', 'dialog', 'Check Input Value.', funcCheckForm)) {
			if(confirm("<gm:string value='jsp.adminmgr.myinfo.myInfoEdit.msg.confirm_upd'/>")) {
				$( "#adMgrForm" ).attr("action", "${CONF['omp.common.url-prefix.https.admin']}${pageContext.request.contextPath}/adminMgr/updateMyInfo.omp");				
				$.blockUI({ message: 'Please Wait.' });
				$("#adMgrForm").submit();
			}
		}
		
	};

	var funcCheckForm = {
		checkemail : function() {
			var vEmailAddr = $('#emailAddrId').val() + "@" + $("#emailAddr").val();
			//alert(vEmailAddr);
			if (!(vEmailAddr.search(/^\s*[\w\~\-\.]+\@[\w\~\-]+(\.[\w\~\-]+)+\s*$/) >= 0)) {
				return false;
			}
			return true;
		}
	};

</script>

		<div id="location">
			首頁  &gt; 管理者中心 &gt; 個人資料變更 &gt; <strong>個人資料變更</strong>
		</div><!-- //location -->

		<h1 class="fl pr10">個人資料變更</h1>
		<p>可修改個人資料</p>
		<s:form action="updateMyInfo" id="adMgrForm" theme="simple">
			<input type="hidden" id="mgrId" name="adMgr.mgrId" value="<s:property value="adMgr.mgrId"/>" />
		<table class="tabletype01">
			<colgroup>
				<col style="width:20%;"><col  style="width:30%;">
				<col style="width:20%;"><col  style="width:30%;">
			</colgroup>
			<tr>
				<th>帳號</th>
				<td><s:property value="adMgr.mgrId"/></td>
				<th>新增日期</th>
				<td><g:text value="${adMgr.regDt}" format="L####-##-## ##:##:##"/></td>
			</tr>
			<tr>
				<th><span class="point2">*</span> 密碼</th>
				<td>
					<input id="pswdNo" name="adMgr.pswdNo" type="password" class="txt" style="width:70%;" value="<s:property value="adMgr.pswdNo"/>"
				 	v:reqexpwwith m:reqexpwwith="<gm:tagAttb value='jsp.adminmgr.myinfo.myInfoEdit.pw02'/>"
					v:reqexpw m:reqexpw="<gm:tagAttb value='jsp.adminmgr.myinfo.myInfoEdit.pw03'/>"		
					v:reqnotuse m:reqnotuse="<gm:tagAttb value='jsp.adminmgr.myinfo.myInfoEdit.pw04'/>"
					v:required m:required="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_password"/>" />
					<br>
					<span class="txtcolor01"> * 需包含大小寫英文、數字、特殊符號共6~16碼(不可有空白)</span>
				</td>
				<th><span class="point2">*</span> 確認密碼</th>
				<td><input id="pswdNo2" type="password" class="txt" style="width:70%;" value="<s:property value="adMgr.pswdNo"/>" 
					v:required m:required="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_password"/>" 
					v:scompare="==,@{adMgr.pswdNo}" m:scompare="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.not_same_password"/>" /></td>
			</tr>
			<tr>
				<th><span class="point2">*</span> 姓名</th>
				<td><input id="mgrNm" type="text" name="adMgr.mgrNm" class="txt" style="width:70%;" value="<s:property value="adMgr.mgrNm"/>" 
					v:required m:required="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.insert_mgrnm"/>" 
					v:text8_limit="50" m:text8_limit="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_len_mgrnm"/>"
					/></td>
				<th><span class="point2">*</span> 公司名稱</th>
				<td><input id="compNm" name="adMgr.compNm" type="text" class="txt" style="width:70%;" value="<s:property value="adMgr.compNm"/>"  
					v:required m:required="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.insert_compnm"/>" 
					v:text8_limit="100" m:text8_limit="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_len_compnm"/>"
					/></td>
			</tr>
			<tr>
				<th>部門</th>
				<td><input id="deptNm" type="text" name="adMgr.deptNm" class="txt" style="width:70%;" value="<s:property value="adMgr.deptNm"/>" 
					v:text8_limit="50" m:text8_limit="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_len_deptnm"/>"
					/></td>
				<th>職位</th>
				<td><input id="jobGrdNm" type="text" name="adMgr.jobGrdNm" class="txt" style="width:70%;" value="<s:property value="adMgr.jobGrdNm"/>" 
					v:text8_limit="50" m:text8_limit="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_len_jobgrdnm"/>"
					/></td>
			</tr>
			<tr>
				<th>行動電話</th>
				<td>
					<!--
					<select id="#">
                       	<option>通訊公司</option>
					</select>
					-->
					<input id="hp1No" type="text" name="adMgr.hp1No" class="txt" style="width:30px;" maxlength="4" value="<s:property value="adMgr.hp1No"/>" 
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_num_hpno"/>" /> - 
					<input id="hp2No" type="text" name="adMgr.hp2No" class="txt" style="width:30px;" maxlength="4" value="<s:property value="adMgr.hp2No"/>" 
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_num_hpno"/>" /> - 
					<input id="hp3No" type="text" name="adMgr.hp3No" class="txt" style="width:40px;" maxlength="4" value="<s:property value="adMgr.hp3No"/>" 
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_num_hpno"/>" />
				</td>
				<th>公司電話</th>
				<td>
					<input id="bizTel1No" type="text" name="adMgr.bizTel1No" class="txt" style="width:30px;" maxlength="4" value="<s:property value="adMgr.bizTel1No"/>" 
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_num_biztelno"/>" /> - 
					<input id="bizTel2No" type="text" name="adMgr.bizTel2No" class="txt" style="width:30px;" maxlength="4" value="<s:property value="adMgr.bizTel2No"/>" 
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_num_biztelno"/>" /> -
					<input id="bizTel3No" type="text" name="adMgr.bizTel3No" class="txt" style="width:40px;" maxlength="4" value="<s:property value="adMgr.bizTel3No"/>" 
						v:mustnum m:mustnum="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_num_biztelno"/>" />
				</td>
			</tr>

			<tr>
				<th><span class="point2">*</span>  電子郵件</th>
				<td colspan="3">
					<input id="emailAddrId" type="text" name="adMgr.emailAddrId" class="txt" style="width:150px;" value="<s:property value="adMgr.emailAddrId"/>"  
						v:required m:required="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.insert_emailaddrid"/>" 
						v:checkemail m:checkemail="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_text_email"/>" />
					@ 
					<input id="emailAddr" type="text" name="adMgr.emailAddr" class="txt" style="width:150px;" value="<s:property value="adMgr.emailAddr"/>" 
						v:required m:required="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.insert_emailaddr"/>" 
						v:checkemail m:checkemail="<gm:string value="jsp.adminmgr.myinfo.myInfoEdit.msg.check_text_email"/>" />
				</td>
			</tr>
			<tr>
				<th>其他事項</th>
				<td colspan="3"><input id="etcDscr" type="text" name="adMgr.etcDscr" class="txt" style="width:80%;" value="<s:property value="adMgr.etcDscr"/>" /></td>
			</tr>
		</table>
		</s:form>
		<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:funcProcessAdMgr()"><span>儲存</span></a></p>

</body>
</html>
