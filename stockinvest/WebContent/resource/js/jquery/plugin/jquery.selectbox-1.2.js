var Browser = {
    a : navigator.userAgent.toLowerCase()
}

Browser = {
    ie : /*@cc_on true || @*/ false,
    ie6 : Browser.a.indexOf('msie 6') != -1,
    ie7 : Browser.a.indexOf('msie 7') != -1,
    ie8 : Browser.a.indexOf('msie 8') != -1,
    opera : !!window.opera,
    safari : Browser.a.indexOf('safari') != -1,
    safari3 : Browser.a.indexOf('applewebkit/5') != -1,
    mac : Browser.a.indexOf('mac') != -1,
    chrome : Browser.a.indexOf('chrome') != -1,
    firefox : Browser.a.indexOf('firefox') != -1
}

function whatKindOfBrowser() {
	if (Browser.chrome) {
		return "chrome";
	} else if (Browser.ie6) {
		return "ie6";
	} else if (Browser.ie7) {
		return "ie7";
	} else if (Browser.ie8) {
		return "ie8";
	} else if (Browser.opera) {
		return "opera";
	} else if (Browser.safari) {
		return "safari";
	} else if (Browser.safari3) {
		return "safari3";
	} else if (Browser.mac) {
		return "mac";
	} else if (Browser.firefox) {
		return "firefox";
	} else {
		return false;
	}
}

UISelectM = function() {

	this.listeners = new Array();

	this.selectboxvalue = [];
	this.selectboxtext = [];
	this.selectIndex = null;
}

UISelectM.prototype = {

	addListener: function(listener) {
		this.listeners[this.listeners.length] = listener;
	},

	notify: function() {
		for (var i = 0 ; i < this.listeners.length ; i++) {
			this.listeners[i].changed();
		}
	},

	optionClick: function(idx) {
		this.selectIndex = idx;
		this.notify();
	}
}

UISelectC = function(model,view) {
	this.model = model;
	this.view = view;
	this.model.addListener(this);
	this.view.setControl(this);
}

UISelectC.prototype = {

	changed: function() {
		this.view.optionClickResult();
	},
	optionClick: function(idx) {
		this.model.optionClick(idx);
	}
}

