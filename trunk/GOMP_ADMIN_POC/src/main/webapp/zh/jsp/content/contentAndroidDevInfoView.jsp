<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript">
<!--
	
	$(document).ready(function() {

		$("#listBtn").click(function() {
			var frm = $('#editForm');
			frm.attr("target", "_self");	
			frm.attr("action","./contentsStatusList.omp");
			frm.submit();
		}).css("cursor", "pointer");
		
		if('${content.drmYn}' != 'Y') {
			$('#licenseBtn').hide();
		}
	});
	
function licenseForDeveloper(cid){

	if(confirm("<gm:string value='jsp.content.contentAndroidDevInfo.btn.license'/>"))
	{
		var url = env.contextPath + "/content/licenseForDeveloper.omp";
		var param = {
			"content.cid" : cid
		};		

		$.postJSON(url, param, function(data){
			
			if(data.resultCode == 'error')
			{  
				alert("<gm:string value='jsp.content.contentAndroidDevInfo.msg.license'/>");
			} else if(data.resultCode == 'success') {
				location.href = env.contextPath + "/content/licenseForDeveloperDownload.omp?content.cid="+cid;
			}
		});		
	}
}
//-->
</script>

<div id="contents_area">
<form id="editForm" name="editForm" method="post">
	<input type="hidden" id="cid" name="content.cid"	value="${resultMap.cid}" />
	<input type="hidden" name="subContent.cid"	value="${resultMap.cid}" />

	<input type="hidden" name="content.searchValue"				value="${content.searchValue}" />
	<input type="hidden" name="content.searchType"				value="${content.searchType}" />
	<input type="hidden" name="content.saleSearchType" 			value="${content.saleSearchType}" />
	<input type="hidden" name="content.page.no"					value="${content.page.no}" />
		
	<input type="hidden" id="redirectUrl" name="redirectUrl" 	value="${redirectUrl}"/>
	<input type="hidden" id="tabGbn" 		name="tabGbn" 		value="${tabGbn}"/>
