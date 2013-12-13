<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %> 
<%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %> 
<%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %>
<%!
/**
 * 테스트 모델
 */
public class TestModel {
	private String 			name;
	private int				age;
	private java.util.Date	birthDay;
	private String			comment;
	
	public TestModel() {
		this.name		= "홍길동";
		this.age		= 25;
		this.birthDay	= new java.util.Date(1980, 5, 23);
		this.comment	= "가나다\"마바사\n으헤으헤 호호호";
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name	= name;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public void setAge(int age) {
		this.age	= age;
	}
	
	public java.util.Date getBirthDay() {
		return this.birthDay;
	}
	
	public void setBirthDay(java.util.Date bd) {
		this.birthDay	= bd;
	}
	
	public String getComment() {
		return this.comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
}
%>
<%
	java.util.List	testList	= new java.util.ArrayList();
	request.setAttribute("examStr1", "서울시종로구");
	request.setAttribute("examStr2", "가나다라마바사아ABCDEFG012345");
	request.setAttribute("examStr3", "20110225131233");
	request.setAttribute("examStr4", "1625");
	request.setAttribute("examInteger1", new Integer(18257714));
	request.setAttribute("examDouble1", new Double(Math.PI));
	request.setAttribute("examDate1", new java.util.Date());
	
	request.setAttribute("examHtml1", "<a href=\"javascript:alert('그런게 어딨슴');\"><font color=\"red\">싼이자 저금리 지금 클릭</font></a>");

	request.setAttribute("examStr5", "가나다\n라마바\" <사아자>차");
	
	request.setAttribute("examStr6", "그는 \"안돼\"라고 소리첬습니다.");
	
	request.setAttribute("examStr7", "textarea는 <textarea>내용</textarea>의 형식으로 사용할 수 있습니다.");
	
	request.setAttribute("examStr8", "펭귄때 &&&가 쫑쫑쫑\n잘도 다니네.");
	
	request.setAttribute("examModel1", new TestModel());
	testList.add(new TestModel());
	testList.add(new TestModel());
	testList.add(new TestModel());
	testList.add(new TestModel());
	request.setAttribute("examList1", testList);

	request.setAttribute("examStr9", "02");


	testList	= new java.util.ArrayList();
	request.setAttribute("examList2", testList);
	testList.add("02");
	testList.add("04");
	testList.add("06");

%> 
<html>
<head>
<style type="text/css">
<!--
body,div,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,form,fieldset,p,button{margin:5px;padding:5px;}
body,h1,h2,h3,h4,th,td,input{color:#333;font-family:"돋움",dotum,sans-serif;font-size:12px;font-weight:normal;}
.code {
	font-weight:bold;
	color:#300000;
};
//-->
</style>
<script>
function pinit() {
	var	tpm	= "<g:string value="${param['testparam']}"/>";
	
	if (tpm !="") {
		alert("전달된 파라메터 ==> " + tpm);
	}
}
</script>
</head>
<body onload="pinit();">
<pre>

<span style="font-size:15px;font-weight:bold">[커스텀 태그 라이브러리 사용 표준]</span>
커스텀 태그 라이브러리는 JSTL의 core 태그와 struts태그 및 아래에 설명하는 커스텀 태그를 사용하며, 기존 app 태그와 JSTL의 format태그 및 message 태그는 사용하지 않습니다.

<span style="font-size:15px;font-weight:bold">[각 라이브러리 정의]</span>
JSTL의 core : 프리픽스 &lt;c: 를 사용하며 라이브러리 정의는 &lt;%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %&gt; 로 합니다.
GOMP의 core : 프리픽스 &lt;g: 를 사용하며 라이브러리 정의는 &lt;%@ taglib prefix="g" uri="http://gomp.com/taglib/core" %&gt; 로 합니다.
GOMP의 code : 프리픽스 &lt;gc: 를 사용하며 라이브러리 정의는 &lt;%@ taglib prefix="gc" uri="http://gomp.com/taglib/code" %&gt; 로 합니다.
GOMP의 message : 프리픽스 &lt;gm: 을 사용하며 라이브러리 정의는 &lt;%@ taglib prefix="gm" uri="http://gomp.com/taglib/message" %&gt; 로 합니다.

<span style="font-size:15px;font-weight:bold">[기타 부언]</span>
아직 없슴.

</pre>  
<span style="font-size:15px;font-weight:bold">[신규 커스텀 라이브러리 설명]</span>
<table width="100%" cellspacing="1" cellpadding="4" border="0" style="background:black">
<tr style="background:white">
	<td width="250" align="center">태그명</td>
	<td align="center">설명</td>
</tr>
<tr style="background:white">
	<td valign="top">
		&lt;g:text [value="표시값"] [format="포멧"]/&gt;
	</td>
	<td>
		데이터를 표시하는 태그입니다.<br/>
		&#36;{exam.str.text1} 등의 표현만 사용하는것과 별 차이가 없지만 출력 포멧을 설정 할 수 있고, 생략시 기본 포멧이 적용되어 출력됩니다.<br/>
		포멧은 문자열로 지정하며, 표시하는 데이터의 타입에 따라 아래와 같이 해석됩니다.<br/>
		표시값은 value 속성을 생략하면 태그의 body 부분이 사용됩니다.<br/>
		<ul type="1">
			<li>
				표시값이 java.util.Date의 서브 클래스 이면 주어지는 포멧은 java.text.SimpleDateFormet의 포멧 문자열로 해석되며,
				포멧 생략시에 java.sql.Date 타입이면 "yyyy/MM/dd"이, java.sql.Time 타입이면  "HH:mm:ss", 그외는 "yyyy/MM/dd HH:mm:ss"이 적용됩니다.
			</li>
			<li>
				표시값이 javaj.lang.Number의 서브 클래이스 이면(모든 숫자타입 및 원형 포함) 주어지는 포멧은 java.text.DecimalFormat의 포멧 문자열로 해석됩니다.
				포멧 생략시 정수 타입은 "############", 실수 타입은 ",###.0#########"이 적용 됩니다.
			</li>
			<li>
				표시값이 그 이외일때는 toString()으로 나타난 문자열을 표시값으로 사용하며 주어지는 포멧은 아래의 형식에 따릅니다.<br>
		    	&nbsp;&nbsp;[L:R][#. 혹은 그외문자]
		    	<ul type="square">
		    		<li>
		    			첫글자는 반드시 L 혹은 R로 시작해야 합니다. 이것은 정렬 지시 문자로 L(좌측정렬) R(우측정렬)을 의미합니다.
		    		</li>
		    		<li>
		    			첫글자 이후는 데이터가 표시될 자리를 나타내는 심볼 #.0* 혹은 삽입할 문자열 들의 조합이 사용 될 수 있습니다.
		    		</li>
		    		<li>
		    			#으로 주어진 자리에 데이터의 문자열 길이가 짧아 할당 되지 않을 경우는 스페이스 케릭터가 적용됩니다. 정렬방향에 따라 스페이스가 나타나는 방향도 영향을 받습니다.
		    		</li>
		    		<li>
		    			.으로 주어진 자리는 데이터의 문자열 길이가 설정된 포멧 문자열에 지정한 길이를 넘어갈 경우 .으로 나타나며, 넘치지 않으면 표시될 문자가 나타납니다.
		    		</li>
		    		<li>
		    			0으로 주어진 자리는 데이터에 표시할 자리의 케릭터가 있으면 표시하고 없으면 0을 표시합니다.
		    		</li>
		    		<li>
		    			^으로 주어진 자리는 데이터에 표시할 자리의 케릭터를 생략하고 건너뜁니다.
		    		</li>
		    		<li>
		    			*으로 주어진 자리는 데이터가 있으면 *로 마킹 합니다.
		    		</li>
		    		<li>
		    			이스케입 문자 \는 심볼 자체를 표현하고자 할때 사용합니다.
		    		</li>
		    		<li>
				    	예제
				    	<table cellspacing="1" cellpadding="4" style="background:gray">
				    	<tr style="background:white">
				    		<td align="center">
				    			태그
				    		</td>
				    		<td align="center">
				    			표현된 값
				    		</td>
				    		<td align="center">
				    			비고
				    		</td>
				    	</tr>
				    	<tr style="background:white">
				    		<td>
				    			&#36;{examStr1}
				    		</td>
				    		<td nowrap>
				    			${examStr1}
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr1}" format="L###A###B###C"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr1}" format="L###A###B###C"/>
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr1}" format="R###A###B###C"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr1}" format="R###A###B###C"/>
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&#36;{examStr2}
				    		</td>
				    		<td nowrap>
				    			${examStr2}
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr2}" format="L##########"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr2}" format="L##########"/>
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr2}" format="R##########"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr2}" format="R##########"/>
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr2}" format="L#######..."/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr2}" format="L#######..."/>
				    		</td>
				    		<td>
				    			표시 가능 자리수를 넘치면 .으로 찍힘
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr2}" format="R...#######"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr2}" format="R...#######"/>
				    		</td>
				    		<td>
				    			표시 가능 자리수를 넘치면 .으로 찍힘
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr1}" format="L#######..."/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr1}" format="L#######..."/>
				    		</td>
				    		<td>
				    			표시 가능 자리수를 넘치지 않으면 #과 동일함
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&#36;{examStr3}
				    		</td>
				    		<td nowrap>
				    			${examStr3}
				    		</td>
				    		<td>
				    			문자열 객체임
				    		</td>
				    	</tr>
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr3}" format="L####년 ##월 ##일"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr3}" format="L####년 ##월 ##일"/>
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr3}" format="L####년"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr3}" format="L####년"/>
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr3}" format="R##월 ##일^^^^^^"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr3}" format="R##월 ##일^^^^^^"/>
				    		</td>
				    		<td>
				    			시분초에 해당하는 부분을 잘라내기위해 ^를 사용함
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr3}" format="L^^^^##월 ##일"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr3}" format="L^^^^##월 ##일"/>
				    		</td>
				    		<td>
				    			위의 예제와 결과는 동일하지만 불필요한 부분을 잘라낸 방식이 다름
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr3}" format="L####/##/## ##:##\.##"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr3}" format="L####/##/## ##:##\\.##"/>
				    		</td>
				    		<td>
				    			점.을 표시하기위해 이스케입 문자 \를 사용하여 포멧을 지정함
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr3}" format="R##시 ##분**"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr3}" format="R##시 ##분**"/>
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&#36;{examStr4}
				    		</td>
				    		<td nowrap>
				    			${examStr4}
				    		</td>
				    		<td>
				    			문자열 객체임
				    		</td>
				    	</tr>
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr4}" format="L0000000000"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr4}" format="L0000000000"/>
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr4}" format="R0000000000"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr4}" format="R0000000000"/>
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr4}" format="R0,000,000,000"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr4}" format="R0,000,000,000"/>
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr4}" format="R#,###,###,###"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr4}" format="R#,###,###,###"/>
				    		</td>
				    		<td>
				    			&nbsp;
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examStr4}" format="L##*********************"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examStr4}" format="L##*********************"/>
				    		</td>
				    		<td>
				    			앞에서 두자리만 표시하고 나머지는 *로 마킹
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&#36;{examInteger1}
				    		</td>
				    		<td nowrap>
				    			${examInteger1}
				    		</td>
				    		<td>
				    			 java.lang.Integer 객체임
				    		</td>
				    	</tr>
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examInteger1}"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examInteger1}"/>
				    		</td>
				    		<td>
				    			기본 포멧 java.text.DecimalFormat("############") 적용됨
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examInteger1}" format="0.###E0"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examInteger1}" format="0.###E0"/>
				    		</td>
				    		<td>
				    			java.text.DecimalFormat 형식의 포멧
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&#36;{examInteger1}
				    		</td>
				    		<td nowrap>
				    			${examDouble1}
				    		</td>
				    		<td>
				    			 java.lang.Double 객체임
				    		</td>
				    	</tr>
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examDouble1}"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examDouble1}"/>
				    		</td>
				    		<td>
				    			기본 포멧 java.text.DecimalFormat(",###.0#########") 적용됨
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examDouble1}" format="##.####"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examDouble1}" format="##.####"/>
				    		</td>
				    		<td>
				    			java.text.DecimalFormat 형식의 포멧
				    		</td>
				    	</tr>				    	
				    	<tr style="background:white">
				    		<td>
				    			&lt;g:text value="&#36;{examDouble1}" format="##.####"/&gt;
				    		</td>
				    		<td nowrap>
				    			<g:text value="${examDouble1}" format="##.####"/>
				    		</td>
				    		<td>
				    			java.text.DecimalFormat 형식의 포멧
				    		</td>
				    	</tr>				    	
				    	</table>
				    </li>
		    	</ul>
		    </li> 
		</ul>
	</td>
