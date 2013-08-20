package com.nmimo.data.reservation.dao;

import java.util.List;

import com.nmimo.data.reservation.info.ReservationInfo;

public interface ReservationDAO {

	public int selectTotalBfac(ReservationInfo info);
	
	public List<ReservationInfo> selectBfacRegList(ReservationInfo info);
	
	public ReservationInfo selectBfacDetail(int seq);
}
