<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!-- Container S-->
	<div id="ft_container2" class="none_bg"> <!-- 0607 수정 -->
	  
		<!-- Content Area S -->
		<h2 class="hide">contents area</h2>
		<div>
			<!-- Title Area S -->
			<div id="ctitle_area">
				<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/ft_title02.gif'/>" alt="開發者使用條款" /></h3>
			</div>
			<!-- //Title Area E -->
			
			<!-- CONTENT TABLE S-->
			<div id="ftcontents">
				<h4 class="h42"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/ft_h4_02.gif'/>" alt="此文規定了有關Whoopy開發商專區使用時所需內容。" /></h4>
				<div class="agree_list">
					<p><span class="txtcolor09">Whoopy開發商專區條款內容如下</span> (點選各項標題可以瀏覽詳細內容)</p>
					<ul>
						<li>1 <a href="#a01"> 前言</a></li>
						<li>2 <a href="#a02"> 名詞定義</a></li>
						<li>3 <a href="#a03"> 註冊或終止您的開發者帳號</a></li>
						<li>4 <a href="#a04"> 拆帳方式與付款條件</a></li>
						<li>5 <a href="#a05"> 開發者權利義務</a></li>
					</ul>
					<ul class="pad_l07">
						<li>&nbsp;6 <a href="#a06"> 相關授權條款</a></li>
						<li>&nbsp;7 <a href="#a07"> 產品上傳與篩選</a></li>
						<li>&nbsp;8<a href="#a08">「WHOOPY」改善計劃</a></li>
						<li>&nbsp;9 <a href="#a09">  保密義務</a></li>
						<li>10 <a href="#a10">隱私和信息</a></li>
					</ul>
					<ul class="pad_l15">
						<li>11 <a href="#a11"> 終止</a></li>
						<li>12 <a href="#a12"> 免責聲明</a></li>
						<li>13 <a href="#a13">責任限制</a></li>
						<li>14 <a href="#a14">損害賠償</a></li>
						<li>15 <a href="#a15">條款修正</a></li>
					</ul>
					<ul class="last">
						<li>16 <a href="#a16">自由軟體</a></li>
						<li>17 <a href="#a17"> 一般法律條款</a></li>
					</ul>
				</div>
			<!-- 0607 수정 -->
			<div id="ft_top">
				<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/ft_top.gif'/>" alt="top" /></a>
			</div>
<!-- top버튼 스크롤 스크립트 -->
<script type="text/javascript">
var slidemenu_X = 350; //상단 제한 값
var slidemenu_Y = 350; //하단 제한 값
</script>
<script type="text/javascript">
// slide menu  2006-06-14
//<![CDATA[
var isDOM = (document.getElementById ? true : false);
var isIE4 = ((document.all && !isDOM) ? true : false);
var isNS4 = (document.layers ? true : false);
var isNS = navigator.appName == "Netscape";
//]]>

function getRef(id) {
    if (isDOM) return document.getElementById(id);
    if (isIE4) return document.all[id];
    if (isNS4) return document.layers[id];
}

function getSty(id) {
    x = getRef(id);
    return (isNS4 ? getRef(id) : getRef(id).style);
}

