<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 
<head>
<script type="text/javascript">
<!--
var originalcount = (${testPhSetCnt}==0?2:${testPhSetCnt}+1);
var count = (${testPhSetCnt}==0?2:${testPhSetCnt}+1);
var countText = (${testPhSetCnt}==0?2:${testPhSetCnt}+1);
var options;
var macAddrText = '<gm:string value="jsp.content.phone.testPhoneSet.text.01" />';

$(document).ready( function() {
	
	$("#btnMod").click(function() {
		
		//if($("#testcel").attr("value") == macAddrText){
		//	$("#testcel").attr("value","");
		//}
		var fooFns = {
				
			showcustomvalidate : function() {
				
				var result = true;

	        	$("input[name=testcel]").each(function() {
	        	
		        	if (isNull($(this).val()) || $(this).val().indexOf(macAddrText) > -1){
		        		result = false;
		        	}
		        
	        	});

	        	return result;
			}
		};
		
		if(showValidate('OTAForm', 'default','<gm:string value="jsp.content.phone.testPhoneSet.msg.title05" />', fooFns)){
			
			var length = document.getElementsByTagName("input").length;
			var tempSeq = null;
			var tempMac = null;
			
			for(var i=0;i<length;i++){
				if(document.getElementsByTagName("input")[i].value==""){
					if(document.getElementsByTagName("input")[i].name=='testcel'){
						alert("<gm:string value='jsp.content.phone.testPhoneSet.btn.01'/>");
						return;
					}
				}
			}
			
			for(var i=0;i<length;i++){
				
				if(document.getElementsByTagName("input")[i].value!=""){
					
					if(document.getElementsByTagName("input")[i].name.substring(0,7)=='testcel'){
						if(tempSeq==null){
							tempSeq = "#"+ document.getElementsByTagName("input")[i].id;
						}else{
							tempSeq = tempSeq+"#"+ document.getElementsByTagName("input")[i].id;
						}
						if(tempMac==null){
							tempMac = "#"+ document.getElementsByTagName("input")[i].value;
						}else{
							tempMac = tempMac+"#"+document.getElementsByTagName("input")[i].value;
						}
					}
				}
			}
			
			document.OTAForm.otaSeq.value = tempSeq;
			document.OTAForm.otaMac.value = tempMac;
			document.OTAForm.action=env.contextPath+"/content/InsTestPhone.omp";
			document.OTAForm.submit();
		}
	}).css("cursor", "pointer");
});

function func_insert(){
	
	if(count>10){
		alert("<gm:string value='jsp.content.phone.testPhoneSet.msg.01'/>");
		return;
	} 
	var paramCount;
	//if (originalcount == count) paramCount = count; countText = count;
	if (countText < 10)  paramCount = '0'+countText;
	else paramCount = countText;
	//if(count >= 10){
	//	$("#testPhonSet").append("<tr id='trPh"+count+"'><th scope='row' class='th01'><label for='testcell02'>테스트 단말 10</label></th><td class='td01'><input type='text' id='testcel"+count+"' name='testcel"+count+"' value='<gm:tagAttb value=''/>' class='w520' v:required='trim' m:required='<gm:tagAttb value='jsp.content.phone.testPhoneSet.btn.01'/>' v:text8_limit='20' m:text8_limit='<gm:tagAttb value='jsp.content.phone.testPhoneSet.msg.02'/>'/> &nbsp;&nbsp;<a href='javascript:func_delete("+count+");'><img src='${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif' alt='삭제' /></a></td></tr>");
	//	count++;
	//	countText++;
	//	return;
	//}
	$("#testPhonSet").append("<tr id='trPh"+countText+"'><th scope='row' class='th01'><label for='testcell02'>테스트 단말 "+paramCount+"</label></th><td class='td01'><input type='text' id='testcel"+countText+"' name='testcel' value='<gm:tagAttb value='jsp.content.phone.testPhoneSet.text.01'/>' class='w520'  onfocus='javascript:inputTextClear(this);'  v:showcustomvalidate m:showcustomvalidate='<gm:tagAttb value='jsp.content.phone.testPhoneSet.btn.01'/>' v:text8_limit='20' m:text8_limit='<gm:tagAttb value='jsp.content.phone.testPhoneSet.msg.02'/>''/> &nbsp;&nbsp;<a href='javascript:func_delete("+countText+");'><img src='${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif' alt='삭제' /></a></td></tr>");
	count++;
	countText++;
}

function func_delete(num){
	if(confirm("<gm:string value='jsp.content.phone.testPhoneSet.btn.02'/>")){
		var temp = null;
		temp = $("#tmpSeq"+num).val();
		if(temp!=null){
			document.getElementById('otaSeq').value = $("#tmpSeq"+num).val();
		}
		$("#trPh"+num).remove();
		count--;
		if(temp!=null){
	    	var options={ 
	    					dataType: "text",
	    				 	url:       env.contextPath+"/content/ajaxDeletePhone.omp",
			         		type:      "post",
			         		success : successResponse,
			         		error	:successResponse
						}; 
			$("#OTAForm").ajaxSubmit(options);
		}
	}
}

