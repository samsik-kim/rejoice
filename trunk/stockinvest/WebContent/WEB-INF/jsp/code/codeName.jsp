<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
&nbsp;[<font color=red>${info.codeName}</font>] <c:if test="${info.codeName == null}"><font color=red>사용되지않는 종목코드입니다.</font></c:if>
<input type="hidden" name="codeName" id="codeName" value="${info.codeName}">