<%@page language="java" contentType="text/html; charset=UTF-8"%>   
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<head>
<script type="text/javascript">
	$(document).ready( function() {
		$("#cancelBtn").click( function() {
			if (confirm("<gm:string value='jsp.content.contentBaseInfo.btn.verifyCancel'/>")) {
				location.href = env.contextPath + "/content/contentVerifyCancel.omp?ctVerify.cid=${contentVerifyDetail.cid }&ctVerify.verifyReqVer=${contentVerifyDetail.verifyReqVer }";	
			}
		});
	});
</script>
</head>

<div class="process mar_b22">
                  <ul>
                  	
                  	<c:if test="${contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_REQ}">
	                  	<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step1_on.gif' />" alt="待審" /></li>
	                    <li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step2.gif' />" alt="審核中" /></li>
	                    <li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step3.gif' />" alt="審核完畢" /></li>
                    </c:if>
                    
                    <c:if test="${contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_ING || contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_END || contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_REJECT}">
	                  	<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step1.gif' />" alt="待審" /></li>
	                  	<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step2_on.gif' />" alt="審核中 " />
	                  		<span>
	                  			<c:if test="${0 > contentVerifyDetail.ctEndExDt}"><div align="center"> D${contentVerifyDetail.ctEndExDt } 日</div></c:if>
								<c:if test="${0 < contentVerifyDetail.ctEndExDt}"><div align="center"> D+${contentVerifyDetail.ctEndExDt } 日</div></c:if>
								<c:if test="${0 == contentVerifyDetail.ctEndExDt}"><div align="center"> D-${contentVerifyDetail.ctEndExDt } 日</div></c:if>
	                  		</span></li>
	                    <li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step3.gif' />" alt="審核完畢" /></li>
                    </c:if>
                    
                    <c:if test="${contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_END}">
	                  	<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step1_1.gif' />" alt="待審" /></li>
	                    <li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step2_1.gif' />" alt="審核中" /></li>
	                    <li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step3_on.gif' />" alt="審核完畢" /></li>
                    </c:if>
                    
                    <c:if test="${contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_CANCEL}">
                    	<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step4_s_on.gif' />" alt="取消審核" /></li>
                        <li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step1_s.gif' />" alt="待審" /></li>
                        <li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step2_s.gif' />" alt="審核中" /></li>
                        <li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/step3_s.gif' />" alt="審核完畢" /></li>
                    </c:if>
                    
                  </ul>

              </div>
		<h4 class="h41"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/pm_h4_14.gif' />" alt="審核資訊" /></h4>
		<div class="tstyleC mar_b25">
			<table summary="審核資訊">
				<caption>審核資訊</caption>
				<colgroup>
					<col width="25%" />
					<col width="25%" />

					<col width="25%" />
					<col width="25%" />
				</colgroup>	
				<thead>
					<tr>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit39.gif' />" alt="請審日期" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit40.gif' />" alt="審核完畢(預期)日期" /></th>
						<th scope="col" class="btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit41.gif' />" alt="審核狀態" /></th>
						<th scope="col" class="btnone brnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit42.gif' />" alt="審核結果" /></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td class="bbnone"><g:text value="${contentVerifyDetail.ctInsDttm }" format="L####-##-##" /></td>
                        <td class="bbnone">
	                        <c:if test="${contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_ING || contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_END || contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_TEST_REJECT}">
	                        	<c:if test="${0 > contentVerifyDetail.ctEndExDt}"><div align="center"> D${contentVerifyDetail.ctEndExDt } 日</c:if>
								<c:if test="${0 < contentVerifyDetail.ctEndExDt}"><div align="center"> D+${contentVerifyDetail.ctEndExDt } 日</c:if>
								<c:if test="${0 == contentVerifyDetail.ctEndExDt}"><div align="center"> D-${contentVerifyDetail.ctEndExDt } 日</c:if>
	                        	(<g:text value="${contentVerifyDetail.ctEndDt }" format="L####-##-##" />)</div>
	                        </c:if>
	                        <c:if test="${contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_END}">
	                        	<g:text value="${contentVerifyDetail.ctEndDt }" format="L####-##-##" />
	                        </c:if>
	                        <c:if test="${!contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_END || !contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_ING }"> - </c:if>
                        </td>
                        <td class="bbnone">
                        	<span class="txtcolor15">
                        		<c:choose>
									<c:when test="${CONST.CODE_VERIFY_TEST_REJECT eq  contentVerifyDetail.verifyPrgrYn || CONST.CODE_VERIFY_TEST_END eq contentVerifyDetail.verifyPrgrYn}">
										<gc:text code="${CONST.CODE_VERIFY_ING }" />
									</c:when>
									<c:otherwise>
										<gc:text code="${contentVerifyDetail.verifyPrgrYn }" />											
									</c:otherwise> 
								</c:choose>
                        	</span>
                        	<c:if test="${contentVerifyDetail.verifyPrgrYn eq CONST.CODE_VERIFY_REQ}">
                        		<img id="cancelBtn" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_cancel02.gif' />" style="cursor: pointer;" alt="取消審核" />
                        	</c:if>
                        	<c:if test="${CONST.CODE_VERIFY_CANCEL eq contentVerifyDetail.verifyPrgrYn }">
                        		<g:text value="${contentVerifyDetail.updDttm }" format="L####-##-##" />
								<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/btn_state.gif' />" alt="" style="cursor: pointer;" onclick="javascript:gotoContentView('${contentVerifyDetail.cid }')"/>
							</c:if>
                        </td>
                        <td class="bbnone brnone">
                        	<c:choose>
                        		<c:when test="${CONST.CODE_VERIFY_ING == contentVerifyDetail.verifyPrgrYn || CONST.CODE_VERIFY_TEST_REJECT == contentVerifyDetail.verifyPrgrYn || 
                        						CONST.CODE_VERIFY_TEST_END == contentVerifyDetail.verifyPrgrYn || CONST.CODE_VERIFY_REQ == contentVerifyDetail.verifyPrgrYn ||
                        						CONST.CODE_VERIFY_CANCEL == contentVerifyDetail.verifyPrgrYn}">
                        			-
                        		</c:when>
                        		<c:otherwise>
                        			<c:if test="${CONST.AGREEMENT_STATUS_AGREE == contentVerifyDetail.agrmntStat}">
										<gc:text code="${contentVerifyDetail.agrmntStat }" />
                        			</c:if>
                        			<c:if  test="${CONST.AGREEMENT_STATUS_REJECT == contentVerifyDetail.agrmntStat }"> 
                        				<span class="txtcolor03"> <gc:text code="${contentVerifyDetail.agrmntStat }" /> </span>  
                        			</c:if>
                        		</c:otherwise>
                        	</c:choose>
                        </td>
					</tr>
				</tbody>
			</table>
		</div>
		<c:forEach items="${subContentsVerifyList }" var="subContent">
		<h4 class="h44">${subContent.runFileNm }</h4>
			<div class="tstyleC">
	
				<table summary="Binary 檔資訊">
					<caption>Binary 檔資訊</caption>
					<colgroup>
						<col width="18%" />
						<col />
					</colgroup>	
					<tbody>
						<tr>
							<th class="tit05 btnone"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit31.gif' />" alt="Binary 檔資訊" /></th>
	
							<td class="btnone brnone lh160 tit04">
								minSdkVersion : ${subContent.vmVerMin }<br />
								targetSdkVersion : ${subContent.vmVerTarget }<br />
								maxSdkVersion : ${subContent.vmVerMax }<br />
								versionCode : ${subContent.versionCode }<br />
								versionName : ${subContent.versionName }<br />
								Package : ${subContent.pkgNm }<br/>
								LCDsize : <c:forEach items="${subContent.itemCdList }" var="lcdSize"><gc:text code="${lcdSize.itemCd }" />&nbsp; </c:forEach>
							</td>
						</tr>
						<tr>
							<th class="tit05"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit32.gif' />" alt="App. 審核結果報告" /></th>
							<td class="brnone tit04">
								<span class="uline"> 
									<a href="<c:url value="/fileSupport/fileDown.omp">
										<c:param name="bnsType" value="common.path.share.product"/>
										<c:param name="filePath" value="${subContent.appCtResultFile }"/>
										<c:param name="fileName" value="${subContent.appCtResultFileNm }"/>
										</c:url>"> ${subContent.appCtResultFileNm } </a>   
								</span>
							</td>
						</tr>
						<tr>
							<th class="tit05"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit33.gif' />" alt="其他附加檔案" /></th>
							<td class="brnone tit04">
								<c:choose>
									<c:when test="${not empty addFile }">
										<span class="uline">
											<c:forEach items="${addFile }" var="aFile">
												<c:forEach items="${aFile }" var="file">
													<c:if test="${file.scid == subContent.scid }">
														<a href="<c:url value="/fileSupport/fileDown.omp">
														<c:param name="bnsType" value="common.path.share.product"/>
														<c:param name="filePath" value="${file.addFile }"/>
														<c:param name="fileName" value="${file.addFileNm }"/>
														</c:url>"> ${file.addFileNm } </a> <br/>
													</c:if>
												</c:forEach>  
											</c:forEach>
										</span>
									</c:when>
									<c:otherwise> - </c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<th class="tit05"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/pm/th_tit34.gif' />" alt="審核結果詳細內容" /></th>
							<td class="brnone tit04"> 
								<a href="#"> ${subContent.appCtCmt } </a>
							</td>
						</tr>
					</tbody>
				</table>
	
			</div> <br/>
		</c:forEach>