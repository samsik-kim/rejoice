package com.omp.dev.common.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

//import com.ispirit.SendMail;
import com.omp.commons.bundle.Message;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.config.ConfigProperties;
import com.omp.dev.common.model.Email;
/**
 * 이메일 전송 솔루션에 요청하는 유틸.
 * 현재는 템플릿 이메일인 경우만 개발되어 있음.
 * @author soohee
 *
 */
public class SendEmailUtil {
	private static GLogger log = new GLogger(SendEmailUtil.class);
	
	private static SendEmailUtil instance = null;
	private String CONNECT_DB_INFO = null;
	private String CONNECT_DB_ID = null;
	private String CONNECT_DB_PW = null;
	private String MAIL_IMAGE_URL = null;
	private String MAIL_DEVPOC_URL = null;
	private Connection con = null;
	private String sender = "T store";
	private String sEmail = "admin@tstore.co.kr";

	public SendEmailUtil() {
		try {
			ConfigProperties prop = new ConfigProperties();
			CONNECT_DB_INFO = "jdbc:oracle:thin:@"
					+ prop.getString("omp.dev.db.oracle.ip") + ":"
					+ prop.getString("omp.dev.db.oracle.port") + ":"
					+ prop.getString("omp.dev.db.oracle.database");
			CONNECT_DB_ID = prop.getString("omp.dev.db.oracle.id");
			CONNECT_DB_PW = prop.getString("omp.dev.db.oracle.password");
			MAIL_IMAGE_URL = "http://" + prop.getString("omp.dev.domain") + prop.getString("omp.dev.context") + "/images/mail/images";
			//MAIL_IMAGE_URL = "http://211.234.235.65/webdesign/customer/mail/images";
			MAIL_DEVPOC_URL = "http://" + prop.getString("omp.dev.domain") + prop.getString("omp.dev.context") + "/main/main.omp";
			sender = Message.get("omp.dev.servie.name");
			sEmail = Message.get("omp.dev.service.admin.mail");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static {
		SendEmailUtil.getInstance();
	}

	/**
	 * 싱글톤
	 */
	public synchronized static SendEmailUtil getInstance() {
		if (instance == null) {
			try {
				instance = new SendEmailUtil();
				log.info("<SendEmailUtil> create...");
			} catch (Exception e) {
				log.warn("<SendEmailUtil> create fail...", e);
				e.printStackTrace();
			}
		}
		return instance;
	}

	
	/**
	 * 메일제목 세팅
	 * @param email
	 * @return
	 */
	private String makeTitle(Email email) {
		String title = null;
		if (email.getTitle() != null && !"".equals(email.getTitle())) {
			title = email.getTitle();
		} else if (email.getTempleteId() != 0) {
			if (email.getTitleArgs() != null && email.getTitleArgs().length > 0) {
				title = Message.get("omp.dev.mail.title." + email.getTempleteId(), email.getTitleArgs());
			} else {
				title = Message.get("omp.dev.mail.title." + email.getTempleteId());
			}
		}
		if (title.indexOf("!") == 0) {
			title = Message.get("omp.dev.mail.title.default");
		}
		if (title.indexOf("!") == 0) {
			title = "[itopping] 알려드립니다.";
		}
		log.debug("Email templateId[" +email.getTempleteId() + "] title :: " + title);
		return title;
	}
	
	/**
	 * 필수 파라메터 세팅
	 * {:FIELD1:} = 이미지 URL
	 * {:FIELD2:} = 보내는 날짜
	 * {:FIELD3:} = itopping 개발자사이트 바로가기 url
	 * @param email
	 * @return
	 */
	private String[] makeParams(Email email) {
		List<String> tempList = new ArrayList<String>();
		String sendDate = email.getSendDate();
		if (sendDate == null || "".equals(sendDate)) {
			sendDate = DateUtil.getCurrentDate();
		}
		tempList.add(MAIL_IMAGE_URL);
		tempList.add(DateUtil.getDateStr(DateUtil.getDate(sendDate),"."));
		tempList.add(MAIL_DEVPOC_URL);
		tempList.addAll(Arrays.asList(email.getParams()));
		
		log.debug("Email templateId[" +email.getTempleteId() + "] setting params :: " + tempList);
		
		return tempList.toArray(new String[] {});
	}
	
	/**
	 * 예약전송일 때 보내는 일시 세팅
	 * 
	 * @param email
	 * @return
	 */
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
	
	/**
	 * 커넥션 반환
	 * @return
	 * @throws SQLException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws ClassNotFoundException
	 */
	private Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
		con = DriverManager.getConnection(CONNECT_DB_INFO, CONNECT_DB_ID, CONNECT_DB_PW);
		if (con != null) {
			con.setAutoCommit(false);
		}
		return con;
	}

}
