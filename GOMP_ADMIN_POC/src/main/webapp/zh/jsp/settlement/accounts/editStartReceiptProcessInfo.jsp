<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/common.js"></script>
<script type="text/javascript">
	
	$(document).ready(function(){
	
		//신청여부 체크
		$("input[id='accountsSS\\.aggtStatCd'][value='${accounts.aggtStatCd}']").attr("checked", "checked"); 
		
	});
	
	$(function() {
		var pForm = document.processForm;
		
		$("#listBtn").click(function(event){
			event.preventDefault();
			pForm.target = '_self';
			pForm.action="<c:url value="/settlement/accounts/receiptProcessList.omp" />";
			pForm.submit();
			
		});
		
		$("#updateBtn").click(function(event){
			event.preventDefault();
			/*
			if ($("input[id='accountsSS\\.aggtStatCd']:checked").length==0){
				alert("<gm:string value='jsp.settlement.accounts.editStartReceiptProcessInfo.application'/>");
				return;
			//}else if (($("input[id='accountsSS\\.aggtStatCd']").filter("input[value='PD004107']").attr("checked")==true) && ($.trim($("#accountsSS\\.rmtReqReason").val())=="")){
			}else if (($("input[id='accountsSS\\.aggtStatCd']").val() =='PD004107') && ($.trim($("#accountsSS\\.rmtReqReason").val())=="")){	
				alert("<gm:string value='jsp.settlement.accounts.editStartReceiptProcessInfo.trnsfer'/>");
				return;
			}else{
				if (confirm("<gm:string value='jsp.settlement.accounts.editStartReceiptProcessInfo.receiptrecord'/>")){
						pForm.action="<c:url value="/settlement/accounts/editEndReceiptProcessInfo.omp" />";
						pForm.target = '_self';
						pForm.submit();
				}
			}*/
			
			
			if(showValidate('insertForm', 'default', "<gm:string value=""/>")){
				if (confirm("<gm:string value='jsp.settlement.accounts.editStartReceiptProcessInfo.receiptrecord'/>")){
					pForm.action="<c:url value="/settlement/accounts/editEndReceiptProcessInfo.omp" />";
					pForm.target = '_self';
					pForm.submit();
				}
			}
			
		});
		
		$("#adjPopUp").click(function(){
			var targetUrl = "<c:url value="/settlement/accounts/popUpAdjAmt.omp"/>";
			popup_center('', 500, 300, 0, 0);
			pForm.target = 'popup';
			pForm.action = targetUrl;
			pForm.submit();
		});
			
	});
	
	
	function setAdjAmt(){
		var adjAmt = eval($("#accountsSS\\.adjAmt").val());
		$("#adjAmtText").text(digitsComma(String(adjAmt))); //조정액 TEXT 수정
		 
	}
	
	
	function digitsComma(numStr)
	{
		var pmCheck="";
		var s=numStr;

		if(s.charAt(0)=="-"){ // +,- 기호처리
			pmCheck = s.charAt(0);
			s = s.substring(1);
		}
		
		var t="";
	    var i;
	    var j=0;
	    var tLen =s.length;

	    if (s.length <= 3 ) {
	        return pmCheck+s;
	    }else{
	    	for(i=0;i<tLen;i++){
		       if (i!=0 && ( i % 3 == tLen % 3) )     t += ",";
		       if(i < s.length ) t += s.charAt(i);
		    }
		    return pmCheck+t;	
	    }
	}
	
</script>	

