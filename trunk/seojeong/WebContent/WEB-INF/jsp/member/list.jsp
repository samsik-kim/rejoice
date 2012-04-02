<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script type="text/javascript">
<!--
$(document).ready(function(){
	$("#stDt").datepicker();
	$("#enDt").datepicker();
	$("#options").tablesorter({
		sortList: [[1,0]], 
		headers: { 0:{sorter: false}, 3:{sorter: false}, 7:{sorter: false}}
	});
	
	$("#delBtn").click(function(){
		var delArr = document.getElementsByName("delch");
		var delValues = "";
		for(var i=0; i<delArr.length; i++){
			delValues += delArr[i].value + ",";
		}
		if(confirm("삭제 하시겠습니까?")){
			$("#delVal").val(delValues);
			$("#frm").attr('action','/member/delete.do') ;
			$("#frm").submit();
		}
	});
	
	$("#excelBtn").click(function(){
		$("#stDt").val($("#stDt").val().replace(/-/g, ''));
		$("#enDt").val($("#enDt").val().replace(/-/g, ''));
		location.href = "/member/excel.do?stDt="+$("#stDt").val()+"&enDt="+$("#enDt").val();
	});
});

//검색
function searchList(){
		if($("#stDt").val() == "시작일") $("#stDt").val('');
		if($("#enDt").val() == "종료일") $("#enDt").val('');
		$("#stDt").val($("#stDt").val().replace(/-/g, ''));
		$("#enDt").val($("#enDt").val().replace(/-/g, ''));
		$("#searchFrm").attr('action','/member/list.do') ;
		$("#searchFrm").submit();
}

//목록
function goList(currentPage){
	location.href="/member/list.do?currentPage="+currentPage;
}

//상세
function fn_detail(seq){
	$("#seq").val(seq);
	$("#frm").attr('action','/member/updateForm.do') ;
	$("#frm").submit();
}

//-->
</script>
<div class="pmbox mar_b22">
<form name="searchFrm" id="searchFrm" method="post">
	<div class="fltl mar_trl20">
		시작일:<input type="text" name="stDt" id="stDt" class="w70" value="${info.stDt }"/>&nbsp;~
		종료일:<input type="text" name="enDt" id="enDt" class="w70" value="${info.enDt }"/>&nbsp;&nbsp;
		<select id="searchKey" name="searchKey" class="w128" title="검색조건선택">
			<option value="nm" <c:if test="${info.searchKey eq 'nm' }">selected</c:if>>고객명</option>
			<option value="mdn" <c:if test="${info.searchKey eq 'mdn' }">selected</c:if>>휴대폰</option>
		</select>&nbsp;
	</div>
	<input type="text" id="searchValue" name="searchValue" class="w150" value="${info.searchValue }" v:required='trim' m:required="검색어를 입력하십시오." />
	<a href="#"><img id="searchBtn" src="/resource/images/common/btn_board_search.gif" alt="검색" onclick="javascript:searchList();"/></a>
</form>
</div>
	<div class="fltr mar_trl20">
		<a href="#"><img id="excelBtn" src="/resource/images/common/btn_excel.gif" alt="excel" /></a>
	</div>
<form name="frm" id="frm" method="post">
	<input type="hidden" name="seq" id="seq" />
	<input type="hidden" name="currentPage" value="${pageInfo.currentPage}" />
	<input type="hidden" name="delVal" id="delVal"/>
</form>
<div class="tstyleC">
<table width="600"   id="options" class="tablesorter">
	<caption>고객 리스트</caption>
	<colgroup>
		<col width="50" />
		<col width="50" />
		<col width="150"/>
		<col width="150" />
		<col width="100" />
		<col width="100" />
		<col width="100" />
		<col width="" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">선택</th>
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
			<tr class="no-list"><td colspan="8">
				조회 내용이 없습니다.
			</td></tr>
		</c:if>
		<c:forEach var="list" items="${pageInfo.dataList}" varStatus="i">
		<tr>
			<td><input type="checkbox" name="delch" value="${list.seq}"></td>
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
<table align="right">
	<tr>
		<td align="right">
			<a href="#"><img id="delBtn" src="${pageContext.request.contextPath }/resource/images/common/btn_del.gif" alt="삭제하기" /></a>	
		</td>
	</tr>
</table>
<div class="pagination"><!-- pagination -->
		<dnc:PageNavigation pages="${pageInfo}" link="goList" pageIndexName="currentPage" styleClass="paging" 
			firstImg="${pageContext.request.contextPath }/resource/images/common/btn_ppre.gif"  
			prevImg="${pageContext.request.contextPath }/resource/images/common/btn_pre.gif" 
			nextImg="${pageContext.request.contextPath }/resource/images/common/btn_next.gif" 
			lastImg="${pageContext.request.contextPath }/resource/images/common/btn_nnext.gif"
			image="true" linkScript="true" 
		/>					
</div><!-- // pagination -->