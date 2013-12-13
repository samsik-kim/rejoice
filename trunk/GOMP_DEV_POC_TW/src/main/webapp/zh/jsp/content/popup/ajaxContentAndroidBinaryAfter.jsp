<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %> 

<script type="text/javascript">
<!--
	$(document).ready(function() {
		// 바이너리가 없을 때 검증요청 버튼 지움
		if(parseInt('${resultMap.subContentsCnt}') <= 0) {
			$('#verifyReqBtn').remove();
		}
	});
	

//-->
</script>

<c:choose>
	<c:when test="${resultMap.subContentsCnt > 0}">
		<c:forEach items="${resultMap.subContsList}" var="subContent" varStatus="listIndex">	
			
			<div class="txtr mar_b5" name="divBtnArea" id="divBtnArea${listIndex.index}">
				<a href="javascript:appendFormDiv('editForm${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_add.gif'/>" name="appendSubContentBtn" alt="추가" /></a>
			</div>
			
			<div class="tstyleA mar_b22" id="divSubContent${listIndex.index}" name="divSubContent">
			<form id="editForm${listIndex.index}" name="editForm${listIndex.index}"  method="post">
				<input type="hidden" name="subContent.cid"	value="${resultMap.cid}" />
				<input type="hidden" name="subContent.scid"	value="${subContent.scid}"  id = "scid${listIndex.index}"/>
				<input type="hidden" name="subContent.runFile.runFilePos"	value="${subContent.runFilePos}" />
				<input type="hidden" name="subContent.runFile.runUploadFileName"	value="${subContent.runFileNm}" />
				<input type="hidden" name="subContent.sprtPhoneModel" value="" />
			
			<table summary="바이너리 파일정보 입력">
				<caption>바이너리 파일정보 입력</caption>
				<colgroup>
					<col width="21%" />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row"><span>*</span> LCD Size(복수선택가능)</th>
						<td>
						<c:forEach items="${resultMap.lcdSizeChkBox}" var="lcdSize" varStatus="lcdListIndex">
							<input type="checkbox"" name="subContent.provisionItem" value="${lcdSize.dtlFullCd}" 
								<c:forEach items="${resultMap.provisionItemsList}" var="lcdSizeInfo" varStatus="privisionListIndex">	
									<c:if test="${lcdSizeInfo.scid == subContent.scid && lcdSizeInfo.itemCd == lcdSize.dtlFullCd}">checked="checked"</c:if>
								</c:forEach>
								onclick="javascript:selectLcdSize(this, 'editForm${listIndex.index}', '${listIndex.index}');"/>
								<label for="lcd_1">&nbsp;<gc:text code="${lcdSize.dtlFullCd}"/></label>&nbsp;&nbsp;
						</c:forEach>		
						</td>
					</tr>
					<tr>
						<th scope="row" rowspan="2"><span>*</span> 바이너리 파일등록</th>
						<td>
							<span class="txtcolor04">${subContent.runFileNm}</span> &nbsp;
							<a href="javascript:removeRunFile('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="삭제" /></a>
						</td>
					</tr>
					<tr>
						<td class="lh160">
							minSdkVersion : ${subContent.vmVerMin}<br />
							targetSdKVersion : ${subContent.vmVerTarget}<br />
							maxSdkVersion : ${subContent.vmVerMax}<br />
							versionCode : ${subContent.versionCode}<br />
							versionName : ${subContent.versionName}<br />
							package : ${subContent.pkgNm}<br />
						</td>
					</tr>
					<tr id="sprtPhoneListTr${listIndex.index}">
						<th class="tit01 tit03">적용단말 선택</th>
						<td class="bgnone">
							등록된 바이너리 파일 정보를 기준으로 서비스대상 단말을 선택할 수 있습니다.&nbsp;
							<a href="javascript:searchSprtPhone('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_dmsearch.gif'/>" alt="단말검색" /></a>
							<div class="tstyleC mar_t10">
								<table summary="단말명칭,모델명,지원OS,LCD Size" class="w577 bbnone">
									<caption>단말명칭,모델명,지원OS,LCD Size</caption>
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
										<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit21.gif'/>" alt="단말명칭" /></th>
										<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit22.gif'/>" alt="모델명" /></th>
										<th scope="col" class="tit06 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit23.gif'/>" alt="지원OS" /></th>
										<th scope="col" class="tit06 btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit24.gif'/>" alt="LCD Size" /></th>
									</tr>
									</thead>
								</table>
							</div>
							<div class="tstyleC hl185" style="overflow-x:hidden;">
								<table summary="적용단말 선택" class="w577">
									<caption>적용단말 선택</caption>
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
							<a href="javascript:subContentSave('editForm${listIndex.index}', '${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_ok.gif'/>" alt="확인" /></a>
							<a href="javascript:subContentReset('editForm${listIndex.index}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_cancle.gif'/>" alt="취소" /></a>
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
		
		<table summary="바이너리 파일정보 입력">
			<caption>바이너리 파일정보 입력</caption>
			<colgroup>
				<col width="21%" />
				<col />
			</colgroup>
			<tbody>
				<tr>
					<th scope="row"><span>*</span> LCD Size(복수선택가능)</th>
					<td>
						<label for="lcd_1" id="label${resultMap.subContentsCnt}"><gc:checkBoxs name="subContent.provisionItem" group="${CONST.PHONE_LCD_SIZE}" codeType="full" divide=" &nbsp;&nbsp; " split="&nbsp;"/></label>
					</td>		
				</tr>
				<tr>
					<th scope="row" class="tit01"><span>*</span> <label for="fileupload">바이너리 파일등록</label></th>
					<td class="bgnone">
						<div class="fileinputs" id="uploadDiv1bin0"  style="display:;" >
							<span><input type="file" class="inputFile" id="1bin0" name="subContent.runFile.runUpload"  maxlength="1" onchange="javascript:setUploadFileNameCheck(this,'tempRunUpload${resultMap.subContentsCnt}','${CONST.FILEEXT_ANDROID_BIN}');" accept="${CONST.FILEEXT_ANDROID_BIN}" style="cursor: pointer;" onkeydown="this.blur();" /></span>
							<div class="fakefile">
								<input type="text" id="tempRunUpload${resultMap.subContentsCnt}" name="tempRunUpload"  value="" class="w410" />
								<a href="#"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_attch.gif'/>" alt="첨부파일" /></a>
								
								<a href="javascript:uploadRunFile('editForm${resultMap.subContentsCnt}', 'tempRunUpload${resultMap.subContentsCnt}', 'label${resultMap.subContentsCnt}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_upload.gif'/>" alt="업로드" id="addBinaryBtn" /></a>
			
							</div>
						</div>
						
					</td>
				</tr>
			</tbody>
		</table>
		</form>
		</div>
	</c:otherwise>
</c:choose>