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
<style type="text/css">
form{clear:both;}
</style>
<script type="text/javascript">
//<![CDATA[
	$(function() {
		var form = document.searchForm;

		// search start ~ end date datepicker
		$( "#startDate" ).datepicker();
		$( "#endDate" ).datepicker();
		
		if ($(":input:checkbox[name=opTypes]:checked").length == 0) {
			$("input[name=opTypes]").filter('input[value=""]').attr("checked", "checked");
		}
		
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
			$("input[name=opTypes]").filter('input[value=""]').attr("checked", "checked");
			$("#searchWeekBtn").click();
			//funcSearch();
		});
		
	});
	
	function goPage(no) {
	    $("#no").val(no);
	    $("#searchBtn").click();
	}
	
	function funcSearch(){
		if(showValidate('searchForm', 'dialog', '<gm:string value="jsp.cash.cash_list.msg.validate"/>')){
			
			if ($(":input:checkbox[name=opTypes]:checked").length == 0) {
				alert("<gm:string value='jsp.cash.cash_list.msg.chkCategory'/>");
				$("input[name=opTypes]").filter('input[value=""]').attr("checked", "checked");
				return;
			}
			
			document.searchForm.action="cashList.omp";
			document.searchForm.submit();
		}
	}
	
	//전체선택해제
	function changeCheck(idx){
		var opType = document.getElementsByName('opTypes');
		if (opType[idx].checked) {
			if (idx == "0") {
				for ( var j=1; j < opType.length; j++) {
					//alert(j);
					opType[j].checked = false;
				}
			}else {
				opType[0].checked = false;
			}			
		}
	}
	
//]]>
</script>
	</head>
