package com.plate.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @作者:Rainsponsor
 * @E-mail:xianzel@163.com
 * @时间: 2015-2-2
 * @描述:文件操作工具类
 */
@SuppressWarnings("rawtypes")
public class FileUtils {
	
	/**
	 * 复制文件
	 * @param srcPath	 : 源文件地址
	 * @param targetPath : 目标文件地址
	 */
	public static void copyFile(String srcPath,String targetPath){
		try{
			//int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(srcPath);
			if (oldfile.exists()) {//文件存在时
				InputStream inStream = new FileInputStream(srcPath);//读入原文件
				FileOutputStream fs = new FileOutputStream(targetPath);
				byte[] buffer = new byte[1024];
				while ( (byteread = inStream.read(buffer)) != -1) {
					//bytesum += byteread;//字节数文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
	    }catch(Exception e) {
           System.out.println("复制单个文件操作出错");
           e.printStackTrace();
	    }
	}
	
	/**
     * 压缩文件
     * 
     * @param srcfile
     *            File[] 需要压缩的文件列表
     * @param zipfile
     *            File 压缩后的文件
     */
    public static void zipFiles(java.io.File[] srcfile, java.io.File zipfile) {
        byte[] buf = new byte[1024];
        try {
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                    zipfile));
            for (int i = 0; i < srcfile.length; i++) {
                FileInputStream in = new FileInputStream(srcfile[i]);
                out.putNextEntry(new ZipEntry(srcfile[i].getName()));
                //String str = srcfile[i].getName();
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.closeEntry();
                in.close();
            }
            out.close();
            System.out.println("压缩完成.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 解压缩
     * 
     * @param zipfile
     *            File 需要解压缩的文件
     * @param descDir
     *            String 解压后的目标目录
     */
	public static void unZipFiles(java.io.File zipfile, String descDir) {
        try {
            ZipFile zf = new ZipFile(zipfile);
            for (Enumeration entries = zf.entries(); entries.hasMoreElements();) {
                ZipEntry entry = ((ZipEntry) entries.nextElement());
                String zipEntryName = entry.getName();
                InputStream in = zf.getInputStream(entry);
                OutputStream out = new FileOutputStream(descDir + zipEntryName);
                byte[] buf1 = new byte[1024];
                int len;
                while ((len = in.read(buf1)) > 0) {
                    out.write(buf1, 0, len);
                }
                in.close();
                out.close();
                System.out.println("解压缩完成.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
