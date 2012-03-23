<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>서정 가든</title>
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
<link href="<c:url value="/resource/css/content.css"/>" rel="stylesheet" type="text/css" />
<link type="text/css" href="/resource/jquery-ui-1.8.17.custom/css/cupertino/jquery-ui-1.8.17.custom.css" rel="stylesheet" />	
<script type="text/javascript" src="/resource/jquery-ui-1.8.17.custom/js/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="/resource/jquery-ui-1.8.17.custom/js/jquery-ui-1.8.17.custom.min.js"></script>

<decorator:head />
</head>
<body>
<div id="wrap"> 
	<!-- Logo & Usermenu -->
	<div id="header">			
		<!-- top gnb -->
		<page:applyDecorator page="/WEB-INF/jsp/decorators/common/include/gnb.jsp" name="empty" />  
	</div>
	<hr />
		<!-- Container S-->
		<div id="container">
		
			<!-- SubMenu S -->
			<div id="left_area">
				<h2 class="hide">서브메뉴영역</h2>
				<page:applyDecorator page="/WEB-INF/jsp/decorators/common/include/leftMenu.jsp" name="empty" />
			</div>
			<hr />
			<!-- //SubMenu E -->
	
			<!-- Content Area S -->
			<div id="contents">
			<h2 class="hide">본문영역</h2>
			<decorator:body />
			</div>
			<!-- Content Area E -->
			
		</div>
		<hr />
		<!-- //Container E-->

	<h2 class="hide">카피라이터영역</h2>
	<div id="footer_wrap">
		<page:applyDecorator page="/WEB-INF/jsp/decorators/common/include/footer.jsp" name="empty" />
	</div>
	<hr />
</div>

<div id="bodyFrame"></div>
<div id="layerArea">
</div>
</body>
</html>