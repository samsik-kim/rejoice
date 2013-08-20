<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="mimo" uri="/WEB-INF/tld/mimo.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<mimo:userinfo var="user_session" />

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
<script type="text/javascript">

$(function() {
	var menuId = "${menuId}";
	var subMenuId = "${subMenuId}";
	var id = menuId.substr(menuId.length-1);
	var subId = subMenuId.substr(subMenuId.length-2);

	if(id >= 0 && subId >=0){
		document.getElementById("qh_con" + id).style.display = "block";	
		document.getElementById("mycon" + subId).className = "menu_current";
	}
	
});

//mouseover 이벤트
function qiehuan(num) {

	//Top Menu Size
	var max=6;
	
	for ( var i = 0; i <= max; i++) {
		
		if (i == num){
			if("${menuId}"=="mynav" + i){
				document.getElementById("qh_con" + i).style.display = "block";
				document.getElementById("mynav" + i).className = "nav_on";
			}else{
				document.getElementById("qh_con" + i).style.display = "none";
				document.getElementById("mynav" + i).className = "nav_on";
			}
		}else{
			
			if("${menuId}"=="mynav" + i){
				document.getElementById("qh_con" + i).style.display = "block";
				document.getElementById("mynav" + i).className = "nav_on";
			}else{
				document.getElementById("qh_con" + i).style.display = "none";
				document.getElementById("mynav" + i).className = "";
			}
		}
	}
}

//mouseout 이벤트
function qiehuanOut(id){
	if("${menuId}"!="mynav" + id){
		document.getElementById("qh_con" + id).style.display = "none";
		document.getElementById("mynav" + id).className = "nav_off";
	}
}

/*
* 화면이동
*/
function doPost(url, topmenu, submenu){
// 	alert(url + "\n" + topmenu + "\n" + submenu);
	
	document.menuFrm.menuId.value = topmenu;	
	document.menuFrm.subMenuId.value = submenu;	
	document.menuFrm.action = url;
	document.menuFrm.submit();
}


//페이지 이동 및 상세보기(Key값 처리)
function doPostDetail(key,url){
// 	alert("key /"+key+" url /"+url);
	
	var topmenu = document.menuFrm.menuId.value;
	topmenu = topmenu.substr(topmenu.length-1);
	
	var submenu = document.menuFrm.subMenuId.value; 
	submenu = submenu.substr(submenu.length-2);
	
	document.menuFrm.menuId.value = topmenu;
	document.menuFrm.subMenuId.value = submenu;
	document.menuFrm.postKey.value = key;
	document.menuFrm.action = url;
	document.menuFrm.submit();
}


//페이지 이동시 사용
function doPostUrl(url){
//  	alert("url /"+url);
	
	var topmenu = document.menuFrm.menuId.value;
	topmenu = topmenu.substr(topmenu.length-1);
	
	var submenu = document.menuFrm.subMenuId.value; 
	submenu = submenu.substr(submenu.length-2);
	
	document.menuFrm.menuId.value = topmenu;
	document.menuFrm.subMenuId.value = submenu;
	document.menuFrm.action = url;
	document.menuFrm.submit();
}

</script>


<decorator:head />   
</head>
<body>
<form name="menuFrm" method="post">
	<!-- Hidden Value -->
	<input type="hidden" name="menuId" value="${menuId}"/>
	<input type="hidden" name="subMenuId" value="${subMenuId}"/>
	<input type="hidden" name="postKey" />
</form>
<div id="wrap"> 
	<!-- Logo & Usermenu -->
<div id="headerWrap">
	<div id="header">
		<!-- top gnb -->
		<c:if test="${user_session.authority eq 'CC1'}"><page:applyDecorator name="header.CC1" /></c:if>
		<c:if test="${user_session.authority eq 'CC2'}"><page:applyDecorator name="header.CC2" /></c:if>
		<c:if test="${user_session.authority eq 'CC3'}"><page:applyDecorator name="header.CC3" /></c:if>
		<c:if test="${user_session.authority eq 'MA'}"><page:applyDecorator name="header.MA" /></c:if>
		<c:if test="${user_session.authority eq 'DV'}"><page:applyDecorator name="header.DV" /></c:if>
		<c:if test="${user_session.authority eq 'CU'}"><page:applyDecorator name="header.CU" /></c:if>
		<c:if test="${user_session.authority eq 'AD'}"><page:applyDecorator name="header.AD" /></c:if>
		<c:if test="${user_session.authority eq 'SC'}"><page:applyDecorator name="header.SC" /></c:if>
		<c:if test="${user_session.authority eq 'KT'}"><page:applyDecorator name="header.KT" /></c:if>
	</div>
</div>

	<hr />
		<!-- Container S-->
		<div id="container">
			<!-- Content Area S -->
			<div id="contents">
				<decorator:body />
			</div>
			<!-- Content Area E -->
		</div>
		<!-- //Container E-->
	<hr />

	<hr />
</div>
	<div id="footer">
		<page:applyDecorator name="footer" />
	</div>
<div id="bodyFrame"></div>
<div id="layerArea">
</div>
</body>
</html>