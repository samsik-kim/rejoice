<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Test System</title>
<!-- META -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Expires" content="-1"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache">
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/jquery-ui-1.8.17.custom/css/cupertino/jquery-ui-1.8.17.custom.css"/>" >
<style type="text/css">
	.ui-datepicker-trigger{ margin-left:3px;cursor:pointer; }
	.ui-datepicker { width:200px; }
	#blockUI{display:none;height:30px;font-weight:bold;font-size: 16px;padding-top:10px}
	form{clear:both;}
</style>
<link rel="stylesheet" href="<c:url value="/resource/css/base.css"/>" type="text/css">
<link rel="stylesheet" href="<c:url value="/resource/css/common.css"/>" type="text/css">
<link type="text/css" href="/resource/jquery-ui-1.8.17.custom/css/cupertino/jquery-ui-1.8.17.custom.css" rel="stylesheet" />	
<script type="text/javascript" src="/resource/jquery-ui-1.8.17.custom/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.8.17.custom/js/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript">
//<![CDATA[
    // set context path
//     setContextPath("${pageContext.request.contextPath }");
	
	$(function() {
		// datepicker localizations
		$.datepicker.setDefaults($.datepicker.regional['ko']);
		$.datepicker.setDefaults({dateFormat: 'yy-mm-dd'});
		// datepicker input right img create
		$.datepicker.setDefaults({showOn:'both', buttonImage:'<c:url value="/images/board/icon_cal.gif"/>', buttonImageOnly:true});
		
		// readonly input prevent backspace
		$(":input[readonly] ").each(function(){
		   $(this).keydown(function(event){
		    if (event.keyCode == '8') { 
		         event.preventDefault(); 
		       }
		   });
		  });
	});
//]]>
</script>
<decorator:head />
</head>
<body>

	<div id="header">
		<page:applyDecorator  page="/decorators/common/include/gnb.jsp" name="empty" />
	</div><!-- //header -->
	<div id="contents_area">
		<div id="left_area">
			<page:applyDecorator  page="/decorators/common/include/leftMenu.jsp" name="empty" />
		</div><!-- //left_area -->
		<hr>
		<div class="contents">
		<decorator:body />
		</div>
	</div><!-- //contents -->
	<hr>
	<div id="footer_area">
		<page:applyDecorator page="/decorators/common/include/footer.jsp" name="empty" />
	</div>
	<div id="blockUI">Please Wait.</div>
</body>
</html>