package com.nmimo.common.util;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;

import com.nmimo.common.ServiceConstants;
import com.nmimo.common.configuration.ConfigurationService;
import com.nmimo.common.exception.NMimoException;

public class SocketString {

	/** LOG4J */
	Log logger = LogFactory.getLog(getClass());
	/** Config 관련 */
	@Autowired
	ConfigurationService config;
	/** Message 관련 */
	@Autowired
	MessageSourceAccessor message;
	
	@SuppressWarnings("unused")
	public HashMap<String, String> getSocketMsg(byte[] header, String msg) throws Exception {
		String returnStr = "";
		Socket sock = null;
		HashMap<String, String> returnMap = null;
			try
			{
				sock = new Socket(config.getString("nmimo.socket.ip"), config.getInt("nmimo.socket.port"));
				logger.debug(sock + ": 연결됨");
				
				//전송
				PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(sock.getOutputStream(), ServiceConstants.Common.CHARSET_EUC_KR)), true);
				
				//Header
				out.println(header);
				
				//Req-Log
				TransLog.getInstance().write(getClass().getSimpleName(), "getSocketMsg" , message.getMessage(ServiceConstants.Common.TRANS_MODE_REQ), TransLog.getInstance().sendDataAnalysis(msg));
				//Body
				out.println(msg);
				
				//리턴
				InputStream fromServer = sock.getInputStream();
				DataInputStream data = new DataInputStream(fromServer);

				byte[] buf = new byte[2048];
				int count;
				
				while ((count = data.read(buf)) != -1){
					String str = new String(buf, ServiceConstants.Common.CHARSET_EUC_KR);
					returnStr = returnStr + str.trim();
				}
				//Res-Log
				TransLog.getInstance().write(getClass().getSimpleName(), "getSocketMsg" , message.getMessage(ServiceConstants.Common.TRANS_MODE_RES), TransLog.getInstance().sendDataAnalysis(returnStr));
				//Return Map
				returnMap = TransLog.getInstance().sendDataAnalysis(returnStr);
			}
			catch (IOException ex)
			{
				logger.error("SOCKET CONNECTION ERROR");
				throw new NMimoException("SOCKET CONNECTION ERROR");
			}
			finally
			{
				try
				{
					if (sock != null)
						sock.close();
				}
				catch (IOException ex)
				{
					throw new NMimoException("SOCKET CONNECTION ERROR");
				}
			}
		return returnMap;
	}
}