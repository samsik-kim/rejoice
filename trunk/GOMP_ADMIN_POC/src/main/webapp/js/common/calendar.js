  /**
 * @author Zag (zag@uzen.net)
 *
 * 달력 팝업 관련
 *
 */
  
var calendar = null;

/**
 * 달력 선택 팝업을 띄운다
 *
 * @param target 값을 돌려 받을 input text, 팝업을 띄울때 target의 좌측 하단에 맞추기때문에 반드시 not null 이어야한다
 * @param dateString 팝업의 설정 날짜
 * @param callback 날짜를 선택한 뒤 특별한 처리를 하고자 할 때 callback을 설정하면 호출된다 func(target, dateString, year, month, day) 형태로 인자를 받을 수 있다
 *
 */
function openCalendar(target, dateString, callback) {

	if(target) {
		calendar = new Calendar(target, dateString, callback);
		calendar.show();
	} else {
		alert('target이 지정되지 않았습니다!\n input text를 첫번째 인자로 넣어주세요.');
	}

}

/**
 * 달력 객체 생성자
 */
function Calendar(target, dateString, callback) {
	
	this.init(target, dateString, callback);
	
}

/**
 * 달력 객체
 */
Calendar.prototype = {
	
	/**
	 * 초기화
	 */
	init: function(target, dateString, callback) {
		
		this.target = target;
		this.selectedDate = stringToDate(dateString);
		if(!this.selectedDate)
			this.selectedDate = stringToDate(target.value);
		if(!this.selectedDate)
			this.selectedDate = new Date();
		this.today = new Date();
		this.callback = callback;
		
		/* Constants */
		this.WIDTH = 193;
		this.HEIGHT_BASE = 47;
		this.HEIGHT_DELTA = 25;
	},
	
	/**
	 * 달력 팝업 띄우기
	 */
	show: function() {
	
		this.popup = window.createPopup();
		
		this.popup.document.body.innerHTML = this.makeBodyHTML();
		
		this.popup.show(0, this.target.offsetHeight - 1, this.WIDTH, this.HEIGHT_BASE + this.rowCount * this.HEIGHT_DELTA, this.target);
	},
	
	/**
	 * 달력 팝업의 BODY의 innerHTML 생성하기
	 */
	makeBodyHTML: function() {
	
		var year = this.selectedDate.getFullYear();
		var month = this.selectedDate.getMonth() + 1; // Date.getMonth()는 0 ~ 11 까지의 값을 리턴하기 때문에 ...
		
		var yearAsString = "" + year;
		var monthAsString = ((month < 10) ? "0" : "") + month;

		html = ""
		+ "<div align='center' style='border:1px solid #828282; font-size:9pt; width:193px'>"
		+ "	<div align='center' style='height:25px; padding-top:2px;'>"
		+ "		<span style='padding-left:5px;'>"
		+ "			<img src='../images/common/calendar/btn_calenderLeft.gif' style='border:none' align='absmiddle' onmouseover=\"this.style.cursor='hand'\" onmouseout=\"this.style.cursor='default'\" onclick='parent.calendar.goPrevYear()'/>"
		+ "		</span>"
		+ "		<input style='width:32px; font-size:9pt' id='keyYear' value='" + yearAsString + "' />년 "
		+ "		<span style='padding-left:5px;'>"
		+ "			<img src='../images/common/calendar/btn_calenderRight.gif' style='border:none' align='absmiddle' onmouseover=\"this.style.cursor='hand'\" onmouseout=\"this.style.cursor='default'\" onclick='parent.calendar.goNextYear()'/>"
		+ "		</span>"
		+ "		<span style='padding-left:5px;'>"
		+ "			<img src='../images/common/calendar/btn_calenderLeft.gif' style='border:none' align='absmiddle' onmouseover=\"this.style.cursor='hand'\" onmouseout=\"this.style.cursor='default'\" onclick='parent.calendar.goPrevMonth()'/>"
		+ "		</span>"
		+ "		<input style='width:18px; font-size:9pt' id='keyDate' value='" + monthAsString + "' /> 월"
		+ "		<span style='padding-right:5px;'>"
		+ "			<img src='../images/common/calendar/btn_calenderRight.gif' style='border:none' align='absmiddle' onmouseover=\"this.style.cursor='hand'\" onmouseout=\"this.style.cursor='default'\" onclick='parent.calendar.goNextMonth()'/>"
		+ "		</span>"
		+ "	</div>"
		+ "	<table width='100%' border='0' cellspacing='0' cellpadding='0' class='calenderPopup' style='border-collapse:collapse'>"
		+ "		<tr>"
		+ "			<th scope='col' style='background:url(../images/common/calendar/calender_sub_hd.gif) repeat-x; height:18px; font-weight:normal; color:#4c4c4c; font-size:9pt; text-align:center; padding-top:3px;'>일</th>"
		+ "			<th scope='col' style='background:url(../images/common/calendar/calender_sub_hd.gif) repeat-x; height:18px; font-weight:normal; color:#4c4c4c; font-size:9pt; text-align:center; padding-top:3px;'>월</th>"
		+ "			<th scope='col' style='background:url(../images/common/calendar/calender_sub_hd.gif) repeat-x; height:18px; font-weight:normal; color:#4c4c4c; font-size:9pt; text-align:center; padding-top:3px;'>화</th>"
		+ "			<th scope='col' style='background:url(../images/common/calendar/calender_sub_hd.gif) repeat-x; height:18px; font-weight:normal; color:#4c4c4c; font-size:9pt; text-align:center; padding-top:3px;'>수</th>"
		+ "			<th scope='col' style='background:url(../images/common/calendar/calender_sub_hd.gif) repeat-x; height:18px; font-weight:normal; color:#4c4c4c; font-size:9pt; text-align:center; padding-top:3px;'>목</th>"
		+ "			<th scope='col' style='background:url(../images/common/calendar/calender_sub_hd.gif) repeat-x; height:18px; font-weight:normal; color:#4c4c4c; font-size:9pt; text-align:center; padding-top:3px;'>금</th>"
		+ "			<th scope='col' style='background:url(../images/common/calendar/calender_sub_hd.gif) repeat-x; height:18px; font-weight:normal; color:#4c4c4c; font-size:9pt; text-align:center; padding-top:3px;'>토</th>"
		+ "		</tr>";
		
		var firstDate = getFirstDateOfMonth(this.selectedDate);
		var lastDate = getLastDateOfMonth(this.selectedDate);
		
		var firstDatePos = firstDate.getDay();
		var lastDatePos = firstDatePos + lastDate.getDate();
		
		cellCount = lastDatePos + (6 - lastDate.getDay());
		
		//this.rowCount = cellCount / 7; // 달력의 줄수
		this.rowCount = 6; 
			
		var realDate;
		
		for(var d = 0; d < cellCount; ++d) {
		
			realDate = d - firstDatePos + 1;
		
			if(d % 7 == 0) {
				html += "<tr>";
			}
			
			
			if(d < firstDatePos || lastDatePos <= d) {
				html += "<td style='font-size:9pt; height:25px; text-align:center; width:25px;'><span>&nbsp;</span>";
			} else {
				html += "<td style='font-size:9pt; height:25px; text-align:center; width:25px; color:#333333;' onclick='parent.calendar.selectDate(" + realDate + ")' onmouseover=\"this.style.cursor='hand'\" onmouseout=\"this.style.cursor='default'\">";
				if(realDate == this.selectedDate.getDate())
					html += "<span style='border:2px solid #76a700; width:20px; padding-top:2px; padding-bottom:0px; display:block;'>" + realDate + "</span>";
				else
					html += "<span>" + realDate + "</span>";
			}
			html += "</td>";
			
			if(d % 7 == 6) {
				html += "</tr>";
			}
			
		}
		var a = cellCount / 7;
		for( ; a < this.rowCount; a++){
			html += "<tr>";
			for(var y=0; y<7; y++){
				html += "<td style='font-size:9pt; height:25px; text-align:center; width:25px;'><span>&nbsp;</span></td>";
			}
			html += "</tr>";
		}
		html += "</table></div>";

		return html;
	},
	
	/**
	 * 날짜 선택 이벤트 핸들러
	 */
	selectDate: function(date) {
		var y = this.selectedDate.getFullYear();
		var m = (((this.selectedDate.getMonth() + 1) < 10) ? "0" : "") + (this.selectedDate.getMonth() + 1);
		var d = ((date < 10) ? "0" : "") + date;
		
		var dateString = y + "-" + m + "-" + d;
		
		if(this.target) {
			this.target.value = dateString;
		}
		
		if(this.callback) {
			this.callback(this.target, dateString, y, m, d);
		}
		
		this.hide();
	},
	
	/**
	 * 달력 숨기기
	 */
	hide: function() {
		this.popup.hide();
	},
	
	/**
	 * 다음 달 보기
	 */
	goNextMonth: function() {
		if(!this.popup) {
			return;
		}
		
		this.selectedDate = getNextMonth(this.selectedDate);
		
		this.popup.document.body.innerHTML = this.makeBodyHTML();
		
		this.popup.show(0, this.target.offsetHeight - 1, this.WIDTH, this.HEIGHT_BASE + this.rowCount* this.HEIGHT_DELTA , this.target);
	},
	
	/**
	 * 지난 달 보기
	 */
	goPrevMonth: function() {
		if(!this.popup) {
			return;
		}
		
		this.selectedDate = getPrevMonth(this.selectedDate);
		
		this.popup.document.body.innerHTML = this.makeBodyHTML();
		
		this.popup.show(0, this.target.offsetHeight - 1, this.WIDTH, this.HEIGHT_BASE + this.rowCount * this.HEIGHT_DELTA, this.target);
	},
	
	/**
	 * 지난 년도 보기
	 */
	goPrevYear: function() {
		if(!this.popup) {
			return;
		}
		
		this.selectedDate = getPrevYear(this.selectedDate);
		
		this.popup.document.body.innerHTML = this.makeBodyHTML();
		
		this.popup.show(0, this.target.offsetHeight - 1, this.WIDTH, this.HEIGHT_BASE + this.rowCount * this.HEIGHT_DELTA, this.target);
	},
	
	/**
	 * 다음 년도 보기
	 */
	goNextYear: function() {
		if(!this.popup) {
			return;
		}
		
		this.selectedDate = getNextYear(this.selectedDate);
		
		this.popup.document.body.innerHTML = this.makeBodyHTML();
		
		this.popup.show(0, this.target.offsetHeight - 1, this.WIDTH, this.HEIGHT_BASE + this.rowCount * this.HEIGHT_DELTA, this.target);
	}
}

