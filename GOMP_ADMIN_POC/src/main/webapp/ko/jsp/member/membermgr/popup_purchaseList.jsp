<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<title>구매내역조회</title>
<script type="text/javascript">
	$(document).ready( function(){
		$("#startDate").datepicker();
		$("#endDate").datepicker();
		
		if("${sc.purchaseStartDt}" == "" ) {
			setOrderSearchDateAdminPoC("7day", document.getElementById("startDate"), document.getElementById("endDate"));
		}
		
		$("#searchBtn").click( function() {
			$("form[name=frm] input[name=purchase\\.prchsId]").val("");
			$("form[name=frm] input[name=sc\\.currentPageNo]").val(1);
			
			$("#frm").attr("target", "_self");
			$("#frm").attr("action", env.contextPath + "/membermgr/popPurchaseList.omp");
			$("#frm").submit();
		});
		
		// search initialization
		$("#searchInitBtn").click( function() {
			$("#payCls option:eq(0)").attr("selected", "true");
			$("#prchsStat option:eq(0)").attr("selected", "true");
			
			setOrderSearchDateAdminPoC("7day", document.getElementById("startDate"), document.getElementById("endDate"));
			
			$("#searchType option:eq(0)").attr("selected", "true");		// Search Type Initialization
			$("#searchNm").val("");
		});
		
		$("#searchType").change( function() {
			$("#searchNm").val("");
		});
		
		// Cell Phone Purchase Cancel List Excel Download
		$("#hpCancelList").click(function(){
			$("#frm").attr("method", "get");
			$("#frm").attr("action", "hpPurcaseCancelListExcel.omp");
			$("#frm").submit();
		});
	});

	// Purchase Cancel Popup
	function popOpen(prchsId, name) {
		$("form[name=frm] input[name=purchase\\.prchsId]").val(prchsId);
		
		var width = 440;
		var height = 310;
		
		openCenteredWindow("", width, height, "no", "popOpen");
		
		$("#frm").attr("target", "popOpen");
		if(name == "view"){
			$("#frm").attr("action", "popCancelView.omp");
		}else if(name == "cancel"){
			$("#frm").attr("action", "popPurchaseCancel.omp");
		}
		$("#frm").submit();
		
		return;
	}
	
	function payDateSet(term) {
		setOrderSearchDateAdminPoC(term, document.getElementById("startDate"), document.getElementById("endDate"));
	}
	
	function goPage(no) {
		if(showValidate('frm', 'default', '<gm:string value="jsp.common.dialog.title"/>')){
			$("#searchNm").val($.trim($("#searchNm").val()));
		    $("form[name=frm] input[name=sc\\.currentPageNo]").val(no);
		    $("form[name=frm] input[name=purchase\\.prchsId]").val("");
		    
		    $("#frm").attr("target", "_self");
		    $("#frm").attr("action", env.contextPath + "/membermgr/popPurchaseList.omp");
			$("#frm").submit();
		}
	}
	
	
</script>

<form id="frm" name="frm" method="post" >
<input type="hidden" name="sc.currentPageNo" value="<g:tagAttb value='${sc.currentPageNo}'/>"/>
<input type="hidden" name="purchase.mbrNo" value="<g:tagAttb value='${purchase.mbrNo}'/>">
<input type="hidden" name="purchase.popupYn" value="Y"/>
<input type="hidden" name="purchase.prchsId" value=""/>

