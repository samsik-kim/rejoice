<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
	<div class="contents">
        
        <!-- Title -->
        <h3 class="tit">공지사항</h3>

		<!-- Table Area -->
		<form id="" name="" method="post">
		<input type="hidden" name="ntfSeq" id="ntfSeq" value="${info.ntfSeq }"/>
		  <fieldset>
		    <legend class="hidden">입력</legend>
				<table summary="쓰기" class="WriteTable">
					<caption>쓰기</caption>
					<tbody>
						<tr>
							<th scope="row" class="l">제 목</th>
							<td class="l"><input type="text" id="" class="iType" style="width:500px;" /></td>
						</tr>
						<tr>
							<th scope="row" class="l">내 용</th>
							<td class="l"><textarea name="" cols="110" rows="20"></textarea></td>
						</tr>
						<tr>
							<th scope="row" class="l">첨부파일</th>
							<td class="l">
                                <input type="text" id="" class="iType" style="width:500px;" />
                                <a href="#" class="btn_sml"><span>파일찾기</span></a>
                                <br /><p class="fontM">첨부된 파일 : www.txt</p></td>
						</tr>
						<tr>
							<th scope="row" class="l">팝업생성 여부<br />게시기간 설정</th>
							<td class="l"><input type="checkbox" id="" name="" /> <label for="" class="red">팝업게시</label>
                            	<br /><p class="fontM">
                                <select id="" class="sType">
                                <option value="">2013</option>
                                    <option value=""></option>
                                </select> 년 
                                <select id="" class="sType">
                                <option value="">00</option>
                                    <option value=""></option>
                                </select> 월 
                                <select id="" class="sType">
                                <option value="">00</option>
                                    <option value=""></option>
                                </select> 일 까지 팝업게시 설정 <input type="checkbox" id="" name="" style="margin-left:10px"/> <label for="">미리보기</label>
                                </p>
                          </td>
						</tr>
					</tbody>
				</table>
		  </fieldset>
	  </form>

	</div><!-- Contents E -->
    
    <!-- Btn -->
    <p class="btnC">
        <a href="#" class="btn_red"><strong>수 정</strong></a>
        <a href="/notice/list.do" class="btn_red"><strong>목 록</strong></a>
    </p>