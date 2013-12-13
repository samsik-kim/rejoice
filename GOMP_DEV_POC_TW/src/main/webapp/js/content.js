//Images_rollover
function imgChg(obj){
	var img = null;
	var imgName = null;
	
	
	
	if(obj != null){
		for(i=0; i<obj.childNodes.length; i++){
			if(obj.childNodes[i].nodeName == "IMG"){
				img = obj.childNodes[i];
			}
		}
	}

	if(img != null){
		imgName = img.src;
		if(imgName.indexOf("_on") > -1){
			img.src = imgName.replace("_on.gif", ".gif");
		}else{
			img.src = imgName.replace(".gif", "_on.gif");
		}
	}
}

// Login Text Focus
function idhideBG(f){
	f.className = "idhide";
}

function idshowBG(f){
	if(f.value == ""){
		f.className = "idshow";
	}
}

function pwhideBG(f){
	f.className = "pwhide";
}

function pwshowBG(f){
	if(f.value == ""){
		f.className = "pwshow";
	}
}