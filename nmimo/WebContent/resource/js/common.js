/**
 * Common Class 공통으로 사용될 기능들을 구현한 샘플 클래스. 스트링 NULL 체크 / NameSpace / Import
 * 
 * @author: 김융규
 */
var Common = {

	/**
	 * 컨텍스트 패스.
	 */
	contextPath : "",
		

	/**
	 * 사용자 아이디.
	 */
	memberId : "",

	/**
	 * JSON 호출 결과 코드
	 */
	JSON_CALL_RESULT_CODE : {
		SUCCESS : 1,
		FAIL : 0
	},

	/**
	 * 다이얼로그 타이틀
	 */
	DIALOG_TITLE : {
		NOTICE : '알림',
		ALERT : '경고',
		INFO : '정보',
		CONFIRM : '확인'
	},

	/**
	 * 메세지
	 */
	MESSAGE : {
		BLOCK : '<h4>잠시만 기다려 주세요.</h4>'
	},

	/**
	 * 스트링 널 체크.
	 * 
	 * @param {String}
	 *            str
	 */
	isEmpty : function(str) {
		if (str == null)
			return true;
		return !(str.replace(/(^\s*)|(\s*$)/g, ""));
	},

	/**
	 * 네임스페이스 등록.
	 * 
	 * @param {String}
	 *            ns 네임스페이스
	 */
	registNamespace : function(ns) {
		var nsParts = ns.split(".");
		var root = window;

		for ( var i = 0; i < nsParts.length; i++) {
			if (typeof root[nsParts[i]] == "undefined") {
				root[nsParts[i]] = new Object();
			}
			root = root[nsParts[i]];
		}
	},

	importJS : function(jsFile) {
		$.ajax( {
			type : "GET",
			url : "/js/" + jsFile,
			async : false,
			dataType : "script"
		});
	},

	/**
	 * 팝업 윈도우 화면의 중간에 위치.
	 * 
	 * @param {String}
	 *            targetUrl 팝업 윈도우의 내용을 구성하기 위한 호출 URL
	 * @param {String}
	 *            windowName 팝업 윈도우의 이름
	 * @param {Object}
	 *            properties 팝업 윈도우의 속성(넓이, 높이, x/y좌표)
	 */
	centerPopupWindow : function(targetUrl, windowName, properties) {
		var childWidth = properties.width;
		var childHeight = properties.height;
		var childTop = (screen.height - childHeight) / 2 - 50; // 아래가 가리는 경향이
																// 있어서 50을 줄임
		var childLeft = (screen.width - childWidth) / 2;
		var popupProps = "width=" + childWidth + ",height=" + childHeight
				+ ", top=" + childTop + ", left=" + childLeft;
		if (properties.scrollBars == "YES") {
			popupProps += ", scrollbars=yes";
		}

		var popupWin = window.open(targetUrl, windowName, popupProps);
		if(popupWin && !popupWin.closed)
		{
			popupWin.focus();
		}
		//popupWin.focus();
	},

	/**
	 * Alert 다이얼로그 띄움.
	 * 
	 * @param {String}
	 *            title 타이틀
	 * @param {String}
	 *            msg 내용
	 */
	alertDialog : function(title, msg) {
		var dialogTag = "<div title=\"" + title + "\">" + msg + "</div>";
		$(dialogTag).dialog( {
			modal : true,
			buttons : {
				'확인' : function() {
					$(this).dialog('destroy').remove();
				}
			}
		});
	},

	/**
	 * 업로드 하려는 파일의 이름 사이즈 체크.
	 * 
	 * @param {String}
	 *            uploadFileName 파일명
	 * @param {String}
	 *            limitSize
	 */
	checkUploadFileNameSize : function(uploadFileName, limitSize) {
		if (!Common.isEmpty(uploadFileName)) {
			var index = uploadFileName.lastIndexOf("\\");
			if (index > -1) {
				uploadFileName = uploadFileName.substring(index + 1);
			}

			if (uploadFileName.getBytes() > limitSize) {
//				alert("파일 명이 너무 길어요.");
				Common.alertDialog("알림", "파일 명이 너무 길어요.");
				return false;
			}

			return true;
		} else {
			return false;
		}
	},

	/**
	 * toString
	 */
	toString : function() {
		return "Common Object";
	},

	/**
	 * X,Y 좌표의 구글 맵 화면을 띄움.
	 * 
	 * @param {integer}
	 *            x GPS X 위치
	 * @param {integer}
	 *            y GPS Y 위치
	 * @param {String}
	 *            message 내용
	 */
	viewMap : function(context, x, y, message) {
		Common.centerPopupWindow(context + '/bbs/locationMap.do?x=' + x + "&y="
				+ y + "&message=" + encodeURIComponent(message), 'mapPopup', {
			width : 500,
			height : 300
		});
		return false;
	},

	/**
	 * 두 날자 사이의 일수를 반환
	 * 
	 * @param {String}
	 *            fromDate 시작일자 (yyyy-mm-dd)
	 * @param {String}
	 *            toDate 종료일자 (yyyy-mm-dd)
	 * @return {integer} 두 일자 사이의 일수
	 */
	intervalDate : function(fromDate, toDate) {
		var FORMAT = "-";

		// FORMAT을 포함한 길이 체크
		if (fromDate.length != 10 || toDate.length != 10)
			return null;

		// FORMAT이 있는지 체크
		if (fromDate.indexOf(FORMAT) < 0 || toDate.indexOf(FORMAT) < 0)
			return null;

		// 년도, 월, 일로 분리
		var start_dt = fromDate.split(FORMAT);
		var end_dt = toDate.split(FORMAT);

		// 월 - 1(자바스크립트는 월이 0부터 시작하기 때문에...)
		// Number()를 이용하여 08, 09월을 10진수로 인식하게 함.
		start_dt[1] = (Number(start_dt[1]) - 1) + "";
		end_dt[1] = (Number(end_dt[1]) - 1) + "";

		var from_dt = new Date(start_dt[0], start_dt[1], start_dt[2]);
		var to_dt = new Date(end_dt[0], end_dt[1], end_dt[2]);

		return (to_dt.getTime() - from_dt.getTime()) / 1000 / 60 / 60 / 24;
	},

	/**
	 * Alert 다이얼로그 띄운 후 정해진 작업을 완료한 후 팝업창을 닫는다.
	 * 
	 * @param {String}
	 *            msg 내용
	 * @param {String}
	 *            procArray 작업 목록 배열
	 */
	alertProcDialog : function(msg, procArray) {
		var dialogTag = "<div title=\"" + Common.DIALOG_TITLE.NOTICE + "\">"
				+ msg + "</div>";
		$(dialogTag).dialog( {
			modal : true,
			buttons : {
				'확인' : function() {
					$(this).dialog('destroy').remove();
					for ( var i = 0; i < procArray.length; i++) {
						eval(procArray[i]);
					}
				}
			}
		});
	},

	/**
	 * Alert 다이얼로그 띄운 후 취소, 확인 버튼을 출력 후 확인 버튼을 클릭할 경우에는 proc를 실행한다.
	 * 
	 * @param {String}
	 *            msg 내용
	 * @param {String}
	 *            proc 작업
	 */
	choiceDialog : function(msg, proc) {
		var dialogTag = "<div title=\"" + Common.DIALOG_TITLE.CONFIRM + "\">"
				+ msg + "</div>";
		$(dialogTag).dialog( {
			modal : true,
			buttons : {
				'취소' : function() {
					$(this).dialog('destroy').remove();
				},
				'확인' : function() {
					if( typeof(proc)=='function' ){
						proc();
					}else{
						eval(proc);
					}
					$(this).dialog('destroy').remove();
				}
			}
		});
		return false;
	},
	
	/**
	 * Ajax Inner 처리결과에 대한 Message Alert
	 * 
	 * @param transport : "{resultCode:value, resultMessage:value}"
	 * 'ERROR' : 처리가 실패한 경우
	 */
	alertJsonResultMsg : function(transport, element){
		try{
			
			var resultCode = "";
	        var result = eval("(" + transport + ")");
	        
	        resultCode = result.resultCode;
	        alert(transport + "\n" + resultCode);
	        if(resultCode == "ERROR"){
//	        	alert(result.resultMessage);
	        	Common.alertDialog("Alert", result.resultMessage);
	        	return false;
	        }
		}catch(e){
			element.html(transport);
            return true;
        }
	}	
};

