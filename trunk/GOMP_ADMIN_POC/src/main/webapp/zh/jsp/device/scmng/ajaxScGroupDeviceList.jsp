<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core"
%><%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"
%><%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"
%><%@ taglib prefix="s" uri="/struts-tags"
%><script>
$(document).ready(function () {
	deviceListUpdated();
});

function withdraw(index, tobj) {
	if (confirm("<gm:string value="jsp.device.scmng.scGroupEdit.msg.confirmWithdrawGroup"/>")) {
		$("#device" + index).removeAttr("disabled");
		$("#btnWithdraw" + index).css("display", "none");
	}
}
</script>
<table class="tabletype02" style="width:621px; border:1px solid #ddd;">
	<colgroup>
		<col width="30px" />
		<col width="15%" />
		<col width="18%" />
		<col width="15%" />
		<col width="15%" />
		<col />
	</colgroup>
	<tbody>
	<tr>
		<th><input type="checkbox" onclick="checkAll(this.checked)"/></th>
		<th>手機名稱</th>
		<th>型號</th>
		<th>OS版本</th>
		<th>LCD Size</th>
		<th>群組</th>
	</tr>
<c:choose>
	<c:when test="${empty deviceList}">
	<tr>
		<td colspan="6"><gm:html value="jsp.device.scmng.scGroupEdit.msg.emptyResult"/></td>
	</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${deviceList}" var="device" varStatus="loop">
	<tr>
			<c:if test="${device.joined}">
		<td>
			<input id="device${loop.index}" name="edit.phoneModelCd" type="checkbox" value="${device.phoneModelCd}" checked
				onclick="deviceListUpdated();"
				v:mustcheck="1,9999" m:mustcheck="<gm:tagAttb value="jsp.device.scmng.scGroupEdit.msg.plzCheckDevice"/>"/>
		</td>
			</c:if>
			<c:if test="${not device.joined}">
		<td>
			<input id="device${loop.index}" name="edit.phoneModelCd" type="checkbox" value="${device.phoneModelCd}" ${not empty device.coreappGroupId ? 'disabled' : ''}
				onclick="deviceListUpdated();"
				v:mustcheck="1,9999" m:mustcheck="<gm:tagAttb value="jsp.device.scmng.scGroupEdit.msg.plzCheckDevice"/>"/>
		</td>
			</c:if>
		<td class="text_l"><g:html value="${device.modelNm}"/></td>
		<td class="text_l"><a href="javascript:popupPhoneHistory(<g:tajson value="${device}"/>, ${loop.index})"><g:html value="${device.mgmtPhoneModelNm}"/></a></td>
		<td id="tdDeviceVer${loop.index}"><gc:html code="${device.vmVer}"/></td>
		<td><gc:html code="${device.lcdSize}"/></td>
		<td class="text_l">
			<g:html value="${empty device.groupName ? '-' : device.groupName}"/>
			<c:if test="${not device.joined and not empty device.coreappGroupId}">			
			<a id="btnWithdraw${loop.index}" href="javascript:withdraw(${loop.index})" class="btn_s" ><span style="cursor:pointer">解除介接</span></a>
			</c:if>
		</td>
	</tr>
		</c:forEach>
	</c:otherwise>
</c:choose>	
	</tbody>
</table>
