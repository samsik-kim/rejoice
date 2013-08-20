<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<style>
	html,body{background:#fff;}
	html {overflow:hidden;}
</style>
</head>

<script type="text/javascript">

$(function(){
		
	$("#goModify").click(function(){
		$("#frm").attr("action","/user/popup/listInfoDetailModifyPop.do").submit();
	});
	
});



</script>

<div id="popup" style="width:400px;">

<!-- pTop -->
<div class="pTop">
	<h2>사용자 상세보기</h2>
</div>
<hr />

<!-- pContents -->
<div class="pContents">

	<!-- Table Area -->
	<form name="frm" id="frm" method="post">
	<input type="hidden" name="userId" id="userId" value=${viewInfo.userId} >
		<table summary="쓰기" class="WriteTable">
			<caption>리스트</caption>
			<colgroup>
				<col width="25%" />
				<col width="*" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row" class="l" style="width:70px">부서명</th>
					<td class="l">${viewInfo.userRlvnDeptNm}</td>
				</tr>
				<tr>
				  <th scope="row" class="l" style="width:70px">사용자ID</th>
				  <td class="l">${viewInfo.userId}</td>
			  </tr>
				<tr>
				  <th scope="row" class="l" style="width:70px">사용자명</th>
				  <td class="l">${viewInfo.userNm}</td>
			  </tr>
				<tr>
				  <th scope="row" class="l" style="width:70px">비밀번호</th>
				  <td class="l">******</td>
			  </tr>
				<tr>
				  <th scope="row" class="l" style="width:70px">권한선택</th>
				  <td class="l">${viewInfo.userAutVal}</td>
			  </tr>
			  	<tr>
				  <th scope="row" class="l" style="width:70px">발송승인자</th>
	  			  <td class="l">${viewInfo.basApvrId}</td>
			  </tr>
				<tr>
				  <th scope="row" class="l" style="width:70px">전화번호</th>
	   			  <td class="l">${viewInfo.genlTelNo}</td>
			  </tr>
				<tr>
				  <th scope="row" class="l" style="width:70px">휴대폰번호</th>
				  <td class="l">${viewInfo.mphonNo}</td>
			  </tr>
				<tr>
				  <th scope="row" class="l" style="width:70px">이메일</th>
				  <td class="l">${viewInfo.emailAdr}</td>
			  </tr>
			</tbody>
		</table>
	</form>
</div>
<hr />

<!-- pBottom -->
<div class="pBottom_detailPop">
	<a href="#" id="goModify" class="btn_red"><strong>수 정</strong></a>
	<a href="javascript:window.open('','_self').close();" class="btn_red"><strong>닫 기</strong></a>
</div>

</div>
