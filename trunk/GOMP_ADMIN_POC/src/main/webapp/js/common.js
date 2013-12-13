/* selectbox */
function selectBox(containerID) {

	var container = document.getElementById(containerID);
	var selectBoxTitle = container.getElementsByTagName("span").item(0);
	var selectBoxList = container.getElementsByTagName("ul").item(0);
	var selectBoxArrow = container.getElementsByTagName("div").item(2);

	selectBoxArrow.style.cursor ="pointer";
	selectBoxList.style.display = "none";

	selectBoxTitle.onclick = function() {
		if (selectBoxList.style.display == "none") {
			selectBoxList.style.display = "block";
		}
		else {
			selectBoxList.style.display = "none";
		}
	}
	selectBoxArrow.onclick = function() {
		if (selectBoxList.style.display == "none") {
			selectBoxList.style.display = "block";
		}
		else {
			selectBoxList.style.display = "none";
		}
	}
	selectBoxList.onclick = function() {
		selectBoxList.style.display = "none";
	}
	selectBoxTitle.onblur = function() {
		selectBoxList.style.display = "block";
	}
	selectBoxList.onblur = function() {
		selectBoxList.style.display = "none";
	}
/* mouse over action --> click event
	selectBoxTitle.onmouseout = function() {
		selectBoxList.style.display = "none";
	}
	selectBoxList.onmouseout = function() {
		selectBoxList.style.display = "none";
	}
*/
}
/* detail page, point */
function selectBoxPoint(containerID) {

	var container = document.getElementById(containerID);
	var selectBoxTitle = container.getElementsByTagName("div").item(1);
	var selectBoxList = container.getElementsByTagName("ul").item(0);
	var selectBoxArrow = container.getElementsByTagName("div").item(2);

	selectBoxArrow.style.cursor ="pointer";
	selectBoxList.style.display = "none";

	selectBoxTitle.onclick = function() {
		selectBoxList.style.display = "block";
	}
	selectBoxArrow.onclick = function() {
		selectBoxList.style.display = "block";
	}
	selectBoxList.onclick = function() {
		selectBoxList.style.display = "none";
	}
	selectBoxTitle.onblur = function() {
		selectBoxList.style.display = "block";
	}
	selectBoxList.onblur = function() {
		selectBoxList.style.display = "none";
	}
}
function mov_point(point) {
	document.getElementById('imgPoint').src = point+".gif";
	document.getElementById('imgPoint').alt = point+"";
}
/* */

// img over/out
function imageOver(imgs) {
	imgs.src = imgs.src.replace("off.gif", "on.gif");
}
function imageOut(imgs) {
	imgs.src = imgs.src.replace("on.gif", "off.gif");
}

// png 파일 투명 처리 
function setPng24(obj) {
    obj.width=obj.height=1;
    obj.className=obj.className.replace(/\bpng24\b/i,'');
    obj.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(src='"+ obj.src +"',sizingMethod='image');"
	//obj.src=''; 
    obj.src='../images/common/blank.gif';
    return '';
}

/* Calendar */
function openCal(openName) { 
	var pop = document.getElementById(openName);
	var objLayer = document.getElementById("calendar");
	var objLayerContents = document.getElementById("calendar_area");
	pop.style.display="block";
	//ie6에서 셀렉트 박스 위로 보이기
		if(navigator.userAgent.indexOf("MSIE 6")>-1 && navigator.userAgent.indexOf("MSIE 7")<0){
			if (!document.getElementById("IE6Iframe")){
				var ie6_ifm = document.createElement("iframe");
				ie6_ifm.setAttribute("id","IE6Iframe");
				ie6_ifm.style.position = "absolute";
				ie6_ifm.style.opacity = "0";
				ie6_ifm.style.filter = "alpha(opacity=0)";
				ie6_ifm.style.zindex = "-1";
				ie6_ifm.style.left = "17";
				ie6_ifm.style.top = "0";
				ie6_ifm.style.width = objLayerContents.offsetWidth;
				ie6_ifm.style.height = objLayerContents.offsetHeight;

				objLayer.appendChild(ie6_ifm);
			}
		}
}

/* Layer */
function showPop(popName, frmLeft, frmTop) { 
	var pop = document.getElementById(popName);
	var objLayer = document.getElementById("layer_body");
	var objLayerContents = document.getElementById("laymid");

	if(frmLeft == "undefined" || frmLeft == undefined){ frmLeft = 0; }
	if(frmTop == "undefined" || frmTop == undefined){ frmTop = 0; }

	pop.style.display="block";
	//ie6에서 셀렉트 박스 위로 보이기
	if(navigator.userAgent.indexOf("MSIE 6")>-1 && navigator.userAgent.indexOf("MSIE 7")<0){
			if (!document.getElementById("IE6Iframe")){
				var ie6_ifm = document.createElement("iframe");
				ie6_ifm.setAttribute("id","IE6Iframe");
				ie6_ifm.style.position			= "absolute";
				ie6_ifm.style.opacity			= "0";				
				ie6_ifm.style.filter				= "alpha(opacity=0)";
				ie6_ifm.style.zindex			= "-1";
				ie6_ifm.style.left					= frmLeft;
				ie6_ifm.style.top					= frmTop;
				if(objLayerContents != null){
					ie6_ifm.style.width			= objLayerContents.offsetWidth;
					ie6_ifm.style.height			= objLayerContents.offsetHeight;
				}
				objLayer.appendChild(ie6_ifm);
			}
		}
	//alert(objLayer.innerHTML);
}

