package com.omp.servlet.jsp.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.lang.StringUtils;

import com.omp.commons.commcode.CacheCommCode;
import com.omp.commons.commcode.model.CommCode;

/**
 * 코드를 코드값으로 번안하는 태그의 공통부분을 구현한 추상객체<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li> id : EL 가능, 코드 캐쉬 매니저 혹은 바인딩 되어 있는 id. 생략시
 *  AbstractCodeListResolveTag.DEFAULT_CCM_NAME으로 바인딩된 속성을 사용합니다.</li>
 * <li> group : EL 가능. 번안 대상 코드 그룹</li>
 * <li> code : EL 가능. 번안 대상 코드
 * </ul>
 * 태그 바디의 내용을 출력되지 않습니다.
 * @author pat
 *
 */
@SuppressWarnings("serial")
public abstract class AbstractCodeValueTag
	extends BodyTagSupport {
	
	protected static final int	CODE_TYPE_UNKNOWN	= -1;
	protected static final int	CODE_TYPE_AUTO		= 0;
	protected static final int	CODE_TYPE_NORMAL	= 1;
	protected static final int	CODE_TYPE_FULL		= 2;

	/**
	 * 코드 캐쉬 매니저 바인딩 id
	 */
	private Object	id;
	/**
	 * 조회 대상 코드 그룹
	 */
	private String	cgnm;
	/**
	 * 조회 대상 코드
	 */
	private String	code;
	/**
	 * 조회 대상 코드 종류
	 */
	private int		codeType;
	/**
	 * 코드그룹에 존재하지 않는 값이 전달 되었을때 표시할 내용
	 */
	private String	naMessage;
	
	/**
	 * 코드 캐쉬 매니저 바인딩 ID를 지정합니다.
	 */
	public void setId(Object id) {
		this.id	= id;
	}
	
	/**
	 * 조회대상 코드그룹을 지정합니다.
	 * @param grp
	 */
	public void setGroup(String grp) {
		this.cgnm	= grp;
	}
	
	/**
	 * 조회대상 코드를 지정합니다.
	 * @param code
	 */
	public void setCode(String code) {
		this.code	= code;
	}

	/**
	 * 지정된 코드그룹에 출력대상 코드가 존재하지 않을때 출력할 메세지를 지정합니다.
	 * @param msg
	 */
	public void setNaMessage(String	msg) {
		this.naMessage	= msg;
	}
	
	public void setCodeType(String type) {
		if ("auto".equals(type)) {
			this.codeType	= CODE_TYPE_AUTO;
		} else if ("normal".equals(type)) {
			this.codeType 	= CODE_TYPE_NORMAL;
		} else if ("full".equals(type)) {
			this.codeType 	= CODE_TYPE_FULL;
		} else {
			throw new RuntimeException("unknown code type '" + type + "', code type must on of (auto|normal|full).");
		}
	}
	
	/**
	 * 이 태그의 기능을 위해 오버라이딩 되었습니다.
	 */
	public int doEndTag()
		throws JspException {
		
		try {
			String				groupId;
			String				codeId;
			String				codeValue;
			
			groupId	= this.cgnm;
			if (this.code == null) { 
				throw new JspException("code must not null.");
			}
			codeId	= this.code;
			if (StringUtils.isEmpty(codeId)) {
				codeValue	= "";
			} else {
				CommCode	cc;
				codeId		= codeId.trim();
				
				if (this.codeType == CODE_TYPE_AUTO) {
					if (codeId.length() > 6) {
						this.codeType	= CODE_TYPE_FULL;
					} else {
						if (groupId == null) {
							throw new JspException("code is to short, group id required.");
						}
						this.codeType	= CODE_TYPE_NORMAL;
					}
				}
				switch (this.codeType) {
					case CODE_TYPE_NORMAL :
						cc	= CacheCommCode.getCommCode(groupId, codeId);
						break;
					case CODE_TYPE_FULL :
						cc	= CacheCommCode.getCommCodeByFullCode(codeId);
						break;
					default :
						throw new JspException("unknown code type " + this.codeType);
				}
				
				if (cc == null) {
					if (this.naMessage != null) {
						codeValue	= this.naMessage;
					} else {
						if (this.codeType == CODE_TYPE_FULL) {
							codeValue	= "Unknown Code " + codeId;
						} else {
							codeValue	= "Unknown Code " + codeId + " in group " + groupId;
						}
					}
				} else {
					codeValue	= cc.getCdNm();
				}
			}
			this.pageContext.getOut().print(this.getEncodeString(codeValue));
		} catch (JspException e) {
			throw e;
		} catch (Exception e) {
			throw new JspException("Code Tag render fail.", e);
		} finally {
			this.release();
		}

		return EVAL_PAGE;
	}
	
	/**
	 * 이 태그의 기능을 위해 오버라이딩 되었습니다.
	 */
	public void release() {
		this.id			= null;
		this.cgnm		= null;
		this.code		= null;
		this.codeType	= CODE_TYPE_AUTO;
		this.naMessage	= null;
	}
	
	/**
	 * 조회된 코드값을 표시할 곳에 맞게 인코딩 하는 코드를 구현하십시오.
	 * @param src 조회된 코드값
	 * @return
	 * @throws Exception
	 */
	protected abstract String getEncodeString(String src)
		throws Exception;
	
	
}
