<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Test 반려</title>
<script type="text/javascript" src="<c:url value="/js/inc/_pophead.js"/>"></script>
<script type="text/javascript">
//<![CDATA[
           
	$(function() {
		
		jQuery.fn.selfPage = {
				
			    /**
			     * Save Test Ing
			     *
			     * @param cid
			     * @param verifyReqVer
			     * @param status
			     */  
			    saveRejectReson : function (){
			        
			    	var param = $("#saveForm").serialize();
			        
			        $.ajax({
			            type: "POST",
			            async : false,
			            url: env.contextPath + "/certifymgr/saveAgreeStatusByAjax.omp",
			            data : param,
			            dataType:  "json",
			            success: function(transport){
						    try{
								alert(transport.responseMessage);
								
								if(transport.responseCode == 'SUCC'){
									window.close();
									window.opener.jQuery("#appListBtn").click();
								}								
							}catch(e){
							}
			            },
			            error: function(xhr, textStatus, errorThrown){}
			        });                     
			    }	    			    
		};		
		
		$("#saveRejectReason").click(function(event){
			event.preventDefault();
			$.fn.selfPage.saveRejectReson();
		});						
		
		$("#closeBtn").click(function(event){
			event.preventDefault();
			window.close();
		});				
		
	});
//]]>
</script>
</head>
<body class="popup">
 <form id="saveForm" name="saveForm" method="post"> 
	<input type="hidden" name="saveTestStatus.cid" value="${saveTestStatus.cid }">
	<input type="hidden" name="saveTestStatus.verifyReqVer" value="${saveTestStatus.verifyReqVer }">
	<input type="hidden" name="saveTestStatus.status" value="${CONST.CODE_VERIFY_TEST_REJECT}">
	<div id="popup_area_440">
		<h1>반려 사유</h1>
		<textarea cols="47" rows="5" id="testRejectCmt" name="saveTestStatus.testRejectCmt"></textarea>
		<div class="pop_btn" >
			<a class="btn_s" href="#" id="saveRejectReason"><strong>확인</strong></a>
			<a class="btn_s" href="#" id="closeBtn"><strong>닫기</strong></a>
		</div>
	</div><!-- //contents -->
</form>	
</body>
</html>

