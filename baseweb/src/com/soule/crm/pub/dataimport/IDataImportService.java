package com.soule.crm.pub.dataimport;

import com.soule.base.service.ServiceException;

public interface IDataImportService {

    public DataImportQueryOut query(DataImportQueryIn in) throws ServiceException;

    public DataImportErrorDetailOut queryFileDetail(DataImportErrorDetailIn in) throws ServiceException;
    
    public DataBatchUploadOut deleteFile(DataImportQueryIn in) throws ServiceException;
    
    public void deleteTargetData(String dataDate) throws ServiceException;

}
