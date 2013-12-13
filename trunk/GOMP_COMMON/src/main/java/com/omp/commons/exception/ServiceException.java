package com.omp.commons.exception;

/**
 * 서비스 장애를 표현하는 익셉션
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class ServiceException extends BaseException {

	public ServiceException(String msgSrc, Object[] msgArgs, Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public ServiceException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public ServiceException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public ServiceException(String msgSrc) {
		super(msgSrc);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
	
	
//	/**
//	 * 
//	 * @param messageKey
//	 * @param causeThrowable
//	 */
//   
//    public ServiceException(String messageKey, Throwable causeThrowable){
//        super(messageKey,causeThrowable);
//        throw this;
//    } 
//    
//    /**
//     * 
//     * @param messageKey
//     */
//    public ServiceException(String messageKey){
//        super(messageKey);
//        throw this;
//    } 
//
//    /**
//	 * Service Layer 에서 발생한 에러 처리
//	 * 업무명 : Constants 클래스에 업무명(BSNM_ADMIN="admin")이 정의 되어 있습니다. 
//	 * errors.업무명.PK 에러에 대한 설명 간략히 메세지 기술   예)PK 중복 에러
//	 * errors.업무명.PK.admin  관리자가 에러발생시 에러대처에 대한 방법 기술  예)키 중복 에러임으로 해당된 소스및 관련된 테이블을 확인하여 보고 해결합니다.
//	 * errors.업무명.PK.user 사용자에게 보여줄 메세지 정의  예)기 등록된 데이터가 있으므로 버튼이 두번 클릭되었는지와 확인해 보시고 이와 같은 증상이 반복될 때에는 관리자한테 문의해주세요
//	 * @param domain : 업무명
//	 * @param code : PK
//     * @param causeThrowable
//     */
//	public ServiceException(String domain, String code,Throwable causeThrowable) {
//		super(domain,code,causeThrowable);
//		throw this;
//	}
//	
//	/**
//	 * Service Layer 에서 발생한 에러 처리
//	 * 업무명 : Constants 클래스에 업무명(BSNM_ADMIN="admin")이 정의 되어 있습니다. 
//	 * errors.업무명.PK 에러에 대한 설명 간략히 메세지 기술   예)PK 중복 에러
//	 * errors.업무명.PK.admin  관리자가 에러발생시 에러대처에 대한 방법 기술  예)키 중복 에러임으로 해당된 소스및 관련된 테이블을 확인하여 보고 해결합니다.
//	 * errors.업무명.PK.user 사용자에게 보여줄 메세지 정의  예)기 등록된 데이터가 있으므로 버튼이 두번 클릭되었는지와 확인해 보시고 이와 같은 증상이 반복될 때에는 관리자한테 문의해주세요
//	 * @param domain : 업무명
//	 * @param code : PK
//	 */
//	public ServiceException(String domain, String code) {
//		super(domain,code);
//		throw this;
//	}
//	
//	/**
//	 * 
//	 * @param domain
//	 * @param code
//	 * @param args
//	 * @param causeThrowable
//	 */
//	public ServiceException(String domain, String code, Object[] args, Throwable causeThrowable) {
//		super(domain,code,args,causeThrowable);
//		throw this;
//	}
//	
//	public ServiceException(String domain, String code, Object[] args) {
//		super(domain,code,args);
//		throw this;
//	}
}
