<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
		
<!-- Title -->
<h3 class="tit">사전 예약</h3>

<!-- Help -->
<div class="help">
	사전예약 화면으로 사전예약후 사전예약승인번호를 발급 받는 화면입니다.<br />
	사전예약승인번호가 있어야 발송등록 요청이 가능합니다.<br />
	승인된 예약번호를 클릭하면 예약번호가 복사 됩니다.
	<span><a href="#" class="btn_help"><strong>화면도움말</strong></a></span>
</div>

<!-- Table Area -->
<div>
	<table summary="리스트" class="WriteTable">
		<caption>리스트</caption>
		<tbody>
			<tr>
				<th scope="row" class="l">구 분</th>
				<td class="l">
                      <fieldset>
                          <input name="" type="radio" value="" /><label for="">피쳐폰</label>
                          <input name="" type="radio" value="" style="margin-left:20px" /><label for="">스마트폰</label>
                      </fieldset>
				</td>
				<th class="l">상 태</th>
				<td class="l" style="width:200px">
                      <fieldset>
                          <input name="" type="radio" value="" /><label for="">사용</label>
                          <input name="" type="radio" value="" style="margin-left:20px" /><label for="">사용안함</label>
                      </fieldset>
				</td>
			</tr>
			<tr>
				<th scope="row" class="l">내 용</th>
				<td colspan="3" class="l">
					<input name="" type="text" id="" style="width:600px" />
				</td>
			</tr>
			<tr>
				<th scope="row" class="l">배너파일</th>
				<td colspan="3" class="l">
					<input name="" type="text" id="" style="width:515px" /> <a href="#" class="btn_sml"><span>파일찾기</span></a>
				</td>
			</tr>
		</tbody>
	</table>

	<!-- Btn -->
	<p class="btnR" style=" padding-bottom:30px">
		<a href="#" class="btn_red"><strong>등 록</strong></a> <a href="#" class="btn_red"><strong>취 소</strong></a> <a href="#" class="btn_red"><strong>목 록</strong></a>
	</p> 
          
 </div>

	
