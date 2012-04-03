<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<ul id="leftmenu">
	<li><a href="#">계정관리</a></li>
	<li><a href="#">코드관리</a>
		<ul id="depth2">
			<li><a href="/code/codeList.do">코드목록</a></li>
			<li><a href="/code/insertCodeForm.do">코드등록</a></li>
		</ul>	
	</li>
	<li>
		<a href="#">게시판관리</a>
		<ul id="depth3">
			<li><a href="/board/boardManageList.do">게시판목록</a></li>		
		<c:forEach var="list" items="${boardManageList}" varStatus="i">
			<li><a href="/board/boardList.do?bbsCd=${list.bbsCd}&boardName=${list.boardName}">${list.boardName}</a></li>
		</c:forEach>
		</ul>		
	</li>
</ul>



				