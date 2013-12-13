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
		<p class="history">Home &gt; 開發商支援中心 &gt; 開發支援指南 <strong>&gt;</strong> <span>產品上架管理</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title06.gif' />" alt="產品上架管理" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt09.gif' />" alt="產品上架以基本資訊, 詳細資訊, 開發資訊3步驟組成." /></p>
		
		<div class="tab mar_b22">
			<ul>
				<li><a href="<c:url value='/community/basicInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab01.gif' />" alt="基本資訊" /></a></li>
				<li><a href="<c:url value='/community/detailInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab02.gif' />" alt="詳細資訊" /></a></li>
				<li><a href="<c:url value='/community/devInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab03_on.gif' />" alt="開發資訊" /></a></li>
			</ul>
		</div>
		<div class="ucbox">
			<p class="ucbult01 pad_b2"><strong>[開發資訊]為上傳以服務使用者所需之binary檔與手機資訊之步驟. </strong></p>
			<p class="uctxt02 pad_l07 pad_b5">修改開發資訊後, 需通過審核, 才可適用於手機端.</p>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img14.gif' />" alt="" /></p>
			<dl>
				<dt class="dt2">1) LCD Size </dt>
				<dd class="dd2">: 可選擇(可重複)適合手機之LCD Size, 搜尋適用手機時使用之資訊.</dd>
			</dl>
				<p class="txt_90">2) 上傳Binary檔</p>
			<ul class="uctxt02 pad_l15 mar_b5 txt_90">
				<li>&middot; 可上傳產品Binary檔.</li>
				<li>&middot; 可依據LCD Size上傳多數Binary檔.</li>
				<li>
					&middot; 雖Binary檔之LCD Size 可以重複, 但適用手機則不得重服. 上傳Binary檔時, 畫面將以以下Binary檔顯示, 以此可確認Binary檔之資訊. <br />
					<!-- 0506 수정 -->
					&nbsp;&nbsp;自動提取資訊 (使用AndroidManifest.xml) : minSdkVersion / targetSdkVersion / maxSdkVersion / versionCode / versionName / <br /> 
					&nbsp;&nbsp;Package <!-- //0506 수정 -->
				</li>
			</ul>
			<p class="ucbult02 cb">注意事項. 上傳Binary檔前請務必確認以下內容. 若不符合以下規定, 產品將無法上架.</p>
			<ul class="uctxt02 pad_l15 txt_90 mar_b22">
				<li>&middot; Platform Version &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : Android 2.1 以上</li>
				<li>&middot; Signing Application : Binary檔需以私用密鑰加鎖, 且有效期應為一萬日以上. 詳情請點選<span class="link02"><a href="http://developer.android.com/guide/publishing/app-signing.html" target="_blank">此鍵</a></span></li>
				<li> &middot; maxSdkVersion &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; : 可於此設置起動應用軟體之maximum API Level, 但建議最好不要特別設置. 詳情請點選<span class="link02"><a href="http://developer.android.com/guide/topics/manifest/uses-sdk-element.html" target="_blank">此鍵</a></span></li>
			</ul>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img15.gif' />" alt="" /></p>
			<dl class="mar_b22">
				<dt class="dt6">3) 選擇適用手機</dt>
				<dd class="dd1">: 可從目錄中選擇以Binary檔與LCD Size為基準搜尋之手機.</dd>
			</dl>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img16.gif' />" alt="" /></p>
			<p class="uctxt02 txt_90 mar_b22">&middot; 透過[附加]/[刪除]功能可同時多數上傳或刪除Binary檔</p>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img17.gif' />" alt="" /></p>
			<dl class="mar_b22">
				<dt class="dt7">4) Application DRM</dt>
				<dd class="dd2">
					: 可選擇是否使用上傳產品之Application DRM. 使用Application DRM之詳情請見<span class="link02"><a href="<c:url value='/community/appDrmGuide.omp'/>">[開發支援指南]>[Application DRM].</a></span>
				</dd>
				<dt class="dt7">5) 使用指南</dt>
				<dd class="dd2">: 該資訊為有效審核商品而使用, 請客觀, 易懂地敘述.</dd>
			</dl>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img18.gif' />" alt="" /></p>
			<dl class="mar_b22">
				<dt class="dt7">6) 審核紀錄管理</dt>
				<dd class="dd2">: 可確認產品請審時所填寫之審核緣由. 為有效進行產品審核, 將適用該資料.</dd>
				<dt class="dt7">7) 更新紀錄管理</dt>
				<dd class="dd2">: 此項為管理產品更新紀錄之功能.</dd>
			</dl>
		</div>
	
	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->