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
<title>조정액 팝업</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/common/stringUtil.js"></script>
<script type="text/javascript">
/*
 * Init
 */
$(document).ready(function(){ 
	
	//조정액 +, - 금액에 따른  +,-및 조정액금액처리
	var adjAmt = "${accountsSS.adjAmt}"; //조정액
	
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
                <col >
                <col style="width:25%;">
                <col >
         	</colgroup>
        	<tbody>
                <tr>
                	<th>금액</th>
                    <td>
                    	<input type="text" class="txt" style="width:280px" id="accountsSS.adjAmt" onKeyPress="preventSubmit()" onKeyUp = "js_OnlyNumberR(this);" disabled />
                    </td>
                </tr>
                <tr>
                	<th>사유</th>
                    <td>
                    	<c:set var="reason" value="${accountsSS.adjReason}" />
                    	<textarea cols="33" rows="5" id="accountsSS.adjReason" readonly><g:tagBody value="${reason}"/></textarea>
                    </td>
                </tr>
            </tbody>
        </table>
				※ 조정액은 다음달에 처리됩니다.
		<div class="pop_btn" >
			<a class="btn_s" href="#" id="closeBtn" onClick="JavaScript:self.close()"><strong>닫기</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>

