<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<script type="text/javascript">

	$(document).ready(function(){
		$( "#startDate" ).datepicker();
		$( "#endDate" ).datepicker();
		$( "#payStartDt" ).datepicker();
		$( "#payEndDt" ).datepicker();
		
		// Regist Date Default Week.
		if('${sc.startDate}' == "") {
			setOrderSearchDateAdminPoC("7day", document.getElementById("startDate"), document.getElementById("endDate"));
		}
		
		// Pay Trans Date Default Week.
		if('${sc.payTransStartDate}' == "") {
			setOrderSearchDateAdminPoC("7day", document.getElementById("payStartDt"), document.getElementById("payEndDt"));
		}
		
		var option = '<option value="ALL">全部</option>';
		var mbrcatcd, bizcatcd;
		
		if($("#mbrclscd").val() != "ALL"){
			if($("#mbrcatcd").val() == "ALL"){
				mbrcatcd = option + '<gc:options group="US0002" codeType="full" filter="admin.dev" value="${sc.mbrcatcd}"/>';
			}
			
			$("#mbrcatcd").html(mbrcatcd);
			
			if($("#mbrclscd").val() == '${CONST.MEM_CLS_BUSINESS}' && $("#mbrcatcd").val() == '${CONST.MEM_TYPE_DEV_PAY}'){
				$("#bizcatcd").empty();
				
				bizcatcd = option + '<gc:options group="US0009" codeType="full" filter="admin.dev" value="${sc.bizcatcd}"/>';
				
				$("#bizcatcd").html(bizcatcd);
			} else {
				$("#bizcatcd").empty();
				$("#bizcatcd").html(option);
			}
		}else{
			$("#mbrcatcd").empty();
			$("#mbrcatcd").html(option);
			
			$("#bizcatcd").empty();
			$("#bizcatcd").html(option);
		}
	});
	
	// SelType Change
	function selType_change(){
		var option = '<option value="ALL">全部</option>';
		var mbrcatcd, bizcatcd;
		
		if($("#mbrclscd").val() != "ALL"){
			if($("#mbrcatcd").val() == "ALL"){
				mbrcatcd = option + '<gc:options group="US0002" codeType="full" filter="admin.dev" />';
			}
			
			$("#mbrcatcd").html(mbrcatcd);
			
			if($("#mbrclscd").val() == '${CONST.MEM_CLS_BUSINESS}' && $("#mbrcatcd").val() == '${CONST.MEM_TYPE_DEV_PAY}'){
				$("#bizcatcd").empty();
				
				bizcatcd = option + '<gc:options group="US0009" codeType="full" filter="admin.dev" />';
				
				$("#bizcatcd").html(bizcatcd);
			} else {
				$("#bizcatcd").empty();
				$("#bizcatcd").html(option);
			}
		}else{
			$("#mbrcatcd").empty();
			$("#mbrcatcd").html(option);
			
			$("#bizcatcd").empty();
			$("#bizcatcd").html(option);
		}
	}
	
	// Search Date
	function search_date(term, type) {
		if(type == "reg"){
			setOrderSearchDateAdminPoC(term, document.getElementById("startDate"), document.getElementById("endDate"));
			
		}else if(type == "pay"){
			setOrderSearchDateAdminPoC(term, document.getElementById("payStartDt"), document.getElementById("payEndDt"));
		}
	}
	
	// Search dev Member
	function selectDevMember(){
		if(showValidate('devMember', 'default', '<gm:string value="jsp.common.dialog.title"/>')){
			$("#searchNm").val($.trim($("#searchNm").val()));
			$("form[name=devMember] input[name=sc\\.currentPageNo]").val(1);
			
			$("#devMember").attr("target", "_self");
			$("#devMember").attr("action", "findDevMemberMgrList.omp");
			$("#devMember").submit();
		}
	}
	
	// Dev Member Info Detail View	
	function member_detail(mbrno){
		$("#mbrno").val(mbrno);
		
		$("#devMember").attr("target", "_self");
		$("#devMember").attr("action", "findDevMemberDetail.omp");
		$("#devMember").submit();
	}
	
	// Value Initialization
	function init() {
		$("#mbrgrcd option:first").attr("selected", "true");		// Member Grade SelectBox Initialization 
		$("#mbrclscd option:first").attr("selected", "true");		// Member Class SelectBox Initialization 
		
		selType_change();
		
		setOrderSearchDateAdminPoC("7day", document.getElementById("startDate"), document.getElementById("endDate"));		// Search Date Default Initialization
		
		setOrderSearchDateAdminPoC("7day", document.getElementById("payStartDt"), document.getElementById("payEndDt"));		// Search Pay Trans Date Default Initialization
		
		$("#devmbrstatcd option:first").attr("selected", "true");		// Dev Member State SelectBox Initialization

		$("#searchType option:first").attr("selected", "true");		// Search Type Initialization
		
		$("#searchNm").val("");		// Search Word Initialization
		
		
	}
	
	function goPage(no) {
		if(showValidate('devMember', 'default', '<gm:string value="jsp.common.dialog.title"/>')){
			$("#searchNm").val($.trim($("#searchNm").val()));
			$("form[name=devMember] input[name=sc\\.currentPageNo]").val(no);
			
			$("#devMember").attr("target", "_self");
			$("#devMember").attr("action", "findDevMemberMgrList.omp");
			$("#devMember").submit();
		}
	}
	
