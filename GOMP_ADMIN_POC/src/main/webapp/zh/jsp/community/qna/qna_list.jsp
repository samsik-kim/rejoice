<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<script type="text/javascript" src="<c:url value="/js/common/niceforms.js"/>"></script>
<script language="javascript" src="<c:url value="/js/common/calendar.js"/>"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#startDate").datepicker();
		$("#endDate").datepicker();	
		
		if('${qna.startDate}' == "") {
			
			var startDate = document.getElementById("startDate");
			var endDate = document.getElementById("endDate");
			
			setOrderSearchDateAdminPoC("today", startDate, endDate);
		}
	});
	
	function init(){
		$("#rb1").attr("checked", "true");
		$("#qnaCtgr01 option:first").attr("selected", "true");		 
		$("#qnaCtgr02 option:first").attr("selected", "true");		
		
		var startDate = document.getElementById("startDate");
		var endDate = document.getElementById("endDate");

		setOrderSearchDateAdminPoC("today", startDate, endDate);		// Search Date Default Initialization
		
		$("#keytype option:eq(1)").attr("selected", "true");		
		$("#queId").val("");		
		$("#keyword").val("");	
		$("#mbrId").val("");
	}
	
	function func_check_all() {
		var frmck = document.getElementsByName("check");
		if(document.getElementById("chk_all").checked == true){
		
			for(var i=0; i< frmck.length; i++) {
				frmck[i].checked = true;	
			}
		}else{
			for(var j=0; j< frmck.length; j++) {
				frmck[j].checked = false;
			}
		}
	}
	
	function func_deleteQnA() {
		var url = document.qnaListForm.url.value;
		var arr = new Array(10);
		var nullCnt = 10;
		if(${questionCnt}>0){
		if(document.qnaListForm.check.length) {    
			for(i=0; i< document.qnaListForm.check.length; i++) {
				if(document.qnaListForm.check[i].checked == true) {
					arr[i] = document.qnaListForm.check[i].value;
					--nullCnt;
				}
			}
		}else{			
			if(document.qnaListForm.check.checked == true) {
				arr[0] = document.qnaListForm.check.value;
				--nullCnt;
			}
		}
		if(nullCnt == 10) {
			alert("<gm:string value='jsp.contents.best.chargeBest.msg.delete_check'/>");
		}else {
			document.qnaListForm.delQueNo.value = arr;
			if(url=='searchDQnA.omp'||url=='removeDQnA.omp'||url=='downloadDevQnaList.omp'||url=='sendDMail.omp'){
				var options={ 
						 success:       responseDel,
				         url:       env.contextPath+"/qna/removeDQnA.omp", 
				         type:      "post"  
					}; 
				//document.qnaListForm.action="removeDQnA.omp";
			}else{
				var options={ 
						 success:       responseDel,
				         url:       env.contextPath+"/qna/removeQnA.omp", 
				         type:      "post"  
					}; 
				//document.qnaListForm.action="removeQnA.omp";
			}
			if(confirm("<gm:string value='jsp.community.qna.qna_list.msg.delchk'/>")){
				$('#qnaListForm').ajaxSubmit(options);
				//document.qnaListForm.submit();
			}
		}
		}
	}
	
	function responseDel(){
		alert("<gm:string value='jsp.bnrMg.mblBnrList.msg.delre'/>");
		selectQnaList();
	}
	
	//Search Date
	function search_date(term) {
		var startDate = document.getElementById("startDate");
		var endDate = document.getElementById("endDate");
	
		setOrderSearchDateAdminPoC(term, startDate, endDate);
	}
	
	function goPage(no) {
		$("form[name=qnaListForm] input[name=qna\\.page\\.no]").val(no);
		selectQnaList();
	}
	
	function selectQnaList(){
		var url = document.qnaListForm.url.value;
		if(showValidate('qnaListForm', 'default', '')){
		$("#qnaListForm").attr("target", "_self");
		if(url=='searchDQnA.omp'||url=='removeDQnA.omp'||url=='downloadDevQnaList.omp'){
			$("#qnaListForm").attr("action", "searchDQnA.omp");
		}else{
			$("#qnaListForm").attr("action", "searchSCQnA.omp");
		}
		$("#qnaListForm").submit();
		}
	}
	
	//2depth AJAX
	$(function() {
		$("#qnaCtgr01").change(function() {
			var selValue = this.value;
			$.post(env.contextPath+"/qna/getAjaxSecondCategoryList.omp", {"selectFaqCategoryType":selValue}, function(data) {
				$("#qnaCtgr02").find('option').remove();
				makeOption("qnaCtgr02", data);
			}, "json");
		});
	});
	
	$(function() {
		$("#keytype").change(function() {
			$("#keyword").attr("value", "");
		});
	});

	function makeOption(target, data) {
		if(data.status=='true'){
			alert("2Depth Category Error.");
		}
		var result = data.result;
		var defText = "全部";
		var option = $("<option/>").attr("value", "");
		var option = $("<option>" + defText + "</option>").attr("value", "");
		option.attr(($.browser.msie ? 'label':'text'), defText);
		$("#" + target).append(option);

		for(var i in result) {
			$("#" + target).append("<option value='" + result[i].ctgrCd + "'>" + result[i].ctgrNm + "</option>");
		}
	}
	
	function func_displayDetailQnA(que_no) {
		var url = document.qnaListForm.url.value;
		document.qnaListForm.queNo.value = que_no;
		if(url=='searchDQnA.omp'||url=="sendDMail.omp"||url=='removeDQnA.omp'||url=='downloadDevQnaList.omp'){
			document.qnaListForm.action="displayDQnA.omp";
		}else{
			document.qnaListForm.action="displayQnA.omp";
		}
		document.qnaListForm.submit();
	}
	
	function excelDown() {
		var url = document.qnaListForm.url.value;
		if ('${questionCnt}'  == 0) {
			alert("<gm:string value='jsp.community.qna.qna_list.msg.exel'/>");
			return;
		}
		if(showValidate('qnaListForm', 'default', '')){
			if(url=='searchDQnA.omp'||url=='removeDQnA.omp'||url=='downloadDevQnaList.omp'){
				document.qnaListForm.action="downloadDevQnaList.omp";
			}else{
				document.qnaListForm.action="downloadSCQnaList.omp";
			}
			document.qnaListForm.submit();
		}
	}
	
