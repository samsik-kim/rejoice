<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- Table Area -->
<table summary="리스트" class="ListTable"  id="reviewEcammsListTable" name="reviewEcammsListTable">
	<caption>리스트</caption>
	<thead>
		<tr>
			<th style="width:100px">작업ID</th>
			<th style="width:240px">작업명</th>
			<th style="width:200px">결과코드</th>
			<th style="width:100px">설명</th>
			<th style="width:100px">사용자</th>
			<th style="width:100px">부서명</th>
			<th style="width:100px">등록시간</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td colspan="7">ECAMMS리스트가 없습니다.</td>
		</tr>
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
