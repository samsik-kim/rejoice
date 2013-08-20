<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<link rel="stylesheet" type="text/css" href="/resource/iphone/css/set.css">
<script type="text/javascript" src="/resource/iphone/js/iphone.js"></script>
<script type="text/javascript">

//발신번호 선택시 Display변경
function chkCallBack(val){
	var v=val;
	
	if(v=="1"){
		document.getElementById("callBack1").style.display = "block";
		document.getElementById("callBack2").style.display = "none";
	}else if(v=="2" || v=="3"){
		document.getElementById("callBack1").style.display = "none";
		document.getElementById("callBack2").style.display = "block";
	}else{
		document.getElementById("callBack1").style.display = "none";
		document.getElementById("callBack2").style.display = "none";
	}
}


//URL정보 선택시 Display변경
function chkUrlInfo(val){
	var v=val;
	
	if(v=="1"){
		document.getElementById("urlInfo1").style.display = "block";
		document.getElementById("urlInfo2").style.display = "none";
		document.getElementById("urlInfo3").style.display = "none";
	}else if(v=="2"){
		document.getElementById("urlInfo1").style.display = "none";
		document.getElementById("urlInfo2").style.display = "block";
		document.getElementById("urlInfo3").style.display = "none";
		
	}else if(v=="3"){
		document.getElementById("urlInfo1").style.display = "none";
		document.getElementById("urlInfo2").style.display = "none";
		document.getElementById("urlInfo3").style.display = "block";
	
	}else{
		document.getElementById("urlInfo1").style.display = "none";
		document.getElementById("urlInfo2").style.display = "none";
		document.getElementById("urlInfo3").style.display = "none";
	}
}


//메세지 구분에 따른 Disabled
function goMsgChk(val){
	var v=val;
	var table = document.getElementById('iphoneTable');
	var links = table.getElementsByTagName('A');
	var imgs = table.getElementsByTagName('IMG');

	for (var i = 0; i < links.length; i++) {
	    links[i].disabled = v;
	}
	
	for (var i = 0; i < imgs.length; i++) {
		imgs[i].disabled = v;
	}
	
	//구분자 예외(msg구분없이 활성)
	document.getElementById('imgException').disabled=false;
}

</script>


<!-- Tab -->
<h3 class="tab">
	<a href="/crm/list.do"><img src="/resource/images/crm_tab01_n.png" alt="나의 작업" /></a>
	<img src="/resource/images/crm_tab02_f.png" alt="메시지 등록" />
</h3>       
       
<!-- Title -->
<h3 class="tit">메세지 등록</h3>

<!-- Table Area -->
<div class="contents_left">
<!-- Table Area -->
<form id="test" name="test" action="#" method="post">

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
						<input type="radio" id="msgType" name="msgType" onClick="goMsgChk(true);"/> <label for="">SMS</label>
						<span style="padding-left:30px;">
						<input type="radio" id="msgType" name="msgType" onClick="goMsgChk(false);"/><label for="">MMS</label>
						</span>
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
							<option value="">공지</option>
							<option value="">상품안내/홍보</option>
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
					<th scope="row" class="l">발신번호</th>
					<td class="l">
						<select id="callBackKind" name="callBackKind" class="sType" style="width:220px; margin-bottom:5px" onchange="chkCallBack(this.value);">
							<option value="0">선택</option>
							<option value="1">고객센터</option>
							<option value="2">전담콜센터</option>
							<option value="3">고객센터(114)</option>
						</select>
						<div id="callBack1" style="display:none">
							※ 피처폰 :&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="" class="iType" style="width:230px;" value="**010" /><br>
							※ 스마트폰 :&nbsp;<input type="text" id="" class="iType" style="width:230px;" /><br>
							스마트폰은**010표기시 연결되지 않습니다.<br>
							고객이 통화 가능한 번호로 입력해 주시기 바랍니다.
						</div>
						<div id="callBack2" style="display:none">
							※ 번호입력 :&nbsp;<input type="text" id="" class="iType" style="width:230px;" value="**010" /><br>
						</div>
						
					</td>
				</tr>
				<tr>
					<th scope="row" class="l">문의처 정보<span style="font-size:11px; display:inline-block; line-height:18px">(링크/URL)</span></th>
					<td class="l">
						<select id="urlInfoKind" class="sType" style="width:220px; margin-bottom:5px" onChange="chkUrlInfo(this.value)">
							<option value="0">선택</option>
							<option value="1">모바일블로그</option>
							<option value="2">URL직접입력</option>
							<option value="3">www.olleh.com</option>
						</select><br>
						<div id="urlInfo1" style="display:none">
							※ blog 주소 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="" class="iType" style="width:230px;" /><br>
							※ 기타 상세주소 : <input type="text" id="" class="iType" style="width:230px;" /><br> 
						</div>
						<div id="urlInfo2" style="display:none">
							<input type="text" id="" class="iType" style="width:230px;" /><br>
							롤백 링크는 고객에게 과금되지 않도록 처리해야 합니다.<br>
							<input type="text" id="" class="iType" style="width:230px;" /><br>
							※ 모바일블로그를 선택하지 않은 사유<br>
						</div>
						<div id="urlInfo3" style="display:none">
							※ 피쳐폰 :&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="" class="iType" style="width:230px;" /><br>
							※ 스마트폰 :&nbsp;<input type="text" id="" class="iType" style="width:230px;" /><br>
							콜백 링크는 고객에게 과금되지 않도록 처리해야 합니다.<br>
							<input type="text" id="" class="iType" style="width:230px;" /><br>
							※ 모바일블로그를 선택하지 않은 사유<br>
						</div>
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
						<span class="fontR">* 총 건수 : 20만<br/>* 발송 건수 : 5만</span>
					</td>
				</tr>
			</tbody>
		</table>
	</fieldset>
