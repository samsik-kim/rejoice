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
<title>結束匯款作業</title>
<script type="text/javascript" src="../inc/_pophead.js"></script>
<script language="JavaScript">
//일괄 송금결과마감 처리
$(function() {
	$("#bundleProcess").click(function(event){
		
		var frm = document.updateForm;
		event.preventDefault();
		
		/*
		if (confirm("<gm:string value='jsp.settlement.remittance.popUpRemittanceEnd.sendend'/>")){
			frm.action="<c:url value="/settlement/remittance/popUpUpdateRemittanceRstBundleEnd.omp"/>";
			frm.submit();	
		}*/
		
		var width = 460;
		var height = 180;
	    var x = (screen.width) ? (screen.width-width)/2 : 0;
	    var y = (screen.height) ? (screen.height-height)/2 : 0;
		 
		var scrollOption = "No";
		
		window.open("","popup1", "left=" + x + ",top=" + y + ",width=" + width + ",height=" + height + ",ScrollBars="+scrollOption+",status=yes,menubar=no");		

		frm.action="<c:url value="/settlement/remittance/popConfirmRemittance.omp"/>";
		frm.target="popup1";
		frm.submit();
		
	});
});
</script>
</head>
<body class="popup">
	<s:form id="updateForm" name="updateForm"  theme="simple" >
		<input type="hidden" id="updateForm.remittanceS.searchTimeBlock" name="remittanceS.searchTimeBlock" value="${remittanceS.searchTimeBlock}"/>
		<input type="hidden" id="updateForm.remittanceS.rmtReqYyyymm" name="remittanceS.rmtReqYyyymm" value="${remittanceS.rmtReqYyyymm}"/>
		<input type="hidden" id="updateForm.remittanceS.rmtEndCd" name="remittanceS.rmtEndCd" value="${remittanceS.rmtEndCd}">
		<input type="hidden" id="updateForm.remittanceS.searchType" name="remittanceS.searchType" value="${remittanceS.searchType}"/>
		<input type="hidden" id="updateForm.remittanceS.searchCont" name="remittanceS.searchCont" value="${remittanceS.searchCont}"/>
		<input type="hidden" id="updateForm.remittanceS.mbrId" name="remittanceS.mbrId" value="${remittanceS.mbrId}" > 
		<input type="hidden" id="updateForm.remittanceS.mbrNm" name="remittanceS.mbrNm" value="${remittanceS.mbrNm}" > 
		<input type="hidden" id="updateForm.remittanceS.page.no" name="remittanceS.page.no" value="${remittanceS.page.no}" />
		<input type="hidden" id="updateForm.remittanceS.firstAccessYN" name="remittanceS.firstAccessYN" value="${remittanceS.firstAccessYN}">
		<input type="hidden" id="updateForm.remittanceSS.rmtReqYyyymm" name="remittanceSS.rmtReqYyyymm" value="${remittanceSS.rmtReqYyyymm}"/>
	</s:form>
	<div id="popup_area_440">
		<p>確定要結束<g:text value="${remittance.rmtReqYyyymm}" format="L####年##月" />份之匯款作業嗎?</p>
    	  	<table class="pop_tabletype01 mt10">
        	<colgroup>
            	<col style="width:25%;">
                <col style="width:25%;">
            </colgroup>
        	<tbody>
            	<tr>
                	<th>匯款月份</th>
                    <td>
                    	<g:text value="${remittance.rmtReqYyyymm}" format="L####-##" />
                    </td>
                </tr>
                <tr>
                	<th>再次匯款</th>
                    <td>
                    	<g:text value="${remittance.reRmtCnt}" format="###,###,###,###,###" />筆
                    </td>
                </tr>
                <tr>
                	<th>匯款失敗</th>
                    <td>
                    	<g:text value="${remittance.giveupCnt}" format="###,###,###,###,###" />筆
                    </td>
                </tr>
                <tr>
                	<th>待款</th>
                    <td>
                    	<g:text value="${remittance.waitRmtCnt}" format="###,###,###,###,###" />筆
                    </td>
                </tr>
            </tbody>
        </table>
		<p class="pt05">※ 若結束當月匯款作業, 「待款」狀態之款項批量變更為「匯款完筆」.<br />&nbsp; &nbsp; 匯款結束前, 請再次確認.</p>		
		<div class="pop_btn" >
			<a class="btn_s" href="#" id="bundleProcess"><strong>確定</strong></a> <a class="btn_s" href="JavaScript:self.close()"><strong>取消</strong></a>
		</div>
	</div><!-- //contents -->
   </body>
</html>
