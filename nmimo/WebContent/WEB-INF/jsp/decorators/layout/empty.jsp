<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mimo" uri="/WEB-INF/tld/mimo.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<title>MIMO</title>
<!-- META -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Expires" content="-1"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/common.css"/>" />
<link type="text/css" href="<c:url value="/resource/jquery-ui-1.10.2.custom/css/smoothness/jquery-ui-1.10.2.custom.min.css"/>" rel="stylesheet" />	
<script type="text/javascript" src="<c:url value="/resource/jquery-ui-1.10.2.custom/js/jquery-1.9.1.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/jquery-ui-1.10.2.custom/js/jquery-ui-1.10.2.custom.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/jquery-ui-1.10.2.custom/js/jquery-ui-1.10.2.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery.tablesorter.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery.metadata.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery.MultiFile.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/validate.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/common.js"/>"></script>
<decorator:head />
</head>
<body>
<decorator:body />
</body>
</html>