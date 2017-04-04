package com.soule.comm.tools;

import java.util.HashMap;

import com.soule.base.po.EnumType;
import com.soule.comm.po.IEnumType;

/**
 * 枚举值
 * 
 * @author wuwei
 */
public class EnumTypeUtil {

    private EnumTypeUtil() {
    }

    private static final HashMap<String, IEnumType> pool = new HashMap<String, IEnumType>();

    public static IEnumType getEnumType(String key) {
        IEnumType et = pool.get(key);

        if (et == null) {
            et = new EnumType(key);
        }

        return et;
    }

    public static void addEnumType(IEnumType et) {
        if (et != null) {
            pool.put(et.getKey(), et);
        }
    }

}
