package com.nmimo.service.reservation;

import com.nmimo.common.info.PageInfo;
import com.nmimo.data.reservation.info.ReservationInfo;

public interface ReservationService {
	
	//사전예약
	public PageInfo selectBfacRegList(PageInfo pageInfo, ReservationInfo info);
	public ReservationInfo selectBfacDetail(int seq);

}
