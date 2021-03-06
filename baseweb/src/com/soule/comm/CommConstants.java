package com.soule.comm;

/**
 * 常量定义
 *
 * @author Administrator
 *
 */
public class CommConstants {
	
	/*
	 * 默认字符集
	 */
    public final static String DEFAULT_CHARSET = "UTF-8";

    /**
     * 登陆用户信息
     */
    public static final String LOGON_USER_INFO = "logUserInfo";
    /**
     * WEB上下文路径
     */
    public static final String CONTEXT_PATH_FIELD = "_CONTEXT_PATH";
    /**
     * 多记录中记录状态域名
     */
    public static final String RECORD_STATUS = "__status";
    /**
     * 多记录中记录状态域名
     */
    public static final String RECORD_STATUS_DELETE = "delete";
    /**
     * 枚举值
     */
    public static final String ENUMS_MAP = "_ENUMS";
    /**
     * 授权值
     */
    public static final String AUTH_MAP = "_AUTHS";
    /**
     * 系统菜单ID
     */
    public static final String SYS_MENU_ROOT_ID = "sys";
    /**
     * URL资源表 自增长关键字
     */
    public static final String URL_SEQ = "SYS_RES_URL";
    
    
    public static final String DATA_FILE_FREQUENCY_DE = "DAY" ;//日
    
    public static final String DATA_FILE_FREQUENCY_TDE = "TENDAYS";//旬
	
	public static final String DATA_FILE_FREQUENCY_ME = "MONTH" ;//月
	
	public static final String DATA_FILE_FREQUENCY_QE = "QUARTER" ;//季
	
	public static final String DATA_FILE_FREQUENCY_HYE = "HALFYEAR" ;//半年
	
	public static final String DATA_FILE_FREQUENCY_YE = "YEAR" ;//年
	
	public static final String  IMPORT_TYPE_ONE = "1" ;//覆盖
	
	public static final String  IMPORT_TYPE_TWO = "2" ;//追加
}
