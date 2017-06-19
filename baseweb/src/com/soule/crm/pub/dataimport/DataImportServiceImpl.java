package com.soule.crm.pub.dataimport;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapException;
import com.ibatis.sqlmap.engine.impl.ExtendedSqlMapClient;
import com.ibatis.sqlmap.engine.impl.SqlMapExecutorDelegate;
import com.ibatis.sqlmap.engine.mapping.parameter.BasicParameterMap;
import com.ibatis.sqlmap.engine.mapping.parameter.BasicParameterMapping;
import com.ibatis.sqlmap.engine.mapping.result.AutoResultMap;
import com.ibatis.sqlmap.engine.mapping.sql.simple.SimpleDynamicSql;
import com.ibatis.sqlmap.engine.mapping.statement.InsertStatement;
import com.ibatis.sqlmap.engine.mapping.statement.MappedStatement;
import com.ibatis.sqlmap.engine.type.DateOnlyTypeHandler;
import com.ibatis.sqlmap.engine.type.ObjectTypeHandler;
import com.soule.MsgConstants;
import com.soule.app.table.SysUploadFilePo;
import com.soule.base.media.DbAccessException;
/*import com.neusoft.MsgConstants;
import com.neusoft.app.table.SysUploadFilePo;
import com.neusoft.base.media.DbAccessException;
import com.neusoft.base.service.IDefaultService;
import com.neusoft.base.service.ILogonUserInfo;
import com.neusoft.base.service.ServiceException;
import com.neusoft.base.service.auth.AdminAuthority;
import com.neusoft.base.service.auth.ResourceAuthority;
import com.neusoft.comm.StringUtils;
import com.neusoft.comm.enu.ResourceType;
import com.neusoft.comm.po.IEnumItem;
import com.neusoft.comm.po.IEnumType;
import com.neusoft.comm.tools.AppUtils;
import com.neusoft.comm.tools.ContextUtil;
import com.neusoft.comm.tools.EnumTypeUtil;
import com.neusoft.comm.tools.StringUtil;
import com.neusoft.crm.comm.IUserManager;
import com.neusoft.crm.tools.ExcelUtil;
import com.neusoft.crm.tools.TimeTool;*/
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.base.service.ServiceException;
import com.soule.base.service.auth.AdminAuthority;
import com.soule.base.service.auth.ResourceAuthority;
import com.soule.comm.StringUtils;
import com.soule.comm.enu.ResourceType;
import com.soule.comm.po.IEnumItem;
import com.soule.comm.po.IEnumType;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.ContextUtil;
import com.soule.comm.tools.EnumTypeUtil;
import com.soule.comm.tools.StringUtil;
import com.soule.crm.comm.IUserManager;
import com.soule.crm.tools.ExcelUtil;

@Service
@Qualifier("dataImportService")
public class DataImportServiceImpl implements IDataImportService {
    private final static Log logger = LogFactory.getLog(DataImportServiceImpl.class);

    @Autowired
    private IDefaultService sDefault;
    @Autowired
    private IUserManager um;

    public List queryFileTypeList(ILogonUserInfo logonUser) throws ServiceException {
        ArrayList<IEnumItem> selected = new ArrayList<IEnumItem>();
        IEnumType uf = EnumTypeUtil.getEnumType("uploadfile_type");
        for (IEnumItem po : uf.getItems()) {
            if (voteMenu(logonUser.getAuthorities(), po)) {
                selected.add(po);
            }
        }
        return selected;
    }

    protected boolean voteMenu(Collection<GrantedAuthority> auths, IEnumItem po) {
        for (GrantedAuthority ga : auths) {
            if (ga instanceof AdminAuthority) {
                return true;
            }
            if (!(ga instanceof ResourceAuthority)) {
                continue;
            }
            ResourceAuthority resAuth = (ResourceAuthority) ga;
            if (resAuth.getRunFlag() && resAuth.getResType() == ResourceType.DATAIMP) {
                String auth = (String) resAuth.getAuthority();
                if (auth.equals(po.getKey())) {
                    return true;
                }
            }
        }
        return false;
    }

