<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>查看審核紀錄之跳出式視窗</title>
<script type="text/javascript" src="<c:url value="/js/inc/_pophead.js"/>"></script>
<script type="text/javascript">
//<![CDATA[
           
	$(function() {
		
		jQuery.fn.selfPage = {
				
			    /**
			     * Detail History Search
			     *
			     */  
			    searchDetailHistory : function (verifyReqVer){
			        
			    	$("#verifyReqVer").val(verifyReqVer);
			    	
			    	var param = $("#searchDetailForm").serialize();
			        
			        $.ajax({
			            type: "POST",
			            async : false,
			            url: env.contextPath + "/certifymgr/detailHistoryByAjax.omp",
			            data : param,
			            dataType:  "html",
			            success: function(transport){
						    try{
						    	$("#tempId").val(transport);
						    	
						    	$("#detailHistory").html(transport);
							}catch(e){
							}
			            },
			            error: function(xhr, textStatus, errorThrown){
			            }
			        });                     
			    }	    			    
		};			
		
		
		$.fn.selfPage.searchDetailHistory("${searchCon.verifyReqVer}");

		
		$("a[name='detailHistoryLink']").click(function(event){
			
			var verifyReqVer = $(this).attr("verifyReqVer");
			
			$.fn.selfPage.searchDetailHistory(verifyReqVer);
		});
		
		$("#closeBtn").click(function(event){
			event.preventDefault();
			window.close();
		});				
		
	});
	
	function goPage(no) {
	    $("#no").val(no);
	    $("#searchForm").submit();
	}	
//]]>
</script>
</head>
<body class="popup">
	<div id="popup_area_810">
		<h1>審核紀錄</h1>
<form id="searchForm" name="searchForm"  action="<c:url value="/certifymgr/popHistoryList.omp"/>" method="post">
	<input type="hidden" id="no" name="searchCon.page.no" value="${searchCon.page.no }" />		
	<input type="hidden" id="cid" name="searchCon.cid" value="${searchCon.cid }" />
		<table class="pop_tabletype02">
			<colgroup><col style="width:15%;"><col style="width:20%"><col style="width:10%;"><col style="width:55%"></colgroup>
			<tbody>
			<tr>
				<th>審核次數</th>
				<th>審核完畢日期</th>
				<th>審核結果</th>
				<th>上傳檔案</th>
			</tr>
			<c:choose>
				<c:when test="${searchedTotalCount > 0}">
					<c:forEach items="${list}" var="info">		
						<tr>
							<td><a href="#" name="detailHistoryLink" verifyReqVer="${info.verifyReqVer}">${info.verifyReqVer}次</a></td>
							<td><g:html value="${info.ctEndDt}" format="L####-##-##" /></td>
							<td><gc:html code="${info.verifyPrgrYn}"/></td>
							<td class="text_l"><c:forEach items="${info.subContentsHistoryList}" var="apkInfo">${apkInfo.runFileNm }<br></c:forEach></td>
						</tr>					
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="19" class="text_c"><gm:string value='jsp.certifymgr.common.msg.list_result' /></td></tr>
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
		<div id="detailHistory"></div>
		
<form id="searchDetailForm" name="searchDetailForm" method="post">	
	<input type="hidden" id="verifyReqVer" name="searchDetailCon.verifyReqVer" value="" />
	<input type="hidden" name="searchDetailCon.cid" value="${searchCon.cid }" />
</form>			
		<div class="pop_btn" >
			<a class="btn" href="#" id="closeBtn"><strong>關閉</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>

