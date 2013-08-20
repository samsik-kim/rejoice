<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- Title -->
<h3 class="tit">SMS/MMS 작업요청</h3>

	<div class="help">
		<span><a href="/kbn/msgRequestForm.do" class="btn_help"><strong>요청</strong></a></span>
	</div>

    <!-- Table Area -->
    <table summary="리스트" class="ListTable">
        <caption>리스트</caption>
        <thead>
            <tr>
                <th style="width:150px">작업ID</th>
                <th style="width:250px">제 목</th>
                <th style="width:150px">전송기간</th>
                <th style="width:100px">전송상태</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><a href="#">mmsb120628163608</a></td>
                <td class="l"><a href="#">mms_test</a></td>
                <td>2013.00.00 ~ 2013.00.00</td>
                <td>전송종료</td>
            </tr>
        </tbody>
    </table>
 
    <!-- Paging -->
    <div class="paging">
        <a href="#" style="padding-right:0;"><img src="/resource/images/btn_p_first.gif" alt="처음" /></a>
        <a href="#"><img src="/resource/images/btn_p_prev.gif" alt="이전" /></a>
        <a href="#">1</a>
        <strong><a href="#">2</a></strong>
        <a href="#">3</a>
        <a href="#"><img src="/resource/images/btn_p_next.gif" alt="다음" /></a>
        <a href="#" style="padding-left:0;"><img src="/resource/images/btn_p_end.gif" alt="끝" /></a>
    </div>