</tr>
<tr style="background:white">
	<td valign="top">
		&lt;g:html [value="표시값"] [format="포멧"]/&gt;
	</td>
	<td>
		표현하려는 데이터가 html 태그로 적용되지 않고 그대로 화면에 나타나도록 하는 태그입니다.<br/>
		사용자가 입력한 내용을 html로 적용되지 않고 텍스트 만을 인정하고자 할때 사용합니다. <br/>
		그 외 포멧 등은 &lt;g:text value="표시값" [format="포멧"]&gt; 와 동일합니다.<br/>
		표시값은 value 속성을 생략하면 태그의 body 부분이 사용됩니다.<br/>
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&#36;{examHtml1}
		    		</td>
		    		<td nowrap>
		    			${examHtml1}
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:html value="&#36;{examHtml1}"/&gt;
		    		</td>
		    		<td nowrap>
		    			<g:html value="${examHtml1}"/>
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>				    	
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:html&gt;&#36;{examHtml1}&lt;/g:html&gt;
		    		</td>
		    		<td nowrap>
		    			<g:html>${examHtml1}</g:html>
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>				    	
		    	</table>
		    </li>
		</ul>
	</td>
</tr>
<tr style="background:white">
	<td valign="top">
		&lt;g:string [value="표시값"] [format="포멧"]/&gt;
	</td>
	<td>
		표현하려는 데이터를 JavaScript String 에 포함시키고자 할때 사용합니다.<br/>
		JavaScript Strin에 포함 시킬때 오류를 발생 시킬 수 있는 줄바꿈 문자나 따옴표 등을 escaping 해줍니다.<br/>
		그 외 포멧 등은 &lt;g:text value="표시값" [format="포멧"]&gt; 와 동일합니다.<br/>
		표시값은 value 속성을 생략하면 태그의 body 부분이 사용됩니다.<br/>
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&#36;{examStr5}
		    		</td>
		    		<td nowrap>
		    			${examStr5}
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:html value="&#36;{examStr5}"/&gt;
		    		</td>
		    		<td nowrap>
		    			<g:html value="${examStr5}"/>
		    		</td>
		    		<td>
		    			실제 데이터가 표현하는 모양을 보여 주기 위해 html 태그 적용.
		    		</td>
		    	</tr>				    	
		    	<tr style="background:white">
		    		<td>
