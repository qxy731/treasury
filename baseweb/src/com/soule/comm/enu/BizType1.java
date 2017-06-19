package com.soule.comm.enu;

/**
 * 业务类型
 * @author wuwei
 *
 */
public enum BizType1 {
    /**
     * 角色，机构，员工，权限,登陆
     */
    MM("MM"),CUSM("CUSM"),MKTM("MKTM"),PUBM("PUBM"),KNM("KNM"),PFM("PFM"),SYSM("SYSM"),REPM("REPM"),BUSM("BUSM"),OPER("OPER"),NOM("NOM");
	private String value;
    private BizType1(String c) {
        value = c;
    }
    public String getValue() {
        return value;
    }
    
    public static BizType1 getInstance(String str) {
    	BizType1 ret = NOM;
        if (str!= null && str.length() > 0 ) {
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
