/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 * redaano    2009. 5. 24.    Description
 *
 */
package com.omp.dev.community.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ibatis.dao.client.DaoManager;
import com.omp.commons.communicate.CommunicatorFactory;
import com.omp.commons.communicate.mail.MailSendAgent;
import com.omp.commons.communicate.mail.SendMailModel;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.model.MemberEmailModel;
import com.omp.commons.persistence.dao.DaoConfig;
import com.omp.commons.service.AbstractService;
import com.omp.commons.text.LocalizeMessage;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.SessionUtil;
import com.omp.dev.common.util.ReturnUrlGenerateUtil;
import com.omp.dev.community.model.Ctgr;
import com.omp.dev.community.model.Qna;
import com.omp.dev.community.persistence.dao.QnaDAO;
import com.omp.dev.user.model.Session;

/**
 * QnaServiceImpl
 * 
 * @author redaano
 * @version 0.1
 */
public class QnaServiceImpl extends AbstractService implements QnaService {
	private static GLogger logger = new GLogger(QnaServiceImpl.class);
	
	private DaoManager manager = null;
	
	private QnaDAO dao = null;

	private MailSendAgent msa;
	private SendMailModel mail;
	private MemberEmailModel model;
	
	public QnaServiceImpl() {
		manager = DaoConfig.getDaoManager();
		dao = (QnaDAO)manager.getDao(QnaDAO.class);
	}


	/**
	 * Qna Insert
	 * 
	 * @param qna
	 * @return
	 */
	public void addQna(HttpServletRequest req, Qna qna) {
		Session session = null;
		try {
			session = (Session) SessionUtil.getMemberSession(req);
			
			manager.startTransaction(); // Transaction Start
			
			if(session != null){		// Login
				qna.setQueId(session.getMbrId());
				qna.setMbrNo(session.getMbrNo());
			}
			
			dao.insertQna(qna);
			
			// 1. MemberEmailModel
			model = new MemberEmailModel();
			
			if(session != null){		// Login
				model.setWriter(session.getMbrNm());
				model.setMbrId(session.getMbrId());
			}else{		// Not Login
				model.setWriter(qna.getQueNm());
			}
			
			model.setTitle(qna.getQueTitle());
			model.setDscr(qna.getQueDscr());
			model.setReqDt(DateUtil.getSysDate());
			model.setMainUrl(ReturnUrlGenerateUtil.rtnUrl(req));
			model.setReqDt(DateUtil.getSysDate());
			
			// 2. SendMailModel
			mail = new SendMailModel();
			mail.setTemplateId("/DEV/US_019.html");
			mail.setRefAppId("QnaServiceImpl.addQna");
			mail.setSubject(LocalizeMessage.getLocalizedMessage("[Tstore 개발자센터] 회원님의 고객문의 접수 안내입니다."));
			
			if(session != null){		// Login
				mail.setRefDataId(session.getMbrNo());
				mail.setToAddr(session.getEmailAddr());
			}else{		// Not Login
				mail.setRefDataId(qna.getEmailAddr());
				mail.setToAddr(qna.getEmailAddr());
			}
			
			mail.setContentData(model);
			msa = CommunicatorFactory.getMailSendAgent();	// Mail Instance
			
			//[SEND - EMAIL]
			msa.requestMailSend(mail);
			
			manager.commitTransaction(); // Transaction Commit
		} catch (Exception e) {
			throw new ServiceException("Q&A 등록을 실패하였습니다.", e);
		} finally {
			manager.endTransaction(); // Transaction End
		}
	}

	/**
	 * CategoryList Search
	 * 
	 * @param highCtgr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Ctgr> getCategoryList()	{
		List<Ctgr> result	= null;

		try	{
			result	= this.commonDAO.queryForList("Community_Qna.selectCategoryList");
		}catch(Exception e){
    		e.printStackTrace();
    		throw new ServiceException("getCategoryList Error.", e);
        }

		return	result;
	}
}