/**
 * 문자열을 날짜로 변환한다
 * 문자열 형식은 "2008/05/21", "2008-05-21", "2008.05.21"만 지원한다
 */
function stringToDate(str) {
	if(str) {
		var matches = str.match(/(\d{4})[-./](\d{1,2})[-./](\d{1,2})/);
		if(matches != null) {
			return createDate(parseInt(matches[1], 10), parseInt(matches[2], 10), parseInt(matches[3], 10));
		}
	}
	return null;
}

/**
 *
 */
function createDate(year, month, date) {
	return doCreateDate(year, month - 1, date);
}

function doCreateDate(year, month, date) {
	return new Date(year, month, date, 0, 0, 0, 0);
}

/**
 *
 */
function copyDate(date) {
	return doCreateDate(date.getFullYear(), date.getMonth(), date.getDate());
}

/**
 * 해당 월의 첫날 날짜 구하기
 */
function getFirstDateOfMonth(date) {
	return doCreateDate(date.getFullYear(), date.getMonth(), 1);
}

/**
 * 해당 월의 마직막 날짜 구하기
 */
function getLastDateOfMonth(date) {
	return doCreateDate(date.getFullYear(), date.getMonth() + 1, 0);
}

/**
 * 한달 전 날짜 구하기
 */
function getPrevMonth(date) {
	
	// 지난 달 마지막 날짜를 구한다
	var newDate = doCreateDate(date.getFullYear(), date.getMonth(), 0);
	
	// 1. 현재 '일'이 지난 달 마지막 '일'보다 작으면, '월'만 감소시키고
	// 2. 아니면 지난 달 마지막 날짜를 리턴한다
	return (date.getDate() < newDate.getDate()) ? doCreateDate(date.getFullYear(), date.getMonth() - 1, date.getDate()) : newDate;
}

