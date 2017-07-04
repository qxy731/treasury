package com.soule.comm.enu;

/**
 * 业务类型
 * @author wuwei
 *
 */
public enum BizType {
    /**
     * 角色，部门，员工，权限,登陆
     */
	ROLE("ROLE"),UNIT("UNIT"),STAFF("STAFF"),AUTH("AUTH"),LOGON("LOGON"),MM("MM"),CUSM("CUSM"),MKTM("MKTM"),PUBM("PUBM"),KNM("KNM"),PFM("PFM"),SYSM("SYSM"),REPM("REPM"),BUSM("BUSM"),OPER("OPER"),NOM("NOM");
	private String value;
    private BizType(String c) {
        value = c;
    }
    public String getValue() {
        return value;
    }
    
    public static BizType getInstance(String str) {
    	BizType ret = NOM;
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
            if ("MM".equals(str)) {
                ret = MM;
            }
            if ("CUSM".equals(str)) {
                ret = CUSM;
            }
            if ("MKTM".equals(str)) {
                ret = MKTM;
            }
            if ("PUBM".equals(str)) {
                ret = PUBM;
            }
            if ("KNM".equals(str)) {
                ret = KNM;
            }
            if ("PFM".equals(str)) {
                ret = PFM;
            }
            if ("SYSM".equals(str)) {
                ret = SYSM;
            }
            if ("REPM".equals(str)) {
                ret = REPM;
            }
            if ("BUSM".equals(str)) {
                ret = BUSM;
            }
            if ("OPER".equals(str)) {
                ret = OPER;
            }
            
        }
        return ret;
        
    }
}
