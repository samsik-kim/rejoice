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
<title>송금결과마감</title>
<script type="text/javascript" src="../inc/_pophead.js"></script>
<script language="JavaScript">
//일괄 송금결과마감 처리
$(function() {
	$("#bundleProcess").click(function(event){
		
		var frm = document.updateForm;
		event.preventDefault();
		
		
		if (confirm("<gm:string value='jsp.settlement.remittance.popUpRemittanceEnd.sendend'/>")){
			frm.action="<c:url value="/settlement/remittance/popUpUpdateRemittanceRstBundleEnd.omp"/>";
			frm.submit();	
		}
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
		<p><g:text value="${remittance.rmtReqYyyymm}" format="L####년##월" /> 내역에 대해 최종 송금 결과 마감 처리하시겠습니까?</p>
    	  	<table class="pop_tabletype01 mt10">
        	<colgroup>
            	<col style="width:25%;">
                <col >
                <col style="width:25%;">
                <col >
         	</colgroup>
        	<tbody>
            	<tr>
                	<th>송금월</th>
                    <td>
                    	<g:text value="${remittance.rmtReqYyyymm}" format="L####-##" />
                    </td>
                </tr>
                <tr>
                	<th>송금완료</th>
                    <td>
                    	<g:text value="${remittance.totlRmtCnt}" format="R###,###,###,###,###" />건
                    </td>
                </tr>
                <tr>
                	<th>재송금</th>
                    <td>
                    	<g:text value="${remittance.reRmtCnt}" format="R###,###,###,###,###" />건
                    </td>
                </tr>
                <tr>
                	<th>송금포기</th>
                    <td>
                    	<g:text value="${remittance.giveupCnt}" format="R###,###,###,###,###" />건
                    </td>
                </tr>
                <tr>
                	<th>미처리내역</th>
                    <td>
                    	<g:text value="${remittance.waitRmtCnt}" format="R###,###,###,###,###" />건
                    </td>
                </tr>
            </tbody>
        </table>
				
		<div class="pop_btn" >
			<a class="btn_s" href="#" id="bundleProcess"><strong>예</strong></a> <a class="btn_s" href="JavaScript:self.close()"><strong>아니요</strong></a>
		</div>
	</div><!-- //contents -->
   </body>
</html>
