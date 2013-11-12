<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<script type="text/javascript">
	$(document).ready(function(){
		
		$("#addBtn").click(function(){
			if(showValidate('frm', 'default', '<gm:string value="jsp.common.dialog.title"/>')){
				$("input:radio[name=chkUseYn]").each(function(){
					if($(this).is(":checked")){
						$("#bankUseYn").val($(this).val());
					}
				});
				
				$.ajax({
					type			: 	"POST",
					url			: 	env.contextPath + "/adminMgr/ajaxDuplCheck.omp",
					data			: 	$("#frm").serialize(),
					dataType	: 	"json",
					cache		: 	false,
					async 		: 	false,
					success		: 	function(json) {
											if(json.BANK_CD == "N" && json.CERTI_CD == "N"){
												alert("<gm:string value='jsp.adminmgr.bank.regBankInfo.two_dupl_chk'/>");
												return false;
											}
						
											if(json.BANK_CD == "N"){
												alert("<gm:string value='jsp.adminmgr.bank.regBankInfo.bankcd_dupl_chk'/>");
												return false;
											}
											
											if(json.CERTI_CD == "N"){
												alert("<gm:string value='jsp.adminmgr.bank.regBankInfo.certicd_dupl_chk'/>");
												return false;
											}
											
											$( "#frm" ).attr("action", "insertBankInfo.omp");
											$( "#frm" ).submit();
										},
					error			: 	function(){
											alert("<gm:string value='jsp.member.common.msg.error'/>");
											return false;
										}
				});	//end ajax
			} else {
				return false;
			}
		});
		
		$("#listBtn").click(function(){
			$( "#frm" ).attr("action", "commBankCdList.omp");
			$( "#frm" ).submit();
		});
	});

	
</script>
<form id="frm" name="frm" method="post">
<input type="hidden" id="bankUseYn" name="bankModel.USE_YN" value="N"/>

	<div id="location">
		首頁  &gt; 管理者中心 &gt; 標準資訊管理 &gt; <strong>國內匯款帳戶管理</strong>
	</div><!-- //location -->
	
	<h1 class="fl pr10">國內匯款帳戶管理</h1>
	<p>可對國內匯款帳戶進行管理.</p>
	<table class="tabletype01">
		<colgroup>
			<col style="width:110px;">
			<col >
			<col style="width:110px">
			<col >
		</colgroup>
		<tr>
			<th>銀行編碼</th>
			<td>
				<input type="text" id="bankcd" name="bankModel.BANK_CD" class="txt" style="width:200px;" maxlength="6"
					v:required m:required="<gm:string value='jsp.adminmgr.bank.regBankInfo.bankcd_req'/>"
					v:regexp="[\d]*" m:regexp="<gm:string value='jsp.adminmgr.bank.regBankInfo.bankcd_chk'/>"/>
			</td>
			<th>認證編碼</th>
			<td>
				<input type="text" id="certicd" name="bankModel.CERTI_CD" class="txt" style="width:200px;" maxlength="1"
					v:required m:required="<gm:string value='jsp.adminmgr.bank.regBankInfo.certicd_req'/>"
					v:regexp="[\d]*" m:regexp="<gm:string value='jsp.adminmgr.bank.regBankInfo.certicd_chk'/>"/>
			</td>
		</tr>
		<tr>
			<th>銀行名稱</th>
			<td colspan="3">
				<input type="text" id="banknm" name="bankModel.BANK_NM" class="txt" style="width:605px;" 
					v:required m:required="<gm:string value='jsp.adminmgr.bank.regBankInfo.banknm_req'/>"
					v:text8_limit="100" m:text8_limit="<gm:string value='jsp.adminmgr.bank.regBankInfo.banknm_chk'/>" />
			</td>
		</tr>
		<tr>
			<th>銀行簡稱</th>
			<td colspan="3">
				<input type="text" id="briffnm" name="bankModel.BANK_BRIEF_NM" class="txt" style="width:605px;" 
					v:required m:required="<gm:string value='jsp.adminmgr.bank.regBankInfo.briffnm_req'/>"
					v:text8_limit="30" m:text8_limit="<gm:string value='jsp.adminmgr.bank.regBankInfo.briffnm_chk'/>"/>
			</td>
		</tr>
		<tr>
			<th>使用與否</th>
			<td colspan="3">
				<input type="radio" name="chkUseYn" class="ml05" value="Y" /> Y
				<input type="radio" name="chkUseYn" class="ml05" value="N" /> N
				<span class="ml20">* 若未設置使用與否，將於設為“N”.</span>
			</td>
		</tr>
	</table>
	<p class="btn_wrap text_r mt25">
		<a class="btn" href="#" id="addBtn"><span>儲存</span></a>
		<a class="btn" href="#" id="listBtn"><span>目錄</span></a>
	</p>

</form>