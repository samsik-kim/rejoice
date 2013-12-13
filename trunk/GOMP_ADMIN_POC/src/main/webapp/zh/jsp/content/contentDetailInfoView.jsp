<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 

<script type="text/javascript">
<!--
	var smmrText = "<gm:string value='jsp.content.contentDetailInfo.text.smmrText' />";
	var dtlText = "<gm:string value='jsp.content.contentDetailInfo.text.dtlText' />";

	$(document).ready(function() {
	 
	 	<c:if test="${empty content.prodDescSmmr}">
	 		$('#prodDescSmmr').val(smmrText);
	 	</c:if>
	 	
	 	<c:if test="${empty content.prodDescDtl}">
	 		$('#prodDescDtl').val(dtlText);
	 	</c:if>
	 	
	 	$('#listBtn').click(function(){
	 		var frm = $('#editForm');
			frm.attr("target", "_self");	
			frm.attr("action","./contentsStatusList.omp");
			frm.submit();
	 		
	 	}).css("cursor", "pointer");
	});


	// page move mypagepage
	function gotoMemberPayInfo() {
		location.href = "${CONF['omp.common.url-prefix.https.dev']}${pageContext.request.contextPath }" + "/mypage/mypageIntro.omp?forwardAction=CALCULATE";
	}
//-->
</script>
<style>							
	.fileinputs {position: relative; overflow: hidden; height: 24px; width: 600px;}
	.fileinputs * {vertical-align: middle;}
	.fakefile {position: absolute; top:0px; left:0px; height: 30px;  z-index: 1;}
	.inputFile {position: relative; text-align: right; top: -12px; width: 500px; height:40px; filter: alpha(opacity=0); opacity: 0; z-index: 2; direction: ltl; selector-dummy: expression(this.hideFocus=true);}
</style>

