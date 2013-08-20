<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	html,body{background:#fff;}
	html {overflow:hidden;}
</style>

<div id="popup" style="width:590px;">

<!-- pTop -->
<div class="pTop">
	<h2>미리보기</h2>
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
        </ul>  
	</div>

</div>
<hr />

<!-- pBottom -->
<div class="pBottom">
	<a href="javascript:window.open('','_self').close();" class="btn_red"><strong>닫 기</strong></a>
</div>

</div>