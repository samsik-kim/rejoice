/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 25. | Description
 *
 */
package com.omp.commons.product.service;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.omp.commons.product.model.contents.Body;
import com.omp.commons.product.model.contents.ContentsDomain;
import com.omp.commons.product.model.contents.DeployMainContent;
import com.omp.commons.product.model.contents.DeployProduct;
import com.omp.commons.product.model.contents.DeploySubContent;
import com.omp.commons.product.model.phone.ack1.PhoneFirstAck;
import com.omp.commons.product.model.phone.file.PhoneDomain;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.HTTPUtil;
import com.omp.commons.util.ThreadSession;
import com.omp.commons.util.config.ConfigProperties;

/**
 * 다운로드 배포 서비스 구현체
 * 
 * @author Administrator
 * @version 0.1
 */
public class DownloadDistributeServiceImpl extends AbstractService implements DownloadDistributeService {
	// 배포 전용 Logger (지역화 메세지를 타지 않음)
	final Log dLogger = LogFactory.getLog(DownloadDistributeServiceImpl.class);
	private Log onmDlLog = LogFactory.getLog("omp.onm.common.dl");
	private Log onmDevicemappingLog = LogFactory.getLog("omp.onm.common.devicemapping");

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.DownloadDistributeService#ddDeployContents (java.lang.String, java.lang.String, boolean)
	 */
	@Override
	public boolean ddDeployContents(String cid, boolean isMainConts) throws DistributeException {
		long startTm;
		String rcode = "00";
		String receiveCode = "";

		// OMC 키값 : cid
		startTm = System.currentTimeMillis();
		this.setStep("MakeTxId");
		String tx_id = this.makeTxId(cid);
		ConfigProperties conf = new ConfigProperties();
		try {
			// DL 연동결과

			if (dLogger.isInfoEnabled()) {
				dLogger.info("==== START DownloadServer Deploy ====");
			}

			this.setStep("StartDownloadDeploy");

			String verify_req_ver = "";
			String cud_type = isMainConts ? "U" : "C";

			if (StringUtils.isEmpty(cid)) {
				dLogger.warn("DownloadServer Deploy Contents FAIL");
				throw new DistributeException("Contents ID(cid) is empty");
			} else {

				if (!isMainConts) {
					this.setStep("FindVerifyReqVer");
					Object reqVerObj = null;
					// ============ 1. Find verifyReqVer ============
					try {
						//caution : use DisplayDeploy query (same business logic)
						reqVerObj = commonDAO.queryForObject("DisplayDeploy.findRightVerifyReqVer", cid);
					} catch (SQLException e) {
						e.printStackTrace();
						throw new DistributeException("다운로드서버 배포 실패 : SQLException", e);
					}
					if (reqVerObj == null || StringUtils.isEmpty((String) reqVerObj)) {
						throw new DistributeException("다운로드서버 배포 실패 : 올바른 검증버전이 없습니다.");
					}
					verify_req_ver = (String) reqVerObj;
				}

				try {

					Map<String, String> param = new HashMap<String, String>();
					param.put("cid", cid);
					param.put("verify_req_ver", verify_req_ver);
					param.put("type", cud_type);
					param.put("cud_type", cud_type);
					param.put("tx_id", tx_id);
					param.put("os_type", "6");
					param.put("content_type", "30");
					// param.put("copyright", "SKT");
					param.put("cont_mime_type", "application/vnd.android.package-archive");
					param.put("pre_path", conf.getString("omp.common.path.share.product"));
					ContentsDomain xmlObj = new ContentsDomain();

					this.setStep("SettingXmlObjHeader");
					//=========== header ====================
					xmlObj.header.setTx_id(tx_id);
					xmlObj.header.setService_id("WEBOMP");
					xmlObj.body = new Body();
					// ========== deploy product =============
					this.setStep("SettingXmlObjBodyDeployProduct");
					DeployProduct deployProduct = (DeployProduct) commonDAO.queryForObject("DownloadDeploy.getProduct", param);
					xmlObj.body.application_data.deploy_product = deployProduct;

					// ========== deploy main content ========
					this.setStep("SettingXmlObjBodyDeployMainProduct");
					DeployMainContent deployMainContent = (DeployMainContent) commonDAO.queryForObject("DownloadDeploy.getMainContent",
							param);
					xmlObj.body.application_data.deploy_main_content = deployMainContent;
					// ========= deploy sub content ==========-				
					if (!isMainConts) {
						this.setStep("SettingXmlObjBodyDeploySubProduct");
						List<DeploySubContent> deploySubContentList = commonDAO.queryForList("DownloadDeploy.getSubContent", param);

						if (Integer.parseInt(verify_req_ver) > 1) {
							this.setStep("SettingXmlObjBodyDeployDeteletedSubProduct");
							// 삭제할 sub컨텐츠가 있는지 검사(이전버전에 서브컨텐츠 1,2가 등록되었고, 
							// 현재 버전에 2,3이 등록되어 있다면, 1은 삭제(D)로 내려 보내야함 
							// 1. TBL_CT_CONTS에서 현재 검증버전보다 작고, 승인완료된 MAX 검증버전을 찾는다.
							// 2. 해당 버전의 SCID MINUS 현재 버전의 SCID = 삭제로 배포할 SCID
							// 3. 해당 SCID의 목록을 가져와 cud_type D로 배포한다.

							param.put("type", "D");
							param.put("cud_type", "D");
							List<DeploySubContent> depleteSubContentList = commonDAO.queryForList("DownloadDeploy.getDeleteSubContent",
									param);

							deploySubContentList.addAll(depleteSubContentList);
						}
						xmlObj.body.application_data.deploy_sub_content = deploySubContentList;
					}
					// request time : always last setting
					xmlObj.service_data.service_time.setRequest_time(DateUtil.getShortTimeStampString());

					// marshalling
					this.setStep("SettingObjToXmlMarshalling");
					String xmlStr = objToXmlString(xmlObj, xmlObj.getClass());

					// http header
					this.setStep("MakeHttpHeader");
					Map<String, String> header = new HashMap<String, String>();
					header.put("tx_id", xmlObj.header.getTx_id());
					header.put("service_id", xmlObj.header.getService_id());
					header.put("request_time", xmlObj.service_data.service_time.getRequest_time());

					HTTPUtil httpUtil = new HTTPUtil(conf.getString("omp.common.dl.content.url"), "POST", xmlStr);
					if (dLogger.isInfoEnabled()) {
						dLogger.info("==== SEND Contents Info To DownLoad Server ====");
						dLogger.info("URL:" + conf.getString("omp.common.dl.content.url"));
						dLogger.info(xmlStr);
					}

					this.setStep("SendHttpSendResponseBody");
					String resultStr = httpUtil.sendResponseBody("UTF-8", header);
					if (dLogger.isInfoEnabled()) {
						dLogger.info("==== GET Download Server ACK ====");
						dLogger.info(resultStr);
					}

					// unmarshal	
					this.setStep("UnmarshallResponseXmlToObj");
					ContentsDomain resultObj = (ContentsDomain) xmlStringToObj(ContentsDomain.class, resultStr);

					this.setStep("CheckDistributeResult");
					receiveCode = resultObj.service_data.error_data.getError_code();
					if (!"0".equals(receiveCode)) {
						throw new DistributeException("다운로드서버 배포 실패: DD Result 에러 - {0}",
								new Object[] { resultObj.service_data.error_data.getError_msg() });
					}

				} catch (Exception e) {
					e.printStackTrace();
					throw new DistributeException("다운로드서버 배포 실패: Exception 발생", e);
				}

				if (dLogger.isInfoEnabled()) {
					dLogger.info("==== END DownloadServer Deploy ====");
				}
				return true;
			}
		} catch (DistributeException e) {
			rcode = "99";
			throw e;
		} finally {
			StringBuffer sb;

			sb = new StringBuffer();
			sb.append("^").append(System.currentTimeMillis() - startTm).append("^").append(rcode).append("^")
					.append(ThreadSession.getSession().getServiceStep()).append("^").append(receiveCode).append("^").append("URL=")
					.append(conf.getString("omp.common.dl.content.url")).append(",TXID=").append(tx_id).append(",CID=").append(cid)
					.append(",MAIN=").append(isMainConts ? "Y" : "N");
			this.onmDlLog.info(sb.toString());
		}

	}

