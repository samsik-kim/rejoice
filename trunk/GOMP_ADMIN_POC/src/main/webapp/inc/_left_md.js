str = ''
str += '<h2><span>단말관리</span></h2>' 
str += '<ul class="s_menu">' 
	str += '<li><a href="#">서비스단말관리</a>' 
		str += '<ul class="n_menu">' 
			str += '<li><a href="#">서비스단말리스트</a></li>' 
			str += '<li><a href="#">단말 Mapping 관리</a></li>' 
			str += '<li><a href="#">단말 Mapping 처리결과</a></li>' 
		str += '</ul>' 
	str += '</li>' 
	str += '<li><a href="#">SC 버전관리</a></li>'  
	str += '<li><a href="#">테스트ID관리</a></li>'  
	str += '<li><a href="#">검증툴 License관리</a></li>'  
str += '</ul> ' 

document.write(str)