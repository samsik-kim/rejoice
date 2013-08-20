<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
body{
	background:#ffffff;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	scrollbar-face-color: #d1d1d1;
	scrollbar-shadow-color: #cccccc;
	scrollbar-highlight-color: #f1f1f1;
	scrollbar-3dlight-color: #ffffff;
	scrollbar-darkshadow-color: #ffffff;
	scrollbar-track-color: #f1f1f1;
	scrollbar-arrow-color: #333333;
} 

td {
	font-size: 9pt;
	text-decoration: none; 
	line-height:160%;
	font-family:굴림;
	color:#666666;
}

/**도움말bg**/
.help_top_bg{background-image:url(/images_n/help_pop_titlebox_center.gif); background-repeat:repeat-x;}
.help_bom_bg{background-image:url(/images_n/help_pop_dotline.gif); background-repeat:repeat-x;}
.msg_gb_line{background-image:url(/images_n/ico_dot_line.gif); background-repeat:repeat-x;}
.msg_red{color:#E4716E;font-weight:bold;}
.help_mid_title{color:#2A2A2A;padding:0 0 0 17;text-align:left;}
.help_con{padding:0 0 0 5px;text-align:left;}
.icon_style{padding:6px 0 0 0;text-align:right;vertical-align:top;}
.img_style{padding:0 0 0 7px;}
</style>
<table width="800" height="100%" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td width="14">
			<img src="/images_n/help_pop_titlebox_left.gif" height="16"></td>		
		<td width="772" class="help_top_bg">
			<img src="/images_n/help_pop_titlebox_center.gif" height="16"></td>
		<td width="14">
			<img src="/images_n/help_pop_titlebox_right.gif" height="16"></td>
	</tr>	
	<tr>
		<td colspan="3" style="border-left:#D9DADD 8px solid;border-right:#D9DADD 9px solid;">
			<table width="783" border="0" cellpadding="0" cellspacing="0" bgcolor="#ffffff" align="center">
				<tr><td height="10"></td></tr>
				<tr>
					<td style="padding:0 0 0 30;">
						<img src="/images_n/help_pop_title.gif"></td>
				</tr>
				<tr><td height="5"></td></tr>
				<tr><td height="1" class="msg_gb_line"></td></tr>
				<tr><td height="15"></td></tr>	
				<tr>
					<td valign="top" style="padding:0 15 0 20;">
						<!--메시지내용-->
						<table width="686" border="0" cellpadding="0" cellspacing="0" align="center">						
							<tr>
								<td colspan="2" class="msg_red">
									<img src="/images_n/ico_arrow2.gif" align="absmiddle"> 대상파일 업로드 							
								</td>
							</tr>		
							<tr><td height="5"></td></tr>
							<tr>
								<td colspan="2" class="help_mid_title">
									수신자 정보인 대상파일 작성에 대한 도움말 입니다.
                                    		
								</td>
							</tr>
							<tr><td height="10"></td></tr>
							<tr>
								<td width="20" class="icon_style">
									<img src="/images_n/help_pop_icon.gif" align="absmiddle"></td>
								<td width="666" class="help_con">
					발송대상파일은 텍스트와 xls 파일 형태로만 업로드 가능합니다.(xlsx 파일 형식 불가)
								</td>
							</tr>
							<tr><td height="10"></td></tr>
							
							<tr>
								<td class="icon_style">
									<img src="/images_n/help_pop_icon.gif" align="absmiddle"></td>
								<td class="help_con">
										텍스트 파일의 형식은 “수신번호(숫자)|모델명|구분자” 형식으로 되어 있어야 합니다. (ex. 01012341234| AIP-R16|올레클럽)
								</td>
							</tr><tr><td height="10"></td></tr>
							<tr>
								<td class="icon_style">
									<img src="/images_n/help_pop_icon.gif" align="absmiddle"></td>
								<td class="help_con">
										xls파일의 형식은 <a href="전송대상자양식샘플.xls" target="_blank">양식다운로드</a> 파일 참조<br>
										xls파일 형식에 타이틀이 수정되면 업로드가 정상적으로 되지 않습니다. 또한 전송번호 필드는 텍스트형식
										이여야 합니다.
								</td>
							</tr><tr><td height="10"></td></tr>
							<tr>
								<td class="icon_style">
									<img src="/images_n/help_pop_icon.gif" align="absmiddle"></td>
								<td class="help_con">
					대상파일을 업로드 후, 일부 발송 부적합한 수신번호 정보와 발송 가능한 수신전호 정보만 모아 새로운 파일을 생성합니다. 해당 파일을 다운로드 받아 정보에 활용하시기 바랍니다


								</td>
							</tr>
                            					<tr><td height="10"></td></tr>
							<tr>
								<td class="icon_style">
									<img src="/images_n/help_pop_icon.gif" align="absmiddle"></td>
								<td class="help_con">
						파일 적용시에 발송신청건수는 전체로, 발송건수는 발송 가능 건수로 책정됩니다.<br />
 &nbsp;예 : 전체 수신자 정보 500건, 발송 가능 한 수신정보는 400건인 경우,<br />
     &nbsp;&nbsp;&nbsp;&nbsp; 발송신청건수는 500건, 발송건수는 400건으로 책정되며, 이중 300건만 수신 성공인경우, <br />
     &nbsp;&nbsp;&nbsp;&nbsp; 발송실패건수는 200건으로 집계된다. </td>
							</tr>
                            
						</table>
						<!--메시지내용-->
					</td>
				</tr>				
				<tr><td height="15"></td></tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3" bgcolor="#D9DADD">
			<table width="783" border="0" cellpadding="0" cellspacing="0" align="center">
				<tr><td height="5"></td></tr>
				<tr><td height="1" class="help_bom_bg"></td></tr>
				<tr><td height="5"></td></tr>	
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="3" align="right" style="padding:5 9 5 0;" bgcolor="#D9DADD">
			<a href="javascript:top.close();">
			<img src="/images_n/message_ico_pop_close.gif" align="absmiddle" border="0"></a>
		</td>
	</tr>
	<tr><td height="100%"></td></tr>
</table>