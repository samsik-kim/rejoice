<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/resource/iphone/css/set.css">
<script type="text/javascript" src="/resource/iphone/js/iphone.js"></script>

<!-- Tab -->
<h3 class="tab">
	<img src="/resource/images/crm_tab01_f.png" alt="나의 작업" />
	<a href="/crm/regMsgForm.do"><img src="/resource/images/crm_tab02_n.png" alt="메시지 등록" /></a>
</h3>
      
<!-- Title -->
<h3 class="tit">나의 작업 상세보기</h3>

<!-- Table Area -->
<div class="contents_left">
<!-- Table Area -->
<form action="#" method="post">

	<fieldset>
		<legend class="hidden">입력</legend>
		<table summary="리스트" class="WriteTable">
			<caption>리스트</caption>
			<colgroup>
				<col width="22%" />
				<col width="*" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row" class="l">메시지 구분</th>
					<td class="l">
						<input type="checkbox" id="" name="" /> <label for="">SMS</label>
						<span style="padding-left:30px;"><input type="checkbox" id="" name="" /> <label for="">MMS</label></span>
					</td>
				</tr>
			</tbody>
		</table>
	</fieldset>
          
	<fieldset style="padding-top:10px;">
		<legend class="hidden">입력</legend>
		<table summary="리스트" class="WriteTable">
			<caption>리스트</caption>
			<colgroup>
				<col width="22%" />
				<col width="*" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row" class="l">작업명</th>
					<td class="l"><input type="text" id="" class="iType" style="width:370px;" /></td>
				</tr>
				<tr>
					<th scope="row" class="l">발송 대상정보</th>
					<td class="l"><input type="text" id="" class="iType" style="width:370px;" /> <span class="fontS">예: 서울 거주 30대 이상 남녀</span></td>
				</tr>
				<tr>
					<th scope="row" class="l">기타정보</th>
					<td class="l"><input type="text" id="" class="iType" style="width:370px;" /> <span class="fontS">기타정보는 관련부서 협의 내용 등을 기입합니다.</span></td>
				</tr>
				<tr>
					<th scope="row" class="l">부서장 확인사항</th>
					<td class="l"><input type="text" id="" class="iType" style="width:370px;" /></td>
				</tr>
			</tbody>
		</table>
	</fieldset>

	<fieldset style="padding-top:10px;">
		<legend class="hidden">입력</legend>
		<table summary="리스트" class="WriteTable">
			<caption>리스트</caption>
			<colgroup>
				<col width="22%" />
				<col width="*" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row" class="l">메시지 특성</th>
					<td class="l">
						<select id="" class="sType" style="width:220px;">
							<option value="">선택</option>
							<option value=""></option>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row" class="l">4대 검토 확인사항</th>
					<td class="l">
						<input type="checkbox" id="" name="" /> <label for="">법률</label>
						<span style="padding-left:30px;"><input type="checkbox" id="" name="" /> <label for="">정보보호</label></span>
						<span style="padding-left:30px;"><input type="checkbox" id="" name="" /> <label for="">리스크</label></span>
						<span style="padding-left:30px;"><input type="checkbox" id="" name="" /> <label for="">공정경쟁</label></span>
					</td>
				</tr>
			</tbody>
		</table>
	</fieldset>

	<fieldset style="padding-top:10px;">
		<legend class="hidden">입력</legend>
		<table summary="리스트" class="WriteTable">
			<caption>리스트</caption>
			<colgroup>
				<col width="22%" />
				<col width="*" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row" class="l">회신번호</th>
					<td class="l">
						<select id="" class="sType" style="width:220px; margin-bottom:5px">
							<option value="">선택</option>
							<option value=""></option>
						</select>
						<input type="text" id="" class="iType" style="width:370px;" />
					</td>
				</tr>
				<tr>
					<th scope="row" class="l">문의처 정보<span style="font-size:11px; display:inline-block; line-height:18px">(링크/URL)</span></th>
					<td class="l">
						<select id="" class="sType" style="width:220px; margin-bottom:5px">
							<option value="">선택</option>
							<option value=""></option>
						</select>
						<input type="text" id="" class="iType" style="width:370px;" />
					</td>
				</tr>
			</tbody>
		</table>
	</fieldset>

	<fieldset style="padding-top:10px;">
		<legend class="hidden">입력</legend>
		<table summary="리스트" class="WriteTable">
			<caption>리스트</caption>
			<colgroup>
				<col width="22%" />
				<col width="*" />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row" class="l">발송 문자건수</th>
					<td class="l">
						<input type="text" id="" class="iType" style="width:220px;" />
					</td>
				</tr>
			</tbody>
		</table>
	</fieldset>
</form>
      
</div>

<!-- Table Area -->
<div class="contents_right">