/**
 * 공통리스트 Inner
 */
function pageLoadAjaxListInner(form, element, url){	
	var form = $("#"+form);
	var element = $("#"+element);
	var data = form.serialize();
	$.ajax({
		url: url,
		type: "POST",
		data: data,
		async : false,		
		cache : false,
		success: function(transport){
			Common.alertJsonResultMsg(transport, element);
	        return false;
		},
//		error : function( jqXHR, textStatus, errorThrown ) {
//			alert( jqXHR.status );
//			alert( jqXHR.statusText );
//			alert( jqXHR.responseText );
//			alert( jqXHR.readyState );
//		}
		error: function(xhr, textStatus, errorThrown){
			Common.alertDialog("Alert", "리스트 정보 조회중 장애가 발생하였습니다.");
		}			
	});
	$.unblockUI();
}

/**
 * Json 등록
 */
function pageSubmitJSONData(form, url, before, result){ // form id, url
	var form = $("#"+form);
	var options = {
		url: url,
		type: "POST",
		dataType: "json",
		beforeSubmit: before,  // pre-submit callback
		success: 	  result,
		error: function(json){
//			alert("데이타 저장중 장애가 발생하였습니다. <br />이와 같은 문제가 계속 발생시 해당 관리자에게 문의하십시오.");
			Common.alertDialog("Alert", "데이타 저장중 장애가 발생하였습니다. <br />이와 같은 문제가 계속 발생시 해당 관리자에게 문의하십시오.");
		}
	};
	form.attr("target", "");
	form.ajaxSubmit(options);
}

