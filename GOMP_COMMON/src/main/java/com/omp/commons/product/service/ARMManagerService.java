package com.omp.commons.product.service;


/**
 * ARM 처리 서비스 인테페이스
 * @author pat
 *
 */
public interface ARMManagerService {

	/** 어플리케이션 등록
	 * @param cid
	 * @return
	 * @throws DistributeException
	 */
	public boolean connectARMReqRegisterApplication(String cid) throws DistributeException;
	
	/**
	 * 어플리케이션 판매상태 변경
	 * 
	 * @param cid
	 * @return
	 * @throws DistributeException
	 */
	public boolean connectARMReqModifyApplicationStatus(String cid) throws DistributeException;
	
	/**
	 * 개발자센터 개발자용 라이센스 발급
	 * 
	 * @param mbrNo
	 * @param aid
	 * @return
	 * @throws DistributeException
	 */
	public String connectARMReqVerificationLicense(String mbrNo, String aid) throws DistributeException;
	
	/** 
	 * 검증센터 검증용 라이센스 발급
	 * 
	 * @param macAddr
	 * @param interval
	 * @return
	 * @throws DistributeException
	 */
	public String connectARMReqManagerVerificationLicense(String macAddr, String interval) throws DistributeException;
	
	String objToXmlString(Object obj, Class objClass)
			throws DistributeException;

	Object xmlStringToObj(Class objClass, String xmlString)
			throws DistributeException;

}
