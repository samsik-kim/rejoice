<%@ page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>

<script type="text/javascript">
<!--

//-->
</script>

<c:if test="${resultMap.resultCode == true && resultMap.errorMessage == null}">
	<c:choose>
		<c:when test="${resultMap.uploadRunFileResult == 'fileSizeError'}">
			<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent07' />
		</c:when>
		<c:when test="${resultMap.uploadRunFileResult == 'fileExtentionError'}">
			<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent08' />
		</c:when>
		<c:otherwise>
		&nbsp;
			<form id="editForm${resultMap.subContentsCnt}" name="editForm${resultMap.subContentsCnt}"  method="post" >
			<input type="hidden" name="subContent.cid"	value="${resultMap.cid}" />
			<input type="hidden" name="subContent.scid"	value="${resultMap.scid}"  id = "scid${resultMap.subContentsCnt}"/>
			<input type="hidden" name="subContent.runFile.runFilePos"	value="${resultMap.tempRunFileInfo.runFile.runFilePos}" />
			<input type="hidden" name="subContent.runFile.runUploadFileName"	value="${resultMap.tempRunFileInfo.runFile.runUploadFileName}" />
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
							<c:forEach items="${resultMap.lcdSizeChkBox}" var="lcdSize" varStatus="listIndex">
								<input type="checkbox"" name="subContent.provisionItem" value="${lcdSize.dtlFullCd}" 
									<c:forEach items="${resultMap.paramProvisionItem}" var="lcdSizeInfo" varStatus="listIndex"> 
										<c:if test="${lcdSize.dtlFullCd == lcdSizeInfo}">checked="checked"</c:if>
									</c:forEach>
									onclick="javascript:selectLcdSize(this, 'editForm${resultMap.subContentsCnt}', '${resultMap.subContentsCnt}');"/>
									<label for="lcd_1">&nbsp;<gc:text code="${lcdSize.dtlFullCd}"/></label>&nbsp;&nbsp;
							</c:forEach>
						</td>
					</tr>
					<tr>
						<th scope="row" rowspan="2"><span>*</span> 바이너리 파일등록</th>
						<td>
							<span class="txtcolor04">${resultMap.tempRunFileInfo.runFile.runUploadFileName}</span> &nbsp;
							<a href="javascript:removeRunFile('editForm${resultMap.subContentsCnt}', '${resultMap.subContentsCnt}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_del02.gif'/>" alt="삭제" /></a>
						</td>
					</tr>
					<tr>
						<td class="lh160">
							minSdkVersion : ${resultMap.minSDKVersion}<br />
							targetSdKVersion : ${resultMap.targetSDKVersion}<br />
							maxSdkVersion : ${resultMap.maxSDKVersion}<br />
							versionCode : ${resultMap.versionCode}<br />
							versionName : ${resultMap.versionName}<br />
							package : ${resultMap.pkgNm}<br />
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
										<th scope="col" class="tit06 btnone"><input type="checkbox"  id="allSelPhoneChkBox${resultMap.subContentsCnt}" onclick="javascript:allSelectCheckBox(this, 'editForm${resultMap.subContentsCnt}', '${resultMap.subContentsCnt}');"/></th>
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
										<c:when test="${not empty resultMap.sprtPhoneList}">
											<c:forEach items="${resultMap.sprtPhoneList}" var="sprtPhone" varStatus="listIndex">	
											<tr>
												<td><input type="checkbox" name="sprtPhoneModel${resultMap.subContentsCnt}" value="${sprtPhone.phoneModelCd}"   onclick="javascript:alramSprtPhoneCnt(this, 'editForm${resultMap.subContentsCnt}');"/></td>
												<td>${sprtPhone.modelNm}</td>
												<td>
													<a href="javascript:gotoPhoneModelInfoList('${phone.mgmtPhoneModelNm}');">
													${sprtPhone.mgmtPhoneModelNm}
													</a>
												</td>
												<td>${sprtPhone.osVersion}</td>
												<td class="brnone">${sprtPhone.lcdSize}</td>
											</tr>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<tr><td colspan="5">&nbsp;</td></tr>
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
							<a href="javascript:subContentReset('editForm${resultMap.subContentsCnt}');"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_cancle.gif'/>" alt="취소" /></a>
						</td>
					</tr>
				</tbody>
			</table>
			</form>	
		</c:otherwise>	
	</c:choose>	
</c:if>

<c:if test="${resultMap.resultCode == false && resultMap.errorMessage != null}">
	<c:choose>
		<c:when test="${resultMap.errorMessage == 'SigningKeyError'}">
			<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent09' />
		</c:when>
		<c:when test="${resultMap.errorMessage == 'VersionCodeError'}">
			<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent10' />
		</c:when>
		<c:when test="${resultMap.errorMessage == 'SupportMaxOSError'}">
			<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent11' />
		</c:when>
		<c:when test="${resultMap.errorMessage == 'SupportMinOSError'}">
			<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent12' />
		</c:when>
		<c:when test="${resultMap.errorMessage == 'NoneSigningModeFalseError'}">
			<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent13' />
		</c:when>
		<c:when test="${resultMap.errorMessage == 'DebugModeTrue'}">
			<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent14' />
		</c:when>
		<c:when test="${resultMap.errorMessage == 'NoneVersionCode'}">
			<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent15' />
		</c:when>
		<c:when test="${resultMap.errorMessage == 'WrongMaxSDKVersion'}">
			<gm:string value='jsp.content.contentAndroidDevInfo.msg.subContent16' />
		</c:when>
		<c:otherwise>
			<gm:string value='jsp.content.common.msg.result.fail' />
		</c:otherwise>
	</c:choose>
</c:if>