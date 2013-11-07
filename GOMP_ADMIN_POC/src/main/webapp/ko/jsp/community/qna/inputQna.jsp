<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<script type="text/javascript" src="<c:url value="/js/twonly.js"/>"></script>
<script type="text/javascript">

$(function() {
	
	//btnOk
		$("#btnOk").click(function() {
	
			if(showValidate('addQna', 'default','<gm:string value="입력오류"/>')){
				if(confirm("<gm:string value='등록하시겠습니까?'/>")) {
					$.ajax("<c:url value="/community/ajaxAddQna.omp"/>",
							{
								  async    : true
								, type	  : "POST"  
								, cache    : false
								, data     : $("#addQna").serialize()
								, dataType : "json"
								, success  : function(json) {
									if(json.result == "success"){
										alert("<gm:string value='등록이 완료 되었습니다.
답변은 메일로 발송됩니다.'/>");
										location.href = "<c:url value='/community/newQna.omp'/>";
										
										return false;
									}
								}
								, error : function() {
									alert("<gm:string value='요청 처리가 실패했습니다. 재 시도해 주시기 바랍니다.'/>");
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
		<p class="history">Home &gt; 개발지원센터 &gt; 개발지원문의 <strong>&gt;</strong> <span>Q&amp;A</span></p>
		<h3><img src="<c:url value='/${ThreadSession.serviceLocale.language}/images/uc/uc_title03.gif' />" alt="Q&amp;A" /></h3>
	</div>
	<div id="contents">
		<div class="tstyleA mar_b22">
			<table summary="Q&amp;A 내용 작성">
				<caption>Q&amp;A 내용 작성</caption>
				<colgroup>
					<col />
					<col />
					<col />
					<col />
				</colgroup>
				<tbody>
					<tr>
						<th scope="row" width="200"><label for="qnaname">이름/아이디</label></th>
						<td width="190">
						<c:choose>
							<c:when test="${not empty MEMBER_SESSION.mbrId }">
								<input type="hidden" name="qna.queId" value="${MEMBER_SESSION.mbrId }">
								<g:html value="${MEMBER_SESSION.mbrId }"/>	
							</c:when>
							<c:otherwise>
								<input type="text" id="queNm" name="qna.queNm" v:required m:required="<gm:tagAttb value='이름을 입력해주세요.'/>" v:text8_limit="50" m:text8_limit="<gm:tagAttb value='이름은 50byte 까지만 입력 할 수 있습니다.'/>" class="w150" />
							</c:otherwise>
						</c:choose>
						</td>
						<th scope="row" width="160"><label for="qnacata">질문유형</label></th>
						<td width="187">
							<select  id="qnaCd"  name="qna.qnaClsCd"  class="w186" title="질문유형 선택">
								<c:forEach items="${ctgrList}" var="list">
									<option value="${list.ctgrCd}"><g:html value="${list.ctgrNm}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="qnamail">이메일 주소</label></th>
						<td colspan="3">
							<c:choose>
								<c:when test="${not empty MEMBER_SESSION.emailAddr }">
									<input type="hidden" name="qna.emailAddr" value="${MEMBER_SESSION.emailAddr}">
									<a href="mailto:<g:html value='${MEMBER_SESSION.emailAddr}'/>"><span class="txtcolor04"><g:html value="${MEMBER_SESSION.emailAddr}"/></span></a>
								</c:when>
								<c:otherwise>
									<input type="text" id="emailAddr" name="qna.emailAddr" class="w576" v:text8_limit="40" m:text8_limit="<gm:tagAttb value='이메일 주소는 40byte 까지만 입력 할 수 있습니다.'/>" v:required m:required="<gm:tagAttb value='이메일을 입력해주세요.'/>" v:email m:email="<gm:tagAttb value='올바른 이메일 형식이 아닙니다.'/>"/>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th scope="row"><label for="subject">제목</label></th>
						<td colspan="3"><input type="text" id="queTitle" name="qna.queTitle" v:required m:required="<gm:tagAttb value='제목을 입력해주세요.'/>" class="w576" v:text8_limit="90" m:text8_limit="<gm:tagAttb value='제목은 90byte 까지만 입력 할 수 있습니다.'/>"/></td>
					</tr>
					<tr>
						<th scope="row" class="tit01"><label for="qnacontent">내용</label></th>
						<td class="bgnone" colspan="3">
							<textarea id="queDscr" name="qna.queDscr" v:required m:required="<gm:tagAttb value='내용을 입력해주세요.'/>" v:text8_limit="3999" m:text8_limit="<gm:tagAttb value='내용은 3999byte 까지만 입력 할 수 있습니다.'/>" cols="1" rows="1" class="w576"></textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		
		<div class="btnarea mar_t30">
              	<img id="btnOk" alt="저장" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_save.gif' />" />
                  <a href="<c:url value='/main/main.omp'/>"><img id="btnReset" src="<c:url value='/${ThreadSession.serviceLocale.language}/images/common/btn_cancel.gif' />" alt="취소" /></a>
		</div>

	</div>
</div>
</form>

