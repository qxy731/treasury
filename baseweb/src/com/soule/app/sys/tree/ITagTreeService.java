package com.soule.app.sys.tree;

import java.util.List;
import java.util.Map;

import com.soule.base.service.ServiceException;

public interface ITagTreeService {
	
    public List getTreeData(Map nodedata) throws ServiceException;
}
