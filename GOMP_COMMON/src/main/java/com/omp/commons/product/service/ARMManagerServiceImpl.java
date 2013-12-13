package com.omp.commons.product.service;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.omp.commons.Constants;
import com.omp.commons.product.model.arm.ARMContent;
import com.omp.commons.product.model.arm.ARMDevice;
import com.omp.commons.product.model.arm.ARMDomainReqApplicationInfo;
import com.omp.commons.product.model.arm.ARMDomainReqManagerVerificationLicense;
import com.omp.commons.product.model.arm.ARMDomainReqModifyApplicationStatus;
import com.omp.commons.product.model.arm.ARMDomainReqVerificationLicense;
import com.omp.commons.product.model.arm.ARMDomainResManagerVerificationLicense;
import com.omp.commons.product.model.arm.ARMDomainResModifyApplicationStatus;
import com.omp.commons.product.model.arm.ARMDomainResRegisterApplicationInfo;
import com.omp.commons.product.model.arm.ARMDomainResVerificationLicense;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.HTTPUtil;
import com.omp.commons.util.ThreadSession;
import com.omp.commons.util.config.ConfigProperties;

/**
 * ARM 처리 서비스 구현체
 * @author pat
 *
 */
public class ARMManagerServiceImpl extends AbstractService implements ARMManagerService {

	private Log	onmArmLog	= LogFactory.getLog("omp.onm.common.arm");
	
	
	/* 어플리케이션 등록
	 * @see com.omp.commons.product.service.ARMManagerService#connectARMReqRegisterApplication(java.lang.String)
	 */
	public boolean connectARMReqRegisterApplication(String cid) throws DistributeException {
		long	startTm;
		String	rcode	= "00";
		String  receiveCode = "";
		String  api = "reqRegisterApplication";
		String  url = "";
		
		// OMC 키값 : cid
		startTm	= System.currentTimeMillis();
		this.setStep("StartARM_RegistApp");
		try {
			if(log.isInfoEnabled()) log.info("==================== ARM ARMReqRegisterApplication START ====================");
	
			boolean result 		 = false;
	
			// Data Setting
			ARMContent armContent = new ARMContent();
			
			if (log.isDebugEnabled()) {
				log.debug("* ARM Manager.connectARMReqRegisterApplication [cid] {0}: ", new Object[] {cid});
			}
			
			if (StringUtils.isEmpty(cid)) {
				log.warn("* connectARMReqRegisterApplication FAIL");
				throw new DistributeException("Contents ID(cid) is empty");
			} else {
				
				try {
					
					this.setStep("GetContentBaseInfo");
					ConfigProperties conf = new ConfigProperties();
					armContent = (ARMContent) this.commonDAO.queryForObject("ARMManager.getContentBaseInfo", cid);
					
					if (armContent == null) {
						throw new DistributeException("* ARM Connect Fail[ARMReqRegisterApplication] : Content No Data  ");
					} else {
						
						// DRM_YN == Y
						if("Y".equals(armContent.getDrmYn())) {
						
							this.setStep("SettingARM_RegistAppXmlObj");
							ARMDomainReqApplicationInfo xml = new ARMDomainReqApplicationInfo();
						
							xml.setAppid(armContent.getAppid());
							xml.setPid(armContent.getPid());
							xml.setAppname(armContent.getAppname());
							xml.setAppversion(armContent.getAppversion());
							xml.setUserid(armContent.getUserid());
							xml.setStatus("101");	// 구매(Defalut)
		
							// kind Setting
							if(Constants.CONTENT_DRM_INFINITE.equals(armContent.getDrmSetOpt()) 
									|| (armContent.getDrmSetOpt() == null || "".equals(armContent.getDrmSetOpt()))) {
								
								xml.constraint.setKind("infinite");
								xml.constraint.setValue("99991231235959");
								
							} if (Constants.CONTENT_DRM_INTERVAL.equals(armContent.getDrmSetOpt())) {
								
								xml.constraint.setKind("interval");
								xml.constraint.setValue(conf.getString("omp.common.arm.interval.value.second", "2592000"));  // 30 Days
							} 
							 
							// status Setting
							if(Constants.CONTENT_SALE_STAT_ING.equals(armContent.getStatus())) 			xml.setStatus("101");
							else if (Constants.CONTENT_SALE_STAT_STOP.equals(armContent.getStatus()))  	xml.setStatus("201");
							else if (Constants.CONTENT_SALE_STAT_RESTRIC.equals(armContent.getStatus()))	xml.setStatus("301");
							
							// marshalling
							this.setStep("SettingObjToXmlMarshalling");
							String xmlStr = objToXmlString(xml, xml.getClass());
		
							if (log.isInfoEnabled()) {
								log.info("{0}", new Object[] {xmlStr});
							}
							
							this.setStep("SendHttpURLRequest");
							HTTPUtil httpUtil = new HTTPUtil(conf.getString("omp.common.arm.req.url"), "POST", xmlStr);
		
							String resultStr = httpUtil.sendResponseBody("UTF-8");
							
							if (log.isInfoEnabled()) {
								log.info("{0}", new Object[] {resultStr});
							}
							
							// unmarshal
							this.setStep("UnmarshalResponseXmlToObj");
							ARMDomainResRegisterApplicationInfo resultObj = (ARMDomainResRegisterApplicationInfo) xmlStringToObj(ARMDomainResRegisterApplicationInfo.class, resultStr);
							
							// omn
							receiveCode = resultObj.getResultcode();
							url = conf.getString("omp.common.arm.req.url");
							
							if (log.isDebugEnabled())
								log.debug("* [ARMReqRegisterApplication] result Code : {0}", new Object[] {receiveCode});
							
							// result value : 0 success , orther value : error, 24001 :  Already Registered ARM
							// There is a Message where result is Error 
							this.setStep("CheckResponseResultCode");
							if (!"0".equals(receiveCode) && !"24001".equals(receiveCode)) {
								result = false;
								throw new DistributeException("* ARMReqRegisterApplication FAIL [Error Code : " 
										+ receiveCode + " ]  : "	+ resultObj.getMessage());
							} else {
							
								if (log.isInfoEnabled())
									log.info("{0}", new Object[] {resultObj.getMessage()});
								result = true;
							}
						} else {
							log.debug("* Application DRM Not Use. DRM_YN Value is \"N\"");
							return true;
						}
					}
					
				} catch (Exception e) {
					result = false;
					throw new DistributeException("ARM Connect Fail[ARMReqRegisterApplication] : ", e);
				}
				
				if (log.isDebugEnabled()) {
					log.debug("* ARMReqRegisterApplication is SUCCESS");
				}
				
				if(log.isInfoEnabled()) log.info("==================== ARM ARMReqRegisterApplication END ====================");
			}
	
			return result;
		} catch (DistributeException e) {
			rcode	= "99";
			throw e;
		} finally {
			StringBuffer 	sb;
			
			sb	= new StringBuffer();
			sb.append("^").append(System.currentTimeMillis() - startTm)
				.append("^").append(rcode)
				.append("^").append(ThreadSession.getSession().getServiceStep())
				.append("^").append(receiveCode)
				.append("^").append("URL=").append(url)
				.append(",").append("API=").append(api)
				.append(",").append("CID=").append(cid);
			onmArmLog.info(sb.toString());
		}
	}
	
