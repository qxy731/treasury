package com.soule.comm.tools;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ContextHolder implements Serializable {
    private static final long serialVersionUID = 1L;
    private Map<String,Object> contexts = new HashMap<String,Object>();

    public Object get(String key) {
        return contexts.get(key);
    }

    public void put(String key, Object value) {
        contexts.put(key, value);
    }

    public void clear() {
        contexts.clear();
    }
}
