var target;
var stime;
var calendar;

document.writeln("<div id='minical' name='minical' onmouseover='Calendar_Over()' onmouseout='Calendar_Out()' style='background-color: #ECE9D8; background: buttonface; margin:2px; border: 1px solid buttonshadow; width:160px; display:none; position:absolute; z-index:2'>");
document.writeln("<iframe id='Cal_iFrame' name='minical' width='160px' height='130px' src='../js/cal.htm' scrolling='no' frameborder='no'></iframe>");
document.writeln("</div>");

function Calendar_Over() {
	window.clearTimeout(stime);
}

function Calendar_Out() {
	stime=window.setTimeout("calendar.style.display='none';", 200);
}

function Calendar_Click(e) {
	cal_Day = e.title;
	if (cal_Day.length > 5) {
		target.value = cal_Day
	}	
	calendar.style.display='none';
}

function showCalendar(objName, btn, type, seper) {
	if(seper == undefined || seper == "") seper = "/";
	var obj = document.getElementById(objName);
	var now = obj.value.split(seper);
	target = obj;

	var top = document.body.offsetTop + GetObjectTop(btn);
	var left = document.body.offsetLeft + GetObjectLeft(btn);
	//alert(document.body.clientLeft + " | " + document.body.offsetLeft);
	calendar = document.getElementById("minical");
	
	calendar.style.top = parseInt(top + btn.offsetHeight) + "px";
	calendar.style.left = left+"px";
	calendar.style.display = "block";
	
	//alert(top + " | " + left + " | " + btn.offsetHeight + "\n" + calendar.style.left + " | " + calendar.style.offsetLeft + " | " + calendar.style.posLeft + " | " + calendar.style.pixelLeft);

	var ifrm = document.getElementById("Cal_iFrame").contentWindow;
	
	if(type == "M") {
		if (now.length == 2) ifrm.Show_cal_M(now[0],now[1], seper);
		else {
			now = new Date();
			ifrm.Show_cal_M(now.getFullYear(), now.getMonth()+1, seper);
		}
	} else if(type == "P") {
		if (now.length == 2) ifrm.Show_cal_P(now[0],now[1], seper);
		else {
			now = new Date();
			var checkP = now.getMonth()+1;
			if(checkP == 1 || checkP == 2 || checkP == 3){
				checkP = 1;
			}else if(checkP == 4 || checkP == 5 || checkP == 6){
				checkP = 2;
			}else if(checkP == 7 || checkP == 8 || checkP == 9){
				checkP = 3;
			}else if(checkP == 10 || checkP == 11 || checkP == 12){
				checkP = 4;
			}
			ifrm.Show_cal_P(now.getFullYear(), checkP, seper);
		}
	} else {
		if (now.length == 3)
			ifrm.Show_cal(now[0],now[1],now[2], seper);
		else {
			now = new Date();
			ifrm.Show_cal(now.getFullYear(), now.getMonth()+1, now.getDate(), seper);
		}
	}
}

// iFrame 사이즈 조절
function resizeIframe(width, height) {
	var ifrm = document.getElementById("Cal_iFrame");
	ifrm.style.width = width+"px";
	ifrm.style.height = height+"px";
}

function GetObjectTop(obj) {
	if (obj.offsetParent == document.body)
		return obj.offsetTop;
	else
		return obj.offsetTop + GetObjectTop(obj.offsetParent);
}

function GetObjectLeft(obj) {
	if (obj.offsetParent == document.body)
		return obj.offsetLeft;
	else
		return obj.offsetLeft + GetObjectLeft(obj.offsetParent);
}
