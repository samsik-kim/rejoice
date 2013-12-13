package com.omp.dev.common.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtil3DES {
    private final static byte[] tdesKeyData = {
        (byte)0xDF, (byte)0xA2, (byte)0xCB, (byte)0xEE, (byte)0x55,
        (byte)0x24, (byte)0xAF, (byte)0xDF, (byte)0xFC, (byte)0xAA,
        (byte)0x8F, (byte)0x4E, (byte)0xEB, (byte)0x5A, (byte)0x90,
        (byte)0x4F, (byte)0xD0, (byte)0xE5, (byte)0x74, (byte)0x67,
        (byte)0x87, (byte)0x47, (byte)0xB9, (byte)0xC4 };

    private final static byte[] myIV = {
        (byte)200, (byte)700, (byte)25, (byte)33,
        (byte)90, (byte)44, (byte)58, (byte)107 };


    public static String encrypt3(String data) throws Exception {
        if(data == null || data.length() == 0) {
            return "";
        }
        // String tdesKeyDataString = "1234567890abcdefghijklmn";
        // byte[] tdesKeyData = tdesKeyDataString.getBytes();

        Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        SecretKeySpec myKey = new SecretKeySpec(tdesKeyData, "DESede");
        IvParameterSpec ivspec = new IvParameterSpec(myIV);

        c3des.init(Cipher.ENCRYPT_MODE, myKey, ivspec);
        byte[] inputBytes1 = data.getBytes("UTF8");
        byte[] outputBytes1 = c3des.doFinal(inputBytes1);

        sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
        return encoder.encode(outputBytes1);
    }


    public static String decrypt3(String data) throws Exception {

        if(data == null || data.length() == 0) {
            return "";
        }
//         String tdesKeyDataString = "1234567890abcdefghijklmn";
//         byte[] tdesKeyData = tdesKeyDataString.getBytes();

        Cipher c3des = Cipher.getInstance("DESede/CBC/PKCS5Padding");
        SecretKeySpec myKey = new SecretKeySpec(tdesKeyData, "DESede");
        IvParameterSpec ivspec = new IvParameterSpec(myIV);

        c3des.init(Cipher.DECRYPT_MODE, myKey, ivspec);
        sun.misc.BASE64Decoder decoder = new sun.misc.BASE64Decoder();
        byte[] inputBytes1 = decoder.decodeBuffer(data);
        byte[] outputBytes2 = c3des.doFinal(inputBytes1);
        return new String(outputBytes2, "UTF8");
    }


    public static void main(String[] args) throws Exception {
        // DES 방식의 암복호화
        String source = "ABCDE1234가나다";
        String encrypt = encrypt3(source);
        String decrypt = decrypt3(encrypt);

        System.out.println("#source=" + source);
        System.out.println("#encrypt=" + encrypt);
        System.out.println("#decrypt=" + decrypt);
    }

}
