package com.omp.dev.test.util;

import java.io.File;
import java.util.List;

import com.omp.commons.util.FileUploadUtil;

public class FileUploadN {
 	
	public static void multiFileUpload(List<File> uploadFiles,List<String> uploadFileNames,String filePath) throws Exception
	{		
		if(uploadFiles.size() > 0){
			for(int i = 0; i<uploadFiles.size(); i++){
				File file = uploadFiles.get(i);
				String uploadFileName = uploadFileNames.get(i);
				String attFile = FileUploadUtil.uploadFile(file, filePath, i+"_"+uploadFileName);
			}
		}
	}
	
	public static void makeThumbnailImag(List<File> inputFiles,List<String> outPutFileNames,String filePath)
	{
		if(inputFiles.size() > 0){
			for(int i = 0; i<inputFiles.size(); i++)
			{
				File file = inputFiles.get(i);
				String outFileName = filePath + File.separator + outPutFileNames.get(i);
				System.out.println("OutfileName[" + i + "]: " + outFileName);
				//ImageUtil.setImgScale(file, outFileName, 100, 100);
			}
			
		}
	}
	
}
