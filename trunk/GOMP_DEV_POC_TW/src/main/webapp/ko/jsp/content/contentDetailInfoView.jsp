<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 

<script type="text/javascript">
<!--
	var smmrText = "최대 32자까지 등록 가능하며 html tag 사용은 불가능합니다.";
	var dtlText = "최대 1300자까지 등록 가능하며, Hyperlink를 위한 간단한 tag에 한하여 삽입이 가능합니다. 예)  <a herf=‘http://www.tstore.co.kr’>T store</a>";

	$(document).ready(function() {
	 
	 	<c:if test="${empty content.prodDescSmmr}">
	 		$('#prodDescSmmr').val(smmrText);
	 	</c:if>
	 	
	 	<c:if test="${empty content.prodDescDtl}">
	 		$('#prodDescDtl').val(dtlText);
	 	</c:if>
	 	
	});

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
		<p class="history">Home &gt; 상품등록/관리 &gt; 상품관리 <strong>&gt;</strong> <span>상품현황</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title03.gif'/>" alt="상품현황" /></h3>
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
		
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_06.gif'/>" alt="기본정보" /></h4>
		<div class="tstyleA mar_b22">
			<table summary="상품기본정보 입력">
				<caption>상품기본정보 입력</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row">
							<label for="product"><span>*</span> 상품명</label>
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>일부 특수문자(<,>)는 사용이 불가하며 최대 30Byte까지 입력 가능합니다.</p>
							</div>
							</div>
							</a>
						</th>
						<td><input type="text" id="prodNm" name="content.prodNm" value="<g:html value='${content.prodNm}'/>" onkeyup="javascript:cuttingAsByte('prodNm', 30, false);" maxlength="15"  class="w150" readonly="readonly"/></td>
					</tr>
					<tr>
						<th scope="row">
							<label for="seller"><span>*</span> 판매자 노출명</label>
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>Shop client에 표시될 상품의 판매자명을 뜻합니다.</p>
								<p>최대 16 글자, 영문 최대 50 글자까지 입력이 가능합니다.</p>
							</div>
							</div>
							</a>
						</th>
						<td><input type="text" id="exposureDevNm" name="content.exposureDevNm" class="w150" value="<g:html value='${content.exposureDevNm}'/>"  readonly="readonly"/> &nbsp;<span class="txt_90">Default는 판매자명이며, 수정 가능합니다.</span></td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="proprice">상품가격</label></th>
						<td>
							<input type="text" id="prodPrc" name="content.prodPrc" class="w150" value="<g:text value="${content.prodPrc}" format='R#,###,###,###'/>" readonly="readonly"/> &nbsp;
							<span class="txt_90">원 (부가가치세 포함)</span> <br /><span class="txt_90">유료상품은 정산정보를 등록하신 회원에 한해 검증/판매가 가능 합니다.</span>&nbsp;
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="promotion">프로모션 URL</label></th>		
						<td><input type="text" id="product" name="content.promotionUrl"  value="<c:out value='${content.promotionUrl eq null ? "http://" : content.promotionUrl}' />" class="w400" readonly="readonly"/> &nbsp;<span class="txt_90"><br />App 홍보가 가능한 외부 리소스 (예: Youtube 동영상) 가 있는 경우 입력해주세요.</span></td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="productnum">상품 관리번호</label>
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
								<div class="helpboxin">
									<p>판매자가 별도의 목적 (예. 3자 정산 등)을 위해 자제척으로 관리하는 식별코드를 입력할 수 있습니다.</p>
									<p>해당 식별코드는 ‘상품별 판매현황’ 메뉴에서 상품별로 함께 제공이 됩니다.</p>
								</div>
								</div>
							</a>
						</th>
						<td class="bgnone"><input type="text" id="corpProdNo" name="content.corpProdNo" value="<g:html value='${content.corpProdNo}'/>" class="w400" readonly="readonly"/> &nbsp;<span class="txt_90">영문과 숫자를 조합하여 입력해주세요.</span></td>
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_07.gif'/>" alt="분류정보" /></h4>
		<div class="tstyleA mar_b22">
			<table summary="상품분류정보 입력">
				<caption>상품분류정보 입력</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> 분류</th>
						<td>
							<input type="hidden" name="content.ctgrDepth"  value="${content.ctgrDepth}" />
							<input type="hidden" name="content.ctgrCd"  value="${content.ctgrCd}" />
							<c:choose>
								<c:when test="${not empty content.ctgrNm}">
									<span id="contentCtgrNm">${content.ctgrNm}</span> &nbsp;
									<c:if test="${content.verifyPrgrYn == CONST.CODE_VERIFY_INIT || content.verifyPrgrYn == CONST.CODE_VERIFY_END}">
									<a href="javascript:popSelectCtgrList('${content.cid}');">
									<img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pm/btn_cata02.gif" alt="분류 수정하기" />
									</a>
									</c:if>
								</c:when>
								<c:otherwise>
									<c:if test="${content.verifyPrgrYn == CONST.CODE_VERIFY_INIT || content.verifyPrgrYn == CONST.CODE_VERIFY_END}">
									<span id="contentCtgrNm"></span> &nbsp;<a href="javascript:popSelectCtgrList('${content.cid}');"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pm/btn_cata.gif" alt="분류 선택하기" /></a>
									</c:if>
								</c:otherwise>
							</c:choose>	
						</td>
					</tr>
					<tr>
						<th scope="row"><span>*</span> 이용등급
							<a href="#" class="help">
							<img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif" alt="도움말" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>미성년자가 이용 불가능한 상품은 “성인 이용가” 로 선택해 주세요.</p>
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
						<th scope="row" class="tit01"><span>*</span> 태그입력
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
							<div class="helpbox">
							<div class="helpboxin">
								<p>상품 검색 시 다양한 Keyword를 입력해 주세요.</p>
							</div>
							</div>
							</a>
						</th>
						<td class="bgnone lh210">
							<input type="text" id="tagInfoSeq0" name="content.tagNm" class="w128"  value="${contentTagMap.tagNm1}" readonly="readonly"/>
							<input type="text" id="tagInfoSeq1" name="content.tagNm" class="w128"  value="${contentTagMap.tagNm2}" readonly="readonly"/>
							<input type="text" id="tagInfoSeq2" name="content.tagNm" class="w128"  value="${contentTagMap.tagNm3}" readonly="readonly"/><br />
							<input type="text" id="tagInfoSeq3" name="content.tagNm" class="w128"  value="${contentTagMap.tagNm4}" readonly="readonly"/>
							<input type="text" id="tagInfoSeq4" name="content.tagNm" class="w128"  value="${contentTagMap.tagNm5}" readonly="readonly"/>
							&nbsp;<span class="txt_90">* 특수문자 사용불가</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_08.gif'/>" alt="상품설명" /></h4>
		<div class="tstyleA mar_b22">
			<table summary="상품설명정보 입력">
				<caption>상품설명정보 입력</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <label for="abstract">요약 설명</label></th>
						<td><input type="text" id="prodDescSmmr" name="content.prodDescSmmr" value="${content.prodDescSmmr}" class="w410" readonly="readonly" /></td>		
					</tr>
					<tr>
						<th scope="row"><span>*</span> <label for="detailexp">상세 설명</label></th>
						<td>
							<textarea id="prodDescDtl" name="content.prodDescDtl" cols="1" rows="1" class="w410" readonly="readonly" >${content.prodDescDtl }</textarea>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="expimg">설명이미지</label> 
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
								<div class="helpbox">
									<div class="helpboxin">
										<p>상품에 대한 보다 자세한정보를 이미지로 표현할 수 있습니다.</p>
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
								<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_view1.gif'/>" alt="미리보기" id="descImgPreviewBtn"/>
							</a>	
						</td>		
					</tr>
				</tbody>
			</table>
		</div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_09.gif'/>" alt="상품이미지" /></h4>
		<div class="tstyleA">
			<table summary="상품이미지정보 입력">
				<caption>상품이미지정보 입력</caption>
				<colgroup>
					<col width="18%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> <label for="icon">대표아이콘</label>
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
								<div class="helpbox">
									<div class="helpboxin">
										<p>212*212 사이즈의 JPG파일을 등록하면 Shop Client에서 사용되는 이미지 사이즈에 맞게 자동 생성됩니다.</p>
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
						<th scope="row" class="tit01"><span>*</span> 미리보기
						<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="도움말" class="pad_b2" />
						<div class="helpbox">
						<div class="helpboxin">
							<p>Shop Client의 상품 상세의 미리보기 기능에 제공되는 이미지를 등록합니다.</p>
							<p>상품 내용을 잘 표현할 수 있는 이미지를 등록해주세요. (200KB 이하, JPG)</p>
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
								<a href="javascript:popImagePreview('${content.cid }');" class="fltr mar_mt20"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_transfor.gif'/>" alt="변환된 이미지 확인" /></a>
							</c:if>	
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="guideinfo">
			<ul class="bult02 txt_90">
				<li>상세정보 항목은 자유롭게 수정하실 수 있으며, 실시간으로 수정 반영됩니다.</li> 
				<li>검증 요청 중일 경우 검증이  완료된 이후에 수정 가능합니다.</li>
				<li>더 자세한 안내는 <span class="txtcolor04">개발지원가이드</span>를 통해 확인하실 수 있습니다.</li>
				<li><span class="txtcolor03">*</span> 가 표시된 부분은 필수입력 항목입니다.</li>
			</ul>
		</div>
		<div class="btnarea mar_t30">
			<p class="btn"><a href="<c:url value='/content/contentsStatusList.omp'/>"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_list.gif'/>" alt="목록" /></a></p>
		</div>
	</form>
	</div>
	<!-- //CONTENT TABLE E-->

</div>
