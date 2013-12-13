package com.omp.dev.contents.service;

import java.util.List;

import com.omp.commons.exception.ServiceException;
import com.omp.dev.contents.model.ContentsVerify;
import com.omp.dev.contents.model.SubContentsVerify;
import com.omp.dev.contents.model.verify.ContentVerifyDetail;
import com.omp.dev.contents.model.verify.SubContentVerify;

/**
 * ContentsVerifyService interface : Contents Verify Present service interface
 * @author Administrator
 *
 */
public interface ContentsVerifyService {

	/**
	 * Contents Verify List
	 * @return
	 * @throws ServiceException
	 */
	public List<ContentsVerify> getContentsVerifyList(ContentsVerify ctVerify) throws ServiceException;
	
	/**
	 * Content Verify Detail Head
	 * @param paramModel
	 * @return
	 * @throws ServiceException
	 */
	public ContentVerifyDetail getContentVerifyDetailViewHead(ContentsVerify paramModel) throws ServiceException;
	
	/**
	 * Content Verify Detail State
	 * @param paramModel
	 * @return
	 */
	public ContentVerifyDetail getSubContentsVerifyDetailState(ContentsVerify paramModel) throws ServiceException;
	
	/**
	 * Content Verify Detail Body
	 * @param ContentsVerify paramModel
	 * @return
	 * @throws ServiceException
	 */
	public List<SubContentsVerify> getContentVerifyDetailViewBody(ContentsVerify paramModel) throws ServiceException;
	
	/**
	 * Content Verify Detail AddFile List
	 * @param ContentsVerify paramModel
	 * @return
	 * @throws ServiceException
	 */
	public List<SubContentVerify> getContentVerifyDetailAddFile(ContentsVerify paramModel) throws ServiceException;
	
	/**
	 * Content Verify Cancel
	 * @param contentsVerify
	 * @throws ServiceException
	 */
	public boolean updateContentVerifyCancel(ContentsVerify contentsVerify) throws ServiceException;
	
	/**
	 * SubContents Verify History List
	 * @param contentsVerify
	 * @return
	 * @throws ServiceException
	 */
	public List<ContentsVerify> getContentsVerifyHisList(ContentsVerify contentsVerify) throws ServiceException;
}
