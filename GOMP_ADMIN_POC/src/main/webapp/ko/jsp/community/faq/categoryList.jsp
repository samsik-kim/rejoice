<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %>
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>

<script type="text/javascript">

	var ctgrIndex = 0;

	$(document).ready( function()	{
		
		$("#faqCtgr li").each(function() {
			$(this).click(function() {
				var selValue = this.id;
				$(this).addClass("on");
			
				var mId = this.id;
				// document.getElementById('ctgrText').value = "Modify " + $(this).text();
				document.getElementById('ctgrText').value = $(this).text();

				$("#faqCtgr li").each(function(){
					if(mId != this.id){
						$(this).removeClass("on");
					}
				});
			});
		});

	});

	var ctgrClick = function() {
		$("#faqCtgr li").each(function() {
			$(this).click(function() {
				$(this).addClass("on");
				var mId = this.id;
				$("#faqCtgr li").each(function() {
					if(mId != $(this).attr('id')) {
						$(this).removeClass("on");
					}
				});
			});
		});
	};
	
	var funcOrderUp = function() {
		$("#faqCtgr li").each(function() {
			if($(this).hasClass("on")) {
				var selectedIndex = $(this).index();
				// alert("selectedIndex : " + selectedIndex);
				var selectedId = this.id;
				// alert("selectedId : " + selectedId);
				var prevIndex = parseInt(selectedIndex);
				var prevId = $("li[name=ctgrDepth]:nth-child("+prevIndex+")").attr('id');
				$(this).insertBefore("#"+prevId);
			}
    	});
	};
	
	var funcOrderDown = function() {
		$("#faqCtgr li").each(function() {
			if($(this).hasClass("on")) {
				var selectedIndex = $(this).index();
				var selectedId = this.id;
				var nextIndex = parseInt(selectedIndex + 2);
				var nextId = $("li[name=ctgrDepth]:nth-child("+nextIndex+")").attr('id');
				$(this).insertAfter("#"+nextId);
			}
    	});
	};

	var addCtgr = function() {

		if(!showValidate('ctgrForm', 'dialog', 'Check Input Value.')) {
			$("#ctgrText").val("");
			return;
		}

		var addText = jQuery.trim($("input[name=ctgrText]").val());

		$("#faqCtgr li").each(function() {
			if($(this).text() == "카테고리명을 입력하세요") {
				$(this).remove();
			}
		});

		/*    <li id="FAQ3001" name="ctgrDepth" value="1" >정산관련</li>    */
		$("#faqCtgr").append("<li name='ctgrDepth' id='ctgrDepth"+ctgrIndex+"' value='' onClick='javascript:ctgrClick();' >" + addText + "</li>");
		$("#ctgrText").val("");

		ctgrIndex++;
		
	};

	var updCtgr = function() {

		var modText = jQuery.trim($("input[name=ctgrText]").val());

		if(!showValidate('ctgrForm', 'dialog', 'Check Input Value.')) {
			$("#ctgrText").val("");
			return;
		}
		
		$("#faqCtgr li").each(function() {
			if($(this).hasClass("on")) {
				$(this).text(modText);
			}
		});
		$("#ctgrText").val("");
	};

	var delCtgr = function() {
		
		if(confirm("<gm:string value='jsp.community.faq.categoryList.msg.confirm_del'/>")) {
			var clsCd = null;
			$("#faqCtgr li").each(function() {
				if($(this).hasClass("on")) {
					document.getElementById('ctgrText').value = "";
					if($(this).attr('cnt') > 0) {
						alert( "<gm:string value="jsp.community.faq.categoryList.msg.alert"/>" );
						return;
					}
					clsCd = $(this).attr('id');
					$(this).remove();
				}
			});
		}
		
	};

	var saveCtgr = function() {
		
		var ctgrNmList = null;
		var ctgrCdList = null;
		var ctgrIndexList = null;

		$("#faqCtgr li").each(function() {
			
			if(ctgrNmList == null) {
				// ctgrNmList = "#" + $(this).text();
				ctgrNmList = $(this).text();
			} else {
				ctgrNmList = ctgrNmList + "#" + $(this).text();
			}
			
			if(ctgrCdList == null) {
				// ctgrCdList = "#" + $(this).attr('id');
				ctgrCdList = $(this).attr('id');
			} else {
				ctgrCdList = ctgrCdList + "#" + $(this).attr('id');
			}
			
			if(ctgrIndexList == null) {
				// ctgrIndexList = "#" + $(this).index();
				ctgrIndexList = $(this).index();
			} else {
				ctgrIndexList = ctgrIndexList + "#" + $(this).index();
			}
			
		});

		// alert("ctgrNmList : " + ctgrNmList);
		// alert("ctgrCdList : " + ctgrCdList);
		// alert("ctgrIndexList : " + ctgrIndexList);
		
		$("#ctgrNmList").val(ctgrNmList);
		$("#ctgrCdList").val(ctgrCdList);
		$("#ctgrIndexList").val(ctgrIndexList);
		
		if(confirm("<gm:string value='jsp.community.faq.categoryList.msg.confirm_ins'/>")) {
			$.blockUI({ message: 'Please Wait.' });
			$("#ctgrForm").submit();
		}
	};

