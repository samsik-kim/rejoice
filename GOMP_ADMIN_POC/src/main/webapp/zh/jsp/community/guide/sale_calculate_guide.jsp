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
				<p class="history">Home &gt; 開發商支援中心 &gt; 開發支援指南 <strong>&gt;</strong> <span>產品販售結算</span></p>
				<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_title.gif' />" alt="產品販售結算" /></h3>
			</div>
			<!-- //Title Area E -->
			
			<!-- CONTENT TABLE S-->

			<div id="contents">
				<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_txt01.gif'/>" alt="開發商可於此查看銷售中產品之每日產品販售現狀、產品類別販售現狀以及結算和匯款現狀." /></p>
				<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_stitle01.gif'/>" alt=" 1 每日販售現狀" /></h5>
				<p class="pad_l15 pad_b5">提供於Whoopy里正在[販售中]產品之每日產品銷售現狀. (※每日販售現狀以昨日為準合計)</p>
				<p class="pad_l15 pad_b10">若利用[下載excel檔]鍵可以得知附加資訊 (如, 產品管理編號等)</p>
				<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_img01.gif'/>" alt=" " /></p>
				<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_stitle02.gif'/>" alt="2 產品類別販售現狀" /></h5>
				<p class="pad_l15 pad_b10">提供開發商上架產品之各類別銷售現狀.</p>
				<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_img02.gif'/>" alt="by product" /></p>
				<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_stitle03.gif'/>" alt=" 3 結算報告" /></h5>
				<p class="pad_l15 pad_b10">結算報告以上月為準撰寫, 且每月25日公開, 開發商可於確認結算報告後請款.</p>
				
				<div class="ucbox mar_b35">
					<p class="uctxt01 pad_b15"><strong>1)結算及匯款流程</strong></p>
					<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_img03.gif'/>" alt="確認結算報告 &rarr; 請款 &rarr; 寄送發票或收據 &rarr; 待款 &rarr; 匯款完畢" /></p>
					<p class="ucbult02">請款流程由5個步驟組成.</p>
					<ol class="ucbult04 pad_b15">
						<li>1. 於每月10日左右為開發商提供結算報告. 開發商查看結算報告後, 確認匯款內容. 若匯款內容有誤, 透過客服中心或客戶諮詢可以發問.</li>
						<li>2. 若結算報告內容無誤,  可以請求匯款.</li>
						<li>3. 請款後, 將自訂的發票或收據寄送至公司地址.  (若是外國戶將收據掃描檔傳送至公司電子信箱.)</li>
						<li>4. 開發商可到[結算報告]確認請款進行狀態,  若我們已收到發票或收據,  狀態則轉換為[等待匯款].</li> <!-- 0602 수정 -->
						<li>5. 請款之帳款, 透過匯款程序, 完成匯款.  </li>
					</ol>
					<p class="uctxt01 pad_b7"><strong>2)結算報告說明</strong></p>
					<p class="uctxt02 pad_b7">- 結算報告範例如下</p>
					<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_img04.gif'/>" alt="結算報告範例如下" /></p>
					<p class="uctxt02 pad_b7">(1)名詞定義</p>
					<div class="tstyleE mar_b5">
						<table summary="名詞定義" class="uc">
							<caption>名詞定義</caption>
							<colgroup>
								<col width="16%" />
								<col />
							</colgroup>
							<thead>
								<tr>
									<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_th01.gif'/>" alt="名詞" /></th>
									<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_th02.gif'/>" alt="說明" /></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="bg01">交易月份</td>
									<td class="left brnone">產品交易之月份</td>
								</tr>
								<tr>
									<td class="bg01">銷售總金額</td>
									<td class="left brnone">於交易月份里應收取之總金額</td>
								</tr>
								<tr>
									<td class="bg01">未稅總金額</td>
									<td class="left brnone">銷售總金額 / 1.05</td>
								</tr>
								<tr>
									<td class="bg01">拆帳總金額</td>
									<td class="left brnone">未稅總金額承上拆分比</td>
								</tr>
								<tr>
									<td class="bg01">稅額</td>
									<td class="left brnone">
										依據會員類別, 適用不同稅率<br />
										- 個人戶 : -6%<br />
										- 公司戶 (一般營業戶) : 5%<br />
										- 公司戶 (小規模營業戶) : 0%<br />
										- 外國戶 : -20%
									</td>
								</tr>
								<tr>
									<td class="bg01">調帳總金額</td>
									<td class="left brnone">結算帳款時, 會計營運團隊確認後所產生之調整金額</td>
								</tr>
								<tr>
									<td class="bg01">實付金額</td>
									<td class="left brnone">拆帳總金額 + 稅額 + 調帳總金額</td>
								</tr>
								<tr>
									<td class="bg01">付款貨幣</td>
									<td class="left brnone">開發者所選擇之匯款幣別</td>
								</tr>
								<tr>
									<td class="bg01">匯款金額</td>
									<td class="left brnone">
										- 若是個人戶或公司戶,  匯款金額與實付金額相同<br /> 
										- 若是外國戶, 依據交易月份平均匯率兌換成美元後加以標示
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<p class="ucbult06 mar_b15">每月25日左右公開上月產品銷售之結算報告(若公開日為週末或假日, 將於次日提供)</p>
					<p class="uctxt02 pad_b2">(2)結算範例</p>
					<p class="uctxt02 pad_l15 txt_90">- 2011年 06月份, 銷售總金額為125,700, 調帳金額為 10,000</p>
					<p class="ucbult02 fltl mar_t10">個人戶</p>
					<ul class="ucbult05 pad_b15">
						<li>拆帳總金額 : (125,700/1.05) × 65% =81,705 </li>
						<li style="width:400px;">實付金額 : 81,705 (拆帳總金額) – 4,900 (稅額) + 10,000 (調帳總金額) =83,535</li>
						<li>稅額&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: -4,900 (-6%)</li>
						<li class="ucline">而個人戶的付款貨幣為台幣, 匯款金額為83,535元.</li>
					</ul>
					<p class="ucbult02 fltl">公司戶(一般營業戶)</p>
					<ul class="ucbult05 pad_b15">
						<li>拆帳總金額 : (125,700/1.05) × 65% =81,705 </li>
						<li style="width:400px;">實付金額 : 81,705 (拆帳總金額) + 6,285 (稅額) + 10,000 (調帳總金額) =97,990</li>
						<li>稅額&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: +6,285 (一般營業 : +5%)</li>
						<li class="ucline">而公司戶(一般營業戶)的付款貨幣為台幣, 匯款金額為97,990.</li>
					</ul>
					<p class="ucbult02 fltl">外國戶</p>
					<ul class="ucbult05 pad_b15">
						<li>拆帳總金額 : (125,700/1.05) × 65% =81,705</li>
						<li>平均匯率 : 美元USD =0.03</li>
						<li>稅額&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;: -16,341 (外國戶 : -20%)</li>
						<li class="ucline">而外國戶付款貨幣為美元, 匯款金額為2260.92$美元.</li>
						<li style="width:400px;">實付金額&nbsp;&nbsp;&nbsp;&nbsp; : 81,705 (拆帳總金額) – 16,341 (稅額) + 10,000 (調帳總金額) =75,364</li>
					</ul>
					
					<p class="uctxt02 pad_b7 cb">(3)匯款狀態定義</p>
					<div class="tstyleE mar_b5">
						<table summary="匯款狀態定義" class="uc">
							<caption>匯款狀態定義</caption>
							<colgroup>
								<col width="16%" />
								<col />
							</colgroup>
							<thead>
								<tr>
									<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_th03.gif'/>" alt="匯款狀態" /></th>
									<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_th02.gif'/>" alt="說明" /></th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="bg01">次月處理</td>
									<td class="left brnone">
										次月處理 : 每月5日後, 狀態轉換為[次月處理]的原因有.<br />
										- 範例1) 本月10日至次月30日未請款<br /> <!-- 0602 수정 -->
										- 範例2) 已請款, 但未提交發票和收據<br />
										- 範例3) 已請款, 交完發票和收據, 但資料有誤
									</td>
								</tr>
								<tr>
									<td class="bg01">等待匯款 </td> <!-- 0602 수정 -->
									<td class="left brnone">請款完畢, 但未寄送發票或收據</td>
								</tr>
								<tr>
									<td class="bg01">資料無誤</td> <!-- 0602 수정 -->
									<td class="left brnone">請款完畢, 也寄完發票和收據</td>
								</tr>
								<tr>
									<td class="bg01">等待匯款</td> <!-- 0602 수정 -->
									<td class="left brnone">等待匯款之狀態(初次帳款或再次請款之帳款)</td>
								</tr>
								<tr>
									<td class="bg01">匯款完畢</td>
									<td class="left brnone">匯款完畢之狀態</td>
								</tr>
								<tr>
									<td class="bg01">再次匯款</td>
									<td class="left brnone">請款之帳款中,  由於銀行資料有誤而未能匯款, 查正後將於次月進行匯款</td>
								</tr>
								<tr>
									<td class="bg01">匯款失敗</td>
									<td class="left brnone">請款之帳款中,  由於銀行資料有誤而未能匯款, 但無法修正錯誤, 狀態則變為[匯款失敗]</td>
								</tr>
							</tbody>
						</table>
					</div>
					<p class="ucbult06">對於[匯款失敗]之帳款, 請利用[客戶詢問(客戶諮詢)]或與客服中心詢問!</p>
				</div>
				
				<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_stitle04.gif'/>" alt="發票開立方式" /></h5>
				<p class="pad_l15 pad_b10">該收據僅限於個人戶及外國戶使用, 若為公司戶, 請依據貴公司內的發票格式開立後發送於此。<br />請參照以下格式開立發票。</p>
				<div class="ucbox">
					<p class="mar_b22"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_img05.gif'/>" alt="" class="ucimg" /></p>
					<p class="ucbult01 pad_b5"><strong>個人戶 </strong></p>
					<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_img06.gif'/>" alt="" /></p>
					<ol class="uclist01">
						<li>填寫產品之販售年份及月份.</li>
						<li>請按照範例, 將結算報告中的該月拆帳大寫輿小寫金額填寫在內. </li>
						<li>請將結算報告中的該月拆帳總金額、稅額、實付金額以小寫方式填寫於欄位中. </li>
						<li>請填寫於加入開發商專區會員時所填寫之資訊. </li>
						<li>請確認 1－4 的內容後簽章.</li>
						<li>請以中華民國歷編寫收據. </li>
					</ol>
					<p class="mar_b25">* 若以上填寫內容與結算報告或會員資訊有出入, 將無法順利進行匯款. 請於准確填寫並確認後發送.</p>
					<p class="ucbult01 pad_b5"><strong>外國戶</strong></p>
					<p><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_sr_img07.gif'/>" alt="" /></p>
					<ol class="uclist02">
						<li>填寫產品之販售年份及月份. (範例: MM/YYYY – 03/2011)</li>
						<li>請將結算報告中的實付金額填寫在內. </li>
						<li>請將結算報告中的該月拆帳總金額、稅額、實付金額以小寫方式填寫於欄位中. </li>
						<li>請填寫於加入開發商轉區會員時所填寫之資訊及銀行資料.</li>
						<li>請確認 1 - 4 的內容後簽章.</li>
					</ol>
					<p>* 以上填寫內容與結算報告或會員資訊有出入, 將無法順利進行匯款。請於准確填寫並確認後發送.</p>
				</div>	
			</div>
			<!-- //CONTENT TABLE E-->

		</div>
		<!-- //Content Area E -->