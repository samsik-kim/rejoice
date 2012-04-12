<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function(){
	
	//취소 -> 목록
	$("#listBtn").click(function(){
		$("#regFrm").attr('action','/code/codeList.do') ;
		$("#regFrm").submit();
	});
	
	// 등록 -> 목록
	$('#modifyBtn').click(function(){
		if(!confirm('정말 수정하시겠습니까?')) return;
		
		if(showValidate('modifyFrm', 'default', "입력오류를 확인하십시오.")){
			if($("#codeName").val() == ""){
				alert("종목명을 확인 해주세요.");
				return;
			}

			if($("#codeNum").val() == ""){
				alert("종목코드를 확인 해주세요.");
				return;
			}
			
			$("#modifyFrm").attr('action','/code/codeUpdate.do') ;
			$("#modifyFrm").submit();
		}
	});
	
	$('#deleteBtn').click(function(){
		if(!confirm('정말 삭제하시겠습니까?')) return;
		
		$("#modifyFrm").attr('action','/code/codeDelete.do') ;
		$("#modifyFrm").submit();		
	});
	
	
});
</script>
<div class="tstyleA">
<form id="modifyFrm" name="modifyFrm" method="post" >
	<table summary="종목코드 기본정보 입력 항목입니다">
		<caption>종목코드 입력 항목</caption>
		<colgroup>
			<col width="15%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span>*</span> 종목명</th>
				<td>
					<input type="text" id="codeName" name="codeName" class="w180"
					v:required='trim' m:required="종목명을 입력하십시오."  value="${info.codeName}"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><span>*</span> <label for="moblenum">종목코드</label></th>
				<td>
					<input type="text" id="codeNum" name="codeNum" class="w180"
					v:required='trim' m:required="종목코드를 입력하십시오." 
					v:mustnum m:mustnum="종목코드는 숫자로 입력하세요." value="${info.codeNum}" maxlength="6"/>&nbsp;
					<span class="txtcolor01"> &nbsp;* “ㅡ” 을 생략하고 숫자로만 입력해 주세요.</span>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="moblenum">전화번호</label></th>
				<td>
					<input type="text" id="tel" name="tel" class="w180" value="${info.tel}"/>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="moblenum">정보연락처</label></th>
				<td>
					<input type="text" id="infoTel" name="infoTel" class="w180" value="${info.infoTel}"/>
				</td>
			</tr>
			<tr>
				<th scope="row" class="tit03">지분보유</th>
				<td><input type="text" id="holdShare" name="holdShare" class="w376" value="${info.holdShare}"/></td>
			</tr>
			<tr>
				<th scope="row" class="tit03">주주</th>
				<td><input type="text" id="juju" name="juju" class="w376" value="${info.juju}"/></td>
			</tr>
			<input type="hidden" id="seqNo" name="seqNo" value="${info.seqNo}"/>
			<input type="hidden" name="currentPage" id="currentPage" value="${info.currentPage}" />
			<input type="hidden" name="stDt" id="stDt" value="${info.stDt}" />
			<input type="hidden" name="enDt" id="enDt" value="${info.enDt}" />	
			<input type="hidden" name="searchKey" id="searchKey" value="${info.searchKey}" />								
			<input type="hidden" name="searchValue" id="searchValue" value="${info.searchValue}" />
		</tbody>
	</table>
	<br/>
	<div class="btnarea">
		<a href="#"><img id="modifyBtn" src="/resource/images/common/btn_inner_change.gif" alt="수정" /></a>
		<a href="#"><img id="deleteBtn" src="/resource/images/common/btn_inner_delete.gif" alt="삭제" /></a>
		<a href="#"><img id="listBtn" src="/resource/images/common/btn_cancel2.gif" alt="목록" /></a>
	</div>
</form>				
</div>