	@Override
	public boolean ddPhoneMapping(PhoneDomain phoneDomain) throws DistributeException {
		long startTm;
		String rcode = "00";
		String receiveCode = "";
		Hashtable<String, String> param = new Hashtable<String, String>();

		// OMC KEY (tx_id): phoneDomain.header.request_info.getTx_id() 
		startTm = System.currentTimeMillis();
		ConfigProperties conf = new ConfigProperties();

		try {
			// DL 연동결과

			if (dLogger.isInfoEnabled()) {
				dLogger.info("==== START Device Remapping Deploy ====");
			}
			this.setStep("StartDeviceRemappingDeploy");
			if (phoneDomain == null) {
				throw new DistributeException("폰 Remapping 정보가 올바르지 않습니다.");
			}

			try {
				this.setStep("MarshallingObjToXmlStr");

				// dir check
				File tmpDir = new File(conf.getString("omp.common.path.share.dl") + "/device");
				tmpDir.mkdirs();

				objToXmlFile(phoneDomain, phoneDomain.getClass(), conf.getString("omp.common.path.share.dl") + "/device",
						phoneDomain.header.request_info.getTx_id() + ".xml");

				this.setStep("MakeRequestParam");

				param.put("PATH", conf.getString("omp.common.path.share.dl") + "/device");
				param.put("NAME", phoneDomain.header.request_info.getTx_id() + ".xml");

				this.setStep("SendHttpURLRequest");
				HTTPUtil httpUtil = new HTTPUtil(conf.getString("omp.common.dl.phone.url"), param);
				String returnXml = httpUtil.getStringContent();

				if (dLogger.isInfoEnabled()) {
					dLogger.info("url:" + conf.getString("omp.common.dl.phone.url"));
					if (param != null) {
						Enumeration<String> keys = param.keys();
						String key = null;
						while (keys.hasMoreElements()) {
							key = (String) keys.nextElement();
							dLogger.info(key + "=" + param.get(key));
						}
					}
					dLogger.info("===== Device Remapping First ACK FROM DL =====");
					dLogger.info(returnXml);

					// unmarshal
					this.setStep("UnmarshalResponseXmlToObj");
					PhoneFirstAck resultObj = (PhoneFirstAck) xmlStringToObj(PhoneFirstAck.class, returnXml);

					this.setStep("CheckResponseResult");
					receiveCode = resultObj.header.result_info.getCode();
					if (!"0".equals(receiveCode)) {
						dLogger.warn("ddPhoneMapping FAIL : ReusltCode is Error " + resultObj.header.result_info.getMsg());
						return false;
					} else {
						return true;
					}

				}
				if (dLogger.isInfoEnabled()) {
					dLogger.info("==== END Device Remapping Deploy ====");
				}
			} catch (Exception e) {
				throw new DistributeException("다운로드서버 배포 실패: Exception 발생", e);
			}
			return true;
		} catch (DistributeException e) {
			e.printStackTrace();
			rcode = "99";
			throw e;
		} finally {
			StringBuffer sb;

			sb = new StringBuffer();
			sb.append("^").append(System.currentTimeMillis() - startTm).append("^").append(rcode).append("^")
					.append(ThreadSession.getSession().getServiceStep()).append("^").append(receiveCode).append("^").append("URL=")
					.append(conf.getString("omp.common.dl.phone.url")).append(",PATH=").append(param.get("PATH")).append(",NAME=")
					.append(param.get("NAME")).append(",TXID=").append(phoneDomain.header.request_info.getTx_id());
			this.onmDevicemappingLog.info(sb.toString());
		}
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.DownloadDistributeService#objToXmlString(java.lang.Object, java.lang.Class)
	 */
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
			throw new DistributeException("다운로드서버 배포 실패 : xml Build Error", e);
		}

