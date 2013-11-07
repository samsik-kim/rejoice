<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<script type="text/javascript">
	$(document).ready( function(){
		$("#startDate").datepicker();
		$("#endDate").datepicker();
		
		if('${sc.searchType}' != "hpNo"){
			$("#ex_hpno").hide();
		} else{
			$("#ex_hpno").show();
		}
		
		if("${sc.purchaseStartDt}" == "" ) {
			setOrderSearchDateAdminPoC("7day", document.frm.startDate, document.frm.endDate);
		}
		
		$("#searchBtn").click( function() {
			if($("#searchType").val() == "rtNo"){
				if(!isNumeric($("#searchNm").val())){
					alert("<gm:string value='jsp.member.membermgr.PurchaseList.msg.rtNo_chk'/>");
					return false;
				}
			}
			
			var submit_Result = false;
			
			$("form[name=frm] input[name=purchase\\.mbrNo]").val("");
			$("form[name=frm] input[name=purchase\\.prchsId]").val("");
			
			$("#frm").attr("target", "_self");
			
			if(showValidate('frm', 'default', '<gm:string value="jsp.common.dialog.title"/>')){
				if($("#searchNm").val() == ""){
					var startDt = toDateObjectAdminPoC($("#startDate").val().replace(/-/gi, ""));
					var endDt = toDateObjectAdminPoC($("#endDate").val().replace(/-/gi, ""));
					var termDate = (endDt.getTime()- startDt.getTime())/(24*60*60*1000);
					
					if(termDate <= 7 && termDate >= 0){
						submit_Result = true;
					}
				}else{
					submit_Result = true;
				}
				
				if(submit_Result){
					$("#searchNm").val($.trim($("#searchNm").val()));
					$("form[name=frm] input[name=sc\\.currentPageNo]").val(1);
					
					$("#frm").attr("action", env.contextPath + "/membermgr/listPurchaseList.omp");
					$("#frm").submit();
				}else{
					alert("<gm:string value='jsp.member.membermgr.PurchaseList.msg.search_chk'/>");
					
					return false;
				}
			}
		});
		
		// search initialization
		$("#searchInitBtn").click( function() {
			$("#payCls option:eq(0)").attr("selected", "true");
			$("#prchsStat option:eq(0)").attr("selected", "true");
			
			setOrderSearchDateAdminPoC("7day", document.frm.startDate, document.frm.endDate);
			
			$("#searchType option:eq(0)").attr("selected", "true");		// Search Type Initialization
			$("#searchNm").val("");
			$("#ex_hpno").hide();
		});
		
		$("#searchType").change( function() {
			$("#searchNm").val("");
			if($("#searchType").val() == "hpNo"){
				$("#ex_hpno").show();	
			} else {
				$("#ex_hpno").hide();
			}
		});

		// Cell Phone Purchase Cancel List Excel Download
		$("#hpCancelList").click(function(){
			$("#frm").attr("method", "get");
			$("#frm").attr("action", "hpPurcaseCancelListExcel.omp");
			$("#frm").submit();
		});
		
	});	// end $(document).ready
	
	
	// Purchase Cancel Popup
	function popOpen(mbrno, prchsId, name) {
		$("form[name=frm] input[name=purchase\\.mbrNo]").val(mbrno);
		$("form[name=frm] input[name=purchase\\.prchsId]").val(prchsId);
		
		var width = 440;
		var height = 310;
		
		openCenteredWindow("", width, height, "no", "cancelpop");
		
		$("#frm").attr("target", "cancelpop");
		if(name == "view"){
			$("#frm").attr("action", "popCancelView.omp");
		}else if(name == "cancel"){
			$("#frm").attr("action", "popPurchaseCancel.omp");
		}
		$("#frm").submit();
		
		return;
	}
	
	function payDateSet(payDate) {
		setOrderSearchDateAdminPoC(payDate, document.frm.startDate, document.frm.endDate);
	}
	
	function goPage(no) {
		if(showValidate('frm', 'default', '<gm:string value="jsp.common.dialog.title"/>')){
			$("#searchNm").val($.trim($("#searchNm").val()));
			$("form[name=frm] input[name=sc\\.currentPageNo]").val(no);
			
			$("form[name=frm] input[name=purchase\\.mbrNo]").val("");
			$("form[name=frm] input[name=purchase\\.prchsId]").val("");
			
			$("#frm").attr("target", "_self");
			$("#frm").attr("action", env.contextPath + "/membermgr/listPurchaseList.omp");
			$("#frm").submit();
		}
	}
	