</head>
<body >

			<div id="location">
				首頁 &gt; 結算管理中心 &gt; 結算現狀 &gt; <strong>發票處理作業</strong> 
			</div><!-- //location -->
			<div class="overflow_h">
				<h1 class="fl pr10">發票處理作業</h1>
				<p>可查看發票處理現狀.</p>
			</div>
			<p class="mt20">▶ 發票詳細記錄</p>
			<s:form id="processForm" name="processForm"  theme="simple" >
			<input type="hidden" id="accountsS.searchTimeBlock" name="accountsS.searchTimeBlock" value="${accountsS.searchTimeBlock}"/>
			<input type="hidden" id="accountsS.saleYm" name="accountsS.saleYm" value="${accountsS.saleYm}"/>
			<input type="hidden" id="accountsS.searchType" name="accountsS.searchType" value="${accountsS.searchType}"/>
			<input type="hidden" id="accountsS.searchCont" name="accountsS.searchCont" value="${accountsS.searchCont}"/>
			<input type="hidden" id="accountsS.mbrId" name="accountsS.mbrId" value="${accountsS.mbrId}" > 
			<input type="hidden" id="accountsS.mbrNm" name="accountsS.mbrNm" value="${accountsS.mbrNm}" > 
			<input type="hidden" id="accountsS.page.no" name="accountsS.page.no" value="${accountsS.page.no}" />
			<input type="hidden" id="accountsS.firstAccessYN" name="accountsS.firstAccessYN" value="${accountsS.firstAccessYN}">
			<input type="hidden" id="accountsSS.saleYm" name="accountsSS.saleYm" value="${accountsSS.saleYm}"/>
			<input type="hidden" id="accountsSS.mbrNo" name="accountsSS.mbrNo" value="${accountsSS.mbrNo}"/>
			<input type="hidden" id="accountsSS.adjYn" name="accountsSS.adjYn" value="${accountsSS.adjYn}"/>
			<input type="hidden" id="accountsSS.adjAmt" name="accountsSS.adjAmt" value="<g:text value="${accounts.adjAmt}" format="############.##" />"/><!-- 조정액 -->
			<input type="hidden" id="accountsSS.adjAmtEditYN" name="accountsSS.adjAmtEditYN" value="N"/><!-- 조정액 수정여부-->
			<input type="hidden" id="accountsSS.adjReason" name="accountsSS.adjReason" value="<g:tagAttb value="${accounts.adjReason}"/>"/><!-- 개발자 조정사유 -->
			<input type="hidden" id="accountsSS.currencyUnitName" name="accountsSS.currencyUnitName" value="${accounts.currencyUnitName}"/><!-- 통화단위코드명 -->
			
			<table class="tabletype01">
				<colgroup><col style="width:88px;" ><col style="width:80px;" /></colgroup>
				<tr>
					<th colspan="2">開發商</th>
					<td>
						${accounts.mbrNm}(${accounts.mbrId})
					</td>
				</tr>
				<tr>
					<th colspan="2">會員類別 / 會員等級</th>
					<td>
						<gc:html code="${accounts.mbrClsCd}"/> / <gc:html code="${accounts.mbrGrCd}"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">結算類別</th>
					<td>
						結算
					</td>
				</tr>
				<tr>
					<th colspan="2">交易月份</th>
					<td>
						<g:text value="${accounts.saleYm}" format="L####-##" />
					</td>
				</tr>
				<tr>
					<th rowspan="3">拆帳總金額(A)</th>
					<th>銷售總金額</th>
					<td>
						<g:text value="${accounts.totlPayAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th>未稅總金額</th>
					<td>
						<g:text value="${accounts.beforeTaxAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th>總計</th>
					<td>
						<g:text value="${accounts.shareAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th colspan="2">稅額(B)</th>
					<td>
						<g:text value="${accounts.txtAmt}" format="###,###,###,###.##" />
					</td>
				</tr>
				<tr>
					<th colspan="2">實付金額(C=A+B)</th>
					<td>
						<g:text value="${accounts.devBuDvAmtSum}" format="###,###,###,###.##" />
					</td>
				</tr>
				<!-- <tr>
					<th colspan="2">실정산액(E=C+(-D))</th>
					<td>
						00,000
					</td>
				</tr> -->
				<tr>
					<th colspan="2">付款幣別</th>
					<td>
						<gc:html code="${accounts.currencyUnit}"/>
					</td>
				</tr>
				<tr>
					<th colspan="2">匯款金額</th>
					<td>
						<font id="sendAmtSumText" ><g:text value="${accounts.devBuDvAmtSum}" format="###,###,###,###.##" /></font>
					</td>
				</tr>
				<tr>
					<th colspan="2">調帳金額</th>
					<td>
						 <p class="fr pt05"><g:html value="${accounts.adjReasonInsBy}"/></p> <font id="adjAmtText" ><g:text value="${accounts.adjAmt}" format="###,###,###,###.##" /></font> <a href="#" class="btn_s" id="adjPopUp"><span>詳細內容</span></a> 
						
					</td>
				</tr>
				<tr>
					<th colspan="2">申請與否</th>
					<td>
						<input type="radio" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004102"  /> 憑證未送達
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004103"  /> 等待憑證
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004107"  /> 次月處理
						<input type="radio" class="ml05" id="accountsSS.aggtStatCd" name="accountsSS.aggtStatCd" value="PD004104"  /> 資料無誤
					</td>
				</tr>
				<tr>
					<th colspan="2">次月處理緣由</th>
					<td>
						<textarea cols="67" rows="8" id="accountsSS.rmtReason" name="accountsSS.rmtReqReason" ><g:tagAttb value="${accounts.rmtReqReason}"/></textarea>
					</td>
				</tr>
			</table>
			</s:form>

			<p class="btn_wrap text_c mt20"><a class="btn" href="#" ><span id="updateBtn">儲存</span></a> <a class="btn" href="#" ><span id="listBtn">目錄</span></a></p>
		
	<hr>
</body>
</html>
