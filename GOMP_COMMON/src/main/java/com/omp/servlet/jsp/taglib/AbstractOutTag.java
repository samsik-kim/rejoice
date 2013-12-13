package com.omp.servlet.jsp.taglib;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.math.BigDecimal;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTagSupport;

import com.omp.commons.text.LocalizeMessage;


/**
 * 출력 태그의 공통 부분을 구현한 추상 클래스<br>
 * 프로퍼티 일람<br>
 * <ul>
 * <li>format : 출력할 포멧을 지정합니다. 생략하면 각 타입별 기본값이 지정됩니다.
 * value의 값이 Date형식이라면 SimpleDateFormat, Number 형식이라면 SimpleDateFormat에 전달할
 * 포멧 문자열로 간주합니다.<br>
 * value의 값이 String이라면, 아래의 형식으로 지정합니다.<br>
 * <ul>
 * <li>첫글자 : 정렬 방향 L좌 R우</li>
 * <li># : 원본 글자 대응, 글자가 존재하지 않으면 생략</li>
 * <li>0 : 원본 글자 대응, 글자가 존재하지 않으면 0</li>
 * <li>. : 원본 글자 대응, 원본 글자 길이가 포멧글자수 보다 넘칠경우., 글자가 존재하지 않을경우 생략</li>
 * <li>\ : 이스케입 문자, 다음에 나타나는 문자를 삽입 합니다.</li>
 * <li>그이외 문자 : 글자 사이에 삽입. 단! 포멧 문자의수가 대상 문자의 수보다 많고,
 * 정렬 방향쪽으로 다음 글자가 미존재시 나타나지 않습니다.</li>
 * </ul>
 * <li>value : 출력 대상 값을 지정합니다. EL식 사용 가능</li>
 * <li>태그바디 : value의 값이 지정 되지 않았을때 value를 지정하는 효과를 냅니다.</li>
 * </ul>
 * @author pat
 */