	/* 어플리케이션 판매상태 변경
	 * @see com.omp.commons.product.service.ARMManagerService#connectARMReqModifyApplicationStatus(java.lang.String)
	 */
	public boolean connectARMReqModifyApplicationStatus(String cid) throws DistributeException {
		long	startTm;
		String	rcode	= "00";
		String  receiveCode = "";
		String  api = "reqModifyApplicationStatus";
		String  url = "";
		String  status = "";
		
		// OMC 키값 : cid
		startTm	= System.currentTimeMillis();
		this.setStep("StartARM_ModifyApp");
		try {
			if(log.isInfoEnabled()) log.info("==================== ARM ARMReqModifyApplicationStatus START ====================");
			
			boolean result 		= false;
			
			// Data Setting
			ARMContent armContent = new ARMContent();
			
			if (log.isDebugEnabled()) {
				log.debug("ARM Manager.connectARMReqModifyApplicationStatus [cid] : {0}", new Object[] {cid});
			}
			
			if (StringUtils.isEmpty(cid)) {
				log.warn("connectARMReqModifyApplicationStatus FAIL");
				throw new DistributeException("Contents ID(cid) is empty");
			} else {
				
				try {
					
					this.setStep("GetContentBaseInfo");
					ConfigProperties conf = new ConfigProperties();
					armContent = (ARMContent) this.commonDAO.queryForObject("ARMManager.getContentBaseInfo", cid);
					
					if (armContent == null) {
						throw new DistributeException("ARM Connect Fail[ARMReqModifyApplicationStatus] : Content No Data  ");
					} else {
						
						// DRM_YN == Y
						if("Y".equals(armContent.getDrmYn())) {
						
							this.setStep("SettingARM_ModifyAppXmlObj");
							ARMDomainReqModifyApplicationStatus xml = new ARMDomainReqModifyApplicationStatus();
		
							xml.setAppid(armContent.getAppid());
							xml.setPid(armContent.getPid());
							
							// status Setting
							if(Constants.CONTENT_SALE_STAT_ING.equals(armContent.getStatus())) 				xml.setStatus("101");
							else if (Constants.CONTENT_SALE_STAT_STOP.equals(armContent.getStatus()))  		xml.setStatus("201");
							else if (Constants.CONTENT_SALE_STAT_RESTRIC.equals(armContent.getStatus()))	xml.setStatus("301");
							
							// marshalling
							this.setStep("SettingObjToXmlMarshalling");
							String xmlStr = objToXmlString(xml, xml.getClass());
		
							if (log.isInfoEnabled()) {
								log.info("{0}", new Object[] {xmlStr});
							}
							
							this.setStep("SendHttpURLRequest");
							HTTPUtil httpUtil = new HTTPUtil(conf.getString("omp.common.arm.req.url"), "POST", xmlStr);
		
							String resultStr = httpUtil.sendResponseBody("UTF-8");
							
							if (log.isInfoEnabled()) {
								log.info("{0}", new Object[] {resultStr});
							}
							
							// unmarshal
							this.setStep("UnmarshalResponseXmlToObj");
							ARMDomainResModifyApplicationStatus resultObj = (ARMDomainResModifyApplicationStatus) xmlStringToObj(ARMDomainResModifyApplicationStatus.class, resultStr);
							
							// onm 
							receiveCode = resultObj.getResultcode();
							status = armContent.getStatus();
							url = conf.getString("omp.common.arm.req.url");
							
							if (log.isDebugEnabled())
								log.debug("[ARMReqModifyApplicationStatus] result Code : {0}", new Object[] {receiveCode});
							
							// result value : 0 success , orther value : error
							// There is a Message where result is Error 
							this.setStep("CheckResponseResultCode");
							if (!"0".equals(receiveCode)) {
								result = false;
								throw new DistributeException("ARMReqModifyApplicationStatus FAIL [Error Code : " 
										+ receiveCode + " ]  : "	+ resultObj.getMessage());
							} else {
							
								if (log.isInfoEnabled())
									log.info("{0}", new Object[] {resultObj.getMessage()});
								result = true;
							}
						} else {
							log.debug("* Application DRM Not Use. DRM_YN Value is \"N\"");
							return true;
						}
					}
					
				} catch (Exception e) {
					throw new DistributeException("ARM Connect Fail[ARMReqModifyApplicationStatus] : ", e);
				}
			}
		
			if (log.isDebugEnabled()) {
				log.debug("* ARMReqModifyApplicationStatus is SUCCESS");
			}
			
			if(log.isInfoEnabled()) log.info("==================== ARM ARMReqModifyApplicationStatus END ====================");
			
			return result;
			
		} catch (DistributeException e) {
			rcode	= "99";
			throw e;
		} finally {
			StringBuffer 	sb;
			
			sb	= new StringBuffer();
			sb.append("^").append(System.currentTimeMillis() - startTm)
				.append("^").append(rcode)
				.append("^").append(ThreadSession.getSession().getServiceStep())
				.append("^").append(receiveCode)
				.append("^").append("URL=").append(url)
				.append(",").append("API=").append(api)
				.append(",").append("CID=").append(cid)
				.append(",").append("STATUS=").append(status);
			onmArmLog.info(sb.toString());
		}
	}
	
