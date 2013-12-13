package com.omp.dev.community.action;

import com.omp.commons.action.BaseAction;
import com.omp.commons.util.GLogger;

/**
 * Guide Action
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class GuideAction extends BaseAction {
	private static GLogger logger = new GLogger(FaqAction.class);

	public GuideAction() {
		
	}

	/**
	 * Member Guide
	 * @return
	 */
	public String memberGuide() {
		return SUCCESS;
	}
	
	/**
	 * Product Register Management Basic Info Guide
	 * @return
	 */
	public String basicInfoGuide() {
		return SUCCESS;
	}
	
	/**
	 * Product Register Management Detail Info Guide
	 * @return
	 */
	public String detailInfoGuide() {
		return SUCCESS;
	}
	
	/**
	 * Product Register Management Dev Info Guide
	 * @return
	 */
	public String devInfoGuide() {
		return SUCCESS;
	}
	
	/**
	 * Product Verification Guide
	 * @return
	 */
	public String prodVerificationGuide() {
		return SUCCESS;
	}
	
	/**
	 * Sale Caculate Guide
	 * @return
	 */
	public String saleCalculateGuide() {
		return SUCCESS;
	}
	
	/**
	 * Application DRM Guide
	 * @return
	 */
	public String appDrmGuide() {
		return SUCCESS;
	}

}