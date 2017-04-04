package com.soule.comm.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;

public class ObjectUtil {
    public static byte[] toBytes(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(os);
            oos.writeObject(obj);
            oos.flush();
            return os.toByteArray();
        } finally {
            try {
                if (os != null)
                    os.close();
            } catch (IOException e) {
            }
        }

    }

    /**
     * 字节流反序列化成对象
     * 
     * @param bytes
     *            字节流
     * @return 对象
     * @throws IOException
     */
    public static Object toObject(byte[] bytes) throws IOException {
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        return toObject(bis);
    }

    /**
     * 从输入流获得数据，反序列化为对象
     * 
     * @param is
     *            输入流
     * @return 对象
     * @throws IOException
     */
    public static Object toObject(InputStream is) throws IOException {
        if (is == null) {
            return null;
        }
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(is);
            return ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("编程错误", e);
        } finally {
            if (ois != null)
                try {
                    ois.close();
                } catch (Exception e) {
                }
        }
    }

    /**
     * 实例化
     * 
     * @param className
     * @return
     */
    public static Object toObject(Log logger, String className) {
        Class<? extends Object> cls;
        try {
            cls = Class.forName(className);
            Object object = cls.newInstance();
            return object;
        } catch (Exception e) {
            if (logger != null) {
                logger.error("ClassName=" + className, e);
            }
        }

        return null;
    }

    public static boolean isEmpty(Object object) {
        return object == null;
    }
    @SuppressWarnings("unchecked")
    public static boolean isEmpty(List list) {
        return list == null || list.size() == 0;
    }
    @SuppressWarnings("unchecked")
    public static boolean isEmpty(Map map) {
        return map == null || map.size() == 0;
    }
}
