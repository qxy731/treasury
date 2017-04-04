package com.soule.app.sys.tree;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;

import com.soule.base.action.BaseAction;
import com.soule.comm.tools.ContextUtil;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

@Namespace("/sys")
public class TreeAction extends BaseAction {
    private static final long serialVersionUID = -5798085778409908461L;
    private final Log logger = LogFactory.getLog(TreeAction.class);
    private String nodeIdValue;
    private String service;
    private String data;

    @JSON(serialize = false)
    public String getSubTree() {
        PrintWriter pw = null;
        try {
            ITagTreeService treeService = (ITagTreeService) ContextUtil.getBean(this.service);
            Map hsmap = new HashMap();
            JSONObject jsonObject = JSONObject.fromObject(this.data);
            if (null != jsonObject && null != this.data) {
                Map map = (Map) jsonObject;
                hsmap = map;
            }
            List subTree = treeService.getTreeData(hsmap);
            List list = convertTree(subTree);
            String treeNodes = JSONArray.fromObject(list).toString();

            HttpServletResponse response = ServletActionContext.getResponse();
            response.setCharacterEncoding("UTF-8");
            pw = response.getWriter();
            pw.write(treeNodes);

        } catch (Exception e) {
            this.logger.error("ACTION", e);
        } finally {
            pw.close();
        }
        return null;
    }

    private List convertTree(List listTree) throws Exception {
        ArrayList ret = new ArrayList();
        for (Object o : listTree) {
            if (o instanceof HashMap) {
                ((Map) o).put("children", "[]");
                ((Map) o).put("isexpand", false);
                ret.add(o);
            } else {
                Map hm = new HashMap();
                hm = BeanUtils.describe(o);
                hm.put("children", "[]");
                hm.put("isexpand", false);
                ret.add(hm);
            }
        }
        return ret;
    }

    public String getNodeIdValue() {
        return nodeIdValue;
    }

    public void setNodeIdValue(String nodeIdValue) {
        this.nodeIdValue = nodeIdValue;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}