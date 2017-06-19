package com.soule.app.report.tools;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

public class SubRptUtils {
	public static HashMap getSumStaffTotal(HashMap context, List lst) {
		if (lst!=null && lst.size() > 0) {
			String arrs = getFieldTypeByName(lst.get(0));
			if (!arrs.equals("")) {
				arrs = arrs.substring(1);
				String[] strs = arrs.split(",");
				for (int i = 0; i < strs.length; i++) {
					context.put(strs[i] + "STol", 0.00);
				}
				for (Object o : lst) {
					String getter = "getMisc";

					Method method = null;
					try {
						method = o.getClass().getMethod(getter);
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Object value = null;
					try {
						value = method.invoke(o);
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (value.equals("综合客户经理")) {
						context = getFieldValue(context, o, strs);
					}

				}
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

			if (clasType.equals("class java.lang.Double") || clasType.equals("class java.lang.Integer") || clasType.equals("class java.math.BigDecimal")) {
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
						context.put(propName + "STol", (Double) value + (Double) context.get(propName + "STol"));
					}
				} else if (value instanceof Integer) {
					if (null != value) {
						context.put(propName + "STol", (Integer) value + (Double) context.get(propName + "STol"));
					}
				} else if (value instanceof BigDecimal) {
					if (null != value) {
						context.put(propName + "STol", ((BigDecimal) value).doubleValue() + (Double) context.get(propName + "STol"));
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
