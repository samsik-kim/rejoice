/*
 * COPYRIGHT(c) SK telecom 2009
 * This software is the proprietary information of SK telecom.
 *
 * Revision History
 * Author     Date          Description
 * --------   ----------    ------------------
 *             2009. 4. 29.    Description
 *
 */
package com.omp.admin.adminmgr.auth.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.omp.admin.adminmgr.auth.model.AdMenuMgrauth;
import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;

/**
 * AdminMainService
 * <p/>
 * AdminMainService
 * 
 * @author
 * @version 0.1
 */
public class AdminMainService extends AbstractService {

	private static final GLogger log = new GLogger(AdminMainService.class);

	public AdMgr adminLogin(AdMgr adMgr) {

		AdMgr resultAdminMain = null;

		try {

			this.setStep("AdminMgr.adminLogin");
			resultAdminMain = (AdMgr) this.commonDAO.queryForObject("AdminMgr.adminLogin", adMgr);

			if (resultAdminMain != null)
				this.commonDAO.update("AdminMgr.adminLoginDateUpdate", adMgr.getMgrId());

		} catch (SQLException e) {
			throw new ServiceException("LOGIN을 하는 동안 에러가 발생 하였습니다.", e);
		}

		return resultAdminMain;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, List<AdMenuMgrauth>> adminAuthLeftMenuListAllMap(String mgrId) {

		HashMap<String, List<AdMenuMgrauth>> hashMapMenu = new HashMap<String, List<AdMenuMgrauth>>();

		try {

			AdMenuMgrauth adMenuMgrauth = new AdMenuMgrauth();
			adMenuMgrauth.setMgrId(mgrId);
			adMenuMgrauth.setTopMenuId("");
			adMenuMgrauth.setMenuDepth(1);

			log.debug("ROOT 쿼리########");
			List<AdMenuMgrauth> depth1List;

			this.setStep("AdminMgr.adminAuthLeftMenuListMap");
			depth1List = this.commonDAO.queryForList("AdminMgr.adminAuthLeftMenuListMap", adMenuMgrauth);

			log.debug("ROOT 쿼리 결과########" + depth1List.size());
			if (depth1List.size() > 0) { // 루트는 하나만 존재

				AdMenuMgrauth auth1 = (AdMenuMgrauth) depth1List.get(0);
				String depth1Code = auth1.getMenuId(); // 루트 메뉴코드

				adMenuMgrauth.setTopMenuId(depth1Code);
				adMenuMgrauth.setMenuDepth(2);

				//2depth menu list // 운영자관리
				this.setStep("depth2ListAdminMgr.adminAuthLeftMenuListMap");
				List<AdMenuMgrauth> depth2List = this.commonDAO.queryForList("AdminMgr.adminAuthLeftMenuListMap", adMenuMgrauth);

				hashMapMenu.put(depth1Code, depth2List);

				for (int k = 0; k < depth2List.size(); k++) {

					AdMenuMgrauth auth2 = (AdMenuMgrauth) depth2List.get(k);
					String depth2Code = auth2.getMenuId();

					adMenuMgrauth.setTopMenuId(depth2Code);
					adMenuMgrauth.setMenuDepth(3);

					this.setStep("depth3ListAdminMgr.adminAuthLeftMenuListMap");
					List<AdMenuMgrauth> depth3List = this.commonDAO.queryForList("AdminMgr.adminAuthLeftMenuListMap", adMenuMgrauth);
					hashMapMenu.put(depth2Code, depth3List);

					for (int i = 0; i < depth3List.size(); i++) {
						AdMenuMgrauth authMgr = (AdMenuMgrauth) depth3List.get(i);
						String depth3Code = authMgr.getMenuId();

						adMenuMgrauth.setTopMenuId(depth3Code);
						adMenuMgrauth.setMenuDepth(4);

						this.setStep("depth4ListAdminMgr.adminAuthLeftMenuListMap");
						List<AdMenuMgrauth> depth4List = this.commonDAO.queryForList("AdminMgr.adminAuthLeftMenuListMap", adMenuMgrauth);
						hashMapMenu.put(depth3Code, depth4List);
					}

				}
			}

		} catch (SQLException e) {
			throw new ServiceException("메뉴 목록을 가져오는 동안 에러가 발생 하였습니다.", e);
		}

		return hashMapMenu;
	}

	public boolean isAdminValidPageAuth(String mgrId, String menuId) {

		boolean isValid = false;

		try {

			AdMenuMgrauth adMenuMgrauth = new AdMenuMgrauth();
			adMenuMgrauth.setMgrId(mgrId);
			adMenuMgrauth.setMenuId(menuId);

			this.setStep("AdminMgr.isAdminValidPageAuth");
			Object obj = this.commonDAO.queryForObject("AdminMgr.isAdminValidPageAuth", adMenuMgrauth);
			if (obj != null) {
				int cnt = Integer.parseInt((String) obj);
				if (cnt == 1) {
					isValid = true;
				}
			}

		} catch (SQLException e) {
			throw new ServiceException("메뉴 권한을 확인하는 동안 에러가 발생 하였습니다.", e);
		}

		return isValid;
	}

}
