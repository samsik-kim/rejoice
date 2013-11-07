<%@page language="java" contentType="text/html; charset=UTF-8"%>      
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core"%>
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>
<head>
<script type="text/javascript" src="<c:url value="/js/common/niceforms.js"/>"></script>
<script type="text/javascript" src="<c:url value="/js/common/stringUtil.js"/>"></script>
<script type="text/javascript">
	var ctgrIndex=0;
	var control01="free";
	var control02="free";
	
	$(function() {
		$("#qnaCtgr01 li").each(function(){
			$(this).click(function(){
				var selValue = this.id;
				$(this).addClass("on");
				$.post(env.contextPath+"/qna/getAjaxSecondCategoryList.omp", {"selectFaqCategoryType":selValue}, function(data) {
					$("#qnaCtgr02").find('li').remove();
					makeOption("qnaCtgr02", data);
					}, "json");
			
				var mId = this.id;
				var temp =$(this).text(); 
				document.getElementById('ctgr01text').value = rtrim($(this).text());
				$("#qnaCtgr01 li").each(function(){
					if(mId != this.id){
						$(this).removeClass("on");
					}
				});
			});
		});
	});
	
	function rtrim(value) {  
		return value.replace(/^\s+|\s+$/g,"");  
	} 

	function ctgr2Click(){
		$("#qnaCtgr02 li").each(function(){
			$(this).click(function(){
				$(this).addClass("on");
				var mId = this.id;
				document.getElementById('ctgr02text').value = $(this).text();
				$("#qnaCtgr02 li").each(function(){
					if(mId != $(this).attr('id')){
						$(this).removeClass("on");
					}
				});
				document.getElementById('ctgr02text').value = rtrim($(this).text());
			});
		});
	}
	
	function ctgr1Click(){
		$("#qnaCtgr01 li").each(function(){
			$(this).click(function(){
				var defText = "<gm:string value='jsp.community.qna.qna_ctgr.text.ctgrchk'/>";
				$(this).addClass("on");
				var mId = this.id;
				$("#qnaCtgr01 li").each(function(){
					if(mId != $(this).attr('id')){
						$(this).removeClass("on");
					}
				});
				$("#qnaCtgr02").find('li').remove();
				var option = $("<li>" + defText + "</li>").attr("class", "on");
				$("#qnaCtgr02").append(option);
				document.getElementById('ctgr01text').value = rtrim($(this).text());
				return;
			});
		});
	}

	function makeOption(target, data) {
		if(data.status=='true'){
			alert("2Depth Category Error.");
		}
		var result = data.result;
		var defText = "<gm:string value='jsp.community.qna.qna_ctgr.text.ctgrchk'/>";
		if(result.length<1){
			var option = $("<li>" + defText + "</li>").attr("class", "on");
			$("#" + target).append(option);
			return;
		}
		var option = $("<li/>").attr("value", "");
		option.attr(($.browser.msie ? 'label':'text'), defText);
		for(var i in result) {
			$("#" + target).append("<li name='ctgrDepth2' id='" + result[i].ctgrCd + "' value='"+result[i].displayOd+"' onClick='javascript:ctgr2Click();' >" + result[i].ctgrNm + "</li>");
		}
	}
		
	function func_up01(){
		$("#qnaCtgr01 li").each(function(){
			if($(this).hasClass("on")){
				var selectedIndex = $(this).index();
				//alert("$(this).index();=>"+$(this).index());
				var selectedId = this.id;
				//alert("this.id==>"+this.id);
				var prevIndex = parseInt(selectedIndex);
				//alert("parseInt(selectedIndex)=>"+parseInt(selectedIndex));
				var prevId;
				$("#qnaCtgr01 li").each(function(){
					if($(this).index()==prevIndex-1){
						prevId = this.id;
					}
				});
				//alert("prevId=>"+prevId);
				//var prevId = $("li[name=ctgrDepth1]:nth-child("+prevIndex+")").attr('id');
				//alert("$('li[name=ctgrDepth1]:nth-child("+prevIndex+")').attr('id')=>"+$("li[name=ctgrDepth1]:nth-child("+prevIndex+")").attr('id'));
				$(this).insertBefore("#"+prevId);
				//alert("$(this).insertBefore('#'+prevId)=>"+$(this).insertBefore("#"+prevId));
			}
    	});
	}
	
	function func_down01(){
		$("#qnaCtgr01 li").each(function(){
			if($(this).hasClass("on")){
				var selectedIndex = $(this).index();
				//alert("$(this).index()=>"+$(this).index());
				var selectedId = this.id;
				//alert("this.id=>"+this.id);
				var nextIndex = parseInt(selectedIndex + 2);
				//alert("parseInt(selectedIndex + 2)=>"+parseInt(selectedIndex + 2));
				var nextId;
				$("#qnaCtgr01 li").each(function(){
					if($(this).index()==nextIndex-1){
						nextId = this.id;
					}
				});
				//alert("nextId=>"+nextId);
				//var nextId = $("li[name=ctgrDepth1]:nth-child("+nextIndex+")").attr('id');
				$(this).insertAfter("#"+nextId);
			}
    	});
	}
	
	function func_up02(){
		$("#qnaCtgr02 li").each(function(){
			if($(this).hasClass("on")){
				var selectedIndex = $(this).index();
				var selectedId = this.id;
				var prevIndex = parseInt(selectedIndex);
				var prevId;
				$("#qnaCtgr02 li").each(function(){
					if($(this).index()==prevIndex-1){
						prevId = this.id;
					}
				});
				//var prevId = $("li[name=ctgrDepth2]:nth-child("+prevIndex+")").attr('id');
				$(this).insertBefore("#"+prevId);
			}
    	});
	}
	
	function func_down02(){
		$("#qnaCtgr02 li").each(function(){
			if($(this).hasClass("on")){
				var selectedIndex = $(this).index();
				var selectedId = this.id;
				var nextIndex = parseInt(selectedIndex + 2);
				var nextId;
				$("#qnaCtgr02 li").each(function(){
					if($(this).index()==nextIndex-1){
						nextId = this.id;
					}
				});
				//var nextId = $("li[name=ctgrDepth2]:nth-child("+nextIndex+")").attr('id');
				$(this).insertAfter("#"+nextId);
			}
    	});
	}
	
	function insertCtgr01(){
		control01="block";
		var ex;
		$("#qnaCtgr01 li").each(function(){
			if($(this).hasClass("on")){
				ex = this.id;
			}
		});
		if(control02=='free'||ex.substring(0,4)=='SQNA'){
		var text = document.getElementById('ctgr01text');
		if(!showValidate(document.ctgrQnaForm.ctgr01text, 'default', "")){
			return;
		}
		$("#qnaCtgr01 li").each(function(){
			if($(this).text()=='<gm:string value='jsp.community.qna.qna_ctgr.text.ctgrsavechk'/>'){
				$(this).remove();
			}
		});
		$("#qnaCtgr01").append("<li name='ctgrDepth1' id='ctgrDepth"+ctgrIndex+"' value='' onClick='javascript:ctgr1Click();'>" + text.value + "</li>");
		ctgrIndex++;
		}else{
			alert("<gm:string value='jsp.community.qna.qna_ctgr.msg.1ctgr'/>");
			document.getElementById('ctgr01text').value="";
			control01="free";
		}
	}
	
	function insertCtgr02(){
		control02="block";
		var ex;
		$("#qnaCtgr01 li").each(function(){
			if($(this).hasClass("on")){
				ex = this.id;
			}
		});
		if(control01=='free'||ex.substring(0,4)=='SQNA'){
		var text = document.getElementById('ctgr02text');
		if(!showValidate(document.ctgrQnaForm.ctgr02text, 'default', "")){
			return;
		}
		var ex = null;
		$("#qnaCtgr02 li").each(function(){
			if($(this).text()=='<gm:string value='jsp.community.qna.qna_ctgr.text.ctgrchk'/>'){
				$(this).remove();
			}
			if($(this).text()=='<gm:string value='jsp.community.qna.qna_ctgr.msg.1ctgrchk'/>'){
				alert("<gm:string value='jsp.community.qna.qna_ctgr.msg.1ctgrbtn'/>");
				ex = "exp";
			}
		});
		if(ex!=null){
			return;
		}
		$("#qnaCtgr02").append("<li name='ctgrDepth2' id='ctgrDepth"+ctgrIndex+"' value='' onClick='javascript:ctgr2Click();'>" + text.value + "</li>");
		ctgrIndex++;
		}else{
			alert("<gm:string value='jsp.community.qna.qna_ctgr.msg.2ctgr'/>");
			document.getElementById('ctgr02text').value="";
			control02="free";
		}
	}
	
	function deleteCtgr01(){
		control01="block";
		var ex;
		$("#qnaCtgr01 li").each(function(){
			if($(this).hasClass("on")){
				ex = this.id;
			}
		});
		if(control02=='free'||ex.substring(0,4)=='SQNA'){
		if(confirm("<gm:string value='jsp.community.qna.qna_list.msg.delchk'/>")){
			var check=0;
			var url = document.ctgrQnaForm.url.value;
			$("#qnaCtgr01 li").each(function(){
				if($(this).hasClass("on")){
					$("#qnaCtgr02 li").each(function(){
						if($(this).attr('id')!='dfault'&&$(this).attr('id')!=''){
							check=1;
							return;
						}
					});
					if(check==0){
						if($(this).attr('id').substring(0,5)!='ctgrD'){
							if(document.ctgrQnaForm.delQueNo.value==''){
								document.ctgrQnaForm.delQueNo.value = "#"+ $(this).attr('id');
							}else{
								document.ctgrQnaForm.delQueNo.value = document.ctgrQnaForm.delQueNo.value+"#"+$(this).attr('id');
							}
						}
					$(this).remove();
					document.getElementById('ctgr01text').value="";
					$("#qnaCtgr02").find('li').remove();
					}else{
						alert("<gm:string value='jsp.community.qna.qna_ctgr.msg.2ctgrchk'/>");
					}
					check=0;
				}
			});
		}
		}else{
			alert("<gm:string value='jsp.community.qna.qna_ctgr.msg.1ctgr'/>");
			document.getElementById('ctgr01text').value="";
			control01="free";
		}
	}
	
	function deleteCtgr02(){
		control02="block";
		var ex;
		$("#qnaCtgr02 li").each(function(){
			if($(this).hasClass("on")){
				ex = this.id;
			}
		});
		if(control01=='free'||ex.substring(0,4)=='SQNA'){
		if(confirm("<gm:string value='jsp.community.qna.qna_list.msg.delchk'/>")){
			var clsCd=null;
			$("#qnaCtgr02 li").each(function(){
				if($(this).hasClass("on")){
					if($(this).attr('id').substring(0,5)!='ctgrD'){
						if(document.ctgrQnaForm.delQueNo.value==''){
							document.ctgrQnaForm.delQueNo.value = "#"+ $(this).attr('id');
						}else{
							document.ctgrQnaForm.delQueNo.value = document.ctgrQnaForm.delQueNo.value+"#"+$(this).attr('id');
						}
					}
					$(this).remove();
					document.getElementById('ctgr02text').value="";
				}
			});
		}
		}else{
			alert("<gm:string value='jsp.community.qna.qna_ctgr.msg.2ctgr'/>");
			document.getElementById('ctgr02text').value="";
			control02="free";
		}
	}
	
	function saveCtgr01(){
		//control01="block";
		
		//if(control02=='free'||ex.substring(0,4)=='SQNA'){
		if(confirm("<gm:string value='jsp.community.qna.qna_ctgr.msg.savechk'/>")){
			var url = document.ctgrQnaForm.url.value;
			var ctgrNmlist = null;
			var ctgrCdlist = null;
			$("#qnaCtgr01 li").each(function(){
				
				if(ctgrNmlist==null){
					ctgrNmlist = "#"+ $(this).text();
				}else{
					ctgrNmlist = ctgrNmlist+"#"+ $(this).text();
				}
				if(ctgrCdlist==null){
					ctgrCdlist = "#"+ $(this).attr('id');
				}else{
					ctgrCdlist = ctgrCdlist+"#"+$(this).attr('id');
				}
			});
			
			document.ctgrQnaForm.qnaClsNm.value = ctgrNmlist;
			document.ctgrQnaForm.qnaClsCd.value = ctgrCdlist; 
			if(url == "registerDCtgr.omp"){
				//document.ctgrQnaForm.action = "saveDCtgrQnA.omp";
				var options={ 
						 success:       responseCtgr,
				         url:       	env.contextPath+"/qna/saveDCtgrQnA.omp", 
				         type:      	"post"  
					};
			}else{
				//document.ctgrQnaForm.action = "saveCtgrQnA.omp";
				var options={ 
						 success:       responseCtgr,
				         url:       	env.contextPath+"/qna/saveCtgrQnA.omp", 
				         type:      	"post"  
					};
			}
			$('#ctgrQnaForm').ajaxSubmit(options);
			
		}
		//}else{
		//	alert("<gm:string value='jsp.community.qna.qna_ctgr.msg.1ctgr'/>");
		//	document.getElementById('ctgr01text').value="";
		//	control01="free";
		//}	
	}
	
	function saveCtgr02(){
		//control02="block";
		//if(control01=='free'||ex.substring(0,4)=='SQNA'){
		if(confirm("<gm:string value='jsp.community.qna.qna_ctgr.msg.savechk'/>")){
			var ctgrNmlist = null;
			var ctgrCdlist = null;
			var highCtgrCd = null;
			$("#qnaCtgr02 li").each(function(){
				
				if(ctgrNmlist==null){
					ctgrNmlist = "#"+ $(this).text();
				}else{
					ctgrNmlist = ctgrNmlist+"#"+ $(this).text();
				}
				if(ctgrCdlist==null){
					ctgrCdlist = "#"+ $(this).attr('id');
				}else{
					ctgrCdlist = ctgrCdlist+"#"+$(this).attr('id');
				}
			});
			$("#qnaCtgr01 li").each(function(){
				if($(this).hasClass("on")){
					highCtgrCd = $(this).attr('id');
				}
			});
			document.ctgrQnaForm.highCtgr.value = highCtgrCd;
			document.ctgrQnaForm.qnaClsNm.value = ctgrNmlist;
			document.ctgrQnaForm.qnaClsCd.value = ctgrCdlist;
			var options={ 
					 success:       responseCtgr,
			         url:       	env.contextPath+"/qna/saveCtgrQnA.omp", 
			         type:      	"post"  
				}; 
			$('#ctgrQnaForm').ajaxSubmit(options);
			//document.ctgrQnaForm.action = "saveCtgrQnA.omp";
			//alert("저장되었습니다.");
			//document.ctgrQnaForm.submit(); 
		}
		//}else{
		//	alert("<gm:string value='jsp.community.qna.qna_ctgr.msg.2ctgr'/>");
		//	document.getElementById('ctgr02text').value="";
		//	control02="free";
		//}
	}
	
	function responseCtgr(){
		alert("<gm:string value='jsp.community.qna.qna_details.msg.savere'/>");
		var url = document.ctgrQnaForm.url.value;
		if(url == "registerDCtgr.omp"||url == "deleteDCtgrQnA.omp"||url == "saveDCtgrQnA.omp"){
			document.ctgrQnaForm.action = "registerDCtgr.omp";
			document.ctgrQnaForm.submit();
		}else{
			document.ctgrQnaForm.action = "registerCtgr.omp";
			document.ctgrQnaForm.submit();
		}
	}
	
	function modifyNm01(){
		control01="block";
		var ex;
		$("#qnaCtgr01 li").each(function(){
			if($(this).hasClass("on")){
				ex = this.id;
			}
		});
		if(control02=='free'||ex.substring(0,4)=='SQNA'){
		var modText = document.getElementById('ctgr01text').value;
		if(!showValidate(document.ctgrQnaForm.ctgr01text, 'default', "")){
			return;
		}
		$("#qnaCtgr01 li").each(function(){
			if($(this).hasClass("on")){
				$(this).text(modText);
			}
		});
		}else{
			alert("<gm:string value='jsp.community.qna.qna_ctgr.msg.1ctgr'/>");
			document.getElementById('ctgr01text').value="";
			control01="free";
		}
	}
	
	function modifyNm02(){
		control02="block";
		var ex;
		$("#qnaCtgr02 li").each(function(){
			if($(this).hasClass("on")){
				ex = this.id;
			}
		});
		if(control01=='free'||ex.substring(0,4)=='SQNA'){
		var modText = document.getElementById('ctgr02text').value;
		if(!showValidate(document.ctgrQnaForm.ctgr02text, 'default', "")){
			return;
		}
		$("#qnaCtgr02 li").each(function(){
			if($(this).hasClass("on")){
				$(this).text(modText);
			}
		});
		}else{
			alert("<gm:string value='jsp.community.qna.qna_ctgr.msg.2ctgr'/>");
			document.getElementById('ctgr02text').value="";
			control02="free";
		}
	}
	//-->	
