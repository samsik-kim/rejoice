<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.omp.admin.adminmgr.auth.model.AdSession" %>
<%@ page import="com.omp.admin.adminmgr.auth.model.AdMenuMgrauth" %>
<%@ page import="com.omp.admin.common.Constants" %>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<script type="text/javascript">

	var makeCheckedMenuIds = function() {
		
		var ids = "";
		$("input:checkbox[name=checkMenuId]:checked").each(function () {
			ids += "," + $(this).attr("value");
		});
		
		return ids.substring(1);
	};

	var funcUpdateAdMgrauth = function() {
		
		if ($("input:checkbox[name=checkMenuId]:checked").length == 0) {
			alert("<gm:string value="jsp.adminmgr.account.popAccountAuth.msg.none_select"/>");
			return;
		}
		
		var selectedMenuId =  makeCheckedMenuIds();

		document.frm.checkMenuId.value = selectedMenuId;
		//alert("선택된 권한:" + document.frm.checkMenuId.value);

		if(confirm("<gm:string value='jsp.adminmgr.account.popAccountAuth.msg.confirm_mod'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			document.frm.submit();
		}

	};

</script>	

<script type="text/javascript">

	function CheckTree(myName) {
		this.myName = myName;
	    this.root = null;
	    this.countAllLevels = false;
	    this.checkFormat = '(%n% checked)';
	    this.evtProcessed = navigator.userAgent.indexOf('Safari') > -1 ? 'safRtnVal' : 'returnValue';
		CheckTree.list[myName] = this;
	};
	CheckTree.list = [];

	// Called onload, this sets up a reference to the 'root' node and hides sublevels.
	CheckTree.prototype.init = function() {
		with (this) {
			if (!document.getElementById) return;
			root = document.getElementById('tree-' + myName);
			if (root) {
				var lists = root.getElementsByTagName('ul');
				for (var ul = 0; ul < lists.length; ul++) {
					// Hide all UL sublevels under the root node, and assign them a toggle/click methods.
					lists[ul].style.display = 'none';
					lists[ul].treeObj = this;
					lists[ul].setBoxStates = setBoxStates;

					var fn = new Function('e', 'this.setBoxStates(e)');
					// Grr, workaronud another Safari bug.
					if (lists[ul].addEventListener && navigator.vendor != 'Apple Computer, Inc.') {
						lists[ul].addEventListener('click', fn, false);
					} else 
						lists[ul].onclick = fn;
					}

					// Now do a similar event capture setup for the 'root' node.
					root.treeObj = this;
					root.setBoxStates = setBoxStates;
					if (root.addEventListener && navigator.vendor != 'Apple Computer, Inc.') {
						root.addEventListener('click', new Function('e', myName + '.click(e)'), false);
					} else
						root.onclick = new Function('e', myName + '.click(e)');
					// Trigger a quick state update, to set the counters for each level.
					root.setBoxStates({}, true, true);

					// Now go through and assign plus/plus-last classes to the appropriate <LI>s.
					var nodes = root.getElementsByTagName('li');
					for (var li = 0; li < nodes.length; li++) {
						if (nodes[li].id.match(/^show-/)) {
							nodes[li].className = (nodes[li].className=='last' ? 'plus-last' : 'plus');
						}
					}
				}
			}
		}; // with (this)


		// Called on click of the entire tree, this manages visibility of sublevels.
		CheckTree.prototype.click = function(e) {
		with (this) {
			e = e || window.event;
			var elm = e.srcElement || e.target;

			// Has a checkbox been clicked, but not processed by a lower level onclick event?
			// If so, one of the 'root' checkboxes must have been clicked.
			// We must therefore trigger a manual 'downwards route' for that tree to update it.
			if (!e[evtProcessed] && elm.id && elm.id.match(/^check-(.*)/)) {
				var tree = document.getElementById('tree-' + RegExp.$1);
				if (tree) tree.setBoxStates(e, true, false);
			}

			while (elm) {
				// Dont' do expand/collapses for clicks on checkboxes, or nested within menus.
				if (elm.tagName.match(/^(input|ul)/i)) break;
				// Show/hide the menu element that matches the source id="show-xxx" tag and quit.
				if (elm.id && elm.id.match(/^show-(.*)/)) {
					var targ = document.getElementById('tree-' + RegExp.$1);
					if (targ.style) {
						var col = (targ.style.display == 'none');
						targ.style.display = col ? 'block' : 'none';
						// Swap the class of the <span> tag inside, maintaining "-last" state if applied.
						elm.className = elm.className.replace(col?'plus':'minus', col?'minus':'plus');
					}
					break;
				}
				// Otherwise, continue looping up the DOM tree, looking for a match.
				elm = elm.parentNode;
			}
		}
	};

	function setBoxStates(e, routingDown, countOnly) { with (this) {
		// Opera <7 fix... don't perform any actions in those browsers.
		if (!this.childNodes) return;

		e = e || window.event;
		var elm = e.srcElement || e.target;

		// Initial check: if the parent checkbox for a tree level has been clicked, trigger a
		// pre-emptive downwards route within that tree, and set returnValue to true so that we
		// don't repeat it or mess with any of the original checkbox's siblings.
		if (elm && elm.id && elm.id.match(/^check-(.*)/) && !routingDown && !e[treeObj.evtProcessed]) {
			var refTree = document.getElementById('tree-' + RegExp.$1);
			if (refTree) {
				refTree.setBoxStates(e, true, countOnly);
				e[treeObj.evtProcessed] = true;
			}
		}

		// Some counter and reference variables.
		var allChecked = true, boxCount = 0, subBoxes = null;
		// Get the name of this branch and see if the source element has id="check-xxxx".
		var thisLevel = this.id.match(/^tree-(.*)/)[1];
		var parBox = document.getElementById('check-' + thisLevel);

		// Loop through all children of all list elements inside this UL tag.
		for (var li = 0; li < childNodes.length; li++) {
			for (var tag = 0; tag < childNodes[li].childNodes.length; tag++) {
				var child = childNodes[li].childNodes[tag];
				if (!child) continue;
				if (child.tagName && child.type && child.tagName.match(/^input/i) && child.type.match(/^checkbox/i)) {
					// Set this box's state depending on its parent state, if we're routing downwards.
					if (routingDown && parBox && elm && elm.id && elm.id.match(/^check-/) && !countOnly)
						child.checked = parBox.checked;
					// Count the checked boxes directly under this level.
					allChecked &= child.checked;
					if (child.checked) boxCount++;
				}
				// And route this event to sublevels, to update their nodes, during a downwards route.
				if (child.tagName && child.tagName.match(/^ul/i) && (!e[treeObj.evtProcessed] || routingDown))
					child.setBoxStates(e, true, countOnly);
			}
		}

		// Once we've routed the event to all sublevels, set the 'returnValue' to true, so that
		// upper levels don't re-trigger a downwards route. This is a bit of a hack, admittedly :).
		if (!routingDown) e[treeObj.evtProcessed] = true;

		// Next, set the parent parBox state depending if all checkboxes in this menu are checked.
		// Of course, we don't set its state if it's the source of the event!
		if (parBox && parBox != elm && !countOnly)
			parBox.checked = allChecked;

		// If "countAllLevels" is set, overwrite the previous one-level-only count.
		if (treeObj.countAllLevels) {
			boxCount = 0;
			var subBoxes = this.getElementsByTagName('input');
			for (var i = 0; i < subBoxes.length; i++)
				if (subBoxes[i].checked)
					boxCount++;
		}

		// Either way, assign the counted value to the id="count-xxx" page element.
		var countElm = document.getElementById('count-' + thisLevel);
		if (countElm) {
			while (countElm.firstChild)
				countElm.removeChild(countElm.firstChild);
			if (boxCount)
				countElm.appendChild(document.createTextNode(treeObj.checkFormat.replace('%n%', boxCount)));
		}
	}};


	// Calls the init() function of any active trees on page load, and backup previous onloads.
	var chtOldOL = window.onload;
	window.onload = function() {
		if (chtOldOL) chtOldOL();
		for (var i in CheckTree.list) CheckTree.list[i].init();
	};

</script>

<script type="text/javascript">
<!--
	var checkmenu = new CheckTree('checkmenu');
//-->
</script>

<title>운영자 권한 설정</title>

<link rel="stylesheet" href="/adminpoc/${ThreadSession.serviceLocale.language}/css/common.css" type="text/css">
<link rel="stylesheet" href="/adminpoc/${ThreadSession.serviceLocale.language}/css/base.css" type="text/css">
<link rel="stylesheet" href="/adminpoc/${ThreadSession.serviceLocale.language}/css/popup.css" type="text/css">

<style>
ul.checktree, ul.checktree ul {
list-style-type: none;
padding: 0;
margin: 0;
font: 12px sans-serif;
}

ul.checktree li {
background: url(${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/tree/node.gif) no-repeat;
margin: 0;
padding: 0 0 0 16px;
cursor: default;
}
ul.checktree li.last {
background-image: url(${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/tree/node-last.gif);
}
ul.checktree li.plus {
background-image: url(${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/tree/plus.gif);
}
ul.checktree li.plus-last {
background-image: url(${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/tree/plus-last.gif);
}
ul.checktree li.minus {
background-image: url(${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/tree/minus.gif);
}
ul.checktree li.minus-last {
background-image: url(${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/tree/minus-last.gif);
}

ul.checktree span.count {
text-indent: 5pt;
color: #999;
font-style: italic;
}
</style>

</head>
<body class="popup" onload="">

	<form name="frm" action="./popUpdateAdMgrAuthList.omp" method="post">
		<input type="hidden" name="selectedAccount" value="${param.selectedAccount}"/>
		<input type="hidden" name="checkMenuId" value="" />
	</form>

	<div id="popup_area_440">
   		<div class="box02">
        	<p>권한설정 아이디: <span class="point2">${selectedAccount}</span></p>
        </div>
        <br />
	<form action="javascript:void(0)">
       	<ul id="tree-checkmenu" class="checktree">
<%
	Map map = (Map)request.getAttribute("authMenuMap");
 	String choiceId = request.getParameter("selectedAccount");

	int r = 0;
	if(map != null) {

		List depth2List = (List)map.get("ROOT");

		    for(int p=0;p<depth2List.size();p++){
			    AdMenuMgrauth authMgr2 = (AdMenuMgrauth)depth2List.get(p);
				String menuCode2 = authMgr2.getMenuId();        
		        String menuName2 = authMgr2.getMenuNm();
		        String mgrId2    = authMgr2.getMgrId();
		        boolean isCheck2 = false;

		        if(mgrId2 != null && mgrId2.length() > 0) {
		            if(choiceId!=null && choiceId.equals(mgrId2)) {
		                isCheck2 = true;
		            }
		        }

		        if(p == (depth2List.size()-1)) {
		            out.println("	<li class=\"last\" id=\"show-" + menuCode2 + "\">");
		              if(isCheck2 == true){
		                  out.println("	  <input id=\"check-" + menuCode2 + "\" name=\"checkMenuId\" type=\"checkbox\" checked=\"true\" value=\""+menuCode2+"\" />" + menuName2);
		              }else{
		                  out.println("	  <input id=\"check-" + menuCode2 + "\" name=\"checkMenuId\" type=\"checkbox\" value=\""+menuCode2+"\" />" + menuName2);    
		              }
			          out.println("	  <span id=\"count-" + menuCode2 + "\" class=\"count\"></span>");	    	
			          out.println("	  <ul id=\"tree-" + menuCode2 + "\" >");		   
		        } else {
			        out.println("	<li id=\"show-" + menuCode2 + "\">");
			          if(isCheck2 == true){
			          	out.println("	  <input id=\"check-" + menuCode2 + "\" name=\"checkMenuId\"  type=\"checkbox\" checked=\"true\" value=\""+menuCode2+"\"  />" + menuName2);
			          }else{
			              out.println("	  <input id=\"check-" + menuCode2 + "\" name=\"checkMenuId\"  type=\"checkbox\" value=\""+menuCode2+"\"  />" + menuName2);
			          }
			          out.println("	  <span id=\"count-" + menuCode2 + "\" class=\"count\"></span>");
			          out.println("	  <ul id=\"tree-" + menuCode2 + "\" >");
		        }

		        List depth3list = (List)map.get(menuCode2);
		    	r++;
				for(int i=0;i<depth3list.size();i++) {
					AdMenuMgrauth authMgr3 = (AdMenuMgrauth)depth3list.get(i);
				    String menuCode3 = authMgr3.getMenuId();
				    String menuName3 = authMgr3.getMenuNm();
				    String url3      = authMgr3.getPageUrl();
				    String mgrId3    = authMgr3.getMgrId();

			        boolean isCheck3 = false;
			        if(mgrId3 !=null && mgrId3.length()>0) {
			            if(choiceId!=null && choiceId.equals(mgrId3)){
			            	isCheck3 = true;
			            }
			        }

				    if(i == (depth3list.size()-1)){
				        out.println("		<li  class=\"last\" id=\"show-" + menuCode3 + "\">");
				    }else{
				    	out.println("		<li id=\"show-" + menuCode3 + "\">");
				    }
				    if(isCheck3 == true){
				    	out.println("		<input id=\"check-" + menuCode3 + "\" name=\"checkMenuId\"  type=\"checkbox\" checked=\"true\" value=\""+menuCode3+"\" />" + menuName3);
				    }else{
				        out.println("		<input id=\"check-" + menuCode3 + "\" name=\"checkMenuId\"  type=\"checkbox\"  value=\""+menuCode3+"\" />" + menuName3);
				    }
				    out.println("		<span id=\"count-" + menuCode3 + "\" class=\"count\"></span>");
				    out.println("		<ul id=\"tree-" + menuCode3 + "\" >");
				    
				    List depth4list = (List)map.get(menuCode3);
				    r++;
					for(int j=0;j<depth4list.size();j++){			    
						AdMenuMgrauth authMgr4 = (AdMenuMgrauth)depth4list.get(j);
					    String menuCode4 = authMgr4.getMenuId();
					    String menuName4 = authMgr4.getMenuNm();
					    
					    String auth4url = authMgr4.getPageUrl();
					    String mgrId4    = authMgr4.getMgrId();
				        boolean isCheck4 = false;
				        if(mgrId4 !=null && mgrId4.length()>0){
				            if(choiceId!=null && choiceId.equals(mgrId4)){
				                isCheck4 = true;
				            }
				        }
				        StringBuffer sb = new StringBuffer();

					    if(j == (depth4list.size()-1)){
					        if(isCheck4 == true){
					            out.println("			<li class=\"last\"><input type=\"checkbox\"  name=\"checkMenuId\" checked=\"true\" value=\""+menuCode4+"\"  />"+menuName4+"</li>");
					        }else{
					        	out.println("			<li class=\"last\"><input type=\"checkbox\"  name=\"checkMenuId\" value=\""+menuCode4+"\"  />"+menuName4+"</li>");
					        }
					    }else{
					        if(isCheck4 == true){
					            out.println("			<li><input type=\"checkbox\"  name=\"checkMenuId\"  checked=\"true\" value=\""+menuCode4+"\"  />"+menuName4+"</li>");
					        }else{
					    		out.println("			<li><input type=\"checkbox\"  name=\"checkMenuId\" value=\""+menuCode4+"\"  />"+menuName4+"</li>");
					        }
					    }
						r++;

					}// 4depth
					out.println("		</ul>");
					out.println("		</li>");
				}//3depth
				
				if(p==(depth2List.size()-1)){
					out.println("    </li>");
				}else{
					out.println("    </ul>");
					out.println("    </li>");
				}
				
		    }//2depth
		

	} // end if

%>
<!--
 			<li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 회원관리</li>
	        <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 컨텐츠관리</li>
            <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 고객지원</li>
            <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 기술지원</li>
            <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_m.gif" alt="" /></a><input type="checkbox" /> 상품정산관리 (1 checked)</li>
            	<ul class="pl30">
                	<li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 상품관리 (1 checked)</li>
                    <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 광고관리</li>
                    <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> mISSD</li>
                    <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 판매현황</li>
                    <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 부분 상품 판매현황</li>
                    <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 정산현황</li>
                    <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 세금계산서 발행관리</li>
                    <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 후불결제수납관리</li>
                    <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 개발자 POC 쿠폰관리</li>
                    <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 운영자 POC 전체대상 쿠폰관리</li>
                    <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 운영자 POC 특정대상 쿠폰관리</li>
                    <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> T store Cash 관리</li>
                </ul>
            <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 통계관리</li>
            <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 운영자관리</li>
            <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> BP관리</li>
            <li><a href="#"><img src="${pageContext.request.contextPath }/${ThreadSession.serviceLocale.language}/images/icon_p.gif" alt="" /></a><input type="checkbox" /> 단말POC관리</li>
-->
        </ul>
	</form>

		<div class="pop_btn" >
			<a class="btn_s" href="javascript:funcUpdateAdMgrauth();"><strong>저장</strong></a>
			<a class="btn_s" href="javascript:self.close();"><strong>취소</strong></a>
		</div>
	</div>
    <!-- //contents -->
</body>
</html>
