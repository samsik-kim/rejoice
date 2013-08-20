package com.nmimo.common.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import JKTFCrypto.JKTFSymmetricKey;

public class KTFCryptoUtil{
	
	private Log LOG = LogFactory.getLog(getClass());
	
	private JKTFSymmetricKey jktfCrypto = null;

	public KTFCryptoUtil() {
		try {
			jktfCrypto = new JKTFSymmetricKey();
			jktfCrypto.InitSecretKey();
		} catch (Throwable t) {
			if (LOG.isFatalEnabled()) {
				LOG.fatal(t.getMessage(), t);
			}
		}
	}
	
	/**
	 * 대칭키 알고리즘 설정
	 * 1: SEED 알고리즘
	 * 4: AES128 알고리즘
	 * 
	 * @param algorithm	the algorithm
	 */
	public void setCipherAlgorithm(int algorithm) {
		jktfCrypto.setCipherAlgorithm(algorithm);
		
		if (LOG.isInfoEnabled()) {
			String name = null;
			switch (algorithm) {
			case 1 : name = "SEED"; break;
			case 4 : name = "AES128"; break;
			}
			LOG.info("cipher algorithm = " + name);
		}
	}
	
	/**
	 * 해쉬 알고리즘 설정
	 * 0x0100(256) : SHA1 알고리즘
	 * 0x0200(512) : SHA256 알고리즘
	 * 
	 * @param algorithm	the algorithm
	 */
	public void setHashAlgorithm(int algorithm) {
		jktfCrypto.setHashAlgorithm(algorithm);
		
		if (LOG.isInfoEnabled()) {
			String name = null;
			switch (algorithm) {
			case 0x0100 : name = "SHA1"; break;
			case 0x0200 : name = "SHA256"; break;
			}
			LOG.info("hash algorithm = " + name);
		}
	}

	public String encryptData(String source) {
		String encrypted = "";
		if (jktfCrypto != null && source != null) {
			encrypted = jktfCrypto.EncryptData(source.getBytes());
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("source = ****, encrypted = " + encrypted);
		}
		
		return encrypted;
	}

	public String decryptData(String encrypted) throws Exception {
		String decrypted = "";
		if(jktfCrypto != null && encrypted != null) {
			byte[] data = jktfCrypto.DecryptData(encrypted);

			if(data == null) {
				int errorCode = jktfCrypto.getErrorCode();
				throw new Exception("Decrypt Error : " + errorCode);
			}
			decrypted = new String(data);
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("encrypted = " + encrypted + ", decrypted = ****");
		}

		return decrypted;
	}
	
	public String hash(String source) {
		String hash = "";
		if (jktfCrypto != null && source != null) {
			hash = jktfCrypto.Hash(source.getBytes());
		}
		
		if (LOG.isDebugEnabled()) {
			LOG.debug("source = ****, hash = " + hash);
		}
		
		return hash;
	}
	public static void main(String[] args) {
//		System.getProperties().list(System.out);
//		System.out.println(System.getProperty("java.library.path"));
		
		KTFCryptoUtil kt = new KTFCryptoUtil();
		String str = "test";
		kt.setHashAlgorithm(0x0200);
		System.out.println(kt.hash(str));
		kt.setCipherAlgorithm(1);
		String enc = kt.encryptData(str);
		System.out.println("enc : " + enc);
		try {
			System.out.println("dnc : " + kt.decryptData(enc));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
