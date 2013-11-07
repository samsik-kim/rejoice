package com.omp.dev.common.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.RandomStringUtils;

import com.omp.commons.exception.InvalidInputException;
import com.omp.commons.text.LocalizeMessage;

/**
 * 쿠폰번호 생성기
 * 쿠폰종류코드는 공통코드지만 쿠폰번호에서는 다음과 같다.
 * A : Tstore10
 * B : Tstore20
 * C : Tstore30
 * 
 * @author soohee
 *
 */
public class CouponNoGenerator
{

    public static final String NO_DIV_CDS = "ABC";

    /** 발행일자별 키 */
    public static final String[] DAY_KEY =
        {
            "06",
            "14",
            "18",
            "32",
            "70",
            "61",
            "25",
            "16",
            "56",
            "29",
            "32",
            "75",
            "83",
            "86",
            "90",
            "17",
            "48",
            "36",
            "72",
            "17",
            "04",
            "63",
            "44",
            "25",
            "93",
            "14",
            "96",
            "81",
            "75",
            "03",
            "69" };

    /** 문자를 외쪽부터 채우방향 정의 */
    public static final int LEFT_PADDING = 1;

    /** 문자를 오른쪽부터 채우방향 정의 */
    public static final int RIGHT_PADDING = 2;

    /** 번호 생성 길이 정의 */
    public static final int GEN_NO_LENGTH = 12;

    private Random ran = new Random();

    /** 생성된 번호를 관리하는 List */
    private ArrayList genNoList = new ArrayList();

    /** 발행된 쿠폰 번호 수 */
    private int issedCouponCount = 0;
    