/**
 * 한달 후 날짜 구하기
 */
function getNextMonth(date) {

	// 다음 달 마지막 날짜를 구한다
	var newDate = doCreateDate(date.getFullYear(), date.getMonth() + 2, 0);
	
	// 1. 현재 '일'이 다음 달 마지막 '일'보다 작으면, '월'만 증가시키고
	// 2. 아니면 다음 달 마지막 날짜를 리턴한다
	return (date.getDate() < newDate.getDate()) ? doCreateDate(date.getFullYear(), date.getMonth() + 1, date.getDate()) : newDate;
}

/**
 * 일년 전 날짜 구하기
 */
function getPrevYear(date) {

	// 일년 전의 해당월의 마지막 날짜를 구한다. 
	var newDate = doCreateDate(date.getFullYear() - 1, date.getMonth() + 1, 0);
	
	// 1. 현재 '일'이 다음 달 마지막 '일'보다 작으면, '월'만 증가시키고
	// 2. 아니면 다음 달 마지막 날짜를 리턴한다
	return (date.getDate() < newDate.getDate()) ? doCreateDate(date.getFullYear() - 1, date.getMonth(), date.getDate()) : newDate;
}

/**
 * 일년 후 날짜 구하기
 */
function getNextYear(date) {

	// 다음 년도 해당월의 마지막 날짜를 구한다
	var newDate = doCreateDate(date.getFullYear() + 1, date.getMonth() + 1 , 0);
	
	// 1. 현재 '일'이 다음 달 마지막 '일'보다 작으면, '월'만 증가시키고
	// 2. 아니면 다음 달 마지막 날짜를 리턴한다
	return (date.getDate() < newDate.getDate()) ? doCreateDate(date.getFullYear() + 1, date.getMonth(), date.getDate()) : newDate;
}
/*********************************************/
//2008/06/04 charles추가
/**
 * 유효한(존재하는) 월(月)인지 체크
 */
