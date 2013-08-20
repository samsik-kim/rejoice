<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
						<th scope="row" class="l">작업명</th>
						<td colspan="3" class="l">
                        	<input name="" type="text" id="" style="width:300px" />
                        </td>
					</tr>
					<tr>
						<th scope="row" class="l">발송희망일자</th>
						<td colspan="3" class="l">
                        	<input name="" type="text" id="" style="width:200px" />
					    	<a href="#" onclick="javascript:Common.centerPopupWindow('/common/popup/newCalendarPop.do','window',{width:350,height:570,scrollBars:'YES'});"><img src="/resource/images/icon_calendar.gif" /></a>
                        </td>
					</tr>
					<tr>
						<th scope="row" class="l">발송내용 요약</th>
						<td colspan="3" class="l">
                        	<input name="" type="text" id="" style="width:300px" />
                       </td>
					</tr>
					<tr>
						<th scope="row" class="l">수신대상 요약</th>
						<td colspan="3" class="l">
                        	<input name="" type="text" id="" style="width:300px" />
                       </td>
					</tr>
					<tr>
						<th scope="row" class="l">메시지 유형</th>
						<td class="l">
                            <select name="select" class="sType" id="select" style="width:220px;">
                              <option value="">선택</option>
                              <option value=""></option>
                            </select>
                        </td>
						<th class="l">발송건수</th>
						<td class="l" style="width:200px">&nbsp;</td>
					</tr>
				</tbody>
			</table>

			<!-- Btn -->
			<p class="btnR" style=" padding-bottom:30px">
				<a href="#" class="btn_red"><strong>사전예약 신청</strong></a>
		  	</p> 
            
		</div>
