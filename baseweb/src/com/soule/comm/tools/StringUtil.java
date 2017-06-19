package com.soule.comm.tools;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

public class StringUtil {
    public static String setString(String str){
        try {
            return new String(str.getBytes("UTF-8"),"ISO8859-1");
        } catch (UnsupportedEncodingException e) {          
            return null;
        }
    }
    
    public static String getString(String str){
        try {
            return new String(str.getBytes("ISO8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     *  分割字符串
     */
    public static String[] toArray(String str, char ch) {
        if (str == null)
            return null;
        if (str.indexOf(ch) == -1) {
            String[] r = {str};
            return r;
        }

        int num = 0;
        for (int k = 0; k < str.length(); k++) {
            if (str.charAt(k) == ch)
                num++;
        }
        String[] result = new String[num+1];
        str += ch;

        num = 0;
        int idx = -1;
        for (int i = 0; i < result.length; i++) {
            int end = str.indexOf(ch, idx + 1);
            result[num++] = str.substring(idx + 1, end);
            idx = end;
        }
        return result;
    }
    
    public static int parseInt(String val,int def) {
        int ret = 0;
        try{
            ret = Integer.parseInt(val.trim());
        }catch(Exception e){
            ret = def;
        }
        return ret;
    }
    
    public static StringBuilder linkString(Object ... val) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < val.length ; i++){
            sb.append(val[i]);
        }
        return sb;
    }
    
    /**
     * 如果字符串不满足最大长度，前面补0
     * @param len 最大长度
     * @param str 字符串
     * @return
     */
    public static String strFormat(int len, String str){
        if (StringUtils.isBlank(str)){
            return null;
        }
		int length = len - str.length();
		if (str.length() < len){
			for (int i = 0; i < length; i++){
				str = "0" + str;
			}
		}
		return str;
    }
}
