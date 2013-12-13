package com.omp.commons.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FileExistsException;


/**
 * 파일 처리 관련 유틸리티
 * @author pat
 *
 */
public class FileUtil {
    /** Default reading buffer Size */
    public final static int BUFFER_SIZE = 1024;
    
    

    /**
     * 지정된 fname에 content(Text)를 저장한다.
     * @param fname 저장할 파일
     * @param content 저장할 내용
     * @throws Exception
     */
    public synchronized static void write( File fname, String content ) throws Exception {
        BufferedWriter _bufferedWriter = null;
        try {
            _bufferedWriter = new BufferedWriter(new FileWriter(fname));

            _bufferedWriter.write(content);
            _bufferedWriter.flush();
        }
        catch (IOException ioe) {
            throw new Exception(ioe);
        }
        finally {
            try {
                if (_bufferedWriter != null)
                    _bufferedWriter.close();
            }
            catch (IOException ioe) {
                throw new Exception(ioe);
            }
        }
    }
    
    /**
     * 지정된 fname에 content(Binary)를 저장한다.
     * @param fname 저장할 파일
     * @param content 저장할 내용
     * @throws Exception
     */
    public synchronized static void write( File fname, byte[] content ) throws Exception {
        try {

            FileOutputStream out = new FileOutputStream(fname);
            out.write(content, 0, content.length);
            out.close();
        }
        catch (IOException ioe) {
            throw new Exception(ioe);
        }
    }

    /**
     * 지정된 fname에서 데이터를 읽어 byte[]로 반환한다.
     * @param fname 읽을 파일
     * @return 파일 내용
     * @throws Exception
     */
    public synchronized static byte[] readBytes( File fname ) throws Exception {
        try {
            FileInputStream fis = new FileInputStream(fname);
            int bufsize = new Long(fname.length()).intValue();
            byte[] buffer = new byte[bufsize];
            int rsize = 0;

            while (-1 != (rsize = fis.read(buffer)))
                ;
            fis.close();

            return buffer;
        }
        catch (IOException ioe) {
            throw new Exception(ioe);
        }
    }

    /**
     * 지정된 fname에서 데이터를 읽어 string으로 반환한다.
     * @param fname 읽을 파일
     * @return 파일 내용
     * @throws Exception
     */
    public static String read( File fname ) throws Exception {
        BufferedReader _bufferedReader = null;
        StringBuffer result = new StringBuffer();

        try {
            _bufferedReader = new BufferedReader(new FileReader(fname));
            int rsize = 0;
            char[] buff = new char[BUFFER_SIZE];

            while (-1 != (rsize = _bufferedReader.read(buff, 0, BUFFER_SIZE))) {
                result.append(buff, 0, rsize);
            }
        }
        catch (IOException ioe) {
            throw new Exception(ioe);
        }
        finally {
            try {
                _bufferedReader.close();
            }
            catch (IOException ioe) {
                throw new Exception(ioe);
            }
        }

        return result.toString();
    }

    /**
     * 지정한 fname을 삭제한다.
     * @param fname 삭제할 파일
     * @throws IOException
     * @deprecated 불필요한 씽크로나이즈 및 File.delete()로 대체
     */
    public static boolean delete( File fname ) throws IOException {
        synchronized (fname) {
            return fname.delete();
        }
    }


    /**
     * 파일을 카피하기 위해 지정된 in을 읽어 out에 저장한다.
     * @param in source file inputstream
     * @param out target file outputstream
     * @throws IOException
     * @deprecated 불필요한 씽크로 나이즈
     */
    public static void copy( InputStream in, OutputStream out ) throws IOException {
        synchronized (in) {
            synchronized (out) {
                BufferedInputStream bin = new BufferedInputStream(in);
                BufferedOutputStream bout = new BufferedOutputStream(out);

                while (true) {
                    int datum = bin.read();
                    if (datum == -1)
                        break;
                    bout.write(datum);
                }
                bout.flush();
            }
        }
    }

    /**
     * 디렉토리가 존재하는지 확인한다.
     * @param fileName
     * @return
     * @deprecated new File(fileName).isDirectory() 로 대체
     */
    public static boolean directoryCheck( String fileName ) {
        String directoryName = "";
        File f = null;
        if (fileName.lastIndexOf(".") > 0) {
            directoryName = fileName.substring(0, fileName.lastIndexOf("."));

        }
        else {
            directoryName = fileName;
        }

        f = new File(directoryName);

        return f.isDirectory();
    }

    /**
     * 디렉토리 생성
     * @param fileName
     * @return
     * @deprecated new File(fileName).getParentFile().mkdirs() 로 대체
     */
    public static boolean createDirectory( String fileName ) {
        String directoryName = "";
        File f = null;
        if (fileName.lastIndexOf(".") > 0) {
            directoryName = fileName.substring(0, fileName.lastIndexOf("."));

        }
        else {
            directoryName = fileName;
        }
        f = new File(directoryName);

        return f.mkdirs();
    }

