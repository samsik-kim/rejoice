<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<script type="text/javascript">
	$(document).ready(function(){
		// Search Button
		$("#searchBtn").click(function(){
			$("#searchValue").val($.trim($("#searchValue").val()));
			$("form[name=frm] input[name=bankModel\\.page\\.no]").val(1);
			
			$( "#frm" ).attr("action", "commBankCdList.omp");
			$( "#frm" ).submit();
		});
		
		// Search Initialize
		$("#searchInit").click(function(){
			$("input:radio[name=useYn]").each(function(index){
				if(index == 0){
					$(this).attr("checked", true);
				}
			});
			$("#searchType option:first").attr("selected", true);
			$("#searchValue").val("");
			return false;
		});
		
		// Select Check Delete
		$("#selectDel").click(function(){
			if($("input:checkbox[name=bankModel.CHECK_BANK_CD]:checked").length == 0) {
				alert("<gm:string value='jsp.adminmgr.bank.commBankCdList.chkYn'/>");
				return false;
			} else {
				if(confirm("<gm:string value='jsp.adminmgr.bank.commBankCdList.del_confirm'/>")) {
					$( "#frm" ).attr("action", "deleteBankInfo.omp");
					$( "#frm" ).submit();
				}else{
					return false;
				}
			}
		});
		
		// Multi Check
		$("#multiChk").click(function(){
			if($(this).is(":checked")){
				$("input:checkbox[name=bankModel.CHECK_BANK_CD]").each(function(){
					$(this).attr("checked", true);
				});
			}else{
				$("input:checkbox[name=bankModel.CHECK_BANK_CD]").each(function(){
					$(this).removeAttr("checked");
				});
			}
		});
		
		// Register Page
		$("#regBtn").click(function(){
			$( "#frm" ).attr("action", "regBankInfo.omp");
			$( "#frm" ).submit();
		});
		
		// SearhType Change Event
		$("#searchType").change(function(){
			$("#searchValue").val("");
			return false;
		});
	});
	
	function modify(code) {
		$("#bankCd").val(code);
		
		$( "#frm" ).attr("action", "modifyBankInfo.omp");
		$( "#frm" ).submit();
	}
	
	function goPage(no) {
		$("#searchValue").val($.trim($("#searchValue").val()));
		$("form[name=frm] input[name=bankModel\\.page\\.no]").val(no);
		
		$( "#frm" ).attr("action", "commBankCdList.omp");
		$( "#frm" ).submit();
	}
	
</script>
<form id="frm" name="frm" method="post">
<input type="hidden" id="bankCd" name="bankModel.BANK_CD" value="" />
<input type="hidden" id="pageNo" name="bankModel.page.no" value="<g:tagAttb value='${bankModel.page.no}'/>" />
	<div id="location">
		Home &gt; 운영자관리 &gt; 기준정보관리 &gt; <strong>국내송금은행 관리</strong> 
	</div><!-- //location -->

	<h1 class="fl pr10">국내송금은행 관리</h1>
	<p>국내송금은행 정보를 관리를 할 수 있습니다.</p>
	<table class="tabletype01">
		<colgroup>
			<col style="width:100px;" />
			<col />
			<col style="width:125px" />
		</colgroup>
		<tr>
			<th>사용여부</th>
			<td class="align_td">
				<input type="radio" name="useYn" value="" checked/> 전체
				<input type="radio" name="useYn" value="Y" <c:if test="${useYn eq 'Y' }">checked</c:if> class="ml05" /> Y
				<input type="radio" name ="useYn" value="N" <c:if test="${useYn eq 'N' }">checked</c:if> class="ml05" /> N
			</td>
			<td rowspan="2" class="text_c" >
				<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a><br />
				<a class="btn" href="#" id="searchInit"><span>검색초기화</span></a>
			</td>
		</tr>
		<tr>
			<th>검색어</th>
			<td class="align_td">
				<select style="width:90px;" id="searchType" name="searchType">
					<option value="code" <c:if test="${searchType eq 'code' }">selected</c:if>>은행코드</option>
					<option value="briefNm" <c:if test="${searchType eq 'briefNm' }">selected</c:if>>은행명약칭</option>
					<option value="nm" <c:if test="${searchType eq 'nm' }">selected</c:if>>은행명칭</option>
				</select>
				<input type="text" class="txt" id="searchValue" name="searchValue" value="<g:tagAttb value='${searchValue}' />" style="width:250px;" />
			</td>
		</tr>
	</table>
	
	<table class="tabletype02 mt20">
		<colgroup>
			<col style="width:30px;" />
			<col style="width:50px;" />
			<col style="width:100px;" />
			<col />
			<col />
			<col />
			<col style="width:100px;" />
		</colgroup>
		<tbody>
			<tr>
				<th><input type="checkbox" id="multiChk" /></th>
				<th>번호</th>
				<th>은행코드</th>
				<th>인증번호/코드</th>
				<th>은행명 약칭</th>
				<th>은행명칭</th>
				<th>사용여부</th>
			</tr>
			<c:choose>
				<c:when test="${!empty bankList}">
					<c:forEach items="${bankList}" var="bank">
						<tr>
							<td><input type="checkbox" name="bankModel.CHECK_BANK_CD" value="<g:tagAttb value='${bank.BANK_CD}' />" /></td>
							<td><g:html value="${bank.RNUM}" /></td>
							<td><a href="javascript:modify('<g:html value="${bank.BANK_CD}" />')"><strong><g:html value="${bank.BANK_CD}" /></strong></a></td>
							<td><g:html value="${bank.CERTI_CD}" /></td>
							<td><g:html value="${bank.BANK_BRIEF_NM}" /></td>
							<td class="text_l"><g:html value="${bank.BANK_NM}" /></td>
							<td><g:html value="${bank.USE_YN}" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7">검색된 결과가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<div class="overflow_h">
		<div class="fl mt10">
			<a class="btn_s" href="#" id="selectDel"><strong>선택삭제</strong></a>
		</div>
		<p class="fr mt10"><a class="btn" href="#" id="regBtn"><span>등록</span></a></p>
	</div>

	<!-- paging -->
	<g:pageIndex item="${bankList}"/>
	<!-- //paging -->
</form>