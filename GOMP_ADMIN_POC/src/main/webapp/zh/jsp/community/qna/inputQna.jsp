<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<script type="text/javascript" src="<c:url value="/js/twonly.js"/>"></script>
<script type="text/javascript">

$(function() {
	
	//btnOk
		$("#btnOk").click(function() {
	
			if(showValidate('addQna', 'default','<gm:string value="jsp.common.msg.title01"/>')){
				if(confirm("<gm:string value='jsp.community.qna.inputQna.msg.inputConfirm'/>")) {
					$.ajax("<c:url value="/community/ajaxAddQna.omp"/>",
							{
								  async    : true
								, type	  : "POST"  
								, cache    : false
								, data     : $("#addQna").serialize()
								, dataType : "json"
								, success  : function(json) {
									if(json.result == "success"){
										alert("<gm:string value='jsp.community.qna.inputQna.msg.inputSucc'/>");
										location.href = "<c:url value='/community/newQna.omp'/>";
										
										return false;
									}
								}
								, error : function() {
									alert("<gm:string value='jsp.community.qna.inputQna.msg.reqFail'/>");
									return false;
								}
							}
					);
				}
		}else{
			return false;
		}
	});	
});

</script>
<form action="addQna" id="addQna" name="addQna">
<div id="contents_area">	  
	<div id="ctitle_area"> 
		<p class="history">Home &gt; 開發商支援中心 &gt; 開發支援詢問 <strong>&gt;</strong> <span>客戶諮詢</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title03.gif' />" alt="客戶諮詢" /></h3>
	</div>
	<div id="contents">
		<div class="tstyleA mar_b22">
			<table summary="客戶諮詢">
				<caption>客戶諮詢</caption>
				<colgroup>
					<col />
					<col />
					<col />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row" width="200"><label for="qnaname">姓名/帳號</label></th>
						<td width="190">
						<c:choose>
							<c:when test="${not empty MEMBER_SESSION.mbrId }">
								<input type="hidden" name="qna.queId" value="${MEMBER_SESSION.mbrId }">
								<g:html value="${MEMBER_SESSION.mbrId }"/>	
							</c:when>
							<c:otherwise>
								<input type="text" id="queNm" name="qna.queNm" v:required m:required="<gm:tagAttb value='jsp.community.qna.inputQna.msg.name01'/>" v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.community.qna.inputQna.msg.name02'/>" class="w150" />
							</c:otherwise>
						</c:choose>
						</td>
						<th scope="row" width="160"><label for="qnacata">問題類別</label></th>
						<td width="187">
							<select  id="qnaCd"  name="qna.qnaClsCd"  class="w186" title="問題類別">
								<c:forEach items="${ctgrList}" var="list">
									<option value="${list.ctgrCd}"><g:html value="${list.ctgrNm}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="qnamail">電子郵件</label></th>
						<td colspan="3">
							<c:choose>
								<c:when test="${not empty MEMBER_SESSION.emailAddr }">
									<input type="hidden" name="qna.emailAddr" value="${MEMBER_SESSION.emailAddr}">
									<a href="mailto:<g:html value='${MEMBER_SESSION.emailAddr}'/>"><span class="txtcolor04"><g:html value="${MEMBER_SESSION.emailAddr}"/></span></a>
								</c:when>
								<c:otherwise>
									<input type="text" id="emailAddr" name="qna.emailAddr" class="w576" v:text8_limit="40" m:text8_limit="<gm:tagAttb value='jsp.community.qna.inputQna.msg.email02'/>" v:required m:required="<gm:tagAttb value='jsp.community.qna.inputQna.msg.email01'/>" v:email m:email="<gm:tagAttb value='jsp.community.qna.inputQna.msg.email03'/>"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="subject">標題</label></th>
						<td colspan="3"><input type="text" id="queTitle" name="qna.queTitle" v:required m:required="<gm:tagAttb value='jsp.community.qna.inputQna.msg.title01'/>" class="w576" v:text8_limit="90" m:text8_limit="<gm:tagAttb value='jsp.community.qna.inputQna.msg.title02'/>"/></td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="qnacontent">內容</label></th>
						<td class="bgnone" colspan="3">
							<textarea id="queDscr" name="qna.queDscr" v:required m:required="<gm:tagAttb value='jsp.community.qna.inputQna.msg.dscr01'/>" v:text8_limit="3999" m:text8_limit="<gm:tagAttb value='jsp.community.qna.inputQna.msg.dscr02'/>" cols="1" rows="1" class="w576"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="btnarea mar_t30">
              	<img id="btnOk" alt="登載" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_save.gif' />" style="cursor: pointer;"/>
                <a href="<c:url value="/main/main.omp"/>"><img id="btnReset" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif' />" alt="cancel"/></a>
		</div>

	</div>
</div>
</form>

