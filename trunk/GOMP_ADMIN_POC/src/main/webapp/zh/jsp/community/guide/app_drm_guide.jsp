<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<script type="text/javascript" src="<c:url value="/js/twonly.js"/>"></script>
<script type="text/javascript">

</script>
<!-- Content Area S -->
<h2 class="hide">Contents Area</h2>
<div id="contents_area">
	<!-- Title Area S -->
	<div id="ctitle_area">
		<p class="history">Home &gt; 開發商支援中心 &gt; 開發支援指南 <strong>&gt;</strong> <span>Application DRM</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title09.gif' />" alt="Application DRM" /></h3>
	</div>
	<!-- //Title Area E -->
	
	<!-- CONTENT TABLE S-->
			<div id="contents">
				<p class="mar_b35"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_txt12.gif' />" alt="Whoopy 提供解決方案, 防止銷售人上傳之應用軟體被非法複製,以確保銷售人之權益." /></p>
				
				<h5 class="h52"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_stitle09.gif' />" alt=" 1 適用ARM之流程" /></h5> 
				<div class="ucbox">
					<p class="mar_b25"><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_img27.gif' />" alt="下載ARM Library &rarr; 產生應用
軟體帳號 &rarr; 適用 ARM Library &rarr; 下載Developer License &rarr; 下載Validation Tool(審核工具) &rarr; 自我審核 &rarr; 上傳應用軟體" /></p>
					<ol class="ucbult07">
						<li>
							<span class="txt_bold">1. 下載ARM Library</span><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;為適用於ARM之解決方案, 請下載所需Library.
							<a href="<c:url value="/fileSupport/bbsFileDown.omp">
										<c:param name="bnsType" value="common.path.share.misc"/>
										<c:param name="filePath" value="/Whoopy_ARMPlugin.zip" />
										<c:param name="fileName" value="Whoopy_ARMPlugin.zip" />
									</c:url>"><img class="vm" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/download.gif' />" alt="下載" /></a>
						</li>
						<li><span class="txt_bold">2. 產生應用軟體帳號</span>
							<ul>
								<li>新帳號 : 將於新產品上架時產生.</li>
								<li>已有應用軟體帳號 : 可於 [產品上架/管理] > [產品管理] 中確認.</li> <!-- 0516 수정 -->
							</ul>
						</li>
						<li><span class="txt_bold">3. 適用ARM Library</span><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;將ARM Library適用於應用軟體開發程序.</li>
						<li><span class="txt_bold">4. 獲取Developer License</span>
							<ul>
								<li>欲透過審核工具測試ARM啟動與否, 需獲得App類別之Developer License.</li>
								<li>Developer License 可於 [產品現狀] > [開發資訊] 中獲取, 只能使用於 [產品上架] > [測試機] 新註冊之手機.</li>  <!-- 0516 수정 -->
							</ul>
						</li>
						<li>
							<span class="txt_bold">5. 下再審核工具</span><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;請開發商下載自行測試ARM啟動與否之程序.
							<a href="<c:url value="/fileSupport/bbsFileDown.omp">
										<c:param name="bnsType" value="common.path.share.misc"/>
										<c:param name="filePath" value="/ARMValidationTool.zip" />
										<c:param name="fileName" value="ARMValidationTool.zip" />
									 </c:url>"><img class="vm" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/download.gif' />" alt="下載" /></a>
						</li>
						<li><span class="txt_bold">6. 自我審核</span><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;可利用審核工具進行自我審核.</li>
						<li><span class="txt_bold">7. 上傳應用軟體</span><br />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;請上傳以通過ARM自我審核之應用軟體.</li>
					</ol>
				</div>
				
			</div>
			<!-- //CONTENT TABLE E-->

</div>
<!-- //Content Area E -->