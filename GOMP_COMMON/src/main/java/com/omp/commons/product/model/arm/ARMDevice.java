package com.omp.commons.product.model.arm;

import javax.xml.bind.annotation.XmlAttribute;

/**
 * ARM 연동용 디바이스 정보 모델
 * @author pat
 *
 */
public class ARMDevice {

	private String model;
	private String mdn;
	private String min;
	private String imei;
	private String imsi;
	private String sn;
	private String macaddr;
	private String gdid;
	
	
	/**
	 * @return the model
	 */
	@XmlAttribute
	public String getModel() {
		return model == null ? "" : model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}
	/**
	 * @return the mdn
	 */
	public String getMdn() {
		return mdn  == null ? "" : mdn;
	}
	/**
	 * @param mdn the mdn to set
	 */
	public void setMdn(String mdn) {
		this.mdn = mdn;
	}
	/**
	 * @return the min
	 */
	public String getMin() {
		return min  == null ? "" : min;
	}
	/**
	 * @param min the min to set
	 */
	public void setMin(String min) {
		this.min = min;
	}
	/**
	 * @return the imei
	 */
	public String getImei() {
		return imei  == null ? "" : imei;
	}
	/**
	 * @param imei the imei to set
	 */
	public void setImei(String imei) {
		this.imei = imei;
	}
	/**
	 * @return the imsi
	 */
	public String getImsi() {
		return imsi  == null ? "" : imsi;
	}
	
	/**
	 * @param imsi the imsi to set
	 */
	public void setImsi(String imsi) {
		this.imsi = imsi;
	}
	/**
	 * @return the sn
	 */
	public String getSn() {
		return sn  == null ? "" : sn;
	}
	/**
	 * @param sn the sn to set
	 */
	public void setSn(String sn) {
		this.sn = sn;
	}
	/**
	 * @return the macaddr
	 */
	public String getMacaddr() {
		return macaddr  == null ? "" : macaddr;
	}
	/**
	 * @param macaddr the macaddr to set
	 */
	public void setMacaddr(String macaddr) {
		this.macaddr = macaddr;
	}
	/**
	 * @return the gdid
	 */
	public String getGdid() {
		return gdid == null ? "": gdid;
	}
	/**
	 * @param gdid the gdid to set
	 */
	public void setGdid(String gdid) {
		this.gdid = gdid;
	}

	
}
