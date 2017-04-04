package com.soule.comm;

/**
 * 参数变量定义
 * 
 * @author wuwei
 *
 */
public class ParamConstants {
    /**
     * 从数据库一次性获取数据的记录最大数
     */
    public static int MAX_FETCH_SIZE = 100000;
    /**
     * 流水号跳跃的段长
     */
    public static int SEQ_LEN = 20;
    
    /**
     * 支持工作流
     */
    public static boolean SUPPORT_WORKFLOW = true;

    /**
     * 上传文件根目录
     */
    public static String UPLOAD_ROOT = "c:/temp/upload";
    /**
     * 下载目录根目录
     */
    public static String DOWNLOAD_ROOT = "c:/temp/download";
}