    /**
     * 파일 복사
     * @param src
     * @param target
     * @return
     * @throws IOException
     * @deprecated 파일크기만큼 버퍼를 잡아 메모리 부담이 있슴. copy 메소드 이용
     * @see #copy
     */
    public static int fileCopy( String src, String target ) throws IOException {
        try {
            FileInputStream fis = new FileInputStream(src);
            int len = fis.available();
            byte buf[] = new byte[len];
            fis.read(buf, 0, len);
            fis.close();
            FileOutputStream fos = new FileOutputStream(target);
            fos.write(buf, 0, len);
            fos.close();
        }
        catch (Exception e) {
            return 0;
        }
        return 1;
    }

    /**
     * 
     * <P/>
     * 파일이 실제 서버에 존재하는지 확인
     *
     * @param url : "http://" 포함한 full URL
     * @return 
     * @throws Exception
     */
    public static boolean CheckUrl(String url) 
    {
        
        if(!url.startsWith("http://") ){
            return false;
        }
        try{
            URL oUrl = new URL(url);
            URLConnection oURLConnection = oUrl.openConnection();
            oURLConnection.connect(); 
            String szContent = oURLConnection.getContent().toString();
            
            return true;
            
        }catch(Exception e){
            
            return false;
        }
    }
    
    /**
     * 파일을 복사합니다. 
     * @param srcPath 소스파일
     * @param desPath 대상파일
     * @param overwrite 덮어쓰기 여부 true이면 덮어씀
     * @throws FileNotFoundException 소스파일이 존재 하지 않을때
     * @throws FileExistsException 덥어쓰기 여부가 false이고 대상파일이 이미 존재할때
     * @throws IOException 그외 IO 오류시
     */
    public static void copy(String srcPath, String desPath, boolean overwrite)
    	throws FileNotFoundException, FileExistsException, IOException {
    	copy(new File(srcPath), new File(desPath), overwrite);
    }
    
    
    /**
     * 파일을 복사합니다. 
     * @param src 소스파일
     * @param des 대상파일
     * @param overwrite 덮어쓰기 여부 true이면 덮어씀
     * @throws FileNotFoundException 소스파일이 존재 하지 않을때
     * @throws FileExistsException 덥어쓰기 여부가 false이고 대상파일이 이미 존재할때
     * @throws IOException 그외 IO 오류시
     */
    public static void copy(File src, File des, boolean overwrite)
    	throws FileNotFoundException, FileExistsException, IOException {
    	FileInputStream		fis;
    	
    	if (!src.exists()) {
    		throw new FileNotFoundException("source file " + src.getAbsolutePath() + " not found.");
    	}
    	if (!overwrite && des.exists()) {
    		throw new FileExistsException("destination file " + des.getAbsolutePath() + " is exists");
    	}
    	
    	fis	= new FileInputStream(src);
    	try {
        	FileOutputStream	fos;
        	
        	fos	= new FileOutputStream(des, false);
        	try {
            	byte[]	buf;
            	int		rsize;
            	
            	buf	= new byte[1024];
            	while ((rsize = fis.read(buf, 0, buf.length)) != -1) {
            		fos.write(buf, 0, rsize);
            	}
        	} finally {
        		fos.close();
        	}
    	} finally {
    		fis.close();
    	}
    }
    
    /**
     * 파일을 옮깁니다. 
     * @param srcPath 소스파일
     * @param desPath 대상파일
     * @param overwrite 덮어쓰기 여부 true이면 덮어씀
     * @throws FileNotFoundException 소스파일이 존재 하지 않을때
     * @throws FileExistsException 덥어쓰기 여부가 false이고 대상파일이 이미 존재할때
     * @throws IOException 그외 IO 오류시
     */
    public static void move(String srcPath, String desPath, boolean overwrite)
    	throws FileNotFoundException, FileExistsException, IOException {
    	move(new File(srcPath), new File(desPath), overwrite);
    }
    
    /**
     * 파일을 옮깁니다. 
     * @param src 소스파일
     * @param des 대상파일
     * @param overwrite 덮어쓰기 여부 true이면 덮어씀
     * @throws FileNotFoundException 소스파일이 존재 하지 않을때
     * @throws FileExistsException 덥어쓰기 여부가 false이고 대상파일이 이미 존재할때
     * @throws IOException 그외 IO 오류시
     */
    public static void move(File src, File des, boolean overwrite)
    	throws FileNotFoundException, FileExistsException, IOException {
    	if (!src.exists()) {
    		throw new FileNotFoundException("source file " + src.getAbsolutePath() + " not found.");
    	}
    	if (!overwrite && des.exists()) {
    		throw new FileExistsException("destination file " + des.getAbsolutePath() + " is exists");
    	}
    	des.getParentFile().mkdirs();
    	if (overwrite || !src.renameTo(des)) {
    		copy(src, des, overwrite);
    		src.delete();
    	}
    }
}
