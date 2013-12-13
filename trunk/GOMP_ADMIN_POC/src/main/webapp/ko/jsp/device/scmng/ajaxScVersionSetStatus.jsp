<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core"
%><%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"
%><%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"
%><%@ taglib prefix="s" uri="/struts-tags"
%><script>
$(document).ready(function() {
	doSearchCoreAppList();
	alert("<gm:string value="처리 되었습니다."/>");
});
</script>