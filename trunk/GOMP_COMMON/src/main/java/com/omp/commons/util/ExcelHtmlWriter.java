package com.omp.commons.util;

import java.io.*;
import java.text.*;
import java.util.*;

import com.omp.commons.text.LocalizeMessage;

import pat.neocore.util.*;

/**
 * <p>Title: ExcelHtmlWriter</p>
 *
 * <p>Description: HTML형식으로 작성되는 Excel 라이터</p>
 *
 * @author pat
 * @version 1.0
 */
public class ExcelHtmlWriter {

    /**
     * 필드의 정렬방향 지정하지 않음
     */
    public static final int ALIGN_NOT_ASSIGN    = -1;
    /**
     * 필드의 정렬방향 왼쪽 정렬
     */
    public static final int ALIGN_LEFT          = 0;
    /**
     * 필드의 정렬방향 중앙 정렬
     */
    public static final int ALIGN_CENTER        = 1;
    /**
     * 필드의 정렬방향 오른쪽 정렬
     */
    public static final int ALIGN_RIGHT         = 2;
    /**
     * 필드의 정렬방행 상수에 해당하는 정렬 지시 문자열
     */
    private static final String[]   ALIGNS      = {
        "left", "center", "right"
    };
    /**
     * 멀티 파트 구분자 생성에 사용될 랜덤객체
     */
    private static final Random     RND         =
        new Random(System.currentTimeMillis());

    /**
     *
     */
    private static final String     DEFAULT_INTEGER_FORMAT =
        "#,##0_ ";

    /**
     *
     */
    private static final String     DEFAULT_FLOAT_FORMAT =
        "#,##0.0#########_ ";

    private static final SimpleDateFormat   DF_DATE     =
        new SimpleDateFormat("yyyy-MM-dd");

    private static final String     DEFAULT_DATE_FORMAT =
        "yyyy\\0022-\\0022m\\0022-\\0022d\\;\\@";

    private static final SimpleDateFormat   DF_TIME     =
        new SimpleDateFormat("HH:mm:ss");

    private static final String     DEFAULT_TIME_FORMAT =
        "h\\:mm\\:ss\\;\\@";

    private static final SimpleDateFormat   DF_DATETIME =
        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String     DEFAULT_DATETIME_FORMAT =
        "yyyy\\0022-\\0022m\\0022-\\0022d\\\\ h\\:mm\\:ss\\;\\@";


    /**
     * 출력 대상 프린트 라이터
     */
    private PrintWriter pw;
    /**
     * 출력 엑셀이 가질 씨트수
     */
    private int         maxSheet;
    /**
     * 현재 출력중 인 씨트 번호(0부터)
     */
    private int         curSheetNum;
    /**
     * 이미 시트 정보 출력했으면 true
     */
    private boolean     commited;
    /**
     * 시트 이름들을 가지는 해쉬맵
     */
    private HashMap<Integer, String>	sheetNames;
    /**
     * 파일간 구분자
     */
    private String      multiPartBoundStr;
    private String      startPartBoundStr;
    private String      endPartBoundStr;
    /**
     * 가상 파일 디렉토리 Path
     */
    private String      vDirPath;
    /**
     * 케릭터셋
     */
    private String		charset;
    
    
    /**
     * 생성자.<br>
     * 주어진 OutputStream에 엑셀 파일을 출력하는 엑셀 라이터를 생성합니다.
     * @param os OutputStream
     */
    public ExcelHtmlWriter(OutputStream os, String charset)
    	throws UnsupportedEncodingException {
        this.pw                 = new PrintWriter(new OutputStreamWriter(os, charset), true);
        this.maxSheet           = 1;
        this.curSheetNum        = -1;
        this.sheetNames         = new HashMap<Integer, String>();
        this.commited           = false;
        this.multiPartBoundStr  = "----=ExeclWriterPartBound."
            + Integer.toHexString(pw.hashCode()) + "."
            + Long.toHexString(System.currentTimeMillis()) + "."
            + Long.toHexString(RND.nextLong());
        this.startPartBoundStr  = "--" + this.multiPartBoundStr;
        this.endPartBoundStr    = "--" + this.multiPartBoundStr + "--";
        this.vDirPath           = "file:///C:/ExcelHtmlWriter"
            + Long.toHexString(RND.nextLong()) + "/";
        this.charset			= charset;
    }

    /**
     * 생성자.<br>
     * 주어진 File에 엑셀 파일을 출력하는 엑셀 라이터를 생성합니다.<br>
     * <font color=red>주의!</font> 파일이 이미 존재하는 경우 교체됩니다.
     * @param file File
     * @throws IOException 파일 생성 실패시.
     */
    public ExcelHtmlWriter(File file, String charset)
        throws IOException {
        this(new FileOutputStream(file), charset);
    }

    
    
    /**
     * 최대 시트수를 지정합니다.
     * @param maxSheet int
     * @throws IllegalAccessError 시트정보 출력한 후에 설정하면 발생합니다.
     * @throws IllegalArgumentException 주어진 maxSheet가 1보다 작으면 발생합니다.
     */
    public void setMaxSheet(int maxSheet)
        throws IllegalAccessError, IllegalArgumentException {
        if (maxSheet < 1) {
            throw new IllegalArgumentException(
                "지정하는 시트수는 1보다 작을 수 없습니다.");
        }
        if (this.commited) {
            throw new IllegalAccessError(
                "이미 시트정보를 출력한 후 이므로 최대 시트수를 설정할 수 없습니다.");
        }
        this.maxSheet   = maxSheet;
    }

