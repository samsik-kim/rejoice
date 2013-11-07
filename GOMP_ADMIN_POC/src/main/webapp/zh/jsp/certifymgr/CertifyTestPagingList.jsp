<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>測試目錄</title>
<script type="text/javascript">
//<![CDATA[
           
	$(function() {
		
		$( "#ctAssignDtFrom" ).datepicker();
		$( "#ctAssignDtTo" ).datepicker();
		$( "#ctEndExDtFrom" ).datepicker();
		$( "#ctEndExDtTo" ).datepicker();
		
		
		$("#searchBtn").click(function(event){
			event.preventDefault();

			if(showValidate("searchForm", 'default')){// Validation Skip
				
				$("#searchForm").submit();
			}
		});		
		
		$("#searchInitBtn").click(function(event){
			event.preventDefault();
			$("#searchForm").reset();
		});		
		
	});
	
	
	function goPage(no) {
	    $("#no").val(no);
	    $("#searchBtn").click();
	}
	
	//]]>
	</script>
</head>
<body>
			<div id="location">
				首頁 &gt; 產品管理中心 &gt; 審核管理 &gt <strong>測試目錄</strong>
			</div><!-- //location -->
 
			<h1 class="fl pr10">測試目錄</h1>
			<p>可確認應用軟體之測試目錄。</p>
<form id="searchForm" name="searchForm"  action="<c:url value="/certifymgr/testAppList.omp"/>" method="post">
			<input type="hidden" id="no" name="searchCon.page.no" value="${searchCon.page.no }" />
			<input type="hidden" id="searchType" name="searchCon.searchType" value="prodNm" />			
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>搜尋條件</th>
					<td class="line2_5">					
						<label for="#" style="width:80px;">平台</label>
                        <select id="vmType" name="searchCon.vmType" style="width:104px;">
                        	<option value="">全部</option>
                        	<gc:options group="PD0056" value="${searchCon.vmType}" codeType="full" />
                        </select>
						<br />
 
						<label for="#" style="width:80px;">測試分配</label>
						<input id="ctAssignDtFrom" name="searchCon.ctAssignDtFrom" type="text" class="txt" style="width:80px;" value="<g:html value="${searchCon.ctAssignDtFrom}" format="L####-##-##" />" readonly 
							v:required m:required="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.start_date'/>" m:scompare="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.date01'/>" /> ~ 
						<input type="text" id="ctAssignDtTo" name="searchCon.ctAssignDtTo" class="txt" style="width:80px;" value="<g:html value="${searchCon.ctAssignDtTo}" format="L####-##-##" />" readonly 
							v:required m:required="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.end_date'/>"
							v:scompare="ge,@{searchCon.ctAssignDtFrom}" m:scompare="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.date02'/>" />
 
						<br />
						<label for="#" style="width:80px;">審核完畢預期</label>
						<input id="ctEndExDtFrom" name="searchCon.ctEndExDtFrom" type="text" class="txt" style="width:80px;" value="<g:html value="${searchCon.ctEndExDtFrom}" format="L####-##-##" />" readonly 
							v:required m:required="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.start_date'/>" m:scompare="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.date01'/>"/> ~ 
						<input type="text" id="ctEndExDtTo" name="searchCon.ctEndExDtTo" class="txt" style="width:80px;" value="<g:html value="${searchCon.ctEndExDtTo}" format="L####-##-##" />" readonly 
							v:required m:required="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.end_date'/>"
							v:scompare="ge,@{searchCon.ctAssignDtFrom}" m:scompare="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.date02'/>" />
					</td>
					<td rowspan="2" class="text_c" >
						<a class="btn" href="#" id="searchBtn"><strong>搜尋</strong></a>
						<a class="btn" href="#" id="searchInitBtn"><span>重新搜尋</span></a>
					</td>
				</tr>
				<tr>
					<th>搜尋關鍵字</th>
					<td class="align_td">
						<label for="#">產品名稱</label>
						<input id="searchValue" name="searchCon.searchValue" value="${searchCon.searchValue}" type="text" class="txt" style="width:80%;" />
					</td>
				</tr>
			</table>
			<p class="mt25">▶ 共: <strong class="point2">${searchedTotalCount}</strong> 項</p>
			<table class="tabletype02">
				<colgroup>
					<c:choose>
					<c:when test="${searchedTotalCount > 0}">
						<col style="width:40px;" >		
					</c:when>
					<c:otherwise>
						<col style="width:8%;" >
					</c:otherwise>
				</c:choose>
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
					<th>No</th>
					<th>產品AID</th>
					<th>平台</th>
					<th>產品名稱</th>
					<th>審核狀態</th>
					<th>審核次數</th>
					<th>審核分配日期</th>
					<th>審核完畢預期</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${searchedTotalCount > 0}">
						<c:forEach items="${list}" var="info">				
							<tr>
								<td>${searchedTotalCount - info.rnum + 1}</td>
								<td>${info.aid}</td>
								<td><gc:html code="${info.vmType}"/></td>
								<td><a href="<c:url value="/certifymgr/appDetail.omp?
									searchConAppDetail.cid=${info.cid}&searchConAppDetail.verifyReqVer=${info.verifyReqVer}"/>&<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>">${info.prodNm}</a></td>
								<td><gc:html code="${info.testerCtStat}"/></td>
								<td>${info.verifyReqVer}</td>
								<td><g:html value="${info.ctAssignDtByMmDd}" format="L####-##-##" /></td>
								<td><g:html value="${info.ctEndExDtByMmDd}" format="L####-##-##" /></td>
							</tr>
						</c:forEach>			
					</c:when>
					<c:otherwise>
						<tr><td colspan="19" class="text_c">${listResultMsg }</td></tr>
					</c:otherwise>
				</c:choose>				
				</tbody>
			</table>
 
			<!-- paging -->
			<div align="center">
		        <g:pageIndex item="${list}"/>
		    </div>
			<!-- //paging -->
		</div>
</body>
</html>