function moveRightEdge() {
//<![CDATA[
    var yMenuFrom, yMenuTo, yOffset, timeoutNextCheck;

    if (isNS4) {
        yMenuFrom   = document.getElementById('ft_top').style.top;
        yMenuTo     = windows.pageYOffset + slidemenu_X;   // 위쪽 위치
    } else if (isDOM) {
        yMenuFrom   = parseInt (document.getElementById('ft_top').style.top, 0);
        yMenuTo     = (isNS ? window.pageYOffset : document.documentElement.scrollTop) + slidemenu_X; // 위쪽 위치
    }
    timeoutNextCheck = 30;
    
    divMenu_H = document.getElementById('ft_top');
    limit_H = (parseInt(document.documentElement.scrollHeight)-slidemenu_Y)-parseInt(divMenu_H.offsetHeight);
    divMenu_t = parseInt(document.getElementById('ft_top').style.top) ;
    if (yMenuFrom != yMenuTo) {
        yOffset = Math.ceil(Math.abs(yMenuTo - yMenuFrom) / 20);
        if (yMenuTo < yMenuFrom){
            yOffset = -yOffset;
        }
        if (isNS4){
            if(yOffset > 0){
                if ( divMenu_t < limit_H) {
                    document.getElementById('ft_top').style.top += yOffset+"px";
                }
            }else{
                document.getElementById('ft_top').style.top += yOffset+"px";
            }
            
        }else if (isDOM){
            if(yOffset > 0){
                if ( divMenu_t < limit_H) {
                    document.getElementById('ft_top').style.top = parseInt (document.getElementById('ft_top').style.top) + yOffset+"px";
                }
            }else{
                document.getElementById('ft_top').style.top = parseInt (document.getElementById('ft_top').style.top) + yOffset+"px";
            }
        }
        timeoutNextCheck = 10;
    }

    setTimeout ("moveRightEdge()", timeoutNextCheck);
//]]>
}
if (isNS4) {    
    var divMenu = document["ft_top"];
    document.getElementById('ft_top').style.top = slidemenu_X+"px";
    document.getElementById('ft_top').style.visibility = "visible";
    moveRightEdge();
} else if (isDOM) {
    var divMenu = getRef('ft_top');    
    document.getElementById('ft_top').style.top = slidemenu_X+"px";    
    document.getElementById('ft_top').style.visibility = "visible";    
    moveRightEdge();
}
</script>
<!-- //top버튼 스크롤 스크립트 -->
			<!-- //0607 수정 -->
			<div class="agree">
				<p><a name="a01">1. 前言 </a></p>
				<ul>
					<li>「WHOOPY」是一個由東陽行動電話股份有限公司擁有並負責營運的公開網站，您可以透過合適並相容之設備將您的產品上傳至「WHOOPY」以供用戶有償或免費下載使用 。</li>
					<li>「WHOOPY」開發者註冊及軟體銷售條款 (以下簡稱 “本條款” ) 一經您同意接受後即形成您與東陽行動電話間有法律約束力之文件，您在「WHOOPY」註冊或提供、上傳、<br />下載、轉載、銷售您的產品將受本條款之規範。如果您不同意本條款中之任何一條條款，您將不得參加並使用「WHOOPY」 。</li>
					<li>一旦您接受本條款，您保證您已達法定年齡且具有完全之行為能力，如果您為一個公司，組織或其他法律實體，您保證您的公司或組織是根據您居住地之法律所組成，您聲明並保證您的公司或組織已合法授權您代表您的公司或組織加入「WHOOPY」。 </li>
					<li>如果您代表您的雇主或其他法律實體同意遵守本條款，您聲明並保證您擁有完整合法的權利，可使您的雇主或實體接受本條款之拘束。</li>
					<li>如果您並無得到所需之授權，您不得接受本條款或代表您的僱員或其他法律實體加入的「WHOOPY」。</li>
				</ul>
				<p><a name="a02">2. 名詞定義 </a></p>
				<ul>
					<li>「品牌」是指您或東陽行動電話所擁有之商標名稱，商標，服務標記，網域名稱，以及其他特殊的品牌特徵。「產品」是指任何信息或材料, 包括服務, 應用程序, 數據, 文件,</li>
					<li>軟體，音樂，聲音，圖片，圖像，圖形，影音訊息，答案，問題，意見，建議，暗示，策略，觀念，設計，想法，計劃。「自由軟體」是指根據其受權條款目前列在</li>
					<li>http://opensource.org/licenses 或歸類於 http://www.opensource.org/docs/definition.php 之任何公開免費軟體資源。「您」是指登記加入「WHOOPY」之個人或法人。</li>
					<li>如果您已經代表您的公司，組織或法人實體接受並同意本條款，那麼「您」指的是您的公司，組織或法律實體。</li>
				</ul>
				<p><a name="a03">3. 註冊或終止您的開發者帳號</a></p>
				<ul>
					<li>當您取得一個永久有效的「WHOOPY」開發者帳號，並同意提供最新、正確、真實、完整的開發者訊息；且您應保持上述訊息 的隨時更新。</li>
					<li>您的註冊信息將被用來驗證您的開發者帳號。如果您不能提供最新，正確，真實，完整的個人開發者訊息，東陽行動電話得拒絕您的註冊及終止您參加「WHOOPY」。</li>
					<li>東陽行動電話並保留接受或拒絕您的註冊之權利. 當您註冊時. 您應該設立您的用戶名稱和密碼。您應對您的用戶名稱和密碼善盡保管之義務並負完全保管之責任以防遭他人盜用，</li>
					<li>如您的用戶名稱和密碼遭到盜用時 您應及時通知東陽行動電話。 東陽行動電話得視實際情況限制對您或您的公司或組織您核發開發者帳號之數量。</li>
					<li>另您不得有任何違法或不當使他人之開發者用戶名稱或密碼之行為。如果您不想再參加「WHOOPY」, 您得隨時終止您的開發者帳號, 您的開發者帳號一但終止後您將無法再登入</li>
					<li>「WHOOPY」。另外如您有任何違反或有跡象指出您將違反 本條款之規定時；東陽行動電話得終止您的開發者帳號或限制您使用某些「WHOOPY」之功能；</li>
					<li>另您的開發者帳號一經終止，東陽行動電話保留將您的產品將「WHOOPY」中移除 之權利。如果您的產品由您或東陽行動電話從 「WHOOPY」移除後，</li>
					<li>產品之紀錄與複製的內容可能仍然保留在「WHOOPY」和與東陽行動電話之伺服器上。</li>
				</ul>
				<p><a name="a04">4. 拆帳方式與付款條件</a></p>
				<ul>
					<li>東陽行動電話依用戶每月使用之計次或月租服務，按月支付資訊使用費給您。計算方式為 WHOOPY用戶在WHOOPY購買產品而支付之總金額(其定價已內含營業稅)，扣除營業稅後(目前台灣營業稅率為5%)之70%，若為台灣一般營業人則依所開立加上5%營業稅之發票金額支付。</li>
					<li>您同意依照東陽行動電話提供之用戶使用記錄為拆帳基準，並以月為計算拆帳金額期間。您若對拆帳之金額有疑義者，且雙方報表有差異之金額超過當月總應收費用之千分之一，可請求東陽行動電話調閱相關資料查核之，若有不足或溢收則於下月計算。</li>
					<li>東陽行動電話負責提供管理介面供您即時查詢交易資料，並於每月25日左右提供上個月支付資訊服務費之服務銷帳報表供您查詢，您應於東陽行動電話25日提供報表後 10日內開立該銷帳報表之應開立金額發票/收據予東陽行動電話，東陽行動電話應於收到發票/收據後45日內將發票/收據未稅銷售額扣除雙方同意之當月東陽行動電話拆分 費用後之金額，以電匯方式支付給您；若您延後送交發票/收據，則電匯日期亦予順延。</li>
					<li class="lh200">上述之發票/收據會依據您的身份類型而有所不同，請依您註冊的身分參考下列規範：發票抬頭:東陽行動電話股份有限公司，統一編號:27903712‧
						<ol>
							<li>(1) 台灣一般營業人：有統一編號，可開立三聯式統一發票向東陽行動電話請款者</li>
							<li>(2) 台灣小規模營業人：有統一編號，適用免開發統一發票，可開立收據向東陽行動電話請款者</li>
							<li>(3) 一般台灣個人：開立收據向東陽行動電話請款，東陽行動電話會依各類所得扣繳率標準代扣繳, 並於次年2月前開立扣繳憑單予所得人。</li>
							<li>(4) 他國個人與公司：開立收據或憑證向東陽行動電話請款，東陽行動電話會依各類所得扣繳率標準代扣繳，並於給付後十日內代為申報所得，並開立扣繳憑單予所得人。<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您同意並了解您所須繳納之營業稅或所得稅，須依據中華民國之相關稅法之規範。</li>
						</ol>
					</li>
				</ul>
				<p><a name="a05">5. 開發者權利義務 </a></p>
				<ul>
					<li>5.1 您同意在依據下列規範使用「WHOOPY」
						<ol>
							<li>(1) 本條款之規定及</li>
							<li>(2) 相關有司法管轄權適用之法律，法規或一般慣例或準則。</li>
						</ol>
					</li>
					<li>5.2 您同意如果您使用「WHOOPY」發佈或銷售產品，您會保護用戶隱私和與相關合法權益。如果用戶於使用您的產品時提供用戶名稱，密碼或其它登錄信息或個人信息，<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;您必須提供適當的隱私權通知于用戶和保護這些用戶的個人資料隱私。此外，用戶所提供之訊息僅限於用戶同意您可使用之範圍內做為您的產品之利用。<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您的產品儲存由用戶所提供之個人信息或其它敏感信息時，您必須盡妥善保管信息之義務。</li>
					<li>5.3 您同意不從事任何會侵害「WHOOPY」之行為，包括在未經授權之下對包含但不限於東陽行動電話或其他第三方之行動通信網路、設備或資產進行干擾，破壞。您不得將於<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;「WHOOPY」中所取得之用戶資訊轉售或揭露於任何第三方知悉。您同意對用戶之個人資料負絕對之保密義務及保管責任，未經用戶之事前書面同意，絕不作超出本條款之目<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;的範圍之使用或以任何方式將其洩露、告知、交 付予任何第三人，若有違反致東陽行動電話或東陽行動電話之客戶受有損害，您同意無條件賠償東陽行動電話所受之一切損害<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(包括因此所產生之律師費用及訴訟費用)，並同意 就每一用戶對於您個別產品洩露事件之主張另支付東陽行動電話新台幣壹佰萬元作為懲罰性損害賠償金，如另涉有民刑事<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;責任，並應負起相關所有民刑事責任。</li>
					<li>5.4 您不得利用「WHOOPY」以從事促銷「WHOOPY」以外產品之行為。</li>
					<li>5.5 您同意並了解，您對您所上傳至「WHOOPY」之任何產品及行為(包含東陽行動電話因此可能所受之損害)負完全之責任 。</li>
					<li>5.6 您同意並了解，您對您違反本條款或任何適用第三方之服務條款，或任何適用法律或法規，以及所造成之損害(包括任何東陽行動電話或任何第三方可能遭受的損失或損害)<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;應單獨負完全之責任。</li>
					<li>5.7 您需負責於上載您的產品至「WHOOPY」時提供用戶所需的產品功能信息及產品安裝於用戶設備上時所需之安全需知；如果您於上傳時未具備上述資訊，您的產品將不會於<br /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;「WHOOPY」發表。</li>
					<li>5.8 您應保證所提供之產品內容符合以下條件：
						<ol>
							<li>a. 產品內容無侵犯著作權、專利權、商標權、命名權、肖像權或其他第三人的權利或違反中華民國公平交易法及消費者保護法等。</li>
							<li>b. 產品內容無任何可能誹謗他人名譽或侵犯第三人隱私權。</li>
							<li>c. 產品內容不含傳染性的程式，例如電腦病毒。</li>
							<li>d. 產品內容不含任何可能構成刑事犯罪的事物。</li>
							<li>e. 產品內容不違反公共秩序或善良風俗或無違反之虞者。</li>
							<li>f. 產品內容不違反現行法律、規章或同類的規定，或無違反之虞者。</li>
						</ol>
					</li>
				</ul>
				<p><a name="a06">6. 相關授權條款</a></p>
				<ul>
					<li>您所上傳之產品所有權歸您所有，您於「WHOOPY」上傳之產品時並不代表產品之所有權移轉至東陽行動電話；此外您同意並授權東陽行動電話非專屬性、全球性、不可撤銷性和免授權費之授權以利東陽行動電話進行使用、複製、公開展演、與公開傳輸。</li>
					<li>除本條款另有規定外，相關產品權利之歸屬各該所有權人所有包括但不限於智慧財產權、品牌特徵…等。</li>
					<li>另您授權東陽行動電話及其顧問，供應商和承包商得基於市場行銷之目的使用您的產品或品牌特徵；但本條款並不授權您可使用東陽行動電話的品牌商標。</li>
					<li>您同意並授權透過「WHOOPY」取得您產品之用戶非專屬性、全球性之長期使用您的產品；如果用戶與您達成其他協議時，相關之規定應依您與用戶所達成之協議為準，如有任何糾紛發生應依您與客戶所達成之其他協議為準，概與東陽行動電話無關。</li>
				</ul>
				<p><a name="a07">7. 產品上傳與篩選</a></p>
				<ul>
					<li>7.1 上傳至「WHOOPY」
						<ol>
							<li>您保證您的產品內容符合本條款之一切規定並配合如實回答及提供一切相關所需之資訊，您就可以上傳您的產品至「WHOOPY」，但東陽行動電話將進行最終審核且決定是否正式上線。</li>
						</ol>
					</li>
					<li>7.2 產品合適性
						<ol>
							<li>您同意並了解東陽行動電話有權自行判斷並決定您的產品是否符合「WHOOPY」之需求，並擁有決定是否可以上架之權利；另「WHOOPY」保留隨時移除您的產品之權利。<br />您進一步了解並同意，東陽行動電話並不對您因開發產品、使用「WHOOPY」或您的產品未能上架銷售、被移除或東陽行動電話終止「WHOOPY」所產生或造成之成本、費用、損害、損失(包括但不限於損失商業機會或利益損失)負責，<br />您必需確保您的產品於設計或運轉是安全且無瑕疵，並符合相關之法律法規；另您應對用戶負保固、產品說明及客戶支援之責任，您並不會因為您的產品通過測試上架銷售而免除您的相關責任與義務。</li>
						</ol>
					</li>
					<li>7.3 產品移除
						<ol>
							<li>您得於隨時於「WHOOPY」上申請移除您的產品且不再進行販售，並經由東陽行動電話之確認。但你必須遵守本條款及付款條件之規定內容。您的產品一經您刪除 且不在「WHOOPY」上進行銷售時並不 </li>
						</ol>
						<ol>
							<li>(1) 影響先前已購買您的產品用戶對於您的產品之使用或授權，</li>
							<li>(2) 將您的產品自「WHOOPY」或用戶先前於「WHOOPY」購買 且已下載並安裝於用戶之設備中移除，或 </li>
							<li>(3) 改變您對先前已購買之用戶提供產品支援或交付之義務。若收到您的申請通知或是有下列任何情形，東陽行動電話得隨時移除您的產品或對您的產品重新分類：</li>
						</ol>
						<ol>
							<li>(1) 侵害第三人之智慧財產權或其他權利；</li>
							<li>(2) 違反 相關法律法規之規定；</li>
							<li>(3) 內容包含色情、令人厭惡的內容或其他違反「WHOOPY」相關政策之情事；</li>
							<li>(4) 不當銷售; </li>
							<li>(5) 可能使東陽行動電話負有責任；</li>
							<li>(6) 內 容包含病毒或被認定為惡意軟體，間諜軟體或會對東陽行動電話網絡有不利影響；</li>
							<li>(7) 違反本條款；或</li>
							<li>(8) 對東陽行動電話之伺服器正常運作有不利影響者。 東陽行動電話得依其單獨判斷而暫停或禁止任一開發者於WHOOPY之權限。<br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;倘若您的內容是因為有瑕疵的或為惡意軟體而遭非自願性移除時，東陽行動電話得採取下列任一方法處理：</li>
						</ol>
						<ol>
							<li>(1) 停止支付您的資訊使用費；</li>
							<li>(2) 從您的付款帳戶追討將因銷售有瑕疵的或為惡意軟體之所得 ；</li>
							<li>(3) 向您收取因移除有瑕疵的或為惡意軟體之所生費用。</li>
						</ol>
					</li>
				</ul>
				<p><a name="a08">8. 「WHOOPY」改善計劃</a></p>
				<ul>
					<li>東陽行動電話保留對「WHOOPY」隨時進行改善、除錯之權利。您同意並了解「WHOOPY」於維修期間或其他時間無法正常運作。</li>
					<li>另東陽行動電話保留隨時終止「WHOOPY」或部份功能暫停使用之權利， 在這種情況下，您將獲得事先通知。</li>
				</ul>
				<p><a name="a09">9. 保密義務</a></p>
				<ul>
					<li>您可以提供對於有關「WHOOPY」之任何想法、意見或資訊，您並同意東陽行動電話得無償使用您所提供之意見以做為對「WHOOPY」之改進參考。您同意了解東陽行動電話根據 本條款所揭露之相關「WHOOPY」及東陽行動電話產品所有之營運的信息包含設計、商業計劃、商業機會、財務資料、研究成果，開發成果，know-how或其它第三 方之機密資訊將被視為並統稱為 「機密資訊」；但下列之資訊除外
						<ol>
							<li>(1) 東陽行動電話已對大眾公開; </li>
							<li>(2) 該等資訊您可以證明在東陽行動電話向您揭露前您已合法取得; </li>
							<li>(3) 由您自己獨立開發且未使 用任何機密資訊或 </li>
							<li>(4) 您透過第三方合法取得且第三方亦經合法管道透過東陽行動電話取得 。</li>
						</ol>
					</li>
					<li>您同意不對任何第三人披露、發佈本條所稱之“機密資訊”，您並進一步同意採取合理之預防措施以防止任何未經授權的使用，披露，出版，或傳播”機密資訊”。</li>
					<li>您同意在未經東陽行動電話書面同意之前，您不得基於您個人或任何第三人之利益使用「機密資訊」，並且東陽行動電話對您因參加「WHOOPY」所提供的資訊不負保密之義務。</li>
					<li>您同意並了解東陽行動電話將可能與其他程式開發者合作開發屬於東陽行動電話自有之產品，且這些產品可能現在或未來會與您的產品類似。</li>
					<li>對於您因簽署本條款、使用「WHOOPY」而所提供任何資訊，包括但不限於您產品內的資訊，您所賦予東陽行動電話之任何保密義務或使用限制，不論明示或暗示，東陽行動電話全部不予同意。</li>
				</ul>
				<p><a name="a10">10. 隱私和信息</a></p>
				<ul>
					<li>您同意並了解東陽行動電話為了持續改善「WHOOPY」將收集一些使用數據，包含但不限於產品、相關設備及「WHOOPY」之整體使用狀況。該等資訊之蒐集與您的個人資料都將受東陽行動電話隱私權政策之規範。</li>
				</ul>
				<p><a name="a11">11. 終止</a></p>
				<ul>
					<li>11.1 本條款將繼續有效，直至終止您或東陽行動電話終止本條款。</li>
					<li>11.2 如果您想終止本條款，您必需於三十日前對東陽行動電話發出書面通知，並立即停止使用 所核發給您之用戶帳號與密碼。</li>
					<li>11.3 東陽行動電話得基於下列之任一情形終止本條款：
						<ol>
							<li>(1) 您違反了任何本條款的規定；</li>
							<li>(2) 基於法律或法規之要求；</li>
							<li>(3) 您於50年內並無任何「WHOOPY」之活動紀 錄；</li>
							<li>(4) 您有申請解散、歇業、聲請破產、聲請重整、聲請清算或被票據交換所公告為拒絕往來戶等情事發生時，東陽行動電話決定終止「WHOOPY」之服務。</li>
						</ol>
					</li>
				</ul>
				<p><a name="a12">12. 免責聲明</a></p>
				<ul>
					<li>您了解並同意，當您使用「WHOOPY」和任何相關的軟體或服務時，東陽行動電話對於「WHOOPY」之服務包括但不限於穩定性、可依賴性與合適性，產品是否適用 於某一特殊用途、是否侵害他人權利等情形，不作任何明示或默示之擔保。對於因使用或無法使用本服務而衍生之任何直接、間接、意外、特別或重大之損壞、利益 喪失或業務中斷，您必需單方面承擔相關之風險。</li>
					<li>您同意使用「WHOOPY」服務係基於您的個人意願，並同意自負任何風險，包括因為透過本服務下載資料或圖片，或自本服務中獲得之資料導致您的電腦系統損壞，或是發生任何資料流失等結果。</li>
				</ul>
				<p><a name="a13">13. 責任限制</a></p>
				<ul>
					<li>您同意不論基於任何理由，東陽行動電話對您不就任何直接、間接、附隨、特別、衍生或懲罰性之損害負責，包括但不限於：資料滅失之損失，即使東陽行動電話已被告知或知悉有發生之可能亦同。</li>
				</ul>
				<p><a name="a14">14. 損害賠償</a></p>
				<ul>
					<li>在法律允許之最大限度下，您同意保障東陽行動電話及其董事，職員，僱員及代理商對抗任何所有第三方索賠，訴訟，訴訟或訴訟及任何和所有責任，損害，費用和開支 (包括合理的訴訟費用)，且這些損失是因您
						<ol>
							<li>(1) 您違反本條款；</li>
							<li>(2) 您所上傳至「WHOOPY」之產品；</li>
							<li>(3) 您侵權或違反任何版權，商標，商業秘密，專利或 其他知識產權權利，或誹謗任何個人或侵犯其隱私權；及</li>
							<li>(4) 您沒有採取合理措施保護您的用戶名稱和密碼導致被盜用。</li>
						</ol>
					</li>
				</ul>
				<p><a name="a15">15. 條款修正</a></p>
				<ul>
					<li>東陽行動電話保留隨時修改本條款之權利；新修正之條款效力並不溯及已上傳「WHOOPY」之產品。為了您可以繼續使用「WHOOPY」，您必須接受和同意的新修正後之條款；如果您不同意新的條款，您將被暫停或終止使用「WHOOPY」。</li>
				</ul>
				<p><a name="a16">16. 自由軟體</a></p>
				<ul>
					<li>如果您的產品包括任何自由軟體，您保證您遵守所有適用的自由軟體授權條款。</li>
				</ul>
				<p><a name="a17">17. 一般法律條款</a></p>
				<ul class="mar_b22">
					<li>17.1 準據法。
						<ol>
							<li>除非法律適用的禁止或另有規定外，本條款之效力、解釋、履行及有關事項，悉依中華民國法令處理。雙方均同意如因本合約之事項涉訟時，以台灣台北地方法院為第一審管轄法院。</li>
						</ol>
					</li>
					<li>17.2 權利轉讓
						<ol>
							<li>您不得未經東陽行動電話書面同意之前擅自將本條款之權利義務轉讓予他人，任何未經東陽行動電話事前書面同意之轉讓，將是無效的。</li>
						</ol>
					</li>
					<li>17.3 合約之可分割性 
						<ol>
							<li>本條款之條款因任何理由被視為無效、違法或無法執行，不得影響本條款其他條款之效力。</li>
						</ol>
					</li>
					<li>17.4 雙方關係 
						<ol>
							<li>雙方因本條款所成立之關係，不得視為為立約人雙方、或其關係企業創設任何合夥、合資、聘僱、或本人與代理人之關係。本條款並非利益第三人契約；您對第三人負有損害賠償責任者，應自負完全之責任，與東陽行動電話無關。</li>
						</ol>
					</li>
					<li>17.5 通知
						<ol>
							<li>本條款之任何通知應以書面(含電子郵件)形式為之；您同意東陽行動電話得以電子郵件之方式透過您註冊成為「WHOOPY」之開發者時所提供之電子郵件地址，寄送書面通知給您。 您可以更新您註冊時所提供的註冊資料而變更您的電子郵件地址。</li>
						</ol>
					</li>
					<li>17.6 權利之行使 
						<ol>
							<li>您同意並了解，如果東陽行動電話未行使基於本條款所賦予之權利義務，並不視為東陽行動電話正式拋棄此等權利義務，且東陽行動電話仍得行使該等權利。<br />您同意 “不利於起草者原則”於本條款並不適用。</li>
						</ol>
					</li>
					<li>17.7 出口管制
						<ol>
							<li>您聲明並保證，您透過「WHOOPY」所提供之產品內容完全遵守並符合您所在地區以及國際間相關對出口管制之法律或規定；這些法律或規定之限制包括對出口目的地的限制，使用方法和一般使用用戶的限制。</li>
						</ol>
					</li>
					<li>17.8 全部協議 
						<ol>
							<li>您同意並了解一旦您同意本條款，將對您及東陽行動電話產生法律拘束力，且您使用「WHOOPY」之所有行為亦受本條款之規範。本條款生效後將取代先前您與東陽行動電話間之一切共識或協議。</li>
						</ol>
						</li>
				</ul>
			</div>
			</div>
			<!-- //CONTENT TABLE E-->

		</div>
		<!-- //Content Area E -->	

	</div>
    <hr />
	<!-- //Container E-->	