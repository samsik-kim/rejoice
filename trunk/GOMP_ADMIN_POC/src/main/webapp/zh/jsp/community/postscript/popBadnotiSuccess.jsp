<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<%
	String pageNo = request.getParameter("pageNo");
	String searchType = request.getParameter("searchType");
	String searchValue = request.getParameter("searchValue");
	String srchBadnotiYn = request.getParameter("srchBadnotiYn");
	String srchDelYn = request.getParameter("srchDelYn");
	String startDate = request.getParameter("startDate");
	String endDate = request.getParameter("endDate");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<script language="javascript">
function successUdate(){
	alert("<gm:string value='jsp.community.postscript.popBadnotiSuccess.msg.success_rpt'/>");
	self.opener.location.href = "${pageContext.request.contextPath}/community/postscriptList.omp?srchFlag=TRUE&amp;pageNo=<%=pageNo%>&amp;searchType=<%=searchType%>&amp;searchValue=<%=searchValue%>&amp;srchBadnotiYn=<%=srchBadnotiYn%>&amp;srchDelYn=<%=srchDelYn%>&amp;startDate=<%=startDate%>&amp;endDate=<%=endDate%>";
	// self.opener.location.reload();
	self.close();
}
</script>
</head>
<body>

<script language="javascript">
	successUdate();
</script>

</body>
</html>