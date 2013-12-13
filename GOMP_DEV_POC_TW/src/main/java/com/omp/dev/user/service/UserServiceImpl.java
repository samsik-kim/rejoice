package com.omp.dev.user.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.omp.commons.exception.NoticeException;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.CipherAES;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.RandomString;
import com.omp.dev.common.Constants;
import com.omp.dev.user.model.Session;

public class UserServiceImpl extends AbstractService implements UserService {
	
	/**
	 * Logger
	 */
	protected GLogger logger = new GLogger(this.getClass());

	@Override
	public Session getDevMemberById(String name, String psReqNo, String mbrClsCd, String flag){
		Session session = null;
		
		/**
		 * 타이완ID, 통일번호, 여권번호를 이용한 ID찾기
		 * (이메일 발송을 하지 않고 ID를 result 페이지에 출력)
		 */ 
		
		try {
			Map<String, String> condition = new HashMap<String, String>();
			condition.put("reqNm", name);
			if(Constants.MEM_CLS_PERSON.equals(mbrClsCd)){
				condition.put("psReqNo", CipherAES.encryption(psReqNo));
			}else{
				condition.put("psReqNo", psReqNo);
			}
			condition.put("mbrClsCd", mbrClsCd);
			condition.put("flag", flag);
			
			session = (Session) this.commonDAO.queryForObject("User.getDevMemberById", condition);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("<UserServiceImpl getDevMemberById>", e);
		}
		return session;
	}

	@Override
	public Session getDevMemberById(String email, String flag) {
		Session session = null;
		
		/**
		 * 타이완ID, 통일번호, 여권번호를 이용한 ID찾기
		 * (이메일 발송)
		 */
		
		try {
			Map<String, String> condition = new HashMap<String, String>();
			condition.put("reqNm", email);
			condition.put("flag", flag);
			
			session = (Session) this.commonDAO.queryForObject("User.getDevMemberById", condition);
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("<UserServiceImpl getDevMemberById>", e);
		}
		return session;
	} 

	@Override
	public Session getDevMemberByPw(String userId) throws Exception {
		Session session = null;
		
		// 1. 입력받은 ID로 EMAIL_ADDR, MBR_NO 를 가져온다.
		try {
			session = (Session) this.commonDAO.queryForObject("User.getDevMemberByEmail", userId);
			if(session == null) {
				throw new NoticeException("존재하지 않는 아이디 입니다.");
			}
		} catch (SQLException e) {
			throw new ServiceException("<UserServiceImpl getDevMemberByPw>", e);
		}
		// 2. 임시비밀번호 생성
		try {
			session.setMbrPw(RandomString.getString(Constants.NUMBER_EIGHTH_CIPHERS)); 
		} catch (Exception e) {
			throw new ServiceException("<UserServiceImpl getDevMemberByPw>", e);
		}
		
		Map<String, String> condition = new HashMap<String, String>();
		condition.put("userId", userId);
		condition.put("password", CipherAES.encryption(session.getMbrPw()));
		
		if (!"".equals(StringUtils.defaultString(session.getEmailAddr(), ""))) {
			// 3. 임시비밀번호 UPDATE
			try {
				this.commonDAO.update("User.tempPwUpdate", condition);
			} catch (SQLException e) {
				throw new ServiceException("<UserServiceImpl getDevMemberByPw>", e);
			}
		} else {
			throw new NoticeException("존재하지 않는 아이디 입니다.");
		}
		return session;
	}

	/* (non-Javadoc)
	 * @see com.omp.dev.user.service.UserService#getSession(com.omp.dev.user.model.Session)
	 */
	public Session getSession(Session session) {
		int result = 0;
		int resultCode = 999;
		HashMap<String, Object> map = new HashMap<String, Object>();
		String remteAddr = session.getRemoteAddr();
		try {
			daoManager.startTransaction();
			
			//아이디 먼저 찾기
			result = (Integer) commonDAO.queryForObject("User.idCheck", session.getMbrId());
			
			if(result > 0){
				//비밀번호 찾기
				result = (Integer)commonDAO.queryForObject("User.pwCheck", session);
				session = (Session)commonDAO.queryForObject("User.getSessionInfo", session.getMbrId());
				if(result > 0 ){
					logger.debug("getRemoteAddr() :: " + session.getRemoteAddr());
					logger.debug("remteAddr :: " + remteAddr);
					if(Constants.YES.equals(session.getEmailCertYn())){
						resultCode = 100;	//아이디 + 비밀번호 일치
						
						map.put("remoteAddr", remteAddr);
						map.put("mbrNo", session.getMbrNo());
						map.put("userId", session.getMbrId());
						map.put("failCnt", 0);
						//[UPDATE] FailCnt - RESET
						commonDAO.update("User.updateLoginFailCnt", map);
						//[INSERT] LOGIN_HIST
						commonDAO.insert("User.insertLoginInfo", map);
					}else{
						resultCode = 400;	//로그인 실패 (탈퇴, 가입 신청중)
					}
				}else{
					resultCode = 300;	//비밀번호 불일치
					int cnt = (Integer) commonDAO.queryForObject("User.getLoginFailCnt", session.getMbrId());
					if(cnt == 14){
						
						//update
						resultCode = 500;	//임시비밀번호 발송
						String tempPw = RandomString.getString(Constants.NUMBER_EIGHTH_CIPHERS);
						session.setTempPw(tempPw);
						logger.debug("임시비밀번호 :: " + tempPw);
						map.put("password", CipherAES.encryption(tempPw));
						map.put("userId", session.getMbrId());
						map.put("failCnt", 0);
						//[UPDATE] TempPw
						commonDAO.update("User.tempPwUpdate", map);
						//[UPDATE] FailCnt - RESET
						commonDAO.update("User.updateLoginFailCnt", map);

					}else if(cnt < 14){
						map.put("failCnt", cnt+1);
						map.put("userId", session.getMbrId());
						//[UPDATE] FailCnt 
						commonDAO.update("User.updateLoginFailCnt", map);
					}
					
				}
			}else resultCode = 200;		//아이디 실패
			//결과 값 던져 주기
			session.setResultCode(resultCode);
			//logger.debug("result :: " + session.getResultCode());
			daoManager.commitTransaction();
		} catch (NoticeException e) {
			throw e;
		} catch(ServiceException e) {
			throw e;
		} catch(Exception e) {
			throw new ServiceException("<UserServiceImpl getSession>", e);
		}finally {
            daoManager.endTransaction();
        }
		
		return session;
	}

	@Override
	public String reEmailCertification(String radioFlag, Session session){
		// 메일발송용 UserId
		String mbrId;
		
		// 1. MBR_NO를 조건으로 USER_ID를 가져온다.(메일 발송용)
		try {
			mbrId = (String) this.commonDAO.queryForObject("User.getMbrId", session);
		} catch (SQLException e1) {
			throw new ServiceException("사용자 회원 아이디 조회 실패.", e1);
		}
		
		if("new".equals(radioFlag)) {
			// 2. 새로 등록한 이메일 주소일 경우 새로운 메일주소로 업데이트
			try {
				this.commonDAO.update("User.updateEmailAddr", session);
			} catch (SQLException e) {
				throw new ServiceException("<UserService reEmailCertification>", e);
			}
		}
		return mbrId;
	}
	
}
