package com.soule.app.report.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RptUtils {

    /**
     * 返回一行空值
     * 
     * @param vo
     * @return
     */
    public static List<HashMap> initNull() {
        List<HashMap> datas = new ArrayList<HashMap>();
        HashMap<String, String> a = new HashMap<String, String>();
        /*
         * Class clz = vo.getClass(); Field[] fields = clz.getDeclaredFields(); for (int i = 0; i <
         * fields.length; i++) { String propName = fields[i].getName(); a.put(propName, ""); }
         */
        datas.add(a);
        return datas;
    }

    /**
     * 求合计
     * 
     * @param context
     * @param lst
     * @param vo
     * @return
     */
    public static HashMap getSumTotal(HashMap context, List lst) {
        String arrs = getFieldTypeByName(lst.get(0));
        if (!arrs.equals("")) {
            arrs = arrs.substring(1);
            String[] strs = arrs.split(",");
            for (int i = 0; i < strs.length; i++) {
                context.put(strs[i] + "All", 0.00);
            }
            for (Object o : lst) {
                context = getFieldValue(context, o, strs);
            }
        }
        return context;
    }

    private static String getFieldTypeByName(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        String arrs = "";
        for (int i = 0; i < fields.length; i++) {
            String propName = fields[i].getName();
            String clasType = fields[i].getType().toString();

            if (clasType.equals("class java.lang.Double") || clasType.equals("class java.lang.Integer")
                    || clasType.equals("class java.math.BigDecimal")) {
                arrs += "," + propName;
            }
        }
        return arrs;
    }

    private static HashMap getFieldValue(HashMap context, Object o, String[] strs) {
        try {
            for (int i = 0; i < strs.length; i++) {
                String propName = strs[i];
                String firstLetter = propName.substring(0, 1).toUpperCase();
                String getter = "get" + firstLetter + propName.substring(1);
                Method method = o.getClass().getMethod(getter);
                Object value = method.invoke(o);

                if (value instanceof Double) {
                    if (null != value) {
                        context.put(propName + "All", (Double) value + (Double) context.get(propName + "All"));
                    }
                } else if (value instanceof Integer) {
                    if (null != value) {
                        context.put(propName + "All", (Integer) value + (Double) context.get(propName + "All"));
                    }
                } else if (value instanceof BigDecimal) {
                    if (null != value) {
                        context.put(propName + "All",
                                ((BigDecimal) value).doubleValue() + (Double) context.get(propName + "All"));
                    }
                }

            }
            return context;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }
}
