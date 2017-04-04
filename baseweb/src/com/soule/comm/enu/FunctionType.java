package com.soule.comm.enu;

/**
 * 操作类型
 * @author wuwei
 *
 */
public enum FunctionType {
    /**
     * 新增，删除，修改，查询
     */
    INSERT("IN"),DELETE("DE"),UPDATE("UP"),QUERY("QU"),NORMAL("NO");
    private String value;
    private FunctionType(String c) {
        value = c;
    }
    public String getValue() {
        return value;
    }
    
    public static FunctionType getInstance(String str) {
        FunctionType ret = NORMAL;
        if (str!= null && str.length() > 0 ) {
            if ("IN".equals(str)) {
                ret = INSERT;
            }
            if ("DE".equals(str)) {
                ret = DELETE;
            }
            if ("UP".equals(str)) {
                ret = UPDATE;
            }
            if ("QU".equals(str)) {
                ret = QUERY;
            }
        }
        return ret;
    }
}
