package com.soule.comm.po;

import java.util.List;

import com.soule.comm.po.IEnumItem;

public interface IEnumType {

    String getKey();

    String toJsString();
    
    public List<IEnumItem> getItems() ;

}
