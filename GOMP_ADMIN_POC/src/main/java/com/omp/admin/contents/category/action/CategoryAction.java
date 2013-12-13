package com.omp.admin.contents.category.action;

import java.io.File;
import java.util.List;

import pat.neocore.io.FileUtility;

import com.omp.admin.common.Constants;
import com.omp.admin.common.util.CommonUtil;
import com.omp.admin.contents.category.model.DpCatAdmin;
import com.omp.admin.contents.category.service.CategoryService;
import com.omp.commons.action.BaseAction;
import com.omp.commons.exception.ServiceException;
import com.omp.commons.util.DateUtil;
import com.omp.commons.util.FileUtil;
import com.omp.commons.util.GLogger;
import com.omp.commons.util.StringUtil;

/**
 * <pre>
 * @Description SC Category
 * @author 
 * @since 2009.04.09
 * </pre>
 */
@SuppressWarnings("serial")
public class CategoryAction extends BaseAction {

	private static final GLogger log = new GLogger(CategoryAction.class);

	private List<DpCatAdmin> dpCatList;
	private DpCatAdmin dpCat;

	private String upDpCatNo;
	private String newUpDpCatNo;
	private String mode;

	private CategoryService categoryService;

	public CategoryAction() {
		categoryService = new CategoryService();
	}

	public String selectCategoryList() {

		if (dpCat == null) {
			dpCat = new DpCatAdmin();
		}

		if ("".equals(StringUtil.nvlStr(upDpCatNo))) {
			upDpCatNo = Constants.CTGR_SC_UPDPCATNO;
		}
		dpCat.setUpDpCatNo(upDpCatNo);

		setDpCatList(categoryService.selectCategoryList(dpCat));

		return SUCCESS;
	}

	public String insertCategory() {

		String sFilePath = "";
		String sFileUrl = "";

		try {

			if (dpCat == null) {
				dpCat = new DpCatAdmin();
			}

			if ("".equals(StringUtil.nvlStr(upDpCatNo))) {
				upDpCatNo = Constants.CTGR_SC_UPDPCATNO;
			}
			dpCat.setUpDpCatNo(upDpCatNo);

			if (log.isDebugEnabled())
				log.debug("dpCat.getBodyImageUpload() : " + StringUtil.nvlStr(dpCat.getBodyImageUpload(), "NULL"));

			if (dpCat.getBodyImageUpload() != null) {

				String sConvFileName = null;

				sFilePath = this.conf.getString("omp.common.path.http-share.common.scctgr");
				sFileUrl = this.conf.getString("omp.common.url.http-share.common.scctgr");
				if (log.isInfoEnabled()) {
					log.info("omp.common.path.http-share.misc.scctgr : {0}", new Object[] { sFilePath });
				}

				if (dpCat.getBodyImageUpload().exists()) {

					sConvFileName = "/" + dpCat.getUpDpCatNo() + "_" + DateUtil.getGeneralTimeStampString() + "."
							+ FileUtility.getFileExt(dpCat.getBodyImageUploadFileName());

					String uploadFileFullPathName = sFilePath + sConvFileName;

					if (log.isDebugEnabled()) {
						log.debug("uploadFileFullPath::::::: " + uploadFileFullPathName);
						log.debug("fileName :::::::::::::::: " + dpCat.getBodyImageUploadFileName());
						log.debug("convFileName :::::::::::: " + sConvFileName);
						log.debug("fileSize :::::::::::::::: " + dpCat.getBodyImageUpload().length() + "bytes");
						log.debug("fileContentType ::::::::: " + dpCat.getBodyImageUploadContentType());
					}

					FileUtil.move(dpCat.getBodyImageUpload(), new File(uploadFileFullPathName), false);

				}

				dpCat.setBodyFilePos(sFileUrl);
				//dpCat.setBodyFileNm(dpCat.getBodyImageUploadFileName());
				dpCat.setBodyFileNm(sConvFileName);
				dpCat.setBodyFileSize(dpCat.getBodyImageUpload().length());

			} else {
				sFileUrl = "";
				if (log.isDebugEnabled()) {
					log.debug("uploadPath : " + StringUtil.nvlStr(sFileUrl));
				}
			}

			dpCat.setUpDpCatNo(upDpCatNo);
			dpCat.setRegId(CommonUtil.getLoginId(this.req.getSession()));

			categoryService.insertCategory(dpCat);

			setMode("I");

		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("카테고리 추가 실패.", e);
		}

		return SUCCESS;
	}

