<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<mimo:userinfo var="user_session" />

<link rel="stylesheet" type="text/css" href="/resource/iphone/css/set.css">
<script type="text/javascript" src="/resource/iphone/js/iphone.js"></script>

<script type="text/javascript">


//HTML 생성 후 MMS기능 차단여부 (S = SMS, M = MMS)
$(document).ready(function() { 
	if("${viewInfo.msgDivVal}"=="S"){			
		goMsgChk(true, "${viewInfo.msgDivVal}");
	}else{		
		goMsgChk(false, "${viewInfo.msgDivVal}");
	}
	
	//발신번호, 문의처 정보 선택시 Div 처리
	setDisplayCallBack(document.getElementById("calbkNoTypeVal").value);
	setDispalyUrlInfo(document.getElementById("calbkUrlTypeVal").value);
	
	//발신번호, 문의처 정보 Data 세팅
	setDataCalbkNoTypeVal2(document.getElementById("calbkNoTypeVal").value);
	setDataUrlInfo2(document.getElementById("calbkUrlTypeVal").value);

	//메세지내용 Iframe 처리
	textWindow.document.open();
	textWindow.document.write("${viewInfo.dspTgtSbst}");
	textWindow.document.close();
	textWindow.document.designMode = 'on'; 
	textWindow.focus();
	thisForm.msgSbst.value = textWindow.document.body.innerHTML;	
	
	chkBytes(thisForm);
	
});

</script>

<!-- Title -->
<h3 class="tit">자동안내</h3>

<!-- Help -->
<div class="help">
	사용량통보 및 고객상태변경시 자동으로 발송되는 메시지 작성 화면입니다.	<span><a href="#" class="btn_help"><strong>화면도움말</strong></a></span>
</div>    
      
<!-- UserInfo -->
<div class="sbox">
	<fieldset>
		<legend class="hidden">사용자정보</legend>
		<label for="">사용자아이디</label> <input type="text" name="sessionUserId" id="sessionUserId" value=${user_session.userId} style="width:100px;"  /> 
		<label for="">사용자명</label> <input type="text" name="sessionUserNm" id="sessionUserNm" value=${user_session.userNm} style="width:100px;" />
		<label for="">부서명</label> <input type="text" name="sessionUserRlvnDeptNm" id="sessionUserRlvnDeptNm" value=${user_session.userRlvnDeptNm} style="width:150px;" />
		<label for="">발송승인자</label> <input type="text" name="sessionBasApvrId" id="sessionBasApvrId" value=${user_session.basApvrId} style="width:100px;" />
	</fieldset>
</div>

<form name="frm" id="frm" method="post">
<input type="hidden" name="hiddenMsgDivVal" id="hiddenMsgDivVal">
<input type="hidden" name="rMode" id="rMode" value="M" alt="현재모드정보 - insert, modify">
<input type="hidden" name="msgSbstHTML" id="msgSbstHTML">
<input type="hidden" name="msgSbstTEXT" id="msgSbstTEXT">
<input type="hidden" name="arrMultiFileName" id="arrMultiFileName" size=100>

<input type="hidden" name="feaponCalbkUrlVal" id="feaponCalbkUrlVal" value="${viewInfo.feaponCalbkUrlVal}" />
<input type="hidden" name="smphCalbkUrlVal" id="smphCalbkUrlVal" value="${viewInfo.smphCalbkUrlVal}" />
<input type="hidden" name="calbkUrlMemoSbst" id="calbkUrlMemoSbst" value="${viewInfo.calbkUrlMemoSbst}" />
<input type="hidden" name="feaponCalbkNo" id="feaponCalbkNo" value="${viewInfo.feaponCalbkNo}" />
<input type="hidden" name="smphCalbkNo" id="smphCalbkNo" value="${viewInfo.smphCalbkNo}" />

