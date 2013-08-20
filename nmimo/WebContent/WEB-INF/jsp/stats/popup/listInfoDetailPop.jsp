<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<style>
	html,body{background:#fff;}
</style>

<div id="popup" style="width:900px;">

<!-- pTop -->
<div class="tit">
	<h2>전송 정보</h2>
</div>
<hr />

<!-- pContents -->
<div class="pContents">
	<table width="100%">
		<tr>
			<td>전송완료된 결과 및 상세 정보를 조회하는 화면입니다.</td>
			<td align="right"><input type="image" src="/resource/images/btn_help.gif" /></td>
		</tr>
	</table>
	<br>
	<!-- Table Area -->
	<table summary="리스트" class="ListTable">
		<caption>리스트</caption>
		<colgroup>
			<col width="25%" />
			<col width="*" />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row" class="l">작업명(제목)</th>
				<td class="l" colspan="5">test검증</td>
			</tr> 
			<tr>
				<th scope="row" class="l">발송부서</th>
				<td class="l">기술팀</td>
				<th scope="row" class="l">발송자</th>
				<td class="l">사용자</td>
				<th scope="row" class="l">발송일</th>
				<td class="l">2013.04.25</td>
			</tr>
			<tr>
				<th scope="row" class="l">총 요청</th>
				<td class="l">80</td>
				<th scope="row" class="l">실패</th>
				<td class="l">8</td>
				<th scope="row" class="l">성공</th>
				<td class="l">72</td>
			</tr>
			<tr>
				<th scope="row" class="l">전송대기</th>
				<td class="l">0</td>
				<th scope="row" class="l">결과대기</th>
				<td class="l">0</td>
				<th scope="row" class="l">중복11111111</th>
				<td class="l">0</td>
			</tr>
		</tbody>
	</table>

</div>
<hr />

<!-- pBottom -->
<div align="right" >
	<input type="image" src="/resource/images/btn_number.gif" />
	<input type="image" src="/resource/images/btn_excelsave.gif" onclick="javascript:Common.centerPopupWindow('/stats/popup/excelSaveCallNumberPop.do','window',{width:420,height:195,scrollBars:'no'});"/>
</div>

</div>