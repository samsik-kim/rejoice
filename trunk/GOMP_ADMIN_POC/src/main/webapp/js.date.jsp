<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title></title>
<!-- META -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Expires" content="-1"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache">
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="<c:url value="/js/jquery/ui/css/redmond/jquery-ui-1.8.10.custom.css"/>">
<link rel="stylesheet" href="<c:url value="/css/base.css"/>" type="text/css">
<link rel="stylesheet" href="<c:url value="/css/common.css"/>" type="text/css">
<!-- JAVASCRIPT -->
<script type="text/javascript" src="<c:url value="/js/jquery/1.5.1/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery-ui-1.8.10.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.bgiframe-2.1.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/ui/jquery-ui-i18n.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.cookie.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.metadata.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/jquery/plugin/jquery.MultiFile.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/common/common.js"/>"></script>
<script type="text/javascript">
//<![CDATA[
	$(function() {
		// datepicker localizations
		$.datepicker.setDefaults($.datepicker.regional['ko']);
		// $.datepicker.setDefaults($.datepicker.regional['zh-TW']);
		$.datepicker.setDefaults({dateFormat: 'yy-mm-dd'});
		// 객체 생성과 동시에 이미지 버튼 설정
		$.datepicker.setDefaults({showOn:'both', buttonImage:'${pageContext.request.contextPath}/images/board/icon_cal.gif', buttonImageOnly:true});
	});
//]]>
</script>
<!-- ============ 여기까지 sitemesh 에서 해줌  ============ -->


<!-- 각 페이지에서 해야 할 부분 START -->
<script type="text/javascript">
//<![CDATA[
	$(function() {
		$( "#datepicker" ).datepicker();
	});
//]]>
</script>
<!-- 각 페이지에서 해야 할 부분 END -->
</head>
<body>
<p>Date: <input type="text" id="datepicker" style="width:120px" /></p>
</body>
</html>