</script>

<form id="frm" name="frm" method="post">
<input type="hidden" name="sc.currentPageNo" value="<g:tagAttb value='${sc.currentPageNo}'/>"/>
<input type="hidden" id="popupYn" name="purchase.popupYn" value="N"/>
<input type="hidden" name="purchase.mbrNo" value=""/>
<input type="hidden" name="purchase.prchsId" value=""/>
	<div id="location">
		首頁 &gt; 會員管理中心 &gt; 使用者管理 &gt; <strong>產品購買記錄</strong>
	</div><!-- //location -->

	<h1>產品購買記錄</h1>
	<!-- 2011-03-24 -->
	<table class="tabletype01">
		<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
		<tr>
			<th>搜尋條件</th>
			<td class="align_td line2_5">					
				<label for="#">付款方式</label>
                      <select id="payCls" name="sc.payCls" style="width:104px;">
                  		<gc:options group="OR0006" codeType="full" filter="adminpoc_puchaseList" value="${sc.payCls }"/>
                      </select>

				<label for="#">購買狀態</label>
                      <select id="prchsStat" name="sc.prchsStat" style="width:104px;">
                      	<option value="">全部</option>
					<gc:options group="OR0003" codeType="full" filter="adminpoc_puchaseList" value="${sc.prchsStat }"/>
                      </select>
				<br />
				<label for="#">購買日期</label>
				<input id="startDate" name="sc.purchaseStartDt" type="text" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.purchaseStartDt}' format='L####-##-##' />" readonly/> ~ 
				<input id="endDate" name="sc.purchaseEndDt" type="text" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.purchaseEndDt}' format='L####-##-##' />" 
					v:scompare="ge,@{sc.purchaseStartDt}" m:scompare="<gm:string value='jsp.member.common.msg.datechk'/>" readonly/> 
				<a class="btn_s" id="todayBtn" href="#" onclick="javascript:payDateSet('today');"><span>今日</span></a>
				<a class="btn_s" id="weekBtn" href="#" onclick="javascript:payDateSet('7day');"><strong>最近一周</strong></a>
				<a class="btn_s" id="monthBtn" href="#" onclick="javascript:payDateSet('1month');"><span>最近一個月</span></a>
			</td>
			<td rowspan="2" class="text_c" >
				<a class="btn" id="searchBtn" name="searchBtn" href="#"><strong>搜尋</strong></a>
				<a class="btn" id="searchInitBtn" name="searchInitBtn" href="#"><span>重新搜尋</span></a>
			</td>
		</tr>
		<tr>
			<th>搜尋關鍵字</th>
			<td class="align_td">
                      <select id="searchType" name="sc.searchType" style="width:104px;">
                      	<option value="id" <c:if test="${sc.searchType eq 'id' || sc.searchType eq null}">selected</c:if>>帳號</option>
                      	<option value="hpNo" <c:if test="${sc.searchType eq 'hpNo'}">selected</c:if>>行動電話</option>
                      	<option value="prodNm" <c:if test="${sc.searchType eq 'prodNm' }">selected</c:if>>產品名稱</option>
						<option value="aid" <c:if test="${sc.searchType eq 'aid' }">selected</c:if> >產品AID</option>
                      	<option value="developer" <c:if test="${sc.searchType eq 'developer' }">selected</c:if> >開發商</option>
                      	<option value="rtNo" <c:if test="${sc.searchType eq 'rtNo' }">selected</c:if> >發票</option>
                      </select>
				<input id="searchNm" name="sc.searchNm" type="text" class="txt" style="width:80px;" value="<g:tagAttb value='${sc.searchNm}'/>" />
				<span id="ex_hpno">範例 : 01012341234</span>
			</td>
		</tr>
	</table>
