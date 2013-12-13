<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 

<title>전환 이력보기</title>

<script type="text/javascript">

	//Pop-Up Open
	function popup(code){
		var width = 480;
		var height = 250;
		
		$("#devmbrstatcd").val(code);
		
		openCenteredWindow("", width, height, "no", "rejectview");
		
		$("#devMember").attr("target", "rejectview");
		$("#devMember").attr("action", "popRejectView.omp");
		$("#devMember").submit();
	}
	
	function goPage(no) {
		$("#pageno").val(no);
		
		$("#devMember").attr("target", "_self");
		$("#devMember").attr("action", "popTransHistoryView.omp");
		$("#devMember").submit();
	}
	
</script>

<form name="devMember" id="devMember"  method="post">
<input type="hidden" id="pageno" name="devMember.page.no" value="1"/>
<input type="hidden" id="mbrno" name="devMember.mbrno" value="${devMember.mbrno}" />
<input type="hidden" id="id" name="devMember.mbrid" value="${devMember.mbrid}" />
<input type="hidden" id="devmbrstatcd" name="devMember.devmbrstatcd" value="" />
	<div id="popup_area_810">
		<h1>전환 이력보기</h1>
		<table class="pop_tabletype01">
			<colgroup>
				<col style="width:12%;"/>
				<col style="width:20%"/>
				<col style="width:12%;"/>
				<col style="width:20%"/>
				<col style="width:13%;"/>
				<col style="width:20%"/>
			</colgroup>
			<tbody>
			<tr>
				<th>아이디</th>
				<td><g:html value="${devMember.mbrid}"/></td>
				<th>회원분류</th>
				<td><gc:text code="${devMember.mbrclscd}"/></td>
				<th>유/무료 회원</th>
				<td><gc:text code="${devMember.mbrcatcd}"/></td>
			</tr>
			</tbody>
		</table>
		<table class="pop_tabletype02 mt25">
			<colgroup>
				<col style="width:10%;"/>
				<col />
				<col style="width:15%;"/>
				<col style="width:15%;"/>
				<col style="width:17%"/>
				<col style="width:10%;"/>
			</colgroup>
			<tbody>
			<tr>
				<th>번호</th>
				<th>전환신청내용</th>
				<th>전환신청일</th>
				<th>전환처리일</th>
				<th>전환상태</th>
				<th>관리</th>
			</tr>
			<c:forEach items="${list}" var="item">
				<tr>
					<td><g:html value="${devMember.page.totalCount - item.rnum + 1}"/></td>
					<td class="text_l">
						<c:forTokens items="${item.transinfo}" delims=":" var="code" varStatus="status">
							<c:if test="${(status.count eq 1) or (status.count eq 3)}">
								<gc:text group="US0001" code="${code}"/>
							</c:if>
							<c:if test="${(status.count eq 2) or (status.count eq 4)}">
								/<g:html format="L##"><gc:text group="US0002"  code="${code}"/></g:html>/
							</c:if>
							<c:if test="${status.count eq 2}">
								&nbsp;<g:html value="→"/>&nbsp;
							</c:if>
						</c:forTokens>
					</td>
					<td><g:html value="${item.regdts}" format="L####-##-##"/></td>
					<td><g:html value="${item.mbrstatregdt}" format="L####-##-##"/></td>
					<td><gc:text code="${item.devmbrstatcd}"/></td>
					<td>
						<c:if test="${(item.devmbrstatcd eq CONST.MEM_DEV_STATUS_TURN_REJECT) or (item.devmbrstatcd eq CONST.MEM_DEV_STATUS_ACCOUNT_DATA_APPR_REJECT)}">
							<a class="btn_s" href="javascript:popup('${item.devmbrstatcd}')"><span>거절사유보기</span></a>
						</c:if>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<!-- paging -->
		<div align="center">
			<g:pageIndex item="${list}"/>
		</div>
		<!-- //paging -->
		
	</div><!-- //contents -->
</form>