<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/calendar.js"></script>
<script type="text/javascript">

//<![CDATA[
	
   $(document).ready(function(){

		//검색년도 현재년월로 셋팅
		var curYear = eval(getYear());
		var curMonth = getMonth();
		
		for ( var i=curYear-10; i <=curYear+10 ; i++){
			$("#receiptSS\\.receiptFromYear").append("<option value='"+i+"'>"+i+"</option>");
		}
		$("#receiptSS\\.receiptFromYear > option[value="+curYear+"]").attr("selected", true);
		$("#receiptSS\\.receiptFromMonth > option[value="+curMonth+"]").attr("selected", true);
	});
	
	
	$(function() {
		
		$("#insertBtn").click(function(event){
			event.preventDefault();
			
			/*
			
			var startNumLength;
			var endNumLength;
			
			startNumLength = $.trim($("#receiptSS\\.rtStartNo").val()).length;
			endNumLength = $.trim($("#receiptSS\\.rtEndNo").val()).length;
			
			if (startNumLength < 8 || endNumLength < 8) {
				alert("<gm:string value='jsp.settlement.basis.insertStartReceipt.numcheck'/>");
				return;
			}else if (eval($.trim($("#receiptSS\\.rtStartNo").val())) > eval($.trim($("#receiptSS\\.rtEndNo").val()))){
				alert("<gm:string value='jsp.settlement.basis.insertStartReceipt.start'/>");
				return;
			}
			
			$("#receiptSS\\.rtYearterm").val($("#receiptSS\\.receiptFromYear").val() + $("#receiptSS\\.receiptFromMonth").val());
			//alert($("#rtYearterm").val())	;
			//frm.action="<c:url value="/settlement/insertEndReceipt.omp" />";
			//frm.submit();
			
			
			var data = $("#insertForm").serialize();
			$.ajax({
				url: '<c:url value="/settlement/ajaxCheckExistReceipt.omp" />',
				dataType: 'json',
				type	: "POST",
				data 	: data,
				async	: false,		
				cache	: false,	
				success: function(json){
					if(json.data == 'N'){
						$.blockUI("<gm:string value='jsp.settlement.basis.insertStartReceipt.process'/>");
				 		$('#insertForm').attr('action', '<c:url value="/settlement/insertEndReceipt.omp" />');
				 		$('#insertForm').submit();
					}else if(json.data == 'Y') {
						alert("<gm:string value='jsp.settlement.basis.insertStartReceipt.double'/>");
						return false;
					}
				},
				error: function(xhr, textStatus, errorThrown){
					//alert('error');	
				}
			});
			*/
			
			if(showValidate('insertForm', 'default', "<gm:string value=""/>")){
				$("#receiptSS\\.rtYearterm").val($("#receiptSS\\.receiptFromYear").val() + $("#receiptSS\\.receiptFromMonth").val());
				
				var data = $("#insertForm").serialize();
				$.ajax({
					url: '<c:url value="/settlement/ajaxCheckExistReceipt.omp" />',
					dataType: 'json',
					type	: "POST",
					data 	: data,
					async	: false,		
					cache	: false,	
					success: function(json){
						if(json.data == 'N'){
							$.blockUI("<gm:string value='jsp.settlement.basis.insertStartReceipt.process'/>");
					 		$('#insertForm').attr('action', '<c:url value="/settlement/insertEndReceipt.omp" />');
					 		$('#insertForm').submit();
						}else if(json.data == 'Y') {
							alert("<gm:string value='jsp.settlement.basis.insertStartReceipt.double'/>");
							return false;
						}
					},
					error: function(xhr, textStatus, errorThrown){
						//alert('error');	
					}
				});
			}
			
		});
		
		$("#listBtn").click(function(event){
			event.preventDefault();
			document.listForm.action="<c:url value="/settlement/receiptList.omp" />";
			document.listForm.submit();
		});
	});
	
	
	function js_OnlyNumberR(obj)	{
		if (event.keyCode == 13){event.returnValue =false;}
		//alert(obj.value);
	    //alert(event.keyCode);
		val=obj.value;
		re=/[^0-9]/gi;
		obj.value=val.replace(re,"");
	}
	
	var insertBtnFnc = {
			   numLength : function() {
			                              //검증 스크립트를 검증 통과시 true, 오류 처리해야 할때는  false를 리턴하도록 작성 
			            },
			   xxx : function() {
			                              //검증 스크립트를 검증 통과시 true, 오류 처리해야 할때는  false를 리턴하도록 작성 
			            }
			};

	
//]]>
</script>

