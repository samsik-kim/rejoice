<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="app" uri="/WEB-INF/tld/app.tld" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<script type="text/javascript">

	var funcDeviceList	= function()	{
		$( "#deviceForm" ).attr("action", "<c:url value="/device/serviceDeviceList.omp"/>");
		$( "#deviceForm" ).submit();
	};

	var makeNetworkCdStr = function() {
		var ids = "";
		$("input:checkbox[name=phoneInfo.networkCd]:checked").each(function () {
			ids += ";" + $(this).attr("value");
		});
		return ids.substring(1);
	};

	var funcProcessDevice = function() {

		if(showValidate('deviceForm', 'dialog', 'check input value.')) {

			var selectedNetworkCd =  makeNetworkCdStr();
			// alert(selectedNetworkCd);
			$("#selectedNetworkCd").val(selectedNetworkCd);

			if(confirm("<gm:string value='jsp.device.servicedevice.serviceDeviceView.msg.confirm_ins'/>")) {
				$.blockUI({ message: 'Please Wait.' });
				$("#deviceForm").submit();
			}
		}
		
	};

	var updatePhoneInfoDelYn = function( phoneModelCd ) {
		
		$("#selectedPhoneModelCd").val(phoneModelCd);
		
		if(confirm("<gm:string value='jsp.device.servicedevice.serviceDeviceView.msg.confirm_del'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$( "#updateDeviceForm" ).submit();
		}
	};

</script>

		<div id="location">
			Home &gt; 단말관리 &gt; 서비스단말관리 &gt; <strong>서비스단말리스트</strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">서비스단말리스트</h1>
		<p>단말기 관리를 합니다.</p>

		<s:form action="updatePhoneInfoDelYn" id="updateDeviceForm" theme="simple">
			<s:hidden id="phoneModelCd" name="phoneInfo.phoneModelCd" />
			<s:hidden id="selectedPhoneModelCd" name="selectedPhoneModelCd" />
			<s:hidden id="searchLcdSize" name="phoneInfo.searchLcdSize" value="%{phoneInfo.searchLcdSize}"/>
			<s:hidden id="searchSvcCd" name="phoneInfo.searchSvcCd" value="%{phoneInfo.searchSvcCd}"/>
			<s:hidden id="searchVmVer" name="phoneInfo.searchVmVer" value="%{phoneInfo.searchVmVer}"/>
			<s:hidden id="searchType" name="phoneInfo.searchType" value="%{phoneInfo.searchType}"/>
			<s:hidden id="searchValue" name="phoneInfo.searchValue" value="%{phoneInfo.searchValue}"/>
			<s:hidden id="pageNo" name="phoneInfo.page.no" value="%{phoneInfo.page.no}"/>
		</s:form>
		<s:form action="processPhoneInfo" id="deviceForm" theme="simple" enctype="multipart/form-data">
			<s:hidden id="srchFlag" name="srchFlag" value="TRUE"/>
			<s:hidden id="phoneModelCd" name="phoneInfo.phoneModelCd" />
			<s:hidden id="vmType" name="phoneInfo.vmType" value="%{phoneInfo.vmType}"/>
			<s:hidden id="selectedNetworkCd" name="phoneInfo.selectedNetworkCd" />
			<s:hidden id="selectedPhoneModelCd" name="selectedPhoneModelCd" />

			<s:hidden id="searchLcdSize" name="phoneInfo.searchLcdSize" value="%{phoneInfo.searchLcdSize}"/>
			<s:hidden id="searchSvcCd" name="phoneInfo.searchSvcCd" value="%{phoneInfo.searchSvcCd}"/>
			<s:hidden id="searchVmVer" name="phoneInfo.searchVmVer" value="%{phoneInfo.searchVmVer}"/>
			<s:hidden id="searchType" name="phoneInfo.searchType" value="%{phoneInfo.searchType}"/>
			<s:hidden id="searchValue" name="phoneInfo.searchValue" value="%{phoneInfo.searchValue}"/>
			<s:hidden id="pageNo" name="phoneInfo.page.no" value="%{phoneInfo.page.no}"/>

		<table class="tabletype01" id="serviceDeviceTable">
			<colgroup>
				<col style="width:20%;"><col  style="width:30%;">
				<col style="width:20%;"><col  style="width:30%;">
			</colgroup>
			<tr>
				<th>단말 코드</th>
				<td>${phoneInfo.phoneModelCd}</td>
				<th>OS TYPE</th>
				<td><gc:html group="PD0056" code="06"/></td>
			</tr>
			<tr>
				<th>모델명</th>
				<td class="align_td">					
					<input id="mgmtPhoneModelNm" maxlength=100 type="text" name="phoneInfo.mgmtPhoneModelNm" class="txt" style="width:200px;" value="${phoneInfo.mgmtPhoneModelNm}" 
						v:required m:required="<gm:string value="jsp.device.servicedevice.serviceDeviceView.msg.check_phonemodelnm"/>" />
				</td>
				<th>단말명(애칭)</th>
				<td class="align_td">					
					<input id="modelNm" type="text" maxlength=100 name="phoneInfo.modelNm" class="txt" style="width:200px;" value="${phoneInfo.modelNm}" 
						v:required m:required="<gm:string value="jsp.device.servicedevice.serviceDeviceView.msg.check_modelnm"/>" />
				</td>
			</tr>
			<tr>
				<th>GDCD</th>
				<td colspan="3" class="align_td">					
					<input id="gdcd" maxlength=50 type="text" name="phoneInfo.gdcd" class="txt" style="width:200px;" value="${phoneInfo.gdcd}" 
						v:required m:required="<gm:string value="jsp.device.servicedevice.serviceDeviceView.msg.check_gdcd"/>" />
				</td>
			</tr>
			<tr>
				<th>제조사</th>
				<td colspan="3" class="align_td">
					<gc:radioButtons name="phoneInfo.makeComp" group="PD0040" codeType="full" value="${phoneInfo.makeComp}">
					<g:param name="extra">
						v:mustcheck="1" m:mustcheck="<gm:string value="jsp.device.servicedevice.serviceDeviceView.msg.check_makecomp"/>" 
					</g:param>
					</gc:radioButtons> 
				</td>
			</tr>
			<tr>
				<th>LCD 크기</th>
				<td colspan="3" class="align_td">					
					<gc:radioButtons name="phoneInfo.lcdSize" group="PD0021" codeType="full" value="${phoneInfo.lcdSize}">
					<g:param name="extra">
						v:mustcheck="1" m:mustcheck="<gm:string value="jsp.device.servicedevice.serviceDeviceView.msg.check_lcdsize"/>" 
					</g:param>
					</gc:radioButtons> 
				</td>
			</tr>
			<tr>
				<th>지원 OS</th>
				<td colspan="3" class="align_td">					
					<gc:radioButtons name="phoneInfo.vmVer" group="PD0091" codeType="full" filter="support" value="${phoneInfo.vmVer}">
					<g:param name="extra">
						v:mustcheck="1" m:mustcheck="<gm:string value="jsp.device.servicedevice.serviceDeviceView.msg.check_vmver"/>" 
					</g:param>
					</gc:radioButtons> 
				</td>
			</tr>
			<tr>
				<th>서비스 구분</th>
				<td colspan="3" class="align_td">
					<gc:radioButtons name="phoneInfo.svcCd" group="US0052" codeType="full" value="${phoneInfo.svcCd}">
					<g:param name="extra">
						v:mustcheck="1" m:mustcheck="<gm:string value="jsp.device.servicedevice.serviceDeviceView.msg.check_dvccd"/>" 
					</g:param>
					</gc:radioButtons> 
				</td>
			</tr>
			<tr>
				<th>Network 구분</th>
				<td colspan="3" class="align_td">
					<gc:checkBoxs name="phoneInfo.networkCd" group="US0056" codeType="full" value="${phoneInfo.arrNetworkCd}"/> 
				</td>
			</tr>
			<tr>
				<th>리스트 이미지</th>
				<td colspan="3" class="align_td">					
					<input id="listImg" type="file" name="phoneInfo.listImg" class="btn_s" style="width:70%;" />
					<c:if test="${not empty phoneInfo.listImgPos}">
					<s:hidden id="listImgPos" name="phoneInfo.listImgPos" value="%{phoneInfo.listImgPos}"/>
					<img src="${CONF['omp.common.url.http-share.phone']}${phoneInfo.listImgPos}" width="37" height="38" 
						onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');"  />
					</c:if>
				</td>
			</tr>
			<tr>
				<th>상세 이미지</th>
				<td colspan="3" class="align_td">					
					<input id="dtlImg" type="file" name="phoneInfo.dtlImg" class="btn_s" style="width:70%;" />
					<c:if test="${not empty phoneInfo.dtlImgPos}">
					<s:hidden id="dtlImgPos" name="phoneInfo.dtlImgPos" value="%{phoneInfo.dtlImgPos}" />
					<img src="${CONF['omp.common.url.http-share.phone']}${phoneInfo.dtlImgPos}" width="37" height="38"
						onerror="fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/no_img/52.jpg"/>');"  />
					</c:if>
				</td>
			</tr>
			<tr>
				<th>사용 여부</th>
				<td colspan="3" class="align_td">					
					<select id="delYn" name="phoneInfo.delYn">
						<option value="Y" <c:if test="${phoneInfo.delYn eq 'Y' }"> selected </c:if>>미사용</option>
						<option value="N" <c:if test="${phoneInfo.delYn eq 'N' }"> selected </c:if>>사용</option>
					</select>
				</td>
			</tr>
		</table>
		</s:form>

		<!--
		<p class="btn_wrap text_r mt05">
			<a class="btn" href="#"><span>삭제</span></a>
			<a class="btn" href="#"><span>저장</span></a>
			<a class="btn" href="#"><span>목록</span></a>
		</p>
		-->
		<c:if test="${not empty phoneInfo.phoneModelCd}">
		<p class="fl mt25"><a class="btn" href="javascript:updatePhoneInfoDelYn('${phoneInfo.phoneModelCd}');"><span>삭제</span></a></p>
		<p class="btn_wrap text_r mt25"><a class="btn" id="btnSubmit" href="javascript:funcProcessDevice()"><span>저장</span></a>
			<a class="btn" href="javascript:funcDeviceList();"><span>목록</span></a></p>
		</c:if>
		<c:if test="${empty phoneInfo.phoneModelCd}">
		<p class="btn_wrap text_r mt25"><a class="btn" id="btnSubmit" href="javascript:funcProcessDevice()"><span>저장</span></a>
			<a class="btn" href="javascript:funcDeviceList();"><span>목록</span></a></p>
		</c:if>

</body>
</html>
