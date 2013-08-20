<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<style>
	html,body{background:#fff;}
</style>

<!-- pTop -->
<div class="tit">
	<h2>발송내역조회 정보</h2>
</div>
<hr />

<!-- Wrap S -->
<div id="wrap" style="width:1120px">

<!-- pContents S -->
<div class="pContents">

	<!-- Contents Area --> 
	<div class="pPreview">
        <ul>
            <li>
       		<!-- Table Area -->
            <table summary="쓰기" class="WriteTable" style=" width:480px;">
                <tbody>
                    <tr>
                        <th scope="row" class="l" style="width:30%">검토협의</th>
                        <td class="l">10131261 고민영</td>
                    </tr>
                    <tr>
                        <th scope="row" class="l">114협의</th>
                        <td class="l">-</td>
                    </tr>
                    <tr>
                        <th scope="row" class="l">발송승인</th>
                        <td class="l">2000700 발송승인자7</td>
                    </tr>
                </tbody>
            </table>
       		<!-- Table Area -->
            <table summary="쓰기" class="WriteTable" style=" width:480px;  margin-top:10px">
                <tbody>
                    <tr>
                        <th scope="row" class="l" style="width:30%">작업명</th>
                        <td class="l">일반SMS테스트</td>
                    </tr>
                    <tr>
                        <th scope="row" class="l">발송 대상정보</th>
                        <td class="l">일반SMS테스트</td>
                    </tr>
                    <tr>
                        <th scope="row" class="l">기타정보</th>
                        <td class="l">일반SMS테스트</td>
                    </tr>
                    <tr>
                      <th scope="row" class="l">부서장 확인사항</th>
                      <td class="l">일반SMS테스트</td>
                    </tr>
                </tbody>
            </table>
       		<!-- Table Area -->
            <table summary="쓰기" class="WriteTable" style=" width:480px; margin-top:10px">
                <tbody>
                    <tr>
                        <th scope="row" class="l" style="width:30%">메시지 특성</th>
                        <td class="l">공지</td>
                    </tr>
                    <tr>
                        <th scope="row" class="l">4대 검토 확인사항</th>
                        <td class="l">법률</td>
                    </tr>
                </tbody>
            </table>
       		<!-- Table Area -->
            <table summary="쓰기" class="WriteTable" style=" width:480px; margin-top:10px">
                <tbody>
                    <tr>
                        <th scope="row" class="l" style="width:30%">발신번호</th>
                        <td class="l">전담콜센트(114)</td>
                    </tr>
                    <tr>
                        <th scope="row" class="l">문의처 정보<span style="font-size:11px; display:inline-block; line-height:11px">(링크/URL)</span></th>
                        <td class="l">&nbsp;</td>
                    </tr>
                </tbody>
            </table>
       		<!-- Table Area -->
            <table summary="쓰기" class="WriteTable" style=" width:480px; margin-top:10px">
                <tbody>
                    <tr>
                        <th scope="row" class="c">예약일 및 발송 대상 고객 파일 첨부</th>
                    </tr>
                    <tr>
                        <td class="l">대상파일 : <a href="#">20121106162715_admin_new.txt </a></td>
                    </tr>
                    <tr>
                      <td class="l">발송일 : 2013-00-00 09시 <span class="red" style=" padding-left:10px">대상건수 : 총 3건</span></td>
                    </tr>
                </tbody>
            </table>
            
            <p class="fontS" style="margin-top:10px; display: block; text-indent:0">
            <input type="checkbox" id="" name="" /> <label for="">수신확인</label>
            </p>
            
            </li>
        	<li>
        	<!--피쳐폰-->	
            <div class="FeaturePhone">
                <h1><img src="../../resource/images/testsend_title_01.gif" alt=""></h1>		
                <div style="padding:20px 10px; width:200px">
                	발신번호 : <input type="text" name="dd" style="width:110px;" value="**010" readonly>
                </div>
                <div class="FeaturePhone_img">
                    <div id="FeaturePhone_text" class="test_sms_msg1">													
                        [olleh알림] 
                    </div>
                </div>
            </div>
            </li>
            <li>
            <!--스마트폰-->	
            <div class="SmartPhone">
                <h1><img src="../../resource/images/testsend_title_03.gif" alt=""></h1>		
                <div class="SmartPhone_img">
                    <div id="SmartPhone_number" class="test_sms_msg">
                        01022223333
                    </div>
                    <div id="SmartPhone_text" class="test_sms_msg">
                        [olleh알림]
                    </div>
                </div>
            </div>
            </li>
        </ul>  
	</div>
    
    <!-- Btn -->
    <p class="btnC" style="position:relative;top:-20px">
        <a href="#" class="btn_red"><strong>닫 기</strong></a>
    </p>

</div><!-- //pContents E -->
<hr />

</div><!-- //Wrap E -->