<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 產品上架/管理 &gt; 產品管理 <strong>&gt;</strong> <span>產品現狀</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title03.gif'/>" alt="產品現狀" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
	<form id="editForm" name="editForm"  method="post" >	
		<input type="hidden" id="cid" name="content.cid" 			value="${content.cid }" />
		<input type="hidden" name="content.vmType" 					value="${content.vmType }" />
		<input type="hidden" name="content.searchValue"				value="${content.searchValue}" />
		<input type="hidden" name="content.searchType"				value="${content.searchType}" />
		<input type="hidden" name="content.saleSearchType" 			value="${content.saleSearchType}" />
		<input type="hidden" name="content.page.no"					value="${content.page.no}" />
		
		<input type="hidden" id="redirectUrl" name="redirectUrl" 	value="${redirectUrl}"/>

		<!-- include contentBaseInfo -->
		<jsp:include page="contentBaseInfo.jsp"/>
		<!-- //Tab_menu E -->
		
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_06.gif'/>" alt="基本資訊" /></h4>
		<div class="tstyleA mar_b22">
			<table summary="基本資訊">
				<caption>基本資訊</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">
							<label for="product"><span>*</span> 產品名稱</label>
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>無法使用部分特殊符號(&lt;,&gt;),且不得超過45Byte.</p>
							</div>
							</div>
							</a>
						</th>
						<td><input type="text" id="prodNm" name="content.prodNm" value="<g:tagAttb value="${content.prodNm}"/>" onkeyup="javascript:cuttingAsByte('prodNm', 30, false);" maxlength="15"  class="w150" readonly="readonly"/></td>
					</tr>
					<tr>
						<th scope="row">
							<label for="seller"><span>*</span> 銷售人名稱</label>
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>顯示於手機之產品販賣者名稱.</p>
								<p>最多可輸入16字與50個英文字母.</p>
							</div>
							</div>
							</a>
						</th>
						<td><input type="text" id="exposureDevNm" name="content.exposureDevNm" class="w150" value="<g:tagAttb value="${content.exposureDevNm}"/>"  readonly="readonly"/> &nbsp;<span class="txt_90">銷售人名稱為預設, 可修改</span></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="proprice"> 產品價格</label></th>
						<td>NT$ 
							<input type="text" id="prodPrc" name="content.prodPrc" class="w150" value="<g:text value="${content.prodPrc}" format='R#,###,###,###'/>" readonly="readonly"/> &nbsp;
							<span class="txt_90">(含附加稅)</span> <br />
							<c:if test="${content.payMemberInfo == CONST.MEM_TYPE_DEV_NOPAY}" >
								<span class="txt_90">付費產品的審核及販售僅限於填寫銀行資料的會員使用</span>&nbsp;
								<a href="javascript:gotoMemberPayInfo();"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_calculate.gif'/>" alt="填寫銀行資料" /></a>
							</c:if>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="promotion">宣傳網址</label></th>		
						<td><input type="text" id="product" name="content.promotionUrl"  value="<c:out value='${content.promotionUrl eq null ? "http://" : content.promotionUrl}' />" class="w400" readonly="readonly"/> &nbsp;<span class="txt_90"><br />若具備App外部宣傳途徑(例如:Youtube影像), 請將網絡地址輸入於此.</span></td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="productnum">產品管理號</label>
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
								<div class="helpboxin">
									<p>銷售人可按照其他需要(例如: 3者結算等)填入自行管理識別碼</p>
									<p>該碼按產品類別顯示於”產品販賣現狀”選單中</p>
								</div>
								</div>
							</a>
						</th>
						<td class="bgnone"><input type="text" id="corpProdNo" name="content.corpProdNo" value="<g:tagAttb value='${content.corpProdNo}'/>" class="w400" readonly="readonly"/> &nbsp;<span class="txt_90">英文, 數字混合碼</span></td>
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_07.gif'/>" alt="類別資訊" /></h4>
		<div class="tstyleA mar_b22">
			<table summary="類別資訊">
				<caption>類別資訊</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> 類別</th>
						<td>
							<input type="hidden" name="content.ctgrDepth"  value="${content.ctgrDepth}" />
							<input type="hidden" name="content.ctgrCd"  value="${content.ctgrCd}" />
							<c:choose>
								<c:when test="${not empty content.ctgrNm}">
									<span id="contentCtgrNm">${content.ctgrNm}</span> &nbsp;
									<c:if test="${content.verifyPrgrYn == CONST.CODE_VERIFY_INIT || content.verifyPrgrYn == CONST.CODE_VERIFY_END}">
									<a href="javascript:popSelectCtgrList('${content.cid}');">
									<img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pm/btn_cata02.gif" alt="選擇類別" />
									</a>
									</c:if>
								</c:when>
								<c:otherwise>
									<c:if test="${content.verifyPrgrYn == CONST.CODE_VERIFY_INIT || content.verifyPrgrYn == CONST.CODE_VERIFY_END}">
									<span id="contentCtgrNm"></span> &nbsp;<a href="javascript:popSelectCtgrList('${content.cid}');"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pm/btn_cata.gif" alt="選擇類別" /></a>
									</c:if>
								</c:otherwise>
							</c:choose>	
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 使用等級
							<a href="#" class="help">
							<img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif" alt="help" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>若為未成年人不得使用的產品, 請點選”限制級”</p>
							</div>
							</div>
							</a>
						</th>
						<td>
							<input type="radio" name="content.gameDelibGrd" value="${CONST.GRADE_ALL}"  <c:out value="${content.gameDelibGrd == CONST.GRADE_ALL ?  'checked=checked' : ''}" /> disabled="disabled" /><label for="radio1"> <gm:string value='jsp.content.contentDetailInfo.lbl.gameDelibGrd01' /></label>&nbsp;&nbsp;
							<input type="radio" name="content.gameDelibGrd" value="${CONST.GRADE_ADULT}" <c:out value="${content.gameDelibGrd == CONST.GRADE_ADULT ?  'checked=checked' : ''}" /> disabled="disabled"  /><label for="radio2"> <gm:string value='jsp.content.contentDetailInfo.lbl.gameDelibGrd02'/></label>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> 填寫關鍵字連結
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>無法使用部分特殊符號(&lt;,&gt;),且不得超過30Byte.</p>
							</div>
							</div>
							</a>
						</th>
						<td class="bgnone lh210">
							<input type="text" id="tagInfoSeq0" name="content.tagNm" class="w128"  value="<g:tagAttb value='${contentTagMap.tagNm1}' />" readonly="readonly"/>
							<input type="text" id="tagInfoSeq1" name="content.tagNm" class="w128"  value="<g:tagAttb value='${contentTagMap.tagNm2}' />" readonly="readonly"/>
							<input type="text" id="tagInfoSeq2" name="content.tagNm" class="w128"  value="<g:tagAttb value='${contentTagMap.tagNm3}' />" readonly="readonly"/>
							<input type="text" id="tagInfoSeq3" name="content.tagNm" class="w128"  value="<g:tagAttb value='${contentTagMap.tagNm4}' />" readonly="readonly"/><br />
							<input type="text" id="tagInfoSeq4" name="content.tagNm" class="w128"  value="<g:tagAttb value='${contentTagMap.tagNm5}' />" readonly="readonly"/>
							&nbsp;<span class="txt_90">* 不得使用特殊符號</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_08.gif'/>" alt="產品說明" /></h4>
		<div class="tstyleA mar_b22">
			<table summary="產品說明">
				<caption>產品說明</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <label for="abstract">簡介說明</label></th>
						<td><input type="text" id="prodDescSmmr" name="content.prodDescSmmr" value="<g:tagAttb value="${content.prodDescSmmr}"/>" class="w520" readonly="readonly" /></td>		
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="detailexp">詳細說明</label></th>
						<td>
							<textarea id="prodDescDtl" name="content.prodDescDtl" cols="1" rows="1" class="w520" readonly="readonly" ><gm:html value='${content.prodDescDtl}' /></textarea>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="expimg">說明圖片</label> 
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
									<div class="helpboxin">
										<p>可利用圖片說明產品詳細資訊.</p>
									</div>
								</div>
							</a>
						</th>
						<td class="bgnone">
							<a href="<c:url value="/fileSupport/fileDown.omp">
								<c:param name="bnsType" value="common.path.http-share.product"/>
								<c:param name="filePath" value="${content.descImg.filePos}"/>
								<c:param name="fileName" value="${content.descImg.fileNm}"/>
								</c:url>" >${content.descImg.fileNm}
							</a> &nbsp;
							
							<a href="javascript:popPreview('${content.cid }');">
								<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_view1.gif'/>" alt="預覽" id="descImgPreviewBtn"/>
							</a>	
						</td>		
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_09.gif'/>" alt="產品圖片" /></h4>
		<div class="tstyleA">
			<table summary="產品圖片">
				<caption>產品圖片</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <label for="icon">圖示</label>
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
									<div class="helpboxin">
										<p>若上傳尺寸為212*212的JPG檔案, 將自動轉換符合手機頁面尺寸之圖示</p>
									</div>
								</div>
							</a>
						</th>
						<td>
							<p>
								<a href="<c:url value="/fileSupport/fileDown.omp">
									<c:param name="bnsType" value="common.path.http-share.product"/>
									<c:param name="filePath" value="${content.iconImg1.filePos}"/>
									<c:param name="fileName" value="${content.iconImg1.fileNm}"/>
									</c:url>" >${content.iconImg1.fileNm }</a> &nbsp;<br /></p>
							<p class="vb">
								<span class="imgbox"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000108}" alt="72" width="72" height="72"/></span>
								<span class="imgbox1"><img src="${CONF['omp.common.url.http-share.product']}${contentImageMap.DP000109}" alt="130"   width="130" height="130"/></span>
							</p>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><span>*</span> 預覽
						<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
						<div class="helpbox">
						<div class="helpboxin">
							<p>請上傳符合手機螢幕解析度之手機預覽圖片</p>
							<p>請上傳可充分說明產品之圖片(200KB以內, JPG)</p>
						</div>
						</div>
						</a>
						</th>
						<td class="bgnone lh210">
							<a href="<c:url value="/fileSupport/fileDown.omp">
								<c:param name="bnsType" value="common.path.http-share.product"/>
								<c:param name="filePath" value="${content.previewImg1.filePos}"/>
								<c:param name="fileName" value="${content.previewImg1.fileNm}"/>
								</c:url>" >${content.previewImg1.fileNm }</a> &nbsp;<br />
							<a href="<c:url value="/fileSupport/fileDown.omp">
								<c:param name="bnsType" value="common.path.http-share.product"/>
								<c:param name="filePath" value="${content.previewImg2.filePos}"/>
								<c:param name="fileName" value="${content.previewImg2.fileNm}"/>
								</c:url>" >${content.previewImg2.fileNm }</a> &nbsp;<br />
							<a href="<c:url value="/fileSupport/fileDown.omp">
								<c:param name="bnsType" value="common.path.http-share.product"/>
								<c:param name="filePath" value="${content.previewImg3.filePos}"/>
								<c:param name="fileName" value="${content.previewImg3.fileNm}"/>
								</c:url>" >${content.previewImg3.fileNm }</a> &nbsp;<br />
							<a href="<c:url value="/fileSupport/fileDown.omp">
								<c:param name="bnsType" value="common.path.http-share.product"/>
								<c:param name="filePath" value="${content.previewImg4.filePos}"/>
								<c:param name="fileName" value="${content.previewImg4.fileNm}"/>
								</c:url>" >${content.previewImg4.fileNm }</a> &nbsp;<br />
								
							<c:if test="${not empty content.previewImg1.filePos && not empty content.previewImg2.filePos && not empty content.previewImg3.filePos && not empty content.previewImg4.filePos}" >
								<a href="javascript:popImagePreview('${content.cid }');" class="fltr mar_mt20"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_transfor.gif'/>" alt="預覽變換檔案" /></a>
							</c:if>	
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="guideinfo">
			<ul class="bult02 txt_90">
				<li>可隨意修改詳細資訊項目, 且可以立即反應修改結果</li> 
				<li>若於請審期間, 審核通過後才可修改資訊</li>
				<li>詳情可於 <span class="txtcolor04">開發支援指南</span>中確定</li>
				<li><span class="txtcolor03">*</span> 為必填欄位</li>
			</ul>
		</div>
		<div class="btnarea mar_t30">
			<p class="btn"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_list.gif'/>" alt="目錄" id="listBtn" /></p>
		</div>
	</form>
	</div>
	<!-- //CONTENT TABLE E-->

</div>
