package com.omp.commons.communicate;

import com.omp.commons.communicate.mail.MailSendAgent;
import com.omp.commons.communicate.sms.SmsSendAgent;
import com.omp.commons.util.config.ConfigProperties;

/**
 * 의사 전달(SMS 혹은 E-MAIL) 관련 객체 펙토리
 * @author pat
 *
 */
public class CommunicatorFactory {
	
	private static MailSendAgent 	mailSendAgent;
	private static SmsSendAgent		smsSendAgent; 
	
	
	/**
	 * 초기화
	 * @param conf
	 */
	public static void initialize(ConfigProperties conf) {
		createNewInstances(conf);
	}
	
	/**
	 * 대행자들 생성
	 * @param conf
	 */
	private static void createNewInstances(ConfigProperties conf) {
		createNewMailSendAgent(conf);
		createNewSmsSendAgent(conf);
	}
	
	/**
	 * 메일 발송 요청 대행자 생성
	 * @param conf
	 */
	private static void createNewMailSendAgent(ConfigProperties conf) {
		try {
			String			agentClassName;
			Class<?>		agentClass;
			MailSendAgent	agent;
			
			agentClassName	= conf.getString("omp.common.communicate.agent.mailSend");
			if (agentClassName == null) {
				return;
			}
			agentClass		= Class.forName(agentClassName);
			agent		= (MailSendAgent)(agentClass.newInstance());
			agent.init(conf);
			mailSendAgent	= agent;
		} catch (Exception e) {
			throw new RuntimeException("MailSendAgent create fail.", e);
		}
	}
	
	/**
	 * SMS 발송 요청자 대행자 생성
	 * @param conf
	 */
	private static void createNewSmsSendAgent(ConfigProperties conf) {
		try {
			String			agentClassName;
			Class<?>		agentClass;
			SmsSendAgent	agent;

			agentClassName	= conf.getString("omp.common.communicate.agent.smsSend");
			if (agentClassName == null) {
				return;
			}
			agentClass	= Class.forName(agentClassName);
			agent		= (SmsSendAgent)(agentClass.newInstance());
			agent.init(conf);
			smsSendAgent	= agent;
		} catch (Exception e) {
			throw new RuntimeException("MailSendAgent create fail.", e);
		}
	}
	

	/**
	 * 메일발송 요청 대행자 얻기
	 * @return
	 */
	public static MailSendAgent getMailSendAgent() {
		return mailSendAgent;
	}
	
	
	/**
	 * SMS 발송 요청 대행자 얻기
	 * @return
	 */
	public static SmsSendAgent getSmsSendAgent() {
		return smsSendAgent;
	}
}