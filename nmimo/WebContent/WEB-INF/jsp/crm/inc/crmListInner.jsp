<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- Table Area -->
<table summary="리스트" class="ListTable"  id="crmListTable" name="crmListTable">
	<caption>리스트</caption>
	<colgroup>
		<col width="10%" />
		<col width="*" />
		<col width="20%" />
		<col width="10%" />
		<col width="10%" />
		<col width="10%" />
	</colgroup>
	<thead>
		<tr>
			<th>서비스구분</th>
			<th>제목</th>
			<th>등록일시</th>
			<th>발송예정일</th>
			<th>요청건수</th>
			<th>상태</th>
		</tr>
	</thead>
	<tbody>
	<c:if test="${pageInfo.totalCount<=0}">
		<tr onMouseOver="this.style.background='#f4e9db'" onMouseOut="this.style.background=''">
			<td colspan="5">해당 조회 자료가 없습니다.</td>
		</tr>
	</c:if>
<%-- 	<c:forEach var="list" items="${pageInfo.dataList}" varStatus="i"> --%>
<!-- 		<tr> -->
<%-- 			<td><a href="#" id="listInfoDetailPop"><c:out value="${list.jobNm}" />&nbsp;</a></td>			 --%>
<%-- 			<td><c:out value="1" />&nbsp;</td> --%>
<%-- 			<td><c:out value="2" />&nbsp;</td> --%>
<%-- 			<td><c:out value="3" />&nbsp;</td> --%>
<%-- 			<td><c:out value="4" />&nbsp;</td> --%>
<%-- 			<td><c:out value="5" />&nbsp;</td> --%>
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
	</tbody>
</table>

	<!-- Paging -->
	<div class="paging">
		<mimo:PageNavigation pages="${pageInfo}" link="goList" pageIndexName="pageIndex" 
			firstImg="/resource/images/btn_p_first.gif"
			prevImg="/resource/images/btn_p_prev.gif" 
			nextImg="/resource/images/btn_p_next.gif" 
			lastImg="/resource/images/btn_p_end.gif"
			image="true" 
			linkScript="true"
		/>
	</div>
	