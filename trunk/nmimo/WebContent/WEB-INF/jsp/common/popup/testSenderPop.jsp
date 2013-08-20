<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<style>
	html,body{background:#fff;}
</style>

<div id="popup" style="width:590px;">

<!-- pTop -->
<div class="pTop">
	<h2>시험전송</h2>
</div>
<hr />

<!-- pContents -->
<div class="pContents">

	<!-- Contents Area --> 
	<div class="pPreview">
        <ul>
        	<li>
        	<!--피쳐폰-->	
            <div class="FeaturePhone">
                <h1><img src="/resource/images/testsend_title_01.gif" alt=""></h1>		
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
                <h1><img src="/resource/images/testsend_title_03.gif" alt=""></h1>		
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
            <li>
       		<!-- Table Area -->
			<fieldset style=" padding:10px 0;">
				<legend class="hidden">입력</legend>
				<table summary="쓰기" class="WriteTable" style=" width:500px">
					<caption>쓰기</caption>
					<tbody>
						<tr>
							<th scope="row" class="l" style="width:25%">수신번호1</th>
							<td class="l"><input type="text" id="" class="iType" style="width:120px;" /> <span class="fontM"><input name="" type="checkbox" value="" /> 스마트폰</span></td>
						</tr>
						<tr>
							<th scope="row" class="l" style="width:25%">수신번호2</th>
							<td class="l"><input type="text" id="" class="iType" style="width:120px;" /> <span class="fontM"><input name="" type="checkbox" value="" /> 스마트폰</span></td>
						</tr>
						<tr>
							<th scope="row" class="l" style="width:25%">수신번호3</th>
							<td class="l"><input type="text" id="" class="iType" style="width:120px;" /> <span class="fontM"><input name="" type="checkbox" value="" /> 스마트폰</span></td>
						</tr>
						<tr>
							<th scope="row" class="l" style="width:25%">수신번호4</th>
							<td class="l"><input type="text" id="" class="iType" style="width:120px;" /> <span class="fontM"><input name="" type="checkbox" value="" /> 스마트폰</span></td>
						</tr>
						<tr>
							<th scope="row" class="l" style="width:25%">수신번호5</th>
							<td class="l"><input type="text" id="" class="iType" style="width:120px;" /> <span class="fontM"><input name="" type="checkbox" value="" /> 스마트폰</span></td>
						</tr>
					</tbody>
				</table>
			</fieldset> 
            </li>
        </ul>  
	</div>

</div>
<hr />

<!-- pBottom -->
<div class="pBottom">
	<a href="#" class="btn_red"><strong>전 송</strong></a>
	<a href="javascript:window.open('','_self').close();" class="btn_red"><strong>취 소</strong></a>
	<a href="#" class="btn_red"><strong>RESET</strong></a>
</div>

</div>