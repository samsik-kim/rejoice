package com.omp.commons.model;

import java.io.Serializable;

/**
 * 컬럼의 정보를 지시하는 모델
 * @author pat
 *
 */
@SuppressWarnings("serial")
public class ColumnInfoModel
	implements Serializable {
	
	public static final int COLUMN_TYPE_OBJECT	= 1;
	public static final int COLUMN_TYPE_STRING	= 2;
	public static final int COLUMN_TYPE_INTEGER	= 3;
	public static final int COLUMN_TYPE_LONG	= 4;
	public static final int COLUMN_TYPE_FLOAT	= 5;
	public static final int COLUMN_TYPE_DOUBLE	= 6;
	public static final int COLUMN_TYPE_DATE	= 7;
	public static final int COLUMN_TYPE_CODE	= 8;
	public static final int COLUMN_TYPE_ENCRYRT = 9;
	
	
	
	
	private String 	name;
	private String 	text;
	private int		type;
	
	public ColumnInfoModel(String name, String text) {
		this(name, text, COLUMN_TYPE_OBJECT);
	}
	
	public ColumnInfoModel(String name, String text, int type) {
		this.name	= name;
		this.text	= text;
		this.type	= type;
	}
	
	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

	public int getType() {
		return type;
	}
}
