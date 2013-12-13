<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="ct" uri="/WEB-INF/tld/commonTag.tld" %>

<script type="text/javascript">
	document.onkeydown = KeyEventHandle;
	document.onkeyup = KeyEventHandle;
	document.oncontextmenu = MouseEventHandle;

	function KeyEventHandle(event) {
		if ((event.ctrlKey == true && (event.keyCode == 78 || event.keyCode == 82))
				|| (event.keyCode >= 112 && event.keyCode <= 123)) {
			event.keyCode = 0;
			event.cancelBubble = true;
			event.returnValue = false;
		}
	}
	function MouseEventHandle() {
		return false;
	}
</script>