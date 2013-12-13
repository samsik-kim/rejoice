<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ page import="java.util.*"
%><%@ page import="javax.servlet.http.*"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core"
%><%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"
%><%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"
%><%!
	private static Random	RND	= new Random(System.currentTimeMillis());
%><%
	HttpSession	hses;
	String		sv;

	hses	= request.getSession();
	sv		= (String)hses.getAttribute("TEST_SESSION_VALUE");
	if (sv == null) {
		request.setAttribute("isNewSession", "yes");
		sv	= new Date().toString();
		hses.setAttribute("TEST_SESSION_VALUE", sv);
	}
	request.setAttribute("sv", sv);
	if ("yes".equals(request.getParameter("error"))) {
		throw new RuntimeException("테스트 에러");
	}
%>
<html>
<head>
</head>
<body>
<c:if test="${isNewSession eq 'yes'}">
<font color="red">New Session</font><br/>
</c:if>
ID : ${CONF['omp.common.id.unique']}<br/>
SV : ${sv}<br/>
TP : ${param.tparam}<br/>

<a href="sessiontest.jsp?tparam=<%=RND.nextInt(100)%>">테스트 페이지 계속</a><br/>
<a href="sessiontest.jsp?error=yes">테스트 에러 페이지로</a><br/>
</body>
</html>