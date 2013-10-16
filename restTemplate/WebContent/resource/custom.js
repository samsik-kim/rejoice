var Rejoice = {
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
		 * Alert 다이얼로그 띄운 후 취소, 확인 버튼을 출력 후 확인 버튼을 클릭할 경우에는 proc를 실행한다.
		 * 
		 * @param {String}
		 *            msg 내용
		 * @param {String}
		 *            proc 작업
		 */
		choiceDialog : function(msg, proc) {
			var dialogTag = "<div title=\"" + Rejoice.DIALOG_TITLE.CONFIRM + "\">"
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
//		        	alert(result.resultMessage);
		        	Rejoice.alertDialog("Alert", result.resultMessage);
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
			Rejoice.alertJsonResultMsg(transport, element);
	        return false;
		},
//		error : function( jqXHR, textStatus, errorThrown ) {
//			alert( jqXHR.status );
//			alert( jqXHR.statusText );
//			alert( jqXHR.responseText );
//			alert( jqXHR.readyState );
//		}
		error: function(xhr, textStatus, errorThrown){
			Rejoice.alertDialog("Alert", "리스트 정보 조회중 장애가 발생하였습니다.");
		}			
	});
//	$.unblockUI();
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
			Rejoice.alertDialog("Alert", "데이타 저장중 장애가 발생하였습니다. <br />이와 같은 문제가 계속 발생시 해당 관리자에게 문의하십시오.");
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