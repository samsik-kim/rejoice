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
		var form = document.myForm;
		
		// target Btn
		$("#targetBtn").click(function(event){
			event.preventDefault();
			searchPresentCashTarget();
		});

		// authDwldConfirmBtn
		$("#authDwldConfirmBtn").click(function(event){
			event.preventDefault();
			authDwldConfirmPopup();
		});
		
		// excelBtn
		$("#excelBtn").click(function(event){
			event.preventDefault();
			goExcel();
		});
		
	});
         
           
	function chageType(issueType){
		var frm = document.getElementById('myForm');
		var isChange = false;

		if($("#resultType").val() != "all"){
			isChange = true;
		}else if(issueType == "excelRegi"){
			if(confirm("<gm:string value='jsp.gift.registReqIssueCash.msg.cancelTarget'/>")){
				isChange = true;
			}
		}else if(issueType == "specific"){
			if(confirm("<gm:string value='jsp.gift.registReqIssueCash.msg.cancelExcel'/>")){
				isChange = true;
			}
		}else
			isChange = true;

		if(isChange){
			$("#issueType").val(issueType);
			
			if($("#resultType").val() == 'all'){
				$("#vToday").val("");
			}else{
				$("#resultType").val("all");
			}
			
			frm.action = "viewRequestIssueCash.omp";
			frm.target="";
			frm.submit();
		}
	}
    
	function gotoList(flag) {
		var frm = document.getElementById('myForm');
		if(flag == 'cancel'){
			if(confirm("<gm:string value='jsp.gift.registReqIssueCash.msg.cancelGift'/>")){		
				$("#vToday").val("");
				$("#issueType").val("all");
				document.myForm.action="presentCashList.omp";
				document.myForm.submit();
			}
		}else if(flag == 'confirm'){
			$("#issueType").val("all");
			var enddate = $("#endDate").val().substring(0,10);
			$("#endDate").val(enddate);
			document.myForm.action="presentCashList.omp";
			document.myForm.submit();
		}
	}

	function searchPresentCashTarget(){

		width = 440;
		height = 350;
		x = (screen.width) ? (screen.width-width)/2 : 0;
		y = (screen.height) ? (screen.height-height)/2 : 0;
			 
		scrollOption = "Yes";

		var url = "popSearchTarget.omp";
			
		window.open(url,"popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");	

	}
	
	
	var filePath = "";

	function doUpfile(idx){
		var frm = document.getElementById('myForm');
		eval("frm.comp_file.click()");
		filePath = $("#comp_file").val();
		//var comvalue= $("#comp_file").val();
		//alert(comvalue);
		//filePath = eval("frm.comp_file.value");
		
		fnImg_Check(filePath,idx);							
	}	
	
	function fnImg_Check(value,idx){   // 파일 확장자 체크하기.
		var src = getFileExtension(value);
		if(!(src.toLowerCase() == "xls") && idx =='3'){
			alert("<gm:string value='jsp.gift.registReqIssueCash.msg.noRegFile'/>");		  
			document.getElementById("comp_file").outerHTML = document.getElementById("comp_file").outerHTML;       
			return false;
		}
		img_Load(value,idx);
	}	

	function getFileExtension(filePath){  // 파일의 확장자를 가져옮
	    var lastIndex = -1;
	    lastIndex  = filePath.lastIndexOf('.');     
	    var extension = "";
		   if(lastIndex != -1){
		     extension = filePath.substring( lastIndex+1, filePath.len );
		   } else{
		     extension = "";
		   }
	    return extension;
	  }

	function img_Load(idx,imgInfo){
		var fname=filePath;
		var s=fname.lastIndexOf("\\");
		var m=fname.lastIndexOf(".");
		var e=fname.length;
		var filename=fname.substring(s+1,m);
		var extname=fname.substring(m+1,e);
			    
		document.getElementById('myForm').comp_file_name.value=filename+"."+extname;
		//alert(document.getElementById('myForm').comp_file_name.value);
		
	}

	function validate(reqCardNm, cash, target, today, issueType){

		if(reqCardNm == ""){
			alert("<gm:string value='jsp.gift.registReqIssueCash.msg.chkContent'/>");
			return false;
		}else if(target == ""){
			if(issueType == 'specific')
				alert("<gm:string value='jsp.gift.registReqIssueCash.msg.chkTarget'/>");
			else if(issueType == 'excelRegi')
				alert("<gm:string value='jsp.gift.registReqIssueCash.msg.chkRegTarget'/>");
			return false;
		}else if(cash == ""){
			alert("<gm:string value='jsp.gift.registReqIssueCash.msg.chkInputCash'/>");
			return false;
		}else if(isNaN(parseInt(cash))){
			alert("<gm:string value='jsp.gift.registReqIssueCash.msg.chkNumberCash'/>");
			return false;
		}else if(parseInt(cash) < 1000 || parseInt(cash) > 2000000){
			alert("<gm:string value='jsp.gift.registReqIssueCash.msg.chkRange'/>");
			return false;
		}

		return true;
	}

	function authDwldConfirmPopup(){
		var frm = document.getElementById('myForm');

		var target = null;
		var reqCardNm = frm.reqCardNm.value;
		var cash = frm.presentCashAmt.value;
		var today = frm.vToday.value;
		var issueType = frm.issueType.value;

		if(issueType == 'specific')
			target = frm.mbrId.value;
		else if(issueType == 'excelRegi')
			target = filePath;
		
		if(validate(reqCardNm, cash, target, today, issueType)){
			width = 440;
			height = 160;
			x = (screen.width) ? (screen.width-width)/2 : 0;
			y = (screen.height) ? (screen.height-height)/2 : 0;
			scrollOption = "No";
			var url = "popAuthDwldConfirm.omp?sub.issueType=" + issueType;
			window.open(url,"popup", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");
		}
	}

	function excute(){
		//alert($("#issueType").val());
		if($("#issueType").val() == 'specific'){
			//if(confirm(ID + "님께 " + cash + " Cash를 발급하시겠습니까?")){
			if(confirm("<gm:string value='jsp.gift.registReqIssueCash.msg.chkIdCash'/>")){
				$("#resultType").val("specific");
				document.myForm.action="requestTargetIssueCash.omp";
				document.myForm.submit();
			}
		}else if($("#issueType").val() == 'excelRegi'){
			if(confirm("<gm:string value='jsp.gift.registReqIssueCash.msg.chkRegCash'/>")){
				$("#resultType").val("excel");
				document.myForm.action="requestTargetIssueCash.omp";
				document.myForm.submit();
			}
		}
	}

	function goExcel() {
		var fm = document.getElementById('myForm');
		fm.action = "issuePresentCashListExcel.omp";
		fm.submit();
	}
//]]>
</script>
	</head>
<body>
	<div id="location">
		首頁 &gt; 產品管理中心  &gt; Whoopy幣管理 &gt <strong>管理者Whoopy幣發行紀錄</strong> 
	</div><!-- //location -->
	<h1 class="fl pr10">管理者Whoopy幣發行紀錄</h1>
	<p>可查看Whoopy幣之現狀。</p>
	<s:form id="myForm" name="myForm" theme="simple" method="post" enctype="multipart/form-data">
	<input type="hidden" id="startDate" name="sub.startDate" value="${sub.startDate}" />
	<input type="hidden" id="endDate" name="sub.endDate" value="${sub.endDate}" />
	<input type="hidden" id="issueType" name="sub.issueType" value="${sub.issueType}" />
	<input type="hidden" id="resultType" name="sub.resultType" value="${sub.resultType}" />
	<input type="hidden" id="vToday" name="sub.vToday" value="${sub.endDate}" />
	<input type="hidden" id="mbrId" name="giftCard.mbrId" value="" /> 
	<input type="hidden" id="comp_file_name" name="giftCard.comp_file_name" value=""/>
	<input type="hidden" id="groupSeq" name="giftCard.groupSeq" value="${giftCard.groupSeq}"/>
	<input type="hidden" id="failedMdnList" name="giftCard.failedMdnList" value="${failedMdnList}"/>
	    
	<table class="tabletype02">
		<colgroup>
			<col style="width:20%;">
			<col style="width:80%;">
		</colgroup>
		<tbody>
		<tr>
			<th>發行形式</th>
			<td class="text_l align_td">
				<input type="radio" id="specific" class="ml05" value="specific" <c:if test="${sub.issueType eq 'excelRegi'}">onClick="javascript:chageType('specific');"</c:if><c:if test="${sub.issueType eq 'specific'}">checked</c:if>/>指定發行 
				<input type="radio" id="excelRegi" class="ml05" value="excelRegi" <c:if test="${sub.issueType eq 'specific'}">onClick="javascript:chageType('excelRegi');"</c:if><c:if test="${sub.issueType eq 'excelRegi'}">checked</c:if>/>Ｅｘｃｅｌ檔發行 
			</td>
		</tr>
	</table>
	<ul class="mt15">
		<c:if test="${sub.issueType eq 'specific'}">
			<li>* 搜尋發行對象後可發行 Whoopy幣。</li>
			<li>* 發行對象以T商店會員為標準，可透過ID搜尋。</li>
			<li>* 一次只能支付於一名會員，日期不可小於今日日期。</li>
			<li>* 輸入Whoopy幣額後，點選［確認］及可完成支付。</li>
		</c:if>
		<c:if test="${sub.issueType eq 'excelRegi'}">
			<li>* 可製作發行對象Ｅｘｃｅｌ檔後發行Whoopy幣。</li>
			<li>* 發行對象以T商店會員為標準，可透過ID搜尋。</li>
			<li>* 一次能支付於多名會員，日期不可小於今日日期。</li>
			<li>* 輸入Whoopy幣額後，點選［確認］及可完成支付。</li>
		</c:if>
	</ul>
			
	<table class="tabletype02 mt20">
		<colgroup>
			<col style="width:20%;">
			<col style="width:80%;">
		</colgroup>
		<tbody>
		<tr>
			<th>發行內容</th>
			<td class="text_l">
			<c:if test="${sub.resultType eq 'all'}">
				<input type="text" id="reqCardNm" name="sub.cardNm" class="txt" style="width:80%;" />
			</c:if>	
			<c:if test="${sub.resultType != 'all'}">
				${sub.cardNm } 
			</c:if>
			</td>
		</tr>
		<tr>
			<th>發行對象</th>
			<td class="text_l align_td">
				<c:if test="${ sub.resultType eq 'all'}">
					<c:if test="${sub.issueType eq 'specific'}">
						<a class="btn" href="#" id="targetBtn"><span>搜尋發行對象</span></a>
						<label id="testLabel" />
					</c:if>
					<c:if test="${sub.issueType eq 'excelRegi'}">
						<input type="file" id="comp_file" name="comp_file" onchange="javascript:doUpfile('3');"><span>請上傳 xls</span>
					</c:if>
				</c:if>
				<c:if test="${sub.resultType eq 'specific'}">
					${giftCard.mbrId } 
				</c:if>
				<c:if test="${sub.resultType eq 'excel'}">
					總計  ${sub.cardCnt } | <strong style="color:blue"> ${giftCard.regCnt } 成功 </strong> / <strong style="color:red"> ${giftCard.rowNum } 失敗</strong>
					&nbsp;&nbsp;<a class="btn" href="#" id="excelBtn"><span>下載Excel 檔</span>
				</c:if>
			</td>
		</tr>
		<tr>
			<th>Cash</th>
			<td class="text_l">
				<c:if test="${sub.resultType == 'all'}">
					<input type="text" id="presentCashAmt" name="sub.presentCashAmt" type="text"/>&nbsp;P
				</c:if>
				<c:if test="${sub.resultType != 'all'}">
					<g:html format="R###,###,###">${ sub.presentCashAmt }</g:html>&nbsp;P
				</c:if>
				<span class="ml30">* 加值限額為１０００Ｐ～２，０００，０００Ｐ。</span>
			</td>
		</tr>
		<tr>
			<th>發行日期</th>
			<td class="text_l">
				${ sub.endDate}
			</td>
		</tr>
		</tbody>
	</table>
	
	
	<p class="btn_wrap text_r mt25">
		<c:if test="${sub.resultType eq 'all'}">
			<a class="btn" href="#" id="authDwldConfirmBtn"><span>儲存</span></a>
			<a class="btn" href="#" onclick="javascript:gotoList('cancel');"><span>目錄</span></a></p>
		</c:if>
		<c:if test="${sub.resultType != 'all'}">
			<a class="btn" href="#" onclick="javascript:gotoList('confirm');"><span>確定</span></a></p>
		</c:if>
	</div>
	</s:form>
</body>
</html>	
