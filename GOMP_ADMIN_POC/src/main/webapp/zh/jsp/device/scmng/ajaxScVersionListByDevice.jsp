<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"
%><%@ taglib prefix="g" uri="http://gomp.com/taglib/core"
%><%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"
%><%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"
%><%@ taglib prefix="s" uri="/struts-tags"
%><table class="tabletype02" style="width:100%; border:1px solid #ddd;">
<colgroup>
	<col width="40">
	<col width="40">
	<col >
	<col >
	<col >
	<col >
</colgroup>

	<thead>
	<tr>
		<th>-</th>
		<th>-</th>
		<th>버전</th>
		<th>파일명</th>
		<th>등록일</th>
		<th>업데이트내용</th>
	</tr>
	</thead>
	<tbody>
<c:choose>
	<c:when test="${empty coreappList}">
	<tr>
		<td colspan="6">등록된 코어앱이 없습니다.</td>
	</tr>
	</c:when>
	<c:otherwise>
		<c:forEach items="${coreappList}" var="coreapp">
	<tr onmouseover="this.style.background='#FFFFC0'" onmouseout="this.style.background=''">
		<td nowrap>
		<c:choose>
			<c:when test="${coreapp.verStatus eq 'DP005403'}">
				<gc:html code="DP005403"/>
			</c:when>
			<c:otherwise>
				<a class="btn" href="javascript:setProdVersion(&quot;<g:tagAttb value="${coreapp.coreappId}"/>&quot;, ${coreapp.verStatus ne 'DP005402'})"><span>상용으로</span></a>
			</c:otherwise>
		</c:choose>		
		</td>
		<td nowrap>
		<c:choose>
			<c:when test="${coreapp.verStatus eq 'DP005402'}">
				<gc:html code="DP005402"/>
			</c:when>
			<c:otherwise>
				<a class="btn" href="javascript:setTestVersion(&quot;<g:tagAttb value="${coreapp.coreappId}"/>&quot;)"><span>테스트로</span></a>
			</c:otherwise>
		</c:choose>		
		</td>
		<td class="text_r"><g:html value="${coreapp.ver}"/></td>
		<td class="text_l"><g:html value="${coreapp.appPath}"/></td>
		<td><g:html value="${coreapp.regDt}" format="L####-##-##"/></td>
		<td class="text_l"><g:html value="${coreapp.prodDesc}" format="L######################..."/></td>
	</tr>
		</c:forEach>	
	</c:otherwise>
</c:choose> 
	</tbody>
</table>