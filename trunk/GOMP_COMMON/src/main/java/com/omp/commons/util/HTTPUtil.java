/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author       Date            Description
 * --------     ----------      ------------------
 * ?            ?               ?
 * nefer        2009.12.08      move to omp_common, modify pom.xml dependency httpclient
 *
 */
package com.omp.commons.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.struts2.ServletActionContext;

/**
 * HTTP URL에 해당하는 내용을 조회한다.
 * <P>
 * Example :
 * 
 * <pre>
 * String url = &quot;http://www.nate.com/index.html&quot;;
 * Hashtable param = new Hashtable();
 * param.put(&quot;category&quot;, &quot;9&quot;);
 * param.put(&quot;page&quot;, &quot;1&quot;);
 * HTTPContent hc = new HTTPContent(url, param);
 * </pre>
 */
public class HTTPUtil {
	private URL url;
	private Hashtable<String, String> param;
	private String encoding = "UTF-8";
	private String methodType = "GET";
	private String bodyData = "";

	/**
	 * URL을 이용하여 HTTPContent를 생성한다.
	 * 
	 * @param urlString 요청할 URL
	 * @throws Exception
	 */
	public HTTPUtil(String urlString) throws Exception {
		url = new URL(urlString);
	}

	public HTTPUtil(String urlString, String method) throws Exception {
		this(urlString);
		this.methodType = method;
	}

	/**
	 * URL과 Request GET Parameter를 이용하여 HTTPContent를 생성한다.
	 * 
	 * @param urlString 요청할 URL
	 * @param param Request Parameter
	 * @throws Exception
	 */
	public HTTPUtil(String urlString, Hashtable<String, String> param) throws Exception {
		this(urlString);
		setParameter(param);
	}

	public HTTPUtil(String urlString, String method, Hashtable<String, String> param) throws Exception {
		this(urlString, param);
		this.methodType = method;
	}

	public HTTPUtil(String urlString, String method, String bodyData) throws Exception {
		this(urlString);
		this.methodType = method;
		this.bodyData = bodyData;
	}

	/**
	 * HTTP Request Parameter를 설정한다.
	 * 
	 * @param param HTTP Request Parameter
	 */
	public void setParameter(Hashtable<String, String> param) {
		this.param = param;
	}

	/**
	 * Encoding을 설정한다. Encoding을 설정하지 않는 경우 기본 encoding("UTF-8")이 사용된다.
	 * 
	 * @param encoding Character encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getContent() throws Exception {
		return getStringContent();
	}

	public Object getObjectContent() throws Exception {
		Object obj = null;
		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setRequestMethod(methodType);
		if (param != null) {
			urlConn.setDoOutput(true);
			OutputStream out = urlConn.getOutputStream();
			Writer writer = new OutputStreamWriter(out, encoding);

			Enumeration<String> keys = param.keys();
			int i = 0;
			String key = null;

			while (keys.hasMoreElements()) {
				if (++i > 1)
					writer.write("&");

				key = keys.nextElement();
				writer.write(key + "=" + URLEncoder.encode(param.get(key), encoding));
			}

			writer.close();
		}

		InputStream in = urlConn.getInputStream();
		ObjectInputStream is = new ObjectInputStream(in);
		obj = is.readObject();
		urlConn.disconnect();
		if (urlConn.getResponseCode() >= 400)
			throw new Exception(Integer.toString(urlConn.getResponseCode()));

		return obj;
	}

	/**
	 * URL Request에 대한 응답 내용을 리턴한다.
	 * 
	 * @return URL Request에 대한 응답 내용
	 * @throws Exception
	 */

	public String getStringContent() throws Exception {

		HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
		urlConn.setRequestMethod(methodType);
		if (param != null) {
			urlConn.setDoOutput(true);
			OutputStream out = urlConn.getOutputStream();
			Writer writer = new OutputStreamWriter(out, encoding);

			Enumeration<String> keys = param.keys();
			int i = 0;
			String key = null;

			while (keys.hasMoreElements()) {
				if (++i > 1)
					writer.write("&");

				key = keys.nextElement();
				writer.write(key + "=" + URLEncoder.encode(param.get(key), encoding));
			}

			writer.close();
		}

		InputStream in = urlConn.getInputStream();
		Reader reader = new InputStreamReader(in, encoding);
		BufferedReader br = new BufferedReader(reader);
		StringBuffer sbContent = new StringBuffer(1024);
		String data = null;
		int i = 0;

		while ((data = br.readLine()) != null) {
			if (++i > 1)
				sbContent.append("\n");

			sbContent.append(data);
		}

		br.close();
		urlConn.disconnect();

		if (urlConn.getResponseCode() >= 400)
			throw new Exception(Integer.toString(urlConn.getResponseCode()));

		return sbContent.toString();
	}

	public String getContentHttps() throws Exception {
		return getStringContentHttps();
	}

