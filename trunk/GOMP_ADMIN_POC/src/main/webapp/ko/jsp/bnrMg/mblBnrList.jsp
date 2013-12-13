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
	if('${mobileBanner.startDate}' == "") {
		
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
	$("form[name=bannerListForm] input[name=mobileBanner\\.page\\.no]").val(no);
	selectMobileBannerList();
}

function selectMobileBannerList(){
	if(showValidate('bannerListForm', 'default', '')){
		document.bannerListForm.action="mobileBannerList.omp";
		document.bannerListForm.submit();
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

function func_deleteBanner() {
	var arr = new Array(20);
	var nullCnt = 20;
	if(${mobileBannerCnt}>0){
	if(document.bannerListForm.check.length) {    
		for(i=0; i< document.bannerListForm.check.length; i++) {
			if(document.bannerListForm.check[i].checked == true) {
				arr[i] = document.bannerListForm.check[i].value;
				--nullCnt;
			}
		}
	}else{										 
		if(document.bannerListForm.check.checked == true) {
			arr[0] = document.bannerListForm.check.value;
			--nullCnt;
		}
	}
	if(nullCnt == 20) {
		alert("<gm:string value='jsp.bnrMg.mblBnrList.msg.nochk'/>");
	}else {
		document.bannerListForm.delIdx.value = arr;
		if(confirm("<gm:string value='jsp.community.qna.qna_list.msg.delchk'/>")){
			var options={ 
					 success:       responseDel,
			         url:       env.contextPath+"/mobileBanner/removeBanner.omp", 
			         type:      "post"  
				}; 
			$('#bannerListForm').ajaxSubmit(options);
		}
	}
	}
}


function responseDel(){
	alert("<gm:string value='jsp.bnrMg.mblBnrList.msg.delre'/>");
	selectMobileBannerList();
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
	if(showValidate('bannerListForm', 'default','')){
	var tempVal = null;
	var tempIdx = null;
	var tempYn = null;
	var expo = document.getElementsByName("expoPrior");
	var idx = document.getElementsByName("check");
	var yn = document.getElementsByName("modOpenYn");
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
		if(tempYn==null){
			tempYn = "#"+ yn[i].value;
		}else{
			tempYn = tempYn+"#"+yn[i].value;
		}
	}
	document.bannerListForm.modYn.value = tempYn;
	document.bannerListForm.modval.value = tempVal;
	document.bannerListForm.modIdx.value = tempIdx;
	if(confirm("<gm:string value='jsp.bnrMg.mblBnrList.msg.modchk'/>")){
		var options={ 
					 success:       responseMod,
			         url:       "<c:url value='/mobileBanner/modifyBanner.omp'/>", 
			         type:      "post"  
				   }; 
		$('#bannerListForm').ajaxSubmit(options);
	}
	}
	}
}

function responseMod(){
	alert("<gm:string value='jsp.bnrMg.mblBnrList.msg.modre'/>");
	selectMobileBannerList();
}

function func_bannerDetail(no){
	if(no == '0000'){
		document.bannerListForm.detailNo.value = "";
	}else{
		document.bannerListForm.detailNo.value = no;
	}
	document.bannerListForm.action="mobileBannerRegister.omp";
	document.bannerListForm.submit();
}

function init(){
	$('#bannerListForm').reset();
	//$(":input:radio[name=mobileBanner.openYn]").attr("checked", "");
	//$(":input:radio[name=mobileBanner.horImgSize]").attr("checked", "");
	//$(":input:radio[name=mobileBanner.bannerType]").attr("checked", "");
	var startDate = document.getElementById("startDate");
	var endDate = document.getElementById("endDate");

	setOrderSearchDateAdminPoC("7day", startDate, endDate);	
			
	$("#keyWord").val("");	
}

function func_cashClear(){
	if(confirm("<gm:string value='jsp.bnrMg.mblBnrList.msg.cashchk'/>")){
		var options={ 
				 success:       responseClear,
		         url:       env.contextPath+"/mobileBanner/wasCashClear.omp", 
		         type:      "post"  
			}; 
		$('#bannerListForm').ajaxSubmit(options);
	}
}