<pre>&lt;script&gt;
	function testFunc1() {
		alert("&#36;{examStr5}");
	}
&lt;/script&gt;
&lt;input type="button" value="테스트" onclick="testFunc1()"/&gt;</pre>
		    		</td>
		    		<td nowrap>
<script>
<!--
	function testFunc1() {
		alert("${examStr5}");
	}
-->
</script>
<input type="button" value="테스트" onclick="testFunc1()"/>
		    		</td>
		    		<td>
		    			이 표현 때문에 자바스크립트 오류 발생함 
		    		</td>
		    	</tr>				    	
		    	<tr style="background:white">
		    		<td>
<pre>&lt;script&gt;
	function testFunc2() {
		alert("&lt;g:string value="&#36;{examStr5}"/&gt;");
	}
&lt;/script&gt;
&lt;input type="button" value="테스트" onclick="testFunc2()"/&gt;</pre>
		    		</td>
		    		<td nowrap>
<script>
<!--
	function testFunc2() {
		alert("<g:string value="${examStr5}"/>");
	}
-->
</script>
<input type="button" value="테스트" onclick="testFunc2()"/>
		    		</td>
		    		<td>
		    			자바스크립트 스트링 형식으로 잘 변환됨 
		    		</td>
		    	</tr>				    	
		    	</table>
		    </li>
		</ul>
	</td>
</tr>
<tr style="background:white">
	<td valign="top">
		&lt;g:tastring [value="표시값"] [format="포멧"]/&gt;
	</td>
	<td>
		표현하려는 데이터를 태그 속성에서 적용된 JavaScript String 에 포함시키고자 할때 사용합니다.<br/>
		태그에 걸린 이벤트 속성등에서 호출하는 펑션등에 데이터를 전달 하고자 할때 사용합니다.<br/>
		그 외 포멧 등은 &lt;g:text value="표시값" [format="포멧"]&gt; 와 동일합니다.<br/>
		표시값은 value 속성을 생략하면 태그의 body 부분이 사용됩니다.<br/>
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&#36;{examStr5}
		    		</td>
		    		<td nowrap>
		    			${examStr5}
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:html value="&#36;{examStr5}"/&gt;
		    		</td>
		    		<td nowrap>
		    			<g:html value="${examStr5}"/>
		    		</td>
		    		<td>
		    			실제 데이터가 표현하는 모양을 보여 주기 위해 html 태그 적용.
		    		</td>
		    	</tr>				    	
		    	<tr style="background:white">
		    		<td>
<pre>&lt;script&gt;
	function testFunc3(msg) {
		alert(msg);
	}
&lt;/script&gt;
&lt;input type="button" value="테스트" onclick="testFunc3(&quot;&#36;{examStr5}&quot;)"/&gt;</pre>
		    		</td>
		    		<td nowrap>
<script>
<!--
	function testFunc3(msg) {
		alert(msg);
	}
-->
</script>
<input type="button" value="테스트" onclick="testFunc3(&quot;${examStr5}&quot;)"/>
		    		</td>
		    		<td>
		    			이 표현 때문에 자바스크립트 오류 및 태그 깨짐 발생함 
		    		</td>
		    	</tr>				    	
		    	<tr style="background:white">
		    		<td>
<pre>&lt;input type="button" value="테스트" onclick="testFunc3(&amp;quot;&lt;g:tastring value="&#36;{examStr5}"/&gt;&amp;quot;)"/&gt;</pre>
		    		</td>
		    		<td nowrap>
<input type="button" value="테스트" onclick="testFunc3(&quot;<g:tastring value="${examStr5}"/>&quot;)"/>
		    		</td>
		    		<td>
		    			안전하게 잘 변환됨 
		    		</td>
		    	</tr>				    	
		    	</table>
		    </li>
		</ul>
	</td>
</tr>
<tr style="background:white">
	<td valign="top">
		&lt;g:tagAttb [value="표시값"] [format="포멧"]/&gt;
	</td>
	<td>
		표현하려는 데이터가 태그의 속성값으로 사용될때 나타날 수 있는 오류를 escaping 합니다.<br/>
		&lt;input value="데이터"&gt;와 같이 테이터의 내용을 태그 속성으로 적용할때 사용 됩니다.<br/>
		그 외 포멧 등은 &lt;g:text value="표시값" [format="포멧"]&gt; 와 동일합니다.<br/>
		표시값은 value 속성을 생략하면 태그의 body 부분이 사용됩니다.<br/>
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&#36;{examStr6}
		    		</td>
		    		<td nowrap>
		    			${examStr6}
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:html value="&#36;{examStr6}"/&gt;
		    		</td>
		    		<td nowrap>
		    			<g:html value="${examStr6}"/>
		    		</td>
		    		<td>
		    			실제 데이터가 표현하는 모양을 보여 주기 위해 html 태그 적용.
		    		</td>
		    	</tr>				    	
		    	<tr style="background:white">
		    		<td>
		    			&lt;input type="text" value="&#36;{examStr6}"/&gt;
		    		</td>
		    		<td nowrap>
		    			<input type="text" value="${examStr6}"/>
		    		</td>
		    		<td>
		    			 데이터 중간의 따옴표 때문에 입력 필드에 잘려서 나타남
		    		</td>
		    	</tr>				    	
		    	<tr style="background:white">
		    		<td>
		    			&lt;input type="text" value="&lt;g:tagAttb value="&#36;{examStr6}"/&gt;"/&gt;
		    		</td>
		    		<td nowrap>
		    			<input type="text" value="<g:tagAttb value="${examStr6}"/>"/>
		    		</td>
		    		<td>
		    			 정상 처리됨
		    		</td>
		    	</tr>				    	
		    	</table>
		    </li>
		</ul>
	</td>
</tr>
<tr style="background:white">
	<td valign="top">
		&lt;g:tagBody [value="표시값"] [format="포멧"]/&gt;
	</td>
	<td>
		표현하려는 데이터가 태그의 바디값으로 사용될때 나타날 수 있는 오류를 escaping 합니다.<br/>
		&lt;textarea&gt;나 xml의 엘리먼트 바디에 테이터의 내용을 지정 해야할때 사용 됩니다.<br/>
		그 외 포멧 등은 &lt;g:text value="표시값" [format="포멧"]&gt; 와 동일합니다.<br/>
		표시값은 value 속성을 생략하면 태그의 body 부분이 사용됩니다.<br/>
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&#36;{examStr7}
		    		</td>
		    		<td nowrap>
		    			${examStr7}
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:html value="&#36;{examStr7}"/&gt;
		    		</td>
		    		<td nowrap>
		    			<g:html value="${examStr7}"/>
		    		</td>
		    		<td>
		    			실제 데이터가 표현하는 모양을 보여 주기 위해 html 태그 적용.
		    		</td>
		    	</tr>				    	
		    	<tr style="background:white">
		    		<td>
		    			&lt;textarea&gt;&#36;{examStr7}&lt;/textarea&gt;
		    		</td>
		    		<td nowrap>
		    			<textarea>${examStr7}</textarea>
		    		</td>
		    		<td>
		    			 데이터 중간의 태그 표현 때문에 입력 필드에 잘려서 나타남
		    		</td>
		    	</tr>				    	
		    	<tr style="background:white">
		    		<td>
		    			&lt;textarea&gt;&lt;g:tagBody value="&#36;{examStr7}"/&gt;&lt;/textarea&gt;
		    		</td>
		    		<td nowrap>
		    			<textarea><g:tagBody value="${examStr7}"/></textarea>
		    		</td>
		    		<td>
		    			 정상 처리됨
		    		</td>
		    	</tr>				    	
		    	</table>
		    </li>
		</ul>
	</td>
</tr>
<tr style="background:white">
	<td valign="top">
		&lt;g:qs [value="표시값"] [format="포멧"] [charset="케릭터셋"]/&gt;
	</td>
	<td>
		표현하려는 데이터가 HTTP URL의 파라메터 값으로 사용될 때 나타날 수 있는 오류를 escaping 합니다.<br/>
		특수문자가(&이나 줄바꿈 등) 포함될 가능성이 있는 데이터의 URL 파라메터 전달 시에 사용됩니다.<br/>
		케릭터셋이 생략되면 해당 페이지의 출력 케릭터셋이 사용됩니다.<br/>
		그 외 포멧 등은 &lt;g:text value="표시값" [format="포멧"]&gt; 와 동일합니다.<br/>
		표시값은 value 속성을 생략하면 태그의 body 부분이 사용됩니다.<br/>
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&#36;{examStr8}
		    		</td>
		    		<td nowrap>
		    			${examStr8}
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:html value="&#36;{examStr8}"/&gt;
		    		</td>
		    		<td nowrap>
		    			<g:html value="${examStr8}"/>
		    		</td>
		    		<td>
		    			실제 데이터가 표현하는 모양을 보여 주기 위해 html 태그 적용.
		    		</td>
		    	</tr>				    	
		    	<tr style="background:white">
		    		<td>
		    			&lt;a href="taglib.jsp?testparam=&#36;{examStr8}"&gt;테스트&lt;/a&gt;
		    		</td>
		    		<td nowrap>
		    			<a href="taglib.jsp?testparam=${examStr8}">테스트</a>
		    		</td>
		    		<td>
		    			값이 잘 전달되지 않음
		    		</td>
		    	</tr>				    	
		    	<tr style="background:white">
		    		<td>
		    			&lt;a href="taglib.jsp?testparam=&lt;g:qs value="&#36;{examStr8}"/&gt;"&gt;테스트&lt;/a&gt;
		    		</td>
		    		<td nowrap>
		    			<a href="taglib.jsp?testparam=<g:qs value="${examStr8}"/>">테스트</a>
		    		</td>
		    		<td>
		    			 정상 처리됨
		    		</td>
		    	</tr>				    	
		    	</table>
		    </li>
		</ul>
	</td>
</tr>
<tr style="background:white">
	<td valign="top">
		&lt;g:json value="표시값"/&gt;
	</td>
	<td>
		표현하려는 데이터(객체/모델)의 스트럭처 구조를 그대로 자바스크립트 오브젝트로 표현해 주는 JSON 구문을 출력합니다.<br/>
		AJAX의 응답이나, 자바스크립트 펑견 콜에서 Java 모델 전체를 전달 하고자 할때 사용합니다.</br>
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&#36;{examModel1}
		    		</td>
		    		<td nowrap>
		    			${examModel1}
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:json value="&#36;{examModel1}"/&gt;
		    		</td>
		    		<td>
		    			<g:json value="${examModel1}"/>
		    		</td>
		    		<td>
		    		</td>
		    	</tr>				    	
		    	</table>
		    </li>
		</ul>
	</td>
</tr>
<tr style="background:white">
	<td valign="top">
		&lt;g:tajson value="표시값"/&gt;
	</td>
	<td>
		표현하려는 데이터(객체/모델)의 스트럭처 구조를 그대로 자바스크립트 오브젝트로 표현해 주는 JSON 구문을  태그 어트리뷰트에 사영될수 있도록  escaping하여 출력합니다.<br/>
		HTML 엘리먼트에서 자바스크립트 펑션에 모델을 JSON으로 전달하고자 할때 사용 할 수 있습니다.<br/>
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
<pre>&lt;script&gt;
function testFunc4(person) {
	alert("이름 :" + person.name
	      + "\n나이 :" + person.age
	      + "\n생일 : " + person.birthDay
	      + "\n한마디 :" + person.comment);
}
&lt;/script&gt;		    		
&lt;input type="button" value="테스트" onclick="testFunc4(&lt;g:tajson value="&#36;{examModel1}"/&gt;)"/></pre>
		    		</td>
		    		<td>
<script>
function testFunc4(person) {
	alert("이름 :" + person.name
			+ "\n나이 :" + person.age
			+ "\n생일 : " + person.birthDay
			+ "\n한마디 :" + person.comment);
}
</script>		    		
<input type="button" value="테스트" onclick="testFunc4(<g:tajson value="${examModel1}"/>)"/>
		    		</td>
		    		<td>
		    			나이는 java.util.Date 형태의 객체이므로 Date 객체에 존재하는 속성들을 사용하여야함
		    		</td>
		    	</tr>				    	
		    	</table>
		    </li>
		</ul>
	</td>
</tr>
<tr style="background:white">
	<td valign="top">
<pre>	
&lt;g:printBean value="표시값
      outType="표시형식"
      [prefix="전달파라메터프리픽스"]
      [charSet="케릭터셋"]
      [skipReadonly="읽기만가능한 속성 건너뜀 여부"]
      [excludePattern="출력에서 제외할 속성명 패턴"]/&gt;
</pre>		      
	</td>
	<td>
		표현하려는 데이터(객체/모델)의 속성들 모두를 파라메터로 전달 할수 있게 출력해 줍니다.<br/>
		특정 모델을 다음 서버 요청에 전달 하고자 하는 등의 작업에서 사용됩니다.
		<ul type="1">
			<li>
				outType은 표시형식으로 qs 혹은 hidden을 지정 할 수 있으며, qs 지정시 URL형식의 쿼리 스트링 방식으로 출력하며,<br/>
				hidden 지정시 &lt;form&gt;에 삽입 할 수 있는 &lt;input type="hidden"&gt; 방식으로 출력합니다.
			</li>
			<li>
				prefix는 전달할 파라메터명 앞부분에 지정할 프리픽스를 지정합니다.<br/>
			</li>
			<li>
				charSet은 outType qs에서만 유효하며, 출력할때 사용할 케릭터셋을 지정할 수 있습니다.<br/>
				생략시 해당 페이지의 출력 케릭터셋이 사용됩니다.
			</li>
			<li>
				skipReadonly 는 yes 혹은 no로 지정 할 수 있으며, yes를 지정하거나 생략시 해당 모델에서 getter 메소드만 존재하는 속성들은
				출력하지 않습니다.
			</li>
			<li>
				excludePattern은 일반적인 와일드 카드 *나 ?를 사용하여 출력시 제외할 속성명들의 패턴을 지정할 수 있습니다.<br/>
				구분자 세미콜론;을 이용하여 여러 패턴을 지정 할 수 있습니다.
			</li>
		</ul>
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:printBean value="&#36;{examModel1}" outType="qs"/&gt;
		    		</td>
		    		<td>
		    			<g:printBean value="${examModel1}" outType="qs"/>
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:printBean value="&#36;{examModel1}" outType="hidden"/&gt;
		    		</td>
		    		<td>
		    			<g:html><g:printBean value="${examModel1}" outType="hidden"/></g:html>
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:printBean value="&#36;{examModel1}" outType="hidden" prefix="jons."/&gt;
		    		</td>
		    		<td>
		    			<g:html><g:printBean value="${examModel1}" outType="hidden" prefix="jons."/></g:html>
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:printBean value="&#36;{examModel1}" outType="hidden" excludePattern="a*;birthDay"/&gt;
		    		</td>
		    		<td>
		    			<g:html><g:printBean value="${examModel1}" outType="hidden" excludePattern="a*;birthDay"/></g:html>
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	</table>
		    </li>
		</ul>
	</td>
</tr>
<tr style="background:white">
	<td valign="top">
		&lt;g:size item="대상컬렉션" var="설정명"/&gt;
	</td>
	<td>
		어떤 컬렉션 객체(List, Map, Set 등)의 크기를 얻어 지정된 이름으로 설정합니다. 
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&#36;{examList1}
		    		</td>
		    		<td>
		    			${examList1}
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;g:size item="&#36;{examList1}" var="examList1Size"/&gt;&#36;{examList1Size}
		    		</td>
		    		<td>
		    			<g:size item="${examList1}" var="examList1Size"/>${examList1Size}
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	</table>
		    </li>
		</ul>
	</td>
</tr>
<tr style="background:white">
	<td valign="top">
		&lt;gc:codeList group="코드그룹" var="설정명" [filter="포함코드필터"] [excludeFilter="제외코드필터"]/&gt;
	</td>
	<td>
		코드그룹의 코드상세 리스트를 지정된 이름으로 설정합니다.<br/>
		설정되는 객체는 List&lt;com.omp.commons.commcode.model.CommCode&gt; 입니다. <br/>
		filter 및 excludeFilter 는 와일드 카드 * 나 ? 를 사용한 문자열로 지정하며, 각 코드에 설정된 태그를 판별하여, 리스트에 포함하거나 제외 시킵니다. <br/>
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;gc:codeList group="US0005" var="cgUs0005"/&gt;<br/>&#36;{cgUs0005}
		    		</td>
		    		<td>
		    			<gc:codeList group="US0005" var="cgUs0005"/>${cgUs0005}
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	</table>
		    </li>
		</ul>
	</td>
</tr>

<tr style="background:white">
	<td valign="top">
		&lt;gc:options group="코드그룹" [codeType="코드형식"] [value="코드값"] [extra="추가렌더링"] [filter="포함코드필터"] [excludeFilter="제외코드필터"]/&gt;
	</td>
	<td>
		코드그룹의 내용을 &lt;select&gt; 태그에서 사용하는  &lt;option&gt; 태그의 형식으로 출력합니다.<br/>
		<ul type="1">
			<li>코드형식은 option의 value에 할당 될 코드값의 형식으로  normal 이나 full 둘중 하나를 지정 할 수 있으며, 생략시 normal 형식이 사용됩니다.</li>
			<li>코드 값은 selected 되어 있어야할 코드값을 지정 할 수 있으며, 생략시 selected 되는 코드 값이 없습니다.</li>
			<li>추가렌더링은 &lt;option value='' {추가렌더링}&gt; 의 위치에 추가로 출력할 내용을 지정하는 곳으로, 태그의 이벤트나 스타일 등의 추가 속성을 설정 할 수 있습니다.</li>
			<li>filter 및 excludeFilter 는 와일드 카드 * 나 ? 를 사용한 문자열로 지정하며, 각 코드에 설정된 태그를 판별하여, 리스트에 포함하거나 제외 시킵니다. </li>
		</ul>
		
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
<pre>&lt;select onchange="alert(this.value);"&gt;
	&lt;option value=""&gt;선택하세요.&lt;/option&gt;
	&lt;gc:options group="US0005" codeType="full"/&gt;
&lt;/select&gt;</pre>		    			
		    		</td>
		    		<td>
		    			<select onchange="alert(this.value);">
		    				<option value="">선택하세요.</option>
		    				<gc:options group="US0005" codeType="full"/>
		    			</select>
		    		</td>
		    		<td>
		    			fullcode가 사용됨
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
<pre>&lt;select onchange="alert(this.value);"&gt;
	&lt;option value=""&gt;선택하세요.&lt;/option&gt;
	&lt;gc:options group="US0005" value="&#36;{examStr9}"/&gt;
&lt;/select&gt;</pre>		    			
		    		</td>
		    		<td>
		    			<select onchange="alert(this.value);">
		    				<option value="">선택하세요.</option>
		    				<gc:options group="US0005" value="${examStr9}"/>
		    			</select>
		    		</td>
		    		<td>
		    			normal 코드가 사용되고 설정값이 &#36;{examStr9}의 값인 02 지정됨.
		    		</td>
		    	</tr>
		    	</table>
		    </li>
		</ul>
	</td>
</tr>

<tr style="background:white">
	<td valign="top">
		&lt;gc:radioButtons group="코드그룹" name="이름" [codeType="코드형식"] [value="코드값"] [extra="추가렌더링"] [filter="포함코드필터"] [excludeFilter="제외코드필터"]/&gt;
	</td>
	<td>
		코드그룹의 내용을 &lt;select&gt; 태그에서 사용하는  &lt;option&gt; 태그의 형식으로 출력합니다.<br/>
		<ul type="1">
			<li>이름은 출력되는 라디오버튼의 폼엘리먼드 이름을 지정할 수 있습니다.</li>
			<li>코드형식은 value에 할당 될 코드값의 형식으로  normal 이나 full 둘중 하나를 지정 할 수 있으며, 생략시 normal 형식이 사용됩니다.</li>
			<li>코드 값은 checked 되어 있어야할 코드값을 지정 할 수 있으며, 생략시 checked 되는 코드 값이 없습니다.</li>
			<li>추가렌더링은 &lt;input type='radio' value='' {추가렌더링}&gt; 의 위치에 추가로 출력할 내용을 지정하는 곳으로, 태그의 이벤트나 스타일 등의 추가 속성을 설정 할 수 있습니다.</li>
			<li>나열방향은 라디오버튼이 나열되는 방향을 지정하는 값으로  h(가로나열)혹은 v(세로나열)를 지정 할 수 있으며, 생략시 h로 사용됩니다.</li>
			<li>filter 및 excludeFilter 는 와일드 카드 * 나 ? 를 사용한 문자열로 지정하며, 각 코드에 설정된 태그를 판별하여, 리스트에 포함하거나 제외 시킵니다. </li>
		</ul>
		
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;gc:radioButtons name="radioTest1" group="US0005" extra=" onclick=&amp;quot;alert(this.value + ' ' + this.checked)&amp;quot;"/&gt;
		    		</td>
		    		<td>
		    			<gc:radioButtons name="radioTest1" group="US0005" extra=" onclick=&quot;alert(this.value + ' ' + this.checked)&quot;"/>
		    		</td>
		    		<td>
		    			가로방향 나열
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;gc:radioButtons name="radioTest1" group="US0005" value="&#36;{examStr9}" extra=" onclick=&amp;quot;alert(this.value + ' ' + this.checked)&amp;quot;"/&gt;
		    		</td>
		    		<td>
		    			<gc:radioButtons name="radioTest1" group="US0005" value="${examStr9}" extra=" onclick=&quot;alert(this.value + ' ' + this.checked)&quot;"/>
		    		</td>
		    		<td>
		    			나열 방향은 세로나열(v), 설정값은 &#36;{examStr9}의 값인 02 지정됨
		    		</td>
		    	</tr>
		    	</table>
		    </li>
		</ul>
	</td>
</tr>

<tr style="background:white">
	<td valign="top">
		&lt;gc:checkBoxs group="코드그룹" name="이름" [codeType="코드형식"] [value="코드값"] [extra="추가렌더링"] [filter="포함코드필터"] [excludeFilter="제외코드필터"]/&gt;
	</td>
	<td>
		코드그룹의 내용을 &lt;input type="checkbox" &gt; 태그의 형식으로 출력합니다.<br/>
		<ul type="1">
			<li>이름은 출력되는 체크박스의 폼엘리먼트 이름을 지정할 수 있습니다.</li>
			<li>코드형식은 value에 할당 될 코드값의 형식으로  normal 이나 full 둘중 하나를 지정 할 수 있으며, 생략시 normal 형식이 사용됩니다.</li>
			<li>코드 값은 checked 되어 있어야할 코드값을 지정 할 수 있으며, Iterable인터페이스 구현체(List, Set, Queue등)이거나 배열인경우 다중 선택, 그외의 경우는 단일 선택의 값으로 사용되며, 생략시 checked 되는 코드 값이 없습니다.</li>
			<li>추가렌더링은 &lt;input type='checkbox' value='' {추가렌더링}&gt; 의 위치에 추가로 출력할 내용을 지정하는 곳으로, 태그의 이벤트나 스타일 등의 추가 속성을 설정 할 수 있습니다.</li>
			<li>나열방향은 체크박스가 나열되는 방향을 지정하는 값으로  h(가로나열)혹은 v(세로나열)를 지정 할 수 있으며, 생략시 h로 사용됩니다.</li>
			<li>filter 및 excludeFilter 는 와일드 카드 * 나 ? 를 사용한 문자열로 지정하며, 각 코드에 설정된 태그를 판별하여, 리스트에 포함하거나 제외 시킵니다. </li>
		</ul>
		
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;gc:checkBoxs name="checkBoxTest1" group="US0005" extra=" onclick=&amp;quot;alert(this.value + ' ' + this.checked)&amp;quot;"/&gt;
		    		</td>
		    		<td>
		    			<gc:checkBoxs name="checkBoxTest1" group="US0005" extra=" onclick=&quot;alert(this.value + ' ' + this.checked)&quot;"/>
		    		</td>
		    		<td>
		    			가로방향 나열
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;gc:checkBoxs name="checkBoxTest2" group="US0005" value="&#36;{examStr9}" extra=" onclick=&amp;quot;alert(this.value + ' ' + this.checked)&amp;quot;"/&gt;
		    		</td>
		    		<td>
		    			<gc:checkBoxs name="checkBoxTest2" group="US0005" value="${examList2}" extra=" onclick=&quot;alert(this.value + ' ' + this.checked)&quot;"/>
		    		</td>
		    		<td>
		    			나열 방향은 세로나열(v), 설정값은 &#36;{examList2}의 값인 02, 04, 06이 지정됨
		    		</td>
		    	</tr>
		    	</table>
		    </li>
		</ul>
	</td>
</tr>

<tr style="background:white">
	<td valign="top">
		&lt;g:param name="속성명"&gt;설정값&lt;/g:param&gt;
	</td>
	<td>
		커스텀 태그의 속성값을 태그 바디의 내용으로 구성하여 지정 하고자 할 때 사용합니다.<br/>
		커스텀 태그의 속성값으로 기술하기에는 복잡하거나 여러줄에 걸처진 표현 등을 처리 하기위해 만들어 졌습니다.<br/> 
		이 태그는 &lt;g: &lt;gc: &lt;gm: 등의 추가 커스탬 태스 안에서만 사용 가능합니다.<br/>
		<ul type="1">
			<li>속성명은 설정하고자 하는 상위 태그의 속성 이름을 지정합니다.</li>
			<li>속성값은 상위태그에 설정할 속성의 값을 지정합니다.</li>
		</ul>
		
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
<pre>&lt;gc:checkBoxs name="checkBoxTest1" group="US0005"&gt;
	&lt;g:param name="extra">onClick="if(this.checked) alert(this.value)"&lt;/g:param&gt;
&lt;/gc:checkBoxs&gt;</pre>
		    		</td>
		    		<td>
		    			<gc:checkBoxs name="checkBoxTest1" group="US0005">
		    				<g:param name="extra">onClick="if(this.checked) alert(this.value)"</g:param>
		    			</gc:checkBoxs>
		    		</td>
		    		<td>
		    		</td>
		    	</tr>
		    	</table>
		    </li>
		</ul>
	</td>
</tr>

<tr style="background:white">
	<td valign="top">
		&lt;gc:text code="코드" [group="코드그룹"] [codeTye="코드타입"] [naMessage="코드 미존재시 표시메세지"]&gt;<br/>
		&lt;gc:html code="코드" [group="코드그룹"] [codeTye="코드타입"] [naMessage="코드 미존재시 표시메세지"]&gt;<br/>
		&lt;gc:string code="코드" [group="코드그룹"] [codeTye="코드타입"] [naMessage="코드 미존재시 표시메세지"]&gt;<br/>
		&lt;gc:tagAttb code="코드" [group="코드그룹"] [codeTye="코드타입"] [naMessage="코드 미존재시 표시메세지"]&gt;<br/>
		&lt;gc:tagBody code="코드" [group="코드그룹"] [codeTye="코드타입"] [naMessage="코드 미존재시 표시메세지"]&gt;<br/>
		&lt;gc:tastring code="코드" [group="코드그룹"] [codeTye="코드타입"] [naMessage="코드 미존재시 표시메세지"]&gt;<br/>
		&lt;gc:qs code="코드" [group="코드그룹"] [codeTye="코드타입"] [naMessage="코드 미존재시 표시메세지"] [charSet="케릭터셋"]&gt;<br/>
	</td>
	<td>
		코드를 코드가 의미하는 텍스트로 표현해 줍니다.<br/>
		&lt;gc: 뒷부분의 text, html, string, tagAttb, tagBody, tastring, qs 는 &lt;g: 의 그것과 동일한 의미 입니다.<br/> 
		code속성을 생략하면 태그바디의 내용이 코드로 사용됩니다.<br/>
		<ul type="1">
			<li>코드그룹은 해당 코드의 소속 그룹으로 코드타입 notmal일때만 유효합니다.</li>
			<li>코드타입은 normal 혹은 full 일 수 있으며, 대상 코드의 형식을 구분해 줍니다. 생략하면 주어진 코드의 길이를 기준으로 자동 판별합니다.</li>
			<li>코드 미존재시 표시메세지 는 해당 코드가 존재하지 않을때 출력할 텍스트 입니다. 생략시 기본 출력은 "Unknown Code {코드} in group {그룹}" 입니다.
		</ul>
		
		<ul type="square">
			<li>
				예제
		    	<table cellspacing="1" cellpadding="4" style="background:gray">
		    	<tr style="background:white">
		    		<td align="center">
		    			태그
		    		</td>
		    		<td align="center">
		    			표현된 값
		    		</td>
		    		<td align="center">
		    			비고
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;gc:html group="US0005" code="02"/&gt;
		    		</td>
		    		<td>
		    			<gc:html group="US0005" code="02"/>
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;gc:html code="US000502"/&gt;
		    		</td>
		    		<td>
		    			<gc:html code="US000502"/>
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;gc:html group="US0005" code="99"/&gt;
		    		</td>
		    		<td>
		    			<gc:html group="US0005" code="99"/>
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	<tr style="background:white">
		    		<td>
		    			&lt;gc:html code="US000599" naMessage="자료오류."/&gt;
		    		</td>
		    		<td>
		    			<gc:html code="US000599" naMessage="자료오류."/>
		    		</td>
		    		<td>
		    			&nbsp;
		    		</td>
		    	</tr>
		    	</table>
		    </li>
		</ul>
	</td>
</tr>
</table>
<gm:html value="test.name"/>
</body>
</html>