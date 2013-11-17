/**
 * 
 */
package com.resttemplate.rejoiceAPI.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @comment : 
 * @date    : 2013. 10. 6.
 * @author  : Rejoice
 */
@XmlRootElement(name = "message")
public class Message {

	@XmlElement
	private String name="kimgyeongbok";

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

}