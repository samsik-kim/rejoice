<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
$(document).ready(function(){
	$("#options").tablesorter({
		sortList: [[1,1]], 
		headers: { 0:{sorter: false} }		
	});
});
</script>
<div class="tstyleC">
<table width="600"   id="options" class="tablesorter">
	<caption>게시판 리스트</caption>
	<colgroup>
		<col width="50" />
		<col width="80" />
		<col width="" />
		<col width="140" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">선택</th>
			<th scope="col">작성일</th>
			<th scope="col">제목</th>
			<th scope="col">종목</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${pageInfo.totalCount<=0}">
			<tr class="no-list"><td colspan="4">
				조회 내용이 없습니다.
			</td></tr>
		</c:if>
		<c:forEach var="list" items="${pageInfo.dataList}" varStatus="i">
		<tr>
			<td><input type="checkbox" name="delch" value="${list.seqNo}"></td>
			<td><div>${list.crtDate}</div></td>
			<td style="text-align: left;"><a href="#" onclick="javascript:fn_detail('${list.seqNo}');">${list.subject}</a></td>
			<td><a href="#" onclick="javascript:fn_detail('${list.seqNo}');">${list.codeName}(${list.codeNum})</a></td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<div class="pagination"><!-- pagination -->
		<dnc:PageNavigation pages="${pageInfo}" link="goList" pageIndexName="currentPage" styleClass="paging" 
			firstImg="${pageContext.request.contextPath }/resource/images/common/btn_ppre.gif"  
			prevImg="${pageContext.request.contextPath }/resource/images/common/btn_pre.gif" 
			nextImg="${pageContext.request.contextPath }/resource/images/common/btn_next.gif" 
			lastImg="${pageContext.request.contextPath }/resource/images/common/btn_nnext.gif"
			image="true" linkScript="true" 
		/>					
</div><!-- // pagination -->