</form>
	<p class="fl mt25 mb05">
		<a class="btn_s" href="#" id="hpCancelList"><strong>下載取消手機購買紀錄</strong></a>
	</p>
	<p class="fr mt35">共 : <strong><g:html value="${purchase.page.totalCount}"/></strong> 項 | 第<strong><g:html value="${purchase.page.no}"/></strong>頁/第<g:html value="${purchase.page.topPage}"/>頁</p>
	<div style="overflow:hidden;overflow-x:scroll;width:810px;clear:both;">
		<c:choose>
			<c:when test="${not empty resultList }">
				<table class="tabletype02" style="width:1000px;">
			</c:when>
			<c:otherwise>
				<table class="tabletype02">
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
				<col >
			</colgroup>
			<thead>
			<tr>
				<th>序號</th>
				<th>帳號</th>
				<th>購買日期</th>
				<th>產品名稱</th>
				<th>產品AID</th>
				<th>開發商</th>
				<th>付款方式</th>
				<th>付款金額</th>
				<th>取消日期</th>
				<th>下載</th>
				<th>驗證碼</th>
				<th>發票編號</th>
				<th>捐贈與否</th>
				<th>購買管理</th>
			</tr>
			</thead>
			<tbody>
				<form id="cancelFrm" name="cancelFrm">
					<c:choose>
						<c:when test="${searchYn eq 'N'}">
							<tr><td colspan="15"><gm:string value="jsp.admin.common.first.page"/></td></tr>
						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${not empty resultList }">
									<c:forEach items="${resultList }" var="item" varStatus="status">
										<tr>
											<td><g:html value="${item.rnum}" /></td>
											<td><a href="<c:url value='/userMemMgr/findUserMemberDetail.omp?userMember.mbrno=${item.mbrNo}&topCode=M001&leftCode=M001001001' />"><strong><g:html value="${item.mbrId}" /></strong></a></td>
											<td><g:text value="${item.prchsDt}" format="L####-##-##<br/>##:##" /></td>
											<td><g:html value="${item.prodNm}" /></td>
											<td><g:html value="${item.aid}" /></td>
											<td><g:html value="${item.devMbrId}" /></td>
											<td><gc:text code="${item.payCls}" /></td>
											<td><g:html value="${item.prchsAmt}" format="R#,###,###,###" /></td>
											<td><g:text value="${item.prchsCnclDtm}" format="L####-##-##<br/>##:##" /></td>
											<td><g:html value="${item.dwnldStat}" /></td>
											<td><g:html value="${item.applNum}" /></td>
											<td><g:html value="${item.oreRtNo}" /></td>
											<td><g:html value="${item.rtCbyn}" /></td>
											<td>
												<c:if test="${(empty item.prchsCnclDtm) and (item.payCls != CONST.PURCHASE_FREE_PROD) 
																	and (item.cancelYn eq 'Y') and (item.prchsStat eq CONST.PURCHASE_END)}">
													<a class="btn_s" href="javascript:popOpen('${item.mbrNo}', '${item.prchsId}', 'cancel');" ><span>取消</span></a>
												</c:if>
												<c:if test="${!empty item.prchsCnclDtm and item.prchsStat eq CONST.PURCHASE_CANCEL}">
													<a class="btn_s" href="javascript:popOpen('${item.mbrNo}', '${item.prchsId}', 'view');"><span>取消記錄</span></a>
												</c:if>
											</td>
										</tr>
									</c:forEach>	
								</c:when>
								<c:otherwise>
									<tr><td colspan="15">無資料.</td></tr>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</form>				
			</tbody>
		</table>
	</div>
	<!-- paging -->
	<div align="center">
		<g:pageIndex item="${resultList}" />
	</div>
	<!-- //paging -->