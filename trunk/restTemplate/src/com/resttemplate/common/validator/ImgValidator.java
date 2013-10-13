/**
 * 
 */
package com.resttemplate.common.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.resttemplate.sample.bean.ImgInfo;

/**
 * @comment : 
 * @date    : 2013. 10. 12.
 * @author  : Rejoice
 */
public class ImgValidator implements Validator {

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return ImgInfo.class.equals(clazz); 
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
		
		ImgInfo info = (ImgInfo) obj;
		
	}

}
