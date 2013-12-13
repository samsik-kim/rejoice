<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/ajaxfileupload.js"/>"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#startDate").datepicker();
	$("#endDate").datepicker();	
	//if('${mobileBanner.openYn}' == "N"){
	//	document.getElementById("open").style.display = "none";
	//}
	if('${mobileBanner.bnrNo}' == "") {
		document.bannerListForm.openOrder.value='';
		document.getElementById("term").checked=false;
		document.getElementById("dateDiv").style.display = "none";
		var startDate = document.getElementById("startDate");
		var endDate = document.getElementById("endDate");
		//$("#openOrder").attr("value","");
		setOrderSearchDateAdminPoC("7day", startDate, endDate);
		
	}else{
		document.bannerListForm.propNm.value = "<g:string value='${mobileBanner.title}'/>";
		document.bannerListForm.aid.value = "<g:string value='${mobileBanner.prodId }'/>";
		document.bannerListForm.bnrNo.value = '${mobileBanner.bnrNo }';
		//$("#preViewer").attr("src","<c:url value='/fileSupport/imgView.omp'/>?bnsType=common.path.http-share.common.banner&filePath="+'${mobileBanner.imgPos}'+'${mobileBanner.imgNm }');
		$("#key").attr("value","<g:string value='${mobileBanner.title }'/>'");
		if('${mobileBanner.endDate}'=='99991231'){
			document.getElementById("term").checked=false;
			document.getElementById("dateDiv").style.display = "none";
		}
		for(var i=0;i<document.bannerListForm.startH.length;i++){
			if(document.bannerListForm.startH.options[i].value=='${mobileBanner.startH }'){
				document.bannerListForm.startH.options[i].selected = true;
			}
			if(document.bannerListForm.endH.options[i].value=='${mobileBanner.endH }'){
				document.bannerListForm.endH.options[i].selected = true;
			}
		}
	}

	if('${mobileBanner.bannerType}' == "DP004002"){
		document.getElementById("productLink").style.display = "none";
		document.getElementById("urlLink").style.display = "";
	}else{
		document.getElementById("productLink").style.display = "";
		document.getElementById("urlLink").style.display = "none";
	}
});

$(function() {
	$("#bannerType").change(function() {
		if(document.getElementById("bannerType").value == 'DP004001'){
			document.getElementById("productLink").style.display = "";
			document.getElementById("urlLink").style.display = "none";
		}else{
			document.getElementById("productLink").style.display = "none";
			document.getElementById("urlLink").style.display = "";
		}
	});
});

$(function() {
	$("#term").click(function() {
		if(document.getElementById("term").checked==true){
			document.getElementById("dateDiv").style.display = "";
			var startDate = document.getElementById("startDate");
			var endDate = document.getElementById("endDate");
			setOrderSearchDateAdminPoC("7day", startDate, endDate);
		}else{
			document.getElementById("dateDiv").style.display = "none";
		}
	});
});

function ajaxFileUpload()
{
	if(!/(\.png)$/i.test(document.bannerListForm.bodyUpload.value)) {
        alert("<gm:string value='jsp.bnrMg.mblBnrRegister.msg.jpgchk'/>");
        return;
    }
	
	$(document).ajaxStart(function(){
		$.blockUI({message:$("#blockUI")});
	}).ajaxComplete(function(){
		$.unblockUI();
	});
	$.ajaxFileUpload
	(
		{
			url:env.contextPath+"/mobileBanner/ajaxPreViewBanner.omp", 
			secureuri:false,
			fileElementId:'bodyUpload',
			dataType: 'JSON',
			success: function (data, status)
			{
				if(data.resultCode == 1){
					document.bannerListForm.fileSize.value = data.size;
					if(document.bannerListForm.fileSize.value>10485760){
						alert("<gm:string value='jsp.bnrMg.mblBnrRegister.msg.filelim'/>");
						return;
					}
					document.bannerListForm.orgNm.value = data.orgNm;
					document.bannerListForm.tempPath.value = data.tempPath;
					$("#preViewer").attr("src","<c:url value="/fileSupport/imgView.omp"/>?bnsType=common.path.temp.base&filePath="+data.tempPath);					
				}else{
					alert("<gm:string value='jsp.bnrMg.mblBnrRegister.msg.jpgchk'/>");
				}
			},
			error: function (data, status, e)
			{
				alert(e);
			}
		}
	);
	return false;

}  