<form name="form1" method="post">
<input type="hidden" name="job_mode" value="mms" alt="sms or mms">
<input type="hidden" name="img_add_yn" value="y" alt="이미지 추가여부 mms모드 변경하기 위해">
<input type="hidden" name="rMode" value="insert" alt="현재모드정보 - insert, modify">
<table width="259px" height="574px" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td colspan="3" class="hp_top_bg" style="background-image:url(/resource/iphone/images/iphone_bg_01.gif);" width="259px" height="133px">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<input type="text" style="width:145px;" name="msgTitle" value="">
					</td>
					<td>
						<a href="javascript:void(0);" onclick="viewMyBox();">
							<img src="/resource/iphone/images/ico_my_space.gif" alt="개인보관함" border="0">
						</a>
					</td>
				</tr>
			</table>

		</td>
	</tr>
	<tr>
		<td>
			<img src="/resource/iphone/images/iphone_bg_02.gif" width="21px" height="198px" alt=""></td>
		<td class="hp_mid_bg" style="background-image:url(/resource/iphone/images/iphone_bg_03.gif);" width="219px" height="198px">
			<!--메시지입력-->
			<textarea name="msg" style="width:209px; height:170px;border:none;padding:5px 0 5px 0;display:none;" onKeyUp="chkBytes2(form1);"></textarea>
			<textarea name="msg_test" id="msg_test" style="width:209px; height:170px;border:none;padding:5px 0 5px 0;display:none;"></textarea>

			<iframe id="textWindow" name="textWindow" marginWidth="0" marginHeight="0" frameBorder="0" width="209px" height="160px" style="visibility:visible;overflow-y:auto;overflow-x:hidden;" onload="iframeOn(form1);"></iframe>

			<div class="msg_length">
				<span id="msg_length" name="msg_length" class="msg_red">0</span>/90byte(90byte넘으면 MMS로 발송)
			</div>
			<!--메시지입력-->
		</td>
		<td>
			<img src="/resource/iphone/images/iphone_bg_04.gif" width="19px" height="198px" alt=""></td>
	</tr>
	<tr>
		<td colspan="3" class="hp_bom_bg" style="background-image:url(/resource/iphone/images/iphone_bg_05.gif); " width="259px" height="243px">

			<table width="219px" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td align="center" style="padding:5px 0 0 0;">
						<!--특수아이콘-->
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td>
                                <ul class="spc">
									<li><a class="spcicon" href="javascript:addMsg('★')">★</a></li>
									<li><a class="spcicon" href="javascript:addMsg('☆')">☆</a></li>
									<li><a class="spcicon" href="javascript:addMsg('○')">○</a></li>
									<li><a class="spcicon" href="javascript:addMsg('●')">●</a></li>
									<li><a class="spcicon" href="javascript:addMsg('◎')">◎</a></li>
									<li><a class="spcicon" href="javascript:addMsg('◇')">◇</a></li>
									<li><a class="spcicon" href="javascript:addMsg('◆')">◆</a></li>
									<li><a class="spcicon" href="javascript:addMsg('□')">□</a></li>
									<li><a class="spcicon" href="javascript:addMsg('■')">■</a></li>
									<li><a class="spcicon" href="javascript:addMsg('△')">△</a></li>
									<li><a class="spcicon" href="javascript:addMsg('▲')">▲</a></li>
                                </ul>    
								</td>
							</tr>
							<tr><td height="2px"></td></tr>
							<tr>
								<td align="center">
                                <ul class="spc">
									<li><a class="spcicon" href="javascript:addMsg('▽')">▽</a></li>
									<li><a class="spcicon" href="javascript:addMsg('▼')">▼</a></li>
									<li><a class="spcicon" href="javascript:addMsg('¶')">¶</a></li>
									<li><a class="spcicon" href="javascript:addMsg('▨')">▨</a></li>
									<li><a class="spcicon" href="javascript:addMsg('▥')">▥</a></li>
									<li><a class="spcicon" href="javascript:addMsg('♤')">♤</a></li>
									<li><a class="spcicon" href="javascript:addMsg('♠')">♠</a></li>
									<li><a class="spcicon" href="javascript:addMsg('♡')">♡</a></li>
									<li><a class="spcicon" href="javascript:addMsg('♥')">♥</a></li>
									<li><a class="spcicon" href="javascript:addMsg('♧')">♧</a></li>
									<li><a class="spcicon" href="javascript:addMsg('♣')">♣</a></li>
                                </ul>
								</td>
							</tr>
							<tr><td height="2px"></td></tr>
							<tr>
								<td align="center">
                                <ul class="spc">
									<li><a class="spcicon" href="javascript:addMsg('⊙')">⊙</a></li>
									<li><a class="spcicon" href="javascript:addMsg('◈')">◈</a></li>
									<li><a class="spcicon" href="javascript:addMsg('▣')">▣</a></li>
									<li><a class="spcicon" href="javascript:addMsg('▩')">▩</a></li>
									<li><a class="spcicon" href="javascript:addMsg('♨')">♨</a></li>
									<li><a class="spcicon" href="javascript:addMsg('☎')">☎</a></li>
									<li><a class="spcicon" href="javascript:addMsg('☜')">☜</a></li>
									<li><a class="spcicon" href="javascript:addMsg('☞')">☞</a></li>
									<li><a class="spcicon" href="javascript:addMsg('♩')">♩</a></li>
									<li><a class="spcicon" href="javascript:addMsg('♪')">♪</a></li>
									<li><a class="spcicon" href="javascript:addMsg('♬')">♬</a></li>
                                </ul>    
								</td>
							</tr>
							<tr><td height="2px"></td></tr>
							<tr>
								<td align="center">
                                <ul class="spc">
									<li><a class="spcicon" href="javascript:addMsg('∞')">∞</a></li>
									<li><a class="spcicon" href="javascript:addMsg('∴')">∴</a></li>
									<li><a class="spcicon" href="javascript:addMsg('∽')">∽</a></li>
									<li><a class="spcicon" href="javascript:addMsg('※')">※</a></li>
									<li><a class="spcicon" href="javascript:addMsg('㉿')">㉿</a></li>
									<li><a class="spcicon" href="javascript:addMsg('》')">》</a></li>
									<li><a class="spcicon" href="javascript:addMsg('♂')">♂</a></li>
									<li><a class="spcicon" href="javascript:addMsg('♀')">♀</a></li>
									<li><a class="spcicon" href="javascript:addMsg('∬')">∬</a></li>
									<li><a class="spcicon" href="javascript:addMsg('‡')">‡</a></li>
									<li><a class="spcicon" href="javascript:addMsg('￠')">￠</a></li>
                                </ul>    
								</td>
							</tr>
						</table>
						<!--특수아이콘-->
					</td>
				</tr>
				<tr>
					<td align="center" style="padding:5px 0 7px 0;">
						<img src="/resource/iphone/images/ico_img_add.gif" align="absmiddle" border="0" style="cursor:hand;" onclick="contentsUploadPop('img');">
						<img src="/resource/iphone/images/ico_mov_add.gif" align="absmiddle" border="0" style="cursor:hand;" onclick="contentsUploadPop('mov');">
						<img src="/resource/iphone/images/ico_param.gif" align="absmiddle" border="0" style="cursor:hand;" onclick="stringAdd(form1);" ></td>
				</tr>
				<tr>
					<td align="center">
						<!--컬러아이콘-->
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','black');">
									<img src="/resource/iphone/images/common_color_01.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','gray');">
									<img src="/resource/iphone/images/common_color_02.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','silver');">
									<img src="/resource/iphone/images/common_color_03.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','white');">
									<img src="/resource/iphone/images/common_color_04.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','maroon');">
									<img src="/resource/iphone/images/common_color_05.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','red');">
									<img src="/resource/iphone/images/common_color_06.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','purple');">
									<img src="/resource/iphone/images/common_color_07.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','Fuchsia');">
									<img src="/resource/iphone/images/common_color_08.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','green');">
									<img src="/resource/iphone/images/common_color_09.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','lime');">
									<img src="/resource/iphone/images/common_color_10.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','olive');">
									<img src="/resource/iphone/images/common_color_11.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','yellow');">
									<img src="/resource/iphone/images/common_color_12.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','navy');">
									<img src="/resource/iphone/images/common_color_13.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','blue');">
									<img src="/resource/iphone/images/common_color_14.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','teal');">
									<img src="/resource/iphone/images/common_color_15.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand('ForeColor','aqua');">
									<img src="/resource/iphone/images/common_color_16.gif" width="11px" height="12px"></td>
							</tr>
						</table>
						<!--컬러아이콘-->
					</td>
				</tr>
				<tr>
					<td>

						<table border="0" cellpadding="0" cellspacing="0">
							<tr><td height="1" bgcolor="#353535"></td></tr>
							<tr><td height="1" bgcolor="#646464"></td></tr>
						</table>

					</td>
				</tr>
				<tr>
					<td valign="top" align="center" style="padding:10px 0 3px 0;">

						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td valign="top" style="padding:2px 2px 0 0;">
									<img src="/resource/iphone/images/ico_arrow.gif" align="absmiddle" border="0"></td>
								<td class="msg_info_text">
									입력하신 내용이90 byte 미만인 경우,<br>
									이미지 첨부 및 글자색기능이 불가능합니다.
								</td>
							</tr>
							<tr>
								<td valign="top" style="padding:2px 2px 0 0;">
									<img src="/resource/iphone/images/ico_arrow.gif" align="absmiddle" border="0"></td>
								<td class="msg_info_text">
									구분자는 10개까지 입력 가능합니다.<br>
									도움말을 참조하세요.
									&nbsp;
									<span class="popup_start1" onclick="popwindow01('/app_new/help/help_202.html','817px','330px');"><u>>> 도움말</u></span>
								</td>
							</tr>
						</table>

					</td>
				</tr>
			</table>

		</td>
	</tr>
</table>
</form>
</div>

<!-- Btn -->
<p class="btnC" style="position:relative;top:-40px">
	<a href="/crm/list.do" class="btn_red"><strong>목 록</strong></a>
</p>