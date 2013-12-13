<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ page import="com.omp.admin.common.Constants" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" href="<c:url value="/${ThreadSession.serviceLocale.language}/css/popup.css"/>" type="text/css">
<style type="text/css">
form{clear:both;}
/*jquery ui dialog header hidden*/
/*.ui-widget-header{display:none} */
/*.ui-dialog .ui-dialog-content {overflow:hidden; padding: 0 0;} */
</style>
<script type="text/javascript" src="<c:url value="/js/product.js"/>"></script>
<script type="text/javascript">
//<![CDATA[
	$(function() {
		// ====================  Dev User Info ==========================		
		$("#devUserInfo, #devUserInfoBtn").click(function(event){
			event.preventDefault();
			var url = "<c:url value='/devMemMgr/findDevMemberDetail.omp?topCode=M001&leftCode=M001002001'/>";
			document.memberForm.action = url;
			document.memberForm.submit();
		});

		<c:choose>
		<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_ING or contents.saleStat eq CONTENT_SALE_STAT_STOP}">
			$("#stopSaleBtn").click(function(event){
				event.preventDefault();
				<c:choose>
				<c:when test="${contents.verifyPrgrYn eq CODE_VERIFY_REQ or contents.verifyPrgrYn eq CODE_VERIFY_ING}">
				alert('<gm:string value="jsp.product.not.modify"><gm:arg><gc:html code="${contents.verifyPrgrYn }"/></gm:arg></gm:string>');
				</c:when>
				<c:otherwise>
				if(confirm('<gm:string value="jsp.product.execute.confirm"><gm:arg><gm:string value="jsp.product.saleStat.saleStop"/></gm:arg></gm:string>')){
					$.post('<c:url value="/product/ajaxUpdateStopSaleStat.omp"/>',{cid:"${contents.cid}"}, 
						function(data){
							alert(data.msg);
							// if success
							if(data.resultCode == 1){
								$("#contentsBaseInfo").click();		
							}
					},"json");		
				}
				</c:otherwise>
				</c:choose>
			});
		</c:when>
		<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_RESTRIC or contents.saleStat eq CONTENT_SALE_STAT_UNREGIST}">
			$("#startSaleBtn").click(function(event){
				event.preventDefault();
				<c:choose>
				<c:when test="${contents.verifyPrgrYn eq CODE_VERIFY_REQ or contents.verifyPrgrYn eq CODE_VERIFY_ING}">
				alert('<gm:string value="jsp.product.not.modify"><gm:arg><gc:html code="${contents.verifyPrgrYn }"/></gm:arg></gm:string>');
				</c:when>
				<c:otherwise>
				if(confirm('<gm:string value="jsp.product.execute.confirm"><gm:arg><gm:string value="jsp.product.saleStat.saleStart"/></gm:arg></gm:string>')){
					$.post('<c:url value="/product/ajaxUpdateStartSaleStat.omp"/>',{cid:"${contents.cid}"}, 
						function(data){
							alert(data.msg);
							// if success
							if(data.resultCode == 1){
								$("#contentsBaseInfo").click();							
							}
					},"json");
				}
				</c:otherwise>
				</c:choose>
			});
		</c:when>
		</c:choose>
		
		// ==================== change sign option ======================
		$("#changeSignOptionBtn").click(function(event){
			event.preventDefault();
			<c:choose>
			<c:when test="${contents.verifyPrgrYn eq CODE_VERIFY_REQ or contents.verifyPrgrYn eq CODE_VERIFY_ING}">
			alert('<gm:string value="jsp.product.not.modify"><gm:arg><gc:html code="${contents.verifyPrgrYn }"/></gm:arg></gm:string>');
			</c:when>
			<c:otherwise>
			
			if($("#signOptionOrg").val() == $("#signOption").val()){
				alert('<gm:string value="jsp.product.sign.not.modify.msg"/>');
				return;
			}
			
			if(confirm('<gm:string value="jsp.product.signLevel.change.confirm"/>')){
				$.post('<c:url value="/product/ajaxUpdateSignOption.omp"/>',{cid:"${contents.cid}", signOption:$("#signOption option:selected").val()}, 
					function(data){
						alert(data.msg);
						// if success
						if(data.resultCode == 1){
							$("#contentsBaseInfo").click();							
						}
				},"json");
			}
			</c:otherwise>
			</c:choose>
		});
		function rateFormInit(){
			$("#rateForm").reset();
			$("#popAdjRate").val($("#adjRate").val());
			$("#popAdjRateSkt").val($("#adjRateSkt").val());
		}
		// ==================== change category =============================
		$("#popAdjRate").change(function(event){
			if(!isNumeric($("#popAdjRate").val()) || $("#popAdjRate").val() < 0){
				alert('<gm:string value="jsp.product.validate.needNumber"/>');
				rateFormInit();
				return false;
			}
			
			if($("#popAdjRate").val() > 100){
				alert('<gm:string value="jsp.product.rate.wrongSum"/>');
				rateFormInit();
				return false;
			}
			
			$("#popAdjRateSkt").val(100-$("#popAdjRate").val());
		});
	
		$( "#rateDiv" ).dialog({
			modal: true,
			autoOpen: false,
			resizable: false,
			width:480
		});
		// change category btn in page
		$("#changeRateBtn").click(function(event){
			event.preventDefault();
			rateFormInit();
			$("#rateDiv").dialog('open');
		});
		// popup close btn
		$("#doCancelRateBtn").click(function(event){
			event.preventDefault();
			$("#rateDiv").dialog('close');
		});
		// change category btn in popup
		$("#doChangeRateBtn").click(function(event){
			event.preventDefault();
			if(confirm('<gm:string value="jsp.product.rate.change.confirm"/>')){
				$.post('<c:url value="/product/ajaxUpdateRate.omp"/>',{cid:"${contents.cid}", adjRate:$("#popAdjRate").val(), adjRateSkt:$("#popAdjRateSkt").val()}, 
						function(data){
							alert(data.msg);
							// if success
							if(data.resultCode == 1){
								$("#contentsBaseInfo").click();							
							}
					},"json");
			}
			
			$("#rateDiv").dialog('close');
		});
	});