		if (StringUtils.isEmpty(sw.toString())) {
			throw new DistributeException("다운로드서버 배포 실패 : xml Build Error");
		}
		return sw.toString().replaceAll("&lt;", "<").replaceAll("&gt;", ">");
	}

	/*
	 * (non-Javadoc)
	 * @see com.omp.commons.product.service.DownloadDistributeService#makePhoneMappingXmlFile(java.lang.Object, java.lang.Class, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public void objToXmlFile(final Object obj, final Class objClass, final String fullPath, final String fileName)
			throws DistributeException {
		JAXBContext context = null;
		Marshaller marshaller = null;

		// for Loggin
		StringWriter sw = new StringWriter();

		File dir = new File(fullPath);
		if (!dir.exists()) {
			dir.mkdirs();
		}

		try {
			File xmlFile = new File(fullPath, fileName);
			if (!xmlFile.exists()) {
				xmlFile.createNewFile();
			}

			context = JAXBContext.newInstance(objClass);
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(obj, sw);
			if (dLogger.isInfoEnabled()) {
				dLogger.info(sw.toString());
				dLogger.info("make XML File, path=" + fullPath + ", fileName=" + fileName);
			}
			marshaller.marshal(obj, xmlFile);

		} catch (JAXBException e) {
			throw new DistributeException("다운로드서버 배포 실패 : xml File Build Error", e);
		} catch (IOException e) {
			throw new DistributeException("다운로드서버 배포 실패 : xml File IOException", e);
		}
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
			throw new DistributeException("다운로드서버 배포 실패 : xml Build Error", e);
		}

		return obj;
	}

	private String makeTxId(String cid) throws DistributeException {
		if (StringUtils.isEmpty(cid) || cid.length() != 10) {
			throw new DistributeException("컨텐츠 정보가 없습니다.");
		} else {
			return DateUtil.getShortTimeStampString() + cid.substring(2);
		}
	}

}
