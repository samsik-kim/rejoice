<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<script type="text/javascript"> 
	$(document).ready( function(){
		$( "#withdrawApprStartDt" ).datepicker();
		$( "#withdrawApprEndDt" ).datepicker();
		
		if("${sc.withdrawApprStartDt }" == "") {
			setOrderSearchDateAdminPoC("7day", document.getElementById("withdrawApprStartDt"), document.getElementById("withdrawApprEndDt"));
		}
		
		if('${sc.searchType}' != "hp"){
			$("#ex_hpno").hide();
		} else{
			$("#ex_hpno").show();
		}
		
		$("#searchInitBtn").click( function() {
			$("#mbrclscd option:first").attr("selected", "true");
			
			setOrderSearchDateAdminPoC("7day", document.getElementById("withdrawApprStartDt"), document.getElementById("withdrawApprEndDt"));
			
			$("#searchType option:first").attr("selected", "true");
			
			selType_change();

			$("#searchValue").val("");
		});
	});
	
	var payDateSet = function(term) {
		setOrderSearchDateAdminPoC(term, document.getElementById("withdrawApprStartDt"), document.getElementById("withdrawApprEndDt"));
	};
	
	function selType_change(){
		$("#searchValue").val("");
		if($("#searchType").val() == "hp"){
			$("#ex_hpno").show();	
		} else {
			$("#ex_hpno").hide();
		}
	}
	
	function searchList(){
		$("#searchValue").val($.trim($("#searchValue").val()));
		$("form[name=frm] input[name=sc\\.currentPageNo]").val(1);
		
		$("#frm").attr('action', env.contextPath + '/member/userMemberWithdrawList.omp');
		$("#frm").submit();
	}
	
	function goPage(no) {
		$("#searchValue").val($.trim($("#searchValue").val()));
	    $("form[name=frm] input[name=sc\\.currentPageNo]").val(no);
	    
	    frmSubmit();
	}

	function frmSubmit() {
		document.getElementsByName("frm").item(0).submit();
	}	

</script>

<gc:codeList var="codeList" group="US0001" filter="admin.user"/>

<div id="location">首頁 &gt; 會員管理中心 &gt; 使用者管理 &gt; <strong>註銷會員記錄</strong></div><!-- //location -->

<h1>註銷會員記錄</h1>   
<form name="frm" action="" id="frm" onsubmit="return showValidate('frm', 'default', '<gm:string value="jsp.common.dialog.title"/>');">
<input type="hidden" id="pageno" name="sc.currentPageNo" value="<g:tagAttb value='${sc.currentPageNo }'/>"/>
	<table class="tabletype01">
		<colgroup>
			<col style="width: 100px;">
			<col>
			<col style="width: 125px">
		</colgroup>
		<tr>
			<th>搜尋條件</th>
			<td class="align_td">
				<label for="#">會員類別</label> 
				<select id="mbrclscd" name="sc.mbrclscd" style="width: 104px;">
					<option value="">全部</option>
					<c:forEach items="${codeList}" var="item">
						<option value="${item.dtlFullCd}" <c:if test="${item.dtlFullCd eq sc.mbrclscd}">selected="selected"</c:if>><g:html value="${item.addField1}"/></option>
					</c:forEach>
				</select> <br />
				<label for="#">註銷日期 </label> 
				<input id="withdrawApprStartDt" name="sc.withdrawApprStartDt" type="text" class="txt" style="width: 80px;" 	value="<g:tagAttb value='${sc.withdrawApprStartDt }' format='L####-##-##' />" readonly /> ~ 
				<input id="withdrawApprEndDt" name="sc.withdrawApprEndDt" type="text" class="txt" style="width: 80px;" value="<g:tagAttb value='${sc.withdrawApprEndDt}'  format='L####-##-##' />" 
					v:scompare="ge,@{sc.withdrawApprStartDt}" m:scompare="<gm:string value='jsp.member.common.msg.datechk'/>" readonly />  
				<a class="btn_s" id="todayBtn" name="todayBtn" href="javascript:payDateSet('today');" ><span>今日</span></a> 
				<a class="btn_s" id="weekBtn" name="weekBtn" href="javascript:payDateSet('7day');" ><strong>最近一周</strong></a> 
				<a class="btn_s" id="monthBtn" name="monthBtn" href="javascript:payDateSet('1month');" ><span>最近一個月</span></a>
			</td>
			<td rowspan="2" class="text_c">
				<a class="btn" id="searchBtn" name="searchBtn" href="#" onclick="javascript:searchList();"><strong>搜尋</strong></a>
				<a class="btn" href="#" id="searchInitBtn" name="searchInitBtn"><span>重新搜尋</span></a>
			</td>
		</tr>
		<tr>
			<th>搜尋關鍵字</th>
			<td class="align_td">
				<select id="searchType" name="sc.searchType" style="width: 104px;" onchange="javascript:selType_change();">
					<option value="id" <c:if test="${sc.searchType eq 'id' || sc.searchType eq null }">selected</c:if>>帳號</option>
					<option value="hp" <c:if test="${sc.searchType eq 'hp' }">selected</c:if>>行動電話</option>
					<option value="name" <c:if test="${sc.searchType eq 'name' }">selected</c:if>>姓名</option>
					<option value="email" <c:if test="${sc.searchType eq 'email' }">selected</c:if>>電子郵件</option>
					
				</select> 
				<input id="searchValue" name="sc.searchNm" type="text" class="txt" style="width: 80px;" value="<g:tagAttb value='${sc.searchNm }'/>" />
				<span id="ex_hpno">範例 : 01012341234</span>
			</td>
		</tr>
	</table>

	<p class="fr mt25">會員搜尋結果  : 共<strong><g:html value="${memberContract.page.dataTo }"/></strong>名</p>
	
	<table class="tabletype02">
		<colgroup>
			<c:choose>
				<c:when test="${!empty resultList}"><col style="width:60px;" /></c:when>
				<c:otherwise><col style="width:8%;" ></c:otherwise>
			</c:choose>
			<col>
			<col>
			<col>
		</colgroup>
		<thead>
			<tr>
				<th>序號</th>
				<th>帳號</th>
				<th>會員類別</th>
				<th>註銷日期</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${searchYn eq 'N'}">
					<tr><td colspan="4"><gm:string value="jsp.admin.common.first.page"/></td></tr>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${not empty resultList }">
							<c:forEach items="${resultList }" var="member" varStatus="status">
								<tr>
									<td><g:html value="${member.rnum}"/></td>
									<td><a href="<c:url value='/userMemMgr/findUserMemberDetail.omp?userMember.mbrno=${member.mbrNo}&topCode=M001&leftCode=M001001001' />"><strong><g:html value="${member.mbrId }"/></strong></a></td>
									<td>
										<c:forEach items="${codeList}" var="code">
											<c:if test="${code.dtlFullCd eq member.mbrClsCd}"><g:html value="${code.addField1}"/></c:if>
										</c:forEach>
									</td>
									<td><g:html value='${member.mbrEndDt }' format="L####-##-##"/></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="4">無資料.</td></tr> 
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<!-- paging -->
	<div align="center">
		<g:pageIndex item="${resultList}" />
	</div>
	<!-- //paging -->
	
</form>
