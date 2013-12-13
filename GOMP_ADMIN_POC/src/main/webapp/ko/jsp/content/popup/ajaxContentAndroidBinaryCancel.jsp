<%@ page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

    <form id="editForm${resultMap.subContentsCnt}" name="editForm${resultMap.subContentsCnt}"  method="post">
		<input type="hidden" name="subContent.cid"	value="${resultMap.cid}" />
		<input type="hidden" name="subContent.scid"	value="${resultMap.subContent.scid}"  id = "scid${resultMap.subContentsCnt}"/>
		<input type="hidden" name="subContent.runFile.runFilePos"	value="${resultMap.subContent.runFilePos}" />
		<input type="hidden" name="subContent.runFile.runUploadFileName"	value="${resultMap.subContent.runFileNm}" />
		<input type="hidden" name="subContent.sprtPhoneModel" value="" />
		<input type="hidden" name="subContent.delYn" value=""  id = "delYn${resultMap.subContentsCnt}"/>
		<input type="hidden" id = "modifySubContentIndex${resultMap.subContentsCnt}" name="modifySubContentIndex"	value="${modifySubContentIndex}" />
	
	<table summary="바이너리 파일정보 입력" id="binaryInfoTable${resultMap.subContentsCnt}" >
		<caption>바이너리 파일정보 입력</caption>
		<colgroup>
			<col width="21%" />
			<col />
		</colgroup>
		<tbody>
			<tr>
				<th scope="row"><span>*</span> LCD Size(복수선택가능)</th>
				<td> <label for="lcd_1" id="label${resultMap.subContentsCnt}">
				<c:forEach items="${resultMap.lcdSizeChkBox}" var="lcdSize" varStatus="lcdListIndex">
					<input type="checkbox"" name="subContent.provisionItem" value="${lcdSize.dtlFullCd}" 
						<c:forEach items="${resultMap.provisionItemsList}" var="lcdSizeInfo" varStatus="privisionListIndex">	
							<c:if test="${lcdSizeInfo.scid == resultMap.subContent.scid && lcdSizeInfo.itemCd == lcdSize.dtlFullCd}">checked="checked"</c:if>
						</c:forEach>
						onclick="javascript:selectLcdSize(this, '${resultMap.subContentsCnt}');"/>
						<label for="lcd_1">&nbsp;<gc:text code="${lcdSize.dtlFullCd}"/></label>&nbsp;&nbsp;
				</c:forEach>	
					</label>		
				</td>
			</tr>
			<tr id="binaryFileTr${resultMap.subContentsCnt}">
				<th scope="row" rowspan="2"><span>*</span> 바이너리 파일등록</th>
				<td>
					<span class="txtcolor04">${resultMap.subContent.runFileNm}</span> &nbsp;
					<a href="javascript:removeFormRunFile('editForm${resultMap.subContentsCnt}', '${resultMap.subContentsCnt}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="삭제" /></a>
				</td>
			</tr>
			<tr id="binaryInfoTr${resultMap.subContentsCnt}">
				<td class="lh160">
					minSdkVersion : ${resultMap.subContent.minSDKVersion} (${resultMap.subContent.vmVerMin})<br />
					targetSdkVersion : ${resultMap.subContent.targetSDKVersion}
					<c:if test="${not empty resultMap.subContent.vmVerTarget && resultMap.subContent.vmVerTarget ne 'N/A'}">(${resultMap.subContent.vmVerTarget})</c:if><br />
					maxSdkVersion : ${resultMap.subContent.maxSDKVersion}
					<c:if test="${not empty resultMap.subContent.vmVerMax}">(${resultMap.subContent.vmVerMax})</c:if><br />
					versionCode : ${resultMap.subContent.versionCode}<br />
					versionName : ${resultMap.subContent.versionName}<br />
					package : ${resultMap.subContent.pkgNm}<br />
				</td>
			</tr>
			<tr id="sprtPhoneListTr${resultMap.subContentsCnt}">
				<th class="tit01 tit03">적용단말 선택</th>
				<td class="bgnone">
					등록된 바이너리 파일 정보를 기준으로 서비스대상 단말을 선택할 수 있습니다.&nbsp;
					<a href="javascript:searchSprtPhone('editForm${resultMap.subContentsCnt}', '${resultMap.subContentsCnt}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_dmsearch.gif'/>" alt="단말검색" /></a>
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
								<th scope="col" class="tit06 btnone"><input type="checkbox" id="allSelPhoneChkBox${resultMap.subContentsCnt}"  disabled="disabled"  checked="checked"  onclick="javascript:allSelectCheckBox(this, 'editForm${resultMap.subContentsCnt}', '${resultMap.subContentsCnt}');"/></th>
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
											<c:when  test="${resultMap.subContent.scid == phone.scid}">
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
					<a href="javascript:subContentSave('editForm${resultMap.subContentsCnt}', '${resultMap.subContentsCnt}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_ok.gif'/>" alt="확인" /></a>
					<a href="javascript:devContsPageReset('editForm${resultMap.subContentsCnt}', '${resultMap.subContentsCnt}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_cancle.gif'/>" alt="취소" /></a>
				</td>
			</tr>
		</tbody>
	</table>
	</form>

	