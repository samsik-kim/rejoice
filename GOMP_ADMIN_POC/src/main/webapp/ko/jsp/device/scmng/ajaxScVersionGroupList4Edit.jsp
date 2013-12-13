<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core"
%><%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"
%><%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"
%><%@ taglib prefix="s" uri="/struts-tags"
%>
<table class="tabletype02" style="width:621px; border:1px solid #ddd;">
	<colgroup>
		<col width="40px" />
		<col width="60%" />
		<col width="12%" />
		<col >
	</colgroup>
	<tbody>
	<tr>
		<th>선택</th>
		<th>그룹명</th>
		<th>대상단말</th>
		<th>등록일시</th>
	</tr>
<c:if test="${empty groupList}">
	<tr>
		<td colspan="4"><gm:html value="jsp.device.scmng.scVersionEdit.msg.emptyGroup"/></td>
	</tr>
</c:if>
<c:forEach items="${groupList}" var="group">
	<tr>
		<td>
			<input name="coreApp.coreappGroupId"  type="radio" value="${group.coreappGroupId}"
				v:mustcheck="1" m:mustcheck="<gm:tagAttb value="jsp.device.scmng.scVersionEdit.msg.plzCheckGroup"/>"/>
		</td>
		<td class="text_l"><a href="javascript:popupGroupDeviceList(<g:tajson value="${group}"/>);"><g:html value="${group.groupName}"/></a></td>
		<td style="text-align:right"><g:html value="${group.deviceCount}"/></td>
		<td><g:html value="${group.regDttm}" format="L####-##-## ##:##"/></td>
	</tr>
</c:forEach>
	</tbody>

