/**
 * 
 */
package com.resttemplate.sample.bean;

import java.util.List;

/**
 * @comment : 
 * @date    : 2013. 11. 4.
 * @author  : Rejoice
 */
public class TestBean {
	public SubTestBean subTestBean;
	public List<Agreement> agreementList;

	/**
	 * @return the agreementList
	 */
	public List<Agreement> getAgreementList() {
		return agreementList;
	}

	/**
	 * @param agreementList the agreementList to set
	 */
	public void setAgreementList(List<Agreement> agreementList) {
		this.agreementList = agreementList;
	}

	/**
	 * @comment :
	 * @date    : 2014. 1. 4.
	 * @author  : Rejoice
	 */
	public TestBean() {
		super();
	}

	/**
	 * @return the subTestBean
	 */
	public SubTestBean getSubTestBean() {
		return subTestBean;
	}

	/**
	 * @param subTestBean the subTestBean to set
	 */
	public void setSubTestBean(SubTestBean subTestBean) {
		this.subTestBean = subTestBean;
	}

	/**
	 * @comment : 
	 * @date    : 2014. 1. 4.
	 * @author  : Rejoice
	 */
	public class SubTestBean {
		
		/**
		 * @comment :
		 * @date    : 2014. 1. 4.
		 * @author  : Rejoice
		 */
		public SubTestBean() {
			super();
		}
		String c;
		String d;
		/**
		 * @return the c
		 */
		public String getC() {
			return c;
		}
		/**
		 * @param c the c to set
		 */
		public void setC(String c) {
			this.c = c;
		}
		/**
		 * @return the d
		 */
		public String getD() {
			return d;
		}
		/**
		 * @param d the d to set
		 */
		public void setD(String d) {
			this.d = d;
		}
	}
	
	public class Agreement{
		public String extraAgreementId="US0001";
		public String extraAgreementVersion="v1";
		public String isExtraAgreement="Y";

	}
}