package com.omp.tw.commons.util;

import pat.neocore.util.MiscEncoder;


/**
 * 타이완 아이디 유효성 검증
 * @author pat
 *
 */
public class Discernment {
	
	/**
	 * @param rocID
	 * @return boolean
	 */
	public static boolean isROCID(String rocID){
		boolean result = false;
		rocID = rocID.toUpperCase();

		String id1 = "";
		int id0 = 0;
		if(rocID.length() == 10){
			if(("A".equals(rocID.charAt(0)) && "A".equals(rocID.charAt(1))) || ("A".equals(rocID.charAt(0)) && "Z".equals(rocID.charAt(1)))){
				result = false;
			}else{
				if("1".equals(String.valueOf(rocID.charAt(1))) || "2".equals(String.valueOf(rocID.charAt(1)))){
					id1 = String.valueOf(rocID.charAt(0));
					if("A".equals(id1)) id0 =1;
					else if("B".equals(id1)) id0 = 10;
					else if("C".equals(id1)) id0 = 19;
					else if("D".equals(id1)) id0 = 28;
					else if("E".equals(id1)) id0 = 37;
					else if("F".equals(id1)) id0 = 46;
					else if("G".equals(id1)) id0 = 55;
					else if("H".equals(id1)) id0 = 64;
					else if("I".equals(id1)) id0 = 39;
					else if("J".equals(id1)) id0 = 73;
					else if("K".equals(id1)) id0 = 82;
					else if("L".equals(id1)) id0 = 2;
					else if("M".equals(id1)) id0 = 11;
					else if("N".equals(id1)) id0 = 20;
					else if("O".equals(id1)) id0 = 48;
					else if("P".equals(id1)) id0 = 29;
					else if("Q".equals(id1)) id0 = 38;
					else if("R".equals(id1)) id0 = 47;
					else if("S".equals(id1)) id0 = 56;
					else if("T".equals(id1)) id0 = 65;
					else if("U".equals(id1)) id0 = 74;
					else if("V".equals(id1)) id0 = 83;
					else if("W".equals(id1)) id0 = 21;
					else if("X".equals(id1)) id0 = 3;
					else if("Y".equals(id1)) id0 = 12;
					else if("Z".equals(id1)) id0 = 30;
					int id2 = id0 + Integer.parseInt(String.valueOf(rocID.charAt(1)))*8
								  + Integer.parseInt(String.valueOf(rocID.charAt(2)))*7
								  + Integer.parseInt(String.valueOf(rocID.charAt(3)))*6
								  + Integer.parseInt(String.valueOf(rocID.charAt(4)))*5
								  + Integer.parseInt(String.valueOf(rocID.charAt(5)))*4
								  + Integer.parseInt(String.valueOf(rocID.charAt(6)))*3
								  + Integer.parseInt(String.valueOf(rocID.charAt(7)))*2
								  + Integer.parseInt(String.valueOf(rocID.charAt(8)))*1
								  + Integer.parseInt(String.valueOf(rocID.charAt(9)))*1;
					if(id2 % 10 == 0){
						result = true;
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Sample ROCID
		String userId = "Z100234759";
		
		System.out.println("RESULT : " + Discernment.isROCID(userId));
		System.out.println(MiscEncoder.encodeEscapeString("<script>"));
		System.out.println(MiscEncoder.decodeEscapeString("\u003cscript\u003e"));
		
	}
}
