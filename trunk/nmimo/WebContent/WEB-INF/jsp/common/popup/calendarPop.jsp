<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp" %>
<style>
	html,body{background:#fff;}
	html {overflow:hidden;}
</style>

<div id="popup" style="width:300px;">

<!-- pTop -->
<div class="pTop">
	<h6>예약시간 설정</h6>
</div>
<hr />

<!-- pContents -->
<div class="pContents">
	
    <!-- Calendar Area -->
    <div class="pCalendar">
        <div class="calD">
        <select name="select" class="sType" id="select" style="width:55px;">
          <option value="">2013</option>
          <option value=""></option>
        </select> 년
        <select name="select" class="sType" id="select" style="width:40px;">
          <option value="">01</option>
          <option value=""></option>
        </select> 월
        </div>
        <!-- Table Area -->
        <table summary="달력" class="CalendarTable">
            <caption>달력</caption>
            <thead>
                <tr>
                    <td class="l">일</td>
                    <td>월</td>
                    <td>화</td>
                    <td>수</td>
                    <td>목</td>
                    <td>금</td>
                    <td class="r">토</td>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="l"><span>1</span></td>
                    <td><span>2</span></td>
                    <td><span>3</span></td>
                    <td><span>4</span></td>
                    <td class="today"><a href="#">5</a></td>
                    <td><a href="#">6</a></td>
                    <td class="r">7</td>
                </tr>
                <tr>
                    <td class="l">8</td>
                    <td><a href="#">9</a></td>
                    <td><a href="#">10</a></td>
                    <td><a href="#">11</a></td>
                    <td><a href="#">12</a></td>
                    <td><a href="#">13</a></td>
                    <td class="r">14</td>
                </tr>
                <tr>
                    <td class="l">15</td>
                    <td><a href="#">16</a></td>
                    <td><a href="#">17</a></td>
                    <td><a href="#">18</a></td>
                    <td><a href="#">19</a></td>
                    <td><a href="#">20</a></td>
                    <td class="r">21</td>
                </tr>
                <tr>
                    <td class="l">22</td>
                    <td><a href="#">23</a></td>
                    <td><a href="#">24</a></td>
                    <td><a href="#">25</a></td>
                    <td><a href="#">26</a></td>
                    <td><a href="#">27</a></td>
                    <td class="r">28</td>
                </tr>
                <tr>
                    <td class="l">29</td>
                    <td><a href="#">30</a></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td class="r"></td>
                </tr>
                <tr>
                    <td class="l"></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td class="r"></td>
                </tr>
            </tbody>
        </table>
	</div>
    
    <div class="pCal">
        <ul>
        <li class="red" style="font-size:11px">
            발송 예약일은 발송 신청일 5일 이후 평일(공휴일 주말 제외)만 선택이 가능합니다. 
        </li>
        <li class="red" style="font-size:11px; margin:0;">
            발송 일시는 승인 단계에서 조정 될 수 있으니 승인완료시에 메시지 발송 일시를 확인하시기 바랍니다. 
        </li>
      	</ul>
    </div>
    
</div>
<hr />

<!-- pBottom -->
<div class="pBottom">
	<a href="#" class="btn_red"><strong>선 택</strong></a> <a href="javascript:window.open('','_self').close();" class="btn_red"><strong>닫 기</strong></a>
</div>
</div>