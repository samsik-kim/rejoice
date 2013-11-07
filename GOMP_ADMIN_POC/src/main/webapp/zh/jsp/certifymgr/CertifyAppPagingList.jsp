<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>搜尋應用軟體</title>

<script type="text/javascript">
//<![CDATA[
           
	$(function() {
		
		jQuery.fn.selfPage = {
			    /**
			     * Device List Search
			     *
			     * @param makeComp
			     */  
			    searchPhoneList : function (makeComp){
			        
			        var param = "searchConPhoneInfo.makeComp=" + makeComp;
			        
			        $.ajax({
			            type: "POST",
			            async : false,
			            url: env.contextPath + "/certifymgr/phoneListByAjax.omp",
			            data : param,
			            dataType:  "json",
			            success: function(transport){
						    try{
						    	var voList = eval(transport.phoneList);
						    	
						    	var strFirtOptionVal = $("#modelNm > option").html();
						    	
						    	$("#modelNm option").remove("");
						    	
						    	$("#modelNm").html("<OPTION value=''>" + strFirtOptionVal + "</OPTION>");
						    	
								jQuery.each(voList, function(i){
									
									$("#modelNm").append("<OPTION value='" + this.modelNm + "'>" + this.modelNm + "</OPTION>");
								});
								
							}catch(e){
							}
			            },
			            error: function(xhr, textStatus, errorThrown){}
			        });                     
			    }
		}
		
		$("#searchType").change( function() {
			$("#searchValue").val("");
		});
		
		$( "#certifyRequestDtFrom" ).datepicker();
		$( "#certifyRequestDtTo" ).datepicker();
		$( "#ctAssignDtFrom" ).datepicker();
		$( "#ctAssignDtTo" ).datepicker();
		$( "#ctEndDtFrom" ).datepicker();
		$( "#ctEndDtTo" ).datepicker();
		
		
		$("#makeComp").change(function(event){
			$.fn.selfPage.searchPhoneList($(this).val());
		});
		
		$("#searchBtn").click(function(event){
			event.preventDefault();
			
			if(showValidate("searchForm", 'default', '<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.dateErr'/>')){// Validation Skip
				
				$("#searchForm").submit();
			}
		});		
		
		$("#searchInitBtn").click(function(event){
			event.preventDefault();
			
			$("#vmType > option[value='']").attr("selected", "true");
			$("#makeComp > option[value='']").attr("selected", "true");
			$("#modelNm > option[value='']").attr("selected", "true");
			
			$("#certifyPrgrYnAll[value='ALL']").attr("checked", "true");
			$("input[name='searchCon.certifyPrgrYnList']").attr("checked", "");
			
			setOrderSearchDateAdminPoC("7day", document.searchForm.certifyRequestDtFrom, document.searchForm.certifyRequestDtTo);
			setOrderSearchDateAdminPoC("7day", document.searchForm.ctAssignDtFrom, document.searchForm.ctAssignDtTo);
			setOrderSearchDateAdminPoC("7day", document.searchForm.ctEndDtFrom, document.searchForm.ctEndDtTo);
			
			$("#searchType > option[value='testerId']").attr("selected", "true");
			$("#searchValue").val("");
		});		
		
		$("#certifyPrgrYnAll").click(function(event){
			
			if($(this).attr("checked") == true){
				$("input[name='searchCon.certifyPrgrYnList']:checkbox").each(function(){
						$(this).attr("checked", "");	
				});			
				
			}else{
				$("input[name='searchCon.certifyPrgrYnList']:checkbox").each(function(){
				});					
					$(this).attr("checked", "checked");	
			}
		});	
		
		
		$("input[name='searchCon.certifyPrgrYnList']:checkbox").click(function(event){
			
			if($("input[name='searchCon.certifyPrgrYnList']:checked").length == 0){
				$("#certifyPrgrYnAll").attr("checked", "checked");
			}else{
				if($("#certifyPrgrYnAll").attr("checked") == true){
					$("#certifyPrgrYnAll").attr("checked", "");
				}
			}
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
				首頁 &gt; 產品管理中心 &gt; 審核管理 &gt <strong>搜尋應用軟體</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">搜尋應用軟體</h1>
			<p>可查看全部應用軟體之目錄。</p>
<form id="searchForm" name="searchForm"  action="<c:url value="/certifymgr/appList.omp"/>" method="post">
			<input type="hidden" id="no" name="searchCon.page.no" value="${searchCon.page.no }" />		
			<table class="tabletype01">
				<colgroup><col style="width:80px;"><col ><col style="width:100px"></colgroup>
				<tr>
					<th>搜尋條件</th>
					<td class="align_td line2_5">					
						<label for="#">平台</label>
                        <select id="vmType" name="searchCon.vmType" style="width:104px;">
                        	<option value="">全部</option>
                        	<gc:options group="PD0056" value="${searchCon.vmType}" codeType="full" />
                        </select>
						<label for="#">手機型號</label>
                        <select id="makeComp" name="searchCon.makeComp" style="width:104px;">
                        	<option value="">全部</option>
                        	<gc:options group="PD0040" value="${searchCon.makeComp}" codeType="full" />
                        </select>

                        <select id="modelNm" name="searchCon.modelNm" style="width:104px;">
                        	<option value="">全部</option>
                        </select>
						<br />

						<label for="#">審核狀態</label>
						<input type="checkbox" id="certifyPrgrYnAll" name="searchCon.certifyPrgrYnAll" value="ALL" <c:if test="${searchCon.certifyPrgrYnAll == 'ALL'}">checked</c:if> /> 全部
						<gc:checkBoxs group="PD0053" name="searchCon.certifyPrgrYnList" value="${searchCon.certifyPrgrYnList}" extra="class=\"ml10\"" codeType="full" filter="ct"/>
						<br />

						<label for="#">請審</label>
						<input id="certifyRequestDtFrom" name="searchCon.certifyRequestDtFrom" type="text" class="txt" style="width:80px;" value="<g:html value="${searchCon.certifyRequestDtFrom }" format="L####-##-##" />" readonly
							v:required m:required="<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.start_date'/>" m:scompare="<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.date01'/>" /> ~ 
						<input type="text" id="certifyRequestDtTo" name="searchCon.certifyRequestDtTo" class="txt" style="width:80px;" value="<g:html value="${searchCon.certifyRequestDtTo }" format="L####-##-##" />" readonly
							v:required m:required="<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.end_date'/>"
							v:scompare="ge,@{searchCon.certifyRequestDtFrom}" m:scompare="<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.date02'/>" />
						<br />

						<label for="#">審核分配</label>
						<input id="ctAssignDtFrom" name="searchCon.ctAssignDtFrom" type="text" class="txt" style="width:80px;" value="<g:html value="${searchCon.ctAssignDtFrom }" format="L####-##-##" />" readonly
							v:required m:required="<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.start_date'/>" m:scompare="<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.date01'/>" /> ~ 
						<input type="text" id="ctAssignDtTo" name="searchCon.ctAssignDtTo" class="txt" style="width:80px;" value="<g:html value="${searchCon.ctAssignDtTo }" format="L####-##-##" />" readonly
							v:required m:required="<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.end_date'/>"
							v:scompare="ge,@{searchCon.ctAssignDtFrom}" m:scompare="<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.date02'/>" />

						<br />

						<label for="#">審核完畢</label>
						<input id="ctEndDtFrom" name="searchCon.ctEndDtFrom" type="text" class="txt" style="width:80px;" value="<g:html value="${searchCon.ctEndDtFrom }" format="L####-##-##" />" readonly
							v:required m:required="<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.start_date'/>" m:scompare="<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.date01'/>" /> ~ 
						<input type="text" id="ctEndDtTo" name="searchCon.ctEndDtTo" class="txt" style="width:80px;" value="<g:html value="${searchCon.ctEndDtTo }" format="L####-##-##" />" readonly
							v:required m:required="<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.end_date'/>"
							v:scompare="ge,@{searchCon.ctEndDtFrom}" m:scompare="<gm:string value='jsp.certifymgr.certifyAppPagingList.msg.date02'/>" />
					</td>
					<td rowspan="2" class="text_c" >
						<a class="btn" href="#" id="searchBtn"><strong>搜尋</strong></a>
						<a class="btn" href="#" id="searchInitBtn"><span>重新搜尋</span></a>
					</td>
				</tr>
				<tr>
					<th>搜尋關鍵字</th>
					<td class="align_td">
						<label for="#">關鍵字</label>
                        <select id="searchType" name="searchCon.searchType" style="width:104px;">
                        	<option value="testerId" <c:if test="${searchCon.searchType == 'testerId'}">selected</c:if>>Tester ID</option>
                        	<option value="prodNm" <c:if test="${searchCon.searchType == 'prodNm'}">selected</c:if>>產品名稱</option>
                        	<option value="devId" <c:if test="${searchCon.searchType == 'devId'}">selected</c:if>>開發商</option>
                        	<option value="aid" <c:if test="${searchCon.searchType == 'aid'}">selected</c:if>>AID</option>
                        </select>
						<input id="searchValue" name="searchCon.searchValue" value="${searchCon.searchValue}" type="text" class="txt" style="width:200px;" />
					</td>
				</tr>
			</table>			
</form>
			<h2>應用軟體目錄</h2>

			<!-- 리스트 시작 -->
			<table class="tabletype02">
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
				</colgroup>
				<thead>
				<tr>
					<th>產品AID</th>

					<th>平台</th>
					<th>產品名稱</th>
					<th>請審日期</th>
					<th>審核次數</th>
					<th>審核狀態</th>
					<th>審核分配</th>

					<th>測試完畢預期</th>
					<th>測試管理者</th>
					<th>審核完畢日</th>
				</tr>                                
				</thead>
				<tbody>
				<c:choose>
					<c:when test="${searchedTotalCount > 0}">
						<c:forEach items="${list}" var="info">
						<tr>
							<td>${info.aid}</td>
							<td><gc:html code="${info.vmType}"/></td>
							<c:choose>
								<c:when test="${info.verifyPrgrYn == CONST.CODE_VERIFY_REQ}">
									<td><a href="<c:url value="/certifymgr/assign.omp?
										searchConAppDetail.cid=${info.cid}&searchConAppDetail.verifyReqVer=${info.verifyReqVer}" />&<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>">${info.prodNmByAbbreviate}</a></td>
								</c:when>
								<c:otherwise>
									<td><a href="<c:url value="/certifymgr/appDetail.omp?
										searchConAppDetail.cid=${info.cid}&searchConAppDetail.verifyReqVer=${info.verifyReqVer}" />&<g:printBean prefix='searchCon.' value='${searchCon}' outType='qs'/>">${info.prodNmByAbbreviate}</a></td>
								</c:otherwise>
							</c:choose>
							
							<td><g:html value="${info.insDttmByMmDd}" format="L####-##-##" /></td>
							<td>${info.verifyReqVer}</td>
							<td><gc:html code="${info.verifyPrgrYn}"/></td>
							<td><g:html value="${info.ctAssignDtByMmDd}" format="L####-##-##" /></td>
							<td><g:html value="${info.ctEndExDtByMmDd}" format="L####-##-##" /></td>
							<td>${info.testerNm}</td>
							<td><g:html value="${info.ctEndDtByMmDd}" format="L####-##-##" /></td>
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
</body>
</html>