function selectProduct(propNm,aid,prodId){
	document.bannerListForm.propNm.value = propNm;
	document.bannerListForm.aid.value = aid;
	document.bannerListForm.prodId.value = prodId;
	if($("#productType option:selected").attr("value") == 'nm'){
		$("#key").attr("value",propNm);
	}else{
		$("#key").attr("value",aid);
	}
}

$(function() {
	$("#productType").change(function() {
		if($("#productType option:selected").attr("value")== 'nm'){
			$("#key").attr("value",document.bannerListForm.propNm.value);
		}else{
			$("#key").attr("value",document.bannerListForm.aid.value);
		}
	});
});

function funcPopProd(){
	var url = "<c:url value='/mobileBanner/popProdList.omp'/>";
    openCenteredWindow(url, 810, 870, "YES", "selectProduct");
}

function saveBanner(){
	if(confirm("<gm:string value='jsp.community.qna.qna_ctgr.msg.savechk'/>")){
		//if(showValidate('bannerListForm', 'default','')){
			//if(document.getElementById("openN").checked){
			//	document.bannerListForm.openOrder.value=999999999;
			//}
			
			if(document.bannerListForm.term.checked==true){
				if(!showValidate(document.bannerListForm.startDate, 'default', "")){
					return;
				}
				if(!showValidate(document.bannerListForm.endDate, 'default', "")){
					return;
				}
				if(!showValidate(document.bannerListForm.startH, 'default', "")){
					return;
				}
				if(!showValidate(document.bannerListForm.endH, 'default', "")){
					return;
				}
				if($("#startDate").val() == $("#endDate").val()){
					if($("#startH").val() > $("#endH").val()){
						alert("<gm:string value='jsp.bnrMg.mblBnrRegister.msg.hourchk'/>");
						return;
					}
				}
				document.bannerListForm.dateChk.value='Y';
			}else{
				document.bannerListForm.dateChk.value='';
			}
			if(document.bannerListForm.tempPath.value==''&&document.bannerListForm.bnrNo.value==''){
				alert("<gm:string value='jsp.bnrMg.mblBnrRegister.msg.bnrchk'/>");
				return;
			}
			if(document.getElementById("bannerType").value == 'DP004001'){
				if(!showValidate(document.bannerListForm.key, 'default', "")){
					return;
				}
				if('${mobileBanner.bnrNo}' == "") {
					if(trim(document.bannerListForm.prodId.value) == ''){
						alert("<gm:string value='jsp.bnrMg.mblBnrRegister.msg.serchchk'/>");
						return;
					}
				}
			}
			if(document.getElementById("bannerType").value == 'DP004002'){
				if(!showValidate(document.bannerListForm.url, 'default', "")){
					return;
				}
				if(!showValidate(document.bannerListForm.title, 'default', "")){
					return;
				}
				document.bannerListForm.prodId.value = trim(document.bannerListForm.url.value);
				document.bannerListForm.propNm.value = trim(document.bannerListForm.title.value);
			}
			if(!showValidate(document.bannerListForm.openOrder, 'default', "")){
				return;
			}
			if(trim(document.bannerListForm.openOrder.value)==''){
				document.bannerListForm.openOrder.value=1;
			}
			var options={ 
			 				success:       responseSave,
	         				url:       env.contextPath+"/mobileBanner/insertBanner.omp", 
	         				type:      "post"  
						}; 
			$('#bannerListForm').ajaxSubmit(options);
		//}
	}
	
}

function responseSave(){
	alert("<gm:string value='jsp.community.qna.qna_details.msg.savere'/>");
	//var url	= env.contextPath+"/mobileBanner/mobileBannerList.omp";
	//location.href = url;
	go_List();
}

function deleteBanner(idx){
	document.bannerListForm.delIdx.value = idx;
	if(confirm("<gm:string value='jsp.community.qna.qna_list.msg.delchk'/>")){
		var options={ 
				 success:       responseDel,
		         url:       env.contextPath+"/mobileBanner/removeBanner.omp", 
		         type:      "post"  
			}; 
		$('#bannerListForm').ajaxSubmit(options);
	}
}

function responseDel(){
	alert("<gm:string value='jsp.bnrMg.mblBnrList.msg.delre'/>");
	go_List();
}

