package com.soule.comm.enu;

/**
 * 业务类型
 * @author wuwei
 *
 */
public enum BizType {
    /**
     * 角色，国库，员工，权限,登陆,数据字典,系统管理,指标管理,模型管理，报表管理，批处理管理,其他
     */
	ROLE("ROLE"),UNIT("UNIT"),STAFF("STAFF"),AUTH("AUTH"),LOGON("LOGON"),ENUM("ENUM"),SYSM("SYSM"),TARM("TARM"),MODM("MODM"),REPM("REPM"),BATM("BATM"),OTHM("OTHM");
	private String value;
    private BizType(String c) {
        value = c;
    }
    public String getValue() {
        return value;
    }
    
    public static BizType getInstance(String str) {
    	BizType ret = OTHM;
        if (str!= null && str.length() > 0 ) {
        	if ("ROLE".equals(str)) {
                ret = ROLE;
            }
        	if ("UNIT".equals(str)) {
                ret = UNIT;
            }
        	if ("STAFF".equals(str)) {
                ret = STAFF;
            }
        	if ("AUTH".equals(str)) {
                ret = AUTH;
            }
        	if ("LOGON".equals(str)) {
                ret = LOGON;
            }
        	if ("ENUM".equals(str)) {
                ret = ENUM;
            }
            if ("SYSM".equals(str)) {
                ret = SYSM;
            }
            if ("TARM".equals(str)) {
                ret = TARM;
            }
            if ("MODM".equals(str)) {
                ret = MODM;
            }
            if ("REPM".equals(str)) {
                ret = REPM;
            }
            if ("BATM".equals(str)) {
                ret = BATM;
            }
        }
        return ret;
        
    }
}