    /**
     * 시트 이름을 설정합니다.
     * @param sheetIdx int 설정할 시트의 인덱스(0부터 시작)
     * @param name String 설정할 이름
     * @throws IllegalAccessError 시트정보 출력한 후에 설정하면 발생합니다.
     * @throws IllegalArgumentException 주어진 sheetIdx가 음수이거나
     * 시트 이름이 유효하지 않을때.
     */
    public void setSheetName(int sheetIdx, String name)
        throws IllegalArgumentException, IllegalAccessError {
        int nameSize;

        if (sheetIdx < 0) {
            throw new IllegalArgumentException(LocalizeMessage.getLocalizedMessage("시트의 인덱스는 음수일 수 없습니다."));
        }
        if (this.commited) {
            throw new IllegalAccessError(
                "이미 시트정보를 출력한 후 이므로 최대 시트수를 설정할 수 없습니다.");
        }

        name        = name.trim();
        nameSize    = name.length();
        if (nameSize == 0) {
            throw new IllegalAccessError(
                "시트 이름은 반드시 1자 이상 지정되어야 합니다.");
        }
        if (nameSize > 31) {
            throw new IllegalAccessError(
                "시트 이름은 31자 이하 이어야 합니다.");
        }
        for (int i=0; i<nameSize; i++) {
            char    cp;

            cp  = name.charAt(i);
            switch (cp) {
                case '\'':
                case '/':
                case '?':
                case '*':
                case '[':
                case ']':
                    throw new IllegalAccessError(
                        "시트 이름에 '/?*[] 등의 문자는 지정할 수 없습니다.");
            }
        }

        this.sheetNames.put(new Integer(sheetIdx), name);
    }

    private void printText(String src) {
        this.printText(src, false);
    }

    private void printText(String src, boolean encodeUni) {
        this.pw.print(this.getEncText(src, encodeUni));
    }

    private String getEncText(String src, boolean encodeUni) {
        int             srcLength;
        StringBuffer    rv;

        if (src == null) {
            src = "";
        }
        srcLength   = src.length();
        rv          = new StringBuffer();
        for (int i = 0; i < srcLength; i++) {
            char    cp;

            cp  = src.charAt(i);
            switch (cp) {
                case '<':
                    rv.append("&lt;");
                    break;
                case '>':
                    rv.append("&gt;");
                    break;
                case '\r':
                    int j;
                    j   = i+1;
                    if (j < src.length() && src.charAt(j) == '\n') {
                        rv.append("<br>");
                        rv.append("\r\n");
                        i++;
                    }
                    break;
                case '\n':
                    rv.append("<br>");
                    rv.append("\r\n");
                    break;
                case '\"':
                    rv.append("&quot;");
                    break;
                case '&':
                    rv.append("&amp;");
                    break;
                default:
                    if (encodeUni && cp > '~') {
                        rv.append("&#");
                        rv.append((int)cp);
                        rv.append(';');
                    } else {
                        rv.append(cp);
                    }
                    break;
            }
        }
        return rv.toString();
    }


