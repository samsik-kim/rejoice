<%@ page language="java" isELIgnored="false" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
$(function() {
$( "#accordion" ).accordion();
	$( ".selector" ).accordion({ collapsible: true });
	
	//getter
	var collapsible = $( ".selector" ).accordion( "option", "collapsible" );
	//setter
	$( ".selector" ).accordion( "option", "collapsible", true );
});

</script>

<div id="accordion">
	<h3><a href="#">Section 1</a></h3>
	<div>
	</div>
	<h3><a href="#">Section 2</a></h3>
	<div>
	</div>
	<h3><a href="#">Section 3</a></h3>
	<div>
		<ul>
			<li>List item one</li>
			<li>List item two</li>
			<li>List item three</li>
		</ul>
	</div>
	<h3><a href="#">Section 4</a></h3>
	<div>
	</div>
</div>

<!-- <div id="accordion"> -->
<!--     <h3><a href="">계정관리</a></h3> -->
<!--     <div></div> -->
<!--     <h3><a href="">코드관리</a></h3> -->
<!--     <div></div> -->
<!--     <h3><a href="">게시판관리</a></h3> -->
<!--     <div> -->
<!-- 		<ul> -->
<!-- 			<li class="depon"><a href="/member/insertForm.do"></a></li> -->
<!-- 			<li class="depon"><a href="/member/list.do"></a></li> -->
<!-- 		</ul> -->
<!-- 	</div> -->
<!-- </div> -->