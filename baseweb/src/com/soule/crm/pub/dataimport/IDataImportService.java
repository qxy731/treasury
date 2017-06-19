package com.soule.crm.pub.dataimport;

import java.util.List;

import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.ServiceException;

/*import com.neusoft.base.service.ILogonUserInfo;
import com.neusoft.base.service.ServiceException;*/

public interface IDataImportService {

    public List queryFileTypeList(ILogonUserInfo logonUser) throws ServiceException;

    public DataImportQueryOut query(DataImportQueryIn in) throws ServiceException;

    public DataImportQueryOut queryFileDetail(DataImportQueryIn in) throws ServiceException;
    
    public DataImportQueryOut queryAjaxRecord(DataImportQueryIn in) throws ServiceException;

    public DataBatchUploadOut deleteFile(DataImportQueryIn in) throws ServiceException;

    public void callUploadData(String uploadFileId, String uploadFileType, String monthId) throws ServiceException;

    public DataBatchUploadOut saveImpData(String uploadFileId, String monthId, String uploadFileType) throws ServiceException;

}
