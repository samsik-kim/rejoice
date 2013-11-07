<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib prefix="ct" uri="/WEB-INF/tld/commonTag.tld" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

		<table class="sitemap">
		<s:if test="listAdMenuItem.size == 0">
		<tr><td>&nbsp;</td></tr>
		</s:if>
		<s:else>
		<s:iterator value="listAdMenuItem" status="stat">
			<s:if test='#stat.first || #stat.index % 6 == 0'><tr></s:if>
				<td>
					<h2><span>${menuNm}</span></h2>
				<s:iterator value="subMenuItem">
					<h3>${menuNm}</h3>
					<ul class="s_menu">
					<s:iterator value="subMenuItem">
						<li><a href="${pageUrl}">${menuNm}</a></li>
					</s:iterator>
					</ul> 
				</s:iterator>
				</td>
			<s:if test='#stat.last || #stat.index % 6 == 5'></tr></s:if>
		</s:iterator>
		</s:else>
		</table>

</body>
</html>
