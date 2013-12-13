package com.omp.admin.adminmgr.auth.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.omp.admin.adminmgr.auth.model.AdMenuMgrauth;
import com.omp.admin.adminmgr.auth.model.AdMgr;
import com.omp.admin.adminmgr.auth.model.AdMgrauth;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;

/**
 * AdMgr 비즈니스 로직 클래스
 * @author soohee
 * 
 */
public class AdminMemberService extends AbstractService {

	//private GLogger log = new GLogger(AdminMemberService.class);

	@SuppressWarnings("unchecked")
	public List<AdMgr> selectAdMgrList(AdMgr adMgr) {
		return this.commonDAO.queryForPageList("AdminMgr.selectAdMgrList", adMgr);
	}

	public AdMgr selectAdMgr(AdMgr adMgr) {
		AdMgr retAdMgr = null;
		try {
			this.setStep("AdminMgr.selectAdMgr");
			retAdMgr = (AdMgr) this.commonDAO.queryForObject("AdminMgr.selectAdMgr", adMgr);
		} catch (SQLException e) {
			throw new ServiceException("관리자 정보를 가져오는 동안 에러가 발생 하였습니다.", e);
		}

		return retAdMgr;
	}

	public AdMgr insertAdMgr(AdMgr adMgr) {

		AdMgr retAdMgr = null;

		try {

			this.setStep("startTransaction");
			this.daoManager.startTransaction();

			this.setStep("AdminMgr.insertAdMgr");
			retAdMgr = (AdMgr) this.commonDAO.insert("AdminMgr.insertAdMgr", adMgr);

			AdMgrauth adMgrauth = new AdMgrauth();
			adMgrauth.setMenuId("M006001001");
			adMgrauth.setMgrId(adMgr.getMgrId());
			adMgrauth.setRegId(adMgr.getRegId());

			this.setStep("AdminMgr.insertAdMgrAuthM006001001");
			this.commonDAO.insert("AdminMgr.insertAdMgrAuth", adMgrauth);

			adMgrauth.setMenuId("M006001");
			adMgrauth.setMgrId(adMgr.getMgrId());
			adMgrauth.setRegId(adMgr.getRegId());

			this.setStep("AdminMgr.insertAdMgrAuthM006001");
			this.commonDAO.insert("AdminMgr.insertAdMgrAuth", adMgrauth);

			adMgrauth.setMenuId("M006");
			adMgrauth.setMgrId(adMgr.getMgrId());
			adMgrauth.setRegId(adMgr.getRegId());

			this.setStep("AdminMgr.insertAdMgrAuthM006");
			this.commonDAO.insert("AdminMgr.insertAdMgrAuth", adMgrauth);

			adMgrauth.setMenuId("ROOT");
			adMgrauth.setMgrId(adMgr.getMgrId());
			adMgrauth.setRegId(adMgr.getRegId());

			this.setStep("AdminMgr.insertAdMgrAuthROOT");
			this.commonDAO.insert("AdminMgr.insertAdMgrAuth", adMgrauth);

			this.setStep("commitTransaction");
			this.daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("관리자 정보를 저장하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			this.daoManager.endTransaction();
		}

		return retAdMgr;
	}

	public int updateAdMgr(AdMgr adMgr) {
		int cnt = 0;
		try {
			this.setStep("AdminMgr.updateAdMgr");
			cnt = this.commonDAO.update("AdminMgr.updateAdMgr", adMgr);
		} catch (SQLException e) {
			throw new ServiceException("관리자 정보를 변경하는 동안 에러가 발생 하였습니다.", e);
		}
		return cnt;
	}

	public int deleteAdMgr(AdMgr adMgr) {

		int cnt = 0;

		try {

			if ("admin".equalsIgnoreCase(adMgr.getMgrId())) {
				return cnt;
			}

			this.setStep("startTransaction");
			this.daoManager.startTransaction();

			this.setStep("AdminMgr.deleteAdMgr");
			cnt = this.commonDAO.delete("AdminMgr.deleteAdMgr", adMgr);

			AdMgrauth adMgrauth = new AdMgrauth();
			adMgrauth.setMgrId(adMgr.getMgrId());

			this.setStep("AdminMgr.deleteAdMgrAuth");
			this.commonDAO.delete("AdminMgr.deleteAdMgrAuth", adMgrauth);

			this.daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("관리자 정보를 삭제하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			this.daoManager.endTransaction();
		}
		return cnt;
	}

	@SuppressWarnings("unchecked")
	public HashMap<String, List<AdMenuMgrauth>> selectAdMenuList(AdMenuMgrauth adMenuMgrauth) {

		HashMap<String, List<AdMenuMgrauth>> hashMapMenu = new HashMap<String, List<AdMenuMgrauth>>();

		try {

			adMenuMgrauth.setTopMenuId("");
			adMenuMgrauth.setMenuDepth(1);

			List<AdMenuMgrauth> depth1List = null;

			if (adMenuMgrauth.getMgrId() == null) {
				this.setStep("depth1ListAdminMgr.selectAdMenuList");
				depth1List = this.commonDAO.queryForList("AdminMgr.selectAdMenuList", adMenuMgrauth);
			} else {
				this.setStep("depth1ListAdminMgr.selectAdMenuMgrIdList");
				depth1List = this.commonDAO.queryForList("AdminMgr.selectAdMenuMgrIdList", adMenuMgrauth);
			}

			if (depth1List.size() > 0) { // 루트는 하나만 존재

				AdMenuMgrauth auth1 = (AdMenuMgrauth) depth1List.get(0);
				String depth1Code = auth1.getMenuId(); // 루트 메뉴코드

				adMenuMgrauth.setTopMenuId(depth1Code);
				adMenuMgrauth.setMenuDepth(2);

				//2depth menu list // 운영자관리
				List<AdMenuMgrauth> depth2List = null;
				if (adMenuMgrauth.getMgrId() == null) {
					this.setStep("depth2ListAdminMgr.selectAdMenuMgrIdList");
					depth2List = this.commonDAO.queryForList("AdminMgr.selectAdMenuList", adMenuMgrauth);
				} else {
					this.setStep("depth2ListAdminMgr.selectAdMenuMgrIdList");
					depth2List = this.commonDAO.queryForList("AdminMgr.selectAdMenuMgrIdList", adMenuMgrauth);
				}
				hashMapMenu.put(depth1Code, depth2List);

				for (int k = 0; k < depth2List.size(); k++) {

					AdMenuMgrauth auth2 = (AdMenuMgrauth) depth2List.get(k);
					String depth2Code = auth2.getMenuId();

					adMenuMgrauth.setTopMenuId(depth2Code);
					adMenuMgrauth.setMenuDepth(3);

					List<AdMenuMgrauth> depth3List = null;
					if (adMenuMgrauth.getMgrId() == null) {
						this.setStep("depth3ListAdminMgr.selectAdMenuList");
						depth3List = this.commonDAO.queryForList("AdminMgr.selectAdMenuList", adMenuMgrauth);
					} else {
						this.setStep("depth3ListAdminMgr.selectAdMenuMgrIdList");
						depth3List = this.commonDAO.queryForList("AdminMgr.selectAdMenuMgrIdList", adMenuMgrauth);
					}
					hashMapMenu.put(depth2Code, depth3List);

					for (int i = 0; i < depth3List.size(); i++) {
						AdMenuMgrauth authMgr = (AdMenuMgrauth) depth3List.get(i);
						String depth3Code = authMgr.getMenuId();

						adMenuMgrauth.setTopMenuId(depth3Code);
						adMenuMgrauth.setMenuDepth(4);

						List<AdMenuMgrauth> depth4List = null;
						if (adMenuMgrauth.getMgrId() == null) {
							this.setStep("depth4ListAdminMgr.selectAdMenuList");
							depth4List = this.commonDAO.queryForList("AdminMgr.selectAdMenuList", adMenuMgrauth);
						} else {
							this.setStep("depth4ListAdminMgr.selectAdMenuMgrIdList");
							depth4List = this.commonDAO.queryForList("AdminMgr.selectAdMenuMgrIdList", adMenuMgrauth);
						}
						hashMapMenu.put(depth3Code, depth4List);
					}

				}
			}

		} catch (SQLException e) {
			throw new ServiceException("메뉴 목록을 가져오는 동안 에러가 발생 하였습니다.", e);
		}

		return hashMapMenu;
	}

	public int updateAdMgrAuthList(String selectedAccount, String selectedMenuId, String loginId) {

		String[] arrMgrId = selectedAccount.split(",");

		int returnCnt = 0;

		try {

			AdMgrauth adMgrauth = new AdMgrauth();

			// log.debug("###selectedAccount=" + arrMgrId.toString());
			// log.debug("###selectedMenuId=" + arrMenuId.toString());

			this.setStep("startTransaction");
			daoManager.startTransaction();

			for (int i = 0; i < arrMgrId.length; i++) {

				log.debug("##arrMgrId[i]=" + arrMgrId[i]);

				if ("admin".equalsIgnoreCase(arrMgrId[i])) {
					log.warn("NOT DELETE AUTH admin, {0}", new Object[] { arrMgrId[i] });
					continue;
				}

				// 해당 계정의 권한을 전부 삭제한다.
				adMgrauth.setMgrId(arrMgrId[i]);
				this.setStep("AdminMgr.deleteAdMgrAuth");
				this.commonDAO.delete("AdminMgr.deleteAdMgrAuth", adMgrauth);

				this.setStep("parseCheckMenuId");
				Iterator<String> it = parseCheckMenuId(selectedMenuId);

				adMgrauth.setMgrId(arrMgrId[i]);
				adMgrauth.setMenuId("ROOT");
				adMgrauth.setRegId(loginId);
				this.setStep("AdminMgr.insertAdMgrAuth");
				this.commonDAO.insert("AdminMgr.insertAdMgrAuth", adMgrauth);
				returnCnt++;

				while (it.hasNext()) {
					String tmpMenuId = it.next();
					log.debug("##mgrId[j]=" + arrMgrId[i]);
					log.debug("##menuId[j]=" + tmpMenuId);

					adMgrauth.setMenuId(tmpMenuId);

					// 해당 계정의 권한을 전부 insert 한다.
					this.setStep("AdminMgr.insertAdMgrAuth");
					this.commonDAO.insert("AdminMgr.insertAdMgrAuth", adMgrauth);
					returnCnt++;
				}
			}

			this.setStep("commitTransaction");
			daoManager.commitTransaction();

		} catch (Exception e) {
			throw new ServiceException("관리자 권한을 변경하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.setStep("endTransaction");
			daoManager.endTransaction();
		}

		return returnCnt;
	}

	private Iterator<String> parseCheckMenuId(String menuId) {

		String[] arrMenuId = menuId.split(",");

		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < arrMenuId.length; i++) {
			String tmpStr = arrMenuId[i];
			if (tmpStr.length() == 10) {
				String dep1Str = tmpStr.substring(0, 4);
				String dep2Str = tmpStr.substring(0, 7);
				String dep3str = tmpStr.substring(0, 10);
				set.add(dep1Str);
				set.add(dep2Str);
				set.add(dep3str);
			}
		}

		Iterator<String> it = set.iterator();
		return it;

	}

}
