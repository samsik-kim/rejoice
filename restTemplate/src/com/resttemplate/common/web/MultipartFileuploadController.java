/**
 * 
 */
package com.resttemplate.common.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @comment : 
 * @date    : 2013. 11. 3.
 * @author  : Rejoice
 */
@Controller
public class MultipartFileuploadController{

	
	@RequestMapping("/multiUpload.do")
	public String upload(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap map,
			MultipartHttpServletRequest multipartHttpServletRequest2) throws IOException{
		
		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;  //다중파일 업로드
		List<MultipartFile> files = multipartRequest.getFiles("file");
		Map files2 = multipartRequest.getFileMap();
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String uploadPath = realPath + "";
		
		for (int i = 0; i < files.size(); i++) {
			MultipartFile mf = files.get(i);
			if(!mf.isEmpty()){
				if(mf.getSize() > 0){
					
					// 원본 파일명
                    String originalFilename = mf.getOriginalFilename();
                    UUID randomUUID = UUID.randomUUID();
                    // 새로운 파일명은 시간(밀리세컨드까지)으로 변경하고 
                    // 확장자만을 갖는다.
//                    String newFilename =  i+ "_" + System.currentTimeMillis() + originalFilename.substring(originalFilename.length() -4, originalFilename.length());;
                    String newFilename = i+"_" + randomUUID.toString() + originalFilename.substring(originalFilename.length() -4, originalFilename.length());
                    File fOri = new File(uploadPath, newFilename);

                    // 실제 파일을 작성한다.
                    InputStream is = mf.getInputStream();
                    OutputStream  os = new FileOutputStream(fOri);
                    FileCopyUtils.copy(is, os);

                    map.put("orgNm_"+String.valueOf(i), originalFilename);
                    map.put("newNm_"+String.valueOf(i), newFilename);
                    map.put("size_"+String.valueOf(i), mf.getSize());
                    
                    os.close();
                    is.close();
				}
			}
		}
		return "sample/result";
	}
	
	@RequestMapping("/Mupload.do")
	public String multiUpload(HttpServletRequest request,
			HttpServletResponse response,
			ModelMap map) throws IOException{
		MultipartHttpServletRequest multipartRequest =  (MultipartHttpServletRequest)request;  //다중파일 업로드
		Map fileMap = multipartRequest.getFileMap();
		String realPath = request.getSession().getServletContext().getRealPath("/");
		String uploadPath = realPath + "";
		int num=0;
        for (Iterator it=fileMap.entrySet().iterator(); it.hasNext();) {
            Entry entry      = (Entry)it.next();
            MultipartFile mf = (MultipartFile)entry.getValue();

            if (!mf.isEmpty()) {
                if(mf.getSize() > 0){

                	// 원본 파일명
                    String originalFilename = mf.getOriginalFilename();
                    UUID randomUUID = UUID.randomUUID();
                    // 새로운 파일명은 시간(밀리세컨드까지)으로 변경하고 
                    // 확장자만을 갖는다.
//                    String newFilename =  i+ "_" + System.currentTimeMillis() + originalFilename.substring(originalFilename.length() -4, originalFilename.length());;
                    String newFilename = num+"_" + randomUUID.toString() + originalFilename.substring(originalFilename.length() -4, originalFilename.length());
                    File fOri = new File(uploadPath, newFilename);

                    // 실제 파일을 작성한다.
                    InputStream is = mf.getInputStream();
                    OutputStream  os = new FileOutputStream(fOri);
                    FileCopyUtils.copy(is, os);

                    map.put("orgNm_"+String.valueOf(num), originalFilename);
                    map.put("newNm_"+String.valueOf(num), newFilename);
                    map.put("size_"+String.valueOf(num), mf.getSize());
                }
            }
        }
        return "";
	}
}
