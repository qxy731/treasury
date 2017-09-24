package com.soule.app.sys.fileupdown;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.soule.base.service.ServiceException;

public interface IFileUploadDownService {
	
	public FileUploadDownOut uploadFile(List<File> file,List<String> fileName,List<String> fileType,List<String> importType,Map<String,Object> params) throws ServiceException;

}
