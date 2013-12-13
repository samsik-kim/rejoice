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
<title>調帳金額</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/stringUtil.js"></script>
<script type="text/javascript">
/*
 * Init
 */
$(document).ready(function(){ 
	
	var adjAmt = '<g:text value="${accountsSS.adjAmt}" format="############.##"/>'; //조정액
	$("#accountsSS\\.adjAmt").val(adjAmt); //조정액 셋팅
});
 
function setAdjAmt(){
	
	var adjAmt;
	adjAmt = $.trim($('#accountsSS\\.adjAmt').val());
	
	if(adjAmt==""){
		alert("<gm:string value='jsp.settlement.accounts.popUpAdjAmtView.changemoney'/>");
		return;
	}
	$('#accountsSS\\.adjAmt', opener.document).val($('#accountsSS\\.adjAmt').val());
	$('#accountsSS\\.adjAmtEditYN', opener.document).val("Y"); //조정액수정 여부 수정으로 변경
	$('#accountsSS\\.adjReason', opener.document).val($('#accountsSS\\.adjReason').val());
	opener.setAdjAmt();
	self.close();
		
}

function js_OnlyNumberR(obj)
{
	if (event.keyCode == 13){event.returnValue =false;}
	//alert(obj.value);
    //alert(event.keyCode);
	val=obj.value;
	re=/[^0-9]/gi;
	obj.value=val.replace(re,"");
}

function preventSubmit()
{
	if (event.keyCode == 13){event.returnValue =false;;}
}


</script>
</head>
<body class="popup">
	<div id="popup_area_440">
    	<table class="pop_tabletype01">
        	<colgroup>
            	<col style="width:25%;">
                <col style="width:25%;">
            </colgroup>
        	<tbody>
                <tr>
                	<th>金額(<g:text value="${accountsSS.currencyUnitName}" />)</th>
                    <td>
                    	<input type="text" class="txt" style="width:280px" id="accountsSS.adjAmt" onKeyPress="preventSubmit()" onKeyUp = "js_OnlyNumberR(this);"  />
                    </td>
                </tr>
                <tr>
                	<th>緣由</th>
                    <td>
                    	<c:set var="reason" value="${accountsSS.adjReason}" />
                    	<textarea cols="33" rows="5" id="accountsSS.adjReason" ><g:tagBody value="${reason}"/></textarea>
                    </td>
                </tr>
            </tbody>
        </table>
				※ 調帳金額將於次月匯款.
		<div class="pop_btn" >
			<a class="btn_s" href="#" id="confirmBtn" onClick="JavaScript:setAdjAmt()"><strong id="confirmBtn">確定</strong></a>
			<a class="btn_s" href="#" id="closeBtn" onClick="JavaScript:self.close()"><strong>關閉</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>

