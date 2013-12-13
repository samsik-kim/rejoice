<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core"
%><%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"
%><%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"
%><%@ taglib prefix="s" uri="/struts-tags"
%><select name="sc.groupId">
	<option value="">전체</option>
<c:forEach items="${groupList}" var="group">
	<option value="${group.coreappGroupId}" ${group.coreappGroupId == sc.groupId ? 'selected' : ''}><g:tagBody value="${group.groupName}"/></option>
</c:forEach>
</select>