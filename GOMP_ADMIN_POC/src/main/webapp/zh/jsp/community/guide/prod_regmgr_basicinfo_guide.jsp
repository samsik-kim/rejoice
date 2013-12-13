<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!-- Content Area S -->
<h2 class="hide">Contents Area</h2>
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 開發商支援中心 &gt; 開發支援指南 <strong>&gt;</strong> <span>產品上架管理</span></span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title06.gif' />" alt="產品上架管理" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt09.gif' />" alt="產品上架以基本資訊, 詳細資訊, 開發資訊3步驟組成." /></p>
		
		<div class="tab mar_b22">
			<ul>
				<li><a href="<c:url value='/community/basicInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab01_on.gif' />" alt="基本資訊" /></a></li>
				<li><a href="<c:url value='/community/detailInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab02.gif' />" alt="詳細資訊" /></a></li>
				<li><a href="<c:url value='/community/devInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab03.gif' />" alt="開發資訊" /></a></li>
			</ul>
		</div>
		<div class="ucbox">
		<p class="ucbult01 pad_b2"><strong>該階段為填寫產品基本資訊(產品名稱和平台)後, 產生應用軟體帳號之步驟.</strong></p>
		<p class="uctxt02 pad_l07 pad_b7">應用軟體帳號為識別產品而自動產生, 用於產品販售/ARM.</p>
		<p class="pad_b7"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img07.gif' />" alt="應用軟體帳號產生之畫面" /></p>
		<p class="ucbult06 mar_b15">應用軟體帳號產生之畫面</p>
		
		<p class="ucbult01 pad_b2"><strong>產品狀態於上傳且完成審核後, 可直接變更.</strong></p>
		<p class="uctxt02 pad_l07 pad_b7">核准後之產品狀態為待售, 點選[開售]才可進行販賣. </p>
		<p class="pad_b7"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img08.gif ' />" alt="產品狀態之變更及管理畫面" /></p>
		<p class="ucbult06 mar_b22">產品狀態之變更及管理畫面</p>
		
		<p class="pad_b7">- 依據產品狀態類別之功能定義</p>
		<div class="tstyleE">
			<table summary="產品狀態" class="uc">
			<caption>產品狀態</caption>
			<colgroup>
				<col width="16%" />
				<col />
				<col width="9%" />
				<col width="16%" />
			</colgroup>
				<thead>
					<tr>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit05.gif' />" alt="狀態" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit06.gif' />" alt="說明" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit07.gif' />" alt="刊登於否" /></th>
						<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit08.gif' />" alt="狀態變更按鍵" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="bg01">未核准</td>
						<td class="left">為經審核或審核失敗之產品</td>
						<td>X</td>
						<td class="brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_del.gif' />" alt="刪除" /></td>
					</tr>
					<tr>
						<td class="bg01">待售</td>
						<td class="left">
							核准後之狀態<br />
							點選[開賣]則變更為[販售中]
						</td>
						<td>X</td>
						<td class="brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_sell02.gif' />" alt="開賣" /></td>
					</tr>
					<tr>
						<td class="bg01">販售中</td>
						<td class="left">產品整銷售於Whoopy 之狀態</td>
						<td>O</td>
						<td class="brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_sell01.gif '/>" alt="停售" /></td>
					</tr>
					<tr>
						<td class="bg01">停售</td>
						<td class="left">被管理者停止銷售之狀態</td>
						<td>X</td>
						<td class="brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_sell02.gif' />" alt="開賣" /></td>
					</tr>
					<tr>
						<td class="bg01">禁售</td>
						<td class="left">
							被管理者禁止銷售之狀態<br />
							(可向管理者請求解禁被禁售之產品)
						</td>
						<td>X</td>
						<td class="brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/icon_cancel.gif' />" alt="請求解禁" /></td>
					</tr>
					<tr>
						<td class="bg01">審核中</td>
						<td class="left">因產品上架, 修改, 變更而正在進行審核之狀態</td>
						<td>O/X</td>
						<td class="brnone">N/A</td>
					</tr>
					<tr>
						<td class="bg01">請求解禁</td>
						<td class="left">向管理者申請解禁禁售產品之狀態</td>
						<td>X</td>
						<td class="brnone">N/A</td>
					</tr>
				</tbody>
			</table>
		</div>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->