function isValidMonth(mm) {
    var m = parseInt(mm,10);
    return (m >= 1 && m <= 12);
}

/**
 * 유효한(존재하는) 일(日)인지 체크
 */
function isValidDay(yyyy, mm, dd) {
    var m = parseInt(mm,10) - 1;
    var d = parseInt(dd,10);

    var end = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
    if ((yyyy % 4 == 0 && yyyy % 100 != 0) || yyyy % 400 == 0) {
        end[1] = 29;
    }

    return (d >= 1 && d <= end[m]);
}

/**
 * 유효한(존재하는) 시(時)인지 체크
 */
function isValidHour(hh) {
    var h = parseInt(hh,10);
    return (h >= 1 && h <= 24);
}

/**
 * 유효한(존재하는) 분(分)인지 체크
 */
function isValidMin(mi) {
    var m = parseInt(mi,10);
    return (m >= 1 && m <= 60);
}

/**
 * Time 형식인지 체크(느슨한 체크)
 */
function isValidTimeFormat(time) {
    return (!isNaN(time) && time.length == 12);
}

/**
 * 유효하는(존재하는) Time 인지 체크

 * ex) var time = form.time.value; //'200102310000'
 *     if (!isValidTime(time)) {
 *         alert("올바른 날짜가 아닙니다.");
 *     }
 */
function isValidTime(time) {
    var year  = time.substring(0,4);
    var month = time.substring(4,6);
    var day   = time.substring(6,8);
    var hour  = time.substring(8,10);
    var min   = time.substring(10,12);

    if (parseInt(year,10) >= 1900  && isValidMonth(month) &&
        isValidDay(year,month,day) && isValidHour(hour)   &&
        isValidMin(min)) {
        return true;
    }
    return false;
}

/**
 * Time 스트링을 자바스크립트 Date 객체로 변환
 * parameter time: Time 형식의 String
 */
