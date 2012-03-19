<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
%><%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"
%><%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><decorator:title default="Whoopy Management System"/></title>
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
	body{margin:0;padding:0}
</style>
<link rel="stylesheet" href="<c:url value="/resource/css/base.css"/>" type="text/css">
<link rel="stylesheet" href="<c:url value="/resource/css/common.css"/>" type="text/css">
<link rel="stylesheet" href="<c:url value="/resource/css/popup.css"/>" type="text/css">
<!-- JAVASCRIPT -->
<script type="text/javascript" src="/resource/jquery-ui-1.8.17.custom/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.8.17.custom/js/jquery-ui-1.8.17.custom.min.js"></script>
<script type="text/javascript">
//<![CDATA[
    // set context path
    setContextPath("${pageContext.request.contextPath }");
    // expire session
	function expireSession()	{
		alert( "<gm:string value='jsp.common.session.invalidate.msg'/>" );
		try{
			var openerType = typeof opener.location.href;
			if( openerType == 'string' ){
				opener.location.href="<c:url value="/adminMgr/adminLogOut.omp"/>";
			}
		}catch(e){}
		self.close();
	}
	setTimeout( 'expireSession()', <%= request.getSession().getMaxInactiveInterval() * 1000 %> );
	
	$(function() {
		// datepicker localizations
		$.datepicker.setDefaults($.datepicker.regional['${ThreadSession.serviceLocale.language}']);
		// $.datepicker.setDefaults($.datepicker.regional['zh-TW']);
		$.datepicker.setDefaults({dateFormat: 'yy-mm-dd'});
		// datepicker input right img create
		$.datepicker.setDefaults({showOn:'both', buttonImage:'<c:url value="/${ThreadSession.serviceLocale.language}/images/board/icon_cal.gif"/>', buttonImageOnly:true});
		
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
<body class="popup">
	<decorator:body />
</body>
</html>