function submitSave(frm, url, success) {
		var options = {
			type: "POST",
	        dataType:  "json",
	        async	: true,		
			cache	: false,
	        success: success
		};
		
		var frm = $('#'+frm);
		frm.attr("action",url);
		frm.ajaxSubmit(options);
}

//
$(function() {
	$.datepicker.regional['ko']= {
			  closeText:'닫기',
			  prevText:'이전달',
			  nextText:'다음달',
			  currentText:'오늘',
			  monthNames:['1월(JAN)','2월(FEB)','3월(MAR)','4월(APR)','5월(MAY)','6월(JUM)','7월(JUL)','8월(AUG)','9월(SEP)','10월(OCT)','11월(NOV)','12월(DEC)'],
			  monthNamesShort:['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
			  dayNames:['일','월','화','수','목','금','토'],
			  dayNamesShort:['일','월','화','수','목','금','토'],
			  dayNamesMin:['일','월','화','수','목','금','토'],
			  weekHeader:'Wk',
			  dateFormat:'yy-mm-dd',
			  firstDay:0,
			  isRTL:false,
			  showMonthAfterYear:true,
			  yearSuffix:''
	};
	$.datepicker.setDefaults($.datepicker.regional['ko']);
	$.datepicker.setDefaults({dateFormat: 'yy-mm-dd'});
	$.datepicker.setDefaults({showOn:'both', buttonImage:'/resource/images/common/icon_cal.gif', buttonImageOnly:true});
	
	// readonly input prevent backspace
	$(":input[readonly] ").each(function(){
	 $(this).keydown(function(event){
	  if (event.keyCode == '8') { 
	       event.preventDefault(); 
	     }
	 });
	});
});