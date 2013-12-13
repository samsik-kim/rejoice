package com.omp.dev.test.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

//import com.ispirit.SendMail;
import com.omp.commons.util.GLogger;
import com.omp.dev.common.model.Email;
import com.omp.dev.common.util.SendEmailUtil;

public class SendEmail {
	private GLogger log = new GLogger(SendEmail.class);

	private Email makeEmail() {
		Email email = new Email();

		email.setSender("개발자POC");
		email.setSMail("master@developer.itopping.com");
		email.setReciever("터리");
		email.setREmail("myssooya@naver.com");
		email.setTitle("테스트 메일");

		String[] param = new String[] { "이명재", "mj.lee@i-spirit.co.kr","2009년 1월 1일" };
		email.setParams(param);

		return email;
	}

	public void test() {
		List<String> bList = new ArrayList<String>();
		String [] aa = new String[] {"A","B","C"};
		
		List<String> aList = Arrays.asList(aa);
		
		bList.add("1990.1.2");
		bList.addAll(aList);
		
		log.debug(bList);
		
		String [] bb = bList.toArray(new String[] {});
//		log.debug("==>" + bb);
		
	}

	public void sendEmailTest() {
//		log.debug("sendEmailTest...");
//		Connection con = null;
//		Email email = makeEmail();
//		DevConfig prop = null;
//
//		try {
//			prop = new DevConfig();
//			String connectionInfo = "jdbc:oracle:thin:@" 
//									+ prop.getString("omp.dev.db.oracle.ip") 
//									+ ":" + prop.getString("omp.dev.db.oracle.port") 
//									+ ":" + prop.getString("omp.dev.db.oracle.database");
//			String dbId = prop.getString("omp.dev.db.oracle.id");
//			String dbPw = prop.getString("omp.dev.db.oracle.password");
//			
//			Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
//			con = DriverManager.getConnection(connectionInfo, dbId, dbPw);
//			
//
//			if (con != null) {
//				con.setAutoCommit(false);
//				SendMail sm = new SendMail(con);
//
//				if (sm.setRecipient(
//						0, 
//						null, 
//						email.getReciever(), 
//						email.getREmail(), 
//						email.getParams(), 
//						makeTableSet(email)) == SendMail.SUCCESS) {
//					if (sm.reserveMail(
//							0, 
//							email.getSender(), 
//							email.getSMail(),
//							null, 
//							email.getTitle(),
//							SendMail.CONTENT_TEMPLATEDB, 
//							email.getTempleteId(), 
//							"100", 
//							"", 
//							setSendTime(email)) == SendMail.SUCCESS) {
//						try {
//							con.commit();
//						} catch (Exception ex) {ex.printStackTrace();}
//						log.info("Send mail success. TemplateId[" + email.getTempleteId() + "] reciever[" + email.getREmail() + "]");
//					} else {
//						try {
//							con.rollback();
//						} catch (Exception ex) {
//							ex.printStackTrace();
//						}
//						log.warn("Send mail fail. TemplateId[" + email.getTempleteId() + "] reciever[" + email.getREmail() + "]");
//						
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				if (con != null) {
//					con.close();
//				}
//			} catch (Exception e) {
//			}
//		}
		throw new UnsupportedOperationException("unsupported.");
	}
	
	@SuppressWarnings("unchecked")
	private Vector makeTableSet(Email email) {
		Vector vt = new Vector();
		// vt.addElement(new String[] {contents});
		return vt;
	}

	private Timestamp setSendTime(Email email) {
		Timestamp ts = null;
		if (email.getSendDate() != null && email.getSendTime() != null) {
			// TODO
		} else {
			Calendar cal = Calendar.getInstance();
			ts = new Timestamp(cal.getTimeInMillis());
		}
		return ts;
	}

}