	/* 개발자센터 개발자용 라이센스 발급
	 * @see com.omp.commons.product.service.ARMManagerService#connectARMReqVerificationLicense(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String connectARMReqVerificationLicense(String mbrNo, String aid) throws DistributeException {
		long	startTm;
		String	rcode	= "00";
		String  receiveCode = "";
		String  api = "reqverificationlicense4update";
		String  url = "";
		
		// OMC 키값 : aid
		startTm	= System.currentTimeMillis();
		this.setStep("StartARM_DevLicense");
		try {
			if(log.isInfoEnabled()) log.info("==================== ARM ARMReqVerificationLicense START ====================");
			
			String result 		= "false";
			
			try {
				
				this.setStep("GetDevTestPhone");
				ConfigProperties conf = new ConfigProperties();
				List<ARMDevice> device = this.commonDAO.queryForList("ARMManager.getOtaDeveloperPhoneList", mbrNo);
				
				if (device == null) {
					throw new DistributeException("ARM Connect Fail[ARMReqVerificationLicense] : Test Phone No Data  ");
				} else {
					
					this.setStep("SettingARM_DevLicenseXmlObj");
					ARMDomainReqVerificationLicense xml = new ARMDomainReqVerificationLicense();
					
					// gdid List에서 gdid,gdid,.. 형식으로 변경 - 2011.05.30
					StringBuffer gdidList = new StringBuffer();
					for(int i = 0; i < device.size(); i++) {
						
						gdidList.append("").append(device.get(i).getMacaddr());
						
						if(i < device.size()-1) {
							gdidList.append(",");
						}
					}

					List<ARMDevice> newDevice = new ArrayList<ARMDevice>();
					ARMDevice armDeviceElmt = new ARMDevice();
					
					xml.limited.setType("interval");
					xml.limited.setValue(conf.getString("omp.common.arm.interval.value", "30"));
					xml.setAppid(aid);
					
					armDeviceElmt.setGdid(gdidList.toString());
					newDevice.add(armDeviceElmt);
					
					xml.device = newDevice;
					
					// marshalling
					this.setStep("SettingObjToXmlMarshalling");
					String xmlStr = objToXmlString(xml, xml.getClass());
	
					if (log.isInfoEnabled()) {
						log.info("{0}", new Object[] {xmlStr});
					}
				
					this.setStep("SendHttpURLRequest");
					HTTPUtil httpUtil = new HTTPUtil(conf.getString("omp.common.arm.req.url"), "POST", xmlStr);
	
					String resultStr = httpUtil.sendResponseBody("UTF-8");
					
					if (log.isInfoEnabled()) {
						log.info("{0}", new Object[] {resultStr});
					}
					
					// unmarshal	
					this.setStep("UnmarshalResponseXmlToObj");
					ARMDomainResVerificationLicense resultObj = (ARMDomainResVerificationLicense) xmlStringToObj(ARMDomainResVerificationLicense.class, resultStr);
					
					// onm
					receiveCode = resultObj.getResultcode();
					url = conf.getString("omp.common.arm.req.url");
					
					if (log.isDebugEnabled()) {
						log.debug("[ARMReqVerificationLicense] result Code : {0}", new Object[] {receiveCode});
						log.debug("[ARMReqVerificationLicense] result Message : {0}", new Object[] {resultObj.getMessage()});
					}
					
					// result value : 0 success , orther value : error
					// There is a Message where result is Success 
					this.setStep("CheckResponseResultCode");
					if (!"0".equals(receiveCode)) {
						//result = "false";
						result = "[" + receiveCode + "]" +  resultObj.getMessage();
	//					throw new DistributeException("ARMReqVerificationLicense FAIL : ReusltCode is Error "
	//							+ resultObj.getMessage());
	//					
					} else {
					
						result = resultObj.getMessage();
					}
					
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new DistributeException("ARM Connect Fail[ARMReqVerificationLicense] : ", e);
			}
	
			if (log.isDebugEnabled()) {
				log.debug("* ARMReqVerificationLicense is SUCCESS");
			}
			
			if(log.isInfoEnabled()) log.info("==================== ARM ARMReqVerificationLicense END ====================");
			
			return result;
		} catch (DistributeException e) {
			rcode	= "99";
			throw e;
		} finally {
			StringBuffer 	sb;
			
			sb	= new StringBuffer();
			sb.append("^").append(System.currentTimeMillis() - startTm)
				.append("^").append(rcode)
				.append("^").append(ThreadSession.getSession().getServiceStep())
				.append("^").append(receiveCode)
				.append("^").append("URL=").append(url)
				.append(",").append("API=").append(api)
				.append(",").append("AID=").append(aid);
			onmArmLog.info(sb.toString());
		}
	}
	
	/* 검증센터 검증용 라이센스 발급
	 * @see com.omp.commons.product.service.ARMManagerService#connectARMReqManagerVerificationLicense(java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public String connectARMReqManagerVerificationLicense(String macAddr, String interval) throws DistributeException {
		
		long	startTm;
		String	rcode	= "00";
		String  receiveCode = "";
		String  api = "reqManagerVerificationLicense";
		String  url = "";
		
		// OMC 키값 : macAddr
		startTm	= System.currentTimeMillis();
		this.setStep("StartARM_MngLicense");
		try {
			if(log.isInfoEnabled()) log.info("==================== ARM ARMReqManagerVerificationLicense START ====================");
			
			String result 		= "false";
			
			try {
	
				this.setStep("GetMngTestPhone");
				ConfigProperties conf = new ConfigProperties();
				List<ARMDevice> device = this.commonDAO.queryForList("ARMManager.getOtaTesterPhoneList", macAddr);
				
				if (device == null) {
					throw new DistributeException("ARM Connect Fail[ARMReqVerificationLicense] : Content No Data  ");
				} else {
					
					this.setStep("SettingARM_MngLicenseXmlObj");
					ARMDomainReqManagerVerificationLicense xml = new ARMDomainReqManagerVerificationLicense();
			
					xml.limited.setType("interval");
					xml.limited.setValue(interval);
				
					xml.device = device;
					
					// marshalling
					this.setStep("SettingObjToXmlMarshalling");
					String xmlStr = objToXmlString(xml, xml.getClass());
	
					if (log.isInfoEnabled()) {
						log.info("{0}", new Object[] {xmlStr});
					}
					
					this.setStep("SendHttpURLRequest");
					HTTPUtil httpUtil = new HTTPUtil(conf.getString("omp.common.arm.req.url"), "POST", xmlStr);
	
					String resultStr = httpUtil.sendResponseBody("UTF-8");
					
					if (log.isInfoEnabled()) {
						log.info("{0}", new Object[] {resultStr});
					}
					
					// unmarshal	
					this.setStep("UnmarshalResponseXmlToObj");
					ARMDomainResManagerVerificationLicense resultObj = (ARMDomainResManagerVerificationLicense) xmlStringToObj(ARMDomainResManagerVerificationLicense.class, resultStr);
					
					// onm
					receiveCode = resultObj.getResultcode();
					url = conf.getString("omp.common.arm.req.url");
					
					if (log.isDebugEnabled()) {
						log.debug("[ARMReqManagerVerificationLicense] result Code : {0}", new Object[] {receiveCode});
						log.debug("[ARMReqManagerVerificationLicense] result Message : {0}", new Object[] {resultObj.getMessage()});
					}
					
					// result value : 0 success , orther value : error
					// There is a Message where result is Success 
					this.setStep("CheckResponseResultCode");
					if (!"0".equals(receiveCode)) {
						//result = "false";
						result = "[" + receiveCode + "]" +  resultObj.getMessage();
	//					throw new DistributeException("ARMReqVerificationLicense FAIL : ReusltCode is Error "
	//							+ resultObj.getMessage());
	//					
					} else {
					
						result = resultObj.getMessage();
					}
					
					// Mac Address ":" 제거
					macAddr = macAddr.replaceAll(":", "");
					
				}
				
			} catch (Exception e) {
				throw new DistributeException("ARM Connect Fail[ARMReqManagerVerificationLicense] : ", e);
			}
	
			if(log.isInfoEnabled()) log.info("==================== ARM ARMReqManagerVerificationLicense END  ====================");
			
			return result;
		} catch (DistributeException e) {
			rcode	= "99";
			throw e;
		} finally {
			StringBuffer 	sb;
			
			sb	= new StringBuffer();
			sb.append("^").append(System.currentTimeMillis() - startTm)
				.append("^").append(rcode)
				.append("^").append(ThreadSession.getSession().getServiceStep())
				.append("^").append(receiveCode)
				.append("^").append(url)
				.append(",").append("API=").append(api)
				.append(",").append("MACADDRESS=").append(macAddr);
			onmArmLog.info(sb.toString());
		}
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public String objToXmlString(final Object obj, final Class objClass) throws DistributeException {
		
		JAXBContext context = null;
		Marshaller marshaller = null;
		StringWriter sw = new StringWriter();
		
		try {
			context = JAXBContext.newInstance(objClass);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(obj, sw);
		
		} catch (JAXBException e) {
			throw new DistributeException("xml Build Error", e);
		}

		if (StringUtils.isEmpty(sw.toString())) {
			throw new DistributeException("xml Build Error");
		}
		
		return sw.toString();
	}
	
	@Override
	@SuppressWarnings("rawtypes")
	public Object xmlStringToObj(final Class objClass, final String xmlString) throws DistributeException {
		
		JAXBContext context = null;
		Unmarshaller unmarshaller = null;
		Object obj = null;
		
		try {

			context = JAXBContext.newInstance(objClass);
			unmarshaller = context.createUnmarshaller();
			obj = unmarshaller.unmarshal(new StringReader(xmlString));

		} catch (JAXBException e) {
			throw new DistributeException("xml Build Error", e);
		}

		return obj;
	}
}
