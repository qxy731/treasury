package com.soule.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonValueProcessorImpl implements JsonValueProcessor {

    private String format = "yyyy-MM-dd HH:mm:ss";

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public JsonValueProcessorImpl() {
        super();
    }

    public JsonValueProcessorImpl(String format) {
        super();
        this.format = format;
    }

    public Object processArrayValue(Object value, JsonConfig jsonConfig) {
        String[] obj = {};
        if (value instanceof Date[]) {
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date[] dates = (Date[]) value;
            obj = new String[dates.length];
            for (int i = 0; i < dates.length; i++) {
                obj[i] = sf.format(dates[i]);
            }
        }
        return obj;
    }

    public Object processObjectValue(String key, Object value, JsonConfig jsonConfig) {
        if (value == null) {
            return "";
        }
        if (value instanceof java.util.Date) {
            Date d = (Date)value;
            String str = new SimpleDateFormat(format).format((Date) value);
            return str;
        }
        return value.toString();
    }

}
