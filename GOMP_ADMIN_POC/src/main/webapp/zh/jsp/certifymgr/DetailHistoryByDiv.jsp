<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
		<h1 class="mt25">審核詳細紀錄</h1>
		<table class="pop_tabletype01">
			<colgroup><col style="width:40%;"><col style="width:60%"></colgroup>
			<tbody>
			<tr>
				<th>審核次數</th>
				<td>${appInfo.verifyReqVer }次</td>
			</tr>
			<tr>
				<th>審核結果</th>
				<td><gc:html code="${appInfo.agrmntStat}"/></td>
			</tr>			
			<tr>
				<th>測試類別</th>
				<td><c:forEach items="${appInfo.testCaseList}" var="info">${info.testCaseTitleNm}&nbsp;/&nbsp;</c:forEach></td>
			</tr>			
			<tr>
				<th>測試管理者</th>
				<td>${appInfo.testerNm}</td>
			</tr>
			</tbody>
		</table>
		<h2 class="mt20">&nbsp;支援手機目錄</h2>
		<table class="tabletype02" style="width:770px;">			
			<tr>
				<td class="text_l scroll_wrap">
					<div class="scroll">
						<table class="tabletype02" style="width:755px;">
							<colgroup>
								<col >
								<col >
								<col >
								<col >
							</colgroup>
							<thead>
							<tr>
								<th>手級名稱</th>
								<th>型號</th>
								<th>OS版本</th>
								<th>LCD解析度</th>
							</tr>
							<c:forEach items="${sourcePhoneList}" var="info">
							<tr>
								<td>${info.modelNm }</td>
								<td>${info.mgmtPhoneModelNm }</td>
								<td><gc:html code="${info.vmVer }"/></td>
								<td><gc:html code="${info.lcdSize }"/></td>
							</tr>
							</c:forEach>
							</tbody>
						</table>							
					</div>
				</td>
			</tr>
		</table>
		<h2 class="mt20">&nbsp;測試手機目錄</h2>
		<table class="tabletype02" style="width:770px;">			
			<tr>
				<td class="text_l scroll_wrap">
					<div class="scroll">
						<table class="tabletype02" style="width:755px;">
							<colgroup>
								<col >
								<col >
								<col >
								<col >
							</colgroup>
							<thead>
							<tr>
								<th>手級名稱</th>
								<th>型號</th>
								<th>OS版本</th>
								<th>LCD解析度</th>
							</tr>
							<c:forEach items="${targetPhoneList}" var="info">
								<tr>
									<td>${info.modelNm }</td>
									<td>${info.mgmtPhoneModelNm }</td>
									<td><gc:html code="${info.vmVer }"/></td>
									<td><gc:html code="${info.lcdSize }"/></td>
								</tr>							
							</c:forEach>
							</tbody>
						</table>							
					</div>
				</td>
			</tr>
		</table>