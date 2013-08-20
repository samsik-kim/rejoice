<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- Table Area // 검토>배너관리 -->
<table id="reviewBannerListTable" name="reviewBannerListTable" summary="리스트" class="ListTable">
	<caption>리스트</caption>
	<thead>
		<tr>
			<th style="width:100px">구분</th>
			<th style="width:740px">내용</th>
			<th style="width:100px">상태</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${pageInfo.dataList}" varStatus="listIndex" var="data">
		<tr>
			<td>${data.telDivVal}</td>
			<td class="l"><a href="#" onclick="javascript:doPostDetail('${data.banrBdyAdmSeq}','/review/bannerDetail.do');">${data.banrSbst}</a></td>
			<td>${data.banrUseYn}</td>
		</tr>
		</c:forEach>
		<c:if test="${pageInfo.totalCount == 0}" >
		<tr>
			<td colspan="3">
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