@SuppressWarnings("serial")
public abstract class AbstractOutTag
	extends BodyTagSupport {
	
	/**
	 * java.sql.Date 타입의 기본포멧 "yyyy/MM/dd" 입니다.
	 */
	public static final String	DEFAULT_FORMAT_SQL_DATE	= "yyyy/MM/dd";
	/**
	 * java.sql.Time 타입의 기본포멧 "HH:mm:ss" 입니다.
	 */
	public static final String	DEFAULT_FORMAT_SQL_TIME	= "HH:mm:ss";
	/**
	 * java.util.Date 타입의 기본 포멧 "yyyy/MM/dd HH:mm:ss" 입니다. 
	 */
	public static final String	DEFAULT_FORMAT_DATE		= "yyyy/MM/dd HH:mm:ss";
	/**
	 * 정수 숫자 타입의 기본 포멧 ",###" 입니다.
	 */
	public static final String	DEFAULT_FORMAT_INTEGER	= "############";
	/**
	 * 실수 숫자 타입의 기본포멧 ",###.0" 입니다.
	 */
	public static final String	DEFAULT_FORMAT_DECIMAL	= ",###.0#########";
	
	/**
	 * 출력 대상을 바인드명 혹은 EL식을 지정합니다
	 */
	protected Object	value;
	/**
	 * 출력 포멧일 지정합니다.
	 * 포멧 문자열의 식은 출력 대상 객체의 형식에 따라 다릅니다. 
	 */
	protected String	format;
	
	/**
	 * 출력 대상을 바인드명 혹은 EL식을 지정합니다
	 * @param value
	 */
	public void setValue(Object value) {
		this.value	= value;
	}
	
	/**
	 * 출력 포멧일 지정합니다.
	 * 포멧 문자열의 식은 출력 대상 객체의 형식에 따라 다릅니다. 
	 * @param format
	 */
	public void setFormat(String format) {
		if (format != null && format.length() == 0) {
			format	= null;
		}
		this.format	= format;
	}
	
	/**
	 * 태그의 기능을 구현하기 위해 오버라이드 되었습니다.
	 */
	public int doEndTag()
		throws JspException {
		try {
			Object		obj;
			String		vstr;
			JspWriter	jout;
			
			if (this.value == null) {
				BodyContent	bct;
				
				bct	= this.getBodyContent();
				if (bct != null) {
					this.value	= bct.getString();
				}
			}
			obj	= this.value;
			if (obj instanceof java.util.Date){
				SimpleDateFormat	sdf;
				
				if (this.format == null) {
					if (obj instanceof java.sql.Date) {
						sdf	= new SimpleDateFormat(DEFAULT_FORMAT_SQL_DATE);
					} else if (obj instanceof java.sql.Time) {
						sdf	= new SimpleDateFormat(DEFAULT_FORMAT_SQL_TIME);
					} else {
						sdf	= new SimpleDateFormat(DEFAULT_FORMAT_DATE);
					}
				} else {
					sdf	= new SimpleDateFormat(this.format);
				}
				vstr	= sdf.format((java.util.Date)obj);
			} else if (obj instanceof Number) {
				Number			num;
				DecimalFormat	df;
				
				num	= (Number)obj;
				if (this.format == null) {
					if (obj instanceof BigDecimal
							|| obj instanceof Double
							|| obj instanceof Float) {
						df	= new DecimalFormat(DEFAULT_FORMAT_DECIMAL);
					} else {
						df	= new DecimalFormat(DEFAULT_FORMAT_INTEGER);
					}
				} else {
					df	= new DecimalFormat(this.format);
				}
				vstr	= df.format(num);
			} else {
				if (obj == null) {
					vstr	= "";
				} else {
					vstr	= obj.toString();
				}
				if (this.format != null && vstr.length() > 0) {
					vstr	= this.getFormatStr(vstr);
				}
			}
			jout	= this.pageContext.getOut();
			jout.print(this.getEncodeString(vstr));
		} catch (JspException e) {
			throw e;
		} catch (Exception e) {
			throw new JspException("Ubigent Tag render fail.", e);
		} finally {
			this.release();
		}
		return EVAL_PAGE;
	}
	
	/**
	 * 출력대상이 문자 타입일때 지정된 포멧에 맞도록 번안하여
	 * 반환합니다.
	 * @param src
	 * @return
	 * @throws Exception
	 */
    private String getFormatStr(String src)
	    throws Exception {
	    StringBuffer	rv;
	    char			cp;
	    int				allow;
	    StringBuffer	fmtStr;
	    int				fmtLen;
	    int				srcLen;
	    int				wlen;
	    int				spos;
	    boolean			ofFlag;
	
	    if (this.format.length() < 2) {
	        throw new JspException(LocalizeMessage.getLocalizedMessage("문자열을 대상으로 하는 포멧 문자열은 2자리 미만 일 수 없습니다."));
	    }
	    cp = this.format.charAt(0);
	    if (cp == 'L' || cp == 'l') {
	        allow	= 1;
	    } else if (cp == 'R' || cp == 'r') {
	        allow	= -1;
	    } else {
	        throw new JspException(LocalizeMessage.getLocalizedMessage("정렬 문자는 L혹은 R이어야 합니다."));
	    }
	    if (this.format.contains(".")) {
	    	// 말줄밍 포멧이 있을경우 소스에서 첫줄만 사용
	    	src	= src.split("\n")[0];
	    }
	    fmtStr	= new StringBuffer();
	    srcLen	= this.format.length()-1;
	    fmtLen	= 0;
	    for (int i=0; i<srcLen; i++) {
	        cp	= this.format.charAt(i+1);
	        if (cp == '#' || cp == '0' || cp == '.' || cp == '*' || cp == '^') {
	            fmtLen++;
	            fmtStr.append(cp);
	        } else if (cp == '\\') {
	            i++;
	            if (i == srcLen) {
	                throw new JspException(
	                    LocalizeMessage.getLocalizedMessage("포멧문자열은 이스케입 문자\\로 종결 될 수 없습니다."));
	            }
	            fmtStr.append('\\');
	            fmtStr.append(this.format.charAt(i+1));
	        } else {
	            fmtStr.append(cp);
	        }
	    }
	    if (allow == -1) {
	        fmtStr  = fmtStr.reverse();
	        src	    = new StringBuffer(src).reverse().toString();
	    }
	    srcLen	= src.length();
	    if (srcLen > fmtLen) {
	        ofFlag	= true;
	        src		= src.substring(0, fmtLen);
	    } else {
	        ofFlag	= false;
	    }
	    wlen	= fmtStr.length();
	    spos	= 0;
	    rv		= new StringBuffer();
	    for (int i=0; i<wlen; i++) {
	        cp	= fmtStr.charAt(i);
	        if (cp == '#') {
	            if (spos < srcLen) {
	                rv.append(src.charAt(spos++));
	            }
	        } else if (cp == '0') {
	            if (spos < srcLen) {
	                rv.append(src.charAt(spos++));
	            } else {
	                rv.append('0');
	            }
	        } else if (cp == '.') {
	            if (spos < srcLen) {
	                if (ofFlag) {
	                    rv.append('.');
	                } else {
	                    rv.append(src.charAt(spos));
	                }
	                spos++;
	            }
	        } else if (cp == '*') {
	            if (spos < srcLen) {
	            	rv.append("*");
	            	spos++;
	            }
	        } else if (cp == '^') {
	            if (spos < srcLen) {
	            	spos++;
	            }
	        } else if (cp == '\\') {
	            if (spos < srcLen) {
	                rv.append(fmtStr.charAt(++i));
	            }
	        } else {
	            if (fmtLen == srcLen || spos < srcLen) {
	                rv.append(cp);
	            }
	        }
	    }
	    if (allow == -1) {
	        rv	= rv.reverse();
	    }
	
	    return rv.toString();
	}
    
    /**
     * 태그의 기능 구현을 위해 오버라이딩 되었습니다.
     */
    public void release() {
    	this.value	= null;
    	this.format	= null;
    }

    /**
     * 출력될 위치에 맞게 인코딩 하는 코드를 구현하십시오.
     * @param src 포멧팅된 출력 대상 문자열
     * @return 인코딩된 문자열
     * @throws Exception
     */
	protected abstract String getEncodeString(String src)
		throws Exception;
}