	public String updateCategory() {

		String sFilePath = "";
		String sFileUrl = "";

		try {

			if (dpCat == null) {
				dpCat = new DpCatAdmin();
			}

			String sUseYn = ("".equals(StringUtil.nvlStr(this.req.getParameter("updateUseYn")))) ? dpCat.getUseYn() : this.req
					.getParameter("updateUseYn");
			String sDpCatPrior = ("".equals(StringUtil.nvlStr(this.req.getParameter("updateDpCatPrior")))) ? "" + dpCat.getDpCatPrior()
					: this.req.getParameter("updateDpCatPrior");
			String sDpCatNo = ("".equals(StringUtil.nvlStr(this.req.getParameter("updateDpCatNo")))) ? dpCat.getDpCatNo() : this.req
					.getParameter("updateDpCatNo");

			if (log.isDebugEnabled())
				log.debug("dpCat.getModifyBodyImageUpload() : " + StringUtil.nvlStr(dpCat.getModifyBodyImageUpload(), "NULL"));

			if (dpCat.getModifyBodyImageUpload() != null) {

				String sConvFileName = null;

				if (dpCat.getModifyBodyImageUpload().exists()) {

					//Properties prop = (Properties) this.req.getSession().getServletContext().getAttribute("CONF");
					//sFilePath = prop.getProperty("omp.common.path.http-share.common.scctgr", "");
					//sFileUrl = prop.getProperty("omp.common.url.http-share.common.scctgr", "");

					sFilePath = this.conf.getString("omp.common.path.http-share.common.scctgr");
					sFileUrl = this.conf.getString("omp.common.url.http-share.common.scctgr");
					if (log.isInfoEnabled()) {
						log.info("omp.common.path.http-share.misc.scctgr : {0}", new Object[] { sFilePath });
					}

					sConvFileName = "/" + dpCat.getDpCatNo() + "." + FileUtility.getFileExt(dpCat.getModifyBodyImageUploadFileName());

					String uploadFileFullPathName = sFilePath + sConvFileName;

					log.debug("uploadFileFullPath::::::: " + uploadFileFullPathName);
					log.debug("fileName :::::::::::::::: " + dpCat.getModifyBodyImageUploadFileName());
					log.debug("convFileName :::::::::::: " + sConvFileName);
					log.debug("fileSize :::::::::::::::: " + dpCat.getModifyBodyImageUpload().length() + "bytes");
					log.debug("fileContentType ::::::::: " + dpCat.getModifyBodyImageUploadContentType());

					FileUtil.move(dpCat.getModifyBodyImageUpload(), new File(uploadFileFullPathName), true);

				}

				dpCat.setBodyFilePos(sFileUrl);
				//dpCat.setBodyFileNm(dpCat.getModifyBodyImageUploadFileName());
				dpCat.setBodyFileNm(sConvFileName);
				dpCat.setBodyFileSize(dpCat.getModifyBodyImageUpload().length());

			} else {
				sFileUrl = "";
				if (log.isDebugEnabled())
					log.debug("uploadPath : " + StringUtil.nvlStr(sFileUrl));
			}

			dpCat.setUpDpCatNo(getNewUpDpCatNo());
			dpCat.setUseYn(sUseYn);
			dpCat.setDpCatNo(sDpCatNo);
			dpCat.setDpCatPrior(Integer.parseInt(sDpCatPrior));

			dpCat.setUpdId(CommonUtil.getLoginId(this.req.getSession()));

			categoryService.updateCategory(dpCat);

			setMode("U");

		} catch (ServiceException e) {
			throw e;
		} catch (Exception e) {
			throw new ServiceException("카테고리 수정실패.", e);
		}

		return SUCCESS;
	}

