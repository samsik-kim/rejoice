str = ''
str += '<h2><span>고객지원</span></h2>' 
str += '<ul class="s_menu">'
	str += '<li><a href="#">사용후기관리</a></li>' 
	str += '<li><a href="#">FAQ 관리</a>' 
		str += '<ul class="n_menu">' 
			str += '<li><a href="#">SC FAQ카테고리 관리</a></li>' 
			str += '<li><a href="#">SC FAQ</a></li>' 
			str += '<li><a href="#">개발자 FAQ카테고리 관리</a></li>' 
			str += '<li><a href="#">개발자 FAQ</a></li>' 
		str += '</ul>' 
	str += '</li>' 
	str += '<li><a href="#">공지사항 관리</a>' 
		str += '<ul class="n_menu">' 
			str += '<li><a href="#">WEB 공지</a></li>' 
			str += '<li><a href="#">SC 공지</a></li>' 
			str += '<li><a href="#">개발자 공지</a></li>' 
		str += '</ul>' 
	str += '</li>' 
	str += '<li><a href="#">Q&A관리</a>' 
		str += '<ul class="n_menu">' 
			str += '<li><a href="#">SC Q&A카테고리 관리</a></li>' 
			str += '<li><a href="#">SC Q&A</a></li>' 
			str += '<li><a href="#">개발자 Q&A카테고리 관리</a></li>' 
			str += '<li><a href="#">개발자 Q&A</a></li>' 
		str += '</ul>' 
	str += '</li>'
	str += '<li><a href="#">이벤트</a></li>' 	
str += '</ul> ' 

document.write(str)