<div class="contents_left">
<!-- Table Area -->
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
					<th scope="row" class="l"><span onmouseover="viewDiv('div1');" onmouseout="hideDiv('div1');">메시지 구분</span></th>
					<div style="position:relative; z-index:1; left:0px; top:3px;">
						<div id="div1" style="position:absolute;left:85px;top:0px; width:170px; height:20px;background-color:#ffffff;z-index:1;display:none;border:solid #d1d1d1 1px;visibility:visible;padding:5px;font-size:12px;font-weight:normal;">
							메시지구분을 선택하세요.
						</div>
					</div>
					<td class="l">
						<input type="radio" name="msgDivVal" id="msgDivValS" style="cursor:hand;" onClick="goMsgChk(true,this.value);" value="S" <c:if test="${viewInfo.msgDivVal =='S'}">checked</c:if> />SMS
						<span style="padding-left:30px;">
						<input type="radio" name="msgDivVal" id="msgDivValM" style="cursor:hand;" onClick="goMsgChk(false,this.value);" value="M" <c:if test="${viewInfo.msgDivVal =='M'}">checked</c:if>/>MMS
						</span>
					</td>
				</tr>
				<tr>
					<th scope="row" class="l"><span onmouseover="viewDiv('div2');" onmouseout="hideDiv('div2');">메세지 특성</span></th>
					<div style="position:relative; z-index:1; left:0px; top:40px;">
						<div id="div2" style="position:absolute;left:104px;top:0px; width:270px; height:20px;background-color:#ffffff;z-index:1;display:none;border:solid #d1d1d1 1px;visibility:visible;padding:5px;font-size:12px;font-weight:normal;">
							메시지 내용 및 목적에 따른 특성을 선택합니다.
						</div>
					</div>					
					<td class="l">
						<select name="msgCharVal" id="msgCharVal" onchange="addHead(frm,this.value)" class="sType" style="width:220px;">
							<option value="0" <c:if test="${viewInfo.msgCharVal == '0'}">selected</c:if>>선택</option>
							<option value="1" <c:if test="${viewInfo.msgCharVal == '1'}">selected</c:if>>공지</option>
							<option value="2" <c:if test="${viewInfo.msgCharVal == '2'}">selected</c:if>>홍보</option>
							<option value="3" <c:if test="${viewInfo.msgCharVal == '3'}">selected</c:if>>광고</option>
							<option value="4" <c:if test="${viewInfo.msgCharVal == '4'}">selected</c:if>>제휴광고</option>
							<option value="5" <c:if test="${viewInfo.msgCharVal == '5'}">selected</c:if>>업무</option>
						</select>
					</td>
				</tr>
				<tr>
					<th scope="row" class="l"><span onmouseover="viewDiv('div3');" onmouseout="hideDiv('div3');">작업 유형</span></th>
					<div style="position:relative; z-index:1; left:0px; top:77px;">
						<div id="div3" style="position:absolute;left:78px;top:0px; width:240px; height:20px;background-color:#ffffff;z-index:1;display:none;border:solid #d1d1d1 1px;visibility:visible;padding:5px;font-size:12px;font-weight:normal;">
							상담사용 작업인 경우 체크 합니다.
						</div>
					</div>
					<td class="l"><input type="checkbox" name="wrkPathVal" id="wrkPathVal" <c:if test="${viewInfo.wrkPathVal=='C'}">checked</c:if> /> 상담사 전용</td>
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
					<th scope="row" class="l"><span onmouseover="viewDiv('div4');" onmouseout="hideDiv('div4');">작업명</span></th>
					<div style="position:relative; z-index:1; left:0px; top:40px;">
						<div id="div4" style="position:absolute;left:78px;top:0px; width:240px; height:20px;background-color:#ffffff;z-index:1;display:none;border:solid #d1d1d1 1px;visibility:visible;padding:5px;font-size:12px;font-weight:normal;">
							메시지 내용을 알수 있게 작성합니다.
						</div>
					</div>
					<td class="l"><input type="text" name="wrkNm" id="wrkNm" class="iType" style="width:370px;" value="${viewInfo.wrkNm}"/></td>
				</tr>
				<tr>
					<th scope="row" class="l"><span onmouseover="viewDiv('div5');" onmouseout="hideDiv('div5');">발신번호</span></th>					
					<div style="position:relative; z-index:1; left:0px; top:75px;">
						<div id="div5" style="position:absolute;left:90px;top:0px; width:480px; height:50px;background-color:#ffffff;z-index:1;display:none;border:solid #d1d1d1 1px;visibility:visible;padding:5px;font-size:12px;font-weight:normal;">
							수신자에게 발신번호로 표시되며, 통화 버튼을 누르면 연결(통화)되는 번호입니다.<br>
							고객센터 핫넘버(**010)선택시, 스마트폰에서는 연결(통화) 되지 않아<br>
							스마트폰용 발신번호를 확인 후 입력해야 합니다.
						</div>
					</div>					
					<td class="l">
						<select name="calbkNoTypeVal" id="calbkNoTypeVal" class="sType" style="width:220px; margin-bottom:5px" onchange="setDisplayCallBack(this.value);">
							<option value="0" <c:if test="${viewInfo.calbkNoTypeVal == '0'}">selected</c:if>>선택</option>
							<option value="1" <c:if test="${viewInfo.calbkNoTypeVal == '1'}">selected</c:if>>고객센터</option>
							<option value="2" <c:if test="${viewInfo.calbkNoTypeVal == '2'}">selected</c:if>>전담콜센터</option>
							<option value="3" <c:if test="${viewInfo.calbkNoTypeVal == '3'}">selected</c:if>>고객센터(114)</option>
						</select>
						<div id="callBackDiv1" style="display:none">
							※ 피처폰 :&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="calbkNoTypeVal1" id="calbkNoTypeVal1" class="iType" style="width:230px;" value="**010" readOnly/><br>
							※ 스마트폰 :&nbsp;<input type="text" name="calbkNoTypeVal2" id="calbkNoTypeVal2" class="iType" style="width:230px;" /><br>
							스마트폰은**010표기시 연결되지 않습니다.<br>
							고객이 통화 가능한 번호로 입력해 주시기 바랍니다.
						</div>
						<div id="callBackDiv2" style="display:none">
							※ 번호입력 :&nbsp;<input type="text" name="calbkNoTypeVal3" id="calbkNoTypeVal3" class="iType" style="width:230px;" /><br>
						</div>
					</td>
				</tr>
				<tr>
					<th scope="row" class="l"><span onmouseover="viewDiv('div6');" onmouseout="hideDiv('div6');">문의처 정보</span><span style="font-size:11px; display:inline-block; line-height:18px">(링크/URL)</span></th>
					<div style="position:relative; z-index:1; left:0px; top:115px;">
						<div id="div6" style="position:absolute;left:110px;top:0px; width:480px; height:50px;background-color:#ffffff;z-index:1;display:none;border:solid #d1d1d1 1px;visibility:visible;padding:5px;font-size:12px;font-weight:normal;">
							문자 내용중 상세 문의처 표기에 대한 부분으로,<br>
							상세 페이지로 링크 되도록 정확한 링크 주소를 입력합니다.<br>
							모바일블로그 선택시에도 해당내용 확인 할 수 있는 상세 주소를 입력합니다.
						</div>
					</div>
					<td class="l">
						<select name="calbkUrlTypeVal" id="calbkUrlTypeVal" class="sType" style="width:220px; margin-bottom:5px" onChange="setDispalyUrlInfo(this.value);">
							<option value="0" <c:if test="${viewInfo.calbkUrlTypeVal == '0'}">selected</c:if>>선택</option>
							<option value="1" <c:if test="${viewInfo.calbkUrlTypeVal == '1'}">selected</c:if>>모바일블로그</option>
							<option value="2" <c:if test="${viewInfo.calbkUrlTypeVal == '2'}">selected</c:if>>URL직접입력</option>
							<option value="3" <c:if test="${viewInfo.calbkUrlTypeVal == '3'}">selected</c:if>>www.olleh.com</option>
						</select><br>
						<div id="urlInfoDiv1" style="display:none">
							※ blog 주소 :&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="calbkUrlTypeVal1" id="calbkUrlTypeVal1" class="iType" style="width:230px;" value="mobileblog.olleh.com" readOnly /><br>
							※ 기타 상세주소 : <input type="text" name="calbkUrlTypeVal2" id="calbkUrlTypeVal2" class="iType" style="width:230px;" /><br> 
						</div>
						<div id="urlInfoDiv2" style="display:none">
							<input type="text" name="calbkUrlTypeVal3" id="calbkUrlTypeVal3" class="iType" style="width:230px;" /><br>
							롤백 링크는 고객에게 과금되지 않도록 처리해야 합니다.<br>
							<input type="text" name="calbkUrlTypeVal4" id="calbkUrlTypeVal4" class="iType" style="width:230px;" /><br>
							※ 모바일블로그를 선택하지 않은 사유<br>
						</div>
						<div id="urlInfoDiv3" style="display:none">
							※ 피쳐폰 :&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="calbkUrlTypeVal5" id="calbkUrlTypeVal5" class="iType" style="width:230px;" value="my.olleh.com" readOnly/><br>
							※ 스마트폰 :&nbsp;<input type="text" name="calbkUrlTypeVal6" id="calbkUrlTypeVal6" class="iType" style="width:230px;" value="m.olleh.com" readOnly /><br>
							콜백 링크는 고객에게 과금되지 않도록 처리해야 합니다.<br>
							<input type="text" name="calbkUrlTypeVal7" id="calbkUrlTypeVal7" class="iType" style="width:230px;" /><br>
							※ 모바일블로그를 선택하지 않은 사유<br>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
	</fieldset>