	public Object getObjectContentHttps() throws Exception {
		Object obj = null;
		HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
		urlConn.setRequestMethod(methodType);

		if (param != null) {
			urlConn.setDoOutput(true);
			OutputStream out = urlConn.getOutputStream();
			Writer writer = new OutputStreamWriter(out, encoding);

			Enumeration<String> keys = param.keys();
			int i = 0;
			String key = null;

			while (keys.hasMoreElements()) {
				if (++i > 1)
					writer.write("&");

				key = keys.nextElement();
				writer.write(key + "=" + URLEncoder.encode(param.get(key), encoding));
			}

			writer.close();
		}

		InputStream in = urlConn.getInputStream();
		ObjectInputStream is = new ObjectInputStream(in);
		obj = is.readObject();
		urlConn.disconnect();

		int code = urlConn.getResponseCode();
		String redtUrl = urlConn.getHeaderField("Location");

		if (code >= 400)
			throw new Exception(Integer.toString(urlConn.getResponseCode()));

		if (redtUrl != null && mustRedirect(code))
			ServletActionContext.getResponse().sendRedirect(redtUrl);

		return obj;
	}

	/**
	 * URL Request에 대한 응답 내용을 리턴한다.
	 * 
	 * @return URL Request에 대한 응답 내용
	 * @throws Exception
	 */

	public String getStringContentHttps() throws Exception {
		HttpsURLConnection urlConn = (HttpsURLConnection) url.openConnection();
		urlConn.setRequestMethod(methodType);

		if (param != null) {
			urlConn.setDoOutput(true);
			OutputStream out = urlConn.getOutputStream();
			Writer writer = new OutputStreamWriter(out, encoding);

			Enumeration<String> keys = param.keys();
			int i = 0;
			String key = null;

			while (keys.hasMoreElements()) {
				if (++i > 1)
					writer.write("&");

				key = keys.nextElement();
				writer.write(key + "=" + URLEncoder.encode(param.get(key), encoding));
			}

			writer.close();
		}

		InputStream in = urlConn.getInputStream();
		Reader reader = new InputStreamReader(in, encoding);
		BufferedReader br = new BufferedReader(reader);
		StringBuffer sbContent = new StringBuffer(1024);
		String data = null;
		int i = 0;

		while ((data = br.readLine()) != null) {
			if (++i > 1)
				sbContent.append("\n");

			sbContent.append(data);
		}

		br.close();
		urlConn.disconnect();

		int code = urlConn.getResponseCode();
		String redtUrl = urlConn.getHeaderField("Location");

		if (code >= 400)
			throw new Exception(Integer.toString(urlConn.getResponseCode()));

		if (redtUrl != null && mustRedirect(code))
			ServletActionContext.getResponse().sendRedirect(redtUrl);

		return sbContent.toString();
	}

	private boolean mustRedirect(int code) {
		if (code == HttpURLConnection.HTTP_MOVED_PERM || code == HttpURLConnection.HTTP_MOVED_TEMP) {
			return true;
		} else
			return false;
	}

	/**
	 * HTML문자열을 분석하여 input type이 text에 해당하는 파라미터 정보를 리턴한다.
	 * 
	 * @param html HTML String
	 * @return Hashtable
	 */
	public static Hashtable getHtmlInputParameter(String html) throws Exception {
		int startTag = -1;
		int endTag = -1;
		Hashtable param = new Hashtable();

		String inputTag = null;
		String name = null;
		String value = null;
		int startPos;
		int endPos;

		while ((startTag = html.indexOf("<input type=\"text\" ", endTag)) != -1) {
			endTag = html.indexOf(">", startTag + 19);
			if (endTag == -1)
				break;

			inputTag = html.substring(startTag + 19, endTag);

			// find "name" position
			startPos = inputTag.indexOf("name=\"");
			endPos = inputTag.indexOf("\"", startPos + 6);
			name = inputTag.substring(startPos + 6, endPos);

			// find "value" position
			startPos = inputTag.indexOf("value=\"", endPos);
			endPos = inputTag.indexOf("\"", startPos + 7);
			value = inputTag.substring(startPos + 7, endPos);

			param.put(name, value);
		}

		return param;
	}

	/**
	 * b
	 * 
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public String sendResponseBody() throws Exception {

		return sendResponseBody("UTF-8", null);
	}

	/**
	 * b
	 * 
	 * @param body
	 * @return
	 * @throws Exception
	 */
	public String sendResponseBody(String charSet) throws Exception {

		return sendResponseBody(charSet, null);
	}

	public String sendResponseBody(String charSet, Map<String, String> header) throws Exception {

		HttpClient client = new DefaultHttpClient();
		try {
			HttpParams config;
			HttpPost post;

			config = client.getParams();

			// Connection TIME OUT 10 초 
			// Read       TIME OUT  10 초
			config.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
			config.setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);

			post = new HttpPost(this.url.toString());
			post.addHeader("Content-Type", "text/xml; charset=" + charSet);
			post.addHeader("Host", this.url.getHost());
			post.addHeader("Connection", "close");

			if (header != null) {
				for (String key : header.keySet()) {
					post.addHeader(key, header.get(key));
				}
			}
			post.setEntity(new StringEntity(this.bodyData, charSet));
			return client.execute(post, new BasicResponseHandler());
		} finally {
			client.getConnectionManager().shutdown();
		}
	}
}
