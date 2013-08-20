function addoverimage() {
	try {
		$("#header li").each(function (idx, obj) {

			var	tobj;
			var	tanker;
			var	timg;
			var	orgsrc;
			var	onsrc;
			
			tobj	= $(obj);
			tanker	= $("a", tobj);
			timg	= $("img", tobj);
			orgsrc	= timg.attr("src");
			onsrc	= orgsrc.replace(".gif", "_on.gif");
			if (tobj.hasClass("on")) {
				timg.attr("src", onsrc);
			} else {
				tanker.mouseover(function () {
					timg.attr("src", onsrc);
				});
				tanker.mouseout(function () {
					timg.attr("src", orgsrc);
				});
			}
		});
	} catch(e){}
}