</script>	

<form name="devMember" id="devMember" method="post">
<input type="hidden" id="pageno" name="sc.currentPageNo" value="<g:tagAttb value='${sc.currentPageNo}'/>"/>
<input type="hidden" id="mbrno" name="devMember.mbrno" value="" />
	<!-- Contents -->
	<div id="location">首頁 &gt; 會員管理中心 &gt; 開發商管理 &gt; <strong>開發商資訊管理</strong></div>
	
	<h1>開發商資訊管理</h1>
	<table class="tabletype01">
		<colgroup>
			<col style="width:100px;" />
			<col />
			<col style="width:125px"/>
		</colgroup>
		<tr>
			<th>搜尋條件</th>
			<td class="align_td line2_5">
				<label for="#">會員等級</label>
				<select id="mbrgrcd" name="sc.mbrgrcd" style="width:85px;">
					<option value="ALL">全部</option>
					<gc:options group="US0050" codeType="full" value="${sc.mbrgrcd}"/>
				</select>
				<label for="#">會員類別</label>
				<select id="mbrclscd" name="sc.mbrclscd" style="width:85px;" onchange="javascript:selType_change();">
					<option value="ALL">全部</option>
					<gc:options group="US0001" codeType="full" value="${sc.mbrclscd}"/>
				</select>
				<select id="mbrcatcd" name="sc.mbrcatcd" style="width:95px;" onchange="javascript:selType_change();">
					<option value="ALL">全部</option>
				</select>
				<select id="bizcatcd" name="sc.bizcatcd" style="width:85px;">
					<option value="ALL">全部</option>
				</select>
				<br/>
				<label for="#">註冊日期</label>
				<input id="startDate" name="sc.startDate" type="text" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.startDate}' format='L####-##-##'/>" readonly/> ~ 
				<input type="text" id="endDate" name="sc.endDate" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.endDate}' format='L####-##-##'/>"
					v:scompare="ge,@{sc.startDate}" m:scompare="<gm:string value='jsp.member.common.msg.datechk'/>" readonly/> 
				<a class="btn_s" href="javascript:search_date('today', 'reg');"><span style="cursor:pointer;">今日</span></a>
				<a class="btn_s" href="javascript:search_date('7day', 'reg');"><strong style="cursor:pointer;">最近一周</strong></a>
				<a class="btn_s" href="javascript:search_date('1month' , 'reg');"><span style="cursor:pointer;">最近一個月</span></a>
				<br/>
				<label for="#">轉換日期</label>
				<input id="payStartDt" name="sc.payTransStartDate" type="text" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.payTransStartDate}' format='L####-##-##'/>" readonly/> ~ 
				<input type="text" id="payEndDt" name="sc.payTransEndDate" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.payTransEndDate}' format='L####-##-##'/>" 
					v:scompare="ge,@{sc.payTransStartDate}" m:scompare="<gm:string value='jsp.member.common.msg.datechk'/>" readonly/> 
				<a class="btn_s" href="javascript:search_date('today', 'pay');"><span style="cursor:pointer;">今日</span></a>
				<a class="btn_s" href="javascript:search_date('7day', 'pay');"><strong style="cursor:pointer;">最近一周</strong></a>
				<a class="btn_s" href="javascript:search_date('1month', 'pay');"><span style="cursor:pointer;">最近一個月</span></a>
			</td>
			<td rowspan="2" class="text_c" >
				<a class="btn" href="javascript:selectDevMember();" ><strong>搜尋</strong></a>
				<a class="btn" href="javascript:init();"><span>重新搜尋</span></a>
			</td>
		</tr>
		<tr>
			<th>搜尋關鍵字</th>
			<td class="align_td">
				<label for="#">會員狀態</label>
				<select id="devmbrstatcd" name="sc.devmbrstatcd" style="width:110px;">
					<option value="ALL">全部</option>
					<gc:options group="US0008" codeType="full" value="${sc.devmbrstatcd}"/>
				</select>
				<select id="searchType" name="sc.searchType" style="width:75px;" onchange="javascript:selType_change();">
                   	<option value="id" selected>帳號</option>
					<option value="name" <c:if test="${sc.searchType eq 'name'}">selected</c:if>>姓名/公司名</option>
				</select>
				<input type="text" id="searchNm" name="sc.searchNm" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.searchNm}'/>" onkeydown="javascript:if (event.keyCode == 13) {selectDevMember();}" />&nbsp;&nbsp; 
			</td>
		</tr>
	</table>
	<p class="fr mt25">會員搜尋結果 : 共<g:html value="${devMember.page.totalCount}"/>名</p>
	<table class="tabletype02 mt25">
		<colgroup>
			<c:choose>
				<c:when test="${!empty list}"><col style="width:60px;" /></c:when>
				<c:otherwise><col style="width:8%;" ></c:otherwise>
			</c:choose>
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
			<col />
		</colgroup>
		<thead>
			<tr>
				<th>序號</th>
				<th>帳號</th>
				<th>姓名/公司名</th>
				<th>會員等級</th>
				<th>會員類別</th>
				<th>付費/免費</th>
				<th>會員狀態</th>
				<th>註冊日期</th>
				<th>轉換日期</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${searchYn eq 'N'}">
					<tr><td colspan="9" class="text_c"><gm:string value="jsp.admin.common.first.page"/></td></tr>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${!empty list}">
							<c:forEach items="${list}" var="item">
								<tr>
									<td><g:html value="${item.rnum}" /></td>
									<td><a href="javascript:member_detail('${item.mbrno }');"><strong><g:html value="${item.mbrid }" /></strong></a></td>
									<td>
										<c:choose>
											<c:when test="${item.mbrclscd eq CONST.MEM_CLS_BUSINESS}">
												<g:html value="${item.compnm}" />
											</c:when>
											<c:otherwise>
												<g:html value="${item.mbrnm}" />
											</c:otherwise>
										</c:choose>
									</td>
									<td><gc:text code="${item.mbrgrcd}" /></td>
									<td><gc:text code="${item.mbrclscd}" /></td>
									<td><gc:text code="${item.mbrcatcd}" /></td>
									<td><gc:text code="${item.devmbrstatcd}" /></td>
									<td><g:html value="${item.mbrstartdt}" format="L####-##-##" /></td>
									<td><g:html value="${item.payTransDt}" format="L####-##-##" /></td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr><td colspan="9" class="text_c">無資料.</td></tr>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	
	<!-- paging -->
	<div align="center">
		<g:pageIndex item="${list}"/>
	</div>
	<!-- //paging -->
	
</form>