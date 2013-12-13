<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!-- Content Area S -->
<h2 class="hide">본문영역</h2>
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 개발지원센터 &gt; 개발지원가이드 <strong>&gt;</strong> <span>판매정산</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_title.gif' />" alt="판매정산" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_txt01.gif' />" alt="판매 중인 상품에 대한 일별 / 상품별 판매현황과 정산 및 송금현황을 확인하실 수 있습니다" /></p>
		<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_stitle01.gif' />" alt="일별 판매현황" /></h5>
		<p class="pad_l15 pad_b5">판매자가 등록한 상품 중 Taiwan Store에서 현재 "판매 중"인 상품을 기준으로 일자 별로 구분하여 판매현황을 제공합니다.<br />
		(※ 판매현황은 전일 기준으로 집계됩니다.)</p>
		<p class="pad_l15 pad_b10">"엑셀파일 다운로드" 기능을 통해 추가적인 정보를 제공해 드립니다. EX)상품 관리번호 등</p>
		<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_img01.gif' />" alt="일별 판매현황 및 판매상세현황" /></p>
		<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_stitle02.gif' />" alt="상품별 판매현황" /></h5>
		<p class="pad_l15 pad_b10">판매자가 등록한 상품 중 "판매 중"인 상품을 기준으로 상품별로 판매현황을 제공합니다.</p>
		<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_img02.gif' />" alt="상품별 판매현황" /></p>
		<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_stitle03.gif' />" alt="정산 리포트" /></h5>
		<p class="pad_l15 pad_b10">정산 리포트는 전월 매출을 기준으로 매월 25일경에 제공되며, 정산 리포트를 확인 후 송금신청을 할 수 있습니다.</p>
		
		<div class="ucbox">
			<p class="uctxt01 pad_b15"><strong>1)정산/송금 Process</strong></p>
			<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_img03.gif' />" alt="정산 리포트 확인 &rarr; 송금신청 &rarr; Invoice 또는 Receipt 전달 &rarr; 송금대기 &rarr; 송금완료" /></p>
			<p class="ucbult02">송금신청은 5가지 단계로 구분됩니다.</p>
			<ol class="ucbult04 pad_b15">
				<li>1. 매월 25일경 개발자센터에서는 정산 리포트를 제공합니다. 제공된 정산 리포트에 대해 확인 후 송금 내역에 대해 확인합니다.<br />
				&nbsp;&nbsp; 만약 송금 내역에 오류가 있을 경우 고객센터 또는 문의하기를 통해 이의제기가 가능합니다.</li>
				<li>2. 제공된 정산 리포트의 내용이 맞을 경우 송금 신청을 합니다.</li>
				<li>3. 송금 신청과 함께 receipt 혹은 invoice를 정해진 양식에 따라 작성 후 원본을 우편으로 전달합니다.<br />
				&nbsp;&nbsp; (해외 사용자는 E-mail에 파일을 스캔 후 첨부하여 전달)</li>
				<li>4. 접수된 receipt 혹은 invoice에 대해서는 개발자센터 수신상태를 확인할 수 있으며, 수신 완료 시 송금 대기로 전환됩니다.</li>
				<li>5. 송금 요청한 내역에 대해 송금 업체를 통해 송금이 완료됩니다.</li>
			</ol>
			<p class="uctxt01 pad_b7"><strong>2)정산 리포트 설명</strong></p>
			<p class="uctxt02 pad_b7">- 정산 리포트 예제 화면</p>
			<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_img04.gif' />" alt="- 정산 리포트 예제 화면" /></p>
			<p class="uctxt02 pad_b7">(1)용어 정의</p>
			<div class="tstyleE mar_b5">
				<table summary="용어 정의입니다." class="uc">
					<caption>용어 정의</caption>
					<colgroup>
						<col width="16%" />
						<col />
					</colgroup>
					<thead>
						<tr>
							<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_th01.gif' />" alt="용어" /></th>
							<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_th02.gif' />" alt="설명" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="bg01">판매월</td>
							<td class="left brnone">거래가 이루어진 월 표시</td>
						</tr>
						<tr>
							<td class="bg01">판매 총액</td>
							<td class="left brnone">거래 당월 수금해야 하는 판매 총액</td>
						</tr>
						<tr>
							<td class="bg01">세전 금액</td>
							<td class="left brnone">판매총액 / 1.05</td>
						</tr>
						<tr>
							<td class="bg01">배분 총액</td>
							<td class="left brnone">배분 비율에 따라 세전 총액을 곱한 금액</td>
						</tr>
						<tr>
							<td class="bg01">세금</td>
							<td class="left brnone">
								세금은 회원 분류에 따라 다른 세율이 적용됨<br />
								- 회사회원(일반) : 5%<br />
								- 회사회원 (소규모영업) : 0%<br />
								- 개인회원 : 배분총액이 20,006과 같거나 많을 경우 -10% / 배분 총액이 20,006보다 적을 경우 0%<br />
								- 외국인회원 : -20%
							</td>
						</tr>
						<tr>
							<td class="bg01">조정 금액</td>
							<td class="left brnone">정산 시 추가 또는 차감되는 금액</td>
						</tr>
						<tr>
							<td class="bg01">실정산액</td>
							<td class="left brnone">배분총액 &#177; 세금 &#177; 조정액</td>
						</tr>
						<tr>
							<td class="bg01">지불화폐</td>
							<td class="left brnone">개발자가 선택한 송금 화폐</td>
						</tr>
						<tr>
							<td class="bg01">송금액</td>
							<td class="left brnone">송금 지불화폐가 USD일 경우 판매월을 기준으로 등록되어 있는 해당 월의 환율에 따라 USD로 변경하여 정산 금액 표시</td>
						</tr>
					</tbody>
				</table>
			</div>
			<p class="ucbult06 mar_b15">정산 리포트는 전월 매출을 기준으로 매월 25일경에 제공됩니다. (공휴일, 주말 제외)</p>
			<p class="uctxt02 pad_b2">(2)계산 예시</p>
			<p class="uctxt02 pad_l15 txt_90">- 2011년 06월 판매총액이 125,700이며, 조정액이 10,000일 경우</p>
			<p class="ucbult02 fltl mar_t10">개인회원의 경우</p>
			<ul class="ucbult05 pad_b15">
				<li>배분총액 : (125,700/ 1.05) × 65% = 81,705 
				<li>실정산액 : 73,535(정산액) + 10,000(조정액) = 83,535</li>
				<li>정산금액 : 81,705*(1+(-0.1)) =73,535</li>
				<li class="ucline">개인회원 결제통화는 TWD이므로 송금금액은 83,535이다.</li>
			</ul>
			<p class="ucbult02 fltl">외국인회원의 경우</p>
			<ul class="ucbult05 pad_b15">
				<li>배분총액 : (125,700/ 1.05) × 65% = 81,705 </li>
				<li>평균환율 : 미국USD = 0.03</li>
				<li>정산금액 : 81,705*(1+(-0.2)) =65,364</li>
				<li class="ucline">외국인회원 결제통화는 USD이므로 송금금액은 2260.92$이다.</li>
				<li>실정산액 : 65,364(정산액) + 10,000(조정액) = 75,364</li>
			</ul>
			<p class="ucbult02 fltl">회사(일반)회원의 경우</p>
			<ul class="ucbult05 pad_b15">
				<li>배분총액 : (125,700/ 1.05) × 65% = 81,705 </li>
				<li>실정산액 : 87,990(정산액) + 10,000(조정액) = 97,990</li>
				<li>정산금액 : 81,705*(1+(+0.05)) =87,990</li>
				<li class="ucline">개인회원 결제통화는 TWD이므로 송금금액은 97,990이다.</li>
			</ul>
			
			<p class="uctxt02 pad_b7 cb">(3)송금상태 정의</p>
			<div class="tstyleE mar_b5">
				<table summary="송금상태 정의입니다." class="uc">
					<caption>송금상태 정의</caption>
					<colgroup>
						<col width="16%" />
						<col />
					</colgroup>
					<thead>
						<tr>
							<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_th03.gif' />" alt="송금상태" /></th>
							<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_th02.gif' />" alt="설명" /></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="bg01">이월처리</td>
							<td class="left brnone">
								아래와 같은 case 발생 시, 해당 5일 이후 해당 상태로 일괄 변경됩니다.<br />
								- case1) 매월 25-5일 사이 송금신청을 하지 않은 경우<br />
								- case2) 송금신청은 했지만 서류 미 발송한 경우<br />
								- case3) 송금신청 하였고, 서류도 발송했지만, 서류상 오류가 있는 경우
							</td>
						</tr>
						<tr>
							<td class="bg01">수신대기</td>
							<td class="left brnone">송금요청은 완료되었지만 Invoice 또는 Receipt 미전달 상태</td>
						</tr>
						<tr>
							<td class="bg01">수신완료</td>
							<td class="left brnone">송금요청 및 Invoice 또는 Receipt 전달 완료 상태</td>
						</tr>
						<tr>
							<td class="bg01">송금대기</td>
							<td class="left brnone">송금내역 및 재송금 내역에 대한 송금대기 상태</td>
						</tr>
						<tr>
							<td class="bg01">송금완료</td>
							<td class="left brnone">송금업체를 통해 송금이 완료된 상태</td>
						</tr>
						<tr>
							<td class="bg01">재송금</td>
							<td class="left brnone">송금요청 내역 중 오류로 인해 송금이 실패되었지만, 오류 확인 및 수정 뒤 익월 송금 처리됨</td>
						</tr>
						<tr>
							<td class="bg01">송금포기</td>
							<td class="left brnone">송금요청 내역 중 오류로 인해 송금이 실패되었으며, 오류 수정 불가로 인해 송금포기 상태 관리</td>
						</tr>
					</tbody>
				</table>
			</div>
			<p class="ucbult06">송금포기된 내역에 대해서는 문의하기 또는 고객센터를 통해 문의바랍니다.</p>
		</div>
		
	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->