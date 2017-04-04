package com.soule.comm.tools;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.RandomAccessFile;
import java.security.SecureRandom;

/**
 * DES加密算法工具类
 */
public class DESUtil {
    public static String strEncode;
    public static int A[] = { 31, -42, 53, -64, 75, -86, 97, -108 };
    private static final String keyStr = "SOULE.COM.WW";

    /**
     * 保存报文文件内容
     */
    private static void saveFile(byte[] strcont, String filename) throws Exception {
        RandomAccessFile infile = null;
        try {
            infile = new RandomAccessFile(filename, "rw");
            infile.write(strcont);
        } catch (Exception e) {
            throw e;
        } finally {
            infile.close();
        }
    }

    /**
     * 获取报文文件内容
     */
    private static byte[] readFile(String strfilename) throws Exception {
        byte[] filecontent = null;
        int i_size = 0;
        RandomAccessFile infile = null;
        try {
            infile = new RandomAccessFile(strfilename, "rw");
            i_size = (int) infile.length();
            filecontent = new byte[i_size];
            infile.read(filecontent, 0, i_size);
        } catch (Exception e) {
            throw e;
        } finally {
            infile.close();
        }
        return filecontent;
    }

    /**
     * 产生密钥文件，存放系统
     */
    private static void getKeyFile() throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 为我们选择的DES算法生成一个KeyGenerator对象
        KeyGenerator kg = KeyGenerator.getInstance("DES");
        kg.init(sr);
        // 生成密匙
        SecretKey key = kg.generateKey();
        // 获取密匙数据
        byte rawKeyData[] = key.getEncoded();
        saveFile(rawKeyData, "deskeyfile");
    }

    /**
     * 判断是否加密后是否落在ASCII为 33-126之间 ，并存入标志为和加密后的一个字符 参数一：ch1:加密前的每位字符 iLength:幅值
     */
    public static boolean isAsCII(char ch1, int iLength) {
        int iLevel = 0;
        int ch = 0;
        ch = ch1;

        do {// 至少执行一次
            ch = ch + iLength;// n*iLength;
            if (ch < 0)
                ch += 256;
            if (ch > 256)
                ch -= 256;
            iLevel++;
        } while (ch < 33 || ch > 126);
        temp.iLength = iLevel; // 存放循环次数
        temp.ch = String.valueOf(ch); // 存放经过转换后的字符
        return true;
    }

    /**
     * 判断是否解密后是否落在ASCII为 0-256之间,并存入解密后的每一个字符 <br>
     * 参数一：ch1:加密前的每位字符 iLength:幅值 iFlag:每位循环次数
     */
    public static boolean isAsCII_DeCode(char ch1, int iLength, char iFlag) {
        int ch = 0;
        ch = ch1;

        do {// 至少执行一次
            ch = ch + iLength * Integer.parseInt(String.valueOf(iFlag));
            // System.out.println("ch2="+ch);
            if (ch < 0)
                ch += 256;
            if (ch > 256)
                ch -= 256;
            // System.out.println("ch3="+ch);
        } while (ch < 0 || ch > 256);
        temp.ch = String.valueOf(ch);
        return true;
    }

    /**
     * 加密 输入为1-8加密前字符串 ，输出为加密后的串1-8,同时得到标志位
     */
    public static String pwdEncode(String strsrc) {
        strsrc = strsrc.trim();
        String temp_src = "";
        String temp_flag = "";

        if (strsrc.equals("")) {
            return "";
        }

        for (int i = 0; i < strsrc.length(); i++) {
            if (isAsCII(strsrc.charAt(i), A[i])) {
                temp_src += (String.valueOf((char) Integer.parseInt(temp.ch)));
                temp_flag += String.valueOf(temp.iLength);
            }
        }
        strEncode = temp_flag.trim();
        return temp_src;

    }

    /**
     * 解码 参数strsrc:1-8位原字符串 strFlag:1-8为标志位
     */
    public static String pwdDecode(String strsrc, String strFlag) {
        strsrc = strsrc.trim();
        strFlag = strFlag.trim();
        String strResult = "";
        int tmpvalue = 0;

        if (strsrc.equals("")) {
            return "";
        }
        if (strsrc.length() != strFlag.length()) {
            return "长度不一样，该串不能解码！";
        }

        for (int i = 0; i < strsrc.length(); i++) {
            tmpvalue = strsrc.charAt(i) - A[i] * (Integer.parseInt(String.valueOf(strFlag.charAt(i))));
            if (tmpvalue > 0) {
                tmpvalue = tmpvalue % 256;
            } else if (tmpvalue < 0) {
                tmpvalue = 256 - ((Math.abs(tmpvalue)) % 256);
            }
            strResult += (String.valueOf((char) tmpvalue));
        }
        return strResult;
    }

    /**
     * 数据加密
     */
    public static byte[] encode(byte[] data) throws Exception {
        if (data == null && data.length == 0) {
            return data;
        }

        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();

        // byte rawKeyData[] = readFile("d:/tmp/deskeyfile");
        byte rawKeyData[] = keyStr.getBytes();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, key, sr);
        // 正式执行加密操作
        byte encryptedData[] = cipher.doFinal(data);
        // 返回加密结果
        return encryptedData;
    }

    /**
     * 数据解密
     */
    public static byte[] decode(byte[] encryptedData) throws Exception {
        if (encryptedData == null && encryptedData.length == 0) {
            return encryptedData;
        }

        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // byte rawKeyData[] = readFile("d:/tmp/deskeyfile");
        byte rawKeyData[] = keyStr.getBytes();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(rawKeyData);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey key = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance("DES");
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, key, sr);
        // 正式执行解密操作
        byte decryptedData[] = cipher.doFinal(encryptedData);
        // 然后将解密后的数据转化成原来的类文件。
        return decryptedData;
    }
}

class temp {
    static int iLength = 0;
    static String ch = "";
}