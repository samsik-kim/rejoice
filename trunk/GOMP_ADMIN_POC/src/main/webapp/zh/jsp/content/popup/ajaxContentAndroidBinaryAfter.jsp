<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<script type="text/javascript">
<!--
	$(document).ready(function() {
		if(parseInt('${resultMap.subContentsCnt}') <= 0) {
			$('#verifyReqBtn').remove();
		}
	});
//-->
</script>
<c:choose><c:when test="${resultMap.subContentsCnt > 0}"><c:forEach items="${resultMap.subContsList}" var="subContent" varStatus="listIndex">	
			<div class="tstyleA mar_b22" id="divSubContent${listIndex.index}" name="divSubContent">
			<form id="editForm${listIndex.index}" name="editForm${listIndex.index}"  method="post">
				<input type="hidden" name="subContent.cid"	value="${resultMap.cid}" />
				<input type="hidden" name="subContent.scid"	value="${subContent.scid}"  id = "scid${listIndex.index}"/>
				<input type="hidden" name="subContent.runFile.runFilePos"	value="${subContent.runFilePos}" />
				<input type="hidden" name="subContent.runFile.runUploadFileName"	value="${subContent.runFileNm}" />
				<input type="hidden" name="subContent.sprtPhoneModel" value="" />
				<input type="hidden" name="subContent.delYn" value="${subContent.delYn}"  id = "delYn${listIndex.index}"/>
				<input type="hidden" id = "modifySubContentIndex${listIndex.index}" name="modifySubContentIndex"	value="${modifySubContentIndex}" />
			<table summary="Binary 檔資訊" id="binaryInfoTable${listIndex.index}" >
				<caption>Binary 檔資訊</caption>
				<colgroup>
					<col width="21%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> LCD Size(可重複選擇) </th>
						<td> <label for="lcd_1" id="label${listIndex.index}">
						<c:forEach items="${resultMap.lcdSizeChkBox}" var="lcdSize" varStatus="lcdListIndex">
							<input type="checkbox"" name="subContent.provisionItem" value="${lcdSize.dtlFullCd}" 
								<c:forEach items="${resultMap.provisionItemsList}" var="lcdSizeInfo" varStatus="privisionListIndex">	
									<c:if test="${lcdSizeInfo.scid == subContent.scid && lcdSizeInfo.itemCd == lcdSize.dtlFullCd}">checked="checked"</c:if>
								</c:forEach>
								onclick="javascript:selectLcdSize(this, '${listIndex.index}');"/>
								<label for="lcd_1">&nbsp;<gc:text code="${lcdSize.dtlFullCd}"/></label>&nbsp;&nbsp;
						</c:forEach>	
							</label>		
						</td>
					</tr>
					<tr id="binaryFileTr${listIndex.index}">
						<th scope="row" rowspan="2"><span>*</span> 上傳Binary 檔</th>
						<td>
							<span class="txtcolor04">${subContent.runFileNm}</span> &nbsp;
							<a href="javascript:removeFormRunFile('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="刪除" /></a>
						</td>
					</tr>
					<tr id="binaryInfoTr${listIndex.index}">
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
					<tr id="sprtPhoneListTr${listIndex.index}">
						<th class="tit01 tit03">選擇適用手機</th>
						<td class="bgnone">
							可以已上傳之Binary 檔為標準選擇手機.&nbsp;
							<a href="javascript:searchSprtPhone('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_dmsearch.gif'/>" alt="搜尋手機" /></a>
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
										<th scope="col" class="tit06 btnone"><input type="checkbox" id="allSelPhoneChkBox${listIndex.index}"  disabled="disabled"  checked="checked"  onclick="javascript:allSelectCheckBox(this, 'editForm${listIndex.index}', '${listIndex.index}');"/></th>
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
															<td><input type="checkbox" checked="checked" disabled="disabled"/></td>
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
					<tr>
						<td colspan="2" class="tit02">
							<a href="javascript:subContentSave('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_ok.gif'/>" alt="確定" /></a>
							<a href="javascript:devContsPageReset('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_cancle.gif'/>" alt="取消" /></a>
						</td>
					</tr>
				</tbody>
			</table>
			</form>
			</div>
			<div class="txtr mar_b5" name="divBtnArea" id="divBtnArea${listIndex.index}">
				<a href="javascript:appendFormDiv('editForm${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_add.gif'/>" name="appendSubContentBtn" alt="추가" /></a>
			</div>		
		</c:forEach></c:when><c:otherwise> 
		<div name="divSubContent" class="tstyleA mar_b5"></div>
		<div class="tstyleA mar_b22" id="divSubContent${resultMap.subContentsCnt}" name="divSubContent">
		<form id="editForm${resultMap.subContentsCnt}" name="editForm${resultMap.subContentsCnt}"  method="post" enctype="multipart/form-data">
		<input type="hidden" name="subContent.cid"	value="${content.cid }" />
		<table summary="Binary 檔資訊">
			<caption>Binary 檔資訊</caption>
			<colgroup>
				<col width="21%" />
				<col />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><span>*</span> LCD Size(選擇可重複)</th>
					<td>
						<label for="lcd_1" id="label${resultMap.subContentsCnt}"><gc:checkBoxs name="subContent.provisionItem" group="${CONST.PHONE_LCD_SIZE}" codeType="full" divide=" &nbsp;&nbsp; " split="&nbsp;"/></label>
					</td>		
				</tr>
				<tr>
					<th scope="row" class="tit01"><span>*</span> <label for="fileupload">上傳Binary 檔</label></th>
					<td class="bgnone">
						<div class="fileinputs" id="uploadDiv1bin0"  style="display:;" >
							<span><input type="file" class="inputFile" id="1bin0" name="subContent.runFile.runUpload"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempRunUpload${resultMap.subContentsCnt}','${CONST.FILEEXT_ANDROID_BIN}');" accept="${CONST.FILEEXT_ANDROID_BIN}" style="cursor: pointer;" onkeydown="this.blur();" /></span>
							<div class="fakefile">
								<input type="text" id="tempRunUpload${resultMap.subContentsCnt}" name="tempRunUpload"  value="" class="w410" />
								<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="瀏覽檔案" /></a>
								
								<a href="javascript:uploadRunFile('editForm${resultMap.subContentsCnt}', 'tempRunUpload${resultMap.subContentsCnt}', 'label${resultMap.subContentsCnt}', ${resultMap.subContentsCnt});"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_upload.gif'/>" alt="上傳" id="addBinaryBtn" /></a>
			
							</div>
						</div>
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		</div>
	</c:otherwise></c:choose>