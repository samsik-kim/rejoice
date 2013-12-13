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
		<p class="history">Home &gt; 開發商支援中心 &gt; 開發支援指南 <strong>&gt;</strong> <span>產品審核</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title07.gif' />" alt="產品審核" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		
		<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt10.gif' />" alt="為提高服務質量, Whoopy 將會對銷售人上傳之產品進行內部審核" /></p>
		<ul class="ucbult08 mar_b30">
			<li>其中包括產品有害性與啟動與否之兩種審核, 詳情請點選 <a href="<c:url value="/fileSupport/bbsFileDown.omp">
																		<c:param name="bnsType" value="common.path.share.misc"/>
																		<c:param name="filePath" value="/AppTestCase.xls" />
																		<c:param name="fileName" value="AppTestCase.xls" />
																	</c:url>"><span class="txtcolor04">此鍵.</span></a></li>
			<li>產品審核步驟分為3階段,即[待審], [審核中], [審核完畢].</li>
		</ul>
		<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_stitle03.gif' />" alt=" 1 請審" /></h5>
		<p class="pad_l07 mar_b22">填寫產品資訊(詳細資訊, 開發資訊)將顯示[請審]之按鍵. <br />點選[請審]按鍵將進行該產品之審核. 處於[審核中]之產品不得修改資訊.</p>
		<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_stitle04.gif' />" alt=" 2 取消請審" /></h5>
		<p class="pad_l07 mar_b22">請審後處於待審狀態時, 可取消請審. 若取消請審, 審核狀態將從[待審]轉換為[取消審核].<br /> 取消請審後, 可修改[詳細資訊]與[開發資訊]之內容.</p>
		<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_stitle05.gif' />" alt=" 3 審核現狀" /></h5>
		<p class="pad_l07 mar_b10">產品之審核結果可於[產品上架/管理]>[產品管理]>[審核現狀]中查看.</p>
		<div class="ucbox">
			<p class="mar_b8"><strong>1)產品狀態定義</strong></p>
			<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img19.gif' />" alt="" /></p>
			<div class="tstyleE mar_b22">
			<table summary="審核狀態" class="uc">
				<caption>審核狀態</caption>
				<colgroup>
					<col width="16%" />
					<col />
				</colgroup>
				<thead>
					<tr>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit09.gif' />" alt="審核狀態" /></th>
						<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/th_tit06.gif' />" alt="說明" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="bg01">待審</td>
						<td class="left brnone">處於等待審核之狀態, 可取消請審.</td>
					</tr>
					<tr>
						<td class="bg01">審核中</td>
						<td class="left brnone">處於產品請審之狀態.</td>
					</tr>
					<tr>
						<td class="bg01">審核完畢</td>
						<td class="left brnone">處於審核完畢狀態, 可確認審核成功與否.</td>
					</tr>
					<tr>
						<td class="bg01">取消審核</td>
						<td class="left brnone">因開發商取消請審, 產品請審被取消.</td>
					</tr>
				</tbody>
			</table>
			</div>
			<p class="mar_b8"><strong>2)審核完畢(預期)日期</strong></p>
			<p class="mar_b8">若[審核完畢]將顯示審核完畢日, 若為[審核中], 則顯示審核完畢預期日.</p>
			<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img20.gif' />" alt="審核中" /></p>
			<p class="mar_b8"><strong>3)審核結果</strong></p> 
			<p class="pad_l10 mar_b8">結果分為[成功], [失敗]兩種, 若審核失敗, 將於審核結果詳細頁面顯示其緣由.  <br /> 2個Binary檔中即使有一項未能成功, 也會顯示為[失敗]. 若取消審核, 將不顯示審核結果.</p>
			<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img21.gif' />" alt="審核結果" /></p>
			<p class="mar_b8"><strong>4)審核紀錄</strong></p>
			<p class="mar_b8">可於審核紀錄確認各項請審結果.  <br />
				※ 點選[審核結果]可瀏覽審核結果之詳細內容. </p>
			<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img22.gif' />" alt="審核紀錄" /></p>
		</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->