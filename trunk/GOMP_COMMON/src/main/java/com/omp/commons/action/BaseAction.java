package com.omp.commons.action;

import java.io.File;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import pat.neocore.lang.NoSuchTargetException;
import pat.neocore.util.objlook.ObjectLooker;

import com.omp.commons.model.FileDownloadInfoModel;
import com.omp.commons.model.PagenateList;
import com.omp.commons.ssc.SyncSignalCaster;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.ThreadSession;
import com.omp.commons.util.config.ConfigProperties;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * Action의 공통 부분을 구현한 추상 객체.
 * @author pat
 *
 */
@SuppressWarnings("serial")  
public abstract class BaseAction
	extends ActionSupport
	implements Preparable {
	
	protected GLogger				logger		= new GLogger(this.getClass());
	protected long					searchedTotalCount;
	protected HttpServletRequest	req;
	protected HttpServletResponse	res;
	protected ConfigProperties		conf;
	protected SyncSignalCaster		ssc;
	private	Properties				confProps;
	protected Locale				loc;
	
	private FileDownloadInfoModel	downloadFile;
	
	
	@Override
	public final void prepare() throws Exception {
		ThreadSession	tses;
		String			ua;
		
		tses	= ThreadSession.getSession();
		tses.setActionStep("Prepare");
		this.searchedTotalCount	= 0;
		this.req				= ServletActionContext.getRequest();
		this.res				= ServletActionContext.getResponse();
		this.conf				= new ConfigProperties();
		this.confProps			= this.conf.getStaticProperties();
		this.downloadFile		= new FileDownloadInfoModel();
		this.ssc				= (SyncSignalCaster)ServletActionContext.getServletContext().getAttribute("SSC");
		this.loc				= ThreadSession.getSession().getServiceLocale();
		ua	= this.req.getHeader("user-agent");
		if (ua != null && ua.indexOf("MSIE") != -1) {
			this.downloadFile.setFilenameUrlEncodeCharst("UTF-8");
		}
		this.prepareRequest();
	}
	
	/**
	 * 컨피그 프로퍼티를 반환합니다.
	 * @return
	 */
	public Properties getConfig() {
		return this.confProps;
	}
	
	/**
	 * 요청 처리 전 준비 사항 처리 메소드
	 * 서브 클래스에서 필요시 오버라이드 하여 구체화
	 * @throws Exception
	 */
	protected void prepareRequest()
		throws Exception {
		// 아무것도 하지 않음, 서브 클래스에서 필요시 구체화
		
	}

	/**
	 * 파일 다운로드 관련 정보 얻기
	 * @return
	 */
	public FileDownloadInfoModel getDownloadFile() {
		return downloadFile;
	}
	
	/**
	 * 해당 액션의 로거 얻기
	 * @return
	 */
	public GLogger	getLogger()	{
		return this.logger;
	}
	
	/**
	 * 검색된 자료의 수 얻기
	 */
	public long getSearchedTotalCount() {
		return this.searchedTotalCount;
	}
	
	/**
	 * 리스트로 부터 검색된 자료의 수를 추출하여 설정합니다.
	 * @param list
	 */
	@SuppressWarnings("rawtypes")
	protected void setSearchedTotalCount(List<?> list) {
		if (list instanceof PagenateList) {
			this.searchedTotalCount	= ((PagenateList)list).getTotalCount();
		} else {
			this.searchedTotalCount	= list.size();
			if (this.searchedTotalCount > 0) {
				Object			data;
				ObjectLooker	olook;
				
				data	= list.get(0);
				olook	= ObjectLooker.getLooker(data);
				try {
					this.searchedTotalCount	= olook.getLong(data, "tatalCount");
				} catch (NoSuchTargetException ignore) {}
			}
		}
	}
	
	/**
	 * 다운로드 시킬 파일을 지정합니다.
	 * 다운로드되는 클라이언트에 보여지는 파일명은 대상 파일의 파일명과 동일 합니다.
	 * @param target 다운로드 시킬 대상 파일
	 * @param contentType 컨탠트 타입
	 */
	protected void setDownloadFile(File target, String contentType) {
		this.setDownloadFile(target, contentType, null);
	}
	
	/**
	 * 다운로드 시킬 파일을 지정합니다.
	 * @param target 다운로드 시킬 대상 파일
	 * @param contentType 컨탠트 타입
	 * @param saveAs 클라이언트에 제시될 저장 파일명
	 */
	protected void setDownloadFile(File target, String contentType, String saveAs) {
		this.downloadFile.setTarget(target);
		this.downloadFile.setContentType(contentType);
		this.downloadFile.setSaveAs(saveAs);
	}
	
	/**
	 * 서비스 로케일 얻습니다.
	 * @return
	 */
	public Locale getLoc() {
		return loc;
	}
	
	/**
	 * 처리 단계를 설정합니다.
	 * @param step
	 */
	protected void setStep(String step) {
		ThreadSession.getSession().setActionStep(step);
	}
	
	/**
	 * 요청의 프라이머리 키를 설정합니다.
	 * @param pkey
	 */
	@SuppressWarnings("rawtypes")
	protected void setPrimaryKey(Object... args) {
		StringBuffer	sb;
		
		sb	= new StringBuffer();
		for (int i=0; i<args.length; i++) {
			String	keyName;
			Object	key;
			
			keyName	= args[i].toString();
			if (i != 0) {
				sb.append(",");
			}
			sb.append(keyName).append("=");
			
			if (i+1 < args.length) {
				key		= args[++i];
			} else {
				key		= null;
			}
			if (key == null) {
				sb.append("null");
			} else {
				if (key.getClass().isArray()) {
					int	size;
					
					size	= Array.getLength(key);
					for (int j=0; j<size; j++) {
						Object	obj;
						
						if (j != 0) {
							sb.append(",");
						}
						obj	= Array.get(key, j);
						sb.append(obj == null ? "null" : obj.toString());
					}
				} else if (key instanceof Collection) {
					Iterator	itr;
					boolean		isFirst;
					
					itr		= ((Collection)key).iterator();
					isFirst	= true;
					while (itr.hasNext()) {
						Object	obj;
						
						if (isFirst) {
							isFirst	= false;
						} else {
							sb.append(",");
						}
						obj	= itr.next();
						sb.append(obj == null ? "null" : obj.toString());
					}
				} else {
					sb.append(key.toString());
				}
			}
		}
		
		ThreadSession.getSession().setPrimaryKey(sb.toString());
	}
}
