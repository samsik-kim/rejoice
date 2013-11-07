
function progress(messge,x_size,y_size,div){
	var new_messge = "";

	new_messge = uriEncode1(messge)
	new_messge = uriEncode2(messge)

	modal = window.showModalDialog("./dialog.jsp?messge="+new_messge+"&div="+div,"","dialogWidth:"+x_size+"px; dialogHeight:"+y_size+"px; scroll:1; help:1; status:0");
	return modal;
}


function uriEncode1(data) { 
	if(data != "") {
		var encdata = '';
		datas = data.split('%');
		for(i=0;i<datas.length;i++) {
			if(i==0){
				encdata = datas[i];
			}else{
				encdata += encodeURIComponent("%")+datas[i];
			}
		}
	}
	return encdata;
}

function uriEncode2(data) {
	if(data != "") {
		var encdata = '';
		
		var datas = data.split('&');
		for(i=0;i<datas.length;i++) {
			if(i==0){
				encdata = datas[i];
			}else{
				encdata += encodeURIComponent("&")+datas[i];
			}
		}
		datas = encdata.split('+');
		for(i=0;i<datas.length;i++) {
			if(i==0){
				encdata = datas[i];
			}else{
				encdata += encodeURIComponent("+")+datas[i];
			}
		}
	}else{
		encdata = "";
	}
	return encdata;
}