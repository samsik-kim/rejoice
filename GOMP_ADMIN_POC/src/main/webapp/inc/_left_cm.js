str = ''
str += '<h2><span>컨텐츠관리</span></h2>' 
str += '<ul class="s_menu">' 
	str += '<li><a href="#">카테고리관리</a>' 
		str += '<ul class="n_menu">' 
			str += '<li><a href="#">SC 메뉴관리</a></li>' 
		str += '</ul>' 
	str += '</li>' 
	str += '<li><a href="#">추천관리</a></li>'  
	str += '<li><a href="#">초보자용관리</a></li>'  
	str += '<li><a href="#">유료BEST</a></li>'  
	str += '<li><a href="#">무료BEST</a></li>'  
	str += '<li><a href="#">신규관리</a></li>'  
	str += '<li><a href="#">배너관리</a></li>'  
	str += '<li><a href="#">검색어관리</a></li>'  
	str += '<li><a href="#">서비스 점검관리</a></li>'  
str += '</ul> ' 

document.write(str)