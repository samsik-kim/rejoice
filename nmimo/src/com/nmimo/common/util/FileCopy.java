package com.nmimo.common.util;

import java.io.*;

public class FileCopy {
 
     private DataInputStream in;
     private DataOutputStream out;
 
     public FileCopy(String source, String target) throws Exception {
          in = new DataInputStream(new FileInputStream(source));
          out  = new DataOutputStream(new FileOutputStream(target));
     }
 
     public void start() throws Exception {
 
          byte[] buff = new byte[1024];
          int size;
 
          while((size = in.read(buff)) > -1)
               out.write(buff, 0, size);

          out.close();
          in.close();
     }
 
     public static void main(String args[]) throws Exception {
    	 
    	  String fileName = "201308025906_1545154308266337129.gif";
    	  String path1 ="D://WORK//MIMO//Project//workspace//nmimo//WebContent//messageBody//"+fileName;
    	  String path2 ="D://"+fileName;
    	 
          FileCopy copy = new FileCopy(path1, path2);
          copy.start();
     }
 
}
