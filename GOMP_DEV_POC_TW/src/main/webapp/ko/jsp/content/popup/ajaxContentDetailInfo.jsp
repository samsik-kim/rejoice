<%@ page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<c:choose>
<c:when test="${returnMessage == 'SUCCESS'}">${returnMessage}${redirectUrl}</c:when>
<c:when test="${returnMessage == 'FILE_EXTENTION_ERROR'}"><gm:string value='jsp.content.contentDetailInfo.msg.result01' /></c:when>
<c:when test="${returnMessage == 'FILE_SIZE_ERROR'}"><gm:string value='jsp.content.contentDetailInfo.msg.result02' /></c:when>
<c:when test="${returnMessage == 'IMAGE_UPDATE_FAIL'}"><gm:string value='jsp.content.contentDetailInfo.msg.result03' /></c:when>
<c:when test="${returnMessage == 'THUMBNAIL_UPDATE_FAIL'}"><gm:string value='jsp.content.contentDetailInfo.msg.result04' /></c:when>
<c:when test="${returnMessage == 'NONE_PAY_MEMBER'}"><gm:string value='jsp.content.contentDetailInfo.msg.result05' /></c:when>
<c:otherwise><gm:string value='jsp.content.common.msg.result.fail' /></c:otherwise>
</c:choose>