<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- Table Area // 사전예약 -->
<table id="reservationListTable" name="reservationListTable" summary="리스트" class="ListTable">
	<caption>리스트</caption>
	<thead>
		<tr>
			<th style="width:340px">작업명</th>
			<th style="width:100px">신청자</th>
			<th style="width:200px">사전등록 일시</th>
			<th style="width:200px">메시지 발송 희망 일시</th>
			<th style="width:100px">상태</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.dataList}" varStatus="listIndex" var="data">
		<tr>
			<td class="l"><a href="#" onclick="javascript:doPostDetail('${data.bfacRegSeq }','/reservation/detail.do');">${data.userNm}</a></td>
		    <td>${data.cretrId}</td>
			<td>${data.cretDt}</td>
			<td>${data.dspRsrvDate}</td>
			<td>${data.apvSttusVal}</td>
		</tr>
		</c:forEach>
		<c:if test="${pageInfo.totalCount == 0}" >
		<tr>
			<td colspan="7">
				해당게시물이 없습니다.
			</td>
		</tr>
		</c:if>
	</tbody>
</table>

<c:if test="${pageInfo.totalCount > 0}" >
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
</c:if>