</script>

		<div id="location">
			Home &gt; 고객지원 &gt; FAQ 관리 &gt; <strong>
			<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_SC">
			SC FAQ 카테고리 관리
			</s:if>
			<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_DEV">
			개발자 FAQ 카테고리 관리
			</s:if>
			</strong> 
		</div><!-- //location -->

		<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_SC">
		<h1 class="fl pr10">SC FAQ 카테고리 관리</h1>
		<p>SC FAQ 카테고리 추가/수정/삭제를 할 수 있습니다.</p>
		</s:if>
		<s:if test="ctgrCd == @com.omp.admin.common.Constants@CTGR_FAQ_DEV">
		<h1 class="fl pr10">개발자 FAQ 카테고리 관리</h1>
		<p>개발자 FAQ 카테고리 추가/수정/삭제를 할 수 있습니다.</p>
		</s:if>

		<s:form action="processCtgr" id="ctgrForm" name="ctgrForm" theme="simple" method="post" >
			<!-- <s:hidden id="ctgrList" name="ctgrList"/> -->
			<s:hidden id="ctgrCd" name="ctgrCd"/>
			<s:hidden id="highCtgr" name="highCtgr"/>
			<s:hidden id="ctgrLevelCd" name="ctgr.ctgrLevelCd"/>
			<s:hidden id="ctgr.ctgrCd" name="ctgr.ctgrCd"/>
			<s:hidden id="ctgrNm" name="ctgr.ctgrNm"/>
			<s:hidden id="displayOrder" name="ctgr.displayOrder"/>
			<s:hidden id="ctgrNmList" name="ctgrNmList"/>
			<s:hidden id="ctgrCdList" name="ctgrCdList"/>
			<s:hidden id="ctgrIndexList" name="ctgrIndexList"/>

		<s:if test="ctgrList.size != 0">
			<s:iterator value="ctgrList" status="status">
			<s:hidden id="asisCtgrCd" name="asisCtgrCd" value="%{ctgrCd}" />
			</s:iterator>
		</s:if>
			
		<div id="adm_cate">
			<dl class="mr15">
				<dt>1차 카테고리</dt>
				<dd class="list_dd">
					<ul id="faqCtgr">
				<s:if test="ctgrList.size == 0">
						<li class="on">카테고리명을 입력하세요</li>
				</s:if>
				<s:else>
					<s:iterator value="ctgrList" status="status">
						<li name="ctgrDepth" id="${ctgrCd}" value="${displayOrder}" cnt="${cntFaq}" >${ctgrNm}</li>
					</s:iterator>
				</s:else>
					</ul>
				</dd>
				<dd>
					<p class="fl">
						<a class="btn_s" href="javascript:funcOrderUp();"><span>▲</span></a>
						<a class="btn_s" href="javascript:funcOrderDown();"><span>▼</span></a> 
					</p>
					<p class="fr">
						<a class="btn_s" href="javascript:updCtgr();"><span>수정</span></a>
						<a class="btn_s" href="javascript:delCtgr();"><span>삭제</span></a>
					</p>
				</dd>

				<dd class="mt05">
					<input id="ctgrText" type="text" name="ctgrText" class="txt" style="width:88%;" 
						v:required="trim" m:required="<gm:string value="jsp.community.faq.categoryList.msg.check_ctgrtext"/>"  />
					<a class="btn_s" href="javascript:addCtgr();"><span>추가</span></a>
					<p class="btn_wrap text_r mt25">
						<a class="btn" href="javascript:saveCtgr();"><span>저장</span></a>
					</p>
				</dd>

			</dl>
		</div>
		</s:form>

</body>
</html>
