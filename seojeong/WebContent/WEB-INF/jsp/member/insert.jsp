<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="tstyleA">
		<form id="personForm" name="personForm" method="post">
			<table summary="회원기본정보 입력 항목입니다">
				<caption>회원기본정보 입력 항목</caption>
				<colgroup>
					<col width="15%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row" class="tit03">이름</th>
						<td></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="userid">아이디</label></th>
						<td><input type="text" id="mbrId" name="member.mbrId" class="w180" />&nbsp;   
						<a href="#"></a><span class="txtcolor01"> &nbsp;* 최대 8-13자의 영대소문자, 숫자 가능, 특수문자 사용 불가</span></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="email1">이메일</label></th>
						<td><input type="text" id="emailAddr" name="member.emailAddr" class="w180"/>&nbsp;
						<a href="#">중복확인</a></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="pwnum">비밀번호</label></th>
						<td><input type="password" id="mbrPw" name="member.mbrPw" class="w180"/>
						<span class="txtcolor01"> &nbsp;* 6-16자의 영대소문자, 숫자, 특수문자만 사용, 공백입력 불가</span>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="pwnuncon">비밀번호 확인</label></th>
						<td><input type="password" id="pwnuncon" name="pwnuncon" class="w180"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="address">주소</label></th>
						<td><input type="text" id="homeAddrDtl" name="member.homeAddrDtl" class="w376"/></td>
					</tr>
					<tr>
						<th scope="row" class="tit03"><label for="phonenum">전화번호</label></th>
						<td>
							<div class="fltl pad_r4">
								<select id="phonenum" class="w67" name="phone">
									<option value=""></option>
								</select>
							</div>
							<input type="text" class="w109" id="phonenum2" name="phonenum2" />
							<span class="txtcolor01"> &nbsp;* “ㅡ” 을 생략하고 숫자로만 입력해 주세요.</span>
							<input type="hidden" name="member.telNo" id="devTelNo"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="moblenum">휴대폰 번호</label></th>
						<td><input type="text" id="devHpNo" name="member.hpNo" class="w180"/>
						<span class="txtcolor01">  &nbsp;* “ㅡ” 을 생략하고 숫자로만 입력해 주세요.</span></td><!-- text추가 -->
					</tr>    
				</tbody>
			</table>
			</form>				
		</div>