    public DataImportQueryOut query(DataImportQueryIn in) throws ServiceException {
        DataImportQueryOut out = new DataImportQueryOut();

        try {
            String querySql = "dataimport.queryFileList";
            if (um.isCustMgr()) {
                in.setStaffId(um.getStaffId());
            }
            in.setOrgCode(um.getOrglst(in.getOrgCode()));
            long x = sDefault.getIbatisMediator().getRecordTotal(querySql, in);
            int pageOffset = (in.getInputHead().getPageNo() - 1) * in.getInputHead().getPageSize();
            if (pageOffset < x) {
                List ret = sDefault.getIbatisMediator().find(querySql, in, pageOffset, in.getInputHead().getPageSize());
                out.getResultHead().setTotal(x);
                out.setDatalist(ret);
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (Exception ex) {
            logger.error("SERVICE", ex);
            AppUtils.setResult(out, MsgConstants.E0002);
        }
        return out;
    }

    public DataBatchUploadOut deleteFile(DataImportQueryIn in) throws ServiceException {
        DataBatchUploadOut out = new DataBatchUploadOut();
        try {
            sDefault.getIbatisMediator().update("dataimport.deleteImpFile", in);
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, MsgConstants.E0001, "删除文件失败！");
        }
        return out;
    }

    public DataImportQueryOut queryFileDetail(DataImportQueryIn in) throws ServiceException {
        DataImportQueryOut out = new DataImportQueryOut();
        try {
            String querySql = "dataimport.findFileDetailList";
            PubImpFilePo pif = getPubImpFile(in.getFileType());
            if (pif == null) {
                throw new ServiceException(MsgConstants.E5000, "上传文件类型未定义");
            }
            in.setTableName(pif.getTargetTable());
            long x = sDefault.getIbatisMediator().getRecordTotal(querySql, in);
            int pageOffset = (in.getInputHead().getPageNo() - 1) * in.getInputHead().getPageSize();
            if (pageOffset < x) {
                List ret = sDefault.getIbatisMediator().find(querySql, in, pageOffset, in.getInputHead().getPageSize());
                out.getResultHead().setTotal(x);
                out.setDetailList(ret);
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException ex) {
            logger.error("DB", ex);
            AppUtils.setResult(out, MsgConstants.E0002);
        }
        return out;
    }
    
    public DataImportQueryOut queryAjaxRecord(DataImportQueryIn in) throws ServiceException {
        DataImportQueryOut out = new DataImportQueryOut();
        try {
            String querySql = "dataimport.findFileDetailList";
            PubImpFilePo pif = getPubImpFile(in.getFileType());
            if (pif == null) {
                throw new ServiceException(MsgConstants.E5000, "上传文件类型未定义");
            }
            in.setTableName(pif.getTargetTable());
            long x = sDefault.getIbatisMediator().getRecordTotal(querySql, in);
            out.setRecordTotle(x + "");
            in.setIsSuccess("3");
            long n = sDefault.getIbatisMediator().getRecordTotal(querySql, in);
            out.setRecordError(n + "");
            out.setRecordSuccess((x - n) + "");
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException ex) {
            logger.error("DB", ex);
            AppUtils.setResult(out, MsgConstants.E0002);
        }
        return out;
    }
    
    public void callUploadData(String uploadId, String uploadFileType, String monthId) throws ServiceException {
        try {
            List<SysUploadFilePo> list = sDefault.getIbatisMediator().find("Common.getSysUploadFileByImport", uploadId);
            PubImpFilePo pif = getPubImpFile(uploadFileType);
            if (pif == null) {
                throw new ServiceException(MsgConstants.E5000, "上传文件类型未定义");
            }
            for (int i = 0; i < list.size(); i++) {
                SysUploadFilePo uploadFile = (SysUploadFilePo) list.get(i);
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("V_FILE_ID", uploadFile.getFileId());
                params.put("V_MONTH", monthId);
                params.put("V_FILE_TYPE", uploadFileType);
                params.put("PROCEDURE_NAME", pif.getProcName());// 存储过程名称
                sDefault.getIbatisMediator().find("dataimport.callProcedureImp", params);
            }
        } catch (DbAccessException e) {
            logger.error("DB", e);
        }
    }

    private PubImpFilePo getPubImpFile(String uploadFileType) throws DbAccessException {
        PubImpFilePo pif = new PubImpFilePo();
        pif.setFileType(uploadFileType);
        pif = (PubImpFilePo) sDefault.getIbatisMediator().findById("dataimport.getPubImpFileByKey", pif);
        return pif;
    }

    public DataBatchUploadOut saveImpData(String uploadId, String monthId , String uploadFileType) throws ServiceException {
        DataBatchUploadOut out = new DataBatchUploadOut();
        try {
            List<SysUploadFilePo> list = sDefault.getIbatisMediator().find("Common.getSysUploadFileByImport", uploadId);
            if (list != null && list.size() > 0) {
                //List modellist = sDefault.getIbatisMediator().find("dataimport.getUploadModel", uploadFileType);
               /* PubImpFilePo pif = getPubImpFile(uploadFileType);
                if (pif == null) {
                    throw new ServiceException(MsgConstants.E5000, "上传文件类型未定义");
                }*/
                for (int i = 0; i < list.size(); i++) {
                    SysUploadFilePo uploadFile = (SysUploadFilePo) list.get(i);
                    //借用createTime 为数据日期
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = (Date)sdf.parse(monthId);
                    uploadFile.setCreateTime(date);
                    List onelist = new ArrayList();
                    onelist.add(uploadFile);
                  //  HashMap retmap = ExcelUtil.CheckFromXlsFile(onelist, modellist);
                  //  List errors = (List)retmap.get("msg");
                   /// if (errors.size() == 0) {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("uploadId", uploadId);
                        params.put("fileId", uploadFile.getFileId());
                        params.put("uploadFileType", uploadFileType);
                        sDefault.getIbatisMediator().update("dataimport.updImpdateFile", params);
                      //  List datas = (List) retmap.get("list");
/*
                        //调用行的特殊定义的处理服务
                        if (datas!=null) {
                            for (int m = 0; m < modellist.size();m++) {
                                HashMap model = (HashMap)modellist.get(m);
                                String linespec = (String)model.get("SPEC");
                                if (linespec != null &&  linespec.trim().length() > 0) {
                                    String colname = (String)model.get("TITLE_NAME");
                                    String[] sers = StringUtils.toArray(linespec, ',');
                                    for (int s = 0 ; s < sers.length ; s++) {
                                        if (sers[i].trim().length() > 0) {
                                            Object sobj = ContextUtil.getBean(sers[i].trim());
                                            if (sobj != null && sobj instanceof IDataImportColumnHandler) {
                                                IDataImportColumnHandler idch = (IDataImportColumnHandler)sobj;
                                                for (int r = 0 ; r < datas.size(); r++) {
                                                    HashMap record = (HashMap)datas.get(r);
                                                    Object ret = idch.handle(errors,r, model, (String)record.get(colname));
                                                    record.put(colname,ret);
                                                }
                                            }
                                            else {
                                                throw new ServiceException(MsgConstants.E5000, "上传文件处理服务("+sers[i]+")未找到");
                                            }
                                        }
                                    }
                                }
                            }
                        }*/
                        //调用特殊定义的处理服务
                       /* if (!StringUtil.isEmpty(pif.getSpecServices())) {
                            String[] sers = StringUtils.toArray(pif.getSpecServices(), ',');
                            for (int s = 0 ; s < sers.length ; s++) {
                                if (sers[s].trim().length() > 0) {
                                    Object sobj = ContextUtil.getBean(sers[s].trim());
                                    if (sobj != null && sobj instanceof IDataImportHandler) {
                                        IDataImportHandler idih = (IDataImportHandler)sobj;
                                        idih.handle(errors, modellist, datas);
                                    }
                                    else {
                                        throw new ServiceException(MsgConstants.E5000, "上传文件处理服务("+sers[i]+")未找到");
                                    }
                                }
                            }
                        }*/
                        //放入文件ID列定义
                       /* HashMap cl = new HashMap();
                        cl.put("TITLE_NAME", "FILE_ID");
                        modellist.add(cl);*/
                       /* if (errors.size() > 0) {
                            out.getResultHead().setRetCode(MsgConstants.E0003);
                            out.setErrors(errors);
                            return out;
                        } else {
                            prepareDyncBatch("dataimport.insert_" +pif.getTargetTable(),pif.getTargetTable(),modellist);
                            sDefault.executeBatch("dataimport.insert_" +pif.getTargetTable(), datas);
                        }*/
                    /*} else {
                        out.getResultHead().setRetCode(MsgConstants.E0003);
                        out.setErrors(errors);
                        return out;
                    }*/
                }
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (Exception ex) {
            logger.error("SERVICE", ex);
            AppUtils.setResult(out, MsgConstants.E5000, ex.getMessage());
        }
        return out;
    }

    private void prepareDyncBatch(String sqlid, String tableName, List modellist) {
        try {
            SqlMapClient sqlMapClient = sDefault.getIbatisMediator().getSqlMapClientTemplate().getSqlMapClient();
            if (sqlMapClient instanceof ExtendedSqlMapClient) {
                SqlMapExecutorDelegate delegate = ((ExtendedSqlMapClient) sqlMapClient).getDelegate();
                boolean exist = false;
                try {
                    delegate.getMappedStatement(sqlid);
                    exist = true;
                } catch (SqlMapException e) {
                    exist = false;
                }

                if (!exist) {
                    MappedStatement stemplate = delegate.getMappedStatement("dataimport.insertDataTemplate");
                    InsertStatement statement = new InsertStatement();
                    statement.setId(sqlid);
                    statement.setResultSetType(stemplate.getResultSetType());
                    statement.setFetchSize(stemplate.getFetchSize());
                    statement.setParameterClass(stemplate.getParameterClass());
                    SimpleDynamicSql sql = new SimpleDynamicSql(delegate,buildSqlString(tableName,modellist));
                    statement.setSql(sql);
                    statement.setSqlMapClient(sqlMapClient);
                    BasicParameterMap bpm = (BasicParameterMap) stemplate.getParameterMap();
                    BasicParameterMap nbpm = new BasicParameterMap(bpm.getDelegate());
                    nbpm.setParameterClass(nbpm.getClass());
                    nbpm.setId(sqlid+"-autoParametermap");
                    ArrayList parameterMappingList = new ArrayList();
                    for (int i = 0 ; i < modellist.size() ; i++) {
                        BasicParameterMapping bpp = new BasicParameterMapping();
                        bpp.setTypeHandler(new ObjectTypeHandler());
                        HashMap one = (HashMap) modellist.get(i);
                        String colname = one.get("TITLE_NAME").toString();
                        String coltype = (String)one.get("DATA_TYPE");
                        if (coltype != null && coltype.equalsIgnoreCase("DATE")) {
                           bpp.setTypeHandler(new DateOnlyTypeHandler());
                           bpp.setJavaType(java.sql.Date.class);
                           bpp.setJdbcTypeName("DATE");
                        }
                        bpp.setPropertyName(colname);
                        parameterMappingList.add(bpp);
                    }
                    nbpm.setParameterMappingList(parameterMappingList);
                    statement.setParameterMap(nbpm);

                    AutoResultMap resultMap = new AutoResultMap(delegate, false);
                    resultMap.setId(statement.getId() + "-AutoResultMap");
                    resultMap.setResultClass(Long.class);
                    resultMap.setResource("auto-create");
                    statement.setResultMap(resultMap);

                    delegate.addMappedStatement(statement);
                }
            }
        } catch (DbAccessException e) {
            logger.error("DB",e);
        }
    }

    private String buildSqlString(String tableName,List modellist) {
        StringBuilder sb = new StringBuilder();
        sb.append("insert into ").append(tableName).append("(");
        for (int i = 0 ; i < modellist.size() ; i++) {
            HashMap one = (HashMap) modellist.get(i);
            String colname = one.get("TITLE_NAME").toString();
            sb.append(colname).append(",");
        }
        sb.setCharAt(sb.length()-1, ')');
        sb.append(" VALUES(");
        for (int i = 0 ; i < modellist.size() ; i++) {
            HashMap one = (HashMap) modellist.get(i);
            String colname = one.get("TITLE_NAME").toString();
            sb.append("?,");
        }
        sb.setCharAt(sb.length()-1, ')');
        return sb.toString();
    }

}
