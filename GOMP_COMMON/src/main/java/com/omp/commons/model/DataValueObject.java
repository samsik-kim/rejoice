package com.omp.commons.model;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import pat.neocore.lang.NoSuchTargetException;
import pat.neocore.util.BeansUtil;
import pat.neocore.util.StringPattern;
import pat.neocore.util.objlook.ObjectLooker;

/**
 * 데이터 값 객체의 슈퍼 클래스
 * @author pat
 *
 */
@SuppressWarnings("serial")
public abstract class DataValueObject
	implements Serializable {
	private static final Pattern		PTN_PROPNAME_ARR	= Pattern.compile("\\.([0-9]+)\\.");
	
	/**
	 * 특정 객체의 내용을 복사하여 모델의 속성에 값을 설정합니다.
	 * 복사 기준은 프로퍼티 명입니다.
	 * @param from 복사 원본 객체입니다. 빈즈, HttpRequest, Map, ResultSet등이 지정
	 * 될 수 있으며, HttpRequest, Map, ResultSet가 지정 된다면 대상 객체에 알맞는 
	 * 데이터 획득방법을 사용하여 프로퍼티명에 해당하는 값을 설정합니다.
	 * @return 설정된 프로퍼티의 갯수가 반환됩니다.
	 * @throws Exception
	 */
	public int copyFrom(Object from)
		throws Exception {
		return copy(from, this, null, false);
	}
	
	/**
	 * 특정 객체의 내용을 복사하여 모델의 속성에 값을 설정합니다.
	 * 복사 기준은 프로퍼티 명입니다.
	 * @param from 복사 원본 객체입니다. 빈즈, HttpRequest, Map, ResultSet등이 지정
	 * 될 수 있으며, HttpRequest, Map, ResultSet가 지정 된다면 대상 객체에 알맞는 
	 * 데이터 획득방법을 사용하여 프로퍼티명에 해당하는 값을 설정합니다.
	 * @param skipNull true가 지정되면 대상 객체의 데이터가 null일때 설정을 건너 뜁니다.(원본 데이터 유지)
	 * @return 설정된 프로퍼티의 갯수가 반환됩니다.
	 * @throws Exception
	 */
	public int copyFrom(Object from, boolean skipNull)
		throws Exception {
		return copy(from, this, null, skipNull);
	}

	/**
	 * 특정 객체의 내용을 복사하여 모델의 속성에 값을 설정합니다.
	 * 복사 기준은 프로퍼티 명입니다.
	 * @param from 복사 대상 객체입니다. 빈즈, HttpRequest, Map, ResultSet등이 지정
	 * 될 수 있으며, HttpRequest, Map, ResultSet가 지정 된다면 대상 객체에 알맞는 
	 * 데이터 획득방법을 사용하여 프로퍼티명에 해당하는 값을 설정합니다.
	 * @param pattern 복사 대상 프로퍼티명을 문자열 패턴(와일드 카드 *나 ?을 사용한)을 지정하여
	 * 범위를 제한 할 수 있습니다.
	 * @throws Exception
	 */
	public int copyFrom(Object from, String pattern)
		throws Exception {
		return copy(from, this, pattern, false);
	}
	
	/**
	 * 특정 객체의 내용을 복사하여 모델의 속성에 값을 설정합니다.
	 * 복사 기준은 프로퍼티 명입니다.
	 * @param from 복사 원본 객체입니다. 빈즈, HttpRequest, Map, ResultSet등이 지정
	 * 될 수 있으며, HttpRequest, Map, ResultSet가 지정 된다면 대상 객체에 알맞는
	 * 데이터 획득방법을 사용하여 프로퍼티명에 해당하는 값을 설정합니다.
	 * @param pattern 복사 원본 프로퍼티명을 문자열 패턴(와일드 카드 *나 ?을 사용한)을 지정하여
	 * 범위를 제한 할 수 있습니다.
	 * @param skipNull true가 지정되면 대상 객체의 데이터가 null일때 설정을 건너 뜁니다.(원본 데이터 유지)
	 * @return 설정된 프로퍼티의 갯수가 반환됩니다.
	 * @throws Exception
	 */
	public int copyFrom(Object from, String pattern, boolean skipNull)
		throws Exception {
		return copy(from, this, pattern, skipNull);
	}
	
	/**
	 * 특정 객체의 내용을 복사하여 모델의 속성에 값을 설정합니다.
	 * 복사 기준은 프로퍼티 명입니다.
	 * @param from 복사 원본 객체입니다. 빈즈, HttpRequest, Map, ResultSet등이 지정
	 * 될 수 있으며, HttpRequest, Map, ResultSet가 지정 된다면 대상 객체에 알맞는 
	 * 데이터 획득방법을 사용하여 프로퍼티명에 해당하는 값을 설정합니다.
	 * @param to 복사 되는 대상 객체입니다.
	 * 복사 대상에 존재하는 setter를 사용하여 값을 복사 합니다.
	 * @return 설정된 프로퍼티의 갯수가 반환됩니다.
	 * @throws Exception
	 */
	public static int copy(Object from, Object to)
		throws Exception {
		return copy(from, to, null, false);
	}
	
	/**
	 * 특정 객체의 내용을 복사하여 모델의 속성에 값을 설정합니다.
	 * 복사 기준은 프로퍼티 명입니다.
	 * @param from 복사 원본 객체입니다. 빈즈, HttpRequest, Map, ResultSet등이 지정
	 * 될 수 있으며, HttpRequest, Map, ResultSet가 지정 된다면 대상 객체에 알맞는 
	 * 데이터 획득방법을 사용하여 프로퍼티명에 해당하는 값을 설정합니다.
	 * @param to 복사 되는 대상 객체입니다.
	 * 복사 대상에 존재하는 setter를 사용하여 값을 복사 합니다.
	 * @param skipNull true가 지정되면 대상 객체의 데이터가 null일때 설정을 건너 뜁니다.(원본 데이터 유지)
	 * @return 설정된 프로퍼티의 갯수가 반환됩니다.
	 * @throws Exception
	 */
	public static int copy(Object from, Object to, boolean skipNull)
		throws Exception {
		return copy(from, to, null, skipNull);
	}

	
	/**
	 * 특정 객체의 내용을 복사하여 모델의 속성에 값을 설정합니다.
	 * 복사 기준은 프로퍼티 명입니다.
	 * @param from 복사 원본 객체입니다. 빈즈, HttpRequest, Map, ResultSet등이 지정
	 * 될 수 있으며, HttpRequest, Map, ResultSet가 지정 된다면 대상 객체에 알맞는
	 * 데이터 획득방법을 사용하여 프로퍼티명에 해당하는 값을 설정합니다.
	 * @param to 복사 되는 대상 객체입니다.
	 * 복사 대상에 존재하는 setter를 사용하여 값을 복사 합니다.
	 * @param pattern 복사 원본 프로퍼티명을 문자열 패턴(와일드 카드 *나 ?을 사용한)을 지정하여
	 * 범위를 제한 할 수 있습니다.
	 * @param skipNull true가 지정되면 대상 객체의 데이터가 null일때 설정을 건너 뜁니다.(원본 데이터 유지)
	 * @return 설정된 프로퍼티의 갯수가 반환됩니다.
	 * @throws Exception
	 */
	public static int copy(Object from, Object to, String pattern, boolean skipNull)
		throws Exception {
		int				rv;
		Iterator<?>		propIts;
		StringPattern	sptn;
		ObjectLooker	olook;

		if (from == null) {
			throw new NullPointerException("copy From is Null!");
		}
		if (to == null) {
			throw new NullPointerException("copy To is Null!");
		}
		if (pattern == null) {
			sptn	= null;
		} else {
			sptn	= new StringPattern(pattern);
		}
		rv		= 0;
		propIts	= BeansUtil.listNestedSetPropNames(to).iterator();
		olook	= ObjectLooker.getLooker(from);
		while (propIts.hasNext()) {
			String	propName;
			Object	value;
			
			propName	= (String)propIts.next();
			if (sptn != null && !sptn.isMatch(propName)) {
				continue;
			}
			try {
				value	= olook.getObject(from, propName);
			} catch (NoSuchTargetException ignore) {
				continue;
			}
			if (skipNull && value == null) {
				continue;
			}
			BeansUtil.setProperty(to, propName, value);
			rv++;
		}
		
		return rv;		
	}
	
	
	/**
	 * 모델이 포함하는 프로퍼티의 내용이 출력되도록 오버라이드 되었습니다.
	 */
	public String toString() {
        ObjectLooker    olook;
        Iterator<?>     its;
        StringBuffer    sb;
        boolean         flag;
        StringBuffer	pnsb;

        olook   = ObjectLooker.getLooker(this);
        its     = olook.getAllNameSet(this).iterator();
        sb      = new StringBuffer();
        pnsb	= new StringBuffer();
        flag    = false;

        sb.append(this.getClass().getName() + ":");
        while (its.hasNext()) {
            String  dName;
            Object  value;
            Matcher	mch;

            dName   = (String)its.next();
            mch		= PTN_PROPNAME_ARR.matcher(dName);
            pnsb.setLength(0);
            while (mch.find()) {
            	mch.appendReplacement(pnsb, "[" + mch.group(1) + "].");
            }
            mch.appendTail(pnsb);
            dName	= pnsb.toString();
            
            value   = olook.getObject(this, dName);
            if (flag) {
                sb.append(", ");
            } else {
                flag    = true;
            }
            sb.append(dName);
            sb.append('=');
            if (value == null) {
                sb.append("[NULL]");
            }
            if (value.getClass().isArray()) {
                int arSize;

                arSize  = Array.getLength(value);
                sb.append('[');
                for (int i=0; i<arSize; i++) {
                    if (i > 0) {
                        sb.append(", ");
                    }
                    sb.append(Array.get(value, i));
                }
                sb.append(']');
            } else {
                sb.append(value);
            }
        }
        return sb.toString();
	}

}
