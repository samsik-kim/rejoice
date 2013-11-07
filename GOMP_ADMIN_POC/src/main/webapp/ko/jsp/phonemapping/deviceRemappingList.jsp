<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
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
		
		// searchType change Action
		$("#searchType").change(function (e){
			$("#searchText").val('');
		});
		
		// view Detail
		$(".viewDetail").each(function(){
			$(this).click(function(event){
				event.preventDefault();
				$("#txId").val($(this).attr("key"));
				// no initializing
				$("#no").val('1');
				document.searchForm.action='<c:url value="/phonemapping/deviceRemappingDetail.omp"/>';
				document.searchForm.submit();
			});
		});
		
		// targetPhoneModelCd Enter
		$("#targetPhoneModelCd").keypress(function (e){
			if(e.which == 13){ 
				funcSearch();
			}
		});
		
		// standardPhoneModelCd Enter
		$("#standardPhoneModelCd").keypress(function (e){
			if(e.which == 13){ 
				funcSearch();
			}
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
			
			document.searchForm.action="deviceRemappingList.omp";
			document.searchForm.submit();
		}
	}
	
//]]>
</script>

</head>
<body>
			<div id="location">
				Home &gt; 단말관리 &gt; 서비스단말관리 &gt; <strong>단말 Mapping 처리결과</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">단말 Mapping 처리결과</h1>
			<p>대상단말 관리 처리결과 입니다.</p>
			<!-- 2011-03-24 -->
			<s:form id="searchForm" name="searchForm" action="deviceRemappingList" theme="simple" method="get">
			<input type="hidden" id="txId" name="searchParam.txId" value="${searchParam.txId }"/>
			<input type="hidden" id="searchToday" name="searchParam.searchToday" value="${searchParam.searchToday }" class="searchDate" rel="searchTodayBtn" />
			<input type="hidden" id="searchWeek" name="searchParam.searchWeek" value="${searchParam.searchWeek }"  class="searchDate" rel="searchWeekBtn" />
			<input type="hidden" id="searchMonth" name="searchParam.searchMonth" value="${searchParam.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
			<input type="hidden" id="no" name="searchParam.page.no" value="${searchParam.page.no }" />
			<input type="hidden" id="masterNo" name="searchParam.masterNo" value="${searchParam.page.no }" />
			<table class="tabletype01">
				<colgroup>
					<col style="width:20%;"><col >
					<col style="width:20%;"><col style="width:25%;"><col style="width:10%;">
				</colgroup>
				<thead>
					<tr>
						<th colspan="5">T store 및 다운로드 서버에 대상 단말을 추가 및 삭제 요청 목록 입니다</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th>등록단말</th>
					<td class="align_td">
						<input id="targetPhoneModelCd" name="searchParam.targetPhoneModelCd" value="${ searchParam.targetPhoneModelCd}" type="text" class="txt" style="width:200px;" maxlength="100"/>
					</td>
					<th>기준단말</th>
					<td colspan="2" class="align_td">
						<input id="standardPhoneModelCd" name="searchParam.standardPhoneModelCd" value="${searchParam.standardPhoneModelCd }" type="text" class="txt" style="width:200px;" maxlength="100"/>
					</td>			
				</tr>
				<tr>
					<th>등록/삭제</th><!-- 2011-03-15 -->
					<td class="align_td">	
						<select id="mappingType" name="searchParam.mappingType" style="width:85px;">
                        	<option value=''>전체</option>
							<gc:options group="PD0061" value="${searchParam.mappingType }" codeType="full"/>
                        </select>
					</td>
					<th>
						<select id="searchType" name="searchParam.searchType" style="width:85px;">
                        	<option value="prodNm" <c:if test='${searchParam.searchType eq "prodNm" }'>selected</c:if>>상품명</option>
							<option value="mbrId" <c:if test='${searchParam.searchType eq "mbrId" }'>selected</c:if>>개발자ID</option>
							<option value="aid" <c:if test='${searchParam.searchType eq "aid" }'>selected</c:if>>AID</option>
                        </select>
					</th>
					<td colspan="2" class="align_td">	
						<input id="searchText" name="searchParam.searchText" value="${searchParam.searchText }" type="text" class="txt" style="width:200px;"/>
					</td>
				</tr>
				<tr>
					<th>진행상태</th>
					<td colspan="4" class="align_td">					
						<select id="mappingStat" name="searchParam.mappingStat" style="width:85px;">
                        	<option value=''>전체</option>
							<gc:options group="PD0062" value="${searchParam.mappingStat }" codeType="full"/>
                        </select>
					</td>
				</tr>
				<tr>
					<th>검색기간</th>
					<td colspan="3" class="align_td">					
						<input id="startDate" name="searchParam.startDate" type="text" class="txt" style="width:80px;" value="${searchParam.startDate }" readonly v:required m:required='<gm:tagAttb value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.list.search.startDate"/></gm:arg></gm:tagAttb>' /> ~ 
						<input id="endDate" name="searchParam.endDate" type="text" class="txt" style="width:80px;" value="${searchParam.endDate }" readonly v:required m:required='<gm:tagAttb value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.list.search.endDate"/></gm:arg></gm:tagAttb>' v:scompare='ge,@{searchParam.startDate}' m:scompare='<gm:tagAttb value="jsp.list.validate.wrongDate"/>' /> 
						<a class="btn_s searchDateBtn" href="#" id="searchTodayBtn" rel="today"><span>오늘</span></a>
						<a class="btn_s searchDateBtn" href="#" id="searchWeekBtn" rel="7day"><span>최근1주</span></a>
						<a class="btn_s searchDateBtn" href="#" id="searchMonthBtn" rel="1month"><span>최근1개월</span></a>
					</td>
					<td class="text_c" >
						<a class="btn" href="#" id="searchBtn"><strong>검색</strong></a>
						<a class="btn" href="#" id="searchInitBtn"><span>검색초기화</span></a>
					</td>	
				</tr>
				</tbody>
			</table>
			</s:form>
			<!-- //2011-03-24 -->
			<table class="tabletype02 mt25">
				<colgroup>
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
					<th>등록구분</th>
					<th>DL 등록상태</th>
					<th>등록단말</th>
					<th>기준단말</th>
					<th>요청일</th>
					<th>등록일</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${firstSearch }">
				<tr>
					<td colspan="7" class="text_c"><gm:html value="jsp.product.initSearchMsg"/></td>
				</tr>
				</c:when>
				<c:when test="${totalCount eq 0 }">
				<tr>
					<td colspan="7" class="text_c"><gm:html value="jsp.product.noSearchMsg"/></td>
				</tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${phoneRemMgrList }" var="phoneRemMgr">
				<tr>
					<td>${phoneRemMgr.totalCount - phoneRemMgr.rnum + 1}</td>
					<td><gc:html code="${phoneRemMgr.mappingType}"/></td>
					<td><a href="#" key='${phoneRemMgr.txId }' class="viewDetail"><gc:html code="${phoneRemMgr.mappingStat}"/></a></td>
					<td>${phoneRemMgr.targetPhoneModelCd }</td>
					<td>${phoneRemMgr.standardPhoneModelCd }</td>
					<td>${phoneRemMgr.insDttm }</td>
					<td>${phoneRemMgr.updDttm }</td>
				</tr>
				</c:forEach>
				</c:otherwise>
				</c:choose>
				</tbody>
				
			</table>
			<p class="btn_wrap text_r mt25">
				<a class="btn" href="<c:url value="/phonemapping/deviceRemapping.omp?topCode=M007&leftCode=M007001002"/>"><span>대상단말등록</span></a>
			</p>
			<!-- paging -->
			<g:pageIndex item="${phoneRemMgrList}"/>
			<!-- //paging -->
</body>
</html>