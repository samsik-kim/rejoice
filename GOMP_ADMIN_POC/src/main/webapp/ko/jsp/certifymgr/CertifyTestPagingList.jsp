<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Test List</title>
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
				Home &gt; 상품관리 &gt; 검증관리 &gt <strong>Test List</strong> 
			</div><!-- //location -->
 
			<h1 class="fl pr10">Test List</h1>
			<p>Application Test List를 확인하실 수 있습니다.</p>
<form id="searchForm" name="searchForm"  action="<c:url value="/certifymgr/testAppList.omp"/>" method="post">
			<input type="hidden" id="no" name="searchCon.page.no" value="${searchCon.page.no }" />
			<input type="hidden" id="searchType" name="searchCon.searchType" value="prodNm" />			
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>검색조건</th>
					<td class="line2_5">					
						<label for="#" style="width:80px;">Platform</label>
                        <select id="vmType" name="searchCon.vmType" style="width:104px;">
                        	<option value="">전체</option>
                        	<gc:options group="PD0056" value="${searchCon.vmType}" codeType="full" />
                        </select>
						<br />
 
						<label for="#" style="width:80px;">검증할당</label>
						<input id="ctAssignDtFrom" name="searchCon.ctAssignDtFrom" type="text" class="txt" style="width:80px;" value="<g:html value="${searchCon.ctAssignDtFrom}" format="L####-##-##" />" readonly 
							v:required m:required="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.start_date'/>" m:scompare="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.date01'/>" /> ~ 
						<input type="text" id="ctAssignDtTo" name="searchCon.ctAssignDtTo" class="txt" style="width:80px;" value="<g:html value="${searchCon.ctAssignDtTo}" format="L####-##-##" />" readonly 
							v:required m:required="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.end_date'/>"
							v:scompare="ge,@{searchCon.ctAssignDtFrom}" m:scompare="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.date02'/>" />
 
						<br />
						<label for="#" style="width:80px;">검증완료예정</label>
						<input id="ctEndExDtFrom" name="searchCon.ctEndExDtFrom" type="text" class="txt" style="width:80px;" value="<g:html value="${searchCon.ctEndExDtFrom}" format="L####-##-##" />" readonly 
							v:required m:required="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.start_date'/>" m:scompare="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.date01'/>"/> ~ 
						<input type="text" id="ctEndExDtTo" name="searchCon.ctEndExDtTo" class="txt" style="width:80px;" value="<g:html value="${searchCon.ctEndExDtTo}" format="L####-##-##" />" readonly 
							v:required m:required="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.end_date'/>"
							v:scompare="ge,@{searchCon.ctAssignDtFrom}" m:scompare="<gm:string value='jsp.certifymgr.certifyTestPagingList.msg.date02'/>" />
					</td>
					<td rowspan="2" class="text_c" >
						<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
						<a class="btn" href="#" id="searchInitBtn"><span>검색초기화</span></a>
					</td>
				</tr>
				<tr>
					<th>검색어</th>
					<td class="align_td">
						<label for="#">상품명</label>
						<input id="searchValue" name="searchCon.searchValue" value="${searchCon.searchValue}" type="text" class="txt" style="width:80%;" />
					</td>
				</tr>
			</table>
			<p class="mt25">▶ Total : <strong class="point2">${searchedTotalCount}</strong> 건</p>
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
					<th>상품 AID</th>
					<th>Platform</th>
					<th>App Name</th>
					<th>검증상태</th>
					<th>검증차수</th>
					<th>검증할당일</th>
					<th>검증완료예정일</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${searchedTotalCount > 0}">
						<c:forEach items="${list}" var="info">				
							<tr>
								<td>${info.rnum}</td>
								<td>${info.aid}</td>
								<td><gc:html code="${info.vmType}"/></td>
								<td><a href="<c:url value="/certifymgr/appDetail.omp?
									searchConAppDetail.cid=${info.cid}&searchConAppDetail.verifyReqVer=${info.verifyReqVer}"/>&<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>">${info.prodNm}</a></td>
								<td><gc:html code="${info.testerCtStat}"/></td>
								<td>${info.verifyReqVer}</td>
								<td><g:html value="${info.ctAssignDtByMmDd}" format="L##/##" /></td>
								<td><g:html value="${info.ctEndExDtByMmDd}" format="L##/##" /></td>
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

