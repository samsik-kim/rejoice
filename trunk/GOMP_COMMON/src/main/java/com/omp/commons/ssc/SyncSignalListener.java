package com.omp.commons.ssc;

/**
 * <p>Title: SyncSignalListener</p>
 *
 * <p>Description: 동기화 메세지 수신 리스너 인터페이스</p>
 *
 * @author pat
 * @version 1.0
 */
public interface SyncSignalListener {

    /**
     * 메세지를 수신하여 수행할 코드를 구체화 하십시오.
     * @param ss SyncSignal 수신된 메세지
     */
    public void syncMessageRecived(SyncSignal ss);
}
