<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
$(document).ready(function() {
	//취소 -> 목록
	$("#listBtn").click(function(){
		$("#regFrm").attr('action','/board/boardList.do') ;
		$("#regFrm").submit();
	});

	$('#codeNum').change(function() {
		var getCodeNum = $("input:[name=codeNum]").val();
	
		if (getCodeNum.length == 6) {
			pageLoadAjaxListInner("regFrm", "checkCodeNum", "/code/ajaxCodeName.do"); // 종목코드명 호출		
		}
		
	});	
	
	
	
	// 등록 -> 목록
	$('#okBtn').click(function(){
		if(showValidate('regFrm', 'default', "입력오류를 확인하십시오.")){
			if($("#codeNum").val() == ""){
				alert("종목코드를 확인 해주세요.");
				return;
			}

			if($("#codeName").val() == "") {
				alert("등록되지않은 종목코드는 입력할수 없습니다.");	
				return;
			}
			
			if($("#subject").val() == ""){
				alert("제목을 확인 해주세요.");
				return;
			}
			if(confirm("입력 하시겠습니까?")){
				$("#regFrm").attr('action','/board/boardInsert.do') ;
				$("#regFrm").submit();
			}
		}
	});	
});
</script>

<form name="regFrm" id="regFrm" method="post" enctype="multipart/form-data">
<input type="hidden" name="currentPage" id="currentPage" value="${info.currentPage}" />
<input type="hidden" name="stDt" id="stDt" value="${info.stDt}" />
<input type="hidden" name="enDt" id="enDt" value="${info.enDt}" />	
<input type="hidden" name="searchKey" id="searchKey" value="${info.searchKey}" />								
<input type="hidden" name="searchValue" id="searchValue" value="${info.searchValue}" />
<input type="hidden" name="bbsCd" id="bbsCd" value="${info.bbsCd}" />
	<table summary="게시판 기본정보 입력 항목입니다">
		<caption>게시판 입력 항목</caption>
		<colgroup>
			<col width="15%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" align="left"><span>*</span> 코드번호</th>
				<td>
				<table>
					<tr><td>
					<input type="text" id="codeNum" name="codeNum" class="w180"
					v:required='trim' m:required="종목코드를 입력하십시오." maxlength=6/>
					</td>
					<td>
					<div id=checkCodeNum></div>
					</td>
					</tr>
				</table>					
				</td>
			</tr>
			<tr>
				<th scope="row" align="left"><span>*</span> 제목</th>
				<td>
					<input type="text" id="subject" name="subject" class="w280" 
					v:required='trim' m:required="제목을 입력하십시오."/>&nbsp;
				</td>
			</tr>
			<tr>
				<td colspan=2>&nbsp;</td>
			</tr>
			<tr>
				<td colspan=2>
					<div>
					<textarea  name="CONTENT" cols="150" rows="50"></textarea>
					
					<script type="text/javascript">
					//<![CDATA[
					CKEDITOR.replace('CONTENT');
					//]]
					</script>
					</div>
				</td>
			</tr>
		</tbody>
	</table>
<div class="fltr mar_t10">
	<a href="#"><img id="okBtn" src="/resource/images/common/btn_inner_ok3.gif" alt="OK" /></a>
	<a href="#"><img id="listBtn" src="/resource/images/common/btn_cancel2.gif" alt="목록" /></a>	
</div>
	<input type="hidden" name="atchFileNm" value=""/>
	<input type="file" id="atchFile" name="atchFile"/>
</form>