// value값 없애기 
function clearText(y){
	if (y.defaultValue==y.value)
		y.value = "";
} 

// faq
function faq(num) {
	var obj = document.getElementById("answer" + num);
	var obj2 = document.getElementById("question" + num);

	if (obj.style.display == "block"){
		for (i=1; i<7 ; i++) {
			document.getElementById("answer"+i).style.display = "none";
			document.getElementById("question"+i).style.display = "block";
		}

		obj.style.display = "none";
		obj2.style.display = "block";
	}
	else {
		for (i=1; i<7 ; i++) {
			document.getElementById("answer"+i).style.display = "none";
			document.getElementById("question"+i).style.display = "block";
		}
		obj.style.display = "block";
		obj2.style.display = "none";
	}
}
/* Layer */
function showPop(popName) { 
	var pop = document.getElementById(popName);
	pop.style.display="block";
}

function closePop(popName,closeName){
	showPop(popName);
	var pop = document.getElementById(popName);
	var close = document.getElementById(closeName);
	pop.style.display="none";
}

/* layer swap */
function tabSwap(sw) {
 for (i = 1; i < 7; i++) { // 2009-06-16 갯수 감소
  if (sw == i) {  
   document.getElementById('tab01_0'+i).style.display='';
  } else {
   document.getElementById('tab01_0'+i).style.display='none';
  }
 }
}

/* 090506 RHJ 추가 */
	function select(num) {
		var obj = document.getElementById("select" + num);
		if (obj.style.display == "none"){
			obj.style.display = "block";
		} else {
			obj.style.display = "none";
		}
	}

function swf_include(swfUrl,swfWidth,swfHeight,swfName,flashVars){
	// 플래시 코드 정의
	var flashStr=
	"<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0' width='"+swfWidth+"' height='"+swfHeight+"' id='"+swfName+"' align='middle' />"+
	"<param name='allowScriptAccess' value='always' />"+
	"<param name='allowFullScreen' value='true' />"+
	"<param name='movie' value='"+swfUrl+"' />"+
	"<param name='FlashVars' value='"+flashVars+"' />"+
	"<param name='loop' value='false' />"+
	"<param name='wmode' value='transparent' />"+
	"<param name='quality' value='high' />"+
	"<param name='scale' value='noscale' />"+
	"<param name='bgcolor' value='#ffffff' />"+
	"<embed src='"+swfUrl+"' FlashVars='"+flashVars+"' wmode='transparent' quality='best' menu='false' width='"+swfWidth+"' height='"+swfHeight+"' name='"+swfName+"' align='top' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' />"+
	"</object>";

	// 플래시 코드 출력
	document.write(flashStr);
}

/* flash re write */
function IE_HtmlRewrite(objParent) {
	if (window.ActiveXObject && objParent) {
		objParent.innerHTML = objParent.innerHTML;
	}
}

function flashHeightResize(heightNum){ 
	document.getElementById('lnb_flash').style.height = heightNum + "px";
}

/* popup */

function popup() {
	window.open('popup_02.html','popup2','left=0, top=0, width=345,height=155');
}
function popup1() {
	window.open('pop_up_evaluation_completed.html','popup2','left=0, top=0, width=420,height=155');
}

/* 2009-07-01 RHJ 추가 */
function balloon() {
	var obj = document.getElementById("balloonBox");
	var view = document.getElementById("view");
	var close = document.getElementById("close");
	if (obj.style.display == "none") {
		obj.style.display = "block";
		view.style.display = "none";
		close.style.display = "";
	} else {
		obj.style.display = "none";
		view.style.display = "";
		close.style.display = "none";
	}
}

/* 2009-12-21 */
//<![CDATA[ 
	//토글 스크립트
	function toggle(elem) {
		var clickElem = document.getElementById("clickEvent");
		clickElem.onclick = function hidden() {
			var target = document.getElementById(elem);
			target.style.display = ( target.style.display != 'none') ? 'none' : '';
		}
	}
//]]> 

function popup_center(url,w, h, s, r) 
{

width=screen.width;
height=screen.height;

x=(width/2)-(w/2);
y=(height/2)-(h/2);

opt = "left=" + x + ", top=" + y + ", width=" + w + ", height=" + h;
opt = opt + ", toolbar=no,location=no,directories=no,status=no,menubar=no";
opt = opt + ",scrollbars=" + s;
opt = opt + ",resizable=" + r;
window.open(url, "_blank", opt);

}

function settlement_popup_center(url,w, h, s, r) 
{

width=screen.width;
height=screen.height;

x=(width/2)-(w/2);
y=(height/2)-(h/2);

opt = "left=" + x + ", top=" + y + ", width=" + w + ", height=" + h;
opt = opt + ", toolbar=no,location=no,directories=no,status=no,menubar=no";
opt = opt + ",scrollbars=" + s;
opt = opt + ",resizable=" + r;
window.open(url, "popup", opt);

}