function toTimeObject(time) { //parseTime(time)
    var year  = time.substr(0,4);
    var month = time.substr(4,2) - 1; // 1월=0,12월=11
    var day   = time.substr(6,2);
    var hour  = time.substr(8,2);
    var min   = time.substr(10,2);

    return new Date(year,month,day,hour,min);
}

/**
 * Time 스트링을 자바스크립트 Date 객체로 변환
 * parameter time: Time 형식의 String
 */
function toDateObject(time) { //parseTime(time)
    var year  = time.substr(0,4);
    var month = time.substr(4,2) - 1; // 1월=0,12월=11
    var day   = time.substr(6,2);

    return new Date(year,month,day);
}

/**
 * Time 스트링을 자바스크립트 Date 객체로 변환
 * parameter time: Time 형식의 String
 */
function toFormatString(time, dele) { //parseTime(time)
    var year  = time.substr(0,4);
    var month = time.substr(4,2); // 1월=0,12월=11
    var day   = time.substr(6,2);

    return ("" + year + dele + month + dele + day)
}


/**
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * parameter date: JavaScript Date Object
 */
function toTimeString(date) { //formatTime(date)
    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    var day   = date.getDate();
    var hour  = date.getHours();
    var min   = date.getMinutes();

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }
    if (("" + hour).length  == 1) { hour  = "0" + hour;  }
    if (("" + min).length   == 1) { min   = "0" + min;   }

    return ("" + year + month + day + hour + min)
}


/**
 * 자바스크립트 Date 객체를 Time 스트링으로 변환
 * parameter date: JavaScript Date Object
 */
function toDateString(date, dele) { //formatTime(date)
    var year  = date.getFullYear();
    var month = date.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    var day   = date.getDate();

    if (("" + month).length == 1) { month = "0" + month; }
    if (("" + day).length   == 1) { day   = "0" + day;   }

    return ("" + year + dele + month + dele + day)
}

/**
 * Time이 현재시각 이후(미래)인지 체크
 */
function isFutureTime(time) {
    return (toTimeObject(time) > new Date());
}

/**
 * Time이 현재시각 이전(과거)인지 체크
 */
function isPastTime(time) {
    return (toTimeObject(time) < new Date());
}

/**
 * 주어진 Time 과 y년 m월 d일 차이나는 Time을 리턴

 * ex) var time = form.time.value; //'20000101'
 *     alert(shiftTime(time,0,0,-100));
 *     => 2000/01/01 00:00 으로부터 100일 전 Time
 */
function shiftDate(time,y,m,d, dele) { //moveTime(time,y,m,d)
    var date = toDateObject(time);

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함

    return toDateString(date, dele);
}

/**
 * 주어진 Time 과 y년 m월 d일 h시 차이나는 Time을 리턴

 * ex) var time = form.time.value; //'20000101000'
 *     alert(shiftTime(time,0,0,-100,0));
 *     => 2000/01/01 00:00 으로부터 100일 전 Time
 */
function shiftTime(time,y,m,d,h) { //moveTime(time,y,m,d,h)
    var date = toTimeObject(time);

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함
    date.setHours(date.getHours() + h);       //h시를 더함

    return toTimeString(date);
}

/**
 * 두 Time이 몇 개월 차이나는지 구함

 * time1이 time2보다 크면(미래면) minus(-)
 */
function getMonthInterval(time1,time2) { //measureMonthInterval(time1,time2)
    var date1 = toTimeObject(time1);
    var date2 = toTimeObject(time2);

    var years  = date2.getFullYear() - date1.getFullYear();
    var months = date2.getMonth() - date1.getMonth();
    var days   = date2.getDate() - date1.getDate();

    return (years * 12 + months + (days >= 0 ? 0 : -1) );
}

/**
 * 두 Time이 며칠 차이나는지 구함
 * time1이 time2보다 크면(미래면) minus(-)
 */
function getDayInterval(time1,time2) {
    var date1 = toTimeObject(time1);
    var date2 = toTimeObject(time2);
    var day   = 1000 * 3600 * 24; //24시간

    return parseInt((date2 - date1) / day, 10);
}

