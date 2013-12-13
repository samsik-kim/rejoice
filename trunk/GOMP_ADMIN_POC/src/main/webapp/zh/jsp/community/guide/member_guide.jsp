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
		<p class="history">Home &gt; 開發商支援中心 &gt; 開發支援指南 <strong>&gt;</strong> <span>會員</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title05.gif' />" alt="會員" /></h3>
	</div>
	<!-- //Title Area E -->

<!-- CONTENT TABLE S-->
<div id="contents">
	<p class="mar_b15"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt08.gif' />" alt="Whoopy 開發商中心 可將開發商製作之應用軟體自由上架與販售之中介平台." /></p>
	<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img01.gif ' />" alt="Whoopy Developer Member 1.Content  應用軟體上架 2.請審 3.開售 4.結算與匯款, 購買商品" /></p>
		
	<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_stitle01.gif' />" alt="1. 會員註冊" /></h5>
	<p class="pad_l15 pad_b10">Whoopy 之會員可分為個人戶, 公司戶(包括小規模營業戶與一般營業戶), 外國戶三種. <br />所有會員可於 Whoopy 免費進行應用軟體上架與販賣, 若填寫銀行資料, 也可上架並販賣付費應用軟體.</p> <!-- 0525 수정 -->
	<div class="ucbox mar_b35">
		<p class="ucbult01 pad_b5"><strong>Whoopy 開發商中心之免費會員註冊流程</strong></p> <!-- 0525 수정 -->
		<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img02.gif' />" alt="選擇會員類別 &rarr; 確認註冊與否 &rarr; 同意會員條款 &rarr; 填寫會員資料 &rarr; 信箱認證 &rarr; 完成免費會員註冊" /></p>
		<p class="ucbult01 pad_b5"><strong>Whoopy 開發中心之付費會員註冊流程</strong></p> <!-- 0525 수정 -->
		<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img03.gif' />" alt="完成免費會員註冊 &rarr; 填寫銀行資料 &rarr; 管理者核准 &rarr; 繳交會員費 &rarr; 完成付回會員註冊" /></p>
		<p class="ucbult02">銀行資料</p>
		<ul class="ucbult03 pad_b7">
			<li>個人戶 : 身分證影本, 結算時使用之銀行帳戶資料, 存摺影本 </li>
			<li>公司戶 : 公司證明影本, 結算時使用之銀行帳戶資料, 存摺影本</li>
			<li>外國戶 : 外國人證明影本, 結算時使用之銀行帳戶資料,存摺影本</li>
		</ul>
	</div>

	<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_stitle02.gif' />" alt="2. 會員轉換" /></h5>
	<p class="pad_l15 pad_b10">以個人戶加入會員後,利用開發商轉區的過程中可依據需要轉換會員身分. 但若為外國戶, 則無法進行會員轉換.</p>
	<div class="ucbox">
		<p class="uctxt01 pad_b5"><strong>1)會員轉換方法</strong></p>
		<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img04.gif' /> " alt="免費個人戶 &rarr; 免費公司戶/付費公司戶, 付費個人戶 &rarr; 付費公司戶" /></p>
		<p class="uctxt01 pad_b7"><strong>2)會員轉換流程</strong></p>
		<p class="ucbult01 pad_b5"><strong>免費個人戶轉換為免費公司戶之流程</strong></p>
		<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img05.gif' />" alt="申請會員轉換 &rarr; 填寫轉換資料 &rarr; 管理者核准 &rarr; 完成會員轉換" /></p>
		<p class="ucbult01 pad_b5"><strong>免費個人戶 &gt; 付費公司戶&付費個人戶 &amp; 付費公司戶轉換流程</strong></p>
		<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img06.gif' />" alt="申請會員轉換 &rarr; 填寫轉換資料 &amp; 銀行資料 &rarr; 管理者核准 &rarr; 完成會員轉換" /></p>
		<p class="ucbult02">會員轉換須知</p>
		<ul class="ucbult03">
			<li>個人戶即使轉換為公司戶, 也可使用相同的帳號與銀行資料.</li>
			<li>免費個人戶轉換為免費公司戶時, 會員轉換成功後才可上架銷售產品.</li>
			<li>申請轉換為付費會員時, 需填寫銀行資料後, 獲得管理者核准, 才可進行商品上架,販售.</li>
			<li>您在完成會員轉換後, 不准再轉換為其處所屬會員類別.</li>
		</ul>
	</div>

	</div>
	<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->
