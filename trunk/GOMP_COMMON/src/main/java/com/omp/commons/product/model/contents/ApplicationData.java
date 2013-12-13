/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 29. | Description
 *
 */
package com.omp.commons.product.model.contents;

import java.util.List;

/**
 * 배포 연동용 XML 표현 모델
 * @author pat
 *
 */
public class ApplicationData {
	public DeployProduct deploy_product;
	public DeployMainContent deploy_main_content;
	public List<DeploySubContent> deploy_sub_content;
}
