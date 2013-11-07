/**
 * 대만 지역 제한적인 스크립트 라이브러리
 */
$(document).ready(function () {
	addValidateFunction({
		twid	: 	function(c) {
			var	rsid_i	= $(c).val();
			var	rexp	= /^[a-zA-Z0-9]{0,10}?$/;
			if (!rexp.test(rsid_i)) {
				return false;
			}
			rsid_i = rsid_i.toUpperCase();
			if (rsid_i != '') {
				check="ERROR";
				if (rsid_i.length==10) {
					if (((rsid_i.charAt(0)=="A") && (rsid_i.charAt(1)=="A"))||((rsid_i.charAt(0)=="A") && (rsid_i.charAt(1)=="Z"))){check="OK";}
				else {
					checknum1=((rsid_i.charAt(0)>="A") && (rsid_i.charAt(0)<="Z"))
					checknum2=((rsid_i.charAt(1)==1) || (rsid_i.charAt(1)==2))
					if (checknum2 && checknum1) {
						id1 = rsid_i.charAt(0)
						if (id1 == 'A') id0=1
						else if (id1 == 'B') { id0=10 ;}
						else if (id1 == 'C') { id0=19 ;}
						else if (id1 == 'D') { id0=28 ;}
						else if (id1 == 'E') { id0=37 ;}
						else if (id1 == 'F') { id0=46 ;}
						else if (id1 == 'G') { id0=55 ;}
						else if (id1 == 'H') { id0=64 ;}
						else if (id1 == 'I') { id0=39 ;}
						else if (id1 == 'J') { id0=73 ;}
						else if (id1 == 'K') { id0=82 ;}
						else if (id1 == 'L') { id0=2 ;}
						else if (id1 == 'M') { id0=11 ;}
						else if (id1 == 'N') { id0=20 ;}
						else if (id1 == 'O') { id0=48 ;}
						else if (id1 == 'P') { id0=29 ;}
						else if (id1 == 'Q') { id0=38 ;}
						else if (id1 == 'R') { id0=47 ;}
						else if (id1 == 'S') { id0=56 ;}
						else if (id1 == 'T') { id0=65 ;}
						else if (id1 == 'U') { id0=74 ;}
						else if (id1 == 'V') { id0=83 ;}
						else if (id1 == 'W') { id0=21 ;}
						else if (id1 == 'X') { id0=3 ;}
						else if (id1 == 'Y') { id0=12 ;}
						else if (id1 == 'Z') { id0=30 ;}
						id2 = id0 + rsid_i.charAt(1)*8 + rsid_i.charAt(2)*7 + rsid_i.charAt(3)*6 + rsid_i.charAt(4)*5 + rsid_i.charAt(5)*4 + rsid_i.charAt(6)*3 + rsid_i.charAt(7)*2 + rsid_i.charAt(8)*1 + rsid_i.charAt(9)*1
						if (id2 % 10 == 0 ) check="OK";
						else check="ERROR";
					}
				}
			}
			if ( rsid_i.charAt(8)>="A" && rsid_i.charAt(8)<="Z" && rsid_i.charAt(9)>="A" && rsid_i.charAt(9)<="Z"){check="OK";}
			}else{check = "ERROR";}
			if (check != "OK"){
				return false;
			}else{
				return true;
			}
		},
		checkspecial :   function(c) {
		var regExp = /[~!@\#$%^&*\()\=+|\\/:;?"<>']/gi;
		
		if (regExp.test($(c).val())) return false;
		else return true;
		},
		reqexpw	: function(c){
			var cnt=0,cnt2=1,cnt3=1;
	        for(i=0;i < $(c).val().length;i++){
                temp_pass1 = $(c).val().charAt(i);

                ttt = temp_pass1.charCodeAt(0);
                next_pass = (parseInt(temp_pass1.charCodeAt(0)))+1;
                temp_p = $(c).val().charAt(i+1);
                temp_pass2 = (parseInt(temp_p.charCodeAt(0)));
                if (temp_pass2 == next_pass) cnt2 = cnt2 + 1;
                else cnt2 = 1;
                if (temp_pass1 == temp_p) cnt3 = cnt3 + 1;
                else cnt3 = 1;

                if (cnt2 > 3) break;
                if (cnt3 > 3) break;
	        }
	        if (cnt2 > 3){
				return false;
	        }else if(cnt3 > 3){
				return false;
	        }else{
	        	return true;
	        }
		},
		reqexpwwith	: function(c){
			var str = $(c).val();
			var	rexp	= /^[a-zA-Z0-9\-\_\=\+\\\|\(\)\*\&\^\%\$\#\@\!\~\`\?\>\<\/\;\,\.\:\']{5,15}?$/;
			if (!rexp.test(str)) {
				return false;
			}
			if (!/[0-9]+/.test(str)) {
				return false;
			}
			if (!/[\-\_\=\+\\\|\(\)\*\&\^\%\$\#\@\!\~\`\?\>\<\/\;\,\.\:\']/.test(str)) {
				return false;
			}
			
			return true;
		}		
	});
});