//]]>
</script>
</head>
<body>
			<div id="location">
				首頁 &gt; 產品管理中心 &gt; 產品管理中心 &gt; <strong>產品資訊</strong>
			</div><!-- //location -->

			<h1 class="fl pr10">產品資訊</h1>
			<p>可查詢產品資訊及變更產品狀態。</p>
			<c:choose>
			<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_ING or contents.saleStat eq CONTENT_SALE_STAT_STOP}">
				<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="stopSaleBtn"><strong>禁售</strong></a></p>
			</c:when>
			<c:when test="${ contents.saleStat eq CONTENT_SALE_STAT_RESTRIC or contents.saleStat eq CONTENT_SALE_STAT_UNREGIST}">
				<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="startSaleBtn"><strong>解禁</strong></a></p>
			</c:when>
			</c:choose>
			
			<div class="tab">
				<ul>
					<li class="on"><a href="#" id="contentsBaseInfo">基本資訊</a></li>
					<li><a href="#" id="contentsProductInfo">產品資訊</a></li>
					<li><a href="#" id="devConts">開發資訊</a></li>
					<c:if test="${CONTENT_SALE_STAT_ING eq contents.saleStat}">
					<li><a href="#" id="saleDevConts">販售中產品開發資訊</a></li>
					</c:if>
					<c:if test="${CONTENT_SALE_STAT_NA ne contents.saleStat}">
					<li><a href="#" id="signDevConts">請審產品開發資訊</a></li>
					</c:if>
					<li><a href="#" id="saleStatHisList">狀態變更紀錄</a></li>
					<li><a href="#" id="productVerifyDetail">審核紀錄</a></li>
				</ul>
			</div>
			<table class="tabletype01">
				<colgroup><col style="width:150px;"><col ></colgroup>
				<tbody>
				<tr>
					<th>產品名稱</th>
					<td>${contents.prodNm }</td>
				</tr>
				<tr>
					<th>產品AID</th>
					<td>${contents.aid }</td>
				</tr>
				<tr>
					<th>首次上架日期</th>
					<td><g:text format="L####-##-##" >${contents.firstInsDt }</g:text></td>
				</tr>
				<tr>
					<th>開發商</th>
					<td>
						<a href="#" id="devUserInfo"><strong>${contents.mbrId} (${contents.mbrNm} )</strong></a>
						<a href="#" class="btn_s" id="devUserInfoBtn"><span>詳細內容</span></a>
					</td>
				</tr>
				<tr>
					<th>販售狀態</th>
					<td><gc:html code="${contents.saleStat}"/></td>
				</tr>
				<tr>
					<th>首次核准日期</th>
					<td><g:text format="L####-##-##" >${contents.firstAgrmntDt}</g:text></td>
				</tr>
				<tr>
					<th>首次開售日期</th>
					<td><g:text format="L####-##-##" >${contents.saleStartDt}</g:text></td>
				</tr>
				<tr>
					<th>拆分比</th>
					<td class="align_td">
						<label for="adjRate">開發商</label>
						<input id="adjRate" type="text" class="txt" style="width:50px;" value="${contents.adjRate}" readonly/>  :
						<label for="adjRateSkt">Whoopy</label>
						<input id="adjRateSkt" type="text" class="txt" style="width:50px;" value="${contents.adjRateSkt}" readonly/> 
						<a class="btn_s" href="#" id="changeRateBtn"><span>變更拆分比</span></a>
					</td>
				</tr>
				<tr>
					<th>審核級別</th>
					<td class="align_td">
						<input type="hidden" id="signOptionOrg" value="${contents.signOption }"/>
						<select style="width:120px;" id="signOption">
                        	<gc:options group="PD0110" value="${contents.signOption }" codeType="full" />
                        </select>	
						<a class="btn_s" href="#" id="changeSignOptionBtn"><span>變更級別</span></a>
					</td>
				</tr>
				</tbody>
			</table>
			<p class="btn_wrap text_r mt25"><a class="btn" href="#" id="contentsList"><span>目錄</span></a></p>
			<s:form id="searchForm" name="searchForm" action="listContents" theme="simple"  method="get">
			<input type="hidden" id="cid" name="sub.cid" value="${sub.cid }"/>
			<input type="hidden" id="searchToday" name="sub.searchToday" value="${sub.searchToday }" class="searchDate" rel="searchTodayBtn" />
			<input type="hidden" id="searchWeek" name="sub.searchWeek" value="${sub.searchWeek }"  class="searchDate" rel="searchWeekBtn" />
			<input type="hidden" id="searchMonth" name="sub.searchMonth" value="${sub.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
			<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />
			<input type="hidden" id="masterNo" name="sub.masterNo" value="${sub.masterNo }" />
			<input type="hidden" id="vmType" name="sub.vmType" value="${sub.vmType }" />
			<input type="hidden" id="saleStat" name="sub.saleStat" value="${sub.saleStat }" />
			<input type="hidden" id="verifyPrgrYn" name="sub.verifyPrgrYn" value="${sub.verifyPrgrYn }" />
			<input type="hidden" id="startDate" name="sub.startDate" value="${sub.startDate }" />
			<input type="hidden" id="endDate" name="sub.endDate" value="${sub.endDate }" />
			<input type="hidden" id="dpCat1" name="sub.dpCat1" value="${sub.dpCat1 }" />
			<input type="hidden" id="dpCat2" name="sub.dpCat2" value="${sub.dpCat2 }" />
			<input type="hidden" id="searchType" name="sub.searchType" value="${sub.searchType }" />
			<input type="hidden" id="searchText" name="sub.searchText" value="${sub.searchText }" />
			</s:form>
			<div class="popup" id="rateDiv" title="變更拆分比">
				<div id="popup_area_440">
					<h1>變更拆分比 | <span class="h1_sub">可於此變更拆分比。</span></h1>
					<form id="rateForm" name="rateForm">
					<table class="pop_tabletype02">
						<colgroup><col style="width:50%;"><col ></colgroup>
						<tbody>
						<tr>
							<th>開發商</th>
							<th>Whoopy</th>
						</tr>
						<tr>
							<td>
								<input id="popAdjRate" type="text" class="txt" style="width:90%;" />
							</td>
							<td class="align_td">
								<input id="popAdjRateSkt" type="text" class="txt" style="width:90%;" readonly/>
							</td>
						</tr>
						</tbody>
					</table>
					</form>
					<div class="pop_btn" >
						<a class="btn_s" href="#" id="doChangeRateBtn"><strong>變更</strong></a>
						<a class="btn_s" href="#" id="doCancelRateBtn"><strong>取消</strong></a>
					</div>
				</div>	
			</div>
			<form id="memberForm" name="memberForm" method="post">
				<input type="hidden" id="mbrno" name="devMember.mbrno" value="${contents.mbrNo}"/>
			</form>
</body>
</html>