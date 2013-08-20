<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- Table Area // 나의작업>나의보관함 -->
<table summary="리스트" class="ListTable"  id="myPageMessageListTable" name="myPageMessageListTable">
	<caption>리스트</caption>
	<thead>
		<tr>
			<th style="width:100px">번호</th>
			<th style="width:640px">작업명</th>
			<th style="width:100px">임시저장 일시</th>
			<th style="width:100px">선택</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>1</td>
			<td class="l">201302(MMS)_마케팅전략팀_강신제_통큰기변_26일_03</td>
			<td>2013-00-00</td>
			<td><a href="#" onclick="javascript:doPostDetail('/myPage/messageListDetail.do');" class="btn_sml"><span>선택</span></a></td>
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
