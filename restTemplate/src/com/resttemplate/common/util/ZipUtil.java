/**
 * 
 */
package com.resttemplate.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/**
 * @comment :
 * @date : 2013. 11. 25.
 * @author : Rejoice
 */
public class ZipUtil {

	public static int unZip(String zipFileDir, String newDir) {
		return ZipUtil.unZip(new File(zipFileDir + newDir));
	}

	public static int unZip(File fileName) {
		Enumeration entries;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(fileName, "EUC-KR");
			entries = zipFile.getEntries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String orgDirName = fileName.getParent() + File.separator;
				String entryFileName = entry.getName();

				if (entry.isDirectory()) {
					System.out.println("Extracting directory:" + entryFileName);
					(new File(orgDirName + entryFileName)).mkdir();
					continue;
				} else {
					String[] tmpSplit = null;
					try {
						tmpSplit = entryFileName.split(File.separator);
					} catch (Exception e) {
						tmpSplit = entryFileName.split("\\\\");
					}
					if (tmpSplit.length > 1) {
						String tmpDir = "";
						for (int i = 0; i < tmpSplit.length; i++) {
							tmpDir += (tmpSplit[i] + File.separator);
							tmpDir = orgDirName + tmpDir;
							File tmpFile = new File(tmpDir);
							if (!tmpFile.exists()) {
								tmpFile.mkdir();
							}
						}
						System.out.println("Extracting File: " + entryFileName);
						// FileParser.copyInputStream(zipFile.getInputStream(entry),
						// new BufferedOutputStream(new
						// FileOutputStream(orgDirName + entryFileName)));
						ZipUtil.unzipEntry(
								(ZipInputStream) zipFile.getInputStream(entry),
								new File(orgDirName + entryFileName));

					}
				}
			}

		} catch (Exception e) {
			return 0;
		} finally {
			// try{
			//
			// }catch(IOException e){
			// if(zipFile != null){
			// zipFile.close();
			// }
			// }

		}
		return 1;
	}

	public static int unZip(File fileName, String newDir) {
		Enumeration entries;
		ZipFile zipFile = null;
		try {
			zipFile = new ZipFile(fileName, "EUC-KR");
			entries = zipFile.getEntries();
			while (entries.hasMoreElements()) {
				ZipEntry entry = (ZipEntry) entries.nextElement();
				String orgDirName = fileName.getParent() + File.separator;
				String entryFileName = entry.getName();
				if (entry.isDirectory()) {
					System.err
							.println("Extracting directory: " + entryFileName);
					(new File(orgDirName + entryFileName)).mkdir();
					continue;
				} else {
					String[] tmpSplit = null;
					try {
						tmpSplit = entryFileName.split(File.separator);
					} catch (Exception e) {
						tmpSplit = entryFileName.split("\\\\");
					}
					if (tmpSplit.length > 1) {
						String tmpDir = "";
						for (int i = 0; i < tmpSplit.length - 1; i++)
							tmpDir += (tmpSplit[i] + File.separator);
						tmpDir = orgDirName + tmpDir;
						File tmpFile = new File(tmpDir);
						if (!tmpFile.exists())
							tmpFile.mkdir();
					}
				}
				System.out.println("Extracting File: " + entryFileName);
				// FileParser.copyInputStream(zipFile.getInputStream(entry), new
				// BufferedOutputStream(new FileOutputStream(newDir +
				// File.separator + entryFileName)));
			}
		} catch (ZipException ze) {
			ze.printStackTrace();
			return 0;
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return 0;
		} finally {
			try {
				if (zipFile != null)
					zipFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}

	protected static File unzipEntry(ZipInputStream zis, File targetFile) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(targetFile);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = zis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}

		} catch (Exception e) {

		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return targetFile;
	}

	public static void main(String[] args) {
		ZipUtil.unZip(new File("C:\\data\\Desktop.zip"));
	}
}