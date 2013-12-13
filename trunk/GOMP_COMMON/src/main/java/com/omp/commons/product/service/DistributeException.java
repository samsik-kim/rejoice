/*
 * COPYRIGHT(c) SK telecom 2011
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author | Date        | Description
 * ---------------------------------------
 * bcpark | 2011. 3. 28. | Description
 *
 */
package com.omp.commons.product.service;

import com.omp.commons.exception.BaseException;

/**
 * The Exception Class Of Distribution </p> case1 : Display Distribution Error case2 : Download Distribution Error
 * 
 * @author Administrator
 * @version 0.1
 */
@SuppressWarnings("serial")
public class DistributeException extends BaseException {

	public DistributeException(String msgSrc, Object[] msgArgs, Throwable cause) {
		super(msgSrc, msgArgs, cause);
	}

	public DistributeException(String msgSrc, Object[] msgArgs) {
		super(msgSrc, msgArgs);
	}

	public DistributeException(String msgSrc, Throwable cause) {
		super(msgSrc, cause);
	}

	public DistributeException(String msgSrc) {
		super(msgSrc);
	}

	public DistributeException(Throwable cause) {
		super(cause);
	}

}
