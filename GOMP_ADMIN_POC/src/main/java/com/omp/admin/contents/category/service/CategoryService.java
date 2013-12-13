package com.omp.admin.contents.category.service;

import java.sql.SQLException;
import java.util.List;

import com.omp.admin.contents.category.model.DpCatAdmin;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.service.AbstractService;
import com.omp.commons.util.GLogger;

public class CategoryService extends AbstractService {

	private static final GLogger log = new GLogger(CategoryService.class);

	@SuppressWarnings("unchecked")
	public List<DpCatAdmin> selectCategoryList(DpCatAdmin dpCat) {
		List<DpCatAdmin> listDpCat = null;
		try {
			listDpCat = this.commonDAO.queryForList("Contents.selectCategoryList", dpCat);
		} catch (SQLException e) {
			throw new ServiceException("SC 메뉴 리스트를 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return listDpCat;
	}

	public DpCatAdmin selectCategory(DpCatAdmin dpCat) {
		DpCatAdmin returnDpCat = null;
		try {
			returnDpCat = (DpCatAdmin) this.commonDAO.queryForObject("Contents.selectCategory", dpCat);
		} catch (SQLException e) {
			throw new ServiceException("SC 메뉴 내용을 가져오는 동안 에러가 발생 하였습니다.", e);
		}
		return returnDpCat;
	}

	public boolean insertCategory(DpCatAdmin dpCat) {
		boolean bool = true;
		try {
			this.commonDAO.insert("Contents.insertCategory", dpCat);
		} catch (SQLException e) {
			throw new ServiceException("SC 메뉴 내용을 저장하는 동안 에러가 발생 하였습니다.", e);
		}
		return bool;
	}

	public int updateCategory(DpCatAdmin dpCat) {
		int cnt = 0;
		try {
			cnt = this.commonDAO.update("Contents.updateCategory", dpCat);
		} catch (SQLException e) {
			throw new ServiceException("SC 메뉴 내용을 변경하는 동안 에러가 발생 하였습니다.", e);
		}
		return cnt;
	}

	public int updateCategoryPriorList(DpCatAdmin dpCat, String[] aDpCatNo, String[] aDpCatPrior) {

		int cnt = 0;

		try {

			this.daoManager.startTransaction();

			for (int i = 0; i < aDpCatNo.length; i++) {
				if (log.isDebugEnabled())
					log.debug("aDpCatNo[" + i + "] : " + aDpCatNo[i] + ", aDpCatPrior[" + i + "] : " + Integer.parseInt(aDpCatPrior[i]));
				dpCat.setDpCatNo(aDpCatNo[i]);
				dpCat.setDpCatPrior(Integer.parseInt(aDpCatPrior[i]));
				cnt = cnt + this.commonDAO.update("Contents.updateCategory", dpCat);
			}

			this.daoManager.commitTransaction();

		} catch (SQLException e) {
			throw new ServiceException("SC 메뉴 내용을 변경하는 동안 에러가 발생 하였습니다.", e);
		} finally {
			this.daoManager.endTransaction();
		}
		return cnt;
	}

	public int deleteCategory(DpCatAdmin dpCat) {
		int cnt = 0;
		try {
			cnt = this.commonDAO.delete("Contents.deleteCategory", dpCat);
		} catch (SQLException e) {
			throw new ServiceException("SC 메뉴 내용을 삭제하는 동안 에러가 발생 하였습니다.", e);
		}
		return cnt;
	}

}
