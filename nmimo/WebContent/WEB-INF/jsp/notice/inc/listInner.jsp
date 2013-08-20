<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- Table Area // 공지사항 리스트 -->
<table id="noticeListTable" name="noticeListTable" summary="리스트" class="ListTable">
	<caption>리스트</caption>
	<thead>
		<tr>
			<th style="width:100px">번호</th>
			<th style="width:540px">제목</th>
			<th style="width:100px">등록자</th>
			<th style="width:100px">등록일</th>
			<th style="width:100px">조회수</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.dataList}" varStatus="listIndex" var="data">
		<tr>
		  	<td>${data.titleNm}</td>
			<td class="l"><a href="#" onclick="javascript:goDetail('${data.ntfSeq}')">${data.ntfSbst}</a></td>
			<td>${data.userNm}</td>
			<td>${data.cretDt }</td>
			<td>${data.retvTmscnt}</td>
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