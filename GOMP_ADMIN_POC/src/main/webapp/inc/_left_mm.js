str = ''
str += '<h2><span>회원관리</span></h2>' 
str += '<ul class="s_menu">' 
	str += '<li><a href="#">사용자 관리</a>' 
		str += '<ul class="n_menu">' 
			str += '<li><a href="#">회원정보관리</a></li>' 
			str += '<li><a href="#">구매내역</a></li>' 
			str += '<li><a href="#">탈퇴회원내역</a></li>' 
			str += '<li><a href="#">징계회원관리</a></li>'  
		str += '</ul>' 
	str += '</li>' 
	str += '<li><a href="#">개발자 관리</a>' 
		str += '<ul class="n_menu">' 
			str += '<li><a href="#">개발자 정보관리</a></li>' 
			str += '<li><a href="#">전환회원내역</a></li>' 
			str += '<li><a href="#">탈퇴회원내역</a></li>' 
			str += '<li><a href="#">상품이관관리</a></li>'  
		str += '</ul>' 
	str += '</li>' 
str += '</ul> ' 

document.write(str)