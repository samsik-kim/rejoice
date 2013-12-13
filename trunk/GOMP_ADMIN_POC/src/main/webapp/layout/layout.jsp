<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"
%><%@ page import="com.omp.admin.adminmgr.auth.model.AdSession"
%><%@ page import="com.omp.admin.adminmgr.auth.model.AdMgr"
%><%@ page import="com.omp.admin.adminmgr.auth.service.AdminMainService"
%><%@ page import="com.omp.admin.common.Constants"
%><%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator"
%><%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%><%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core"
%><%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"
%><%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"
%><%@ taglib prefix="s" uri="/struts-tags"
%><%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires", 0);

	if ( request.getProtocol().equals("HTTP/1.1") )	{
		response.addHeader( "Cache-Control","no-cache" );
	}

    AdSession adminSes	= (AdSession) request.getSession().getAttribute( Constants.ADMIN_AUTH_SESSION_KEY );
	String topMenuCode	= request.getParameter( "topCode" );
	String leftMenuCode	= request.getParameter( "leftCode" );

	//파라미터로 가지고 다니는게 불편하여 topCode, leftCode를 session 에 넣어둠.
	if ( !"".equals( topMenuCode ) && topMenuCode != null && !topMenuCode.equals( session.getAttribute( "topMenuCode" ) ) )	{
		session.setAttribute( "topMenuCode", topMenuCode );
	}
	if ( !"".equals( leftMenuCode ) && leftMenuCode != null && !leftMenuCode.equals( session.getAttribute( "leftMenuCode" ) ) )	{
		session.setAttribute( "leftMenuCode", leftMenuCode );
	}

	//실 사용은 session 의 값을 사용.
	topMenuCode	= (String)session.getAttribute( "topMenuCode" );
	leftMenuCode	= (String)session.getAttribute( "leftMenuCode" );

	if ( adminSes == null || topMenuCode == null || leftMenuCode == null )	{
		response.sendRedirect("${pageContext.request.contextPath}/adminMgr/adminLogin.omp");
	}
	boolean isValidPageAuth	= false; 
	if ( adminSes != null )	{
		AdminMainService service	= new AdminMainService();
		AdMgr member	= adminSes.getAdMgr();
		isValidPageAuth	= service.isAdminValidPageAuth( member.getMgrId(), leftMenuCode );
	}
%>
<s:set name="uri" value="%{@org.apache.struts2.ServletActionContext@getRequest().getRequestURI()}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Whoopy Management System</title>
<!-- META -->
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta http-equiv="Expires" content="-1"> 
<meta http-equiv="Pragma" content="no-cache"> 
<meta http-equiv="Cache-Control" content="no-cache">
<!-- CSS -->
<link rel="stylesheet" type="text/css" href="<c:url value="/js/jquery/ui/css/redmond/jquery-ui-1.8.10.custom.css"/>" >
<style type="text/css">
	.ui-datepicker-trigger{ margin-left:3px;cursor:pointer; }
	.ui-datepicker { width:200px; }
	#blockUI{display:none;height:30px;font-weight:bold;font-size: 16px;padding-top:10px}
	form{clear:both;}
</style>
<link rel="stylesheet" href="<c:url value="/${ThreadSession.serviceLocale.language}/css/base.css"/>" type="text/css">
<link rel="stylesheet" href="<c:url value="/${ThreadSession.serviceLocale.language}/css/common.css"/>" type="text/css">
<link rel="shortcut icon" href="${pageContext.request.contextPath}/${ThreadSession.serviceLocale.language}/images/whoopy.ico" type="image/x-icon" />
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
<script type="text/javascript" src="<c:url value="/js/validate.js"/>"></script>
<script type="text/javascript">
//<![CDATA[
    // set context path
    setContextPath("${pageContext.request.contextPath }");
    // expire session
	function expireSession()	{
		alert( "<gm:string value='jsp.common.session.invalidate.msg'/>" );
		window.location	= "<c:url value="/adminMgr/adminLogin.omp"/>";
		// self.location.replace("<c:url value="/adminMgr/adminLogin.omp"/>");
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
<body>
<%	if ( adminSes == null || topMenuCode == null || leftMenuCode == null )	{	%>
		<s:action namespace="/adminMgr" name="adminLogin"  executeResult="true" flush="false" />
<%	}	else	{	%>
	<%	if ( isValidPageAuth == false ) {	%>
		<script type="text/javascript">
			alert('Permission denied');
			self.location.href="<c:url value="/adminMgr/adminLogOut.omp"/>";
		</script>
	<%	}	%>
	<div id="header">
		<page:applyDecorator  page="/${ThreadSession.serviceLocale.language}/jsp/common/include/gnb.jsp" name="empty" />
	</div><!-- //header -->
	<c:choose>
	<c:when test="${fn:contains(uri, '/adminMgr/loginMain.omp')}">
	<div id="sitemap_area">
		<decorator:body />
	</div><!-- //contents -->
	</c:when>
	<c:otherwise>
	<div id="contents_area">
		<div id="left_area">
			<page:applyDecorator  page="/${ThreadSession.serviceLocale.language}/jsp/common/include/leftMenu.jsp" name="empty" />
		</div><!-- //left_area -->
		<hr>
		<div class="contents">
		<decorator:body />
		</div>
	</div><!-- //contents -->
	</c:otherwise>
	</c:choose>
	<hr>
	<div id="footer_area">
		<page:applyDecorator page="/${ThreadSession.serviceLocale.language}/jsp/common/include/footer.jsp" name="empty" />
	</div>
	<div id="blockUI">Please Wait.</div>
<%	} %>
</body>
</html>