<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
<script type="text/javascript">
$(document).ready(function(){
	$("#startDate").datepicker();
	$("#endDate").datepicker();	
	
	if('${search.startDate}' == "") {
		
		var startDate = document.getElementById("startDate");
		var endDate = document.getElementById("endDate");
		
		setOrderSearchDateAdminPoC("7day", startDate, endDate);
	}
});

//Search Date
function search_date(term) {
	var startDate = document.getElementById("startDate");
	var endDate = document.getElementById("endDate");
	
	setOrderSearchDateAdminPoC(term, startDate, endDate);
}

function goPage(no) {
	$("form[name=searchListForm] input[name=search\\.page\\.no]").val(no);
	selectSearchList();
}

function selectSearchList(){
	if(showValidate('searchListForm', 'default', '')){
		$("#searchListForm").attr("target", "_self");
		document.searchListForm.action="searchList.omp";
		document.searchListForm.submit();
	}
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

function func_deleteSearch() {
	var arr = new Array(20);
	var nullCnt = 20;
	if(${searchCnt}>0){
	if(document.searchListForm.check.length) {    
		for(i=0; i< document.searchListForm.check.length; i++) {
			if(document.searchListForm.check[i].checked == true) {
				arr[i] = document.searchListForm.check[i].value;
				--nullCnt;
			}
		}
	}else{										   //한개뿐일 때
		if(document.searchListForm.check.checked == true) {
			arr[0] = document.searchListForm.check.value;
			--nullCnt;
		}
	}
	if(nullCnt == 20) {
		alert("<gm:string value='jsp.bnrMg.mblBnrList.msg.nochk'/>");
	}else {
		document.searchListForm.delIdx.value = arr;
		if(confirm("<gm:string value='jsp.community.qna.qna_list.msg.delchk'/>")){
			var options={ 
					 success:       responseDel,
			         url:       env.contextPath+"/search/removeSearch.omp", 
			         type:      "post"  
				}; 
			$('#searchListForm').ajaxSubmit(options);
		}
	}
	}
}

function btnDel(idx){
	document.searchListForm.delIdx.value = idx;
	if(confirm("<gm:string value='jsp.community.qna.qna_list.msg.delchk'/>")){
		var options={ 
				 success:       responseDel,
		         url:       env.contextPath+"/search/removeSearch.omp", 
		         type:      "post"  
			}; 
		$('#searchListForm').ajaxSubmit(options);
	}
}

function responseDel(){
	alert("<gm:string value='jsp.bnrMg.mblBnrList.msg.delre'/>");
	selectSearchList();
}

function upNum(num){
	var temp = $('#expoPrior'+num).val();
	$('#expoPrior'+num).attr("value",parseInt(temp)+1);
}

function downNum(num){
	if($('#expoPrior'+num).val()>1){
		var temp = $('#expoPrior'+num).val();
		$('#expoPrior'+num).attr("value",parseInt(temp)-1);
	}
}

function func_modAll(){
	if(showValidate('searchListForm', 'default','')){
	var tempVal = null;
	var tempIdx = null;
	var expo = document.getElementsByName("expoPrior");
	var idx = document.getElementsByName("check");
	if(expo.length>0){
	for(var i=0;i<idx.length;i++){
				if(tempVal==null){
					tempVal = "#"+ expo[i].value;
				}else{
					tempVal = tempVal+"#"+ expo[i].value;
				}
				if(tempIdx==null){
					tempIdx = "#"+ idx[i].value;
				}else{
					tempIdx = tempIdx+"#"+idx[i].value;
				}
			}
	document.searchListForm.modval.value = tempVal;
	document.searchListForm.modIdx.value = tempIdx;
	if(confirm("<gm:string value='jsp.bnrMg.mblBnrList.msg.modchk'/>")){
		var options={ 
					 success:       responseMod,
			         url:       env.contextPath+"/search/modifySearch.omp", 
			         type:      "post"  
				   }; 
		$('#searchListForm').ajaxSubmit(options);
	}
	}
	}
}

function responseMod(){
	alert("<gm:string value='jsp.bnrMg.mblBnrList.msg.modre'/>");
	selectSearchList();
}

function searchIns(){
	document.searchListForm.insIdx.value = "";
	document.searchListForm.insVal.value = "";
	width = 440;
	height = 160;
    x = (screen.width) ? (screen.width-width)/2 : 0;
    y = (screen.height) ? (screen.height-height)/2 : 0;
	window.open('','popup', 'left=' + x + ',top=' + y + ',width=' + width + ',height=' + height + ',scrollbars=no,status=yes,menubar=no');
	document.searchListForm.target = 'popup';
	document.searchListForm.action = 'popSearch.omp';
	document.searchListForm.submit();
}

function modNm(idx,nm){
	document.searchListForm.insIdx.value = idx;
	document.searchListForm.insVal.value = nm;
	width = 440;
	height = 160;
    x = (screen.width) ? (screen.width-width)/2 : 0;
    y = (screen.height) ? (screen.height-height)/2 : 0;
	window.open('','popup', 'left=' + x + ',top=' + y + ',width=' + width + ',height=' + height + ',scrollbars=no,status=yes,menubar=no');
	document.searchListForm.target = 'popup';
	document.searchListForm.action = 'popSearch.omp';
	document.searchListForm.submit();
}
</script>
</head>
<body>
<s:form id="searchListForm" name="searchListForm" theme="simple" method="post">
<input type="hidden" name="search.page.no" value="${search.page.no}"/>
<s:hidden id="insIdx" name="insIdx"/>
<s:hidden id="insVal" name="insVal"/>
<s:hidden id="delIdx" name="delIdx"/>
<s:hidden id="modIdx" name="modIdx"/>
<s:hidden id="modval" name="modval"/>
<div id="location">
				首頁 &gt; 內容管理中心 &gt; 關鍵字管理 &gt; <strong>關鍵字管理</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">關鍵字管理</h1>
			<p>可管理搜尋關鍵字.</p>
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>關鍵字查詢期間</th>
					<td class="align_td">					
						<input id="startDate" name="search.startDate" type="text" class="txt" style="width:80px;" value="<g:text value='${search.startDate }' format='L####-##-##'/>" readonly/> ~ 
						<input id="endDate" name="search.endDate" type="text" class="txt" style="width:80px;" value="<g:text value='${search.endDate }' format='L####-##-##'/>" readonly v:scompare="ge,@{search.startDate}" m:scompare="<gm:tagAttb value='jsp.community.qna.qna_list.msg.datechk'/>"/>
						<a class="btn_s" href="javascript:search_date('today');"><span>今日</span></a> 
						<a class="btn_s" href="javascript:search_date('7day');"><strong>最近一周</strong></a> 
						<a class="btn_s" href="javascript:search_date('1month');"><span>最近一個月</span></a>
					</td>
					<td class="text_c" >
						<a class="btn" href="javascript:selectSearchList();"><strong>搜尋</strong></a>
					</td>
				</tr>
			</table>
			<p class="fl mt20 mb05"><a class="btn_s" href="javascript:searchIns()"><span>附加關鍵字</span></a></p>
			<p class="fr mt25">關鍵字總數 : 共<g:html value="${searchCnt }" />筆</p>
			<table class="tabletype02">
				<colgroup>
				<c:choose>
				<c:when test="${searchCnt > 0}">
					<col style="width:30px;" >
					<col style="width:40px;" >
					<col >
					<col style="width:100px;" >
					<col style="width:70px;" >
				</c:when>
				<c:otherwise><col style="width:8%;" ></c:otherwise>
			</c:choose>
				</colgroup>
				<thead>
				<tr>
					<th><input type="checkbox" id="chk_all" name="chk_all" onClick="javascript:func_check_all();"/></th>
					<th>序號</th>
					<th>關鍵字</th>
					<th>變更</th>
					<th>編輯排序</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${searchCnt > 0}">
					<c:forEach items="${searchList}" var="item" varStatus="status">
						<tr>
							<td><input type="checkbox" id="check" name="check" value="${item.searchIdx}"/></td>
							<td><g:html value="${item.rnum}" /></td>
							<td class="text_l"><g:html value="${item.searchNm }" format="L######################..."/></a></td>
							<td class="align_td"><a class="btn_s" href="javascript:modNm(${item.searchIdx},'<g:string value='${item.searchNm}'/>');"><span>變更</span></a> <a class="btn_s" href="javascript:btnDel(${item.searchIdx});"><span>刪除</span></a></td>
							<td class="align_td">
								<input id="expoPrior${status.index+1}" name="expoPrior" type="text" class="txt" style="width:20px;" value="${item.expoPrior }" v:regexp="[1-9][0-9]*" m:regexp="<gm:tagAttb value='jsp.bnrMg.mblBnrList.msg.orderchk'/>" v:required m:required="<gm:tagAttb value='jsp.bnrMg.mblBnrList.msg.ordereq'/>"/> 
								<span class="in_num">
									<a class="num_up" href="javascript:upNum(${status.index+1});">+1</a>
									<a class="num_down" href="javascript:downNum(${status.index+1});">-1</a>
								</span>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr><td colspan="5" class="text_c">${searcheck}</td></tr>
				</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<p class="fl mt05"><a class="btn_s" href="javascript:func_deleteSearch();"><span>批量刪除</span></a></p>
			<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:func_modAll();"><span>批量變更</span></a></p>
			<!-- paging -->
	<div align="center">
		<g:pageIndex item="${searchList}"/>
	</div>
	<!-- //paging -->
</s:form>	
</body>