</script>
</head>
<body>
<s:form id="qnaListForm" name="qnaListForm" theme="simple" method="post">
	<s:hidden id="queNo" name="qna.queNo"/>
	<s:hidden name="selectFaqCategoryType"/>
	<s:hidden name="delQueNo"/>
	<s:hidden id="url" name="url"/>
	<s:hidden id="chk" name="chk" value="Y"/>
	<input type="hidden" name="qna.page.no" value="${qna.page.no}"/>
	<div id="location">
	<c:if test="${url eq 'searchSCQnA.omp' || url eq 'removeQnA.omp' || url eq 'sendMail.omp'}">
		首頁  &gt; 客戶服務中心 &gt; 客戶諮詢管理 &gt; <strong>SC客戶諮詢</strong>
	</c:if>
	<c:if test="${url eq 'searchDQnA.omp' || url eq 'removeDQnA.omp' || url eq 'sendDMail.omp'}">
		首頁  &gt; 客戶服務中心 &gt; 客戶諮詢管理 &gt; <strong>開發商諮詢</strong>
	</c:if>
	</div>
	<!-- //location -->

	<h1 class="fl pr10"><c:if test="${url eq 'searchDQnA.omp' || url eq 'removeDQnA.omp' || url eq 'sendDMail.omp'}">開發商諮詢</c:if><c:if test="${url eq 'searchSCQnA.omp' || url eq 'removeQnA.omp' || url eq 'sendMail.omp'}">SC客戶諮詢</c:if></h1>
	<c:if test="${url eq 'searchDQnA.omp' || url eq 'removeDQnA.omp' || url eq 'sendDMail.omp'}"><p>可查看／答覆開發商諮詢。</p></c:if>
	<c:if test="${url eq 'searchSCQnA.omp' || url eq 'removeQnA.omp' || url eq 'sendMail.omp'}"><p> 可查看/回復客戶諮詢。</p></c:if>

	<table class="tabletype01">
		<colgroup>
			<col style="width: 100px;">
			<col>
			<col style="width: 125px">
		</colgroup>
		<tr>
			<th>狀態</th>
			<td class="align_td">
				<input type="radio" id="rb1" name="qna.prcStatCd" class="ml05" value="" <s:if test="qna.prcStatCd == ''">checked</s:if>/>全部  
				<input type="radio" id="rb2" name="qna.prcStatCd" class="ml05" value="CM000701" <s:if test="qna.prcStatCd == 'CM000701'">checked</s:if>/>答覆中 
				<input type="radio" id="rb3" name="qna.prcStatCd" class="ml05" value="CM000702" <s:if test="qna.prcStatCd == 'CM000702'">checked</s:if>/>答覆完畢
			</td>
			<td rowspan="5" class="text_c">
				<a class="btn" href="javascript:selectQnaList();"><strong>搜尋</strong></a>
				<a class="btn" href="javascript:init();"><span>重新搜尋</span></a></td>
		</tr>
		<tr>
			<th>搜尋會員</th>
			<td>
				<s:if test="qna.queId == ''">
				<!-- <input id="queId" name="qna.queId" type="text" class="txt" onfocus="this.value=''" style="width: 200px;" value="<gm:html value='jsp.community.qna.qna_list.text.idef'/>"/> -->	
					<input type="text" id="mbrId" name="qna.queId" value="${qna.queId}" class="txt" style="width:200px;background:url(<c:url value='/${ThreadSession.serviceLocale.language}/images/cs_qa_02_bg.gif' />) no-repeat;" onfocus="this.style.background=''" onfocus="this.value=''"  />
				</s:if>
				<s:else>
					<input type="text" id="mbrId" name="qna.queId" value="${qna.queId}" class="txt" style="width: 200px;" onfocus="this.value=''"  />
				</s:else>
			</td>
		</tr>
		<tr>
			<th>類別</th>
			<td>
				<s:select id="qnaCtgr01" name="qna.ctgrCd" theme="simple" list="categoryNameList" listKey="ctgrCd" listValue="ctgrNm" value="%{qna.ctgrCd}" headerKey="" headerValue="全部 " style="width:104px;"/>
				<!-- 2차카테고리 -->
				<c:if test="${url eq 'searchSCQnA.omp' || url eq 'removeQnA.omp' || url eq 'sendMail.omp'}">
				<s:if test="qna.ctgrCd == null">
					<select id="qnaCtgr01" name="qna.ctgrCd" style="width: 104px;">
						<option value="">全部 </option>
					</select>
				</s:if>
				<s:else>
					<s:select id="qnaCtgr02" name="qna.qnaClsCd2" theme="simple" list="secondDepthCategoryList" listKey="ctgrCd" listValue="ctgrNm" value="%{qna.qnaClsCd2}" headerKey="" headerValue="全部 " style="width:104px;"/>
				</s:else>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>搜尋諮詢</th>
			<td>
				<select id="keytype" name="qna.keytype" style="width: 104px;">
					<option value="both" <c:if test="${qna.keytype eq 'both' }">selected</c:if>>全部</option>
					<option value="title" <c:if test="${qna.keytype eq 'title' }">selected</c:if>>標題</option>
					<option value="dscr" <c:if test="${qna.keytype eq 'dscr' }">selected</c:if>>內容</option>
				</select> 
				<s:if test="qna.keyword == ''">
					<input id="keyword" name="qna.keyword" type="text" class="txt" style="width: 200px;" onfocus="this.value=''" value=""/>
				</s:if>
				<s:else>
					<input id="keyword" name="qna.keyword" type="text" class="txt" style="width: 200px;" onfocus="this.value=''" value="${qna.keyword}"/>
				</s:else>
			</td>
		</tr>
		<tr>
			<th>諮詢日期</th>
			<td class="align_td">
				<c:if test="${url eq 'searchDQnA.omp' || url eq 'removeDQnA.omp' || url eq 'sendDMail.omp'}"><label for="#">搜尋期間</label></c:if>
				<c:if test="${url eq 'searchSCQnA.omp' || url eq 'removeQnA.omp' || url eq 'sendMail.omp'}"><label for="#">諮詢日期</label></c:if>
				<input id="startDate" name="qna.startDate" type="text" class="txt" style="width: 80px;" value="<g:text value='${qna.startDate }' format='L####-##-##'/>" readonly/> 
			 	~ 
				<input id="endDate" name="qna.endDate" type="text" class="txt" style="width: 80px;" value="<g:text value='${qna.endDate }' format='L####-##-##'/>" readonly v:scompare="ge,@{qna.startDate}" m:scompare="<gm:tagAttb value='jsp.community.qna.qna_list.msg.datechk'/>"/> 
				<select id="dateType" name="qna.dateType" style="width:104px;">
                	<option value="" <c:if test="${qna.dateType eq ''}">selected</c:if>>全部</option>
					<option value="wait" <c:if test="${qna.dateType eq 'wait'}">selected</c:if>>答覆中</option>
					<option value="receipt" <c:if test="${qna.dateType eq 'receipt'}">selected</c:if>>答覆完畢</option>
                </select>
				
			 	<br />
				<a class="btn_s" href="javascript:search_date('today');"><strong>今日</strong></a> 
				<a class="btn_s" href="javascript:search_date('7day');"><span>最近一周</span></a> 
				<a class="btn_s" href="javascript:search_date('14day');"><span>最近兩周</span></a>
				<a class="btn_s" href="javascript:search_date('1month');"><span>最近一個月</span></a></td>
		</tr>
	</table>
	<p class="btn_wrap text_r mt05">
		<a class="btn" href="javascript:excelDown();">
			<span>下載Excel檔</span>
		</a>
	</p>
	<table class="tabletype02 mt25">
		<colgroup>
		<c:choose>
				<c:when test="${questionCnt > 0}">
					<col style="width: 40px;">
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
					<col>
				</c:when>
				<c:otherwise><col style="width:8%;" ></c:otherwise>
			</c:choose>
		</colgroup>
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="chk_all" name="chk_all" onClick="javascript:func_check_all();"/>
				</th>
				<th>序號</th>
				<th>諮詢者</th>
				<th>類別</th>
				<th>標題</th>
				<th>答覆中</th>
				<th>答覆完畢</th>
				<th>狀態</th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${questionCnt > 0}">
					<c:forEach items="${questionList}" var="item" varStatus="cnt">
						<tr>
							<td style="text-align:center;"><input type="checkbox" id="check" name="check" value="${item.queNo}"/></td>
							<td style="text-align:center;"><g:html value="${item.totalCount - item.rnum + 1}" /></td>
							<td style="text-align:center;"><g:html value="${item.queId }" /></a></td>
							<td style="text-align:center;"><c:if test="${item.highCtgrNm ne null}"><c:if test="${url eq 'searchDQnA.omp' || url eq 'removeDQnA.omp' || url eq 'sendDMail.omp'}"><g:html value="${item.highCtgrNm}" /></c:if><c:if test="${url eq 'searchSCQnA.omp' || url eq 'removeQnA.omp' || url eq 'sendMail.omp'}"><c:if test="${item.qnaClsNm2 ne null }"><g:html value="${item.highCtgrNm}>${item.qnaClsNm2}" /></c:if><c:if test="${item.qnaClsNm2 eq null }"><g:html value="${item.highCtgrNm}" /></c:if></c:if></c:if></td>
							<td style="text-align:center;"><a href="javascript:func_displayDetailQnA('${item.queNo }');"><strong><g:html value="${item.queTitle}" format="L##########..."/></strong></a></td>
							<td style="text-align:center;"><g:html value="${item.regDt}" /></td>
							<td style="text-align:center;"><g:html value="${item.prcCompDt}" /></td>
							<td style="text-align:center;"><g:html value="${item.prcStatNm}" /></td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="8" class="text_c">${searcheck}</td>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	<p class="text_l mt05">
		<a class="btn_s" href="javascript:func_deleteQnA();">
			<span>批量刪除</span>
		</a>
	</p>

	<!-- paging -->
	<div align="center">
		<g:pageIndex item="${questionList}"/>
	</div>
	<!-- //paging -->
</s:form>	
</body>