</div>

<!-- Table Area -->
<div class="contents_right" style="vertical-align:middle">


<table width="259px" height="574px" border="0" cellpadding="0" cellspacing="0" id="iphoneTable">
	<tr>
		<td colspan="3" class="hp_top_bg" style="background-image:url(/resource/iphone/images/iphone_bg_01.gif);" width="259px" height="133px">
			<table border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<input type="text" style="width:145px;" name="msgTitleNm" id="msgTitleNm" value="${viewInfo.msgTitleNm}">
					</td>
					<td>			
						<a href="#" onclick="javascript:Common.centerPopupWindow('/common/popup/myLibraryPop.do','window',{width:980,height:380,scrollBars:'no'});">
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
			<textarea name="msgSbst" id="msgSbst" style="width:209px; height:170px;border:none;padding:5px 0 5px 0; display:none;" onKeyUp="chkBytes(frm);"></textarea>
			<iframe name="textWindow" id="textWindow" marginWidth="0" marginHeight="0" frameBorder="0" width="209px" height="160px" style="visibility:visible;overflow-y:auto;overflow-x:hidden;" onload="iframeOn(frm);"></iframe>

			<div class="msg_length">
				<span name="msg_length" id="msg_length" class="msg_red">0</span>/90byte(SMS기준 90byte이하로 작성)
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
						<img src="/resource/iphone/images/ico_img_add.gif" align="absmiddle" border="0" style="cursor:hand;" onclick="javascript:Common.centerPopupWindow('/common/popup/imgFileUploadPop.do','window',{width:480,height:295,scrollBars:'no'});">
						<img src="/resource/iphone/images/ico_mov_add.gif" align="absmiddle" border="0" style="cursor:hand;" onclick="javascript:Common.centerPopupWindow('/common/popup/movFileUploadPop.do','window',{width:480,height:295,scrollBars:'no'});">
						<img src="/resource/iphone/images/ico_param.gif"  id="allAddBtn" align="absmiddle" border="0" style="cursor:hand;" onclick="stringAdd(frm);" ></td>
				</tr>
				<tr>
					<td align="center">
						<!--컬러아이콘-->
						<table border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','black');">
									<img src="/resource/iphone/images/common_color_01.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','gray');">
									<img src="/resource/iphone/images/common_color_02.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','silver');">
									<img src="/resource/iphone/images/common_color_03.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','white');">
									<img src="/resource/iphone/images/common_color_04.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','maroon');">
									<img src="/resource/iphone/images/common_color_05.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','red');">
									<img src="/resource/iphone/images/common_color_06.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','purple');">
									<img src="/resource/iphone/images/common_color_07.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','Fuchsia');">
									<img src="/resource/iphone/images/common_color_08.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','green');">
									<img src="/resource/iphone/images/common_color_09.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','lime');">
									<img src="/resource/iphone/images/common_color_10.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','olive');">
									<img src="/resource/iphone/images/common_color_11.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','yellow');">
									<img src="/resource/iphone/images/common_color_12.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','navy');">
									<img src="/resource/iphone/images/common_color_13.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','blue');">
									<img src="/resource/iphone/images/common_color_14.gif" width="11px" height="12px"></td>
								<td width="3"></td>
								<td class="msg_font_color" onclick="changeCommand(frm,'ForeColor','teal');">
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
									<img src="/resource/iphone/images/ico_arrow.gif" align="absmiddle" border="0">
								</td>
								<td class="msg_info_text">
									입력하신 내용이90 byte 미만인 경우,<br>
									이미지 첨부 및 글자색기능이 불가능합니다.
								</td>
							</tr>
							<tr>
								<td valign="top" style="padding:2px 2px 0 0;">
									<img src="/resource/iphone/images/ico_arrow.gif" align="absmiddle" border="0">
								</td>
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
</div>

</form>

<!-- Btn -->
<p class="btnC" style="position:relative;top:-10px">
<!-- 	<a href="#" onclick="javascript:Common.centerPopupWindow('/common/popup/previewPop.do','window',{width:610,height:625,scrollBars:'no'});" class="btn_red"><strong>미리보기</strong></a> -->
	<a href="#" id="goTempSave" class="btn_red"><strong>임시저장</strong></a>
    <a href="#" onclick="javascript:Common.centerPopupWindow('/review/popup/testSenderPop.do','window',{width:610,height:865,scrollBars:'no'});" class="btn_red"><strong>시험발송</strong></a>
    <a href="#" id="goNext" class="btn_red"><strong>승인요청</strong></a>
</p>