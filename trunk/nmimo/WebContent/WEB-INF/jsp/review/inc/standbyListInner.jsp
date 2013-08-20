<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- Table Area // 검토>대기 -->
<table summary="리스트" class="ListTable">
	<caption>리스트</caption>
	<thead>
		<tr>
			<th rowspan="2" style="width:240px">작업명</th>
			<th rowspan="2" style="width:100px">부서명</th>
			<th rowspan="2" style="width:100px">등록자</th>
			<th colspan="3" style="width:300px">승인현황</th>
			<th rowspan="2" style="width:100px">요청건수</th>
			<th rowspan="2" style="width:100px">예약일시</th>
		</tr>
		<tr>
			<th>검토협의</th>
			<th>114협의</th>
			<th>발송승인</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.dataList}" varStatus="listIndex" var="data">
		<tr>
			<td class="l"><a href="#" onclick="javascript:doPostDetail('${data.wrkId}','/review/standbyListDetail.do');">${data.msgTitleNm}</a></td>
			<td>nMIMO</td>
			<td>${data.wrkId}</td>
			<td>검토대기</td>
			<td>대기</td>
			<td>승인대기</td>
			<td>${data.trmRqtCascnt}</td>
			<td>${data.rsrvDt}</td>
		</tr>
		</c:forEach>
		<c:if test="${pageInfo.totalCount == 0}" >
		<tr>
			<td colspan="9">
				해당게시물이 없습니다.
			</td>
		</tr>
		</c:if>	
	</tbody>
</table>    

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