</head>
<body>
	
			<div id="location">
				Home &gt; 정산관리 &gt; 영수증관리 &gt; <strong>영수증발행관리</strong> 
			</div><!-- //location -->

			<h1 class="fl pr10">영수증발행관리</h1>
			<p>영수증 발행에 대한 내역을 확인하실 수 있습니다.</p>
			<s:form id="insertForm" name="insertForm"  theme="simple" >
			<input type="hidden" id="receiptSS.rtYearterm" name="receiptSS.rtYearterm"/>
			
			
			<input type="hidden" name="receiptS.receiptFromMonth" id="receiptS.receiptFromMonth" value="${receiptS.receiptFromMonth }">
		<input type="hidden" name="receiptS.receiptFromYear" id="receiptS.receiptFromYear" value="${receiptS.receiptFromYear }">
		<input type="hidden" name="receiptS.rtYearterm" id="receiptS.rtYearterm" value="${receiptS.rtYearterm }">
		<input type="hidden" id="receiptS.page.no" name="receiptS.page.no" value="${receiptS.page.no}" />
		<input type="hidden" id="receiptS.firstAccessYN" name="receiptS.firstAccessYN" value="${receiptS.firstAccessYN}">
			
			
			<table class="tabletype01">
				<colgroup><col style="width:100px;"><col ><col style="width:125px"></colgroup>
				<tr>
					<th>영수증 관리</th>
					<td class="align_td line2_5">					
						<label for="#" class="mr10">사용기간</label>
                        <select id="receiptSS.receiptFromYear" name="receiptSS.receiptFromYear">
                        </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<select id="receiptSS.receiptFromMonth" name="receiptSS.receiptFromMonth">
                        	<option value="01">01</option>
							<option value="02">02</option>
							<option value="03">03</option>
							<option value="04">04</option>
							<option value="05">05</option>
							<option value="06">06</option>
							<option value="07">07</option>
							<option value="08">08</option>
							<option value="09">09</option>
							<option value="10">10</option>
							<option value="11">11</option>
							<option value="12">01</option>
                        </select>
						<br />
						<label for="#" class="mr10">번호</label>
						<input type="text" class="txt" id="receiptSS.rtPrfix" name="receiptSS.rtPrfix"  style="width:80px;" maxlength="2"
							v:required m:required="<gm:string value='jsp.settlement.basis.insertStartReceipt.numcheck'/>"
							v:regexp="[A-Za-z]{2}" m:regexp="<gm:string value='jsp.settlement.basis.insertStartReceipt.numcheck'/>"
						/>
						 <input type="text" class="txt" id="receiptSS.rtStartNo" name="receiptSS.rtStartNo" maxlength="8" onKeyUp = "js_OnlyNumberR(this);" 
							v:required m:required="<gm:string value='jsp.settlement.basis.insertStartReceipt.numcheck'/>"
							v:regexp="[0-9]{8}" m:regexp="<gm:string value='jsp.settlement.basis.insertStartReceipt.numcheck'/>"
						 /> ~ 
						 <input type="text" class="txt" id="receiptSS.rtEndNo" name="receiptSS.rtEndNo" maxlength="8" onKeyUp = "js_OnlyNumberR(this);"
							 v:required m:required="<gm:string value='jsp.settlement.basis.insertStartReceipt.numcheck'/>"
							 v:regexp="[0-9]{8}" m:regexp="<gm:string value='jsp.settlement.basis.insertStartReceipt.numcheck'/>"
							 v:ncompare=">,@{receiptSS\\.rtStartNo}" m:ncompare="<gm:string value='jsp.settlement.basis.insertStartReceipt.numcheck'/>"
						 /> 
					</td>
				</tr>
			</table>
			</s:form>
			<p class="btn_wrap text_c mt20"><a class="btn" href="#" id="insertBtn"  ><span>저장</span></a>
											<a class="btn" href="#" id="listBtn"  ><span>목록</span></a>
			</p>

	
	<hr>
	<s:form id="listForm" name="listForm"  theme="simple" >
		<input type="hidden" name="receiptS.receiptFromMonth" id="receiptS.receiptFromMonth" value="${receiptS.receiptFromMonth }">
		<input type="hidden" name="receiptS.receiptFromYear" id="receiptS.receiptFromYear" value="${receiptS.receiptFromYear }">
		<input type="hidden" name="receiptS.rtYearterm" id="receiptS.rtYearterm" value="${receiptS.rtYearterm }">
		<input type="hidden" id="receiptS.page.no" name="receiptS.page.no" value="${receiptS.page.no}" />
		<input type="hidden" id="receiptS.firstAccessYN" name="receiptS.firstAccessYN" value="${receiptS.firstAccessYN}">
	</s:form>
	
</body>
</html>
