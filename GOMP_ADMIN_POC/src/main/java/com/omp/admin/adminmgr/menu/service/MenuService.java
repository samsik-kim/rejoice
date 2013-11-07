package com.omp.admin.adminmgr.menu.service;

import java.sql.SQLException;
import java.util.List;

import com.omp.admin.adminmgr.auth.model.AdMgrauth;
import com.omp.admin.adminmgr.menu.model.AdMenu;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

public class MenuService extends AbstractService {

	@SuppressWarnings("unchecked")
	public List<AdMenu> selectAdMenuList(AdMenu adMenu) {
		List<AdMenu> retAdMenuList = null;
		try {
			this.setStep("AdminMgr.selectAdMenuMgmtList");
			retAdMenuList = this.commonDAO.queryForList("AdminMgr.selectAdMenuMgmtList", adMenu);
		} catch (SQLException e) {
			throw new ServiceException("메뉴 정보 목록을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retAdMenuList;
	}

	public AdMenu selectAdMenu(AdMenu adMenu) {
		AdMenu retAdMenu = null;
		try {
			this.setStep("AdminMgr.selectAdMenuMgmt");
			retAdMenu = (AdMenu) this.commonDAO.queryForObject("AdminMgr.selectAdMenuMgmt", adMenu);
		} catch (SQLException e) {
			throw new ServiceException("메뉴 정보를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return retAdMenu;
	}

	public AdMenu insertAdMenu(AdMenu adMenu) {

		AdMenu retAdMenu = null;

		try {

			this.setStep("startTransaction");
			this.daoManager.startTransaction();

			this.setStep("AdminMgr.insertAdMenuMgmt");
			retAdMenu = (AdMenu) this.commonDAO.insert("AdminMgr.insertAdMenuMgmt", adMenu);

			AdMgrauth adMgrauth = new AdMgrauth();
			adMgrauth.setMenuId(adMenu.getMenuId());
			adMgrauth.setMgrId("admin");
			adMgrauth.setRegId(adMenu.getRegId());

			this.setStep("AdminMgr.insertAdMgrAuth");
			this.commonDAO.insert("AdminMgr.insertAdMgrAuth", adMgrauth);

			this.setStep("commitTransaction");
			this.daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("메뉴 정보를 저장하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			this.daoManager.endTransaction();
		}

		return retAdMenu;
	}

	public int updateAdMenu(AdMenu adMenu) {
		int cnt = 0;
		try {
			this.setStep("AdminMgr.updateAdMenuMgmt");
			cnt = this.commonDAO.update("AdminMgr.updateAdMenuMgmt", adMenu);
		} catch (SQLException e) {
			throw new ServiceException("메뉴 정보를 변경하는 동안 에러가 발생 하였습니다.", e);
		}
		return cnt;
	}

	public int updateAdMenuUseYn(AdMenu adMenu) {
		int cnt = 0;
		try {
			this.setStep("AdminMgr.updateAdMenuMgmtUseYn");
			cnt = this.commonDAO.update("AdminMgr.updateAdMenuMgmtUseYn", adMenu);
		} catch (SQLException e) {
			throw new ServiceException("메뉴 정보를 변경하는 동안 에러가 발생 하였습니다.", e);
		}
		return cnt;
	}

}
