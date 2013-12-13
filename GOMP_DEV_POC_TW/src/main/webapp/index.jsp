<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
	response.setHeader("Cache-Control","no-store");
	response.setHeader("Pragma","no-cache");
	response.setDateHeader("Expires", 0);
	if(request.getProtocol().equals("HTTP/1.1")){
		response.addHeader("Cache-Control","no-cache");
	}
	String url = request.getContextPath() + "/main/main.omp";  
	response.sendRedirect(url);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Expires" content="-1"/> 
<meta http-equiv="Pragma" content="no-cache"/> 
<meta http-equiv="Cache-Control" content="no-cache"/>
</head>

<body>
<div>
	<a href="<%= request.getContextPath() %>/notice/listNotice.omp">1.Notice</a><br/>
	<a href="<%= request.getContextPath() %>/download/listDownload.omp?ctgr=DWN10">2.Download</a><br/>
	<a href="<%= request.getContextPath() %>/faq/listFaq.omp">3.Faq</a><br/><br/>
</div>
${DEVCONF['properties.ext.files']}
</body>
</html>
