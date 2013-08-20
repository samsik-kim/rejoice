<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<!-- Title -->
<h3 class="tit">나의 정보관리</h3>

<!-- Help -->
<div class="help">
	나의 개인정보 및 시험발송 대상정보를 관리하는 화면입니다.
	<span><a href="#" class="btn_help"><strong>화면도움말</strong></a></span>
</div>

<!-- Img Area -->
<div style="float:left; width:465px;"><img src="/resource/images/img_myinfo.jpg" alt="도움말이미지" /></div>

<!-- Table Area -->
<div style="float:right; width:500px;">
	<table summary="리스트" class="WriteTable">
		<caption>리스트</caption>
		<tbody>
			<tr>
				<th scope="row" class="l">부서명</th>
				<td class="l">
                      	<input name="" type="text" id="" style="width:200px">
              			<span class="fontSS">부서명을 정확하게 입력해 주세요.<br />부정확한 부서명 입력시 반려될 수 있습니다.</span>
           		</td>
			</tr>
			<tr>
				<th scope="row" class="l">권한</th>
				<td class="l">${viewInfo.userAutVal}</td>
			</tr>
			<tr>
				<th scope="row" class="l">발송승인자</th>
				<td class="l">
	               	<input name="" type="text" id="" style="width:150px"/><a href="#" onclick="javascript:Common.centerPopupWindow('/common/popup/approverPop.do','window',{width:419,height:325,scrollBars:'no'});" class="btn_sml"><span>검 색</span></a>
	       			<span class="fontSS">- 발송 승인자는 신청자의 부서장입니다.<br />
	                   - 발송 승인자 정보가 없으면 메시지 발송이 불가능 하오니 반드시 입력해야 합니다.</span>
                </td>
			</tr>
			<tr>
				<th scope="row" class="l">사용자명</th>
				<td class="l">${viewInfo.userNm}</td>
			</tr>
			<tr>
				<th scope="row" class="l">사용자ID</th>
				<td class="l">${viewInfo.userId}</td>
			</tr>
			<tr>
				<th scope="row" class="l">비밀번호</th>
				<td class="l">
	               	<input name="" type="password" id="" style="width:150px">
                </td>
			</tr>
			<tr>
				<th scope="row" class="l">비밀번호<br />재확인</th>
				<td class="l"><input name="" type="password" id="" style="width:150px"></td>
			</tr>
			<tr>
				<th scope="row" class="l">전화번호</th>
				<td class="l">${viewInfo.genlTelNo}</td>
			</tr>
			<tr>
				<th scope="row" class="l">휴대폰번호</th>
				<td class="l">${viewInfo.mphonNo}</td>
			</tr>
			<tr>
				<th scope="row" class="l">시험발송번호1</th>
				<td class="l">
	                <select id="" class="sType" style="width:50px;">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
	                </select>
                  - <input name="" type="text" id="" style="width:40px"> - <input name="" type="text" id="" style="width:40px">
				</td>
			</tr>
			<tr>
				<th scope="row" class="l">시험발송번호2</th>
				<td class="l">
			        <select id="" class="sType" style="width:50px;">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
			        </select>
			     - <input name="" type="text" id="" style="width:40px"> - <input name="" type="text" id="" style="width:40px">
				</td>
			</tr>
			<tr>
				<th scope="row" class="l">시험발송번호3</th>
				<td class="l">
					<select id="" class="sType" style="width:50px;">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
					</select>
					- <input name="" type="text" id="" style="width:40px"> - <input name="" type="text" id="" style="width:40px">
				</td>
			</tr>
			<tr>
				<th scope="row" class="l">시험발송번호4</th>
				<td class="l">
					<select id="" class="sType" style="width:50px;">
						<option value="010">010</option>
						<option value="011">011</option>
						<option value="016">016</option>
						<option value="017">017</option>
						<option value="018">018</option>
						<option value="019">019</option>
					</select>
					- <input name="" type="text" id="" style="width:40px"> - <input name="" type="text" id="" style="width:40px">
				</td>
		</tr>
			<tr>
				<th scope="row" class="l">이메일</th>
				<td class="l">${viewInfo.emailAdr}</td>
			</tr>
		</tbody>
	</table>

	<!-- Btn -->
	<p class="btnR" style=" padding-bottom:30px">
		<a href="#" class="btn_red"><strong>수정</strong></a>
		<a href="#" class="btn_red"><strong>확인</strong></a>
	</p>
</div>