	public String updateCategoryPriorList() {

		if (dpCat == null) {
			dpCat = new DpCatAdmin();
		}

		String[] aDpCatNo = this.req.getParameterValues("dpCatNo");
		String[] aDpCatPrior = this.req.getParameterValues("dpCatPrior");
		//log.debug("aDpCatNo.length : " + aDpCatNo.length);
		//log.debug("aDpCatPrior.length : " + aDpCatPrior.length);

		dpCat.setUpdId(CommonUtil.getLoginId(this.req.getSession()));

		categoryService.updateCategoryPriorList(dpCat, aDpCatNo, aDpCatPrior);

		return SUCCESS;
	}

	public String deleteCategory() {

		if (dpCat == null) {
			dpCat = new DpCatAdmin();
		}

		if ("".equals(StringUtil.nvlStr(upDpCatNo))) {
			upDpCatNo = Constants.CTGR_SC_UPDPCATNO;
		}
		dpCat.setUpDpCatNo(upDpCatNo);

		String dpCatNo = this.req.getParameter("deleteDpCatNo");

		dpCat.setDpCatNo(dpCatNo);

		categoryService.deleteCategory(dpCat);

		setUpDpCatNo(upDpCatNo);

		return SUCCESS;
	}

	public String popCategory() {

		if (dpCat == null) {
			dpCat = new DpCatAdmin();
		}

		if ("".equals(StringUtil.nvlStr(upDpCatNo))) {
			upDpCatNo = Constants.CTGR_SC_UPDPCATNO;
		}
		dpCat.setUpDpCatNo(upDpCatNo);

		String dpCatNo = this.req.getParameter("dpCatNo");
		if (log.isDebugEnabled()) {
			log.debug("upDpCatNo : " + upDpCatNo);
			log.debug("dpCatNo : " + dpCatNo);
		}

		dpCat.setUpDpCatNo(upDpCatNo);
		dpCat.setDpCatNo(dpCatNo);

		if (!"".equals(dpCat.getDpCatNo())) {
			dpCat = categoryService.selectCategory(dpCat);
		}
		setUpDpCatNo(upDpCatNo);

		return SUCCESS;
	}

	public static String DeCode(String param) {

		StringBuffer sb = new StringBuffer();
		int pos = 0;
		boolean flg = true;

		if (param != null) {
			if (param.length() > 1) {
				while (flg) {
					String sLen = param.substring(pos, ++pos);
					int nLen = 0;

					try {
						nLen = Integer.parseInt(sLen);
					} catch (Exception e) {
						nLen = 0;
					}

					String code = "";
					if ((pos + nLen) > param.length())
						code = param.substring(pos);
					else
						code = param.substring(pos, (pos + nLen));

					pos += nLen;

					sb.append(((char) Integer.parseInt(code)));

					if (pos >= param.length())
						flg = false;
				}
			}
		} else {
			param = "";
		}

		return sb.toString();
	}

	public List<DpCatAdmin> getDpCatList() {
		return dpCatList;
	}

	public void setDpCatList(List<DpCatAdmin> dpCatList) {
		this.dpCatList = dpCatList;
	}

	public DpCatAdmin getDpCat() {
		return dpCat;
	}

	public void setDpCat(DpCatAdmin dpCat) {
		this.dpCat = dpCat;
	}

	public String getUpDpCatNo() {
		return upDpCatNo;
	}

	public void setUpDpCatNo(String upDpCatNo) {
		this.upDpCatNo = upDpCatNo;
	}

	public String getNewUpDpCatNo() {
		return newUpDpCatNo;
	}

	public void setNewUpDpCatNo(String newUpDpCatNo) {
		this.newUpDpCatNo = newUpDpCatNo;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