<div id="popup_area_810">
	<h1>구매내역조회</h1>
	<table class="pop_tabletype01">
		<colgroup>
			<col style="width:100px;">
			<col >
			<col style="width:125px">
		</colgroup>
		<tbody>
		<tr>
			<th>검색조건</th>
			<td class="align_td line2_5">					
				<label for="#">결제수단</label>
                <select id="payCls" name="sc.payCls" style="width:104px;">
                	<gc:options group="OR0006" codeType="full" filter="adminpoc_puchaseList" value="${sc.payCls }"/>
                </select>

				<label for="#">구매상태</label>
                <select id="prchsStat" name="sc.prchsStat" style="width:104px;">
                	<option value="">전체</option>
					<gc:options group="OR0003" codeType="full" filter="adminpoc_puchaseList" value="${sc.prchsStat }"/>
                </select>
				<br />
				<label for="#">구매일자</label>
				<input id="startDate" name="sc.purchaseStartDt" type="text" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.purchaseStartDt }' format='L####-##-##' />" readonly /> ~ 
				<input id="endDate" name="sc.purchaseEndDt" type="text" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.purchaseEndDt }' format='L####-##-##' />" 
					v:scompare="ge,@{sc.purchaseStartDt}" m:scompare="<gm:string value='jsp.member.common.msg.datechk'/>" readonly/>
				<a class="btn_s" id="todayBtn" href="#" onclick="javascript:payDateSet('today');"><span>오늘</span></a>
				<a class="btn_s" id="weekBtn" href="#" onclick="javascript:payDateSet('7day');"><strong>최근1주</strong></a>
				<a class="btn_s" id="monthBtn" href="#" onclick="javascript:payDateSet('1month');"><span>최근1개월</span></a>
			</td>
			<td rowspan="2" class="text_c" >
				<a class="btn" id="searchBtn" name="searchBtn" href="#"><strong>검색</strong></a>
				<a class="btn" id="searchInitBtn" name="searchInitBtn" href="#"><span>검색초기화</span></a>
			</td>
		</tr>
		<tr>
			<th>검색어</th>
			<td class="align_td">
				<select id="searchType" name="sc.searchType" style="width:104px;">
					<option value="prodNm" selected>상품명</option>
					<option value="aid" <c:if test="${sc.searchType eq 'aid' }">selected</c:if> >상품AID</option>
					<option value="developer" <c:if test="${sc.searchType eq 'developer' }">selected</c:if> >개발자</option>
				</select>
				<input id="searchNm" name="sc.searchNm" type="text" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.searchNm}'/>" />
			</td>
		</tr>
		</tbody>
	</table>
	
	<div class="mt25">
		<p class="fl mb05">
			<a class="btn_s" href="#" id="hpCancelList"><strong>휴대폰 결제 취소내역</strong></a>
		</p>		
		<p class="fr pt05">총 등록건수 : <strong><g:html value="${purchase.page.totalCount}"/></strong>건 | 현재페이지 <strong><g:html value="${purchase.page.no}"/></strong>/<g:html value="${purchase.page.topPage}"/></p>
	</div>
	<div class="scroll" style="width:770px;height:300px; border:1px solid #ddd;clear:both;overflow-x:scroll;">
		<c:choose>
			<c:when test="${not empty resultList}">
				<table class="pop_tabletype02" style="width:1300px;">
			</c:when>
			<c:otherwise>
				<table class="pop_tabletype02" style="width:900px;">
			</c:otherwise>
		</c:choose>
			<colgroup>
				<col >
				<col >
				<col >
				<col >
				<col >
				<col >
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
				<th>번호</th>
				<th>구매일시</th>
				<th>상품명</th>
				<th>상품AID</th>
				<th>개발자</th>
				<th>결제수단</th>
				<th>결제금액</th>
				<th>D/L</th>
				<th>취소일시</th>
				<th>승인번호</th>
				<th>영수증번호</th>
				<th>기부여부</th>
				<th>구매관리</th>
			</tr>
			</thead>
			<tbody>
				<c:choose>
					<c:when test="${searchYn eq 'N'}">
						<tr><td colspan="14">검색조건을 선택하신 후 ‘검색’ 버튼을 클릭하세요.</td></tr>
					</c:when>
					<c:otherwise>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="item" varStatus="status">
									<tr>
										<td><g:html value="${item.rnum}" /></td>
										<td><g:text value="${item.prchsDt}" format="L####-##-##<br/>##:##" /></td>
										<td><g:html value="${item.prodNm}" /></td>
										<td><g:html value="${item.aid}" /></td>
										<td><g:html value="${item.devMbrId}" /></td>
										<td><gc:text code="${item.payCls}" /></td>
										<td><g:html value="${item.prchsAmt}" format="R#,###,###,###" /></td>
										<td><g:html value="${item.dwnldStat}" /></td>
										<td><g:text value="${item.prchsCnclDtm}" format="L####-##-##<br/>##:##" /></td>
										<td><g:html value="${item.applNum}" /></td>
										<td><g:html value="${item.oreRtNo}" /></td>
										<td><g:html value="${item.rtCbyn}" /></td>
										<td>
											<c:if test="${(empty item.prchsCnclDtm) and (item.payCls != CONST.PURCHASE_FREE_PROD) 
																and (item.cancelYn eq 'Y') and (item.prchsStat eq CONST.PURCHASE_END)}">
												<a class="btn_s" href="javascript:popOpen('${item.prchsId}', 'cancel');" ><span>취소</span></a>
											</c:if>
											<c:if test="${!empty item.prchsCnclDtm and item.prchsStat eq CONST.PURCHASE_CANCEL}">
												<a class="btn_s" href="javascript:popOpen('${item.prchsId}', 'view');"><span>취소내역</span></a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr><td colspan="14">검색 결과가 없습니다.</td></tr>
							</c:otherwise>
						</c:choose>
					</c:otherwise>
				</c:choose>
			</tbody>
		</table>
	</div><!-- //scroll -->
	
	<!-- paging -->
		<div align="center">
			<g:pageIndex item="${resultList}" />
		</div>
	<!-- //paging -->
	
	<div class="pop_btn" >
		<a class="btn" href="javascript:self.close();"><strong>닫기</strong></a>
	</div>

</div><!-- //contents -->
</form>