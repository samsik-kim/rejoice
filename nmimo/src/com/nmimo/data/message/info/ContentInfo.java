package com.nmimo.data.message.info;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.nmimo.common.ServiceConstants;
import com.nmimo.common.exception.NMimoException;

/**
 * <pre>
 *
 * </pre>
 * @file ContentInfo.java
 * @since 2013. 7. 31.
 * @author Rejoice
 */
@SuppressWarnings("serial")
public class ContentInfo implements Serializable{

	/** ROOT ELEMENT */
	private static final String ROOT_ELEMENT = "Info";
	/** PART ELEMENT */
	private static final String ELEMENT_NODE_PART = "Part";
	/** CONTENT-ID ELEMENT */
	private static final String ELEMENT_NODE_CONTENT_ID	= "Content-ID";
	/** CONTENT ELEMENT */
	private static final String ELEMENT_NODE_CONTENT   ="Content";
	/** CONTENT-TYPE ELEMENT */
	private static final String ELEMENT_NODE_CONTENT_TYPE ="Content-Type";
	/** FILENAME ELEMENT */
	private static final String ELEMENT_NODE_FILENAME   ="FileName";
	
	private String contentId="Message";
	private String content;
	private ArrayList<ContentSubInfo> subInfos;
	private String filePath;
	
	/**
	 * <pre>
	 *
	 * </pre>
	 */
	public ContentInfo() {
		super();
	}
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param content
	 * @param subInfos
	 * @param filePath
	 */
	public ContentInfo(String content, ArrayList<ContentSubInfo> subInfos,
			String filePath) {
		super();
		this.content = content;
		this.subInfos = subInfos;
		this.filePath = filePath;
		writeXml(filePath);
	}
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param content
	 */
	public ContentInfo(String content) {
		super();
		this.content = content;
	}
	
	/**
	 * <pre>
	 *
	 * </pre>
	 * @param content
	 * @param subInfos
	 */
	public ContentInfo(String content, ArrayList<ContentSubInfo> subInfos) {
		super();
		this.content = content;
		this.subInfos = subInfos;
	}
	
	/**
	 * @return the contentId
	 */
	public String getContentId() {
		return contentId;
	}
	/**
	 * @param contentId the contentId to set
	 */
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the subInfos
	 */
	public ArrayList<ContentSubInfo> getSubInfos() {
		return subInfos;
	}
	/**
	 * @param subInfos the subInfos to set
	 */
	public void setSubInfos(ArrayList<ContentSubInfo> subInfos) {
		this.subInfos = subInfos;
	}
	
	public void writeXml(String fileFullPath){
		Document doc = null;
		ArrayList<ContentSubInfo> list = getSubInfos();
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
			// 루트 엘리먼트
			doc = docBuilder.newDocument();
			Element rootElement = doc.createElement(ROOT_ELEMENT);
			doc.appendChild(rootElement);
			
		
			if(getContentId() != null && getContent() != null){
				Element part = doc.createElement(ELEMENT_NODE_PART);
				rootElement.appendChild(part);
				// Message Content 엘리먼트
				Element messageContent = doc.createElement(ELEMENT_NODE_CONTENT_ID);
				messageContent.appendChild(doc.createTextNode(getContentId()));
				part.appendChild(messageContent);
				
				// Message Content Type 엘리먼트
				Element messageContentType = doc.createElement(ELEMENT_NODE_CONTENT);
				messageContentType.appendChild(doc.createCDATASection(getContent()));
				part.appendChild(messageContentType);
			}
			
			
			for (int i = 0; i < list.size(); i++) {
				Element part_i = doc.createElement(ELEMENT_NODE_PART);
				rootElement.appendChild(part_i);
				
				Element content_i = doc.createElement(ELEMENT_NODE_CONTENT_ID);
				Element contentType_i = doc.createElement(ELEMENT_NODE_CONTENT_TYPE);
				Element fileName_i = doc.createElement(ELEMENT_NODE_FILENAME);
				content_i.appendChild(doc.createTextNode(list.get(i).getContentId()));
				contentType_i.appendChild(doc.createTextNode(list.get(i).getContentType()));
				fileName_i.appendChild(doc.createTextNode(list.get(i).getFileName()));
				part_i.appendChild(content_i);
				part_i.appendChild(contentType_i);
				part_i.appendChild(fileName_i);
			}

			// XML 파일로 쓰기
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
	
			transformer.setOutputProperty(OutputKeys.ENCODING, ServiceConstants.Common.CHARSET_EUC_KR);
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new FileOutputStream(new File(fileFullPath)));
	
			// 파일로 쓰지 않고 콘솔에 찍어보고 싶을 경우 다음을 사용 (디버깅용)
			// StreamResult result = new StreamResult(System.out);
	
			transformer.transform(source, result);
			
		}catch (ParserConfigurationException e) {
			throw new NMimoException("[ParserConfigurationException] : " + e.getMessage()); 
		}catch (TransformerException tfe) {
			throw new NMimoException("[TransformerException] : " + tfe.getMessage());
		}catch(FileNotFoundException fe){
			throw new NMimoException("[FileNotFoundException] : " + fe.getMessage());
		}
	}
	
	public static void main(String[] args) {
		ArrayList<ContentSubInfo> list = new ArrayList<ContentSubInfo>();
		for (int i = 0; i < 3; i++) {
			ContentSubInfo cs = new ContentSubInfo();
			cs.setContentId("id_"+i);
			cs.setContentType("type_"+i);
			cs.setFileName("file_"+i);
			list.add(cs);
		}
		
		ContentInfo ci = new ContentInfo();
		ci.setContent("임시 테스트 입니다.");
		ci.setContentId("ID");
		ci.setSubInfos(list);
		ci.writeXml("D:/test.xml");
	}
}