/**
 * 두 Time이 몇 시간 차이나는지 구함

 * time1이 time2보다 크면(미래면) minus(-)
 */
function getHourInterval(time1,time2) {
    var date1 = toTimeObject(time1);
    var date2 = toTimeObject(time2);
    var hour  = 1000 * 3600; //1시간

    return parseInt((date2 - date1) / hour, 10);
}

/**
 * 현재 시각을 Date 형식으로 리턴

 */
function getCurrentDate(dele) {
    return toFormatString(toTimeString(new Date()), "/");
}

/**
 * 현재 시각을 Time 형식으로 리턴

 */
function getCurrentTime() {
    return toTimeString(new Date());
}

/**
 * 현재 시각과 y년 m월 d일 h시 차이나는 Time을 리턴
 */
function getRelativeTime(y,m,d,h) {
/*
    var date = new Date();

    date.setFullYear(date.getFullYear() + y); //y년을 더함
    date.setMonth(date.getMonth() + m);       //m월을 더함
    date.setDate(date.getDate() + d);         //d일을 더함
    date.setHours(date.getHours() + h);       //h시를 더함

    return toTimeString(date);
*/
    return shiftTime(getCurrentTime(),y,m,d,h);
}

/**
 * 현재 年을 YYYY형식으로 리턴
 */
function getYear() {
/*
    var now = new Date();
    return now.getFullYear();
*/
    return getCurrentTime().substr(0,4);
}

/**
 * 현재 月을 MM형식으로 리턴
 */
function getMonth() {
/*
    var now = new Date();

    var month = now.getMonth() + 1; // 1월=0,12월=11이므로 1 더함
    if (("" + month).length == 1) { month = "0" + month; }

    return month;
*/
    return getCurrentTime().substr(4,2);
}

/**
 * 현재 日을 DD형식으로 리턴

 */
function getDay() {
/*
    var now = new Date();

    var day = now.getDate();
    if (("" + day).length == 1) { day = "0" + day; }

    return day;
*/
    return getCurrentTime().substr(6,2);
}

/**
 * 현재 時를 HH형식으로 리턴
 */
function getHour() {
/*
    var now = new Date();

    var hour = now.getHours();
    if (("" + hour).length == 1) { hour = "0" + hour; }

    return hour;
*/
    return getCurrentTime().substr(8,2);
}

/**
 * 오늘이 무슨 요일이야?

 * ex) alert('오늘은 ' + getDayOfWeek() + '요일입니다.');
 * 특정 날짜의 요일을 구하려면? => 여러분이 직접 만들어 보세요.
 */
function getDayOfWeek() {
    var now = new Date();

    var day = now.getDay(); //일요일=0,월요일=1,...,토요일=6
    var week = new Array('일','월','화','수','목','금','토');

    return week[day];
}


/*
  윤달 포함 달별 일수 Return
*/
function daysPerMonth()
{
    var DOMonth  = new Array("31","28","31","30","31","30","31","31","30","31","30","31");
    var IDOMonth = new Array("31","29","31","30","31","30","31","31","30","31","30","31");

	if(arguments[1] == 0) arguments[1] = 12;

    if ( (arguments[0]%4) == 0 )
    {
        if ( (arguments[0]%100) == 0 && (arguments[0]%400) != 0 )
            return DOMonth[arguments[1]-1];
        return IDOMonth[arguments[1]-1];
    }
    else
        return DOMonth[arguments[1]-1];
}

