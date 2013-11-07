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

	$(document).ready( function()	{
		
		$("#delYn").change( function() {
		    //alert($("#delYn").val());
		    if($("#delYn").val() == "Y") {
		    	if(!confirm("<gm:string value='jsp.device.servicedevice.serviceDeviceView.msg.confirm_mod'/>")) {
		    		$("#delYn").val( "N" );
		    	}
		    }
		});

	    $(":input:radio[name='phoneInfo\.svcCd']").change( function() {
		    //alert($(":input:radio[name='phoneInfo\.svcCd']:checked").val());
		    if($(":input:radio[name='phoneInfo\.svcCd']:checked").val() != "US005203") {
		    	if(!confirm("<gm:string value='jsp.device.servicedevice.serviceDeviceView.msg.confirm_mod'/>")) {
		    		$(":input:radio[name='phoneInfo\.svcCd']").filter('input:radio[value="US005203"]').attr("checked", "checked");
		    	}
		    }
	    });

	});

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
			首頁  &gt; 手機管理  &gt; 支援手機管理  &gt; <strong>支援手機目錄</strong> 
		</div><!-- //location -->

		<h1 class="fl pr10">支援手機目錄 </h1>
		<p>可對手機進行管理。</p>

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
				<th>手機管理代碼</th>
				<td>${phoneInfo.phoneModelCd}</td>
				<th>OS TYPE</th>
				<td><gc:html group="PD0056" code="06"/></td>
			</tr>
			<tr>
				<th>型號</th>
				<td class="align_td">					
					<input id="mgmtPhoneModelNm" maxlength=100 type="text" name="phoneInfo.mgmtPhoneModelNm" class="txt" style="width:200px;" value="${phoneInfo.mgmtPhoneModelNm}" 
						v:required m:required="<gm:string value="jsp.device.servicedevice.serviceDeviceView.msg.check_phonemodelnm"/>" />
				</td>
				<th>手機名稱(簡稱)</th>
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
				<th>廠商</th>
				<td colspan="3" class="align_td">
					<gc:radioButtons name="phoneInfo.makeComp" group="PD0040" codeType="full" value="${phoneInfo.makeComp}">
					<g:param name="extra">
						v:mustcheck="1" m:mustcheck="<gm:string value="jsp.device.servicedevice.serviceDeviceView.msg.check_makecomp"/>" 
					</g:param>
					</gc:radioButtons> 
				</td>
			</tr>
			<tr>
				<th>LCD解析度</th>
				<td colspan="3" class="align_td">					
					<gc:radioButtons name="phoneInfo.lcdSize" group="PD0021" codeType="full" value="${phoneInfo.lcdSize}">
					<g:param name="extra">
						v:mustcheck="1" m:mustcheck="<gm:string value="jsp.device.servicedevice.serviceDeviceView.msg.check_lcdsize"/>" 
					</g:param>
					</gc:radioButtons> 
				</td>
			</tr>
			<tr>
				<th>OS版本</th>
				<td colspan="3" class="align_td">					
					<gc:radioButtons name="phoneInfo.vmVer" group="PD0091" codeType="full" filter="support" value="${phoneInfo.vmVer}">
					<g:param name="extra">
						v:mustcheck="1" m:mustcheck="<gm:string value="jsp.device.servicedevice.serviceDeviceView.msg.check_vmver"/>" 
					</g:param>
					</gc:radioButtons> 
				</td>
			</tr>
			<tr>
				<th>服務類別</th>
				<td colspan="3" class="align_td">
					<gc:radioButtons name="phoneInfo.svcCd" group="US0052" codeType="full" value="${phoneInfo.svcCd}">
					<g:param name="extra">
						v:mustcheck="1" m:mustcheck="<gm:string value="jsp.device.servicedevice.serviceDeviceView.msg.check_dvccd"/>" 
					</g:param>
					</gc:radioButtons> 
				</td>
			</tr>
			<tr>
				<th>網路類別</th>
				<td colspan="3" class="align_td">
					<gc:checkBoxs name="phoneInfo.networkCd" group="US0056" codeType="full" value="${phoneInfo.arrNetworkCd}"/> 
				</td>
			</tr>
			<tr>
				<th>目錄圖示</th>
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
				<th>詳細圖片</th>
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
				<th>使用與否</th>
				<td colspan="3" class="align_td">					
					<select id="delYn" name="phoneInfo.delYn">
						<option value="Y" <c:if test="${phoneInfo.delYn eq 'Y' }"> selected </c:if>>未使用</option>
						<option value="N" <c:if test="${phoneInfo.delYn eq 'N' }"> selected </c:if>>使用</option>
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
		<p class="fl mt25"><a class="btn" href="javascript:updatePhoneInfoDelYn('${phoneInfo.phoneModelCd}');"><span>刪除</span></a></p>
		<p class="btn_wrap text_r mt25"><a class="btn" id="btnSubmit" href="javascript:funcProcessDevice()"><span>儲存</span></a>
			<a class="btn" href="javascript:funcDeviceList();"><span>目錄</span></a></p>
		</c:if>
		<c:if test="${empty phoneInfo.phoneModelCd}">
		<p class="btn_wrap text_r mt25"><a class="btn" id="btnSubmit" href="javascript:funcProcessDevice()"><span>儲存</span></a>
			<a class="btn" href="javascript:funcDeviceList();"><span>目錄</span></a></p>
		</c:if>

</body>
</html>
