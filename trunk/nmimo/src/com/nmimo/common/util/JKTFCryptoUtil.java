package com.nmimo.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import JKTFCrypto.JKTFCrypto;


/**
 * <pre>
 * JKTF Crypto Util
 * </pre>
 * @file JKTFCryptoUtil.java
 * @since 2013. 5. 31.
 * @author Rejoice
 */
public class JKTFCryptoUtil {

	public static final int CIPHER_SEED_ALGO = 1;
	public static final int CIPHER_3DES_ALGO = 2;
	public static final int CIPHER_DES_ALGO = 3;
	public static final int CIPHER_AES_128 = 4;
	public static final String PSSO_SECRETKEY = "PSSO_enckey";
	
	/**
	 * <pre>
	 * Encrypt
	 * param 
	 *  - type : Encrypt Type
	 *  - data : Encrypt Data
	 * </pre>
	 * @param str
	 * @return String encData 
	 */
	public static String encryptOnlyData(int type, String data){
		JKTFCrypto crypto = new JKTFCrypto();
		crypto.CreateSessionKey();
		crypto.setCipherAlgorithm(type);
		
		return crypto.EncryptData(data.getBytes());
	}
	
	/**
	 * <pre>
	 * Encrypt
	 * param 
	 *  - type : Encrypt Type
	 *  - data : Encrypt Data
	 * </pre>
	 * @param str
	 * @return String [{[0] : encSessionKey, [1] : encData} ] 
	 */
	public static String[] encryptCipher(int type, String data){
		JKTFCrypto crypto = new JKTFCrypto();
		String encSecretKey = crypto.CreateSessionKey();
		crypto.setCipherAlgorithm(type);
		String encData [] = {encSecretKey , crypto.EncryptData(data.getBytes())};
		return encData;
	}
	
	/**
	 * <pre>
	 * Decrypt
	 * param
	 *  - type			: Decrypt Type
	 *  - encSecretKey	: Encrypt SecretKey
	 *  - data			: Decrypt Data
	 * </pre>
	 * @param key
	 * @param str
	 * @return String : Decrypt Data
	 */
	public static String decryptCipher(int type, String encSecretKey, String data){
		JKTFCrypto crypto = new JKTFCrypto();
		crypto.CreateSessionKey();
		crypto.DecryptSessionKey(encSecretKey);
		crypto.setCipherAlgorithm(type);
		return new String(crypto.DecryptData(data));
	}
	
	/**
	 * <pre>
	 * HASH_SHA256
	 * [nMIMO] 단반향 암호화
	 * </pre>
	 * @param str
	 * @return String : PW 암호화
	 */
	public static String hashAlgo256(String data){
		JKTFCrypto crypto = new JKTFCrypto ();
		crypto.setHashAlgorithm(JKTFCrypto.HASH_SHA256);
		return crypto.Hash(data.getBytes());
	}

	/**
	 * <pre>
	 *
	 * </pre>
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static HashMap<String, String> pssoEncSecretKey(HttpServletRequest request) throws UnsupportedEncodingException{
		HashMap<String, String> map = new HashMap<String, String>();
		Cookie[] c = request.getCookies();
		for(int i=0; i<c.length; i++){
			String name = URLDecoder.decode(c[i].getName(), "UTF-8");
			String value = URLDecoder.decode(c[i].getValue(), "UTF-8");
			if(JKTFCryptoUtil.PSSO_SECRETKEY.equals(name)){
				map.put(name, value);
			}else{
				map.put(name, value);
			}
		}
		return map;
	}
	
	/**
	 * <pre>
	 * Test Main
	 * </pre>
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String data = "test";
		System.out.println("=====================");
		System.out.println("Data : " + data);
		System.out.println("=====================");
		System.out.println();
		System.out.println("# HASH_SHA256 #");
		System.out.println("encData : " + JKTFCryptoUtil.hashAlgo256(data));
		System.out.println();
		System.out.println("# CIPHER_AES_128 #");
		String enc128 [] = JKTFCryptoUtil.encryptCipher(CIPHER_AES_128, data);
		System.out.println("key : " + enc128[0]);
		System.out.println("encData : " + enc128[1]);
		System.out.println("decData : " + JKTFCryptoUtil.decryptCipher(CIPHER_AES_128, enc128[0], enc128[1]));
		System.out.println();
		System.out.println("# CIPHER_3DES_ALGO #");
		String enc3des [] = JKTFCryptoUtil.encryptCipher(CIPHER_3DES_ALGO, data);
		System.out.println("key : " + enc3des[0]);
		System.out.println("encData : " + enc3des[1]);
		System.out.println("decData : " + JKTFCryptoUtil.decryptCipher(CIPHER_3DES_ALGO, enc3des[0], enc3des[1]));
		System.out.println();
		System.out.println("# CIPHER_DES_ALGO #");
		String encDes [] = JKTFCryptoUtil.encryptCipher(CIPHER_DES_ALGO, data);
		System.out.println("key : " + encDes[0]);
		System.out.println("encData : " + encDes[1]);
		System.out.println("decData : " + JKTFCryptoUtil.decryptCipher(CIPHER_DES_ALGO, encDes[0], encDes[1]));
		System.out.println();
		System.out.println("# CIPHER_SEED_ALGO #");
		String encSeed [] = JKTFCryptoUtil.encryptCipher(CIPHER_SEED_ALGO, data);
		System.out.println("key : " + encSeed[0]);
		System.out.println("encData : " + encSeed[1]);
		System.out.println("decData : " + JKTFCryptoUtil.decryptCipher(CIPHER_SEED_ALGO, encSeed[0], encSeed[1]));
		System.out.println();
	
		String aes = JKTFCryptoUtil.encryptOnlyData(CIPHER_AES_128, data);
		System.out.println("aes : " + aes);

		
	}
}
