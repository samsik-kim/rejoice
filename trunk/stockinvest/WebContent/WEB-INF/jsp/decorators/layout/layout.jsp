<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Stockinvest</title>
<!-- META -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Expires" content="-1"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache">
<!-- CSS -->
<style type="text/css">
	.ui-datepicker-trigger{ margin-left:3px;cursor:pointer; }
	.ui-datepicker { width:200px; }
	#blockUI{display:none;height:30px;font-weight:bold;font-size: 16px;padding-top:10px}
	form{clear:both;}
</style>


<link href="<c:url value="/resource/css/content.css"/>" rel="stylesheet" type="text/css" />
<link type="text/css" href="<c:url value="/resource/js/jquery/ui/css/cupertino/jquery-ui-1.8.10.custom.css"/>" rel="stylesheet" />
<link rel="stylesheet" href="/resource/js/jquery/plugin/tablesorter/style.css" type="text/css" id="" media="print, projection, screen" />

<script type="text/javascript" src="<c:url value="/resource/js/jquery/1.5.1/jquery.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery/ui/jquery-ui-1.8.10.custom.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery/jsTree/jquery.jstree.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery/plugin/jquery.cookie.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery/plugin/jquery.blockUI.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery/plugin/jquery.form.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery/plugin/jquery.metadata.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery/plugin/jquery.MultiFile.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery/plugin/jquery.selectbox-1.2.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery/ui/jquery-ui-i18n.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/validate.js"/>"></script>
<script type="text/javascript" src="<c:url value='/resource/js/xtractor_cookie.js'/>"></script>
<script type="text/javascript" src="<c:url value="/resource/js/jquery/plugin/jquery.tablesorter.js"/>"></script>

	
<script type="text/javascript" src="/resource/js/common.js"></script>
<script type="text/javascript" src="/resource/js/ckeditor/ckeditor.js"></script>

<script type="text/javascript">
$(function() {
	$.datepicker.regional['ko']= {
			  closeText:'닫기',
			  prevText:'이전달',
			  nextText:'다음달',
			  currentText:'오늘',
			  monthNames:['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUM)','7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
			  monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			  dayNames:['일','월','화','수','목','금','토'],
			  dayNamesShort:['일','월','화','수','목','금','토'],
			  dayNamesMin:['일','월','화','수','목','금','토'],
			  weekHeader:'Wk',
			  dateFormat:'yy-mm-dd',
			  firstDay:0,
			  isRTL:false,
			  showMonthAfterYear:true,
			  yearSuffix:''
	};
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$.datepicker.setDefaults({dateFormat: 'yy-mm-dd'});
	$.datepicker.setDefaults({showOn:'both', buttonImage:'/resource/images/common/icon_cal.gif', buttonImageOnly:true});
	
	// readonly input prevent backspace
	$(":input[readonly] ").each(function(){
	   $(this).keydown(function(event){
	    if (event.keyCode == '8') { 
	         event.preventDefault(); 
	       }
	   });
	  });
});

</script>
<decorator:head />
</head>
<body>
<div id="wrap"> 
	<!-- Logo & Usermenu -->
	<div id="header">			
		<!-- top gnb -->
		<page:applyDecorator page="/WEB-INF/jsp/decorators/common/gnb.jsp" name="empty" />  
	</div>
	<hr />
		<!-- Container S-->
		<div id="container">
		
			<!-- SubMenu S -->
			<div id="left_area">
				<h2 class="hide">서브메뉴영역</h2>
				<page:applyDecorator page="/WEB-INF/jsp/decorators/common/leftMenu.jsp" name="empty" />
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
		<page:applyDecorator page="/WEB-INF/jsp/decorators/common/footer.jsp" name="empty" />
	</div>
	<hr />
</div>

<div id="bodyFrame"></div>
<div id="layerArea">
</div>
</body>
</html>