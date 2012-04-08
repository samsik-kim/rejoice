<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="dnc" uri="/WEB-INF/tld/dnc.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
<!--
var CURRENT_PAGE = '${info.currentPage}';
//수정
function fn_modify(seqNo,positionNum){
	if(confirm("수정 하시겠습니까?")){	
		$("#seqNo").val(seqNo);
		//var test = $("#modify[name='['+positionNum+']'").val();
		$("#boardName").val(document.all.modifyName[positionNum].value);
		$("#regFrm").attr('action','/board/boardManageUpdate.do') ;
		$("#regFrm").submit();
	}
}

//입력
function fn_insert() {
	if ($("#insertName").val() == "" ) {
		alert("게시판명을 확인해주세요.");
		return;	
	}
	
	if(confirm("입력 하시겠습니까?")){
		$("#boardName").val($("#insertName").val());
		$("#regFrm").attr('action','/board/boardManageInsert.do') ;
		$("#regFrm").submit();		
	}
}

//삭제
function fn_delete(seqNo){
	if(confirm("삭제 하시겠습니까?")){	
		$("#seqNo").val(seqNo);
		$("#regFrm").attr('action','/board/boardManageDelete.do') ;
		$("#regFrm").submit();
	}
}


//-->
</script>
<div class="pmbox mar_b22">
<form name="regFrm" id="regFrm" method="post" enctype="application/x-www-form-urlencoded">
	<input type="hidden" name="seqNo" id="seqNo"/>
	<input type="hidden" name="boardName" id="boardName"/>
</form>
</div>
<div class="tstyleC">
<table>
	<caption>게시판 리스트</caption>
	<colgroup>
		<col width="100" />
		<col width="80" />
	</colgroup>
	<thead>
		<tr>
			<th scope="col">게시판명</th>
			<th scope="col">선택</th>
		</tr>
	</thead>
	<tbody>		
		<c:forEach var="list" items="${boardManageList}" varStatus="i">
			<c:set var="setCnt" value="${i.count-1}"/>
		<tr>
			<td><input type="text" name="modifyName" id="modifyName" value="${list.boardName}"></td>
			<td><a href="#" onclick="javascript:fn_modify('${list.seqNo}','${setCnt}');">수정</a>/<a href="#" onclick="javascript:fn_delete('${list.seqNo}');">삭제</a></td>
		</tr>
		</c:forEach>
		<tr>
			<td><input type="text" name="insertName" id="insertName"></td>
			<td><a href="#" onclick="javascript:fn_insert();">추가</a></td>
		</tr>
	</tbody>	
</table>
</div>		
<!-- Excel Download -->
<div id="editManageList">
</div>