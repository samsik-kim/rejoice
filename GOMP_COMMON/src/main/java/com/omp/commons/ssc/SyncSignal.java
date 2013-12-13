package com.omp.commons.ssc;

import java.io.*;

/**
 * <p>Title: SyncSignal</p>
 *
 * <p>Description: 동기화 신호 객체</p>
 *
 *
 * @author pat
 * @version 1.0
 */
@SuppressWarnings("serial")
public class SyncSignal
    implements Serializable {

    private String from;
    private String to;
    private Object type;
    private Object message;

    /**
     * 생성자
     * @param to String 메세지를 보낼 대상자, null이면 모두
     * @param type Object 메세지의 타입
     * @param message Object 메세지 내용
     */
    public SyncSignal(String to, Object type, Object message) {
        this.to         = to;
        this.type       = type;
        this.message    = message;
    }

    /**
     * 메세지 대상자를 설정합니다.
     * @param to String 메세지의 대상자, null이면 모두
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * 메세지 타입을 설정합니다.
     * @param type Object 메세지 타입
     */
    public void setType(Object type) {
        this.type = type;
    }

    /**
     * 메세지를 설정합니다.
     * @param message Object
     */
    public void setMessage(Object message) {
        this.message = message;
    }

    /**
     * 메세지 전송자를 설정합니다.
     * @param from String 메세지 전송자
     */
    void setFrom(String from) {
        this.from = from;
    }

    /**
     * 메세지 대상자를 얻습니다.
     * @return String null이면 모두
     */
    public String getTo() {
        return to;
    }

    /**
     * 메세지 타입을 얻습니다.
     * @return Object
     */
    public Object getType() {
        return type;
    }

    /**
     * 메세지를 얻습니다.
     * @return Object
     */
    public Object getMessage() {
        return message;
    }

    /**
     * 매세지 전송자를 얻습니다.
     * @return String
     */
    public String getFrom() {
        return from;
    }
}
