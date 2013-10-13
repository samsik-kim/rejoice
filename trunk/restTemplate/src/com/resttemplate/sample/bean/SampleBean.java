/**
 * 
 */
package com.resttemplate.sample.bean;

import com.sun.istack.internal.NotNull;

/**
 * @comment : 
 * @date    : 2013. 10. 12.
 * @author  : Rejoice
 */
public class SampleBean {

	@Size(min=1, message="")
	@NotNull
	private String code;
}
