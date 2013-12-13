<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires", 0);
	if(request.getProtocol().equals("HTTP/1.1")){
		response.addHeader("Cache-Control","no-cache");
	}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Expires" content="-1"/> 
<meta http-equiv="Pragma" content="no-cache"/> 
<meta http-equiv="Cache-Control" content="no-cache"/>

<link href="<c:url value='/${ThreadSession.serviceLocale.language}/css/content.css'/>" rel="stylesheet" type="text/css" />
<link type="text/css" href="<c:url value="/js/jquery/ui/css/smoothness/jquery-ui-1.8.10.custom.css"/>" rel="stylesheet" />
<link rel="shortcut icon" href="<c:url value='/${ThreadSession.serviceLocale.language}/images/whoopy.ico'/>" type="image/x-icon" />

<script type="text/javascript" src="<c:url value="/js/jquery/1.5.1/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery-ui-1.8.10.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/jsTree/jquery.jstree.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.cookie.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.metadata.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.MultiFile.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.selectbox-1.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/validate.js"/>"></script>


<script type="text/javascript" src="<c:url value='/js/common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/dev.common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/js/xtractor_cookie.js'/>"></script>
<script>
$(document).ready(function(){
	setContextPath("${pageContext.request.contextPath }");
});
</script>

<title><decorator:title default="Whoopy" /></title>
<decorator:head />
</head>

<body style="overflow-x:hidden;">

	<decorator:body />

</body>
</html>