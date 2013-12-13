package com.omp.dev.common.util;

import java.awt.Image;

import com.sun.jimi.core.Jimi;
import com.sun.jimi.core.JimiUtils;


/**
 *<pre>
 * sourcePath : Resizing source image file path
 * targetPath : Creation target image file path
 *</pre>
 *
 * @author shsin
 */
public class ImageResizeUtil
{

	public static boolean resizing(String sourcePath, String targetPath, int width, int height) throws Exception{
		
		boolean isSuccess = false;
		
		Image image = JimiUtils.getThumbnail(sourcePath, width, height, Jimi.IN_MEMORY);
	
		Jimi.putImage(image, targetPath);
		
		return isSuccess;
	}
	
	
//	public static void main(String[] args) {
//		try {
//			ImageResizeUtil.resizing("c:/마시마로212.jpg", "c:/test.jpg", 200, 200);
//			ImageResizeUtil.resizing("c:/마시마로212.jpg", "c:/test.png", 200, 200);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
   
}
