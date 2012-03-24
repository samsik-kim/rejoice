<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
<!--
$(document).ready(function(){
	$("table").tablesorter({
		// sort on the first column and third column, order asc         
		sortList: [[0,0],[2,0]]     
	}); 
});


//목록
function goList(currentPage){
	location.href="/member/list.do?currentPage="+currentPage;
}
//-->
</script>
<div class="pmbox mar_b22">
	<div class="fltl mar_trl20">
		<select id="searchType" name="notice.searchType" class="w145" title="검색조건선택">
			<option value="nm" selected>고객명</option>
			<option value="tel">연락처</option>
		</select>
	</div>
	<input type="text" id="searchWord" name="notice.searchWord" class="w410"  value="" title="검색어를 입력해주세요" />
	<input type="image" id="searchBtn" src="/resource/images/common/btn_board_search.gif" alt="검색" />
</div>
<div class="tstyleE">
<table width="600"  class="tablesorter">
	<caption>고객 리스트</caption>
	<colgroup>
		<col width="50" />
		<col width="150"/>
		<col width="300" />
		<col width="100" />
		<col width="" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">No</th>
			<th scope="col">고객명</th>
			<th scope="col">연락처</th>
			<th scope="col">당첨횟수</th>
			<th scope="col">등록일</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${pageInfo.totalCount<=0}">
			<tr class="no-list"><td colspan="5">								
				<c:if test="${!empty info.MEMBER_NM}">
					조회 내용이 없습니다.
				</c:if>							
			</td></tr>
		</c:if>
		<c:forEach var="list" items="${pageInfo.dataList}" varStatus="i">
		<tr>
			<td><div>${list.rnum}</div></td>
			<td>${list.MEMBER_NM}</td>
			<td>${list.CONTANCT1}</td>
			<td>${list.WIN_CNT}</td>
			<td>${list.REG_DT}</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
</div>
<div class="pagination"><!-- pagination -->
		<dnc:PageNavigation pages="${pageInfo}" link="goList" pageIndexName="currentPage" styleClass="paging" 
			firstImg="/resource/images/common/btn_ppre.gif"  
			prevImg="/resource/images/common/btn_pre.gif" 
			nextImg="/resource/images/common/btn_next.gif" 
			lastImg="/resource/images/common/btn_nnext.gif"
			image="true" linkScript="true" 
		/>					
</div><!-- // pagination -->