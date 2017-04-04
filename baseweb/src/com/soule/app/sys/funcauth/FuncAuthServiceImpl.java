package com.soule.app.sys.funcauth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.base.service.keygen.IKeyGenerator;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.FileUtil;
import com.soule.comm.tools.ObjectUtil;

/**
 * 功能权限资源维护业务操作
 */
@Service
public class FuncAuthServiceImpl implements IFuncAuthService {

    private final static Log logger = LogFactory.getLog(FuncAuthServiceImpl.class);
    @Autowired
    private IDefaultService sDefault;
    @Autowired
    private IKeyGenerator keyGen;
    /**
     * 按页面路径模糊查询资源
     * 
     * @throws DbAccessException
     */
    @SuppressWarnings("unchecked")
	public FuncAuthQueryOut query(FuncAuthQueryIn in) throws ServiceException {
        FuncAuthQueryOut out = new FuncAuthQueryOut();
        HashMap<String, String> condition = new HashMap<String, String>();
        try {
            if (in.getJspPath() != null) {
                condition.put("jspPath", in.getJspPath().replace('\\', '/'));
                List<FuncAuthRecordPo> data = sDefault.getIbatisMediator().find("func.getResFunction", condition);
                out.setRecord(data);
                AppUtils.setResult(out, MsgConstants.I0000);
            }
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002);
        } catch (Exception e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, MsgConstants.E0000);
        }
        return out;
    }

    /**
     * 新增功能权限资源
     * 
     * @throws DbAccessException
     */
    public FuncAuthInsertOut insert(FuncAuthInsertIn in) throws ServiceException {
        FuncAuthInsertOut out = new FuncAuthInsertOut();
        try {
            in.getNews().setFuncId(keyGen.getUUIDKey(in.getNews()));
            int ret = sDefault.getIbatisMediator().save("func.saveResFunction", in.getNews());
            if (ret == 1) {
                AppUtils.setResult(out, MsgConstants.I0001);
            }
            AppUtils.setResult(out, MsgConstants.I0000);
        } catch (DbAccessException e) {
            logger.error("DB", e);
            AppUtils.setResult(out, MsgConstants.E0002);
        } catch (Exception e) {
            logger.error("SERVICE", e);
            AppUtils.setResult(out, MsgConstants.E0000);
        }
        return out;
    }

    /**
     * 某一页面中功能权限资源的批量删除
     */
    public FuncAuthDeleteOut delete(FuncAuthDeleteIn in) throws ServiceException {
        FuncAuthDeleteOut out = new FuncAuthDeleteOut();
        if (ObjectUtil.isEmpty(in.getDeletes())) {
            AppUtils.setResult(out,MsgConstants.E0001);
            return out;
        }
        int count =0;
        for (FuncAuthRecordPo po: in.getDeletes()) {
            int ret = 0;
            try {
                ret = sDefault.getIbatisMediator().delete("func.delResFunction", po);
            } catch (DbAccessException e) {
                logger.error("DB",e);
            }
            if (ret ==1) {
                count ++;
            }
        }
        if ( count == 0 ){
            AppUtils.setResult(out,MsgConstants.W0000,"没有一条记录被删除");
        }
        else {
            AppUtils.setResult(out, MsgConstants.I0000);
        }
        return out;
    }

    /**
     * 从指定页面搜索功能权限资源
     */
    public FuncAuthParseOut parse(FuncAuthParseIn in) throws ServiceException {
        FuncAuthParseOut out = new FuncAuthParseOut();
        if (in.getJspPath() == null) {
            AppUtils.setResult(out,MsgConstants.E0001);
            return out;
        }
        FuncAuthQueryIn qIn = new FuncAuthQueryIn();
        qIn.setJspPath(in.getJspPath());
        FuncAuthQueryOut qOut = query(qIn);
        List<FuncAuthRecordPo> outpo = qOut.getRecord(); 
        List<FuncAuthRecordPo> newpo = parseFile(in.getRootPath(),in.getJspPath());
        for (FuncAuthRecordPo npo :newpo) {
            if (!alreadyExist(outpo,npo)) {
                FuncAuthInsertIn iIn = new FuncAuthInsertIn();
                iIn.setNews(npo);
                insert(iIn);
                outpo.add(npo);
            }
        }
        out.setPage(outpo);
        AppUtils.setResult(out,MsgConstants.I0000);
        return out;
    }

    private boolean alreadyExist(List<FuncAuthRecordPo> outpo, FuncAuthRecordPo npo) {
        for (FuncAuthRecordPo opo:outpo){
            if (opo.getFuncCode().equals(npo.getFuncCode())) {
                return true;
            }
        }
        return false;
    }

    private List<FuncAuthRecordPo> parseFile(String rootPath, String jspPath) {
        List<FuncAuthRecordPo> output = new ArrayList<FuncAuthRecordPo>();
        try {
            String fileContent = FileUtil.readTxtFile(rootPath +'/' + jspPath, 0);
            parseAuth(fileContent,output,"<n:auth ");
            parseAuth(fileContent,output,"<n:authif ");
            for (FuncAuthRecordPo po:output) {
                po.setJspPath(jspPath);
            }
        } catch (Exception e) {
            logger.error("SERVICE",e);
        }
        return output;
    }
    private void parseAuth(String fileContent,List<FuncAuthRecordPo> output,String prefix) {
        int sidx = 0;
        do {
            int eidx = fileContent.indexOf(prefix,sidx);
            if (eidx < 0) {
                break;
            }
            int tidx = fileContent.indexOf('>',eidx);
            if (tidx > eidx ) {
                int kidx = fileContent.indexOf('=',eidx + prefix.length());
                if (kidx > eidx && kidx < eidx + 40) {
                    String key = fileContent.substring(eidx + prefix.length(),kidx);
                    int vidx = fileContent.indexOf('/',kidx);
                    int veidx = tidx;
                    if (vidx > 0){
                        veidx = (tidx > vidx) ? vidx: tidx;
                    }
                    else{
                        veidx = tidx;
                    }
                    String val = fileContent.substring(kidx + 1,veidx);
                    if (val.length()>2) {
                        if (val.charAt(0) == val.charAt(val.length()-1) && (val.charAt(0) == '\''||val.charAt(0)=='"')){
                            val = val.substring(1,val.length()-1);
                        }
                        else{
                            val ="";
                        }
                    }
                    else {
                        val = "";
                    }
                    if (key.equals("key") && val.length() > 0) {
                        FuncAuthRecordPo po = new FuncAuthRecordPo();
                        po.setFuncId(keyGen.getUUIDKey(po));
                        po.setFuncCode(val);
                        po.setFuncName(val);
                        po.setFuncDesc("");
                        output.add(po);
                    }
                }
            }
            sidx = eidx +1;
        }while(true);
    }

}
