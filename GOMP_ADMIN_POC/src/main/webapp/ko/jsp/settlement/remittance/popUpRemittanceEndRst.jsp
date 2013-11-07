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

$(document).ready(function(){

	//alert("상태코드 : ${remittanceS.adjStatCd}");
		
	//처리결과 메세지가 존재하면 화면에 출력을 해준다.
	<c:if test="${not empty remittanceS.processMessage   }">
		alert("<gm:string value='${receiptS.processMessage}'/>");		
	</c:if>
		
		
			
	});

$(function() {
	//일괄 송금결과마감 처리
	$("#closeBtn").click(function(event){
		opener.goPage("${remittanceS.page.no}");
		//$(opener.document).find('#searchBtn').click(function(event){});
		self.close();
	});
});	
</script>
</head>
<body class="popup">
	<div id="popup_area_440">
		<p><g:text value="${remittance.rmtReqYyyymm}" format="L####년##월" /> 내역은 이미 송금 결과 마감 되었습니다.</p>
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
			<a class="btn_s" href="#" id="closeBtn"><strong>확인</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>
