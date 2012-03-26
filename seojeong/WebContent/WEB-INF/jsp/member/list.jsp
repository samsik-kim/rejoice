<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
<!--
$(document).ready(function(){
	$("#options").tablesorter({
		sortList: [[0,0]], 
		headers: { 2:{sorter: false}, 6:{sorter: false}}
	});
	
});

//검색
function searchList(){
	if(showValidate("searchFrm", 'default', "입력 오류가 있습니다.")){
		$("#searchFrm").attr('action','/member/list.do') ;
		$("#searchFrm").submit();
	}
}

//목록
function goList(currentPage){
	location.href="/member/list.do?currentPage="+currentPage;
}

//상세
function fn_detail(seq){
	$("#seq").val(seq);
	$("#detailFrm").attr('action','/member/updateForm.do') ;
	$("#detailFrm").submit();
}
//-->
</script>
<div class="pmbox mar_b22">
<form name="searchFrm" id="searchFrm" method="post" onsubmit="return showValidate('searchFrm', 'default', '입력 오류가 있습니다.')">
	<div class="fltl mar_trl20">
		<select id="searchKey" name="searchKey" class="w145" title="검색조건선택">
			<option value="nm" <c:if test="${info.searchKey eq 'nm' }">selected</c:if>>고객명</option>
			<option value="mdn" <c:if test="${info.searchKey eq 'mdn' }">selected</c:if>>휴대폰</option>
		</select>
	</div>
	<input type="text" id="searchValue" name="searchValue" class="w410" value="${info.searchValue }" v:required='trim' m:required="검색어를 입력하십시오." />
	<a href="#"><img id="searchBtn" src="/resource/images/common/btn_board_search.gif" alt="검색" onclick="javascript:searchList();"/></a>
</form>
</div>
<form name="detailFrm" id="detailFrm" method="post">
	<input type="hidden" name="seq" id="seq" />
	<input type="hidden" name="currentPage" value="${pageInfo.currentPage}" />
</form>
<div class="tstyleC">
<table width="600"   id="options" class="tablesorter">
	<caption>고객 리스트</caption>
	<colgroup>
		<col width="50" />
		<col width="150"/>
		<col width="200" />
		<col width="100" />
		<col width="100" />
		<col width="100" />
		<col width="" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">순번</th>
			<th scope="col">고객명</th>
			<th scope="col">휴대폰</th>
			<th scope="col">당첨횟수</th>
			<th scope="col">방문횟수</th>
			<th scope="col">방문일</th>
			<th scope="col">등록일</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${pageInfo.totalCount<=0}">
			<tr class="no-list"><td colspan="7">								
				조회 내용이 없습니다.
			</td></tr>
		</c:if>
		<c:forEach var="list" items="${pageInfo.dataList}" varStatus="i">
		<tr>
			<td><div>${list.rnum}</div></td>
			<td><a href="#" onclick="javascript:fn_detail('${list.seq}');">${list.memberNm}</a></td>
			<td><a href="#" onclick="javascript:fn_detail('${list.seq}');">${list.mdn}</a></td>
			<td>${list.winCnt}</td>
			<td>${list.vstCnt}</td>
			<td>${list.vstDt}</td>
			<td>${list.regDt}</td>
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