/**
 * 
 */
package com.resttemplate.common.bean;

import java.io.Serializable;

/**
 * @comment : 
 * @date    : 2013. 11. 3.
 * @author  : Rejoice
 */
@SuppressWarnings("serial")
public class FileMeta implements Serializable{
	
	/**
	 * @comment :
	 * @date    : 2013. 11. 3.
	 * @author  : Rejoice
	 */
	public FileMeta() {
		super();
	}
	/**
	 * @comment :
	 * @date    : 2013. 11. 3.
	 * @author  : Rejoice
	 * @param path
	 * @param ext
	 * @param size
	 */
	public FileMeta(String path, long size, String ext) {
		super();
		this.path = path;
		this.ext = ext;
		this.size = size;
	}


	private String path, ext;
	private String name;
	private long size;
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the ext
	 */
	public String getExt() {
		return ext;
	}
	/**
	 * @param ext the ext to set
	 */
	public void setExt(String ext) {
		this.ext = ext;
	}
	/**
	 * @return the size
	 */
	public long getSize() {
		return size;
	}
	/**
	 * @param size the size to set
	 */
	public void setSize(long size) {
		this.size = size;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
}