<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
function goEdit(){
	document.noticeFrm.action = "/notice/editForm.do";
	document.noticeFrm.submit();
} 
</script>
<form id="noticeFrm" name="noticeFrm" method="post">
	<input type="hidden" name="ntfSeq" id="ntfSeq" value="${info.ntfSeq }"/>
	<div class="contents">
        <!-- Title -->
        <h3 class="tit">공지사항</h3>

		<!-- Table Area -->
            <table summary="쓰기" class="WriteTable">
                <caption>쓰기</caption>
                <tbody>
                    <tr>
                        <th scope="row" class="l">제 목</th>
                        <td colspan="3"  class="l">메시지 발송 프로세스 추가 내용</td>
                    </tr>
                    <tr>
                        <th scope="row" class="l">등록자</th>
                        <td class="l">관리자</td>
                        <th scope="row" class="l">등록일</th>
                        <td class="l">2013-00-00</td>
                    </tr>
                    <tr>
                        <th scope="row" class="l">이메일</th>
                        <td class="l">mrts@magicn.com</td>
                        <th scope="row" class="l">전화번호</th>
                        <td class="l">010-0000-0000</td>
                    </tr>
                    <tr>
                        <th scope="row" class="l">첨부파일</th>
                        <td colspan="3" class="l"><a href="#">img_123.jpg</a></td>
                    </tr>
                    <tr>
                      <th scope="row" class="l">내 용</th>
                      <td colspan="3" class="l"></td>
                    </tr>
                </tbody>
            </table>

	</div><!-- Contents E -->
    
    <!-- Btn -->
    <p class="btnC">
        <a href="javascript:goEdit();" class="btn_red"><strong>수 정</strong></a>
        <a href="#" class="btn_red"><strong>삭 제</strong></a>
        <a href="/notice/list.do" class="btn_red"><strong>목 록</strong></a>
    </p>
</form>