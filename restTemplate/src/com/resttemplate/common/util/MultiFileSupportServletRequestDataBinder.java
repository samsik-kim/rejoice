/**
 * 
 */
package com.resttemplate.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * @comment :
 * @date : 2013. 11. 2.
 * @author : Rejoice
 */
public class MultiFileSupportServletRequestDataBinder extends
		CommonsMultipartResolver {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.multipart.commons.CommonsFileUploadSupport#
	 * cleanupFileItems(org.springframework.util.MultiValueMap)
	 */
	@Override
	protected void cleanupFileItems(
			MultiValueMap<String, MultipartFile> multipartFiles) {
/*
		for (Iterator it = ((List) multipartFiles).iterator(); it.hasNext();) {
			CommonsMultipartFile file = (CommonsMultipartFile) it.next();
			List fileList = (List) it.next();
			Iterator iter = fileList.iterator();
			while (iter.hasNext()) {
				CommonsMultipartFile file1 = (CommonsMultipartFile) iter.next();
				if (logger.isDebugEnabled()) {
					logger.debug("Cleaning up multipart file ["
							+ file1.getName() + "] with original filename ["
							+ file1.getOriginalFilename() + "], stored "
							+ file1.getStorageDescription());
				}
				file1.getFileItem().delete();
			}
			file.getFileItem().delete();
		}
*/
		// TODO Auto-generated method stub
		super.cleanupFileItems(multipartFiles);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.multipart.commons.CommonsFileUploadSupport#
	 * parseFileItems(java.util.List, java.lang.String)
	 */
	@Override
	protected MultipartParsingResult parseFileItems(List<FileItem> fileItems,
			String encoding) {

		MultiValueMap<String, MultipartFile> multipartFiles = new LinkedMultiValueMap<String, MultipartFile>();
		Map<String, String[]> multipartParameters = new HashMap<String, String[]>();

		// Extract multipart files and multipart parameters.
		for (Iterator it = fileItems.iterator(); it.hasNext();) {
			FileItem fileItem = (FileItem) it.next();
			if (!fileItem.isFormField()) {
				// multipart file field
				CommonsMultipartFile file = new CommonsMultipartFile(fileItem);
//				if (multipartFiles.put(file.getName(), (List<MultipartFile>) file) != null) {
//					throw new MultipartException(
//							"Multiple files for field name ["
//									+ file.getName()
//									+ "] found - not supported by MultipartResolver");
//				}
				List list = (List) multipartFiles.get("files");
				if (list != null) {
					list.add(file);
				} else {
					List fileList = new ArrayList();
					fileList.add(file);
					multipartFiles.put("files", fileList);
				}
			}
		}
		return new MultipartParsingResult(multipartFiles, multipartParameters);
	}

	protected void bindMultipartFiles(Map multipartFiles,
			MutablePropertyValues mpvs) {
		for (Iterator it = multipartFiles.entrySet().iterator(); it.hasNext();) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			Object value = entry.getValue();
			// if (isBindEmptyMultipartFiles() || !value.isEmpty()) {
			// mpvs.addPropertyValue(key, value);
			// }
		}
	}
}