<body>
	<div id="location">
		首頁 &gt; 產品管理中心 &gt; Whoopy幣管理 &gt <strong>會員Whoopy幣紀錄</strong> 
	</div><!-- //location -->

	<h1 class="fl pr10">會員Whoopy幣紀錄</h1>
	<p>可查看各會員之Whoopy幣紀錄。</p>		
	
	<s:form id="searchForm" name="searchForm" action="selectSaleStatDailyMainList" theme="simple" >
	<input type="hidden" id="searchToday" name="sub.searchToday" value="${sub.searchToday }" class="searchDate" rel="searchTodayBtn" />
	<input type="hidden" id="searchWeek" name="sub.searchWeek" value="${sub.searchWeek }"  class="searchDate" rel="searchWeekBtn" />
	<input type="hidden" id="searchMonth" name="sub.searchMonth" value="${sub.searchMonth }"  class="searchDate" rel="searchMonthBtn" />
	<input type="hidden" id="no" name="sub.page.no" value="${sub.page.no }" />
	
	<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>類別</th>
					<td class="align_td">
						<input type="checkbox" class="ml10" name="opTypes" value=""   <c:forEach  var="test"  items="${sub.opTypes}"><c:out value="${test eq '' ? 'checked ':'' }"/></c:forEach>  onclick="changeCheck('0')"/> <label>全部</label>
						<input type="checkbox" class="ml10" name="opTypes" value="OR003310" <c:forEach  var="test"  items="${sub.opTypes}"><c:out value="${test eq 'OR003310' ? 'checked':'' }"/></c:forEach> onclick="changeCheck('1')"/> <label>Whoopy幣加值</label>
						<input type="checkbox" class="ml10" name="opTypes" value="OR003342" <c:forEach  var="test"  items="${sub.opTypes}"><c:out value="${test eq 'OR003342' ? 'checked':'' }"/></c:forEach> onclick="changeCheck('2')"/> <label>送出禮物</label><br />
						<input type="checkbox" class="ml10" name="opTypes" value="OR003341" <c:forEach  var="test"  items="${sub.opTypes}"><c:out value="${test eq 'OR003341' ? 'checked':'' }"/></c:forEach> onclick="changeCheck('3')"/> <label>收到禮物</label>
						<input type="checkbox" class="ml10" name="opTypes" value="OR003311" <c:forEach  var="test"  items="${sub.opTypes}"><c:out value="${test eq 'OR003311' ? 'checked':'' }"/></c:forEach> onclick="changeCheck('4')"/> <label>使用禮券</label>
						<input type="checkbox" class="ml10" name="opTypes" value="OR003331" <c:forEach  var="test"  items="${sub.opTypes}"><c:out value="${test eq 'OR003331' ? 'checked':'' }"/></c:forEach> onclick="changeCheck('5')"/> <label>購買產品</label>
						<input type="checkbox" class="ml10" name="opTypes" value="OR003313" <c:forEach  var="test"  items="${sub.opTypes}"><c:out value="${test eq 'OR003313' ? 'checked':'' }"/></c:forEach> onclick="changeCheck('6')"/> <label>取消購買</label>
					</td>
					<td rowspan="3" class="text_c" >
						<a class="btn" href="#" id="searchBtn"><strong>搜尋</strong></a>
						<a class="btn" href="#" id="searchInitBtn"><span>重新搜尋</span></a>
					</td>
				</tr>
				<tr>
					<th>實行日期</th>
					<td class="align_td">
						<input id="startDate" name="sub.startDate" type="text" class="txt" style="width:80px;" value="${sub.startDate }" readonly v:required m:required='<gm:tagAttb value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.list.search.startDate"/></gm:arg></gm:tagAttb>' /> ~ 
						<input id="endDate" name="sub.endDate" type="text" class="txt" style="width:80px;" value="${sub.endDate }" readonly v:required m:required='<gm:tagAttb value="jsp.product.validate.needInput"><gm:arg><gm:text value="jsp.list.search.endDate"/></gm:arg></gm:tagAttb>' v:scompare="ge,@{sub.startDate}" m:scompare='<gm:tagAttb value="jsp.list.validate.wrongDate"/>'/> 
						<a class="btn_s searchDateBtn" href="#" id="searchTodayBtn" rel="today"><span>今日</span></a>
						<a class="btn_s searchDateBtn" href="#" id="searchWeekBtn" rel="7day"><span>最近一周</span></a>
						<a class="btn_s searchDateBtn" href="#" id="searchMonthBtn" rel="1month"><span>最近一個月</span></a>
					</td>
				</tr>
				<tr>
					<th>搜尋會員</th>
					<td class="align_td">
						<label for="#">ID</label>
						 <input id="searchText" name="sub.searchText" type="text" class="txt" style="width:120px;" value="${sub.searchText }"/>
					</td>
				</tr>
			</table>
			</s:form>
			
	<!-- 리스트 시작 -->
			<table class="tabletype02 mt25">
				<colgroup>
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
					<th>帳號(姓名)</th>
					<th>類別</th>
					<th>出納紀錄</th>
					<th>發送人</th>
					<th>收取人</th>
					<th>實行日期</th>
				</tr>                                
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${totalCount eq 0 }">
				<tr>
					<td colspan="7" class="text_c"><gm:html value='jsp.cash.cash_list.msg.noresult'/></td>
				</tr>
				</c:when>
				<c:otherwise>
				<c:forEach items="${list }" var="cash">
				<tr>
					<td>${cash.totalCount - cash.rnum + 1}</td>
					<td>${cash.mbrId}(${cash.mbrNm})</td>
					<td><gc:html code="${cash.opType}" naMessage="자료오류."/></td>
					<td><g:html format="R###,###,###">${cash.occrAmt}</g:html></td>
					<td><c:choose><c:when test="${cash.opType eq 'OR003342' }">${cash.mbrId}</c:when><c:when test="${cash.opType eq 'OR003341' }">${cash.relateMbrId}</c:when><c:otherwise> - </c:otherwise></c:choose></td>
					<td><c:choose><c:when test="${cash.opType eq 'OR003341' }">${cash.mbrId}</c:when><c:when test="${cash.opType eq 'OR003342' }">${cash.relateMbrId}</c:when><c:otherwise> - </c:otherwise></c:choose></td>
					<td>${cash.occrDt}</td>
				</tr>
				</c:forEach>
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