function successResponse(responseText, status) {

	var obj = eval("(" + responseText + ")");
	var testPhSetCnt = obj.testPhSetCnt; 

	var intTestPhSetCnt = parseInt(testPhSetCnt);
	
	if (testPhSetCnt == 0) {
		count = countText = 1;

		$("#testPhonSet").append("<tr id='trPh"+count+"'><th scope='row' class='th01'><label for='testcell02'>테스트 단말 0"+countText+"</label></th><td class='td01'><input type='text' id='testcel"+count+"' name='testcel' value='<gm:tagAttb value='jsp.content.phone.testPhoneSet.text.01'/>' class='w520'  onfocus='javascript:inputTextClear(this);'  v:showcustomvalidate m:showcustomvalidate='<gm:tagAttb value='jsp.content.phone.testPhoneSet.btn.01'/>' v:text8_limit='20' m:text8_limit='<gm:tagAttb value='jsp.content.phone.testPhoneSet.msg.02'/>''/> &nbsp;&nbsp;</td></tr>");
		
		count++; countText++;
	} 
}

function cancel(){
	document.OTAForm.action = "testPhoneRegister.omp";
	document.OTAForm.submit();
}

function inputTextClear(obj) {
	if($(obj).val() == macAddrText) {
		$(obj).val('');
		$(obj).empty();
	}
}

//-->
</script>
</head>
<body>
<form id="OTAForm" name="OTAForm"  method="post" enctype="multipart/form-data" action="">
	<input type="hidden" id="otaSeq" name="otaSeq" />
	<input type="hidden" id="otaMac" name="otaMac" />	
	<div id="contents_area">
		<!-- Title Area S -->
		<div id="ctitle_area"> 
			<p class="history">Home &gt; 상품등록/관리 &gt; 상품등록 <strong>&gt;</strong> <span>테스트단말 설정</span></p>
			<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title_05.gif'/>" alt="테스트단말 설정" /></h3>
		</div>
		<!-- //Title Area E -->
	<div id="contents">
		<h4 class="h41 fltl"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_16.gif'/>" alt="테스트를 위한 단말을 설정해주세요." /> <span> (단말은 최대 10개까지 등록이 가능합니다.)</span></h4>
		<p class="txtr pad_b5"><a href="javascript:func_insert();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_add1.gif'/>" alt="테스트 단말 추가" /></a></p>
			<div class="tstyleA">
				<table id="testPhonSet" name="testPhonSet" summary="테스트단말 설정">
					<caption>테스트단말 설정</caption>
					<colgroup>
						<col width="15%" />
						<col />
					</colgroup>
					<tbody>
					<c:choose>
					<c:when test="${testPhSetCnt > 0}">
					<c:forEach items="${testPhSetList}" var="item" varStatus="status">
						<tr id="trPh${status.index+1}">
							<th scope="row" class="th01">
								<label for="testcell02">테스트 단말 <c:if test="${status.index+1 ne 10}">0</c:if>${status.index+1}</label>
							</th>
							<td class="td01">
								<input type="text" id="${item.tmpSeq}" name="testcel" value="<gm:html value="${item.macAddr}"/>" class="w520"  onfocus="javascript:inputTextClear(this);" v:showcustomvalidate m:showcustomvalidate="<gm:tagAttb value='jsp.content.phone.testPhoneSet.btn.01'/>"  v:text8_limit="20"  m:text8_limit="<gm:tagAttb value='jsp.content.phone.testPhoneSet.msg.02'/>"/>
								<input type="hidden" id="tmpSeq${status.index+1}" name="tmpSeq" value="${item.tmpSeq}" /> &nbsp;
								<a href="javascript:func_delete(${status.index+1});"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="삭제" /></a>
							</td>
						</tr>
					</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<th scope="row" class="th01">
								<label for="testcell02">테스트 단말 01</label>
							</th>
							<td class="td01">
								<input type="text" id="testcel" name="testcel"  class="w520"  onfocus="javascript:inputTextClear(this);" value="<gm:html value='jsp.content.phone.testPhoneSet.text.01'/>" v:showcustomvalidate m:showcustomvalidate="<gm:tagAttb value='jsp.content.phone.testPhoneSet.btn.01'/>" v:text8_limit="20" m:text8_limit="<gm:tagAttb value='jsp.content.phone.testPhoneSet.msg.02'/>"/>
							</td>
						</tr>
					</c:otherwise>
					</c:choose>
					</tbody>
				</table>
			</div>
			<div class="btnarea mar_t30">
				<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_save.gif'/>" alt="저장"  id="btnMod"/>
				<a href="javascript:cancel();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>" alt="취소" /></a>
			</div>
		</div>
	</div>
</form>
</body>