<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<style>
	html,body{background:#fff;}
</style>


<div id="popup" style="width:400px;">

<!-- pTop -->
<div class="pTop">
	<h2>반려 사유 선택</h2>
</div>
<hr />

<!-- pContents -->
<div class="pContents">

	<!-- Table Area -->
	<table summary="쓰기" class="WriteTable">
		<caption>리스트</caption>
		<tbody>
			<tr>
			  <th class="c" scope="row">
              	메시지가 <span class="red">반려</span> 됩니다. <span class="red">반려사유</span>를 입력해 주세요.
			  </th>
			</tr>
			<tr>
			  <td class="l" scope="row">
              <fieldset>
                  <input name="" type="checkbox" value="" /> <label for="">BI/CI준수여부</label>
                  <input name="" type="checkbox" value="" style="margin-left:20px" /> <label for="">메시지내용</label>
                  <input name="" type="checkbox" value="" style="margin-left:41px" /> <label for="">콜백번호</label> <br />
                  <input name="" type="checkbox" value="" /> <label for="">메시지 총 건수</label> 
                  <input name="" type="checkbox" value="" style="margin-left:17px" /> <label for="">URL준수여부</label> 
                  <input name="" type="checkbox" value="" style="margin-left:30px" /> <label for="">스팸여부</label> <br />
                  <input name="" type="checkbox" value="" /> <label for="">발송건수</label> 
                  <input name="" type="checkbox" value="" style="margin-left:49px" /> <label for="">기타</label>
              </fieldset> 
              </td>
		    </tr>
			<tr>
			  <td class="l" scope="row">
              <fieldset>
              	<textarea name="" cols="55" rows="7"></textarea>
              </fieldset>
              </td>
		    </tr>
		</tbody>
	</table>

</div>
<hr />

<!-- pBottom -->
<div class="pBottom">
	<a href="#" class="btn_red"><strong>확 인</strong></a>
</div>

</div>