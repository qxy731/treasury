package com.soule.app.sys.enums;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.MsgConstants;
import com.soule.base.action.BaseAction;
import com.soule.comm.CommConstants;

@Namespace("/sys")
@Results( { @Result(name = "json", type = "json",params={"ignoreHierarchy","false"})})
public class EnumDetailAction extends BaseAction {

    private final static Log logger = LogFactory.getLog(EnumDetailAction.class);
    private static final long serialVersionUID = 1L;
    private String enumId=null;
    private EnumPo po = new EnumPo();
    @Autowired
    private ISysEnumService service;
    /**
     * 更新
     */
    private List<EnumItemPo> items = new ArrayList<EnumItemPo>();
 
    public String queryDetail() throws Exception {
        rows = service.queryEnumItem(enumId);
        this.retCode=MsgConstants.I0000;
        this.retMsg="操作成功";
        return JSON;
    }
    public String commitDetail() throws Exception {
        service.updateEnum(po);
        service.updateEnumItem(po.getEnumId(),items);
        this.retCode=MsgConstants.I0000;
        this.retMsg="操作成功";
        return JSON;
    }
    @SuppressWarnings({ "unchecked", "static-access" })
    public void setItems(String jsonData) {
        JSONArray jsonArray = JSONArray.fromObject(jsonData);
        this.items = (List<EnumItemPo>) jsonArray.toList(jsonArray, EnumItemPo.class);
        JSONObject[] arr = (JSONObject[]) jsonArray.toArray(new JSONObject[0]);
        List<EnumItemPo> ne = new ArrayList<EnumItemPo>();
        for (int i = 0; i < arr.length ; i++) {
            if (!CommConstants.RECORD_STATUS_DELETE.equals(arr[i].get(CommConstants.RECORD_STATUS))) {
                ne.add(items.get(i));
            }
        }
        this.items = ne;
    }
    public String getItems() {
        return "";
    }
    public String getEnumId() {
        return enumId;
    }
    public void setEnumId(String enumId) {
        this.enumId = enumId;
    }
    public EnumPo getPo() {
        return po;
    }
    public void setPo(EnumPo po) {
        this.po = po;
    }
    
}