/*
	화면의 오늘, 어제, 이번주, 지난주, 이번달, 지난달 기간 구하기 
*/
function setDuration(obj, start, end) {

	var flag = obj.value;

	// 기간선택이면...
	if(flag == "CM01601") {
		start.value = "";
		end.value = "";
	}
	// 오늘이면...
	if(flag == "CM01602") {
		start.value = getCurrentDate("/");
		end.value = getCurrentDate("/");
	}
	// 어제이면...
	if(flag == "CM01603") {
		var tmpDate = getCurrentTime();
		start.value = shiftDate(tmpDate, 0, 0, -1, "/");
		end.value = shiftDate(tmpDate, 0, 0, -1, "/");
	}
	// 최근한주이면...
	if(flag == "CM01604") {
		start.value = shiftDate(getCurrentTime(), 0, 0, -7, "/");
		end.value = getCurrentDate("/");
	}
	// 이번주이면...
	if(flag == "CM01605") {
	    var now = new Date();
	    var day = now.getDay(); //일요일=0,월요일=1,화요일=2,수요일=3,목요일=4,금요일=5,토요일=6
	    
		start.value = shiftDate(getCurrentTime(), 0, 0, -1 * day, "/");
		end.value = shiftDate(getCurrentTime(), 0, 0, 6-day, "/");
	}
	// 지난주이면...
	if(flag == "CM01606") {
	    var now = new Date();
	    var day = now.getDay() + 7; //일요일=0,월요일=1,화요일=2,수요일=3,목요일=4,금요일=5,토요일=6
	    
		start.value = shiftDate(getCurrentTime(), 0, 0, -1 * day, "/");
		end.value = shiftDate(getCurrentTime(), 0, 0, 6-day, "/");
	}
	// 최근한달이면...
	//if(flag == "") {
	//	start.value = shiftDate(getCurrentTime(), 0, -1, 0, "/");
	//	end.value = getCurrentDate("/");
	//}
	// 이번달이면...
	if(flag == "CM01607") {
	    var now = new Date();
	    var day = now.getDate();
		var last_day = daysPerMonth(now.getYear(), now.getMonth()+1);
		
		start.value = shiftDate(getCurrentTime(), 0, 0, -1 * (day - 1), "/");
		end.value = shiftDate(getCurrentTime(), 0, 0, last_day-day, "/");
	}
	// 지난달이면...
	if(flag == "CM01608") {
	    var now = new Date();
	    var day = now.getDate();
		var last_day = daysPerMonth(now.getYear(), now.getMonth());
		
		start.value = shiftDate(getCurrentTime(), 0, -1, -1 * (day - 1), "/");
		end.value = shiftDate(getCurrentTime(), 0, -1, last_day-day, "/");
	}
	/*******************************************************/
	//charles추가끝
}

/*
	front 주문/배송조회 - 검색 기간(1주일, 15일, 1달, 3달) 구하기 
*/
function setOrderSearchDate(term) {
	var startDate;
	if(term == "7day"){
		startDate = shiftDate(getCurrentTime(), 0, 0, -7, "");
	}else if(term == "15day"){
		startDate = shiftDate(getCurrentTime(), 0, 0, -15, "");
	}else if(term == "1month"){
		startDate = shiftDate(getCurrentTime(), 0, -1, 0, "");
	}else if(term == "3month"){
		startDate = shiftDate(getCurrentTime(), 0, -3, 0, "");
	}
	var endDate = toFormatString(getCurrentTime(), "");
	
	$('startYear').value = startDate.substr(0,4);
    $('startMonth').value = startDate.substr(4,2);
    $('startDay').value = startDate.substr(6,2);
	
	$('endYear').value = endDate.substr(0,4);
    $('endMonth').value = endDate.substr(4,2);
    $('endDay').value = endDate.substr(6,2);
}

/*
	front 주문/배송조회 - 검색 기간(1주일, 15일, 1달, 3달) 구하기 - NO.2 (김성엽 victor@uzen.net 추가)
*/
function setOrderSearchDate1(term, start, end) {

	// 1주일 이면...
	if(term == "7day") {
		start.value = shiftDate(getCurrentTime(), 0, 0, -7, "/");
		end.value = getCurrentDate("/");
	}
	// 15일 이면...
	if(term == "15day") {
		start.value = shiftDate(getCurrentTime(), 0, 0, -15, "/");
		end.value = getCurrentDate("/");
	}
	// 1개월 이면...
	if(term == "1month") {
	    start.value = shiftDate(getCurrentTime(), 0, -1, 0, "/");
		end.value = getCurrentDate("/");
	}
	// 3개월 이면...
	if(term == "3month") {
	    start.value = shiftDate(getCurrentTime(), 0, -3, 0, "/");
		end.value = getCurrentDate("/");
	}
	
	/*******************************************************/
}
