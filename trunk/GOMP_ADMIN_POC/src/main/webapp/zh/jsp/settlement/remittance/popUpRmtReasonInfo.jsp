<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>詳細內容</title>
<script type="text/javascript" src="../inc/_pophead.js"></script>
</head>
<body class="popup">
	<div id="popup_area_440">
		<input type="text" class="txt" id="" style="width:370px;" value="▶ 變更匯款狀態之緣由" />
		<textarea cols="44" rows="8" class="mt20"><g:tagAttb value="${remittance.rmtReason}"/></textarea>
		<div class="pop_btn" >
			<a class="btn_s" href="JavaScript:self.close()"><strong>確定</strong></a>
		</div>
	</div><!-- //contents -->
</body>
</html>