function responseClear(){
	alert("<gm:string value='jsp.bnrMg.mblBnrList.msg.cashre'/>");
}
</script>
</head>
<body>
<s:form id="bannerListForm" name="bannerListForm" theme="simple" method="post">
<input type="hidden" name="mobileBanner.page.no" value="${mobileBanner.page.no}"/>
<s:hidden id="delIdx" name="delIdx"/>
<s:hidden id="modIdx" name="modIdx"/>
<s:hidden id="modval" name="modval"/>
<s:hidden id="modYn" name="modYn"/>
<s:hidden id="detailNo" name="detailNo"/>
<s:hidden id="searcheck" name="searcheck"/>		
			<div id="location">
				Home &gt; 컨텐츠관리 &gt; 배너관리 &gt; <strong>배너관리</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">배너관리</h1>
			<p>모바일 클라이언트에 노출되는 배너를 관리할 수 있습니다.</p>
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>노출구분</th>
					<td class="align_td">
						<input type="radio" id="openYn" name="mobileBanner.openYn" class="ml05" value="" <c:if test="${mobileBanner.openYn eq '' }">checked</c:if>/>전체 &nbsp;
						<input type="radio" id="openYn" name="mobileBanner.openYn" class="ml05" value="Y" <c:if test="${mobileBanner.openYn eq 'Y' }">checked</c:if>/>노출 &nbsp;&nbsp;
						<input type="radio" id="openYn" name="mobileBanner.openYn" class="ml05" value="N" <c:if test="${mobileBanner.openYn eq 'N' }">checked</c:if>/>숨김 
					</td>
					<td rowspan="6" class="text_c" >
						<a class="btn" href="javascript:selectMobileBannerList();"><strong>검색</strong></a>
						<a class="btn" href="javascript:init();"><span>검색초기화</span></a>
					</td>
				</tr>
				<tr>
					<th>가로해상도</th>
					<td class="align_td">
						<input type="radio" id="horImgSize" name="mobileBanner.horImgSize" class="ml05" value=""checked/>전체 &nbsp;&nbsp;		
						<gc:radioButtons name="mobileBanner.horImgSize" group="DP0052" codeType="full" value="${mobileBanner.horImgSize}" divide="&nbsp; &nbsp; &nbsp; &nbsp;" />
					</td>
				</tr>
				<tr>
					<th>배너타입</th>
					<td class="align_td">	
						<input type="radio" id="bannerType" name="mobileBanner.bannerType" class="ml05" value="" <c:if test="${mobileBanner.bannerType eq '' }">checked</c:if>/>전체 &nbsp;&nbsp;				
						<gc:radioButtons name="mobileBanner.bannerType" group="DP0040" codeType="full" value="${mobileBanner.bannerType }" divide="&nbsp; &nbsp; &nbsp; &nbsp;" />
					</td>
				</tr>
				<tr>
					<th>등록일</th>
					<td class="align_td">					
						<input id="startDate" name="mobileBanner.startDate" type="text" class="txt" style="width:80px;" value="<g:text value='${mobileBanner.startDate }' format='L####-##-##'/>" readonly/> ~ 
						<input id="endDate" name="mobileBanner.endDate" type="text" class="txt" style="width:80px;" value="<g:text value='${mobileBanner.endDate }' format='L####-##-##'/>" readonly v:scompare="ge,@{mobileBanner.startDate}" m:scompare="<gm:tagAttb value='jsp.community.qna.qna_list.msg.datechk'/>"/>
						<a class="btn_s" href="javascript:search_date('today');"><span>오늘</span></a> 
						<a class="btn_s" href="javascript:search_date('7day');"><strong>최근1주</strong></a> 
						<a class="btn_s" href="javascript:search_date('1month');"><span>최근1개월</span></a>
					</td>
				</tr>
				<tr>
					<th>배너명</th>
					<td class="align_td">					
						<input id="keyWord" name="mobileBanner.keyWord" type="text" class="txt" style="width:200px;" value="${mobileBanner.keyWord }" />
					</td>
				</tr>
				<tr>
					<th>WAS캐쉬초기화</th>
					<td class="align_td">					
						<a class="btn_s" href="javascript:func_cashClear();"><span>캐쉬초기화</span></a>
					</td>
				</tr>
			</table>
			<p class="fl mt20 mb05"><a class="btn_s" href="javascript:func_bannerDetail('0000');"><span>배너등록</span></a></p>
			<p class="fr mt25">검색 컨텐츠수 : <g:html value="${mobileBannerCnt }" /> 건</p>
			<table class="tabletype02">
				<colgroup>
				<c:choose>
				<c:when test="${mobileBannerCnt > 0}">
					<col style="width:30px;" >
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
					<col >
				</c:when>
				<c:otherwise><col style="width:8%;" ></c:otherwise>
			</c:choose>	
				</colgroup>
				<thead>
				<tr>
					<th><input type="checkbox" id="chk_all" name="chk_all" onClick="javascript:func_check_all();"/></th>
					<th>번호</th>
					<th>이미지</th>
					<th>배너명</th>
					<th>가로해상도</th>
					<th>배너타입</th>
					<th>등록일</th>
					<th>시작일시</th>
					<th>종료일시</th>
					<th>상태</th>
					<th>노출</th>
					<th>순서변경</th>
				</tr>
				</thead>
				<tbody>
				<c:choose>
				<c:when test="${mobileBannerCnt > 0}">
					<c:forEach items="${mobileBannerList}" var="item" varStatus="status">
						<tr>
							<td><input type="checkbox" id="check" name="check" value="${item.bnrNo}"/></td>
							<td><g:html value="${item.rnum}" /></td>
							<td><img src="<c:url value="/fileSupport/imgView.omp"><c:param name="bnsType" value="common.path.http-share.common.banner"/><c:param name="filePath" value="${item.imgPos}${item.imgNm }"/></c:url>" alt="" width="69" height="40" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');"/></td>
							<td class="text_l"><a href="javascript:func_bannerDetail('${item.bnrNo }');"><g:html value="${item.title }" format="L######..." /></a></td>
							<td><g:html value="${item.horImgSize }" /></td>
							<td><g:html value="${item.bannerType }" /></td>
							<td><g:html value="${item.regDttm}" format="L####-##-##" /></td>
							<td><g:html value="${item.bnrStartDt}" format="L####-##-## ##:##" /></td>
							<td><g:html value="${item.bnrEndDt}" format="L####-##-## ##:##" /></td>
							<td><c:if test="${item.state eq 'PROGRESS' }"><gm:html value="jsp.bnrMg.mblBnrList.text.prog"/></c:if><c:if test="${item.state eq '' }"></c:if></td>
							<td>
								<select id="modOpenYn" name="modOpenYn" >
                        			<option value="Y" <c:if test="${item.openYn eq 'Y' }">selected</c:if>>노출</option>
                        			<option value="N" <c:if test="${item.openYn eq 'N' }">selected</c:if>>숨김</option>
                        		</select>					
							</td>
							<td class="align_td">
								<input id="expoPrior${status.index+1}" name="expoPrior" type="text" class="txt" style="width:20px;" value="${item.openOrder }" v:regexp="[1-9][0-9]*" m:regexp="<gm:tagAttb value='jsp.bnrMg.mblBnrList.msg.orderchk'/>" v:required m:required="<gm:tagAttb value='jsp.bnrMg.mblBnrList.msg.ordereq'/>"/> 
								<span class="in_num">
									<a class="num_up" href="javascript:upNum(${status.index+1});">+1</a>
									<a class="num_down" href="javascript:downNum(${status.index+1});">-1</a>
								</span>
							</td>
						</tr>
					</c:forEach>
				</c:when>
				<c:otherwise>
					<td colspan="12" class="text_c">${searcheck}</td>
				</c:otherwise>
				</c:choose>
				</tbody>
			</table>
			<p class="fl mt05"><a class="btn_s" href="javascript:func_deleteBanner();"><span>선택삭제</span></a></p>
			<p class="btn_wrap text_r mt05"><a class="btn" href="javascript:func_modAll();"><span>일괄변경</span></a></p>
			<!-- paging -->
			<div align="center">
			<g:pageIndex item="${mobileBannerList}"/>
			</div>
			<!-- //paging -->
	</s:form>
</body>