function go_List(){
	document.bannerListForm.chk.value = "Y";
	if(document.bannerListForm.openOrder.value==''){
		document.bannerListForm.openOrder.value=1;
	}
	$("#bannerListForm").attr("action", "mobileBannerList.omp");
	$("#bannerListForm").submit();
}

function trim(value) {  
	return value.replace(/^\s+|\s+$/g,"");  
} 
</script>
</head>
<body>
<s:form id="bannerListForm" name="bannerListForm" theme="simple" method="post" enctype="multipart/form-data">
	<input type="hidden" id="pageNo" name="mobileBannerSub.page.no" value="${mobileBannerSub.page.no}"/>
	<s:hidden id="delIdx" name="delIdx"/>
	<s:hidden id="fileName" name="fileName"/>
	<s:hidden id="filePathName" name="filePathName"/>
	<s:hidden type="hidden" id="tempPath" name="tempPath" />
	<s:hidden id="propNm" name="propNm"/>
	<input type="hidden" id="aid" name="aid" />
	<s:hidden id="fileSize" name="fileSize"/>
	<s:hidden id="orgNm" name="orgNm"/>
	<s:hidden id="bnrNo" name="bnrNo"/>
	<s:hidden id="prodId" name="prodId"/>
	<s:hidden id="chk" name="chk"/>
	<input type="hidden" id="mobileBannerSub" name="mobileBannerSub.startDate" value="${mobileBannerSub.startDate}"/>
	<input type="hidden" id="mobileBannerSub" name="mobileBannerSub.endDate" value="${mobileBannerSub.endDate}"/>
	<input type="hidden" id="mobileBannerSub" name="mobileBannerSub.keyWord" value="${mobileBannerSub.keyWord}"/>
	<input type="hidden" id="mobileBannerSub" name="mobileBannerSub.bannerType" value="${mobileBannerSub.bannerType}"/>
	<input type="hidden" id="mobileBannerSub" name="mobileBannerSub.horImgSize" value="${mobileBannerSub.horImgSize}"/>
	<input type="hidden" id="mobileBannerSub" name="mobileBannerSub.openYn" value="${mobileBannerSub.openYn}"/>
	<input type="hidden" id="dateChk" name="dateChk" value="${dateChk}"/>
	<div id="location">
		首頁 &gt; 內容管理中心 &gt; 橫幅廣告管理 &gt;<strong>橫幅廣告管理</strong> 
	</div><!-- //location -->
	<h1 class="fl pr10">橫幅廣告管理</h1>
			<p>可管理刊登於手機端之橫幅廣告.</p>
	<table class="tabletype01">
		<colgroup>
		<col style="width:20%;">
		<col  style="width:80%;">
		</colgroup>
		<tr>
			<th>橫向解析度</th>
			<td class="align_td">					
				<gc:radioButtons name="mobileBanner.horImgSize" group="DP0052" codeType="full" value="${mobileBanner.horImgSize }" divide="&nbsp; &nbsp; &nbsp; &nbsp;" /> 
			</td>
		</tr>
		<tr><!-- write – URL 링크 등록 -->
			<th>橫幅廣告類別</th>
			<td class="align_td">	
				<select id="bannerType" name="mobileBanner.bannerType" style="width:104px;">
					<gc:options group="DP0040" codeType="full" value="${mobileBanner.bannerType}"/>
				</select>		
				<span id="productLink" name="productLink" >		
                <select id="productType" name="mobileBanner.productType" style="width:104px;">
                    <option value="nm">產品名稱</option>
                    <option value="aid">產品AID</option>
                </select>
                <input id="key" name="key" type="text" class="txt" style="width:200px;" value="<g:tagAttb value=""/>" v:text8_limit="256" v:required="trim" m:required="<gm:tagAttb value='jsp.bnrMg.mblBnrRegister.msg.prodchk'/>" m:text8_limit="<gm:tagAttb value='jsp.bnrMg.mblBnrRegister.msg.256'/>"/>
				<a class="btn_s" href="javascript:funcPopProd();"><span>搜尋</span></a>
                </span>
                <span id="urlLink" name="urlLink" style="display:none;">
				<label for="#">URL</label> <input id="url" name="url" type="text" class="txt" style="width:200px;" value="<g:tagAttb value="${mobileBanner.prodId}"/>" v:required="trim" m:required="<gm:tagAttb value='jsp.bnrMg.mblBnrRegister.msg.urlchk'/>" v:text8_limit="300" m:text8_limit="<gm:tagAttb value='jsp.bnrMg.mblBnrRegister.msg.300'/>"/><br />
				<label for="#" style="margin-left:118px;">標題</label> <input id="title" name="title" type="text" class="txt" style="width:200px;" value="<g:tagAttb value="${mobileBanner.title}"/>" v:required="trim" m:required="<gm:tagAttb value='jsp.bnrMg.mblBnrRegister.msg.reqTitle'/>" v:text8_limit="256" m:text8_limit="<gm:tagAttb value='jsp.bnrMg.mblBnrRegister.msg.256'/>"/>
				</span>
			</td>
		</tr>
		<tr>
			<th>上傳橫幅廣告</th>
			<td class="align_td">
			<span id="spView" name="spView">
			<c:if test="${mobileBanner.bnrNo ne '' }">
			<img src="<c:url value='/fileSupport/imgView.omp'><c:param name="bnsType" value="common.path.http-share.common.banner"/><c:param name="filePath" value="${mobileBanner.imgPos}${mobileBanner.imgNm }"/></c:url>" alt="preView" id="preViewer" name="preViewer" class="fl mr10" width="107" height="95" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');"/>
			</c:if>
			<c:if test="${mobileBanner.bnrNo eq '' }">
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/temp_img02.jpg' />" alt="preView" id="preViewer" name="preViewer" class="fl mr10" width="107" height="95" onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');"/>
			</c:if>
			</span>
			<s:file id="bodyUpload" name="bodyUpload" onchange="ajaxFileUpload()" /><br />
			- 請上傳PNG檔。
			</td>
		</tr>
		<tr>
			<th>公開設定</th>
			<td class="align_td">
				<p class="fr">*若不輸入排列順序, 將最先顯示該廣告。</p>					
				<input type="radio" id="openY" name="mobileBanner.openYn" class="ml05" value="Y" <c:if test="${mobileBanner.openYn eq 'Y' || mobileBanner.openYn eq '' }">checked</c:if> />公開  &nbsp;
				<input type="radio" id="openN" name="mobileBanner.openYn" class="ml05" value="N" <c:if test="${mobileBanner.openYn eq 'N' }">checked</c:if> />隱藏 
				<!--<span id="open" name="open" > -->
				<label for="#" class="ml30">排列順序</label>
				<input id="openOrder" name="mobileBanner.openOrder" type="text" class="txt" style="width:30px;" v:regexp="[1-9][0-9]*" m:regexp="<gm:tagAttb value='jsp.bnrMg.mblBnrList.msg.orderchk'/>" value="${mobileBanner.openOrder }" />次 
				<!-- <input id="openOrder" name="mobileBanner.openOrder" type="text" class="txt" style="width:30px;" v:required="trim" m:required="<gm:tagAttb value='jsp.bnrMg.mblBnrList.msg.ordereq'/>" v:regexp="[1-9][0-9]*" m:regexp="<gm:tagAttb value='jsp.bnrMg.mblBnrList.msg.orderchk'/>" value="${mobileBanner.openOrder }" />次 -->
				<!--</span>-->
			</td>
		</tr>
		<tr>
			<th>刊登期間 <input type="checkbox" id="term" name="term" checked/></th>
			<td class="align_td">				
			<div id="dateDiv" name="dateDiv" >	
			<input id="startDate" name="mobileBanner.startDate" type="text" class="txt" style="width:80px;" value="<g:text value='${mobileBanner.startDate }' format='L####-##-##'/>" readonly v:required="trim" m:required="<gm:tagAttb value='jsp.bnrMg.mblBnrRegister.msg.stdatechk'/>"/>
			<select id="startH" name="mobileBanner.startH" style="width:104px;" v:required="trim" m:required="<gm:tagAttb value='jsp.bnrMg.mblBnrRegister.msg.sthourchk'/>">
                <option value="000000">00:00</option>
                <option value="003000">00:30</option>
                <option value="010000">01:00</option>
                <option value="013000">01:30</option>
                <option value="020000">02:00</option>
                <option value="023000">02:30</option>
                <option value="030000">03:00</option>
                <option value="033000">03:30</option>
                <option value="040000">04:00</option>
                <option value="043000">04:30</option>
                <option value="050000">05:00</option>
                <option value="053000">05:30</option>
                <option value="060000">06:00</option>
                <option value="063000">06:30</option>
                <option value="070000">07:00</option>
                <option value="073000">07:30</option>
                <option value="080000">08:00</option>
                <option value="083000">08:30</option>
                <option value="090000">09:00</option>
                <option value="093000">09:30</option>
                <option value="100000">10:00</option>
                <option value="103000">10:30</option>
                <option value="110000">11:00</option>
                <option value="113000">11:30</option>
                <option value="120000">12:00</option>
                <option value="123000">12:30</option>
                <option value="130000">13:00</option>
                <option value="133000">13:30</option>
                <option value="140000">14:00</option>
                <option value="143000">14:30</option>
                <option value="150000">15:00</option>
                <option value="153000">15:30</option>
                <option value="160000">16:00</option>
                <option value="163000">16:30</option>
                <option value="170000">17:00</option>
                <option value="173000">17:30</option>
                <option value="180000">18:00</option>
                <option value="183000">18:30</option>
                <option value="190000">19:00</option>
                <option value="193000">19:30</option>
                <option value="200000">20:00</option>
                <option value="203000">20:30</option>
                <option value="210000">21:00</option>
                <option value="213000">21:30</option>
                <option value="220000">22:00</option>
                <option value="223000">22:30</option>
                <option value="230000">23:00</option>
                <option value="233000">23:30</option>
            </select> ~ 
			<input id="endDate" name="mobileBanner.endDate" type="text" class="txt" style="width:80px;" value="<g:text value='${mobileBanner.endDate }' format='L####-##-##'/>" readonly v:required="trim" m:required="<gm:tagAttb value='jsp.bnrMg.mblBnrRegister.msg.enddatechk'/>" v:scompare="ge,@{mobileBanner.startDate}" m:scompare="<gm:tagAttb value='jsp.bnrMg.mblBnrRegister.msg.startend'/>"/>		
			<select id="endH" name="mobileBanner.endH" style="width:104px;" v:required="trim" m:required="<gm:tagAttb value='jsp.bnrMg.mblBnrRegister.msg.endhourchk'/>">
                <option value="000000">00:00</option>
                <option value="003000">00:30</option>
                <option value="010000">01:00</option>
                <option value="013000">01:30</option>
                <option value="020000">02:00</option>
                <option value="023000">02:30</option>
                <option value="030000">03:00</option>
                <option value="033000">03:30</option>
                <option value="040000">04:00</option>
                <option value="043000">04:30</option>
                <option value="050000">05:00</option>
                <option value="053000">05:30</option>
                <option value="060000">06:00</option>
                <option value="063000">06:30</option>
                <option value="070000">07:00</option>
                <option value="073000">07:30</option>
                <option value="080000">08:00</option>
                <option value="083000">08:30</option>
                <option value="090000">09:00</option>
                <option value="093000">09:30</option>
                <option value="100000">10:00</option>
                <option value="103000">10:30</option>
                <option value="110000">11:00</option>
                <option value="113000">11:30</option>
                <option value="120000">12:00</option>
                <option value="123000">12:30</option>
                <option value="130000">13:00</option>
                <option value="133000">13:30</option>
                <option value="140000">14:00</option>
                <option value="143000">14:30</option>
                <option value="150000">15:00</option>
                <option value="153000">15:30</option>
                <option value="160000">16:00</option>
                <option value="163000">16:30</option>
                <option value="170000">17:00</option>
                <option value="173000">17:30</option>
                <option value="180000">18:00</option>
                <option value="183000">18:30</option>
                <option value="190000">19:00</option>
                <option value="193000">19:30</option>
                <option value="200000">20:00</option>
                <option value="203000">20:30</option>
                <option value="210000">21:00</option>
                <option value="213000">21:30</option>
                <option value="220000">22:00</option>
                <option value="223000">22:30</option>
                <option value="230000">23:00</option>
                <option value="233000">23:30</option>
            </select>
            </div>
			</td>
		</tr>
	</table>
	<p class="btn_wrap text_r mt05">
	<c:if test="${mobileBanner.bnrNo ne '' }">
		<a class="btn" href="javascript:deleteBanner('${mobileBanner.bnrNo}');"><span>刪除</span></a>
	</c:if>
		<a class="btn" href="javascript:saveBanner();"><span>儲存</span></a>
		<a class="btn" href="javascript:go_List();"><span>目錄</span></a>
	</p>
</s:form>
</body>
</html>