package com.soule.comm.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 文件操作服务工具类
 */
public class FileUtil {
	/**
	 * 读取文本文件
	 * @param strFullFileName 目标文件
	 * @param iEncode 0-该文件未加密 1-该文件有加密
	 */
    public static String readTxtFile(String strFullFileName, int iEncode) throws Exception
	{
		String strOutput = "";

		RandomAccessFile infile = null;
		File testfile = null;

		byte[] strTemp = null;
		int i_size = 0;

		if ((strFullFileName != null) && (strFullFileName.trim().length() > 0)) {
			try {
				testfile = new File(strFullFileName.trim());
				if (!testfile.exists() || testfile.isDirectory()) {
					testfile = null;
					return "";
				}
				testfile = null;
				infile = new RandomAccessFile(strFullFileName.trim(), "rw");

				i_size = (int) infile.length();
				strTemp = new byte[i_size];
				infile.read(strTemp, 0, i_size);

				if (iEncode == 1) {
					strTemp = DESUtil.decode(strTemp);
				}
			} catch (Exception e) {
				System.out.println("Read file error : " + e.getMessage());
				throw e;
			} finally {
				if (infile != null) {
					try {
						infile.close();
					} catch (Exception e) {
						System.out.println("Encode Error : " + e.getMessage());
						throw e;
					}
				}
			}
		} else {
			strOutput = "";
			return strOutput;
		}
		if (strTemp != null && strTemp.length > 0) {
			strOutput = new String(strTemp);
		}

		return strOutput;
	}

	/**
	 * 功 能：保存文本文件 入口参数：strFullFileName 目标文件 iEncode 0-该文件未加密 1-该文件有加密 出口参数：
	 * 返回：int型：0 保存工作完成 修改备注：
	 */
	public static int saveTxtFile(String strFullFileName, String strContent, int iEncode) throws Exception
	{
		try {
			File f1 = new File(strFullFileName);
			RandomAccessFile infile = new RandomAccessFile(f1, "rw");
			byte[] contentBys = strContent.getBytes();
			if (iEncode == 1) {
				contentBys = DESUtil.encode(contentBys);
			}
			infile.write(contentBys);
			infile.close();
		} catch (Exception e) {
			System.err.println("UTIL:" + "写文本【" + strFullFileName + "】时发生异常，将删除已产生的文件。");
			File f2 = new File(strFullFileName);
			if (f2.exists())
				f2.delete();
			throw e;
		}
		return 0;
	}
    /**
     * 一次性获得文件内容
     * 
     * @param file
     * @throws IOException 
     */
    public static byte[] getFileContent(File file) throws IOException {
        FileInputStream fis = null;
        try {
            fis =new FileInputStream(file);
            byte[] bs = new byte[fis.available()];
            fis.read(bs);
            return bs;
        } finally {
            if (fis!= null){
                fis.close();
            }
        }
    }
}