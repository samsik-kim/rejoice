<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<script type="text/javascript">
$(document).ready(function(){
<c:if test="${calculateInfo.dispStat eq 'POP'}">
	createPopupLayer('pop_area');
	$('#pop_area01').show();
	$("#pop_area_body").html($('#pop_area01'));
	showPopupLayer('pop_area', 'wrap');
	$('#mm').css('visibility','visible');
	$('#yyyy').css('visibility','visible');
</c:if>
<c:if test="${calculateInfo.dispStat ne 'POP'}">
	$("#pop_area01").hide();
</c:if>

	$('#btnOk').click(function(){
		<c:if test="${calculateInfo.dispStat ne 'POP'}">
			alert("<gm:string value='jsp.member.mypage.msg.com03'/>");
		</c:if>
	});
	
	$('#profileMove').click(function(){
		$('#introFrm').attr('action', env.contextPath + "/mypage/mypageProfileView.omp");
		$('#introFrm').submit();
	});
});

function payTest() {
	if(showValidate('payForm', 'default', "<gm:string value='jsp.common.msg.title01'/>")){
		$('#cardNum').val($('#cardNum1').val() +$('#cardNum2').val() + $('#cardNum3').val() + $('#cardNum4').val());
		var data = $("#payForm").serialize();
		$.ajax({
			url: env.contextPath + "/mypage/mypageChangePaidMember.omp",
			dataType: 'json',
			type	: "POST",
			data 	: data,
			async	: false,		
			cache	: false,	
			success: function(json){
				if(json.result == '0000'){
					alert("<gm:string value='jsp.member.mypage.msg.com02'/>");
					location.href = env.contextPath + "/main/main.omp";
				}else{
					alert(json.resultMsg);
					return false;
				}
			}
		});
	}
}
</script>
<form name="introFrm" id="introFrm" method="post">
	<input type="hidden" name="isProfile" id="isProfile" value="${isProfile }"/>
</form>
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; <gm:html value='마이페이지'/> &gt; <span><gm:html value='회원정보변경'/></span></p>
		<h3><img alt="회원정보변경" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_title01.gif'/>"></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
	
		<!-- Tab_menu S -->
		<div class="tab mar_b22">
			<ul>
				<li><a href="#"><img alt="기본정보" id="profileMove" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_tab01.gif'/>" /></a></li>
				<li><a href="#"><img alt="정산정보" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_tab02_on.gif'/>" /></a></li>
			</ul>
			<p class="info"><span>*</span> 가 표시된 부분은 필수입력 항목입니다.</p>
		</div>
		<!-- //Tab_menu E -->
		<h4 class="h41"><img alt="정산정보" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/mp/mp_h4_02.gif'/>"></h4>
		<c:if test="${calculateInfo.dispStat ne 'POP'}">
		<div class="pmbox1 mar_b10">
			<p>정산정보에 대해서 운영자가 <span class="txtcolor10">승인 검토</span> 중입니다. &nbsp;승인 검토 중에는 정산정보 수정이 불가합니다.</p>
		</div>
		</c:if>
		<div class="tstyleA">
			<c:if test="${calculateInfo.mbrClsCd eq 'US000101'}">
			<table summary="정산정보">
				<caption><gm:html value='정산정보'/>${calculateInfo.dispStat}</caption>
				<colgroup>
					<col width="15%">
					<col>
				</colgroup>
				<tbody>
					<!-- New -->  
					<tr>
						<th scope="row"><span>*</span> <gm:html value='개인신분 사본'/></th>
						<td><g:html value="${calculateInfo.identityDocNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='예금주'/></th>
						<td><g:html value="${calculateInfo.acctNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='은행정보'/></th>
						<td><g:html value="${calculateInfo.bankCd}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='계좌정보'/></th>
						<td><g:html value="${calculateInfo.acctNo}"/></td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> <gm:html value='통장사본'/></th>
						<td class="bgnone"><g:html value="${calculateInfo.acctDocNm}"/></td>
					</tr>
				</tbody>
			</table>
			</c:if>
			<c:if test="${calculateInfo.mbrClsCd eq 'US000102'}">
			<table summary="정산정보 입력">
				<caption><gm:html value='정산정보'/></caption>
				<colgroup>
					<col width="15%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='회사증명 사본'/></th>
						<td><g:html value="${calculateInfo.identityDocNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='회사구분'/></th>
						<td><gc:html code="${calculateInfo.bizCatCd}"/> </td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='예금주'/></th>
						<td><g:html value="${calculateInfo.acctNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='은행정보'/></th>
						<td><g:html value="${calculateInfo.bankCd}"/><br /> <g:html value="${calculateInfo.bankNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='계좌정보'/></th>
						<td><g:html value="${calculateInfo.acctNo}"/></td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> <gm:html value='통장사본'/></th>
						<td class="bgnone"><g:html value="${calculateInfo.acctDocNm}"/></td>
					</tr>
				</tbody>
			</table>
			</c:if>
			<c:if test="${calculateInfo.mbrClsCd eq 'US000103'}">
			<table summary="정산정보 입력">
				<caption><gm:html value='정산정보 입력'/></caption>
				<colgroup>
					<col width="15%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='외국인증명 사본'/></th>
						<td><g:html value="${calculateInfo.identityDocNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='예금주'/></th>
						<td><g:html value="${calculateInfo.acctNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='은행명'/></th>
						<td><g:html value="${calculateInfo.bankNm}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='계좌번호'/></th>
						<td><g:html value="${calculateInfo.acctNo}"/></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='은행코드'/></th>
						<td>
							<div class="fltl pad_r4">
							<select id="bankcode" class="w188" disabled="disabled">
								<gc:options group="US0057" codeType="full" value="${calculateInfo.bankGlType}"/>
							</select>
							</div>
							<input type="text" id="bankcode1" name="bankcode1" class="w180" value="${calculateInfo.bankGlCd}" readonly="readonly"/>
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <gm:html value='화폐선택'/></th>
						<td><gc:html code="${calculateInfo.currentcyUnit}"/></td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> <gm:html value='통장사본'/></th>
						<td class="bgnone"><g:html value="${calculateInfo.acctDocNm}"/></td>
					</tr>
				</tbody>
			</table>
			</c:if>
		</div>
		<c:if test="${calculateInfo.dispStat ne 'POP'}">
		<div class="btnarea mar_t30">
			<input id="btnOk" type="image" alt="확인" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_confirm.gif'/>">
			<a href="<c:url value='/main/main.omp'/>"><img alt="취소" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif'/>"></a>
		</div>
		</c:if>
	</div>
	<!-- //CONTENT TABLE E-->

