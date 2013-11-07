<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core"
%><%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"
%><%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"
%><%@ taglib prefix="s" uri="/struts-tags"
%>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		setLastVersion("<g:string value="${sc.groupId}"/>", "<g:string value="${lastVersion}"/>");
	});
//-->
</script>
Last Version : <g:html value="${lastVersion}"/>