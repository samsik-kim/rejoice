package com.omp.commons.util;

import java.io.Serializable;
import java.security.cert.X509Certificate;
import java.util.Date;

/**
 * APK Infomation Model
 * @author pat
 *
 */
public class APKInfomation
	implements Serializable {

	private static final long serialVersionUID	= 20110303;

	private String			versionCode;
	private String			versionName;
	private String			packageName;
	private String			minSDKVersion;
	private String			targetSDKVersion;
	private String			maxSDKVersion;
	private String[]		usesPermissions;
	private String[]		usesFeatures;
	private X509Certificate	signedKey;
	
	public X509Certificate getSignedKey() {
		return signedKey;
	}
	
	public void setSignedKey(X509Certificate signedKey) {
		this.signedKey = signedKey;
	}
	
	public String getVersionCode() {
		return versionCode;
	}
	
	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}
	
	public String getVersionName() {
		return versionName;
	}
	
	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}
	
	public String getPackageName() {
		return packageName;
	}
	
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	
	public String getMinSDKVersion() {
		return minSDKVersion;
	}
	
	public void setMinSDKVersion(String minSDKVersion) {
		this.minSDKVersion = minSDKVersion;
	}
	
	public String getTargetSDKVersion() {
		return targetSDKVersion;
	}
	
	public void setTargetSDKVersion(String targetSDKVersion) {
		this.targetSDKVersion = targetSDKVersion;
	}
	
	public String getMaxSDKVersion() {
		return maxSDKVersion;
	}
	
	public void setMaxSDKVersion(String maxSDKVersion) {
		this.maxSDKVersion = maxSDKVersion;
	}
	
	public String[] getUsesPermissions() {
		return usesPermissions;
	}
	
	public void setUsesPermissions(String[] usesPermissions) {
		this.usesPermissions = usesPermissions;
	}
	
	public String[] getUsesFeatures() {
		return usesFeatures;
	}
	
	public void setUsesFeatures(String[] usesFeatures) {
		this.usesFeatures = usesFeatures;
	}
	
	/**
	 * check apk is signed
	 * @return return true when apk is signed
	 */
	public boolean isSigned() {
		return this.signedKey != null;
	}
	
	/**
	 * check apk is debug signed
	 * @return return true when apk is debug signed
	 * @throws IllegalStateException when apk is unsigned
	 */
	public boolean isDebugSign()
		throws IllegalStateException {
		if (this.signedKey == null) {
			throw new IllegalStateException("apk is unsingned."); 
		}
		return this.signedKey.getSubjectDN().getName().indexOf("CN=Android Debug") != -1;
	}
	
	/**
	 * get signed apk's validity days from current day
	 * @return
	 * @throws IllegalStateException when apk is unsigned
	 */
	public int getSignKeyValidityDays()
		throws IllegalStateException {
		Date	now;
		Date	vfrom;
		Date	vto;
		
		if (this.signedKey == null) {
			throw new IllegalStateException("apk is unsingned."); 
		}
		now		= new Date();
		vfrom	= this.signedKey.getNotBefore();
		vto		= this.signedKey.getNotAfter();
//		사업팀이 현재날자 기준 검증 안해도 된다고 장담했슴
//		return now.before(vfrom) ? -1 : (int)((vto.getTime() - now.getTime()) / (1000*60*60*24));
		return (int)((vto.getTime() - vfrom.getTime()) / (1000*60*60*24));
	}
}
