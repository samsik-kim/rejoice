package com.nmimo.common.util;

import JKTFCrypto.JKTFCrypto;


/**
 * <pre>
 * User 정보를 위한 Util 클래스
 * </pre>
 * @file UserUtil.java
 * @since 2013. 6. 3.
 * @author Administrator
 */
public class UserUtil {

	public static final int USER_PW_64 = 64;
	
	/**
	 * <pre>
	 * 비밀번호 암호화 생성
	 * 1. AES128 암호화 => 0~9 + a~Z 랜덤 40 문자열 생성
	 * 2. 단방향 암호화 => 랜덤문자열 + "|" + 비밀번호
	 * </pre>
	 * @param pw
	 * @return String [암호화된 비밀번호, AES128랜덤 문자열]
	 */
	public static String [] encryptPw(String pw){
		
		//AES128 암호화 문자 생성
		String aesData = JKTFCryptoUtil.encryptOnlyData(JKTFCryptoUtil.CIPHER_AES_128, RandomString.getString(USER_PW_64));
		
		//HASH 암호화 생성
		String newPw = JKTFCryptoUtil.hashAlgo256(aesData + "|" + pw);
		
		String resultStr [] = {newPw , aesData};
		return resultStr;
//		return null;
	}
	
	/**
	 * <pre>
	 * 1. pw = 비밀번호
	 * 2. encSecretKey = 암호화 키
	 * </pre>
	 * @param pw
	 * @param encSecretKey
	 * @return String hashAlgo256 Data
	 */
	public static String encryptPw(String pw, String encSecretKey){
		JKTFCrypto crypto = new JKTFCrypto ();
		crypto.setHashAlgorithm(JKTFCrypto.HASH_SHA256);
		String data = encSecretKey + "|" + pw;
		return crypto.Hash(data.getBytes());
//		return JKTFCryptoUtil.hashAlgo256(encSecretKey + "|" + pw);
	}
	

	public static void main(String[] args) {
		String a[] = UserUtil.encryptPw("1234");
		System.out.println("AES128 [" + a[1].length() + "] : " + a[1]);
		System.out.println("HashAlgo256 [" + a[0].length() + "] : " + a[0]);
	}
}