    /** 랜덤 알파벳 대문자 **/
    public static final String FIRST_FORMAT = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 외부에 공개된 쿠폰번호 생성 함수
     * 총 20자리의 쿠폰번호를 생성하여 주며 쿠폰번호 구성은 
     * 쿠폰분류코드 1자리 + 금일일자 2자리 + 금일일자에 해당되는 key 3자리 + 번호구분자('-') 1자리 +
     * 영문 2자리 + 랜덤번호 6자리 + 번호구분자('-') 1자리 + checkSum 2자리
     * 예) A2881519-IL960090-64
     * 
     * @param noDivCd String   쿠폰종류구분 코드(A~H)
     * @param count int   생성하고자 하는 쿠폰번호 수
     * @return String[]   생성된 쿠폰번호 수만의 String 배열에 담아 반환한다.
     * @throws Exception   쿠폰분류코드가 올바르지 않거나 번호생성 중 오류 발생시 Exception 을 발생시  
     */
    public String[] genCouponNo(String noDivCd, int count) throws Exception
    {
//      long startTime = System.currentTimeMillis();

        //1. 유효성 검사 수행
        validateCd(noDivCd, count);

        String[] noArray = new String[count];
        String tempNo = "";
        for (int i = 0; i < count; i++)
        {
            tempNo = genNo(noDivCd);

            //시스템의 부하를 줄인다.
            //Thread.sleep(10);
            //생성된 번호가 기 생성된 번호와 동일한지 체크
            while (true)
            {
                boolean isGeneratedNo = genNoList.contains(tempNo);

                if (!isGeneratedNo)
                {
                    break;
                }
                
                tempNo = genNo(noDivCd);
            }

            noArray[i] = tempNo;
            genNoList.add(tempNo);
            issedCouponCount++;
        }

//      long endTime = System.currentTimeMillis();
//      long differTime = endTime - startTime;
//      long differSecond = differTime / 1000;
//      long differMillis = differTime % 1000;

//      String diffTimeSec = differSecond + "." + differMillis;

        return noArray;
    }

    /**
     * 외부에 공개된 생성되었던 번호에 대한 유효성 검증 함수
     * 발행되었던 날짜정보와 checksum 정보를 이용해서 번호의 유효성을 검증한다.
     * 
     * @param no String   검증하고자 하는 쿠폰번호(20자리)
     * @return boolean   검증결과(정상 = true, 비정상 = false)
     */
    public boolean validateCouponNo(String no)
    {
        boolean isValidatedNo = false;

        if (no.length() != GEN_NO_LENGTH)
        {
            return false;
        }

        //1.  날짜 번호 유효성 검증
        //String issuedDate = no.substring(1, 3);
        //String dateKey = no.substring(3, 6);
        //println("issuedDate :" + issuedDate + ", dateKey :" + dateKey);

        //2. 숫자상의 checksum 검증
        isValidatedNo = checkSum(no);
        return isValidatedNo;
    }

    /**
     * 쿠폰번호에 포함된 제일 긑 2자리 checksum 정보를 이용 번호 유효성을 검증한다.
     * 
     * @param no String   검증하고자 하는 쿠폰번호 20 자
     * @return boolean   검증결과(정상 = true, 비정상 = false)
     */
    private boolean checkSum(String no)
    {
        // 번호예 : A2881532-GO810028-05
        int fistIdx = no.indexOf("-");
        String firstNo = no.substring(0, fistIdx);
        String secondNo = no.substring(fistIdx + 1, no.lastIndexOf("-"));
        String checkSumNo = no.substring(no.lastIndexOf("-") + 1, no.length());

        int firstNoSum = getCharSumNo(firstNo);
        int secondNoSum = getCharSumNo(secondNo);

        int firstCheckSum = getOddNo(firstNoSum); //10을 나눈 나머지 0 ~ 9까지의 값
        int secondCheckSum = getOddNo(secondNoSum);

        if ((firstCheckSum != Integer.parseInt(checkSumNo.substring(0, 1)))
            || (secondCheckSum != Integer.parseInt(checkSumNo.substring(1, 2))))
        {
            return false;
        }
        
        return true;
    }

    /**
     * 쿠폰번호 발행시 사용되는 쿠폰 종류 코드 및 발행수 요청 파라미터 유효성 검증
     * 
     * @param noDivCd String   쿠폰종류코드
     * @param count int   쿠폰번호 발생수
     * @return boolean   유효성 검증결과(정상 = true, 오류시(Exception 발생함))
     * @throws Exception - 오류발견시 Exception 발생
     */
    private boolean validateCd(String noDivCd, int count) throws Exception
    {
        if (count < 1)
        {
            throw new InvalidInputException("발행할 쿠폰 수는 0보다 작을 수 없습니다. !");
        }

        if (noDivCd == null || NO_DIV_CDS.indexOf(noDivCd) < 0)
        {
            throw new InvalidInputException("쿠폰분류코드(A~H)가 올바르지 않습니다.");
        }

        return true;
    }

    /**
     * 한건의 쿠폰번호를 발행한다.
     * [쿠폰번호 생성 규칙]
     * 쿠폰분류코드 1자리 + 금일일자 2자리 + 금일일자에 해당되는 key 3자리
     * + 영문 2자리 + 랜덤번호 2자리 + checkSum 2자리
     * 
     * @param noDivCd String   쿠폰종류코드
     * @return String   생성 된 쿠폰번호
     * @throws Exception
     */
    private String genNo(String noDivCd) throws Exception
    {
        StringBuffer sb = new StringBuffer();

        //1. 랜덤 영문 2자리
        String issueAlpha = RandomStringUtils.random(2, FIRST_FORMAT);
        
        //2. 랜덤번호
        String randomNo3 = genRandomNo(3); //4자리 생성
        String randomNo5 = genRandomNo(5); //5자리 생성
        
        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();

        //3. 앞의 5자리 번호 연결
        sb1.append(issueAlpha)      //랜덤 영문 2자리
        .append(randomNo3);         //랜덤 번호 3자리

        //4. 뒤 5자리 번호 연경
        sb2.append(randomNo5); //랜덤번호  5자리

        String checkSum = makeCheckSum(sb1.toString(), sb2.toString());

        sb.append(sb1)
        .append(sb2)
        .append(checkSum);

        return sb.toString();
    }
    
    /**
     * 한건의 쿠폰번호를 발행한다.
     * [쿠폰번호 생성 규칙]
     * 쿠폰분류코드 1자리 + 금일일자 2자리 + 금일일자에 해당되는 key 3자리
     * + 영문 2자리 + 랜덤번호 2자리 + checkSum 2자리
     * 
     * @param noDivCd String   쿠폰종류코드
     * @return String   생성 된 쿠폰번호
     * @throws Exception
     */
    /**
    private String genNo(String noDivCd) throws Exception
    {
        StringBuffer sb = new StringBuffer();

        //1. 금일 일자를 구한다.
        String toDay = getCurrentTime("dd");
        //2. 금일 일자에 해당되는 키를 얻는다.
        String toDayKey = DAY_KEY[Integer.parseInt(toDay) - 1];
        //println("toDay :"+ toDay + ", toDayKey : " + toDayKey);

        String randomNo2 = genRandomNo(2); //2자리 생성

        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();

        //3. 앞의 8자리 번호 연결
        sb1.append(noDivCd) //쿠폰분류코드 1자리
        .append(toDay) //금일일자       2자리
        .append(toDayKey); //금일일자에 해당되는 key 3자리 

        //4. 뒤 8자리 번호 연경
        sb2.append(genTwoChars()) //영문      2자리          
        .append(randomNo2); //랜덤번호  2자리

        String checkSum = makeCheckSum(sb1.toString(), sb2.toString());

        sb.append(sb1)
        .append(sb2)
        .append(checkSum);

        return sb.toString();
    }
    **/

    /**
     * 한건의 쿠폰번호를 발행한다.
     * [쿠폰번호 생성 규칙]
     * 쿠폰분류코드 1자리 + 금일일자 2자리 + 금일일자에 해당되는 key 3자리 + 번호구분자('-') 1자리
     * + 영문 2자리 + 랜덤번호 6자리 + 번호구분자('-') 1자리 + checkSum 2자리
     * 
     * @param noDivCd String   쿠폰종류코드
     * @return String   생성 된 쿠폰번호
     * @throws Exception
     */
    /** 원본
    private String genNo(String noDivCd) throws Exception
    {
        StringBuffer sb = new StringBuffer();

        //1. 금일 일자를 구한다.
        String toDay = getCurrentTime("dd");
        //2. 금일 일자에 해당되는 키를 얻는다.
        String toDayKey = DAY_KEY[Integer.parseInt(toDay) - 1];
        //println("toDay :"+ toDay + ", toDayKey : " + toDayKey);

        String randomNo2 = genRandomNo(2); //2자리 생성
        String randomNo6 = genRandomNo(6); //6자리 생성

        StringBuffer sb1 = new StringBuffer();
        StringBuffer sb2 = new StringBuffer();

        //3. 앞의 8자리 번호 연결 
        sb1.append(noDivCd) //쿠폰분류코드 1자리
        .append(toDay) //금일일자       2자리
        .append(toDayKey) //금일일자에 해당되는 key 3자리          
        .append(randomNo2);

        //4. 뒤 8자리 번호 연경
        sb2.append(genTwoChars()) //영문      2자리          
        .append(randomNo6); //랜덤번호  6자리

        String checkSum = makeCheckSum(sb1.toString(), sb2.toString());

        sb.append(sb1).append("-") //구분자       1자리
        .append(sb2).append("-") //구분자       1자리
        .append(checkSum);

        return sb.toString();
    }
    **/
    
    /**
     * 조합된 쿠폰번호에 대한 검증용 checksum 번호 2자리를 생성한다.
     * 
     * @param firstNo String   조합된 1 번째 문자열
     * @param secondNo String   조합된 2 번째 문자열
     * @return String   1번째, 2번째 문자열에 대한 검증용 checksum 번호 2자리 문자
     */
    private String makeCheckSum(String firstNo, String secondNo)
    {
//      char[] firstNoArray = firstNo.toCharArray();
//      char[] secondNoArray = secondNo.toCharArray();

        int firstNoSum = getCharSumNo(firstNo);
        int secondNoSum = getCharSumNo(secondNo);

        int firstCheckSum = getOddNo(firstNoSum); //10을 나눈 나머지 0 ~ 9까지의 값
        int secondCheckSum = getOddNo(secondNoSum);

        return (String.valueOf(firstCheckSum) + String.valueOf(secondCheckSum));
    }

    /**
     * 문자열의 모든 char들을 숫자로 전환하고 각각의 숫자합계를 구한다.
     * 
     * @param no String   전환하고 하는 문자열
     * @return int   숫자로 전환된 문자열들의 합계
     */
    private int getCharSumNo(String no)
    {
        int tempNo = 0;
        int noSum = 0;
        char[] noArray = no.toCharArray();

        for (int i = 0; i < noArray.length; i++)
        {
            tempNo = (int) noArray[i];
            noSum = noSum + tempNo;
        }
        return noSum;
    }

    /**
     * 총합계수를 10을 나눈 나머지 0 ~ 9까지의 값을 반환한다.
     * 
     * @param sumNo int   총합계 수
     * @return int   10으로 나눈후의 나머지 값
     */
    private int getOddNo(int sumNo)
    {
        return ((sumNo * 2 / 5) % 10);
    }

    /**
     * 영문 문자열 2자리를 랜덤생성한다.
     * 
     * @return String   연문자열(2자리) String
     */
    private String genTwoChars()
    {
        // 1. 2자리 랜덤번호를 생성한다.
//      String twoStr = "";
        int genNo = ran.nextInt(100);
        // 2. 1자리 랜덤번호를 생성한다.
        int charGenNo = ran.nextInt(10);

        String char1 = "";
        String char2 = "";

        // 문자 A~Z로 변환한다.
        if (genNo < 65 || genNo > 90)
        {
            if (genNo <= 25)
            { 
                // 65를 더하였을 때 90이 넘지 않아야 한다.
                genNo = genNo + 65;
            }
            else if (genNo >= 26 && genNo <= 64)
            {
                // 최소 65 를 만든다
                genNo = genNo + 39;
                
                if (genNo > 90)
                {
                    genNo = genNo - 20;
                }
            }
            else if (genNo > 90)
            { 
                //90보다 큰경우
                genNo = genNo - 10;
            }
        }

        char1 = String.valueOf((char) (genNo));

        if ((genNo + charGenNo) > 90)
        { 
            // Z 를 넘어가는 경우
            char2 = String.valueOf((char) (genNo - charGenNo));
        }
        else
        {
            char2 = String.valueOf((char) (genNo + charGenNo));
        }

        return (char1 + char2);
    }

    /**
     * 지정한 자리수만큼의 숫자를 랜덤으로 생성하고 생성된 번호가 지정된 자리수보다 작은 경우
     * 작은 수 자리수 만큼 왼쪽부터 0으로 채운후 생성된 번호를 문자로 반환한다.
     * 현재는 integer 자리수 만큼의 랜덤 숫자 생성만을 지원한다.
     * 최대 10자리까지의 숫자 생성지원(integer형의 최대자리 수임)
     * 
     * @param genLenth int   생성하고자 하는 랜덤 숫자 자리수(integer 형)
     * @return String   생성된 랜덤 숫자 String
     */
    private String genRandomNo(int genLenth) throws Exception
    {
        if (genLenth > 10)
        {
            throw new InvalidInputException("램덤생성 숫자 자리수는 10자리를 넘을 수 없습니다.");
        }

        int genRangeNo = (int) Math.pow((double) 10, (double) genLenth);
        int genNo = ran.nextInt(genRangeNo);

        String genNoStr = String.valueOf(genNo);

        if (genNoStr.length() < genLenth)
        {
//          int diffLenth = genLenth - genNoStr.length();
            genNoStr = fillPadding(genNoStr, '0', genLenth, LEFT_PADDING);
        }

        return genNoStr;
    }

    /**
     * 문자열의 좌측 또는 우측을 총 문자열 기준으로 지정된 문자(예 '0')로 채워주는 함수
     * 
     * @param val String   원 문자열 String
     * @param pad char   채우고자 하는 문자 char
     * @param totLen int   총 전체 문자열 길이
     * @param direct int   외쪽/오른쪽 방향 구분(왼쪽 : LEFT_PADDING, 오른쪽 : RIGHT_PADDING)     
     * @return String   지정된 문자가 채워진 문자열 String
     */
    private String fillPadding(String val, char pad, int totLen, int direct)
    {
        StringBuffer cb = new StringBuffer();

        // 전문 문자열 앞에 filler를 추가함
        // 한글 byte단위 처리로 수정
        byte[] strBytes = val.getBytes();
        int valLen = strBytes.length;

        if (valLen > totLen)
        {
            // 한글 byte단위로 처리하도록 수정
            val = substringByte(val, 0, totLen);
        }

        if (direct == RIGHT_PADDING)
        {
            // 실제값 추가
            cb.append(val);
        }

        if (valLen < totLen)
        {
            for (int i = 0; i < (totLen - valLen); i++)
            {
                // PAD(예 공백, '0') 한개 추가
                cb.append(pad);
            }
        }

        if (direct == LEFT_PADDING)
        {
            // 실제값 추가
            cb.append(val);
        }
        return cb.toString();
    }

    /**
     * source String에서 begin index에서 end index까지 byte단위로 자르고자 할때 사용
     * 
     * @param src String   자르고자 하는 String
     * @param beginIdx int   begin index
     * @param endIdx int   end index
     * @return String   byte 단위로 잘려진 String
     */
    private String substringByte(String src, int beginIdx, int endIdx)
    {
        return new String(subByte(src.getBytes(), beginIdx, endIdx));
    }

    /**
     * source byte에서 begin index에서 end index까지 byte단위로 자르고자 할때 사용
     * 
     * @param src byte[]   자르고자 하는 byte[]
     * @param beginIdx int   begin index
     * @param endIdx int   end index
     * @return byte[]   자른 byte[]
     */
    private byte[] subByte(byte[] src, int beginIdx, int endIdx)
    {
        if (src == null || src.length == 0)
        {
            return src;
        }

        int differByteCnt = endIdx - beginIdx;
        if (differByteCnt < 0)
        {
            throw new IllegalArgumentException(
                LocalizeMessage.getLocalizedMessage("end index 값 [{0}] 이 begin index [{1}] 보다 작습니다.", new Object[] {endIdx, beginIdx}));
        }

        byte[] resultBytes = new byte[differByteCnt];
        int resultIdx = 0;
        for (int idx = beginIdx; idx < endIdx; idx++)
        {
            resultBytes[resultIdx++] = src[idx];
        }
        return resultBytes;
    }

    /**
     * 시스템의 현재 일시를 지정된 날짜 포멧(SimpleDateFormat참조)으로 변환하여 반환함
     * 
     * @param dateFormat String   SimpleDateFormat 의 날짜 포멧(예: yyyyMMdd HHmmssSSS)
     * @return String   현재일자
     */
    private String getCurrentTime(String dateFormat)
    {
        Calendar rightNow = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Date currentTime = rightNow.getTime();
        String dateString = formatter.format(currentTime);
        return dateString;
    }
    
    public static void main(String[] args)
    {
        CouponNoGenerator gen = new CouponNoGenerator();
        
        try {
            String cpn[] = gen.genCouponNo("A", 10);
            for (int i=0; i<cpn.length; i++) {
                System.out.println("cpn["+i+"] " + cpn[i]);
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

}
