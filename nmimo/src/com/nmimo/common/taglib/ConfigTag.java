package com.nmimo.common.taglib;

import javax.servlet.jsp.tagext.Tag;

import org.springframework.web.servlet.tags.HtmlEscapingAwareTag;

import com.nmimo.common.WCommon;
import com.nmimo.common.configuration.ConfigurationService;
import com.nmimo.common.util.StringUtils;

@SuppressWarnings("serial")
public class ConfigTag extends HtmlEscapingAwareTag {

	private String key;

    /**  configurationService */
	private ConfigurationService configurationService;

	/**
	 * <pre>
	 * {@inheritDoc}
	 * </pre>
	 * 
	 * @see org.springframework.web.servlet.tags.RequestContextAwareTag#doStartTagInternal()
	 * 
	 * @return
	 * @throws Exception
	 * 
	 */
	@Override
	protected int doStartTagInternal() throws Exception {
       ConfigurationService configurationService =  (ConfigurationService) WCommon.getBean("configurationService");

       String message = StringUtils.nvlStr(configurationService.getString(key));

	   /** The extended properties. */
		pageContext.getOut().write(message);
		return Tag.SKIP_BODY;
	}

	/**
	 * <pre>
	 * String 을 반환하는 기능을 제공한다.
	 * </pre>
	 * 
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	/**
	 * <pre>
	 * key 을 설정하는 기능을 제공한다.
	 * </pre>
	 * 
	 * @param key
	 *            String
	 */
	public void setKey(String key) {
		this.key = key;
	}
}