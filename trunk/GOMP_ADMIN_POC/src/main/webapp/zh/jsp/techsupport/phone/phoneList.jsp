<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<script type="text/javascript">
$(document).ready(function(){ 
	if('${phoneInfo.searchType}' == "modelNm"){
		$("#searchList").hide();
        $("#searchInput").show();
	}else{
        $("#searchList").show();
		$("#searchInput").hide();
	}
	
	 $("#searchType").change( function() {
	     if ($(this).val() == "makeComp") {
	         $("#searchList").show();
	         $("#searchInput").hide();
	         
	         $("#searchText").val("");
	     } else {
	  	 	 $("#searchList").hide();
	         $("#searchInput").show();
	     }
	 });
	 
	 $("#searchBtn").click( function() {
		 $("#frm").attr("action", env.contextPath +  "/community/phoneInfoList.omp");
		$("#frm").submit();
	});
	
});
</script>

<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 開發商支援中心 <strong>&gt;</strong> <span>掌上設備資訊</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title04.gif' />" alt="掌上設備資訊" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
	<div id="contents">
		<form id="frm" name="frm" action="" method="post">
		<input type="hidden" name="phoneInfo.page.no" value="1"/>
		<div class="pmbox mar_b15">
  	            <div class="pad_bl40">
      	        	<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt02.gif' />" alt="手機搜尋" class="pad_tr6 fltl" />
          	        <div class="fltl pad_tr2">
					<select id="searchType" name="phoneInfo.searchType" class="w186">
					   <option value="makeComp" <c:if test="${phoneInfo.searchType eq 'makeComp' }"> selected </c:if> >廠商</option>
					   <option value="modelNm" <c:if test="${phoneInfo.searchType eq 'modelNm' }"> selected </c:if> >機型</option>
					</select>
				</div>

				<div class="fltl pad_tr2" id="searchList">
					<select id="searchSelect" name="phoneInfo.searchSelect" class="w250">
						<option value="">全部</option>
						<gc:options group="PD0040" codeType="full" value="${phoneInfo.searchSelect }" />
					</select>
				</div>
				<div class="fltl pad_tr2" id="searchInput">
					<input type="text" id="searchText" name="phoneInfo.searchText" value="<g:tagAttb value="${phoneInfo.searchText }"/>" style="width: 245px" />
				</div>					
              	    <input type="image" id="searchBtn" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_search1.gif' />" alt="搜尋" />
              	</div>
		</div>
		</form>
		
		<div class="sdinfo">
			<table summary="掌上設備資訊">
				<caption>掌上設備資訊</caption>
				<colgroup>
					<col width="14%" />
					<col />
				</colgroup>
				<tbody>
					<c:choose>
						<c:when test="${not empty resultList }">
							<c:forEach items="${resultList }" var="phoneInfo" varStatus="status">
								<tr>
									<td rowspan="2" class="tit01">
										<span class="imgbox">
										<c:choose>
											<c:when test="${not empty phoneInfo.listImgPos }">
												<img src="${CONF['omp.common.url.http-share.phone']}/${phoneInfo.listImgPos}" width="72" height="72" onerror="javascript:fixBroken(this, '<c:url value="/${ThreadSession.serviceLocale.language}/images/uc/no_images.gif"/>');" alt="no images" />
											</c:when>
											<c:otherwise>
												<img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/no_images.gif' />" alt="no images" />
											</c:otherwise>
										</c:choose>	
										</span>
									</td>
									<th>${phoneInfo.mgmtPhoneModelNm }(<gc:text code="${phoneInfo.vmVer }"/>)</th>
								</tr>
								<tr>
									<td>
										<ul class="sdinfo">
											<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt03.gif' />" alt="廠商" />&nbsp;&nbsp; <gc:text code="${phoneInfo.makeComp }"/> </li>
											<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt05.gif' />" alt="機名" />&nbsp;&nbsp; ${phoneInfo.modelNm } </li>
											<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt07.gif' />" alt="Network" />&nbsp;&nbsp;
												<c:choose>
													<c:when test="${fn:indexOf(phoneInfo.networkCd, ';') != -1 }">
														<c:set var="multiNtCd" value="${fn:split(phoneInfo.networkCd, ';')}" />
														<c:forEach items="${multiNtCd }" var="networkCd" varStatus="status">
															<gc:text code="${networkCd }" /> <c:if test="${fn:length(multiNtCd) != status.count }"> / </c:if>
														</c:forEach>			
													</c:when>
													<c:otherwise>
														<gc:text code="${phoneInfo.networkCd }" />
													</c:otherwise>
												</c:choose> 
											</li>
											<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt04.gif' />" alt="LCD SiZE" />&nbsp;&nbsp; <gc:text code="${phoneInfo.lcdSize }" /> </li>
											<li><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt06.gif' />" alt="OS TYPE" />&nbsp;&nbsp; <gc:text code="${phoneInfo.vmType }" /> </li>
										</ul>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise><div align="center"><gm:string value='jsp.content.verify.contentsVerifyList.msg.list_result'/></div></c:otherwise>
					</c:choose>
				</tbody>
			</table> 
		</div>   
		
	<!-- paging -->
		<div align="center">
			<g:pageIndex item="${resultList}"/>
		</div>
		<script type="text/javascript">
			function goPage(no) {
				$("form[name=frm] input[name=phoneInfo\\.page\\.no]").val(no);
				$("#frm").submit();
			}
		</script>
	<!-- //paging -->
	</div>
	<!-- //CONTENT TABLE E-->

</div><!-- //Content Area E -->