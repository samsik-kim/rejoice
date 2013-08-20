package com.nmimo.common.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class StringUtils {
	public static final String[][] toText = { { "&", "&amp;" },
			{ "<", "&lt;" }, { ">", "&gt;" }, { "\"", "&quot;" } };

	public static final String[][] toHtml = { { "&quot;", "\"" },
			{ "&gt;", ">" }, { "&lt;", "<" }, { "&amp", "&" } };

	public static final String[][] toSafeSql = { { "'", "''" }, { ";", "" },
			{ "--", "" }, { "|", "" }, { ":", "" }, { "+", "" }, { "\\", "" },
			{ "/", "" }, { "|", "" } };

	public static String replace(String str, String src, String tgt) {
		StringBuffer buf = new StringBuffer();
		String ch = null;
		if (str == null || str.length() == 0)
			return "";
		int i = 0;
		int len = src.length();
		while (i < str.length() - len + 1) {
			ch = str.substring(i, i + len);
			if (ch.equals(src)) {
				buf.append(tgt);
				i += len;
			} else {
				buf.append(str.substring(i, i + 1));
				++i;
			}
		}
		if (i < str.length())
			buf.append(str.substring(i, str.length()));
		return buf.toString();
	}

	public static List arrayToList(String[] parameterValues) {
		List list = new ArrayList();
		int size = parameterValues.length;
		for (int i = 0; i < size; ++i) {
			if (parameterValues[i] != null)
				list.add(parameterValues[i]);
		}
		return list;
	}

	private static String cutString(String str, int limit, int hangleByte) {
		int len = str.length();
		int cnt = 0;
		int index = 0;

		while (index < len && cnt < limit) {
			if (str.charAt(index++) < 256) {
				++cnt;
			} else
				cnt += hangleByte;

		}

		if (index < len && limit >= cnt)
			str = str.substring(0, index);
		else if (index < len && limit < cnt) {
			str = str.substring(0, index - 1);
		}
		return str;
	}

	public static String cutString(String str, int limit) {
		return cutString(str, limit, 2);
	}

	public static String cutUniCodeString(String str, int limit) {
		return cutString(str, limit, 3);
	}

	public static String replaceHtml2String(String value) {
		return replaceMulti(value, toText);
	}

	public static String replaceString2Html(String value) {
		return replaceMulti(value, toHtml);
	}

	private static String replaceMulti(String value, String[][] arrReplace) {
		if (value == null || "".equals(value)) {
			return "";
		}
		StringBuffer strBuffer = new StringBuffer(value);
		int start_index = 0;
		int find_index = 0;

		for (int i = 0; i < arrReplace.length; ++i) {
			start_index = find_index = 0;

			while ((find_index = strBuffer.indexOf(arrReplace[i][0],
					start_index)) != -1) {
				strBuffer.replace(find_index,
						find_index + arrReplace[i][0].length(),
						arrReplace[i][1]);
				start_index = find_index + arrReplace[i][1].length();
			}
		}

		return strBuffer.toString();
	}

	public static String removeLastDelimiter(String srcData, String delim) {
		String rtnData = "";
		if (srcData.length() > 0
				&& srcData.lastIndexOf(delim) == srcData.length() - delim.length())
			rtnData = srcData.substring(0, srcData.length() - delim.length());
		return rtnData;
	}

	public static String replaceNonWordChr(String value) {
		if (value == null)
			return value;
		return value.replaceAll("(?im)\\W", "");
	}

	public static String safeXML(String str) {
		if (str == null)
			return str;
		char replaceChar = ' ';
		StringBuffer sb = new StringBuffer(str.length());

		for (int i = 0; i < str.length(); ++i) {
			char ch = str.charAt(i);
			if (ch != '\t' && ch != '\n' && ch != '\r'
					&& ch < ' ' || ch > 55295
					&& ch < 57344 || ch > 65533
					&& ch < 65536 || ch > 1114111)
				sb.append(replaceChar);
			else {
				sb.append(ch);
			}
		}

		return sb.toString();
	}

	public static boolean getString2Boolean(String tmp) {
		boolean returnStr = false;
		if (tmp != null && tmp.length() > 0) {
			tmp = tmp.trim();
			if (tmp.equals("Y")) {
				returnStr = true;
			}
		}
		return returnStr;
	}

	public static String RPAD(String str, int iLen, char cPad) {
		String result = str;
		int iTempLen = iLen - result.getBytes().length;
		for (int i = 0; i < iTempLen; ++i) {
			result = result + cPad;
		}
		return result;
	}

	public static String LPAD(String str, int iLen, char cPad) {
		String result = str;
		int iTempLen = iLen - result.getBytes().length;
		for (int i = 0; i < iTempLen; ++i)
			result = cPad + result;
		return result;
	}

	public static String appendNumberDelimeter(String str, String del) {
		DecimalFormat df = new DecimalFormat();
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setGroupingSeparator(del.charAt(0));
		df.setGroupingSize(3);
		df.setDecimalFormatSymbols(dfs);
		return df.format(Double.parseDouble(str)).toString();
	}

	public static String nvlStr(String orgStr, String initStr) {
		if (orgStr == null || orgStr.equals("")) {
			return initStr;
		}
		return orgStr;
	}

	public static String nvlStr(String orgStr) {
		return nvlStr(orgStr, "");
	}

	public static boolean isNVL(String orgStr) {
		return orgStr != null && !"".equals(orgStr.trim());
	}

	public static boolean isNotNVL(String orgStr) {
		return !(isNVL(orgStr));
	}

	public static String replaceSaftSql(String value) {
		String result = "";
		result = nvlStr(replace(value.toLowerCase(), "select", ""), "");
		result = nvlStr(replace(value.toLowerCase(), "update", ""), "");
		result = nvlStr(replace(value.toLowerCase(), "delete", ""), "");
		result = nvlStr(replace(value.toLowerCase(), "insert", ""), "");
		return replaceMulti(result, toSafeSql);
	}
}