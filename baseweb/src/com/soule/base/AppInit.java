package com.soule.base;

import java.lang.reflect.Method;
import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;

import net.sf.ezmorph.object.DateMorpher;
import net.sf.json.util.JSONUtils;

import com.soule.comm.ParamConstants;

public class AppInit {
    private static String clsname = "com.soule.base." + "o";

    private static void a() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 80; i++) {
            sb.append('*');
        }
        try {
            Class cls = Class.forName("com.soule." + "c" + "r" + "m" + "." + "l" + "i" + "c" + "e" + "n" + "s" + "e"
                    + "." + "L" + "i" + "c" + "e" + "n" + "s" + "e" + "D" + "a" + "t" + "a");
            Method meth = cls.getMethod("dump");
            Object obj = cls.newInstance();
            final byte[] bs = (byte[]) meth.invoke(obj, new Object[] {});
            ClassLoader loader = new ClassLoader(AppInit.class.getClassLoader()) {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException {
                    if (clsname.equals(name)) {
                        return defineClass(null, bs, 0, bs.length);
                    }
                    return super.findClass(name);
                }
            };
            Class<?> clz = loader.loadClass(clsname);
            Method meth1 = clz.getMethod("checkLicense", null);
            Object obj1 = clz.newInstance();
            meth1.invoke(obj1, new Object[] {});
        } catch (ClassNotFoundException e) {
            System.err.println(sb.toString());
            //System.err.println("License" + " Error: Please check license.jar in classpath");
            System.exit(-1);
        } catch (Exception e) {
            System.err.println(sb.toString());
            //System.err.println("License" + " Error:" + e.getMessage());
            System.exit(-1);
        }
    }

    public static void init(ServletContextEvent context) {
        a();
        // json转换日期格式指定
        String[] dateFmts = new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" };
        JSONUtils.getMorpherRegistry().registerMorpher(new DateMorpher(dateFmts));
        System.setProperty("user.timezone", "GMT+8");

        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("config/env/app");
            initParamConstants(resourceBundle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 工作流引擎初始化 FIXME 换地方
    }

    private static synchronized void initParamConstants(ResourceBundle resource) {
        ParamConstants.UPLOAD_ROOT = resource.getString("UPLOAD_ROOT");
        ParamConstants.DOWNLOAD_ROOT = resource.getString("DOWNLOAD_ROOT");
        String sw = resource.getString("START_WORKFLOW");
        if (sw != null && sw.equalsIgnoreCase("false")) {
            ParamConstants.SUPPORT_WORKFLOW = false;
        }
    }

}
