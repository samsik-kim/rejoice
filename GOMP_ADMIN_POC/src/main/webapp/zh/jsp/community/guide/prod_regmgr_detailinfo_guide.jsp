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
          	<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt09.gif'/>" alt="產品上架以基本資訊, 詳細資訊, 開發資訊3步驟組成." /></p>
		
		<div class="tab mar_b22">
			<ul>
				<li><a href="<c:url value='/community/basicInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab01.gif' />" alt="基本資訊" /></a></li>
				<li><a href="<c:url value='/community/detailInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab02_on.gif' />" alt="詳細資訊" /></a></li>
				<li><a href="<c:url value='/community/devInfoGuide.omp' />"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_tab03.gif' />" alt="開發資訊" /></a></li>
			</ul>
		</div>  
                
        <div class="ucbox">
			<p class="ucbult01 pad_b2"><strong>[詳細資訊]為填寫個項詳細資料以滿足服務需要之步驟</strong></p>
			<p class="uctxt02 pad_l07 pad_b5">該項目可隨意更變, 且可以隨時顯示於手機端</p>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img09.gif'/>" alt="" /></p>
				<dl class="mar_b22">
					<dt class="dt1">1) 產品名稱</dt>
					<dd class="dd3">: 填寫基本資訊時所輸入之產品名稱.</dd>
					<dt class="dt1">2) 銷售人名稱</dt>
					<dd class="dd3">
						: 該項為販售產品時所顯示銷售人名稱之欄位, 預設為註冊會員時所填寫之銷售人名稱. <br />
						&nbsp; 可於持產品所有權者與銷售人相異時使用.
					</dd>
					<dt class="dt1">3) 產品價格</dt>
					<dd class="dd3">
						: 需填寫包括附加稅之價格. 於設為“0”, 若未經修正而銷售時, 該產品則以免費產品出售.<br />
						&nbsp; 為銷售付費產品,需填寫銀行資料, 且於認證後, 才可銷售付費產品.
					</dd>
					<dt class="dt1">4) 宣傳網址</dt>
					<dd class="dd3">: 若具備App外部宣傳途徑(例如: Youtube影像), 請將網絡地址輸入於此.</dd>
					<dt class="dt1">5) 產品管理編號</dt>
					<dd class="dd3">
						: 若銷售人基於其他目的, 自行管理而建立之識別碼, 可填寫於此. 該資訊將與 <span class="link02"><a href="<c:url value="/settlement/dailysale/dailySaleList.omp"/>">[產品販售/結算現狀]</a></span> 一同顯示.
					</dd>
				</dl>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img10.gif'/>" alt="" /></p>
				<dl class="mar_b22">
					<dt class="dt2">6) 類別 </dt>
					<dd class="dd1">: 可依據產品屬性選擇分類.</dd>
					<dt class="dt2">7) 使用等級</dt>
					<dd class="dd1">
						: 可分為 “普遍級”, “限制級” 兩種. 若所上傳之產品不適宜於未成年人使用, 可點選“限制級”
					</dd>
					<dt class="dt2">8) 填寫關鍵字連接</dt>
					<dd class="dd1">
						: 可填寫5個說明產品之關鍵字, 共用戶搜尋時使用. 不得填寫俚语, 禁語(包括特殊文字), 且關鍵字不得重複.
					</dd>
					<p class="cb txt_90">* 不得填寫俚语, 禁語(包括特殊文字), 且關鍵字不得重複</p>
				</dl>  
				
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img11.gif'/>" alt="" /></p>
				<dl class="mar_b22">
					<dt class="dt3">9) 簡介說明</dt>
					<dd class="dd1">: 該欄位為填寫產品簡介之處, 可用於產品搜尋.</dd>
					<dt class="dt3">10) 詳細說明&amp;說明圖片</dt>
					<dd class="dd1">
						: 該欄位為填寫產品詳細資訊之處, 無法用文字說明的操作方法, 說明圖片可於此處上傳.
					</dd>
				</dl>
			<p class="mar_b10"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img12.gif'/>" alt="" /></p>
			<p class="uctxt02 txt_90">11) 圖示&nbsp;&nbsp;: 上傳尺寸為212*212之JPG檔, 將自動調整為適用於手機端之尺寸.</p>
			<p class="uctxt02 txt_90 mar_b22">12) 預覽&nbsp;&nbsp;: 上傳顯示於手機端之預覽圖片. 請上傳可詳細說明產品之圖片(100KB以內之JPG檔) </p>
			<p class="mar_b15"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img13.gif'/>" alt="" /></p>
			<p class="uctxt02 txt_90">13) 可透過 “預覽” 功能確認產品資訊如何顯示於手機端.</p>
		</div>
              
	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->