</form>
	<!-- Title Area S -->
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 產品上架/管理 &gt; 產品管理 <strong>&gt;</strong> <span>產品現狀</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_title03.gif'/>" alt="產品現狀" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		
		<!-- include contentBaseInfo -->
		<jsp:include page="contentBaseInfo.jsp"/>
		<!-- //Tab_menu E -->
		
		<h4 class="h41 fltl">
			<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_10.gif'/>" alt="Binary 檔資訊" />
			<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
				<div class="helpbox">
				<div class="helpboxin">
					<p>可同時上傳多個LCD類別最佳狀態之Apk檔.</p>
				</div>
				</div>
			</a>
		</h4>
		
		<div id="binaryList">
		<c:choose>
			<c:when test="${resultMap.subContentsCnt > 0}">
				<c:forEach items="${resultMap.subContsList}" var="subContent" varStatus="listIndex">	

					<div class="tstyleA mar_b22" id="divSubContent${listIndex.index}" name="divSubContent">
					<form id="editForm${listIndex.index}" name="editForm${listIndex.index}"  method="post">
						<input type="hidden" name="subContent.cid"	value="${resultMap.cid}" />
						<input type="hidden" name="subContent.scid"	value="${subContent.scid}"  id = "scid${listIndex.index}"/>
						<input type="hidden" name="subContent.runFile.runFilePos"	value="${subContent.runFilePos}" />
						<input type="hidden" name="subContent.runFile.runUploadFileName"	value="${subContent.runFileNm}" />
					
					<table summary="Binary 檔資訊">
						<caption>Binary 檔資訊</caption>
						<colgroup>
							<col width="21%" />
							<col />
						</colgroup>
						<tbody>
							<tr>
								<th scope="row"><span>*</span> LCD Size(可重複選擇) </th>
								<td> 
								<c:forEach items="${resultMap.lcdSizeChkBox}" var="lcdSize" varStatus="lcdListIndex">
									<input type="checkbox"" name="subContent.provisionItem" value="${lcdSize.dtlFullCd}" 
										<c:forEach items="${resultMap.provisionItemsList}" var="lcdSizeInfo" varStatus="privisionListIndex">		
											<c:if test="${lcdSizeInfo.scid == subContent.scid && lcdSizeInfo.itemCd == lcdSize.dtlFullCd}">checked="checked"</c:if>
										</c:forEach>
										disabled="disabled"/>
										<label for="lcd_1">&nbsp;<gc:text code="${lcdSize.dtlFullCd}"/></label>&nbsp;&nbsp;
								</c:forEach>		
								</td>
							</tr>
							<tr>
								<th scope="row" rowspan="2"><span>*</span> 上傳Binary 檔</th>
								<td>
									<span class="txtcolor04">${subContent.runFileNm}</span> &nbsp;
								</td>
							</tr>
							<tr>
								<td class="lh160">
									minSdkVersion : ${subContent.minSDKVersion} (${subContent.vmVerMin})<br />
									targetSdkVersion : ${subContent.targetSDKVersion}
									<c:if test="${not empty subContent.vmVerTarget && subContent.vmVerTarget ne 'N/A'}">(${subContent.vmVerTarget})</c:if><br />
									maxSdkVersion : ${subContent.maxSDKVersion}
									<c:if test="${not empty subContent.vmVerMax}">(${subContent.vmVerMax})</c:if><br />
									versionCode : ${subContent.versionCode}<br />
									versionName : ${subContent.versionName}<br />
									package : ${subContent.pkgNm}<br />
								</td>
							</tr>
							<tr  id="sprtPhoneListTr${listIndex.index}">
								<th class="tit01 tit03">選擇適用手機</th>
								<td class="bgnone">
									可以已上傳之Binary 檔為標準選擇手機.&nbsp;
									<div class="tstyleC mar_t10">
										<table summary="手機名稱,型號,OS 版本,LCD Size" class="w577 bbnone">
											<caption>手機名稱,型號,OS 版本,LCD Size</caption>
											<colgroup>
												<col width="7%" />
												<col width="18%" />
												<col width="25%" />
												<col width="18%" />
												<col />
											</colgroup>
											<thead>
											<tr>
												<th scope="col" class="tit06 btnone"><input type="checkbox" id="allSelPhoneChkBox${listIndex.index}"  disabled="disabled" checked="checked" /></th>
												<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit21.gif'/>" alt="手機名稱" /></th>
												<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit22.gif'/>" alt="型號" /></th>
												<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit23.gif'/>" alt="OS 版本" /></th>
												<th scope="col" class="tit06 btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit24.gif'/>" alt="LCD Size" /></th>
											</tr>
											</thead>
										</table>
									</div>
									<div class="tstyleC hl185" style="overflow-x:hidden;">
										<table summary="手機名稱" class="w577">
											<caption>手機名稱</caption>
											<colgroup>
												<col width="7%" />
												<col width="18%" />
												<col width="25%" />
												<col width="18%" />
												<col />
											</colgroup>
											<tbody>
											<c:choose>
												<c:when test="${not empty resultMap.targetPhoneList}">
													<c:forEach items="${resultMap.targetPhoneList}" var="phone" varStatus="phonelistIndex">	
														<c:choose>
															<c:when  test="${subContent.scid == phone.scid}">
																<tr>
																	<td><input type="checkbox" checked="checked" disabled="disabled" /></td>
																	<td>${phone.modelNm}</td>
																	<td>
																		<a href="javascript:gotoPhoneModelInfoList('${phone.mgmtPhoneModelNm}');">
																		${phone.mgmtPhoneModelNm}
																		</a>
																	</td>
																	<td><gc:text code="${phone.osVersion}"/></td>
																	<td class="brnone"><gc:text code="${phone.lcdSize}"/></td>
																</tr>
															</c:when>
														</c:choose>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<tr>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td>&nbsp;</td>
														<td class="brnone">&nbsp;</td>
													</tr>
												</c:otherwise>
											</c:choose>
											</tbody>
										 </table>
									 </div>
								</td>
							</tr>
						</tbody>
					</table>
					</form>
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise> 
				
				<p class="txtr"></p>
				<div name="divSubContent" class="tstyleA mar_b5"></div>
		
				<div class="tstyleA mar_b22" id="divSubContent${resultMap.subContentsCnt}" name="divSubContent">
				<form id="editForm${resultMap.subContentsCnt}" name="editForm${resultMap.subContentsCnt}"  method="post" enctype="multipart/form-data">
				<input type="hidden" name="subContent.cid"	value="${content.cid }" />
				<input type="hidden" name="subContent.scid"	value="${subContent.scid }" id = "scid${resultMap.subContentsCnt}" />
				
				<table summary="Binary 檔資訊">
					<caption>Binary 檔資訊</caption>
					<colgroup>
						<col width="21%" />
						<col />
					</colgroup>
					<tbody>
						<tr>
							<th scope="row"><span>*</span> LCD Size(可重複選擇) </th>
							<td>
								<label for="lcd_1" id="label${resultMap.subContentsCnt}"><gc:checkBoxs name="subContent.provisionItem" group="${CONST.PHONE_LCD_SIZE}" codeType="full" divide=" &nbsp;&nbsp;" extra="disabled='disabled'" split="&nbsp;"/></label>
							</td>		
						</tr>
						<tr>
							<th scope="row" class="tit01"><span>*</span> <label for="fileupload">上傳Binary 檔</label></th>
							<td class="bgnone">
								<span class="txtcolor04">${subContent.runFileNm}</span> &nbsp;
							</td>
						</tr>
					</tbody>
				</table>
				</form>
				</div>
			</c:otherwise>
		</c:choose>
		</div>
	
		

		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_11.gif'/>" alt="審核附加資訊" /></h4>
		<div class="tstyleA mar_b22">
		<form id="editEtcForm" name="editEtcForm" method="post" enctype="multipart/form-data">
		<input type="hidden" name="content.cid"	value="${resultMap.cid}" />
		<input type="hidden" id="drmSetOpt" name="content.drmSetOpt" value="${content.drmSetOpt}" />
			<table summary="審核附加資訊">
				<caption>審核附加資訊</caption>
				<colgroup>
					<col width="21%" />
					<col />
				</colgroup>
				<tbody>
					<tr> 
						<th scope="row"><span>*</span> Application DRM
							<a href="#" class="help"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
								<div class="helpboxin">
									<p>請選擇是否適用由Whoopy提供的ARM Library. 有關’ARMLibrary’之詳情請見[開發支援指南].</p>
								</div>
								</div>
							</a>
						</th>
						<td>
							<input type="radio" id="drmYnY" name="content.drmYn" value="Y" <c:if test='${content.drmYn == "Y"}'>checked</c:if> disabled='true' onclick="javascript:selectDrmYn('Y');"/><label for="radio1" > <gm:string value='jsp.content.contentAndroidDevInfo.lbl.license01'/></label>&nbsp;&nbsp;
							<input type="radio" id="drmYnN" name="content.drmYn" value="N" <c:if test='${content.drmYn != "Y"}'>checked</c:if> disabled='true' onclick="javascript:selectDrmYn('N');" /><label for="radio2"> <gm:string value='jsp.content.contentAndroidDevInfo.lbl.license02'/></label>
							<br />
							<span class="txt_90" id="licenseBtn">為測試ARM啟動與否, 請先下載.&nbsp;&nbsp;<a href="javascript:licenseForDeveloper('${content.cid}')"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_lidown.gif'/>" alt="下載許可" /></a></span>
						</td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="manual">使用指南</label> 
							<a href="#" class="help zindex1"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
								<div class="helpbox">
								<div class="helpboxin">
									<p>該資訊為有效進行產品審核所用, 請易懂, 客觀地記述.</p>
								</div>
								</div>
							</a>
						</th>
						<td class="bgnone">
							<span class="txtcolor04">${content.verifyScnrFileNm}</span> &nbsp;
						</td>
					</tr>
				</tbody>
			</table>
		</form>	
		</div>

		<IFRAME src="<c:url value='/content/iFrameVerifyCommentList.omp?content.cid=${content.cid }'/>" id="Verify" frameBorder=0 width=100% scrolling=no marginwidth=0 marginheight=10 height="175px"></iframe>
		
		<form id="editUpdateForm"  method="post">
		<input type="hidden" name="contentUpdate.cid"	value="${content.cid}" />
		<input type="hidden" name="contentUpdate.updateSeq"	value="" id="updateSeq" />
		<div id="idUpdateListDiv">
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_12.gif'/>" alt="更新紀錄管理" />
			<a href="#" class="help zindex2"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/icon_info.gif'/>" alt="" class="pad_b2" />
				<div class="helpbox">
				<div class="helpboxin">
					<p>可對販售產品之更新紀錄進行管理.</p>
				</div>
				</div>
			</a>
		</h4>
		<div class="tstyleC">
			<table summary="更新紀錄管理" class="w792 bbnone">
				<caption>更新紀錄管理</caption>
				<colgroup>
					<col width="18%" />
					<col width="18%" />
					<col />
					<col width="18%" />
				</colgroup>
				<thead>
					<tr>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit15.gif'/>" alt="適用日期" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit16.gif'/>" alt="上傳日期" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit17.gif'/>" alt="內容" /></th>
						<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit18.gif'/>" alt="修改/刪除" /></th>
					</tr>
				</thead>
			</table>
		</div>
		<div class="tstyleC h191" style="overflow-x:hidden;">
			<table summary="更新紀錄管理" class="w792 wbreak">
				<caption>更新紀錄管理</caption>
				<colgroup>
					<col width="18%" />
					<col width="18%" />
                    <col />
                    <col width="18%" />
				</colgroup>
				<tbody>
				<c:choose>
					<c:when test="${not empty resultContentUpdate }">
						<c:forEach items="${resultContentUpdate}" var="contentUpdate">
							<c:if test="${contentUpdate.contsUpdDtNum == 1}">
							<tr>
								<td rowspan="${contentUpdate.contsUpdDtRow}">${contentUpdate.contsUpdDt}</td>
								<td>${contentUpdate.insDttm}</td>
								<td class="tit01 wbtd">${contentUpdate.updateText}</td>
								<td class="brnone">
									<c:if test="${fn:trim(content.verifyPrgrYn) eq 'PD005399'  or fn:trim(content.verifyPrgrYn) eq 'PD005303'}">
										<a href="javascript:selectUpdateText('${contentUpdate.updateSeq}', '${contentUpdate.updateText}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif'/>" alt="修改"/></a> 
										<a href="javascript:deleteUpdateText('${contentUpdate.updateSeq}');""><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="刪除"/></a>
									</c:if>
									<c:if test="${fn:trim(content.verifyPrgrYn) ne 'PD005399' and fn:trim(content.verifyPrgrYn) ne 'PD005303'}">
										<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update04"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif'/>" alt="修改"/></a> 
										<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update05"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="刪除"/></a>
									</c:if>
								</td>
							</tr>
							</c:if>
							<c:if test="${contentUpdate.contsUpdDtNum != 1}">
							<tr>
								<td>${contentUpdate.insDttm}</td>
								<td class="tit01 wbtd">${contentUpdate.updateText}</td>
								<td class="brnone">
									<c:if test="${fn:trim(content.verifyPrgrYn) eq 'PD005399'  or fn:trim(content.verifyPrgrYn) eq 'PD005303'}">
										<a href="javascript:selectUpdateText('${contentUpdate.updateSeq}', '${contentUpdate.updateText}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif'/>" alt="修改"/></a> 
										<a href="javascript:deleteUpdateText('${contentUpdate.updateSeq}');""><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="刪除"/></a>
									</c:if>
									<c:if test="${fn:trim(content.verifyPrgrYn) ne 'PD005399' and fn:trim(content.verifyPrgrYn) ne 'PD005303'}">
										<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update04"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_modify.gif'/>" alt="修改"/></a> 
										<a href="javascript:alert('<gm:string value="jsp.content.contentAndroidDevInfo.btn.update05"/>');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="刪除"/></a>
									</c:if>
								</td>
							</tr>
							</c:if>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr><td colspan="4"><gm:string value='jsp.content.contentAndroidDevInfo.text.update.list02' /></td></tr>
						<tr><td colspan="4">&nbsp;</td></tr>
						<tr><td colspan="4">&nbsp;</td></tr>
						<tr><td colspan="4">&nbsp;</td></tr>
						<tr><td colspan="4">&nbsp;</td></tr>
					</c:otherwise>
				</c:choose>	
				</tbody>
			</table>
		</div>
		</div>
		</form>
	
		<div class="btnarea mar_t30">
			<p class="btn"><img id="listBtn" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_list.gif'/>" alt="目錄" /></p>
		</div>
	</div>
	<!-- //CONTENT TABLE E-->
	
</div>
	

