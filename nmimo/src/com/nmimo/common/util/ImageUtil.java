package com.nmimo.common.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang.StringUtils;

import com.nmimo.common.ServiceConstants;
import com.nmimo.common.exception.NMimoException;

/**
 * <pre>
 *
 * </pre>
 * @file ImageUtil.java
 * @since 2013. 7. 31.
 * @author Rejoice
 */
public class ImageUtil {


	/**
	 * <pre>
	 * 이미지 변환 
	 * </pre>
	 * @param filePath : 저장된 파일 위치
	 * @param savePath : 저장할 파일 위치
	 * @param fileName : 파일명
	 * @param ext : 변환 확장자
	 * @return boolean : 결과 성공 유무
	 */
	public static boolean convertorImg(String filePath, String savePath, String ext){
		boolean flag = true;
		File file = new File(filePath); // 파일을 가져온다
		ext = StringUtils.lowerCase(ext);
		try {
			BufferedImage bi = ImageIO.read(file); // 이건 파일을 읽는다..
			File f = new File(savePath, file.getName().substring(0,file.getName().lastIndexOf("."))+"."+ext); // temp 파일을 만든다
			ImageIO.write(bi, ext, f); // bmp파일을 읽어서 jpg로 포멧변환
			
//			String name1[] = ImageIO.getWriterFormatNames(); // 쓸수 있는 파일네임들
//			String name2[] = ImageIO.getReaderFormatNames(); // 읽을수 있는 파일 네임들
			
		} catch (Exception e) {
			flag = false;
			throw new NMimoException("[IOException] : " + e.getMessage());
		}
		return flag;
	}

	/**
	 * <pre>
	 * 이미지 사이즈 반환
	 * </pre>
	 * 
	 * @param filename : 파일명
	 * @return HashMap<String, Object>
	 */
	public static HashMap<String, Object> getSize(String filename) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		Image img = new ImageIcon(filename).getImage();
		map.put(ServiceConstants.File.IMG_WIDTH, img.getWidth(null));
		map.put(ServiceConstants.File.IMG_HEIGHT, img.getHeight(null));
		return map;
	}

	/**
	 * <pre>
	 * Debug
	 * </pre>
	 * @param args
	 */
	public static void main(String[] args) {
		/** 
		 1. 파일 풀네임
		 2. 저장될 폴더
		 3. 변환할 확장자
		*/
		ImageUtil.convertorImg("C:\\Users\\Administrator\\Pictures\\3.bmp", "D:/depoly_nmimo", ServiceConstants.File.FILE_EXT_JPG);
		ImageUtil.convertorImg("C:\\Users\\Administrator\\Pictures\\3.bmp", "D:/depoly_nmimo", ServiceConstants.File.FILE_EXT_GIF);
	};
}