    /**
     * 불린값을 출력합니다.
     * @param b boolean
     */
    public void print(boolean b) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print(b);
    }

    /**
     * 캐릭터를 출력합니다.
     * @param c char
     */
    public void print(char c) {
        this.printText(String.valueOf(c));
    }

    /**
     * 캐릭터 배열을 출력합니다.
     * @param s char[]
     */
    public void print(char[] s) {
        this.printText(new String(s));
    }

    /**
     * 더블값을 출력합니다.
     * @param d double
     */
    public void print(double d) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print(d);
    }

    /**
     * 플로트값을 출력합니다.
     * @param f float
     */
    public void print(float f) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print(f);
    }

    /**
     * 롱값을 출력합니다.
     * @param l long
     */
    public void print(long l) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print(l);
    }

    /**
     * 정수값을 출력합니다.
     * @param i int
     */
    public void print(int i) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print(i);
    }

    /**
     * 문자열을 출력합니다.
     * @param str String
     */
    public void print(String str) {
        this.printText(str);
    }

    /**
     * 객체를 출력합니다.
     * @param obj Object
     */
    public void print(Object obj) {
        this.printText(obj.toString());
    }

    /**
     * 줄을 바꿉니다.
     */
    public void println() {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print("<br>");
        this.pw.print("\r\n");
    }

    /**
     * 불린값을 출력하고 줄을 바꿉니다.
     * @param b boolean
     */
    public void println(boolean b) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print(b);
        this.pw.print("<br>");
        this.pw.print("\r\n");
    }

    /**
     * 문자를 출력하고 줄을 바꿉니다.
     * @param c char
     */
    public void println(char c) {
        this.printText(String.valueOf(c));
        this.pw.print("<br>");
        this.pw.print("\r\n");
    }

    /**
     * 문자 배열을 출력하고 줄을 바꿉니다.
     * @param s char[]
     */
    public void println(char[] s) {
        this.printText(new String(s));
        this.pw.print("<br>");
        this.pw.print("\r\n");
    }

    /**
     * 더블값을 출력하고 줄을 바꿉니다.
     * @param d double
     */
    public void println(double d) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print(d);
        this.pw.print("<br>");
        this.pw.print("\r\n");
    }

    /**
     * 플로트 값을 출력하고 줄을 바꿉니다.
     * @param f float
     */
    public void println(float f) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print(f);
        this.pw.print("<br>");
        this.pw.print("\r\n");
    }

    /**
     * 인티저 값을 출력하고 줄을 바꿉니다.
     * @param i int
     */
    public void println(int i) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print(i);
        this.pw.print("<br>");
        this.pw.print("\r\n");
    }

    /**
     * 롱 값을 출력하고 줄을 바꿉니다.
     * @param l long
     */
    public void println(long l) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print(l);
        this.pw.print("<br>");
        this.pw.print("\r\n");
    }

    /**
     * 문자열을 출력하고 줄을 바꿉니다.
     * @param str String
     */
    public void println(String str) {
        this.printText(str);
        this.pw.print("<br>");
        this.pw.print("\r\n");
    }

    /**
     * 객체를 출력하고 줄을 바꿉니다.
     * @param obj Object
     */
    public void println(Object obj) {
        this.printText(obj.toString());
        this.pw.print("<br>");
        this.pw.print("\r\n");
    }

    /**
     * HTML로 쓰일 문자열을 출력합니다.
     * @param s String
     */
    public void printHtml(String s) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print(s);
    }

    /**
     * 필드의 시작 부분을 출력합니다.
     * @param align int 필드내 데이터의 정렬 방향
     * @param colSpan int 열 병합
     * @param rowSpan int 행 병합
     */
    private void startField(int align, int colSpan, int rowSpan) {
        if (colSpan <= 0) {
            throw new IllegalArgumentException(LocalizeMessage.getLocalizedMessage("유효하지 않은 colSpan 인수입니다."));
        }
        if (rowSpan <= 0) {
            throw new IllegalArgumentException(LocalizeMessage.getLocalizedMessage("유효하지 않은 rowSpan 인수입니다."));
        }
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print("<td");
        if (align >= ExcelHtmlWriter.ALIGN_LEFT
            && align <= ExcelHtmlWriter.ALIGN_RIGHT) {
            this.pw.print(" align=" + ExcelHtmlWriter.ALIGNS[align]);
        }

        if (colSpan > 1) {
            this.pw.print(" colspan=" + colSpan);
        }
        if (rowSpan > 1) {
            this.pw.print(" rowspan=" + rowSpan);
        }
    }

    /**
     * 필드의 끝을 출력합니다.
     */
    private void endField() {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print("</td>");
        this.pw.print("\r\n");
    }

    /**
     * 텍스트 필드의 시작부분을 출력합니다.
     * @param align int 정렬 방향
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    private void startTextField(int align, int colSpan, int rowSpan) {
        this.startField(align, colSpan, rowSpan);
        this.pw.print(" x:str>");
    }

    /**
     * 텍스트 필드의 시작부분을 출력합니다.
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    private void startTextField(int colSpan, int rowSpan) {
        this.startTextField(ExcelHtmlWriter.ALIGN_NOT_ASSIGN, colSpan, rowSpan);
    }

    /**
     * 숫자 필드의 시작부분을 출력합니다.
     * @param String fmt 숫자 포멧 (excel 의 숫자 포멧)
     * @param num Number 대상 숫자
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    private void startNumberField(String fmt, int colSpan
        , int rowSpan) {
        if (fmt == null) {
            fmt = DEFAULT_FLOAT_FORMAT;
        }
        this.startField(ExcelHtmlWriter.ALIGN_NOT_ASSIGN, colSpan, rowSpan);
        this.pw.print(" x:num style=\"mso-number-format:&quot;");
        this.pw.print(fmt);
        this.pw.print("&quot;\">");
    }

    /**
     * 날자 필드의 시작 부분을 출력합니다.
     * @param fmt String 날자 포멧 (excel의 날자 포멧)
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    private void startDateField(String fmt, int colSpan, int rowSpan) {
        if (fmt == null) {
            fmt = DEFAULT_DATETIME_FORMAT;
        }
        this.startField(ExcelHtmlWriter.ALIGN_NOT_ASSIGN, colSpan, rowSpan);
        this.pw.print(" style=\"mso-number-format:&quot;");
        this.pw.print(fmt);
        this.pw.print("&quot;\">");
    }

    /**
     * 불린값을 데이터로 하는 필드를 출력합니다.
     * @param b boolean
     */
    public void printField(boolean b) {
        this.startTextField(1, 1);
        this.print(b);
        this.endField();
    }

    /**
     * 불린값을 데이터로 하는 필드를 출력합니다.
     * @param b boolean
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(boolean b, int colSpan, int rowSpan) {
        this.startTextField(colSpan, rowSpan);
        this.print(b);
        this.endField();
    }

    /**
     * 캐릭터를 데이터로 하는 필드를 출력합니다.
     * @param c char
     */
    public void printField(char c) {
        this.printField(c, ExcelHtmlWriter.ALIGN_NOT_ASSIGN, 1, 1);
    }

    /**
     * 케릭터를 데이터로 하는 필드를 출력합니다.
     * @param c char
     * @param align int 필드내 데이터의 정렬방향
     */
    public void printField(char c, int align) {
        this.printField(c, align, 1, 1);
    }

    /**
     * 케릭터를 데이터로 하는 필드를 출력합니다.
     * @param c char
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(char c, int colSpan, int rowSpan) {
        this.printField(c, ExcelHtmlWriter.ALIGN_NOT_ASSIGN, colSpan, rowSpan);
    }

    /**
     * 케릭터를 데이터로 하는 필드를 출력합니다.
     * @param c char
     * @param align int 필드내 데이터의 정렬방향
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(char c, int align, int colSpan, int rowSpan) {
        this.startTextField(align, colSpan, rowSpan);
        this.print(c);
        this.endField();
    }

    /**
     * 캐릭터 어레이를 데이터로 하는 필드를 출력합니다.
     * @param s char[]
     */
    public void printField(char[] s) {
        this.printField(s, ExcelHtmlWriter.ALIGN_NOT_ASSIGN, 1, 1);
    }

    /**
     * 캐릭터 어레이를 데이터로 하는 필드를 출력합니다.
     * @param s char[]
     * @param align int 필드내 데이터의 정렬방향
     */
    public void printField(char[] s, int align) {
        this.printField(s, align, 1, 1);
    }

    /**
     * 캐릭터 어레이를 데이터로 하는 필드를 출력합니다.
     * @param s char[]
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(char[] s, int colSpan, int rowSpan) {
        this.printField(s, ExcelHtmlWriter.ALIGN_NOT_ASSIGN, colSpan, rowSpan);
    }

    /**
     * 캐릭터 어레이를 데이터로 하는 필드를 출력합니다.
     * @param s char[]
     * @param align int 필드내 데이터의 정렬방향
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(char[] s, int align, int colSpan ,int rowSpan) {
        this.startTextField(align, colSpan, rowSpan);
        this.print(s);
        this.endField();
    }

    /**
     * 더블을 데이터로하는 필드를 출력합니다.
     * @param d double
     */
    public void printField(double d) {
        this.printField(d, 1, 1);
    }

    /**
     * 더블을 데이터로하는 필드를 출력합니다.
     * @param d double
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(double d, int colSpan, int rowSpan) {
        this.startNumberField(DEFAULT_FLOAT_FORMAT, colSpan, rowSpan);
        this.print(d);
        this.endField();
    }

    /**
     * 더블을 데이터로하는 필드를 출력합니다.
     * @param d double
     * @param fmt String 숫자표시 포메터 (ms excel 기준)
     */
    public void printField(double d, String fmt) {
        this.printField(d, 1, 1, fmt);
    }

    /**
     * 더블을 데이터로하는 필드를 출력합니다.
     * @param d double
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     * @param fmt String 숫자표시 포메터 (ms excel 기준)
     */
    public void printField(double d, int colSpan
            , int rowSpan, String fmt) {
        this.startNumberField(fmt, colSpan, rowSpan);
        this.print(MiscUtil.DECIMAL_FORMAT.format(d));
        this.endField();
    }

    /**
     * 플로트를 데이터로 하는 필드를 출력합니다.
     * @param f float
     */
    public void printField(float f) {
        this.printField(f, 1, 1);
    }

    /**
     * 플로트를 데이터로 하는 필드를 출력합니다.
     * @param f float
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(float f, int colSpan, int rowSpan) {
        this.startNumberField(DEFAULT_FLOAT_FORMAT, colSpan, rowSpan);
        this.print(MiscUtil.DECIMAL_FORMAT.format(f));
        this.endField();
    }

    /**
     * 플로트를 데이터로 하는 필드를 출력합니다.
     * @param f float
     * @param fmt String 숫자표시 포메터 (ms excel 기준)
     */
    public void printField(float f, String fmt) {
        this.printField(f, 1, 1, fmt);
    }

    /**
     * 플로트를 데이터로 하는 필드를 출력합니다.
     * @param f float
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     * @param fmt String 숫자표시 포메터 (ms excel 기준)
     */
    public void printField(float f, int colSpan
            , int rowSpan, String fmt) {
        this.startNumberField(fmt, colSpan, rowSpan);
        this.print(f);
        this.endField();
    }

    /**
     * 인티저를 데이터로하는 필드를 출력합니다.
     * @param i int
     */
    public void printField(int i) {
        this.printField(i, 1, 1);
    }

    /**
     * 인티저를 데이터로하는 필드를 출력합니다.
     * @param i int
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(int i, int colSpan, int rowSpan) {
        this.startNumberField(DEFAULT_INTEGER_FORMAT, colSpan, rowSpan);
        this.print(i);
        this.endField();
    }

    /**
     * 인티저를 데이터로하는 필드를 출력합니다.
     * @param i int
     * @param fmt String 숫자표시 포메터 (ms excel 기준)
     */
    public void printField(int i, String fmt) {
        this.printField(i, 1, 1, fmt);
    }

    /**
     * 인티저를 데이터로하는 필드를 출력합니다.
     * @param i int
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     * @param fmt String 숫자표시 포메터 (ms excel 기준)
     */
    public void printField(int i, int colSpan, int rowSpan, String fmt) {
        this.startNumberField(fmt, colSpan, rowSpan);
        this.print(MiscUtil.DECIMAL_FORMAT.format(i));
        this.endField();
    }

    /**
     * 롱을 데이터로하는 필드를 출력합니다.
     *
     * @param l long
     */
    public void printField(long l) {
        this.printField(l, 1, 1);
    }

    /**
     * 롱을 데이터로하는 필드를 출력합니다.
     * @param l long
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(long l, int colSpan, int rowSpan) {
        this.startNumberField(DEFAULT_INTEGER_FORMAT, colSpan, rowSpan);
        this.print(l);
        this.endField();
    }

    /**
     * 롱을 데이터로하는 필드를 출력합니다.
     * @param l long
     * @param fmt String 숫자표시 포메터 (ms excel 기준)
     */
    public void printField(long l, String fmt) {
        this.printField(l, 1, 1, fmt);
    }

    /**
     * 롱을 데이터로하는 필드를 출력합니다.
     * @param l long
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     * @param fmt String 숫자표시 포메터 (ms excel 기준)
     */
    public void printField(long l, int colSpan, int rowSpan, String fmt) {
        this.startNumberField(fmt, colSpan, rowSpan);
        this.print(MiscUtil.DECIMAL_FORMAT.format(l));
        this.endField();
    }

    /**
     * 객체를 데이터로하는 필드를 출력합니다.
     * @param o Object
     */
    public void printField(Object o) {
        if (o instanceof Number) {
            this.printField(o, ExcelHtmlWriter.ALIGN_RIGHT, 1, 1);
        } else if (o instanceof Date) {
            this.printField(o, ExcelHtmlWriter.ALIGN_CENTER, 1, 1);
        } else if (o instanceof Calendar) {
            this.printField(o, ExcelHtmlWriter.ALIGN_CENTER, 1, 1);
        } else {
            this.printField(o, ExcelHtmlWriter.ALIGN_NOT_ASSIGN, 1, 1);
        }
    }

    /**
     * 객체를 데이터로하는 필드를 출력합니다.
     * @param o Object
     * @param align int 필드내 데이터의 정렬방향
     */
    public void printField(Object o, int align) {
        this.printField(o, align, 1, 1);
    }

    /**
     * 객체를 데이터로하는 필드를 출력합니다.
     * @param o Object
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(Object o, int colSpan, int rowSpan) {
        this.printField(o, ExcelHtmlWriter.ALIGN_NOT_ASSIGN, colSpan, rowSpan);
    }

    /**
     * 객체를 데이터로하는 필드를 출력합니다.
     * @param o Object
     * @param align int 필드내 데이터의 정렬방향
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(Object o, int align, int colSpan, int rowSpan) {
        if (o == null) {
            this.startTextField(align, colSpan, rowSpan);
        } else if (o instanceof Number) {
            if (((Number)o).doubleValue() % 1 == 0) {
                this.startNumberField(DEFAULT_INTEGER_FORMAT, colSpan, rowSpan);
            } else {
                this.startNumberField(DEFAULT_FLOAT_FORMAT, colSpan, rowSpan);
            }
            this.print(MiscUtil.DECIMAL_FORMAT.format(o));
        } else if (o instanceof java.sql.Date) {
            this.startDateField(DEFAULT_DATE_FORMAT, colSpan, rowSpan);
            this.print(DF_DATE.format(o));
        } else if (o instanceof java.sql.Time) {
            this.startDateField(DEFAULT_TIME_FORMAT, colSpan, rowSpan);
            this.print(DF_TIME.format(o));
        } else if (o instanceof Date) {
            this.startDateField(DEFAULT_DATETIME_FORMAT, colSpan, rowSpan);
            this.print(DF_DATETIME.format(o));
        } else if (o instanceof Calendar) {
            this.startDateField(DEFAULT_DATETIME_FORMAT, colSpan, rowSpan);
            this.print(DF_DATETIME.format(((Calendar)o).getTime()));
        } else {
            this.startTextField(align, colSpan, rowSpan);
            this.print(o);
        }
        this.endField();
    }

    /**
     * 넘버 객체를 데이터로하는 필드를 출력합니다.
     * @param num Number
     * @param fmt String 숫자표시 포메터 (ms excel 기준)
     */
    public void printField(Number num, String fmt) {
        this.printField(num, 1, 1, fmt);
    }

    /**
     * 넘버 객체를 데이터로하는 필드를 출력합니다.
     * @param num Number
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     * @param fmt String 숫자표시 포메터 (ms excel 기준)
     */
    public void printField(Number num, int colSpan
            , int rowSpan, String fmt) {
        this.startNumberField(fmt, colSpan, rowSpan);
        if (num != null) {
            this.print(MiscUtil.DECIMAL_FORMAT.format(num));
        }
        this.endField();
    }

    /**
     *
     * @param date Date
     * @param fmt String
     */
    public void printField(Date date, String fmt) {
        this.printField(date, 1, 1, fmt);
    }

    /**
     *
     * @param date Date
     * @param colSpan int
     * @param rowSpan int
     * @param fmt String
     */
    public void printField(Date date, int colSpan, int rowSpan, String fmt) {
        this.startDateField(fmt, colSpan, rowSpan);
        if (date != null) {
            this.print(date);
        }
        this.endField();
    }

    /**
     * 문자열을 데이터로 하는 필드를 출력합니다.
     * @param s String
     */
    public void printField(String s) {
        this.printField(s, ExcelHtmlWriter.ALIGN_NOT_ASSIGN, 1, 1);
    }

    /**
     * 문자열을 데이터로 하는 필드를 출력합니다.
     * @param s String
     * @param align int 필드내 데이터의 정렬방향
     */
    public void printField(String s, int align) {
        this.printField(s, align, 1, 1);
    }

    /**
     * 문자열을 데이터로 하는 필드를 출력합니다.
     * @param s String
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(String s, int colSpan, int rowSpan) {
        this.printField(s, ExcelHtmlWriter.ALIGN_NOT_ASSIGN, colSpan, rowSpan);
    }

    /**
     * 문자열을 데이터로 하는 필드를 출력합니다.
     * @param s String
     * @param align int 필드내 데이터의 정렬방향
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printField(String s, int align, int colSpan, int rowSpan) {
        this.startTextField(align, colSpan, rowSpan);
        this.print(s);
        this.endField();
    }

    /**
     * HTML으로 쓰일 데이터를 내용으로하는 필드를 출력합니다.
     * @param s String
     */
    public void printHtmlField(String s) {
        this.printHtmlField(s, ExcelHtmlWriter.ALIGN_NOT_ASSIGN, 1, 1);
    }

    /**
     * HTML으로 쓰일 데이터를 내용으로하는 필드를 출력합니다.
     * @param s String
     * @param align int 필드내 데이터의 정렬방향
     */
    public void printHtmlField(String s, int align) {
        this.printHtmlField(s, align, 1, 1);
    }

    /**
     * HTML으로 쓰일 데이터를 내용으로하는 필드를 출력합니다.
     * @param s String
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printHtmlField(String s, int colSpan, int rowSpan) {
        this.printHtmlField(s, ExcelHtmlWriter.ALIGN_NOT_ASSIGN, colSpan, rowSpan);
    }

    /**
     * HTML으로 쓰일 데이터를 내용으로하는 필드를 출력합니다.
     * @param s String
     * @param align int 필드내 데이터의 정렬방향
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printHtmlField(String s, int align, int colSpan, int rowSpan) {
        this.startField(align, colSpan, rowSpan);
        this.pw.print(">");
        this.pw.print(s);
        this.endField();
    }

    /**
     * 시트 링크 필드를 출력합니다.
     * @param sheetName String 대상 시트이름
     * @param cellName String 대상 셀아이디
     * @param linkText String 링크 텍스트
     */
    public void printLinkField(String sheetName, String cellName
        , String linkText) {
        this.printLinkField(sheetName, cellName, linkText
            , ExcelHtmlWriter.ALIGN_NOT_ASSIGN, 1, 1);
    }

    /**
     * 시트 링크 필드를 출력합니다.
     * @param sheetName String 대상 시트이름
     * @param cellName String 대상 셀아이디
     * @param linkText String 링크 텍스트
     * @param align int 필드내 데이터의 정렬방향
     */
    public void printLinkField(String sheetName, String cellName
        , String linkText, int align) {
        this.printLinkField(sheetName, cellName, linkText
            , align, 1, 1);

    }


    /**
     * 시트 링크 필드를 출력합니다.
     * @param sheetName String 대상 시트이름
     * @param cellName String 대상 셀아이디
     * @param linkText String 링크 텍스트
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printLinkField(String sheetName, String cellName
        , String linkText, int colspan, int rowspan) {
        this.printLinkField(sheetName, cellName, linkText
            , ExcelHtmlWriter.ALIGN_NOT_ASSIGN, colspan, rowspan);
    }


    /**
     * 시트 링크 필드를 출력합니다.
     * @param sheetName String 대상 시트이름
     * @param cellName String 대상 셀아이디
     * @param linkText String 링크 텍스트
     * @param align int 필드내 데이터의 정렬방향
     * @param colSpan int 열병합
     * @param rowSpan int 행병합
     */
    public void printLinkField(String sheetName, String cellName
        , String linkText, int align, int colspan, int rowspan) {
        this.printHtmlField(this.getLinkHtml(sheetName, cellName, linkText)
            , align, colspan, rowspan);
    }

    /**
     * 에러메세지와 함께 익셉션의 스택 트래이스를 출력합니다.
     * @param message String
     * @param t Throwable
     */
    public void printError(String message, Throwable t) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print("<h4><font color=red>! ");
        this.print(message);
        this.pw.print(" !</font color=red></h4>");
        this.pw.print("\r\n");
        if (t != null) {
            StringWriter    sw;

            sw  = new StringWriter();
            t.printStackTrace(new PrintWriter(sw));
            this.print(sw.toString());
            this.pw.print("\r\n");
        }
    }

    /**
     * 필드를 가지는 테이블의 시작을 출력합니다
     */
    public void tableStart() {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print("<table border=\"1\">");
        this.pw.print("\r\n");
    }

    /**
     * 필드를 가지는 테이블의 종료를 출력합니다.
     */
    public void tableEnd() {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print("</table>");
        this.pw.print("\r\n");
    }

    /**
     * 필드열의 시작을 출력합니다.
     */
    public void rowStart() {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print("<tr>");
        this.pw.print("\r\n");
    }

    /**
     * 필드열의 종료를 출력합니다.
     */
    public void rowEnd() {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print("</tr>");
        this.pw.print("\r\n");
    }

    /**
     * 시트의 시작을 표시합니다.
     */
    public void startSheet() {
        this.startSheet(this.curSheetNum+1);
    }

    /**
     * 시트의 시작을 표시합니다.
     * @param idx int 시작하는 시트의 인덱스(0부터 시작) 현재 출력중인 시트 번호보다 크고
     * maxSheet 보다 작아야 합니다.
     * @throws IllegalArgumentException 현재 출력중인 시트 번호와 같거나 작을때 혹은
     * maxSheet 보다 클때.
     */
    public void startSheet(int idx)
        throws IllegalArgumentException {
        if (idx < 0) {
            throw new IllegalArgumentException(
            		LocalizeMessage.getLocalizedMessage("시트 번호는 음수 일 수 없습니다."));
        }
        if (idx <= this.curSheetNum) {
            throw new IllegalArgumentException(
            		LocalizeMessage.getLocalizedMessage("이미 출력된 시트를 다시 출력할 수 없습니다."));
        }
        if (idx >= this.maxSheet) {
            throw new IllegalArgumentException(
            		LocalizeMessage.getLocalizedMessage("설정된 최대 시트수를 넘는 시트 번호가 지정되었습니다."));
        }
        // 시트 및 파일 정보가 커밋되지 않았다면 커밋정보 전송
        if (!this.commited) {
            this.startFile();
        } else { // 아니면 전 시트의 종료 처리
            this.pw.print("</body>");
            this.pw.print("\r\n");
            this.pw.print("</html>");
            this.pw.print("\r\n");
        }

        this.pw.flush();

        for (int i=++this.curSheetNum; i<=idx; i++) {
            if (this.maxSheet > 1) {
                this.pw.print(this.startPartBoundStr);
                this.pw.print("\r\n");
                this.pw.print("Content-Location: ");
                this.pw.print(this.vDirPath);
                this.pw.print("files/sheet");
                this.pw.print(i + 1);
                this.pw.print(".htm");
                this.pw.print("\r\n");
                this.pw.print("Content-Type: text/html; charset=\"" + this.charset + "\"");
                this.pw.print("\r\n");
                this.pw.print("\r\n");
            }
            this.pw.print("<html "
                + "xmlns:o=\"urn:schemas-microsoft-com:office:office\"\r\n");
            this.pw.print("xmlns:x=\"urn:schemas-microsoft-com:office:excel\""
                + "\r\n");
            this.pw.print("xmlns=\"http://www.w3.org/TR/REC-html40\">\r\n");
            this.pw.print("<head>\r\n");
            this.pw.print("	<meta http-equiv=\"Content-Type\" "
                + "content=\"application/vnd.ms-excel; charset=" + this.charset + "\">\r\n");
            this.pw.print("	<style>\r\n");
            this.pw.print("	<!--\r\n");
            this.pw.print("	br\r\n");
            this.pw.print("	    {mso-data-placement:same-cell;}\r\n");
            this.pw.print("	tr\r\n");
            this.pw.print("	    {mso-height-source:auto;\r\n");
            this.pw.print("	    mso-ruby-visibility:none;}\r\n");
            this.pw.print("	td\r\n");
            this.pw.print("	    {white-space:normal;}\r\n");
            this.pw.print("	-->\r\n");
            this.pw.print("	</style>\r\n");
            this.pw.print("</head>\r\n");
            this.pw.print("<body>\r\n");
            if (i<idx) {
                this.pw.print("</body>\r\n");
                this.pw.print("</html>\r\n");
            }
        }

    }

    /**
     * 파일의 시작부분(시트및 기타정보)를 출력합니다.
     */
    private void startFile() {
        if (this.maxSheet > 1) {
            this.pw.print("MIME-Version: 1.0");
            this.pw.print("\r\n");
            this.pw.print("X-Document-Type: Workbook");
            this.pw.print("\r\n");
            this.pw.print("Content-Type: multipart/related; boundary=\"");
            this.pw.print(this.multiPartBoundStr);
            this.pw.print("\"");
            this.pw.print("\r\n");
            this.pw.print("\r\n");
            this.pw.print(this.startPartBoundStr);
            this.pw.print("\r\n");
            this.pw.print("Content-Location: ");
            this.pw.print(this.vDirPath);
            this.pw.print("main.htm");
            this.pw.print("\r\n");
            this.pw.print("Content-Transfer-Encoding: quoted-printable");
            this.pw.print("\r\n");
            this.pw.print("Content-Type: text/html; charset=\"us-ascii\"");
            this.pw.print("\r\n");
            this.pw.print("\r\n");
            this.pw.print("<html "
                + "xmlns:o=3D\"urn:schemas-microsoft-com:office:office\"");
            this.pw.print("\r\n");
            this.pw.print(
                "xmlns:x=3D\"urn:schemas-microsoft-com:office:excel\"");
            this.pw.print("\r\n");
            this.pw.print("xmlns=3D\"http://www.w3.org/TR/REC-html40\">");
            this.pw.print("\r\n");
            this.pw.print("<head>");
            this.pw.print("\r\n");
            this.pw.print("<!--[if gte mso 9]><xml>");
            this.pw.print("\r\n");
            this.pw.print("<x:ExcelWorkbook>");
            this.pw.print("\r\n");
            this.pw.print("  <x:ExcelWorksheets>");
            this.pw.print("\r\n");
            for (int i = 0; i < this.maxSheet; i++) {
                String sheetName;

                sheetName = (String)this.sheetNames.get(new Integer(i));
                if (sheetName == null) {
                    sheetName = "sheet" + (i + 1);
                }
                this.pw.print("    <x:ExcelWorksheet>");
                this.pw.print("\r\n");
                this.pw.print("      <x:Name>");
                this.printText(sheetName, true);
                this.pw.print("</x:Name>");
                this.pw.print("\r\n");
                this.pw.print("      <x:WorksheetSource HRef=3D\"files/sheet");
                this.pw.print(i + 1);
                this.pw.print(".htm\"/>");
                this.pw.print("\r\n");
                this.pw.print("    </x:ExcelWorksheet>");
                this.pw.print("\r\n");
            }
            this.pw.print("  </x:ExcelWorksheets>");
            this.pw.print("\r\n");
            this.pw.print("</x:ExcelWorkbook>");
            this.pw.print("\r\n");
            this.pw.print("</xml><![endif]-->");
            this.pw.print("\r\n");
            this.pw.print("</head>");
            this.pw.print("\r\n");
            this.pw.print("</html>");
            this.pw.print("\r\n");
        }
        this.commited   = true;
    }

    /**
     * 엑셀 파일의 종료를 출력합니다.
     */
    private void endFile() {

        if (this.curSheetNum < this.maxSheet-1) {
            this.startSheet(this.maxSheet-1);
        }
        this.pw.print("</body>");
        this.pw.print("\r\n");
        this.pw.print("</html>");
        this.pw.print("\r\n");

        if (this.maxSheet > 1) {
            this.pw.print(this.startPartBoundStr);
            this.pw.print("\r\n");
            this.pw.print("Content-Location: ");
            this.pw.print(this.vDirPath);
            this.pw.print("files/filelist.xml");
            this.pw.print("Content-Transfer-Encoding: quoted-printable");
            this.pw.print("\r\n");
            this.pw.print("Content-Type: text/html; charset=\"us-ascii\"");
            this.pw.print("\r\n");
            this.pw.print("\r\n");
            this.pw.print(
                "<xml xmlns:o=3D\"urn:schemas-microsoft-com:office:office\">");
            this.pw.print("\r\n");
            this.pw.print("  <o:MainFile HRef=3D\"../main.htm\"/>");
            this.pw.print("\r\n");
            for (int i=0; i<this.maxSheet; i++) {
                this.pw.print("  <o:File HRef=3D\"sheet");
                this.pw.print(i+1);
                this.pw.print(".htm\"/>");
                this.pw.print("\r\n");
            }
            this.pw.print("  <o:File HRef=3D\"filelist.xml\"/>");
            this.pw.print("\r\n");
            this.pw.print("</xml>");
            this.pw.print("\r\n");
            this.pw.print(this.endPartBoundStr);
            this.pw.print("\r\n");
        }
    }

    /**
     * 엑셀 라이터를 닫습니다.<br>
     * 생성자에 소스로 전달된 라이터 혹은 아웃풋 스트림을 닫습니다.
     */
    public void close() {
        this.endFile();
        this.pw.close();
    }

    /**
     * 엑셀 라이터를 닫습니다.
     * 단.! 생성자에 소스로 전달된 라이터 혹은 아웃풋 스트림은 닫지 않습니다.
     */
    public void excelClose() {
        this.endFile();
        this.pw.flush();
    }

    /**
     * 대 제목을 출력합니다.
     * @param title String
     */
    public void printBigTitle(String title) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print("<H2>");
        this.print(title);
        this.pw.print("</H2>");
        this.pw.print("\r\n");
    }

    /**
     * 색션 제목을 출력합니다.
     * @param title String
     */
    public void printSectionTitle(String title) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.pw.print("<H4>○");
        this.print(title);
        this.pw.print("</H4>");
        this.pw.print("\r\n");
    }

    /**
     * 색션 제목을 출력합니다.
     * @param notice String
     */
    public void printNotice(String notice) {
        if (!this.commited) {
            throw new IllegalStateException(LocalizeMessage.getLocalizedMessage("시트가 시작되지 않았습니다."));
        }
        this.print("◎");
        this.println(notice);
    }

    /**
     * 링크 HTML을 반환합니다.
     * @param sheetName String 링크대상 시트이름
     * @param cellName String 링크대상 쎌아이디 A1 ~ Znnnnn
     * @param linkText String 링크 텍스트
     * @return String
     */
    public String getLinkHtml(String sheetName, String cellName
        , String linkText) {
        StringBuffer    sb;

        sb  = new StringBuffer();
        sb.append("<a href=\"#'");
        sb.append(sheetName);
        sb.append("'!");
        sb.append(cellName);
        sb.append("\">");
        sb.append(this.getEncText(linkText, false));
        sb.append("</a>");

        return sb.toString();
    }

    /**
     * 출력을 flush 합니다.
     */
    public void flush() {
        this.pw.flush();
    }

    /**
     * 클래스 테스트용 실행 메쏘드
     * @param atgs String[]
     */
    public static void main(String[] atgs) {
        try {
            BufferedReader  rd;
            String          fileName;
            String[][]      sampleData  = new String[][] {
                {"05-1005", "KIM.G.D\n其現場", "45", "36", "27"},
                {"05-1010", "HONG.G.D", "22", "33", "9022222222"},
                {"05-1011", "HONG.S.D", "77", "34", "84"},
                {"05-1012", "KIM.M.J", "98", "86", "35"},
                {"05-1013", "HWANG.S.J", "64", "37", "84"},
                {"05-1014", "LEE.C.S", "34", "72", "39"},
                {"05-1015", "JUNG.Y.H", "73", "63", "82"},
                {"05-1016", "NA.J.I", "87", "32", "90"}
            };
            int             numOfSheet  = 3;

            rd = new BufferedReader(new InputStreamReader(System.in));
            /*
            System.out.println("출력할 파일명을 입력 하십시오.");
            do {
                fileName = rd.readLine();
            } while (fileName.trim().length() == 0);
            */

            File            tf;
            ExcelHtmlWriter     elw;

//            fileName    = "C:\\Documents and Settings\\pat\\바탕 화면\\test.xls";
            fileName    = "E:\\user\\pat\\Desktop\\test-utf8.xls";
            tf  = new File(fileName);
            elw = new ExcelHtmlWriter(tf, "utf8");

            elw.setMaxSheet(numOfSheet);
            for (int i=0; i<numOfSheet; i++) {
                elw.setSheetName(i, "Sheet " + (i+1));
            }

            for (int i=0; i<numOfSheet; i++) {
                elw.startSheet();

                elw.printBigTitle("Excel Writer Sample " + i);
                elw.println();
                elw.printSectionTitle("Sample Section A");
                elw.printNotice("This is just Test.");
                elw.tableStart();
                elw.rowStart();
                elw.printField("Student", ExcelHtmlWriter.ALIGN_CENTER, 2, 1);
                elw.printField("Subject", ExcelHtmlWriter.ALIGN_CENTER, 3, 1);
                elw.printField("Total", ExcelHtmlWriter.ALIGN_CENTER, 1, 2);
                elw.printField("Average", ExcelHtmlWriter.ALIGN_CENTER, 1, 2);
                elw.printField("Time", ExcelHtmlWriter.ALIGN_CENTER, 3, 1);
                elw.rowEnd();
                elw.rowStart();
                elw.printField("st.ID", ExcelHtmlWriter.ALIGN_CENTER);
                elw.printField("名", ExcelHtmlWriter.ALIGN_CENTER);
                elw.printField("歷史", ExcelHtmlWriter.ALIGN_CENTER);
                elw.printField("英語", ExcelHtmlWriter.ALIGN_CENTER);
                elw.printField("數學", ExcelHtmlWriter.ALIGN_CENTER);
                elw.printField("日", ExcelHtmlWriter.ALIGN_CENTER);
                elw.printField("時", ExcelHtmlWriter.ALIGN_CENTER);
                elw.printField("日時", ExcelHtmlWriter.ALIGN_CENTER);
                elw.rowEnd();


                for (int j=0; j < sampleData.length; j++) {
                    long    sub1;
                    long    sub2;
                    long    sub3;
                    long    tot;
                    double  ave;

                    sub1    = Long.parseLong(sampleData[j][2]);
                    sub2    = Long.parseLong(sampleData[j][3]);
                    sub3    = Long.parseLong(sampleData[j][4]);
                    tot     = sub1 + sub2+ sub3;
                    ave     = tot / 3f;

                    elw.rowStart();
                    elw.printField(sampleData[j][0]);
                    elw.printField(sampleData[j][1]);
                    elw.printField(sub1);
                    elw.printField(sub2);
                    elw.printField(sub3);
                    elw.printField(MiscUtil.DECIMAL_FORMAT.format(tot)
                        , ALIGN_RIGHT);
                    elw.printLinkField("Sheet 3"
                        , "A1", MiscUtil.DECIMAL_PERCENT.format(ave)
                        , ALIGN_RIGHT);
                    elw.printField(new java.sql.Date(System.currentTimeMillis()));
                    elw.printField(new java.sql.Time(System.currentTimeMillis()));
                    elw.printField(new java.sql.Timestamp(System.currentTimeMillis()));
                    elw.rowEnd();
                }
                elw.tableEnd();
            }
            elw.printError("Test Fail", new Exception("Test Exception"));

            elw.close();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}