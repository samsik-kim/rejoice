<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%
	request.setAttribute("mode", request.getParameter("mode"));
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<script type="text/javascript">
//<![CDATA[
	$(function() {
		var form = document.searchForm;
		// search start ~ end date datepicker
		$( "#startDate" ).datepicker();
		$( "#endDate" ).datepicker();
		
		// today, weekly, monthly search btn action
		$(".searchDateBtn").click(function(event){
			event.preventDefault();
			var clickedId = this.id;
			
			// strong / span switch
			$(".searchDateBtn").each(function(){
				if(clickedId == this.id){
					$(this).html("<strong>"+$(this).text()+"</stong>");
					
				}else{
					$(this).html("<span>"+$(this).text()+"</span>");
				}
			});
			// searchDate Condition setting
			$(".searchDate").each(function(){
				if(clickedId == $(this).attr('rel')){
					$(this).val("Y");
				}else{
					$(this).val("");
				}
			});
			
			// startDate, endDate calculate (7day, .. , ..);
			setOrderSearchDateAdminPoC($(this).attr("rel"),form.startDate, form.endDate);
			// search
			funcSearch();
		});
		
		// startDate, endDate onChange Action
		$("#startDate, #endDate ").change(function(event){
			// strong / span switch
			$(".searchDateBtn").each(function(){
				$(this).html("<span>"+$(this).text()+"</span>");
			});
			// searchDate Condition setting
			$(".searchDate").each(function(){
				$(this).val("");
			});
		});
		
		// searchDateBtn init condition (onLoad apply)
		$(".searchDate").each(function(){
			var btnId = $(this).attr("rel");
			if($(this).val() == 'Y'){
				$("#"+btnId).html("<strong>"+$("#"+btnId).text()+"</stong>");
			}
		});
		
		// search btn
		$("#searchBtn").click(function(event){
			event.preventDefault();
			funcSearch();
		});
		
		// search init btn	
		$("#searchInitBtn").click(function(event){
			event.preventDefault();
			$.clearForm("searchForm", true, false);
			$("#searchWeekBtn").click();
			funcSearch();
		});
		
		// excel download btn 
		$("#excelDownBtn").click(function(event){
			event.preventDefault();
			if ('${totalCount}'  == '0') {
				alert('<gm:string value="jsp.product.noSearchMsg"/>');
				return;
			}
			excelDown();
		});
		
		// searchType change Action
		$("#searchType").change(function (e){
			$("#searchText").val('');
		});
		
		// searchText Enter
		$("#searchText").keypress(function (e){
			if(e.which == 13){ 
				funcSearch();
			}
		});
		
	});
	
	function goPage(no) {
	    $("#no").val(no);
	    $("#masterNo").val(no);
	    $("#searchBtn").click();
	}
	
	function funcSearch(){
		if(showValidate('searchForm', 'default', '<gm:string value="jsp.product.validate.commMsg"/>')){
			
			document.searchForm.action="listContents.omp";
			document.searchForm.submit();
		}
	}
	
	function contentsView(cid){
		$("#cid").val(cid);
		document.searchForm.action="<c:url value="/product/viewContentsBaseInfo.omp"/>";
		document.searchForm.submit();
	}
	
	function excelDown() {
		if(showValidate('searchForm', 'dialog', '<gm:string value="jsp.product.validate.commMsg"/>')){
			
			document.searchForm.action="downloadContentList.omp";
			document.searchForm.submit();
		}
	}
	
	function saleStop(cid){
		if(confirm('<gm:string value="jsp.product.execute.confirm"><gm:arg><gm:string value="jsp.product.saleStat.saleStop"/></gm:arg></gm:string>')){
			$.post('<c:url value="/product/ajaxUpdateStopSaleStat.omp"/>',{cid:cid}, 
				function(data){
					alert(data.msg);
					// if success
					if(data.resultCode == 1){
						window.location.reload(true);
					}
			},"json");		
		}
	}
	
	function saleStart(cid){
		if(confirm('<gm:string value="jsp.product.execute.confirm"><gm:arg><gm:string value="jsp.product.saleStat.saleStart"/></gm:arg></gm:string>')){
			$.post('<c:url value="/product/ajaxUpdateStartSaleStat.omp"/>',{cid:cid}, 
				function(data){
					alert(data.msg);
					// if success
					if(data.resultCode == 1){
						window.location.reload(true);				
					}
			},"json");
		}
	}
	
	function deployFull(cid, type){
		var msg = "";
		if(type == 'dd'){
			msg=('CID:'+cid+' <gm:string value="jsp.product.execute.confirm"><gm:arg><gm:string value="jsp.product.deploy.download"/></gm:arg></gm:string>');
		}else if(type == 'dp'){
			msg=('CID:'+cid+' <gm:string value="jsp.product.execute.confirm"><gm:arg><gm:string value="jsp.product.deploy.display"/></gm:arg></gm:string>');
		}else{
			alert('<gm:string value="jsp.product.wrong.request"/>');
			return;
		}
		
		if(confirm(msg)){
			
			$.post('<c:url value="/product/ajaxDeplyFullTemporary.omp"/>',{cid:cid, type:type}, 
				function(data){
					alert(data.msg);
					// if success
					if(data.resultCode == 1){
						// window.location.reload(true);
					}
			},"json");
		}
	}
	