</div>

<div id="pop_area01">
	<h2><img alt="연회비 결제" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/mp_title_03.gif'/>"></h2>
	<form name="payForm" method="post" id="payForm">
	<div class="pop_con">
		<div class="pop_box  mar_b10">
			<p class="pbimg02 pad_tb10"><img alt="카드번호" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/mp_txt_02.gif'/>">
				<input type="text" class="w44" id="cardNum1" maxlength="4" name="cardNum1"
				v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.card01'/>"
				v:required m:required="<gm:tagAttb value='jsp.member.mypage.msg.card02'/>"/> - <input type="text" id="cardNum2" class="w44" maxlength="4" name="cardNum2" 
				v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.card03'/>"
				v:required m:required="<gm:tagAttb value='jsp.member.mypage.msg.card04'/>"/> - <input type="password" id="cardNum3" class="w44" maxlength="4" name="cardNum3"
				v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.card05'/>"
				v:required m:required="<gm:tagAttb value='jsp.member.mypage.msg.card06'/>"/> - <input type="password" id="cardNum4" class="w44" name="cardNum4"
				v:text8_limit="7" m:text8_limit="<gm:tagAttb value='jsp.member.mypage.msg.card07'/>"
				v:regexp="[\d]+" m:regexp="<gm:tagAttb value='jsp.member.mypage.msg.card08'/>"
				v:required m:required="<gm:tagAttb value='jsp.member.mypage.msg.card09'/>"/>
				<input type="hidden" name="purchase.cardNum" id="cardNum"/>
			</p>
			<div class="pbimg02 pad_tb10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/mp_txt_03.gif'/>" alt="보안번호" />
               	<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="vm pad_r5" />
                   <div class="helpbox">
                       	<div class="helpboxin">
                               <p>보안코드는 카드종류에 따라 3자리 혹은 4자리로 되어 있는 숫자로 신용카드 번호와는 별도로 카드 앞면 혹은 뒷면에 표시되어 있습니다.</p>
                               <p>- Visa , Master 카드인 경우 : 카드 뒷면3자리 숫자를 입력합니다. </p>
                               <p>- American Express 카드인 경우 : 카드 앞면 4자리 숫자를 입력합니다. </p>
                       	</div>
                       </div>
                   </a>
				<input type="password"" class="w109" name="purchase.cardext"
				v:text8_limit="4" m:text8_limit="<gm:tagAttb value='보안번호정보는 4Byte 까지만 입력 할 수 있습니다.'/>"
				v:regexp="[\d]+" m:regexp="<gm:tagAttb value='보안번호정보는 숫자이어야 합니다.'/>" 
				v:required m:required="<gm:tagAttb value='보안번호 정보를 입력해주세요.'/>">
			</div>
			<p class="pbimg02 pad_tb10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/mp_txt_04.gif'/>" alt="유효기간" />
				<select id="mm" name="purchase.cardexpm" class="w90">
					<option value="01">1</option>
					<option value="02">2</option>
					<option value="03">3</option>
					<option value="04">4</option>
					<option value="05">5</option>
					<option value="06">6</option>
					<option value="07">7</option>
					<option value="08">8</option>
					<option value="09">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>&nbsp;
				<select id="yyyy" name="purchase.cardexpy" class="w90">
					<option value="2011">2011</option>
					<option value="2012">2012</option>
					<option value="2013">2013</option>
					<option value="2014">2014</option>
					<option value="2015">2015</option>
					<option value="2016">2016</option>
					<option value="2017">2017</option>
					<option value="2018">2018</option>
					<option value="2019">2019</option>
					<option value="2020">2020</option>
					<option value="2021">2021</option>
				</select>
			</p>
			<p class="pad_t15"><img alt="결제금액" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/mp_txt_05.gif'/>">
				<strong>NT<span class="txt_nor">$</span> 500</strong>
				<input type="hidden" name="purchase.usPayYn" value="Y"/>
			</p>
		</div>
		<div class="popinfo">
			<ul class="popbult03">
				<li>연회비 결제를 하셔야 상품의 판매가 가능합니다.</li>
				<li>연회비는 평생 1회만 납부하시면 됩니다.</li>
				<li>연회비는 신용카드로만 결제가 가능합니다.</li>
				<li>결제 후 취소 및 환불은 불가능합니다.</li>
			</ul>
		</div>
	</div>
	</form>
	<div class="pop_btn">
		<a href="javascript:payTest();"><img alt="결제하기" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_pay.gif'/>"></a>
		<a href="<c:url value='/main/main.omp'/>"><img alt="취소하기" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_pay_no.gif'/>"></a>
	</div>
	<p class="pop_close"><a href="<c:url value='/main/main.omp'/>"><img alt="닫기" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pop/btn_close.gif'/>"></a></p>
</div>
