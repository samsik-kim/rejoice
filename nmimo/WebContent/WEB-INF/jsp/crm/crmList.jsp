<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<script type="text/javascript">

	$(function() {
		
		//InnerList 호출
		pageLoadAjaxListInner("crmListForm", "innerList", "/crm/ajaxListInner.do"); 
		
		//tablesorter 적용 
		$("#crmListTable").tablesorter({widgets: ['zebra']});
		
 	});	
	
	//검색조건 조회 aJax처리  
	function goSearch(){
		
		//검색키워드 validation체크
		if($("select[name=searchCode]").val() !="0"){	
			if(showValidate('crmListForm', 'dialog', '검색조건 확인.')){
				return;
			}	
			return;
		}
		pageLoadAjaxListInner("crmListForm", "innerList", "/crm/ajaxListInner.do");
	}
	
	//Paging처리 aJax처리
	function goList(v){
		$("#pageIndex").val(v);
		pageLoadAjaxListInner("crmListForm", "innerList", "/crm/ajaxListInner.do");
	}
</script>


	<h3 class="tab">
		<img src="/resource/images/crm_tab01_f.png" alt="나의 작업" />
		<a href="/crm/regMsgForm.do"><img src="/resource/images/crm_tab02_n.png" alt="메시지 등록" /></a>
	</h3>
       
	<!-- Title -->
  <h3 class="tit">나의 작업</h3>

      <!-- Search Area -->
      <div class="" style="background:#e7e7e7; border:solid 1px #d2d2d2">
			<form name="crmListForm" id="crmListForm" >
			<input type="hidden" id="pageIndex" name="pageIndex" value="${pageInfo.currentPage}">
              <fieldset style="padding:10px; text-align:center">
                  <legend class="hidden">검색</legend>
                      <select id="searchCode" name="searchCode" class="sType" style="width:100px;">
                          <option value="0" <c:if test="${crmInfo.searchCode == '0'}"> selected </c:if>>전체</option>
                          <option value="1" <c:if test="${crmInfo.searchCode == '1'}"> selected </c:if>>서비스구분</option>                          
                          <option value="2" <c:if test="${crmInfo.searchCode == '2'}"> selected </c:if>>제목</option>
                          <option value="3" <c:if test="${crmInfo.searchCode == '3'}"> selected </c:if>>상태</option>
                      </select>
                      <input type="text" id="searchVal" name="searchVal" class="iType" style="width:300px;" value="<c:out value="${crmInfo.searchCodeVal}"/>" v:required m:required="검색값을 입력해 주세요."/>
                      <a href="#" class="btn_sml" onClick="goSearch();"><span>검 색</span></a>
              </fieldset>
          </form>
      </div>
      
<div id="innerList"></div>