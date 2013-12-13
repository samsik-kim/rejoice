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


$(function() {
	//일괄 송금결과마감 처리
	$("#closeBtn").click(function(event){
		//opener.goPage("${remittanceS.page.no}");
		//$(opener.document).find('#searchBtn').click(function(event){});
		self.close();
	});
});	
</script>
</head>
<body class="popup">
	<div id="popup_area_440">
		<p><g:text value="${remittance.rmtReqYyyymm}" format="L####年##月" />份之匯款作業已結束.</p>
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
                	<th>匯款完畢</th>
                    <td>
                    	<g:text value="${remittance.totlRmtCnt}" format="###,###,###,###,###" />筆
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
                	<th>處理記錄</th>
                    <td>
                    	<g:text value="${remittance.rntRstEndInsBy}" />
                    </td>
                </tr>
            </tbody>
        </table>
				
		<div class="pop_btn" >
			<a class="btn_s" href="#" id="closeBtn"><strong>確定</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>