UISelectV = function(selectbox,w,h,f) {

	var bs = whatKindOfBrowser();
	var _this = this;
	if(!w) w=100;
	if(!h) h=14;
	if(!f) f=f;

	/** setup **/
	this.showcnt = 11;
	this.item_height = 14;
	this.item_width = w;
	this.item_fontSize = f;

	this.style_paddingtop = 1;
	this.style_paddingbottom = 2;
	this.style_paddingleft = 6;
	
	this.style_color = "#333333";
	this.style_bcolor = "#ffffff";

	this.style_rollover_color = "#333333";
	this.style_rollover_bcolor = "#f1f1f1";

	this.item_width -= 2;
	this.selectbox = document.getElementById(selectbox);
	this.selectboxp = this.selectbox.parentNode;
	this.BaseDiv = document.createElement('div');
	this.ParentDivLayer = document.createElement('div');

	this.selectboxvalue = [];
	this.selectboxtext = [];
	this.selectIndex = 0;

	if(this.selectbox.options.length < this.showcnt) {
		this.showcnt = this.selectbox.options.length;
		this.ParentDivLayer.style.overflowY = "hidden";
	}
	else {
		this.ParentDivLayer.style.overflowY = "auto";
	}

	/** status **/
	this.rollover = [false,false,false];
	
	this.BaseDiv.style.width = this.item_width - this.style_paddingleft +"px";
	this.BaseDiv.style.height = h+"px";
	this.BaseDiv.innerHTML = this.selectbox.options[0].text;
	this.BaseDiv.style.fontSize = this.item_fontSize +"px";
	this.BaseDiv.style.backgroundColor = "#ffffff";
	this.BaseDiv.style.backgroundImage = "url('../images/common/down.gif')";
	this.BaseDiv.style.backgroundRepeat = "no-repeat";
	this.BaseDiv.style.cursor = 'pointer';

	var padTop = parseInt( ( this.item_height + this.style_paddingtop + this.style_paddingbottom - 4 ) / 2 );
	var padLeft = parseInt( this.item_width - 12 );
	this.BaseDiv.style.backgroundPosition = padLeft + "px "+ padTop+ "px";

	this.BaseDiv.style.border = "1px solid #bebebe";
	/* ie6 패딩조절 */
	if( bs == "ie6" ) {
		this.BaseDiv.style.paddingTop = "1";
		this.BaseDiv.style.paddingBottom = "0";
	}
	else {
		this.BaseDiv.style.paddingTop = this.style_paddingtop+"px";
		this.BaseDiv.style.paddingBottom = this.style_paddingbottom+"px";
	}

	/* ie6 상단마진조절 */
	if( bs == "ie6" ) { this.BaseDiv.style.marginTop = "1"; }

	/* ie7 상단마진조절 */
	if( bs == "ie7" ) { this.BaseDiv.style.marginTop = "1"; }

	this.BaseDiv.style.paddingLeft = this.style_paddingleft+"px";

	jQuery(this.BaseDiv).bind('mouseover',function() {
		_this.rollover[0] = true;
	}).bind('mouseout',function() {
		_this.rollover[0] = false;
	});

	this.selectboxp.appendChild(this.BaseDiv);

	for(var i=0; i<this.selectbox.options.length; i++) {

		this.selectboxvalue[i] = this.selectbox.options[i].value;
		this.selectboxtext[i] = this.selectbox.options[i].text;
		if(this.selectbox.options[i].selected) this.selectedIndex = i;

		var oDiv = document.createElement('div');
		oDiv.style.letterSpacing = "0px";
		oDiv.style.width = this.item_width+"px";
		oDiv.style.height = this.item_height+"px";
		oDiv.style.fontSize = this.item_fontSize+"px";
		oDiv.style.paddingTop = this.style_paddingtop+"px";
		oDiv.style.paddingBottom = this.style_paddingbottom+"px";
		oDiv.style.cursor = 'pointer';
		/* ie6 임시삽입 */
		if( bs == "ie6" ) oDiv.style.paddingBottom = "0px";
		oDiv.style.paddingLeft = this.style_paddingleft+"px";
		oDiv.setAttribute("idx",i);
		oDiv.innerHTML = this.selectbox.options[i].text;

		/** 롤오버부분 **/
		jQuery(oDiv).bind('mouseover',function() {
			jQuery(this).css('backgroundColor',_this.style_rollover_bcolor);
			jQuery(this).css('color',_this.style_rollover_color);
			_this.rollover[2] = true;
			return false;
		}).bind('mouseout',function() {
			jQuery(this).css('backgroundColor',_this.style_bcolor);
			jQuery(this).css('color',_this.style_color);
			_this.rollover[2] = false;
			return false;
		}).bind('click',function() {
		
		/** 클릭 부분 **/
			_this.optionClick(jQuery(this).attr('idx'));
		});

		this.ParentDivLayer.appendChild(oDiv);

	}
	
	this.ParentDivLayer.style.color = this.style_color;
	this.ParentDivLayer.style.backgroundColor = this.style_bcolor;
	this.ParentDivLayer.style.display = "none";
	this.ParentDivLayer.style.position = "absolute";
	this.ParentDivLayer.style.marginTop = "-1px";
	this.ParentDivLayer.style.width = this.item_width + "px";
	this.ParentDivLayer.style.height = (this.item_height + this.style_paddingtop +  this.style_paddingbottom) * this.showcnt + "px";
	this.ParentDivLayer.style.overflowX = "hidden";
	this.ParentDivLayer.style.border = "1px solid #bebebe";
	this.ParentDivLayer.style.zIndex = "10";
	
	jQuery(this.ParentDivLayer).bind('mouseover',function() {
		_this.rollover[1] = true;
	}).bind('mouseout',function() {
		_this.rollover[1] = false;
	});

	this.selectboxp.appendChild(this.ParentDivLayer);

	this.select_hidden = document.createElement('input');

	this.select_hidden.setAttribute('type','hidden');
	this.select_hidden.setAttribute('name',this.selectbox.name);
	this.select_hidden.setAttribute('id',this.selectbox.id);
	this.select_hidden.setAttribute('value',this.selectboxvalue[this.selectedIndex]);

	this.selectboxp.appendChild(this.select_hidden);

	jQuery(this.BaseDiv).bind('click',function() {

		if(_this.ParentDivLayer.style.display == 'block') _this.ParentDivLayer.style.display = "none";
		else {
			_this.ParentDivLayer.style.display = "block";
			_this.ParentDivLayer.scrollTop = ( _this.item_height + _this.style_paddingtop +  _this.style_paddingbottom) * _this.selectIndex;

			/** 클릭 색상 적용 **/
			for(var i=0; i<_this.ParentDivLayer.childNodes.length; i++) {
				var oDiv = _this.ParentDivLayer.childNodes[i];
				oDiv.style.backgroundColor = _this.style_bcolor;
				oDiv.style.color = _this.style_color;
			};

			var oDiv = _this.ParentDivLayer.childNodes[_this.selectIndex];
			oDiv.style.backgroundColor = _this.style_rollover_bcolor;
			oDiv.style.color = _this.style_rollover_color;

		}
	});

	/** 다른곳 클릭시 사라짐 **/
	jQuery(document).bind('click',function() {
		if(!_this.rollover[0] && !_this.rollover[1] && !_this.rollover[2]) {
			_this.ParentDivLayer.style.display = "none";
		}
	});

	//this.rolloverChk = setInterval( Event.bindAsListener(this.chksec,this) ,100);
	this.selectboxEvent = this.selectbox.getAttribute('onchange');

	this.selectboxp.removeChild(this.selectbox);
	
}

UISelectV.prototype = {

	setControl: function(controller) {
		this.controller = controller;
	},

	optionClick: function(idx) {

		this.controller.model.selectboxvalue = this.selectboxvalue;
		this.controller.model.selectboxtext = this.selectboxtext;
		this.controller.model.selectIndex = this.selectIndex;

		this.controller.optionClick(idx);
	},

	optionClickResult: function() {

		var idx = this.controller.model.selectIndex;

		var oDiv = this.ParentDivLayer.childNodes[idx];
		oDiv.style.backgroundColor = this.style_rollover_bcolor;
		oDiv.style.color = this.style_rollover_color;

		this.BaseDiv.innerHTML = this.selectboxtext[idx];

		this.ParentDivLayer.style.display = "none";
		this.select_hidden.setAttribute('value',this.selectboxvalue[idx]);

		if(this.selectIndex != this.controller.model.selectIndex) {

			try {
				this.selectboxEvent();
			}
			catch(e) {
				try {
					eval(this.selectboxEvent);
				}
				catch(e){}
			}


			/** onchange 이벤트 **/
/*
			var bs = whatKindOfBrowser();
			if( bs == "ie6" || bs == "ie7" ) {
				this.selectboxEvent();
			}
			else {
				eval(this.selectboxEvent);
			}
*/
		}

		this.selectIndex = this.controller.model.selectIndex;

	},

	chksec: function() {
//		document.getElementById("ccc").innerHTML = this.rollover;
	},

	selectbyValue: function(val) {
		for(var i=0; i<this.selectboxvalue.length; i++) {
			if(this.selectboxvalue[i] == val) {
				this.optionClick(i);
			}
		}
	}
}