</form>
      
</div>

<!-- Table Area -->
<div class="contents_right" >

<form name="iphoneForm" id="iphoneForm" method="post">
<input type="hidden" name="job_mode" value="mms" alt="sms or mms">
<input type="hidden" name="img_add_yn" value="y" alt="이미지 추가여부 mms모드 변경하기 위해">
<input type="hidden" name="rMode" value="insert" alt="현재모드정보 - insert, modify">
<table width="259px" height="574px" border="0" cellpadding="0" cellspacing="0" id="iphoneTable">
	<tr>
		<td colspan="3" class="hp_top_bg" style="background-image:url(/resource/iphone/images/iphone_bg_01.gif);" width="259px" height="133px">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<input type="text" style="width:145px;" name="msgTitle" value="">
					</td>
					<td>
						<a href="#" onclick="javascript:pop('/common/popup/myLibraryPop.do','crmMyBoxPop','800','300');">
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
			<textarea name="msg" style="width:209px; height:170px;border:none;padding:5px 0 5px 0;display:none;" onKeyUp="chkBytes(iphoneForm);"></textarea>
			<textarea name="msg_test" id="msg_test" style="width:209px; height:170px;border:none;padding:5px 0 5px 0;display:none;"></textarea>

			<iframe id="textWindow" name="textWindow" marginWidth="0" marginHeight="0" frameBorder="0" width="209px" height="160px" style="visibility:visible;overflow-y:auto;overflow-x:hidden;" onload="iframeOn(iphoneForm);"></iframe>

			<div class="msg_length">
				<span id="msg_length" name="msg_length" class="msg_red">0</span>/90byte(90byte넘으면 MMS로 발송)
			</div>
			<!--메시지입력-->
		</td>
		<td>
			<img src="/resource/iphone/images/iphone_bg_04.gif" width="19px" height="198px" alt="">
		</td>
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
						<img src="/resource/iphone/images/ico_img_add.gif" align="absmiddle" border="0" style="cursor:hand;" onclick="javascript:pop('/crm/addImgPop.do','crmAddImgPop','800','300');">
						<img src="/resource/iphone/images/ico_mov_add.gif" align="absmiddle" border="0" style="cursor:hand;" onclick="javascript:pop('/crm/addMovPop.do','crmAddMovPop','800','300');">
						<img src="/resource/iphone/images/ico_param.gif" align="absmiddle" border="0" style="cursor:hand;" onclick="stringAdd(iphoneForm);" ></td>
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
	<a href="#" class="btn_red"><strong>초기화</strong></a> <a href="#" class="btn_red"><strong>시험발송</strong></a>
</p>


