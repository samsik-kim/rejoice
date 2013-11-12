<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>불량사용후기 신고사유</title>
</head>

<body class="popup">
	<div id="popup_area_440">
    	<table class="pop_tabletype01">
        	<tbody>
        	<colgroup><col style="width:25%;"><col ><col style="width:25%;"><col ></colgroup>
            	<tr>
                	<th>신고사유</th>
                    <td colspan="3"><g:tagBody value="${dpBadnoti.badnotiRptNm}" /></td>
                </tr>
                <tr>
                	<th>운영자ID</th>
                    <td>${dpBadnoti.regId}</td>
                    <th>신고일</th>
                    <td><g:text value="${dpBadnoti.regDttm}" format="L####-##-## ##:##"/></td>
                </tr>
                <tr>
                	<th>의견</th>
                    <td colspan="3"><g:string value="${dpBadnoti.rptDscr}" /></td>
                </tr>
            </tbody>
        </table>
				
		<div class="pop_btn" >
			<a class="btn_s" href="javascript:self.close();"><strong>닫기</strong></a>
		</div>
	</div>
    <!-- //contents -->
</body>
</html>