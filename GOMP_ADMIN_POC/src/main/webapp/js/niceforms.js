/*#############################################################
Name: Niceforms
Version: 1.0
Author: Lucian Slatineanu
URL: http://www.badboy.ro/

Feel free to use and modify but please provide credits.
#############################################################*/

//Global Variables
var niceforms = document.getElementsByTagName('form'); var inputs = new Array(); var labels = new Array(); var radios = new Array(); var radioLabels = new Array(); var checkboxes = new Array(); var checkboxLabels = new Array(); var texts = new Array(); var textareas = new Array(); var selects = new Array(); var selectText = "please select"; var agt = navigator.userAgent.toLowerCase(); this.ie = ((agt.indexOf("msie") != -1) && (agt.indexOf("opera") == -1)); var hovers = new Array(); var buttons = new Array(); var isMac = new RegExp('(^|)'+'Apple'+'(|$)');

//Theme Variables - edit these to match your theme
var selectRightSideWidth = 21;
var selectLeftSideWidth = 8;
var selectAreaHeight = 21;
var selectAreaOptionsOverlap = 2;
var imagesPath = "images/";

//Initialization function - if you have any other 'onload' functions, add them here
function init() {
	if(!document.getElementById) {return false;}
	getElements();
	separateElements();
	replaceRadios();
	replaceCheckboxes();
}

