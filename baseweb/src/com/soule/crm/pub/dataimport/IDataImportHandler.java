package com.soule.crm.pub.dataimport;

import java.util.HashMap;
import java.util.List;

import com.soule.crm.tools.ErrorInfoPo;

/*import com.neusoft.crm.tools.ErrorInfoPo;
*/
public interface IDataImportHandler {
    /**
     * 数据导入前校验
     * @param errors    错误信息返回
     * @param modellist 表列定义信息(PUB_MODEL_UPLOAD)
     * @param datas     导入的数据
     */
    public void handle(List<ErrorInfoPo> errors,List<HashMap> modellist,List<HashMap> datas);
}
