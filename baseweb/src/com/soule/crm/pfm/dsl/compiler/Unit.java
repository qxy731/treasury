package com.soule.crm.pfm.dsl.compiler;

import java.util.ArrayList;
import java.util.List;

import com.soule.crm.pfm.dsl.ast.Type;

public class Unit {
    public String operator = null;
    
    public List oprands = new ArrayList();
    
    public Type returnedType;
    
    public Object ref;
    
    public int type;
    
    public static final int OPERATION = 1;
    
    public static final int FUNCTION = 2;
    
    //操作数，为10以上的常量
    public static final int NUMERIC = 11;
    
    public static final int CONSTANT = 12;
    
    public static final int ASSERT = 13;
    
}