</script>		
</head>
<body>
<s:form id="ctgrQnaForm" name="ctgrQnaForm" theme="simple" method="post" enctype="multipart/form-data">
	<s:hidden name="selectFaqCategoryType"/>
	<s:hidden id="qnaClsNm" name="qnaClsNm"/>
	<s:hidden id="qnaClsCd" name="qnaClsCd"/>
	<s:hidden id="highCtgr" name="highCtgr"/>
	<s:hidden id="delQueNo" name="delQueNo"/>
	<s:hidden id="url" name="url"/>
	<div id="location">
	<c:if test="${url eq 'registerDCtgr.omp' || url eq 'deleteDCtgrQnA.omp' || url eq 'saveDCtgrQnA.omp'}">
		Home &gt; 고객지원 &gt; Q&A &gt; <strong>개발자 Q&A카테고리 관리</strong>
	</c:if>
	<c:if test="${url eq 'registerCtgr.omp' || url eq 'deleteCtgrQnA.omp' || url eq 'saveCtgrQnA.omp'}">
		Home &gt; 고객지원 &gt; Q&A관리 &gt; <strong>SC Q&A카테고리 관리</strong> 
	</c:if>
	</div>
	<!-- //location -->
	<div class="overflow_h" style="">
		<h1 class="fl pr10"><c:if test="${url eq 'registerDCtgr.omp' || url eq 'deleteDCtgrQnA.omp' || url eq 'saveDCtgrQnA.omp'}">개발자 Q&A카테고리 관리</c:if><c:if test="${url eq 'registerCtgr.omp' || url eq 'deleteCtgrQnA.omp' || url eq 'saveCtgrQnA.omp'}">SC Q&A카테고리 관리</c:if></h1>
		<p><c:if test="${url eq 'registerDCtgr.omp' || url eq 'deleteDCtgrQnA.omp' || url eq 'saveDCtgrQnA.omp'}">개발자 Q&A카테고리 추가/수정/삭제를 할 수 있습니다.</c:if><c:if test="${url eq 'registerCtgr.omp' || url eq 'deleteCtgrQnA.omp' || url eq 'saveCtgrQnA.omp'}">SC Q&A카테고리 추가/수정/삭제를 할 수 있습니다.</c:if></p>
	</div>
	<div id="adm_cate">
		<dl class="mr15">
			<dt>1차 카테고리</dt>
			<dd class="list_dd">
				<ul id="qnaCtgr01">
				<c:choose>
					<c:when test="${ctgrCnt > 0}">
						<c:forEach items="${categoryNameList}" var="item" varStatus="ctgrCnt">
							<li name="ctgrDepth1" id="${item.ctgrCd}" value="${item.displayOd}" >${item.ctgrNm}</li>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<li name="ctgrDepth1" id="dfault" class="on" value=""><gm:html value="jsp.community.qna.qna_ctgr.text.ctgrsavechk"/></li>
					</c:otherwise>
				</c:choose>
				</ul>
			</dd>
			<dd>
				<p class="fl">
					<a class="btn_s" href="javascript:func_up01();"><span>▲</span></a>
					<a class="btn_s" href="javascript:func_down01();"><span>▼</span></a> 
				</p>
				<p class="fr">
					<a class="btn_s" href="javascript:modifyNm01();"><span>수정</span></a>
					<a class="btn_s" href="javascript:deleteCtgr01();"><span>삭제</span></a>
				</p>
			</dd>
			<dd class="mt05">
				<input id="ctgr01text" type="text" class="txt" style="width:88%;" value="<gm:html value=""/>" v:required="trim" m:required="<gm:tagAttb value='jsp.community.qna.qna_ctgr.text.ctgrchk'/>" v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.community.qna.qna_ctgr.msg.50'/>"/><a class="btn_s" href="javascript:insertCtgr01();"><span>추가</span></a>
				<p class="btn_wrap text_r mt25"><a class="btn" href="javascript:saveCtgr01();"><span>저장</span></a></p>
			</dd>
		</dl>
		<c:if test="${url eq 'registerCtgr.omp' || url eq 'deleteCtgrQnA.omp' || url eq 'saveCtgrQnA.omp'}">
		<dl>
			<dt>2차 카테고리</dt>
			<dd class="list_dd">
				<ul id="qnaCtgr02">
					<li name="ctgrDepth2" id="dfault" class="on" value=""><gm:html value="jsp.community.qna.qna_ctgr.msg.1ctgrchk"/></li>
				</ul>					
			</dd>
			<dd>
				<p class="fl">
					<a class="btn_s" href="javascript:func_up02();"><span>▲</span></a>
					<a class="btn_s" href="javascript:func_down02();"><span>▼</span></a> 
				</p>
				<p class="fr">
					<a class="btn_s" href="javascript:modifyNm02();"><span>수정</span></a>
					<a class="btn_s" href="javascript:deleteCtgr02();"><span>삭제</span></a>
				</p>
			</dd>
			<dd class="mt05">
				<input id="ctgr02text" name="ctgr02text" type="text" class="txt" style="width:88%;" value="<gm:html value=""/>" v:required="trim" m:required="<gm:tagAttb value='jsp.community.qna.qna_ctgr.text.ctgrchk'/>" v:text8_limit="50" m:text8_limit="<gm:tagAttb value='jsp.community.qna.qna_ctgr.msg.50'/>" /><a class="btn_s" href="javascript:insertCtgr02();"><span>추가</span></a> 
				<p class="btn_wrap text_r mt25">
					<a class="btn" href="javascript:saveCtgr02();"><span>저장</span></a>
				</p>
			</dd>
		</dl>
		</c:if>
	</div>
</s:form>
</body>