//getting all the required elements
function getElements() {
	var re = new RegExp('(^| )'+'niceform'+'( |$)');
	for (var nf = 0; nf < document.getElementsByTagName('form').length; nf++) {
		if(re.test(niceforms[nf].className)) {
			for(var nfi = 0; nfi < document.forms[nf].getElementsByTagName('input').length; nfi++) {inputs.push(document.forms[nf].getElementsByTagName('input')[nfi]);}
			for(var nfl = 0; nfl < document.forms[nf].getElementsByTagName('label').length; nfl++) {labels.push(document.forms[nf].getElementsByTagName('label')[nfl]);}
		}
	}
}
//separating all the elements in their respective arrays
function separateElements() {
	var r = 0; var c = 0; var t = 0; var rl = 0; var cl = 0; var tl = 0; var b = 0;
	for (var q = 0; q < inputs.length; q++) {
		if(inputs[q].type == 'radio') {
			radios[r] = inputs[q]; ++r;
			for(var w = 0; w < labels.length; w++) {if(labels[w].htmlFor == inputs[q].id) {if(inputs[q].checked) {labels[w].className = "chosen";} radioLabels[rl] = labels[w]; ++rl;}}
		}
		if(inputs[q].type == 'checkbox') {
			checkboxes[c] = inputs[q]; ++c;
			for(var w = 0; w < labels.length; w++) {if(labels[w].htmlFor == inputs[q].id) {if(inputs[q].checked) {labels[w].className = "chosen";} checkboxLabels[cl] = labels[w]; ++cl;}}
		}
	}
}
function replaceRadios() {
	for (var q = 0; q < radios.length; q++) {
		//move radios out of the way
		radios[q].className = "outtaHere";
		//create div
		var radioArea = document.createElement('div');
		if(radios[q].checked) {radioArea.className = "radioAreaChecked";} else {radioArea.className = "radioArea";}
		radioArea.style.left = findPosX(radios[q]) + 'px';
		radioArea.style.top = findPosY(radios[q]) + 'px';
		radioArea.style.margin = "1px";
		radioArea.id = "myRadio" + q;
		//insert div
		radios[q].parentNode.insertBefore(radioArea, radios[q]);
		//assign actions
		radioArea.onclick = new Function('rechangeRadios('+q+')');
		radioLabels[q].onclick = new Function('rechangeRadios('+q+')');
		if(!this.ie) {radios[q].onfocus = new Function('focusRadios('+q+')'); radios[q].onblur = new Function('blurRadios('+q+')');}
		radios[q].onclick = radioEvent;
	}
	return true;
}
function focusRadios(who) {
	var what = document.getElementById('myRadio'+who);
	what.style.border = "1px dotted #333"; what.style.margin = "0";
	return false;
}
function blurRadios(who) {
	var what = document.getElementById('myRadio'+who);
	what.style.border = "0"; what.style.margin = "1px";
	return false;
}
function checkRadios(who) {
	var what = document.getElementById('myRadio'+who);
	others = document.getElementsByTagName('div');
	for(var q = 0; q < others.length; q++) {if((others[q].className == "radioAreaChecked")&&(others[q].nextSibling.name == radios[who].name)) {others[q].className = "radioArea";}}
	what.className = "radioAreaChecked";
}
function changeRadios(who) {
	if(radios[who].checked) {
		for(var q = 0; q < radios.length; q++) {if(radios[q].name == radios[who].name) {radios[q].checked = false; radioLabels[q].className = "";}} 
		radios[who].checked = true; radioLabels[who].className = "chosen";
		checkRadios(who);
	}
}
function rechangeRadios(who) {
	if(!radios[who].checked) {
		for(var q = 0; q < radios.length; q++) {if(radios[q].name == radios[who].name) {radios[q].checked = false; radioLabels[q].className = "";}}
		radios[who].checked = true; radioLabels[who].className = "chosen";
		checkRadios(who);
	}
}
function radioEvent(e) {
	if (!e) var e = window.event;
	if(e.type == "click") {for (var q = 0; q < radios.length; q++) {if(this == radios[q]) {changeRadios(q); break;}}}
}
function replaceCheckboxes() {
	for (var q = 0; q < checkboxes.length; q++) {
		//move checkboxes out of the way
		checkboxes[q].className = "outtaHere";
		//create div
		var checkboxArea = document.createElement('div');
		if(checkboxes[q].checked) {checkboxArea.className = "checkboxAreaChecked";} else {checkboxArea.className = "checkboxArea";}
		checkboxArea.style.left = findPosX(checkboxes[q]) + 'px';
		checkboxArea.style.top = findPosY(checkboxes[q]) + 'px';
		checkboxArea.style.margin = "1px";
		checkboxArea.id = "myCheckbox" + q;
		//insert div
		checkboxes[q].parentNode.insertBefore(checkboxArea, checkboxes[q]);
		//asign actions
		checkboxArea.onclick = new Function('rechangeCheckboxes('+q+')');
		if(!isMac.test(navigator.vendor)) {checkboxLabels[q].onclick = new Function('changeCheckboxes('+q+')');}
		else {checkboxLabels[q].onclick = new Function('rechangeCheckboxes('+q+')');}
		if(!this.ie) {checkboxes[q].onfocus = new Function('focusCheckboxes('+q+')'); checkboxes[q].onblur = new Function('blurCheckboxes('+q+')');}
		checkboxes[q].onkeydown = checkEvent;
	}
	return true;
}
function focusCheckboxes(who) {
	var what = document.getElementById('myCheckbox'+who);
	what.style.border = "1px dotted #333"; what.style.margin = "0";
	return false;
}
function blurCheckboxes(who) {
	var what = document.getElementById('myCheckbox'+who);
	what.style.border = "0"; what.style.margin = "1px";
	return false;
}
function checkCheckboxes(who, action) {
	var what = document.getElementById('myCheckbox'+who);
	if(action == true) {what.className = "checkboxAreaChecked";}
	if(action == false) {what.className = "checkboxArea";}
}
function changeCheckboxes(who) {
	if(checkboxLabels[who].className == "chosen") {
		checkboxes[who].checked = true;
		checkboxLabels[who].className = "";
		checkCheckboxes(who, false);
	}
	else if(checkboxLabels[who].className == "") {
		checkboxes[who].checked = false;
		checkboxLabels[who].className = "chosen";
		checkCheckboxes(who, true);
	}
}
function rechangeCheckboxes(who) {
	var tester = false;
	if(checkboxLabels[who].className == "chosen") {
		tester = false;
		checkboxLabels[who].className = "";
	}
	else if(checkboxLabels[who].className == "") {
		tester = true;
		checkboxLabels[who].className = "chosen";
	}
	checkboxes[who].checked = tester;
	checkCheckboxes(who, tester);
}
function checkEvent(e) {
	if (!e) var e = window.event;
	if(e.keyCode == 32) {for (var q = 0; q < checkboxes.length; q++) {if(this == checkboxes[q]) {changeCheckboxes(q);}}} //check if space is pressed
}

//Useful functions
function findPosY(obj) {
	var posTop = 0;
	while (obj.offsetParent) {posTop += obj.offsetTop; obj = obj.offsetParent;}
	return posTop;
}
function findPosX(obj) {
	var posLeft = 0;
	while (obj.offsetParent) {posLeft += obj.offsetLeft; obj = obj.offsetParent;}
	return posLeft;
}

window.onload = init;