//]]>
</script>
</head>
<body>
			<div id="location">
				Home &gt; 상품관리 &gt; 상품관리 &gt; <strong>상품정보</strong>
			</div><!-- //location -->

			<h1 class="fl pr10">상품정보</h1>
			<p>상품정보 조회 및 상품상태를 변경 관리합니다.</p>
			<s:form id="searchForm" name="searchForm" action="listContents" theme="simple" method="get">
			<input type="hidden" id="cid" name="sub.cid" value=""/>
			<input type="hidden" id="searchToday" name="sub.searchToday" value="${sub.searchToday }" class="searchDate" rel="searchTodayBtn" />
			<input type="hidden" id="searchWeek" name="sub.searchWeek" value="${sub.searchWeek }"  class="searchDate" rel="searchWeekBtn" />
			<input type="hidden" id="searchMonth" name="sub.searchMonth" value="${sub.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
			<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />
			<input type="hidden" id="masterNo" name="sub.masterNo" value="${sub.page.no }" />
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>검색조건</th>
					<td class="align_td">
						<label for="vmType">Platform</label>
                        <select id="vmType" name="sub.vmType" style="width:104px;">
                        	<gc:options group="PD0056" value="${sub.vmType }" codeType="full"/> 
                        </select>

						<label for="saleStat">판매상태</label>
                        <select id="saleStat" name="sub.saleStat" "style="width:104px;" >
                        	<option value="">전체</option>
                        	<gc:options group="PD0004" value="${sub.saleStat }" codeType="full" /> 
                        </select>

						<label for="verifyPrgrYn">검증상태</label>
                        <select id="verifyPrgrYn" name="sub.verifyPrgrYn" style="width:104px;">
                        	<option value="">전체</option>
                        	<gc:options group="PD0053" value="${sub.verifyPrgrYn }" codeType="full" filter="pd"/>
                        </select>
						<br />
						<label for="startDate">검색기간</label>
						<input id="startDate" name="sub.startDate" type="text" class="txt" style="width:80px;" value="${sub.startDate }" readonly v:required m:required='<gm:tagAttb value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.list.search.startDate"/></gm:arg></gm:tagAttb>'/> ~ 
						<input id="endDate" name="sub.endDate" type="text" class="txt" style="width:80px;" value="${sub.endDate }" readonly v:required m:required='<gm:tagAttb value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.list.search.endDate"/></gm:arg></gm:tagAttb>' v:scompare='ge,@{sub.startDate}' m:scompare='<gm:tagAttb value="jsp.list.validate.wrongDate"/>'/> 
						<a class="btn_s searchDateBtn" href="#" id="searchTodayBtn" rel="today"><span>오늘</span></a>
						<a class="btn_s searchDateBtn" href="#" id="searchWeekBtn" rel="7day"><span>최근1주</span></a>
						<a class="btn_s searchDateBtn" href="#" id="searchMonthBtn" rel="1month"><span>최근1개월</span></a>
					</td>
					<td rowspan="2" class="text_c" >
						<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
						<a class="btn" href="#" id="searchInitBtn"><span>검색초기화</span></a>
					</td>
				</tr>
				<tr>
					<th>검색어</th>
					<td class="align_td">
						<input type="hidden" id="dpCat1" name="sub.dpCat1" value="${sub.dpCat1 }"/>
                        <select id="dpCat2" name="sub.dpCat2" style="width:104px;">
                        	<option value="">카테고리전체</option>
                        	<c:forEach items="${dpCatList2 }" var="dpCat">
                        		<option value="${dpCat.dpCatNo }" <c:if test='${dpCat.dpCatNo eq sub.dpCat2 }'>selected</c:if>>${dpCat.dpCatNm }</option>
                        	</c:forEach>
                        </select>
						<select id="searchType" name="sub.searchType" style="width:104px;">
                        	<option value="prd" <c:if test='${sub.searchType eq "prd" }'>selected</c:if>>상품명</option>
                        	<option value="devId" <c:if test='${sub.searchType eq "devId" }'>selected</c:if>>개발자ID</option>
                        	<option value="aid" <c:if test='${sub.searchType eq "aid" }'>selected</c:if>>AID</option>
                        	<option value="cid" <c:if test='${sub.searchType eq "cid" }'>selected</c:if>>CID</option>
                        </select>
                        <input id="searchText" name="sub.searchText" type="text" class="txt" style="width:120px;" value="${sub.searchText }"/>
					</td>
				</tr>
				
			</table>
			</s:form>
			<!-- 검색결과가 없습니다. -->
			<!-- 리스트 시작 -->
			<table class="tabletype02 mt25" id="contsTable">
				<colgroup>
					<col >
					<col >
					<col style="width:100px;" >
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
					<th>이미지</th>
					<th>상품명</th>
					<th>카테고리</th>
					<th>상품AID</th>
					<th>개발자</th>
					<th>가격</th>
					<th>판매 상태</th>
					<th>검증 상태</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${firstSearch }">
				<tr>
					<td colspan="9" class="text_c"><gm:html value="jsp.product.initSearchMsg"/></td>
				</tr>
				</c:when>
				<c:when test="${totalCount eq 0 }">
				<tr>
					<td colspan="9" class="text_c"><gm:html value="jsp.product.noSearchMsg"/></td>
				</tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${list }" var="product">
				<tr>
					<td>${product.totalCount - product.rnum + 1}</td>
					<td><img src="${CONF['omp.common.url.http-share.product']}${product.statusImgPos}" width="52" height="52" alt="" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');"/></td>
					<td class="text_l">
						<a href="#" onclick="contentsView('${product.cid}')">${product.prodNm }</a>
					</td>
					<td>${product.ctgrNm2}</td>
					<td>${product.aid}</td>
					<td>${product.mbrId}</td>
					<td><g:text value="${product.prodPrc}" format="R###,###"/></td>
					<td>
						<gc:html code="${product.saleStat}"/>
						<c:choose>
						<c:when test="${product.saleStat eq  CONTENT_SALE_STAT_ING or product.saleStat eq CONTENT_SALE_STAT_STOP}">
						<c:if test="${product.verifyPrgrYn ne CODE_VERIFY_REQ && product.verifyPrgrYn ne CODE_VERIFY_ING }" >
							<br/><a href="#" class="btn_s" id="stopSaleBtn" onclick="saleStop('${product.cid}');"><span>판매금지</span></a>
						</c:if>
						<c:if test="${mode eq 'dev' }">
						<br/><a href="#" class="btn_s" id="dpFullBtn" onclick="deployFull('${product.cid}', 'dp');"><span>전시배포</span></a>
						<a href="#" class="btn_s" id="ddFullBtn" onclick="deployFull('${product.cid}', 'dd');"><span>DL배포</span></a>
						</c:if>
						</c:when>
						<c:when test="${product.saleStat eq CONTENT_SALE_STAT_RESTRIC or  product.saleStat eq CONTENT_SALE_STAT_UNREGIST}">
						<br/><a href="#" class="btn_s" id="startSaleBtn" onclick="saleStart('${product.cid}');"><span>해제</span></a>
						</c:when>
						</c:choose>
					</td>
					<td><gc:html code="${product.verifyPrgrYn}"/></td>
				</tr>
				</c:forEach>
				</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<p class="btn_wrap"><a class="btn" href="#" id="excelDownBtn"><span>EXCEL다운로드</span></a></p>
			<!-- paging -->
			<div align="center">
		        <g:pageIndex item="${list}"/>
		    </div>
			<!-- //paging -->
</body>
</html>