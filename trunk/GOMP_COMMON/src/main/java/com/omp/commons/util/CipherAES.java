package com.omp.commons.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
/**
 * AES 암호화 유틸리티
 * @author nefer 2009-12-21
 *
 */
public class CipherAES {
    
    private static final String KEY = "F82799142FA202C1";

    // 알고리즘 CBC 타입은 결과가 계속 변경되고 IV값이 필요하기 때문에 ECB로 사용함
    private static final String TRANSFORM = "AES/ECB/PKCS5Padding";
    
    
    
    /**
     * 암호화
     * @param str
     * @return
     * @throws Exception
     */
    public static String encryption(String str)throws Exception{
    	return encrypt(str);
    }
    
    /**
     * 복호화
     * @param str
     * @return
     * @throws Exception
     */
    public static String decryption(String str) throws Exception{
    	return decrypt(str);
    }
    
    /**
     * 암호화
     * @param plainText 원본 문자열
     * @return 암호화 문자열
     * @throws Exception
     */
    private static String encrypt(String plainText) throws Exception {
        if (null != plainText) {
            // 128bit AES 키 생성기
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            
            // keySpac 생성
            byte[] raw = KEY.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            
            // 암호화 모듈 인스턴스 생성 및 초기화
            Cipher cipher = Cipher.getInstance(TRANSFORM);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            
            // 암호화
            byte[] encrypted = cipher.doFinal(plainText.getBytes());
            return asHex(encrypted);
        } else {
            return "";
        }
    }
    
    /**
     * 복호화
     * @param cipherText 암호화된 문자열
     * @return 복호화된 문자열
     * @throws Exception
     */
    private static String decrypt(String cipherText) throws Exception {
        if (null != cipherText) {
            // 128bit 키 생성기
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);

            // keySpac 생성
            byte[] raw = KEY.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            
            // 암호화 모듈 인스턴스 생성 및 초기화
            Cipher cipher = Cipher.getInstance(TRANSFORM);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
            
            // 복호화
            byte[] original = cipher.doFinal(fromString(cipherText));
            String originalString = new String(original);
            return originalString;
        } else {
            return "";
        }
    }

    /**
     * byte 배열을 Hex 문자열로 반환
     * @param buf byte 배열
     * @return
     */
    private static String asHex(byte buf[]) {
        StringBuffer strbuf = new StringBuffer(buf.length * 2);
        int i;

        for (i = 0; i < buf.length; i++) {
            if (((int) buf[i] & 0xff) < 0x10)
                strbuf.append("0");

            strbuf.append(Long.toString((int) buf[i] & 0xff, 16));
        }

        return strbuf.toString();
    }

    /*
     * hex 문자열을 byte 배열로 반환
     */
    private static byte[] fromString(String hex) {
        int len = hex.length();
        byte[] buf = new byte[((len + 1) / 2)];

        int i = 0, j = 0;
        if ((len % 2) == 1)
            buf[j++] = (byte) fromDigit(hex.charAt(i++));

        while (i < len) {
            buf[j++] = (byte) ((fromDigit(hex.charAt(i++)) << 4) | fromDigit(hex
                    .charAt(i++)));
        }
        return buf;
    }

    /*
     * char를 Hex 번호로 반환
     */
    private static int fromDigit(char ch) {
        if (ch >= '0' && ch <= '9')
            return ch - '0';
        if (ch >= 'A' && ch <= 'F')
            return ch - 'A' + 10;
        if (ch >= 'a' && ch <= 'f')
            return ch - 'a' + 10;

        throw new IllegalArgumentException("invalid hex digit '" + ch + "'");
    }
    
    public static void main(String[] args) throws Exception {
    	//String data = "pid=00004&logGB=Y";
    	//String data = "cmd=authEmailChange&param=aqwsde@nate.com";
    	String data = "123qwe";
        String encryption = CipherAES.encryption(data);
        String decryption = CipherAES.decryption("3d4f2bf07dc1be38b20cd6e46949a1071f9d0e3d");
        System.out.println("원본 데이터 : " + data);
        System.out.println("암호화 : " + encryption);
        System.out.println("복호화 : " + decryption);
    	//String num1 = "4938170000001008";
    	//String num = purchase.getCardNum().substring(0, 3) + "-" + purchase.getCardNum().substring(3, 8) + "-" + "****-****";
    	//System.out.println(num1.substring(0, 4)+"-"+num1.substring(4, 8)+"-****-****");
    	//System.out.println(DateUtil.getSysDate());
        
    }
}
